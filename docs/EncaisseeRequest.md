

# EncaisseeRequest

Requête simplifiée pour soumettre un statut ENCAISSÉE (212).  **Usage** : Pour une facture ÉMISE (vous êtes vendeur). Le vendeur confirme l'encaissement et envoie le statut à l'acheteur.  Statut obligatoire PPF - Le montant encaissé est OBLIGATOIRE (BR-FR-CDV-14).

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**invoiceId** | **String** | Identifiant de la facture (BT-1) |  |
|**invoiceIssueDate** | **LocalDate** | Date d&#39;émission de la facture (YYYY-MM-DD) |  |
|**invoiceBuyerSiren** | **String** | SIREN de l&#39;acheteur (destinataire du statut) |  |
|**invoiceBuyerElectronicAddress** | **String** | Adresse électronique de l&#39;acheteur (MDT-73) |  |
|**amount** | [**Amount**](Amount.md) |  |  |
|**currency** | **String** | Code devise ISO 4217 |  [optional] |
|**senderSiren** | **String** |  |  [optional] |
|**flowType** | **String** | Type de flux (CustomerInvoiceLC pour facture émise) |  [optional] |
|**pdpFlowServiceUrl** | **String** |  |  [optional] |
|**pdpTokenUrl** | **String** |  |  [optional] |
|**pdpClientId** | **String** |  |  [optional] |
|**pdpClientSecret** | **String** |  |  [optional] |



