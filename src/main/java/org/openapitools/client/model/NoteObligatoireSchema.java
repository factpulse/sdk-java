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
import org.openapitools.client.model.BoundingBoxSchema;
import org.openapitools.client.model.StatutChampAPI;
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
 * Note obligatoire détectée avec localisation et comparaison XML/PDF.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-12-10T09:35:31.529791871Z[Etc/UTC]", comments = "Generator version: 7.18.0-SNAPSHOT")
public class NoteObligatoireSchema {
  public static final String SERIALIZED_NAME_CODE_SUJET = "code_sujet";
  @SerializedName(SERIALIZED_NAME_CODE_SUJET)
  @javax.annotation.Nonnull
  private String codeSujet;

  public static final String SERIALIZED_NAME_LABEL = "label";
  @SerializedName(SERIALIZED_NAME_LABEL)
  @javax.annotation.Nonnull
  private String label;

  public static final String SERIALIZED_NAME_VALEUR_PDF = "valeur_pdf";
  @SerializedName(SERIALIZED_NAME_VALEUR_PDF)
  @javax.annotation.Nullable
  private String valeurPdf;

  public static final String SERIALIZED_NAME_VALEUR_XML = "valeur_xml";
  @SerializedName(SERIALIZED_NAME_VALEUR_XML)
  @javax.annotation.Nullable
  private String valeurXml;

  public static final String SERIALIZED_NAME_STATUT = "statut";
  @SerializedName(SERIALIZED_NAME_STATUT)
  @javax.annotation.Nullable
  private StatutChampAPI statut = StatutChampAPI.NON_VERIFIE;

  public static final String SERIALIZED_NAME_MESSAGE = "message";
  @SerializedName(SERIALIZED_NAME_MESSAGE)
  @javax.annotation.Nullable
  private String message;

  public static final String SERIALIZED_NAME_BBOX = "bbox";
  @SerializedName(SERIALIZED_NAME_BBOX)
  @javax.annotation.Nullable
  private BoundingBoxSchema bbox;

  public NoteObligatoireSchema() {
  }

  public NoteObligatoireSchema codeSujet(@javax.annotation.Nonnull String codeSujet) {
    this.codeSujet = codeSujet;
    return this;
  }

  /**
   * Code sujet (PMT, PMD, AAB)
   * @return codeSujet
   */
  @javax.annotation.Nonnull
  public String getCodeSujet() {
    return codeSujet;
  }

  public void setCodeSujet(@javax.annotation.Nonnull String codeSujet) {
    this.codeSujet = codeSujet;
  }


  public NoteObligatoireSchema label(@javax.annotation.Nonnull String label) {
    this.label = label;
    return this;
  }

  /**
   * Libellé (ex: Indemnité recouvrement)
   * @return label
   */
  @javax.annotation.Nonnull
  public String getLabel() {
    return label;
  }

  public void setLabel(@javax.annotation.Nonnull String label) {
    this.label = label;
  }


  public NoteObligatoireSchema valeurPdf(@javax.annotation.Nullable String valeurPdf) {
    this.valeurPdf = valeurPdf;
    return this;
  }

  /**
   * Get valeurPdf
   * @return valeurPdf
   */
  @javax.annotation.Nullable
  public String getValeurPdf() {
    return valeurPdf;
  }

  public void setValeurPdf(@javax.annotation.Nullable String valeurPdf) {
    this.valeurPdf = valeurPdf;
  }


  public NoteObligatoireSchema valeurXml(@javax.annotation.Nullable String valeurXml) {
    this.valeurXml = valeurXml;
    return this;
  }

  /**
   * Get valeurXml
   * @return valeurXml
   */
  @javax.annotation.Nullable
  public String getValeurXml() {
    return valeurXml;
  }

  public void setValeurXml(@javax.annotation.Nullable String valeurXml) {
    this.valeurXml = valeurXml;
  }


  public NoteObligatoireSchema statut(@javax.annotation.Nullable StatutChampAPI statut) {
    this.statut = statut;
    return this;
  }

  /**
   * Statut de conformité (CONFORME si XML trouvé dans PDF)
   * @return statut
   */
  @javax.annotation.Nullable
  public StatutChampAPI getStatut() {
    return statut;
  }

  public void setStatut(@javax.annotation.Nullable StatutChampAPI statut) {
    this.statut = statut;
  }


