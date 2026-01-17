

# FactureElectroniqueRestApiSchemasProcessingChorusProCredentials

Optional Chorus Pro credentials.  **MODE 1 - JWT retrieval (recommended):** Do not provide this `credentials` field in the payload. Credentials will be automatically retrieved via client_uid from JWT (0-trust).  **MODE 2 - Credentials in payload:** Provide all required fields below. Useful for tests or third-party integrations.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**pisteClientId** | **String** |  |  [optional] |
|**pisteClientSecret** | **String** |  |  [optional] |
|**chorusLogin** | **String** |  |  [optional] |
|**chorusPassword** | **String** |  |  [optional] |
|**sandboxMode** | **Boolean** | [MODE 2] Use sandbox mode (default: True) |  [optional] |



