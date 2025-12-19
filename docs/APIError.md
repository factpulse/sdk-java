

# APIError

Standardized API error (aligned with AFNOR Error schema).  Unified format for all HTTP error responses.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**errorCode** | **String** | Alphanumeric code precisely identifying the error |  |
|**errorMessage** | **String** | Message describing the error (not intended for end user) |  |
|**details** | [**List&lt;ValidationErrorDetail&gt;**](ValidationErrorDetail.md) |  |  [optional] |



