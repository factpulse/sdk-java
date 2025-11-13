# SantApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**racineGet**](SantApi.md#racineGet) | **GET** / | Vérifier l&#39;état de l&#39;API |


<a id="racineGet"></a>
# **racineGet**
> Object racineGet()

Vérifier l&#39;état de l&#39;API

Endpoint de health check pour vérifier que l&#39;API répond.  Utile pour : - Monitoring de disponibilité - Tests d&#39;intégration - Load balancers

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.SantApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    SantApi apiInstance = new SantApi(defaultClient);
    try {
      Object result = apiInstance.racineGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SantApi#racineGet");
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
| **200** | L&#39;API est opérationnelle |  -  |

