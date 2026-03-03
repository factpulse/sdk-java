# AfnorPdpPaFlowServiceApi

All URIs are relative to *https://factpulse.fr*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createWebhookProxyApiV1AfnorFlowV1WebhooksPost**](AfnorPdpPaFlowServiceApi.md#createWebhookProxyApiV1AfnorFlowV1WebhooksPost) | **POST** /api/v1/afnor/flow/v1/webhooks | Create a webhook |
| [**deleteWebhookProxyApiV1AfnorFlowV1WebhooksWebhookUidDelete**](AfnorPdpPaFlowServiceApi.md#deleteWebhookProxyApiV1AfnorFlowV1WebhooksWebhookUidDelete) | **DELETE** /api/v1/afnor/flow/v1/webhooks/{webhookUid} | Delete a webhook |
| [**downloadFlowProxyApiV1AfnorFlowV1FlowsFlowIdGet**](AfnorPdpPaFlowServiceApi.md#downloadFlowProxyApiV1AfnorFlowV1FlowsFlowIdGet) | **GET** /api/v1/afnor/flow/v1/flows/{flowId} | Download a flow |
| [**flowHealthcheckProxyApiV1AfnorFlowV1HealthcheckGet**](AfnorPdpPaFlowServiceApi.md#flowHealthcheckProxyApiV1AfnorFlowV1HealthcheckGet) | **GET** /api/v1/afnor/flow/v1/healthcheck | Healthcheck Flow Service |
| [**getWebhookProxyApiV1AfnorFlowV1WebhooksWebhookUidGet**](AfnorPdpPaFlowServiceApi.md#getWebhookProxyApiV1AfnorFlowV1WebhooksWebhookUidGet) | **GET** /api/v1/afnor/flow/v1/webhooks/{webhookUid} | Get a webhook |
| [**listWebhooksProxyApiV1AfnorFlowV1WebhooksGet**](AfnorPdpPaFlowServiceApi.md#listWebhooksProxyApiV1AfnorFlowV1WebhooksGet) | **GET** /api/v1/afnor/flow/v1/webhooks | List webhooks |
| [**searchFlowsProxyApiV1AfnorFlowV1FlowsSearchPost**](AfnorPdpPaFlowServiceApi.md#searchFlowsProxyApiV1AfnorFlowV1FlowsSearchPost) | **POST** /api/v1/afnor/flow/v1/flows/search | Search flows |
| [**submitFlowProxyApiV1AfnorFlowV1FlowsPost**](AfnorPdpPaFlowServiceApi.md#submitFlowProxyApiV1AfnorFlowV1FlowsPost) | **POST** /api/v1/afnor/flow/v1/flows | Submit an invoicing flow |
| [**updateWebhookProxyApiV1AfnorFlowV1WebhooksWebhookUidPatch**](AfnorPdpPaFlowServiceApi.md#updateWebhookProxyApiV1AfnorFlowV1WebhooksWebhookUidPatch) | **PATCH** /api/v1/afnor/flow/v1/webhooks/{webhookUid} | Update a webhook |


<a id="createWebhookProxyApiV1AfnorFlowV1WebhooksPost"></a>
# **createWebhookProxyApiV1AfnorFlowV1WebhooksPost**
> Object createWebhookProxyApiV1AfnorFlowV1WebhooksPost()

Create a webhook

Register a new webhook subscription (AFNOR XP Z12-013 v1.2.0)

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
      Object result = apiInstance.createWebhookProxyApiV1AfnorFlowV1WebhooksPost();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaFlowServiceApi#createWebhookProxyApiV1AfnorFlowV1WebhooksPost");
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
| **201** | Created - Webhook registered successfully |  -  |
| **400** | Bad request - Invalid input parameters |  -  |
| **401** | Authentication error - Missing or invalid token |  -  |
| **403** | Forbidden - Insufficient permissions |  -  |
| **422** | Unprocessable entity - Business rule validation failed |  -  |
| **429** | Too many requests - Rate limit exceeded |  -  |
| **500** | Internal server error |  -  |
| **503** | Service unavailable - PDP temporarily unavailable |  -  |

<a id="deleteWebhookProxyApiV1AfnorFlowV1WebhooksWebhookUidDelete"></a>
# **deleteWebhookProxyApiV1AfnorFlowV1WebhooksWebhookUidDelete**
> Object deleteWebhookProxyApiV1AfnorFlowV1WebhooksWebhookUidDelete(webhookUid)

Delete a webhook

Delete a webhook subscription (AFNOR XP Z12-013 v1.2.0)

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
    String webhookUid = "webhookUid_example"; // String | Webhook unique identifier (UUID)
    try {
      Object result = apiInstance.deleteWebhookProxyApiV1AfnorFlowV1WebhooksWebhookUidDelete(webhookUid);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaFlowServiceApi#deleteWebhookProxyApiV1AfnorFlowV1WebhooksWebhookUidDelete");
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
| **webhookUid** | **String**| Webhook unique identifier (UUID) | |

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
| **204** | Webhook deleted |  -  |
| **401** | Authentication error - Missing or invalid token |  -  |
| **403** | Forbidden - Insufficient permissions |  -  |
| **404** | Resource not found |  -  |
| **429** | Too many requests - Rate limit exceeded |  -  |
| **500** | Internal server error |  -  |
| **503** | Service unavailable - PDP temporarily unavailable |  -  |
| **422** | Validation Error |  -  |

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

<a id="getWebhookProxyApiV1AfnorFlowV1WebhooksWebhookUidGet"></a>
# **getWebhookProxyApiV1AfnorFlowV1WebhooksWebhookUidGet**
> AFNORWebhook getWebhookProxyApiV1AfnorFlowV1WebhooksWebhookUidGet(webhookUid)

Get a webhook

Get details of a specific webhook (AFNOR XP Z12-013 v1.2.0)

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
    String webhookUid = "webhookUid_example"; // String | Webhook unique identifier (UUID)
    try {
      AFNORWebhook result = apiInstance.getWebhookProxyApiV1AfnorFlowV1WebhooksWebhookUidGet(webhookUid);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaFlowServiceApi#getWebhookProxyApiV1AfnorFlowV1WebhooksWebhookUidGet");
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
| **webhookUid** | **String**| Webhook unique identifier (UUID) | |

### Return type

[**AFNORWebhook**](AFNORWebhook.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK - Webhook details returned |  -  |
| **401** | Authentication error - Missing or invalid token |  -  |
| **403** | Forbidden - Insufficient permissions |  -  |
| **404** | Resource not found |  -  |
| **429** | Too many requests - Rate limit exceeded |  -  |
| **500** | Internal server error |  -  |
| **503** | Service unavailable - PDP temporarily unavailable |  -  |
| **422** | Validation Error |  -  |

<a id="listWebhooksProxyApiV1AfnorFlowV1WebhooksGet"></a>
# **listWebhooksProxyApiV1AfnorFlowV1WebhooksGet**
> List&lt;AFNORWebhook&gt; listWebhooksProxyApiV1AfnorFlowV1WebhooksGet()

List webhooks

List all registered webhooks (AFNOR XP Z12-013 v1.2.0)

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
      List<AFNORWebhook> result = apiInstance.listWebhooksProxyApiV1AfnorFlowV1WebhooksGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaFlowServiceApi#listWebhooksProxyApiV1AfnorFlowV1WebhooksGet");
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

[**List&lt;AFNORWebhook&gt;**](AFNORWebhook.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK - List of webhooks returned |  -  |
| **401** | Authentication error - Missing or invalid token |  -  |
| **403** | Forbidden - Insufficient permissions |  -  |
| **429** | Too many requests - Rate limit exceeded |  -  |
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

<a id="updateWebhookProxyApiV1AfnorFlowV1WebhooksWebhookUidPatch"></a>
# **updateWebhookProxyApiV1AfnorFlowV1WebhooksWebhookUidPatch**
> AFNORWebhook updateWebhookProxyApiV1AfnorFlowV1WebhooksWebhookUidPatch(webhookUid)

Update a webhook

Partially update a webhook subscription (AFNOR XP Z12-013 v1.2.0)

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
    String webhookUid = "webhookUid_example"; // String | Webhook unique identifier (UUID)
    try {
      AFNORWebhook result = apiInstance.updateWebhookProxyApiV1AfnorFlowV1WebhooksWebhookUidPatch(webhookUid);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaFlowServiceApi#updateWebhookProxyApiV1AfnorFlowV1WebhooksWebhookUidPatch");
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
| **webhookUid** | **String**| Webhook unique identifier (UUID) | |

### Return type

[**AFNORWebhook**](AFNORWebhook.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK - Webhook updated successfully |  -  |
| **400** | Bad request - Invalid input parameters |  -  |
| **401** | Authentication error - Missing or invalid token |  -  |
| **403** | Forbidden - Insufficient permissions |  -  |
| **404** | Resource not found |  -  |
| **422** | Unprocessable entity - Business rule validation failed |  -  |
| **429** | Too many requests - Rate limit exceeded |  -  |
| **500** | Internal server error |  -  |
| **503** | Service unavailable - PDP temporarily unavailable |  -  |

