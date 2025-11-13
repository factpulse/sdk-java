

# Utilisateur

Modèle Pydantic représentant les données de l'utilisateur authentifié.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **Integer** |  |  |
|**username** | **String** |  |  |
|**email** | **String** |  |  |
|**isActive** | **Boolean** |  |  |
|**isSuperuser** | **Boolean** |  |  [optional] |
|**isStaff** | **Boolean** |  |  [optional] |
|**bypassQuota** | **Boolean** |  |  [optional] |
|**teamId** | **Integer** |  |  [optional] |
|**hasQuota** | **Boolean** |  |  [optional] |
|**quotaInfo** | [**QuotaInfo**](QuotaInfo.md) |  |  [optional] |
|**isTrial** | **Boolean** |  |  [optional] |
|**clientUid** | **String** |  |  [optional] |



