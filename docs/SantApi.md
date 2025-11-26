# SantApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**healthcheckHealthcheckGet**](SantApi.md#healthcheckHealthcheckGet) | **GET** /healthcheck | Endpoint de healthcheck pour Docker |
| [**racineGet**](SantApi.md#racineGet) | **GET** / | Vérifier l&#39;état de l&#39;API |


<a id="healthcheckHealthcheckGet"></a>
# **healthcheckHealthcheckGet**
> Object healthcheckHealthcheckGet()

Endpoint de healthcheck pour Docker

Endpoint de healthcheck pour Docker et les load balancers.  Utile pour : - Docker healthcheck - Kubernetes liveness/readiness probes - Load balancers (Nginx, HAProxy) - Monitoring de disponibilité - Déploiement zero downtime  Retourne un code 200 si l&#39;API est opérationnelle.

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
      Object result = apiInstance.healthcheckHealthcheckGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SantApi#healthcheckHealthcheckGet");
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
| **200** | L&#39;API est en bonne santé |  -  |

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

