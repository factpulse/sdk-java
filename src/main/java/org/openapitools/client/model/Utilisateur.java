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
import org.openapitools.client.model.QuotaInfo;
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
 * Modèle Pydantic représentant les données de l&#39;utilisateur authentifié.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-11-26T13:16:03.392341018Z[Etc/UTC]", comments = "Generator version: 7.18.0-SNAPSHOT")
public class Utilisateur {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  @javax.annotation.Nonnull
  private Integer id;

  public static final String SERIALIZED_NAME_USERNAME = "username";
  @SerializedName(SERIALIZED_NAME_USERNAME)
  @javax.annotation.Nonnull
  private String username;

  public static final String SERIALIZED_NAME_EMAIL = "email";
  @SerializedName(SERIALIZED_NAME_EMAIL)
  @javax.annotation.Nonnull
  private String email;

  public static final String SERIALIZED_NAME_IS_ACTIVE = "is_active";
  @SerializedName(SERIALIZED_NAME_IS_ACTIVE)
  @javax.annotation.Nonnull
  private Boolean isActive;

  public static final String SERIALIZED_NAME_IS_SUPERUSER = "is_superuser";
  @SerializedName(SERIALIZED_NAME_IS_SUPERUSER)
  @javax.annotation.Nullable
  private Boolean isSuperuser = false;

  public static final String SERIALIZED_NAME_IS_STAFF = "is_staff";
  @SerializedName(SERIALIZED_NAME_IS_STAFF)
  @javax.annotation.Nullable
  private Boolean isStaff = false;

  public static final String SERIALIZED_NAME_BYPASS_QUOTA = "bypass_quota";
  @SerializedName(SERIALIZED_NAME_BYPASS_QUOTA)
  @javax.annotation.Nullable
  private Boolean bypassQuota = false;

  public static final String SERIALIZED_NAME_TEAM_ID = "team_id";
  @SerializedName(SERIALIZED_NAME_TEAM_ID)
  @javax.annotation.Nullable
  private Integer teamId;

  public static final String SERIALIZED_NAME_HAS_QUOTA = "has_quota";
  @SerializedName(SERIALIZED_NAME_HAS_QUOTA)
  @javax.annotation.Nullable
  private Boolean hasQuota = true;

  public static final String SERIALIZED_NAME_QUOTA_INFO = "quota_info";
  @SerializedName(SERIALIZED_NAME_QUOTA_INFO)
  @javax.annotation.Nullable
  private QuotaInfo quotaInfo;

  public static final String SERIALIZED_NAME_IS_TRIAL = "is_trial";
  @SerializedName(SERIALIZED_NAME_IS_TRIAL)
  @javax.annotation.Nullable
  private Boolean isTrial = false;

  public static final String SERIALIZED_NAME_CLIENT_UID = "client_uid";
  @SerializedName(SERIALIZED_NAME_CLIENT_UID)
  @javax.annotation.Nullable
  private String clientUid;

  public Utilisateur() {
  }

