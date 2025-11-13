

# OptionsProcessing

Options de traitement pour la génération et la soumission.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**profilFacturx** | [**ProfilFacturxEnum**](#ProfilFacturxEnum) | Profil Factur-X à utiliser |  [optional] |
|**autoEnrichir** | **Boolean** | Auto-enrichir les données (APIs Entreprises, Chorus Pro, etc.) |  [optional] |
|**valider** | **Boolean** | Valider le XML Factur-X avec Schematron |  [optional] |
|**verifierParametresDestination** | **Boolean** | Vérifier les paramètres requis par la destination (ex: code_service pour Chorus) |  [optional] |



## Enum: ProfilFacturxEnum

| Name | Value |
|---- | -----|
| MINIMUM | &quot;MINIMUM&quot; |
| BASIC | &quot;BASIC&quot; |
| EN16931 | &quot;EN16931&quot; |
| EXTENDED | &quot;EXTENDED&quot; |



