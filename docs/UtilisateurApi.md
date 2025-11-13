# UtilisateurApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**obtenirInfosUtilisateurApiV1MoiGet**](UtilisateurApi.md#obtenirInfosUtilisateurApiV1MoiGet) | **GET** /api/v1/moi | Obtenir les informations de l&#39;utilisateur connecté |


<a id="obtenirInfosUtilisateurApiV1MoiGet"></a>
# **obtenirInfosUtilisateurApiV1MoiGet**
> Object obtenirInfosUtilisateurApiV1MoiGet()

Obtenir les informations de l&#39;utilisateur connecté

Retourne les informations de l&#39;utilisateur authentifié.  Cet endpoint permet de : - Vérifier que l&#39;authentification fonctionne - Obtenir les détails du compte connecté - Tester la validité du token JWT - Consulter son quota de consommation  **Nécessite une authentification valide.**

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UtilisateurApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    UtilisateurApi apiInstance = new UtilisateurApi(defaultClient);
    try {
      Object result = apiInstance.obtenirInfosUtilisateurApiV1MoiGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UtilisateurApi#obtenirInfosUtilisateurApiV1MoiGet");
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
| **200** | Informations de l&#39;utilisateur |  -  |

