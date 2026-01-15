

# AFNORFlowInfo

Signaling of the flow: - The tracking id is an external identifier and is used to track the flow by the sender - The sha256 is the fingerprint of the attached file:   - if provided in the request: it should be checked once received   - if not provided in the request: it should be computed and returned in the response 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**trackingId** | **String** | Unique identifier supporting UUID but not only, for flexibility purpose |  [optional] |
|**name** | **String** | Name of the file |  [optional] |
|**processingRule** | **AFNORProcessingRule** |  |  [optional] |
|**flowSyntax** | **AFNORFlowSyntax** |  |  |
|**flowProfile** | **AFNORFlowProfile** |  |  [optional] |
|**sha256** | **String** |  |  [optional] |



