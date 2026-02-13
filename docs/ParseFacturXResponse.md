

# ParseFacturXResponse

Response from the /parse-facturx endpoint.  Contains the parsed invoice data, plus metadata about the detected format and profile.  **Response schema depends on source format:** - **CII / Factur-X**: ``invoice`` contains a ``FacturXInvoice`` dict — round-trip compatible   with ``/generate-invoice``. - **UBL**: ``invoice`` contains an ``IncomingInvoice`` dict (simplified extraction with   supplier, amounts, dates). Not directly compatible with ``/generate-invoice``.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**status** | [**StatusEnum**](#StatusEnum) | Parsing status |  |
|**invoice** | **Map&lt;String, Object&gt;** | Parsed invoice data. For CII/Factur-X: FacturXInvoice format (round-trip with /generate-invoice). For UBL: IncomingInvoice format (summary extraction). |  |
|**detectedFormat** | **String** |  |  [optional] |
|**detectedProfile** | **String** |  |  [optional] |
|**error** | **String** |  |  [optional] |



## Enum: StatusEnum

| Name | Value |
|---- | -----|
| SUCCESS | &quot;SUCCESS&quot; |
| FAILURE | &quot;FAILURE&quot; |



