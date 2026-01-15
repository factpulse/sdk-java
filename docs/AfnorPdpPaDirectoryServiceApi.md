# AfnorPdpPaDirectoryServiceApi

All URIs are relative to *https://factpulse.fr*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLinePost**](AfnorPdpPaDirectoryServiceApi.md#createDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLinePost) | **POST** /api/v1/afnor/directory/v1/directory-line | Creating a directory line |
| [**createRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodePost**](AfnorPdpPaDirectoryServiceApi.md#createRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodePost) | **POST** /api/v1/afnor/directory/v1/routing-code | Create a routing code |
| [**deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDelete**](AfnorPdpPaDirectoryServiceApi.md#deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDelete) | **DELETE** /api/v1/afnor/directory/v1/directory-line/id-instance:{id_instance} | Delete a directory line |
| [**directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGet**](AfnorPdpPaDirectoryServiceApi.md#directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGet) | **GET** /api/v1/afnor/directory/v1/healthcheck | Healthcheck Directory Service |
| [**getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGet**](AfnorPdpPaDirectoryServiceApi.md#getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGet) | **GET** /api/v1/afnor/directory/v1/directory-line/code:{addressing_identifier} | Get a directory line. |
| [**getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGet**](AfnorPdpPaDirectoryServiceApi.md#getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGet) | **GET** /api/v1/afnor/directory/v1/directory-line/id-instance:{id_instance} | Get a directory line. |
| [**getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGet**](AfnorPdpPaDirectoryServiceApi.md#getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGet) | **GET** /api/v1/afnor/directory/v1/routing-code/id-instance:{id_instance} | Get a routing code by instance-id. |
| [**getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGet**](AfnorPdpPaDirectoryServiceApi.md#getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGet) | **GET** /api/v1/afnor/directory/v1/routing-code/siret:{siret}/code:{routing_identifier} | Get a routing code by SIRET and routing identifier |
| [**getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGet**](AfnorPdpPaDirectoryServiceApi.md#getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGet) | **GET** /api/v1/afnor/directory/v1/siren/code-insee:{siren} | Consult a siren (legal unit) by SIREN number |
| [**getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGet**](AfnorPdpPaDirectoryServiceApi.md#getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGet) | **GET** /api/v1/afnor/directory/v1/siren/id-instance:{id_instance} | Gets a siren (legal unit) by instance ID |
| [**getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGet**](AfnorPdpPaDirectoryServiceApi.md#getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGet) | **GET** /api/v1/afnor/directory/v1/siret/code-insee:{siret} | Gets a siret (facility) by SIRET number |
| [**getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGet**](AfnorPdpPaDirectoryServiceApi.md#getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGet) | **GET** /api/v1/afnor/directory/v1/siret/id-instance:{id_instance} | Gets a siret (facility) by id-instance |
| [**patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatch**](AfnorPdpPaDirectoryServiceApi.md#patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatch) | **PATCH** /api/v1/afnor/directory/v1/directory-line/id-instance:{id_instance} | Partially updates a directory line.. |
| [**patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatch**](AfnorPdpPaDirectoryServiceApi.md#patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatch) | **PATCH** /api/v1/afnor/directory/v1/routing-code/id-instance:{id_instance} | Partially update a private routing code. |
| [**putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePut**](AfnorPdpPaDirectoryServiceApi.md#putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePut) | **PUT** /api/v1/afnor/directory/v1/routing-code/id-instance:{id_instance} | Completely update a private routing code. |
| [**searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPost**](AfnorPdpPaDirectoryServiceApi.md#searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPost) | **POST** /api/v1/afnor/directory/v1/directory-line/search | Search for a directory line |
| [**searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPost**](AfnorPdpPaDirectoryServiceApi.md#searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPost) | **POST** /api/v1/afnor/directory/v1/routing-code/search | Search for a routing code |
| [**searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPost**](AfnorPdpPaDirectoryServiceApi.md#searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPost) | **POST** /api/v1/afnor/directory/v1/siren/search | SIREN search (or legal unit) |
| [**searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPost**](AfnorPdpPaDirectoryServiceApi.md#searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPost) | **POST** /api/v1/afnor/directory/v1/siret/search | Search for a SIRET (facility) |


<a id="createDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLinePost"></a>
# **createDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLinePost**
> Object createDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLinePost()

Creating a directory line

Creation of a new directory line for a SIREN, a SIRET or a ROUTING CODE.

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
    defaultClient.setBasePath("https://factpulse.fr");

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

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **201** | A new resource has been created. |  -  |
| **400** | Bad request. The request is invalid or cannot be completed. |  -  |
| **401** | Unauthorized. The request requires user authentication. |  -  |
| **403** | Forbidden. The server understood the request but denied access or access is not authorized. |  -  |
| **404** | Not found. There is no resource at the given URI. |  -  |
| **408** | Request timeout exceeded. |  -  |
| **422** | Data validation error. |  -  |
| **429** | The client has issued too many calls within a given time frame. |  -  |
| **500** | Internal Server Error. |  -  |
| **501** | Not implemented. |  -  |
| **503** | Service unavailable. |  -  |

<a id="createRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodePost"></a>
# **createRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodePost**
> Object createRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodePost()

Create a routing code

Creating a routing code.

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
    defaultClient.setBasePath("https://factpulse.fr");

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

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **201** | A new resource has been created. |  -  |
| **400** | Bad request. The request is invalid or cannot be completed. |  -  |
| **401** | Unauthorized. The request requires user authentication. |  -  |
| **403** | Forbidden. The server understood the request but denied access or access is not authorized. |  -  |
| **404** | Not found. There is no resource at the given URI. |  -  |
| **408** | Request timeout exceeded. |  -  |
| **422** | Data validation error. |  -  |
| **429** | The client has issued too many calls within a given time frame. |  -  |
| **500** | Internal Server Error. |  -  |
| **501** | Not implemented. |  -  |
| **503** | Service unavailable. |  -  |

<a id="deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDelete"></a>
# **deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDelete**
> Object deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDelete(idInstance)

Delete a directory line

Delete a directory line.

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
    defaultClient.setBasePath("https://factpulse.fr");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    String idInstance = "idInstance_example"; // String | AFNOR instance ID (UUID)
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
| **idInstance** | **String**| AFNOR instance ID (UUID) | |

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
| **204** | OK. The resource has been deleted. |  -  |
| **400** | Bad request. The request is invalid or cannot be completed. |  -  |
| **401** | Unauthorized. The request requires user authentication. |  -  |
| **403** | Forbidden. The server understood the request but denied access or access is not authorized. |  -  |
| **404** | Not found. There is no resource at the given URI. |  -  |
| **408** | Request timeout exceeded. |  -  |
| **422** | Data validation error. |  -  |
| **429** | The client has issued too many calls within a given time frame. |  -  |
| **500** | Internal Server Error. |  -  |
| **501** | Not implemented. |  -  |
| **503** | Service unavailable. |  -  |

<a id="directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGet"></a>
# **directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGet**
> Object directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGet()

Healthcheck Directory Service

Check Directory Service availability (AFNOR XP Z12-013 compliant)

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
    defaultClient.setBasePath("https://factpulse.fr");

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
| **200** | OK - Service is operational |  -  |
| **500** | Internal Server Error. |  -  |
| **503** | Service unavailable. |  -  |

<a id="getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGet"></a>
# **getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGet**
> AFNORDirectoryLinePayloadHistoryLegalUnitFacilityRoutingCode getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGet(addressingIdentifier)

Get a directory line.

Retrieve the data from the directory line corresponding to the identifier passed in parameters.

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
    defaultClient.setBasePath("https://factpulse.fr");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    String addressingIdentifier = "addressingIdentifier_example"; // String | Addressing identifier (SIREN, SIRET or routing code)
    try {
      AFNORDirectoryLinePayloadHistoryLegalUnitFacilityRoutingCode result = apiInstance.getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGet(addressingIdentifier);
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
| **addressingIdentifier** | **String**| Addressing identifier (SIREN, SIRET or routing code) | |

### Return type

[**AFNORDirectoryLinePayloadHistoryLegalUnitFacilityRoutingCode**](AFNORDirectoryLinePayloadHistoryLegalUnitFacilityRoutingCode.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Retourns a directory line. |  -  |
| **400** | Bad request. The request is invalid or cannot be completed. |  -  |
| **401** | Unauthorized. The request requires user authentication. |  -  |
| **403** | Forbidden. The server understood the request but denied access or access is not authorized. |  -  |
| **404** | Not found. There is no resource at the given URI. |  -  |
| **408** | Request timeout exceeded. |  -  |
| **422** | Data validation error. |  -  |
| **429** | The client has issued too many calls within a given time frame. |  -  |
| **500** | Internal Server Error. |  -  |
| **501** | Not implemented. |  -  |
| **503** | Service unavailable. |  -  |

<a id="getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGet"></a>
# **getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGet**
> AFNORDirectoryLinePayloadHistoryLegalUnitFacilityRoutingCode getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGet(idInstance)

Get a directory line.

Retrieve the data from the directory line corresponding to the identifier passed in parameters.

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
    defaultClient.setBasePath("https://factpulse.fr");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    String idInstance = "idInstance_example"; // String | AFNOR instance ID (UUID)
    try {
      AFNORDirectoryLinePayloadHistoryLegalUnitFacilityRoutingCode result = apiInstance.getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGet(idInstance);
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
| **idInstance** | **String**| AFNOR instance ID (UUID) | |

### Return type

[**AFNORDirectoryLinePayloadHistoryLegalUnitFacilityRoutingCode**](AFNORDirectoryLinePayloadHistoryLegalUnitFacilityRoutingCode.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Returns a directory line. |  -  |
| **400** | Bad request. The request is invalid or cannot be completed. |  -  |
| **401** | Unauthorized. The request requires user authentication. |  -  |
| **403** | Forbidden. The server understood the request but denied access or access is not authorized. |  -  |
| **404** | Not found. There is no resource at the given URI. |  -  |
| **408** | Request timeout exceeded. |  -  |
| **422** | Data validation error. |  -  |
| **429** | The client has issued too many calls within a given time frame. |  -  |
| **500** | Internal Server Error. |  -  |
| **501** | Not implemented. |  -  |
| **503** | Service unavailable. |  -  |

<a id="getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGet"></a>
# **getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGet**
> AFNORRoutingCodePayloadHistoryLegalUnitFacility getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGet(idInstance)

Get a routing code by instance-id.

Retrieve the Routing Code data corresponding to the Instance ID.

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
    defaultClient.setBasePath("https://factpulse.fr");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    String idInstance = "idInstance_example"; // String | AFNOR instance ID (UUID)
    try {
      AFNORRoutingCodePayloadHistoryLegalUnitFacility result = apiInstance.getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGet(idInstance);
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
| **idInstance** | **String**| AFNOR instance ID (UUID) | |

### Return type

[**AFNORRoutingCodePayloadHistoryLegalUnitFacility**](AFNORRoutingCodePayloadHistoryLegalUnitFacility.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Returns a routing code. |  -  |
| **400** | Bad request. The request is invalid or cannot be completed. |  -  |
| **401** | Unauthorized. The request requires user authentication. |  -  |
| **403** | Forbidden. The server understood the request but denied access or access is not authorized. |  -  |
| **404** | Not found. There is no resource at the given URI. |  -  |
| **408** | Request timeout exceeded. |  -  |
| **422** | Data validation error. |  -  |
| **429** | The client has issued too many calls within a given time frame. |  -  |
| **500** | Internal Server Error. |  -  |
| **501** | Not implemented. |  -  |
| **503** | Service unavailable. |  -  |

<a id="getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGet"></a>
# **getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGet**
> AFNORRoutingCodePayloadHistoryLegalUnitFacility getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGet(siret, routingIdentifier)

Get a routing code by SIRET and routing identifier

Retrieve the Routing Code data corresponding to the identifier passed in parameters.

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
    defaultClient.setBasePath("https://factpulse.fr");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    String siret = "siret_example"; // String | 14-digit SIRET number (INSEE establishment identifier)
    String routingIdentifier = "routingIdentifier_example"; // String | Routing code identifier
    try {
      AFNORRoutingCodePayloadHistoryLegalUnitFacility result = apiInstance.getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGet(siret, routingIdentifier);
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
| **siret** | **String**| 14-digit SIRET number (INSEE establishment identifier) | |
| **routingIdentifier** | **String**| Routing code identifier | |

### Return type

[**AFNORRoutingCodePayloadHistoryLegalUnitFacility**](AFNORRoutingCodePayloadHistoryLegalUnitFacility.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Returns a routing code. |  -  |
| **400** | Bad request. The request is invalid or cannot be completed. |  -  |
| **401** | Unauthorized. The request requires user authentication. |  -  |
| **403** | Forbidden. The server understood the request but denied access or access is not authorized. |  -  |
| **404** | Not found. There is no resource at the given URI. |  -  |
| **408** | Request timeout exceeded. |  -  |
| **422** | Data validation error. |  -  |
| **429** | The client has issued too many calls within a given time frame. |  -  |
| **500** | Internal Server Error. |  -  |
| **501** | Not implemented. |  -  |
| **503** | Service unavailable. |  -  |

<a id="getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGet"></a>
# **getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGet**
> AFNORLegalUnitPayloadHistory getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGet(siren)

Consult a siren (legal unit) by SIREN number

Returns the details of a company (legal unit) identified by the SIREN number passed as a parameter.

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
    defaultClient.setBasePath("https://factpulse.fr");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    String siren = "siren_example"; // String | 9-digit SIREN number (INSEE company identifier)
    try {
      AFNORLegalUnitPayloadHistory result = apiInstance.getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGet(siren);
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
| **siren** | **String**| 9-digit SIREN number (INSEE company identifier) | |

### Return type

[**AFNORLegalUnitPayloadHistory**](AFNORLegalUnitPayloadHistory.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Returns a company. |  -  |
| **400** | Bad request. The request is invalid or cannot be completed. |  -  |
| **401** | Unauthorized. The request requires user authentication. |  -  |
| **403** | Forbidden. The server understood the request but denied access or access is not authorized. |  -  |
| **404** | Not found. There is no resource at the given URI. |  -  |
| **408** | Request timeout exceeded. |  -  |
| **422** | Data validation error. |  -  |
| **429** | The client has issued too many calls within a given time frame. |  -  |
| **500** | Internal Server Error. |  -  |
| **501** | Not implemented. |  -  |
| **503** | Service unavailable. |  -  |

<a id="getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGet"></a>
# **getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGet**
> AFNORLegalUnitPayloadHistory getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGet(idInstance)

Gets a siren (legal unit) by instance ID

Returns the details of a company (legal unit) identified by the id-instance passed as a parameter.

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
    defaultClient.setBasePath("https://factpulse.fr");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    String idInstance = "idInstance_example"; // String | AFNOR instance ID (UUID)
    try {
      AFNORLegalUnitPayloadHistory result = apiInstance.getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGet(idInstance);
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
| **idInstance** | **String**| AFNOR instance ID (UUID) | |

### Return type

[**AFNORLegalUnitPayloadHistory**](AFNORLegalUnitPayloadHistory.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Returns a routing code. |  -  |
| **400** | Bad request. The request is invalid or cannot be completed. |  -  |
| **401** | Unauthorized. The request requires user authentication. |  -  |
| **403** | Forbidden. The server understood the request but denied access or access is not authorized. |  -  |
| **404** | Not found. There is no resource at the given URI. |  -  |
| **408** | Request timeout exceeded. |  -  |
| **422** | Data validation error. |  -  |
| **429** | The client has issued too many calls within a given time frame. |  -  |
| **500** | Internal Server Error. |  -  |
| **501** | Not implemented. |  -  |
| **503** | Service unavailable. |  -  |

<a id="getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGet"></a>
# **getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGet**
> AFNORFacilityPayloadHistory getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGet(siret)

Gets a siret (facility) by SIRET number

Returns the details of a facility associated to a SIRET.

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
    defaultClient.setBasePath("https://factpulse.fr");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    String siret = "siret_example"; // String | 14-digit SIRET number (INSEE establishment identifier)
    try {
      AFNORFacilityPayloadHistory result = apiInstance.getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGet(siret);
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
| **siret** | **String**| 14-digit SIRET number (INSEE establishment identifier) | |

### Return type

[**AFNORFacilityPayloadHistory**](AFNORFacilityPayloadHistory.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Returns a facility. |  -  |
| **400** | Bad request. The request is invalid or cannot be completed. |  -  |
| **401** | Unauthorized. The request requires user authentication. |  -  |
| **403** | Forbidden. The server understood the request but denied access or access is not authorized. |  -  |
| **404** | Not found. There is no resource at the given URI. |  -  |
| **408** | Request timeout exceeded. |  -  |
| **422** | Data validation error. |  -  |
| **429** | The client has issued too many calls within a given time frame. |  -  |
| **500** | Internal Server Error. |  -  |
| **501** | Not implemented. |  -  |
| **503** | Service unavailable. |  -  |

<a id="getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGet"></a>
# **getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGet**
> AFNORFacilityPayloadHistory getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGet(idInstance)

Gets a siret (facility) by id-instance

Returns the details of a facility according to an instance-id.

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
    defaultClient.setBasePath("https://factpulse.fr");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    String idInstance = "idInstance_example"; // String | AFNOR instance ID (UUID)
    try {
      AFNORFacilityPayloadHistory result = apiInstance.getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGet(idInstance);
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
| **idInstance** | **String**| AFNOR instance ID (UUID) | |

### Return type

[**AFNORFacilityPayloadHistory**](AFNORFacilityPayloadHistory.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Returns a routing code. |  -  |
| **400** | Bad request. The request is invalid or cannot be completed. |  -  |
| **401** | Unauthorized. The request requires user authentication. |  -  |
| **403** | Forbidden. The server understood the request but denied access or access is not authorized. |  -  |
| **404** | Not found. There is no resource at the given URI. |  -  |
| **408** | Request timeout exceeded. |  -  |
| **422** | Data validation error. |  -  |
| **429** | The client has issued too many calls within a given time frame. |  -  |
| **500** | Internal Server Error. |  -  |
| **501** | Not implemented. |  -  |
| **503** | Service unavailable. |  -  |

<a id="patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatch"></a>
# **patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatch**
> AFNORDirectoryLinePost201Response patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatch(idInstance)

Partially updates a directory line..

Partially updates a directory line.

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
    defaultClient.setBasePath("https://factpulse.fr");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    String idInstance = "idInstance_example"; // String | AFNOR instance ID (UUID)
    try {
      AFNORDirectoryLinePost201Response result = apiInstance.patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatch(idInstance);
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
| **idInstance** | **String**| AFNOR instance ID (UUID) | |

### Return type

[**AFNORDirectoryLinePost201Response**](AFNORDirectoryLinePost201Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Bad request. The request is invalid or cannot be completed. |  -  |
| **401** | Unauthorized. The request requires user authentication. |  -  |
| **403** | Forbidden. The server understood the request but denied access or access is not authorized. |  -  |
| **404** | Not found. There is no resource at the given URI. |  -  |
| **408** | Request timeout exceeded. |  -  |
| **422** | Data validation error. |  -  |
| **429** | The client has issued too many calls within a given time frame. |  -  |
| **500** | Internal Server Error. |  -  |
| **501** | Not implemented. |  -  |
| **503** | Service unavailable. |  -  |

<a id="patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatch"></a>
# **patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatch**
> AFNORRoutingCodePost201Response patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatch(idInstance)

Partially update a private routing code.

Partially update a private routing code.

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
    defaultClient.setBasePath("https://factpulse.fr");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    String idInstance = "idInstance_example"; // String | AFNOR instance ID (UUID)
    try {
      AFNORRoutingCodePost201Response result = apiInstance.patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatch(idInstance);
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
| **idInstance** | **String**| AFNOR instance ID (UUID) | |

### Return type

[**AFNORRoutingCodePost201Response**](AFNORRoutingCodePost201Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **206** | Request processed without error, but the volume of information returned has been reduced. |  -  |
| **400** | Bad request. The request is invalid or cannot be completed. |  -  |
| **401** | Unauthorized. The request requires user authentication. |  -  |
| **403** | Forbidden. The server understood the request but denied access or access is not authorized. |  -  |
| **404** | Not found. There is no resource at the given URI. |  -  |
| **408** | Request timeout exceeded. |  -  |
| **422** | Data validation error. |  -  |
| **429** | The client has issued too many calls within a given time frame. |  -  |
| **500** | Internal Server Error. |  -  |
| **501** | Not implemented. |  -  |
| **503** | Service unavailable. |  -  |

<a id="putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePut"></a>
# **putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePut**
> AFNORRoutingCodePost201Response putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePut(idInstance)

Completely update a private routing code.

Completely update a private routing code.

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
    defaultClient.setBasePath("https://factpulse.fr");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    String idInstance = "idInstance_example"; // String | AFNOR instance ID (UUID)
    try {
      AFNORRoutingCodePost201Response result = apiInstance.putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePut(idInstance);
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
| **idInstance** | **String**| AFNOR instance ID (UUID) | |

### Return type

[**AFNORRoutingCodePost201Response**](AFNORRoutingCodePost201Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **206** | Request processed without error, but the volume of information returned has been reduced. |  -  |
| **400** | Bad request. The request is invalid or cannot be completed. |  -  |
| **401** | Unauthorized. The request requires user authentication. |  -  |
| **403** | Forbidden. The server understood the request but denied access or access is not authorized. |  -  |
| **404** | Not found. There is no resource at the given URI. |  -  |
| **408** | Request timeout exceeded. |  -  |
| **422** | Data validation error. |  -  |
| **429** | The client has issued too many calls within a given time frame. |  -  |
| **500** | Internal Server Error. |  -  |
| **501** | Not implemented. |  -  |
| **503** | Service unavailable. |  -  |

<a id="searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPost"></a>
# **searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPost**
> AFNORDirectoryLineSearchPost200Response searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPost()

Search for a directory line

Search for directory lines that meet all the criteria passed as parameters and return the results in the desired format.

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
    defaultClient.setBasePath("https://factpulse.fr");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    try {
      AFNORDirectoryLineSearchPost200Response result = apiInstance.searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPost();
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

[**AFNORDirectoryLineSearchPost200Response**](AFNORDirectoryLineSearchPost200Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | returns the directory line(s) matching the search criteria. |  -  |
| **206** | Request processed without error, but the volume of information returned has been reduced. |  -  |
| **400** | Bad request. The request is invalid or cannot be completed. |  -  |
| **401** | Unauthorized. The request requires user authentication. |  -  |
| **403** | Forbidden. The server understood the request but denied access or access is not authorized. |  -  |
| **404** | Not found. There is no resource at the given URI. |  -  |
| **408** | Request timeout exceeded. |  -  |
| **422** | Data validation error. |  -  |
| **429** | The client has issued too many calls within a given time frame. |  -  |
| **500** | Internal Server Error. |  -  |
| **501** | Not implemented. |  -  |
| **503** | Service unavailable. |  -  |

<a id="searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPost"></a>
# **searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPost**
> AFNORRoutingCodeSearchPost200Response searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPost()

Search for a routing code

Search for routing codes that meet all the criteria passed as parameters and return the routing codes in the desired format.

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
    defaultClient.setBasePath("https://factpulse.fr");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    try {
      AFNORRoutingCodeSearchPost200Response result = apiInstance.searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPost();
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

[**AFNORRoutingCodeSearchPost200Response**](AFNORRoutingCodeSearchPost200Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Returns the routing code(s) matching the search criteria. |  -  |
| **206** | Request processed without error, but the volume of information returned has been reduced. |  -  |
| **400** | Bad request. The request is invalid or cannot be completed. |  -  |
| **401** | Unauthorized. The request requires user authentication. |  -  |
| **403** | Forbidden. The server understood the request but denied access or access is not authorized. |  -  |
| **404** | Not found. There is no resource at the given URI. |  -  |
| **408** | Request timeout exceeded. |  -  |
| **422** | Data validation error. |  -  |
| **429** | The client has issued too many calls within a given time frame. |  -  |
| **500** | Internal Server Error. |  -  |
| **501** | Not implemented. |  -  |
| **503** | Service unavailable. |  -  |

<a id="searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPost"></a>
# **searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPost**
> AFNORSirenSearchPost200Response searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPost()

SIREN search (or legal unit)

Multi-criteria company search.

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
    defaultClient.setBasePath("https://factpulse.fr");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    try {
      AFNORSirenSearchPost200Response result = apiInstance.searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPost();
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

[**AFNORSirenSearchPost200Response**](AFNORSirenSearchPost200Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Returns one or more companies. |  -  |
| **206** | Request processed without error, but the volume of information returned has been reduced. |  -  |
| **400** | Bad request. The request is invalid or cannot be completed. |  -  |
| **401** | Unauthorized. The request requires user authentication. |  -  |
| **403** | Forbidden. The server understood the request but denied access or access is not authorized. |  -  |
| **404** | Not found. There is no resource at the given URI. |  -  |
| **408** | Request timeout exceeded. |  -  |
| **422** | Data validation error. |  -  |
| **429** | The client has issued too many calls within a given time frame. |  -  |
| **500** | Internal Server Error. |  -  |
| **501** | Not implemented. |  -  |
| **503** | Service unavailable. |  -  |

<a id="searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPost"></a>
# **searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPost**
> AFNORSiretSearchPost200Response searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPost()

Search for a SIRET (facility)

Multi-criteria search for facilities.

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
    defaultClient.setBasePath("https://factpulse.fr");

    AfnorPdpPaDirectoryServiceApi apiInstance = new AfnorPdpPaDirectoryServiceApi(defaultClient);
    try {
      AFNORSiretSearchPost200Response result = apiInstance.searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPost();
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

[**AFNORSiretSearchPost200Response**](AFNORSiretSearchPost200Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Returns an establishment as defined on a given observation date or as defined on the current date if the observation date is not specified. |  -  |
| **206** | Request processed without error, but the volume of information returned has been reduced. |  -  |
| **400** | Bad request. The request is invalid or cannot be completed. |  -  |
| **401** | Unauthorized. The request requires user authentication. |  -  |
| **403** | Forbidden. The server understood the request but denied access or access is not authorized. |  -  |
| **404** | Not found. There is no resource at the given URI. |  -  |
| **408** | Request timeout exceeded. |  -  |
| **422** | Data validation error. |  -  |
| **429** | The client has issued too many calls within a given time frame. |  -  |
| **500** | Internal Server Error. |  -  |
| **501** | Not implemented. |  -  |
| **503** | Service unavailable. |  -  |

