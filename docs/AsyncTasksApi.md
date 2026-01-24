# AsyncTasksApi

All URIs are relative to *https://factpulse.fr*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getTaskStatusApiV1ProcessingTasksTaskIdStatusGet**](AsyncTasksApi.md#getTaskStatusApiV1ProcessingTasksTaskIdStatusGet) | **GET** /api/v1/processing/tasks/{task_id}/status | Get task generation status |


<a id="getTaskStatusApiV1ProcessingTasksTaskIdStatusGet"></a>
# **getTaskStatusApiV1ProcessingTasksTaskIdStatusGet**
> AsyncTaskStatus getTaskStatusApiV1ProcessingTasksTaskIdStatusGet(taskId)

Get task generation status

Retrieves the progress status of an invoice generation task.  ## Possible states  The &#x60;status&#x60; field uses the &#x60;CeleryStatus&#x60; enum with values: - **PENDING, STARTED, SUCCESS, FAILURE, RETRY**  See the &#x60;CeleryStatus&#x60; schema documentation for details.  ## Business result  When &#x60;status&#x3D;\&quot;SUCCESS\&quot;&#x60;, the &#x60;result&#x60; field contains: - &#x60;status&#x60;: \&quot;SUCCESS\&quot; or \&quot;ERROR\&quot; (business result) - &#x60;content_b64&#x60;: Base64 encoded content (if success) - &#x60;errorCode&#x60;, &#x60;errorMessage&#x60;, &#x60;details&#x60;: AFNOR format (if business error)  ## Usage  Poll this endpoint every 2-3 seconds until &#x60;status&#x60; is &#x60;SUCCESS&#x60; or &#x60;FAILURE&#x60;.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AsyncTasksApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AsyncTasksApi apiInstance = new AsyncTasksApi(defaultClient);
    String taskId = "taskId_example"; // String | Celery task ID returned by async endpoints (UUID format)
    try {
      AsyncTaskStatus result = apiInstance.getTaskStatusApiV1ProcessingTasksTaskIdStatusGet(taskId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AsyncTasksApi#getTaskStatusApiV1ProcessingTasksTaskIdStatusGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **taskId** | **String**| Celery task ID returned by async endpoints (UUID format) | |

### Return type

[**AsyncTaskStatus**](AsyncTaskStatus.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Current task state |  -  |
| **422** | Validation Error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

