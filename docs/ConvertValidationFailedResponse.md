

# ConvertValidationFailedResponse

Reponse echec de validation - inclut les donnees extraites pour correction.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**status** | **String** | Statut de la conversion |  [optional] |
|**conversionId** | **String** | Identifiant unique de conversion |  |
|**message** | **String** | Message explicatif |  [optional] |
|**extraction** | [**ExtractionInfo**](ExtractionInfo.md) | Informations sur l&#39;extraction OCR |  |
|**extractedData** | **Map&lt;String, Object&gt;** | Donnees extraites par OCR au format FacturXInvoice (a completer/corriger) |  |
|**missingFields** | [**List&lt;MissingField&gt;**](MissingField.md) | Champs manquants pour conformite Factur-X |  [optional] |
|**validation** | [**ValidationInfo**](ValidationInfo.md) | Resultat de la validation Factur-X |  |
|**resumeUrl** | **String** | URL pour reprendre la conversion avec corrections |  |
|**expiresAt** | **OffsetDateTime** | Expiration de la conversion (1h) |  |



