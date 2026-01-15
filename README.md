# FactPulse SDK Java

Official Java client for the FactPulse API.

## Installation

### Maven

```xml
<dependency>
    <groupId>fr.factpulse</groupId>
    <artifactId>factpulse-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

```groovy
implementation 'fr.factpulse:factpulse-sdk:1.0.0'
```

## Quick Start

```java
import fr.factpulse.sdk.FactPulseClient;
import fr.factpulse.sdk.models.*;

FactPulseClient client = new FactPulseClient.Builder()
    .email("your_email@example.com")
    .password("your_password")
    .build();

// Generate a Factur-X invoice
byte[] pdfBytes = client.generateFacturx(
    InvoiceData.builder()
        .number("INV-2025-001")
        .supplier(Supplier.builder().name("My Company").siret("12345678901234").build())
        .recipient(Recipient.builder().name("Client").siret("98765432109876").build())
        .build(),
    sourcePdfBytes,
    "EN16931"
);

Files.write(Path.of("facturx.pdf"), pdfBytes);
```

## License

MIT
