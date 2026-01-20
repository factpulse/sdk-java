

# RefuseeRequest

Requête simplifiée pour soumettre un statut REFUSÉE (210).  Statut obligatoire PPF - Le destinataire refuse la facture. Un code motif est OBLIGATOIRE (BR-FR-CDV-15).  Codes motif autorisés (BR-FR-CDV-CL-09_MDT-113_210): - TX_TVA_ERR: Taux de TVA erroné - MONTANTTOTAL_ERR: Montant total erroné - CALCUL_ERR: Erreur de calcul - NON_CONFORME: Non conforme - DOUBLON: Doublon - DEST_ERR: Destinataire erroné - TRANSAC_INC: Transaction incomplète - EMMET_INC: Émetteur inconnu - CONTRAT_TERM: Contrat terminé - DOUBLE_FACT: Double facturation - CMD_ERR: Commande erronée - ADR_ERR: Adresse erronée - REF_CT_ABSENT: Référence contrat absente

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
|**reasonCode** | **String** | Code motif du refus (obligatoire). Valeurs autorisées: TX_TVA_ERR, MONTANTTOTAL_ERR, CALCUL_ERR, NON_CONFORME, DOUBLON, DEST_ERR, TRANSAC_INC, EMMET_INC, CONTRAT_TERM, DOUBLE_FACT, CMD_ERR, ADR_ERR, REF_CT_ABSENT |  |
|**reasonText** | **String** |  |  [optional] |



