# AfnorPdpPaDirectoryServiceApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGet**](AfnorPdpPaDirectoryServiceApi.md#directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGet) | **GET** /api/v1/afnor/directory/v1/healthcheck | Healthcheck Directory Service |
| [**getCompanyProxyApiV1AfnorDirectoryV1CompaniesSirenGet**](AfnorPdpPaDirectoryServiceApi.md#getCompanyProxyApiV1AfnorDirectoryV1CompaniesSirenGet) | **GET** /api/v1/afnor/directory/v1/companies/{siren} | Récupérer une entreprise |
| [**searchCompaniesProxyApiV1AfnorDirectoryV1CompaniesSearchPost**](AfnorPdpPaDirectoryServiceApi.md#searchCompaniesProxyApiV1AfnorDirectoryV1CompaniesSearchPost) | **POST** /api/v1/afnor/directory/v1/companies/search | Rechercher des entreprises |


<a id="directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGet"></a>
# **directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGet**
> Object directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGet()

Healthcheck Directory Service

Vérifier la disponibilité du Directory Service

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
| **200** | Service opérationnel |  -  |

<a id="getCompanyProxyApiV1AfnorDirectoryV1CompaniesSirenGet"></a>
# **getCompanyProxyApiV1AfnorDirectoryV1CompaniesSirenGet**
> Object getCompanyProxyApiV1AfnorDirectoryV1CompaniesSirenGet(siren)

Récupérer une entreprise

Récupérer les informations d&#39;une entreprise par son SIREN

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
    String siren = "siren_example"; // String | 
    try {
      Object result = apiInstance.getCompanyProxyApiV1AfnorDirectoryV1CompaniesSirenGet(siren);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaDirectoryServiceApi#getCompanyProxyApiV1AfnorDirectoryV1CompaniesSirenGet");
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

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Informations de l&#39;entreprise |  -  |
| **404** | Entreprise non trouvée |  -  |
| **401** | Non authentifié |  -  |
| **422** | Validation Error |  -  |

<a id="searchCompaniesProxyApiV1AfnorDirectoryV1CompaniesSearchPost"></a>
# **searchCompaniesProxyApiV1AfnorDirectoryV1CompaniesSearchPost**
> Object searchCompaniesProxyApiV1AfnorDirectoryV1CompaniesSearchPost()

Rechercher des entreprises

Rechercher des entreprises dans l&#39;annuaire AFNOR

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
      Object result = apiInstance.searchCompaniesProxyApiV1AfnorDirectoryV1CompaniesSearchPost();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaDirectoryServiceApi#searchCompaniesProxyApiV1AfnorDirectoryV1CompaniesSearchPost");
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
| **200** | Résultats de recherche |  -  |
| **401** | Non authentifié |  -  |

