

# ConversionTaskStatusResult


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
|**message** | **String** |  |  |
|**missingFields** | **List&lt;String&gt;** |  |  |
|**extractedData** | **Map&lt;String, Object&gt;** |  |  |
|**confidenceScore** | **BigDecimal** |  |  |
|**validationErrors** | **List&lt;Map&lt;String, Object&gt;&gt;** |  |  [optional] |
|**correctionAttempted** | **Boolean** |  |  [optional] |
|**errorCode** | **String** |  |  |
|**errorMessage** | **String** |  |  |
|**details** | [**List&lt;AFNORErrorDetail&gt;**](AFNORErrorDetail.md) |  |  [optional] |
|**traceback** | **String** |  |  [optional] |



## Enum: StatusEnum

| Name | Value |
|---- | -----|
| ERROR | &quot;ERROR&quot; |



