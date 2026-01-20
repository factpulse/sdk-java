

# EncaisseeRequest

Requête simplifiée pour soumettre un statut ENCAISSÉE (212).  Statut obligatoire PPF - Le paiement a été effectué. Le montant encaissé est OBLIGATOIRE (BR-FR-CDV-14).

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**invoiceId** | **String** | Identifiant de la facture (BT-1) |  |
|**invoiceIssueDate** | **LocalDate** | Date d&#39;émission de la facture (YYYY-MM-DD) |  |
|**senderSiren** | **String** |  |  [optional] |
|**flowType** | **String** | Type de flux: SupplierInvoiceLC (acheteur) ou CustomerInvoiceLC (vendeur) |  [optional] |
|**pdpFlowServiceUrl** | **String** |  |  [optional] |
|**pdpTokenUrl** | **String** |  |  [optional] |
|**pdpClientId** | **String** |  |  [optional] |
|**pdpClientSecret** | **String** |  |  [optional] |
|**amount** | [**Amount**](Amount.md) |  |  |
|**currency** | **String** | Code devise ISO 4217 |  [optional] |



