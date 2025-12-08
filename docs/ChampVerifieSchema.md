

# ChampVerifieSchema

Un champ vérifié avec toutes ses informations (extraction + conformité + localisation).

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**businessTerm** | **String** | Business Term EN16931 (ex: BT-1) |  |
|**label** | **String** | Libellé du champ (ex: N° Facture) |  |
|**valeurPdf** | **String** |  |  [optional] |
|**valeurXml** | **String** |  |  [optional] |
|**statut** | **StatutChampAPI** | Statut de conformité |  |
|**message** | **String** |  |  [optional] |
|**confiance** | **BigDecimal** | Score de confiance (0-1) |  [optional] |
|**source** | **String** | Source d&#39;extraction |  [optional] |
|**bbox** | [**BoundingBoxSchema**](BoundingBoxSchema.md) |  |  [optional] |



