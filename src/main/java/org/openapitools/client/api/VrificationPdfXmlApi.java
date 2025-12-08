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


import org.openapitools.client.model.APIError;
import java.io.File;
import org.openapitools.client.model.HTTPValidationError;
import org.openapitools.client.model.ReponseTache;
import org.openapitools.client.model.ReponseVerificationSucces;
import org.openapitools.client.model.StatutTache;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VrificationPdfXmlApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public VrificationPdfXmlApi() {
        this(Configuration.getDefaultApiClient());
    }

    public VrificationPdfXmlApi(ApiClient apiClient) {
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
     * Build call for obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet
     * @param idTache  (required)
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
    public okhttp3.Call obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGetCall(@javax.annotation.Nonnull String idTache, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/verification/verifier-async/{id_tache}/statut"
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
    private okhttp3.Call obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGetValidateBeforeCall(@javax.annotation.Nonnull String idTache, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'idTache' is set
        if (idTache == null) {
            throw new ApiException("Missing the required parameter 'idTache' when calling obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet(Async)");
        }

        return obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGetCall(idTache, _callback);

    }

    /**
     * Obtenir le statut d&#39;une vérification asynchrone
     * Récupère le statut et le résultat d&#39;une tâche de vérification asynchrone.  **Statuts possibles:** - &#x60;PENDING&#x60;: Tâche en attente dans la file - &#x60;STARTED&#x60;: Tâche en cours d&#39;exécution - &#x60;SUCCESS&#x60;: Tâche terminée avec succès (voir &#x60;resultat&#x60;) - &#x60;FAILURE&#x60;: Erreur système (exception non gérée)  **Note:** Le champ &#x60;resultat.statut&#x60; peut être \&quot;SUCCES\&quot; ou \&quot;ERREUR\&quot; indépendamment du statut Celery (qui sera toujours SUCCESS si la tâche s&#39;est exécutée).
     * @param idTache  (required)
     * @return StatutTache
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public StatutTache obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet(@javax.annotation.Nonnull String idTache) throws ApiException {
        ApiResponse<StatutTache> localVarResp = obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGetWithHttpInfo(idTache);
        return localVarResp.getData();
    }

    /**
     * Obtenir le statut d&#39;une vérification asynchrone
     * Récupère le statut et le résultat d&#39;une tâche de vérification asynchrone.  **Statuts possibles:** - &#x60;PENDING&#x60;: Tâche en attente dans la file - &#x60;STARTED&#x60;: Tâche en cours d&#39;exécution - &#x60;SUCCESS&#x60;: Tâche terminée avec succès (voir &#x60;resultat&#x60;) - &#x60;FAILURE&#x60;: Erreur système (exception non gérée)  **Note:** Le champ &#x60;resultat.statut&#x60; peut être \&quot;SUCCES\&quot; ou \&quot;ERREUR\&quot; indépendamment du statut Celery (qui sera toujours SUCCESS si la tâche s&#39;est exécutée).
     * @param idTache  (required)
     * @return ApiResponse&lt;StatutTache&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<StatutTache> obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGetWithHttpInfo(@javax.annotation.Nonnull String idTache) throws ApiException {
        okhttp3.Call localVarCall = obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGetValidateBeforeCall(idTache, null);
        Type localVarReturnType = new TypeToken<StatutTache>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Obtenir le statut d&#39;une vérification asynchrone (asynchronously)
     * Récupère le statut et le résultat d&#39;une tâche de vérification asynchrone.  **Statuts possibles:** - &#x60;PENDING&#x60;: Tâche en attente dans la file - &#x60;STARTED&#x60;: Tâche en cours d&#39;exécution - &#x60;SUCCESS&#x60;: Tâche terminée avec succès (voir &#x60;resultat&#x60;) - &#x60;FAILURE&#x60;: Erreur système (exception non gérée)  **Note:** Le champ &#x60;resultat.statut&#x60; peut être \&quot;SUCCES\&quot; ou \&quot;ERREUR\&quot; indépendamment du statut Celery (qui sera toujours SUCCESS si la tâche s&#39;est exécutée).
     * @param idTache  (required)
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
    public okhttp3.Call obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGetAsync(@javax.annotation.Nonnull String idTache, final ApiCallback<StatutTache> _callback) throws ApiException {

        okhttp3.Call localVarCall = obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGetValidateBeforeCall(idTache, _callback);
        Type localVarReturnType = new TypeToken<StatutTache>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet_0
     * @param idTache  (required)
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
    public okhttp3.Call obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet_0Call(@javax.annotation.Nonnull String idTache, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/verification/verifier-async/{id_tache}/statut"
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
    private okhttp3.Call obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet_0ValidateBeforeCall(@javax.annotation.Nonnull String idTache, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'idTache' is set
        if (idTache == null) {
            throw new ApiException("Missing the required parameter 'idTache' when calling obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet_0(Async)");
        }

        return obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet_0Call(idTache, _callback);

    }

    /**
     * Obtenir le statut d&#39;une vérification asynchrone
     * Récupère le statut et le résultat d&#39;une tâche de vérification asynchrone.  **Statuts possibles:** - &#x60;PENDING&#x60;: Tâche en attente dans la file - &#x60;STARTED&#x60;: Tâche en cours d&#39;exécution - &#x60;SUCCESS&#x60;: Tâche terminée avec succès (voir &#x60;resultat&#x60;) - &#x60;FAILURE&#x60;: Erreur système (exception non gérée)  **Note:** Le champ &#x60;resultat.statut&#x60; peut être \&quot;SUCCES\&quot; ou \&quot;ERREUR\&quot; indépendamment du statut Celery (qui sera toujours SUCCESS si la tâche s&#39;est exécutée).
     * @param idTache  (required)
     * @return StatutTache
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public StatutTache obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet_0(@javax.annotation.Nonnull String idTache) throws ApiException {
        ApiResponse<StatutTache> localVarResp = obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet_0WithHttpInfo(idTache);
        return localVarResp.getData();
    }

    /**
     * Obtenir le statut d&#39;une vérification asynchrone
     * Récupère le statut et le résultat d&#39;une tâche de vérification asynchrone.  **Statuts possibles:** - &#x60;PENDING&#x60;: Tâche en attente dans la file - &#x60;STARTED&#x60;: Tâche en cours d&#39;exécution - &#x60;SUCCESS&#x60;: Tâche terminée avec succès (voir &#x60;resultat&#x60;) - &#x60;FAILURE&#x60;: Erreur système (exception non gérée)  **Note:** Le champ &#x60;resultat.statut&#x60; peut être \&quot;SUCCES\&quot; ou \&quot;ERREUR\&quot; indépendamment du statut Celery (qui sera toujours SUCCESS si la tâche s&#39;est exécutée).
     * @param idTache  (required)
     * @return ApiResponse&lt;StatutTache&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<StatutTache> obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet_0WithHttpInfo(@javax.annotation.Nonnull String idTache) throws ApiException {
        okhttp3.Call localVarCall = obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet_0ValidateBeforeCall(idTache, null);
        Type localVarReturnType = new TypeToken<StatutTache>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Obtenir le statut d&#39;une vérification asynchrone (asynchronously)
     * Récupère le statut et le résultat d&#39;une tâche de vérification asynchrone.  **Statuts possibles:** - &#x60;PENDING&#x60;: Tâche en attente dans la file - &#x60;STARTED&#x60;: Tâche en cours d&#39;exécution - &#x60;SUCCESS&#x60;: Tâche terminée avec succès (voir &#x60;resultat&#x60;) - &#x60;FAILURE&#x60;: Erreur système (exception non gérée)  **Note:** Le champ &#x60;resultat.statut&#x60; peut être \&quot;SUCCES\&quot; ou \&quot;ERREUR\&quot; indépendamment du statut Celery (qui sera toujours SUCCESS si la tâche s&#39;est exécutée).
     * @param idTache  (required)
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
    public okhttp3.Call obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet_0Async(@javax.annotation.Nonnull String idTache, final ApiCallback<StatutTache> _callback) throws ApiException {

        okhttp3.Call localVarCall = obtenirStatutVerificationApiV1VerificationVerifierAsyncIdTacheStatutGet_0ValidateBeforeCall(idTache, _callback);
        Type localVarReturnType = new TypeToken<StatutTache>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for verifierPdfAsyncApiV1VerificationVerifierAsyncPost
     * @param fichierPdf Fichier PDF Factur-X à vérifier (required)
     * @param forcerOcr Forcer l&#39;utilisation de l&#39;OCR même si le PDF contient du texte natif (optional, default to false)
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
    public okhttp3.Call verifierPdfAsyncApiV1VerificationVerifierAsyncPostCall(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable Boolean forcerOcr, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/verification/verifier-async";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (fichierPdf != null) {
            localVarFormParams.put("fichier_pdf", fichierPdf);
        }

        if (forcerOcr != null) {
            localVarFormParams.put("forcer_ocr", forcerOcr);
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
    private okhttp3.Call verifierPdfAsyncApiV1VerificationVerifierAsyncPostValidateBeforeCall(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable Boolean forcerOcr, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'fichierPdf' is set
        if (fichierPdf == null) {
            throw new ApiException("Missing the required parameter 'fichierPdf' when calling verifierPdfAsyncApiV1VerificationVerifierAsyncPost(Async)");
        }

        return verifierPdfAsyncApiV1VerificationVerifierAsyncPostCall(fichierPdf, forcerOcr, _callback);

    }

    /**
     * Vérifier la conformité PDF/XML Factur-X (asynchrone)
     * Vérifie la conformité PDF/XML Factur-X de manière asynchrone.  **IMPORTANT**: Seuls les PDF Factur-X (avec XML embarqué) sont acceptés. Les PDF sans XML Factur-X retourneront une erreur &#x60;NOT_FACTURX&#x60; dans le résultat.  Cette version utilise une tâche Celery et peut faire appel au service OCR si le PDF est une image ou si &#x60;forcer_ocr&#x3D;true&#x60;.  **Retourne immédiatement** un ID de tâche. Utilisez &#x60;/verifier-async/{id_tache}/statut&#x60; pour récupérer le résultat.  **Principe de vérification (Factur-X 1.08):** - Principe n°2: Le XML ne peut contenir que des infos présentes dans le PDF - Principe n°4: Toute info XML doit être présente et conforme dans le PDF  **Champs vérifiés:** - Identification: BT-1 (n° facture), BT-2 (date), BT-3 (type), BT-5 (devise), BT-23 (cadre) - Vendeur: BT-27 (nom), BT-29 (SIRET), BT-30 (SIREN), BT-31 (TVA) - Acheteur: BT-44 (nom), BT-46 (SIRET), BT-47 (SIREN), BT-48 (TVA) - Montants: BT-109 (HT), BT-110 (TVA), BT-112 (TTC), BT-115 (à payer) - Ventilation TVA: BT-116, BT-117, BT-118, BT-119 - Lignes de facture: BT-153, BT-129, BT-146, BT-131 - Notes obligatoires: PMT, PMD, AAB - Règle BR-FR-09: cohérence SIRET/SIREN  **Avantages par rapport à la version synchrone:** - Support OCR pour les PDF images (via service DocTR) - Timeout plus long pour les gros documents - Ne bloque pas le serveur
     * @param fichierPdf Fichier PDF Factur-X à vérifier (required)
     * @param forcerOcr Forcer l&#39;utilisation de l&#39;OCR même si le PDF contient du texte natif (optional, default to false)
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
    public ReponseTache verifierPdfAsyncApiV1VerificationVerifierAsyncPost(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable Boolean forcerOcr) throws ApiException {
        ApiResponse<ReponseTache> localVarResp = verifierPdfAsyncApiV1VerificationVerifierAsyncPostWithHttpInfo(fichierPdf, forcerOcr);
        return localVarResp.getData();
    }

    /**
     * Vérifier la conformité PDF/XML Factur-X (asynchrone)
     * Vérifie la conformité PDF/XML Factur-X de manière asynchrone.  **IMPORTANT**: Seuls les PDF Factur-X (avec XML embarqué) sont acceptés. Les PDF sans XML Factur-X retourneront une erreur &#x60;NOT_FACTURX&#x60; dans le résultat.  Cette version utilise une tâche Celery et peut faire appel au service OCR si le PDF est une image ou si &#x60;forcer_ocr&#x3D;true&#x60;.  **Retourne immédiatement** un ID de tâche. Utilisez &#x60;/verifier-async/{id_tache}/statut&#x60; pour récupérer le résultat.  **Principe de vérification (Factur-X 1.08):** - Principe n°2: Le XML ne peut contenir que des infos présentes dans le PDF - Principe n°4: Toute info XML doit être présente et conforme dans le PDF  **Champs vérifiés:** - Identification: BT-1 (n° facture), BT-2 (date), BT-3 (type), BT-5 (devise), BT-23 (cadre) - Vendeur: BT-27 (nom), BT-29 (SIRET), BT-30 (SIREN), BT-31 (TVA) - Acheteur: BT-44 (nom), BT-46 (SIRET), BT-47 (SIREN), BT-48 (TVA) - Montants: BT-109 (HT), BT-110 (TVA), BT-112 (TTC), BT-115 (à payer) - Ventilation TVA: BT-116, BT-117, BT-118, BT-119 - Lignes de facture: BT-153, BT-129, BT-146, BT-131 - Notes obligatoires: PMT, PMD, AAB - Règle BR-FR-09: cohérence SIRET/SIREN  **Avantages par rapport à la version synchrone:** - Support OCR pour les PDF images (via service DocTR) - Timeout plus long pour les gros documents - Ne bloque pas le serveur
     * @param fichierPdf Fichier PDF Factur-X à vérifier (required)
     * @param forcerOcr Forcer l&#39;utilisation de l&#39;OCR même si le PDF contient du texte natif (optional, default to false)
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
    public ApiResponse<ReponseTache> verifierPdfAsyncApiV1VerificationVerifierAsyncPostWithHttpInfo(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable Boolean forcerOcr) throws ApiException {
        okhttp3.Call localVarCall = verifierPdfAsyncApiV1VerificationVerifierAsyncPostValidateBeforeCall(fichierPdf, forcerOcr, null);
        Type localVarReturnType = new TypeToken<ReponseTache>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Vérifier la conformité PDF/XML Factur-X (asynchrone) (asynchronously)
     * Vérifie la conformité PDF/XML Factur-X de manière asynchrone.  **IMPORTANT**: Seuls les PDF Factur-X (avec XML embarqué) sont acceptés. Les PDF sans XML Factur-X retourneront une erreur &#x60;NOT_FACTURX&#x60; dans le résultat.  Cette version utilise une tâche Celery et peut faire appel au service OCR si le PDF est une image ou si &#x60;forcer_ocr&#x3D;true&#x60;.  **Retourne immédiatement** un ID de tâche. Utilisez &#x60;/verifier-async/{id_tache}/statut&#x60; pour récupérer le résultat.  **Principe de vérification (Factur-X 1.08):** - Principe n°2: Le XML ne peut contenir que des infos présentes dans le PDF - Principe n°4: Toute info XML doit être présente et conforme dans le PDF  **Champs vérifiés:** - Identification: BT-1 (n° facture), BT-2 (date), BT-3 (type), BT-5 (devise), BT-23 (cadre) - Vendeur: BT-27 (nom), BT-29 (SIRET), BT-30 (SIREN), BT-31 (TVA) - Acheteur: BT-44 (nom), BT-46 (SIRET), BT-47 (SIREN), BT-48 (TVA) - Montants: BT-109 (HT), BT-110 (TVA), BT-112 (TTC), BT-115 (à payer) - Ventilation TVA: BT-116, BT-117, BT-118, BT-119 - Lignes de facture: BT-153, BT-129, BT-146, BT-131 - Notes obligatoires: PMT, PMD, AAB - Règle BR-FR-09: cohérence SIRET/SIREN  **Avantages par rapport à la version synchrone:** - Support OCR pour les PDF images (via service DocTR) - Timeout plus long pour les gros documents - Ne bloque pas le serveur
     * @param fichierPdf Fichier PDF Factur-X à vérifier (required)
     * @param forcerOcr Forcer l&#39;utilisation de l&#39;OCR même si le PDF contient du texte natif (optional, default to false)
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
    public okhttp3.Call verifierPdfAsyncApiV1VerificationVerifierAsyncPostAsync(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable Boolean forcerOcr, final ApiCallback<ReponseTache> _callback) throws ApiException {

        okhttp3.Call localVarCall = verifierPdfAsyncApiV1VerificationVerifierAsyncPostValidateBeforeCall(fichierPdf, forcerOcr, _callback);
        Type localVarReturnType = new TypeToken<ReponseTache>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for verifierPdfAsyncApiV1VerificationVerifierAsyncPost_0
     * @param fichierPdf Fichier PDF Factur-X à vérifier (required)
     * @param forcerOcr Forcer l&#39;utilisation de l&#39;OCR même si le PDF contient du texte natif (optional, default to false)
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
    public okhttp3.Call verifierPdfAsyncApiV1VerificationVerifierAsyncPost_0Call(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable Boolean forcerOcr, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/verification/verifier-async";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (fichierPdf != null) {
            localVarFormParams.put("fichier_pdf", fichierPdf);
        }

        if (forcerOcr != null) {
            localVarFormParams.put("forcer_ocr", forcerOcr);
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
    private okhttp3.Call verifierPdfAsyncApiV1VerificationVerifierAsyncPost_0ValidateBeforeCall(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable Boolean forcerOcr, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'fichierPdf' is set
        if (fichierPdf == null) {
            throw new ApiException("Missing the required parameter 'fichierPdf' when calling verifierPdfAsyncApiV1VerificationVerifierAsyncPost_0(Async)");
        }

        return verifierPdfAsyncApiV1VerificationVerifierAsyncPost_0Call(fichierPdf, forcerOcr, _callback);

    }

    /**
     * Vérifier la conformité PDF/XML Factur-X (asynchrone)
     * Vérifie la conformité PDF/XML Factur-X de manière asynchrone.  **IMPORTANT**: Seuls les PDF Factur-X (avec XML embarqué) sont acceptés. Les PDF sans XML Factur-X retourneront une erreur &#x60;NOT_FACTURX&#x60; dans le résultat.  Cette version utilise une tâche Celery et peut faire appel au service OCR si le PDF est une image ou si &#x60;forcer_ocr&#x3D;true&#x60;.  **Retourne immédiatement** un ID de tâche. Utilisez &#x60;/verifier-async/{id_tache}/statut&#x60; pour récupérer le résultat.  **Principe de vérification (Factur-X 1.08):** - Principe n°2: Le XML ne peut contenir que des infos présentes dans le PDF - Principe n°4: Toute info XML doit être présente et conforme dans le PDF  **Champs vérifiés:** - Identification: BT-1 (n° facture), BT-2 (date), BT-3 (type), BT-5 (devise), BT-23 (cadre) - Vendeur: BT-27 (nom), BT-29 (SIRET), BT-30 (SIREN), BT-31 (TVA) - Acheteur: BT-44 (nom), BT-46 (SIRET), BT-47 (SIREN), BT-48 (TVA) - Montants: BT-109 (HT), BT-110 (TVA), BT-112 (TTC), BT-115 (à payer) - Ventilation TVA: BT-116, BT-117, BT-118, BT-119 - Lignes de facture: BT-153, BT-129, BT-146, BT-131 - Notes obligatoires: PMT, PMD, AAB - Règle BR-FR-09: cohérence SIRET/SIREN  **Avantages par rapport à la version synchrone:** - Support OCR pour les PDF images (via service DocTR) - Timeout plus long pour les gros documents - Ne bloque pas le serveur
     * @param fichierPdf Fichier PDF Factur-X à vérifier (required)
     * @param forcerOcr Forcer l&#39;utilisation de l&#39;OCR même si le PDF contient du texte natif (optional, default to false)
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
    public ReponseTache verifierPdfAsyncApiV1VerificationVerifierAsyncPost_0(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable Boolean forcerOcr) throws ApiException {
        ApiResponse<ReponseTache> localVarResp = verifierPdfAsyncApiV1VerificationVerifierAsyncPost_0WithHttpInfo(fichierPdf, forcerOcr);
        return localVarResp.getData();
    }

    /**
     * Vérifier la conformité PDF/XML Factur-X (asynchrone)
     * Vérifie la conformité PDF/XML Factur-X de manière asynchrone.  **IMPORTANT**: Seuls les PDF Factur-X (avec XML embarqué) sont acceptés. Les PDF sans XML Factur-X retourneront une erreur &#x60;NOT_FACTURX&#x60; dans le résultat.  Cette version utilise une tâche Celery et peut faire appel au service OCR si le PDF est une image ou si &#x60;forcer_ocr&#x3D;true&#x60;.  **Retourne immédiatement** un ID de tâche. Utilisez &#x60;/verifier-async/{id_tache}/statut&#x60; pour récupérer le résultat.  **Principe de vérification (Factur-X 1.08):** - Principe n°2: Le XML ne peut contenir que des infos présentes dans le PDF - Principe n°4: Toute info XML doit être présente et conforme dans le PDF  **Champs vérifiés:** - Identification: BT-1 (n° facture), BT-2 (date), BT-3 (type), BT-5 (devise), BT-23 (cadre) - Vendeur: BT-27 (nom), BT-29 (SIRET), BT-30 (SIREN), BT-31 (TVA) - Acheteur: BT-44 (nom), BT-46 (SIRET), BT-47 (SIREN), BT-48 (TVA) - Montants: BT-109 (HT), BT-110 (TVA), BT-112 (TTC), BT-115 (à payer) - Ventilation TVA: BT-116, BT-117, BT-118, BT-119 - Lignes de facture: BT-153, BT-129, BT-146, BT-131 - Notes obligatoires: PMT, PMD, AAB - Règle BR-FR-09: cohérence SIRET/SIREN  **Avantages par rapport à la version synchrone:** - Support OCR pour les PDF images (via service DocTR) - Timeout plus long pour les gros documents - Ne bloque pas le serveur
     * @param fichierPdf Fichier PDF Factur-X à vérifier (required)
     * @param forcerOcr Forcer l&#39;utilisation de l&#39;OCR même si le PDF contient du texte natif (optional, default to false)
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
    public ApiResponse<ReponseTache> verifierPdfAsyncApiV1VerificationVerifierAsyncPost_0WithHttpInfo(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable Boolean forcerOcr) throws ApiException {
        okhttp3.Call localVarCall = verifierPdfAsyncApiV1VerificationVerifierAsyncPost_0ValidateBeforeCall(fichierPdf, forcerOcr, null);
        Type localVarReturnType = new TypeToken<ReponseTache>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Vérifier la conformité PDF/XML Factur-X (asynchrone) (asynchronously)
     * Vérifie la conformité PDF/XML Factur-X de manière asynchrone.  **IMPORTANT**: Seuls les PDF Factur-X (avec XML embarqué) sont acceptés. Les PDF sans XML Factur-X retourneront une erreur &#x60;NOT_FACTURX&#x60; dans le résultat.  Cette version utilise une tâche Celery et peut faire appel au service OCR si le PDF est une image ou si &#x60;forcer_ocr&#x3D;true&#x60;.  **Retourne immédiatement** un ID de tâche. Utilisez &#x60;/verifier-async/{id_tache}/statut&#x60; pour récupérer le résultat.  **Principe de vérification (Factur-X 1.08):** - Principe n°2: Le XML ne peut contenir que des infos présentes dans le PDF - Principe n°4: Toute info XML doit être présente et conforme dans le PDF  **Champs vérifiés:** - Identification: BT-1 (n° facture), BT-2 (date), BT-3 (type), BT-5 (devise), BT-23 (cadre) - Vendeur: BT-27 (nom), BT-29 (SIRET), BT-30 (SIREN), BT-31 (TVA) - Acheteur: BT-44 (nom), BT-46 (SIRET), BT-47 (SIREN), BT-48 (TVA) - Montants: BT-109 (HT), BT-110 (TVA), BT-112 (TTC), BT-115 (à payer) - Ventilation TVA: BT-116, BT-117, BT-118, BT-119 - Lignes de facture: BT-153, BT-129, BT-146, BT-131 - Notes obligatoires: PMT, PMD, AAB - Règle BR-FR-09: cohérence SIRET/SIREN  **Avantages par rapport à la version synchrone:** - Support OCR pour les PDF images (via service DocTR) - Timeout plus long pour les gros documents - Ne bloque pas le serveur
     * @param fichierPdf Fichier PDF Factur-X à vérifier (required)
     * @param forcerOcr Forcer l&#39;utilisation de l&#39;OCR même si le PDF contient du texte natif (optional, default to false)
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
    public okhttp3.Call verifierPdfAsyncApiV1VerificationVerifierAsyncPost_0Async(@javax.annotation.Nonnull File fichierPdf, @javax.annotation.Nullable Boolean forcerOcr, final ApiCallback<ReponseTache> _callback) throws ApiException {

        okhttp3.Call localVarCall = verifierPdfAsyncApiV1VerificationVerifierAsyncPost_0ValidateBeforeCall(fichierPdf, forcerOcr, _callback);
        Type localVarReturnType = new TypeToken<ReponseTache>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for verifierPdfSyncApiV1VerificationVerifierPost
     * @param fichierPdf Fichier PDF Factur-X à vérifier (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Vérification réussie </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Erreur de vérification (PDF non Factur-X, invalide, etc.) </td><td>  -  </td></tr>
        <tr><td> 413 </td><td> PDF trop volumineux ou trop de pages </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call verifierPdfSyncApiV1VerificationVerifierPostCall(@javax.annotation.Nonnull File fichierPdf, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/verification/verifier";

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
    private okhttp3.Call verifierPdfSyncApiV1VerificationVerifierPostValidateBeforeCall(@javax.annotation.Nonnull File fichierPdf, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'fichierPdf' is set
        if (fichierPdf == null) {
            throw new ApiException("Missing the required parameter 'fichierPdf' when calling verifierPdfSyncApiV1VerificationVerifierPost(Async)");
        }

        return verifierPdfSyncApiV1VerificationVerifierPostCall(fichierPdf, _callback);

    }

    /**
     * Vérifier la conformité PDF/XML Factur-X (synchrone)
     * Vérifie la conformité entre le PDF et son XML Factur-X embarqué.  **IMPORTANT**: Seuls les PDF Factur-X (avec XML embarqué) sont acceptés. Les PDF sans XML Factur-X seront rejetés avec une erreur 400.  Cette version synchrone utilise uniquement l&#39;extraction PDF native (pdfplumber). Pour les PDF images nécessitant de l&#39;OCR, utilisez l&#39;endpoint &#x60;/verifier-async&#x60;.  **Principe de vérification (Factur-X 1.08):** - Principe n°2: Le XML ne peut contenir que des infos présentes dans le PDF - Principe n°4: Toute info XML doit être présente et conforme dans le PDF  **Champs vérifiés:** - Identification: BT-1 (n° facture), BT-2 (date), BT-3 (type), BT-5 (devise), BT-23 (cadre) - Vendeur: BT-27 (nom), BT-29 (SIRET), BT-30 (SIREN), BT-31 (TVA) - Acheteur: BT-44 (nom), BT-46 (SIRET), BT-47 (SIREN), BT-48 (TVA) - Montants: BT-109 (HT), BT-110 (TVA), BT-112 (TTC), BT-115 (à payer) - Ventilation TVA: BT-116, BT-117, BT-118, BT-119 - Lignes de facture: BT-153, BT-129, BT-146, BT-131 - Notes obligatoires: PMT, PMD, AAB - Règle BR-FR-09: cohérence SIRET/SIREN
     * @param fichierPdf Fichier PDF Factur-X à vérifier (required)
     * @return ReponseVerificationSucces
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Vérification réussie </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Erreur de vérification (PDF non Factur-X, invalide, etc.) </td><td>  -  </td></tr>
        <tr><td> 413 </td><td> PDF trop volumineux ou trop de pages </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ReponseVerificationSucces verifierPdfSyncApiV1VerificationVerifierPost(@javax.annotation.Nonnull File fichierPdf) throws ApiException {
        ApiResponse<ReponseVerificationSucces> localVarResp = verifierPdfSyncApiV1VerificationVerifierPostWithHttpInfo(fichierPdf);
        return localVarResp.getData();
    }

    /**
     * Vérifier la conformité PDF/XML Factur-X (synchrone)
     * Vérifie la conformité entre le PDF et son XML Factur-X embarqué.  **IMPORTANT**: Seuls les PDF Factur-X (avec XML embarqué) sont acceptés. Les PDF sans XML Factur-X seront rejetés avec une erreur 400.  Cette version synchrone utilise uniquement l&#39;extraction PDF native (pdfplumber). Pour les PDF images nécessitant de l&#39;OCR, utilisez l&#39;endpoint &#x60;/verifier-async&#x60;.  **Principe de vérification (Factur-X 1.08):** - Principe n°2: Le XML ne peut contenir que des infos présentes dans le PDF - Principe n°4: Toute info XML doit être présente et conforme dans le PDF  **Champs vérifiés:** - Identification: BT-1 (n° facture), BT-2 (date), BT-3 (type), BT-5 (devise), BT-23 (cadre) - Vendeur: BT-27 (nom), BT-29 (SIRET), BT-30 (SIREN), BT-31 (TVA) - Acheteur: BT-44 (nom), BT-46 (SIRET), BT-47 (SIREN), BT-48 (TVA) - Montants: BT-109 (HT), BT-110 (TVA), BT-112 (TTC), BT-115 (à payer) - Ventilation TVA: BT-116, BT-117, BT-118, BT-119 - Lignes de facture: BT-153, BT-129, BT-146, BT-131 - Notes obligatoires: PMT, PMD, AAB - Règle BR-FR-09: cohérence SIRET/SIREN
     * @param fichierPdf Fichier PDF Factur-X à vérifier (required)
     * @return ApiResponse&lt;ReponseVerificationSucces&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Vérification réussie </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Erreur de vérification (PDF non Factur-X, invalide, etc.) </td><td>  -  </td></tr>
        <tr><td> 413 </td><td> PDF trop volumineux ou trop de pages </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<ReponseVerificationSucces> verifierPdfSyncApiV1VerificationVerifierPostWithHttpInfo(@javax.annotation.Nonnull File fichierPdf) throws ApiException {
        okhttp3.Call localVarCall = verifierPdfSyncApiV1VerificationVerifierPostValidateBeforeCall(fichierPdf, null);
        Type localVarReturnType = new TypeToken<ReponseVerificationSucces>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Vérifier la conformité PDF/XML Factur-X (synchrone) (asynchronously)
     * Vérifie la conformité entre le PDF et son XML Factur-X embarqué.  **IMPORTANT**: Seuls les PDF Factur-X (avec XML embarqué) sont acceptés. Les PDF sans XML Factur-X seront rejetés avec une erreur 400.  Cette version synchrone utilise uniquement l&#39;extraction PDF native (pdfplumber). Pour les PDF images nécessitant de l&#39;OCR, utilisez l&#39;endpoint &#x60;/verifier-async&#x60;.  **Principe de vérification (Factur-X 1.08):** - Principe n°2: Le XML ne peut contenir que des infos présentes dans le PDF - Principe n°4: Toute info XML doit être présente et conforme dans le PDF  **Champs vérifiés:** - Identification: BT-1 (n° facture), BT-2 (date), BT-3 (type), BT-5 (devise), BT-23 (cadre) - Vendeur: BT-27 (nom), BT-29 (SIRET), BT-30 (SIREN), BT-31 (TVA) - Acheteur: BT-44 (nom), BT-46 (SIRET), BT-47 (SIREN), BT-48 (TVA) - Montants: BT-109 (HT), BT-110 (TVA), BT-112 (TTC), BT-115 (à payer) - Ventilation TVA: BT-116, BT-117, BT-118, BT-119 - Lignes de facture: BT-153, BT-129, BT-146, BT-131 - Notes obligatoires: PMT, PMD, AAB - Règle BR-FR-09: cohérence SIRET/SIREN
     * @param fichierPdf Fichier PDF Factur-X à vérifier (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Vérification réussie </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Erreur de vérification (PDF non Factur-X, invalide, etc.) </td><td>  -  </td></tr>
        <tr><td> 413 </td><td> PDF trop volumineux ou trop de pages </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call verifierPdfSyncApiV1VerificationVerifierPostAsync(@javax.annotation.Nonnull File fichierPdf, final ApiCallback<ReponseVerificationSucces> _callback) throws ApiException {

        okhttp3.Call localVarCall = verifierPdfSyncApiV1VerificationVerifierPostValidateBeforeCall(fichierPdf, _callback);
        Type localVarReturnType = new TypeToken<ReponseVerificationSucces>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for verifierPdfSyncApiV1VerificationVerifierPost_0
     * @param fichierPdf Fichier PDF Factur-X à vérifier (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Vérification réussie </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Erreur de vérification (PDF non Factur-X, invalide, etc.) </td><td>  -  </td></tr>
        <tr><td> 413 </td><td> PDF trop volumineux ou trop de pages </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call verifierPdfSyncApiV1VerificationVerifierPost_0Call(@javax.annotation.Nonnull File fichierPdf, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/verification/verifier";

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
    private okhttp3.Call verifierPdfSyncApiV1VerificationVerifierPost_0ValidateBeforeCall(@javax.annotation.Nonnull File fichierPdf, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'fichierPdf' is set
        if (fichierPdf == null) {
            throw new ApiException("Missing the required parameter 'fichierPdf' when calling verifierPdfSyncApiV1VerificationVerifierPost_0(Async)");
        }

        return verifierPdfSyncApiV1VerificationVerifierPost_0Call(fichierPdf, _callback);

    }

    /**
     * Vérifier la conformité PDF/XML Factur-X (synchrone)
     * Vérifie la conformité entre le PDF et son XML Factur-X embarqué.  **IMPORTANT**: Seuls les PDF Factur-X (avec XML embarqué) sont acceptés. Les PDF sans XML Factur-X seront rejetés avec une erreur 400.  Cette version synchrone utilise uniquement l&#39;extraction PDF native (pdfplumber). Pour les PDF images nécessitant de l&#39;OCR, utilisez l&#39;endpoint &#x60;/verifier-async&#x60;.  **Principe de vérification (Factur-X 1.08):** - Principe n°2: Le XML ne peut contenir que des infos présentes dans le PDF - Principe n°4: Toute info XML doit être présente et conforme dans le PDF  **Champs vérifiés:** - Identification: BT-1 (n° facture), BT-2 (date), BT-3 (type), BT-5 (devise), BT-23 (cadre) - Vendeur: BT-27 (nom), BT-29 (SIRET), BT-30 (SIREN), BT-31 (TVA) - Acheteur: BT-44 (nom), BT-46 (SIRET), BT-47 (SIREN), BT-48 (TVA) - Montants: BT-109 (HT), BT-110 (TVA), BT-112 (TTC), BT-115 (à payer) - Ventilation TVA: BT-116, BT-117, BT-118, BT-119 - Lignes de facture: BT-153, BT-129, BT-146, BT-131 - Notes obligatoires: PMT, PMD, AAB - Règle BR-FR-09: cohérence SIRET/SIREN
     * @param fichierPdf Fichier PDF Factur-X à vérifier (required)
     * @return ReponseVerificationSucces
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Vérification réussie </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Erreur de vérification (PDF non Factur-X, invalide, etc.) </td><td>  -  </td></tr>
        <tr><td> 413 </td><td> PDF trop volumineux ou trop de pages </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ReponseVerificationSucces verifierPdfSyncApiV1VerificationVerifierPost_0(@javax.annotation.Nonnull File fichierPdf) throws ApiException {
        ApiResponse<ReponseVerificationSucces> localVarResp = verifierPdfSyncApiV1VerificationVerifierPost_0WithHttpInfo(fichierPdf);
        return localVarResp.getData();
    }

    /**
     * Vérifier la conformité PDF/XML Factur-X (synchrone)
     * Vérifie la conformité entre le PDF et son XML Factur-X embarqué.  **IMPORTANT**: Seuls les PDF Factur-X (avec XML embarqué) sont acceptés. Les PDF sans XML Factur-X seront rejetés avec une erreur 400.  Cette version synchrone utilise uniquement l&#39;extraction PDF native (pdfplumber). Pour les PDF images nécessitant de l&#39;OCR, utilisez l&#39;endpoint &#x60;/verifier-async&#x60;.  **Principe de vérification (Factur-X 1.08):** - Principe n°2: Le XML ne peut contenir que des infos présentes dans le PDF - Principe n°4: Toute info XML doit être présente et conforme dans le PDF  **Champs vérifiés:** - Identification: BT-1 (n° facture), BT-2 (date), BT-3 (type), BT-5 (devise), BT-23 (cadre) - Vendeur: BT-27 (nom), BT-29 (SIRET), BT-30 (SIREN), BT-31 (TVA) - Acheteur: BT-44 (nom), BT-46 (SIRET), BT-47 (SIREN), BT-48 (TVA) - Montants: BT-109 (HT), BT-110 (TVA), BT-112 (TTC), BT-115 (à payer) - Ventilation TVA: BT-116, BT-117, BT-118, BT-119 - Lignes de facture: BT-153, BT-129, BT-146, BT-131 - Notes obligatoires: PMT, PMD, AAB - Règle BR-FR-09: cohérence SIRET/SIREN
     * @param fichierPdf Fichier PDF Factur-X à vérifier (required)
     * @return ApiResponse&lt;ReponseVerificationSucces&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Vérification réussie </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Erreur de vérification (PDF non Factur-X, invalide, etc.) </td><td>  -  </td></tr>
        <tr><td> 413 </td><td> PDF trop volumineux ou trop de pages </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<ReponseVerificationSucces> verifierPdfSyncApiV1VerificationVerifierPost_0WithHttpInfo(@javax.annotation.Nonnull File fichierPdf) throws ApiException {
        okhttp3.Call localVarCall = verifierPdfSyncApiV1VerificationVerifierPost_0ValidateBeforeCall(fichierPdf, null);
        Type localVarReturnType = new TypeToken<ReponseVerificationSucces>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Vérifier la conformité PDF/XML Factur-X (synchrone) (asynchronously)
     * Vérifie la conformité entre le PDF et son XML Factur-X embarqué.  **IMPORTANT**: Seuls les PDF Factur-X (avec XML embarqué) sont acceptés. Les PDF sans XML Factur-X seront rejetés avec une erreur 400.  Cette version synchrone utilise uniquement l&#39;extraction PDF native (pdfplumber). Pour les PDF images nécessitant de l&#39;OCR, utilisez l&#39;endpoint &#x60;/verifier-async&#x60;.  **Principe de vérification (Factur-X 1.08):** - Principe n°2: Le XML ne peut contenir que des infos présentes dans le PDF - Principe n°4: Toute info XML doit être présente et conforme dans le PDF  **Champs vérifiés:** - Identification: BT-1 (n° facture), BT-2 (date), BT-3 (type), BT-5 (devise), BT-23 (cadre) - Vendeur: BT-27 (nom), BT-29 (SIRET), BT-30 (SIREN), BT-31 (TVA) - Acheteur: BT-44 (nom), BT-46 (SIRET), BT-47 (SIREN), BT-48 (TVA) - Montants: BT-109 (HT), BT-110 (TVA), BT-112 (TTC), BT-115 (à payer) - Ventilation TVA: BT-116, BT-117, BT-118, BT-119 - Lignes de facture: BT-153, BT-129, BT-146, BT-131 - Notes obligatoires: PMT, PMD, AAB - Règle BR-FR-09: cohérence SIRET/SIREN
     * @param fichierPdf Fichier PDF Factur-X à vérifier (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Vérification réussie </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Erreur de vérification (PDF non Factur-X, invalide, etc.) </td><td>  -  </td></tr>
        <tr><td> 413 </td><td> PDF trop volumineux ou trop de pages </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call verifierPdfSyncApiV1VerificationVerifierPost_0Async(@javax.annotation.Nonnull File fichierPdf, final ApiCallback<ReponseVerificationSucces> _callback) throws ApiException {

        okhttp3.Call localVarCall = verifierPdfSyncApiV1VerificationVerifierPost_0ValidateBeforeCall(fichierPdf, _callback);
        Type localVarReturnType = new TypeToken<ReponseVerificationSucces>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
