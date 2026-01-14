

# AFNORFullFlowInfo

Identified Flow info: flow info + id + timestamp

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**trackingId** | **String** | Unique identifier supporting UUID but not only, for flexibility purpose |  [optional] |
|**name** | **String** | Name of the file |  [optional] |
|**processingRule** | **AFNORProcessingRule** |  |  [optional] |
|**flowSyntax** | **AFNORFlowSyntax** |  |  |
|**flowProfile** | **AFNORFlowProfile** |  |  [optional] |
|**sha256** | **String** |  |  [optional] |
|**flowId** | **String** | Unique identifier supporting UUID but not only, for flexibility purpose |  [optional] |
|**submittedAt** | **OffsetDateTime** | The flow submission date and time (the date and time when the flow was created on the system) This property should be used by the API consumer as a time reference to avoid clock synchronization issues  |  [optional] |



