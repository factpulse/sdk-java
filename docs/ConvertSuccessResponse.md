

# ConvertSuccessResponse

Reponse succes de conversion.  Le champ `invoice` contient les donnees de la facture au format FacturXInvoice (cf. facture_electronique.models.FacturXInvoice). Ce modele est le meme que celui utilise pour la generation Factur-X, garantissant une coherence totale.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**status** | **String** | Statut de la conversion |  [optional] |
|**conversionId** | **String** | Identifiant unique de conversion |  |
|**documentType** | [**DocumentTypeInfo**](DocumentTypeInfo.md) |  |  |
|**invoice** | **Map&lt;String, Object&gt;** | Donnees facture au format FacturXInvoice (cf. models.py) |  |
|**extraction** | [**ExtractionInfo**](ExtractionInfo.md) |  |  |
|**validation** | [**ValidationInfo**](ValidationInfo.md) |  |  |
|**files** | [**FilesInfo**](FilesInfo.md) |  |  |
|**processingTimeMs** | **Integer** | Temps de traitement en ms |  |
|**pdfRegenerated** | **Boolean** | True si le PDF a ete regenere (source non exploitable) |  [optional] |
|**pdfRegeneratedReason** | **String** |  |  [optional] |



