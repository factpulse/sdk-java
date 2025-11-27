# ChorusProApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**ajouterFichierApiV1ChorusProTransversesAjouterFichierPost**](ChorusProApi.md#ajouterFichierApiV1ChorusProTransversesAjouterFichierPost) | **POST** /api/v1/chorus-pro/transverses/ajouter-fichier | Ajouter une pièce jointe |
| [**completerFactureApiV1ChorusProFacturesCompleterPost**](ChorusProApi.md#completerFactureApiV1ChorusProFacturesCompleterPost) | **POST** /api/v1/chorus-pro/factures/completer | Compléter une facture suspendue (Fournisseur) |
| [**consulterFactureApiV1ChorusProFacturesConsulterPost**](ChorusProApi.md#consulterFactureApiV1ChorusProFacturesConsulterPost) | **POST** /api/v1/chorus-pro/factures/consulter | Consulter le statut d&#39;une facture |
| [**consulterStructureApiV1ChorusProStructuresConsulterPost**](ChorusProApi.md#consulterStructureApiV1ChorusProStructuresConsulterPost) | **POST** /api/v1/chorus-pro/structures/consulter | Consulter les détails d&#39;une structure |
| [**listerServicesStructureApiV1ChorusProStructuresIdStructureCppServicesGet**](ChorusProApi.md#listerServicesStructureApiV1ChorusProStructuresIdStructureCppServicesGet) | **GET** /api/v1/chorus-pro/structures/{id_structure_cpp}/services | Lister les services d&#39;une structure |
| [**obtenirIdChorusProDepuisSiretApiV1ChorusProStructuresObtenirIdDepuisSiretPost**](ChorusProApi.md#obtenirIdChorusProDepuisSiretApiV1ChorusProStructuresObtenirIdDepuisSiretPost) | **POST** /api/v1/chorus-pro/structures/obtenir-id-depuis-siret | Utilitaire : Obtenir l&#39;ID Chorus Pro depuis un SIRET |
| [**rechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost**](ChorusProApi.md#rechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost) | **POST** /api/v1/chorus-pro/factures/rechercher-destinataire | Rechercher factures reçues (Destinataire) |
| [**rechercherFacturesFournisseurApiV1ChorusProFacturesRechercherFournisseurPost**](ChorusProApi.md#rechercherFacturesFournisseurApiV1ChorusProFacturesRechercherFournisseurPost) | **POST** /api/v1/chorus-pro/factures/rechercher-fournisseur | Rechercher factures émises (Fournisseur) |
| [**rechercherStructuresApiV1ChorusProStructuresRechercherPost**](ChorusProApi.md#rechercherStructuresApiV1ChorusProStructuresRechercherPost) | **POST** /api/v1/chorus-pro/structures/rechercher | Rechercher des structures Chorus Pro |
| [**recyclerFactureApiV1ChorusProFacturesRecyclerPost**](ChorusProApi.md#recyclerFactureApiV1ChorusProFacturesRecyclerPost) | **POST** /api/v1/chorus-pro/factures/recycler | Recycler une facture (Fournisseur) |
| [**soumettreFactureApiV1ChorusProFacturesSoumettrePost**](ChorusProApi.md#soumettreFactureApiV1ChorusProFacturesSoumettrePost) | **POST** /api/v1/chorus-pro/factures/soumettre | Soumettre une facture à Chorus Pro |
| [**telechargerGroupeFacturesApiV1ChorusProFacturesTelechargerGroupePost**](ChorusProApi.md#telechargerGroupeFacturesApiV1ChorusProFacturesTelechargerGroupePost) | **POST** /api/v1/chorus-pro/factures/telecharger-groupe | Télécharger un groupe de factures |
| [**traiterFactureRecueApiV1ChorusProFacturesTraiterFactureRecuePost**](ChorusProApi.md#traiterFactureRecueApiV1ChorusProFacturesTraiterFactureRecuePost) | **POST** /api/v1/chorus-pro/factures/traiter-facture-recue | Traiter une facture reçue (Destinataire) |
| [**valideurConsulterFactureApiV1ChorusProFacturesValideurConsulterPost**](ChorusProApi.md#valideurConsulterFactureApiV1ChorusProFacturesValideurConsulterPost) | **POST** /api/v1/chorus-pro/factures/valideur/consulter | Consulter une facture (Valideur) |
| [**valideurRechercherFacturesApiV1ChorusProFacturesValideurRechercherPost**](ChorusProApi.md#valideurRechercherFacturesApiV1ChorusProFacturesValideurRechercherPost) | **POST** /api/v1/chorus-pro/factures/valideur/rechercher | Rechercher factures à valider (Valideur) |
| [**valideurTraiterFactureApiV1ChorusProFacturesValideurTraiterPost**](ChorusProApi.md#valideurTraiterFactureApiV1ChorusProFacturesValideurTraiterPost) | **POST** /api/v1/chorus-pro/factures/valideur/traiter | Valider ou refuser une facture (Valideur) |


<a id="ajouterFichierApiV1ChorusProTransversesAjouterFichierPost"></a>
# **ajouterFichierApiV1ChorusProTransversesAjouterFichierPost**
> Object ajouterFichierApiV1ChorusProTransversesAjouterFichierPost(requestBody)

Ajouter une pièce jointe

Ajoute une pièce jointe au compte utilisateur courant.      **Taille max** : 10 Mo par fichier      **Payload exemple** :     &#x60;&#x60;&#x60;json     {       \&quot;pieceJointeFichier\&quot;: \&quot;JVBERi0xLjQKJeLjz9MKNSAwIG9iago8P...\&quot;,       \&quot;pieceJointeNom\&quot;: \&quot;bon_commande.pdf\&quot;,       \&quot;pieceJointeTypeMime\&quot;: \&quot;application/pdf\&quot;,       \&quot;pieceJointeExtension\&quot;: \&quot;PDF\&quot;     }     &#x60;&#x60;&#x60;      **Retour** : L&#39;ID de la pièce jointe (&#x60;pieceJointeIdFichier&#x60;) à utiliser ensuite dans &#x60;/factures/completer&#x60;.      **Extensions acceptées** : PDF, JPG, PNG, ZIP, XML, etc.

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

Compléter une facture suspendue (Fournisseur)

Complète une facture au statut SUSPENDUE en ajoutant des pièces jointes ou un commentaire.      **Statut requis** : SUSPENDUE      **Actions possibles** :     - Ajouter des pièces jointes (justificatifs, bons de commande, etc.)     - Modifier le commentaire      **Payload exemple** :     &#x60;&#x60;&#x60;json     {       \&quot;identifiantFactureCPP\&quot;: 12345,       \&quot;commentaire\&quot;: \&quot;Voici les justificatifs demandés\&quot;,       \&quot;listePiecesJointes\&quot;: [         {           \&quot;pieceJointeIdFichier\&quot;: 98765,           \&quot;pieceJointeNom\&quot;: \&quot;bon_commande.pdf\&quot;         }       ]     }     &#x60;&#x60;&#x60;      **Note** : Les pièces jointes doivent d&#39;abord être uploadées via &#x60;/transverses/ajouter-fichier&#x60;.      **Après complétion** : La facture repasse au statut MISE_A_DISPOSITION.

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
> ConsulterFactureResponse consulterFactureApiV1ChorusProFacturesConsulterPost(consulterFactureRequest)

Consulter le statut d&#39;une facture

Récupère les informations et le statut actuel d&#39;une facture soumise à Chorus Pro.      **Retour** :     - Numéro et date de facture     - Montant TTC     - **Statut courant** : SOUMISE, VALIDEE, REJETEE, SUSPENDUE, MANDATEE, MISE_EN_PAIEMENT, etc.     - Structure destinataire      **Cas d&#39;usage** :     - Suivre l&#39;évolution du traitement d&#39;une facture     - Vérifier si une facture a été validée ou rejetée     - Obtenir la date de mise en paiement      **Polling** : Appelez cet endpoint régulièrement pour suivre l&#39;évolution du statut.

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
    ConsulterFactureRequest consulterFactureRequest = new ConsulterFactureRequest(); // ConsulterFactureRequest | 
    try {
      ConsulterFactureResponse result = apiInstance.consulterFactureApiV1ChorusProFacturesConsulterPost(consulterFactureRequest);
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
| **consulterFactureRequest** | [**ConsulterFactureRequest**](ConsulterFactureRequest.md)|  | |

### Return type

[**ConsulterFactureResponse**](ConsulterFactureResponse.md)

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
> ConsulterStructureResponse consulterStructureApiV1ChorusProStructuresConsulterPost(consulterStructureRequest)

Consulter les détails d&#39;une structure

Récupère les informations détaillées d&#39;une structure Chorus Pro.       **Retour** :     - Raison sociale     - Numéro de TVA intracommunautaire     - Email de contact     - **Paramètres obligatoires** : Indique si le code service et/ou numéro d&#39;engagement sont requis pour soumettre une facture      **Étape typique** : Appelée après &#x60;rechercher-structures&#x60; pour savoir quels champs sont obligatoires avant de soumettre une facture.

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
    ConsulterStructureRequest consulterStructureRequest = new ConsulterStructureRequest(); // ConsulterStructureRequest | 
    try {
      ConsulterStructureResponse result = apiInstance.consulterStructureApiV1ChorusProStructuresConsulterPost(consulterStructureRequest);
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
| **consulterStructureRequest** | [**ConsulterStructureRequest**](ConsulterStructureRequest.md)|  | |

### Return type

[**ConsulterStructureResponse**](ConsulterStructureResponse.md)

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
> RechercherServicesResponse listerServicesStructureApiV1ChorusProStructuresIdStructureCppServicesGet(idStructureCpp)

Lister les services d&#39;une structure

Récupère la liste des services actifs d&#39;une structure publique.      **Cas d&#39;usage** :     - Lister les services disponibles pour une administration     - Vérifier qu&#39;un code service existe avant de soumettre une facture      **Retour** :     - Liste des services avec leur code, libellé et statut (actif/inactif)

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
      RechercherServicesResponse result = apiInstance.listerServicesStructureApiV1ChorusProStructuresIdStructureCppServicesGet(idStructureCpp);
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

[**RechercherServicesResponse**](RechercherServicesResponse.md)

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
> ObtenirIdChorusProResponse obtenirIdChorusProDepuisSiretApiV1ChorusProStructuresObtenirIdDepuisSiretPost(obtenirIdChorusProRequest)

Utilitaire : Obtenir l&#39;ID Chorus Pro depuis un SIRET

**Utilitaire pratique** pour obtenir l&#39;ID Chorus Pro d&#39;une structure à partir de son SIRET.       Cette fonction wrapper combine :     1. Recherche de la structure par SIRET     2. Extraction de l&#39;&#x60;id_structure_cpp&#x60; si une seule structure est trouvée      **Retour** :     - &#x60;id_structure_cpp&#x60; : ID Chorus Pro (0 si non trouvé ou si plusieurs résultats)     - &#x60;designation_structure&#x60; : Nom de la structure (si trouvée)     - &#x60;message&#x60; : Message explicatif      **Cas d&#39;usage** :     - Raccourci pour obtenir directement l&#39;ID Chorus Pro avant de soumettre une facture     - Alternative simplifiée à &#x60;rechercher-structures&#x60; + extraction manuelle de l&#39;ID      **Note** : Si plusieurs structures correspondent au SIRET (rare), retourne 0 et un message d&#39;erreur.

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
    ObtenirIdChorusProRequest obtenirIdChorusProRequest = new ObtenirIdChorusProRequest(); // ObtenirIdChorusProRequest | 
    try {
      ObtenirIdChorusProResponse result = apiInstance.obtenirIdChorusProDepuisSiretApiV1ChorusProStructuresObtenirIdDepuisSiretPost(obtenirIdChorusProRequest);
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
| **obtenirIdChorusProRequest** | [**ObtenirIdChorusProRequest**](ObtenirIdChorusProRequest.md)|  | |

### Return type

[**ObtenirIdChorusProResponse**](ObtenirIdChorusProResponse.md)

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

Rechercher factures reçues (Destinataire)

Recherche les factures reçues par le destinataire connecté.      **Filtres** :     - Téléchargée / non téléchargée     - Dates de réception     - Statut (MISE_A_DISPOSITION, SUSPENDUE, etc.)     - Fournisseur      **Indicateur utile** : &#x60;factureTelechargeeParDestinataire&#x60; permet de savoir si la facture a déjà été téléchargée.

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

Rechercher factures émises (Fournisseur)

Recherche les factures émises par le fournisseur connecté.      **Filtres disponibles** :     - Numéro de facture     - Dates (début/fin)     - Statut     - Structure destinataire     - Montant      **Cas d&#39;usage** :     - Suivi des factures émises     - Vérification des statuts     - Export pour comptabilité

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
> RechercherStructureResponse rechercherStructuresApiV1ChorusProStructuresRechercherPost(rechercherStructureRequest)

Rechercher des structures Chorus Pro

Recherche des structures (entreprises, administrations) enregistrées sur Chorus Pro.      **Cas d&#39;usage** :     - Trouver l&#39;ID Chorus Pro d&#39;une structure à partir de son SIRET     - Vérifier si une structure est enregistrée sur Chorus Pro     - Lister les structures correspondant à des critères      **Filtres disponibles** :     - Identifiant (SIRET, SIREN, etc.)     - Raison sociale     - Type d&#39;identifiant     - Structures privées uniquement      **Étape typique** : Appelée avant &#x60;soumettre-facture&#x60; pour obtenir l&#39;&#x60;id_structure_cpp&#x60; du destinataire.

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
    RechercherStructureRequest rechercherStructureRequest = new RechercherStructureRequest(); // RechercherStructureRequest | 
    try {
      RechercherStructureResponse result = apiInstance.rechercherStructuresApiV1ChorusProStructuresRechercherPost(rechercherStructureRequest);
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
| **rechercherStructureRequest** | [**RechercherStructureRequest**](RechercherStructureRequest.md)|  | |

### Return type

[**RechercherStructureResponse**](RechercherStructureResponse.md)

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

Recycler une facture (Fournisseur)

Recycle une facture au statut A_RECYCLER en modifiant les données d&#39;acheminement.      **Statut requis** : A_RECYCLER      **Champs modifiables** :     - Destinataire (&#x60;idStructureCPP&#x60;)     - Code service     - Numéro d&#39;engagement      **Cas d&#39;usage** :     - Erreur de destinataire     - Changement de service facturation     - Mise à jour du numéro d&#39;engagement      **Payload exemple** :     &#x60;&#x60;&#x60;json     {       \&quot;identifiantFactureCPP\&quot;: 12345,       \&quot;idStructureCPP\&quot;: 67890,       \&quot;codeService\&quot;: \&quot;SERVICE_01\&quot;,       \&quot;numeroEngagement\&quot;: \&quot;ENG2024001\&quot;     }     &#x60;&#x60;&#x60;      **Note** : La facture conserve son numéro et ses montants, seuls les champs d&#39;acheminement changent.

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
> SoumettreFactureResponse soumettreFactureApiV1ChorusProFacturesSoumettrePost(soumettreFactureRequest)

Soumettre une facture à Chorus Pro

Soumet une facture électronique à une structure publique via Chorus Pro.       **📋 Workflow complet** :     1. **Uploader le PDF Factur-X** via &#x60;/transverses/ajouter-fichier&#x60; → récupérer &#x60;pieceJointeId&#x60;     2. **Obtenir l&#39;ID structure** via &#x60;/structures/rechercher&#x60; ou &#x60;/structures/obtenir-id-depuis-siret&#x60;     3. **Vérifier les paramètres obligatoires** via &#x60;/structures/consulter&#x60;     4. **Soumettre la facture** avec le &#x60;piece_jointe_principale_id&#x60; obtenu à l&#39;étape 1      **Pré-requis** :     1. Avoir l&#39;&#x60;id_structure_cpp&#x60; du destinataire (via &#x60;/structures/rechercher&#x60;)     2. Connaître les paramètres obligatoires (via &#x60;/structures/consulter&#x60;) :        - Code service si &#x60;code_service_doit_etre_renseigne&#x3D;true&#x60;        - Numéro d&#39;engagement si &#x60;numero_ej_doit_etre_renseigne&#x3D;true&#x60;     3. Avoir uploadé le PDF Factur-X (via &#x60;/transverses/ajouter-fichier&#x60;)      **Format attendu** :     - &#x60;piece_jointe_principale_id&#x60; : ID retourné par &#x60;/transverses/ajouter-fichier&#x60;     - Montants : Chaînes de caractères avec 2 décimales (ex: \&quot;1250.50\&quot;)     - Dates : Format ISO 8601 (YYYY-MM-DD)      **Retour** :     - &#x60;identifiant_facture_cpp&#x60; : ID Chorus Pro de la facture créée     - &#x60;numero_flux_depot&#x60; : Numéro de suivi du dépôt      **Statuts possibles après soumission** :     - SOUMISE : En attente de validation     - VALIDEE : Validée par le destinataire     - REJETEE : Rejetée (erreur de données ou refus métier)     - SUSPENDUE : En attente d&#39;informations complémentaires      **Note** : Utilisez &#x60;/factures/consulter&#x60; pour suivre l&#39;évolution du statut.

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
    SoumettreFactureRequest soumettreFactureRequest = new SoumettreFactureRequest(); // SoumettreFactureRequest | 
    try {
      SoumettreFactureResponse result = apiInstance.soumettreFactureApiV1ChorusProFacturesSoumettrePost(soumettreFactureRequest);
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
| **soumettreFactureRequest** | [**SoumettreFactureRequest**](SoumettreFactureRequest.md)|  | |

### Return type

[**SoumettreFactureResponse**](SoumettreFactureResponse.md)

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

Télécharger un groupe de factures

Télécharge une ou plusieurs factures (max 10 recommandé) avec leurs pièces jointes.      **Formats disponibles** :     - PDF : Fichier PDF uniquement     - XML : Fichier XML uniquement     - ZIP : Archive contenant PDF + XML + pièces jointes      **Taille maximale** : 120 Mo par téléchargement      **Payload exemple** :     &#x60;&#x60;&#x60;json     {       \&quot;listeIdentifiantsFactureCPP\&quot;: [12345, 12346],       \&quot;inclurePiecesJointes\&quot;: true,       \&quot;formatFichier\&quot;: \&quot;ZIP\&quot;     }     &#x60;&#x60;&#x60;      **Retour** : Le fichier est encodé en base64 dans le champ &#x60;fichierBase64&#x60;.      **Note** : Le flag &#x60;factureTelechargeeParDestinataire&#x60; est mis à jour automatiquement.

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

Traiter une facture reçue (Destinataire)

Change le statut d&#39;une facture reçue.      **Statuts possibles** :     - MISE_A_DISPOSITION : Facture acceptée     - SUSPENDUE : En attente d&#39;informations complémentaires (motif obligatoire)     - REJETEE : Facture refusée (motif obligatoire)     - MANDATEE : Facture mandatée     - MISE_EN_PAIEMENT : Facture en cours de paiement     - COMPTABILISEE : Facture comptabilisée     - MISE_A_DISPOSITION_COMPTABLE : Mise à disposition comptable     - A_RECYCLER : À recycler     - COMPLETEE : Complétée     - SERVICE-FAIT : Service fait     - PRISE_EN_COMPTE_DESTINATAIRE : Prise en compte     - TRANSMISE_MOA : Transmise à la MOA      **Payload exemple** :     &#x60;&#x60;&#x60;json     {       \&quot;identifiantFactureCPP\&quot;: 12345,       \&quot;nouveauStatut\&quot;: \&quot;REJETEE\&quot;,       \&quot;motifRejet\&quot;: \&quot;Facture en double\&quot;,       \&quot;commentaire\&quot;: \&quot;Facture déjà reçue sous la référence ABC123\&quot;     }     &#x60;&#x60;&#x60;      **Règles** :     - Un motif est **obligatoire** pour SUSPENDUE et REJETEE     - Seuls certains statuts sont autorisés selon le statut actuel de la facture

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

Consulter une facture (Valideur)

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

Rechercher factures à valider (Valideur)

Recherche les factures en attente de validation par le valideur connecté.      **Rôle** : Valideur dans le circuit de validation interne.      **Filtres** : Dates, structure, service, etc.

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

Valider ou refuser une facture (Valideur)

Valide ou refuse une facture en attente de validation.      **Actions** :     - Valider : La facture passe au statut suivant du circuit     - Refuser : La facture est rejetée (motif obligatoire)

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

