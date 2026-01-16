

# SimplifiedInvoiceData

Simplified invoice data (minimal format for auto-enrichment).

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**number** | **String** | Unique invoice number |  |
|**supplier** | **Map&lt;String, Object&gt;** | Supplier information (siret, iban, ...) |  |
|**recipient** | **Map&lt;String, Object&gt;** | Recipient information (siret, ...) |  |
|**lines** | **List&lt;Map&lt;String, Object&gt;&gt;** | Invoice lines |  |
|**date** | **String** |  |  [optional] |
|**dueDays** | **Integer** | Due date in days (default: 30) |  [optional] |
|**comment** | **String** |  |  [optional] |
|**purchaseOrderReference** | **String** |  |  [optional] |
|**contractReference** | **String** |  |  [optional] |
|**invoiceType** | **FactureElectroniqueModelsInvoiceTypeCode** | Document type (UNTDID 1001). Default: 380 (Invoice). |  [optional] |
|**precedingInvoiceReference** | **String** |  |  [optional] |
|**operationNature** | **OperationNature** |  |  [optional] |
|**invoicingFramework** | **InvoicingFrameworkCode** |  |  [optional] |



