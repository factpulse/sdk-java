

# VATLine

Represents a VAT breakdown line by rate.  For exemptions (categories E, AE, K, G, O), the fields `exemption_reason` and `vatex_code` are required per EN16931.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**taxableAmount** | [**TaxableAmount**](TaxableAmount.md) |  |  |
|**vatAmount** | [**VATAmount**](VATAmount.md) |  |  |
|**rate** | **String** |  |  [optional] |
|**manualRate** | [**ManualRate**](ManualRate.md) |  |  [optional] |
|**category** | **VATCategory** |  |  [optional] |
|**exemptionReason** | **String** |  |  [optional] |
|**vatexCode** | **String** |  |  [optional] |



