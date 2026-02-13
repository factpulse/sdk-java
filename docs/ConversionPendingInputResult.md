

# ConversionPendingInputResult

Conversion needs user input (missing fields).

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**status** | [**StatusEnum**](#StatusEnum) |  |  [optional] |
|**conversionId** | **String** |  |  |
|**message** | **String** |  |  |
|**missingFields** | **List&lt;String&gt;** |  |  |
|**extractedData** | **Map&lt;String, Object&gt;** |  |  |
|**confidenceScore** | **BigDecimal** |  |  |
|**processingTimeMs** | **Integer** |  |  |



## Enum: StatusEnum

| Name | Value |
|---- | -----|
| PENDING_INPUT | &quot;PENDING_INPUT&quot; |



