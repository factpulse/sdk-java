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
import org.openapitools.client.model.CertificateInfoResponse;
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
 * Réponse après génération d&#39;un certificat de test.  Contient le certificat PEM, la clé privée PEM, et optionnellement le PKCS#12.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-11-29T06:31:22.124413796Z[Etc/UTC]", comments = "Generator version: 7.18.0-SNAPSHOT")
public class GenerateCertificateResponse {
  public static final String SERIALIZED_NAME_STATUS = "status";
  @SerializedName(SERIALIZED_NAME_STATUS)
  @javax.annotation.Nullable
  private String status = "success";

  public static final String SERIALIZED_NAME_CERTIFICAT_PEM = "certificat_pem";
  @SerializedName(SERIALIZED_NAME_CERTIFICAT_PEM)
  @javax.annotation.Nonnull
  private String certificatPem;

  public static final String SERIALIZED_NAME_CLE_PRIVEE_PEM = "cle_privee_pem";
  @SerializedName(SERIALIZED_NAME_CLE_PRIVEE_PEM)
  @javax.annotation.Nonnull
  private String clePriveePem;

  public static final String SERIALIZED_NAME_PKCS12_BASE64 = "pkcs12_base64";
  @SerializedName(SERIALIZED_NAME_PKCS12_BASE64)
  @javax.annotation.Nullable
  private String pkcs12Base64;

  public static final String SERIALIZED_NAME_INFO = "info";
  @SerializedName(SERIALIZED_NAME_INFO)
  @javax.annotation.Nonnull
  private CertificateInfoResponse info;

  public static final String SERIALIZED_NAME_AVERTISSEMENT = "avertissement";
  @SerializedName(SERIALIZED_NAME_AVERTISSEMENT)
  @javax.annotation.Nullable
  private String avertissement = "⚠️ Ce certificat est AUTO-SIGNÉ et destiné uniquement aux TESTS. Ne PAS utiliser en production. Niveau eIDAS : SES (Simple Electronic Signature)";

  public GenerateCertificateResponse() {
  }

  public GenerateCertificateResponse status(@javax.annotation.Nullable String status) {
    this.status = status;
    return this;
  }

  /**
   * Statut de l&#39;opération
   * @return status
   */
  @javax.annotation.Nullable
  public String getStatus() {
    return status;
  }

  public void setStatus(@javax.annotation.Nullable String status) {
    this.status = status;
  }


  public GenerateCertificateResponse certificatPem(@javax.annotation.Nonnull String certificatPem) {
    this.certificatPem = certificatPem;
    return this;
  }

  /**
   * Certificat X.509 au format PEM
   * @return certificatPem
   */
  @javax.annotation.Nonnull
  public String getCertificatPem() {
    return certificatPem;
  }

  public void setCertificatPem(@javax.annotation.Nonnull String certificatPem) {
    this.certificatPem = certificatPem;
  }


  public GenerateCertificateResponse clePriveePem(@javax.annotation.Nonnull String clePriveePem) {
    this.clePriveePem = clePriveePem;
    return this;
  }

  /**
   * Clé privée RSA au format PEM
   * @return clePriveePem
   */
  @javax.annotation.Nonnull
  public String getClePriveePem() {
    return clePriveePem;
  }

  public void setClePriveePem(@javax.annotation.Nonnull String clePriveePem) {
    this.clePriveePem = clePriveePem;
  }


  public GenerateCertificateResponse pkcs12Base64(@javax.annotation.Nullable String pkcs12Base64) {
    this.pkcs12Base64 = pkcs12Base64;
    return this;
  }

  /**
   * Get pkcs12Base64
   * @return pkcs12Base64
   */
  @javax.annotation.Nullable
  public String getPkcs12Base64() {
    return pkcs12Base64;
  }

  public void setPkcs12Base64(@javax.annotation.Nullable String pkcs12Base64) {
    this.pkcs12Base64 = pkcs12Base64;
  }


  public GenerateCertificateResponse info(@javax.annotation.Nonnull CertificateInfoResponse info) {
    this.info = info;
    return this;
  }

  /**
   * Informations sur le certificat généré
   * @return info
   */
  @javax.annotation.Nonnull
  public CertificateInfoResponse getInfo() {
    return info;
  }

