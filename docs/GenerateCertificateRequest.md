

# GenerateCertificateRequest

Request to generate a self-signed X.509 test certificate.  WARNING: This certificate is intended for TESTING only. DO NOT use in production! eIDAS level: SES (Simple Electronic Signature)

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**cn** | **String** | Common Name (CN) - Certificate name |  [optional] |
|**organization** | **String** | Organization (O) |  [optional] |
|**country** | **String** | ISO 2-letter country code (C) |  [optional] |
|**city** | **String** | City (L) |  [optional] |
|**state** | **String** | State/Province (ST) |  [optional] |
|**email** | **String** |  |  [optional] |
|**validityDays** | **Integer** | Validity duration in days |  [optional] |
|**keySize** | **Integer** | RSA key size in bits |  [optional] |
|**keyPassphrase** | **String** |  |  [optional] |
|**generateP12** | **Boolean** | Also generate a PKCS#12 (.p12) file |  [optional] |
|**p12Passphrase** | **String** | Passphrase for PKCS#12 file |  [optional] |



