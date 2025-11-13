

# DonneesFactureSimplifiees

Données simplifiées de la facture (format minimal pour auto-enrichissement).

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**numero** | **String** | Numéro de facture unique |  |
|**emetteur** | **Map&lt;String, Object&gt;** | Informations sur l&#39;émetteur (siret, iban, ...) |  |
|**destinataire** | **Map&lt;String, Object&gt;** | Informations sur le destinataire (siret, ...) |  |
|**lignes** | **List&lt;Map&lt;String, Object&gt;&gt;** | Lignes de la facture |  |
|**date** | **String** |  |  [optional] |
|**echeanceJours** | **Integer** | Échéance en jours (défaut: 30) |  [optional] |
|**commentaire** | **String** |  |  [optional] |
|**numeroBonCommande** | **String** |  |  [optional] |
|**numeroMarche** | **String** |  |  [optional] |



