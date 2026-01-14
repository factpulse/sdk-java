

# InvoiceLine

Represents an invoice line item (BG-25).

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**lineNumber** | **Integer** | Invoice line identifier (BT-126). |  |
|**lineNote** | **String** |  |  [optional] |
|**parentLineId** | **String** |  |  [optional] |
|**lineSubType** | **LineSubType** |  |  [optional] |
|**reference** | **String** |  |  [optional] |
|**buyerAssignedId** | **String** |  |  [optional] |
|**productGlobalId** | **String** |  |  [optional] |
|**productGlobalIdScheme** | **String** |  |  [optional] |
|**itemName** | **String** | Item name (BT-153). |  |
|**itemDescription** | **String** |  |  [optional] |
|**originCountry** | **String** |  |  [optional] |
|**characteristics** | [**List&lt;ProductCharacteristic&gt;**](ProductCharacteristic.md) |  |  [optional] |
|**classifications** | [**List&lt;ProductClassification&gt;**](ProductClassification.md) |  |  [optional] |
|**quantity** | [**Quantity**](Quantity.md) |  |  |
|**unit** | **UnitOfMeasure** | Invoiced quantity unit of measure code (BT-130). |  |
|**grossUnitPrice** | [**GrossUnitPrice**](GrossUnitPrice.md) |  |  [optional] |
|**unitNetPrice** | [**UnitNetPrice**](UnitNetPrice.md) |  |  |
|**priceBasisQuantity** | [**PriceBasisQuantity**](PriceBasisQuantity.md) |  |  [optional] |
|**priceBasisUnit** | **String** |  |  [optional] |
|**priceAllowanceAmount** | [**PriceAllowanceAmount**](PriceAllowanceAmount.md) |  |  [optional] |
|**lineNetAmount** | [**LineNetAmount**](LineNetAmount.md) |  |  [optional] |
|**allowanceAmount** | [**InvoiceLineAllowanceAmount**](InvoiceLineAllowanceAmount.md) |  |  [optional] |
|**allowanceReasonCode** | **AllowanceReasonCode** |  |  [optional] |
|**allowanceReason** | **String** |  |  [optional] |
|**allowancesCharges** | [**List&lt;AllowanceCharge&gt;**](AllowanceCharge.md) |  |  [optional] |
|**vatRate** | **String** |  |  [optional] |
|**manualVatRate** | [**ManualVatRate**](ManualVatRate.md) |  |  [optional] |
|**vatCategory** | **VATCategory** |  |  [optional] |
|**periodStartDate** | **String** |  |  [optional] |
|**periodEndDate** | **String** |  |  [optional] |
|**purchaseOrderLineRef** | **String** |  |  [optional] |
|**accountingAccount** | **String** |  |  [optional] |
|**additionalDocuments** | [**List&lt;AdditionalDocument&gt;**](AdditionalDocument.md) |  |  [optional] |
|**lineNotes** | [**List&lt;InvoiceNote&gt;**](InvoiceNote.md) |  |  [optional] |



