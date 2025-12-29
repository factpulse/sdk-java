

# IncomingInvoice

Invoice received from a supplier via PDP/PA.  This model contains essential metadata extracted from incoming invoices, regardless of their source format (CII, UBL, Factur-X).  Amounts are Decimal in Python but will be serialized as strings in JSON to preserve monetary precision.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**flowId** | **String** |  |  [optional] |
|**sourceFormat** | **InvoiceFormat** | Invoice source format |  |
|**supplierReference** | **String** | Invoice number issued by the supplier (BT-1) |  |
|**documentType** | **InvoiceTypeCode** | Document type (BT-3) |  [optional] |
|**supplier** | [**IncomingSupplier**](IncomingSupplier.md) | Invoice issuer (SellerTradeParty) |  |
|**billingSiteName** | **String** | Recipient name / your company (BT-44) |  |
|**billingSiteSiret** | **String** |  |  [optional] |
|**issueDate** | **String** | Invoice date (BT-2) - YYYY-MM-DD |  |
|**dueDate** | **String** |  |  [optional] |
|**currency** | **String** | ISO currency code (BT-5) |  [optional] |
|**netAmount** | **String** | Total net amount (BT-109) |  |
|**vatAmount** | **String** | Total VAT amount (BT-110) |  |
|**grossAmount** | **String** | Total gross amount (BT-112) |  |
|**purchaseOrderNumber** | **String** |  |  [optional] |
|**contractReference** | **String** |  |  [optional] |
|**invoiceSubject** | **String** |  |  [optional] |
|**documentBase64** | **String** |  |  [optional] |
|**documentContentType** | **String** |  |  [optional] |
|**documentFilename** | **String** |  |  [optional] |



