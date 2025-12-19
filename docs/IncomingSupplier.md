

# IncomingSupplier

Supplier extracted from an incoming invoice.  Unlike the Supplier model in models.py, this model does not have a supplier_id field as this information is not available in Factur-X/CII/UBL XML files.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**name** | **String** | Supplier business name (BT-27) |  |
|**siren** | **String** |  |  [optional] |
|**siret** | **String** |  |  [optional] |
|**vatNumber** | **String** |  |  [optional] |
|**postalAddress** | [**PostalAddress**](PostalAddress.md) |  |  [optional] |
|**electronicAddress** | [**ElectronicAddress**](ElectronicAddress.md) |  |  [optional] |
|**email** | **String** |  |  [optional] |
|**phone** | **String** |  |  [optional] |



