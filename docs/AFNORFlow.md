

# AFNORFlow

The properties of a Flow resource

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**submittedAt** | **OffsetDateTime** | The flow submission date and time (the date and time when the flow was created on the system)  |  [optional] |
|**updatedAt** | **OffsetDateTime** | The last update date and time of the flow. When the flow is submitted updatedAt is equal to submittedAt. When the flow acknowledgment status is changed updatedAt date and time is updated.  |  [optional] |
|**flowId** | **String** | Unique identifier supporting UUID but not only, for flexibility purpose |  [optional] |
|**trackingId** | **String** | Unique identifier supporting UUID but not only, for flexibility purpose |  [optional] |
|**flowType** | **AFNORFlowType** |  |  [optional] |
|**processingRule** | **AFNORProcessingRule** |  |  [optional] |
|**processingRuleSource** | [**ProcessingRuleSourceEnum**](#ProcessingRuleSourceEnum) | Says whether the processing rule has been computed or the processing rule was an input parameter |  [optional] |
|**flowDirection** | **AFNORFlowDirection** |  |  [optional] |
|**flowSyntax** | **AFNORFlowSyntax** |  |  [optional] |
|**flowProfile** | **AFNORFlowProfile** |  |  [optional] |
|**acknowledgement** | [**AFNORAcknowledgement**](AFNORAcknowledgement.md) |  |  [optional] |



## Enum: ProcessingRuleSourceEnum

| Name | Value |
|---- | -----|
| INPUT | &quot;Input&quot; |
| COMPUTED | &quot;Computed&quot; |



