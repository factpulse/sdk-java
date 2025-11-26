

# APIError

Erreur API standardisée (alignée sur AFNOR Error schema).  Format unifié pour toutes les réponses d'erreur HTTP.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**errorCode** | **String** | Code alphanumérique identifiant précisément l&#39;erreur |  |
|**errorMessage** | **String** | Message décrivant l&#39;erreur (non destiné à l&#39;utilisateur final) |  |
|**details** | [**List&lt;ValidationErrorDetail&gt;**](ValidationErrorDetail.md) |  |  [optional] |



