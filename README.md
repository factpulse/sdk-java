# FactPulse SDK Java

Official Java client for the FactPulse API - French electronic invoicing.

## Features

- **Factur-X**: Generation and validation of electronic invoices (MINIMUM, BASIC, EN16931, EXTENDED profiles)
- **Chorus Pro**: Integration with the French public sector invoicing platform
- **AFNOR PDP/PA**: Submission of flows compliant with the XP Z12-013 standard
- **Electronic signature**: PDF signature (PAdES-B-B, PAdES-B-T, PAdES-B-LT)
- **Simplified client**: JWT authentication and integrated polling via `helpers`

## Installation

### Maven

```xml
<dependency>
    <groupId>fr.factpulse</groupId>
    <artifactId>factpulse-sdk</artifactId>
    <version>3.1.0</version>
</dependency>
```

### Gradle

```groovy
implementation 'fr.factpulse:factpulse-sdk:3.1.0'
```

## Quick Start

The `helpers` package provides a simplified API with automatic authentication and polling:

```java
import fr.factpulse.sdk.helpers.FactPulseClient;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

// Create the client
FactPulseClient client = new FactPulseClient(
    "your_email@example.com",
    "your_password"
);

// Build the invoice using simplified format (auto-calculates totals)
Map<String, Object> invoiceData = new LinkedHashMap<>();
invoiceData.put("number", "INV-2025-001");

Map<String, Object> supplier = new LinkedHashMap<>();
supplier.put("name", "My Company SAS");
supplier.put("siret", "12345678901234");
supplier.put("iban", "FR7630001007941234567890185");
invoiceData.put("supplier", supplier);

Map<String, Object> recipient = new LinkedHashMap<>();
recipient.put("name", "Client SARL");
recipient.put("siret", "98765432109876");
invoiceData.put("recipient", recipient);

Map<String, Object> line = new LinkedHashMap<>();
line.put("description", "Consulting services");
line.put("quantity", 10);
line.put("unitPrice", 100.0);
line.put("vatRate", 20);
invoiceData.put("lines", Arrays.asList(line));

// Generate the Factur-X PDF
byte[] pdfBytes = client.generateFacturx(invoiceData, "source_invoice.pdf");

Files.write(Paths.get("invoice_facturx.pdf"), pdfBytes);
```

## Available Helpers (AmountHelpers class)

### amount(value)

Converts a value to a formatted string for monetary amounts.

```java
import static fr.factpulse.sdk.helpers.AmountHelpers.amount;

amount(1234.5);      // "1234.50"
amount("1234.56");   // "1234.56"
amount(null);        // "0.00"
```

### invoiceTotals(exclTax, vat, inclTax, amountDue, ...)

Creates a complete invoice totals object.

```java
import static fr.factpulse.sdk.helpers.AmountHelpers.invoiceTotals;

Map<String, Object> totals = invoiceTotals(
    1000.00,        // exclTax
    200.00,         // vat
    1200.00,        // inclTax
    1200.00,        // amountDue
    50.00,          // globalAllowanceAmount (optional)
    "Loyalty",      // globalAllowanceReason (optional)
    100.00          // prepayment (optional)
);
```

### invoiceLine(lineNumber, itemName, quantity, unitNetPrice, lineNetAmount, ...)

Creates an invoice line.

```java
import static fr.factpulse.sdk.helpers.AmountHelpers.invoiceLine;

Map<String, Object> line = invoiceLine(
    1,
    "Consulting services",
    5,
    200.00,
    1000.00,
    "20.00",  // vatRate
    "S",      // vatCategory: S, Z, E, AE, K
    "HOUR",   // unit: LUMP_SUM, PIECE, HOUR, DAY...
    null      // options Map (reference, etc.)
);
```

### vatLine(manualRate, taxableAmount, vatAmount, category)

Creates a VAT breakdown line.

```java
import static fr.factpulse.sdk.helpers.AmountHelpers.vatLine;

Map<String, Object> vat = vatLine(
    "20.00",    // manualRate
    1000.00,    // taxableAmount
    200.00,     // vatAmount
    "S"         // category: S, Z, E, AE, K
);
```

### postalAddress(line1, postalCode, city, ...)

Creates a structured postal address.

```java
import static fr.factpulse.sdk.helpers.AmountHelpers.postalAddress;

Map<String, Object> address = postalAddress(
    "123 Republic Street",
    "75001",
    "Paris",
    "FR",           // country (default: "FR")
    "Building A",   // line2 (optional)
    null            // line3 (optional)
);
```

### supplier(name, siret, addressLine1, postalCode, city, options)

Creates a complete supplier with automatic calculation of SIREN and intra-community VAT.

```java
import static fr.factpulse.sdk.helpers.AmountHelpers.supplier;

Map<String, Object> s = supplier(
    "My Company SAS",
    "12345678901234",
    "123 Example Street",
    "75001",
    "Paris",
    Map.of("iban", "FR7630006000011234567890189")
);
// SIREN and intra-community VAT automatically calculated
```

### recipient(name, siret, addressLine1, postalCode, city, options)

Creates a recipient (customer) with automatic calculation of SIREN.

```java
import static fr.factpulse.sdk.helpers.AmountHelpers.recipient;

Map<String, Object> r = recipient(
    "Client SARL",
    "98765432109876",
    "456 Test Avenue",
    "69001",
    "Lyon",
    null
);
```

## Zero-Trust Mode (Chorus Pro / AFNOR)

To pass your own credentials without server-side storage:

```java
import fr.factpulse.sdk.helpers.*;

ChorusProCredentials chorusCreds = new ChorusProCredentials(
    "your_client_id",
    "your_client_secret",
    "your_login",
    "your_password",
    true  // sandbox
);

AFNORCredentials afnorCreds = new AFNORCredentials(
    "https://api.pdp.fr/flow/v1",
    "https://auth.pdp.fr/oauth/token",
    "your_client_id",
    "your_client_secret"
);

FactPulseClient client = new FactPulseClient(
    "your_email@example.com",
    "your_password",
    null,  // apiUrl
    null,  // clientUid
    chorusCreds,
    afnorCreds,
    2000,  // pollingInterval
    120000,  // pollingTimeout
    1  // maxRetries
);
```

## Resources

- **API Documentation**: https://factpulse.fr/api/facturation/documentation
- **Support**: contact@factpulse.fr

## License

MIT License - Copyright (c) 2025 FactPulse
