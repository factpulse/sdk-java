

# NoteObligatoireSchema

Note obligatoire détectée avec localisation et comparaison XML/PDF.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**codeSujet** | **String** | Code sujet (PMT, PMD, AAB) |  |
|**label** | **String** | Libellé (ex: Indemnité recouvrement) |  |
|**valeurPdf** | **String** |  |  [optional] |
|**valeurXml** | **String** |  |  [optional] |
|**statut** | **StatutChampAPI** | Statut de conformité (CONFORME si XML trouvé dans PDF) |  [optional] |
|**message** | **String** |  |  [optional] |
|**bbox** | [**BoundingBoxSchema**](BoundingBoxSchema.md) |  |  [optional] |



