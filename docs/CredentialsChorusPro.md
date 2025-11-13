

# CredentialsChorusPro

Credentials Chorus Pro optionnels.  **MODE 1 - Récupération via JWT (recommandé) :** Ne pas fournir ce champ `credentials` dans le payload. Les credentials seront récupérés automatiquement via client_uid du JWT (0-trust).  **MODE 2 - Credentials dans le payload :** Fournir tous les champs requis ci-dessous. Utile pour tests ou intégrations tierces.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**pisteClientId** | **String** |  |  [optional] |
|**pisteClientSecret** | **String** |  |  [optional] |
|**chorusLogin** | **String** |  |  [optional] |
|**chorusPassword** | **String** |  |  [optional] |
|**modeSandbox** | **Boolean** | [MODE 2] Utiliser le mode sandbox (défaut: True) |  [optional] |



