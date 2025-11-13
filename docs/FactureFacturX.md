

# FactureFacturX

Modèle de données pour une facture destinée à être convertie en Factur-X.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**numeroFacture** | **String** |  |  |
|**dateEcheancePaiement** | **String** |  |  |
|**dateFacture** | **String** |  |  [optional] |
|**modeDepot** | **ModeDepot** |  |  |
|**destinataire** | [**Destinataire**](Destinataire.md) |  |  |
|**fournisseur** | [**Fournisseur**](Fournisseur.md) |  |  |
|**cadreDeFacturation** | [**CadreDeFacturation**](CadreDeFacturation.md) |  |  |
|**references** | [**References**](References.md) |  |  |
|**montantTotal** | [**MontantTotal**](MontantTotal.md) |  |  |
|**lignesDePoste** | [**List&lt;LigneDePoste&gt;**](LigneDePoste.md) |  |  [optional] |
|**lignesDeTva** | [**List&lt;LigneDeTVA&gt;**](LigneDeTVA.md) |  |  [optional] |
|**commentaire** | **String** |  |  [optional] |
|**idUtilisateurCourant** | **Integer** |  |  [optional] |
|**piecesJointesComplementaires** | [**List&lt;PieceJointeComplementaire&gt;**](PieceJointeComplementaire.md) |  |  [optional] |



