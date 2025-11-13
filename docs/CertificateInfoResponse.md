

# CertificateInfoResponse

Informations sur un certificat généré.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**cn** | **String** | Common Name |  |
|**organisation** | **String** | Organisation |  |
|**pays** | **String** | Code pays |  |
|**ville** | **String** | Ville |  |
|**province** | **String** | Province |  |
|**email** | **String** |  |  [optional] |
|**sujet** | **String** | Sujet complet (RFC4514) |  |
|**emetteur** | **String** | Émetteur (auto-signé &#x3D; même que sujet) |  |
|**numeroSerie** | **Integer** | Numéro de série du certificat |  |
|**valideDu** | **String** | Date de début de validité (ISO 8601) |  |
|**valideAu** | **String** | Date de fin de validité (ISO 8601) |  |
|**algorithme** | **String** | Algorithme de signature |  |