  public NoteObligatoireSchema message(@javax.annotation.Nullable String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   */
  @javax.annotation.Nullable
  public String getMessage() {
    return message;
  }

  public void setMessage(@javax.annotation.Nullable String message) {
    this.message = message;
  }


  public NoteObligatoireSchema bbox(@javax.annotation.Nullable BoundingBoxSchema bbox) {
    this.bbox = bbox;
    return this;
  }

  /**
   * Get bbox
   * @return bbox
   */
  @javax.annotation.Nullable
  public BoundingBoxSchema getBbox() {
    return bbox;
  }

  public void setBbox(@javax.annotation.Nullable BoundingBoxSchema bbox) {
    this.bbox = bbox;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NoteObligatoireSchema noteObligatoireSchema = (NoteObligatoireSchema) o;
    return Objects.equals(this.codeSujet, noteObligatoireSchema.codeSujet) &&
        Objects.equals(this.label, noteObligatoireSchema.label) &&
        Objects.equals(this.valeurPdf, noteObligatoireSchema.valeurPdf) &&
        Objects.equals(this.valeurXml, noteObligatoireSchema.valeurXml) &&
        Objects.equals(this.statut, noteObligatoireSchema.statut) &&
        Objects.equals(this.message, noteObligatoireSchema.message) &&
        Objects.equals(this.bbox, noteObligatoireSchema.bbox);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(codeSujet, label, valeurPdf, valeurXml, statut, message, bbox);
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
    sb.append("class NoteObligatoireSchema {\n");
    sb.append("    codeSujet: ").append(toIndentedString(codeSujet)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    valeurPdf: ").append(toIndentedString(valeurPdf)).append("\n");
    sb.append("    valeurXml: ").append(toIndentedString(valeurXml)).append("\n");
    sb.append("    statut: ").append(toIndentedString(statut)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    bbox: ").append(toIndentedString(bbox)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("code_sujet", "label", "valeur_pdf", "valeur_xml", "statut", "message", "bbox"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(Arrays.asList("code_sujet", "label"));
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to NoteObligatoireSchema
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!NoteObligatoireSchema.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field(s) %s in NoteObligatoireSchema is not found in the empty JSON string", NoteObligatoireSchema.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!NoteObligatoireSchema.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The field `%s` in the JSON string is not defined in the `NoteObligatoireSchema` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : NoteObligatoireSchema.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (!jsonObj.get("code_sujet").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `code_sujet` to be a primitive type in the JSON string but got `%s`", jsonObj.get("code_sujet").toString()));
      }
      if (!jsonObj.get("label").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `label` to be a primitive type in the JSON string but got `%s`", jsonObj.get("label").toString()));
      }
      if ((jsonObj.get("valeur_pdf") != null && !jsonObj.get("valeur_pdf").isJsonNull()) && !jsonObj.get("valeur_pdf").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `valeur_pdf` to be a primitive type in the JSON string but got `%s`", jsonObj.get("valeur_pdf").toString()));
      }
      if ((jsonObj.get("valeur_xml") != null && !jsonObj.get("valeur_xml").isJsonNull()) && !jsonObj.get("valeur_xml").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `valeur_xml` to be a primitive type in the JSON string but got `%s`", jsonObj.get("valeur_xml").toString()));
      }
      // validate the optional field `statut`
      if (jsonObj.get("statut") != null && !jsonObj.get("statut").isJsonNull()) {
        StatutChampAPI.validateJsonElement(jsonObj.get("statut"));
      }
      if ((jsonObj.get("message") != null && !jsonObj.get("message").isJsonNull()) && !jsonObj.get("message").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `message` to be a primitive type in the JSON string but got `%s`", jsonObj.get("message").toString()));
      }
      // validate the optional field `bbox`
      if (jsonObj.get("bbox") != null && !jsonObj.get("bbox").isJsonNull()) {
        BoundingBoxSchema.validateJsonElement(jsonObj.get("bbox"));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!NoteObligatoireSchema.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'NoteObligatoireSchema' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<NoteObligatoireSchema> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(NoteObligatoireSchema.class));

       return (TypeAdapter<T>) new TypeAdapter<NoteObligatoireSchema>() {
           @Override
           public void write(JsonWriter out, NoteObligatoireSchema value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public NoteObligatoireSchema read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of NoteObligatoireSchema given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of NoteObligatoireSchema
   * @throws IOException if the JSON string is invalid with respect to NoteObligatoireSchema
   */
  public static NoteObligatoireSchema fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, NoteObligatoireSchema.class);
  }

  /**
   * Convert an instance of NoteObligatoireSchema to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

