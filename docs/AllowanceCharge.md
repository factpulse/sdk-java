

# AllowanceCharge

Document-level or line-level allowance/charge.  Represents BG-20 (Document level allowances), BG-21 (Document level charges), BG-27 (Invoice line allowances), or BG-28 (Invoice line charges).

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**isCharge** | **Boolean** | True for charge, False for allowance (ChargeIndicator). |  |
|**amount** | [**Amount2**](Amount2.md) |  |  |
|**baseAmount** | [**BaseAmount**](BaseAmount.md) |  |  [optional] |
|**percentage** | [**Percentage**](Percentage.md) |  |  [optional] |
|**reason** | **String** |  |  [optional] |
|**reasonCode** | **AllowanceChargeReasonCode** |  |  [optional] |
|**vatCategory** | **VATCategory** |  |  [optional] |
|**vatRate** | [**VatRate**](VatRate.md) |  |  [optional] |



