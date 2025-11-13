

# GenerateCertificateRequest

Requête pour générer un certificat X.509 auto-signé de test.  ⚠️ ATTENTION : Ce certificat est destiné uniquement aux TESTS. NE PAS utiliser en production ! Niveau eIDAS : SES (Simple Electronic Signature)

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**cn** | **String** | Common Name (CN) - Nom du certificat |  [optional] |
|**organisation** | **String** | Organisation (O) |  [optional] |
|**pays** | **String** | Code pays ISO 2 lettres (C) |  [optional] |
|**ville** | **String** | Ville (L) |  [optional] |
|**province** | **String** | Province/État (ST) |  [optional] |
|**email** | **String** |  |  [optional] |
|**dureeJours** | **Integer** | Durée de validité en jours |  [optional] |
|**tailleCle** | **Integer** | Taille de la clé RSA en bits |  [optional] |
|**passphraseCle** | **String** |  |  [optional] |
|**genererP12** | **Boolean** | Générer aussi un fichier PKCS#12 (.p12) |  [optional] |
|**passphraseP12** | **String** | Passphrase pour le fichier PKCS#12 |  [optional] |



