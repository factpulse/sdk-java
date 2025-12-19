

# InvoiceLine

Represents a line item in an invoice.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**lineNumber** | **Integer** |  |  |
|**reference** | **String** |  |  [optional] |
|**itemName** | **String** |  |  |
|**quantity** | [**Quantity**](Quantity.md) |  |  |
|**unit** | **UnitOfMeasure** |  |  |
|**unitNetPrice** | [**UnitNetPrice**](UnitNetPrice.md) |  |  |
|**allowanceAmount** | [**InvoiceLineAllowanceAmount**](InvoiceLineAllowanceAmount.md) |  |  [optional] |
|**lineNetAmount** | [**LineNetAmount**](LineNetAmount.md) |  |  [optional] |
|**vatRate** | **String** |  |  [optional] |
|**manualVatRate** | [**ManualVatRate**](ManualVatRate.md) |  |  [optional] |
|**vatCategory** | **VATCategory** |  |  [optional] |
|**periodStartDate** | **String** |  |  [optional] |
|**periodEndDate** | **String** |  |  [optional] |
|**allowanceReasonCode** | **AllowanceReasonCode** |  |  [optional] |
|**allowanceReason** | **String** |  |  [optional] |



