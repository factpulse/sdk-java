# FactPulse SDK Java

Official Java client for the FactPulse API - French electronic invoicing.

## Features

- **Factur-X**: Generation and validation of electronic invoices (MINIMUM, BASIC, EN16931, EXTENDED profiles)
- **Chorus Pro**: Integration with the French public invoicing platform
- **AFNOR PDP/PA**: Submission of flows compliant with XP Z12-013 standard
- **Electronic signature**: PDF signing (PAdES-B-B, PAdES-B-T, PAdES-B-LT)
- **Thin HTTP wrapper**: Generic `post()` and `get()` methods with automatic JWT auth and polling

## Installation

### Maven

```xml
<dependency>
    <groupId>fr.factpulse</groupId>
    <artifactId>factpulse-sdk</artifactId>
    <version>4.1.2</version>
</dependency>
```

### Gradle

```groovy
implementation 'fr.factpulse:factpulse-sdk:4.1.2'
```

## Quick Start

```java
import fr.factpulse.sdk.FactPulseClient;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Create the client
        FactPulseClient client = new FactPulseClient(
            "your_email@example.com",
            "your_password",
            "your-client-uuid"  // From dashboard: Configuration > Clients
        );

        // Read your source PDF
        byte[] pdfBytes = Files.readAllBytes(Paths.get("source_invoice.pdf"));
        String pdfB64 = Base64.getEncoder().encodeToString(pdfBytes);

        // Build invoice data
        Map<String, Object> invoiceData = new LinkedHashMap<>();
        invoiceData.put("number", "INV-2025-001");

        Map<String, Object> supplier = new LinkedHashMap<>();
        supplier.put("name", "ACME Corporation");
        supplier.put("siret", "12345678901234");
        supplier.put("iban", "FR7630001007941234567890185");
        supplier.put("routing_address", "12345678901234");
        invoiceData.put("supplier", supplier);

        Map<String, Object> recipient = new LinkedHashMap<>();
        recipient.put("name", "Client Company SA");
        recipient.put("siret", "98765432109876");
        recipient.put("routing_address", "98765432109876");
        invoiceData.put("recipient", recipient);

        Map<String, Object> line = new LinkedHashMap<>();
        line.put("description", "Consulting services");
        line.put("quantity", 10);
        line.put("unitPrice", 100.0);
        line.put("vatRate", 20.0);
        invoiceData.put("lines", Arrays.asList(line));

        // Generate Factur-X and submit to PDP in one call
        Map<String, Object> request = new LinkedHashMap<>();
        request.put("invoiceData", invoiceData);
        request.put("sourcePdf", pdfB64);
        request.put("profile", "EN16931");
        request.put("destination", Map.of("type", "afnor"));

        Map<String, Object> result = client.post(
            "processing/invoices/submit-complete-async",
            request
        );

        // PDF is in result.get("content") (auto-polled, auto-decoded)
        byte[] facturxPdf = (byte[]) result.get("content");
        Files.write(Paths.get("facturx_invoice.pdf"), facturxPdf);

        Map<String, Object> afnorResult = (Map<String, Object>) result.get("afnorResult");
        System.out.println("Flow ID: " + afnorResult.get("flowId"));
    }
}
```

## API Methods

The SDK provides two generic methods that map directly to API endpoints:

```java
// POST /api/v1/{path}
Map<String, Object> result = client.post("path/to/endpoint", data);

// GET /api/v1/{path}
Map<String, Object> result = client.get("path/to/endpoint", params);
```

### Common Endpoints

| Endpoint | Method | Description |
|----------|--------|-------------|
| `processing/invoices/submit-complete-async` | POST | Generate Factur-X + submit to PDP |
| `processing/generate-invoice` | POST | Generate Factur-X XML or PDF |
| `processing/validate-xml` | POST | Validate Factur-X XML |
| `processing/validate-facturx-pdf` | POST | Validate Factur-X PDF |
| `processing/sign-pdf` | POST | Sign PDF with certificate |
| `afnor/flow/v1/flows` | POST | Submit flow to AFNOR PDP |
| `afnor/incoming-flows/{flow_id}` | GET | Get incoming invoice |
| `chorus-pro/factures/soumettre` | POST | Submit to Chorus Pro |

