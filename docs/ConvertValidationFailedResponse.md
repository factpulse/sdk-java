

# ConvertValidationFailedResponse

Reponse echec de validation - inclut les donnees extraites pour correction.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**status** | **String** |  |  [optional] |
|**conversionId** | **String** |  |  |
|**message** | **String** |  |  [optional] |
|**extraction** | [**ExtractionInfo**](ExtractionInfo.md) |  |  |
|**extractedData** | **Map&lt;String, Object&gt;** | Donnees extraites par OCR au format FacturXInvoice (a completer/corriger) |  |
|**missingFields** | [**List&lt;MissingField&gt;**](MissingField.md) | Champs manquants pour conformite Factur-X |  [optional] |
|**validation** | [**ValidationInfo**](ValidationInfo.md) |  |  |
|**resumeUrl** | **String** | URL pour reprendre la conversion avec corrections |  |
|**expiresAt** | **OffsetDateTime** | Expiration de la conversion (1h) |  |



