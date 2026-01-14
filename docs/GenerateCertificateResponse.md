

# GenerateCertificateResponse

Response after generating a test certificate.  Contains certificate PEM, private key PEM, and optionally PKCS#12.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**status** | **String** | Operation status |  [optional] |
|**certificatePem** | **String** | X.509 certificate in PEM format |  |
|**privateKeyPem** | **String** | RSA private key in PEM format |  |
|**pkcs12Base64** | **String** |  |  [optional] |
|**info** | [**CertificateInfoResponse**](CertificateInfoResponse.md) | Generated certificate information |  |
|**warning** | **String** | Warning about certificate usage |  [optional] |



