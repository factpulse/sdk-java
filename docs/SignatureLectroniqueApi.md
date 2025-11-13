# SignatureLectroniqueApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**genererCertificatTestApiV1TraitementGenererCertificatTestPost**](SignatureLectroniqueApi.md#genererCertificatTestApiV1TraitementGenererCertificatTestPost) | **POST** /api/v1/traitement/generer-certificat-test | Générer un certificat X.509 auto-signé de test |
| [**signerPdfApiV1TraitementSignerPdfPost**](SignatureLectroniqueApi.md#signerPdfApiV1TraitementSignerPdfPost) | **POST** /api/v1/traitement/signer-pdf | Signer un PDF avec le certificat du client (PAdES-B-LT) |
| [**signerPdfAsyncApiV1TraitementSignerPdfAsyncPost**](SignatureLectroniqueApi.md#signerPdfAsyncApiV1TraitementSignerPdfAsyncPost) | **POST** /api/v1/traitement/signer-pdf-async | Signer un PDF de manière asynchrone (Celery) |
| [**validerSignaturePdfEndpointApiV1TraitementValiderSignaturePdfPost**](SignatureLectroniqueApi.md#validerSignaturePdfEndpointApiV1TraitementValiderSignaturePdfPost) | **POST** /api/v1/traitement/valider-signature-pdf | Valider les signatures électroniques d&#39;un PDF |


<a id="genererCertificatTestApiV1TraitementGenererCertificatTestPost"></a>
# **genererCertificatTestApiV1TraitementGenererCertificatTestPost**
> GenerateCertificateResponse genererCertificatTestApiV1TraitementGenererCertificatTestPost(generateCertificateRequest)

Générer un certificat X.509 auto-signé de test

Génère un certificat X.509 auto-signé pour les tests de signature électronique PDF.      **⚠️ ATTENTION : Certificat de TEST uniquement !**      Ce certificat est :     - ✅ Adapté pour tests et développement     - ✅ Compatible signature PDF (PAdES)     - ✅ Conforme eIDAS niveau **SES** (Simple Electronic Signature)     - ❌ **JAMAIS utilisable en production**     - ❌ **Non reconnu** par les navigateurs et lecteurs PDF     - ❌ **Aucune valeur juridique**      ## Niveaux eIDAS      - **SES** (Simple) : Certificat auto-signé ← Généré par cet endpoint     - **AdES** (Advanced) : Certificat CA commerciale (Let&#39;s Encrypt, etc.)     - **QES** (Qualified) : Certificat qualifié PSCO (CertEurope, Universign, etc.)      ## Utilisation      Une fois généré, le certificat peut être :      1. **Enregistré dans Django** (recommandé) :        - Django Admin &gt; Certificats de signature        - Upload &#x60;certificat_pem&#x60; et &#x60;cle_privee_pem&#x60;      2. **Utilisé directement** :        - Signer un PDF avec &#x60;/signer-pdf&#x60;        - Le certificat sera automatiquement utilisé      ## Exemple d&#39;appel      &#x60;&#x60;&#x60;bash     curl -X POST \&quot;https://www.factpulse.fr/api/facturation/generer-certificat-test\&quot; \\       -H \&quot;Authorization: Bearer eyJ0eXAi...\&quot; \\       -H \&quot;Content-Type: application/json\&quot; \\       -d &#39;{         \&quot;cn\&quot;: \&quot;Test Client XYZ\&quot;,         \&quot;organisation\&quot;: \&quot;Client XYZ SARL\&quot;,         \&quot;email\&quot;: \&quot;contact@xyz.fr\&quot;,         \&quot;duree_jours\&quot;: 365       }&#39;     &#x60;&#x60;&#x60;      ## Cas d&#39;usage      - Tests de signature PDF en développement     - POC de signature électronique     - Formation et démos     - Tests d&#39;intégration automatisés      ## Conformité technique      Certificat généré avec :     - Clé RSA 2048 ou 4096 bits     - Algorithme SHA-256     - Extensions Key Usage : &#x60;digitalSignature&#x60;, &#x60;contentCommitment&#x60; (non-repudiation)     - Extensions Extended Key Usage : &#x60;codeSigning&#x60;, &#x60;emailProtection&#x60;     - Validité : 1 jour à 10 ans (configurable)     - Format : PEM (certificat et clé)     - Optionnel : PKCS#12 (.p12)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.SignatureLectroniqueApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    SignatureLectroniqueApi apiInstance = new SignatureLectroniqueApi(defaultClient);
    GenerateCertificateRequest generateCertificateRequest = new GenerateCertificateRequest(); // GenerateCertificateRequest | 
    try {
      GenerateCertificateResponse result = apiInstance.genererCertificatTestApiV1TraitementGenererCertificatTestPost(generateCertificateRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SignatureLectroniqueApi#genererCertificatTestApiV1TraitementGenererCertificatTestPost");
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
| **generateCertificateRequest** | [**GenerateCertificateRequest**](GenerateCertificateRequest.md)|  | |

### Return type

[**GenerateCertificateResponse**](GenerateCertificateResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Certificat généré avec succès |  -  |
| **400** | Requête invalide (paramètres incorrects) |  -  |
| **500** | Erreur serveur lors de la génération |  -  |
| **422** | Validation Error |  -  |

<a id="signerPdfApiV1TraitementSignerPdfPost"></a>
# **signerPdfApiV1TraitementSignerPdfPost**
> Object signerPdfApiV1TraitementSignerPdfPost(fichierPdf, raison, localisation, contact, fieldName, usePadesLt, useTimestamp)

Signer un PDF avec le certificat du client (PAdES-B-LT)

Signe un PDF uploadé avec le certificat électronique configuré pour le client (via client_uid du JWT).      **Standards supportés** : PAdES-B-B, PAdES-B-T (horodatage), PAdES-B-LT (archivage long terme).      **Niveaux eIDAS** : SES (auto-signé), AdES (CA commerciale), QES (PSCO - hors scope).      **⚠️ Disclaimer légal** : Les signatures générées sont des cachets électroniques au sens     du règlement eIDAS. Le niveau de validité juridique dépend du certificat utilisé (SES/AdES/QES).     FactPulse ne fournit pas de certificats qualifiés QES - vous devez obtenir un certificat auprès     d&#39;un PSCO (Prestataire de Services de Confiance qualifié) pour une validité juridique maximale.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.SignatureLectroniqueApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    SignatureLectroniqueApi apiInstance = new SignatureLectroniqueApi(defaultClient);
    File fichierPdf = new File("/path/to/file"); // File | Fichier PDF à signer (sera traité puis retourné signé en base64)
    String raison = "raison_example"; // String | 
    String localisation = "localisation_example"; // String | 
    String contact = "contact_example"; // String | 
    String fieldName = "FactPulseSignature"; // String | Nom du champ de signature PDF
    Boolean usePadesLt = false; // Boolean | Activer PAdES-B-LT (archivage long terme avec données de validation embarquées). NÉCESSITE un certificat avec accès OCSP/CRL.
    Boolean useTimestamp = true; // Boolean | Activer l'horodatage RFC 3161 avec FreeTSA (PAdES-B-T)
    try {
      Object result = apiInstance.signerPdfApiV1TraitementSignerPdfPost(fichierPdf, raison, localisation, contact, fieldName, usePadesLt, useTimestamp);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SignatureLectroniqueApi#signerPdfApiV1TraitementSignerPdfPost");
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
| **fichierPdf** | **File**| Fichier PDF à signer (sera traité puis retourné signé en base64) | |
| **raison** | **String**|  | [optional] |
| **localisation** | **String**|  | [optional] |
| **contact** | **String**|  | [optional] |
| **fieldName** | **String**| Nom du champ de signature PDF | [optional] [default to FactPulseSignature] |
| **usePadesLt** | **Boolean**| Activer PAdES-B-LT (archivage long terme avec données de validation embarquées). NÉCESSITE un certificat avec accès OCSP/CRL. | [optional] [default to false] |
| **useTimestamp** | **Boolean**| Activer l&#39;horodatage RFC 3161 avec FreeTSA (PAdES-B-T) | [optional] [default to true] |

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
| **200** | PDF signé avec succès |  -  |
| **400** | Certificat invalide ou expiré |  -  |
| **404** | Aucun certificat configuré pour ce client |  -  |
| **401** | Non authentifié - Token JWT manquant ou invalide |  -  |
| **503** | Service Django inaccessible |  -  |
| **422** | Validation Error |  -  |

<a id="signerPdfAsyncApiV1TraitementSignerPdfAsyncPost"></a>
# **signerPdfAsyncApiV1TraitementSignerPdfAsyncPost**
> Object signerPdfAsyncApiV1TraitementSignerPdfAsyncPost(fichierPdf, raison, localisation, contact, fieldName, usePadesLt, useTimestamp)

Signer un PDF de manière asynchrone (Celery)

Signe un PDF uploadé de manière asynchrone via une tâche Celery.      **Différence avec /signer-pdf** :     - &#x60;/signer-pdf&#x60; : Signature synchrone (blocage jusqu&#39;à la fin)     - &#x60;/signer-pdf-async&#x60; : Signature asynchrone (retourne immédiatement un task_id)      **Avantages de l&#39;async** :     - Pas de timeout pour les gros fichiers     - Pas de blocage du worker FastAPI     - Possibilité de suivre la progression via le task_id     - Idéal pour les traitements par lot      **Standards supportés** : PAdES-B-B, PAdES-B-T (horodatage), PAdES-B-LT (archivage long terme).      **⚠️ Disclaimer légal** : Identique à /signer-pdf (voir documentation de cet endpoint).

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.SignatureLectroniqueApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    SignatureLectroniqueApi apiInstance = new SignatureLectroniqueApi(defaultClient);
    File fichierPdf = new File("/path/to/file"); // File | Fichier PDF à signer (traité de manière asynchrone)
    String raison = "raison_example"; // String | 
    String localisation = "localisation_example"; // String | 
    String contact = "contact_example"; // String | 
    String fieldName = "FactPulseSignature"; // String | Nom du champ de signature PDF
    Boolean usePadesLt = false; // Boolean | Activer PAdES-B-LT (archivage long terme avec données de validation embarquées). NÉCESSITE un certificat avec accès OCSP/CRL.
    Boolean useTimestamp = true; // Boolean | Activer l'horodatage RFC 3161 avec FreeTSA (PAdES-B-T)
    try {
      Object result = apiInstance.signerPdfAsyncApiV1TraitementSignerPdfAsyncPost(fichierPdf, raison, localisation, contact, fieldName, usePadesLt, useTimestamp);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SignatureLectroniqueApi#signerPdfAsyncApiV1TraitementSignerPdfAsyncPost");
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
| **fichierPdf** | **File**| Fichier PDF à signer (traité de manière asynchrone) | |
| **raison** | **String**|  | [optional] |
| **localisation** | **String**|  | [optional] |
| **contact** | **String**|  | [optional] |
| **fieldName** | **String**| Nom du champ de signature PDF | [optional] [default to FactPulseSignature] |
| **usePadesLt** | **Boolean**| Activer PAdES-B-LT (archivage long terme avec données de validation embarquées). NÉCESSITE un certificat avec accès OCSP/CRL. | [optional] [default to false] |
| **useTimestamp** | **Boolean**| Activer l&#39;horodatage RFC 3161 avec FreeTSA (PAdES-B-T) | [optional] [default to true] |

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
| **202** | Tâche de signature créée avec succès |  -  |
| **400** | Paramètres invalides |  -  |
| **401** | Non authentifié - Token JWT manquant ou invalide |  -  |
| **422** | Validation Error |  -  |

<a id="validerSignaturePdfEndpointApiV1TraitementValiderSignaturePdfPost"></a>
# **validerSignaturePdfEndpointApiV1TraitementValiderSignaturePdfPost**
> Object validerSignaturePdfEndpointApiV1TraitementValiderSignaturePdfPost(fichierPdf)

Valider les signatures électroniques d&#39;un PDF

Valide les signatures électroniques présentes dans un PDF uploadé.      **Vérifications effectuées** :     - Présence de signatures     - Intégrité du document (non modifié depuis signature)     - Validité des certificats     - Chaîne de confiance (si disponible)     - Présence d&#39;horodatage (PAdES-B-T)     - Données de validation (PAdES-B-LT)      **Standards supportés** : PAdES-B-B, PAdES-B-T, PAdES-B-LT, ISO 32000-2.      **⚠️ Note** : Cette validation est technique (intégrité cryptographique). La validité juridique     dépend du niveau eIDAS du certificat (SES/AdES/QES) et du contexte d&#39;utilisation.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.SignatureLectroniqueApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    SignatureLectroniqueApi apiInstance = new SignatureLectroniqueApi(defaultClient);
    File fichierPdf = new File("/path/to/file"); // File | Fichier PDF à valider (sera analysé pour détecter et valider les signatures)
    try {
      Object result = apiInstance.validerSignaturePdfEndpointApiV1TraitementValiderSignaturePdfPost(fichierPdf);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SignatureLectroniqueApi#validerSignaturePdfEndpointApiV1TraitementValiderSignaturePdfPost");
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
| **fichierPdf** | **File**| Fichier PDF à valider (sera analysé pour détecter et valider les signatures) | |

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
| **200** | Validation réussie |  -  |
| **400** | Fichier invalide ou non lisible |  -  |
| **422** | Validation Error |  -  |

