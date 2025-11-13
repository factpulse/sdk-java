

# SoumettreFactureCompleteResponse

Réponse complète après soumission automatisée.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**succes** | **Boolean** | La facture a été soumise avec succès |  |
|**destinationType** | [**DestinationTypeEnum**](#DestinationTypeEnum) | Type de destination |  |
|**resultatChorus** | [**ResultatChorusPro**](ResultatChorusPro.md) |  |  [optional] |
|**resultatAfnor** | [**ResultatAFNOR**](ResultatAFNOR.md) |  |  [optional] |
|**factureEnrichie** | [**FactureEnrichieInfoOutput**](FactureEnrichieInfoOutput.md) | Données de la facture enrichie |  |
|**pdfFacturx** | [**PDFFacturXInfo**](PDFFacturXInfo.md) | Informations sur le PDF généré |  |
|**signature** | [**SignatureInfo**](SignatureInfo.md) |  |  [optional] |
|**pdfBase64** | **String** | PDF Factur-X généré (et signé si demandé) encodé en base64 |  |
|**message** | **String** | Message de retour |  |



## Enum: DestinationTypeEnum

| Name | Value |
|---- | -----|
| CHORUS_PRO | &quot;chorus_pro&quot; |
| AFNOR | &quot;afnor&quot; |



