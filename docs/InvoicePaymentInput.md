

# InvoicePaymentInput

Payment linked to a specific invoice (flux 10.2).  Used for B2B international invoice payments.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**invoiceId** | **String** | Invoice identifier |  |
|**invoiceDate** | **LocalDate** | Original invoice date |  |
|**paymentDate** | **LocalDate** | Payment date |  |
|**currency** | [**Currency**](Currency.md) |  |  [optional] |
|**amountsByRate** | [**List&lt;PaymentAmountByRate&gt;**](PaymentAmountByRate.md) | Payment amounts by VAT rate |  |



