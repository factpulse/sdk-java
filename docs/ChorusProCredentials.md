

# ChorusProCredentials

Credentials Chorus Pro pour mode Zero-Trust.  **Mode Zero-Trust** : Les credentials sont passés dans chaque requête et ne sont JAMAIS stockés.  **Sécurité** : - Les credentials ne sont jamais persistés dans la base de données - Ils sont utilisés uniquement pour la durée de la requête - Transmission sécurisée via HTTPS  **Cas d'usage** : - Environnements à haute sécurité (banques, administrations) - Conformité RGPD stricte - Tests avec credentials temporaires - Utilisateurs ne voulant pas stocker leurs credentials

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**pisteClientId** | **String** | Client ID PISTE (portail API gouvernement) |  |
|**pisteClientSecret** | **String** | Client Secret PISTE |  |
|**chorusProLogin** | **String** | Login Chorus Pro |  |
|**chorusProPassword** | **String** | Mot de passe Chorus Pro |  |
|**sandbox** | **Boolean** | Utiliser l&#39;environnement sandbox (true) ou production (false) |  [optional] |



