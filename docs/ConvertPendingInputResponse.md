

# ConvertPendingInputResponse

Reponse donnees manquantes.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**status** | **String** |  |  [optional] |
|**conversionId** | **String** |  |  |
|**message** | **String** |  |  [optional] |
|**extraction** | [**ExtractionInfo**](ExtractionInfo.md) |  |  |
|**extractedData** | **Map&lt;String, Object&gt;** | Donnees extraites par OCR au format FacturXInvoice |  |
|**missingFields** | [**List&lt;MissingField&gt;**](MissingField.md) |  |  |
|**resumeUrl** | **String** |  |  |
|**expiresAt** | **OffsetDateTime** |  |  |



