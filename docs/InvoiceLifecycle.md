

# InvoiceLifecycle

Cycle de vie d'une facture specifique.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**sellerId** | **String** |  |  [optional] |
|**invoiceId** | **String** | Reference de la facture (IssuerAssignedID du CDAR) |  |
|**events** | [**List&lt;LifecycleEvent&gt;**](LifecycleEvent.md) | Evenements de cycle de vie tries chronologiquement |  [optional] |
|**totalEvents** | **Integer** | Nombre total d&#39;evenements |  |



