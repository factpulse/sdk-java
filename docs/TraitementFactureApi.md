# TraitementFactureApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**genererCertificatTestApiV1TraitementGenererCertificatTestPost**](TraitementFactureApi.md#genererCertificatTestApiV1TraitementGenererCertificatTestPost) | **POST** /api/v1/traitement/generer-certificat-test | Générer un certificat X.509 auto-signé de test |
| [**genererFactureApiV1TraitementGenererFacturePost**](TraitementFactureApi.md#genererFactureApiV1TraitementGenererFacturePost) | **POST** /api/v1/traitement/generer-facture | Générer une facture Factur-X |
| [**obtenirStatutTacheApiV1TraitementTachesIdTacheStatutGet**](TraitementFactureApi.md#obtenirStatutTacheApiV1TraitementTachesIdTacheStatutGet) | **GET** /api/v1/traitement/taches/{id_tache}/statut | Obtenir le statut d&#39;une tâche de génération |
| [**signerPdfApiV1TraitementSignerPdfPost**](TraitementFactureApi.md#signerPdfApiV1TraitementSignerPdfPost) | **POST** /api/v1/traitement/signer-pdf | Signer un PDF avec le certificat du client (PAdES-B-LT) |
| [**signerPdfAsyncApiV1TraitementSignerPdfAsyncPost**](TraitementFactureApi.md#signerPdfAsyncApiV1TraitementSignerPdfAsyncPost) | **POST** /api/v1/traitement/signer-pdf-async | Signer un PDF de manière asynchrone (Celery) |
| [**soumettreFactureCompleteApiV1TraitementFacturesSoumettreCompletePost**](TraitementFactureApi.md#soumettreFactureCompleteApiV1TraitementFacturesSoumettreCompletePost) | **POST** /api/v1/traitement/factures/soumettre-complete | Soumettre une facture complète (génération + signature + soumission) |
| [**soumettreFactureCompleteAsyncApiV1TraitementFacturesSoumettreCompleteAsyncPost**](TraitementFactureApi.md#soumettreFactureCompleteAsyncApiV1TraitementFacturesSoumettreCompleteAsyncPost) | **POST** /api/v1/traitement/factures/soumettre-complete-async | Soumettre une facture complète (asynchrone avec Celery) |
| [**validerPdfFacturxApiV1TraitementValiderPdfFacturxPost**](TraitementFactureApi.md#validerPdfFacturxApiV1TraitementValiderPdfFacturxPost) | **POST** /api/v1/traitement/valider-pdf-facturx | Valider un PDF Factur-X complet |
| [**validerPdfFacturxAsyncApiV1TraitementValiderFacturxAsyncPost**](TraitementFactureApi.md#validerPdfFacturxAsyncApiV1TraitementValiderFacturxAsyncPost) | **POST** /api/v1/traitement/valider-facturx-async | Valider un PDF Factur-X (asynchrone avec polling) |
| [**validerSignaturePdfEndpointApiV1TraitementValiderSignaturePdfPost**](TraitementFactureApi.md#validerSignaturePdfEndpointApiV1TraitementValiderSignaturePdfPost) | **POST** /api/v1/traitement/valider-signature-pdf | Valider les signatures électroniques d&#39;un PDF |
| [**validerXmlApiV1TraitementValiderXmlPost**](TraitementFactureApi.md#validerXmlApiV1TraitementValiderXmlPost) | **POST** /api/v1/traitement/valider-xml | Valider un XML Factur-X existant |


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
import org.openapitools.client.api.TraitementFactureApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    TraitementFactureApi apiInstance = new TraitementFactureApi(defaultClient);
    GenerateCertificateRequest generateCertificateRequest = new GenerateCertificateRequest(); // GenerateCertificateRequest | 
    try {
      GenerateCertificateResponse result = apiInstance.genererCertificatTestApiV1TraitementGenererCertificatTestPost(generateCertificateRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TraitementFactureApi#genererCertificatTestApiV1TraitementGenererCertificatTestPost");
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

<a id="genererFactureApiV1TraitementGenererFacturePost"></a>
# **genererFactureApiV1TraitementGenererFacturePost**
> ReponseTache genererFactureApiV1TraitementGenererFacturePost(donneesFacture, profil, formatSortie, autoEnrichir, sourcePdf)

Générer une facture Factur-X

Génère une facture électronique au format Factur-X conforme aux normes européennes.  ## Normes appliquées  - **Factur-X** (France) : Norme FNFE-MPE (Forum National de la Facture Électronique) - **ZUGFeRD** (Allemagne) : Format allemand compatible Factur-X - **EN 16931** : Norme sémantique européenne pour la facturation électronique - **ISO 19005-3** (PDF/A-3) : Archivage électronique à long terme - **Cross Industry Invoice (CII)** : Syntaxe XML UN/CEFACT  ## 🆕 Nouveau : Format simplifié avec auto-enrichissement (P0.1)  Vous pouvez désormais créer une facture en fournissant uniquement : - Un numéro de facture - Un SIRET émetteur + **IBAN** (obligatoire) - Un SIRET destinataire - Les lignes de facture (description, quantité, prix HT)  **Exemple format simplifié** : &#x60;&#x60;&#x60;json {   \&quot;numero\&quot;: \&quot;FACT-2025-001\&quot;,   \&quot;emetteur\&quot;: {     \&quot;siret\&quot;: \&quot;92019522900017\&quot;,     \&quot;iban\&quot;: \&quot;FR7630001007941234567890185\&quot;   },   \&quot;destinataire\&quot;: {\&quot;siret\&quot;: \&quot;35600000000048\&quot;},   \&quot;lignes\&quot;: [     {\&quot;description\&quot;: \&quot;Prestation\&quot;, \&quot;quantite\&quot;: 10, \&quot;prix_ht\&quot;: 100.00, \&quot;tva\&quot;: 20.0}   ] } &#x60;&#x60;&#x60;  **⚠️ Champs obligatoires (format simplifié)** : - &#x60;numero&#x60; : Numéro de facture unique - &#x60;emetteur.siret&#x60; : SIRET de l&#39;émetteur (14 chiffres) - &#x60;emetteur.iban&#x60; : IBAN du compte bancaire (pas d&#39;API publique pour le récupérer) - &#x60;destinataire.siret&#x60; : SIRET du destinataire - &#x60;lignes[]&#x60; : Au moins une ligne de facture  **Ce qui se passe automatiquement avec &#x60;auto_enrichir&#x3D;True&#x60;** : - ✅ Enrichissement des noms depuis API Chorus Pro - ✅ Enrichissement des adresses depuis API Recherche Entreprises (gratuite, publique) - ✅ Calcul automatique de la TVA intracommunautaire (FR + clé + SIREN) - ✅ Récupération de l&#39;ID Chorus Pro pour la facturation électronique - ✅ Calcul des totaux HT/TVA/TTC - ✅ Génération des dates (aujourd&#39;hui + échéance 30j) - ✅ Gestion multi-taux de TVA  **Identifiants supportés** : - SIRET (14 chiffres) : Établissement précis ⭐ Recommandé - SIREN (9 chiffres) : Entreprise (sélection auto du siège) - Types spéciaux : UE_HORS_FRANCE, RIDET, TAHITI, etc.  ## Contrôles effectués lors de la génération  ### 1. Validation des données (Pydantic) - Types de données (montants en Decimal, dates ISO 8601) - Formats (SIRET 14 chiffres, SIREN 9 chiffres, IBAN) - Champs obligatoires selon le profil - Cohérence des montants (HT + TVA &#x3D; TTC)  ### 2. Génération XML conforme CII - Sérialisation selon schéma XSD Cross Industry Invoice - Namespaces UN/CEFACT corrects - Structure hiérarchique respectée - Encodage UTF-8 sans BOM  ### 3. Validation Schematron - Règles métier du profil sélectionné (MINIMUM, BASIC, EN16931, EXTENDED) - Cardinalité des éléments (obligatoire, optionnel, répétable) - Règles de calcul (totaux, TVA, remises) - Conformité européenne EN 16931  ### 4. Conversion PDF/A-3 (si format_sortie&#x3D;&#39;pdf&#39;) - Conversion du PDF source en PDF/A-3 via Ghostscript - Embarquement du XML Factur-X dans le PDF - Métadonnées XMP conformes - Profil ICC sRGB pour les couleurs - Suppression des éléments interdits (JavaScript, formulaires)  ## Fonctionnement  1. **Soumission** : La facture est mise en file d&#39;attente Celery pour traitement asynchrone 2. **Retour immédiat** : Vous recevez un &#x60;id_tache&#x60; (HTTP 202 Accepted) 3. **Suivi** : Utilisez l&#39;endpoint &#x60;/taches/{id_tache}/statut&#x60; pour suivre l&#39;avancement  ## Formats de sortie  - **xml** : Génère uniquement le XML Factur-X (recommandé pour les tests) - **pdf** : Génère un PDF/A-3 avec XML embarqué (nécessite &#x60;source_pdf&#x60;)  ## Profils Factur-X  - **MINIMUM** : Données minimales (facture simplifiée) - **BASIC** : Informations de base (PME) - **EN16931** : Standard européen (recommandé, conforme directive 2014/55/UE) - **EXTENDED** : Toutes les données disponibles (grands comptes)  ## Ce que vous obtenez  Après traitement réussi (statut &#x60;completed&#x60;) : - **XML seul** : Fichier XML encodé base64 conforme Factur-X - **PDF/A-3** : PDF avec XML embarqué, prêt pour envoi/archivage - **Métadonnées** : Profil, version Factur-X, taille fichier - **Validation** : Confirmation de conformité Schematron  ## Validation  Les données sont validées automatiquement selon le format détecté. En cas d&#39;erreur, un statut 422 est retourné avec les détails des champs invalides.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TraitementFactureApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    TraitementFactureApi apiInstance = new TraitementFactureApi(defaultClient);
    String donneesFacture = "donneesFacture_example"; // String | Données de la facture au format JSON.              Deux formats acceptés :             1. **Format classique** : Structure complète FactureFacturX (tous les champs)             2. **Format simplifié** (🆕 P0.1) : Structure minimale avec auto-enrichissement              Le format est détecté automatiquement !             
    ProfilAPI profil = ProfilAPI.fromValue("MINIMUM"); // ProfilAPI | Profil Factur-X : MINIMUM, BASIC, EN16931 ou EXTENDED.
    FormatSortie formatSortie = FormatSortie.fromValue("xml"); // FormatSortie | Format de sortie : 'xml' (XML seul) ou 'pdf' (PDF Factur-X avec XML embarqué).
    Boolean autoEnrichir = true; // Boolean | 🆕 Activer l'auto-enrichissement depuis SIRET/SIREN (format simplifié uniquement)
    File sourcePdf = new File("/path/to/file"); // File | 
    try {
      ReponseTache result = apiInstance.genererFactureApiV1TraitementGenererFacturePost(donneesFacture, profil, formatSortie, autoEnrichir, sourcePdf);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TraitementFactureApi#genererFactureApiV1TraitementGenererFacturePost");
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
| **donneesFacture** | **String**| Données de la facture au format JSON.              Deux formats acceptés :             1. **Format classique** : Structure complète FactureFacturX (tous les champs)             2. **Format simplifié** (🆕 P0.1) : Structure minimale avec auto-enrichissement              Le format est détecté automatiquement !              | |
| **profil** | [**ProfilAPI**](ProfilAPI.md)| Profil Factur-X : MINIMUM, BASIC, EN16931 ou EXTENDED. | [optional] [enum: MINIMUM, BASIC, EN16931, EXTENDED] |
| **formatSortie** | [**FormatSortie**](FormatSortie.md)| Format de sortie : &#39;xml&#39; (XML seul) ou &#39;pdf&#39; (PDF Factur-X avec XML embarqué). | [optional] [enum: xml, pdf] |
| **autoEnrichir** | **Boolean**| 🆕 Activer l&#39;auto-enrichissement depuis SIRET/SIREN (format simplifié uniquement) | [optional] [default to true] |
| **sourcePdf** | **File**|  | [optional] |

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
| **400** | Données de facture invalides ou fichier PDF manquant |  -  |
| **422** | Erreur de validation des données de la facture |  -  |

<a id="obtenirStatutTacheApiV1TraitementTachesIdTacheStatutGet"></a>
# **obtenirStatutTacheApiV1TraitementTachesIdTacheStatutGet**
> StatutTache obtenirStatutTacheApiV1TraitementTachesIdTacheStatutGet(idTache)

Obtenir le statut d&#39;une tâche de génération

Récupère l&#39;état d&#39;avancement d&#39;une tâche de génération de facture.  ## États possibles  - **PENDING** : Tâche en attente de traitement - **STARTED** : Tâche en cours d&#39;exécution - **SUCCESS** : Tâche terminée (vérifier &#x60;resultat.statut&#x60; pour le résultat réel) - **FAILURE** : Erreur système lors de l&#39;exécution - **RETRY** : Tentative de ré-exécution en cours  ## Champ resultat  Quand la tâche est terminée (SUCCESS), le champ &#x60;resultat&#x60; contient : - &#x60;statut&#x60; : \&quot;SUCCES\&quot; ou \&quot;ERREUR\&quot; - &#x60;chemin_fichier&#x60; : Chemin du fichier généré (si succès) - &#x60;message_erreur&#x60; : Détails de l&#39;erreur (si échec)  ## Usage  Appelez cet endpoint en boucle (polling) toutes les 2-3 secondes jusqu&#39;à ce que le statut soit SUCCESS ou FAILURE.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TraitementFactureApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    TraitementFactureApi apiInstance = new TraitementFactureApi(defaultClient);
    String idTache = "idTache_example"; // String | 
    try {
      StatutTache result = apiInstance.obtenirStatutTacheApiV1TraitementTachesIdTacheStatutGet(idTache);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TraitementFactureApi#obtenirStatutTacheApiV1TraitementTachesIdTacheStatutGet");
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
| **200** | État actuel de la tâche |  -  |
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
import org.openapitools.client.api.TraitementFactureApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    TraitementFactureApi apiInstance = new TraitementFactureApi(defaultClient);
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
      System.err.println("Exception when calling TraitementFactureApi#signerPdfApiV1TraitementSignerPdfPost");
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
import org.openapitools.client.api.TraitementFactureApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    TraitementFactureApi apiInstance = new TraitementFactureApi(defaultClient);
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
      System.err.println("Exception when calling TraitementFactureApi#signerPdfAsyncApiV1TraitementSignerPdfAsyncPost");
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
import org.openapitools.client.api.TraitementFactureApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    TraitementFactureApi apiInstance = new TraitementFactureApi(defaultClient);
    SoumettreFactureCompleteRequest soumettreFactureCompleteRequest = new SoumettreFactureCompleteRequest(); // SoumettreFactureCompleteRequest | 
    try {
      SoumettreFactureCompleteResponse result = apiInstance.soumettreFactureCompleteApiV1TraitementFacturesSoumettreCompletePost(soumettreFactureCompleteRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TraitementFactureApi#soumettreFactureCompleteApiV1TraitementFacturesSoumettreCompletePost");
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
import org.openapitools.client.api.TraitementFactureApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    TraitementFactureApi apiInstance = new TraitementFactureApi(defaultClient);
    SoumettreFactureCompleteRequest soumettreFactureCompleteRequest = new SoumettreFactureCompleteRequest(); // SoumettreFactureCompleteRequest | 
    try {
      ReponseTache result = apiInstance.soumettreFactureCompleteAsyncApiV1TraitementFacturesSoumettreCompleteAsyncPost(soumettreFactureCompleteRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TraitementFactureApi#soumettreFactureCompleteAsyncApiV1TraitementFacturesSoumettreCompleteAsyncPost");
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

<a id="validerPdfFacturxApiV1TraitementValiderPdfFacturxPost"></a>
# **validerPdfFacturxApiV1TraitementValiderPdfFacturxPost**
> ResultatValidationPDFAPI validerPdfFacturxApiV1TraitementValiderPdfFacturxPost(fichierPdf, profil, useVerapdf)

Valider un PDF Factur-X complet

Valide un PDF Factur-X complet selon les normes européennes et françaises.  ## Normes de validation appliquées  - **EN 16931** : Norme sémantique européenne (directive 2014/55/UE) - **ISO 19005-3** (PDF/A-3) : Archivage électronique à long terme - **Factur-X / ZUGFeRD** : Spécification franco-allemande - **Schematron** : Validation des règles métier XML - **eIDAS** : Règlement européen sur l&#39;identification électronique (signatures)  ## Contrôles effectués  ### 1. Extraction et validation du XML Factur-X **Contrôles réalisés :** - Présence d&#39;un fichier XML embarqué (&#x60;factur-x.xml&#x60; ou &#x60;zugferd-invoice.xml&#x60;) - Détection automatique du profil (MINIMUM, BASIC, EN16931, EXTENDED) - Parsing XML avec validation UTF-8 - Extraction du GuidelineSpecifiedDocumentContextParameter/ID  **Validation Schematron :** - Règles métier du profil détecté (MINIMUM : 45 règles, EN16931 : 178 règles) - Cardinalité des éléments obligatoires - Cohérence des calculs (montants HT, TVA, TTC, remises) - Formats des identifiants (SIRET, TVA intracommunautaire, IBAN) - Codes normalisés (codes pays ISO, unités UN/ECE, codes TVA)  **Ce qui est vérifié :** - ✅ Structure XML conforme XSD Cross Industry Invoice - ✅ Namespace UN/CEFACT correct - ✅ Règles de gestion européennes (BR-xx) - ✅ Règles françaises spécifiques (FR-xx)  ### 2. Conformité PDF/A-3 **Validation de base (métadonnées) :** - Présence du champ &#x60;/Type&#x60; à &#x60;Catalog&#x60; - Métadonnée &#x60;pdfaid:part&#x60; &#x3D; 3 (PDF/A-3) - Métadonnée &#x60;pdfaid:conformance&#x60; &#x3D; B ou U - Version PDF &gt;&#x3D; 1.4  **Validation stricte VeraPDF (si use_verapdf&#x3D;True) :** - 146+ règles ISO 19005-3 (PDF/A-3B) - Absence de contenu interdit (JavaScript, multimedia, formulaires dynamiques) - Polices embarquées et sous-ensembles corrects - Espaces colorimétriques conformes (sRGB, DeviceGray) - Structure de fichier valide (cross-reference table) - Métadonnées XMP conformes ISO 16684-1  **Ce qui est vérifié :** - ✅ Fichier archivable à long terme (20+ ans) - ✅ Lisibilité garantie (polices embarquées) - ✅ Conformité légale (France, Allemagne, UE)  ### 3. Métadonnées XMP (eXtensible Metadata Platform) **Contrôles réalisés :** - Présence du bloc &#x60;&lt;?xpacket&gt;&#x60; avec métadonnées XMP - Namespace &#x60;fx:&#x60; pour Factur-X : &#x60;urn:factur-x:pdfa:CrossIndustryDocument:invoice:1p0#&#x60; - Champs Factur-X obligatoires :   - &#x60;fx:ConformanceLevel&#x60; : Profil (MINIMUM, BASIC, EN16931, EXTENDED)   - &#x60;fx:DocumentFileName&#x60; : Nom du XML embarqué   - &#x60;fx:DocumentType&#x60; : \&quot;INVOICE\&quot;   - &#x60;fx:Version&#x60; : Version Factur-X (1.0.07)  **Ce qui est vérifié :** - ✅ Métadonnées conformes ISO 16684-1 - ✅ Profil Factur-X déclaré correct - ✅ Version Factur-X supportée  ### 4. Signatures électroniques **Détection et analyse :** - Présence de dictionnaires &#x60;/Sig&#x60; dans le PDF - Type de signature : PAdES (PDF Advanced Electronic Signature) - Extraction des informations :   - Nom du signataire (&#x60;/Name&#x60;)   - Date de signature (&#x60;/M&#x60;)   - Raison de la signature (&#x60;/Reason&#x60;)   - Lieu de signature (&#x60;/Location&#x60;)   - Type de signature (approval, certification)  **Ce qui est vérifié :** - ✅ Présence de signatures ou cachets - ✅ Nombre de signatures (mono ou multi-signature) - ℹ️ Pas de vérification cryptographique (nécessite certificats)  ## Paramètres  - **fichier_pdf** (requis) : Le fichier PDF Factur-X à valider - **profil** (optionnel) : Profil attendu. Si absent, détection automatique depuis le XML - **use_verapdf** (optionnel, défaut&#x3D;false) : Active la validation stricte PDF/A avec VeraPDF   - &#x60;false&#x60; : Validation rapide par métadonnées (2-3 secondes)   - &#x60;true&#x60; : Validation complète ISO 19005-3 (15-30 secondes, **recommandé en production**)  ## Réponse détaillée  &#x60;&#x60;&#x60;json {   \&quot;est_conforme\&quot;: true,   \&quot;xml\&quot;: {     \&quot;present\&quot;: true,     \&quot;conforme\&quot;: true,     \&quot;profil\&quot;: \&quot;EN16931\&quot;,     \&quot;erreurs\&quot;: []   },   \&quot;pdfa\&quot;: {     \&quot;conforme\&quot;: true,     \&quot;version\&quot;: \&quot;PDF/A-3B\&quot;,     \&quot;methode\&quot;: \&quot;verapdf\&quot;,     \&quot;erreurs\&quot;: []   },   \&quot;xmp\&quot;: {     \&quot;present\&quot;: true,     \&quot;conforme\&quot;: true,     \&quot;metadonnees\&quot;: {...}   },   \&quot;signatures\&quot;: {     \&quot;present\&quot;: true,     \&quot;nombre\&quot;: 1,     \&quot;details\&quot;: [...]   } } &#x60;&#x60;&#x60;  ## Cas d&#39;usage  - **Avant envoi** : Valider la facture générée avant transmission à un client - **À réception** : Vérifier la conformité d&#39;une facture reçue d&#39;un fournisseur - **Audit** : Contrôler la qualité de lots de factures - **Conformité légale** : S&#39;assurer du respect des obligations B2B/B2G en France - **Debugging** : Identifier les problèmes dans le processus de génération - **Archivage** : Garantir la validité à long terme (PDF/A-3)  ## Temps de traitement  - Validation basique : 2-3 secondes - Validation VeraPDF : 15-30 secondes (dépend de la taille du PDF)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TraitementFactureApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    TraitementFactureApi apiInstance = new TraitementFactureApi(defaultClient);
    File fichierPdf = new File("/path/to/file"); // File | Fichier PDF Factur-X à valider (format .pdf).
    ProfilAPI profil = ProfilAPI.fromValue("MINIMUM"); // ProfilAPI | 
    Boolean useVerapdf = false; // Boolean | Active la validation stricte PDF/A avec VeraPDF (recommandé pour la production). Si False, utilise une validation basique par métadonnées.
    try {
      ResultatValidationPDFAPI result = apiInstance.validerPdfFacturxApiV1TraitementValiderPdfFacturxPost(fichierPdf, profil, useVerapdf);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TraitementFactureApi#validerPdfFacturxApiV1TraitementValiderPdfFacturxPost");
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
| **fichierPdf** | **File**| Fichier PDF Factur-X à valider (format .pdf). | |
| **profil** | [**ProfilAPI**](ProfilAPI.md)|  | [optional] [enum: MINIMUM, BASIC, EN16931, EXTENDED] |
| **useVerapdf** | **Boolean**| Active la validation stricte PDF/A avec VeraPDF (recommandé pour la production). Si False, utilise une validation basique par métadonnées. | [optional] [default to false] |

### Return type

[**ResultatValidationPDFAPI**](ResultatValidationPDFAPI.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Validation effectuée avec succès (vérifier le champ est_conforme pour le résultat) |  -  |
| **400** | Fichier PDF invalide ou impossible à lire |  -  |
| **422** | Validation Error |  -  |

<a id="validerPdfFacturxAsyncApiV1TraitementValiderFacturxAsyncPost"></a>
# **validerPdfFacturxAsyncApiV1TraitementValiderFacturxAsyncPost**
> ReponseTache validerPdfFacturxAsyncApiV1TraitementValiderFacturxAsyncPost(fichierPdf, profil, useVerapdf)

Valider un PDF Factur-X (asynchrone avec polling)

Valide un PDF Factur-X de manière asynchrone avec système de polling.  ## Fonctionnement  1. **Soumission** : Le PDF est mis en file d&#39;attente pour validation asynchrone 2. **Retour immédiat** : Vous recevez un &#x60;id_tache&#x60; (HTTP 202) 3. **Suivi** : Utilisez l&#39;endpoint &#x60;/taches/{id_tache}/statut&#x60; pour suivre l&#39;avancement  ## Avantages du mode asynchrone  - **Pas de timeout** : Idéal pour les gros PDFs ou la validation VeraPDF (qui peut prendre plusieurs secondes) - **Scalabilité** : Les validations sont traitées par des workers Celery dédiés - **Suivi d&#39;état** : Permet de suivre la progression de la validation - **Non-bloquant** : Votre client ne reste pas en attente pendant la validation  ## Quand utiliser ce mode ?  - **Validation VeraPDF activée** (&#x60;use_verapdf&#x3D;True&#x60;) : La validation stricte peut prendre 2-10 secondes - **Gros fichiers PDF** : PDFs &gt; 1 MB - **Traitement par lots** : Validation de multiples factures en parallèle - **Intégration asynchrone** : Votre système supporte le polling  ## Contrôles effectués  ### 1. Extraction et validation du XML Factur-X - Vérifie la présence d&#39;un fichier XML embarqué conforme Factur-X - Détecte automatiquement le profil utilisé (MINIMUM, BASIC, EN16931, EXTENDED) - Valide le XML contre les règles Schematron du profil détecté  ### 2. Conformité PDF/A - **Sans VeraPDF** : Validation basique par métadonnées (rapide, ~100ms) - **Avec VeraPDF** : Validation stricte selon ISO 19005 (146+ règles, 2-10s)   - Détecte la version PDF/A (PDF/A-1, PDF/A-3, etc.)   - Rapports détaillés des non-conformités  ### 3. Métadonnées XMP - Vérifie la présence de métadonnées XMP dans le PDF - Valide la conformité des métadonnées Factur-X (profil, version) - Extrait toutes les métadonnées XMP disponibles  ### 4. Signatures électroniques - Détecte la présence de signatures ou cachets électroniques - Extrait les informations sur chaque signature (signataire, date, raison) - Compte le nombre de signatures présentes  ## Paramètres  - **fichier_pdf** : Le fichier PDF Factur-X à valider - **profil** : Le profil Factur-X attendu (optionnel). Si non spécifié, le profil   sera automatiquement détecté depuis le fichier XML embarqué. - **use_verapdf** : Active la validation stricte PDF/A avec VeraPDF.   ⚠️ **Attention** : VeraPDF peut prendre 2-10 secondes selon la taille du PDF.   Recommandé uniquement en mode asynchrone pour éviter les timeouts.  ## Récupération du résultat  Après soumission, utilisez l&#39;endpoint &#x60;GET /taches/{id_tache}/statut&#x60; pour récupérer le résultat.  **Exemple de polling** : &#x60;&#x60;&#x60;python import requests import time  # 1. Soumettre la tâche response &#x3D; requests.post(\&quot;/valider-facturx-async\&quot;, files&#x3D;{\&quot;fichier_pdf\&quot;: pdf_file}) task_id &#x3D; response.json()[\&quot;id_tache\&quot;]  # 2. Polling toutes les 2 secondes while True:     status_response &#x3D; requests.get(f\&quot;/taches/{task_id}/statut\&quot;)     status &#x3D; status_response.json()      if status[\&quot;statut\&quot;] &#x3D;&#x3D; \&quot;SUCCESS\&quot;:         resultat &#x3D; status[\&quot;resultat\&quot;][\&quot;resultat_validation\&quot;]         print(f\&quot;Conforme: {resultat[&#39;est_conforme&#39;]}\&quot;)         break     elif status[\&quot;statut\&quot;] &#x3D;&#x3D; \&quot;FAILURE\&quot;:         print(f\&quot;Erreur: {status[&#39;resultat&#39;][&#39;message_erreur&#39;]}\&quot;)         break      time.sleep(2)  # Attendre 2 secondes avant le prochain check &#x60;&#x60;&#x60;  ## Cas d&#39;usage  - Valider des factures avant envoi avec VeraPDF (validation stricte) - Traiter des lots de factures en parallèle - Intégrer la validation dans un pipeline asynchrone - Valider des PDFs volumineux sans risque de timeout

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TraitementFactureApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    TraitementFactureApi apiInstance = new TraitementFactureApi(defaultClient);
    File fichierPdf = new File("/path/to/file"); // File | Fichier PDF Factur-X à valider (format .pdf).
    ProfilAPI profil = ProfilAPI.fromValue("MINIMUM"); // ProfilAPI | 
    Boolean useVerapdf = false; // Boolean | Active la validation stricte PDF/A avec VeraPDF (recommandé pour la production). Peut prendre plusieurs secondes.
    try {
      ReponseTache result = apiInstance.validerPdfFacturxAsyncApiV1TraitementValiderFacturxAsyncPost(fichierPdf, profil, useVerapdf);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TraitementFactureApi#validerPdfFacturxAsyncApiV1TraitementValiderFacturxAsyncPost");
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
| **fichierPdf** | **File**| Fichier PDF Factur-X à valider (format .pdf). | |
| **profil** | [**ProfilAPI**](ProfilAPI.md)|  | [optional] [enum: MINIMUM, BASIC, EN16931, EXTENDED] |
| **useVerapdf** | **Boolean**| Active la validation stricte PDF/A avec VeraPDF (recommandé pour la production). Peut prendre plusieurs secondes. | [optional] [default to false] |

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
| **400** | Fichier PDF invalide ou impossible à lire |  -  |
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
import org.openapitools.client.api.TraitementFactureApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    TraitementFactureApi apiInstance = new TraitementFactureApi(defaultClient);
    File fichierPdf = new File("/path/to/file"); // File | Fichier PDF à valider (sera analysé pour détecter et valider les signatures)
    try {
      Object result = apiInstance.validerSignaturePdfEndpointApiV1TraitementValiderSignaturePdfPost(fichierPdf);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TraitementFactureApi#validerSignaturePdfEndpointApiV1TraitementValiderSignaturePdfPost");
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

<a id="validerXmlApiV1TraitementValiderXmlPost"></a>
# **validerXmlApiV1TraitementValiderXmlPost**
> ReponseValidationSucces validerXmlApiV1TraitementValiderXmlPost(fichierXml, profil)

Valider un XML Factur-X existant

Valide un fichier XML Factur-X contre les règles métier Schematron selon la norme EN 16931.  ## Norme appliquée  **Schematron ISO/IEC 19757-3** : Langage de validation de règles métier pour XML - Validation sémantique (au-delà de la syntaxe XSD) - Règles métier européennes EN 16931 - Règles françaises spécifiques Factur-X - Calculs arithmétiques et cohérence des données  ## Profils et règles validées  ### MINIMUM (45 règles) - Identifiant de facture unique - Dates (émission, échéance) - Identifiants parties (SIRET/SIREN) - Montant total TTC  ### BASIC (102 règles) - Toutes les règles MINIMUM - Lignes de facture détaillées - Calculs de TVA basiques - Modes de paiement - Références (commande, contrat)  ### EN16931 (178 règles) - Toutes les règles BASIC - **Règles européennes (BR-xx)** : 81 règles business - **Règles françaises (FR-xx)** : 12 règles spécifiques France - **Calculs avancés (CR-xx)** : 32 règles de calcul - **Codes normalisés (CL-xx)** : 52 listes de codes  ### EXTENDED (210+ règles) - Toutes les règles EN16931 - Informations logistiques - Données comptables avancées - Références externes multiples  ## Contrôles effectués  ### 1. Validation syntaxique - Parsing XML correct (UTF-8, bien formé) - Namespaces UN/CEFACT présents - Structure hiérarchique respectée  ### 2. Règles business (BR-xx) Exemples : - &#x60;BR-1&#x60; : Le total de la facture doit être égal à la somme des totaux de lignes + montants au niveau document - &#x60;BR-CO-10&#x60; : La somme des montants de base de TVA doit être égale au total net de la facture - &#x60;BR-16&#x60; : Le code de devise de la facture doit figurer dans la liste ISO 4217  ### 3. Règles françaises (FR-xx) Exemples : - &#x60;FR-1&#x60; : Le SIRET fournisseur doit avoir 14 chiffres - &#x60;FR-2&#x60; : Le SIRET client doit avoir 14 chiffres (si présent) - &#x60;FR-5&#x60; : Le numéro de TVA intracommunautaire doit être au format FRxx999999999  ### 4. Règles de calcul (CR-xx) - Montants HT + TVA &#x3D; TTC - Somme des lignes &#x3D; Total document - Remises et majorations correctement appliquées - Arrondis conformes (2 décimales pour les montants)  ### 5. Codes normalisés (CL-xx) - Codes pays ISO 3166-1 alpha-2 - Codes devises ISO 4217 - Unités de mesure UN/ECE Rec 20 - Codes TVA (types, catégories, exonérations) - SchemeID pour identifiants (0002&#x3D;SIREN, 0009&#x3D;SIRET, etc.)  ## Processus de validation  1. **Chargement XSLT** : Fichier Schematron converti en XSLT (Saxon-HE) 2. **Transformation** : Application des règles sur le XML 3. **Analyse résultats** : Extraction des erreurs (&#x60;failed-assert&#x60;) et avertissements (&#x60;successful-report&#x60;) 4. **Rapport** : Liste structurée des non-conformités  ## Réponses  **200 OK** : XML conforme &#x60;&#x60;&#x60;json {   \&quot;message\&quot;: \&quot;Le XML est conforme au profil EN16931\&quot; } &#x60;&#x60;&#x60;  **400 Bad Request** : XML non conforme &#x60;&#x60;&#x60;json {   \&quot;detail\&quot;: [     \&quot;[BR-1] Le total de la facture (120.00) ne correspond pas à la somme calculée (100.00 + 20.00)\&quot;,     \&quot;[FR-1] Le SIRET fournisseur doit contenir exactement 14 chiffres\&quot;   ] } &#x60;&#x60;&#x60;  ## Cas d&#39;usage  - **Pré-validation** : Vérifier un XML avant intégration dans un PDF/A - **Debugging** : Identifier précisément les erreurs de génération - **Tests** : Valider des XMLs de test ou d&#39;exemple - **Conformité** : S&#39;assurer du respect des règles européennes et françaises - **Développement** : Tester rapidement sans générer de PDF  ## Temps de traitement  - Profil MINIMUM : ~0.5 seconde - Profil EN16931 : ~1-2 secondes - Profil EXTENDED : ~2-3 secondes

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TraitementFactureApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    TraitementFactureApi apiInstance = new TraitementFactureApi(defaultClient);
    File fichierXml = new File("/path/to/file"); // File | Fichier XML Factur-X à valider (format .xml).
    ProfilAPI profil = ProfilAPI.fromValue("MINIMUM"); // ProfilAPI | Profil de validation (MINIMUM, BASIC, EN16931, EXTENDED).
    try {
      ReponseValidationSucces result = apiInstance.validerXmlApiV1TraitementValiderXmlPost(fichierXml, profil);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TraitementFactureApi#validerXmlApiV1TraitementValiderXmlPost");
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
| **fichierXml** | **File**| Fichier XML Factur-X à valider (format .xml). | |
| **profil** | [**ProfilAPI**](ProfilAPI.md)| Profil de validation (MINIMUM, BASIC, EN16931, EXTENDED). | [optional] [enum: MINIMUM, BASIC, EN16931, EXTENDED] |

### Return type

[**ReponseValidationSucces**](ReponseValidationSucces.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Le XML ne respecte pas les règles du profil Factur-X |  -  |
| **422** | Validation Error |  -  |

