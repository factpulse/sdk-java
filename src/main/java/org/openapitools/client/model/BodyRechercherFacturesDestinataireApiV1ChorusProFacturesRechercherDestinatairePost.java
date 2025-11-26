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
import java.util.HashMap;
import java.util.Map;
import org.openapitools.client.model.ChorusProCredentials;
import org.openapitools.client.model.Utilisateur;
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
 * BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-11-26T13:16:03.392341018Z[Etc/UTC]", comments = "Generator version: 7.18.0-SNAPSHOT")
public class BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost {
  public static final String SERIALIZED_NAME_PAYLOAD = "payload";
  @SerializedName(SERIALIZED_NAME_PAYLOAD)
  @javax.annotation.Nonnull
  private Map<String, Object> payload = new HashMap<>();

  public static final String SERIALIZED_NAME_USER_INFO = "user_info";
  @SerializedName(SERIALIZED_NAME_USER_INFO)
  @javax.annotation.Nonnull
  private Utilisateur userInfo;

  public static final String SERIALIZED_NAME_CREDENTIALS = "credentials";
  @SerializedName(SERIALIZED_NAME_CREDENTIALS)
  @javax.annotation.Nullable
  private ChorusProCredentials credentials;

  public BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost() {
  }

  public BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost payload(@javax.annotation.Nonnull Map<String, Object> payload) {
    this.payload = payload;
    return this;
  }

  public BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost putPayloadItem(String key, Object payloadItem) {
    if (this.payload == null) {
      this.payload = new HashMap<>();
    }
    this.payload.put(key, payloadItem);
    return this;
  }

  /**
   * Get payload
   * @return payload
   */
  @javax.annotation.Nonnull
  public Map<String, Object> getPayload() {
    return payload;
  }

  public void setPayload(@javax.annotation.Nonnull Map<String, Object> payload) {
    this.payload = payload;
  }


  public BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost userInfo(@javax.annotation.Nonnull Utilisateur userInfo) {
    this.userInfo = userInfo;
    return this;
  }

  /**
   * Get userInfo
   * @return userInfo
   */
  @javax.annotation.Nonnull
  public Utilisateur getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(@javax.annotation.Nonnull Utilisateur userInfo) {
    this.userInfo = userInfo;
  }


  public BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost credentials(@javax.annotation.Nullable ChorusProCredentials credentials) {
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



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost bodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost = (BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost) o;
    return Objects.equals(this.payload, bodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost.payload) &&
        Objects.equals(this.userInfo, bodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost.userInfo) &&
        Objects.equals(this.credentials, bodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost.credentials);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(payload, userInfo, credentials);
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
    sb.append("class BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost {\n");
    sb.append("    payload: ").append(toIndentedString(payload)).append("\n");
    sb.append("    userInfo: ").append(toIndentedString(userInfo)).append("\n");
    sb.append("    credentials: ").append(toIndentedString(credentials)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("payload", "user_info", "credentials"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(Arrays.asList("payload", "user_info"));
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field(s) %s in BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost is not found in the empty JSON string", BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The field `%s` in the JSON string is not defined in the `BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      // validate the required field `user_info`
      Utilisateur.validateJsonElement(jsonObj.get("user_info"));
      // validate the optional field `credentials`
      if (jsonObj.get("credentials") != null && !jsonObj.get("credentials").isJsonNull()) {
        ChorusProCredentials.validateJsonElement(jsonObj.get("credentials"));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost.class));

       return (TypeAdapter<T>) new TypeAdapter<BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost>() {
           @Override
           public void write(JsonWriter out, BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost
   * @throws IOException if the JSON string is invalid with respect to BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost
   */
  public static BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost.class);
  }

  /**
   * Convert an instance of BodyRechercherFacturesDestinataireApiV1ChorusProFacturesRechercherDestinatairePost to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

