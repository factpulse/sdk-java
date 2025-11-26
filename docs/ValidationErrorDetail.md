

# ValidationErrorDetail

Détail d'une erreur de validation (aligné sur AFNOR AcknowledgementDetail).  Format unifié pour toutes les erreurs de validation Factur-X, compatible avec la norme AFNOR XP Z12-013.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**level** | **ErrorLevel** | Niveau de gravité : &#39;Error&#39; ou &#39;Warning&#39; |  [optional] |
|**item** | **String** | Identifiant de l&#39;élément concerné (XPath, champ, règle BR-FR, etc.) |  |
|**reason** | **String** | Description de l&#39;erreur |  |
|**source** | **ErrorSource** |  |  [optional] |
|**code** | **String** |  |  [optional] |



