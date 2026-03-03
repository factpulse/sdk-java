

# AFNORFlowInfo

Signaling of the flow

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**trackingId** | **String** | The tracking id is an external identifier and is used to track the flow by the sender |  [optional] |
|**name** | **String** | Name of the file |  |
|**processingRule** | **AFNORProcessingRule** |  |  [optional] |
|**flowSyntax** | **AFNORFlowSyntax** |  |  |
|**flowProfile** | **AFNORFlowProfile** |  |  [optional] |
|**sha256** | **byte[]** | The sha256 is the fingerprint of the attached file: - if provided in the request: it should be checked once received - if not provided in the request: it may be computed and returned in the response  |  [optional] |



