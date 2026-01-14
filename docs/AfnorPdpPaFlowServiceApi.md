# AfnorPdpPaFlowServiceApi

All URIs are relative to *https://factpulse.fr*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**downloadFlowProxyApiV1AfnorFlowV1FlowsFlowIdGet**](AfnorPdpPaFlowServiceApi.md#downloadFlowProxyApiV1AfnorFlowV1FlowsFlowIdGet) | **GET** /api/v1/afnor/flow/v1/flows/{flowId} | Download a flow |
| [**flowHealthcheckProxyApiV1AfnorFlowV1HealthcheckGet**](AfnorPdpPaFlowServiceApi.md#flowHealthcheckProxyApiV1AfnorFlowV1HealthcheckGet) | **GET** /api/v1/afnor/flow/v1/healthcheck | Healthcheck Flow Service |
| [**searchFlowsProxyApiV1AfnorFlowV1FlowsSearchPost**](AfnorPdpPaFlowServiceApi.md#searchFlowsProxyApiV1AfnorFlowV1FlowsSearchPost) | **POST** /api/v1/afnor/flow/v1/flows/search | Search flows |
| [**submitFlowProxyApiV1AfnorFlowV1FlowsPost**](AfnorPdpPaFlowServiceApi.md#submitFlowProxyApiV1AfnorFlowV1FlowsPost) | **POST** /api/v1/afnor/flow/v1/flows | Submit an invoicing flow |


<a id="downloadFlowProxyApiV1AfnorFlowV1FlowsFlowIdGet"></a>
# **downloadFlowProxyApiV1AfnorFlowV1FlowsFlowIdGet**
> AFNORFlow downloadFlowProxyApiV1AfnorFlowV1FlowsFlowIdGet(flowId, docType)

Download a flow

Download a file related to a given flow (AFNOR XP Z12-013 compliant): - Metadata [Default]: provides the flow metadata as JSON - Original: the document initially sent by the emitter - Converted: the document optionally converted by the system - ReadableView: the document optionally generated as readable file

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaFlowServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");

    AfnorPdpPaFlowServiceApi apiInstance = new AfnorPdpPaFlowServiceApi(defaultClient);
    String flowId = "flowId_example"; // String | AFNOR flow identifier (UUID)
    DocType docType = DocType.fromValue("Metadata"); // DocType | Type of file to download: Metadata (default, JSON), Original, Converted, or ReadableView
    try {
      AFNORFlow result = apiInstance.downloadFlowProxyApiV1AfnorFlowV1FlowsFlowIdGet(flowId, docType);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaFlowServiceApi#downloadFlowProxyApiV1AfnorFlowV1FlowsFlowIdGet");
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
| **flowId** | **String**| AFNOR flow identifier (UUID) | |
| **docType** | [**DocType**](.md)| Type of file to download: Metadata (default, JSON), Original, Converted, or ReadableView | [optional] [default to Metadata] [enum: Metadata, Original, Converted, ReadableView] |

### Return type

[**AFNORFlow**](AFNORFlow.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, application/pdf, application/xml

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK - Flow file or metadata returned |  -  |
| **400** | Bad request - Invalid input parameters |  -  |
| **401** | Authentication error - Missing or invalid token |  -  |
| **403** | Forbidden - Insufficient permissions |  -  |
| **404** | Resource not found |  -  |
| **429** | Too many requests - Rate limit exceeded |  -  |
| **500** | Internal server error |  -  |
| **503** | Service unavailable - PDP temporarily unavailable |  -  |
| **422** | Validation Error |  -  |

<a id="flowHealthcheckProxyApiV1AfnorFlowV1HealthcheckGet"></a>
# **flowHealthcheckProxyApiV1AfnorFlowV1HealthcheckGet**
> Object flowHealthcheckProxyApiV1AfnorFlowV1HealthcheckGet()

Healthcheck Flow Service

Check Flow Service availability (AFNOR XP Z12-013 compliant)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaFlowServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");

    AfnorPdpPaFlowServiceApi apiInstance = new AfnorPdpPaFlowServiceApi(defaultClient);
    try {
      Object result = apiInstance.flowHealthcheckProxyApiV1AfnorFlowV1HealthcheckGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaFlowServiceApi#flowHealthcheckProxyApiV1AfnorFlowV1HealthcheckGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK - Service is operational |  -  |
| **500** | Internal server error |  -  |
| **503** | Service unavailable - PDP temporarily unavailable |  -  |

<a id="searchFlowsProxyApiV1AfnorFlowV1FlowsSearchPost"></a>
# **searchFlowsProxyApiV1AfnorFlowV1FlowsSearchPost**
> AFNORSearchFlowContent searchFlowsProxyApiV1AfnorFlowV1FlowsSearchPost(afNORSearchFlowParams)

Search flows

Search invoicing flows by criteria (AFNOR XP Z12-013 compliant)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaFlowServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");

    AfnorPdpPaFlowServiceApi apiInstance = new AfnorPdpPaFlowServiceApi(defaultClient);
    AFNORSearchFlowParams afNORSearchFlowParams = new AFNORSearchFlowParams(); // AFNORSearchFlowParams | 
    try {
      AFNORSearchFlowContent result = apiInstance.searchFlowsProxyApiV1AfnorFlowV1FlowsSearchPost(afNORSearchFlowParams);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaFlowServiceApi#searchFlowsProxyApiV1AfnorFlowV1FlowsSearchPost");
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
| **afNORSearchFlowParams** | [**AFNORSearchFlowParams**](AFNORSearchFlowParams.md)|  | |

### Return type

[**AFNORSearchFlowContent**](AFNORSearchFlowContent.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK - Search results returned |  -  |
| **400** | Bad request - Invalid input parameters |  -  |
| **401** | Authentication error - Missing or invalid token |  -  |
| **403** | Forbidden - Insufficient permissions |  -  |
| **429** | Too many requests - Rate limit exceeded |  -  |
| **500** | Internal server error |  -  |
| **503** | Service unavailable - PDP temporarily unavailable |  -  |

<a id="submitFlowProxyApiV1AfnorFlowV1FlowsPost"></a>
# **submitFlowProxyApiV1AfnorFlowV1FlowsPost**
> Object submitFlowProxyApiV1AfnorFlowV1FlowsPost(flowInfo, _file)

Submit an invoicing flow

Submits an electronic invoice to a Partner Dematerialization Platform (PDP) in compliance with the AFNOR XP Z12-013 standard

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaFlowServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");

    AfnorPdpPaFlowServiceApi apiInstance = new AfnorPdpPaFlowServiceApi(defaultClient);
    AFNORFlowInfo flowInfo = new AFNORFlowInfo(); // AFNORFlowInfo | 
    File _file = new File("/path/to/file"); // File | Flow file (PDF/A-3 with embedded XML or XML)
    try {
      Object result = apiInstance.submitFlowProxyApiV1AfnorFlowV1FlowsPost(flowInfo, _file);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaFlowServiceApi#submitFlowProxyApiV1AfnorFlowV1FlowsPost");
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
| **flowInfo** | [**AFNORFlowInfo**](AFNORFlowInfo.md)|  | |
| **_file** | **File**| Flow file (PDF/A-3 with embedded XML or XML) | |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **202** | OK - Flow has been uploaded and accepted for processing |  -  |
| **400** | Bad request - Invalid input parameters |  -  |
| **401** | Authentication error - Missing or invalid token |  -  |
| **403** | Forbidden - Insufficient permissions |  -  |
| **404** | Resource not found |  -  |
| **413** | Payload too large - File exceeds maximum size |  -  |
| **422** | Unprocessable entity - Business rule validation failed |  -  |
| **429** | Too many requests - Rate limit exceeded |  -  |
| **500** | Internal server error |  -  |
| **503** | Service unavailable - PDP temporarily unavailable |  -  |

