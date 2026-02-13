# FacturXConversionApi

All URIs are relative to *https://factpulse.fr*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**convertDocumentAsyncApiV1ConvertAsyncPost**](FacturXConversionApi.md#convertDocumentAsyncApiV1ConvertAsyncPost) | **POST** /api/v1/convert/async | Convert a document to Factur-X (async mode) |
| [**downloadFileApiV1ConvertConversionIdDownloadFilenameGet**](FacturXConversionApi.md#downloadFileApiV1ConvertConversionIdDownloadFilenameGet) | **GET** /api/v1/convert/{conversion_id}/download/{filename} | Download a generated file |
| [**getConversionStatusApiV1ConvertConversionIdStatusGet**](FacturXConversionApi.md#getConversionStatusApiV1ConvertConversionIdStatusGet) | **GET** /api/v1/convert/{conversion_id}/status | Check conversion status |
| [**getConversionTaskStatusApiV1ConvertTasksTaskIdConversionStatusGet**](FacturXConversionApi.md#getConversionTaskStatusApiV1ConvertTasksTaskIdConversionStatusGet) | **GET** /api/v1/convert/tasks/{task_id}/conversion-status | Get typed conversion task status |
| [**resumeConversionApiV1ConvertConversionIdResumePost**](FacturXConversionApi.md#resumeConversionApiV1ConvertConversionIdResumePost) | **POST** /api/v1/convert/{conversion_id}/resume | Resume a conversion with corrections |
| [**resumeConversionAsyncApiV1ConvertConversionIdResumeAsyncPost**](FacturXConversionApi.md#resumeConversionAsyncApiV1ConvertConversionIdResumeAsyncPost) | **POST** /api/v1/convert/{conversion_id}/resume/async | Resume a conversion asynchronously |


<a id="convertDocumentAsyncApiV1ConvertAsyncPost"></a>
# **convertDocumentAsyncApiV1ConvertAsyncPost**
> Object convertDocumentAsyncApiV1ConvertAsyncPost(_file, output, callbackUrl, webhookMode)

Convert a document to Factur-X (async mode)

Launch an asynchronous conversion via Celery.  ## Workflow  1. **Upload**: Document is sent as multipart/form-data 2. **Celery Task**: Task is queued for processing 3. **Callback**: Webhook notification on completion  ## Possible responses  - **202**: Task accepted, processing - **400**: Invalid file

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FacturXConversionApi;

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

    FacturXConversionApi apiInstance = new FacturXConversionApi(defaultClient);
    File _file = new File("/path/to/file"); // File | Document to convert (PDF, DOCX, XLSX, JPG, PNG)
    String output = "pdf"; // String | Output format: pdf, xml, both
    String callbackUrl = "callbackUrl_example"; // String | 
    String webhookMode = "inline"; // String | Content delivery mode: 'inline' (base64 in webhook) or 'download_url' (temporary URL, 1h TTL)
    try {
      Object result = apiInstance.convertDocumentAsyncApiV1ConvertAsyncPost(_file, output, callbackUrl, webhookMode);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FacturXConversionApi#convertDocumentAsyncApiV1ConvertAsyncPost");
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
| **_file** | **File**| Document to convert (PDF, DOCX, XLSX, JPG, PNG) | |
| **output** | **String**| Output format: pdf, xml, both | [optional] [default to pdf] |
| **callbackUrl** | **String**|  | [optional] |
| **webhookMode** | **String**| Content delivery mode: &#39;inline&#39; (base64 in webhook) or &#39;download_url&#39; (temporary URL, 1h TTL) | [optional] [default to inline] |

### Return type

**Object**

### Authorization

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **202** | Task accepted |  -  |
| **400** | Invalid file |  -  |
| **422** | Validation Error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="downloadFileApiV1ConvertConversionIdDownloadFilenameGet"></a>
# **downloadFileApiV1ConvertConversionIdDownloadFilenameGet**
> Object downloadFileApiV1ConvertConversionIdDownloadFilenameGet(conversionId, filename)

Download a generated file

Download the generated Factur-X PDF or XML file.  ## Available files  - &#x60;facturx.pdf&#x60;: PDF/A-3 with embedded XML - &#x60;facturx.xml&#x60;: XML CII only (Cross Industry Invoice)  Files are available for 24 hours after generation.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FacturXConversionApi;

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

    FacturXConversionApi apiInstance = new FacturXConversionApi(defaultClient);
    String conversionId = "conversionId_example"; // String | Conversion ID returned by POST /convert (UUID format)
    String filename = "filename_example"; // String | File to download: 'facturx.pdf' or 'facturx.xml'
    try {
      Object result = apiInstance.downloadFileApiV1ConvertConversionIdDownloadFilenameGet(conversionId, filename);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FacturXConversionApi#downloadFileApiV1ConvertConversionIdDownloadFilenameGet");
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
| **conversionId** | **String**| Conversion ID returned by POST /convert (UUID format) | |
| **filename** | **String**| File to download: &#39;facturx.pdf&#39; or &#39;facturx.xml&#39; | |

### Return type

**Object**

### Authorization

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | File downloaded |  -  |
| **404** | File not found or expired |  -  |
| **422** | Validation Error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="getConversionStatusApiV1ConvertConversionIdStatusGet"></a>
# **getConversionStatusApiV1ConvertConversionIdStatusGet**
> Map&lt;String, Object&gt; getConversionStatusApiV1ConvertConversionIdStatusGet(conversionId)

Check conversion status

Returns the current status of an asynchronous conversion.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FacturXConversionApi;

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

    FacturXConversionApi apiInstance = new FacturXConversionApi(defaultClient);
    String conversionId = "conversionId_example"; // String | Conversion ID returned by POST /convert (UUID format)
    try {
      Map<String, Object> result = apiInstance.getConversionStatusApiV1ConvertConversionIdStatusGet(conversionId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FacturXConversionApi#getConversionStatusApiV1ConvertConversionIdStatusGet");
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
| **conversionId** | **String**| Conversion ID returned by POST /convert (UUID format) | |

### Return type

**Map&lt;String, Object&gt;**

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

<a id="getConversionTaskStatusApiV1ConvertTasksTaskIdConversionStatusGet"></a>
# **getConversionTaskStatusApiV1ConvertTasksTaskIdConversionStatusGet**
> ConversionTaskStatus getConversionTaskStatusApiV1ConvertTasksTaskIdConversionStatusGet(taskId)

Get typed conversion task status

Typed status endpoint for document conversion tasks.  Returns a strongly-typed &#x60;result&#x60; discriminated on &#x60;result.status&#x60;: - **SUCCESS**: &#x60;ConversionSuccessTaskResult&#x60; - **PENDING_INPUT**: &#x60;ConversionPendingInputResult&#x60; (missing fields) - **VALIDATION_FAILED**: &#x60;ConversionValidationFailedResult&#x60; - **ERROR**: &#x60;ConversionErrorResult&#x60;

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FacturXConversionApi;

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

    FacturXConversionApi apiInstance = new FacturXConversionApi(defaultClient);
    String taskId = "taskId_example"; // String | Celery task ID returned by POST /convert
    try {
      ConversionTaskStatus result = apiInstance.getConversionTaskStatusApiV1ConvertTasksTaskIdConversionStatusGet(taskId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FacturXConversionApi#getConversionTaskStatusApiV1ConvertTasksTaskIdConversionStatusGet");
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
| **taskId** | **String**| Celery task ID returned by POST /convert | |

### Return type

[**ConversionTaskStatus**](ConversionTaskStatus.md)

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

<a id="resumeConversionApiV1ConvertConversionIdResumePost"></a>
# **resumeConversionApiV1ConvertConversionIdResumePost**
> ConvertSuccessResponse resumeConversionApiV1ConvertConversionIdResumePost(conversionId, convertResumeRequest)

Resume a conversion with corrections

Resume a conversion after completing missing data or correcting errors.  The OCR extraction is preserved, data is updated with corrections, then a new Schematron validation is performed.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FacturXConversionApi;

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

    FacturXConversionApi apiInstance = new FacturXConversionApi(defaultClient);
    String conversionId = "conversionId_example"; // String | Conversion ID returned by POST /convert (UUID format)
    ConvertResumeRequest convertResumeRequest = new ConvertResumeRequest(); // ConvertResumeRequest | 
    try {
      ConvertSuccessResponse result = apiInstance.resumeConversionApiV1ConvertConversionIdResumePost(conversionId, convertResumeRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FacturXConversionApi#resumeConversionApiV1ConvertConversionIdResumePost");
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
| **conversionId** | **String**| Conversion ID returned by POST /convert (UUID format) | |
| **convertResumeRequest** | [**ConvertResumeRequest**](ConvertResumeRequest.md)|  | |

### Return type

[**ConvertSuccessResponse**](ConvertSuccessResponse.md)

### Authorization

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **404** | Conversion not found or expired |  -  |
| **422** | Validation still failing |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="resumeConversionAsyncApiV1ConvertConversionIdResumeAsyncPost"></a>
# **resumeConversionAsyncApiV1ConvertConversionIdResumeAsyncPost**
> Object resumeConversionAsyncApiV1ConvertConversionIdResumeAsyncPost(conversionId, convertResumeRequest)

Resume a conversion asynchronously

Resume a conversion after completing missing data or correcting errors (async mode).  The OCR extraction is preserved, data is updated with corrections, then processing is performed asynchronously via Celery.  ## Workflow  1. **Submit corrections**: Corrections are validated and task is queued 2. **Celery Task**: Task processes corrections and generates Factur-X 3. **Callback**: Webhook notification on completion  ## Possible responses  - **202**: Task accepted, processing - **404**: Conversion not found or expired

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FacturXConversionApi;

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

    FacturXConversionApi apiInstance = new FacturXConversionApi(defaultClient);
    String conversionId = "conversionId_example"; // String | Conversion ID returned by POST /convert (UUID format)
    ConvertResumeRequest convertResumeRequest = new ConvertResumeRequest(); // ConvertResumeRequest | 
    try {
      Object result = apiInstance.resumeConversionAsyncApiV1ConvertConversionIdResumeAsyncPost(conversionId, convertResumeRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FacturXConversionApi#resumeConversionAsyncApiV1ConvertConversionIdResumeAsyncPost");
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
| **conversionId** | **String**| Conversion ID returned by POST /convert (UUID format) | |
| **convertResumeRequest** | [**ConvertResumeRequest**](ConvertResumeRequest.md)|  | |

### Return type

**Object**

### Authorization

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **202** | Task accepted |  -  |
| **404** | Conversion not found or expired |  -  |
| **422** | Validation Error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

