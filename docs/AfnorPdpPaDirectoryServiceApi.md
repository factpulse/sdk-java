# AfnorPdpPaDirectoryServiceApi

All URIs are relative to *https://factpulse.fr*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGet**](AfnorPdpPaDirectoryServiceApi.md#directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGet) | **GET** /api/v1/afnor/directory/v1/healthcheck | Healthcheck Directory Service |
| [**getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGet**](AfnorPdpPaDirectoryServiceApi.md#getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGet) | **GET** /api/v1/afnor/directory/v1/directory-line/code:{addressing_identifier} | Get a directory line. |
| [**getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGet**](AfnorPdpPaDirectoryServiceApi.md#getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGet) | **GET** /api/v1/afnor/directory/v1/routing-code/siret:{siret}/code:{routing_identifier} | Get a routing code by SIRET and routing identifier |
| [**getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGet**](AfnorPdpPaDirectoryServiceApi.md#getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGet) | **GET** /api/v1/afnor/directory/v1/siren/code-insee:{siren} | Consult a siren (legal unit) by SIREN number |
| [**getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGet**](AfnorPdpPaDirectoryServiceApi.md#getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGet) | **GET** /api/v1/afnor/directory/v1/siret/code-insee:{siret} | Gets a siret (facility) by SIRET number |
| [**searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPost**](AfnorPdpPaDirectoryServiceApi.md#searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPost) | **POST** /api/v1/afnor/directory/v1/directory-line/search | Search for a directory line |
| [**searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPost**](AfnorPdpPaDirectoryServiceApi.md#searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPost) | **POST** /api/v1/afnor/directory/v1/routing-code/search | Search for a routing code |
| [**searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPost**](AfnorPdpPaDirectoryServiceApi.md#searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPost) | **POST** /api/v1/afnor/directory/v1/siren/search | SIREN search (or legal unit) |
| [**searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPost**](AfnorPdpPaDirectoryServiceApi.md#searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPost) | **POST** /api/v1/afnor/directory/v1/siret/search | Search for a SIRET (facility) |


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
> AFNORDirectoryLinePayloadLegalUnitFacilityRoutingCode getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGet(addressingIdentifier)

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
      AFNORDirectoryLinePayloadLegalUnitFacilityRoutingCode result = apiInstance.getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGet(addressingIdentifier);
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

[**AFNORDirectoryLinePayloadLegalUnitFacilityRoutingCode**](AFNORDirectoryLinePayloadLegalUnitFacilityRoutingCode.md)

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
| **204** | No content |  -  |

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
| **204** | No content |  -  |

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
| **204** | No content |  -  |

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
| **204** | No content |  -  |

