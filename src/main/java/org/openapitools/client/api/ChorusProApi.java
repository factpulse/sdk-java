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


import org.openapitools.client.model.ConsulterFactureRequest;
import org.openapitools.client.model.ConsulterFactureResponse;
import org.openapitools.client.model.ConsulterStructureRequest;
import org.openapitools.client.model.ConsulterStructureResponse;
import org.openapitools.client.model.HTTPValidationError;
import org.openapitools.client.model.ObtenirIdChorusProRequest;
import org.openapitools.client.model.ObtenirIdChorusProResponse;
import org.openapitools.client.model.RechercherServicesResponse;
import org.openapitools.client.model.RechercherStructureRequest;
import org.openapitools.client.model.RechercherStructureResponse;
import org.openapitools.client.model.SoumettreFactureRequest;
import org.openapitools.client.model.SoumettreFactureResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChorusProApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public ChorusProApi() {
        this(Configuration.getDefaultApiClient());
    }

    public ChorusProApi(ApiClient apiClient) {
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
     * Build call for ajouterFichierApiV1ChorusProTransversesAjouterFichierPost
     * @param requestBody  (required)
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
    public okhttp3.Call ajouterFichierApiV1ChorusProTransversesAjouterFichierPostCall(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = requestBody;

        // create path and map variables
        String localVarPath = "/api/v1/chorus-pro/transverses/ajouter-fichier";

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
    private okhttp3.Call ajouterFichierApiV1ChorusProTransversesAjouterFichierPostValidateBeforeCall(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'requestBody' is set
        if (requestBody == null) {
            throw new ApiException("Missing the required parameter 'requestBody' when calling ajouterFichierApiV1ChorusProTransversesAjouterFichierPost(Async)");
        }

        return ajouterFichierApiV1ChorusProTransversesAjouterFichierPostCall(requestBody, _callback);

    }

    /**
     * Ajouter une pièce jointe
     * Ajoute une pièce jointe au compte utilisateur courant.      **Taille max** : 10 Mo par fichier      **Payload exemple** :     &#x60;&#x60;&#x60;json     {       \&quot;pieceJointeFichier\&quot;: \&quot;JVBERi0xLjQKJeLjz9MKNSAwIG9iago8P...\&quot;,       \&quot;pieceJointeNom\&quot;: \&quot;bon_commande.pdf\&quot;,       \&quot;pieceJointeTypeMime\&quot;: \&quot;application/pdf\&quot;,       \&quot;pieceJointeExtension\&quot;: \&quot;PDF\&quot;     }     &#x60;&#x60;&#x60;      **Retour** : L&#39;ID de la pièce jointe (&#x60;pieceJointeIdFichier&#x60;) à utiliser ensuite dans &#x60;/factures/completer&#x60;.      **Extensions acceptées** : PDF, JPG, PNG, ZIP, XML, etc.
     * @param requestBody  (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object ajouterFichierApiV1ChorusProTransversesAjouterFichierPost(@javax.annotation.Nonnull Map<String, Object> requestBody) throws ApiException {
        ApiResponse<Object> localVarResp = ajouterFichierApiV1ChorusProTransversesAjouterFichierPostWithHttpInfo(requestBody);
        return localVarResp.getData();
    }

    /**
     * Ajouter une pièce jointe
     * Ajoute une pièce jointe au compte utilisateur courant.      **Taille max** : 10 Mo par fichier      **Payload exemple** :     &#x60;&#x60;&#x60;json     {       \&quot;pieceJointeFichier\&quot;: \&quot;JVBERi0xLjQKJeLjz9MKNSAwIG9iago8P...\&quot;,       \&quot;pieceJointeNom\&quot;: \&quot;bon_commande.pdf\&quot;,       \&quot;pieceJointeTypeMime\&quot;: \&quot;application/pdf\&quot;,       \&quot;pieceJointeExtension\&quot;: \&quot;PDF\&quot;     }     &#x60;&#x60;&#x60;      **Retour** : L&#39;ID de la pièce jointe (&#x60;pieceJointeIdFichier&#x60;) à utiliser ensuite dans &#x60;/factures/completer&#x60;.      **Extensions acceptées** : PDF, JPG, PNG, ZIP, XML, etc.
     * @param requestBody  (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> ajouterFichierApiV1ChorusProTransversesAjouterFichierPostWithHttpInfo(@javax.annotation.Nonnull Map<String, Object> requestBody) throws ApiException {
        okhttp3.Call localVarCall = ajouterFichierApiV1ChorusProTransversesAjouterFichierPostValidateBeforeCall(requestBody, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Ajouter une pièce jointe (asynchronously)
     * Ajoute une pièce jointe au compte utilisateur courant.      **Taille max** : 10 Mo par fichier      **Payload exemple** :     &#x60;&#x60;&#x60;json     {       \&quot;pieceJointeFichier\&quot;: \&quot;JVBERi0xLjQKJeLjz9MKNSAwIG9iago8P...\&quot;,       \&quot;pieceJointeNom\&quot;: \&quot;bon_commande.pdf\&quot;,       \&quot;pieceJointeTypeMime\&quot;: \&quot;application/pdf\&quot;,       \&quot;pieceJointeExtension\&quot;: \&quot;PDF\&quot;     }     &#x60;&#x60;&#x60;      **Retour** : L&#39;ID de la pièce jointe (&#x60;pieceJointeIdFichier&#x60;) à utiliser ensuite dans &#x60;/factures/completer&#x60;.      **Extensions acceptées** : PDF, JPG, PNG, ZIP, XML, etc.
     * @param requestBody  (required)
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
    public okhttp3.Call ajouterFichierApiV1ChorusProTransversesAjouterFichierPostAsync(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = ajouterFichierApiV1ChorusProTransversesAjouterFichierPostValidateBeforeCall(requestBody, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for completerFactureApiV1ChorusProFacturesCompleterPost
     * @param requestBody  (required)
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
    public okhttp3.Call completerFactureApiV1ChorusProFacturesCompleterPostCall(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = requestBody;

        // create path and map variables
        String localVarPath = "/api/v1/chorus-pro/factures/completer";

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
    private okhttp3.Call completerFactureApiV1ChorusProFacturesCompleterPostValidateBeforeCall(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'requestBody' is set
        if (requestBody == null) {
            throw new ApiException("Missing the required parameter 'requestBody' when calling completerFactureApiV1ChorusProFacturesCompleterPost(Async)");
        }

        return completerFactureApiV1ChorusProFacturesCompleterPostCall(requestBody, _callback);

    }

    /**
     * Compléter une facture suspendue (Fournisseur)
     * Complète une facture au statut SUSPENDUE en ajoutant des pièces jointes ou un commentaire.      **Statut requis** : SUSPENDUE      **Actions possibles** :     - Ajouter des pièces jointes (justificatifs, bons de commande, etc.)     - Modifier le commentaire      **Payload exemple** :     &#x60;&#x60;&#x60;json     {       \&quot;identifiantFactureCPP\&quot;: 12345,       \&quot;commentaire\&quot;: \&quot;Voici les justificatifs demandés\&quot;,       \&quot;listePiecesJointes\&quot;: [         {           \&quot;pieceJointeIdFichier\&quot;: 98765,           \&quot;pieceJointeNom\&quot;: \&quot;bon_commande.pdf\&quot;         }       ]     }     &#x60;&#x60;&#x60;      **Note** : Les pièces jointes doivent d&#39;abord être uploadées via &#x60;/transverses/ajouter-fichier&#x60;.      **Après complétion** : La facture repasse au statut MISE_A_DISPOSITION.
     * @param requestBody  (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object completerFactureApiV1ChorusProFacturesCompleterPost(@javax.annotation.Nonnull Map<String, Object> requestBody) throws ApiException {
        ApiResponse<Object> localVarResp = completerFactureApiV1ChorusProFacturesCompleterPostWithHttpInfo(requestBody);
        return localVarResp.getData();
    }

    /**
     * Compléter une facture suspendue (Fournisseur)
     * Complète une facture au statut SUSPENDUE en ajoutant des pièces jointes ou un commentaire.      **Statut requis** : SUSPENDUE      **Actions possibles** :     - Ajouter des pièces jointes (justificatifs, bons de commande, etc.)     - Modifier le commentaire      **Payload exemple** :     &#x60;&#x60;&#x60;json     {       \&quot;identifiantFactureCPP\&quot;: 12345,       \&quot;commentaire\&quot;: \&quot;Voici les justificatifs demandés\&quot;,       \&quot;listePiecesJointes\&quot;: [         {           \&quot;pieceJointeIdFichier\&quot;: 98765,           \&quot;pieceJointeNom\&quot;: \&quot;bon_commande.pdf\&quot;         }       ]     }     &#x60;&#x60;&#x60;      **Note** : Les pièces jointes doivent d&#39;abord être uploadées via &#x60;/transverses/ajouter-fichier&#x60;.      **Après complétion** : La facture repasse au statut MISE_A_DISPOSITION.
     * @param requestBody  (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> completerFactureApiV1ChorusProFacturesCompleterPostWithHttpInfo(@javax.annotation.Nonnull Map<String, Object> requestBody) throws ApiException {
        okhttp3.Call localVarCall = completerFactureApiV1ChorusProFacturesCompleterPostValidateBeforeCall(requestBody, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Compléter une facture suspendue (Fournisseur) (asynchronously)
     * Complète une facture au statut SUSPENDUE en ajoutant des pièces jointes ou un commentaire.      **Statut requis** : SUSPENDUE      **Actions possibles** :     - Ajouter des pièces jointes (justificatifs, bons de commande, etc.)     - Modifier le commentaire      **Payload exemple** :     &#x60;&#x60;&#x60;json     {       \&quot;identifiantFactureCPP\&quot;: 12345,       \&quot;commentaire\&quot;: \&quot;Voici les justificatifs demandés\&quot;,       \&quot;listePiecesJointes\&quot;: [         {           \&quot;pieceJointeIdFichier\&quot;: 98765,           \&quot;pieceJointeNom\&quot;: \&quot;bon_commande.pdf\&quot;         }       ]     }     &#x60;&#x60;&#x60;      **Note** : Les pièces jointes doivent d&#39;abord être uploadées via &#x60;/transverses/ajouter-fichier&#x60;.      **Après complétion** : La facture repasse au statut MISE_A_DISPOSITION.
     * @param requestBody  (required)
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
    public okhttp3.Call completerFactureApiV1ChorusProFacturesCompleterPostAsync(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = completerFactureApiV1ChorusProFacturesCompleterPostValidateBeforeCall(requestBody, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for consulterFactureApiV1ChorusProFacturesConsulterPost
     * @param consulterFactureRequest  (required)
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
    public okhttp3.Call consulterFactureApiV1ChorusProFacturesConsulterPostCall(@javax.annotation.Nonnull ConsulterFactureRequest consulterFactureRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = consulterFactureRequest;

        // create path and map variables
        String localVarPath = "/api/v1/chorus-pro/factures/consulter";

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
    private okhttp3.Call consulterFactureApiV1ChorusProFacturesConsulterPostValidateBeforeCall(@javax.annotation.Nonnull ConsulterFactureRequest consulterFactureRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'consulterFactureRequest' is set
        if (consulterFactureRequest == null) {
            throw new ApiException("Missing the required parameter 'consulterFactureRequest' when calling consulterFactureApiV1ChorusProFacturesConsulterPost(Async)");
        }

        return consulterFactureApiV1ChorusProFacturesConsulterPostCall(consulterFactureRequest, _callback);

    }

    /**
     * Consulter le statut d&#39;une facture
     * Récupère les informations et le statut actuel d&#39;une facture soumise à Chorus Pro.      **Retour** :     - Numéro et date de facture     - Montant TTC     - **Statut courant** : SOUMISE, VALIDEE, REJETEE, SUSPENDUE, MANDATEE, MISE_EN_PAIEMENT, etc.     - Structure destinataire      **Cas d&#39;usage** :     - Suivre l&#39;évolution du traitement d&#39;une facture     - Vérifier si une facture a été validée ou rejetée     - Obtenir la date de mise en paiement      **Polling** : Appelez cet endpoint régulièrement pour suivre l&#39;évolution du statut.
     * @param consulterFactureRequest  (required)
     * @return ConsulterFactureResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ConsulterFactureResponse consulterFactureApiV1ChorusProFacturesConsulterPost(@javax.annotation.Nonnull ConsulterFactureRequest consulterFactureRequest) throws ApiException {
        ApiResponse<ConsulterFactureResponse> localVarResp = consulterFactureApiV1ChorusProFacturesConsulterPostWithHttpInfo(consulterFactureRequest);
        return localVarResp.getData();
    }

    /**
     * Consulter le statut d&#39;une facture
     * Récupère les informations et le statut actuel d&#39;une facture soumise à Chorus Pro.      **Retour** :     - Numéro et date de facture     - Montant TTC     - **Statut courant** : SOUMISE, VALIDEE, REJETEE, SUSPENDUE, MANDATEE, MISE_EN_PAIEMENT, etc.     - Structure destinataire      **Cas d&#39;usage** :     - Suivre l&#39;évolution du traitement d&#39;une facture     - Vérifier si une facture a été validée ou rejetée     - Obtenir la date de mise en paiement      **Polling** : Appelez cet endpoint régulièrement pour suivre l&#39;évolution du statut.
     * @param consulterFactureRequest  (required)
     * @return ApiResponse&lt;ConsulterFactureResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<ConsulterFactureResponse> consulterFactureApiV1ChorusProFacturesConsulterPostWithHttpInfo(@javax.annotation.Nonnull ConsulterFactureRequest consulterFactureRequest) throws ApiException {
        okhttp3.Call localVarCall = consulterFactureApiV1ChorusProFacturesConsulterPostValidateBeforeCall(consulterFactureRequest, null);
        Type localVarReturnType = new TypeToken<ConsulterFactureResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Consulter le statut d&#39;une facture (asynchronously)
     * Récupère les informations et le statut actuel d&#39;une facture soumise à Chorus Pro.      **Retour** :     - Numéro et date de facture     - Montant TTC     - **Statut courant** : SOUMISE, VALIDEE, REJETEE, SUSPENDUE, MANDATEE, MISE_EN_PAIEMENT, etc.     - Structure destinataire      **Cas d&#39;usage** :     - Suivre l&#39;évolution du traitement d&#39;une facture     - Vérifier si une facture a été validée ou rejetée     - Obtenir la date de mise en paiement      **Polling** : Appelez cet endpoint régulièrement pour suivre l&#39;évolution du statut.
     * @param consulterFactureRequest  (required)
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
    public okhttp3.Call consulterFactureApiV1ChorusProFacturesConsulterPostAsync(@javax.annotation.Nonnull ConsulterFactureRequest consulterFactureRequest, final ApiCallback<ConsulterFactureResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = consulterFactureApiV1ChorusProFacturesConsulterPostValidateBeforeCall(consulterFactureRequest, _callback);
        Type localVarReturnType = new TypeToken<ConsulterFactureResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for consulterStructureApiV1ChorusProStructuresConsulterPost
     * @param consulterStructureRequest  (required)
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
    public okhttp3.Call consulterStructureApiV1ChorusProStructuresConsulterPostCall(@javax.annotation.Nonnull ConsulterStructureRequest consulterStructureRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = consulterStructureRequest;

        // create path and map variables
        String localVarPath = "/api/v1/chorus-pro/structures/consulter";

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
    private okhttp3.Call consulterStructureApiV1ChorusProStructuresConsulterPostValidateBeforeCall(@javax.annotation.Nonnull ConsulterStructureRequest consulterStructureRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'consulterStructureRequest' is set
        if (consulterStructureRequest == null) {
            throw new ApiException("Missing the required parameter 'consulterStructureRequest' when calling consulterStructureApiV1ChorusProStructuresConsulterPost(Async)");
        }

        return consulterStructureApiV1ChorusProStructuresConsulterPostCall(consulterStructureRequest, _callback);

    }

    /**
     * Consulter les détails d&#39;une structure
     * Récupère les informations détaillées d&#39;une structure Chorus Pro.       **Retour** :     - Raison sociale     - Numéro de TVA intracommunautaire     - Email de contact     - **Paramètres obligatoires** : Indique si le code service et/ou numéro d&#39;engagement sont requis pour soumettre une facture      **Étape typique** : Appelée après &#x60;rechercher-structures&#x60; pour savoir quels champs sont obligatoires avant de soumettre une facture.
     * @param consulterStructureRequest  (required)
     * @return ConsulterStructureResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ConsulterStructureResponse consulterStructureApiV1ChorusProStructuresConsulterPost(@javax.annotation.Nonnull ConsulterStructureRequest consulterStructureRequest) throws ApiException {
        ApiResponse<ConsulterStructureResponse> localVarResp = consulterStructureApiV1ChorusProStructuresConsulterPostWithHttpInfo(consulterStructureRequest);
        return localVarResp.getData();
    }

    /**
     * Consulter les détails d&#39;une structure
     * Récupère les informations détaillées d&#39;une structure Chorus Pro.       **Retour** :     - Raison sociale     - Numéro de TVA intracommunautaire     - Email de contact     - **Paramètres obligatoires** : Indique si le code service et/ou numéro d&#39;engagement sont requis pour soumettre une facture      **Étape typique** : Appelée après &#x60;rechercher-structures&#x60; pour savoir quels champs sont obligatoires avant de soumettre une facture.
     * @param consulterStructureRequest  (required)
     * @return ApiResponse&lt;ConsulterStructureResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<ConsulterStructureResponse> consulterStructureApiV1ChorusProStructuresConsulterPostWithHttpInfo(@javax.annotation.Nonnull ConsulterStructureRequest consulterStructureRequest) throws ApiException {
        okhttp3.Call localVarCall = consulterStructureApiV1ChorusProStructuresConsulterPostValidateBeforeCall(consulterStructureRequest, null);
        Type localVarReturnType = new TypeToken<ConsulterStructureResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Consulter les détails d&#39;une structure (asynchronously)
     * Récupère les informations détaillées d&#39;une structure Chorus Pro.       **Retour** :     - Raison sociale     - Numéro de TVA intracommunautaire     - Email de contact     - **Paramètres obligatoires** : Indique si le code service et/ou numéro d&#39;engagement sont requis pour soumettre une facture      **Étape typique** : Appelée après &#x60;rechercher-structures&#x60; pour savoir quels champs sont obligatoires avant de soumettre une facture.
     * @param consulterStructureRequest  (required)
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
    public okhttp3.Call consulterStructureApiV1ChorusProStructuresConsulterPostAsync(@javax.annotation.Nonnull ConsulterStructureRequest consulterStructureRequest, final ApiCallback<ConsulterStructureResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = consulterStructureApiV1ChorusProStructuresConsulterPostValidateBeforeCall(consulterStructureRequest, _callback);
        Type localVarReturnType = new TypeToken<ConsulterStructureResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for listerServicesStructureApiV1ChorusProStructuresIdStructureCppServicesGet
     * @param idStructureCpp  (required)
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
    public okhttp3.Call listerServicesStructureApiV1ChorusProStructuresIdStructureCppServicesGetCall(@javax.annotation.Nonnull Integer idStructureCpp, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/v1/chorus-pro/structures/{id_structure_cpp}/services"
            .replace("{" + "id_structure_cpp" + "}", localVarApiClient.escapeString(idStructureCpp.toString()));

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
    private okhttp3.Call listerServicesStructureApiV1ChorusProStructuresIdStructureCppServicesGetValidateBeforeCall(@javax.annotation.Nonnull Integer idStructureCpp, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'idStructureCpp' is set
        if (idStructureCpp == null) {
            throw new ApiException("Missing the required parameter 'idStructureCpp' when calling listerServicesStructureApiV1ChorusProStructuresIdStructureCppServicesGet(Async)");
        }

        return listerServicesStructureApiV1ChorusProStructuresIdStructureCppServicesGetCall(idStructureCpp, _callback);

    }

    /**
     * Lister les services d&#39;une structure
     * Récupère la liste des services actifs d&#39;une structure publique.      **Cas d&#39;usage** :     - Lister les services disponibles pour une administration     - Vérifier qu&#39;un code service existe avant de soumettre une facture      **Retour** :     - Liste des services avec leur code, libellé et statut (actif/inactif)
     * @param idStructureCpp  (required)
     * @return RechercherServicesResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public RechercherServicesResponse listerServicesStructureApiV1ChorusProStructuresIdStructureCppServicesGet(@javax.annotation.Nonnull Integer idStructureCpp) throws ApiException {
        ApiResponse<RechercherServicesResponse> localVarResp = listerServicesStructureApiV1ChorusProStructuresIdStructureCppServicesGetWithHttpInfo(idStructureCpp);
        return localVarResp.getData();
    }

    /**
     * Lister les services d&#39;une structure
     * Récupère la liste des services actifs d&#39;une structure publique.      **Cas d&#39;usage** :     - Lister les services disponibles pour une administration     - Vérifier qu&#39;un code service existe avant de soumettre une facture      **Retour** :     - Liste des services avec leur code, libellé et statut (actif/inactif)
     * @param idStructureCpp  (required)
     * @return ApiResponse&lt;RechercherServicesResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<RechercherServicesResponse> listerServicesStructureApiV1ChorusProStructuresIdStructureCppServicesGetWithHttpInfo(@javax.annotation.Nonnull Integer idStructureCpp) throws ApiException {
        okhttp3.Call localVarCall = listerServicesStructureApiV1ChorusProStructuresIdStructureCppServicesGetValidateBeforeCall(idStructureCpp, null);
        Type localVarReturnType = new TypeToken<RechercherServicesResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Lister les services d&#39;une structure (asynchronously)
     * Récupère la liste des services actifs d&#39;une structure publique.      **Cas d&#39;usage** :     - Lister les services disponibles pour une administration     - Vérifier qu&#39;un code service existe avant de soumettre une facture      **Retour** :     - Liste des services avec leur code, libellé et statut (actif/inactif)
     * @param idStructureCpp  (required)
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
    public okhttp3.Call listerServicesStructureApiV1ChorusProStructuresIdStructureCppServicesGetAsync(@javax.annotation.Nonnull Integer idStructureCpp, final ApiCallback<RechercherServicesResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = listerServicesStructureApiV1ChorusProStructuresIdStructureCppServicesGetValidateBeforeCall(idStructureCpp, _callback);
        Type localVarReturnType = new TypeToken<RechercherServicesResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for obtenirIdChorusProDepuisSiretApiV1ChorusProStructuresObtenirIdDepuisSiretPost
     * @param obtenirIdChorusProRequest  (required)
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
    public okhttp3.Call obtenirIdChorusProDepuisSiretApiV1ChorusProStructuresObtenirIdDepuisSiretPostCall(@javax.annotation.Nonnull ObtenirIdChorusProRequest obtenirIdChorusProRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = obtenirIdChorusProRequest;

        // create path and map variables
        String localVarPath = "/api/v1/chorus-pro/structures/obtenir-id-depuis-siret";

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
    private okhttp3.Call obtenirIdChorusProDepuisSiretApiV1ChorusProStructuresObtenirIdDepuisSiretPostValidateBeforeCall(@javax.annotation.Nonnull ObtenirIdChorusProRequest obtenirIdChorusProRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'obtenirIdChorusProRequest' is set
        if (obtenirIdChorusProRequest == null) {
            throw new ApiException("Missing the required parameter 'obtenirIdChorusProRequest' when calling obtenirIdChorusProDepuisSiretApiV1ChorusProStructuresObtenirIdDepuisSiretPost(Async)");
        }

        return obtenirIdChorusProDepuisSiretApiV1ChorusProStructuresObtenirIdDepuisSiretPostCall(obtenirIdChorusProRequest, _callback);

    }

    /**
     * Utilitaire : Obtenir l&#39;ID Chorus Pro depuis un SIRET
     * **Utilitaire pratique** pour obtenir l&#39;ID Chorus Pro d&#39;une structure à partir de son SIRET.       Cette fonction wrapper combine :     1. Recherche de la structure par SIRET     2. Extraction de l&#39;&#x60;id_structure_cpp&#x60; si une seule structure est trouvée      **Retour** :     - &#x60;id_structure_cpp&#x60; : ID Chorus Pro (0 si non trouvé ou si plusieurs résultats)     - &#x60;designation_structure&#x60; : Nom de la structure (si trouvée)     - &#x60;message&#x60; : Message explicatif      **Cas d&#39;usage** :     - Raccourci pour obtenir directement l&#39;ID Chorus Pro avant de soumettre une facture     - Alternative simplifiée à &#x60;rechercher-structures&#x60; + extraction manuelle de l&#39;ID      **Note** : Si plusieurs structures correspondent au SIRET (rare), retourne 0 et un message d&#39;erreur.
     * @param obtenirIdChorusProRequest  (required)
     * @return ObtenirIdChorusProResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ObtenirIdChorusProResponse obtenirIdChorusProDepuisSiretApiV1ChorusProStructuresObtenirIdDepuisSiretPost(@javax.annotation.Nonnull ObtenirIdChorusProRequest obtenirIdChorusProRequest) throws ApiException {
        ApiResponse<ObtenirIdChorusProResponse> localVarResp = obtenirIdChorusProDepuisSiretApiV1ChorusProStructuresObtenirIdDepuisSiretPostWithHttpInfo(obtenirIdChorusProRequest);
        return localVarResp.getData();
    }

    /**
     * Utilitaire : Obtenir l&#39;ID Chorus Pro depuis un SIRET
     * **Utilitaire pratique** pour obtenir l&#39;ID Chorus Pro d&#39;une structure à partir de son SIRET.       Cette fonction wrapper combine :     1. Recherche de la structure par SIRET     2. Extraction de l&#39;&#x60;id_structure_cpp&#x60; si une seule structure est trouvée      **Retour** :     - &#x60;id_structure_cpp&#x60; : ID Chorus Pro (0 si non trouvé ou si plusieurs résultats)     - &#x60;designation_structure&#x60; : Nom de la structure (si trouvée)     - &#x60;message&#x60; : Message explicatif      **Cas d&#39;usage** :     - Raccourci pour obtenir directement l&#39;ID Chorus Pro avant de soumettre une facture     - Alternative simplifiée à &#x60;rechercher-structures&#x60; + extraction manuelle de l&#39;ID      **Note** : Si plusieurs structures correspondent au SIRET (rare), retourne 0 et un message d&#39;erreur.
     * @param obtenirIdChorusProRequest  (required)
     * @return ApiResponse&lt;ObtenirIdChorusProResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<ObtenirIdChorusProResponse> obtenirIdChorusProDepuisSiretApiV1ChorusProStructuresObtenirIdDepuisSiretPostWithHttpInfo(@javax.annotation.Nonnull ObtenirIdChorusProRequest obtenirIdChorusProRequest) throws ApiException {
        okhttp3.Call localVarCall = obtenirIdChorusProDepuisSiretApiV1ChorusProStructuresObtenirIdDepuisSiretPostValidateBeforeCall(obtenirIdChorusProRequest, null);
        Type localVarReturnType = new TypeToken<ObtenirIdChorusProResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Utilitaire : Obtenir l&#39;ID Chorus Pro depuis un SIRET (asynchronously)
     * **Utilitaire pratique** pour obtenir l&#39;ID Chorus Pro d&#39;une structure à partir de son SIRET.       Cette fonction wrapper combine :     1. Recherche de la structure par SIRET     2. Extraction de l&#39;&#x60;id_structure_cpp&#x60; si une seule structure est trouvée      **Retour** :     - &#x60;id_structure_cpp&#x60; : ID Chorus Pro (0 si non trouvé ou si plusieurs résultats)     - &#x60;designation_structure&#x60; : Nom de la structure (si trouvée)     - &#x60;message&#x60; : Message explicatif      **Cas d&#39;usage** :     - Raccourci pour obtenir directement l&#39;ID Chorus Pro avant de soumettre une facture     - Alternative simplifiée à &#x60;rechercher-structures&#x60; + extraction manuelle de l&#39;ID      **Note** : Si plusieurs structures correspondent au SIRET (rare), retourne 0 et un message d&#39;erreur.
     * @param obtenirIdChorusProRequest  (required)
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
    public okhttp3.Call obtenirIdChorusProDepuisSiretApiV1ChorusProStructuresObtenirIdDepuisSiretPostAsync(@javax.annotation.Nonnull ObtenirIdChorusProRequest obtenirIdChorusProRequest, final ApiCallback<ObtenirIdChorusProResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = obtenirIdChorusProDepuisSiretApiV1ChorusProStructuresObtenirIdDepuisSiretPostValidateBeforeCall(obtenirIdChorusProRequest, _callback);
        Type localVarReturnType = new TypeToken<ObtenirIdChorusProResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for rechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost
     * @param requestBody  (required)
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
    public okhttp3.Call rechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePostCall(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = requestBody;

        // create path and map variables
        String localVarPath = "/api/v1/chorus-pro/factures/rechercher-destinataire";

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
    private okhttp3.Call rechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePostValidateBeforeCall(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'requestBody' is set
        if (requestBody == null) {
            throw new ApiException("Missing the required parameter 'requestBody' when calling rechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost(Async)");
        }

        return rechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePostCall(requestBody, _callback);

    }

    /**
     * Rechercher factures reçues (Destinataire)
     * Recherche les factures reçues par le destinataire connecté.      **Filtres** :     - Téléchargée / non téléchargée     - Dates de réception     - Statut (MISE_A_DISPOSITION, SUSPENDUE, etc.)     - Fournisseur      **Indicateur utile** : &#x60;factureTelechargeeParDestinataire&#x60; permet de savoir si la facture a déjà été téléchargée.
     * @param requestBody  (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object rechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost(@javax.annotation.Nonnull Map<String, Object> requestBody) throws ApiException {
        ApiResponse<Object> localVarResp = rechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePostWithHttpInfo(requestBody);
        return localVarResp.getData();
    }

    /**
     * Rechercher factures reçues (Destinataire)
     * Recherche les factures reçues par le destinataire connecté.      **Filtres** :     - Téléchargée / non téléchargée     - Dates de réception     - Statut (MISE_A_DISPOSITION, SUSPENDUE, etc.)     - Fournisseur      **Indicateur utile** : &#x60;factureTelechargeeParDestinataire&#x60; permet de savoir si la facture a déjà été téléchargée.
     * @param requestBody  (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> rechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePostWithHttpInfo(@javax.annotation.Nonnull Map<String, Object> requestBody) throws ApiException {
        okhttp3.Call localVarCall = rechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePostValidateBeforeCall(requestBody, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Rechercher factures reçues (Destinataire) (asynchronously)
     * Recherche les factures reçues par le destinataire connecté.      **Filtres** :     - Téléchargée / non téléchargée     - Dates de réception     - Statut (MISE_A_DISPOSITION, SUSPENDUE, etc.)     - Fournisseur      **Indicateur utile** : &#x60;factureTelechargeeParDestinataire&#x60; permet de savoir si la facture a déjà été téléchargée.
     * @param requestBody  (required)
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
    public okhttp3.Call rechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePostAsync(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = rechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePostValidateBeforeCall(requestBody, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for rechercherFacturesFournisseurApiV1ChorusProFacturesRechercherFournisseurPost
     * @param requestBody  (required)
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
    public okhttp3.Call rechercherFacturesFournisseurApiV1ChorusProFacturesRechercherFournisseurPostCall(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = requestBody;

        // create path and map variables
        String localVarPath = "/api/v1/chorus-pro/factures/rechercher-fournisseur";

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
    private okhttp3.Call rechercherFacturesFournisseurApiV1ChorusProFacturesRechercherFournisseurPostValidateBeforeCall(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'requestBody' is set
        if (requestBody == null) {
            throw new ApiException("Missing the required parameter 'requestBody' when calling rechercherFacturesFournisseurApiV1ChorusProFacturesRechercherFournisseurPost(Async)");
        }

        return rechercherFacturesFournisseurApiV1ChorusProFacturesRechercherFournisseurPostCall(requestBody, _callback);

    }

    /**
     * Rechercher factures émises (Fournisseur)
     * Recherche les factures émises par le fournisseur connecté.      **Filtres disponibles** :     - Numéro de facture     - Dates (début/fin)     - Statut     - Structure destinataire     - Montant      **Cas d&#39;usage** :     - Suivi des factures émises     - Vérification des statuts     - Export pour comptabilité
     * @param requestBody  (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object rechercherFacturesFournisseurApiV1ChorusProFacturesRechercherFournisseurPost(@javax.annotation.Nonnull Map<String, Object> requestBody) throws ApiException {
        ApiResponse<Object> localVarResp = rechercherFacturesFournisseurApiV1ChorusProFacturesRechercherFournisseurPostWithHttpInfo(requestBody);
        return localVarResp.getData();
    }

    /**
     * Rechercher factures émises (Fournisseur)
     * Recherche les factures émises par le fournisseur connecté.      **Filtres disponibles** :     - Numéro de facture     - Dates (début/fin)     - Statut     - Structure destinataire     - Montant      **Cas d&#39;usage** :     - Suivi des factures émises     - Vérification des statuts     - Export pour comptabilité
     * @param requestBody  (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> rechercherFacturesFournisseurApiV1ChorusProFacturesRechercherFournisseurPostWithHttpInfo(@javax.annotation.Nonnull Map<String, Object> requestBody) throws ApiException {
        okhttp3.Call localVarCall = rechercherFacturesFournisseurApiV1ChorusProFacturesRechercherFournisseurPostValidateBeforeCall(requestBody, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Rechercher factures émises (Fournisseur) (asynchronously)
     * Recherche les factures émises par le fournisseur connecté.      **Filtres disponibles** :     - Numéro de facture     - Dates (début/fin)     - Statut     - Structure destinataire     - Montant      **Cas d&#39;usage** :     - Suivi des factures émises     - Vérification des statuts     - Export pour comptabilité
     * @param requestBody  (required)
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
    public okhttp3.Call rechercherFacturesFournisseurApiV1ChorusProFacturesRechercherFournisseurPostAsync(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = rechercherFacturesFournisseurApiV1ChorusProFacturesRechercherFournisseurPostValidateBeforeCall(requestBody, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for rechercherStructuresApiV1ChorusProStructuresRechercherPost
     * @param rechercherStructureRequest  (required)
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
    public okhttp3.Call rechercherStructuresApiV1ChorusProStructuresRechercherPostCall(@javax.annotation.Nonnull RechercherStructureRequest rechercherStructureRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = rechercherStructureRequest;

        // create path and map variables
        String localVarPath = "/api/v1/chorus-pro/structures/rechercher";

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
    private okhttp3.Call rechercherStructuresApiV1ChorusProStructuresRechercherPostValidateBeforeCall(@javax.annotation.Nonnull RechercherStructureRequest rechercherStructureRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'rechercherStructureRequest' is set
        if (rechercherStructureRequest == null) {
            throw new ApiException("Missing the required parameter 'rechercherStructureRequest' when calling rechercherStructuresApiV1ChorusProStructuresRechercherPost(Async)");
        }

        return rechercherStructuresApiV1ChorusProStructuresRechercherPostCall(rechercherStructureRequest, _callback);

    }

    /**
     * Rechercher des structures Chorus Pro
     * Recherche des structures (entreprises, administrations) enregistrées sur Chorus Pro.      **Cas d&#39;usage** :     - Trouver l&#39;ID Chorus Pro d&#39;une structure à partir de son SIRET     - Vérifier si une structure est enregistrée sur Chorus Pro     - Lister les structures correspondant à des critères      **Filtres disponibles** :     - Identifiant (SIRET, SIREN, etc.)     - Raison sociale     - Type d&#39;identifiant     - Structures privées uniquement      **Étape typique** : Appelée avant &#x60;soumettre-facture&#x60; pour obtenir l&#39;&#x60;id_structure_cpp&#x60; du destinataire.
     * @param rechercherStructureRequest  (required)
     * @return RechercherStructureResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public RechercherStructureResponse rechercherStructuresApiV1ChorusProStructuresRechercherPost(@javax.annotation.Nonnull RechercherStructureRequest rechercherStructureRequest) throws ApiException {
        ApiResponse<RechercherStructureResponse> localVarResp = rechercherStructuresApiV1ChorusProStructuresRechercherPostWithHttpInfo(rechercherStructureRequest);
        return localVarResp.getData();
    }

    /**
     * Rechercher des structures Chorus Pro
     * Recherche des structures (entreprises, administrations) enregistrées sur Chorus Pro.      **Cas d&#39;usage** :     - Trouver l&#39;ID Chorus Pro d&#39;une structure à partir de son SIRET     - Vérifier si une structure est enregistrée sur Chorus Pro     - Lister les structures correspondant à des critères      **Filtres disponibles** :     - Identifiant (SIRET, SIREN, etc.)     - Raison sociale     - Type d&#39;identifiant     - Structures privées uniquement      **Étape typique** : Appelée avant &#x60;soumettre-facture&#x60; pour obtenir l&#39;&#x60;id_structure_cpp&#x60; du destinataire.
     * @param rechercherStructureRequest  (required)
     * @return ApiResponse&lt;RechercherStructureResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<RechercherStructureResponse> rechercherStructuresApiV1ChorusProStructuresRechercherPostWithHttpInfo(@javax.annotation.Nonnull RechercherStructureRequest rechercherStructureRequest) throws ApiException {
        okhttp3.Call localVarCall = rechercherStructuresApiV1ChorusProStructuresRechercherPostValidateBeforeCall(rechercherStructureRequest, null);
        Type localVarReturnType = new TypeToken<RechercherStructureResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Rechercher des structures Chorus Pro (asynchronously)
     * Recherche des structures (entreprises, administrations) enregistrées sur Chorus Pro.      **Cas d&#39;usage** :     - Trouver l&#39;ID Chorus Pro d&#39;une structure à partir de son SIRET     - Vérifier si une structure est enregistrée sur Chorus Pro     - Lister les structures correspondant à des critères      **Filtres disponibles** :     - Identifiant (SIRET, SIREN, etc.)     - Raison sociale     - Type d&#39;identifiant     - Structures privées uniquement      **Étape typique** : Appelée avant &#x60;soumettre-facture&#x60; pour obtenir l&#39;&#x60;id_structure_cpp&#x60; du destinataire.
     * @param rechercherStructureRequest  (required)
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
    public okhttp3.Call rechercherStructuresApiV1ChorusProStructuresRechercherPostAsync(@javax.annotation.Nonnull RechercherStructureRequest rechercherStructureRequest, final ApiCallback<RechercherStructureResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = rechercherStructuresApiV1ChorusProStructuresRechercherPostValidateBeforeCall(rechercherStructureRequest, _callback);
        Type localVarReturnType = new TypeToken<RechercherStructureResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for recyclerFactureApiV1ChorusProFacturesRecyclerPost
     * @param requestBody  (required)
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
    public okhttp3.Call recyclerFactureApiV1ChorusProFacturesRecyclerPostCall(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = requestBody;

        // create path and map variables
        String localVarPath = "/api/v1/chorus-pro/factures/recycler";

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
    private okhttp3.Call recyclerFactureApiV1ChorusProFacturesRecyclerPostValidateBeforeCall(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'requestBody' is set
        if (requestBody == null) {
            throw new ApiException("Missing the required parameter 'requestBody' when calling recyclerFactureApiV1ChorusProFacturesRecyclerPost(Async)");
        }

        return recyclerFactureApiV1ChorusProFacturesRecyclerPostCall(requestBody, _callback);

    }

    /**
     * Recycler une facture (Fournisseur)
     * Recycle une facture au statut A_RECYCLER en modifiant les données d&#39;acheminement.      **Statut requis** : A_RECYCLER      **Champs modifiables** :     - Destinataire (&#x60;idStructureCPP&#x60;)     - Code service     - Numéro d&#39;engagement      **Cas d&#39;usage** :     - Erreur de destinataire     - Changement de service facturation     - Mise à jour du numéro d&#39;engagement      **Payload exemple** :     &#x60;&#x60;&#x60;json     {       \&quot;identifiantFactureCPP\&quot;: 12345,       \&quot;idStructureCPP\&quot;: 67890,       \&quot;codeService\&quot;: \&quot;SERVICE_01\&quot;,       \&quot;numeroEngagement\&quot;: \&quot;ENG2024001\&quot;     }     &#x60;&#x60;&#x60;      **Note** : La facture conserve son numéro et ses montants, seuls les champs d&#39;acheminement changent.
     * @param requestBody  (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object recyclerFactureApiV1ChorusProFacturesRecyclerPost(@javax.annotation.Nonnull Map<String, Object> requestBody) throws ApiException {
        ApiResponse<Object> localVarResp = recyclerFactureApiV1ChorusProFacturesRecyclerPostWithHttpInfo(requestBody);
        return localVarResp.getData();
    }

    /**
     * Recycler une facture (Fournisseur)
     * Recycle une facture au statut A_RECYCLER en modifiant les données d&#39;acheminement.      **Statut requis** : A_RECYCLER      **Champs modifiables** :     - Destinataire (&#x60;idStructureCPP&#x60;)     - Code service     - Numéro d&#39;engagement      **Cas d&#39;usage** :     - Erreur de destinataire     - Changement de service facturation     - Mise à jour du numéro d&#39;engagement      **Payload exemple** :     &#x60;&#x60;&#x60;json     {       \&quot;identifiantFactureCPP\&quot;: 12345,       \&quot;idStructureCPP\&quot;: 67890,       \&quot;codeService\&quot;: \&quot;SERVICE_01\&quot;,       \&quot;numeroEngagement\&quot;: \&quot;ENG2024001\&quot;     }     &#x60;&#x60;&#x60;      **Note** : La facture conserve son numéro et ses montants, seuls les champs d&#39;acheminement changent.
     * @param requestBody  (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> recyclerFactureApiV1ChorusProFacturesRecyclerPostWithHttpInfo(@javax.annotation.Nonnull Map<String, Object> requestBody) throws ApiException {
        okhttp3.Call localVarCall = recyclerFactureApiV1ChorusProFacturesRecyclerPostValidateBeforeCall(requestBody, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Recycler une facture (Fournisseur) (asynchronously)
     * Recycle une facture au statut A_RECYCLER en modifiant les données d&#39;acheminement.      **Statut requis** : A_RECYCLER      **Champs modifiables** :     - Destinataire (&#x60;idStructureCPP&#x60;)     - Code service     - Numéro d&#39;engagement      **Cas d&#39;usage** :     - Erreur de destinataire     - Changement de service facturation     - Mise à jour du numéro d&#39;engagement      **Payload exemple** :     &#x60;&#x60;&#x60;json     {       \&quot;identifiantFactureCPP\&quot;: 12345,       \&quot;idStructureCPP\&quot;: 67890,       \&quot;codeService\&quot;: \&quot;SERVICE_01\&quot;,       \&quot;numeroEngagement\&quot;: \&quot;ENG2024001\&quot;     }     &#x60;&#x60;&#x60;      **Note** : La facture conserve son numéro et ses montants, seuls les champs d&#39;acheminement changent.
     * @param requestBody  (required)
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
    public okhttp3.Call recyclerFactureApiV1ChorusProFacturesRecyclerPostAsync(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = recyclerFactureApiV1ChorusProFacturesRecyclerPostValidateBeforeCall(requestBody, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for soumettreFactureApiV1ChorusProFacturesSoumettrePost
     * @param soumettreFactureRequest  (required)
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
    public okhttp3.Call soumettreFactureApiV1ChorusProFacturesSoumettrePostCall(@javax.annotation.Nonnull SoumettreFactureRequest soumettreFactureRequest, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = soumettreFactureRequest;

        // create path and map variables
        String localVarPath = "/api/v1/chorus-pro/factures/soumettre";

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
    private okhttp3.Call soumettreFactureApiV1ChorusProFacturesSoumettrePostValidateBeforeCall(@javax.annotation.Nonnull SoumettreFactureRequest soumettreFactureRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'soumettreFactureRequest' is set
        if (soumettreFactureRequest == null) {
            throw new ApiException("Missing the required parameter 'soumettreFactureRequest' when calling soumettreFactureApiV1ChorusProFacturesSoumettrePost(Async)");
        }

        return soumettreFactureApiV1ChorusProFacturesSoumettrePostCall(soumettreFactureRequest, _callback);

    }

    /**
     * Soumettre une facture à Chorus Pro
     * Soumet une facture électronique à une structure publique via Chorus Pro.       **📋 Workflow complet** :     1. **Uploader le PDF Factur-X** via &#x60;/transverses/ajouter-fichier&#x60; → récupérer &#x60;pieceJointeId&#x60;     2. **Obtenir l&#39;ID structure** via &#x60;/structures/rechercher&#x60; ou &#x60;/structures/obtenir-id-depuis-siret&#x60;     3. **Vérifier les paramètres obligatoires** via &#x60;/structures/consulter&#x60;     4. **Soumettre la facture** avec le &#x60;piece_jointe_principale_id&#x60; obtenu à l&#39;étape 1      **Pré-requis** :     1. Avoir l&#39;&#x60;id_structure_cpp&#x60; du destinataire (via &#x60;/structures/rechercher&#x60;)     2. Connaître les paramètres obligatoires (via &#x60;/structures/consulter&#x60;) :        - Code service si &#x60;code_service_doit_etre_renseigne&#x3D;true&#x60;        - Numéro d&#39;engagement si &#x60;numero_ej_doit_etre_renseigne&#x3D;true&#x60;     3. Avoir uploadé le PDF Factur-X (via &#x60;/transverses/ajouter-fichier&#x60;)      **Format attendu** :     - &#x60;piece_jointe_principale_id&#x60; : ID retourné par &#x60;/transverses/ajouter-fichier&#x60;     - Montants : Chaînes de caractères avec 2 décimales (ex: \&quot;1250.50\&quot;)     - Dates : Format ISO 8601 (YYYY-MM-DD)      **Retour** :     - &#x60;identifiant_facture_cpp&#x60; : ID Chorus Pro de la facture créée     - &#x60;numero_flux_depot&#x60; : Numéro de suivi du dépôt      **Statuts possibles après soumission** :     - SOUMISE : En attente de validation     - VALIDEE : Validée par le destinataire     - REJETEE : Rejetée (erreur de données ou refus métier)     - SUSPENDUE : En attente d&#39;informations complémentaires      **Note** : Utilisez &#x60;/factures/consulter&#x60; pour suivre l&#39;évolution du statut.
     * @param soumettreFactureRequest  (required)
     * @return SoumettreFactureResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public SoumettreFactureResponse soumettreFactureApiV1ChorusProFacturesSoumettrePost(@javax.annotation.Nonnull SoumettreFactureRequest soumettreFactureRequest) throws ApiException {
        ApiResponse<SoumettreFactureResponse> localVarResp = soumettreFactureApiV1ChorusProFacturesSoumettrePostWithHttpInfo(soumettreFactureRequest);
        return localVarResp.getData();
    }

    /**
     * Soumettre une facture à Chorus Pro
     * Soumet une facture électronique à une structure publique via Chorus Pro.       **📋 Workflow complet** :     1. **Uploader le PDF Factur-X** via &#x60;/transverses/ajouter-fichier&#x60; → récupérer &#x60;pieceJointeId&#x60;     2. **Obtenir l&#39;ID structure** via &#x60;/structures/rechercher&#x60; ou &#x60;/structures/obtenir-id-depuis-siret&#x60;     3. **Vérifier les paramètres obligatoires** via &#x60;/structures/consulter&#x60;     4. **Soumettre la facture** avec le &#x60;piece_jointe_principale_id&#x60; obtenu à l&#39;étape 1      **Pré-requis** :     1. Avoir l&#39;&#x60;id_structure_cpp&#x60; du destinataire (via &#x60;/structures/rechercher&#x60;)     2. Connaître les paramètres obligatoires (via &#x60;/structures/consulter&#x60;) :        - Code service si &#x60;code_service_doit_etre_renseigne&#x3D;true&#x60;        - Numéro d&#39;engagement si &#x60;numero_ej_doit_etre_renseigne&#x3D;true&#x60;     3. Avoir uploadé le PDF Factur-X (via &#x60;/transverses/ajouter-fichier&#x60;)      **Format attendu** :     - &#x60;piece_jointe_principale_id&#x60; : ID retourné par &#x60;/transverses/ajouter-fichier&#x60;     - Montants : Chaînes de caractères avec 2 décimales (ex: \&quot;1250.50\&quot;)     - Dates : Format ISO 8601 (YYYY-MM-DD)      **Retour** :     - &#x60;identifiant_facture_cpp&#x60; : ID Chorus Pro de la facture créée     - &#x60;numero_flux_depot&#x60; : Numéro de suivi du dépôt      **Statuts possibles après soumission** :     - SOUMISE : En attente de validation     - VALIDEE : Validée par le destinataire     - REJETEE : Rejetée (erreur de données ou refus métier)     - SUSPENDUE : En attente d&#39;informations complémentaires      **Note** : Utilisez &#x60;/factures/consulter&#x60; pour suivre l&#39;évolution du statut.
     * @param soumettreFactureRequest  (required)
     * @return ApiResponse&lt;SoumettreFactureResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<SoumettreFactureResponse> soumettreFactureApiV1ChorusProFacturesSoumettrePostWithHttpInfo(@javax.annotation.Nonnull SoumettreFactureRequest soumettreFactureRequest) throws ApiException {
        okhttp3.Call localVarCall = soumettreFactureApiV1ChorusProFacturesSoumettrePostValidateBeforeCall(soumettreFactureRequest, null);
        Type localVarReturnType = new TypeToken<SoumettreFactureResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Soumettre une facture à Chorus Pro (asynchronously)
     * Soumet une facture électronique à une structure publique via Chorus Pro.       **📋 Workflow complet** :     1. **Uploader le PDF Factur-X** via &#x60;/transverses/ajouter-fichier&#x60; → récupérer &#x60;pieceJointeId&#x60;     2. **Obtenir l&#39;ID structure** via &#x60;/structures/rechercher&#x60; ou &#x60;/structures/obtenir-id-depuis-siret&#x60;     3. **Vérifier les paramètres obligatoires** via &#x60;/structures/consulter&#x60;     4. **Soumettre la facture** avec le &#x60;piece_jointe_principale_id&#x60; obtenu à l&#39;étape 1      **Pré-requis** :     1. Avoir l&#39;&#x60;id_structure_cpp&#x60; du destinataire (via &#x60;/structures/rechercher&#x60;)     2. Connaître les paramètres obligatoires (via &#x60;/structures/consulter&#x60;) :        - Code service si &#x60;code_service_doit_etre_renseigne&#x3D;true&#x60;        - Numéro d&#39;engagement si &#x60;numero_ej_doit_etre_renseigne&#x3D;true&#x60;     3. Avoir uploadé le PDF Factur-X (via &#x60;/transverses/ajouter-fichier&#x60;)      **Format attendu** :     - &#x60;piece_jointe_principale_id&#x60; : ID retourné par &#x60;/transverses/ajouter-fichier&#x60;     - Montants : Chaînes de caractères avec 2 décimales (ex: \&quot;1250.50\&quot;)     - Dates : Format ISO 8601 (YYYY-MM-DD)      **Retour** :     - &#x60;identifiant_facture_cpp&#x60; : ID Chorus Pro de la facture créée     - &#x60;numero_flux_depot&#x60; : Numéro de suivi du dépôt      **Statuts possibles après soumission** :     - SOUMISE : En attente de validation     - VALIDEE : Validée par le destinataire     - REJETEE : Rejetée (erreur de données ou refus métier)     - SUSPENDUE : En attente d&#39;informations complémentaires      **Note** : Utilisez &#x60;/factures/consulter&#x60; pour suivre l&#39;évolution du statut.
     * @param soumettreFactureRequest  (required)
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
    public okhttp3.Call soumettreFactureApiV1ChorusProFacturesSoumettrePostAsync(@javax.annotation.Nonnull SoumettreFactureRequest soumettreFactureRequest, final ApiCallback<SoumettreFactureResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = soumettreFactureApiV1ChorusProFacturesSoumettrePostValidateBeforeCall(soumettreFactureRequest, _callback);
        Type localVarReturnType = new TypeToken<SoumettreFactureResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for telechargerGroupeFacturesApiV1ChorusProFacturesTelechargerGroupePost
     * @param requestBody  (required)
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
    public okhttp3.Call telechargerGroupeFacturesApiV1ChorusProFacturesTelechargerGroupePostCall(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = requestBody;

        // create path and map variables
        String localVarPath = "/api/v1/chorus-pro/factures/telecharger-groupe";

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
    private okhttp3.Call telechargerGroupeFacturesApiV1ChorusProFacturesTelechargerGroupePostValidateBeforeCall(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'requestBody' is set
        if (requestBody == null) {
            throw new ApiException("Missing the required parameter 'requestBody' when calling telechargerGroupeFacturesApiV1ChorusProFacturesTelechargerGroupePost(Async)");
        }

        return telechargerGroupeFacturesApiV1ChorusProFacturesTelechargerGroupePostCall(requestBody, _callback);

    }

    /**
     * Télécharger un groupe de factures
     * Télécharge une ou plusieurs factures (max 10 recommandé) avec leurs pièces jointes.      **Formats disponibles** :     - PDF : Fichier PDF uniquement     - XML : Fichier XML uniquement     - ZIP : Archive contenant PDF + XML + pièces jointes      **Taille maximale** : 120 Mo par téléchargement      **Payload exemple** :     &#x60;&#x60;&#x60;json     {       \&quot;listeIdentifiantsFactureCPP\&quot;: [12345, 12346],       \&quot;inclurePiecesJointes\&quot;: true,       \&quot;formatFichier\&quot;: \&quot;ZIP\&quot;     }     &#x60;&#x60;&#x60;      **Retour** : Le fichier est encodé en base64 dans le champ &#x60;fichierBase64&#x60;.      **Note** : Le flag &#x60;factureTelechargeeParDestinataire&#x60; est mis à jour automatiquement.
     * @param requestBody  (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object telechargerGroupeFacturesApiV1ChorusProFacturesTelechargerGroupePost(@javax.annotation.Nonnull Map<String, Object> requestBody) throws ApiException {
        ApiResponse<Object> localVarResp = telechargerGroupeFacturesApiV1ChorusProFacturesTelechargerGroupePostWithHttpInfo(requestBody);
        return localVarResp.getData();
    }

    /**
     * Télécharger un groupe de factures
     * Télécharge une ou plusieurs factures (max 10 recommandé) avec leurs pièces jointes.      **Formats disponibles** :     - PDF : Fichier PDF uniquement     - XML : Fichier XML uniquement     - ZIP : Archive contenant PDF + XML + pièces jointes      **Taille maximale** : 120 Mo par téléchargement      **Payload exemple** :     &#x60;&#x60;&#x60;json     {       \&quot;listeIdentifiantsFactureCPP\&quot;: [12345, 12346],       \&quot;inclurePiecesJointes\&quot;: true,       \&quot;formatFichier\&quot;: \&quot;ZIP\&quot;     }     &#x60;&#x60;&#x60;      **Retour** : Le fichier est encodé en base64 dans le champ &#x60;fichierBase64&#x60;.      **Note** : Le flag &#x60;factureTelechargeeParDestinataire&#x60; est mis à jour automatiquement.
     * @param requestBody  (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> telechargerGroupeFacturesApiV1ChorusProFacturesTelechargerGroupePostWithHttpInfo(@javax.annotation.Nonnull Map<String, Object> requestBody) throws ApiException {
        okhttp3.Call localVarCall = telechargerGroupeFacturesApiV1ChorusProFacturesTelechargerGroupePostValidateBeforeCall(requestBody, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Télécharger un groupe de factures (asynchronously)
     * Télécharge une ou plusieurs factures (max 10 recommandé) avec leurs pièces jointes.      **Formats disponibles** :     - PDF : Fichier PDF uniquement     - XML : Fichier XML uniquement     - ZIP : Archive contenant PDF + XML + pièces jointes      **Taille maximale** : 120 Mo par téléchargement      **Payload exemple** :     &#x60;&#x60;&#x60;json     {       \&quot;listeIdentifiantsFactureCPP\&quot;: [12345, 12346],       \&quot;inclurePiecesJointes\&quot;: true,       \&quot;formatFichier\&quot;: \&quot;ZIP\&quot;     }     &#x60;&#x60;&#x60;      **Retour** : Le fichier est encodé en base64 dans le champ &#x60;fichierBase64&#x60;.      **Note** : Le flag &#x60;factureTelechargeeParDestinataire&#x60; est mis à jour automatiquement.
     * @param requestBody  (required)
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
    public okhttp3.Call telechargerGroupeFacturesApiV1ChorusProFacturesTelechargerGroupePostAsync(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = telechargerGroupeFacturesApiV1ChorusProFacturesTelechargerGroupePostValidateBeforeCall(requestBody, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for traiterFactureRecueApiV1ChorusProFacturesTraiterFactureRecuePost
     * @param requestBody  (required)
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
    public okhttp3.Call traiterFactureRecueApiV1ChorusProFacturesTraiterFactureRecuePostCall(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = requestBody;

        // create path and map variables
        String localVarPath = "/api/v1/chorus-pro/factures/traiter-facture-recue";

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
    private okhttp3.Call traiterFactureRecueApiV1ChorusProFacturesTraiterFactureRecuePostValidateBeforeCall(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'requestBody' is set
        if (requestBody == null) {
            throw new ApiException("Missing the required parameter 'requestBody' when calling traiterFactureRecueApiV1ChorusProFacturesTraiterFactureRecuePost(Async)");
        }

        return traiterFactureRecueApiV1ChorusProFacturesTraiterFactureRecuePostCall(requestBody, _callback);

    }

    /**
     * Traiter une facture reçue (Destinataire)
     * Change le statut d&#39;une facture reçue.      **Statuts possibles** :     - MISE_A_DISPOSITION : Facture acceptée     - SUSPENDUE : En attente d&#39;informations complémentaires (motif obligatoire)     - REJETEE : Facture refusée (motif obligatoire)     - MANDATEE : Facture mandatée     - MISE_EN_PAIEMENT : Facture en cours de paiement     - COMPTABILISEE : Facture comptabilisée     - MISE_A_DISPOSITION_COMPTABLE : Mise à disposition comptable     - A_RECYCLER : À recycler     - COMPLETEE : Complétée     - SERVICE-FAIT : Service fait     - PRISE_EN_COMPTE_DESTINATAIRE : Prise en compte     - TRANSMISE_MOA : Transmise à la MOA      **Payload exemple** :     &#x60;&#x60;&#x60;json     {       \&quot;identifiantFactureCPP\&quot;: 12345,       \&quot;nouveauStatut\&quot;: \&quot;REJETEE\&quot;,       \&quot;motifRejet\&quot;: \&quot;Facture en double\&quot;,       \&quot;commentaire\&quot;: \&quot;Facture déjà reçue sous la référence ABC123\&quot;     }     &#x60;&#x60;&#x60;      **Règles** :     - Un motif est **obligatoire** pour SUSPENDUE et REJETEE     - Seuls certains statuts sont autorisés selon le statut actuel de la facture
     * @param requestBody  (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object traiterFactureRecueApiV1ChorusProFacturesTraiterFactureRecuePost(@javax.annotation.Nonnull Map<String, Object> requestBody) throws ApiException {
        ApiResponse<Object> localVarResp = traiterFactureRecueApiV1ChorusProFacturesTraiterFactureRecuePostWithHttpInfo(requestBody);
        return localVarResp.getData();
    }

    /**
     * Traiter une facture reçue (Destinataire)
     * Change le statut d&#39;une facture reçue.      **Statuts possibles** :     - MISE_A_DISPOSITION : Facture acceptée     - SUSPENDUE : En attente d&#39;informations complémentaires (motif obligatoire)     - REJETEE : Facture refusée (motif obligatoire)     - MANDATEE : Facture mandatée     - MISE_EN_PAIEMENT : Facture en cours de paiement     - COMPTABILISEE : Facture comptabilisée     - MISE_A_DISPOSITION_COMPTABLE : Mise à disposition comptable     - A_RECYCLER : À recycler     - COMPLETEE : Complétée     - SERVICE-FAIT : Service fait     - PRISE_EN_COMPTE_DESTINATAIRE : Prise en compte     - TRANSMISE_MOA : Transmise à la MOA      **Payload exemple** :     &#x60;&#x60;&#x60;json     {       \&quot;identifiantFactureCPP\&quot;: 12345,       \&quot;nouveauStatut\&quot;: \&quot;REJETEE\&quot;,       \&quot;motifRejet\&quot;: \&quot;Facture en double\&quot;,       \&quot;commentaire\&quot;: \&quot;Facture déjà reçue sous la référence ABC123\&quot;     }     &#x60;&#x60;&#x60;      **Règles** :     - Un motif est **obligatoire** pour SUSPENDUE et REJETEE     - Seuls certains statuts sont autorisés selon le statut actuel de la facture
     * @param requestBody  (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> traiterFactureRecueApiV1ChorusProFacturesTraiterFactureRecuePostWithHttpInfo(@javax.annotation.Nonnull Map<String, Object> requestBody) throws ApiException {
        okhttp3.Call localVarCall = traiterFactureRecueApiV1ChorusProFacturesTraiterFactureRecuePostValidateBeforeCall(requestBody, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Traiter une facture reçue (Destinataire) (asynchronously)
     * Change le statut d&#39;une facture reçue.      **Statuts possibles** :     - MISE_A_DISPOSITION : Facture acceptée     - SUSPENDUE : En attente d&#39;informations complémentaires (motif obligatoire)     - REJETEE : Facture refusée (motif obligatoire)     - MANDATEE : Facture mandatée     - MISE_EN_PAIEMENT : Facture en cours de paiement     - COMPTABILISEE : Facture comptabilisée     - MISE_A_DISPOSITION_COMPTABLE : Mise à disposition comptable     - A_RECYCLER : À recycler     - COMPLETEE : Complétée     - SERVICE-FAIT : Service fait     - PRISE_EN_COMPTE_DESTINATAIRE : Prise en compte     - TRANSMISE_MOA : Transmise à la MOA      **Payload exemple** :     &#x60;&#x60;&#x60;json     {       \&quot;identifiantFactureCPP\&quot;: 12345,       \&quot;nouveauStatut\&quot;: \&quot;REJETEE\&quot;,       \&quot;motifRejet\&quot;: \&quot;Facture en double\&quot;,       \&quot;commentaire\&quot;: \&quot;Facture déjà reçue sous la référence ABC123\&quot;     }     &#x60;&#x60;&#x60;      **Règles** :     - Un motif est **obligatoire** pour SUSPENDUE et REJETEE     - Seuls certains statuts sont autorisés selon le statut actuel de la facture
     * @param requestBody  (required)
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
    public okhttp3.Call traiterFactureRecueApiV1ChorusProFacturesTraiterFactureRecuePostAsync(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = traiterFactureRecueApiV1ChorusProFacturesTraiterFactureRecuePostValidateBeforeCall(requestBody, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for valideurConsulterFactureApiV1ChorusProFacturesValideurConsulterPost
     * @param requestBody  (required)
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
    public okhttp3.Call valideurConsulterFactureApiV1ChorusProFacturesValideurConsulterPostCall(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = requestBody;

        // create path and map variables
        String localVarPath = "/api/v1/chorus-pro/factures/valideur/consulter";

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
    private okhttp3.Call valideurConsulterFactureApiV1ChorusProFacturesValideurConsulterPostValidateBeforeCall(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'requestBody' is set
        if (requestBody == null) {
            throw new ApiException("Missing the required parameter 'requestBody' when calling valideurConsulterFactureApiV1ChorusProFacturesValideurConsulterPost(Async)");
        }

        return valideurConsulterFactureApiV1ChorusProFacturesValideurConsulterPostCall(requestBody, _callback);

    }

    /**
     * Consulter une facture (Valideur)
     * 
     * @param requestBody  (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object valideurConsulterFactureApiV1ChorusProFacturesValideurConsulterPost(@javax.annotation.Nonnull Map<String, Object> requestBody) throws ApiException {
        ApiResponse<Object> localVarResp = valideurConsulterFactureApiV1ChorusProFacturesValideurConsulterPostWithHttpInfo(requestBody);
        return localVarResp.getData();
    }

    /**
     * Consulter une facture (Valideur)
     * 
     * @param requestBody  (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> valideurConsulterFactureApiV1ChorusProFacturesValideurConsulterPostWithHttpInfo(@javax.annotation.Nonnull Map<String, Object> requestBody) throws ApiException {
        okhttp3.Call localVarCall = valideurConsulterFactureApiV1ChorusProFacturesValideurConsulterPostValidateBeforeCall(requestBody, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Consulter une facture (Valideur) (asynchronously)
     * 
     * @param requestBody  (required)
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
    public okhttp3.Call valideurConsulterFactureApiV1ChorusProFacturesValideurConsulterPostAsync(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = valideurConsulterFactureApiV1ChorusProFacturesValideurConsulterPostValidateBeforeCall(requestBody, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for valideurRechercherFacturesApiV1ChorusProFacturesValideurRechercherPost
     * @param requestBody  (required)
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
    public okhttp3.Call valideurRechercherFacturesApiV1ChorusProFacturesValideurRechercherPostCall(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = requestBody;

        // create path and map variables
        String localVarPath = "/api/v1/chorus-pro/factures/valideur/rechercher";

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
    private okhttp3.Call valideurRechercherFacturesApiV1ChorusProFacturesValideurRechercherPostValidateBeforeCall(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'requestBody' is set
        if (requestBody == null) {
            throw new ApiException("Missing the required parameter 'requestBody' when calling valideurRechercherFacturesApiV1ChorusProFacturesValideurRechercherPost(Async)");
        }

        return valideurRechercherFacturesApiV1ChorusProFacturesValideurRechercherPostCall(requestBody, _callback);

    }

    /**
     * Rechercher factures à valider (Valideur)
     * Recherche les factures en attente de validation par le valideur connecté.      **Rôle** : Valideur dans le circuit de validation interne.      **Filtres** : Dates, structure, service, etc.
     * @param requestBody  (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object valideurRechercherFacturesApiV1ChorusProFacturesValideurRechercherPost(@javax.annotation.Nonnull Map<String, Object> requestBody) throws ApiException {
        ApiResponse<Object> localVarResp = valideurRechercherFacturesApiV1ChorusProFacturesValideurRechercherPostWithHttpInfo(requestBody);
        return localVarResp.getData();
    }

    /**
     * Rechercher factures à valider (Valideur)
     * Recherche les factures en attente de validation par le valideur connecté.      **Rôle** : Valideur dans le circuit de validation interne.      **Filtres** : Dates, structure, service, etc.
     * @param requestBody  (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> valideurRechercherFacturesApiV1ChorusProFacturesValideurRechercherPostWithHttpInfo(@javax.annotation.Nonnull Map<String, Object> requestBody) throws ApiException {
        okhttp3.Call localVarCall = valideurRechercherFacturesApiV1ChorusProFacturesValideurRechercherPostValidateBeforeCall(requestBody, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Rechercher factures à valider (Valideur) (asynchronously)
     * Recherche les factures en attente de validation par le valideur connecté.      **Rôle** : Valideur dans le circuit de validation interne.      **Filtres** : Dates, structure, service, etc.
     * @param requestBody  (required)
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
    public okhttp3.Call valideurRechercherFacturesApiV1ChorusProFacturesValideurRechercherPostAsync(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = valideurRechercherFacturesApiV1ChorusProFacturesValideurRechercherPostValidateBeforeCall(requestBody, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for valideurTraiterFactureApiV1ChorusProFacturesValideurTraiterPost
     * @param requestBody  (required)
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
    public okhttp3.Call valideurTraiterFactureApiV1ChorusProFacturesValideurTraiterPostCall(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback _callback) throws ApiException {
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

        Object localVarPostBody = requestBody;

        // create path and map variables
        String localVarPath = "/api/v1/chorus-pro/factures/valideur/traiter";

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
    private okhttp3.Call valideurTraiterFactureApiV1ChorusProFacturesValideurTraiterPostValidateBeforeCall(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'requestBody' is set
        if (requestBody == null) {
            throw new ApiException("Missing the required parameter 'requestBody' when calling valideurTraiterFactureApiV1ChorusProFacturesValideurTraiterPost(Async)");
        }

        return valideurTraiterFactureApiV1ChorusProFacturesValideurTraiterPostCall(requestBody, _callback);

    }

    /**
     * Valider ou refuser une facture (Valideur)
     * Valide ou refuse une facture en attente de validation.      **Actions** :     - Valider : La facture passe au statut suivant du circuit     - Refuser : La facture est rejetée (motif obligatoire)
     * @param requestBody  (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public Object valideurTraiterFactureApiV1ChorusProFacturesValideurTraiterPost(@javax.annotation.Nonnull Map<String, Object> requestBody) throws ApiException {
        ApiResponse<Object> localVarResp = valideurTraiterFactureApiV1ChorusProFacturesValideurTraiterPostWithHttpInfo(requestBody);
        return localVarResp.getData();
    }

    /**
     * Valider ou refuser une facture (Valideur)
     * Valide ou refuse une facture en attente de validation.      **Actions** :     - Valider : La facture passe au statut suivant du circuit     - Refuser : La facture est rejetée (motif obligatoire)
     * @param requestBody  (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successful Response </td><td>  -  </td></tr>
        <tr><td> 422 </td><td> Validation Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Object> valideurTraiterFactureApiV1ChorusProFacturesValideurTraiterPostWithHttpInfo(@javax.annotation.Nonnull Map<String, Object> requestBody) throws ApiException {
        okhttp3.Call localVarCall = valideurTraiterFactureApiV1ChorusProFacturesValideurTraiterPostValidateBeforeCall(requestBody, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Valider ou refuser une facture (Valideur) (asynchronously)
     * Valide ou refuse une facture en attente de validation.      **Actions** :     - Valider : La facture passe au statut suivant du circuit     - Refuser : La facture est rejetée (motif obligatoire)
     * @param requestBody  (required)
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
    public okhttp3.Call valideurTraiterFactureApiV1ChorusProFacturesValideurTraiterPostAsync(@javax.annotation.Nonnull Map<String, Object> requestBody, final ApiCallback<Object> _callback) throws ApiException {

        okhttp3.Call localVarCall = valideurTraiterFactureApiV1ChorusProFacturesValideurTraiterPostValidateBeforeCall(requestBody, _callback);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
