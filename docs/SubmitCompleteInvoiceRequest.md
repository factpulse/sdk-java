

# SubmitCompleteInvoiceRequest

Request to submit a complete invoice (generation + submission).  Workflow: 1. Auto-enrichment (optional) 2. Factur-X PDF generation 3. Signature (optional) 4. Submission to destination

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**invoiceData** | [**SimplifiedInvoiceData**](SimplifiedInvoiceData.md) | Invoice data in simplified format (see examples) |  |
|**sourcePdf** | **String** | Base64-encoded source PDF (will be transformed to Factur-X) |  |
|**destination** | [**Destination**](Destination.md) |  |  |
|**signature** | [**SignatureParameters**](SignatureParameters.md) |  |  [optional] |
|**options** | [**ProcessingOptions**](ProcessingOptions.md) | Processing options |  [optional] |



