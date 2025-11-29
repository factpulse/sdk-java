# AfnorPdpPaApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getAfnorCredentialsApiV1AfnorCredentialsGet**](AfnorPdpPaApi.md#getAfnorCredentialsApiV1AfnorCredentialsGet) | **GET** /api/v1/afnor/credentials | Récupérer les credentials AFNOR stockés |
| [**getFluxEntrantApiV1AfnorFluxEntrantsFlowIdGet**](AfnorPdpPaApi.md#getFluxEntrantApiV1AfnorFluxEntrantsFlowIdGet) | **GET** /api/v1/afnor/flux-entrants/{flow_id} | Récupérer et extraire une facture entrante |
| [**oauthTokenProxyApiV1AfnorOauthTokenPost**](AfnorPdpPaApi.md#oauthTokenProxyApiV1AfnorOauthTokenPost) | **POST** /api/v1/afnor/oauth/token | Endpoint OAuth2 pour authentification AFNOR |


<a id="getAfnorCredentialsApiV1AfnorCredentialsGet"></a>
# **getAfnorCredentialsApiV1AfnorCredentialsGet**
> Object getAfnorCredentialsApiV1AfnorCredentialsGet()

Récupérer les credentials AFNOR stockés

Récupère les credentials AFNOR/PDP stockés pour le client_uid du JWT. Cet endpoint est utilisé par le SDK en mode &#39;stored&#39; pour récupérer les credentials avant de faire l&#39;OAuth AFNOR lui-même.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorPdpPaApi apiInstance = new AfnorPdpPaApi(defaultClient);
    try {
      Object result = apiInstance.getAfnorCredentialsApiV1AfnorCredentialsGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaApi#getAfnorCredentialsApiV1AfnorCredentialsGet");
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
| **200** | Credentials AFNOR récupérés avec succès |  -  |
| **400** | Aucun client_uid dans le JWT |  -  |
| **401** | Non authentifié - Token JWT manquant ou invalide |  -  |
| **404** | Client non trouvé ou pas de credentials AFNOR configurés |  -  |

<a id="getFluxEntrantApiV1AfnorFluxEntrantsFlowIdGet"></a>
# **getFluxEntrantApiV1AfnorFluxEntrantsFlowIdGet**
> FactureEntrante getFluxEntrantApiV1AfnorFluxEntrantsFlowIdGet(flowId)

Récupérer et extraire une facture entrante

Télécharge un flux entrant depuis la PDP AFNOR et extrait les métadonnées de la facture vers un format JSON unifié. Supporte les formats Factur-X, CII et UBL.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    AfnorPdpPaApi apiInstance = new AfnorPdpPaApi(defaultClient);
    String flowId = "flowId_example"; // String | 
    try {
      FactureEntrante result = apiInstance.getFluxEntrantApiV1AfnorFluxEntrantsFlowIdGet(flowId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaApi#getFluxEntrantApiV1AfnorFluxEntrantsFlowIdGet");
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

[**FactureEntrante**](FactureEntrante.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Facture extraite avec succès |  -  |
| **400** | Format de facture non supporté ou invalide |  -  |
| **401** | Non authentifié |  -  |
| **404** | Flux non trouvé |  -  |
| **503** | Service PDP indisponible |  -  |
| **422** | Validation Error |  -  |

<a id="oauthTokenProxyApiV1AfnorOauthTokenPost"></a>
# **oauthTokenProxyApiV1AfnorOauthTokenPost**
> Object oauthTokenProxyApiV1AfnorOauthTokenPost()

Endpoint OAuth2 pour authentification AFNOR

Endpoint proxy OAuth2 pour obtenir un token d&#39;accès AFNOR. Fait proxy vers le mock AFNOR (sandbox) ou la vraie PDP selon MOCK_AFNOR_BASE_URL. Cet endpoint est public (pas d&#39;auth Django requise) car il est appelé par le SDK AFNOR.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AfnorPdpPaApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    AfnorPdpPaApi apiInstance = new AfnorPdpPaApi(defaultClient);
    try {
      Object result = apiInstance.oauthTokenProxyApiV1AfnorOauthTokenPost();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AfnorPdpPaApi#oauthTokenProxyApiV1AfnorOauthTokenPost");
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
| **200** | Token OAuth2 acquis avec succès |  -  |
| **401** | Credentials invalides |  -  |

