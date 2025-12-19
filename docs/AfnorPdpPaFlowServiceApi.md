# AfnorPdpPaFlowServiceApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**downloadFlowProxyApiV1AfnorFlowV1FlowsFlowIdGet**](AfnorPdpPaFlowServiceApi.md#downloadFlowProxyApiV1AfnorFlowV1FlowsFlowIdGet) | **GET** /api/v1/afnor/flow/v1/flows/{flowId} | Download a flow |
| [**flowHealthcheckProxyApiV1AfnorFlowV1HealthcheckGet**](AfnorPdpPaFlowServiceApi.md#flowHealthcheckProxyApiV1AfnorFlowV1HealthcheckGet) | **GET** /api/v1/afnor/flow/v1/healthcheck | Healthcheck Flow Service |
| [**searchFlowsProxyApiV1AfnorFlowV1FlowsSearchPost**](AfnorPdpPaFlowServiceApi.md#searchFlowsProxyApiV1AfnorFlowV1FlowsSearchPost) | **POST** /api/v1/afnor/flow/v1/flows/search | Search flows |
| [**submitFlowProxyApiV1AfnorFlowV1FlowsPost**](AfnorPdpPaFlowServiceApi.md#submitFlowProxyApiV1AfnorFlowV1FlowsPost) | **POST** /api/v1/afnor/flow/v1/flows | Submit an invoicing flow |


<a id="downloadFlowProxyApiV1AfnorFlowV1FlowsFlowIdGet"></a>
# **downloadFlowProxyApiV1AfnorFlowV1FlowsFlowIdGet**
> Object downloadFlowProxyApiV1AfnorFlowV1FlowsFlowIdGet(flowId)

Download a flow

Download the PDF/A-3 file of an invoicing flow (uses JWT client_uid)

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
    defaultClient.setBasePath("http://localhost");

    AfnorPdpPaFlowServiceApi apiInstance = new AfnorPdpPaFlowServiceApi(defaultClient);
    String flowId = "flowId_example"; // String | 
    try {
      Object result = apiInstance.downloadFlowProxyApiV1AfnorFlowV1FlowsFlowIdGet(flowId);
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
| **flowId** | **String**|  | |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, application/pdf

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | PDF/A-3 file |  -  |
| **400** | Missing PDP configuration |  -  |
| **401** | Not authenticated - Missing or invalid JWT token |  -  |
| **404** | Flow not found or invalid client_uid |  -  |
| **422** | Validation Error |  -  |

<a id="flowHealthcheckProxyApiV1AfnorFlowV1HealthcheckGet"></a>
# **flowHealthcheckProxyApiV1AfnorFlowV1HealthcheckGet**
> Object flowHealthcheckProxyApiV1AfnorFlowV1HealthcheckGet()

Healthcheck Flow Service

Check Flow Service availability

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
    defaultClient.setBasePath("http://localhost");

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
| **200** | Service operational |  -  |

<a id="searchFlowsProxyApiV1AfnorFlowV1FlowsSearchPost"></a>
# **searchFlowsProxyApiV1AfnorFlowV1FlowsSearchPost**
> Object searchFlowsProxyApiV1AfnorFlowV1FlowsSearchPost()

Search flows

Search invoicing flows by criteria (uses JWT client_uid)

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
    defaultClient.setBasePath("http://localhost");

    AfnorPdpPaFlowServiceApi apiInstance = new AfnorPdpPaFlowServiceApi(defaultClient);
    try {
      Object result = apiInstance.searchFlowsProxyApiV1AfnorFlowV1FlowsSearchPost();
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
| **200** | Search results |  -  |
| **400** | Missing PDP configuration |  -  |
| **401** | Not authenticated - Missing or invalid JWT token |  -  |
| **404** | PDP client not found (invalid client_uid) |  -  |
| **429** | Too many requests - Rate limit reached (60 searches/minute) |  -  |

<a id="submitFlowProxyApiV1AfnorFlowV1FlowsPost"></a>
# **submitFlowProxyApiV1AfnorFlowV1FlowsPost**
> Object submitFlowProxyApiV1AfnorFlowV1FlowsPost()

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
    defaultClient.setBasePath("http://localhost");

    AfnorPdpPaFlowServiceApi apiInstance = new AfnorPdpPaFlowServiceApi(defaultClient);
    try {
      Object result = apiInstance.submitFlowProxyApiV1AfnorFlowV1FlowsPost();
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
| **200** | Successful Response |  -  |
| **201** | Flow submitted successfully |  -  |
| **400** | Invalid request or missing PDP configuration |  -  |
| **401** | Not authenticated - Missing or invalid JWT token |  -  |
| **403** | Not authorized - Quota exceeded or insufficient permissions |  -  |
| **404** | PDP client not found (invalid client_uid) |  -  |
| **429** | Too many requests - Rate limit reached (30 submissions/minute) |  -  |

