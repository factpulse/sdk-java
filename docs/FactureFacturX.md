

# FactureFacturX

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



