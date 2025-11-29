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


package org.openapitools.client.model;

import java.util.Objects;
import java.util.Locale;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Arrays;
import org.openapitools.client.model.ChorusProCredentials;
import org.openapitools.client.model.MontantHtTotal;
import org.openapitools.client.model.MontantTtcTotal;
import org.openapitools.client.model.MontantTva;
import org.openapitools.jackson.nullable.JsonNullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Locale;

import org.openapitools.client.JSON;

/**
 * Soumission d&#39;une facture Chorus Pro.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-11-29T10:45:22.448682716Z[Etc/UTC]", comments = "Generator version: 7.18.0-SNAPSHOT")
public class SoumettreFactureRequest {
  public static final String SERIALIZED_NAME_CREDENTIALS = "credentials";
  @SerializedName(SERIALIZED_NAME_CREDENTIALS)
  @javax.annotation.Nullable
  private ChorusProCredentials credentials;

  public static final String SERIALIZED_NAME_NUMERO_FACTURE = "numero_facture";
  @SerializedName(SERIALIZED_NAME_NUMERO_FACTURE)
  @javax.annotation.Nonnull
  private String numeroFacture;

  public static final String SERIALIZED_NAME_DATE_FACTURE = "date_facture";
  @SerializedName(SERIALIZED_NAME_DATE_FACTURE)
  @javax.annotation.Nonnull
  private String dateFacture;

  public static final String SERIALIZED_NAME_DATE_ECHEANCE_PAIEMENT = "date_echeance_paiement";
  @SerializedName(SERIALIZED_NAME_DATE_ECHEANCE_PAIEMENT)
  @javax.annotation.Nullable
  private String dateEcheancePaiement;

  public static final String SERIALIZED_NAME_ID_STRUCTURE_CPP = "id_structure_cpp";
  @SerializedName(SERIALIZED_NAME_ID_STRUCTURE_CPP)
  @javax.annotation.Nonnull
  private Integer idStructureCpp;

  public static final String SERIALIZED_NAME_CODE_SERVICE = "code_service";
  @SerializedName(SERIALIZED_NAME_CODE_SERVICE)
  @javax.annotation.Nullable
  private String codeService;

  public static final String SERIALIZED_NAME_NUMERO_ENGAGEMENT = "numero_engagement";
  @SerializedName(SERIALIZED_NAME_NUMERO_ENGAGEMENT)
  @javax.annotation.Nullable
  private String numeroEngagement;

  public static final String SERIALIZED_NAME_MONTANT_HT_TOTAL = "montant_ht_total";
  @SerializedName(SERIALIZED_NAME_MONTANT_HT_TOTAL)
  @javax.annotation.Nonnull
  private MontantHtTotal montantHtTotal;

  public static final String SERIALIZED_NAME_MONTANT_TVA = "montant_tva";
  @SerializedName(SERIALIZED_NAME_MONTANT_TVA)
  @javax.annotation.Nonnull
  private MontantTva montantTva;

  public static final String SERIALIZED_NAME_MONTANT_TTC_TOTAL = "montant_ttc_total";
  @SerializedName(SERIALIZED_NAME_MONTANT_TTC_TOTAL)
  @javax.annotation.Nonnull
  private MontantTtcTotal montantTtcTotal;

  public static final String SERIALIZED_NAME_PIECE_JOINTE_PRINCIPALE_ID = "piece_jointe_principale_id";
  @SerializedName(SERIALIZED_NAME_PIECE_JOINTE_PRINCIPALE_ID)
  @javax.annotation.Nullable
  private Integer pieceJointePrincipaleId;

  public static final String SERIALIZED_NAME_PIECE_JOINTE_PRINCIPALE_DESIGNATION = "piece_jointe_principale_designation";
  @SerializedName(SERIALIZED_NAME_PIECE_JOINTE_PRINCIPALE_DESIGNATION)
  @javax.annotation.Nullable
  private String pieceJointePrincipaleDesignation;

  public static final String SERIALIZED_NAME_COMMENTAIRE = "commentaire";
  @SerializedName(SERIALIZED_NAME_COMMENTAIRE)
  @javax.annotation.Nullable
  private String commentaire;

  public static final String SERIALIZED_NAME_NUMERO_BON_COMMANDE = "numero_bon_commande";
  @SerializedName(SERIALIZED_NAME_NUMERO_BON_COMMANDE)
  @javax.annotation.Nullable
  private String numeroBonCommande;

  public static final String SERIALIZED_NAME_NUMERO_MARCHE = "numero_marche";
  @SerializedName(SERIALIZED_NAME_NUMERO_MARCHE)
  @javax.annotation.Nullable
  private String numeroMarche;

  public SoumettreFactureRequest() {
  }

  public SoumettreFactureRequest credentials(@javax.annotation.Nullable ChorusProCredentials credentials) {
    this.credentials = credentials;
    return this;
  }

  /**
   * Get credentials
   * @return credentials
   */
  @javax.annotation.Nullable
  public ChorusProCredentials getCredentials() {
    return credentials;
  }

  public void setCredentials(@javax.annotation.Nullable ChorusProCredentials credentials) {
    this.credentials = credentials;
  }


  public SoumettreFactureRequest numeroFacture(@javax.annotation.Nonnull String numeroFacture) {
    this.numeroFacture = numeroFacture;
    return this;
  }

  /**
   * Numéro de la facture
   * @return numeroFacture
   */
  @javax.annotation.Nonnull
  public String getNumeroFacture() {
    return numeroFacture;
  }

  public void setNumeroFacture(@javax.annotation.Nonnull String numeroFacture) {
    this.numeroFacture = numeroFacture;
  }


  public SoumettreFactureRequest dateFacture(@javax.annotation.Nonnull String dateFacture) {
    this.dateFacture = dateFacture;
    return this;
  }

  /**
   * Date de facture (format ISO: YYYY-MM-DD)
   * @return dateFacture
   */
  @javax.annotation.Nonnull
  public String getDateFacture() {
    return dateFacture;
  }

  public void setDateFacture(@javax.annotation.Nonnull String dateFacture) {
    this.dateFacture = dateFacture;
  }


  public SoumettreFactureRequest dateEcheancePaiement(@javax.annotation.Nullable String dateEcheancePaiement) {
    this.dateEcheancePaiement = dateEcheancePaiement;
    return this;
  }

  /**
   * Get dateEcheancePaiement
   * @return dateEcheancePaiement
   */
  @javax.annotation.Nullable
  public String getDateEcheancePaiement() {
    return dateEcheancePaiement;
  }

  public void setDateEcheancePaiement(@javax.annotation.Nullable String dateEcheancePaiement) {
    this.dateEcheancePaiement = dateEcheancePaiement;
  }


  public SoumettreFactureRequest idStructureCpp(@javax.annotation.Nonnull Integer idStructureCpp) {
    this.idStructureCpp = idStructureCpp;
    return this;
  }

  /**
   * ID Chorus Pro de la structure destinataire
   * @return idStructureCpp
   */
  @javax.annotation.Nonnull
  public Integer getIdStructureCpp() {
    return idStructureCpp;
  }

  public void setIdStructureCpp(@javax.annotation.Nonnull Integer idStructureCpp) {
    this.idStructureCpp = idStructureCpp;
  }


  public SoumettreFactureRequest codeService(@javax.annotation.Nullable String codeService) {
    this.codeService = codeService;
    return this;
  }

  /**
   * Get codeService
   * @return codeService
   */
  @javax.annotation.Nullable
  public String getCodeService() {
    return codeService;
  }

  public void setCodeService(@javax.annotation.Nullable String codeService) {
    this.codeService = codeService;
  }


  public SoumettreFactureRequest numeroEngagement(@javax.annotation.Nullable String numeroEngagement) {
    this.numeroEngagement = numeroEngagement;
    return this;
  }

  /**
   * Get numeroEngagement
   * @return numeroEngagement
   */
  @javax.annotation.Nullable
  public String getNumeroEngagement() {
    return numeroEngagement;
  }

  public void setNumeroEngagement(@javax.annotation.Nullable String numeroEngagement) {
    this.numeroEngagement = numeroEngagement;
  }


  public SoumettreFactureRequest montantHtTotal(@javax.annotation.Nonnull MontantHtTotal montantHtTotal) {
    this.montantHtTotal = montantHtTotal;
    return this;
  }

  /**
   * Get montantHtTotal
   * @return montantHtTotal
   */
  @javax.annotation.Nonnull
  public MontantHtTotal getMontantHtTotal() {
    return montantHtTotal;
  }

  public void setMontantHtTotal(@javax.annotation.Nonnull MontantHtTotal montantHtTotal) {
    this.montantHtTotal = montantHtTotal;
  }


  public SoumettreFactureRequest montantTva(@javax.annotation.Nonnull MontantTva montantTva) {
    this.montantTva = montantTva;
    return this;
  }

  /**
   * Get montantTva
   * @return montantTva
   */
  @javax.annotation.Nonnull
  public MontantTva getMontantTva() {
    return montantTva;
  }

  public void setMontantTva(@javax.annotation.Nonnull MontantTva montantTva) {
    this.montantTva = montantTva;
  }


  public SoumettreFactureRequest montantTtcTotal(@javax.annotation.Nonnull MontantTtcTotal montantTtcTotal) {
    this.montantTtcTotal = montantTtcTotal;
    return this;
  }

  /**
   * Get montantTtcTotal
   * @return montantTtcTotal
   */
  @javax.annotation.Nonnull
  public MontantTtcTotal getMontantTtcTotal() {
    return montantTtcTotal;
  }

  public void setMontantTtcTotal(@javax.annotation.Nonnull MontantTtcTotal montantTtcTotal) {
    this.montantTtcTotal = montantTtcTotal;
  }


  public SoumettreFactureRequest pieceJointePrincipaleId(@javax.annotation.Nullable Integer pieceJointePrincipaleId) {
    this.pieceJointePrincipaleId = pieceJointePrincipaleId;
    return this;
  }

  /**
   * Get pieceJointePrincipaleId
   * @return pieceJointePrincipaleId
   */
  @javax.annotation.Nullable
  public Integer getPieceJointePrincipaleId() {
    return pieceJointePrincipaleId;
  }

  public void setPieceJointePrincipaleId(@javax.annotation.Nullable Integer pieceJointePrincipaleId) {
    this.pieceJointePrincipaleId = pieceJointePrincipaleId;
  }


  public SoumettreFactureRequest pieceJointePrincipaleDesignation(@javax.annotation.Nullable String pieceJointePrincipaleDesignation) {
    this.pieceJointePrincipaleDesignation = pieceJointePrincipaleDesignation;
    return this;
  }

  /**
   * Get pieceJointePrincipaleDesignation
   * @return pieceJointePrincipaleDesignation
   */
  @javax.annotation.Nullable
  public String getPieceJointePrincipaleDesignation() {
    return pieceJointePrincipaleDesignation;
  }

  public void setPieceJointePrincipaleDesignation(@javax.annotation.Nullable String pieceJointePrincipaleDesignation) {
    this.pieceJointePrincipaleDesignation = pieceJointePrincipaleDesignation;
  }


  public SoumettreFactureRequest commentaire(@javax.annotation.Nullable String commentaire) {
    this.commentaire = commentaire;
    return this;
  }

  /**
   * Get commentaire
   * @return commentaire
   */
  @javax.annotation.Nullable
  public String getCommentaire() {
    return commentaire;
  }

  public void setCommentaire(@javax.annotation.Nullable String commentaire) {
    this.commentaire = commentaire;
  }


  public SoumettreFactureRequest numeroBonCommande(@javax.annotation.Nullable String numeroBonCommande) {
    this.numeroBonCommande = numeroBonCommande;
    return this;
  }

  /**
   * Get numeroBonCommande
   * @return numeroBonCommande
   */
  @javax.annotation.Nullable
  public String getNumeroBonCommande() {
    return numeroBonCommande;
  }

  public void setNumeroBonCommande(@javax.annotation.Nullable String numeroBonCommande) {
    this.numeroBonCommande = numeroBonCommande;
  }


  public SoumettreFactureRequest numeroMarche(@javax.annotation.Nullable String numeroMarche) {
    this.numeroMarche = numeroMarche;
    return this;
  }

  /**
   * Get numeroMarche
   * @return numeroMarche
   */
  @javax.annotation.Nullable
  public String getNumeroMarche() {
    return numeroMarche;
  }

  public void setNumeroMarche(@javax.annotation.Nullable String numeroMarche) {
    this.numeroMarche = numeroMarche;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SoumettreFactureRequest soumettreFactureRequest = (SoumettreFactureRequest) o;
    return Objects.equals(this.credentials, soumettreFactureRequest.credentials) &&
        Objects.equals(this.numeroFacture, soumettreFactureRequest.numeroFacture) &&
        Objects.equals(this.dateFacture, soumettreFactureRequest.dateFacture) &&
        Objects.equals(this.dateEcheancePaiement, soumettreFactureRequest.dateEcheancePaiement) &&
        Objects.equals(this.idStructureCpp, soumettreFactureRequest.idStructureCpp) &&
        Objects.equals(this.codeService, soumettreFactureRequest.codeService) &&
        Objects.equals(this.numeroEngagement, soumettreFactureRequest.numeroEngagement) &&
        Objects.equals(this.montantHtTotal, soumettreFactureRequest.montantHtTotal) &&
        Objects.equals(this.montantTva, soumettreFactureRequest.montantTva) &&
        Objects.equals(this.montantTtcTotal, soumettreFactureRequest.montantTtcTotal) &&
        Objects.equals(this.pieceJointePrincipaleId, soumettreFactureRequest.pieceJointePrincipaleId) &&
        Objects.equals(this.pieceJointePrincipaleDesignation, soumettreFactureRequest.pieceJointePrincipaleDesignation) &&
        Objects.equals(this.commentaire, soumettreFactureRequest.commentaire) &&
        Objects.equals(this.numeroBonCommande, soumettreFactureRequest.numeroBonCommande) &&
        Objects.equals(this.numeroMarche, soumettreFactureRequest.numeroMarche);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(credentials, numeroFacture, dateFacture, dateEcheancePaiement, idStructureCpp, codeService, numeroEngagement, montantHtTotal, montantTva, montantTtcTotal, pieceJointePrincipaleId, pieceJointePrincipaleDesignation, commentaire, numeroBonCommande, numeroMarche);
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SoumettreFactureRequest {\n");
    sb.append("    credentials: ").append(toIndentedString(credentials)).append("\n");
    sb.append("    numeroFacture: ").append(toIndentedString(numeroFacture)).append("\n");
    sb.append("    dateFacture: ").append(toIndentedString(dateFacture)).append("\n");
    sb.append("    dateEcheancePaiement: ").append(toIndentedString(dateEcheancePaiement)).append("\n");
    sb.append("    idStructureCpp: ").append(toIndentedString(idStructureCpp)).append("\n");
    sb.append("    codeService: ").append(toIndentedString(codeService)).append("\n");
    sb.append("    numeroEngagement: ").append(toIndentedString(numeroEngagement)).append("\n");
    sb.append("    montantHtTotal: ").append(toIndentedString(montantHtTotal)).append("\n");
    sb.append("    montantTva: ").append(toIndentedString(montantTva)).append("\n");
    sb.append("    montantTtcTotal: ").append(toIndentedString(montantTtcTotal)).append("\n");
    sb.append("    pieceJointePrincipaleId: ").append(toIndentedString(pieceJointePrincipaleId)).append("\n");
    sb.append("    pieceJointePrincipaleDesignation: ").append(toIndentedString(pieceJointePrincipaleDesignation)).append("\n");
    sb.append("    commentaire: ").append(toIndentedString(commentaire)).append("\n");
    sb.append("    numeroBonCommande: ").append(toIndentedString(numeroBonCommande)).append("\n");
    sb.append("    numeroMarche: ").append(toIndentedString(numeroMarche)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>(Arrays.asList("credentials", "numero_facture", "date_facture", "date_echeance_paiement", "id_structure_cpp", "code_service", "numero_engagement", "montant_ht_total", "montant_tva", "montant_ttc_total", "piece_jointe_principale_id", "piece_jointe_principale_designation", "commentaire", "numero_bon_commande", "numero_marche"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(Arrays.asList("numero_facture", "date_facture", "id_structure_cpp", "montant_ht_total", "montant_tva", "montant_ttc_total"));
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to SoumettreFactureRequest
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!SoumettreFactureRequest.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field(s) %s in SoumettreFactureRequest is not found in the empty JSON string", SoumettreFactureRequest.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!SoumettreFactureRequest.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The field `%s` in the JSON string is not defined in the `SoumettreFactureRequest` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : SoumettreFactureRequest.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      // validate the optional field `credentials`
      if (jsonObj.get("credentials") != null && !jsonObj.get("credentials").isJsonNull()) {
        ChorusProCredentials.validateJsonElement(jsonObj.get("credentials"));
      }
      if (!jsonObj.get("numero_facture").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `numero_facture` to be a primitive type in the JSON string but got `%s`", jsonObj.get("numero_facture").toString()));
      }
      if (!jsonObj.get("date_facture").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `date_facture` to be a primitive type in the JSON string but got `%s`", jsonObj.get("date_facture").toString()));
      }
      if ((jsonObj.get("date_echeance_paiement") != null && !jsonObj.get("date_echeance_paiement").isJsonNull()) && !jsonObj.get("date_echeance_paiement").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `date_echeance_paiement` to be a primitive type in the JSON string but got `%s`", jsonObj.get("date_echeance_paiement").toString()));
      }
      if ((jsonObj.get("code_service") != null && !jsonObj.get("code_service").isJsonNull()) && !jsonObj.get("code_service").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `code_service` to be a primitive type in the JSON string but got `%s`", jsonObj.get("code_service").toString()));
      }
      if ((jsonObj.get("numero_engagement") != null && !jsonObj.get("numero_engagement").isJsonNull()) && !jsonObj.get("numero_engagement").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `numero_engagement` to be a primitive type in the JSON string but got `%s`", jsonObj.get("numero_engagement").toString()));
      }
      // validate the required field `montant_ht_total`
      MontantHtTotal.validateJsonElement(jsonObj.get("montant_ht_total"));
      // validate the required field `montant_tva`
      MontantTva.validateJsonElement(jsonObj.get("montant_tva"));
      // validate the required field `montant_ttc_total`
      MontantTtcTotal.validateJsonElement(jsonObj.get("montant_ttc_total"));
      if ((jsonObj.get("piece_jointe_principale_designation") != null && !jsonObj.get("piece_jointe_principale_designation").isJsonNull()) && !jsonObj.get("piece_jointe_principale_designation").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `piece_jointe_principale_designation` to be a primitive type in the JSON string but got `%s`", jsonObj.get("piece_jointe_principale_designation").toString()));
      }
      if ((jsonObj.get("commentaire") != null && !jsonObj.get("commentaire").isJsonNull()) && !jsonObj.get("commentaire").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `commentaire` to be a primitive type in the JSON string but got `%s`", jsonObj.get("commentaire").toString()));
      }
      if ((jsonObj.get("numero_bon_commande") != null && !jsonObj.get("numero_bon_commande").isJsonNull()) && !jsonObj.get("numero_bon_commande").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `numero_bon_commande` to be a primitive type in the JSON string but got `%s`", jsonObj.get("numero_bon_commande").toString()));
      }
      if ((jsonObj.get("numero_marche") != null && !jsonObj.get("numero_marche").isJsonNull()) && !jsonObj.get("numero_marche").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `numero_marche` to be a primitive type in the JSON string but got `%s`", jsonObj.get("numero_marche").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!SoumettreFactureRequest.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'SoumettreFactureRequest' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<SoumettreFactureRequest> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(SoumettreFactureRequest.class));

       return (TypeAdapter<T>) new TypeAdapter<SoumettreFactureRequest>() {
           @Override
           public void write(JsonWriter out, SoumettreFactureRequest value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public SoumettreFactureRequest read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of SoumettreFactureRequest given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of SoumettreFactureRequest
   * @throws IOException if the JSON string is invalid with respect to SoumettreFactureRequest
   */
  public static SoumettreFactureRequest fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, SoumettreFactureRequest.class);
  }

  /**
   * Convert an instance of SoumettreFactureRequest to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

