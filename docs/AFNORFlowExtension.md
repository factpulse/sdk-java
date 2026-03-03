

# AFNORFlowExtension

Extension schema to build Flow schema

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
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



