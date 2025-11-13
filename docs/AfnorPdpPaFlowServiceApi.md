# AfnorPdpPaFlowServiceApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**downloadFlowProxyApiV1AfnorFlowV1FlowsFlowIdGet**](AfnorPdpPaFlowServiceApi.md#downloadFlowProxyApiV1AfnorFlowV1FlowsFlowIdGet) | **GET** /api/v1/afnor/flow/v1/flows/{flowId} | Télécharger un flux |
| [**flowHealthcheckProxyApiV1AfnorFlowV1HealthcheckGet**](AfnorPdpPaFlowServiceApi.md#flowHealthcheckProxyApiV1AfnorFlowV1HealthcheckGet) | **GET** /api/v1/afnor/flow/v1/healthcheck | Healthcheck Flow Service |
| [**searchFlowsProxyApiV1AfnorFlowV1FlowsSearchPost**](AfnorPdpPaFlowServiceApi.md#searchFlowsProxyApiV1AfnorFlowV1FlowsSearchPost) | **POST** /api/v1/afnor/flow/v1/flows/search | Rechercher des flux |
| [**submitFlowProxyApiV1AfnorFlowV1FlowsPost**](AfnorPdpPaFlowServiceApi.md#submitFlowProxyApiV1AfnorFlowV1FlowsPost) | **POST** /api/v1/afnor/flow/v1/flows | Soumettre un flux de facturation |


<a id="downloadFlowProxyApiV1AfnorFlowV1FlowsFlowIdGet"></a>
# **downloadFlowProxyApiV1AfnorFlowV1FlowsFlowIdGet**
> Object downloadFlowProxyApiV1AfnorFlowV1FlowsFlowIdGet(flowId)

Télécharger un flux

Télécharger le fichier PDF/A-3 d&#39;un flux de facturation (utilise le client_uid du JWT)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaFlowServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    AfnorPdpPaFlowServiceApi apiInstance = new AfnorPdpPaFlowServiceApi(defaultClient);
    String flowId = "flowId_example"; // String | 
    try {
      Object result = apiInstance.downloadFlowProxyApiV1AfnorFlowV1FlowsFlowIdGet(flowId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaFlowServiceApi#downloadFlowProxyApiV1AfnorFlowV1FlowsFlowIdGet");
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
| **flowId** | **String**|  | |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, application/pdf

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Fichier PDF/A-3 |  -  |
| **400** | Configuration PDP manquante |  -  |
| **401** | Non authentifié - Token JWT manquant ou invalide |  -  |
| **404** | Flux non trouvé ou client_uid invalide |  -  |
| **422** | Validation Error |  -  |

<a id="flowHealthcheckProxyApiV1AfnorFlowV1HealthcheckGet"></a>
# **flowHealthcheckProxyApiV1AfnorFlowV1HealthcheckGet**
> Object flowHealthcheckProxyApiV1AfnorFlowV1HealthcheckGet()

Healthcheck Flow Service

Vérifier la disponibilité du Flow Service

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaFlowServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    AfnorPdpPaFlowServiceApi apiInstance = new AfnorPdpPaFlowServiceApi(defaultClient);
    try {
      Object result = apiInstance.flowHealthcheckProxyApiV1AfnorFlowV1HealthcheckGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaFlowServiceApi#flowHealthcheckProxyApiV1AfnorFlowV1HealthcheckGet");
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

<a id="searchFlowsProxyApiV1AfnorFlowV1FlowsSearchPost"></a>
# **searchFlowsProxyApiV1AfnorFlowV1FlowsSearchPost**
> Object searchFlowsProxyApiV1AfnorFlowV1FlowsSearchPost()

Rechercher des flux

Rechercher des flux de facturation selon des critères (utilise le client_uid du JWT)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaFlowServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    AfnorPdpPaFlowServiceApi apiInstance = new AfnorPdpPaFlowServiceApi(defaultClient);
    try {
      Object result = apiInstance.searchFlowsProxyApiV1AfnorFlowV1FlowsSearchPost();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaFlowServiceApi#searchFlowsProxyApiV1AfnorFlowV1FlowsSearchPost");
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
| **400** | Configuration PDP manquante |  -  |
| **401** | Non authentifié - Token JWT manquant ou invalide |  -  |
| **404** | Client PDP non trouvé (client_uid invalide) |  -  |
| **429** | Trop de requêtes - Rate limit atteint (60 recherches/minute) |  -  |

<a id="submitFlowProxyApiV1AfnorFlowV1FlowsPost"></a>
# **submitFlowProxyApiV1AfnorFlowV1FlowsPost**
> Object submitFlowProxyApiV1AfnorFlowV1FlowsPost()

Soumettre un flux de facturation

Soumet une facture électronique à une Plateforme de Dématérialisation Partenaire (PDP) conformément à la norme AFNOR XP Z12-013

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaFlowServiceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    AfnorPdpPaFlowServiceApi apiInstance = new AfnorPdpPaFlowServiceApi(defaultClient);
    try {
      Object result = apiInstance.submitFlowProxyApiV1AfnorFlowV1FlowsPost();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaFlowServiceApi#submitFlowProxyApiV1AfnorFlowV1FlowsPost");
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
| **201** | Flux soumis avec succès |  -  |
| **400** | Requête invalide ou configuration PDP manquante |  -  |
| **401** | Non authentifié - Token JWT manquant ou invalide |  -  |
| **403** | Non autorisé - Quota dépassé ou permissions insuffisantes |  -  |
| **404** | Client PDP non trouvé (client_uid invalide) |  -  |
| **429** | Trop de requêtes - Rate limit atteint (30 soumissions/minute) |  -  |

