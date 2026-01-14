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
    <version>3.0.14</version>
</dependency>
```

### Gradle

```groovy
implementation 'fr.factpulse:factpulse-sdk:3.0.14'
```

## Quick Start

The `helpers` package provides a simplified API with automatic authentication and polling:

```java
import fr.factpulse.sdk.helpers.FactPulseClient;
import fr.factpulse.sdk.helpers.AmountHelpers;
import static fr.factpulse.sdk.helpers.AmountHelpers.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

// Create the client
FactPulseClient client = new FactPulseClient(
    "your_email@example.com",
    "your_password"
);

// Build the invoice with helpers
Map<String, Object> invoiceData = new LinkedHashMap<>();
invoiceData.put("number", "INV-2025-001");
invoiceData.put("date", "2025-01-15");
invoiceData.put("supplier", supplier(
    "My Company SAS", "12345678901234",
    "123 Example Street", "75001", "Paris"
));
invoiceData.put("recipient", recipient(
    "Client SARL", "98765432109876",
    "456 Test Avenue", "69001", "Lyon"
));
invoiceData.put("totalAmount", totalAmount(1000.00, 200.00, 1200.00, 1200.00));
invoiceData.put("lines", Arrays.asList(
    invoiceLine(1, "Consulting services", 10, 100.00, 1000.00)
));
invoiceData.put("vatLines", Arrays.asList(
    vatLine("20.00", 1000.00, 200.00)
));

// Generate the Factur-X PDF
byte[] pdfBytes = client.generateFacturx(invoiceData, "source_invoice.pdf", "EN16931");

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

### totalAmount(excludingTax, vat, includingTax, due, ...)

Creates a complete TotalAmount object.

```java
import static fr.factpulse.sdk.helpers.AmountHelpers.totalAmount;

Map<String, Object> total = totalAmount(
    1000.00,        // excludingTax
    200.00,         // vat
    1200.00,        // includingTax
    1200.00,        // due
    50.00,          // discountIncludingTax (optional)
    "Loyalty",      // discountReason (optional)
    100.00          // prepayment (optional)
);
```

### invoiceLine(number, description, quantity, unitPrice, lineTotal, ...)

Creates an invoice line.

```java
import static fr.factpulse.sdk.helpers.AmountHelpers.invoiceLine;

Map<String, Object> line = invoiceLine(
    1,
    "Consulting services",
    5,
    200.00,
    1000.00,  // lineTotal required
    "20.00",  // vatRate
    "S",      // vatCategory: S, Z, E, AE, K
    "HOUR",   // unit: FIXED, PIECE, HOUR, DAY...
    null      // options Map (reference, etc.)
);
```

### vatLine(manualRate, baseExcludingTax, vatAmount, category)

Creates a VAT breakdown line.

```java
import static fr.factpulse.sdk.helpers.AmountHelpers.vatLine;

Map<String, Object> vat = vatLine(
    "20.00",    // manualRate
    1000.00,    // baseExcludingTax
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
