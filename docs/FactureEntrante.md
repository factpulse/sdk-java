

# FactureEntrante

Facture reçue d'un fournisseur via PDP/PA.  Ce modèle contient les métadonnées essentielles extraites des factures entrantes, quel que soit leur format source (CII, UBL, Factur-X).  Les montants sont en Decimal en Python mais seront sérialisés en string dans le JSON pour préserver la précision monétaire.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**flowId** | **String** |  |  [optional] |
|**formatSource** | **FormatFacture** | Format source de la facture |  |
|**refFournisseur** | **String** | Numéro de facture émis par le fournisseur (BT-1) |  |
|**typeDocument** | **TypeDocument** | Type de document (BT-3) |  [optional] |
|**fournisseur** | [**FournisseurEntrant**](FournisseurEntrant.md) | Émetteur de la facture (SellerTradeParty) |  |
|**siteFacturationNom** | **String** | Nom du destinataire / votre entreprise (BT-44) |  |
|**siteFacturationSiret** | **String** |  |  [optional] |
|**dateDePiece** | **String** | Date de la facture (BT-2) - YYYY-MM-DD |  |
|**dateReglement** | **String** |  |  [optional] |
|**devise** | **String** | Code devise ISO (BT-5) |  [optional] |
|**montantHt** | **String** | Montant HT total (BT-109) |  |
|**montantTva** | **String** | Montant TVA total (BT-110) |  |
|**montantTtc** | **String** | Montant TTC total (BT-112) |  |
|**numeroBonCommande** | **String** |  |  [optional] |
|**referenceContrat** | **String** |  |  [optional] |
|**objetFacture** | **String** |  |  [optional] |
|**documentBase64** | **String** |  |  [optional] |
|**documentContentType** | **String** |  |  [optional] |
|**documentFilename** | **String** |  |  [optional] |



