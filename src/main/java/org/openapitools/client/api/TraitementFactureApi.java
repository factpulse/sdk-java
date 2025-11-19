/*
 * API REST FactPulse
 *  API REST pour la facturation électronique en France : Factur-X, AFNOR PDP/PA, signatures électroniques.  ## 🎯 Fonctionnalités principales  ### 📄 Génération de factures Factur-X - **Formats** : XML seul ou PDF/A-3 avec XML embarqué - **Profils** : MINIMUM, BASIC, EN16931, EXTENDED - **Normes** : EN 16931 (directive UE 2014/55), ISO 19005-3 (PDF/A-3), CII (UN/CEFACT) - **🆕 Format simplifié** : Génération à partir de SIRET + auto-enrichissement (API Chorus Pro + Recherche Entreprises)  ### ✅ Validation et conformité - **Validation XML** : Schematron (45 à 210+ règles selon profil) - **Validation PDF** : PDF/A-3, métadonnées XMP Factur-X, signatures électroniques - **VeraPDF** : Validation stricte PDF/A (146+ règles ISO 19005-3) - **Traitement asynchrone** : Support Celery pour validations lourdes (VeraPDF)  ### 📡 Intégration AFNOR PDP/PA (XP Z12-013) - **Soumission de flux** : Envoi de factures vers Plateformes de Dématérialisation Partenaires - **Recherche de flux** : Consultation des factures soumises - **Téléchargement** : Récupération des PDF/A-3 avec XML - **Directory Service** : Recherche d'entreprises (SIREN/SIRET) - **Multi-client** : Support de plusieurs configs PDP par utilisateur (stored credentials ou zero-storage)  ### ✍️ Signature électronique PDF - **Standards** : PAdES-B-B, PAdES-B-T (horodatage RFC 3161), PAdES-B-LT (archivage long terme) - **Niveaux eIDAS** : SES (auto-signé), AdES (CA commerciale), QES (PSCO) - **Validation** : Vérification intégrité cryptographique et certificats - **Génération de certificats** : Certificats X.509 auto-signés pour tests  ### 🔄 Traitement asynchrone - **Celery** : Génération, validation et signature asynchrones - **Polling** : Suivi d'état via `/taches/{id_tache}/statut` - **Pas de timeout** : Idéal pour gros fichiers ou validations lourdes  ## 🔒 Authentification  Toutes les requêtes nécessitent un **token JWT** dans le header Authorization : ``` Authorization: Bearer YOUR_JWT_TOKEN ```  ### Comment obtenir un token JWT ?  #### 🔑 Méthode 1 : API `/api/token/` (Recommandée)  **URL :** `https://www.factpulse.fr/api/token/`  Cette méthode est **recommandée** pour l'intégration dans vos applications et workflows CI/CD.  **Prérequis :** Avoir défini un mot de passe sur votre compte  **Pour les utilisateurs inscrits via email/password :** - Vous avez déjà un mot de passe, utilisez-le directement  **Pour les utilisateurs inscrits via OAuth (Google/GitHub) :** - Vous devez d'abord définir un mot de passe sur : https://www.factpulse.fr/accounts/password/set/ - Une fois le mot de passe créé, vous pourrez utiliser l'API  **Exemple de requête :** ```bash curl -X POST https://www.factpulse.fr/api/token/ \\   -H \"Content-Type: application/json\" \\   -d '{     \"username\": \"votre_email@example.com\",     \"password\": \"votre_mot_de_passe\"   }' ```  **Paramètre optionnel `client_uid` :**  Pour sélectionner les credentials d'un client spécifique (PA/PDP, Chorus Pro, certificats de signature), ajoutez `client_uid` :  ```bash curl -X POST https://www.factpulse.fr/api/token/ \\   -H \"Content-Type: application/json\" \\   -d '{     \"username\": \"votre_email@example.com\",     \"password\": \"votre_mot_de_passe\",     \"client_uid\": \"550e8400-e29b-41d4-a716-446655440000\"   }' ```  Le `client_uid` sera inclus dans le JWT et permettra à l'API d'utiliser automatiquement : - Les credentials AFNOR/PDP configurés pour ce client - Les credentials Chorus Pro configurés pour ce client - Les certificats de signature électronique configurés pour ce client  **Réponse :** ```json {   \"access\": \"eyJ0eXAiOiJKV1QiLCJhbGc...\",  // Token d'accès (validité: 30 min)   \"refresh\": \"eyJ0eXAiOiJKV1QiLCJhbGc...\"  // Token de rafraîchissement (validité: 7 jours) } ```  **Avantages :** - ✅ Automatisation complète (CI/CD, scripts) - ✅ Gestion programmatique des tokens - ✅ Support du refresh token pour renouveler automatiquement l'accès - ✅ Intégration facile dans n'importe quel langage/outil  #### 🖥️ Méthode 2 : Génération via Dashboard (Alternative)  **URL :** https://www.factpulse.fr/dashboard/  Cette méthode convient pour des tests rapides ou une utilisation occasionnelle via l'interface graphique.  **Fonctionnement :** - Connectez-vous au dashboard - Utilisez les boutons \"Generate Test Token\" ou \"Generate Production Token\" - Fonctionne pour **tous** les utilisateurs (OAuth et email/password), sans nécessiter de mot de passe  **Types de tokens :** - **Token Test** : Validité 24h, quota 1000 appels/jour (gratuit) - **Token Production** : Validité 7 jours, quota selon votre forfait  **Avantages :** - ✅ Rapide pour tester l'API - ✅ Aucun mot de passe requis - ✅ Interface visuelle simple  **Inconvénients :** - ❌ Nécessite une action manuelle - ❌ Pas de refresh token - ❌ Moins adapté pour l'automatisation  ### 📚 Documentation complète  Pour plus d'informations sur l'authentification et l'utilisation de l'API : https://www.factpulse.fr/documentation-api/     
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import org.openapitools.client.ApiCallback;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.Configuration;
import org.openapitools.client.Pair;
import org.openapitools.client.ProgressRequestBody;
import org.openapitools.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import java.io.File;
import org.openapitools.client.model.FormatSortie;
import org.openapitools.client.model.GenerateCertificateRequest;
import org.openapitools.client.model.GenerateCertificateResponse;
import org.openapitools.client.model.HTTPValidationError;
import org.openapitools.client.model.ProfilAPI;
import org.openapitools.client.model.ReponseTache;
import org.openapitools.client.model.ReponseValidationErreur;
import org.openapitools.client.model.ReponseValidationSucces;
import org.openapitools.client.model.ResultatValidationPDFAPI;
import org.openapitools.client.model.SoumettreFactureCompleteRequest;
import org.openapitools.client.model.SoumettreFactureCompleteResponse;
import org.openapitools.client.model.StatutTache;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TraitementFactureApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public TraitementFactureApi() {
        this(Configuration.getDefaultApiClient());
    }

    public TraitementFactureApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public int getHostIndex() {
        return localHostIndex;
    }

    public void setHostIndex(int hostIndex) {
        this.localHostIndex = hostIndex;
    }

    public String getCustomBaseUrl() {
        return localCustomBaseUrl;
    }

    public void setCustomBaseUrl(String customBaseUrl) {
        this.localCustomBaseUrl = customBaseUrl;
    }

    /**
     * Build call for genererCertificatTestApiV1TraitementGenererCertificatTestPost
     * @param generateCertificateRequest  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Certificat généré avec succès </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Requête invalide (paramètres incorrects) </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Erreur serveur lors de la génération </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call genererCertificatTestApiV1TraitementGenererCertificatTestPostCall(@javax.annotation.Nonnull GenerateCertificateRequest generateCertificateRequest, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = generateCertificateRequest;

        // create path and map variables
        String localVarPath = "/api/v1/traitement/generer-certificat-test";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "HTTPBearer" };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call genererCertificatTestApiV1TraitementGenererCertificatTestPostValidateBeforeCall(@javax.annotation.Nonnull GenerateCertificateRequest generateCertificateRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'generateCertificateRequest' is set
        if (generateCertificateRequest == null) {
            throw new ApiException("Missing the required parameter 'generateCertificateRequest' when calling genererCertificatTestApiV1TraitementGenererCertificatTestPost(Async)");
        }

        return genererCertificatTestApiV1TraitementGenererCertificatTestPostCall(generateCertificateRequest, _callback);

    }

    /**
     * Générer un certificat X.509 auto-signé de test
     * Génère un certificat X.509 auto-signé pour les tests de signature électronique PDF.      **⚠️ ATTENTION : Certificat de TEST uniquement !**      Ce certificat est :     - ✅ Adapté pour tests et développement     - ✅ Compatible signature PDF (PAdES)     - ✅ Conforme eIDAS niveau **SES** (Simple Electronic Signature)     - ❌ **JAMAIS utilisable en production**     - ❌ **Non reconnu** par les navigateurs et lecteurs PDF     - ❌ **Aucune valeur juridique**      ## Niveaux eIDAS      - **SES** (Simple) : Certificat auto-signé ← Généré par cet endpoint     - **AdES** (Advanced) : Certificat CA commerciale (Let&#39;s Encrypt, etc.)     - **QES** (Qualified) : Certificat qualifié PSCO (CertEurope, Universign, etc.)      ## Utilisation      Une fois généré, le certificat peut être :      1. **Enregistré dans Django** (recommandé) :        - Django Admin &gt; Certificats de signature        - Upload &#x60;certificat_pem&#x60; et &#x60;cle_privee_pem&#x60;      2. **Utilisé directement** :        - Signer un PDF avec &#x60;/signer-pdf&#x60;        - Le certificat sera automatiquement utilisé      ## Exemple d&#39;appel      &#x60;&#x60;&#x60;bash     curl -X POST \&quot;https://www.factpulse.fr/api/facturation/generer-certificat-test\&quot; \\       -H \&quot;Authorization: Bearer eyJ0eXAi...\&quot; \\       -H \&quot;Content-Type: application/json\&quot; \\       -d &#39;{         \&quot;cn\&quot;: \&quot;Test Client XYZ\&quot;,         \&quot;organisation\&quot;: \&quot;Client XYZ SARL\&quot;,         \&quot;email\&quot;: \&quot;contact@xyz.fr\&quot;,         \&quot;duree_jours\&quot;: 365       }&#39;     &#x60;&#x60;&#x60;      ## Cas d&#39;usage      - Tests de signature PDF en développement     - POC de signature électronique     - Formation et démos     - Tests d&#39;intégration automatisés      ## Conformité technique      Certificat généré avec :     - Clé RSA 2048 ou 4096 bits     - Algorithme SHA-256     - Extensions Key Usage : &#x60;digitalSignature&#x60;, &#x60;contentCommitment&#x60; (non-repudiation)     - Extensions Extended Key Usage : &#x60;codeSigning&#x60;, &#x60;emailProtection&#x60;     - Validité : 1 jour à 10 ans (configurable)     - Format : PEM (certificat et clé)     - Optionnel : PKCS#12 (.p12)
     * @param generateCertificateRequest  (required)
     * @return GenerateCertificateResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Certificat généré avec succès </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Requête invalide (paramètres incorrects) </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Erreur serveur lors de la génération </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public GenerateCertificateResponse genererCertificatTestApiV1TraitementGenererCertificatTestPost(@javax.annotation.Nonnull GenerateCertificateRequest generateCertificateRequest) throws ApiException {
        ApiResponse<GenerateCertificateResponse> localVarResp = genererCertificatTestApiV1TraitementGenererCertificatTestPostWithHttpInfo(generateCertificateRequest);
        return localVarResp.getData();
    }

    /**
     * Générer un certificat X.509 auto-signé de test
     * Génère un certificat X.509 auto-signé pour les tests de signature électronique PDF.      **⚠️ ATTENTION : Certificat de TEST uniquement !**      Ce certificat est :     - ✅ Adapté pour tests et développement     - ✅ Compatible signature PDF (PAdES)     - ✅ Conforme eIDAS niveau **SES** (Simple Electronic Signature)     - ❌ **JAMAIS utilisable en production**     - ❌ **Non reconnu** par les navigateurs et lecteurs PDF     - ❌ **Aucune valeur juridique**      ## Niveaux eIDAS      - **SES** (Simple) : Certificat auto-signé ← Généré par cet endpoint     - **AdES** (Advanced) : Certificat CA commerciale (Let&#39;s Encrypt, etc.)     - **QES** (Qualified) : Certificat qualifié PSCO (CertEurope, Universign, etc.)      ## Utilisation      Une fois généré, le certificat peut être :      1. **Enregistré dans Django** (recommandé) :        - Django Admin &gt; Certificats de signature        - Upload &#x60;certificat_pem&#x60; et &#x60;cle_privee_pem&#x60;      2. **Utilisé directement** :        - Signer un PDF avec &#x60;/signer-pdf&#x60;        - Le certificat sera automatiquement utilisé      ## Exemple d&#39;appel      &#x60;&#x60;&#x60;bash     curl -X POST \&quot;https://www.factpulse.fr/api/facturation/generer-certificat-test\&quot; \\       -H \&quot;Authorization: Bearer eyJ0eXAi...\&quot; \\       -H \&quot;Content-Type: application/json\&quot; \\       -d &#39;{         \&quot;cn\&quot;: \&quot;Test Client XYZ\&quot;,         \&quot;organisation\&quot;: \&quot;Client XYZ SARL\&quot;,         \&quot;email\&quot;: \&quot;contact@xyz.fr\&quot;,         \&quot;duree_jours\&quot;: 365       }&#39;     &#x60;&#x60;&#x60;      ## Cas d&#39;usage      - Tests de signature PDF en développement     - POC de signature électronique     - Formation et démos     - Tests d&#39;intégration automatisés      ## Conformité technique      Certificat généré avec :     - Clé RSA 2048 ou 4096 bits     - Algorithme SHA-256     - Extensions Key Usage : &#x60;digitalSignature&#x60;, &#x60;contentCommitment&#x60; (non-repudiation)     - Extensions Extended Key Usage : &#x60;codeSigning&#x60;, &#x60;emailProtection&#x60;     - Validité : 1 jour à 10 ans (configurable)     - Format : PEM (certificat et clé)     - Optionnel : PKCS#12 (.p12)
     * @param generateCertificateRequest  (required)
     * @return ApiResponse&lt;GenerateCertificateResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Certificat généré avec succès </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Requête invalide (paramètres incorrects) </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Erreur serveur lors de la génération </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<GenerateCertificateResponse> genererCertificatTestApiV1TraitementGenererCertificatTestPostWithHttpInfo(@javax.annotation.Nonnull GenerateCertificateRequest generateCertificateRequest) throws ApiException {
        okhttp3.Call localVarCall = genererCertificatTestApiV1TraitementGenererCertificatTestPostValidateBeforeCall(generateCertificateRequest, null);
        Type localVarReturnType = new TypeToken<GenerateCertificateResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Générer un certificat X.509 auto-signé de test (asynchronously)
     * Génère un certificat X.509 auto-signé pour les tests de signature électronique PDF.      **⚠️ ATTENTION : Certificat de TEST uniquement !**      Ce certificat est :     - ✅ Adapté pour tests et développement     - ✅ Compatible signature PDF (PAdES)     - ✅ Conforme eIDAS niveau **SES** (Simple Electronic Signature)     - ❌ **JAMAIS utilisable en production**     - ❌ **Non reconnu** par les navigateurs et lecteurs PDF     - ❌ **Aucune valeur juridique**      ## Niveaux eIDAS      - **SES** (Simple) : Certificat auto-signé ← Généré par cet endpoint     - **AdES** (Advanced) : Certificat CA commerciale (Let&#39;s Encrypt, etc.)     - **QES** (Qualified) : Certificat qualifié PSCO (CertEurope, Universign, etc.)      ## Utilisation      Une fois généré, le certificat peut être :      1. **Enregistré dans Django** (recommandé) :        - Django Admin &gt; Certificats de signature        - Upload &#x60;certificat_pem&#x60; et &#x60;cle_privee_pem&#x60;      2. **Utilisé directement** :        - Signer un PDF avec &#x60;/signer-pdf&#x60;        - Le certificat sera automatiquement utilisé      ## Exemple d&#39;appel      &#x60;&#x60;&#x60;bash     curl -X POST \&quot;https://www.factpulse.fr/api/facturation/generer-certificat-test\&quot; \\       -H \&quot;Authorization: Bearer eyJ0eXAi...\&quot; \\       -H \&quot;Content-Type: application/json\&quot; \\       -d &#39;{         \&quot;cn\&quot;: \&quot;Test Client XYZ\&quot;,         \&quot;organisation\&quot;: \&quot;Client XYZ SARL\&quot;,         \&quot;email\&quot;: \&quot;contact@xyz.fr\&quot;,         \&quot;duree_jours\&quot;: 365       }&#39;     &#x60;&#x60;&#x60;      ## Cas d&#39;usage      - Tests de signature PDF en développement     - POC de signature électronique     - Formation et démos     - Tests d&#39;intégration automatisés      ## Conformité technique      Certificat généré avec :     - Clé RSA 2048 ou 4096 bits     - Algorithme SHA-256     - Extensions Key Usage : &#x60;digitalSignature&#x60;, &#x60;contentCommitment&#x60; (non-repudiation)     - Extensions Extended Key Usage : &#x60;codeSigning&#x60;, &#x60;emailProtection&#x60;     - Validité : 1 jour à 10 ans (configurable)     - Format : PEM (certificat et clé)     - Optionnel : PKCS#12 (.p12)
     * @param generateCertificateRequest  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Certificat généré avec succès </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Requête invalide (paramètres incorrects) </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Erreur serveur lors de la génération </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call genererCertificatTestApiV1TraitementGenererCertificatTestPostAsync(@javax.annotation.Nonnull GenerateCertificateRequest generateCertificateRequest, final ApiCallback<GenerateCertificateResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = genererCertificatTestApiV1TraitementGenererCertificatTestPostValidateBeforeCall(generateCertificateRequest, _callback);
        Type localVarReturnType = new TypeToken<GenerateCertificateResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for genererFactureApiV1TraitementGenererFacturePost
     * @param donneesFacture Données de la facture au format JSON.              Deux formats acceptés :             1. **Format classique** : Structure complète FactureFacturX (tous les champs)             2. **Format simplifié** (🆕 P0.1) : Structure minimale avec auto-enrichissement              Le format est détecté automatiquement !              (required)
     * @param profil Profil Factur-X : MINIMUM, BASIC, EN16931 ou EXTENDED. (optional)
     * @param formatSortie Format de sortie : &#39;xml&#39; (XML seul) ou &#39;pdf&#39; (PDF Factur-X avec XML embarqué). (optional)
     * @param autoEnrichir 🆕 Activer l&#39;auto-enrichissement depuis SIRET/SIREN (format simplifié uniquement) (optional, default to true)
     * @param sourcePdf  (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 202 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Données de facture invalides ou fichier PDF manquant </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Erreur de validation des données de la facture </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call genererFactureApiV1TraitementGenererFacturePostCall(@javax.annotation.Nonnull String donneesFacture, @javax.annotation.Nullable ProfilAPI profil, @javax.annotation.Nullable FormatSortie formatSortie, @javax.annotation.Nullable Boolean autoEnrichir, @javax.annotation.Nullable File sourcePdf, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/v1/traitement/generer-facture";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (donneesFacture != null) {
            localVarFormParams.put("donnees_facture", donneesFacture);
        }

        if (profil != null) {
            localVarFormParams.put("profil", profil);
        }

        if (formatSortie != null) {
            localVarFormParams.put("format_sortie", formatSortie);
        }

        if (autoEnrichir != null) {
            localVarFormParams.put("auto_enrichir", autoEnrichir);
        }

        if (sourcePdf != null) {
            localVarFormParams.put("source_pdf", sourcePdf);
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "multipart/form-data"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "HTTPBearer" };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call genererFactureApiV1TraitementGenererFacturePostValidateBeforeCall(@javax.annotation.Nonnull String donneesFacture, @javax.annotation.Nullable ProfilAPI profil, @javax.annotation.Nullable FormatSortie formatSortie, @javax.annotation.Nullable Boolean autoEnrichir, @javax.annotation.Nullable File sourcePdf, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'donneesFacture' is set
        if (donneesFacture == null) {
            throw new ApiException("Missing the required parameter 'donneesFacture' when calling genererFactureApiV1TraitementGenererFacturePost(Async)");
        }

        return genererFactureApiV1TraitementGenererFacturePostCall(donneesFacture, profil, formatSortie, autoEnrichir, sourcePdf, _callback);

    }

    /**
     * Générer une facture Factur-X
     * Génère une facture électronique au format Factur-X conforme aux normes européennes.  ## Normes appliquées  - **Factur-X** (France) : Norme FNFE-MPE (Forum National de la Facture Électronique) - **ZUGFeRD** (Allemagne) : Format allemand compatible Factur-X - **EN 16931** : Norme sémantique européenne pour la facturation électronique - **ISO 19005-3** (PDF/A-3) : Archivage électronique à long terme - **Cross Industry Invoice (CII)** : Syntaxe XML UN/CEFACT  ## 🆕 Nouveau : Format simplifié avec auto-enrichissement (P0.1)  Vous pouvez désormais créer une facture en fournissant uniquement : - Un numéro de facture - Un SIRET émetteur + **IBAN** (obligatoire) - Un SIRET destinataire - Les lignes de facture (description, quantité, prix HT)  **Exemple format simplifié** : &#x60;&#x60;&#x60;json {   \&quot;numero\&quot;: \&quot;FACT-2025-001\&quot;,   \&quot;emetteur\&quot;: {     \&quot;siret\&quot;: \&quot;92019522900017\&quot;,     \&quot;iban\&quot;: \&quot;FR7630001007941234567890185\&quot;   },   \&quot;destinataire\&quot;: {\&quot;siret\&quot;: \&quot;35600000000048\&quot;},   \&quot;lignes\&quot;: [     {\&quot;description\&quot;: \&quot;Prestation\&quot;, \&quot;quantite\&quot;: 10, \&quot;prix_ht\&quot;: 100.00, \&quot;tva\&quot;: 20.0}   ] } &#x60;&#x60;&#x60;  **⚠️ Champs obligatoires (format simplifié)** : - &#x60;numero&#x60; : Numéro de facture unique - &#x60;emetteur.siret&#x60; : SIRET de l&#39;émetteur (14 chiffres) - &#x60;emetteur.iban&#x60; : IBAN du compte bancaire (pas d&#39;API publique pour le récupérer) - &#x60;destinataire.siret&#x60; : SIRET du destinataire - &#x60;lignes[]&#x60; : Au moins une ligne de facture  **Ce qui se passe automatiquement avec &#x60;auto_enrichir&#x3D;True&#x60;** : - ✅ Enrichissement des noms depuis API Chorus Pro - ✅ Enrichissement des adresses depuis API Recherche Entreprises (gratuite, publique) - ✅ Calcul automatique de la TVA intracommunautaire (FR + clé + SIREN) - ✅ Récupération de l&#39;ID Chorus Pro pour la facturation électronique - ✅ Calcul des totaux HT/TVA/TTC - ✅ Génération des dates (aujourd&#39;hui + échéance 30j) - ✅ Gestion multi-taux de TVA  **Identifiants supportés** : - SIRET (14 chiffres) : Établissement précis ⭐ Recommandé - SIREN (9 chiffres) : Entreprise (sélection auto du siège) - Types spéciaux : UE_HORS_FRANCE, RIDET, TAHITI, etc.  ## Contrôles effectués lors de la génération  ### 1. Validation des données (Pydantic) - Types de données (montants en Decimal, dates ISO 8601) - Formats (SIRET 14 chiffres, SIREN 9 chiffres, IBAN) - Champs obligatoires selon le profil - Cohérence des montants (HT + TVA &#x3D; TTC)  ### 2. Génération XML conforme CII - Sérialisation selon schéma XSD Cross Industry Invoice - Namespaces UN/CEFACT corrects - Structure hiérarchique respectée - Encodage UTF-8 sans BOM  ### 3. Validation Schematron - Règles métier du profil sélectionné (MINIMUM, BASIC, EN16931, EXTENDED) - Cardinalité des éléments (obligatoire, optionnel, répétable) - Règles de calcul (totaux, TVA, remises) - Conformité européenne EN 16931  ### 4. Conversion PDF/A-3 (si format_sortie&#x3D;&#39;pdf&#39;) - Conversion du PDF source en PDF/A-3 via Ghostscript - Embarquement du XML Factur-X dans le PDF - Métadonnées XMP conformes - Profil ICC sRGB pour les couleurs - Suppression des éléments interdits (JavaScript, formulaires)  ## Fonctionnement  1. **Soumission** : La facture est mise en file d&#39;attente Celery pour traitement asynchrone 2. **Retour immédiat** : Vous recevez un &#x60;id_tache&#x60; (HTTP 202 Accepted) 3. **Suivi** : Utilisez l&#39;endpoint &#x60;/taches/{id_tache}/statut&#x60; pour suivre l&#39;avancement  ## Formats de sortie  - **xml** : Génère uniquement le XML Factur-X (recommandé pour les tests) - **pdf** : Génère un PDF/A-3 avec XML embarqué (nécessite &#x60;source_pdf&#x60;)  ## Profils Factur-X  - **MINIMUM** : Données minimales (facture simplifiée) - **BASIC** : Informations de base (PME) - **EN16931** : Standard européen (recommandé, conforme directive 2014/55/UE) - **EXTENDED** : Toutes les données disponibles (grands comptes)  ## Ce que vous obtenez  Après traitement réussi (statut &#x60;completed&#x60;) : - **XML seul** : Fichier XML encodé base64 conforme Factur-X - **PDF/A-3** : PDF avec XML embarqué, prêt pour envoi/archivage - **Métadonnées** : Profil, version Factur-X, taille fichier - **Validation** : Confirmation de conformité Schematron  ## Validation  Les données sont validées automatiquement selon le format détecté. En cas d&#39;erreur, un statut 422 est retourné avec les détails des champs invalides.
     * @param donneesFacture Données de la facture au format JSON.              Deux formats acceptés :             1. **Format classique** : Structure complète FactureFacturX (tous les champs)             2. **Format simplifié** (🆕 P0.1) : Structure minimale avec auto-enrichissement              Le format est détecté automatiquement !              (required)
     * @param profil Profil Factur-X : MINIMUM, BASIC, EN16931 ou EXTENDED. (optional)
     * @param formatSortie Format de sortie : &#39;xml&#39; (XML seul) ou &#39;pdf&#39; (PDF Factur-X avec XML embarqué). (optional)
     * @param autoEnrichir 🆕 Activer l&#39;auto-enrichissement depuis SIRET/SIREN (format simplifié uniquement) (optional, default to true)
     * @param sourcePdf  (optional)
     * @return ReponseTache
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 202 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Données de facture invalides ou fichier PDF manquant </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Erreur de validation des données de la facture </td><td>  -  </td></tr>
     </table>
     */
    public ReponseTache genererFactureApiV1TraitementGenererFacturePost(@javax.annotation.Nonnull String donneesFacture, @javax.annotation.Nullable ProfilAPI profil, @javax.annotation.Nullable FormatSortie formatSortie, @javax.annotation.Nullable Boolean autoEnrichir, @javax.annotation.Nullable File sourcePdf) throws ApiException {
        ApiResponse<ReponseTache> localVarResp = genererFactureApiV1TraitementGenererFacturePostWithHttpInfo(donneesFacture, profil, formatSortie, autoEnrichir, sourcePdf);
        return localVarResp.getData();
    }

    /**
     * Générer une facture Factur-X
     * Génère une facture électronique au format Factur-X conforme aux normes européennes.  ## Normes appliquées  - **Factur-X** (France) : Norme FNFE-MPE (Forum National de la Facture Électronique) - **ZUGFeRD** (Allemagne) : Format allemand compatible Factur-X - **EN 16931** : Norme sémantique européenne pour la facturation électronique - **ISO 19005-3** (PDF/A-3) : Archivage électronique à long terme - **Cross Industry Invoice (CII)** : Syntaxe XML UN/CEFACT  ## 🆕 Nouveau : Format simplifié avec auto-enrichissement (P0.1)  Vous pouvez désormais créer une facture en fournissant uniquement : - Un numéro de facture - Un SIRET émetteur + **IBAN** (obligatoire) - Un SIRET destinataire - Les lignes de facture (description, quantité, prix HT)  **Exemple format simplifié** : &#x60;&#x60;&#x60;json {   \&quot;numero\&quot;: \&quot;FACT-2025-001\&quot;,   \&quot;emetteur\&quot;: {     \&quot;siret\&quot;: \&quot;92019522900017\&quot;,     \&quot;iban\&quot;: \&quot;FR7630001007941234567890185\&quot;   },   \&quot;destinataire\&quot;: {\&quot;siret\&quot;: \&quot;35600000000048\&quot;},   \&quot;lignes\&quot;: [     {\&quot;description\&quot;: \&quot;Prestation\&quot;, \&quot;quantite\&quot;: 10, \&quot;prix_ht\&quot;: 100.00, \&quot;tva\&quot;: 20.0}   ] } &#x60;&#x60;&#x60;  **⚠️ Champs obligatoires (format simplifié)** : - &#x60;numero&#x60; : Numéro de facture unique - &#x60;emetteur.siret&#x60; : SIRET de l&#39;émetteur (14 chiffres) - &#x60;emetteur.iban&#x60; : IBAN du compte bancaire (pas d&#39;API publique pour le récupérer) - &#x60;destinataire.siret&#x60; : SIRET du destinataire - &#x60;lignes[]&#x60; : Au moins une ligne de facture  **Ce qui se passe automatiquement avec &#x60;auto_enrichir&#x3D;True&#x60;** : - ✅ Enrichissement des noms depuis API Chorus Pro - ✅ Enrichissement des adresses depuis API Recherche Entreprises (gratuite, publique) - ✅ Calcul automatique de la TVA intracommunautaire (FR + clé + SIREN) - ✅ Récupération de l&#39;ID Chorus Pro pour la facturation électronique - ✅ Calcul des totaux HT/TVA/TTC - ✅ Génération des dates (aujourd&#39;hui + échéance 30j) - ✅ Gestion multi-taux de TVA  **Identifiants supportés** : - SIRET (14 chiffres) : Établissement précis ⭐ Recommandé - SIREN (9 chiffres) : Entreprise (sélection auto du siège) - Types spéciaux : UE_HORS_FRANCE, RIDET, TAHITI, etc.  ## Contrôles effectués lors de la génération  ### 1. Validation des données (Pydantic) - Types de données (montants en Decimal, dates ISO 8601) - Formats (SIRET 14 chiffres, SIREN 9 chiffres, IBAN) - Champs obligatoires selon le profil - Cohérence des montants (HT + TVA &#x3D; TTC)  ### 2. Génération XML conforme CII - Sérialisation selon schéma XSD Cross Industry Invoice - Namespaces UN/CEFACT corrects - Structure hiérarchique respectée - Encodage UTF-8 sans BOM  ### 3. Validation Schematron - Règles métier du profil sélectionné (MINIMUM, BASIC, EN16931, EXTENDED) - Cardinalité des éléments (obligatoire, optionnel, répétable) - Règles de calcul (totaux, TVA, remises) - Conformité européenne EN 16931  ### 4. Conversion PDF/A-3 (si format_sortie&#x3D;&#39;pdf&#39;) - Conversion du PDF source en PDF/A-3 via Ghostscript - Embarquement du XML Factur-X dans le PDF - Métadonnées XMP conformes - Profil ICC sRGB pour les couleurs - Suppression des éléments interdits (JavaScript, formulaires)  ## Fonctionnement  1. **Soumission** : La facture est mise en file d&#39;attente Celery pour traitement asynchrone 2. **Retour immédiat** : Vous recevez un &#x60;id_tache&#x60; (HTTP 202 Accepted) 3. **Suivi** : Utilisez l&#39;endpoint &#x60;/taches/{id_tache}/statut&#x60; pour suivre l&#39;avancement  ## Formats de sortie  - **xml** : Génère uniquement le XML Factur-X (recommandé pour les tests) - **pdf** : Génère un PDF/A-3 avec XML embarqué (nécessite &#x60;source_pdf&#x60;)  ## Profils Factur-X  - **MINIMUM** : Données minimales (facture simplifiée) - **BASIC** : Informations de base (PME) - **EN16931** : Standard européen (recommandé, conforme directive 2014/55/UE) - **EXTENDED** : Toutes les données disponibles (grands comptes)  ## Ce que vous obtenez  Après traitement réussi (statut &#x60;completed&#x60;) : - **XML seul** : Fichier XML encodé base64 conforme Factur-X - **PDF/A-3** : PDF avec XML embarqué, prêt pour envoi/archivage - **Métadonnées** : Profil, version Factur-X, taille fichier - **Validation** : Confirmation de conformité Schematron  ## Validation  Les données sont validées automatiquement selon le format détecté. En cas d&#39;erreur, un statut 422 est retourné avec les détails des champs invalides.
     * @param donneesFacture Données de la facture au format JSON.              Deux formats acceptés :             1. **Format classique** : Structure complète FactureFacturX (tous les champs)             2. **Format simplifié** (🆕 P0.1) : Structure minimale avec auto-enrichissement              Le format est détecté automatiquement !              (required)
     * @param profil Profil Factur-X : MINIMUM, BASIC, EN16931 ou EXTENDED. (optional)
     * @param formatSortie Format de sortie : &#39;xml&#39; (XML seul) ou &#39;pdf&#39; (PDF Factur-X avec XML embarqué). (optional)
     * @param autoEnrichir 🆕 Activer l&#39;auto-enrichissement depuis SIRET/SIREN (format simplifié uniquement) (optional, default to true)
     * @param sourcePdf  (optional)
     * @return ApiResponse&lt;ReponseTache&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 202 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Données de facture invalides ou fichier PDF manquant </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Erreur de validation des données de la facture </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<ReponseTache> genererFactureApiV1TraitementGenererFacturePostWithHttpInfo(@javax.annotation.Nonnull String donneesFacture, @javax.annotation.Nullable ProfilAPI profil, @javax.annotation.Nullable FormatSortie formatSortie, @javax.annotation.Nullable Boolean autoEnrichir, @javax.annotation.Nullable File sourcePdf) throws ApiException {
        okhttp3.Call localVarCall = genererFactureApiV1TraitementGenererFacturePostValidateBeforeCall(donneesFacture, profil, formatSortie, autoEnrichir, sourcePdf, null);
        Type localVarReturnType = new TypeToken<ReponseTache>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Générer une facture Factur-X (asynchronously)
     * Génère une facture électronique au format Factur-X conforme aux normes européennes.  ## Normes appliquées  - **Factur-X** (France) : Norme FNFE-MPE (Forum National de la Facture Électronique) - **ZUGFeRD** (Allemagne) : Format allemand compatible Factur-X - **EN 16931** : Norme sémantique européenne pour la facturation électronique - **ISO 19005-3** (PDF/A-3) : Archivage électronique à long terme - **Cross Industry Invoice (CII)** : Syntaxe XML UN/CEFACT  ## 🆕 Nouveau : Format simplifié avec auto-enrichissement (P0.1)  Vous pouvez désormais créer une facture en fournissant uniquement : - Un numéro de facture - Un SIRET émetteur + **IBAN** (obligatoire) - Un SIRET destinataire - Les lignes de facture (description, quantité, prix HT)  **Exemple format simplifié** : &#x60;&#x60;&#x60;json {   \&quot;numero\&quot;: \&quot;FACT-2025-001\&quot;,   \&quot;emetteur\&quot;: {     \&quot;siret\&quot;: \&quot;92019522900017\&quot;,     \&quot;iban\&quot;: \&quot;FR7630001007941234567890185\&quot;   },   \&quot;destinataire\&quot;: {\&quot;siret\&quot;: \&quot;35600000000048\&quot;},   \&quot;lignes\&quot;: [     {\&quot;description\&quot;: \&quot;Prestation\&quot;, \&quot;quantite\&quot;: 10, \&quot;prix_ht\&quot;: 100.00, \&quot;tva\&quot;: 20.0}   ] } &#x60;&#x60;&#x60;  **⚠️ Champs obligatoires (format simplifié)** : - &#x60;numero&#x60; : Numéro de facture unique - &#x60;emetteur.siret&#x60; : SIRET de l&#39;émetteur (14 chiffres) - &#x60;emetteur.iban&#x60; : IBAN du compte bancaire (pas d&#39;API publique pour le récupérer) - &#x60;destinataire.siret&#x60; : SIRET du destinataire - &#x60;lignes[]&#x60; : Au moins une ligne de facture  **Ce qui se passe automatiquement avec &#x60;auto_enrichir&#x3D;True&#x60;** : - ✅ Enrichissement des noms depuis API Chorus Pro - ✅ Enrichissement des adresses depuis API Recherche Entreprises (gratuite, publique) - ✅ Calcul automatique de la TVA intracommunautaire (FR + clé + SIREN) - ✅ Récupération de l&#39;ID Chorus Pro pour la facturation électronique - ✅ Calcul des totaux HT/TVA/TTC - ✅ Génération des dates (aujourd&#39;hui + échéance 30j) - ✅ Gestion multi-taux de TVA  **Identifiants supportés** : - SIRET (14 chiffres) : Établissement précis ⭐ Recommandé - SIREN (9 chiffres) : Entreprise (sélection auto du siège) - Types spéciaux : UE_HORS_FRANCE, RIDET, TAHITI, etc.  ## Contrôles effectués lors de la génération  ### 1. Validation des données (Pydantic) - Types de données (montants en Decimal, dates ISO 8601) - Formats (SIRET 14 chiffres, SIREN 9 chiffres, IBAN) - Champs obligatoires selon le profil - Cohérence des montants (HT + TVA &#x3D; TTC)  ### 2. Génération XML conforme CII - Sérialisation selon schéma XSD Cross Industry Invoice - Namespaces UN/CEFACT corrects - Structure hiérarchique respectée - Encodage UTF-8 sans BOM  ### 3. Validation Schematron - Règles métier du profil sélectionné (MINIMUM, BASIC, EN16931, EXTENDED) - Cardinalité des éléments (obligatoire, optionnel, répétable) - Règles de calcul (totaux, TVA, remises) - Conformité européenne EN 16931  ### 4. Conversion PDF/A-3 (si format_sortie&#x3D;&#39;pdf&#39;) - Conversion du PDF source en PDF/A-3 via Ghostscript - Embarquement du XML Factur-X dans le PDF - Métadonnées XMP conformes - Profil ICC sRGB pour les couleurs - Suppression des éléments interdits (JavaScript, formulaires)  ## Fonctionnement  1. **Soumission** : La facture est mise en file d&#39;attente Celery pour traitement asynchrone 2. **Retour immédiat** : Vous recevez un &#x60;id_tache&#x60; (HTTP 202 Accepted) 3. **Suivi** : Utilisez l&#39;endpoint &#x60;/taches/{id_tache}/statut&#x60; pour suivre l&#39;avancement  ## Formats de sortie  - **xml** : Génère uniquement le XML Factur-X (recommandé pour les tests) - **pdf** : Génère un PDF/A-3 avec XML embarqué (nécessite &#x60;source_pdf&#x60;)  ## Profils Factur-X  - **MINIMUM** : Données minimales (facture simplifiée) - **BASIC** : Informations de base (PME) - **EN16931** : Standard européen (recommandé, conforme directive 2014/55/UE) - **EXTENDED** : Toutes les données disponibles (grands comptes)  ## Ce que vous obtenez  Après traitement réussi (statut &#x60;completed&#x60;) : - **XML seul** : Fichier XML encodé base64 conforme Factur-X - **PDF/A-3** : PDF avec XML embarqué, prêt pour envoi/archivage - **Métadonnées** : Profil, version Factur-X, taille fichier - **Validation** : Confirmation de conformité Schematron  ## Validation  Les données sont validées automatiquement selon le format détecté. En cas d&#39;erreur, un statut 422 est retourné avec les détails des champs invalides.
     * @param donneesFacture Données de la facture au format JSON.              Deux formats acceptés :             1. **Format classique** : Structure complète FactureFacturX (tous les champs)             2. **Format simplifié** (🆕 P0.1) : Structure minimale avec auto-enrichissement              Le format est détecté automatiquement !              (required)
     * @param profil Profil Factur-X : MINIMUM, BASIC, EN16931 ou EXTENDED. (optional)
     * @param formatSortie Format de sortie : &#39;xml&#39; (XML seul) ou &#39;pdf&#39; (PDF Factur-X avec XML embarqué). (optional)
     * @param autoEnrichir 🆕 Activer l&#39;auto-enrichissement depuis SIRET/SIREN (format simplifié uniquement) (optional, default to true)
     * @param sourcePdf  (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 202 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Données de facture invalides ou fichier PDF manquant </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Erreur de validation des données de la facture </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call genererFactureApiV1TraitementGenererFacturePostAsync(@javax.annotation.Nonnull String donneesFacture, @javax.annotation.Nullable ProfilAPI profil, @javax.annotation.Nullable FormatSortie formatSortie, @javax.annotation.Nullable Boolean autoEnrichir, @javax.annotation.Nullable File sourcePdf, final ApiCallback<ReponseTache> _callback) throws ApiException {

        okhttp3.Call localVarCall = genererFactureApiV1TraitementGenererFacturePostValidateBeforeCall(donneesFacture, profil, formatSortie, autoEnrichir, sourcePdf, _callback);
        Type localVarReturnType = new TypeToken<ReponseTache>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for obtenirStatutTacheApiV1TraitementTachesIdTacheStatutGet
     * @param idTache  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> État actuel de la tâche </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call obtenirStatutTacheApiV1TraitementTachesIdTacheStatutGetCall(@javax.annotation.Nonnull String idTache, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/v1/traitement/taches/{id_tache}/statut"
            .replace("{" + "id_tache" + "}", localVarApiClient.escapeString(idTache.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "HTTPBearer" };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call obtenirStatutTacheApiV1TraitementTachesIdTacheStatutGetValidateBeforeCall(@javax.annotation.Nonnull String idTache, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'idTache' is set
        if (idTache == null) {
            throw new ApiException("Missing the required parameter 'idTache' when calling obtenirStatutTacheApiV1TraitementTachesIdTacheStatutGet(Async)");
        }

        return obtenirStatutTacheApiV1TraitementTachesIdTacheStatutGetCall(idTache, _callback);

    }

    /**
     * Obtenir le statut d&#39;une tâche de génération
     * Récupère l&#39;état d&#39;avancement d&#39;une tâche de génération de facture.  ## États possibles  - **PENDING** : Tâche en attente de traitement - **STARTED** : Tâche en cours d&#39;exécution - **SUCCESS** : Tâche terminée (vérifier &#x60;resultat.statut&#x60; pour le résultat réel) - **FAILURE** : Erreur système lors de l&#39;exécution - **RETRY** : Tentative de ré-exécution en cours  ## Champ resultat  Quand la tâche est terminée (SUCCESS), le champ &#x60;resultat&#x60; contient : - &#x60;statut&#x60; : \&quot;SUCCES\&quot; ou \&quot;ERREUR\&quot; - &#x60;chemin_fichier&#x60; : Chemin du fichier généré (si succès) - &#x60;message_erreur&#x60; : Détails de l&#39;erreur (si échec)  ## Usage  Appelez cet endpoint en boucle (polling) toutes les 2-3 secondes jusqu&#39;à ce que le statut soit SUCCESS ou FAILURE.
     * @param idTache  (required)
     * @return StatutTache
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> État actuel de la tâche </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public StatutTache obtenirStatutTacheApiV1TraitementTachesIdTacheStatutGet(@javax.annotation.Nonnull String idTache) throws ApiException {
        ApiResponse<StatutTache> localVarResp = obtenirStatutTacheApiV1TraitementTachesIdTacheStatutGetWithHttpInfo(idTache);
        return localVarResp.getData();
    }

    /**
     * Obtenir le statut d&#39;une tâche de génération
     * Récupère l&#39;état d&#39;avancement d&#39;une tâche de génération de facture.  ## États possibles  - **PENDING** : Tâche en attente de traitement - **STARTED** : Tâche en cours d&#39;exécution - **SUCCESS** : Tâche terminée (vérifier &#x60;resultat.statut&#x60; pour le résultat réel) - **FAILURE** : Erreur système lors de l&#39;exécution - **RETRY** : Tentative de ré-exécution en cours  ## Champ resultat  Quand la tâche est terminée (SUCCESS), le champ &#x60;resultat&#x60; contient : - &#x60;statut&#x60; : \&quot;SUCCES\&quot; ou \&quot;ERREUR\&quot; - &#x60;chemin_fichier&#x60; : Chemin du fichier généré (si succès) - &#x60;message_erreur&#x60; : Détails de l&#39;erreur (si échec)  ## Usage  Appelez cet endpoint en boucle (polling) toutes les 2-3 secondes jusqu&#39;à ce que le statut soit SUCCESS ou FAILURE.
     * @param idTache  (required)
     * @return ApiResponse&lt;StatutTache&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> État actuel de la tâche </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<StatutTache> obtenirStatutTacheApiV1TraitementTachesIdTacheStatutGetWithHttpInfo(@javax.annotation.Nonnull String idTache) throws ApiException {
        okhttp3.Call localVarCall = obtenirStatutTacheApiV1TraitementTachesIdTacheStatutGetValidateBeforeCall(idTache, null);
        Type localVarReturnType = new TypeToken<StatutTache>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Obtenir le statut d&#39;une tâche de génération (asynchronously)
     * Récupère l&#39;état d&#39;avancement d&#39;une tâche de génération de facture.  ## États possibles  - **PENDING** : Tâche en attente de traitement - **STARTED** : Tâche en cours d&#39;exécution - **SUCCESS** : Tâche terminée (vérifier &#x60;resultat.statut&#x60; pour le résultat réel) - **FAILURE** : Erreur système lors de l&#39;exécution - **RETRY** : Tentative de ré-exécution en cours  ## Champ resultat  Quand la tâche est terminée (SUCCESS), le champ &#x60;resultat&#x60; contient : - &#x60;statut&#x60; : \&quot;SUCCES\&quot; ou \&quot;ERREUR\&quot; - &#x60;chemin_fichier&#x60; : Chemin du fichier généré (si succès) - &#x60;message_erreur&#x60; : Détails de l&#39;erreur (si échec)  ## Usage  Appelez cet endpoint en boucle (polling) toutes les 2-3 secondes jusqu&#39;à ce que le statut soit SUCCESS ou FAILURE.
     * @param idTache  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> État actuel de la tâche </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call obtenirStatutTacheApiV1TraitementTachesIdTacheStatutGetAsync(@javax.annotation.Nonnull String idTache, final ApiCallback<StatutTache> _callback) throws ApiException {

        okhttp3.Call localVarCall = obtenirStatutTacheApiV1TraitementTachesIdTacheStatutGetValidateBeforeCall(idTache, _callback);
        Type localVarReturnType = new TypeToken<StatutTache>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for signerPdfApiV1TraitementSignerPdfPost
     * @param fichierPdf Fichier PDF à signer (sera traité puis retourné signé en base64) (required)
     * @param raison  (optional)
     * @param localisation  (optional)
     * @param contact  (optional)
     * @param fieldName Nom du champ de signature PDF (optional, default to FactPulseSignature)
     * @param usePadesLt Activer PAdES-B-LT (archivage long terme avec données de validation embarquées). NÉCESSITE un certificat avec accès OCSP/CRL. (optional, default to false)
     * @param useTimestamp Activer l&#39;horodatage RFC 3161 avec FreeTSA (PAdES-B-T) (optional, default to true)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> PDF signé avec succès </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Certificat invalide ou expiré </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Aucun certificat configuré pour ce client </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié - Token JWT manquant ou invalide </td><td>  -  </td></tr>
        <tr><td> 503 </td><td> Service Django inaccessible </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call signerPdfApiV1TraitementSignerPdfPostCall(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable String raison, @javax.annotation.Nullable String localisation, @javax.annotation.Nullable String contact, @javax.annotation.Nullable String fieldName, @javax.annotation.Nullable Boolean usePadesLt, @javax.annotation.Nullable Boolean useTimestamp, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/v1/traitement/signer-pdf";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (fichierPdf != null) {
            localVarFormParams.put("fichier_pdf", fichierPdf);
        }

        if (raison != null) {
            localVarFormParams.put("raison", raison);
        }

        if (localisation != null) {
            localVarFormParams.put("localisation", localisation);
        }

        if (contact != null) {
            localVarFormParams.put("contact", contact);
        }

        if (fieldName != null) {
            localVarFormParams.put("field_name", fieldName);
        }

        if (usePadesLt != null) {
            localVarFormParams.put("use_pades_lt", usePadesLt);
        }

        if (useTimestamp != null) {
            localVarFormParams.put("use_timestamp", useTimestamp);
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "multipart/form-data"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "HTTPBearer" };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call signerPdfApiV1TraitementSignerPdfPostValidateBeforeCall(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable String raison, @javax.annotation.Nullable String localisation, @javax.annotation.Nullable String contact, @javax.annotation.Nullable String fieldName, @javax.annotation.Nullable Boolean usePadesLt, @javax.annotation.Nullable Boolean useTimestamp, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'fichierPdf' is set
        if (fichierPdf == null) {
            throw new ApiException("Missing the required parameter 'fichierPdf' when calling signerPdfApiV1TraitementSignerPdfPost(Async)");
        }

        return signerPdfApiV1TraitementSignerPdfPostCall(fichierPdf, raison, localisation, contact, fieldName, usePadesLt, useTimestamp, _callback);

    }

    /**
     * Signer un PDF avec le certificat du client (PAdES-B-LT)
     * Signe un PDF uploadé avec le certificat électronique configuré pour le client (via client_uid du JWT).      **Standards supportés** : PAdES-B-B, PAdES-B-T (horodatage), PAdES-B-LT (archivage long terme).      **Niveaux eIDAS** : SES (auto-signé), AdES (CA commerciale), QES (PSCO - hors scope).      **⚠️ Disclaimer légal** : Les signatures générées sont des cachets électroniques au sens     du règlement eIDAS. Le niveau de validité juridique dépend du certificat utilisé (SES/AdES/QES).     FactPulse ne fournit pas de certificats qualifiés QES - vous devez obtenir un certificat auprès     d&#39;un PSCO (Prestataire de Services de Confiance qualifié) pour une validité juridique maximale.
     * @param fichierPdf Fichier PDF à signer (sera traité puis retourné signé en base64) (required)
     * @param raison  (optional)
     * @param localisation  (optional)
     * @param contact  (optional)
     * @param fieldName Nom du champ de signature PDF (optional, default to FactPulseSignature)
     * @param usePadesLt Activer PAdES-B-LT (archivage long terme avec données de validation embarquées). NÉCESSITE un certificat avec accès OCSP/CRL. (optional, default to false)
     * @param useTimestamp Activer l&#39;horodatage RFC 3161 avec FreeTSA (PAdES-B-T) (optional, default to true)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> PDF signé avec succès </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Certificat invalide ou expiré </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Aucun certificat configuré pour ce client </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié - Token JWT manquant ou invalide </td><td>  -  </td></tr>
        <tr><td> 503 </td><td> Service Django inaccessible </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object signerPdfApiV1TraitementSignerPdfPost(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable String raison, @javax.annotation.Nullable String localisation, @javax.annotation.Nullable String contact, @javax.annotation.Nullable String fieldName, @javax.annotation.Nullable Boolean usePadesLt, @javax.annotation.Nullable Boolean useTimestamp) throws ApiException {
        ApiResponse<Object> localVarResp = signerPdfApiV1TraitementSignerPdfPostWithHttpInfo(fichierPdf, raison, localisation, contact, fieldName, usePadesLt, useTimestamp);
        return localVarResp.getData();
    }

    /**
     * Signer un PDF avec le certificat du client (PAdES-B-LT)
     * Signe un PDF uploadé avec le certificat électronique configuré pour le client (via client_uid du JWT).      **Standards supportés** : PAdES-B-B, PAdES-B-T (horodatage), PAdES-B-LT (archivage long terme).      **Niveaux eIDAS** : SES (auto-signé), AdES (CA commerciale), QES (PSCO - hors scope).      **⚠️ Disclaimer légal** : Les signatures générées sont des cachets électroniques au sens     du règlement eIDAS. Le niveau de validité juridique dépend du certificat utilisé (SES/AdES/QES).     FactPulse ne fournit pas de certificats qualifiés QES - vous devez obtenir un certificat auprès     d&#39;un PSCO (Prestataire de Services de Confiance qualifié) pour une validité juridique maximale.
     * @param fichierPdf Fichier PDF à signer (sera traité puis retourné signé en base64) (required)
     * @param raison  (optional)
     * @param localisation  (optional)
     * @param contact  (optional)
     * @param fieldName Nom du champ de signature PDF (optional, default to FactPulseSignature)
     * @param usePadesLt Activer PAdES-B-LT (archivage long terme avec données de validation embarquées). NÉCESSITE un certificat avec accès OCSP/CRL. (optional, default to false)
     * @param useTimestamp Activer l&#39;horodatage RFC 3161 avec FreeTSA (PAdES-B-T) (optional, default to true)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> PDF signé avec succès </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Certificat invalide ou expiré </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Aucun certificat configuré pour ce client </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié - Token JWT manquant ou invalide </td><td>  -  </td></tr>
        <tr><td> 503 </td><td> Service Django inaccessible </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> signerPdfApiV1TraitementSignerPdfPostWithHttpInfo(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable String raison, @javax.annotation.Nullable String localisation, @javax.annotation.Nullable String contact, @javax.annotation.Nullable String fieldName, @javax.annotation.Nullable Boolean usePadesLt, @javax.annotation.Nullable Boolean useTimestamp) throws ApiException {
        okhttp3.Call localVarCall = signerPdfApiV1TraitementSignerPdfPostValidateBeforeCall(fichierPdf, raison, localisation, contact, fieldName, usePadesLt, useTimestamp, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Signer un PDF avec le certificat du client (PAdES-B-LT) (asynchronously)
     * Signe un PDF uploadé avec le certificat électronique configuré pour le client (via client_uid du JWT).      **Standards supportés** : PAdES-B-B, PAdES-B-T (horodatage), PAdES-B-LT (archivage long terme).      **Niveaux eIDAS** : SES (auto-signé), AdES (CA commerciale), QES (PSCO - hors scope).      **⚠️ Disclaimer légal** : Les signatures générées sont des cachets électroniques au sens     du règlement eIDAS. Le niveau de validité juridique dépend du certificat utilisé (SES/AdES/QES).     FactPulse ne fournit pas de certificats qualifiés QES - vous devez obtenir un certificat auprès     d&#39;un PSCO (Prestataire de Services de Confiance qualifié) pour une validité juridique maximale.
     * @param fichierPdf Fichier PDF à signer (sera traité puis retourné signé en base64) (required)
     * @param raison  (optional)
     * @param localisation  (optional)
     * @param contact  (optional)
     * @param fieldName Nom du champ de signature PDF (optional, default to FactPulseSignature)
     * @param usePadesLt Activer PAdES-B-LT (archivage long terme avec données de validation embarquées). NÉCESSITE un certificat avec accès OCSP/CRL. (optional, default to false)
     * @param useTimestamp Activer l&#39;horodatage RFC 3161 avec FreeTSA (PAdES-B-T) (optional, default to true)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> PDF signé avec succès </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Certificat invalide ou expiré </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Aucun certificat configuré pour ce client </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié - Token JWT manquant ou invalide </td><td>  -  </td></tr>
        <tr><td> 503 </td><td> Service Django inaccessible </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call signerPdfApiV1TraitementSignerPdfPostAsync(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable String raison, @javax.annotation.Nullable String localisation, @javax.annotation.Nullable String contact, @javax.annotation.Nullable String fieldName, @javax.annotation.Nullable Boolean usePadesLt, @javax.annotation.Nullable Boolean useTimestamp, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = signerPdfApiV1TraitementSignerPdfPostValidateBeforeCall(fichierPdf, raison, localisation, contact, fieldName, usePadesLt, useTimestamp, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for signerPdfAsyncApiV1TraitementSignerPdfAsyncPost
     * @param fichierPdf Fichier PDF à signer (traité de manière asynchrone) (required)
     * @param raison  (optional)
     * @param localisation  (optional)
     * @param contact  (optional)
     * @param fieldName Nom du champ de signature PDF (optional, default to FactPulseSignature)
     * @param usePadesLt Activer PAdES-B-LT (archivage long terme avec données de validation embarquées). NÉCESSITE un certificat avec accès OCSP/CRL. (optional, default to false)
     * @param useTimestamp Activer l&#39;horodatage RFC 3161 avec FreeTSA (PAdES-B-T) (optional, default to true)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 202 </td><td> Tâche de signature créée avec succès </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Paramètres invalides </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié - Token JWT manquant ou invalide </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call signerPdfAsyncApiV1TraitementSignerPdfAsyncPostCall(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable String raison, @javax.annotation.Nullable String localisation, @javax.annotation.Nullable String contact, @javax.annotation.Nullable String fieldName, @javax.annotation.Nullable Boolean usePadesLt, @javax.annotation.Nullable Boolean useTimestamp, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/v1/traitement/signer-pdf-async";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (fichierPdf != null) {
            localVarFormParams.put("fichier_pdf", fichierPdf);
        }

        if (raison != null) {
            localVarFormParams.put("raison", raison);
        }

        if (localisation != null) {
            localVarFormParams.put("localisation", localisation);
        }

        if (contact != null) {
            localVarFormParams.put("contact", contact);
        }

        if (fieldName != null) {
            localVarFormParams.put("field_name", fieldName);
        }

        if (usePadesLt != null) {
            localVarFormParams.put("use_pades_lt", usePadesLt);
        }

        if (useTimestamp != null) {
            localVarFormParams.put("use_timestamp", useTimestamp);
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "multipart/form-data"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "HTTPBearer" };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call signerPdfAsyncApiV1TraitementSignerPdfAsyncPostValidateBeforeCall(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable String raison, @javax.annotation.Nullable String localisation, @javax.annotation.Nullable String contact, @javax.annotation.Nullable String fieldName, @javax.annotation.Nullable Boolean usePadesLt, @javax.annotation.Nullable Boolean useTimestamp, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'fichierPdf' is set
        if (fichierPdf == null) {
            throw new ApiException("Missing the required parameter 'fichierPdf' when calling signerPdfAsyncApiV1TraitementSignerPdfAsyncPost(Async)");
        }

        return signerPdfAsyncApiV1TraitementSignerPdfAsyncPostCall(fichierPdf, raison, localisation, contact, fieldName, usePadesLt, useTimestamp, _callback);

    }

    /**
     * Signer un PDF de manière asynchrone (Celery)
     * Signe un PDF uploadé de manière asynchrone via une tâche Celery.      **Différence avec /signer-pdf** :     - &#x60;/signer-pdf&#x60; : Signature synchrone (blocage jusqu&#39;à la fin)     - &#x60;/signer-pdf-async&#x60; : Signature asynchrone (retourne immédiatement un task_id)      **Avantages de l&#39;async** :     - Pas de timeout pour les gros fichiers     - Pas de blocage du worker FastAPI     - Possibilité de suivre la progression via le task_id     - Idéal pour les traitements par lot      **Standards supportés** : PAdES-B-B, PAdES-B-T (horodatage), PAdES-B-LT (archivage long terme).      **⚠️ Disclaimer légal** : Identique à /signer-pdf (voir documentation de cet endpoint).
     * @param fichierPdf Fichier PDF à signer (traité de manière asynchrone) (required)
     * @param raison  (optional)
     * @param localisation  (optional)
     * @param contact  (optional)
     * @param fieldName Nom du champ de signature PDF (optional, default to FactPulseSignature)
     * @param usePadesLt Activer PAdES-B-LT (archivage long terme avec données de validation embarquées). NÉCESSITE un certificat avec accès OCSP/CRL. (optional, default to false)
     * @param useTimestamp Activer l&#39;horodatage RFC 3161 avec FreeTSA (PAdES-B-T) (optional, default to true)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 202 </td><td> Tâche de signature créée avec succès </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Paramètres invalides </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié - Token JWT manquant ou invalide </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object signerPdfAsyncApiV1TraitementSignerPdfAsyncPost(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable String raison, @javax.annotation.Nullable String localisation, @javax.annotation.Nullable String contact, @javax.annotation.Nullable String fieldName, @javax.annotation.Nullable Boolean usePadesLt, @javax.annotation.Nullable Boolean useTimestamp) throws ApiException {
        ApiResponse<Object> localVarResp = signerPdfAsyncApiV1TraitementSignerPdfAsyncPostWithHttpInfo(fichierPdf, raison, localisation, contact, fieldName, usePadesLt, useTimestamp);
        return localVarResp.getData();
    }

    /**
     * Signer un PDF de manière asynchrone (Celery)
     * Signe un PDF uploadé de manière asynchrone via une tâche Celery.      **Différence avec /signer-pdf** :     - &#x60;/signer-pdf&#x60; : Signature synchrone (blocage jusqu&#39;à la fin)     - &#x60;/signer-pdf-async&#x60; : Signature asynchrone (retourne immédiatement un task_id)      **Avantages de l&#39;async** :     - Pas de timeout pour les gros fichiers     - Pas de blocage du worker FastAPI     - Possibilité de suivre la progression via le task_id     - Idéal pour les traitements par lot      **Standards supportés** : PAdES-B-B, PAdES-B-T (horodatage), PAdES-B-LT (archivage long terme).      **⚠️ Disclaimer légal** : Identique à /signer-pdf (voir documentation de cet endpoint).
     * @param fichierPdf Fichier PDF à signer (traité de manière asynchrone) (required)
     * @param raison  (optional)
     * @param localisation  (optional)
     * @param contact  (optional)
     * @param fieldName Nom du champ de signature PDF (optional, default to FactPulseSignature)
     * @param usePadesLt Activer PAdES-B-LT (archivage long terme avec données de validation embarquées). NÉCESSITE un certificat avec accès OCSP/CRL. (optional, default to false)
     * @param useTimestamp Activer l&#39;horodatage RFC 3161 avec FreeTSA (PAdES-B-T) (optional, default to true)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 202 </td><td> Tâche de signature créée avec succès </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Paramètres invalides </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié - Token JWT manquant ou invalide </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> signerPdfAsyncApiV1TraitementSignerPdfAsyncPostWithHttpInfo(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable String raison, @javax.annotation.Nullable String localisation, @javax.annotation.Nullable String contact, @javax.annotation.Nullable String fieldName, @javax.annotation.Nullable Boolean usePadesLt, @javax.annotation.Nullable Boolean useTimestamp) throws ApiException {
        okhttp3.Call localVarCall = signerPdfAsyncApiV1TraitementSignerPdfAsyncPostValidateBeforeCall(fichierPdf, raison, localisation, contact, fieldName, usePadesLt, useTimestamp, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Signer un PDF de manière asynchrone (Celery) (asynchronously)
     * Signe un PDF uploadé de manière asynchrone via une tâche Celery.      **Différence avec /signer-pdf** :     - &#x60;/signer-pdf&#x60; : Signature synchrone (blocage jusqu&#39;à la fin)     - &#x60;/signer-pdf-async&#x60; : Signature asynchrone (retourne immédiatement un task_id)      **Avantages de l&#39;async** :     - Pas de timeout pour les gros fichiers     - Pas de blocage du worker FastAPI     - Possibilité de suivre la progression via le task_id     - Idéal pour les traitements par lot      **Standards supportés** : PAdES-B-B, PAdES-B-T (horodatage), PAdES-B-LT (archivage long terme).      **⚠️ Disclaimer légal** : Identique à /signer-pdf (voir documentation de cet endpoint).
     * @param fichierPdf Fichier PDF à signer (traité de manière asynchrone) (required)
     * @param raison  (optional)
     * @param localisation  (optional)
     * @param contact  (optional)
     * @param fieldName Nom du champ de signature PDF (optional, default to FactPulseSignature)
     * @param usePadesLt Activer PAdES-B-LT (archivage long terme avec données de validation embarquées). NÉCESSITE un certificat avec accès OCSP/CRL. (optional, default to false)
     * @param useTimestamp Activer l&#39;horodatage RFC 3161 avec FreeTSA (PAdES-B-T) (optional, default to true)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 202 </td><td> Tâche de signature créée avec succès </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Paramètres invalides </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié - Token JWT manquant ou invalide </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call signerPdfAsyncApiV1TraitementSignerPdfAsyncPostAsync(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable String raison, @javax.annotation.Nullable String localisation, @javax.annotation.Nullable String contact, @javax.annotation.Nullable String fieldName, @javax.annotation.Nullable Boolean usePadesLt, @javax.annotation.Nullable Boolean useTimestamp, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = signerPdfAsyncApiV1TraitementSignerPdfAsyncPostValidateBeforeCall(fichierPdf, raison, localisation, contact, fieldName, usePadesLt, useTimestamp, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for soumettreFactureCompleteApiV1TraitementFacturesSoumettreCompletePost
     * @param soumettreFactureCompleteRequest  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call soumettreFactureCompleteApiV1TraitementFacturesSoumettreCompletePostCall(@javax.annotation.Nonnull SoumettreFactureCompleteRequest soumettreFactureCompleteRequest, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = soumettreFactureCompleteRequest;

        // create path and map variables
        String localVarPath = "/api/v1/traitement/factures/soumettre-complete";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "HTTPBearer" };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call soumettreFactureCompleteApiV1TraitementFacturesSoumettreCompletePostValidateBeforeCall(@javax.annotation.Nonnull SoumettreFactureCompleteRequest soumettreFactureCompleteRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'soumettreFactureCompleteRequest' is set
        if (soumettreFactureCompleteRequest == null) {
            throw new ApiException("Missing the required parameter 'soumettreFactureCompleteRequest' when calling soumettreFactureCompleteApiV1TraitementFacturesSoumettreCompletePost(Async)");
        }

        return soumettreFactureCompleteApiV1TraitementFacturesSoumettreCompletePostCall(soumettreFactureCompleteRequest, _callback);

    }

    /**
     * Soumettre une facture complète (génération + signature + soumission)
     * Endpoint unifié pour soumettre une facture complète vers différentes destinations.      **Workflow automatisé :**     1. **Auto-enrichissement** (optionnel) : récupère les données via APIs publiques et Chorus Pro/AFNOR     2. **Génération PDF Factur-X** : crée un PDF/A-3 avec XML embarqué     3. **Signature électronique** (optionnelle) : signe le PDF avec un certificat     4. **Soumission** : envoie vers la destination choisie (Chorus Pro ou AFNOR PDP)      **Destinations supportées :**     - **Chorus Pro** : plateforme B2G française (factures vers secteur public)     - **AFNOR PDP** : Plateformes de Dématérialisation Partenaires      **Credentials de destination - 2 modes disponibles :**      **Mode 1 - Récupération via JWT (recommandé) :**     - Les credentials sont récupérés automatiquement via le &#x60;client_uid&#x60; du JWT     - Ne pas fournir le champ &#x60;credentials&#x60; dans &#x60;destination&#x60;     - Architecture 0-trust : aucun secret dans le payload     - Exemple : &#x60;\&quot;destination\&quot;: {\&quot;type\&quot;: \&quot;chorus_pro\&quot;}&#x60;      **Mode 2 - Credentials dans le payload :**     - Fournir les credentials directement dans le payload     - Utile pour tests ou intégrations tierces     - Exemple : &#x60;\&quot;destination\&quot;: {\&quot;type\&quot;: \&quot;chorus_pro\&quot;, \&quot;credentials\&quot;: {...}}&#x60;       **Signature électronique (optionnelle) - 2 modes disponibles :**      **Mode 1 - Certificat stocké (recommandé) :**     - Le certificat est récupéré automatiquement via le &#x60;client_uid&#x60; du JWT     - Aucune clé à fournir dans le payload     - Signature PAdES-B-LT avec horodatage (conforme eIDAS)     - Exemple : &#x60;\&quot;signature\&quot;: {\&quot;raison\&quot;: \&quot;Conformité Factur-X\&quot;}&#x60;      **Mode 2 - Clés dans le payload (pour tests) :**     - Fournir &#x60;key_pem&#x60; et &#x60;cert_pem&#x60; directement     - Format PEM accepté : brut ou base64     - Utile pour tests ou cas spéciaux sans certificat stocké     - Exemple : &#x60;\&quot;signature\&quot;: {\&quot;key_pem\&quot;: \&quot;-----BEGIN...\&quot;, \&quot;cert_pem\&quot;: \&quot;-----BEGIN...\&quot;}&#x60;      Si &#x60;key_pem&#x60; et &#x60;cert_pem&#x60; sont fournis → Mode 2     Sinon → Mode 1 (certificat récupéré via &#x60;client_uid&#x60;)
     * @param soumettreFactureCompleteRequest  (required)
     * @return SoumettreFactureCompleteResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public SoumettreFactureCompleteResponse soumettreFactureCompleteApiV1TraitementFacturesSoumettreCompletePost(@javax.annotation.Nonnull SoumettreFactureCompleteRequest soumettreFactureCompleteRequest) throws ApiException {
        ApiResponse<SoumettreFactureCompleteResponse> localVarResp = soumettreFactureCompleteApiV1TraitementFacturesSoumettreCompletePostWithHttpInfo(soumettreFactureCompleteRequest);
        return localVarResp.getData();
    }

    /**
     * Soumettre une facture complète (génération + signature + soumission)
     * Endpoint unifié pour soumettre une facture complète vers différentes destinations.      **Workflow automatisé :**     1. **Auto-enrichissement** (optionnel) : récupère les données via APIs publiques et Chorus Pro/AFNOR     2. **Génération PDF Factur-X** : crée un PDF/A-3 avec XML embarqué     3. **Signature électronique** (optionnelle) : signe le PDF avec un certificat     4. **Soumission** : envoie vers la destination choisie (Chorus Pro ou AFNOR PDP)      **Destinations supportées :**     - **Chorus Pro** : plateforme B2G française (factures vers secteur public)     - **AFNOR PDP** : Plateformes de Dématérialisation Partenaires      **Credentials de destination - 2 modes disponibles :**      **Mode 1 - Récupération via JWT (recommandé) :**     - Les credentials sont récupérés automatiquement via le &#x60;client_uid&#x60; du JWT     - Ne pas fournir le champ &#x60;credentials&#x60; dans &#x60;destination&#x60;     - Architecture 0-trust : aucun secret dans le payload     - Exemple : &#x60;\&quot;destination\&quot;: {\&quot;type\&quot;: \&quot;chorus_pro\&quot;}&#x60;      **Mode 2 - Credentials dans le payload :**     - Fournir les credentials directement dans le payload     - Utile pour tests ou intégrations tierces     - Exemple : &#x60;\&quot;destination\&quot;: {\&quot;type\&quot;: \&quot;chorus_pro\&quot;, \&quot;credentials\&quot;: {...}}&#x60;       **Signature électronique (optionnelle) - 2 modes disponibles :**      **Mode 1 - Certificat stocké (recommandé) :**     - Le certificat est récupéré automatiquement via le &#x60;client_uid&#x60; du JWT     - Aucune clé à fournir dans le payload     - Signature PAdES-B-LT avec horodatage (conforme eIDAS)     - Exemple : &#x60;\&quot;signature\&quot;: {\&quot;raison\&quot;: \&quot;Conformité Factur-X\&quot;}&#x60;      **Mode 2 - Clés dans le payload (pour tests) :**     - Fournir &#x60;key_pem&#x60; et &#x60;cert_pem&#x60; directement     - Format PEM accepté : brut ou base64     - Utile pour tests ou cas spéciaux sans certificat stocké     - Exemple : &#x60;\&quot;signature\&quot;: {\&quot;key_pem\&quot;: \&quot;-----BEGIN...\&quot;, \&quot;cert_pem\&quot;: \&quot;-----BEGIN...\&quot;}&#x60;      Si &#x60;key_pem&#x60; et &#x60;cert_pem&#x60; sont fournis → Mode 2     Sinon → Mode 1 (certificat récupéré via &#x60;client_uid&#x60;)
     * @param soumettreFactureCompleteRequest  (required)
     * @return ApiResponse&lt;SoumettreFactureCompleteResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<SoumettreFactureCompleteResponse> soumettreFactureCompleteApiV1TraitementFacturesSoumettreCompletePostWithHttpInfo(@javax.annotation.Nonnull SoumettreFactureCompleteRequest soumettreFactureCompleteRequest) throws ApiException {
        okhttp3.Call localVarCall = soumettreFactureCompleteApiV1TraitementFacturesSoumettreCompletePostValidateBeforeCall(soumettreFactureCompleteRequest, null);
        Type localVarReturnType = new TypeToken<SoumettreFactureCompleteResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Soumettre une facture complète (génération + signature + soumission) (asynchronously)
     * Endpoint unifié pour soumettre une facture complète vers différentes destinations.      **Workflow automatisé :**     1. **Auto-enrichissement** (optionnel) : récupère les données via APIs publiques et Chorus Pro/AFNOR     2. **Génération PDF Factur-X** : crée un PDF/A-3 avec XML embarqué     3. **Signature électronique** (optionnelle) : signe le PDF avec un certificat     4. **Soumission** : envoie vers la destination choisie (Chorus Pro ou AFNOR PDP)      **Destinations supportées :**     - **Chorus Pro** : plateforme B2G française (factures vers secteur public)     - **AFNOR PDP** : Plateformes de Dématérialisation Partenaires      **Credentials de destination - 2 modes disponibles :**      **Mode 1 - Récupération via JWT (recommandé) :**     - Les credentials sont récupérés automatiquement via le &#x60;client_uid&#x60; du JWT     - Ne pas fournir le champ &#x60;credentials&#x60; dans &#x60;destination&#x60;     - Architecture 0-trust : aucun secret dans le payload     - Exemple : &#x60;\&quot;destination\&quot;: {\&quot;type\&quot;: \&quot;chorus_pro\&quot;}&#x60;      **Mode 2 - Credentials dans le payload :**     - Fournir les credentials directement dans le payload     - Utile pour tests ou intégrations tierces     - Exemple : &#x60;\&quot;destination\&quot;: {\&quot;type\&quot;: \&quot;chorus_pro\&quot;, \&quot;credentials\&quot;: {...}}&#x60;       **Signature électronique (optionnelle) - 2 modes disponibles :**      **Mode 1 - Certificat stocké (recommandé) :**     - Le certificat est récupéré automatiquement via le &#x60;client_uid&#x60; du JWT     - Aucune clé à fournir dans le payload     - Signature PAdES-B-LT avec horodatage (conforme eIDAS)     - Exemple : &#x60;\&quot;signature\&quot;: {\&quot;raison\&quot;: \&quot;Conformité Factur-X\&quot;}&#x60;      **Mode 2 - Clés dans le payload (pour tests) :**     - Fournir &#x60;key_pem&#x60; et &#x60;cert_pem&#x60; directement     - Format PEM accepté : brut ou base64     - Utile pour tests ou cas spéciaux sans certificat stocké     - Exemple : &#x60;\&quot;signature\&quot;: {\&quot;key_pem\&quot;: \&quot;-----BEGIN...\&quot;, \&quot;cert_pem\&quot;: \&quot;-----BEGIN...\&quot;}&#x60;      Si &#x60;key_pem&#x60; et &#x60;cert_pem&#x60; sont fournis → Mode 2     Sinon → Mode 1 (certificat récupéré via &#x60;client_uid&#x60;)
     * @param soumettreFactureCompleteRequest  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call soumettreFactureCompleteApiV1TraitementFacturesSoumettreCompletePostAsync(@javax.annotation.Nonnull SoumettreFactureCompleteRequest soumettreFactureCompleteRequest, final ApiCallback<SoumettreFactureCompleteResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = soumettreFactureCompleteApiV1TraitementFacturesSoumettreCompletePostValidateBeforeCall(soumettreFactureCompleteRequest, _callback);
        Type localVarReturnType = new TypeToken<SoumettreFactureCompleteResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for soumettreFactureCompleteAsyncApiV1TraitementFacturesSoumettreCompleteAsyncPost
     * @param soumettreFactureCompleteRequest  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 202 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call soumettreFactureCompleteAsyncApiV1TraitementFacturesSoumettreCompleteAsyncPostCall(@javax.annotation.Nonnull SoumettreFactureCompleteRequest soumettreFactureCompleteRequest, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = soumettreFactureCompleteRequest;

        // create path and map variables
        String localVarPath = "/api/v1/traitement/factures/soumettre-complete-async";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "HTTPBearer" };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call soumettreFactureCompleteAsyncApiV1TraitementFacturesSoumettreCompleteAsyncPostValidateBeforeCall(@javax.annotation.Nonnull SoumettreFactureCompleteRequest soumettreFactureCompleteRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'soumettreFactureCompleteRequest' is set
        if (soumettreFactureCompleteRequest == null) {
            throw new ApiException("Missing the required parameter 'soumettreFactureCompleteRequest' when calling soumettreFactureCompleteAsyncApiV1TraitementFacturesSoumettreCompleteAsyncPost(Async)");
        }

        return soumettreFactureCompleteAsyncApiV1TraitementFacturesSoumettreCompleteAsyncPostCall(soumettreFactureCompleteRequest, _callback);

    }

    /**
     * Soumettre une facture complète (asynchrone avec Celery)
     * Version asynchrone de l&#39;endpoint &#x60;/factures/soumettre-complete&#x60; utilisant Celery pour le traitement en arrière-plan.      **Workflow automatisé (identique à la version synchrone) :**     1. **Auto-enrichissement** (optionnel) : récupère les données via APIs publiques et Chorus Pro/AFNOR     2. **Génération PDF Factur-X** : crée un PDF/A-3 avec XML embarqué     3. **Signature électronique** (optionnelle) : signe le PDF avec un certificat     4. **Soumission** : envoie vers la destination choisie (Chorus Pro ou AFNOR PDP)      **Destinations supportées :**     - **Chorus Pro** : plateforme B2G française (factures vers secteur public)     - **AFNOR PDP** : Plateformes de Dématérialisation Partenaires      **Différences avec la version synchrone :**     - ✅ **Non-bloquant** : Retourne immédiatement un &#x60;id_tache&#x60; (HTTP 202 Accepted)     - ✅ **Traitement en arrière-plan** : La facture est traitée par un worker Celery     - ✅ **Suivi d&#39;avancement** : Utilisez &#x60;/taches/{id_tache}/statut&#x60; pour suivre le statut     - ✅ **Idéal pour gros volumes** : Permet de traiter de nombreuses factures en parallèle      **Comment utiliser :**     1. **Soumission** : Appelez cet endpoint avec vos données de facture     2. **Retour immédiat** : Vous recevez un &#x60;id_tache&#x60; (ex: \&quot;abc123-def456\&quot;)     3. **Suivi** : Appelez &#x60;/taches/{id_tache}/statut&#x60; pour vérifier l&#39;avancement     4. **Résultat** : Quand &#x60;statut &#x3D; \&quot;SUCCESS\&quot;&#x60;, le champ &#x60;resultat&#x60; contient la réponse complète      **Credentials et signature** : Mêmes modes que la version synchrone (JWT ou payload).
     * @param soumettreFactureCompleteRequest  (required)
     * @return ReponseTache
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 202 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ReponseTache soumettreFactureCompleteAsyncApiV1TraitementFacturesSoumettreCompleteAsyncPost(@javax.annotation.Nonnull SoumettreFactureCompleteRequest soumettreFactureCompleteRequest) throws ApiException {
        ApiResponse<ReponseTache> localVarResp = soumettreFactureCompleteAsyncApiV1TraitementFacturesSoumettreCompleteAsyncPostWithHttpInfo(soumettreFactureCompleteRequest);
        return localVarResp.getData();
    }

    /**
     * Soumettre une facture complète (asynchrone avec Celery)
     * Version asynchrone de l&#39;endpoint &#x60;/factures/soumettre-complete&#x60; utilisant Celery pour le traitement en arrière-plan.      **Workflow automatisé (identique à la version synchrone) :**     1. **Auto-enrichissement** (optionnel) : récupère les données via APIs publiques et Chorus Pro/AFNOR     2. **Génération PDF Factur-X** : crée un PDF/A-3 avec XML embarqué     3. **Signature électronique** (optionnelle) : signe le PDF avec un certificat     4. **Soumission** : envoie vers la destination choisie (Chorus Pro ou AFNOR PDP)      **Destinations supportées :**     - **Chorus Pro** : plateforme B2G française (factures vers secteur public)     - **AFNOR PDP** : Plateformes de Dématérialisation Partenaires      **Différences avec la version synchrone :**     - ✅ **Non-bloquant** : Retourne immédiatement un &#x60;id_tache&#x60; (HTTP 202 Accepted)     - ✅ **Traitement en arrière-plan** : La facture est traitée par un worker Celery     - ✅ **Suivi d&#39;avancement** : Utilisez &#x60;/taches/{id_tache}/statut&#x60; pour suivre le statut     - ✅ **Idéal pour gros volumes** : Permet de traiter de nombreuses factures en parallèle      **Comment utiliser :**     1. **Soumission** : Appelez cet endpoint avec vos données de facture     2. **Retour immédiat** : Vous recevez un &#x60;id_tache&#x60; (ex: \&quot;abc123-def456\&quot;)     3. **Suivi** : Appelez &#x60;/taches/{id_tache}/statut&#x60; pour vérifier l&#39;avancement     4. **Résultat** : Quand &#x60;statut &#x3D; \&quot;SUCCESS\&quot;&#x60;, le champ &#x60;resultat&#x60; contient la réponse complète      **Credentials et signature** : Mêmes modes que la version synchrone (JWT ou payload).
     * @param soumettreFactureCompleteRequest  (required)
     * @return ApiResponse&lt;ReponseTache&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 202 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<ReponseTache> soumettreFactureCompleteAsyncApiV1TraitementFacturesSoumettreCompleteAsyncPostWithHttpInfo(@javax.annotation.Nonnull SoumettreFactureCompleteRequest soumettreFactureCompleteRequest) throws ApiException {
        okhttp3.Call localVarCall = soumettreFactureCompleteAsyncApiV1TraitementFacturesSoumettreCompleteAsyncPostValidateBeforeCall(soumettreFactureCompleteRequest, null);
        Type localVarReturnType = new TypeToken<ReponseTache>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Soumettre une facture complète (asynchrone avec Celery) (asynchronously)
     * Version asynchrone de l&#39;endpoint &#x60;/factures/soumettre-complete&#x60; utilisant Celery pour le traitement en arrière-plan.      **Workflow automatisé (identique à la version synchrone) :**     1. **Auto-enrichissement** (optionnel) : récupère les données via APIs publiques et Chorus Pro/AFNOR     2. **Génération PDF Factur-X** : crée un PDF/A-3 avec XML embarqué     3. **Signature électronique** (optionnelle) : signe le PDF avec un certificat     4. **Soumission** : envoie vers la destination choisie (Chorus Pro ou AFNOR PDP)      **Destinations supportées :**     - **Chorus Pro** : plateforme B2G française (factures vers secteur public)     - **AFNOR PDP** : Plateformes de Dématérialisation Partenaires      **Différences avec la version synchrone :**     - ✅ **Non-bloquant** : Retourne immédiatement un &#x60;id_tache&#x60; (HTTP 202 Accepted)     - ✅ **Traitement en arrière-plan** : La facture est traitée par un worker Celery     - ✅ **Suivi d&#39;avancement** : Utilisez &#x60;/taches/{id_tache}/statut&#x60; pour suivre le statut     - ✅ **Idéal pour gros volumes** : Permet de traiter de nombreuses factures en parallèle      **Comment utiliser :**     1. **Soumission** : Appelez cet endpoint avec vos données de facture     2. **Retour immédiat** : Vous recevez un &#x60;id_tache&#x60; (ex: \&quot;abc123-def456\&quot;)     3. **Suivi** : Appelez &#x60;/taches/{id_tache}/statut&#x60; pour vérifier l&#39;avancement     4. **Résultat** : Quand &#x60;statut &#x3D; \&quot;SUCCESS\&quot;&#x60;, le champ &#x60;resultat&#x60; contient la réponse complète      **Credentials et signature** : Mêmes modes que la version synchrone (JWT ou payload).
     * @param soumettreFactureCompleteRequest  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 202 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call soumettreFactureCompleteAsyncApiV1TraitementFacturesSoumettreCompleteAsyncPostAsync(@javax.annotation.Nonnull SoumettreFactureCompleteRequest soumettreFactureCompleteRequest, final ApiCallback<ReponseTache> _callback) throws ApiException {

        okhttp3.Call localVarCall = soumettreFactureCompleteAsyncApiV1TraitementFacturesSoumettreCompleteAsyncPostValidateBeforeCall(soumettreFactureCompleteRequest, _callback);
        Type localVarReturnType = new TypeToken<ReponseTache>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for validerPdfFacturxApiV1TraitementValiderPdfFacturxPost
     * @param fichierPdf Fichier PDF Factur-X à valider (format .pdf). (required)
     * @param profil  (optional)
     * @param useVerapdf Active la validation stricte PDF/A avec VeraPDF (recommandé pour la production). Si False, utilise une validation basique par métadonnées. (optional, default to false)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Validation effectuée avec succès (vérifier le champ est_conforme pour le résultat) </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Fichier PDF invalide ou impossible à lire </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call validerPdfFacturxApiV1TraitementValiderPdfFacturxPostCall(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable ProfilAPI profil, @javax.annotation.Nullable Boolean useVerapdf, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/v1/traitement/valider-pdf-facturx";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (fichierPdf != null) {
            localVarFormParams.put("fichier_pdf", fichierPdf);
        }

        if (profil != null) {
            localVarFormParams.put("profil", profil);
        }

        if (useVerapdf != null) {
            localVarFormParams.put("use_verapdf", useVerapdf);
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "multipart/form-data"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "HTTPBearer" };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call validerPdfFacturxApiV1TraitementValiderPdfFacturxPostValidateBeforeCall(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable ProfilAPI profil, @javax.annotation.Nullable Boolean useVerapdf, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'fichierPdf' is set
        if (fichierPdf == null) {
            throw new ApiException("Missing the required parameter 'fichierPdf' when calling validerPdfFacturxApiV1TraitementValiderPdfFacturxPost(Async)");
        }

        return validerPdfFacturxApiV1TraitementValiderPdfFacturxPostCall(fichierPdf, profil, useVerapdf, _callback);

    }

    /**
     * Valider un PDF Factur-X complet
     * Valide un PDF Factur-X complet selon les normes européennes et françaises.  ## Normes de validation appliquées  - **EN 16931** : Norme sémantique européenne (directive 2014/55/UE) - **ISO 19005-3** (PDF/A-3) : Archivage électronique à long terme - **Factur-X / ZUGFeRD** : Spécification franco-allemande - **Schematron** : Validation des règles métier XML - **eIDAS** : Règlement européen sur l&#39;identification électronique (signatures)  ## Contrôles effectués  ### 1. Extraction et validation du XML Factur-X **Contrôles réalisés :** - Présence d&#39;un fichier XML embarqué (&#x60;factur-x.xml&#x60; ou &#x60;zugferd-invoice.xml&#x60;) - Détection automatique du profil (MINIMUM, BASIC, EN16931, EXTENDED) - Parsing XML avec validation UTF-8 - Extraction du GuidelineSpecifiedDocumentContextParameter/ID  **Validation Schematron :** - Règles métier du profil détecté (MINIMUM : 45 règles, EN16931 : 178 règles) - Cardinalité des éléments obligatoires - Cohérence des calculs (montants HT, TVA, TTC, remises) - Formats des identifiants (SIRET, TVA intracommunautaire, IBAN) - Codes normalisés (codes pays ISO, unités UN/ECE, codes TVA)  **Ce qui est vérifié :** - ✅ Structure XML conforme XSD Cross Industry Invoice - ✅ Namespace UN/CEFACT correct - ✅ Règles de gestion européennes (BR-xx) - ✅ Règles françaises spécifiques (FR-xx)  ### 2. Conformité PDF/A-3 **Validation de base (métadonnées) :** - Présence du champ &#x60;/Type&#x60; à &#x60;Catalog&#x60; - Métadonnée &#x60;pdfaid:part&#x60; &#x3D; 3 (PDF/A-3) - Métadonnée &#x60;pdfaid:conformance&#x60; &#x3D; B ou U - Version PDF &gt;&#x3D; 1.4  **Validation stricte VeraPDF (si use_verapdf&#x3D;True) :** - 146+ règles ISO 19005-3 (PDF/A-3B) - Absence de contenu interdit (JavaScript, multimedia, formulaires dynamiques) - Polices embarquées et sous-ensembles corrects - Espaces colorimétriques conformes (sRGB, DeviceGray) - Structure de fichier valide (cross-reference table) - Métadonnées XMP conformes ISO 16684-1  **Ce qui est vérifié :** - ✅ Fichier archivable à long terme (20+ ans) - ✅ Lisibilité garantie (polices embarquées) - ✅ Conformité légale (France, Allemagne, UE)  ### 3. Métadonnées XMP (eXtensible Metadata Platform) **Contrôles réalisés :** - Présence du bloc &#x60;&lt;?xpacket&gt;&#x60; avec métadonnées XMP - Namespace &#x60;fx:&#x60; pour Factur-X : &#x60;urn:factur-x:pdfa:CrossIndustryDocument:invoice:1p0#&#x60; - Champs Factur-X obligatoires :   - &#x60;fx:ConformanceLevel&#x60; : Profil (MINIMUM, BASIC, EN16931, EXTENDED)   - &#x60;fx:DocumentFileName&#x60; : Nom du XML embarqué   - &#x60;fx:DocumentType&#x60; : \&quot;INVOICE\&quot;   - &#x60;fx:Version&#x60; : Version Factur-X (1.0.07)  **Ce qui est vérifié :** - ✅ Métadonnées conformes ISO 16684-1 - ✅ Profil Factur-X déclaré correct - ✅ Version Factur-X supportée  ### 4. Signatures électroniques **Détection et analyse :** - Présence de dictionnaires &#x60;/Sig&#x60; dans le PDF - Type de signature : PAdES (PDF Advanced Electronic Signature) - Extraction des informations :   - Nom du signataire (&#x60;/Name&#x60;)   - Date de signature (&#x60;/M&#x60;)   - Raison de la signature (&#x60;/Reason&#x60;)   - Lieu de signature (&#x60;/Location&#x60;)   - Type de signature (approval, certification)  **Ce qui est vérifié :** - ✅ Présence de signatures ou cachets - ✅ Nombre de signatures (mono ou multi-signature) - ℹ️ Pas de vérification cryptographique (nécessite certificats)  ## Paramètres  - **fichier_pdf** (requis) : Le fichier PDF Factur-X à valider - **profil** (optionnel) : Profil attendu. Si absent, détection automatique depuis le XML - **use_verapdf** (optionnel, défaut&#x3D;false) : Active la validation stricte PDF/A avec VeraPDF   - &#x60;false&#x60; : Validation rapide par métadonnées (2-3 secondes)   - &#x60;true&#x60; : Validation complète ISO 19005-3 (15-30 secondes, **recommandé en production**)  ## Réponse détaillée  &#x60;&#x60;&#x60;json {   \&quot;est_conforme\&quot;: true,   \&quot;xml\&quot;: {     \&quot;present\&quot;: true,     \&quot;conforme\&quot;: true,     \&quot;profil\&quot;: \&quot;EN16931\&quot;,     \&quot;erreurs\&quot;: []   },   \&quot;pdfa\&quot;: {     \&quot;conforme\&quot;: true,     \&quot;version\&quot;: \&quot;PDF/A-3B\&quot;,     \&quot;methode\&quot;: \&quot;verapdf\&quot;,     \&quot;erreurs\&quot;: []   },   \&quot;xmp\&quot;: {     \&quot;present\&quot;: true,     \&quot;conforme\&quot;: true,     \&quot;metadonnees\&quot;: {...}   },   \&quot;signatures\&quot;: {     \&quot;present\&quot;: true,     \&quot;nombre\&quot;: 1,     \&quot;details\&quot;: [...]   } } &#x60;&#x60;&#x60;  ## Cas d&#39;usage  - **Avant envoi** : Valider la facture générée avant transmission à un client - **À réception** : Vérifier la conformité d&#39;une facture reçue d&#39;un fournisseur - **Audit** : Contrôler la qualité de lots de factures - **Conformité légale** : S&#39;assurer du respect des obligations B2B/B2G en France - **Debugging** : Identifier les problèmes dans le processus de génération - **Archivage** : Garantir la validité à long terme (PDF/A-3)  ## Temps de traitement  - Validation basique : 2-3 secondes - Validation VeraPDF : 15-30 secondes (dépend de la taille du PDF)
     * @param fichierPdf Fichier PDF Factur-X à valider (format .pdf). (required)
     * @param profil  (optional)
     * @param useVerapdf Active la validation stricte PDF/A avec VeraPDF (recommandé pour la production). Si False, utilise une validation basique par métadonnées. (optional, default to false)
     * @return ResultatValidationPDFAPI
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Validation effectuée avec succès (vérifier le champ est_conforme pour le résultat) </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Fichier PDF invalide ou impossible à lire </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ResultatValidationPDFAPI validerPdfFacturxApiV1TraitementValiderPdfFacturxPost(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable ProfilAPI profil, @javax.annotation.Nullable Boolean useVerapdf) throws ApiException {
        ApiResponse<ResultatValidationPDFAPI> localVarResp = validerPdfFacturxApiV1TraitementValiderPdfFacturxPostWithHttpInfo(fichierPdf, profil, useVerapdf);
        return localVarResp.getData();
    }

    /**
     * Valider un PDF Factur-X complet
     * Valide un PDF Factur-X complet selon les normes européennes et françaises.  ## Normes de validation appliquées  - **EN 16931** : Norme sémantique européenne (directive 2014/55/UE) - **ISO 19005-3** (PDF/A-3) : Archivage électronique à long terme - **Factur-X / ZUGFeRD** : Spécification franco-allemande - **Schematron** : Validation des règles métier XML - **eIDAS** : Règlement européen sur l&#39;identification électronique (signatures)  ## Contrôles effectués  ### 1. Extraction et validation du XML Factur-X **Contrôles réalisés :** - Présence d&#39;un fichier XML embarqué (&#x60;factur-x.xml&#x60; ou &#x60;zugferd-invoice.xml&#x60;) - Détection automatique du profil (MINIMUM, BASIC, EN16931, EXTENDED) - Parsing XML avec validation UTF-8 - Extraction du GuidelineSpecifiedDocumentContextParameter/ID  **Validation Schematron :** - Règles métier du profil détecté (MINIMUM : 45 règles, EN16931 : 178 règles) - Cardinalité des éléments obligatoires - Cohérence des calculs (montants HT, TVA, TTC, remises) - Formats des identifiants (SIRET, TVA intracommunautaire, IBAN) - Codes normalisés (codes pays ISO, unités UN/ECE, codes TVA)  **Ce qui est vérifié :** - ✅ Structure XML conforme XSD Cross Industry Invoice - ✅ Namespace UN/CEFACT correct - ✅ Règles de gestion européennes (BR-xx) - ✅ Règles françaises spécifiques (FR-xx)  ### 2. Conformité PDF/A-3 **Validation de base (métadonnées) :** - Présence du champ &#x60;/Type&#x60; à &#x60;Catalog&#x60; - Métadonnée &#x60;pdfaid:part&#x60; &#x3D; 3 (PDF/A-3) - Métadonnée &#x60;pdfaid:conformance&#x60; &#x3D; B ou U - Version PDF &gt;&#x3D; 1.4  **Validation stricte VeraPDF (si use_verapdf&#x3D;True) :** - 146+ règles ISO 19005-3 (PDF/A-3B) - Absence de contenu interdit (JavaScript, multimedia, formulaires dynamiques) - Polices embarquées et sous-ensembles corrects - Espaces colorimétriques conformes (sRGB, DeviceGray) - Structure de fichier valide (cross-reference table) - Métadonnées XMP conformes ISO 16684-1  **Ce qui est vérifié :** - ✅ Fichier archivable à long terme (20+ ans) - ✅ Lisibilité garantie (polices embarquées) - ✅ Conformité légale (France, Allemagne, UE)  ### 3. Métadonnées XMP (eXtensible Metadata Platform) **Contrôles réalisés :** - Présence du bloc &#x60;&lt;?xpacket&gt;&#x60; avec métadonnées XMP - Namespace &#x60;fx:&#x60; pour Factur-X : &#x60;urn:factur-x:pdfa:CrossIndustryDocument:invoice:1p0#&#x60; - Champs Factur-X obligatoires :   - &#x60;fx:ConformanceLevel&#x60; : Profil (MINIMUM, BASIC, EN16931, EXTENDED)   - &#x60;fx:DocumentFileName&#x60; : Nom du XML embarqué   - &#x60;fx:DocumentType&#x60; : \&quot;INVOICE\&quot;   - &#x60;fx:Version&#x60; : Version Factur-X (1.0.07)  **Ce qui est vérifié :** - ✅ Métadonnées conformes ISO 16684-1 - ✅ Profil Factur-X déclaré correct - ✅ Version Factur-X supportée  ### 4. Signatures électroniques **Détection et analyse :** - Présence de dictionnaires &#x60;/Sig&#x60; dans le PDF - Type de signature : PAdES (PDF Advanced Electronic Signature) - Extraction des informations :   - Nom du signataire (&#x60;/Name&#x60;)   - Date de signature (&#x60;/M&#x60;)   - Raison de la signature (&#x60;/Reason&#x60;)   - Lieu de signature (&#x60;/Location&#x60;)   - Type de signature (approval, certification)  **Ce qui est vérifié :** - ✅ Présence de signatures ou cachets - ✅ Nombre de signatures (mono ou multi-signature) - ℹ️ Pas de vérification cryptographique (nécessite certificats)  ## Paramètres  - **fichier_pdf** (requis) : Le fichier PDF Factur-X à valider - **profil** (optionnel) : Profil attendu. Si absent, détection automatique depuis le XML - **use_verapdf** (optionnel, défaut&#x3D;false) : Active la validation stricte PDF/A avec VeraPDF   - &#x60;false&#x60; : Validation rapide par métadonnées (2-3 secondes)   - &#x60;true&#x60; : Validation complète ISO 19005-3 (15-30 secondes, **recommandé en production**)  ## Réponse détaillée  &#x60;&#x60;&#x60;json {   \&quot;est_conforme\&quot;: true,   \&quot;xml\&quot;: {     \&quot;present\&quot;: true,     \&quot;conforme\&quot;: true,     \&quot;profil\&quot;: \&quot;EN16931\&quot;,     \&quot;erreurs\&quot;: []   },   \&quot;pdfa\&quot;: {     \&quot;conforme\&quot;: true,     \&quot;version\&quot;: \&quot;PDF/A-3B\&quot;,     \&quot;methode\&quot;: \&quot;verapdf\&quot;,     \&quot;erreurs\&quot;: []   },   \&quot;xmp\&quot;: {     \&quot;present\&quot;: true,     \&quot;conforme\&quot;: true,     \&quot;metadonnees\&quot;: {...}   },   \&quot;signatures\&quot;: {     \&quot;present\&quot;: true,     \&quot;nombre\&quot;: 1,     \&quot;details\&quot;: [...]   } } &#x60;&#x60;&#x60;  ## Cas d&#39;usage  - **Avant envoi** : Valider la facture générée avant transmission à un client - **À réception** : Vérifier la conformité d&#39;une facture reçue d&#39;un fournisseur - **Audit** : Contrôler la qualité de lots de factures - **Conformité légale** : S&#39;assurer du respect des obligations B2B/B2G en France - **Debugging** : Identifier les problèmes dans le processus de génération - **Archivage** : Garantir la validité à long terme (PDF/A-3)  ## Temps de traitement  - Validation basique : 2-3 secondes - Validation VeraPDF : 15-30 secondes (dépend de la taille du PDF)
     * @param fichierPdf Fichier PDF Factur-X à valider (format .pdf). (required)
     * @param profil  (optional)
     * @param useVerapdf Active la validation stricte PDF/A avec VeraPDF (recommandé pour la production). Si False, utilise une validation basique par métadonnées. (optional, default to false)
     * @return ApiResponse&lt;ResultatValidationPDFAPI&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Validation effectuée avec succès (vérifier le champ est_conforme pour le résultat) </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Fichier PDF invalide ou impossible à lire </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<ResultatValidationPDFAPI> validerPdfFacturxApiV1TraitementValiderPdfFacturxPostWithHttpInfo(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable ProfilAPI profil, @javax.annotation.Nullable Boolean useVerapdf) throws ApiException {
        okhttp3.Call localVarCall = validerPdfFacturxApiV1TraitementValiderPdfFacturxPostValidateBeforeCall(fichierPdf, profil, useVerapdf, null);
        Type localVarReturnType = new TypeToken<ResultatValidationPDFAPI>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Valider un PDF Factur-X complet (asynchronously)
     * Valide un PDF Factur-X complet selon les normes européennes et françaises.  ## Normes de validation appliquées  - **EN 16931** : Norme sémantique européenne (directive 2014/55/UE) - **ISO 19005-3** (PDF/A-3) : Archivage électronique à long terme - **Factur-X / ZUGFeRD** : Spécification franco-allemande - **Schematron** : Validation des règles métier XML - **eIDAS** : Règlement européen sur l&#39;identification électronique (signatures)  ## Contrôles effectués  ### 1. Extraction et validation du XML Factur-X **Contrôles réalisés :** - Présence d&#39;un fichier XML embarqué (&#x60;factur-x.xml&#x60; ou &#x60;zugferd-invoice.xml&#x60;) - Détection automatique du profil (MINIMUM, BASIC, EN16931, EXTENDED) - Parsing XML avec validation UTF-8 - Extraction du GuidelineSpecifiedDocumentContextParameter/ID  **Validation Schematron :** - Règles métier du profil détecté (MINIMUM : 45 règles, EN16931 : 178 règles) - Cardinalité des éléments obligatoires - Cohérence des calculs (montants HT, TVA, TTC, remises) - Formats des identifiants (SIRET, TVA intracommunautaire, IBAN) - Codes normalisés (codes pays ISO, unités UN/ECE, codes TVA)  **Ce qui est vérifié :** - ✅ Structure XML conforme XSD Cross Industry Invoice - ✅ Namespace UN/CEFACT correct - ✅ Règles de gestion européennes (BR-xx) - ✅ Règles françaises spécifiques (FR-xx)  ### 2. Conformité PDF/A-3 **Validation de base (métadonnées) :** - Présence du champ &#x60;/Type&#x60; à &#x60;Catalog&#x60; - Métadonnée &#x60;pdfaid:part&#x60; &#x3D; 3 (PDF/A-3) - Métadonnée &#x60;pdfaid:conformance&#x60; &#x3D; B ou U - Version PDF &gt;&#x3D; 1.4  **Validation stricte VeraPDF (si use_verapdf&#x3D;True) :** - 146+ règles ISO 19005-3 (PDF/A-3B) - Absence de contenu interdit (JavaScript, multimedia, formulaires dynamiques) - Polices embarquées et sous-ensembles corrects - Espaces colorimétriques conformes (sRGB, DeviceGray) - Structure de fichier valide (cross-reference table) - Métadonnées XMP conformes ISO 16684-1  **Ce qui est vérifié :** - ✅ Fichier archivable à long terme (20+ ans) - ✅ Lisibilité garantie (polices embarquées) - ✅ Conformité légale (France, Allemagne, UE)  ### 3. Métadonnées XMP (eXtensible Metadata Platform) **Contrôles réalisés :** - Présence du bloc &#x60;&lt;?xpacket&gt;&#x60; avec métadonnées XMP - Namespace &#x60;fx:&#x60; pour Factur-X : &#x60;urn:factur-x:pdfa:CrossIndustryDocument:invoice:1p0#&#x60; - Champs Factur-X obligatoires :   - &#x60;fx:ConformanceLevel&#x60; : Profil (MINIMUM, BASIC, EN16931, EXTENDED)   - &#x60;fx:DocumentFileName&#x60; : Nom du XML embarqué   - &#x60;fx:DocumentType&#x60; : \&quot;INVOICE\&quot;   - &#x60;fx:Version&#x60; : Version Factur-X (1.0.07)  **Ce qui est vérifié :** - ✅ Métadonnées conformes ISO 16684-1 - ✅ Profil Factur-X déclaré correct - ✅ Version Factur-X supportée  ### 4. Signatures électroniques **Détection et analyse :** - Présence de dictionnaires &#x60;/Sig&#x60; dans le PDF - Type de signature : PAdES (PDF Advanced Electronic Signature) - Extraction des informations :   - Nom du signataire (&#x60;/Name&#x60;)   - Date de signature (&#x60;/M&#x60;)   - Raison de la signature (&#x60;/Reason&#x60;)   - Lieu de signature (&#x60;/Location&#x60;)   - Type de signature (approval, certification)  **Ce qui est vérifié :** - ✅ Présence de signatures ou cachets - ✅ Nombre de signatures (mono ou multi-signature) - ℹ️ Pas de vérification cryptographique (nécessite certificats)  ## Paramètres  - **fichier_pdf** (requis) : Le fichier PDF Factur-X à valider - **profil** (optionnel) : Profil attendu. Si absent, détection automatique depuis le XML - **use_verapdf** (optionnel, défaut&#x3D;false) : Active la validation stricte PDF/A avec VeraPDF   - &#x60;false&#x60; : Validation rapide par métadonnées (2-3 secondes)   - &#x60;true&#x60; : Validation complète ISO 19005-3 (15-30 secondes, **recommandé en production**)  ## Réponse détaillée  &#x60;&#x60;&#x60;json {   \&quot;est_conforme\&quot;: true,   \&quot;xml\&quot;: {     \&quot;present\&quot;: true,     \&quot;conforme\&quot;: true,     \&quot;profil\&quot;: \&quot;EN16931\&quot;,     \&quot;erreurs\&quot;: []   },   \&quot;pdfa\&quot;: {     \&quot;conforme\&quot;: true,     \&quot;version\&quot;: \&quot;PDF/A-3B\&quot;,     \&quot;methode\&quot;: \&quot;verapdf\&quot;,     \&quot;erreurs\&quot;: []   },   \&quot;xmp\&quot;: {     \&quot;present\&quot;: true,     \&quot;conforme\&quot;: true,     \&quot;metadonnees\&quot;: {...}   },   \&quot;signatures\&quot;: {     \&quot;present\&quot;: true,     \&quot;nombre\&quot;: 1,     \&quot;details\&quot;: [...]   } } &#x60;&#x60;&#x60;  ## Cas d&#39;usage  - **Avant envoi** : Valider la facture générée avant transmission à un client - **À réception** : Vérifier la conformité d&#39;une facture reçue d&#39;un fournisseur - **Audit** : Contrôler la qualité de lots de factures - **Conformité légale** : S&#39;assurer du respect des obligations B2B/B2G en France - **Debugging** : Identifier les problèmes dans le processus de génération - **Archivage** : Garantir la validité à long terme (PDF/A-3)  ## Temps de traitement  - Validation basique : 2-3 secondes - Validation VeraPDF : 15-30 secondes (dépend de la taille du PDF)
     * @param fichierPdf Fichier PDF Factur-X à valider (format .pdf). (required)
     * @param profil  (optional)
     * @param useVerapdf Active la validation stricte PDF/A avec VeraPDF (recommandé pour la production). Si False, utilise une validation basique par métadonnées. (optional, default to false)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Validation effectuée avec succès (vérifier le champ est_conforme pour le résultat) </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Fichier PDF invalide ou impossible à lire </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call validerPdfFacturxApiV1TraitementValiderPdfFacturxPostAsync(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable ProfilAPI profil, @javax.annotation.Nullable Boolean useVerapdf, final ApiCallback<ResultatValidationPDFAPI> _callback) throws ApiException {

        okhttp3.Call localVarCall = validerPdfFacturxApiV1TraitementValiderPdfFacturxPostValidateBeforeCall(fichierPdf, profil, useVerapdf, _callback);
        Type localVarReturnType = new TypeToken<ResultatValidationPDFAPI>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for validerPdfFacturxAsyncApiV1TraitementValiderFacturxAsyncPost
     * @param fichierPdf Fichier PDF Factur-X à valider (format .pdf). (required)
     * @param profil  (optional)
     * @param useVerapdf Active la validation stricte PDF/A avec VeraPDF (recommandé pour la production). Peut prendre plusieurs secondes. (optional, default to false)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 202 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Fichier PDF invalide ou impossible à lire </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call validerPdfFacturxAsyncApiV1TraitementValiderFacturxAsyncPostCall(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable ProfilAPI profil, @javax.annotation.Nullable Boolean useVerapdf, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/v1/traitement/valider-facturx-async";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (fichierPdf != null) {
            localVarFormParams.put("fichier_pdf", fichierPdf);
        }

        if (profil != null) {
            localVarFormParams.put("profil", profil);
        }

        if (useVerapdf != null) {
            localVarFormParams.put("use_verapdf", useVerapdf);
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "multipart/form-data"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "HTTPBearer" };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call validerPdfFacturxAsyncApiV1TraitementValiderFacturxAsyncPostValidateBeforeCall(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable ProfilAPI profil, @javax.annotation.Nullable Boolean useVerapdf, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'fichierPdf' is set
        if (fichierPdf == null) {
            throw new ApiException("Missing the required parameter 'fichierPdf' when calling validerPdfFacturxAsyncApiV1TraitementValiderFacturxAsyncPost(Async)");
        }

        return validerPdfFacturxAsyncApiV1TraitementValiderFacturxAsyncPostCall(fichierPdf, profil, useVerapdf, _callback);

    }

    /**
     * Valider un PDF Factur-X (asynchrone avec polling)
     * Valide un PDF Factur-X de manière asynchrone avec système de polling.  ## Fonctionnement  1. **Soumission** : Le PDF est mis en file d&#39;attente pour validation asynchrone 2. **Retour immédiat** : Vous recevez un &#x60;id_tache&#x60; (HTTP 202) 3. **Suivi** : Utilisez l&#39;endpoint &#x60;/taches/{id_tache}/statut&#x60; pour suivre l&#39;avancement  ## Avantages du mode asynchrone  - **Pas de timeout** : Idéal pour les gros PDFs ou la validation VeraPDF (qui peut prendre plusieurs secondes) - **Scalabilité** : Les validations sont traitées par des workers Celery dédiés - **Suivi d&#39;état** : Permet de suivre la progression de la validation - **Non-bloquant** : Votre client ne reste pas en attente pendant la validation  ## Quand utiliser ce mode ?  - **Validation VeraPDF activée** (&#x60;use_verapdf&#x3D;True&#x60;) : La validation stricte peut prendre 2-10 secondes - **Gros fichiers PDF** : PDFs &gt; 1 MB - **Traitement par lots** : Validation de multiples factures en parallèle - **Intégration asynchrone** : Votre système supporte le polling  ## Contrôles effectués  ### 1. Extraction et validation du XML Factur-X - Vérifie la présence d&#39;un fichier XML embarqué conforme Factur-X - Détecte automatiquement le profil utilisé (MINIMUM, BASIC, EN16931, EXTENDED) - Valide le XML contre les règles Schematron du profil détecté  ### 2. Conformité PDF/A - **Sans VeraPDF** : Validation basique par métadonnées (rapide, ~100ms) - **Avec VeraPDF** : Validation stricte selon ISO 19005 (146+ règles, 2-10s)   - Détecte la version PDF/A (PDF/A-1, PDF/A-3, etc.)   - Rapports détaillés des non-conformités  ### 3. Métadonnées XMP - Vérifie la présence de métadonnées XMP dans le PDF - Valide la conformité des métadonnées Factur-X (profil, version) - Extrait toutes les métadonnées XMP disponibles  ### 4. Signatures électroniques - Détecte la présence de signatures ou cachets électroniques - Extrait les informations sur chaque signature (signataire, date, raison) - Compte le nombre de signatures présentes  ## Paramètres  - **fichier_pdf** : Le fichier PDF Factur-X à valider - **profil** : Le profil Factur-X attendu (optionnel). Si non spécifié, le profil   sera automatiquement détecté depuis le fichier XML embarqué. - **use_verapdf** : Active la validation stricte PDF/A avec VeraPDF.   ⚠️ **Attention** : VeraPDF peut prendre 2-10 secondes selon la taille du PDF.   Recommandé uniquement en mode asynchrone pour éviter les timeouts.  ## Récupération du résultat  Après soumission, utilisez l&#39;endpoint &#x60;GET /taches/{id_tache}/statut&#x60; pour récupérer le résultat.  **Exemple de polling** : &#x60;&#x60;&#x60;python import requests import time  # 1. Soumettre la tâche response &#x3D; requests.post(\&quot;/valider-facturx-async\&quot;, files&#x3D;{\&quot;fichier_pdf\&quot;: pdf_file}) task_id &#x3D; response.json()[\&quot;id_tache\&quot;]  # 2. Polling toutes les 2 secondes while True:     status_response &#x3D; requests.get(f\&quot;/taches/{task_id}/statut\&quot;)     status &#x3D; status_response.json()      if status[\&quot;statut\&quot;] &#x3D;&#x3D; \&quot;SUCCESS\&quot;:         resultat &#x3D; status[\&quot;resultat\&quot;][\&quot;resultat_validation\&quot;]         print(f\&quot;Conforme: {resultat[&#39;est_conforme&#39;]}\&quot;)         break     elif status[\&quot;statut\&quot;] &#x3D;&#x3D; \&quot;FAILURE\&quot;:         print(f\&quot;Erreur: {status[&#39;resultat&#39;][&#39;message_erreur&#39;]}\&quot;)         break      time.sleep(2)  # Attendre 2 secondes avant le prochain check &#x60;&#x60;&#x60;  ## Cas d&#39;usage  - Valider des factures avant envoi avec VeraPDF (validation stricte) - Traiter des lots de factures en parallèle - Intégrer la validation dans un pipeline asynchrone - Valider des PDFs volumineux sans risque de timeout
     * @param fichierPdf Fichier PDF Factur-X à valider (format .pdf). (required)
     * @param profil  (optional)
     * @param useVerapdf Active la validation stricte PDF/A avec VeraPDF (recommandé pour la production). Peut prendre plusieurs secondes. (optional, default to false)
     * @return ReponseTache
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 202 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Fichier PDF invalide ou impossible à lire </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ReponseTache validerPdfFacturxAsyncApiV1TraitementValiderFacturxAsyncPost(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable ProfilAPI profil, @javax.annotation.Nullable Boolean useVerapdf) throws ApiException {
        ApiResponse<ReponseTache> localVarResp = validerPdfFacturxAsyncApiV1TraitementValiderFacturxAsyncPostWithHttpInfo(fichierPdf, profil, useVerapdf);
        return localVarResp.getData();
    }

    /**
     * Valider un PDF Factur-X (asynchrone avec polling)
     * Valide un PDF Factur-X de manière asynchrone avec système de polling.  ## Fonctionnement  1. **Soumission** : Le PDF est mis en file d&#39;attente pour validation asynchrone 2. **Retour immédiat** : Vous recevez un &#x60;id_tache&#x60; (HTTP 202) 3. **Suivi** : Utilisez l&#39;endpoint &#x60;/taches/{id_tache}/statut&#x60; pour suivre l&#39;avancement  ## Avantages du mode asynchrone  - **Pas de timeout** : Idéal pour les gros PDFs ou la validation VeraPDF (qui peut prendre plusieurs secondes) - **Scalabilité** : Les validations sont traitées par des workers Celery dédiés - **Suivi d&#39;état** : Permet de suivre la progression de la validation - **Non-bloquant** : Votre client ne reste pas en attente pendant la validation  ## Quand utiliser ce mode ?  - **Validation VeraPDF activée** (&#x60;use_verapdf&#x3D;True&#x60;) : La validation stricte peut prendre 2-10 secondes - **Gros fichiers PDF** : PDFs &gt; 1 MB - **Traitement par lots** : Validation de multiples factures en parallèle - **Intégration asynchrone** : Votre système supporte le polling  ## Contrôles effectués  ### 1. Extraction et validation du XML Factur-X - Vérifie la présence d&#39;un fichier XML embarqué conforme Factur-X - Détecte automatiquement le profil utilisé (MINIMUM, BASIC, EN16931, EXTENDED) - Valide le XML contre les règles Schematron du profil détecté  ### 2. Conformité PDF/A - **Sans VeraPDF** : Validation basique par métadonnées (rapide, ~100ms) - **Avec VeraPDF** : Validation stricte selon ISO 19005 (146+ règles, 2-10s)   - Détecte la version PDF/A (PDF/A-1, PDF/A-3, etc.)   - Rapports détaillés des non-conformités  ### 3. Métadonnées XMP - Vérifie la présence de métadonnées XMP dans le PDF - Valide la conformité des métadonnées Factur-X (profil, version) - Extrait toutes les métadonnées XMP disponibles  ### 4. Signatures électroniques - Détecte la présence de signatures ou cachets électroniques - Extrait les informations sur chaque signature (signataire, date, raison) - Compte le nombre de signatures présentes  ## Paramètres  - **fichier_pdf** : Le fichier PDF Factur-X à valider - **profil** : Le profil Factur-X attendu (optionnel). Si non spécifié, le profil   sera automatiquement détecté depuis le fichier XML embarqué. - **use_verapdf** : Active la validation stricte PDF/A avec VeraPDF.   ⚠️ **Attention** : VeraPDF peut prendre 2-10 secondes selon la taille du PDF.   Recommandé uniquement en mode asynchrone pour éviter les timeouts.  ## Récupération du résultat  Après soumission, utilisez l&#39;endpoint &#x60;GET /taches/{id_tache}/statut&#x60; pour récupérer le résultat.  **Exemple de polling** : &#x60;&#x60;&#x60;python import requests import time  # 1. Soumettre la tâche response &#x3D; requests.post(\&quot;/valider-facturx-async\&quot;, files&#x3D;{\&quot;fichier_pdf\&quot;: pdf_file}) task_id &#x3D; response.json()[\&quot;id_tache\&quot;]  # 2. Polling toutes les 2 secondes while True:     status_response &#x3D; requests.get(f\&quot;/taches/{task_id}/statut\&quot;)     status &#x3D; status_response.json()      if status[\&quot;statut\&quot;] &#x3D;&#x3D; \&quot;SUCCESS\&quot;:         resultat &#x3D; status[\&quot;resultat\&quot;][\&quot;resultat_validation\&quot;]         print(f\&quot;Conforme: {resultat[&#39;est_conforme&#39;]}\&quot;)         break     elif status[\&quot;statut\&quot;] &#x3D;&#x3D; \&quot;FAILURE\&quot;:         print(f\&quot;Erreur: {status[&#39;resultat&#39;][&#39;message_erreur&#39;]}\&quot;)         break      time.sleep(2)  # Attendre 2 secondes avant le prochain check &#x60;&#x60;&#x60;  ## Cas d&#39;usage  - Valider des factures avant envoi avec VeraPDF (validation stricte) - Traiter des lots de factures en parallèle - Intégrer la validation dans un pipeline asynchrone - Valider des PDFs volumineux sans risque de timeout
     * @param fichierPdf Fichier PDF Factur-X à valider (format .pdf). (required)
     * @param profil  (optional)
     * @param useVerapdf Active la validation stricte PDF/A avec VeraPDF (recommandé pour la production). Peut prendre plusieurs secondes. (optional, default to false)
     * @return ApiResponse&lt;ReponseTache&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 202 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Fichier PDF invalide ou impossible à lire </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<ReponseTache> validerPdfFacturxAsyncApiV1TraitementValiderFacturxAsyncPostWithHttpInfo(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable ProfilAPI profil, @javax.annotation.Nullable Boolean useVerapdf) throws ApiException {
        okhttp3.Call localVarCall = validerPdfFacturxAsyncApiV1TraitementValiderFacturxAsyncPostValidateBeforeCall(fichierPdf, profil, useVerapdf, null);
        Type localVarReturnType = new TypeToken<ReponseTache>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Valider un PDF Factur-X (asynchrone avec polling) (asynchronously)
     * Valide un PDF Factur-X de manière asynchrone avec système de polling.  ## Fonctionnement  1. **Soumission** : Le PDF est mis en file d&#39;attente pour validation asynchrone 2. **Retour immédiat** : Vous recevez un &#x60;id_tache&#x60; (HTTP 202) 3. **Suivi** : Utilisez l&#39;endpoint &#x60;/taches/{id_tache}/statut&#x60; pour suivre l&#39;avancement  ## Avantages du mode asynchrone  - **Pas de timeout** : Idéal pour les gros PDFs ou la validation VeraPDF (qui peut prendre plusieurs secondes) - **Scalabilité** : Les validations sont traitées par des workers Celery dédiés - **Suivi d&#39;état** : Permet de suivre la progression de la validation - **Non-bloquant** : Votre client ne reste pas en attente pendant la validation  ## Quand utiliser ce mode ?  - **Validation VeraPDF activée** (&#x60;use_verapdf&#x3D;True&#x60;) : La validation stricte peut prendre 2-10 secondes - **Gros fichiers PDF** : PDFs &gt; 1 MB - **Traitement par lots** : Validation de multiples factures en parallèle - **Intégration asynchrone** : Votre système supporte le polling  ## Contrôles effectués  ### 1. Extraction et validation du XML Factur-X - Vérifie la présence d&#39;un fichier XML embarqué conforme Factur-X - Détecte automatiquement le profil utilisé (MINIMUM, BASIC, EN16931, EXTENDED) - Valide le XML contre les règles Schematron du profil détecté  ### 2. Conformité PDF/A - **Sans VeraPDF** : Validation basique par métadonnées (rapide, ~100ms) - **Avec VeraPDF** : Validation stricte selon ISO 19005 (146+ règles, 2-10s)   - Détecte la version PDF/A (PDF/A-1, PDF/A-3, etc.)   - Rapports détaillés des non-conformités  ### 3. Métadonnées XMP - Vérifie la présence de métadonnées XMP dans le PDF - Valide la conformité des métadonnées Factur-X (profil, version) - Extrait toutes les métadonnées XMP disponibles  ### 4. Signatures électroniques - Détecte la présence de signatures ou cachets électroniques - Extrait les informations sur chaque signature (signataire, date, raison) - Compte le nombre de signatures présentes  ## Paramètres  - **fichier_pdf** : Le fichier PDF Factur-X à valider - **profil** : Le profil Factur-X attendu (optionnel). Si non spécifié, le profil   sera automatiquement détecté depuis le fichier XML embarqué. - **use_verapdf** : Active la validation stricte PDF/A avec VeraPDF.   ⚠️ **Attention** : VeraPDF peut prendre 2-10 secondes selon la taille du PDF.   Recommandé uniquement en mode asynchrone pour éviter les timeouts.  ## Récupération du résultat  Après soumission, utilisez l&#39;endpoint &#x60;GET /taches/{id_tache}/statut&#x60; pour récupérer le résultat.  **Exemple de polling** : &#x60;&#x60;&#x60;python import requests import time  # 1. Soumettre la tâche response &#x3D; requests.post(\&quot;/valider-facturx-async\&quot;, files&#x3D;{\&quot;fichier_pdf\&quot;: pdf_file}) task_id &#x3D; response.json()[\&quot;id_tache\&quot;]  # 2. Polling toutes les 2 secondes while True:     status_response &#x3D; requests.get(f\&quot;/taches/{task_id}/statut\&quot;)     status &#x3D; status_response.json()      if status[\&quot;statut\&quot;] &#x3D;&#x3D; \&quot;SUCCESS\&quot;:         resultat &#x3D; status[\&quot;resultat\&quot;][\&quot;resultat_validation\&quot;]         print(f\&quot;Conforme: {resultat[&#39;est_conforme&#39;]}\&quot;)         break     elif status[\&quot;statut\&quot;] &#x3D;&#x3D; \&quot;FAILURE\&quot;:         print(f\&quot;Erreur: {status[&#39;resultat&#39;][&#39;message_erreur&#39;]}\&quot;)         break      time.sleep(2)  # Attendre 2 secondes avant le prochain check &#x60;&#x60;&#x60;  ## Cas d&#39;usage  - Valider des factures avant envoi avec VeraPDF (validation stricte) - Traiter des lots de factures en parallèle - Intégrer la validation dans un pipeline asynchrone - Valider des PDFs volumineux sans risque de timeout
     * @param fichierPdf Fichier PDF Factur-X à valider (format .pdf). (required)
     * @param profil  (optional)
     * @param useVerapdf Active la validation stricte PDF/A avec VeraPDF (recommandé pour la production). Peut prendre plusieurs secondes. (optional, default to false)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 202 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Fichier PDF invalide ou impossible à lire </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call validerPdfFacturxAsyncApiV1TraitementValiderFacturxAsyncPostAsync(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable ProfilAPI profil, @javax.annotation.Nullable Boolean useVerapdf, final ApiCallback<ReponseTache> _callback) throws ApiException {

        okhttp3.Call localVarCall = validerPdfFacturxAsyncApiV1TraitementValiderFacturxAsyncPostValidateBeforeCall(fichierPdf, profil, useVerapdf, _callback);
        Type localVarReturnType = new TypeToken<ReponseTache>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for validerSignaturePdfEndpointApiV1TraitementValiderSignaturePdfPost
     * @param fichierPdf Fichier PDF à valider (sera analysé pour détecter et valider les signatures) (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Validation réussie </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Fichier invalide ou non lisible </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call validerSignaturePdfEndpointApiV1TraitementValiderSignaturePdfPostCall(@javax.annotation.Nonnull File fichierPdf, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/v1/traitement/valider-signature-pdf";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (fichierPdf != null) {
            localVarFormParams.put("fichier_pdf", fichierPdf);
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "multipart/form-data"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "HTTPBearer" };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call validerSignaturePdfEndpointApiV1TraitementValiderSignaturePdfPostValidateBeforeCall(@javax.annotation.Nonnull File fichierPdf, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'fichierPdf' is set
        if (fichierPdf == null) {
            throw new ApiException("Missing the required parameter 'fichierPdf' when calling validerSignaturePdfEndpointApiV1TraitementValiderSignaturePdfPost(Async)");
        }

        return validerSignaturePdfEndpointApiV1TraitementValiderSignaturePdfPostCall(fichierPdf, _callback);

    }

    /**
     * Valider les signatures électroniques d&#39;un PDF
     * Valide les signatures électroniques présentes dans un PDF uploadé.      **Vérifications effectuées** :     - Présence de signatures     - Intégrité du document (non modifié depuis signature)     - Validité des certificats     - Chaîne de confiance (si disponible)     - Présence d&#39;horodatage (PAdES-B-T)     - Données de validation (PAdES-B-LT)      **Standards supportés** : PAdES-B-B, PAdES-B-T, PAdES-B-LT, ISO 32000-2.      **⚠️ Note** : Cette validation est technique (intégrité cryptographique). La validité juridique     dépend du niveau eIDAS du certificat (SES/AdES/QES) et du contexte d&#39;utilisation.
     * @param fichierPdf Fichier PDF à valider (sera analysé pour détecter et valider les signatures) (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Validation réussie </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Fichier invalide ou non lisible </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object validerSignaturePdfEndpointApiV1TraitementValiderSignaturePdfPost(@javax.annotation.Nonnull File fichierPdf) throws ApiException {
        ApiResponse<Object> localVarResp = validerSignaturePdfEndpointApiV1TraitementValiderSignaturePdfPostWithHttpInfo(fichierPdf);
        return localVarResp.getData();
    }

    /**
     * Valider les signatures électroniques d&#39;un PDF
     * Valide les signatures électroniques présentes dans un PDF uploadé.      **Vérifications effectuées** :     - Présence de signatures     - Intégrité du document (non modifié depuis signature)     - Validité des certificats     - Chaîne de confiance (si disponible)     - Présence d&#39;horodatage (PAdES-B-T)     - Données de validation (PAdES-B-LT)      **Standards supportés** : PAdES-B-B, PAdES-B-T, PAdES-B-LT, ISO 32000-2.      **⚠️ Note** : Cette validation est technique (intégrité cryptographique). La validité juridique     dépend du niveau eIDAS du certificat (SES/AdES/QES) et du contexte d&#39;utilisation.
     * @param fichierPdf Fichier PDF à valider (sera analysé pour détecter et valider les signatures) (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Validation réussie </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Fichier invalide ou non lisible </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> validerSignaturePdfEndpointApiV1TraitementValiderSignaturePdfPostWithHttpInfo(@javax.annotation.Nonnull File fichierPdf) throws ApiException {
        okhttp3.Call localVarCall = validerSignaturePdfEndpointApiV1TraitementValiderSignaturePdfPostValidateBeforeCall(fichierPdf, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Valider les signatures électroniques d&#39;un PDF (asynchronously)
     * Valide les signatures électroniques présentes dans un PDF uploadé.      **Vérifications effectuées** :     - Présence de signatures     - Intégrité du document (non modifié depuis signature)     - Validité des certificats     - Chaîne de confiance (si disponible)     - Présence d&#39;horodatage (PAdES-B-T)     - Données de validation (PAdES-B-LT)      **Standards supportés** : PAdES-B-B, PAdES-B-T, PAdES-B-LT, ISO 32000-2.      **⚠️ Note** : Cette validation est technique (intégrité cryptographique). La validité juridique     dépend du niveau eIDAS du certificat (SES/AdES/QES) et du contexte d&#39;utilisation.
     * @param fichierPdf Fichier PDF à valider (sera analysé pour détecter et valider les signatures) (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Validation réussie </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Fichier invalide ou non lisible </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call validerSignaturePdfEndpointApiV1TraitementValiderSignaturePdfPostAsync(@javax.annotation.Nonnull File fichierPdf, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = validerSignaturePdfEndpointApiV1TraitementValiderSignaturePdfPostValidateBeforeCall(fichierPdf, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for validerXmlApiV1TraitementValiderXmlPost
     * @param fichierXml Fichier XML Factur-X à valider (format .xml). (required)
     * @param profil Profil de validation (MINIMUM, BASIC, EN16931, EXTENDED). (optional)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Le XML ne respecte pas les règles du profil Factur-X </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call validerXmlApiV1TraitementValiderXmlPostCall(@javax.annotation.Nonnull File fichierXml, @javax.annotation.Nullable ProfilAPI profil, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/v1/traitement/valider-xml";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (fichierXml != null) {
            localVarFormParams.put("fichier_xml", fichierXml);
        }

        if (profil != null) {
            localVarFormParams.put("profil", profil);
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "multipart/form-data"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] { "HTTPBearer" };
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call validerXmlApiV1TraitementValiderXmlPostValidateBeforeCall(@javax.annotation.Nonnull File fichierXml, @javax.annotation.Nullable ProfilAPI profil, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'fichierXml' is set
        if (fichierXml == null) {
            throw new ApiException("Missing the required parameter 'fichierXml' when calling validerXmlApiV1TraitementValiderXmlPost(Async)");
        }

        return validerXmlApiV1TraitementValiderXmlPostCall(fichierXml, profil, _callback);

    }

    /**
     * Valider un XML Factur-X existant
     * Valide un fichier XML Factur-X contre les règles métier Schematron selon la norme EN 16931.  ## Norme appliquée  **Schematron ISO/IEC 19757-3** : Langage de validation de règles métier pour XML - Validation sémantique (au-delà de la syntaxe XSD) - Règles métier européennes EN 16931 - Règles françaises spécifiques Factur-X - Calculs arithmétiques et cohérence des données  ## Profils et règles validées  ### MINIMUM (45 règles) - Identifiant de facture unique - Dates (émission, échéance) - Identifiants parties (SIRET/SIREN) - Montant total TTC  ### BASIC (102 règles) - Toutes les règles MINIMUM - Lignes de facture détaillées - Calculs de TVA basiques - Modes de paiement - Références (commande, contrat)  ### EN16931 (178 règles) - Toutes les règles BASIC - **Règles européennes (BR-xx)** : 81 règles business - **Règles françaises (FR-xx)** : 12 règles spécifiques France - **Calculs avancés (CR-xx)** : 32 règles de calcul - **Codes normalisés (CL-xx)** : 52 listes de codes  ### EXTENDED (210+ règles) - Toutes les règles EN16931 - Informations logistiques - Données comptables avancées - Références externes multiples  ## Contrôles effectués  ### 1. Validation syntaxique - Parsing XML correct (UTF-8, bien formé) - Namespaces UN/CEFACT présents - Structure hiérarchique respectée  ### 2. Règles business (BR-xx) Exemples : - &#x60;BR-1&#x60; : Le total de la facture doit être égal à la somme des totaux de lignes + montants au niveau document - &#x60;BR-CO-10&#x60; : La somme des montants de base de TVA doit être égale au total net de la facture - &#x60;BR-16&#x60; : Le code de devise de la facture doit figurer dans la liste ISO 4217  ### 3. Règles françaises (FR-xx) Exemples : - &#x60;FR-1&#x60; : Le SIRET fournisseur doit avoir 14 chiffres - &#x60;FR-2&#x60; : Le SIRET client doit avoir 14 chiffres (si présent) - &#x60;FR-5&#x60; : Le numéro de TVA intracommunautaire doit être au format FRxx999999999  ### 4. Règles de calcul (CR-xx) - Montants HT + TVA &#x3D; TTC - Somme des lignes &#x3D; Total document - Remises et majorations correctement appliquées - Arrondis conformes (2 décimales pour les montants)  ### 5. Codes normalisés (CL-xx) - Codes pays ISO 3166-1 alpha-2 - Codes devises ISO 4217 - Unités de mesure UN/ECE Rec 20 - Codes TVA (types, catégories, exonérations) - SchemeID pour identifiants (0002&#x3D;SIREN, 0009&#x3D;SIRET, etc.)  ## Processus de validation  1. **Chargement XSLT** : Fichier Schematron converti en XSLT (Saxon-HE) 2. **Transformation** : Application des règles sur le XML 3. **Analyse résultats** : Extraction des erreurs (&#x60;failed-assert&#x60;) et avertissements (&#x60;successful-report&#x60;) 4. **Rapport** : Liste structurée des non-conformités  ## Réponses  **200 OK** : XML conforme &#x60;&#x60;&#x60;json {   \&quot;message\&quot;: \&quot;Le XML est conforme au profil EN16931\&quot; } &#x60;&#x60;&#x60;  **400 Bad Request** : XML non conforme &#x60;&#x60;&#x60;json {   \&quot;detail\&quot;: [     \&quot;[BR-1] Le total de la facture (120.00) ne correspond pas à la somme calculée (100.00 + 20.00)\&quot;,     \&quot;[FR-1] Le SIRET fournisseur doit contenir exactement 14 chiffres\&quot;   ] } &#x60;&#x60;&#x60;  ## Cas d&#39;usage  - **Pré-validation** : Vérifier un XML avant intégration dans un PDF/A - **Debugging** : Identifier précisément les erreurs de génération - **Tests** : Valider des XMLs de test ou d&#39;exemple - **Conformité** : S&#39;assurer du respect des règles européennes et françaises - **Développement** : Tester rapidement sans générer de PDF  ## Temps de traitement  - Profil MINIMUM : ~0.5 seconde - Profil EN16931 : ~1-2 secondes - Profil EXTENDED : ~2-3 secondes
     * @param fichierXml Fichier XML Factur-X à valider (format .xml). (required)
     * @param profil Profil de validation (MINIMUM, BASIC, EN16931, EXTENDED). (optional)
     * @return ReponseValidationSucces
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Le XML ne respecte pas les règles du profil Factur-X </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ReponseValidationSucces validerXmlApiV1TraitementValiderXmlPost(@javax.annotation.Nonnull File fichierXml, @javax.annotation.Nullable ProfilAPI profil) throws ApiException {
        ApiResponse<ReponseValidationSucces> localVarResp = validerXmlApiV1TraitementValiderXmlPostWithHttpInfo(fichierXml, profil);
        return localVarResp.getData();
    }

    /**
     * Valider un XML Factur-X existant
     * Valide un fichier XML Factur-X contre les règles métier Schematron selon la norme EN 16931.  ## Norme appliquée  **Schematron ISO/IEC 19757-3** : Langage de validation de règles métier pour XML - Validation sémantique (au-delà de la syntaxe XSD) - Règles métier européennes EN 16931 - Règles françaises spécifiques Factur-X - Calculs arithmétiques et cohérence des données  ## Profils et règles validées  ### MINIMUM (45 règles) - Identifiant de facture unique - Dates (émission, échéance) - Identifiants parties (SIRET/SIREN) - Montant total TTC  ### BASIC (102 règles) - Toutes les règles MINIMUM - Lignes de facture détaillées - Calculs de TVA basiques - Modes de paiement - Références (commande, contrat)  ### EN16931 (178 règles) - Toutes les règles BASIC - **Règles européennes (BR-xx)** : 81 règles business - **Règles françaises (FR-xx)** : 12 règles spécifiques France - **Calculs avancés (CR-xx)** : 32 règles de calcul - **Codes normalisés (CL-xx)** : 52 listes de codes  ### EXTENDED (210+ règles) - Toutes les règles EN16931 - Informations logistiques - Données comptables avancées - Références externes multiples  ## Contrôles effectués  ### 1. Validation syntaxique - Parsing XML correct (UTF-8, bien formé) - Namespaces UN/CEFACT présents - Structure hiérarchique respectée  ### 2. Règles business (BR-xx) Exemples : - &#x60;BR-1&#x60; : Le total de la facture doit être égal à la somme des totaux de lignes + montants au niveau document - &#x60;BR-CO-10&#x60; : La somme des montants de base de TVA doit être égale au total net de la facture - &#x60;BR-16&#x60; : Le code de devise de la facture doit figurer dans la liste ISO 4217  ### 3. Règles françaises (FR-xx) Exemples : - &#x60;FR-1&#x60; : Le SIRET fournisseur doit avoir 14 chiffres - &#x60;FR-2&#x60; : Le SIRET client doit avoir 14 chiffres (si présent) - &#x60;FR-5&#x60; : Le numéro de TVA intracommunautaire doit être au format FRxx999999999  ### 4. Règles de calcul (CR-xx) - Montants HT + TVA &#x3D; TTC - Somme des lignes &#x3D; Total document - Remises et majorations correctement appliquées - Arrondis conformes (2 décimales pour les montants)  ### 5. Codes normalisés (CL-xx) - Codes pays ISO 3166-1 alpha-2 - Codes devises ISO 4217 - Unités de mesure UN/ECE Rec 20 - Codes TVA (types, catégories, exonérations) - SchemeID pour identifiants (0002&#x3D;SIREN, 0009&#x3D;SIRET, etc.)  ## Processus de validation  1. **Chargement XSLT** : Fichier Schematron converti en XSLT (Saxon-HE) 2. **Transformation** : Application des règles sur le XML 3. **Analyse résultats** : Extraction des erreurs (&#x60;failed-assert&#x60;) et avertissements (&#x60;successful-report&#x60;) 4. **Rapport** : Liste structurée des non-conformités  ## Réponses  **200 OK** : XML conforme &#x60;&#x60;&#x60;json {   \&quot;message\&quot;: \&quot;Le XML est conforme au profil EN16931\&quot; } &#x60;&#x60;&#x60;  **400 Bad Request** : XML non conforme &#x60;&#x60;&#x60;json {   \&quot;detail\&quot;: [     \&quot;[BR-1] Le total de la facture (120.00) ne correspond pas à la somme calculée (100.00 + 20.00)\&quot;,     \&quot;[FR-1] Le SIRET fournisseur doit contenir exactement 14 chiffres\&quot;   ] } &#x60;&#x60;&#x60;  ## Cas d&#39;usage  - **Pré-validation** : Vérifier un XML avant intégration dans un PDF/A - **Debugging** : Identifier précisément les erreurs de génération - **Tests** : Valider des XMLs de test ou d&#39;exemple - **Conformité** : S&#39;assurer du respect des règles européennes et françaises - **Développement** : Tester rapidement sans générer de PDF  ## Temps de traitement  - Profil MINIMUM : ~0.5 seconde - Profil EN16931 : ~1-2 secondes - Profil EXTENDED : ~2-3 secondes
     * @param fichierXml Fichier XML Factur-X à valider (format .xml). (required)
     * @param profil Profil de validation (MINIMUM, BASIC, EN16931, EXTENDED). (optional)
     * @return ApiResponse&lt;ReponseValidationSucces&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Le XML ne respecte pas les règles du profil Factur-X </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<ReponseValidationSucces> validerXmlApiV1TraitementValiderXmlPostWithHttpInfo(@javax.annotation.Nonnull File fichierXml, @javax.annotation.Nullable ProfilAPI profil) throws ApiException {
        okhttp3.Call localVarCall = validerXmlApiV1TraitementValiderXmlPostValidateBeforeCall(fichierXml, profil, null);
        Type localVarReturnType = new TypeToken<ReponseValidationSucces>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Valider un XML Factur-X existant (asynchronously)
     * Valide un fichier XML Factur-X contre les règles métier Schematron selon la norme EN 16931.  ## Norme appliquée  **Schematron ISO/IEC 19757-3** : Langage de validation de règles métier pour XML - Validation sémantique (au-delà de la syntaxe XSD) - Règles métier européennes EN 16931 - Règles françaises spécifiques Factur-X - Calculs arithmétiques et cohérence des données  ## Profils et règles validées  ### MINIMUM (45 règles) - Identifiant de facture unique - Dates (émission, échéance) - Identifiants parties (SIRET/SIREN) - Montant total TTC  ### BASIC (102 règles) - Toutes les règles MINIMUM - Lignes de facture détaillées - Calculs de TVA basiques - Modes de paiement - Références (commande, contrat)  ### EN16931 (178 règles) - Toutes les règles BASIC - **Règles européennes (BR-xx)** : 81 règles business - **Règles françaises (FR-xx)** : 12 règles spécifiques France - **Calculs avancés (CR-xx)** : 32 règles de calcul - **Codes normalisés (CL-xx)** : 52 listes de codes  ### EXTENDED (210+ règles) - Toutes les règles EN16931 - Informations logistiques - Données comptables avancées - Références externes multiples  ## Contrôles effectués  ### 1. Validation syntaxique - Parsing XML correct (UTF-8, bien formé) - Namespaces UN/CEFACT présents - Structure hiérarchique respectée  ### 2. Règles business (BR-xx) Exemples : - &#x60;BR-1&#x60; : Le total de la facture doit être égal à la somme des totaux de lignes + montants au niveau document - &#x60;BR-CO-10&#x60; : La somme des montants de base de TVA doit être égale au total net de la facture - &#x60;BR-16&#x60; : Le code de devise de la facture doit figurer dans la liste ISO 4217  ### 3. Règles françaises (FR-xx) Exemples : - &#x60;FR-1&#x60; : Le SIRET fournisseur doit avoir 14 chiffres - &#x60;FR-2&#x60; : Le SIRET client doit avoir 14 chiffres (si présent) - &#x60;FR-5&#x60; : Le numéro de TVA intracommunautaire doit être au format FRxx999999999  ### 4. Règles de calcul (CR-xx) - Montants HT + TVA &#x3D; TTC - Somme des lignes &#x3D; Total document - Remises et majorations correctement appliquées - Arrondis conformes (2 décimales pour les montants)  ### 5. Codes normalisés (CL-xx) - Codes pays ISO 3166-1 alpha-2 - Codes devises ISO 4217 - Unités de mesure UN/ECE Rec 20 - Codes TVA (types, catégories, exonérations) - SchemeID pour identifiants (0002&#x3D;SIREN, 0009&#x3D;SIRET, etc.)  ## Processus de validation  1. **Chargement XSLT** : Fichier Schematron converti en XSLT (Saxon-HE) 2. **Transformation** : Application des règles sur le XML 3. **Analyse résultats** : Extraction des erreurs (&#x60;failed-assert&#x60;) et avertissements (&#x60;successful-report&#x60;) 4. **Rapport** : Liste structurée des non-conformités  ## Réponses  **200 OK** : XML conforme &#x60;&#x60;&#x60;json {   \&quot;message\&quot;: \&quot;Le XML est conforme au profil EN16931\&quot; } &#x60;&#x60;&#x60;  **400 Bad Request** : XML non conforme &#x60;&#x60;&#x60;json {   \&quot;detail\&quot;: [     \&quot;[BR-1] Le total de la facture (120.00) ne correspond pas à la somme calculée (100.00 + 20.00)\&quot;,     \&quot;[FR-1] Le SIRET fournisseur doit contenir exactement 14 chiffres\&quot;   ] } &#x60;&#x60;&#x60;  ## Cas d&#39;usage  - **Pré-validation** : Vérifier un XML avant intégration dans un PDF/A - **Debugging** : Identifier précisément les erreurs de génération - **Tests** : Valider des XMLs de test ou d&#39;exemple - **Conformité** : S&#39;assurer du respect des règles européennes et françaises - **Développement** : Tester rapidement sans générer de PDF  ## Temps de traitement  - Profil MINIMUM : ~0.5 seconde - Profil EN16931 : ~1-2 secondes - Profil EXTENDED : ~2-3 secondes
     * @param fichierXml Fichier XML Factur-X à valider (format .xml). (required)
     * @param profil Profil de validation (MINIMUM, BASIC, EN16931, EXTENDED). (optional)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Le XML ne respecte pas les règles du profil Factur-X </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call validerXmlApiV1TraitementValiderXmlPostAsync(@javax.annotation.Nonnull File fichierXml, @javax.annotation.Nullable ProfilAPI profil, final ApiCallback<ReponseValidationSucces> _callback) throws ApiException {

        okhttp3.Call localVarCall = validerXmlApiV1TraitementValiderXmlPostValidateBeforeCall(fichierXml, profil, _callback);
        Type localVarReturnType = new TypeToken<ReponseValidationSucces>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
