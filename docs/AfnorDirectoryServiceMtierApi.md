# AfnorDirectoryServiceMtierApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getSirenMetierApiV1AfnorDirectorySirenSirenGet**](AfnorDirectoryServiceMtierApi.md#getSirenMetierApiV1AfnorDirectorySirenSirenGet) | **GET** /api/v1/afnor/directory/siren/{siren} | Récupérer une entreprise par SIREN (multi-tenant) |
| [**getSiretMetierApiV1AfnorDirectorySiretSiretGet**](AfnorDirectoryServiceMtierApi.md#getSiretMetierApiV1AfnorDirectorySiretSiretGet) | **GET** /api/v1/afnor/directory/siret/{siret} | Récupérer un établissement par SIRET (multi-tenant) |
| [**searchSirenMetierApiV1AfnorDirectorySirenSearchPost**](AfnorDirectoryServiceMtierApi.md#searchSirenMetierApiV1AfnorDirectorySirenSearchPost) | **POST** /api/v1/afnor/directory/siren/search | Rechercher des entreprises (multi-tenant) |
| [**searchSiretMetierApiV1AfnorDirectorySiretSearchPost**](AfnorDirectoryServiceMtierApi.md#searchSiretMetierApiV1AfnorDirectorySiretSearchPost) | **POST** /api/v1/afnor/directory/siret/search | Rechercher des établissements (multi-tenant) |


<a id="getSirenMetierApiV1AfnorDirectorySirenSirenGet"></a>
# **getSirenMetierApiV1AfnorDirectorySirenSirenGet**
> Object getSirenMetierApiV1AfnorDirectorySirenSirenGet(siren, pdPCredentials)

Récupérer une entreprise par SIREN (multi-tenant)

Récupère les informations d&#39;une entreprise dans le Directory Service AFNOR. Les credentials PDP sont récupérés automatiquement via le client_uid du JWT, ou peuvent être fournis directement dans le body (zero-storage).

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorDirectoryServiceMtierApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorDirectoryServiceMtierApi apiInstance = new AfnorDirectoryServiceMtierApi(defaultClient);
    String siren = "siren_example"; // String | 
    PDPCredentials pdPCredentials = new PDPCredentials(); // PDPCredentials | 
    try {
      Object result = apiInstance.getSirenMetierApiV1AfnorDirectorySirenSirenGet(siren, pdPCredentials);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorDirectoryServiceMtierApi#getSirenMetierApiV1AfnorDirectorySirenSirenGet");
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
| **pdPCredentials** | [**PDPCredentials**](PDPCredentials.md)|  | [optional] |

### Return type

**Object**

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Entreprise trouvée |  -  |
| **404** | Entreprise non trouvée |  -  |
| **400** | Aucune config PDP fournie |  -  |
| **422** | Validation Error |  -  |

<a id="getSiretMetierApiV1AfnorDirectorySiretSiretGet"></a>
# **getSiretMetierApiV1AfnorDirectorySiretSiretGet**
> Object getSiretMetierApiV1AfnorDirectorySiretSiretGet(siret, pdPCredentials)

Récupérer un établissement par SIRET (multi-tenant)

Récupère les informations d&#39;un établissement dans le Directory Service AFNOR. Les credentials PDP sont récupérés automatiquement via le client_uid du JWT, ou peuvent être fournis directement dans le body (zero-storage).

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorDirectoryServiceMtierApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorDirectoryServiceMtierApi apiInstance = new AfnorDirectoryServiceMtierApi(defaultClient);
    String siret = "siret_example"; // String | 
    PDPCredentials pdPCredentials = new PDPCredentials(); // PDPCredentials | 
    try {
      Object result = apiInstance.getSiretMetierApiV1AfnorDirectorySiretSiretGet(siret, pdPCredentials);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorDirectoryServiceMtierApi#getSiretMetierApiV1AfnorDirectorySiretSiretGet");
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
| **pdPCredentials** | [**PDPCredentials**](PDPCredentials.md)|  | [optional] |

### Return type

**Object**

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Établissement trouvé |  -  |
| **404** | Établissement non trouvé |  -  |
| **422** | Validation Error |  -  |

<a id="searchSirenMetierApiV1AfnorDirectorySirenSearchPost"></a>
# **searchSirenMetierApiV1AfnorDirectorySirenSearchPost**
> Object searchSirenMetierApiV1AfnorDirectorySirenSearchPost(bodySearchSirenMetierApiV1AfnorDirectorySirenSearchPost)

Rechercher des entreprises (multi-tenant)

Recherche multi-critères d&#39;entreprises dans le Directory Service AFNOR. Les credentials PDP sont récupérés automatiquement via le client_uid du JWT, ou peuvent être fournis directement dans le body (zero-storage).

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorDirectoryServiceMtierApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorDirectoryServiceMtierApi apiInstance = new AfnorDirectoryServiceMtierApi(defaultClient);
    BodySearchSirenMetierApiV1AfnorDirectorySirenSearchPost bodySearchSirenMetierApiV1AfnorDirectorySirenSearchPost = new BodySearchSirenMetierApiV1AfnorDirectorySirenSearchPost(); // BodySearchSirenMetierApiV1AfnorDirectorySirenSearchPost | 
    try {
      Object result = apiInstance.searchSirenMetierApiV1AfnorDirectorySirenSearchPost(bodySearchSirenMetierApiV1AfnorDirectorySirenSearchPost);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorDirectoryServiceMtierApi#searchSirenMetierApiV1AfnorDirectorySirenSearchPost");
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
| **bodySearchSirenMetierApiV1AfnorDirectorySirenSearchPost** | [**BodySearchSirenMetierApiV1AfnorDirectorySirenSearchPost**](BodySearchSirenMetierApiV1AfnorDirectorySirenSearchPost.md)|  | |

### Return type

**Object**

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Résultats de recherche |  -  |
| **422** | Validation Error |  -  |

<a id="searchSiretMetierApiV1AfnorDirectorySiretSearchPost"></a>
# **searchSiretMetierApiV1AfnorDirectorySiretSearchPost**
> Object searchSiretMetierApiV1AfnorDirectorySiretSearchPost(bodySearchSiretMetierApiV1AfnorDirectorySiretSearchPost)

Rechercher des établissements (multi-tenant)

Recherche multi-critères d&#39;établissements dans le Directory Service AFNOR. Les credentials PDP sont récupérés automatiquement via le client_uid du JWT, ou peuvent être fournis directement dans le body (zero-storage).

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorDirectoryServiceMtierApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorDirectoryServiceMtierApi apiInstance = new AfnorDirectoryServiceMtierApi(defaultClient);
    BodySearchSiretMetierApiV1AfnorDirectorySiretSearchPost bodySearchSiretMetierApiV1AfnorDirectorySiretSearchPost = new BodySearchSiretMetierApiV1AfnorDirectorySiretSearchPost(); // BodySearchSiretMetierApiV1AfnorDirectorySiretSearchPost | 
    try {
      Object result = apiInstance.searchSiretMetierApiV1AfnorDirectorySiretSearchPost(bodySearchSiretMetierApiV1AfnorDirectorySiretSearchPost);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorDirectoryServiceMtierApi#searchSiretMetierApiV1AfnorDirectorySiretSearchPost");
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
| **bodySearchSiretMetierApiV1AfnorDirectorySiretSearchPost** | [**BodySearchSiretMetierApiV1AfnorDirectorySiretSearchPost**](BodySearchSiretMetierApiV1AfnorDirectorySiretSearchPost.md)|  | |

### Return type

**Object**

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Résultats de recherche |  -  |
| **422** | Validation Error |  -  |

