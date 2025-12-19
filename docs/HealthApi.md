# HealthApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**healthcheckHealthcheckGet**](HealthApi.md#healthcheckHealthcheckGet) | **GET** /healthcheck | Docker healthcheck endpoint |
| [**rootGet**](HealthApi.md#rootGet) | **GET** / | Check API status |


<a id="healthcheckHealthcheckGet"></a>
# **healthcheckHealthcheckGet**
> Object healthcheckHealthcheckGet()

Docker healthcheck endpoint

Healthcheck endpoint for Docker and load balancers.  Useful for: - Docker healthcheck - Kubernetes liveness/readiness probes - Load balancers (Nginx, HAProxy) - Availability monitoring - Zero downtime deployment  Returns a 200 code if the API is operational.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.HealthApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    HealthApi apiInstance = new HealthApi(defaultClient);
    try {
      Object result = apiInstance.healthcheckHealthcheckGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling HealthApi#healthcheckHealthcheckGet");
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
| **200** | API is healthy |  -  |

<a id="rootGet"></a>
# **rootGet**
> Object rootGet()

Check API status

Health check endpoint to verify the API is responding.  Useful for: - Availability monitoring - Integration tests - Load balancers

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.HealthApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    HealthApi apiInstance = new HealthApi(defaultClient);
    try {
      Object result = apiInstance.rootGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling HealthApi#rootGet");
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
| **200** | API is operational |  -  |

