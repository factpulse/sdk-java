

# SubmitInvoiceRequest

Submit an invoice to Chorus Pro.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**credentials** | [**FactureElectroniqueRestApiSchemasChorusProChorusProCredentials**](FactureElectroniqueRestApiSchemasChorusProChorusProCredentials.md) |  |  [optional] |
|**invoiceNumber** | **String** | Invoice number |  |
|**invoiceDate** | **String** | Invoice date (ISO format: YYYY-MM-DD) |  |
|**paymentDueDate** | **String** |  |  [optional] |
|**structureId** | **Integer** | Chorus Pro recipient structure ID |  |
|**serviceCode** | **String** |  |  [optional] |
|**engagementNumber** | **String** |  |  [optional] |
|**totalNetAmount** | [**TotalNetAmount**](TotalNetAmount.md) |  |  |
|**vatAmount** | [**VatAmount**](VatAmount.md) |  |  |
|**totalGrossAmount** | [**TotalGrossAmount**](TotalGrossAmount.md) |  |  |
|**mainAttachmentId** | **Integer** |  |  [optional] |
|**mainAttachmentLabel** | **String** |  |  [optional] |
|**comment** | **String** |  |  [optional] |
|**purchaseOrderReference** | **String** |  |  [optional] |
|**contractReference** | **String** |  |  [optional] |



