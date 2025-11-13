

# CredentialsAFNOR

Credentials AFNOR PDP optionnels.  **MODE 1 - Récupération via JWT (recommandé) :** Ne pas fournir ce champ `credentials` dans le payload. Les credentials PDP seront récupérées automatiquement via client_uid du JWT (0-trust).  **MODE 2 - Credentials dans le payload (zero-storage) :** Fournir tous les champs requis ci-dessous. Utile pour tests ou intégrations tierces.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**clientId** | **String** |  |  [optional] |
|**clientSecret** | **String** |  |  [optional] |
|**flowServiceUrl** | **String** |  |  [optional] |



