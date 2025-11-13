# AfnorPdpPaApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**oauthTokenProxyApiV1AfnorOauthTokenPost**](AfnorPdpPaApi.md#oauthTokenProxyApiV1AfnorOauthTokenPost) | **POST** /api/v1/afnor/oauth/token | Endpoint OAuth2 pour authentification AFNOR |


<a id="oauthTokenProxyApiV1AfnorOauthTokenPost"></a>
# **oauthTokenProxyApiV1AfnorOauthTokenPost**
> Object oauthTokenProxyApiV1AfnorOauthTokenPost()

Endpoint OAuth2 pour authentification AFNOR

Endpoint proxy OAuth2 pour obtenir un token d&#39;accès AFNOR. Fait proxy vers le mock AFNOR (sandbox) ou la vraie PDP selon MOCK_AFNOR_BASE_URL. Cet endpoint est public (pas d&#39;auth Django requise) car il est appelé par le SDK AFNOR.

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
| **200** | Token OAuth2 acquis avec succès |  -  |
| **401** | Credentials invalides |  -  |

