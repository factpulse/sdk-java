# CdarCycleDeVieApi

All URIs are relative to *https://factpulse.fr*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**generateCdarApiV1CdarGeneratePost**](CdarCycleDeVieApi.md#generateCdarApiV1CdarGeneratePost) | **POST** /api/v1/cdar/generate | Générer un message CDAR |
| [**getActionCodesApiV1CdarActionCodesGet**](CdarCycleDeVieApi.md#getActionCodesApiV1CdarActionCodesGet) | **GET** /api/v1/cdar/action-codes | Liste des codes action CDAR |
| [**getReasonCodesApiV1CdarReasonCodesGet**](CdarCycleDeVieApi.md#getReasonCodesApiV1CdarReasonCodesGet) | **GET** /api/v1/cdar/reason-codes | Liste des codes motif CDAR |
| [**getStatusCodesApiV1CdarStatusCodesGet**](CdarCycleDeVieApi.md#getStatusCodesApiV1CdarStatusCodesGet) | **GET** /api/v1/cdar/status-codes | Liste des codes statut CDAR |
| [**submitCdarApiV1CdarSubmitPost**](CdarCycleDeVieApi.md#submitCdarApiV1CdarSubmitPost) | **POST** /api/v1/cdar/submit | Générer et soumettre un message CDAR |
| [**submitCdarXmlApiV1CdarSubmitXmlPost**](CdarCycleDeVieApi.md#submitCdarXmlApiV1CdarSubmitXmlPost) | **POST** /api/v1/cdar/submit-xml | Soumettre un XML CDAR pré-généré |
| [**validateCdarApiV1CdarValidatePost**](CdarCycleDeVieApi.md#validateCdarApiV1CdarValidatePost) | **POST** /api/v1/cdar/validate | Valider des données CDAR |


<a id="generateCdarApiV1CdarGeneratePost"></a>
# **generateCdarApiV1CdarGeneratePost**
> GenerateCDARResponse generateCdarApiV1CdarGeneratePost(createCDARRequest)

Générer un message CDAR

Génère un message XML CDAR (Cross Domain Acknowledgement and Response) pour communiquer le statut d&#39;une facture.  **Types de messages:** - **23** (Traitement): Message de cycle de vie standard - **305** (Transmission): Message de transmission entre plateformes  **Règles métier:** - BR-FR-CDV-14: Le statut 212 (ENCAISSEE) requiert un montant encaissé - BR-FR-CDV-15: Les statuts 206/207/208/210/213/501 requièrent un code motif

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CdarCycleDeVieApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    CdarCycleDeVieApi apiInstance = new CdarCycleDeVieApi(defaultClient);
    CreateCDARRequest createCDARRequest = new CreateCDARRequest(); // CreateCDARRequest | 
    try {
      GenerateCDARResponse result = apiInstance.generateCdarApiV1CdarGeneratePost(createCDARRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CdarCycleDeVieApi#generateCdarApiV1CdarGeneratePost");
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
| **createCDARRequest** | [**CreateCDARRequest**](CreateCDARRequest.md)|  | |

### Return type

[**GenerateCDARResponse**](GenerateCDARResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Requête invalide |  -  |
| **422** | Erreur de validation |  -  |
| **500** | Erreur serveur |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="getActionCodesApiV1CdarActionCodesGet"></a>
# **getActionCodesApiV1CdarActionCodesGet**
> ActionCodesResponse getActionCodesApiV1CdarActionCodesGet()

Liste des codes action CDAR

Retourne la liste complète des codes action (BR-FR-CDV-CL-10).  Ces codes indiquent l&#39;action demandée sur la facture.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CdarCycleDeVieApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");

    CdarCycleDeVieApi apiInstance = new CdarCycleDeVieApi(defaultClient);
    try {
      ActionCodesResponse result = apiInstance.getActionCodesApiV1CdarActionCodesGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CdarCycleDeVieApi#getActionCodesApiV1CdarActionCodesGet");
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

[**ActionCodesResponse**](ActionCodesResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Requête invalide |  -  |
| **422** | Erreur de validation |  -  |
| **500** | Erreur serveur |  -  |

<a id="getReasonCodesApiV1CdarReasonCodesGet"></a>
# **getReasonCodesApiV1CdarReasonCodesGet**
> ReasonCodesResponse getReasonCodesApiV1CdarReasonCodesGet()

Liste des codes motif CDAR

Retourne la liste complète des codes motif de statut (BR-FR-CDV-CL-09).  Ces codes expliquent la raison d&#39;un statut particulier.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CdarCycleDeVieApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");

    CdarCycleDeVieApi apiInstance = new CdarCycleDeVieApi(defaultClient);
    try {
      ReasonCodesResponse result = apiInstance.getReasonCodesApiV1CdarReasonCodesGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CdarCycleDeVieApi#getReasonCodesApiV1CdarReasonCodesGet");
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

[**ReasonCodesResponse**](ReasonCodesResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Requête invalide |  -  |
| **422** | Erreur de validation |  -  |
| **500** | Erreur serveur |  -  |

<a id="getStatusCodesApiV1CdarStatusCodesGet"></a>
# **getStatusCodesApiV1CdarStatusCodesGet**
> StatusCodesResponse getStatusCodesApiV1CdarStatusCodesGet()

Liste des codes statut CDAR

Retourne la liste complète des codes statut de facture (BR-FR-CDV-CL-06).  Ces codes indiquent l&#39;état du cycle de vie d&#39;une facture.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CdarCycleDeVieApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");

    CdarCycleDeVieApi apiInstance = new CdarCycleDeVieApi(defaultClient);
    try {
      StatusCodesResponse result = apiInstance.getStatusCodesApiV1CdarStatusCodesGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CdarCycleDeVieApi#getStatusCodesApiV1CdarStatusCodesGet");
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

[**StatusCodesResponse**](StatusCodesResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Requête invalide |  -  |
| **422** | Erreur de validation |  -  |
| **500** | Erreur serveur |  -  |

<a id="submitCdarApiV1CdarSubmitPost"></a>
# **submitCdarApiV1CdarSubmitPost**
> SubmitCDARResponse submitCdarApiV1CdarSubmitPost(userId, bodySubmitCdarApiV1CdarSubmitPost, jwtToken, clientUid)

Générer et soumettre un message CDAR

Génère un message CDAR et le soumet à la plateforme PA/PDP.  Nécessite une authentification AFNOR valide.  **Types de flux (flowType):** - &#x60;CustomerInvoiceLC&#x60;: Cycle de vie côté client (acheteur) - &#x60;SupplierInvoiceLC&#x60;: Cycle de vie côté fournisseur (vendeur)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CdarCycleDeVieApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    CdarCycleDeVieApi apiInstance = new CdarCycleDeVieApi(defaultClient);
    Integer userId = 56; // Integer | 
    BodySubmitCdarApiV1CdarSubmitPost bodySubmitCdarApiV1CdarSubmitPost = new BodySubmitCdarApiV1CdarSubmitPost(); // BodySubmitCdarApiV1CdarSubmitPost | 
    String jwtToken = "jwtToken_example"; // String | 
    String clientUid = "clientUid_example"; // String | 
    try {
      SubmitCDARResponse result = apiInstance.submitCdarApiV1CdarSubmitPost(userId, bodySubmitCdarApiV1CdarSubmitPost, jwtToken, clientUid);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CdarCycleDeVieApi#submitCdarApiV1CdarSubmitPost");
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
| **userId** | **Integer**|  | |
| **bodySubmitCdarApiV1CdarSubmitPost** | [**BodySubmitCdarApiV1CdarSubmitPost**](BodySubmitCdarApiV1CdarSubmitPost.md)|  | |
| **jwtToken** | **String**|  | [optional] |
| **clientUid** | **String**|  | [optional] |

### Return type

[**SubmitCDARResponse**](SubmitCDARResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Requête invalide |  -  |
| **422** | Erreur de validation |  -  |
| **500** | Erreur serveur |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="submitCdarXmlApiV1CdarSubmitXmlPost"></a>
# **submitCdarXmlApiV1CdarSubmitXmlPost**
> SubmitCDARResponse submitCdarXmlApiV1CdarSubmitXmlPost(userId, bodySubmitCdarXmlApiV1CdarSubmitXmlPost, jwtToken, clientUid)

Soumettre un XML CDAR pré-généré

Soumet un message XML CDAR pré-généré à la plateforme PA/PDP.  Utile pour soumettre des XML générés par d&#39;autres systèmes.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CdarCycleDeVieApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    CdarCycleDeVieApi apiInstance = new CdarCycleDeVieApi(defaultClient);
    Integer userId = 56; // Integer | 
    BodySubmitCdarXmlApiV1CdarSubmitXmlPost bodySubmitCdarXmlApiV1CdarSubmitXmlPost = new BodySubmitCdarXmlApiV1CdarSubmitXmlPost(); // BodySubmitCdarXmlApiV1CdarSubmitXmlPost | 
    String jwtToken = "jwtToken_example"; // String | 
    String clientUid = "clientUid_example"; // String | 
    try {
      SubmitCDARResponse result = apiInstance.submitCdarXmlApiV1CdarSubmitXmlPost(userId, bodySubmitCdarXmlApiV1CdarSubmitXmlPost, jwtToken, clientUid);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CdarCycleDeVieApi#submitCdarXmlApiV1CdarSubmitXmlPost");
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
| **userId** | **Integer**|  | |
| **bodySubmitCdarXmlApiV1CdarSubmitXmlPost** | [**BodySubmitCdarXmlApiV1CdarSubmitXmlPost**](BodySubmitCdarXmlApiV1CdarSubmitXmlPost.md)|  | |
| **jwtToken** | **String**|  | [optional] |
| **clientUid** | **String**|  | [optional] |

### Return type

[**SubmitCDARResponse**](SubmitCDARResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Requête invalide |  -  |
| **422** | Erreur de validation |  -  |
| **500** | Erreur serveur |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="validateCdarApiV1CdarValidatePost"></a>
# **validateCdarApiV1CdarValidatePost**
> ValidateCDARResponse validateCdarApiV1CdarValidatePost(validateCDARRequest)

Valider des données CDAR

Valide les données CDAR sans générer le XML.  Vérifie: - Les formats des champs (SIREN, dates, etc.) - Les codes enums (statut, motif, action) - Les règles métier BR-FR-CDV-*

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CdarCycleDeVieApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    CdarCycleDeVieApi apiInstance = new CdarCycleDeVieApi(defaultClient);
    ValidateCDARRequest validateCDARRequest = new ValidateCDARRequest(); // ValidateCDARRequest | 
    try {
      ValidateCDARResponse result = apiInstance.validateCdarApiV1CdarValidatePost(validateCDARRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CdarCycleDeVieApi#validateCdarApiV1CdarValidatePost");
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
| **validateCDARRequest** | [**ValidateCDARRequest**](ValidateCDARRequest.md)|  | |

### Return type

[**ValidateCDARResponse**](ValidateCDARResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Requête invalide |  -  |
| **422** | Erreur de validation |  -  |
| **500** | Erreur serveur |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

