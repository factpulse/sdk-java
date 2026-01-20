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
| [**submitEncaisseeApiV1CdarEncaisseePost**](CdarCycleDeVieApi.md#submitEncaisseeApiV1CdarEncaisseePost) | **POST** /api/v1/cdar/encaissee | [Simplifié] Soumettre un statut ENCAISSÉE (212) |
| [**submitRefuseeApiV1CdarRefuseePost**](CdarCycleDeVieApi.md#submitRefuseeApiV1CdarRefuseePost) | **POST** /api/v1/cdar/refusee | [Simplifié] Soumettre un statut REFUSÉE (210) |
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
> SubmitCDARResponse submitCdarApiV1CdarSubmitPost(submitCDARRequest)

Générer et soumettre un message CDAR

Génère un message CDAR et le soumet à la plateforme PA/PDP.  **Stratégies d&#39;authentification:** 1. **JWT avec client_uid** (recommandé): credentials PDP récupérés du backend 2. **Zero-storage**: Fournir pdpFlowServiceUrl, pdpClientId, pdpClientSecret dans la requête  **Types de flux (flowType):** - &#x60;CustomerInvoiceLC&#x60;: Cycle de vie côté client (acheteur) - &#x60;SupplierInvoiceLC&#x60;: Cycle de vie côté fournisseur (vendeur)

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
    SubmitCDARRequest submitCDARRequest = new SubmitCDARRequest(); // SubmitCDARRequest | 
    try {
      SubmitCDARResponse result = apiInstance.submitCdarApiV1CdarSubmitPost(submitCDARRequest);
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
| **submitCDARRequest** | [**SubmitCDARRequest**](SubmitCDARRequest.md)|  | |

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
> SubmitCDARResponse submitCdarXmlApiV1CdarSubmitXmlPost(submitCDARXMLRequest)

Soumettre un XML CDAR pré-généré

Soumet un message XML CDAR pré-généré à la plateforme PA/PDP.  Utile pour soumettre des XML générés par d&#39;autres systèmes.  **Stratégies d&#39;authentification:** 1. **JWT avec client_uid** (recommandé): credentials PDP récupérés du backend 2. **Zero-storage**: Fournir pdpFlowServiceUrl, pdpClientId, pdpClientSecret dans la requête

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
    SubmitCDARXMLRequest submitCDARXMLRequest = new SubmitCDARXMLRequest(); // SubmitCDARXMLRequest | 
    try {
      SubmitCDARResponse result = apiInstance.submitCdarXmlApiV1CdarSubmitXmlPost(submitCDARXMLRequest);
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
| **submitCDARXMLRequest** | [**SubmitCDARXMLRequest**](SubmitCDARXMLRequest.md)|  | |

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

<a id="submitEncaisseeApiV1CdarEncaisseePost"></a>
# **submitEncaisseeApiV1CdarEncaisseePost**
> SimplifiedCDARResponse submitEncaisseeApiV1CdarEncaisseePost(encaisseeRequest)

[Simplifié] Soumettre un statut ENCAISSÉE (212)

**Endpoint simplifié pour OD** - Soumet un statut ENCAISSÉE (212) pour une facture.  Ce statut est **obligatoire pour le PPF** (BR-FR-CDV-14 requiert le montant encaissé).  **Cas d&#39;usage:** L&#39;acheteur confirme le paiement d&#39;une facture.  **Authentification:** JWT Bearer (recommandé) ou credentials PDP dans la requête.

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
    EncaisseeRequest encaisseeRequest = new EncaisseeRequest(); // EncaisseeRequest | 
    try {
      SimplifiedCDARResponse result = apiInstance.submitEncaisseeApiV1CdarEncaisseePost(encaisseeRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CdarCycleDeVieApi#submitEncaisseeApiV1CdarEncaisseePost");
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
| **encaisseeRequest** | [**EncaisseeRequest**](EncaisseeRequest.md)|  | |

### Return type

[**SimplifiedCDARResponse**](SimplifiedCDARResponse.md)

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

<a id="submitRefuseeApiV1CdarRefuseePost"></a>
# **submitRefuseeApiV1CdarRefuseePost**
> SimplifiedCDARResponse submitRefuseeApiV1CdarRefuseePost(refuseeRequest)

[Simplifié] Soumettre un statut REFUSÉE (210)

**Endpoint simplifié pour OD** - Soumet un statut REFUSÉE (210) pour une facture.  Ce statut est **obligatoire pour le PPF** (BR-FR-CDV-15 requiert un code motif).  **Cas d&#39;usage:** L&#39;acheteur refuse une facture reçue.  **Codes motif autorisés (BR-FR-CDV-CL-09):** - &#x60;TX_TVA_ERR&#x60;: Taux de TVA erroné - &#x60;MONTANTTOTAL_ERR&#x60;: Montant total erroné - &#x60;CALCUL_ERR&#x60;: Erreur de calcul - &#x60;NON_CONFORME&#x60;: Non conforme - &#x60;DOUBLON&#x60;: Doublon - &#x60;DEST_ERR&#x60;: Destinataire erroné - &#x60;TRANSAC_INC&#x60;: Transaction incomplète - &#x60;EMMET_INC&#x60;: Émetteur inconnu - &#x60;CONTRAT_TERM&#x60;: Contrat terminé - &#x60;DOUBLE_FACT&#x60;: Double facturation - &#x60;CMD_ERR&#x60;: Commande erronée - &#x60;ADR_ERR&#x60;: Adresse erronée - &#x60;REF_CT_ABSENT&#x60;: Référence contrat absente  **Authentification:** JWT Bearer (recommandé) ou credentials PDP dans la requête.

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
    RefuseeRequest refuseeRequest = new RefuseeRequest(); // RefuseeRequest | 
    try {
      SimplifiedCDARResponse result = apiInstance.submitRefuseeApiV1CdarRefuseePost(refuseeRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CdarCycleDeVieApi#submitRefuseeApiV1CdarRefuseePost");
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
| **refuseeRequest** | [**RefuseeRequest**](RefuseeRequest.md)|  | |

### Return type

[**SimplifiedCDARResponse**](SimplifiedCDARResponse.md)

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

