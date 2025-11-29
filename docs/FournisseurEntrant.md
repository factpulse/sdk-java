

# FournisseurEntrant

Fournisseur extrait d'une facture entrante.  Contrairement au modèle Fournisseur de models.py, ce modèle n'a pas de champ id_fournisseur car cette information n'est pas disponible dans les XML Factur-X/CII/UBL.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**nom** | **String** | Raison sociale du fournisseur (BT-27) |  |
|**siren** | **String** |  |  [optional] |
|**siret** | **String** |  |  [optional] |
|**numeroTvaIntra** | **String** |  |  [optional] |
|**adressePostale** | [**AdressePostale**](AdressePostale.md) |  |  [optional] |
|**adresseElectronique** | [**AdresseElectronique**](AdresseElectronique.md) |  |  [optional] |
|**email** | **String** |  |  [optional] |
|**telephone** | **String** |  |  [optional] |



