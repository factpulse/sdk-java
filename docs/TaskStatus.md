

# TaskStatus

Complete description of an async task status.  The `status` field indicates the Celery state of the task. When `status=\"SUCCESS\"`, check `result.status` for the business result (\"SUCCESS\" or \"ERROR\").

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**taskId** | **String** | Unique task identifier |  |
|**status** | **CeleryStatus** | Celery task status (PENDING, STARTED, SUCCESS, FAILURE, RETRY) |  |
|**result** | **Map&lt;String, Object&gt;** |  |  [optional] |



