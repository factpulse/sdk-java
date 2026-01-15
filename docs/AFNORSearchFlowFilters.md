

# AFNORSearchFlowFilters

Filtering criteria, at least one is required

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**updatedAfter** | **OffsetDateTime** |  |  [optional] |
|**updatedBefore** | **OffsetDateTime** |  |  [optional] |
|**processingRule** | **List&lt;AFNORProcessingRule&gt;** |  |  [optional] |
|**flowType** | **List&lt;AFNORFlowType&gt;** |  |  [optional] |
|**flowDirection** | **List&lt;AFNORFlowDirection&gt;** |  |  [optional] |
|**trackingId** | **String** | Unique identifier supporting UUID but not only, for flexibility purpose |  [optional] |
|**ackStatus** | **AFNORFlowAckStatus** |  |  [optional] |



