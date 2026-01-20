

# SubmitCDARRequest

Requête de soumission CDAR (génération + envoi).

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**documentId** | **String** | Identifiant unique du message CDAR |  |
|**businessProcess** | **String** | Code processus métier (REGULATED, B2C, B2BINT, etc.) |  [optional] |
|**typeCode** | **String** | Type de message (23&#x3D;Traitement, 305&#x3D;Transmission) |  [optional] |
|**senderSiren** | **String** | SIREN de l&#39;émetteur (9 chiffres) |  |
|**senderRole** | **String** | Rôle de l&#39;émetteur (WK, SE, BY, etc.) |  [optional] |
|**senderName** | **String** |  |  [optional] |
|**senderEmail** | **String** |  |  [optional] |
|**recipients** | [**List&lt;RecipientInput&gt;**](RecipientInput.md) | Liste des destinataires |  [optional] |
|**invoiceId** | **String** | Identifiant de la facture (BT-1) |  |
|**invoiceIssueDate** | **LocalDate** | Date d&#39;émission de la facture (YYYY-MM-DD) |  |
|**invoiceTypeCode** | **String** | Type de document (380&#x3D;Facture, 381&#x3D;Avoir) |  [optional] |
|**invoiceSellerSiren** | **String** |  |  [optional] |
|**invoiceBuyerSiren** | **String** |  |  [optional] |
|**status** | **String** | Code statut de la facture (200-601) |  |
|**reasonCode** | **String** |  |  [optional] |
|**reasonText** | **String** |  |  [optional] |
|**actionCode** | **String** |  |  [optional] |
|**encaisseAmount** | [**Encaisseamount**](Encaisseamount.md) |  |  [optional] |
|**flowType** | **String** | Type de flux AFNOR (CustomerInvoiceLC, SupplierInvoiceLC, etc.) |  [optional] |
|**pdpFlowServiceUrl** | **String** |  |  [optional] |
|**pdpTokenUrl** | **String** |  |  [optional] |
|**pdpClientId** | **String** |  |  [optional] |
|**pdpClientSecret** | **String** |  |  [optional] |



