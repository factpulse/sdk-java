# ChorusProApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**ajouterFichierApiV1ChorusProTransversesAjouterFichierPost**](ChorusProApi.md#ajouterFichierApiV1ChorusProTransversesAjouterFichierPost) | **POST** /api/v1/chorus-pro/transverses/ajouter-fichier | Add an attachment |
| [**completerFactureApiV1ChorusProFacturesCompleterPost**](ChorusProApi.md#completerFactureApiV1ChorusProFacturesCompleterPost) | **POST** /api/v1/chorus-pro/factures/completer | Complete a suspended invoice (Supplier) |
| [**consulterFactureApiV1ChorusProFacturesConsulterPost**](ChorusProApi.md#consulterFactureApiV1ChorusProFacturesConsulterPost) | **POST** /api/v1/chorus-pro/factures/consulter | Consult invoice status |
| [**consulterStructureApiV1ChorusProStructuresConsulterPost**](ChorusProApi.md#consulterStructureApiV1ChorusProStructuresConsulterPost) | **POST** /api/v1/chorus-pro/structures/consulter | Consult structure details |
| [**listerServicesStructureApiV1ChorusProStructuresIdStructureCppServicesGet**](ChorusProApi.md#listerServicesStructureApiV1ChorusProStructuresIdStructureCppServicesGet) | **GET** /api/v1/chorus-pro/structures/{id_structure_cpp}/services | List structure services |
| [**obtenirIdChorusProDepuisSiretApiV1ChorusProStructuresObtenirIdDepuisSiretPost**](ChorusProApi.md#obtenirIdChorusProDepuisSiretApiV1ChorusProStructuresObtenirIdDepuisSiretPost) | **POST** /api/v1/chorus-pro/structures/obtenir-id-depuis-siret | Utility: Get Chorus Pro ID from SIRET |
| [**rechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost**](ChorusProApi.md#rechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost) | **POST** /api/v1/chorus-pro/factures/rechercher-destinataire | Search received invoices (Recipient) |
| [**rechercherFacturesFournisseurApiV1ChorusProFacturesRechercherFournisseurPost**](ChorusProApi.md#rechercherFacturesFournisseurApiV1ChorusProFacturesRechercherFournisseurPost) | **POST** /api/v1/chorus-pro/factures/rechercher-fournisseur | Search issued invoices (Supplier) |
| [**rechercherStructuresApiV1ChorusProStructuresRechercherPost**](ChorusProApi.md#rechercherStructuresApiV1ChorusProStructuresRechercherPost) | **POST** /api/v1/chorus-pro/structures/rechercher | Search Chorus Pro structures |
| [**recyclerFactureApiV1ChorusProFacturesRecyclerPost**](ChorusProApi.md#recyclerFactureApiV1ChorusProFacturesRecyclerPost) | **POST** /api/v1/chorus-pro/factures/recycler | Recycle an invoice (Supplier) |
| [**soumettreFactureApiV1ChorusProFacturesSoumettrePost**](ChorusProApi.md#soumettreFactureApiV1ChorusProFacturesSoumettrePost) | **POST** /api/v1/chorus-pro/factures/soumettre | Submit an invoice to Chorus Pro |
| [**telechargerGroupeFacturesApiV1ChorusProFacturesTelechargerGroupePost**](ChorusProApi.md#telechargerGroupeFacturesApiV1ChorusProFacturesTelechargerGroupePost) | **POST** /api/v1/chorus-pro/factures/telecharger-groupe | Download a group of invoices |
| [**traiterFactureRecueApiV1ChorusProFacturesTraiterFactureRecuePost**](ChorusProApi.md#traiterFactureRecueApiV1ChorusProFacturesTraiterFactureRecuePost) | **POST** /api/v1/chorus-pro/factures/traiter-facture-recue | Process a received invoice (Recipient) |
| [**valideurConsulterFactureApiV1ChorusProFacturesValideurConsulterPost**](ChorusProApi.md#valideurConsulterFactureApiV1ChorusProFacturesValideurConsulterPost) | **POST** /api/v1/chorus-pro/factures/valideur/consulter | Consult an invoice (Validator) |
| [**valideurRechercherFacturesApiV1ChorusProFacturesValideurRechercherPost**](ChorusProApi.md#valideurRechercherFacturesApiV1ChorusProFacturesValideurRechercherPost) | **POST** /api/v1/chorus-pro/factures/valideur/rechercher | Search invoices to validate (Validator) |
| [**valideurTraiterFactureApiV1ChorusProFacturesValideurTraiterPost**](ChorusProApi.md#valideurTraiterFactureApiV1ChorusProFacturesValideurTraiterPost) | **POST** /api/v1/chorus-pro/factures/valideur/traiter | Validate or reject an invoice (Validator) |


<a id="ajouterFichierApiV1ChorusProTransversesAjouterFichierPost"></a>
# **ajouterFichierApiV1ChorusProTransversesAjouterFichierPost**
> Object ajouterFichierApiV1ChorusProTransversesAjouterFichierPost(requestBody)

Add an attachment

Add an attachment to the current user account.      **Max size**: 10 MB per file      **Example payload**:     &#x60;&#x60;&#x60;json     {       \&quot;pieceJointeFichier\&quot;: \&quot;JVBERi0xLjQKJeLjz9MKNSAwIG9iago8P...\&quot;,       \&quot;pieceJointeNom\&quot;: \&quot;purchase_order.pdf\&quot;,       \&quot;pieceJointeTypeMime\&quot;: \&quot;application/pdf\&quot;,       \&quot;pieceJointeExtension\&quot;: \&quot;PDF\&quot;     }     &#x60;&#x60;&#x60;      **Returns**: The attachment ID (&#x60;pieceJointeIdFichier&#x60;) to use in &#x60;/factures/completer&#x60;.      **Accepted extensions**: PDF, JPG, PNG, ZIP, XML, etc.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChorusProApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ChorusProApi apiInstance = new ChorusProApi(defaultClient);
    Map<String, Object> requestBody = null; // Map<String, Object> | 
    try {
      Object result = apiInstance.ajouterFichierApiV1ChorusProTransversesAjouterFichierPost(requestBody);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ChorusProApi#ajouterFichierApiV1ChorusProTransversesAjouterFichierPost");
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
| **requestBody** | [**Map&lt;String, Object&gt;**](Object.md)|  | |

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
| **200** | Successful Response |  -  |
| **422** | Validation Error |  -  |

<a id="completerFactureApiV1ChorusProFacturesCompleterPost"></a>
# **completerFactureApiV1ChorusProFacturesCompleterPost**
> Object completerFactureApiV1ChorusProFacturesCompleterPost(requestBody)

Complete a suspended invoice (Supplier)

Complete a SUSPENDUE status invoice by adding attachments or a comment.      **Required status**: SUSPENDUE      **Possible actions**:     - Add attachments (supporting documents, purchase orders, etc.)     - Modify comment      **Example payload**:     &#x60;&#x60;&#x60;json     {       \&quot;identifiantFactureCPP\&quot;: 12345,       \&quot;commentaire\&quot;: \&quot;Here are the requested documents\&quot;,       \&quot;listePiecesJointes\&quot;: [         {           \&quot;pieceJointeIdFichier\&quot;: 98765,           \&quot;pieceJointeNom\&quot;: \&quot;purchase_order.pdf\&quot;         }       ]     }     &#x60;&#x60;&#x60;      **Note**: Attachments must first be uploaded via &#x60;/transverses/ajouter-fichier&#x60;.      **After completion**: The invoice returns to MISE_A_DISPOSITION status.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChorusProApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ChorusProApi apiInstance = new ChorusProApi(defaultClient);
    Map<String, Object> requestBody = null; // Map<String, Object> | 
    try {
      Object result = apiInstance.completerFactureApiV1ChorusProFacturesCompleterPost(requestBody);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ChorusProApi#completerFactureApiV1ChorusProFacturesCompleterPost");
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
| **requestBody** | [**Map&lt;String, Object&gt;**](Object.md)|  | |

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
| **200** | Successful Response |  -  |
| **422** | Validation Error |  -  |

<a id="consulterFactureApiV1ChorusProFacturesConsulterPost"></a>
# **consulterFactureApiV1ChorusProFacturesConsulterPost**
> GetInvoiceResponse consulterFactureApiV1ChorusProFacturesConsulterPost(getInvoiceRequest)

Consult invoice status

Retrieves the information and current status of an invoice submitted to Chorus Pro.      **Returns**:     - Invoice number and date     - Total gross amount     - **Current status**: SOUMISE, VALIDEE, REJETEE, SUSPENDUE, MANDATEE, MISE_EN_PAIEMENT, etc.     - Recipient structure      **Use cases**:     - Track the processing progress of an invoice     - Check if an invoice has been validated or rejected     - Get the payment date      **Polling**: Call this endpoint regularly to track status changes.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChorusProApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ChorusProApi apiInstance = new ChorusProApi(defaultClient);
    GetInvoiceRequest getInvoiceRequest = new GetInvoiceRequest(); // GetInvoiceRequest | 
    try {
      GetInvoiceResponse result = apiInstance.consulterFactureApiV1ChorusProFacturesConsulterPost(getInvoiceRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ChorusProApi#consulterFactureApiV1ChorusProFacturesConsulterPost");
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
| **getInvoiceRequest** | [**GetInvoiceRequest**](GetInvoiceRequest.md)|  | |

### Return type

[**GetInvoiceResponse**](GetInvoiceResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **422** | Validation Error |  -  |

<a id="consulterStructureApiV1ChorusProStructuresConsulterPost"></a>
# **consulterStructureApiV1ChorusProStructuresConsulterPost**
> GetStructureResponse consulterStructureApiV1ChorusProStructuresConsulterPost(getStructureRequest)

Consult structure details

Retrieves detailed information about a Chorus Pro structure.       **Returns**:     - Company name     - Intra-EU VAT number     - Contact email     - **Required parameters**: Indicates if service code and/or engagement number are required to submit an invoice      **Typical step**: Called after &#x60;search-structures&#x60; to know which fields are mandatory before submitting an invoice.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChorusProApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ChorusProApi apiInstance = new ChorusProApi(defaultClient);
    GetStructureRequest getStructureRequest = new GetStructureRequest(); // GetStructureRequest | 
    try {
      GetStructureResponse result = apiInstance.consulterStructureApiV1ChorusProStructuresConsulterPost(getStructureRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ChorusProApi#consulterStructureApiV1ChorusProStructuresConsulterPost");
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
| **getStructureRequest** | [**GetStructureRequest**](GetStructureRequest.md)|  | |

### Return type

[**GetStructureResponse**](GetStructureResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **422** | Validation Error |  -  |

<a id="listerServicesStructureApiV1ChorusProStructuresIdStructureCppServicesGet"></a>
# **listerServicesStructureApiV1ChorusProStructuresIdStructureCppServicesGet**
> SearchServicesResponse listerServicesStructureApiV1ChorusProStructuresIdStructureCppServicesGet(idStructureCpp)

List structure services

Retrieves the list of active services for a public structure.      **Use cases**:     - List available services for an administration     - Verify that a service code exists before submitting an invoice      **Returns**:     - List of services with their code, label, and status (active/inactive)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChorusProApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ChorusProApi apiInstance = new ChorusProApi(defaultClient);
    Integer idStructureCpp = 56; // Integer | 
    try {
      SearchServicesResponse result = apiInstance.listerServicesStructureApiV1ChorusProStructuresIdStructureCppServicesGet(idStructureCpp);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ChorusProApi#listerServicesStructureApiV1ChorusProStructuresIdStructureCppServicesGet");
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
| **idStructureCpp** | **Integer**|  | |

### Return type

[**SearchServicesResponse**](SearchServicesResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **422** | Validation Error |  -  |

<a id="obtenirIdChorusProDepuisSiretApiV1ChorusProStructuresObtenirIdDepuisSiretPost"></a>
# **obtenirIdChorusProDepuisSiretApiV1ChorusProStructuresObtenirIdDepuisSiretPost**
> GetChorusProIdResponse obtenirIdChorusProDepuisSiretApiV1ChorusProStructuresObtenirIdDepuisSiretPost(getChorusProIdRequest)

Utility: Get Chorus Pro ID from SIRET

**Convenient utility** to get a structure&#39;s Chorus Pro ID from its SIRET.       This wrapper function combines:     1. Searching for the structure by SIRET     2. Extracting the &#x60;id_structure_cpp&#x60; if a single structure is found      **Returns**:     - &#x60;id_structure_cpp&#x60;: Chorus Pro ID (0 if not found or multiple results)     - &#x60;designation_structure&#x60;: Structure name (if found)     - &#x60;message&#x60;: Explanatory message      **Use cases**:     - Shortcut to directly get the Chorus Pro ID before submitting an invoice     - Simplified alternative to &#x60;search-structures&#x60; + manual ID extraction      **Note**: If multiple structures match the SIRET (rare), returns 0 and an error message.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChorusProApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ChorusProApi apiInstance = new ChorusProApi(defaultClient);
    GetChorusProIdRequest getChorusProIdRequest = new GetChorusProIdRequest(); // GetChorusProIdRequest | 
    try {
      GetChorusProIdResponse result = apiInstance.obtenirIdChorusProDepuisSiretApiV1ChorusProStructuresObtenirIdDepuisSiretPost(getChorusProIdRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ChorusProApi#obtenirIdChorusProDepuisSiretApiV1ChorusProStructuresObtenirIdDepuisSiretPost");
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
| **getChorusProIdRequest** | [**GetChorusProIdRequest**](GetChorusProIdRequest.md)|  | |

### Return type

[**GetChorusProIdResponse**](GetChorusProIdResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **422** | Validation Error |  -  |

<a id="rechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost"></a>
# **rechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost**
> Object rechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost(requestBody)

Search received invoices (Recipient)

Search invoices received by the connected recipient.      **Filters**:     - Downloaded / not downloaded     - Reception dates     - Status (MISE_A_DISPOSITION, SUSPENDUE, etc.)     - Supplier      **Useful indicator**: &#x60;factureTelechargeeParDestinataire&#x60; indicates whether the invoice has already been downloaded.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChorusProApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ChorusProApi apiInstance = new ChorusProApi(defaultClient);
    Map<String, Object> requestBody = null; // Map<String, Object> | 
    try {
      Object result = apiInstance.rechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost(requestBody);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ChorusProApi#rechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost");
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
| **requestBody** | [**Map&lt;String, Object&gt;**](Object.md)|  | |

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
| **200** | Successful Response |  -  |
| **422** | Validation Error |  -  |

<a id="rechercherFacturesFournisseurApiV1ChorusProFacturesRechercherFournisseurPost"></a>
# **rechercherFacturesFournisseurApiV1ChorusProFacturesRechercherFournisseurPost**
> Object rechercherFacturesFournisseurApiV1ChorusProFacturesRechercherFournisseurPost(requestBody)

Search issued invoices (Supplier)

Search invoices issued by the connected supplier.      **Available filters**:     - Invoice number     - Dates (start/end)     - Status     - Recipient structure     - Amount      **Use cases**:     - Track issued invoices     - Verify statuses     - Export for accounting

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChorusProApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ChorusProApi apiInstance = new ChorusProApi(defaultClient);
    Map<String, Object> requestBody = null; // Map<String, Object> | 
    try {
      Object result = apiInstance.rechercherFacturesFournisseurApiV1ChorusProFacturesRechercherFournisseurPost(requestBody);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ChorusProApi#rechercherFacturesFournisseurApiV1ChorusProFacturesRechercherFournisseurPost");
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
| **requestBody** | [**Map&lt;String, Object&gt;**](Object.md)|  | |

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
| **200** | Successful Response |  -  |
| **422** | Validation Error |  -  |

<a id="rechercherStructuresApiV1ChorusProStructuresRechercherPost"></a>
# **rechercherStructuresApiV1ChorusProStructuresRechercherPost**
> SearchStructureResponse rechercherStructuresApiV1ChorusProStructuresRechercherPost(searchStructureRequest)

Search Chorus Pro structures

Search for structures (companies, administrations) registered on Chorus Pro.      **Use cases**:     - Find the Chorus Pro ID of a structure from its SIRET     - Check if a structure is registered on Chorus Pro     - List structures matching criteria      **Available filters**:     - Identifier (SIRET, SIREN, etc.)     - Company name     - Identifier type     - Private structures only      **Typical step**: Called before &#x60;submit-invoice&#x60; to get the recipient&#39;s &#x60;id_structure_cpp&#x60;.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChorusProApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ChorusProApi apiInstance = new ChorusProApi(defaultClient);
    SearchStructureRequest searchStructureRequest = new SearchStructureRequest(); // SearchStructureRequest | 
    try {
      SearchStructureResponse result = apiInstance.rechercherStructuresApiV1ChorusProStructuresRechercherPost(searchStructureRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ChorusProApi#rechercherStructuresApiV1ChorusProStructuresRechercherPost");
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
| **searchStructureRequest** | [**SearchStructureRequest**](SearchStructureRequest.md)|  | |

### Return type

[**SearchStructureResponse**](SearchStructureResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **422** | Validation Error |  -  |

<a id="recyclerFactureApiV1ChorusProFacturesRecyclerPost"></a>
# **recyclerFactureApiV1ChorusProFacturesRecyclerPost**
> Object recyclerFactureApiV1ChorusProFacturesRecyclerPost(requestBody)

Recycle an invoice (Supplier)

Recycle an invoice with A_RECYCLER status by modifying routing data.      **Required status**: A_RECYCLER      **Modifiable fields**:     - Recipient (&#x60;idStructureCPP&#x60;)     - Service code     - Engagement number      **Use cases**:     - Wrong recipient     - Change of billing service     - Update engagement number      **Example payload**:     &#x60;&#x60;&#x60;json     {       \&quot;identifiantFactureCPP\&quot;: 12345,       \&quot;idStructureCPP\&quot;: 67890,       \&quot;codeService\&quot;: \&quot;SERVICE_01\&quot;,       \&quot;numeroEngagement\&quot;: \&quot;ENG2024001\&quot;     }     &#x60;&#x60;&#x60;      **Note**: The invoice keeps its number and amounts, only routing fields change.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChorusProApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ChorusProApi apiInstance = new ChorusProApi(defaultClient);
    Map<String, Object> requestBody = null; // Map<String, Object> | 
    try {
      Object result = apiInstance.recyclerFactureApiV1ChorusProFacturesRecyclerPost(requestBody);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ChorusProApi#recyclerFactureApiV1ChorusProFacturesRecyclerPost");
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
| **requestBody** | [**Map&lt;String, Object&gt;**](Object.md)|  | |

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
| **200** | Successful Response |  -  |
| **422** | Validation Error |  -  |

<a id="soumettreFactureApiV1ChorusProFacturesSoumettrePost"></a>
# **soumettreFactureApiV1ChorusProFacturesSoumettrePost**
> SubmitInvoiceResponse soumettreFactureApiV1ChorusProFacturesSoumettrePost(submitInvoiceRequest)

Submit an invoice to Chorus Pro

Submits an electronic invoice to a public structure via Chorus Pro.       **Complete workflow**:     1. **Upload the Factur-X PDF** via &#x60;/transverses/ajouter-fichier&#x60; → retrieve &#x60;pieceJointeId&#x60;     2. **Get the structure ID** via &#x60;/structures/rechercher&#x60; or &#x60;/structures/obtenir-id-depuis-siret&#x60;     3. **Check mandatory parameters** via &#x60;/structures/consulter&#x60;     4. **Submit the invoice** with the &#x60;piece_jointe_principale_id&#x60; obtained in step 1      **Prerequisites**:     1. Have the recipient&#39;s &#x60;id_structure_cpp&#x60; (via &#x60;/structures/rechercher&#x60;)     2. Know the mandatory parameters (via &#x60;/structures/consulter&#x60;):        - Service code if &#x60;code_service_doit_etre_renseigne&#x3D;true&#x60;        - Engagement number if &#x60;numero_ej_doit_etre_renseigne&#x3D;true&#x60;     3. Have uploaded the Factur-X PDF (via &#x60;/transverses/ajouter-fichier&#x60;)      **Expected format**:     - &#x60;piece_jointe_principale_id&#x60;: ID returned by &#x60;/transverses/ajouter-fichier&#x60;     - Amounts: Strings with 2 decimals (e.g., \&quot;1250.50\&quot;)     - Dates: ISO 8601 format (YYYY-MM-DD)      **Returns**:     - &#x60;identifiant_facture_cpp&#x60;: Chorus Pro ID of the created invoice     - &#x60;numero_flux_depot&#x60;: Deposit tracking number      **Possible statuses after submission**:     - SOUMISE: Pending validation     - VALIDEE: Validated by recipient     - REJETEE: Rejected (data error or business refusal)     - SUSPENDUE: Pending additional information      **Note**: Use &#x60;/factures/consulter&#x60; to track status changes.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChorusProApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ChorusProApi apiInstance = new ChorusProApi(defaultClient);
    SubmitInvoiceRequest submitInvoiceRequest = new SubmitInvoiceRequest(); // SubmitInvoiceRequest | 
    try {
      SubmitInvoiceResponse result = apiInstance.soumettreFactureApiV1ChorusProFacturesSoumettrePost(submitInvoiceRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ChorusProApi#soumettreFactureApiV1ChorusProFacturesSoumettrePost");
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
| **submitInvoiceRequest** | [**SubmitInvoiceRequest**](SubmitInvoiceRequest.md)|  | |

### Return type

[**SubmitInvoiceResponse**](SubmitInvoiceResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **422** | Validation Error |  -  |

<a id="telechargerGroupeFacturesApiV1ChorusProFacturesTelechargerGroupePost"></a>
# **telechargerGroupeFacturesApiV1ChorusProFacturesTelechargerGroupePost**
> Object telechargerGroupeFacturesApiV1ChorusProFacturesTelechargerGroupePost(requestBody)

Download a group of invoices

Download one or more invoices (max 10 recommended) with their attachments.      **Available formats**:     - PDF: PDF file only     - XML: XML file only     - ZIP: Archive containing PDF + XML + attachments      **Maximum size**: 120 MB per download      **Example payload**:     &#x60;&#x60;&#x60;json     {       \&quot;listeIdentifiantsFactureCPP\&quot;: [12345, 12346],       \&quot;inclurePiecesJointes\&quot;: true,       \&quot;formatFichier\&quot;: \&quot;ZIP\&quot;     }     &#x60;&#x60;&#x60;      **Returns**: The file is base64-encoded in the &#x60;fichierBase64&#x60; field.      **Note**: The &#x60;factureTelechargeeParDestinataire&#x60; flag is automatically updated.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChorusProApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ChorusProApi apiInstance = new ChorusProApi(defaultClient);
    Map<String, Object> requestBody = null; // Map<String, Object> | 
    try {
      Object result = apiInstance.telechargerGroupeFacturesApiV1ChorusProFacturesTelechargerGroupePost(requestBody);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ChorusProApi#telechargerGroupeFacturesApiV1ChorusProFacturesTelechargerGroupePost");
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
| **requestBody** | [**Map&lt;String, Object&gt;**](Object.md)|  | |

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
| **200** | Successful Response |  -  |
| **422** | Validation Error |  -  |

<a id="traiterFactureRecueApiV1ChorusProFacturesTraiterFactureRecuePost"></a>
# **traiterFactureRecueApiV1ChorusProFacturesTraiterFactureRecuePost**
> Object traiterFactureRecueApiV1ChorusProFacturesTraiterFactureRecuePost(requestBody)

Process a received invoice (Recipient)

Change the status of a received invoice.      **Possible statuses**:     - MISE_A_DISPOSITION: Invoice accepted     - SUSPENDUE: Pending additional information (reason required)     - REJETEE: Invoice refused (reason required)     - MANDATEE: Invoice mandated     - MISE_EN_PAIEMENT: Invoice being paid     - COMPTABILISEE: Invoice accounted     - MISE_A_DISPOSITION_COMPTABLE: Made available to accounting     - A_RECYCLER: To be recycled     - COMPLETEE: Completed     - SERVICE-FAIT: Service rendered     - PRISE_EN_COMPTE_DESTINATAIRE: Acknowledged     - TRANSMISE_MOA: Transmitted to MOA      **Example payload**:     &#x60;&#x60;&#x60;json     {       \&quot;identifiantFactureCPP\&quot;: 12345,       \&quot;nouveauStatut\&quot;: \&quot;REJETEE\&quot;,       \&quot;motifRejet\&quot;: \&quot;Duplicate invoice\&quot;,       \&quot;commentaire\&quot;: \&quot;Invoice already received under reference ABC123\&quot;     }     &#x60;&#x60;&#x60;      **Rules**:     - A reason is **required** for SUSPENDUE and REJETEE     - Only certain statuses are allowed depending on the invoice&#39;s current status

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChorusProApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ChorusProApi apiInstance = new ChorusProApi(defaultClient);
    Map<String, Object> requestBody = null; // Map<String, Object> | 
    try {
      Object result = apiInstance.traiterFactureRecueApiV1ChorusProFacturesTraiterFactureRecuePost(requestBody);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ChorusProApi#traiterFactureRecueApiV1ChorusProFacturesTraiterFactureRecuePost");
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
| **requestBody** | [**Map&lt;String, Object&gt;**](Object.md)|  | |

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
| **200** | Successful Response |  -  |
| **422** | Validation Error |  -  |

<a id="valideurConsulterFactureApiV1ChorusProFacturesValideurConsulterPost"></a>
# **valideurConsulterFactureApiV1ChorusProFacturesValideurConsulterPost**
> Object valideurConsulterFactureApiV1ChorusProFacturesValideurConsulterPost(requestBody)

Consult an invoice (Validator)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChorusProApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ChorusProApi apiInstance = new ChorusProApi(defaultClient);
    Map<String, Object> requestBody = null; // Map<String, Object> | 
    try {
      Object result = apiInstance.valideurConsulterFactureApiV1ChorusProFacturesValideurConsulterPost(requestBody);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ChorusProApi#valideurConsulterFactureApiV1ChorusProFacturesValideurConsulterPost");
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
| **requestBody** | [**Map&lt;String, Object&gt;**](Object.md)|  | |

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
| **200** | Successful Response |  -  |
| **422** | Validation Error |  -  |

<a id="valideurRechercherFacturesApiV1ChorusProFacturesValideurRechercherPost"></a>
# **valideurRechercherFacturesApiV1ChorusProFacturesValideurRechercherPost**
> Object valideurRechercherFacturesApiV1ChorusProFacturesValideurRechercherPost(requestBody)

Search invoices to validate (Validator)

Search invoices pending validation by the connected validator.      **Role**: Validator in the internal validation workflow.      **Filters**: Dates, structure, service, etc.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChorusProApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ChorusProApi apiInstance = new ChorusProApi(defaultClient);
    Map<String, Object> requestBody = null; // Map<String, Object> | 
    try {
      Object result = apiInstance.valideurRechercherFacturesApiV1ChorusProFacturesValideurRechercherPost(requestBody);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ChorusProApi#valideurRechercherFacturesApiV1ChorusProFacturesValideurRechercherPost");
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
| **requestBody** | [**Map&lt;String, Object&gt;**](Object.md)|  | |

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
| **200** | Successful Response |  -  |
| **422** | Validation Error |  -  |

<a id="valideurTraiterFactureApiV1ChorusProFacturesValideurTraiterPost"></a>
# **valideurTraiterFactureApiV1ChorusProFacturesValideurTraiterPost**
> Object valideurTraiterFactureApiV1ChorusProFacturesValideurTraiterPost(requestBody)

Validate or reject an invoice (Validator)

Validate or reject an invoice pending validation.      **Actions**:     - Validate: The invoice moves to the next status in the workflow     - Reject: The invoice is rejected (reason required)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ChorusProApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ChorusProApi apiInstance = new ChorusProApi(defaultClient);
    Map<String, Object> requestBody = null; // Map<String, Object> | 
    try {
      Object result = apiInstance.valideurTraiterFactureApiV1ChorusProFacturesValideurTraiterPost(requestBody);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ChorusProApi#valideurTraiterFactureApiV1ChorusProFacturesValideurTraiterPost");
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
| **requestBody** | [**Map&lt;String, Object&gt;**](Object.md)|  | |

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
| **200** | Successful Response |  -  |
| **422** | Validation Error |  -  |

