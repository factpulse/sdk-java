

# SearchFlowRequest

Request to search submitted flows.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**updatedAfter** | **OffsetDateTime** |  |  [optional] |
|**updatedBefore** | **OffsetDateTime** |  |  [optional] |
|**flowTypes** | **List&lt;FlowType&gt;** |  |  [optional] |
|**flowDirections** | **List&lt;FlowDirection&gt;** |  |  [optional] |
|**trackingId** | **String** |  |  [optional] |
|**flowId** | **String** |  |  [optional] |
|**acknowledgmentStatus** | **AcknowledgmentStatus** |  |  [optional] |
|**offset** | **Integer** | Offset for pagination |  [optional] |
|**limit** | **Integer** | Maximum number of results (max 100) |  [optional] |



