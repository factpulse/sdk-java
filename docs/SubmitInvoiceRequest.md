

# SubmitInvoiceRequest

Submit an invoice to Chorus Pro.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**credentials** | [**ChorusProCredentials**](ChorusProCredentials.md) |  |  [optional] |
|**invoiceNumber** | **String** | Invoice number |  |
|**invoiceDate** | **String** | Invoice date (ISO format: YYYY-MM-DD) |  |
|**paymentDueDate** | **String** |  |  [optional] |
|**structureId** | **Integer** | Chorus Pro recipient structure ID |  |
|**serviceCode** | **String** |  |  [optional] |
|**engagementNumber** | **String** |  |  [optional] |
|**totalNetAmount** | [**SubmitNetAmount**](SubmitNetAmount.md) |  |  |
|**vatAmount** | [**SubmitVatAmount**](SubmitVatAmount.md) |  |  |
|**totalGrossAmount** | [**SubmitGrossAmount**](SubmitGrossAmount.md) |  |  |
|**mainAttachmentId** | **Integer** |  |  [optional] |
|**mainAttachmentLabel** | **String** |  |  [optional] |
|**comment** | **String** |  |  [optional] |
|**purchaseOrderReference** | **String** |  |  [optional] |
|**contractReference** | **String** |  |  [optional] |



