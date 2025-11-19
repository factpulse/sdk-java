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

import org.openapitools.client.ApiException;
import org.openapitools.client.model.HTTPValidationError;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for AfnorPdpPaDirectoryServiceApi
 */
@Disabled
public class AfnorPdpPaDirectoryServiceApiTest {

    private final AfnorPdpPaDirectoryServiceApi api = new AfnorPdpPaDirectoryServiceApi();

    /**
     * Creating a directory line
     *
     * Créer une ligne dans l&#39;annuaire
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void createDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLinePostTest() throws ApiException {
        Object response = api.createDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLinePost();
        // TODO: test validations
    }

    /**
     * Create a routing code
     *
     * Créer un code de routage dans l&#39;annuaire
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void createRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodePostTest() throws ApiException {
        Object response = api.createRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodePost();
        // TODO: test validations
    }

    /**
     * Delete a directory line
     *
     * Supprimer une ligne d&#39;annuaire
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDeleteTest() throws ApiException {
        String idInstance = null;
        Object response = api.deleteDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceDelete(idInstance);
        // TODO: test validations
    }

    /**
     * Healthcheck Directory Service
     *
     * Vérifier la disponibilité du Directory Service
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGetTest() throws ApiException {
        Object response = api.directoryHealthcheckProxyApiV1AfnorDirectoryV1HealthcheckGet();
        // TODO: test validations
    }

    /**
     * Get a directory line
     *
     * Obtenir une ligne d&#39;annuaire identifiée par un identifiant d&#39;adressage
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGetTest() throws ApiException {
        String addressingIdentifier = null;
        Object response = api.getDirectoryLineByCodeProxyApiV1AfnorDirectoryV1DirectoryLineCodeAddressingIdentifierGet(addressingIdentifier);
        // TODO: test validations
    }

    /**
     * Get a directory line
     *
     * Obtenir une ligne d&#39;annuaire identifiée par son idInstance
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGetTest() throws ApiException {
        String idInstance = null;
        Object response = api.getDirectoryLineByIdInstanceProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstanceGet(idInstance);
        // TODO: test validations
    }

    /**
     * Get a routing code by instance-id
     *
     * Obtenir un code de routage identifié par son idInstance
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGetTest() throws ApiException {
        String idInstance = null;
        Object response = api.getRoutingCodeByIdInstanceProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstanceGet(idInstance);
        // TODO: test validations
    }

    /**
     * Get a routing code by SIRET and routing identifier
     *
     * Consulter un code de routage identifié par SIRET et identifiant de routage
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGetTest() throws ApiException {
        String siret = null;
        String routingIdentifier = null;
        Object response = api.getRoutingCodeBySiretAndCodeProxyApiV1AfnorDirectoryV1RoutingCodeSiretSiretCodeRoutingIdentifierGet(siret, routingIdentifier);
        // TODO: test validations
    }

    /**
     * Consult a siren (legal unit) by SIREN number
     *
     * Retourne les détails d&#39;une entreprise (unité légale) identifiée par son numéro SIREN
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGetTest() throws ApiException {
        String siren = null;
        Object response = api.getSirenByCodeInseeProxyApiV1AfnorDirectoryV1SirenCodeInseeSirenGet(siren);
        // TODO: test validations
    }

    /**
     * Gets a siren (legal unit) by instance ID
     *
     * Obtenir une entreprise (unité légale) identifiée par son idInstance
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGetTest() throws ApiException {
        String idInstance = null;
        Object response = api.getSirenByIdInstanceProxyApiV1AfnorDirectoryV1SirenIdInstanceIdInstanceGet(idInstance);
        // TODO: test validations
    }

    /**
     * Gets a siret (facility) by SIRET number
     *
     * Obtenir un établissement identifié par son numéro SIRET
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGetTest() throws ApiException {
        String siret = null;
        Object response = api.getSiretByCodeInseeProxyApiV1AfnorDirectoryV1SiretCodeInseeSiretGet(siret);
        // TODO: test validations
    }

    /**
     * Gets a siret (facility) by id-instance
     *
     * Obtenir un établissement identifié par son idInstance
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGetTest() throws ApiException {
        String idInstance = null;
        Object response = api.getSiretByIdInstanceProxyApiV1AfnorDirectoryV1SiretIdInstanceIdInstanceGet(idInstance);
        // TODO: test validations
    }

    /**
     * Partially updates a directory line
     *
     * Mettre à jour partiellement une ligne d&#39;annuaire
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatchTest() throws ApiException {
        String idInstance = null;
        Object response = api.patchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineIdInstanceIdInstancePatch(idInstance);
        // TODO: test validations
    }

    /**
     * Partially update a private routing code
     *
     * Mettre à jour partiellement un code de routage privé
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatchTest() throws ApiException {
        String idInstance = null;
        Object response = api.patchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePatch(idInstance);
        // TODO: test validations
    }

    /**
     * Completely update a private routing code
     *
     * Mettre à jour complètement un code de routage privé
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePutTest() throws ApiException {
        String idInstance = null;
        Object response = api.putRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeIdInstanceIdInstancePut(idInstance);
        // TODO: test validations
    }

    /**
     * Search for a directory line
     *
     * Rechercher des lignes d&#39;annuaire selon des critères
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPostTest() throws ApiException {
        Object response = api.searchDirectoryLineProxyApiV1AfnorDirectoryV1DirectoryLineSearchPost();
        // TODO: test validations
    }

    /**
     * Search for a routing code
     *
     * Rechercher des codes de routage selon des critères
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPostTest() throws ApiException {
        Object response = api.searchRoutingCodeProxyApiV1AfnorDirectoryV1RoutingCodeSearchPost();
        // TODO: test validations
    }

    /**
     * SIREN search (or legal unit)
     *
     * Recherche multi-critères d&#39;entreprises (unités légales)
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPostTest() throws ApiException {
        Object response = api.searchSirenProxyApiV1AfnorDirectoryV1SirenSearchPost();
        // TODO: test validations
    }

    /**
     * Search for a SIRET (facility)
     *
     * Recherche multi-critères d&#39;établissements
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPostTest() throws ApiException {
        Object response = api.searchSiretProxyApiV1AfnorDirectoryV1SiretSearchPost();
        // TODO: test validations
    }

}
