

# ConversionValidationFailedResult

Conversion extracted data failed Schematron validation.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**status** | [**StatusEnum**](#StatusEnum) |  |  [optional] |
|**conversionId** | **String** |  |  |
|**message** | **String** |  |  |
|**extractedData** | **Map&lt;String, Object&gt;** |  |  |
|**extraction** | [**ConversionExtractionInfo**](ConversionExtractionInfo.md) |  |  [optional] |
|**validationErrors** | **List&lt;Map&lt;String, Object&gt;&gt;** |  |  [optional] |
|**profile** | **String** |  |  |
|**processingTimeMs** | **Integer** |  |  |
|**correctionAttempted** | **Boolean** |  |  [optional] |



## Enum: StatusEnum

| Name | Value |
|---- | -----|
| VALIDATION_FAILED | &quot;VALIDATION_FAILED&quot; |



