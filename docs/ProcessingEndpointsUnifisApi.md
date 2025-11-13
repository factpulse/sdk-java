# ProcessingEndpointsUnifisApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**soumettreFactureCompleteApiV1TraitementFacturesSoumettreCompletePost**](ProcessingEndpointsUnifisApi.md#soumettreFactureCompleteApiV1TraitementFacturesSoumettreCompletePost) | **POST** /api/v1/traitement/factures/soumettre-complete | Soumettre une facture complète (génération + signature + soumission) |
| [**soumettreFactureCompleteAsyncApiV1TraitementFacturesSoumettreCompleteAsyncPost**](ProcessingEndpointsUnifisApi.md#soumettreFactureCompleteAsyncApiV1TraitementFacturesSoumettreCompleteAsyncPost) | **POST** /api/v1/traitement/factures/soumettre-complete-async | Soumettre une facture complète (asynchrone avec Celery) |


<a id="soumettreFactureCompleteApiV1TraitementFacturesSoumettreCompletePost"></a>
# **soumettreFactureCompleteApiV1TraitementFacturesSoumettreCompletePost**
> SoumettreFactureCompleteResponse soumettreFactureCompleteApiV1TraitementFacturesSoumettreCompletePost(soumettreFactureCompleteRequest)

Soumettre une facture complète (génération + signature + soumission)

Endpoint unifié pour soumettre une facture complète vers différentes destinations.      **Workflow automatisé :**     1. **Auto-enrichissement** (optionnel) : récupère les données via APIs publiques et Chorus Pro/AFNOR     2. **Génération PDF Factur-X** : crée un PDF/A-3 avec XML embarqué     3. **Signature électronique** (optionnelle) : signe le PDF avec un certificat     4. **Soumission** : envoie vers la destination choisie (Chorus Pro ou AFNOR PDP)      **Destinations supportées :**     - **Chorus Pro** : plateforme B2G française (factures vers secteur public)     - **AFNOR PDP** : Plateformes de Dématérialisation Partenaires      **Credentials de destination - 2 modes disponibles :**      **Mode 1 - Récupération via JWT (recommandé) :**     - Les credentials sont récupérés automatiquement via le &#x60;client_uid&#x60; du JWT     - Ne pas fournir le champ &#x60;credentials&#x60; dans &#x60;destination&#x60;     - Architecture 0-trust : aucun secret dans le payload     - Exemple : &#x60;\&quot;destination\&quot;: {\&quot;type\&quot;: \&quot;chorus_pro\&quot;}&#x60;      **Mode 2 - Credentials dans le payload :**     - Fournir les credentials directement dans le payload     - Utile pour tests ou intégrations tierces     - Exemple : &#x60;\&quot;destination\&quot;: {\&quot;type\&quot;: \&quot;chorus_pro\&quot;, \&quot;credentials\&quot;: {...}}&#x60;       **Signature électronique (optionnelle) - 2 modes disponibles :**      **Mode 1 - Certificat stocké (recommandé) :**     - Le certificat est récupéré automatiquement via le &#x60;client_uid&#x60; du JWT     - Aucune clé à fournir dans le payload     - Signature PAdES-B-LT avec horodatage (conforme eIDAS)     - Exemple : &#x60;\&quot;signature\&quot;: {\&quot;raison\&quot;: \&quot;Conformité Factur-X\&quot;}&#x60;      **Mode 2 - Clés dans le payload (pour tests) :**     - Fournir &#x60;key_pem&#x60; et &#x60;cert_pem&#x60; directement     - Format PEM accepté : brut ou base64     - Utile pour tests ou cas spéciaux sans certificat stocké     - Exemple : &#x60;\&quot;signature\&quot;: {\&quot;key_pem\&quot;: \&quot;-----BEGIN...\&quot;, \&quot;cert_pem\&quot;: \&quot;-----BEGIN...\&quot;}&#x60;      Si &#x60;key_pem&#x60; et &#x60;cert_pem&#x60; sont fournis → Mode 2     Sinon → Mode 1 (certificat récupéré via &#x60;client_uid&#x60;)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ProcessingEndpointsUnifisApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ProcessingEndpointsUnifisApi apiInstance = new ProcessingEndpointsUnifisApi(defaultClient);
    SoumettreFactureCompleteRequest soumettreFactureCompleteRequest = new SoumettreFactureCompleteRequest(); // SoumettreFactureCompleteRequest | 
    try {
      SoumettreFactureCompleteResponse result = apiInstance.soumettreFactureCompleteApiV1TraitementFacturesSoumettreCompletePost(soumettreFactureCompleteRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessingEndpointsUnifisApi#soumettreFactureCompleteApiV1TraitementFacturesSoumettreCompletePost");
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
| **soumettreFactureCompleteRequest** | [**SoumettreFactureCompleteRequest**](SoumettreFactureCompleteRequest.md)|  | |

### Return type

[**SoumettreFactureCompleteResponse**](SoumettreFactureCompleteResponse.md)

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

<a id="soumettreFactureCompleteAsyncApiV1TraitementFacturesSoumettreCompleteAsyncPost"></a>
# **soumettreFactureCompleteAsyncApiV1TraitementFacturesSoumettreCompleteAsyncPost**
> ReponseTache soumettreFactureCompleteAsyncApiV1TraitementFacturesSoumettreCompleteAsyncPost(soumettreFactureCompleteRequest)

Soumettre une facture complète (asynchrone avec Celery)

Version asynchrone de l&#39;endpoint &#x60;/factures/soumettre-complete&#x60; utilisant Celery pour le traitement en arrière-plan.      **Workflow automatisé (identique à la version synchrone) :**     1. **Auto-enrichissement** (optionnel) : récupère les données via APIs publiques et Chorus Pro/AFNOR     2. **Génération PDF Factur-X** : crée un PDF/A-3 avec XML embarqué     3. **Signature électronique** (optionnelle) : signe le PDF avec un certificat     4. **Soumission** : envoie vers la destination choisie (Chorus Pro ou AFNOR PDP)      **Destinations supportées :**     - **Chorus Pro** : plateforme B2G française (factures vers secteur public)     - **AFNOR PDP** : Plateformes de Dématérialisation Partenaires      **Différences avec la version synchrone :**     - ✅ **Non-bloquant** : Retourne immédiatement un &#x60;id_tache&#x60; (HTTP 202 Accepted)     - ✅ **Traitement en arrière-plan** : La facture est traitée par un worker Celery     - ✅ **Suivi d&#39;avancement** : Utilisez &#x60;/taches/{id_tache}/statut&#x60; pour suivre le statut     - ✅ **Idéal pour gros volumes** : Permet de traiter de nombreuses factures en parallèle      **Comment utiliser :**     1. **Soumission** : Appelez cet endpoint avec vos données de facture     2. **Retour immédiat** : Vous recevez un &#x60;id_tache&#x60; (ex: \&quot;abc123-def456\&quot;)     3. **Suivi** : Appelez &#x60;/taches/{id_tache}/statut&#x60; pour vérifier l&#39;avancement     4. **Résultat** : Quand &#x60;statut &#x3D; \&quot;SUCCESS\&quot;&#x60;, le champ &#x60;resultat&#x60; contient la réponse complète      **Credentials et signature** : Mêmes modes que la version synchrone (JWT ou payload).

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ProcessingEndpointsUnifisApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ProcessingEndpointsUnifisApi apiInstance = new ProcessingEndpointsUnifisApi(defaultClient);
    SoumettreFactureCompleteRequest soumettreFactureCompleteRequest = new SoumettreFactureCompleteRequest(); // SoumettreFactureCompleteRequest | 
    try {
      ReponseTache result = apiInstance.soumettreFactureCompleteAsyncApiV1TraitementFacturesSoumettreCompleteAsyncPost(soumettreFactureCompleteRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessingEndpointsUnifisApi#soumettreFactureCompleteAsyncApiV1TraitementFacturesSoumettreCompleteAsyncPost");
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
| **soumettreFactureCompleteRequest** | [**SoumettreFactureCompleteRequest**](SoumettreFactureCompleteRequest.md)|  | |

### Return type

[**ReponseTache**](ReponseTache.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **202** | Successful Response |  -  |
| **422** | Validation Error |  -  |

