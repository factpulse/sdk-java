

# PDPConfigUpdateRequest

PDP configuration update request.  For encryption_mode='double', the X-Encryption-Key header must also be provided containing a base64-encoded AES-256 key (32 bytes).

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**isActive** | **Boolean** | Whether config is active |  [optional] |
|**modeSandbox** | **Boolean** | Sandbox mode |  [optional] |
|**flowServiceUrl** | **String** | PDP Flow Service URL |  |
|**tokenUrl** | **String** | PDP OAuth token URL |  |
|**oauthClientId** | **String** | OAuth Client ID |  |
|**clientSecret** | **String** | OAuth Client Secret (sent but never returned) |  |
|**encryptionMode** | [**EncryptionModeEnum**](#EncryptionModeEnum) |  |  [optional] |



## Enum: EncryptionModeEnum

| Name | Value |
|---- | -----|
| FERNET | &quot;fernet&quot; |
| DOUBLE | &quot;double&quot; |



