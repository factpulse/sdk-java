# AfnorPdpPaDirectoryServiceApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLinePost**](AfnorPdpPaDirectoryServiceApi.md#createDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLinePost) | **POST** /api/v1/afnor/directory/v1/directory-line | Creating a directory line |
| [**createRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodePost**](AfnorPdpPaDirectoryServiceApi.md#createRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodePost) | **POST** /api/v1/afnor/directory/v1/routing-code | Create a routing code |
| [**deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDelete**](AfnorPdpPaDirectoryServiceApi.md#deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDelete) | **DELETE** /api/v1/afnor/directory/v1/directory-line/id-instance:{id_instance} | Delete a directory line |
| [**directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGet**](AfnorPdpPaDirectoryServiceApi.md#directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGet) | **GET** /api/v1/afnor/directory/v1/healthcheck | Healthcheck Directory Service |
| [**getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGet**](AfnorPdpPaDirectoryServiceApi.md#getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGet) | **GET** /api/v1/afnor/directory/v1/directory-line/code:{addressing_identifier} | Get a directory line |
| [**getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGet**](AfnorPdpPaDirectoryServiceApi.md#getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGet) | **GET** /api/v1/afnor/directory/v1/directory-line/id-instance:{id_instance} | Get a directory line |
| [**getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGet**](AfnorPdpPaDirectoryServiceApi.md#getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGet) | **GET** /api/v1/afnor/directory/v1/routing-code/id-instance:{id_instance} | Get a routing code by instance-id |
| [**getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGet**](AfnorPdpPaDirectoryServiceApi.md#getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGet) | **GET** /api/v1/afnor/directory/v1/routing-code/siret:{siret}/code:{routing_identifier} | Get a routing code by SIRET and routing identifier |
| [**getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGet**](AfnorPdpPaDirectoryServiceApi.md#getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGet) | **GET** /api/v1/afnor/directory/v1/siren/code-insee:{siren} | Consult a siren (legal unit) by SIREN number |
| [**getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGet**](AfnorPdpPaDirectoryServiceApi.md#getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGet) | **GET** /api/v1/afnor/directory/v1/siren/id-instance:{id_instance} | Gets a siren (legal unit) by instance ID |
| [**getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGet**](AfnorPdpPaDirectoryServiceApi.md#getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGet) | **GET** /api/v1/afnor/directory/v1/siret/code-insee:{siret} | Gets a siret (facility) by SIRET number |
| [**getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGet**](AfnorPdpPaDirectoryServiceApi.md#getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGet) | **GET** /api/v1/afnor/directory/v1/siret/id-instance:{id_instance} | Gets a siret (facility) by id-instance |
| [**patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatch**](AfnorPdpPaDirectoryServiceApi.md#patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatch) | **PATCH** /api/v1/afnor/directory/v1/directory-line/id-instance:{id_instance} | Partially updates a directory line |
| [**patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatch**](AfnorPdpPaDirectoryServiceApi.md#patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatch) | **PATCH** /api/v1/afnor/directory/v1/routing-code/id-instance:{id_instance} | Partially update a private routing code |
| [**putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePut**](AfnorPdpPaDirectoryServiceApi.md#putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePut) | **PUT** /api/v1/afnor/directory/v1/routing-code/id-instance:{id_instance} | Completely update a private routing code |
| [**searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPost**](AfnorPdpPaDirectoryServiceApi.md#searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPost) | **POST** /api/v1/afnor/directory/v1/directory-line/search | Search for a directory line |
| [**searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPost**](AfnorPdpPaDirectoryServiceApi.md#searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPost) | **POST** /api/v1/afnor/directory/v1/routing-code/search | Search for a routing code |
| [**searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPost**](AfnorPdpPaDirectoryServiceApi.md#searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPost) | **POST** /api/v1/afnor/directory/v1/siren/search | SIREN search (or legal unit) |
| [**searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPost**](AfnorPdpPaDirectoryServiceApi.md#searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPost) | **POST** /api/v1/afnor/directory/v1/siret/search | Search for a SIRET (facility) |


<a id="createDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLinePost"></a>
# **createDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLinePost**
> Object createDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLinePost()

Creating a directory line

Create a line in the directory

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaDirectoryServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    try {
      Object result = apiInstance.createDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLinePost();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaDirectoryServiceApi#createDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLinePost");
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
| **200** | Successful Response |  -  |
| **201** | Directory line created successfully |  -  |
| **400** | Invalid request |  -  |
| **401** | Not authenticated |  -  |

<a id="createRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodePost"></a>
# **createRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodePost**
> Object createRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodePost()

Create a routing code

Create a routing code in the directory

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaDirectoryServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    try {
      Object result = apiInstance.createRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodePost();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaDirectoryServiceApi#createRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodePost");
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
| **200** | Successful Response |  -  |
| **201** | Routing code created successfully |  -  |
| **400** | Invalid request |  -  |
| **401** | Not authenticated |  -  |

<a id="deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDelete"></a>
# **deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDelete**
> Object deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDelete(idInstance)

Delete a directory line

Delete a directory line

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaDirectoryServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    String idInstance = "idInstance_example"; // String | 
    try {
      Object result = apiInstance.deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDelete(idInstance);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaDirectoryServiceApi#deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDelete");
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
| **idInstance** | **String**|  | |

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
| **200** | Successful Response |  -  |
| **204** | Directory line deleted |  -  |
| **404** | Directory line not found |  -  |
| **401** | Not authenticated |  -  |
| **422** | Validation Error |  -  |

<a id="directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGet"></a>
# **directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGet**
> Object directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGet()

Healthcheck Directory Service

Check Directory Service availability

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaDirectoryServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    try {
      Object result = apiInstance.directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaDirectoryServiceApi#directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGet");
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

<a id="getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGet"></a>
# **getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGet**
> Object getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGet(addressingIdentifier)

Get a directory line

Get a directory line identified by an addressing identifier

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaDirectoryServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    String addressingIdentifier = "addressingIdentifier_example"; // String | 
    try {
      Object result = apiInstance.getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGet(addressingIdentifier);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaDirectoryServiceApi#getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGet");
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
| **addressingIdentifier** | **String**|  | |

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
| **200** | Directory line details |  -  |
| **404** | Directory line not found |  -  |
| **401** | Not authenticated |  -  |
| **422** | Validation Error |  -  |

<a id="getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGet"></a>
# **getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGet**
> Object getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGet(idInstance)

Get a directory line

Get a directory line identified by its idInstance

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaDirectoryServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    String idInstance = "idInstance_example"; // String | 
    try {
      Object result = apiInstance.getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGet(idInstance);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaDirectoryServiceApi#getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGet");
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
| **idInstance** | **String**|  | |

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
| **200** | Directory line details |  -  |
| **404** | Directory line not found |  -  |
| **401** | Not authenticated |  -  |
| **422** | Validation Error |  -  |

<a id="getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGet"></a>
# **getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGet**
> Object getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGet(idInstance)

Get a routing code by instance-id

Get a routing code identified by its idInstance

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaDirectoryServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    String idInstance = "idInstance_example"; // String | 
    try {
      Object result = apiInstance.getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGet(idInstance);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaDirectoryServiceApi#getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGet");
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
| **idInstance** | **String**|  | |

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
| **200** | Routing code details |  -  |
| **404** | Routing code not found |  -  |
| **401** | Not authenticated |  -  |
| **422** | Validation Error |  -  |

<a id="getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGet"></a>
# **getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGet**
> Object getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGet(siret, routingIdentifier)

Get a routing code by SIRET and routing identifier

Consult a routing code identified by SIRET and routing identifier

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaDirectoryServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    String siret = "siret_example"; // String | 
    String routingIdentifier = "routingIdentifier_example"; // String | 
    try {
      Object result = apiInstance.getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGet(siret, routingIdentifier);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaDirectoryServiceApi#getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGet");
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
| **siret** | **String**|  | |
| **routingIdentifier** | **String**|  | |

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
| **200** | Routing code details |  -  |
| **404** | Routing code not found |  -  |
| **401** | Not authenticated |  -  |
| **422** | Validation Error |  -  |

<a id="getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGet"></a>
# **getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGet**
> Object getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGet(siren)

Consult a siren (legal unit) by SIREN number

Returns details of a company (legal unit) identified by its SIREN number

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaDirectoryServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    String siren = "siren_example"; // String | 
    try {
      Object result = apiInstance.getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGet(siren);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaDirectoryServiceApi#getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGet");
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
| **siren** | **String**|  | |

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
| **200** | Company information |  -  |
| **404** | Company not found |  -  |
| **401** | Not authenticated |  -  |
| **422** | Validation Error |  -  |

<a id="getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGet"></a>
# **getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGet**
> Object getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGet(idInstance)

Gets a siren (legal unit) by instance ID

Get a company (legal unit) identified by its idInstance

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaDirectoryServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    String idInstance = "idInstance_example"; // String | 
    try {
      Object result = apiInstance.getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGet(idInstance);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaDirectoryServiceApi#getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGet");
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
| **idInstance** | **String**|  | |

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
| **200** | Company information |  -  |
| **404** | Company not found |  -  |
| **401** | Not authenticated |  -  |
| **422** | Validation Error |  -  |

<a id="getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGet"></a>
# **getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGet**
> Object getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGet(siret)

Gets a siret (facility) by SIRET number

Get an establishment identified by its SIRET number

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaDirectoryServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    String siret = "siret_example"; // String | 
    try {
      Object result = apiInstance.getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGet(siret);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaDirectoryServiceApi#getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGet");
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
| **siret** | **String**|  | |

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
| **200** | Establishment information |  -  |
| **404** | Establishment not found |  -  |
| **401** | Not authenticated |  -  |
| **422** | Validation Error |  -  |

<a id="getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGet"></a>
# **getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGet**
> Object getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGet(idInstance)

Gets a siret (facility) by id-instance

Get an establishment identified by its idInstance

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaDirectoryServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    String idInstance = "idInstance_example"; // String | 
    try {
      Object result = apiInstance.getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGet(idInstance);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaDirectoryServiceApi#getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGet");
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
| **idInstance** | **String**|  | |

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
| **200** | Establishment information |  -  |
| **404** | Establishment not found |  -  |
| **401** | Not authenticated |  -  |
| **422** | Validation Error |  -  |

<a id="patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatch"></a>
# **patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatch**
> Object patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatch(idInstance)

Partially updates a directory line

Partially update a directory line

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaDirectoryServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    String idInstance = "idInstance_example"; // String | 
    try {
      Object result = apiInstance.patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatch(idInstance);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaDirectoryServiceApi#patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatch");
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
| **idInstance** | **String**|  | |

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
| **200** | Directory line updated |  -  |
| **404** | Directory line not found |  -  |
| **401** | Not authenticated |  -  |
| **422** | Validation Error |  -  |

<a id="patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatch"></a>
# **patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatch**
> Object patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatch(idInstance)

Partially update a private routing code

Partially update a private routing code

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaDirectoryServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    String idInstance = "idInstance_example"; // String | 
    try {
      Object result = apiInstance.patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatch(idInstance);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaDirectoryServiceApi#patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatch");
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
| **idInstance** | **String**|  | |

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
| **200** | Routing code updated |  -  |
| **404** | Routing code not found |  -  |
| **401** | Not authenticated |  -  |
| **422** | Validation Error |  -  |

<a id="putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePut"></a>
# **putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePut**
> Object putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePut(idInstance)

Completely update a private routing code

Completely update a private routing code

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaDirectoryServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    String idInstance = "idInstance_example"; // String | 
    try {
      Object result = apiInstance.putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePut(idInstance);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaDirectoryServiceApi#putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePut");
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
| **idInstance** | **String**|  | |

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
| **200** | Routing code updated |  -  |
| **404** | Routing code not found |  -  |
| **401** | Not authenticated |  -  |
| **422** | Validation Error |  -  |

<a id="searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPost"></a>
# **searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPost**
> Object searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPost()

Search for a directory line

Search for directory lines by criteria

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaDirectoryServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    try {
      Object result = apiInstance.searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPost();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaDirectoryServiceApi#searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPost");
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
| **200** | Search results |  -  |
| **401** | Not authenticated |  -  |

<a id="searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPost"></a>
# **searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPost**
> Object searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPost()

Search for a routing code

Search for routing codes by criteria

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaDirectoryServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    try {
      Object result = apiInstance.searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPost();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaDirectoryServiceApi#searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPost");
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
| **200** | Search results |  -  |
| **401** | Not authenticated |  -  |

<a id="searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPost"></a>
# **searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPost**
> Object searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPost()

SIREN search (or legal unit)

Multi-criteria search for companies (legal units)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaDirectoryServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    try {
      Object result = apiInstance.searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPost();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaDirectoryServiceApi#searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPost");
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
| **200** | Returns one or more companies |  -  |
| **401** | Not authenticated |  -  |

<a id="searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPost"></a>
# **searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPost**
> Object searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPost()

Search for a SIRET (facility)

Multi-criteria search for establishments

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaDirectoryServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    try {
      Object result = apiInstance.searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPost();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaDirectoryServiceApi#searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPost");
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
| **200** | Returns one or more establishments |  -  |
| **401** | Not authenticated |  -  |

