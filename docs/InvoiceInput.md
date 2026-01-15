

# InvoiceInput

Invoice for B2B international reporting (flux 10.1).  Used for unitary declaration of international B2B invoices.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**invoiceId** | **String** | Invoice identifier |  |
|**issueDate** | **LocalDate** | Invoice issue date |  |
|**typeCode** | **InvoiceTypeCode** | Invoice type code |  [optional] |
|**currency** | [**Currency**](Currency.md) |  |  [optional] |
|**dueDate** | **LocalDate** |  |  [optional] |
|**sellerSiren** | **String** | Seller SIREN/SIRET |  |
|**sellerVatId** | **String** |  |  [optional] |
|**sellerCountry** | [**Sellercountry**](Sellercountry.md) |  |  [optional] |
|**buyerId** | **String** |  |  [optional] |
|**buyerVatId** | **String** |  |  [optional] |
|**buyerCountry** | [**Buyercountry**](Buyercountry.md) |  |  |
|**taxExclusiveAmount** | [**Taxexclusiveamount1**](Taxexclusiveamount1.md) |  |  |
|**taxAmount** | [**Taxamount1**](Taxamount1.md) |  |  |
|**taxBreakdown** | [**List&lt;TaxBreakdownInput&gt;**](TaxBreakdownInput.md) | VAT breakdown |  |
|**referencedInvoiceId** | **String** |  |  [optional] |
|**referencedInvoiceDate** | **LocalDate** |  |  [optional] |



