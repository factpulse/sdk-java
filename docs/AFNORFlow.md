

# AFNORFlow

The properties of a Flow resource

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**trackingId** | **String** | The tracking id is an external identifier and is used to track the flow by the sender |  [optional] |
|**name** | **String** | Name of the file |  |
|**processingRule** | **AFNORProcessingRule** |  |  [optional] |
|**flowSyntax** | **AFNORFlowSyntax** |  |  |
|**flowProfile** | **AFNORFlowProfile** |  |  [optional] |
|**flowId** | **String** | Unique identifier supporting UUID but not only, for flexibility purpose |  |
|**submittedAt** | **OffsetDateTime** | The flow submission date and time (the date and time when the flow was created on the system) This property should be used by the API consumer as a time reference to avoid clock synchronization issues  |  |
|**updatedAt** | **OffsetDateTime** | The last update date and time of the flow. When the flow is submitted updatedAt is equal to submittedAt. When the flow acknowledgment status is changed updatedAt date and time is updated.  |  |
|**flowType** | **AFNORFlowType** |  |  |
|**processingRuleSource** | [**ProcessingRuleSourceEnum**](#ProcessingRuleSourceEnum) | Says whether the processing rule has been computed or the processing rule was an input parameter |  |
|**flowDirection** | **AFNORFlowDirection** |  |  |
|**acknowledgement** | [**AFNORAcknowledgement**](AFNORAcknowledgement.md) |  |  |



## Enum: ProcessingRuleSourceEnum

| Name | Value |
|---- | -----|
| INPUT | &quot;Input&quot; |
| COMPUTED | &quot;Computed&quot; |



