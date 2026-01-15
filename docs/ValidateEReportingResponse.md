

# ValidateEReportingResponse

Response after validating e-reporting data.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**valid** | **Boolean** | Whether the data is valid |  |
|**reportId** | **String** | Report identifier |  |
|**flowType** | **String** | Flux type |  |
|**errors** | [**List&lt;ValidationError&gt;**](ValidationError.md) | List of validation errors (if any) |  [optional] |
|**warnings** | [**List&lt;ValidationError&gt;**](ValidationError.md) | List of validation warnings (if any) |  [optional] |
|**message** | **String** | Status message |  |



