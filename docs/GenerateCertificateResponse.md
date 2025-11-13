

# GenerateCertificateResponse

Réponse après génération d'un certificat de test.  Contient le certificat PEM, la clé privée PEM, et optionnellement le PKCS#12.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**status** | **String** | Statut de l&#39;opération |  [optional] |
|**certificatPem** | **String** | Certificat X.509 au format PEM |  |
|**clePriveePem** | **String** | Clé privée RSA au format PEM |  |
|**pkcs12Base64** | **String** |  |  [optional] |
|**info** | [**CertificateInfoResponse**](CertificateInfoResponse.md) | Informations sur le certificat généré |  |
|**avertissement** | **String** | Avertissement sur l&#39;utilisation du certificat |  [optional] |



