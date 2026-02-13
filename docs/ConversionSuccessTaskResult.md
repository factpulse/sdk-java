

# ConversionSuccessTaskResult

Successful result from task_convert_document.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**status** | [**StatusEnum**](#StatusEnum) |  |  [optional] |
|**conversionId** | **String** |  |  |
|**documentTypeCode** | **Integer** |  |  |
|**profile** | **String** |  |  |
|**extraction** | [**ConversionExtractionInfo**](ConversionExtractionInfo.md) |  |  |
|**processingTimeMs** | **Integer** |  |  |
|**pdfRegenerated** | **Boolean** |  |  [optional] |
|**pdfRegeneratedReason** | **String** |  |  [optional] |
|**contentB64** | **String** |  |  [optional] |
|**xmlContent** | **String** |  |  [optional] |



## Enum: StatusEnum

| Name | Value |
|---- | -----|
| SUCCESS | &quot;SUCCESS&quot; |



