# AfnorPdpPaApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getAfnorCredentialsApiV1AfnorCredentialsGet**](AfnorPdpPaApi.md#getAfnorCredentialsApiV1AfnorCredentialsGet) | **GET** /api/v1/afnor/credentials | Retrieve stored AFNOR credentials |
| [**getFluxEntrantApiV1AfnorIncomingFlowsFlowIdGet**](AfnorPdpPaApi.md#getFluxEntrantApiV1AfnorIncomingFlowsFlowIdGet) | **GET** /api/v1/afnor/incoming-flows/{flow_id} | Retrieve and extract an incoming invoice |
| [**oauthTokenProxyApiV1AfnorOauthTokenPost**](AfnorPdpPaApi.md#oauthTokenProxyApiV1AfnorOauthTokenPost) | **POST** /api/v1/afnor/oauth/token | OAuth2 endpoint for AFNOR authentication |


<a id="getAfnorCredentialsApiV1AfnorCredentialsGet"></a>
# **getAfnorCredentialsApiV1AfnorCredentialsGet**
> Object getAfnorCredentialsApiV1AfnorCredentialsGet()

Retrieve stored AFNOR credentials

Retrieves stored AFNOR/PDP credentials for the JWT&#39;s client_uid. This endpoint is used by the SDK in &#39;stored&#39; mode to retrieve credentials before performing AFNOR OAuth itself.

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
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorPdpPaApi apiInstance = new AfnorPdpPaApi(defaultClient);
    try {
      Object result = apiInstance.getAfnorCredentialsApiV1AfnorCredentialsGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaApi#getAfnorCredentialsApiV1AfnorCredentialsGet");
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

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | AFNOR credentials retrieved successfully |  -  |
| **400** | No client_uid in JWT |  -  |
| **401** | Not authenticated - Missing or invalid JWT token |  -  |
| **404** | Client not found or no AFNOR credentials configured |  -  |

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
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorPdpPaApi apiInstance = new AfnorPdpPaApi(defaultClient);
    String flowId = "flowId_example"; // String | 
    Boolean includeDocument = false; // Boolean | 
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
| **flowId** | **String**|  | |
| **includeDocument** | **Boolean**|  | [optional] [default to false] |

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

OAuth2 endpoint for AFNOR authentication

OAuth2 proxy endpoint to obtain an AFNOR access token. Proxies to AFNOR mock (sandbox) or real PDP depending on MOCK_AFNOR_BASE_URL. This endpoint is public (no Django auth required) as it is called by the AFNOR SDK.

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
    defaultClient.setBasePath("http://localhost");

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
| **200** | OAuth2 token acquired successfully |  -  |
| **401** | Invalid credentials |  -  |

