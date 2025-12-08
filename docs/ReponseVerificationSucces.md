

# ReponseVerificationSucces

Réponse de vérification réussie avec données unifiées.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**estConforme** | **Boolean** | True si aucun écart critique |  |
|**scoreConformite** | **BigDecimal** | Score de conformité (0-100%) |  |
|**champsVerifies** | **Integer** | Nombre de champs vérifiés |  [optional] |
|**champsConformes** | **Integer** | Nombre de champs conformes |  [optional] |
|**estFacturx** | **Boolean** | True si le PDF contient du XML Factur-X |  [optional] |
|**profilFacturx** | **String** |  |  [optional] |
|**champs** | [**List&lt;ChampVerifieSchema&gt;**](ChampVerifieSchema.md) | Liste des champs vérifiés avec valeurs, statuts et coordonnées PDF |  [optional] |
|**notesObligatoires** | [**List&lt;NoteObligatoireSchema&gt;**](NoteObligatoireSchema.md) | Notes obligatoires (PMT, PMD, AAB) avec localisation PDF |  [optional] |
|**dimensionsPages** | [**List&lt;DimensionPageSchema&gt;**](DimensionPageSchema.md) | Dimensions de chaque page du PDF (largeur, hauteur) |  [optional] |
|**avertissements** | **List&lt;String&gt;** | Avertissements non bloquants |  [optional] |



