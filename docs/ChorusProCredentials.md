

# ChorusProCredentials

Chorus Pro credentials for Zero-Trust mode.  **Zero-Trust Mode**: Credentials are passed in each request and are NEVER stored.  **Security**: - Credentials are never persisted in the database - They are used only for the duration of the request - Secure transmission via HTTPS  **Use cases**: - High-security environments (banks, administrations) - Strict GDPR compliance - Tests with temporary credentials - Users who don't want to store their credentials

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**pisteClientId** | **String** | PISTE Client ID (government API portal) |  |
|**pisteClientSecret** | **String** | PISTE Client Secret |  |
|**chorusProLogin** | **String** | Chorus Pro login |  |
|**chorusProPassword** | **String** | Chorus Pro password |  |
|**sandbox** | **Boolean** | Use sandbox environment (true) or production (false) |  [optional] |



