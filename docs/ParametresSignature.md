

# ParametresSignature

Paramètres optionnels pour signer le PDF généré.  **MODE 1 - Certificat stocké (recommandé) :** Ne fournissez que les métadonnées (raison, localisation, etc.). Le certificat sera récupéré automatiquement via client_uid du JWT. Signature PAdES-B-LT conforme eIDAS.  **MODE 2 - Clés dans le payload (tests/cas spéciaux) :** Fournissez key_pem + cert_pem directement dans le payload. Format PEM accepté : brut (\"-----BEGIN...\") ou base64.  **Règle de sélection :** - Si key_pem ET cert_pem fournis → Mode 2 (clés payload) - Sinon → Mode 1 (certificat stocké récupéré via client_uid)

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**keyPem** | **String** |  |  [optional] |
|**certPem** | **String** |  |  [optional] |
|**keyPassphrase** | **String** |  |  [optional] |
|**raison** | **String** |  |  [optional] |
|**localisation** | **String** |  |  [optional] |
|**contact** | **String** |  |  [optional] |
|**fieldName** | **String** | Nom du champ de signature PDF |  [optional] |
|**usePadesLt** | **Boolean** | Activer PAdES-B-LT (archivage long terme). NÉCESSITE certificat avec accès OCSP/CRL |  [optional] |
|**useTimestamp** | **Boolean** | Activer l&#39;horodatage RFC 3161 avec FreeTSA (PAdES-B-T) |  [optional] |



