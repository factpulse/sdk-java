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
 * Résultat de la soumission à AFNOR PDP.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-11-27T20:34:04.834638695Z[Etc/UTC]", comments = "Generator version: 7.18.0-SNAPSHOT")
public class ResultatAFNOR {
  public static final String SERIALIZED_NAME_FLOW_ID = "flow_id";
  @SerializedName(SERIALIZED_NAME_FLOW_ID)
  @javax.annotation.Nonnull
  private String flowId;

  public static final String SERIALIZED_NAME_TRACKING_ID = "tracking_id";
  @SerializedName(SERIALIZED_NAME_TRACKING_ID)
  @javax.annotation.Nullable
  private String trackingId;

  public static final String SERIALIZED_NAME_SHA256 = "sha256";
  @SerializedName(SERIALIZED_NAME_SHA256)
  @javax.annotation.Nonnull
  private String sha256;

  public static final String SERIALIZED_NAME_FLOW_SYNTAX = "flow_syntax";
  @SerializedName(SERIALIZED_NAME_FLOW_SYNTAX)
  @javax.annotation.Nonnull
  private String flowSyntax;

  public static final String SERIALIZED_NAME_FLOW_PROFILE = "flow_profile";
  @SerializedName(SERIALIZED_NAME_FLOW_PROFILE)
  @javax.annotation.Nullable
  private String flowProfile;

  public ResultatAFNOR() {
  }

  public ResultatAFNOR flowId(@javax.annotation.Nonnull String flowId) {
    this.flowId = flowId;
    return this;
  }

  /**
   * Identifiant du flux soumis
   * @return flowId
   */
  @javax.annotation.Nonnull
  public String getFlowId() {
    return flowId;
  }

  public void setFlowId(@javax.annotation.Nonnull String flowId) {
    this.flowId = flowId;
  }


  public ResultatAFNOR trackingId(@javax.annotation.Nullable String trackingId) {
    this.trackingId = trackingId;
    return this;
  }

  /**
   * Get trackingId
   * @return trackingId
   */
  @javax.annotation.Nullable
  public String getTrackingId() {
    return trackingId;
  }

  public void setTrackingId(@javax.annotation.Nullable String trackingId) {
    this.trackingId = trackingId;
  }


  public ResultatAFNOR sha256(@javax.annotation.Nonnull String sha256) {
    this.sha256 = sha256;
    return this;
  }

  /**
   * Hash SHA-256 du fichier soumis
   * @return sha256
   */
  @javax.annotation.Nonnull
  public String getSha256() {
    return sha256;
  }

  public void setSha256(@javax.annotation.Nonnull String sha256) {
    this.sha256 = sha256;
  }


  public ResultatAFNOR flowSyntax(@javax.annotation.Nonnull String flowSyntax) {
    this.flowSyntax = flowSyntax;
    return this;
  }

  /**
   * Syntaxe du flux
   * @return flowSyntax
   */
  @javax.annotation.Nonnull
  public String getFlowSyntax() {
    return flowSyntax;
  }

  public void setFlowSyntax(@javax.annotation.Nonnull String flowSyntax) {
    this.flowSyntax = flowSyntax;
  }


  public ResultatAFNOR flowProfile(@javax.annotation.Nullable String flowProfile) {
    this.flowProfile = flowProfile;
    return this;
  }

  /**
   * Get flowProfile
   * @return flowProfile
   */
  @javax.annotation.Nullable
  public String getFlowProfile() {
    return flowProfile;
  }

  public void setFlowProfile(@javax.annotation.Nullable String flowProfile) {
    this.flowProfile = flowProfile;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResultatAFNOR resultatAFNOR = (ResultatAFNOR) o;
    return Objects.equals(this.flowId, resultatAFNOR.flowId) &&
        Objects.equals(this.trackingId, resultatAFNOR.trackingId) &&
        Objects.equals(this.sha256, resultatAFNOR.sha256) &&
        Objects.equals(this.flowSyntax, resultatAFNOR.flowSyntax) &&
        Objects.equals(this.flowProfile, resultatAFNOR.flowProfile);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(flowId, trackingId, sha256, flowSyntax, flowProfile);
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
    sb.append("class ResultatAFNOR {\n");
    sb.append("    flowId: ").append(toIndentedString(flowId)).append("\n");
    sb.append("    trackingId: ").append(toIndentedString(trackingId)).append("\n");
    sb.append("    sha256: ").append(toIndentedString(sha256)).append("\n");
    sb.append("    flowSyntax: ").append(toIndentedString(flowSyntax)).append("\n");
    sb.append("    flowProfile: ").append(toIndentedString(flowProfile)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("flow_id", "tracking_id", "sha256", "flow_syntax", "flow_profile"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(Arrays.asList("flow_id", "sha256", "flow_syntax"));
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to ResultatAFNOR
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ResultatAFNOR.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field(s) %s in ResultatAFNOR is not found in the empty JSON string", ResultatAFNOR.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!ResultatAFNOR.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The field `%s` in the JSON string is not defined in the `ResultatAFNOR` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : ResultatAFNOR.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (!jsonObj.get("flow_id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `flow_id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("flow_id").toString()));
      }
      if ((jsonObj.get("tracking_id") != null && !jsonObj.get("tracking_id").isJsonNull()) && !jsonObj.get("tracking_id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `tracking_id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("tracking_id").toString()));
      }
      if (!jsonObj.get("sha256").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `sha256` to be a primitive type in the JSON string but got `%s`", jsonObj.get("sha256").toString()));
      }
      if (!jsonObj.get("flow_syntax").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `flow_syntax` to be a primitive type in the JSON string but got `%s`", jsonObj.get("flow_syntax").toString()));
      }
      if ((jsonObj.get("flow_profile") != null && !jsonObj.get("flow_profile").isJsonNull()) && !jsonObj.get("flow_profile").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `flow_profile` to be a primitive type in the JSON string but got `%s`", jsonObj.get("flow_profile").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ResultatAFNOR.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ResultatAFNOR' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ResultatAFNOR> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ResultatAFNOR.class));

       return (TypeAdapter<T>) new TypeAdapter<ResultatAFNOR>() {
           @Override
           public void write(JsonWriter out, ResultatAFNOR value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public ResultatAFNOR read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of ResultatAFNOR given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of ResultatAFNOR
   * @throws IOException if the JSON string is invalid with respect to ResultatAFNOR
   */
  public static ResultatAFNOR fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ResultatAFNOR.class);
  }

  /**
   * Convert an instance of ResultatAFNOR to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

