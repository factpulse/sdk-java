

# RefuseeRequest

Requête simplifiée pour soumettre un statut REFUSÉE (210).  **Usage** : Pour une facture REÇUE (vous êtes acheteur). L'acheteur refuse la facture et envoie le statut au vendeur.  Statut obligatoire PPF - Un code motif est OBLIGATOIRE (BR-FR-CDV-15).  Codes motif autorisés (BR-FR-CDV-CL-09_MDT-113_210): - TX_TVA_ERR, MONTANTTOTAL_ERR, CALCUL_ERR, NON_CONFORME, DOUBLON, - DEST_ERR, TRANSAC_INC, EMMET_INC, CONTRAT_TERM, DOUBLE_FACT, - CMD_ERR, ADR_ERR, REF_CT_ABSENT

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**invoiceId** | **String** | Identifiant de la facture (BT-1) |  |
|**invoiceIssueDate** | **LocalDate** | Date d&#39;émission de la facture (YYYY-MM-DD) |  |
|**invoiceSellerSiren** | **String** | SIREN du vendeur (destinataire du statut, MDT-129) |  |
|**invoiceSellerElectronicAddress** | **String** | Adresse électronique du vendeur (MDT-73) |  |
|**reasonCode** | **String** | Code motif du refus (obligatoire). Valeurs: TX_TVA_ERR, MONTANTTOTAL_ERR, CALCUL_ERR, NON_CONFORME, DOUBLON, DEST_ERR, TRANSAC_INC, EMMET_INC, CONTRAT_TERM, DOUBLE_FACT, CMD_ERR, ADR_ERR, REF_CT_ABSENT |  |
|**reasonText** | **String** |  |  [optional] |
|**senderSiren** | **String** |  |  [optional] |
|**flowType** | **String** | Type de flux (SupplierInvoiceLC pour facture reçue) |  [optional] |
|**pdpFlowServiceUrl** | **String** |  |  [optional] |
|**pdpTokenUrl** | **String** |  |  [optional] |
|**pdpClientId** | **String** |  |  [optional] |
|**pdpClientSecret** | **String** |  |  [optional] |



