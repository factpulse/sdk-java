

# GenerateAggregatedReportResponse

Response after generating an aggregated e-reporting XML.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**reportId** | **String** | Report identifier |  |
|**transmissionType** | **String** | Transmission type (IN or RE) |  |
|**flowType** | **String** | AFNOR FlowType determined from content |  |
|**xml** | **String** | Generated XML content |  |
|**xmlSize** | **Integer** | XML size in bytes |  |
|**contentSummary** | **Map&lt;String, Object&gt;** | Summary of content (counts by flux type) |  |
|**message** | **String** | Status message |  |



