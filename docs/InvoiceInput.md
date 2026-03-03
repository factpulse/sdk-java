

# InvoiceInput

Invoice for B2B international reporting (flux 10.1).  Used for unitary declaration of international B2B invoices. Supports three scenarios: - B2Bi: French seller → Foreign buyer (issuer role = SE) - Bi2B: Foreign seller → French buyer (issuer role = BY) - Bi2Bi: Foreign seller → Foreign buyer (issuer role = SE or BY)  Source: Annexe 6 v1.9, bloc TG-8 \"Invoice\"

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**invoiceId** | **String** | Invoice identifier |  |
|**issueDate** | **LocalDate** | Invoice issue date |  |
|**typeCode** | **FactureElectroniqueRestApiSchemasEreportingInvoiceTypeCode** | Invoice type code |  [optional] |
|**currency** | [**Currency**](Currency.md) |  |  [optional] |
|**dueDate** | **LocalDate** |  |  [optional] |
|**sellerId** | **String** |  |  [optional] |
|**sellerSiren** | **String** |  |  [optional] |
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



