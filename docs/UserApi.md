# UserApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getUserInfoApiV1MeGet**](UserApi.md#getUserInfoApiV1MeGet) | **GET** /api/v1/me | Get current user information |


<a id="getUserInfoApiV1MeGet"></a>
# **getUserInfoApiV1MeGet**
> Object getUserInfoApiV1MeGet()

Get current user information

Returns information about the authenticated user.  This endpoint allows you to: - Verify that authentication works - Get connected account details - Test JWT token validity - Check your consumption quota  **Requires valid authentication.**

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    UserApi apiInstance = new UserApi(defaultClient);
    try {
      Object result = apiInstance.getUserInfoApiV1MeGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#getUserInfoApiV1MeGet");
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
| **200** | User information |  -  |

