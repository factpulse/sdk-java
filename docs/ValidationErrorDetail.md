

# ValidationErrorDetail

Validation error detail (aligned with AFNOR AcknowledgementDetail).  Unified format for all Factur-X validation errors, compatible with AFNOR XP Z12-013 standard.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**level** | **ErrorLevel** | Severity level: &#39;Error&#39; or &#39;Warning&#39; |  [optional] |
|**item** | **String** | Identifier of the concerned element (XPath, field, BR-FR rule, etc.) |  |
|**reason** | **String** | Error description |  |
|**source** | **ErrorSource** |  |  [optional] |
|**code** | **String** |  |  [optional] |



