

# SubmitCompleteInvoiceResponse

Complete response after automated submission.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**success** | **Boolean** | Invoice was successfully submitted |  |
|**destinationType** | [**DestinationTypeEnum**](#DestinationTypeEnum) | Destination type |  |
|**chorusResult** | [**ChorusProResult**](ChorusProResult.md) |  |  [optional] |
|**afnorResult** | [**AFNORResult**](AFNORResult.md) |  |  [optional] |
|**enrichedInvoice** | [**EnrichedInvoiceInfo**](EnrichedInvoiceInfo.md) | Enriched invoice data |  |
|**facturxPdf** | [**FacturXPDFInfo**](FacturXPDFInfo.md) | Generated PDF information |  |
|**signature** | [**SignatureInfo**](SignatureInfo.md) |  |  [optional] |
|**contentB64** | **String** | Generated Factur-X PDF (and signed if requested) base64-encoded |  |
|**message** | **String** | Return message |  |



## Enum: DestinationTypeEnum

| Name | Value |
|---- | -----|
| CHORUS_PRO | &quot;chorus_pro&quot; |
| AFNOR | &quot;afnor&quot; |



