

# FacturXInvoice

Data model for an invoice to be converted to Factur-X.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**invoiceNumber** | **String** |  |  |
|**paymentDueDate** | **String** |  |  |
|**invoiceDate** | **String** |  |  [optional] |
|**submissionMode** | **SubmissionMode** |  |  |
|**recipient** | [**Recipient**](Recipient.md) |  |  |
|**supplier** | [**Supplier**](Supplier.md) |  |  |
|**invoicingFramework** | [**InvoicingFramework**](InvoicingFramework.md) |  |  |
|**references** | [**InvoiceReferences**](InvoiceReferences.md) |  |  |
|**totals** | [**InvoiceTotals**](InvoiceTotals.md) |  |  |
|**invoiceLines** | [**List&lt;InvoiceLine&gt;**](InvoiceLine.md) |  |  [optional] |
|**vatLines** | [**List&lt;VATLine&gt;**](VATLine.md) |  |  [optional] |
|**notes** | [**List&lt;InvoiceNote&gt;**](InvoiceNote.md) |  |  [optional] |
|**comment** | **String** |  |  [optional] |
|**currentUserId** | **Integer** |  |  [optional] |
|**supplementaryAttachments** | [**List&lt;SupplementaryAttachment&gt;**](SupplementaryAttachment.md) |  |  [optional] |
|**payee** | [**Payee**](Payee.md) |  |  [optional] |
|**deliveryParty** | [**DeliveryParty**](DeliveryParty.md) |  |  [optional] |
|**taxRepresentative** | [**TaxRepresentative**](TaxRepresentative.md) |  |  [optional] |
|**deliveryDate** | **String** |  |  [optional] |
|**billingPeriodStart** | **String** |  |  [optional] |
|**billingPeriodEnd** | **String** |  |  [optional] |
|**paymentReference** | **String** |  |  [optional] |
|**creditorReferenceId** | **String** |  |  [optional] |
|**directDebitMandateId** | **String** |  |  [optional] |
|**debtorIban** | **String** |  |  [optional] |
|**paymentTerms** | **String** |  |  [optional] |
|**allowancesCharges** | [**List&lt;AllowanceCharge&gt;**](AllowanceCharge.md) |  |  [optional] |
|**additionalDocuments** | [**List&lt;AdditionalDocument&gt;**](AdditionalDocument.md) |  |  [optional] |
|**buyerAccountingReference** | **String** |  |  [optional] |
|**paymentCard** | [**PaymentCard**](PaymentCard.md) |  |  [optional] |



