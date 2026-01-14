

# AggregatedTransactionInput

Aggregated transaction for B2C reporting (flux 10.3).  Represents daily aggregation by category (TLB1, TPS1, etc.). Each occurrence corresponds to one day + one currency + one category.  Source: Annexe 6 v1.9, bloc TG-31 \"Transactions\"

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**date** | **LocalDate** | Transaction date (TT-77) |  |
|**categoryCode** | **TransactionCategory** | Transaction category code (TT-81). Use TLB1 for goods, TPS1 for services. |  |
|**currency** | [**Currency**](Currency.md) |  |  [optional] |
|**taxExclusiveAmount** | [**Taxexclusiveamount**](Taxexclusiveamount.md) |  |  |
|**taxAmount** | [**Taxamount**](Taxamount.md) |  |  |
|**taxBreakdown** | [**List&lt;TaxBreakdownInput&gt;**](TaxBreakdownInput.md) | VAT breakdown by rate |  |
|**transactionCount** | **Integer** |  |  [optional] |
|**taxDueType** | **TaxDueDateType** |  |  [optional] |



