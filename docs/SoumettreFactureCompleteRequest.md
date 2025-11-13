

# SoumettreFactureCompleteRequest

Requête pour soumettre une facture complète (génération + soumission).  Workflow : 1. Auto-enrichissement (optionnel) 2. Génération PDF Factur-X 3. Signature (optionnelle) 4. Soumission vers la destination

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**donneesFacture** | [**DonneesFactureSimplifiees**](DonneesFactureSimplifiees.md) | Données de la facture au format simplifié (voir exemples) |  |
|**pdfSource** | **String** | PDF source encodé en base64 (sera transformé en Factur-X) |  |
|**destination** | [**Destination**](Destination.md) |  |  |
|**signature** | [**ParametresSignature**](ParametresSignature.md) |  |  [optional] |
|**options** | [**OptionsProcessing**](OptionsProcessing.md) | Options de traitement |  [optional] |



