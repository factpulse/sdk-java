# DocumentConversionApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**convertDocumentApiV1ConvertPost**](DocumentConversionApi.md#convertDocumentApiV1ConvertPost) | **POST** /api/v1/convert | Convertir un document en Factur-X |
| [**convertDocumentAsyncApiV1ConvertAsyncPost**](DocumentConversionApi.md#convertDocumentAsyncApiV1ConvertAsyncPost) | **POST** /api/v1/convert/async | Convertir un document en Factur-X (mode asynchrone) |
| [**downloadFileApiV1ConvertConversionIdDownloadFilenameGet**](DocumentConversionApi.md#downloadFileApiV1ConvertConversionIdDownloadFilenameGet) | **GET** /api/v1/convert/{conversion_id}/download/{filename} | Télécharger un fichier généré |
| [**getConversionStatusApiV1ConvertConversionIdStatusGet**](DocumentConversionApi.md#getConversionStatusApiV1ConvertConversionIdStatusGet) | **GET** /api/v1/convert/{conversion_id}/status | Vérifier le statut d&#39;une conversion |
| [**resumeConversionApiV1ConvertConversionIdResumePost**](DocumentConversionApi.md#resumeConversionApiV1ConvertConversionIdResumePost) | **POST** /api/v1/convert/{conversion_id}/resume | Reprendre une conversion avec corrections |


<a id="convertDocumentApiV1ConvertPost"></a>
# **convertDocumentApiV1ConvertPost**
> ConvertSuccessResponse convertDocumentApiV1ConvertPost(_file, output, callbackUrl)

Convertir un document en Factur-X

Convertit un document (PDF, DOCX, XLSX, image) en Factur-X conforme.  ## Workflow  1. **Upload** : Le document est envoyé en multipart/form-data 2. **Extraction OCR + Classification** : Mistral OCR extrait les données et classifie le document en un seul appel 3. **Enrichissement** : Les données sont enrichies via SIRENE (SIRET → raison sociale) 4. **Validation** : Les règles Schematron sont appliquées 5. **Génération** : Le Factur-X PDF/A-3 est généré  ## Réponses possibles  - **200** : Conversion réussie, fichiers disponibles - **202** : Données manquantes, complétion requise - **422** : Validation échouée, corrections nécessaires - **400** : Fichier invalide - **429** : Quota dépassé

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DocumentConversionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    DocumentConversionApi apiInstance = new DocumentConversionApi(defaultClient);
    File _file = new File("/path/to/file"); // File | Document à convertir (PDF, DOCX, XLSX, JPG, PNG)
    String output = "pdf"; // String | Format de sortie: pdf, xml, both
    String callbackUrl = "callbackUrl_example"; // String | 
    try {
      ConvertSuccessResponse result = apiInstance.convertDocumentApiV1ConvertPost(_file, output, callbackUrl);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DocumentConversionApi#convertDocumentApiV1ConvertPost");
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
| **_file** | **File**| Document à convertir (PDF, DOCX, XLSX, JPG, PNG) | |
| **output** | **String**| Format de sortie: pdf, xml, both | [optional] [default to pdf] |
| **callbackUrl** | **String**|  | [optional] |

### Return type

[**ConvertSuccessResponse**](ConvertSuccessResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **202** | Données manquantes |  -  |
| **422** | Validation échouée |  -  |
| **400** | Fichier invalide |  -  |

<a id="convertDocumentAsyncApiV1ConvertAsyncPost"></a>
# **convertDocumentAsyncApiV1ConvertAsyncPost**
> Object convertDocumentAsyncApiV1ConvertAsyncPost(_file, output, callbackUrl)

Convertir un document en Factur-X (mode asynchrone)

Lance une conversion asynchrone via Celery.  ## Workflow  1. **Upload** : Le document est envoyé en multipart/form-data 2. **Task Celery** : La tâche est mise en file d&#39;attente 3. **Callback** : Notification par webhook à la fin  ## Réponses possibles  - **202** : Tâche acceptée, en cours de traitement - **400** : Fichier invalide

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DocumentConversionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    DocumentConversionApi apiInstance = new DocumentConversionApi(defaultClient);
    File _file = new File("/path/to/file"); // File | Document à convertir (PDF, DOCX, XLSX, JPG, PNG)
    String output = "pdf"; // String | Format de sortie: pdf, xml, both
    String callbackUrl = "callbackUrl_example"; // String | 
    try {
      Object result = apiInstance.convertDocumentAsyncApiV1ConvertAsyncPost(_file, output, callbackUrl);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DocumentConversionApi#convertDocumentAsyncApiV1ConvertAsyncPost");
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
| **_file** | **File**| Document à convertir (PDF, DOCX, XLSX, JPG, PNG) | |
| **output** | **String**| Format de sortie: pdf, xml, both | [optional] [default to pdf] |
| **callbackUrl** | **String**|  | [optional] |

### Return type

**Object**

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **202** | Tâche acceptée |  -  |
| **400** | Fichier invalide |  -  |
| **422** | Validation Error |  -  |

<a id="downloadFileApiV1ConvertConversionIdDownloadFilenameGet"></a>
# **downloadFileApiV1ConvertConversionIdDownloadFilenameGet**
> Object downloadFileApiV1ConvertConversionIdDownloadFilenameGet(conversionId, filename)

Télécharger un fichier généré

Télécharge le fichier Factur-X PDF ou XML généré.  ## Fichiers disponibles  - &#x60;facturx.pdf&#x60; : PDF/A-3 avec XML embarqué - &#x60;facturx.xml&#x60; : XML CII seul (Cross Industry Invoice)  Les fichiers sont disponibles pendant 24 heures après génération.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DocumentConversionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    DocumentConversionApi apiInstance = new DocumentConversionApi(defaultClient);
    String conversionId = "conversionId_example"; // String | 
    String filename = "filename_example"; // String | 
    try {
      Object result = apiInstance.downloadFileApiV1ConvertConversionIdDownloadFilenameGet(conversionId, filename);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DocumentConversionApi#downloadFileApiV1ConvertConversionIdDownloadFilenameGet");
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
| **conversionId** | **String**|  | |
| **filename** | **String**|  | |

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
| **200** | Fichier téléchargé |  -  |
| **404** | Fichier non trouvé ou expiré |  -  |
| **422** | Validation Error |  -  |

<a id="getConversionStatusApiV1ConvertConversionIdStatusGet"></a>
# **getConversionStatusApiV1ConvertConversionIdStatusGet**
> Map&lt;String, Object&gt; getConversionStatusApiV1ConvertConversionIdStatusGet(conversionId)

Vérifier le statut d&#39;une conversion

Retourne le statut actuel d&#39;une conversion asynchrone.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DocumentConversionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    DocumentConversionApi apiInstance = new DocumentConversionApi(defaultClient);
    String conversionId = "conversionId_example"; // String | 
    try {
      Map<String, Object> result = apiInstance.getConversionStatusApiV1ConvertConversionIdStatusGet(conversionId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DocumentConversionApi#getConversionStatusApiV1ConvertConversionIdStatusGet");
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
| **conversionId** | **String**|  | |

### Return type

**Map&lt;String, Object&gt;**

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

<a id="resumeConversionApiV1ConvertConversionIdResumePost"></a>
# **resumeConversionApiV1ConvertConversionIdResumePost**
> ConvertSuccessResponse resumeConversionApiV1ConvertConversionIdResumePost(conversionId, convertResumeRequest)

Reprendre une conversion avec corrections

Reprend une conversion après complétion des données manquantes ou correction des erreurs.  L&#39;extraction OCR est conservée, les données sont mises à jour avec les corrections, puis une nouvelle validation Schematron est effectuée.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DocumentConversionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    DocumentConversionApi apiInstance = new DocumentConversionApi(defaultClient);
    String conversionId = "conversionId_example"; // String | 
    ConvertResumeRequest convertResumeRequest = new ConvertResumeRequest(); // ConvertResumeRequest | 
    try {
      ConvertSuccessResponse result = apiInstance.resumeConversionApiV1ConvertConversionIdResumePost(conversionId, convertResumeRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DocumentConversionApi#resumeConversionApiV1ConvertConversionIdResumePost");
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
| **conversionId** | **String**|  | |
| **convertResumeRequest** | [**ConvertResumeRequest**](ConvertResumeRequest.md)|  | |

### Return type

[**ConvertSuccessResponse**](ConvertSuccessResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **404** | Conversion non trouvée ou expirée |  -  |
| **422** | Validation toujours en échec |  -  |

