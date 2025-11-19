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


import org.openapitools.client.model.HTTPValidationError;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AfnorPdpPaDirectoryServiceApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public AfnorPdpPaDirectoryServiceApi() {
        this(Configuration.getDefaultApiClient());
    }

    public AfnorPdpPaDirectoryServiceApi(ApiClient apiClient) {
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
     * Build call for createDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLinePost
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 201 </td><td> Ligne d&#39;annuaire créée avec succès </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Requête invalide </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLinePostCall(final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/afnor/directory/v1/directory-line";

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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call createDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLinePostValidateBeforeCall(final ApiCallback _callback) throws ApiException {
        return createDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLinePostCall(_callback);

    }

    /**
     * Creating a directory line
     * Créer une ligne dans l&#39;annuaire
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 201 </td><td> Ligne d&#39;annuaire créée avec succès </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Requête invalide </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
     </table>
     */
    public Object createDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLinePost() throws ApiException {
        ApiResponse<Object> localVarResp = createDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLinePostWithHttpInfo();
        return localVarResp.getData();
    }

    /**
     * Creating a directory line
     * Créer une ligne dans l&#39;annuaire
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 201 </td><td> Ligne d&#39;annuaire créée avec succès </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Requête invalide </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> createDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLinePostWithHttpInfo() throws ApiException {
        okhttp3.Call localVarCall = createDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLinePostValidateBeforeCall(null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Creating a directory line (asynchronously)
     * Créer une ligne dans l&#39;annuaire
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 201 </td><td> Ligne d&#39;annuaire créée avec succès </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Requête invalide </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLinePostAsync(final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = createDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLinePostValidateBeforeCall(_callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for createRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodePost
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 201 </td><td> Code de routage créé avec succès </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Requête invalide </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodePostCall(final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/afnor/directory/v1/routing-code";

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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call createRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodePostValidateBeforeCall(final ApiCallback _callback) throws ApiException {
        return createRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodePostCall(_callback);

    }

    /**
     * Create a routing code
     * Créer un code de routage dans l&#39;annuaire
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 201 </td><td> Code de routage créé avec succès </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Requête invalide </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
     </table>
     */
    public Object createRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodePost() throws ApiException {
        ApiResponse<Object> localVarResp = createRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodePostWithHttpInfo();
        return localVarResp.getData();
    }

    /**
     * Create a routing code
     * Créer un code de routage dans l&#39;annuaire
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 201 </td><td> Code de routage créé avec succès </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Requête invalide </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> createRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodePostWithHttpInfo() throws ApiException {
        okhttp3.Call localVarCall = createRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodePostValidateBeforeCall(null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Create a routing code (asynchronously)
     * Créer un code de routage dans l&#39;annuaire
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 201 </td><td> Code de routage créé avec succès </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Requête invalide </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call createRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodePostAsync(final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = createRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodePostValidateBeforeCall(_callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDelete
     * @param idInstance  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 204 </td><td> Ligne d&#39;annuaire supprimée </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Ligne d&#39;annuaire non trouvée </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDeleteCall(@javax.annotation.Nonnull String idInstance, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/afnor/directory/v1/directory-line/id-instance:{id_instance}"
            .replace("{" + "id_instance" + "}", localVarApiClient.escapeString(idInstance.toString()));

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
        return localVarApiClient.buildCall(basePath, localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDeleteValidateBeforeCall(@javax.annotation.Nonnull String idInstance, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'idInstance' is set
        if (idInstance == null) {
            throw new ApiException("Missing the required parameter 'idInstance' when calling deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDelete(Async)");
        }

        return deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDeleteCall(idInstance, _callback);

    }

    /**
     * Delete a directory line
     * Supprimer une ligne d&#39;annuaire
     * @param idInstance  (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 204 </td><td> Ligne d&#39;annuaire supprimée </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Ligne d&#39;annuaire non trouvée </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDelete(@javax.annotation.Nonnull String idInstance) throws ApiException {
        ApiResponse<Object> localVarResp = deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDeleteWithHttpInfo(idInstance);
        return localVarResp.getData();
    }

    /**
     * Delete a directory line
     * Supprimer une ligne d&#39;annuaire
     * @param idInstance  (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 204 </td><td> Ligne d&#39;annuaire supprimée </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Ligne d&#39;annuaire non trouvée </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDeleteWithHttpInfo(@javax.annotation.Nonnull String idInstance) throws ApiException {
        okhttp3.Call localVarCall = deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDeleteValidateBeforeCall(idInstance, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Delete a directory line (asynchronously)
     * Supprimer une ligne d&#39;annuaire
     * @param idInstance  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 204 </td><td> Ligne d&#39;annuaire supprimée </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Ligne d&#39;annuaire non trouvée </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDeleteAsync(@javax.annotation.Nonnull String idInstance, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDeleteValidateBeforeCall(idInstance, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGet
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Service opérationnel </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGetCall(final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/afnor/directory/v1/healthcheck";

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

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGetValidateBeforeCall(final ApiCallback _callback) throws ApiException {
        return directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGetCall(_callback);

    }

    /**
     * Healthcheck Directory Service
     * Vérifier la disponibilité du Directory Service
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Service opérationnel </td><td>  -  </td></tr>
     </table>
     */
    public Object directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGet() throws ApiException {
        ApiResponse<Object> localVarResp = directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGetWithHttpInfo();
        return localVarResp.getData();
    }

    /**
     * Healthcheck Directory Service
     * Vérifier la disponibilité du Directory Service
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Service opérationnel </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGetWithHttpInfo() throws ApiException {
        okhttp3.Call localVarCall = directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGetValidateBeforeCall(null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Healthcheck Directory Service (asynchronously)
     * Vérifier la disponibilité du Directory Service
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Service opérationnel </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGetAsync(final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGetValidateBeforeCall(_callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGet
     * @param addressingIdentifier  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Détails de la ligne d&#39;annuaire </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Ligne d&#39;annuaire non trouvée </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGetCall(@javax.annotation.Nonnull String addressingIdentifier, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/afnor/directory/v1/directory-line/code:{addressing_identifier}"
            .replace("{" + "addressing_identifier" + "}", localVarApiClient.escapeString(addressingIdentifier.toString()));

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
    private okhttp3.Call getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGetValidateBeforeCall(@javax.annotation.Nonnull String addressingIdentifier, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'addressingIdentifier' is set
        if (addressingIdentifier == null) {
            throw new ApiException("Missing the required parameter 'addressingIdentifier' when calling getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGet(Async)");
        }

        return getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGetCall(addressingIdentifier, _callback);

    }

    /**
     * Get a directory line
     * Obtenir une ligne d&#39;annuaire identifiée par un identifiant d&#39;adressage
     * @param addressingIdentifier  (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Détails de la ligne d&#39;annuaire </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Ligne d&#39;annuaire non trouvée </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGet(@javax.annotation.Nonnull String addressingIdentifier) throws ApiException {
        ApiResponse<Object> localVarResp = getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGetWithHttpInfo(addressingIdentifier);
        return localVarResp.getData();
    }

    /**
     * Get a directory line
     * Obtenir une ligne d&#39;annuaire identifiée par un identifiant d&#39;adressage
     * @param addressingIdentifier  (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Détails de la ligne d&#39;annuaire </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Ligne d&#39;annuaire non trouvée </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGetWithHttpInfo(@javax.annotation.Nonnull String addressingIdentifier) throws ApiException {
        okhttp3.Call localVarCall = getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGetValidateBeforeCall(addressingIdentifier, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get a directory line (asynchronously)
     * Obtenir une ligne d&#39;annuaire identifiée par un identifiant d&#39;adressage
     * @param addressingIdentifier  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Détails de la ligne d&#39;annuaire </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Ligne d&#39;annuaire non trouvée </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGetAsync(@javax.annotation.Nonnull String addressingIdentifier, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGetValidateBeforeCall(addressingIdentifier, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGet
     * @param idInstance  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Détails de la ligne d&#39;annuaire </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Ligne d&#39;annuaire non trouvée </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGetCall(@javax.annotation.Nonnull String idInstance, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/afnor/directory/v1/directory-line/id-instance:{id_instance}"
            .replace("{" + "id_instance" + "}", localVarApiClient.escapeString(idInstance.toString()));

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
    private okhttp3.Call getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGetValidateBeforeCall(@javax.annotation.Nonnull String idInstance, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'idInstance' is set
        if (idInstance == null) {
            throw new ApiException("Missing the required parameter 'idInstance' when calling getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGet(Async)");
        }

        return getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGetCall(idInstance, _callback);

    }

    /**
     * Get a directory line
     * Obtenir une ligne d&#39;annuaire identifiée par son idInstance
     * @param idInstance  (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Détails de la ligne d&#39;annuaire </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Ligne d&#39;annuaire non trouvée </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGet(@javax.annotation.Nonnull String idInstance) throws ApiException {
        ApiResponse<Object> localVarResp = getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGetWithHttpInfo(idInstance);
        return localVarResp.getData();
    }

    /**
     * Get a directory line
     * Obtenir une ligne d&#39;annuaire identifiée par son idInstance
     * @param idInstance  (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Détails de la ligne d&#39;annuaire </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Ligne d&#39;annuaire non trouvée </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGetWithHttpInfo(@javax.annotation.Nonnull String idInstance) throws ApiException {
        okhttp3.Call localVarCall = getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGetValidateBeforeCall(idInstance, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get a directory line (asynchronously)
     * Obtenir une ligne d&#39;annuaire identifiée par son idInstance
     * @param idInstance  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Détails de la ligne d&#39;annuaire </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Ligne d&#39;annuaire non trouvée </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGetAsync(@javax.annotation.Nonnull String idInstance, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGetValidateBeforeCall(idInstance, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGet
     * @param idInstance  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Détails du code de routage </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Code de routage non trouvé </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGetCall(@javax.annotation.Nonnull String idInstance, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/afnor/directory/v1/routing-code/id-instance:{id_instance}"
            .replace("{" + "id_instance" + "}", localVarApiClient.escapeString(idInstance.toString()));

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
    private okhttp3.Call getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGetValidateBeforeCall(@javax.annotation.Nonnull String idInstance, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'idInstance' is set
        if (idInstance == null) {
            throw new ApiException("Missing the required parameter 'idInstance' when calling getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGet(Async)");
        }

        return getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGetCall(idInstance, _callback);

    }

    /**
     * Get a routing code by instance-id
     * Obtenir un code de routage identifié par son idInstance
     * @param idInstance  (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Détails du code de routage </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Code de routage non trouvé </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGet(@javax.annotation.Nonnull String idInstance) throws ApiException {
        ApiResponse<Object> localVarResp = getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGetWithHttpInfo(idInstance);
        return localVarResp.getData();
    }

    /**
     * Get a routing code by instance-id
     * Obtenir un code de routage identifié par son idInstance
     * @param idInstance  (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Détails du code de routage </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Code de routage non trouvé </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGetWithHttpInfo(@javax.annotation.Nonnull String idInstance) throws ApiException {
        okhttp3.Call localVarCall = getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGetValidateBeforeCall(idInstance, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get a routing code by instance-id (asynchronously)
     * Obtenir un code de routage identifié par son idInstance
     * @param idInstance  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Détails du code de routage </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Code de routage non trouvé </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGetAsync(@javax.annotation.Nonnull String idInstance, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGetValidateBeforeCall(idInstance, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGet
     * @param siret  (required)
     * @param routingIdentifier  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Détails du code de routage </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Code de routage non trouvé </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGetCall(@javax.annotation.Nonnull String siret, @javax.annotation.Nonnull String routingIdentifier, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/afnor/directory/v1/routing-code/siret:{siret}/code:{routing_identifier}"
            .replace("{" + "siret" + "}", localVarApiClient.escapeString(siret.toString()))
            .replace("{" + "routing_identifier" + "}", localVarApiClient.escapeString(routingIdentifier.toString()));

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
    private okhttp3.Call getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGetValidateBeforeCall(@javax.annotation.Nonnull String siret, @javax.annotation.Nonnull String routingIdentifier, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'siret' is set
        if (siret == null) {
            throw new ApiException("Missing the required parameter 'siret' when calling getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGet(Async)");
        }

        // verify the required parameter 'routingIdentifier' is set
        if (routingIdentifier == null) {
            throw new ApiException("Missing the required parameter 'routingIdentifier' when calling getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGet(Async)");
        }

        return getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGetCall(siret, routingIdentifier, _callback);

    }

    /**
     * Get a routing code by SIRET and routing identifier
     * Consulter un code de routage identifié par SIRET et identifiant de routage
     * @param siret  (required)
     * @param routingIdentifier  (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Détails du code de routage </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Code de routage non trouvé </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGet(@javax.annotation.Nonnull String siret, @javax.annotation.Nonnull String routingIdentifier) throws ApiException {
        ApiResponse<Object> localVarResp = getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGetWithHttpInfo(siret, routingIdentifier);
        return localVarResp.getData();
    }

    /**
     * Get a routing code by SIRET and routing identifier
     * Consulter un code de routage identifié par SIRET et identifiant de routage
     * @param siret  (required)
     * @param routingIdentifier  (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Détails du code de routage </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Code de routage non trouvé </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGetWithHttpInfo(@javax.annotation.Nonnull String siret, @javax.annotation.Nonnull String routingIdentifier) throws ApiException {
        okhttp3.Call localVarCall = getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGetValidateBeforeCall(siret, routingIdentifier, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get a routing code by SIRET and routing identifier (asynchronously)
     * Consulter un code de routage identifié par SIRET et identifiant de routage
     * @param siret  (required)
     * @param routingIdentifier  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Détails du code de routage </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Code de routage non trouvé </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGetAsync(@javax.annotation.Nonnull String siret, @javax.annotation.Nonnull String routingIdentifier, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGetValidateBeforeCall(siret, routingIdentifier, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGet
     * @param siren  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Informations de l&#39;entreprise </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entreprise non trouvée </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGetCall(@javax.annotation.Nonnull String siren, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/afnor/directory/v1/siren/code-insee:{siren}"
            .replace("{" + "siren" + "}", localVarApiClient.escapeString(siren.toString()));

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
    private okhttp3.Call getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGetValidateBeforeCall(@javax.annotation.Nonnull String siren, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'siren' is set
        if (siren == null) {
            throw new ApiException("Missing the required parameter 'siren' when calling getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGet(Async)");
        }

        return getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGetCall(siren, _callback);

    }

    /**
     * Consult a siren (legal unit) by SIREN number
     * Retourne les détails d&#39;une entreprise (unité légale) identifiée par son numéro SIREN
     * @param siren  (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Informations de l&#39;entreprise </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entreprise non trouvée </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGet(@javax.annotation.Nonnull String siren) throws ApiException {
        ApiResponse<Object> localVarResp = getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGetWithHttpInfo(siren);
        return localVarResp.getData();
    }

    /**
     * Consult a siren (legal unit) by SIREN number
     * Retourne les détails d&#39;une entreprise (unité légale) identifiée par son numéro SIREN
     * @param siren  (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Informations de l&#39;entreprise </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entreprise non trouvée </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGetWithHttpInfo(@javax.annotation.Nonnull String siren) throws ApiException {
        okhttp3.Call localVarCall = getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGetValidateBeforeCall(siren, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Consult a siren (legal unit) by SIREN number (asynchronously)
     * Retourne les détails d&#39;une entreprise (unité légale) identifiée par son numéro SIREN
     * @param siren  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Informations de l&#39;entreprise </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entreprise non trouvée </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGetAsync(@javax.annotation.Nonnull String siren, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGetValidateBeforeCall(siren, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGet
     * @param idInstance  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Informations de l&#39;entreprise </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entreprise non trouvée </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGetCall(@javax.annotation.Nonnull String idInstance, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/afnor/directory/v1/siren/id-instance:{id_instance}"
            .replace("{" + "id_instance" + "}", localVarApiClient.escapeString(idInstance.toString()));

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
    private okhttp3.Call getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGetValidateBeforeCall(@javax.annotation.Nonnull String idInstance, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'idInstance' is set
        if (idInstance == null) {
            throw new ApiException("Missing the required parameter 'idInstance' when calling getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGet(Async)");
        }

        return getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGetCall(idInstance, _callback);

    }

    /**
     * Gets a siren (legal unit) by instance ID
     * Obtenir une entreprise (unité légale) identifiée par son idInstance
     * @param idInstance  (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Informations de l&#39;entreprise </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entreprise non trouvée </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGet(@javax.annotation.Nonnull String idInstance) throws ApiException {
        ApiResponse<Object> localVarResp = getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGetWithHttpInfo(idInstance);
        return localVarResp.getData();
    }

    /**
     * Gets a siren (legal unit) by instance ID
     * Obtenir une entreprise (unité légale) identifiée par son idInstance
     * @param idInstance  (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Informations de l&#39;entreprise </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entreprise non trouvée </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGetWithHttpInfo(@javax.annotation.Nonnull String idInstance) throws ApiException {
        okhttp3.Call localVarCall = getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGetValidateBeforeCall(idInstance, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Gets a siren (legal unit) by instance ID (asynchronously)
     * Obtenir une entreprise (unité légale) identifiée par son idInstance
     * @param idInstance  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Informations de l&#39;entreprise </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Entreprise non trouvée </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGetAsync(@javax.annotation.Nonnull String idInstance, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGetValidateBeforeCall(idInstance, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGet
     * @param siret  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Informations de l&#39;établissement </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Établissement non trouvé </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGetCall(@javax.annotation.Nonnull String siret, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/afnor/directory/v1/siret/code-insee:{siret}"
            .replace("{" + "siret" + "}", localVarApiClient.escapeString(siret.toString()));

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
    private okhttp3.Call getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGetValidateBeforeCall(@javax.annotation.Nonnull String siret, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'siret' is set
        if (siret == null) {
            throw new ApiException("Missing the required parameter 'siret' when calling getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGet(Async)");
        }

        return getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGetCall(siret, _callback);

    }

    /**
     * Gets a siret (facility) by SIRET number
     * Obtenir un établissement identifié par son numéro SIRET
     * @param siret  (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Informations de l&#39;établissement </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Établissement non trouvé </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGet(@javax.annotation.Nonnull String siret) throws ApiException {
        ApiResponse<Object> localVarResp = getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGetWithHttpInfo(siret);
        return localVarResp.getData();
    }

    /**
     * Gets a siret (facility) by SIRET number
     * Obtenir un établissement identifié par son numéro SIRET
     * @param siret  (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Informations de l&#39;établissement </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Établissement non trouvé </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGetWithHttpInfo(@javax.annotation.Nonnull String siret) throws ApiException {
        okhttp3.Call localVarCall = getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGetValidateBeforeCall(siret, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Gets a siret (facility) by SIRET number (asynchronously)
     * Obtenir un établissement identifié par son numéro SIRET
     * @param siret  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Informations de l&#39;établissement </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Établissement non trouvé </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGetAsync(@javax.annotation.Nonnull String siret, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGetValidateBeforeCall(siret, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGet
     * @param idInstance  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Informations de l&#39;établissement </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Établissement non trouvé </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGetCall(@javax.annotation.Nonnull String idInstance, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/afnor/directory/v1/siret/id-instance:{id_instance}"
            .replace("{" + "id_instance" + "}", localVarApiClient.escapeString(idInstance.toString()));

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
    private okhttp3.Call getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGetValidateBeforeCall(@javax.annotation.Nonnull String idInstance, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'idInstance' is set
        if (idInstance == null) {
            throw new ApiException("Missing the required parameter 'idInstance' when calling getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGet(Async)");
        }

        return getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGetCall(idInstance, _callback);

    }

    /**
     * Gets a siret (facility) by id-instance
     * Obtenir un établissement identifié par son idInstance
     * @param idInstance  (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Informations de l&#39;établissement </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Établissement non trouvé </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGet(@javax.annotation.Nonnull String idInstance) throws ApiException {
        ApiResponse<Object> localVarResp = getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGetWithHttpInfo(idInstance);
        return localVarResp.getData();
    }

    /**
     * Gets a siret (facility) by id-instance
     * Obtenir un établissement identifié par son idInstance
     * @param idInstance  (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Informations de l&#39;établissement </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Établissement non trouvé </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGetWithHttpInfo(@javax.annotation.Nonnull String idInstance) throws ApiException {
        okhttp3.Call localVarCall = getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGetValidateBeforeCall(idInstance, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Gets a siret (facility) by id-instance (asynchronously)
     * Obtenir un établissement identifié par son idInstance
     * @param idInstance  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Informations de l&#39;établissement </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Établissement non trouvé </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGetAsync(@javax.annotation.Nonnull String idInstance, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGetValidateBeforeCall(idInstance, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatch
     * @param idInstance  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Ligne d&#39;annuaire mise à jour </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Ligne d&#39;annuaire non trouvée </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatchCall(@javax.annotation.Nonnull String idInstance, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/afnor/directory/v1/directory-line/id-instance:{id_instance}"
            .replace("{" + "id_instance" + "}", localVarApiClient.escapeString(idInstance.toString()));

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
        return localVarApiClient.buildCall(basePath, localVarPath, "PATCH", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatchValidateBeforeCall(@javax.annotation.Nonnull String idInstance, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'idInstance' is set
        if (idInstance == null) {
            throw new ApiException("Missing the required parameter 'idInstance' when calling patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatch(Async)");
        }

        return patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatchCall(idInstance, _callback);

    }

    /**
     * Partially updates a directory line
     * Mettre à jour partiellement une ligne d&#39;annuaire
     * @param idInstance  (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Ligne d&#39;annuaire mise à jour </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Ligne d&#39;annuaire non trouvée </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatch(@javax.annotation.Nonnull String idInstance) throws ApiException {
        ApiResponse<Object> localVarResp = patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatchWithHttpInfo(idInstance);
        return localVarResp.getData();
    }

    /**
     * Partially updates a directory line
     * Mettre à jour partiellement une ligne d&#39;annuaire
     * @param idInstance  (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Ligne d&#39;annuaire mise à jour </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Ligne d&#39;annuaire non trouvée </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatchWithHttpInfo(@javax.annotation.Nonnull String idInstance) throws ApiException {
        okhttp3.Call localVarCall = patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatchValidateBeforeCall(idInstance, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Partially updates a directory line (asynchronously)
     * Mettre à jour partiellement une ligne d&#39;annuaire
     * @param idInstance  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Ligne d&#39;annuaire mise à jour </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Ligne d&#39;annuaire non trouvée </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatchAsync(@javax.annotation.Nonnull String idInstance, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatchValidateBeforeCall(idInstance, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatch
     * @param idInstance  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Code de routage mis à jour </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Code de routage non trouvé </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatchCall(@javax.annotation.Nonnull String idInstance, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/afnor/directory/v1/routing-code/id-instance:{id_instance}"
            .replace("{" + "id_instance" + "}", localVarApiClient.escapeString(idInstance.toString()));

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
        return localVarApiClient.buildCall(basePath, localVarPath, "PATCH", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatchValidateBeforeCall(@javax.annotation.Nonnull String idInstance, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'idInstance' is set
        if (idInstance == null) {
            throw new ApiException("Missing the required parameter 'idInstance' when calling patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatch(Async)");
        }

        return patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatchCall(idInstance, _callback);

    }

    /**
     * Partially update a private routing code
     * Mettre à jour partiellement un code de routage privé
     * @param idInstance  (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Code de routage mis à jour </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Code de routage non trouvé </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatch(@javax.annotation.Nonnull String idInstance) throws ApiException {
        ApiResponse<Object> localVarResp = patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatchWithHttpInfo(idInstance);
        return localVarResp.getData();
    }

    /**
     * Partially update a private routing code
     * Mettre à jour partiellement un code de routage privé
     * @param idInstance  (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Code de routage mis à jour </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Code de routage non trouvé </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatchWithHttpInfo(@javax.annotation.Nonnull String idInstance) throws ApiException {
        okhttp3.Call localVarCall = patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatchValidateBeforeCall(idInstance, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Partially update a private routing code (asynchronously)
     * Mettre à jour partiellement un code de routage privé
     * @param idInstance  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Code de routage mis à jour </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Code de routage non trouvé </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatchAsync(@javax.annotation.Nonnull String idInstance, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatchValidateBeforeCall(idInstance, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePut
     * @param idInstance  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Code de routage mis à jour </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Code de routage non trouvé </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePutCall(@javax.annotation.Nonnull String idInstance, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/afnor/directory/v1/routing-code/id-instance:{id_instance}"
            .replace("{" + "id_instance" + "}", localVarApiClient.escapeString(idInstance.toString()));

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
        return localVarApiClient.buildCall(basePath, localVarPath, "PUT", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePutValidateBeforeCall(@javax.annotation.Nonnull String idInstance, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'idInstance' is set
        if (idInstance == null) {
            throw new ApiException("Missing the required parameter 'idInstance' when calling putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePut(Async)");
        }

        return putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePutCall(idInstance, _callback);

    }

    /**
     * Completely update a private routing code
     * Mettre à jour complètement un code de routage privé
     * @param idInstance  (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Code de routage mis à jour </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Code de routage non trouvé </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePut(@javax.annotation.Nonnull String idInstance) throws ApiException {
        ApiResponse<Object> localVarResp = putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePutWithHttpInfo(idInstance);
        return localVarResp.getData();
    }

    /**
     * Completely update a private routing code
     * Mettre à jour complètement un code de routage privé
     * @param idInstance  (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Code de routage mis à jour </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Code de routage non trouvé </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePutWithHttpInfo(@javax.annotation.Nonnull String idInstance) throws ApiException {
        okhttp3.Call localVarCall = putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePutValidateBeforeCall(idInstance, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Completely update a private routing code (asynchronously)
     * Mettre à jour complètement un code de routage privé
     * @param idInstance  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Code de routage mis à jour </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Code de routage non trouvé </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePutAsync(@javax.annotation.Nonnull String idInstance, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePutValidateBeforeCall(idInstance, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPost
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Résultats de recherche </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPostCall(final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/afnor/directory/v1/directory-line/search";

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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPostValidateBeforeCall(final ApiCallback _callback) throws ApiException {
        return searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPostCall(_callback);

    }

    /**
     * Search for a directory line
     * Rechercher des lignes d&#39;annuaire selon des critères
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Résultats de recherche </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
     </table>
     */
    public Object searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPost() throws ApiException {
        ApiResponse<Object> localVarResp = searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPostWithHttpInfo();
        return localVarResp.getData();
    }

    /**
     * Search for a directory line
     * Rechercher des lignes d&#39;annuaire selon des critères
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Résultats de recherche </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPostWithHttpInfo() throws ApiException {
        okhttp3.Call localVarCall = searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPostValidateBeforeCall(null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Search for a directory line (asynchronously)
     * Rechercher des lignes d&#39;annuaire selon des critères
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Résultats de recherche </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPostAsync(final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPostValidateBeforeCall(_callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPost
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Résultats de recherche </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPostCall(final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/afnor/directory/v1/routing-code/search";

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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPostValidateBeforeCall(final ApiCallback _callback) throws ApiException {
        return searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPostCall(_callback);

    }

    /**
     * Search for a routing code
     * Rechercher des codes de routage selon des critères
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Résultats de recherche </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
     </table>
     */
    public Object searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPost() throws ApiException {
        ApiResponse<Object> localVarResp = searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPostWithHttpInfo();
        return localVarResp.getData();
    }

    /**
     * Search for a routing code
     * Rechercher des codes de routage selon des critères
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Résultats de recherche </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPostWithHttpInfo() throws ApiException {
        okhttp3.Call localVarCall = searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPostValidateBeforeCall(null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Search for a routing code (asynchronously)
     * Rechercher des codes de routage selon des critères
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Résultats de recherche </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPostAsync(final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPostValidateBeforeCall(_callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPost
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Retourne une ou plusieurs entreprises </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPostCall(final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/afnor/directory/v1/siren/search";

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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPostValidateBeforeCall(final ApiCallback _callback) throws ApiException {
        return searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPostCall(_callback);

    }

    /**
     * SIREN search (or legal unit)
     * Recherche multi-critères d&#39;entreprises (unités légales)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Retourne une ou plusieurs entreprises </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
     </table>
     */
    public Object searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPost() throws ApiException {
        ApiResponse<Object> localVarResp = searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPostWithHttpInfo();
        return localVarResp.getData();
    }

    /**
     * SIREN search (or legal unit)
     * Recherche multi-critères d&#39;entreprises (unités légales)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Retourne une ou plusieurs entreprises </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPostWithHttpInfo() throws ApiException {
        okhttp3.Call localVarCall = searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPostValidateBeforeCall(null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * SIREN search (or legal unit) (asynchronously)
     * Recherche multi-critères d&#39;entreprises (unités légales)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Retourne une ou plusieurs entreprises </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPostAsync(final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPostValidateBeforeCall(_callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPost
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Retourne un ou plusieurs établissements </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPostCall(final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/afnor/directory/v1/siret/search";

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
        return localVarApiClient.buildCall(basePath, localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPostValidateBeforeCall(final ApiCallback _callback) throws ApiException {
        return searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPostCall(_callback);

    }

    /**
     * Search for a SIRET (facility)
     * Recherche multi-critères d&#39;établissements
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Retourne un ou plusieurs établissements </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
     </table>
     */
    public Object searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPost() throws ApiException {
        ApiResponse<Object> localVarResp = searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPostWithHttpInfo();
        return localVarResp.getData();
    }

    /**
     * Search for a SIRET (facility)
     * Recherche multi-critères d&#39;établissements
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Retourne un ou plusieurs établissements </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPostWithHttpInfo() throws ApiException {
        okhttp3.Call localVarCall = searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPostValidateBeforeCall(null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Search for a SIRET (facility) (asynchronously)
     * Recherche multi-critères d&#39;établissements
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Retourne un ou plusieurs établissements </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Non authentifié </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPostAsync(final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPostValidateBeforeCall(_callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
