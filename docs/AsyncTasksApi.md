# AsyncTasksApi

All URIs are relative to *https://factpulse.fr*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getGenerationTaskStatusApiV1ProcessingTasksTaskIdGenerationStatusGet**](AsyncTasksApi.md#getGenerationTaskStatusApiV1ProcessingTasksTaskIdGenerationStatusGet) | **GET** /api/v1/processing/tasks/{task_id}/generation-status | Get typed generation task status |
| [**getSignatureTaskStatusApiV1ProcessingTasksTaskIdSignatureStatusGet**](AsyncTasksApi.md#getSignatureTaskStatusApiV1ProcessingTasksTaskIdSignatureStatusGet) | **GET** /api/v1/processing/tasks/{task_id}/signature-status | Get typed signature task status |
| [**getTaskStatusApiV1ProcessingTasksTaskIdStatusGet**](AsyncTasksApi.md#getTaskStatusApiV1ProcessingTasksTaskIdStatusGet) | **GET** /api/v1/processing/tasks/{task_id}/status | Get task generation status |
| [**getValidationTaskStatusApiV1ProcessingTasksTaskIdValidationStatusGet**](AsyncTasksApi.md#getValidationTaskStatusApiV1ProcessingTasksTaskIdValidationStatusGet) | **GET** /api/v1/processing/tasks/{task_id}/validation-status | Get typed validation task status |


<a id="getGenerationTaskStatusApiV1ProcessingTasksTaskIdGenerationStatusGet"></a>
# **getGenerationTaskStatusApiV1ProcessingTasksTaskIdGenerationStatusGet**
> GenerationTaskStatus getGenerationTaskStatusApiV1ProcessingTasksTaskIdGenerationStatusGet(taskId)

Get typed generation task status

Typed status endpoint for invoice generation tasks.  Returns a strongly-typed &#x60;result&#x60; (discriminated on &#x60;result.status&#x60;): - **SUCCESS**: &#x60;GenerationSuccessResult&#x60; with &#x60;content_b64&#x60; / &#x60;xml_content&#x60; - **ERROR**: &#x60;TaskErrorResult&#x60; in AFNOR format

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
    
    // Configure API key authorization: APIKeyHeader
    ApiKeyAuth APIKeyHeader = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyHeader");
    APIKeyHeader.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyHeader.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AsyncTasksApi apiInstance = new AsyncTasksApi(defaultClient);
    String taskId = "taskId_example"; // String | Celery task ID returned by /generate-invoice
    try {
      GenerationTaskStatus result = apiInstance.getGenerationTaskStatusApiV1ProcessingTasksTaskIdGenerationStatusGet(taskId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AsyncTasksApi#getGenerationTaskStatusApiV1ProcessingTasksTaskIdGenerationStatusGet");
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
| **taskId** | **String**| Celery task ID returned by /generate-invoice | |

### Return type

[**GenerationTaskStatus**](GenerationTaskStatus.md)

### Authorization

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **422** | Validation Error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="getSignatureTaskStatusApiV1ProcessingTasksTaskIdSignatureStatusGet"></a>
# **getSignatureTaskStatusApiV1ProcessingTasksTaskIdSignatureStatusGet**
> SignatureTaskStatus getSignatureTaskStatusApiV1ProcessingTasksTaskIdSignatureStatusGet(taskId)

Get typed signature task status

Typed status endpoint for PDF signing tasks.  Returns a strongly-typed &#x60;result&#x60; (discriminated on &#x60;result.status&#x60;): - **SUCCESS**: &#x60;SignatureSuccessResult&#x60; with &#x60;content_b64&#x60;, &#x60;signer_cn&#x60;, etc. - **ERROR**: &#x60;TaskErrorResult&#x60; in AFNOR format

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
    
    // Configure API key authorization: APIKeyHeader
    ApiKeyAuth APIKeyHeader = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyHeader");
    APIKeyHeader.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyHeader.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AsyncTasksApi apiInstance = new AsyncTasksApi(defaultClient);
    String taskId = "taskId_example"; // String | Celery task ID returned by /sign-pdf-async
    try {
      SignatureTaskStatus result = apiInstance.getSignatureTaskStatusApiV1ProcessingTasksTaskIdSignatureStatusGet(taskId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AsyncTasksApi#getSignatureTaskStatusApiV1ProcessingTasksTaskIdSignatureStatusGet");
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
| **taskId** | **String**| Celery task ID returned by /sign-pdf-async | |

### Return type

[**SignatureTaskStatus**](SignatureTaskStatus.md)

### Authorization

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **422** | Validation Error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

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
    
    // Configure API key authorization: APIKeyHeader
    ApiKeyAuth APIKeyHeader = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyHeader");
    APIKeyHeader.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyHeader.setApiKeyPrefix("Token");

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

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Current task state |  -  |
| **422** | Validation Error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="getValidationTaskStatusApiV1ProcessingTasksTaskIdValidationStatusGet"></a>
# **getValidationTaskStatusApiV1ProcessingTasksTaskIdValidationStatusGet**
> ValidationTaskStatus getValidationTaskStatusApiV1ProcessingTasksTaskIdValidationStatusGet(taskId)

Get typed validation task status

Typed status endpoint for Factur-X PDF validation tasks.  Returns a strongly-typed &#x60;result&#x60; (discriminated on &#x60;result.status&#x60;): - **SUCCESS**: &#x60;ValidationSuccessTaskResult&#x60; with &#x60;validation_result&#x60; - **ERROR**: &#x60;TaskErrorResult&#x60; in AFNOR format

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
    
    // Configure API key authorization: APIKeyHeader
    ApiKeyAuth APIKeyHeader = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyHeader");
    APIKeyHeader.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyHeader.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AsyncTasksApi apiInstance = new AsyncTasksApi(defaultClient);
    String taskId = "taskId_example"; // String | Celery task ID returned by /validate-pdf
    try {
      ValidationTaskStatus result = apiInstance.getValidationTaskStatusApiV1ProcessingTasksTaskIdValidationStatusGet(taskId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AsyncTasksApi#getValidationTaskStatusApiV1ProcessingTasksTaskIdValidationStatusGet");
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
| **taskId** | **String**| Celery task ID returned by /validate-pdf | |

### Return type

[**ValidationTaskStatus**](ValidationTaskStatus.md)

### Authorization

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **422** | Validation Error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

