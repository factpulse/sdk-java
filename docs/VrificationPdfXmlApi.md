# VrificationPdfXmlApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet**](VrificationPdfXmlApi.md#obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet) | **GET** /api/v1/verification/verifier-async/{id_tache}/statut | Obtenir le statut d&#39;une vérification asynchrone |
| [**obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet_0**](VrificationPdfXmlApi.md#obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet_0) | **GET** /api/v1/verification/verifier-async/{id_tache}/statut | Obtenir le statut d&#39;une vérification asynchrone |
| [**verifierPdfAsyncApiV1VerificationVerifierAsyncPost**](VrificationPdfXmlApi.md#verifierPdfAsyncApiV1VerificationVerifierAsyncPost) | **POST** /api/v1/verification/verifier-async | Vérifier la conformité PDF/XML Factur-X (asynchrone) |
| [**verifierPdfAsyncApiV1VerificationVerifierAsyncPost_0**](VrificationPdfXmlApi.md#verifierPdfAsyncApiV1VerificationVerifierAsyncPost_0) | **POST** /api/v1/verification/verifier-async | Vérifier la conformité PDF/XML Factur-X (asynchrone) |
| [**verifierPdfSyncApiV1VerificationVerifierPost**](VrificationPdfXmlApi.md#verifierPdfSyncApiV1VerificationVerifierPost) | **POST** /api/v1/verification/verifier | Vérifier la conformité PDF/XML Factur-X (synchrone) |
| [**verifierPdfSyncApiV1VerificationVerifierPost_0**](VrificationPdfXmlApi.md#verifierPdfSyncApiV1VerificationVerifierPost_0) | **POST** /api/v1/verification/verifier | Vérifier la conformité PDF/XML Factur-X (synchrone) |


<a id="obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet"></a>
# **obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet**
> StatutTache obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet(idTache)

Obtenir le statut d&#39;une vérification asynchrone

Récupère le statut et le résultat d&#39;une tâche de vérification asynchrone.  **Statuts possibles:** - &#x60;PENDING&#x60;: Tâche en attente dans la file - &#x60;STARTED&#x60;: Tâche en cours d&#39;exécution - &#x60;SUCCESS&#x60;: Tâche terminée avec succès (voir &#x60;resultat&#x60;) - &#x60;FAILURE&#x60;: Erreur système (exception non gérée)  **Note:** Le champ &#x60;resultat.statut&#x60; peut être \&quot;SUCCES\&quot; ou \&quot;ERREUR\&quot; indépendamment du statut Celery (qui sera toujours SUCCESS si la tâche s&#39;est exécutée).

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.VrificationPdfXmlApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    VrificationPdfXmlApi apiInstance = new VrificationPdfXmlApi(defaultClient);
    String idTache = "idTache_example"; // String | 
    try {
      StatutTache result = apiInstance.obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet(idTache);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling VrificationPdfXmlApi#obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet");
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
| **idTache** | **String**|  | |

### Return type

[**StatutTache**](StatutTache.md)

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

<a id="obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet_0"></a>
# **obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet_0**
> StatutTache obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet_0(idTache)

Obtenir le statut d&#39;une vérification asynchrone

Récupère le statut et le résultat d&#39;une tâche de vérification asynchrone.  **Statuts possibles:** - &#x60;PENDING&#x60;: Tâche en attente dans la file - &#x60;STARTED&#x60;: Tâche en cours d&#39;exécution - &#x60;SUCCESS&#x60;: Tâche terminée avec succès (voir &#x60;resultat&#x60;) - &#x60;FAILURE&#x60;: Erreur système (exception non gérée)  **Note:** Le champ &#x60;resultat.statut&#x60; peut être \&quot;SUCCES\&quot; ou \&quot;ERREUR\&quot; indépendamment du statut Celery (qui sera toujours SUCCESS si la tâche s&#39;est exécutée).

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.VrificationPdfXmlApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    VrificationPdfXmlApi apiInstance = new VrificationPdfXmlApi(defaultClient);
    String idTache = "idTache_example"; // String | 
    try {
      StatutTache result = apiInstance.obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet_0(idTache);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling VrificationPdfXmlApi#obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet_0");
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
| **idTache** | **String**|  | |

### Return type

[**StatutTache**](StatutTache.md)

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

<a id="verifierPdfAsyncApiV1VerificationVerifierAsyncPost"></a>
# **verifierPdfAsyncApiV1VerificationVerifierAsyncPost**
> ReponseTache verifierPdfAsyncApiV1VerificationVerifierAsyncPost(fichierPdf, forcerOcr)

Vérifier la conformité PDF/XML Factur-X (asynchrone)

Vérifie la conformité PDF/XML Factur-X de manière asynchrone.  **IMPORTANT**: Seuls les PDF Factur-X (avec XML embarqué) sont acceptés. Les PDF sans XML Factur-X retourneront une erreur &#x60;NOT_FACTURX&#x60; dans le résultat.  Cette version utilise une tâche Celery et peut faire appel au service OCR si le PDF est une image ou si &#x60;forcer_ocr&#x3D;true&#x60;.  **Retourne immédiatement** un ID de tâche. Utilisez &#x60;/verifier-async/{id_tache}/statut&#x60; pour récupérer le résultat.  **Principe de vérification (Factur-X 1.08):** - Principe n°2: Le XML ne peut contenir que des infos présentes dans le PDF - Principe n°4: Toute info XML doit être présente et conforme dans le PDF  **Champs vérifiés:** - Identification: BT-1 (n° facture), BT-2 (date), BT-3 (type), BT-5 (devise), BT-23 (cadre) - Vendeur: BT-27 (nom), BT-29 (SIRET), BT-30 (SIREN), BT-31 (TVA) - Acheteur: BT-44 (nom), BT-46 (SIRET), BT-47 (SIREN), BT-48 (TVA) - Montants: BT-109 (HT), BT-110 (TVA), BT-112 (TTC), BT-115 (à payer) - Ventilation TVA: BT-116, BT-117, BT-118, BT-119 - Lignes de facture: BT-153, BT-129, BT-146, BT-131 - Notes obligatoires: PMT, PMD, AAB - Règle BR-FR-09: cohérence SIRET/SIREN  **Avantages par rapport à la version synchrone:** - Support OCR pour les PDF images (via service DocTR) - Timeout plus long pour les gros documents - Ne bloque pas le serveur

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.VrificationPdfXmlApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    VrificationPdfXmlApi apiInstance = new VrificationPdfXmlApi(defaultClient);
    File fichierPdf = new File("/path/to/file"); // File | Fichier PDF Factur-X à vérifier
    Boolean forcerOcr = false; // Boolean | Forcer l'utilisation de l'OCR même si le PDF contient du texte natif
    try {
      ReponseTache result = apiInstance.verifierPdfAsyncApiV1VerificationVerifierAsyncPost(fichierPdf, forcerOcr);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling VrificationPdfXmlApi#verifierPdfAsyncApiV1VerificationVerifierAsyncPost");
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
| **fichierPdf** | **File**| Fichier PDF Factur-X à vérifier | |
| **forcerOcr** | **Boolean**| Forcer l&#39;utilisation de l&#39;OCR même si le PDF contient du texte natif | [optional] [default to false] |

### Return type

[**ReponseTache**](ReponseTache.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **202** | Successful Response |  -  |
| **422** | Validation Error |  -  |

<a id="verifierPdfAsyncApiV1VerificationVerifierAsyncPost_0"></a>
# **verifierPdfAsyncApiV1VerificationVerifierAsyncPost_0**
> ReponseTache verifierPdfAsyncApiV1VerificationVerifierAsyncPost_0(fichierPdf, forcerOcr)

Vérifier la conformité PDF/XML Factur-X (asynchrone)

Vérifie la conformité PDF/XML Factur-X de manière asynchrone.  **IMPORTANT**: Seuls les PDF Factur-X (avec XML embarqué) sont acceptés. Les PDF sans XML Factur-X retourneront une erreur &#x60;NOT_FACTURX&#x60; dans le résultat.  Cette version utilise une tâche Celery et peut faire appel au service OCR si le PDF est une image ou si &#x60;forcer_ocr&#x3D;true&#x60;.  **Retourne immédiatement** un ID de tâche. Utilisez &#x60;/verifier-async/{id_tache}/statut&#x60; pour récupérer le résultat.  **Principe de vérification (Factur-X 1.08):** - Principe n°2: Le XML ne peut contenir que des infos présentes dans le PDF - Principe n°4: Toute info XML doit être présente et conforme dans le PDF  **Champs vérifiés:** - Identification: BT-1 (n° facture), BT-2 (date), BT-3 (type), BT-5 (devise), BT-23 (cadre) - Vendeur: BT-27 (nom), BT-29 (SIRET), BT-30 (SIREN), BT-31 (TVA) - Acheteur: BT-44 (nom), BT-46 (SIRET), BT-47 (SIREN), BT-48 (TVA) - Montants: BT-109 (HT), BT-110 (TVA), BT-112 (TTC), BT-115 (à payer) - Ventilation TVA: BT-116, BT-117, BT-118, BT-119 - Lignes de facture: BT-153, BT-129, BT-146, BT-131 - Notes obligatoires: PMT, PMD, AAB - Règle BR-FR-09: cohérence SIRET/SIREN  **Avantages par rapport à la version synchrone:** - Support OCR pour les PDF images (via service DocTR) - Timeout plus long pour les gros documents - Ne bloque pas le serveur

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.VrificationPdfXmlApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    VrificationPdfXmlApi apiInstance = new VrificationPdfXmlApi(defaultClient);
    File fichierPdf = new File("/path/to/file"); // File | Fichier PDF Factur-X à vérifier
    Boolean forcerOcr = false; // Boolean | Forcer l'utilisation de l'OCR même si le PDF contient du texte natif
    try {
      ReponseTache result = apiInstance.verifierPdfAsyncApiV1VerificationVerifierAsyncPost_0(fichierPdf, forcerOcr);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling VrificationPdfXmlApi#verifierPdfAsyncApiV1VerificationVerifierAsyncPost_0");
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
| **fichierPdf** | **File**| Fichier PDF Factur-X à vérifier | |
| **forcerOcr** | **Boolean**| Forcer l&#39;utilisation de l&#39;OCR même si le PDF contient du texte natif | [optional] [default to false] |

### Return type

[**ReponseTache**](ReponseTache.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **202** | Successful Response |  -  |
| **422** | Validation Error |  -  |

<a id="verifierPdfSyncApiV1VerificationVerifierPost"></a>
# **verifierPdfSyncApiV1VerificationVerifierPost**
> ReponseVerificationSucces verifierPdfSyncApiV1VerificationVerifierPost(fichierPdf)

Vérifier la conformité PDF/XML Factur-X (synchrone)

Vérifie la conformité entre le PDF et son XML Factur-X embarqué.  **IMPORTANT**: Seuls les PDF Factur-X (avec XML embarqué) sont acceptés. Les PDF sans XML Factur-X seront rejetés avec une erreur 400.  Cette version synchrone utilise uniquement l&#39;extraction PDF native (pdfplumber). Pour les PDF images nécessitant de l&#39;OCR, utilisez l&#39;endpoint &#x60;/verifier-async&#x60;.  **Principe de vérification (Factur-X 1.08):** - Principe n°2: Le XML ne peut contenir que des infos présentes dans le PDF - Principe n°4: Toute info XML doit être présente et conforme dans le PDF  **Champs vérifiés:** - Identification: BT-1 (n° facture), BT-2 (date), BT-3 (type), BT-5 (devise), BT-23 (cadre) - Vendeur: BT-27 (nom), BT-29 (SIRET), BT-30 (SIREN), BT-31 (TVA) - Acheteur: BT-44 (nom), BT-46 (SIRET), BT-47 (SIREN), BT-48 (TVA) - Montants: BT-109 (HT), BT-110 (TVA), BT-112 (TTC), BT-115 (à payer) - Ventilation TVA: BT-116, BT-117, BT-118, BT-119 - Lignes de facture: BT-153, BT-129, BT-146, BT-131 - Notes obligatoires: PMT, PMD, AAB - Règle BR-FR-09: cohérence SIRET/SIREN

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.VrificationPdfXmlApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    VrificationPdfXmlApi apiInstance = new VrificationPdfXmlApi(defaultClient);
    File fichierPdf = new File("/path/to/file"); // File | Fichier PDF Factur-X à vérifier
    try {
      ReponseVerificationSucces result = apiInstance.verifierPdfSyncApiV1VerificationVerifierPost(fichierPdf);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling VrificationPdfXmlApi#verifierPdfSyncApiV1VerificationVerifierPost");
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
| **fichierPdf** | **File**| Fichier PDF Factur-X à vérifier | |

### Return type

[**ReponseVerificationSucces**](ReponseVerificationSucces.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Vérification réussie |  -  |
| **400** | Erreur de vérification (PDF non Factur-X, invalide, etc.) |  -  |
| **413** | PDF trop volumineux ou trop de pages |  -  |
| **422** | Validation Error |  -  |

<a id="verifierPdfSyncApiV1VerificationVerifierPost_0"></a>
# **verifierPdfSyncApiV1VerificationVerifierPost_0**
> ReponseVerificationSucces verifierPdfSyncApiV1VerificationVerifierPost_0(fichierPdf)

Vérifier la conformité PDF/XML Factur-X (synchrone)

Vérifie la conformité entre le PDF et son XML Factur-X embarqué.  **IMPORTANT**: Seuls les PDF Factur-X (avec XML embarqué) sont acceptés. Les PDF sans XML Factur-X seront rejetés avec une erreur 400.  Cette version synchrone utilise uniquement l&#39;extraction PDF native (pdfplumber). Pour les PDF images nécessitant de l&#39;OCR, utilisez l&#39;endpoint &#x60;/verifier-async&#x60;.  **Principe de vérification (Factur-X 1.08):** - Principe n°2: Le XML ne peut contenir que des infos présentes dans le PDF - Principe n°4: Toute info XML doit être présente et conforme dans le PDF  **Champs vérifiés:** - Identification: BT-1 (n° facture), BT-2 (date), BT-3 (type), BT-5 (devise), BT-23 (cadre) - Vendeur: BT-27 (nom), BT-29 (SIRET), BT-30 (SIREN), BT-31 (TVA) - Acheteur: BT-44 (nom), BT-46 (SIRET), BT-47 (SIREN), BT-48 (TVA) - Montants: BT-109 (HT), BT-110 (TVA), BT-112 (TTC), BT-115 (à payer) - Ventilation TVA: BT-116, BT-117, BT-118, BT-119 - Lignes de facture: BT-153, BT-129, BT-146, BT-131 - Notes obligatoires: PMT, PMD, AAB - Règle BR-FR-09: cohérence SIRET/SIREN

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.VrificationPdfXmlApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    VrificationPdfXmlApi apiInstance = new VrificationPdfXmlApi(defaultClient);
    File fichierPdf = new File("/path/to/file"); // File | Fichier PDF Factur-X à vérifier
    try {
      ReponseVerificationSucces result = apiInstance.verifierPdfSyncApiV1VerificationVerifierPost_0(fichierPdf);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling VrificationPdfXmlApi#verifierPdfSyncApiV1VerificationVerifierPost_0");
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
| **fichierPdf** | **File**| Fichier PDF Factur-X à vérifier | |

### Return type

[**ReponseVerificationSucces**](ReponseVerificationSucces.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Vérification réussie |  -  |
| **400** | Erreur de vérification (PDF non Factur-X, invalide, etc.) |  -  |
| **413** | PDF trop volumineux ou trop de pages |  -  |
| **422** | Validation Error |  -  |