## Webhooks

Instead of polling, you can receive results via webhook by adding `callbackUrl`:

```java
Map<String, Object> request = new LinkedHashMap<>();
request.put("invoiceData", invoiceData);
request.put("sourcePdf", pdfB64);
request.put("destination", Map.of("type", "afnor"));
request.put("callbackUrl", "https://your-server.com/webhook/factpulse");
request.put("webhookMode", "INLINE");  // or "DOWNLOAD_URL"

Map<String, Object> result = client.post(
    "processing/invoices/submit-complete-async",
    request
);

String taskId = (String) result.get("taskId");
// Result will be POSTed to your webhook URL
```

### Webhook Receiver Example (Spring Boot)

```java
import org.springframework.web.bind.annotation.*;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.HexFormat;

@RestController
public class WebhookController {

    private static final String WEBHOOK_SECRET = "your-shared-secret";

    @PostMapping("/webhook/factpulse")
    public Map<String, String> handleWebhook(
            @RequestBody String payload,
            @RequestHeader("X-Webhook-Signature") String signature) {

        if (!verifySignature(payload, signature)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid signature");
        }

        Map<String, Object> event = new ObjectMapper().readValue(payload, Map.class);
        String eventType = (String) event.get("event_type");
        Map<String, Object> data = (Map<String, Object>) event.get("data");

        if ("submission.completed".equals(eventType)) {
            Map<String, Object> afnorResult = (Map<String, Object>) data.get("afnorResult");
            System.out.println("Invoice submitted: " + afnorResult.get("flowId"));
        } else if ("submission.failed".equals(eventType)) {
            System.out.println("Submission failed: " + data.get("error"));
        }

        return Map.of("status", "received");
    }

    private boolean verifySignature(String payload, String signature) {
        if (!signature.startsWith("sha256=")) return false;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(WEBHOOK_SECRET.getBytes(), "HmacSHA256"));
            String expected = HexFormat.of().formatHex(mac.doFinal(payload.getBytes()));
            return expected.equals(signature.substring(7));
        } catch (Exception e) {
            return false;
        }
    }
}
```

### Webhook Event Types

| Event | Description |
|-------|-------------|
| `generation.completed` | Factur-X generated successfully |
| `generation.failed` | Generation failed |
| `validation.completed` | Validation passed |
| `validation.failed` | Validation failed |
| `signature.completed` | PDF signed |
| `submission.completed` | Submitted to PDP/Chorus |
| `submission.failed` | Submission failed |

## Zero-Storage Mode

Pass PDP credentials directly in the request (no server-side storage):

```java
Map<String, Object> destination = new LinkedHashMap<>();
destination.put("type", "afnor");
destination.put("flowServiceUrl", "https://api.pdp.example.com/flow/v1");
destination.put("tokenUrl", "https://auth.pdp.example.com/oauth/token");
destination.put("clientId", "your_pdp_client_id");
destination.put("clientSecret", "your_pdp_client_secret");

request.put("destination", destination);
```

## Error Handling

```java
import fr.factpulse.sdk.FactPulseClient;
import fr.factpulse.sdk.FactPulseException;

try {
    Map<String, Object> result = client.post("processing/validate-xml", data);
} catch (FactPulseException e) {
    System.out.println("Error: " + e.getMessage());
    System.out.println("Status code: " + e.getStatusCode());
    System.out.println("Details: " + e.getDetails());
}
```

## Available Helpers

The SDK provides the following helper classes:

- `FactPulseClient`: Main HTTP client with auto-auth and polling
- `FactPulseException`: Base exception class
- `FactPulseAuthException`: Authentication failure
- `FactPulseValidationException`: Validation errors with details
- `FactPulsePollingTimeoutException`: Task polling timeout

## Resources

- **API Documentation**: https://factpulse.fr/api/facturation/documentation
- **Support**: contact@factpulse.fr

## License

MIT License - Copyright (c) 2025 FactPulse
