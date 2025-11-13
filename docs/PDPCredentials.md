

# PDPCredentials

Credentials PDP pour la stratégie zero-storage (Strategy B).  Permet de fournir directement les credentials PDP dans la requête au lieu de les stocker dans Django.  Utile pour : - Tests ponctuels sans persister les credentials - Intégrations temporaires - Environnements de développement

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**flowServiceUrl** | **String** | URL de base du Flow Service AFNOR |  |
|**tokenUrl** | **String** | URL du serveur OAuth2 |  |
|**clientId** | **String** | Client ID OAuth2 |  |
|**clientSecret** | **String** | Client Secret OAuth2 (sensible) |  |



