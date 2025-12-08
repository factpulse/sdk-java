

# LigneDeTVA

Représente une ligne de totalisation par taux de TVA.  Pour les exonérations (catégories E, AE, K, G, O), les champs `motif_exoneration` et `code_vatex` sont requis selon EN16931.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**montantBaseHt** | [**MontantBaseHt**](MontantBaseHt.md) |  |  |
|**montantTva** | [**MontantTvaLigne**](MontantTvaLigne.md) |  |  |
|**taux** | **String** |  |  [optional] |
|**tauxManuel** | [**Tauxmanuel**](Tauxmanuel.md) |  |  [optional] |
|**categorie** | **CategorieTVA** |  |  [optional] |
|**motifExoneration** | **String** |  |  [optional] |
|**codeVatex** | **String** |  |  [optional] |



