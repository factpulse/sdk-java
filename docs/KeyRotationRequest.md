

# KeyRotationRequest

Request to rotate the client encryption key.  This operation re-encrypts all secrets from the old key to the new key. Both keys must be base64-encoded AES-256 keys (32 bytes each).

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**oldKey** | **String** | Current encryption key (base64-encoded AES-256) |  |
|**newKey** | **String** | New encryption key (base64-encoded AES-256) |  |