  public Utilisateur id(@javax.annotation.Nonnull Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @javax.annotation.Nonnull
  public Integer getId() {
    return id;
  }

  public void setId(@javax.annotation.Nonnull Integer id) {
    this.id = id;
  }


  public Utilisateur username(@javax.annotation.Nonnull String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
   */
  @javax.annotation.Nonnull
  public String getUsername() {
    return username;
  }

  public void setUsername(@javax.annotation.Nonnull String username) {
    this.username = username;
  }


  public Utilisateur email(@javax.annotation.Nonnull String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   */
  @javax.annotation.Nonnull
  public String getEmail() {
    return email;
  }

  public void setEmail(@javax.annotation.Nonnull String email) {
    this.email = email;
  }


  public Utilisateur isActive(@javax.annotation.Nonnull Boolean isActive) {
    this.isActive = isActive;
    return this;
  }

  /**
   * Get isActive
   * @return isActive
   */
  @javax.annotation.Nonnull
  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(@javax.annotation.Nonnull Boolean isActive) {
    this.isActive = isActive;
  }


  public Utilisateur isSuperuser(@javax.annotation.Nullable Boolean isSuperuser) {
    this.isSuperuser = isSuperuser;
    return this;
  }

  /**
   * Get isSuperuser
   * @return isSuperuser
   */
  @javax.annotation.Nullable
  public Boolean getIsSuperuser() {
    return isSuperuser;
  }

  public void setIsSuperuser(@javax.annotation.Nullable Boolean isSuperuser) {
    this.isSuperuser = isSuperuser;
  }


  public Utilisateur isStaff(@javax.annotation.Nullable Boolean isStaff) {
    this.isStaff = isStaff;
    return this;
  }

  /**
   * Get isStaff
   * @return isStaff
   */
  @javax.annotation.Nullable
  public Boolean getIsStaff() {
    return isStaff;
  }

  public void setIsStaff(@javax.annotation.Nullable Boolean isStaff) {
    this.isStaff = isStaff;
  }


  public Utilisateur bypassQuota(@javax.annotation.Nullable Boolean bypassQuota) {
    this.bypassQuota = bypassQuota;
    return this;
  }

  /**
   * Get bypassQuota
   * @return bypassQuota
   */
  @javax.annotation.Nullable
  public Boolean getBypassQuota() {
    return bypassQuota;
  }

  public void setBypassQuota(@javax.annotation.Nullable Boolean bypassQuota) {
    this.bypassQuota = bypassQuota;
  }


  public Utilisateur teamId(@javax.annotation.Nullable Integer teamId) {
    this.teamId = teamId;
    return this;
  }

  /**
   * Get teamId
   * @return teamId
   */
  @javax.annotation.Nullable
  public Integer getTeamId() {
    return teamId;
  }

  public void setTeamId(@javax.annotation.Nullable Integer teamId) {
    this.teamId = teamId;
  }


  public Utilisateur hasQuota(@javax.annotation.Nullable Boolean hasQuota) {
    this.hasQuota = hasQuota;
    return this;
  }

  /**
   * Get hasQuota
   * @return hasQuota
   */
  @javax.annotation.Nullable
  public Boolean getHasQuota() {
    return hasQuota;
  }

  public void setHasQuota(@javax.annotation.Nullable Boolean hasQuota) {
    this.hasQuota = hasQuota;
  }


  public Utilisateur quotaInfo(@javax.annotation.Nullable QuotaInfo quotaInfo) {
    this.quotaInfo = quotaInfo;
    return this;
  }

  /**
   * Get quotaInfo
   * @return quotaInfo
   */
  @javax.annotation.Nullable
  public QuotaInfo getQuotaInfo() {
    return quotaInfo;
  }

  public void setQuotaInfo(@javax.annotation.Nullable QuotaInfo quotaInfo) {
    this.quotaInfo = quotaInfo;
  }


  public Utilisateur isTrial(@javax.annotation.Nullable Boolean isTrial) {
    this.isTrial = isTrial;
    return this;
  }

  /**
   * Get isTrial
   * @return isTrial
   */
  @javax.annotation.Nullable
  public Boolean getIsTrial() {
    return isTrial;
  }

  public void setIsTrial(@javax.annotation.Nullable Boolean isTrial) {
    this.isTrial = isTrial;
  }


  public Utilisateur clientUid(@javax.annotation.Nullable String clientUid) {
    this.clientUid = clientUid;
    return this;
  }

  /**
   * Get clientUid
   * @return clientUid
   */
  @javax.annotation.Nullable
  public String getClientUid() {
    return clientUid;
  }

  public void setClientUid(@javax.annotation.Nullable String clientUid) {
    this.clientUid = clientUid;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Utilisateur utilisateur = (Utilisateur) o;
    return Objects.equals(this.id, utilisateur.id) &&
        Objects.equals(this.username, utilisateur.username) &&
        Objects.equals(this.email, utilisateur.email) &&
        Objects.equals(this.isActive, utilisateur.isActive) &&
        Objects.equals(this.isSuperuser, utilisateur.isSuperuser) &&
        Objects.equals(this.isStaff, utilisateur.isStaff) &&
        Objects.equals(this.bypassQuota, utilisateur.bypassQuota) &&
        Objects.equals(this.teamId, utilisateur.teamId) &&
        Objects.equals(this.hasQuota, utilisateur.hasQuota) &&
        Objects.equals(this.quotaInfo, utilisateur.quotaInfo) &&
        Objects.equals(this.isTrial, utilisateur.isTrial) &&
        Objects.equals(this.clientUid, utilisateur.clientUid);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, email, isActive, isSuperuser, isStaff, bypassQuota, teamId, hasQuota, quotaInfo, isTrial, clientUid);
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
    sb.append("class Utilisateur {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    isActive: ").append(toIndentedString(isActive)).append("\n");
    sb.append("    isSuperuser: ").append(toIndentedString(isSuperuser)).append("\n");
    sb.append("    isStaff: ").append(toIndentedString(isStaff)).append("\n");
    sb.append("    bypassQuota: ").append(toIndentedString(bypassQuota)).append("\n");
    sb.append("    teamId: ").append(toIndentedString(teamId)).append("\n");
    sb.append("    hasQuota: ").append(toIndentedString(hasQuota)).append("\n");
    sb.append("    quotaInfo: ").append(toIndentedString(quotaInfo)).append("\n");
    sb.append("    isTrial: ").append(toIndentedString(isTrial)).append("\n");
    sb.append("    clientUid: ").append(toIndentedString(clientUid)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("id", "username", "email", "is_active", "is_superuser", "is_staff", "bypass_quota", "team_id", "has_quota", "quota_info", "is_trial", "client_uid"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(Arrays.asList("id", "username", "email", "is_active"));
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to Utilisateur
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!Utilisateur.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field(s) %s in Utilisateur is not found in the empty JSON string", Utilisateur.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!Utilisateur.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The field `%s` in the JSON string is not defined in the `Utilisateur` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : Utilisateur.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (!jsonObj.get("username").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `username` to be a primitive type in the JSON string but got `%s`", jsonObj.get("username").toString()));
      }
      if (!jsonObj.get("email").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `email` to be a primitive type in the JSON string but got `%s`", jsonObj.get("email").toString()));
      }
      // validate the optional field `quota_info`
      if (jsonObj.get("quota_info") != null && !jsonObj.get("quota_info").isJsonNull()) {
        QuotaInfo.validateJsonElement(jsonObj.get("quota_info"));
      }
      if ((jsonObj.get("client_uid") != null && !jsonObj.get("client_uid").isJsonNull()) && !jsonObj.get("client_uid").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `client_uid` to be a primitive type in the JSON string but got `%s`", jsonObj.get("client_uid").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!Utilisateur.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'Utilisateur' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<Utilisateur> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(Utilisateur.class));

       return (TypeAdapter<T>) new TypeAdapter<Utilisateur>() {
           @Override
           public void write(JsonWriter out, Utilisateur value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public Utilisateur read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of Utilisateur given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of Utilisateur
   * @throws IOException if the JSON string is invalid with respect to Utilisateur
   */
  public static Utilisateur fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, Utilisateur.class);
  }

  /**
   * Convert an instance of Utilisateur to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

