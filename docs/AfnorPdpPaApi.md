# AfnorPdpPaApi

All URIs are relative to *https://factpulse.fr*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getFluxEntrantApiV1AfnorIncomingFlowsFlowIdGet**](AfnorPdpPaApi.md#getFluxEntrantApiV1AfnorIncomingFlowsFlowIdGet) | **GET** /api/v1/afnor/incoming-flows/{flow_id} | Retrieve and extract an incoming invoice |
| [**oauthTokenProxyApiV1AfnorOauthTokenPost**](AfnorPdpPaApi.md#oauthTokenProxyApiV1AfnorOauthTokenPost) | **POST** /api/v1/afnor/oauth/token | Test PDP OAuth2 credentials |


<a id="getFluxEntrantApiV1AfnorIncomingFlowsFlowIdGet"></a>
# **getFluxEntrantApiV1AfnorIncomingFlowsFlowIdGet**
> IncomingInvoice getFluxEntrantApiV1AfnorIncomingFlowsFlowIdGet(flowId, includeDocument)

Retrieve and extract an incoming invoice

Downloads an incoming flow from the AFNOR PDP and extracts invoice metadata into a unified JSON format. Supports Factur-X, CII, and UBL formats.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorPdpPaApi apiInstance = new AfnorPdpPaApi(defaultClient);
    String flowId = "flowId_example"; // String | AFNOR flow ID (UUID format)
    Boolean includeDocument = false; // Boolean | Include base64-encoded document in response
    try {
      IncomingInvoice result = apiInstance.getFluxEntrantApiV1AfnorIncomingFlowsFlowIdGet(flowId, includeDocument);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaApi#getFluxEntrantApiV1AfnorIncomingFlowsFlowIdGet");
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
| **flowId** | **String**| AFNOR flow ID (UUID format) | |
| **includeDocument** | **Boolean**| Include base64-encoded document in response | [optional] [default to false] |

### Return type

[**IncomingInvoice**](IncomingInvoice.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Invoice extracted successfully |  -  |
| **400** | Unsupported or invalid invoice format |  -  |
| **401** | Not authenticated |  -  |
| **404** | Flow not found |  -  |
| **503** | PDP service unavailable |  -  |
| **422** | Validation Error |  -  |

<a id="oauthTokenProxyApiV1AfnorOauthTokenPost"></a>
# **oauthTokenProxyApiV1AfnorOauthTokenPost**
> Object oauthTokenProxyApiV1AfnorOauthTokenPost()

Test PDP OAuth2 credentials

OAuth2 proxy to validate PDP credentials. Use this endpoint to verify that OAuth credentials (client_id, client_secret) are valid before saving a PDP configuration. This endpoint is public (no authentication required).

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");

    AfnorPdpPaApi apiInstance = new AfnorPdpPaApi(defaultClient);
    try {
      Object result = apiInstance.oauthTokenProxyApiV1AfnorOauthTokenPost();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaApi#oauthTokenProxyApiV1AfnorOauthTokenPost");
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
| **200** | OAuth2 token acquired successfully - credentials are valid |  -  |
| **401** | Invalid credentials - client_id or client_secret is wrong |  -  |
| **503** | PDP OAuth server unavailable |  -  |