  public void setInfo(@javax.annotation.Nonnull CertificateInfoResponse info) {
    this.info = info;
  }


  public GenerateCertificateResponse avertissement(@javax.annotation.Nullable String avertissement) {
    this.avertissement = avertissement;
    return this;
  }

  /**
   * Avertissement sur l&#39;utilisation du certificat
   * @return avertissement
   */
  @javax.annotation.Nullable
  public String getAvertissement() {
    return avertissement;
  }

  public void setAvertissement(@javax.annotation.Nullable String avertissement) {
    this.avertissement = avertissement;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GenerateCertificateResponse generateCertificateResponse = (GenerateCertificateResponse) o;
    return Objects.equals(this.status, generateCertificateResponse.status) &&
        Objects.equals(this.certificatPem, generateCertificateResponse.certificatPem) &&
        Objects.equals(this.clePriveePem, generateCertificateResponse.clePriveePem) &&
        Objects.equals(this.pkcs12Base64, generateCertificateResponse.pkcs12Base64) &&
        Objects.equals(this.info, generateCertificateResponse.info) &&
        Objects.equals(this.avertissement, generateCertificateResponse.avertissement);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, certificatPem, clePriveePem, pkcs12Base64, info, avertissement);
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
    sb.append("class GenerateCertificateResponse {\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    certificatPem: ").append(toIndentedString(certificatPem)).append("\n");
    sb.append("    clePriveePem: ").append(toIndentedString(clePriveePem)).append("\n");
    sb.append("    pkcs12Base64: ").append(toIndentedString(pkcs12Base64)).append("\n");
    sb.append("    info: ").append(toIndentedString(info)).append("\n");
    sb.append("    avertissement: ").append(toIndentedString(avertissement)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("status", "certificat_pem", "cle_privee_pem", "pkcs12_base64", "info", "avertissement"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(Arrays.asList("certificat_pem", "cle_privee_pem", "info"));
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to GenerateCertificateResponse
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!GenerateCertificateResponse.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field(s) %s in GenerateCertificateResponse is not found in the empty JSON string", GenerateCertificateResponse.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!GenerateCertificateResponse.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The field `%s` in the JSON string is not defined in the `GenerateCertificateResponse` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : GenerateCertificateResponse.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("status") != null && !jsonObj.get("status").isJsonNull()) && !jsonObj.get("status").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `status` to be a primitive type in the JSON string but got `%s`", jsonObj.get("status").toString()));
      }
      if (!jsonObj.get("certificat_pem").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `certificat_pem` to be a primitive type in the JSON string but got `%s`", jsonObj.get("certificat_pem").toString()));
      }
      if (!jsonObj.get("cle_privee_pem").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `cle_privee_pem` to be a primitive type in the JSON string but got `%s`", jsonObj.get("cle_privee_pem").toString()));
      }
      if ((jsonObj.get("pkcs12_base64") != null && !jsonObj.get("pkcs12_base64").isJsonNull()) && !jsonObj.get("pkcs12_base64").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `pkcs12_base64` to be a primitive type in the JSON string but got `%s`", jsonObj.get("pkcs12_base64").toString()));
      }
      // validate the required field `info`
      CertificateInfoResponse.validateJsonElement(jsonObj.get("info"));
      if ((jsonObj.get("avertissement") != null && !jsonObj.get("avertissement").isJsonNull()) && !jsonObj.get("avertissement").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `avertissement` to be a primitive type in the JSON string but got `%s`", jsonObj.get("avertissement").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!GenerateCertificateResponse.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'GenerateCertificateResponse' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<GenerateCertificateResponse> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(GenerateCertificateResponse.class));

       return (TypeAdapter<T>) new TypeAdapter<GenerateCertificateResponse>() {
           @Override
           public void write(JsonWriter out, GenerateCertificateResponse value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public GenerateCertificateResponse read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of GenerateCertificateResponse given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of GenerateCertificateResponse
   * @throws IOException if the JSON string is invalid with respect to GenerateCertificateResponse
   */
  public static GenerateCertificateResponse fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, GenerateCertificateResponse.class);
  }

  /**
   * Convert an instance of GenerateCertificateResponse to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

