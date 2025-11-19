

# LigneDePoste

Représente une ligne de détail dans une facture.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**numero** | **Integer** |  |  |
|**reference** | **String** |  |  [optional] |
|**denomination** | **String** |  |  |
|**quantite** | [**Quantite**](Quantite.md) |  |  |
|**unite** | **Unite** |  |  |
|**montantUnitaireHt** | [**MontantUnitaireHt**](MontantUnitaireHt.md) |  |  |
|**montantRemiseHt** | **BigDecimal** | Montant de la remise HT. |  [optional] |
|**montantTotalLigneHt** | **BigDecimal** | Montant total HT de la ligne (quantité × prix unitaire - remise). |  [optional] |
|**tauxTva** | **String** |  |  [optional] |
|**tauxTvaManuel** | **BigDecimal** | Taux de TVA avec valeur manuelle. |  [optional] |
|**categorieTva** | **CategorieTVA** |  |  [optional] |
|**dateDebutPeriode** | **String** |  |  [optional] |
|**dateFinPeriode** | **String** |  |  [optional] |
|**codeRaisonReduction** | **CodeRaisonReduction** |  |  [optional] |
|**raisonReduction** | **String** |  |  [optional] |



