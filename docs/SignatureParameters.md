

# SignatureParameters

Optional parameters to sign the generated PDF.  **MODE 1 - Stored certificate (recommended):** Only provide metadata (reason, location, etc.). The certificate will be automatically retrieved via client_uid from JWT. eIDAS compliant PAdES-B-LT signature.  **MODE 2 - Keys in payload (tests/special cases):** Provide key_pem + cert_pem directly in the payload. Accepted PEM format: raw (\"-----BEGIN...\") or base64.  **Selection rule:** - If key_pem AND cert_pem provided → Mode 2 (payload keys) - Otherwise → Mode 1 (stored certificate retrieved via client_uid)

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**keyPem** | **String** |  |  [optional] |
|**certPem** | **String** |  |  [optional] |
|**keyPassphrase** | **String** |  |  [optional] |
|**reason** | **String** |  |  [optional] |
|**location** | **String** |  |  [optional] |
|**contact** | **String** |  |  [optional] |
|**fieldName** | **String** | PDF signature field name |  [optional] |
|**usePadesLt** | **Boolean** | Enable PAdES-B-LT (long-term archival). REQUIRES certificate with OCSP/CRL access |  [optional] |
|**useTimestamp** | **Boolean** | Enable RFC 3161 timestamping with FreeTSA (PAdES-B-T) |  [optional] |



