

# PDPCredentials

PDP credentials for zero-storage strategy (Strategy B).  Allows providing PDP credentials directly in the request instead of storing them in Django.  Useful for: - Ad-hoc tests without persisting credentials - Temporary integrations - Development environments

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**flowServiceUrl** | **String** | Base URL of the AFNOR Flow Service |  |
|**directoryServiceUrl** | **String** |  |  [optional] |
|**tokenUrl** | **String** | OAuth2 server URL |  |
|**clientId** | **String** | OAuth2 Client ID |  |
|**clientSecret** | **String** | OAuth2 Client Secret (sensitive) |  |



