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
 * Paramètres optionnels pour signer le PDF généré.  **MODE 1 - Certificat stocké (recommandé) :** Ne fournissez que les métadonnées (raison, localisation, etc.). Le certificat sera récupéré automatiquement via client_uid du JWT. Signature PAdES-B-LT conforme eIDAS.  **MODE 2 - Clés dans le payload (tests/cas spéciaux) :** Fournissez key_pem + cert_pem directement dans le payload. Format PEM accepté : brut (\&quot;-----BEGIN...\&quot;) ou base64.  **Règle de sélection :** - Si key_pem ET cert_pem fournis → Mode 2 (clés payload) - Sinon → Mode 1 (certificat stocké récupéré via client_uid)
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-11-27T20:36:19.987046853Z[Etc/UTC]", comments = "Generator version: 7.18.0-SNAPSHOT")
public class ParametresSignature {
  public static final String SERIALIZED_NAME_KEY_PEM = "key_pem";
  @SerializedName(SERIALIZED_NAME_KEY_PEM)
  @javax.annotation.Nullable
  private String keyPem;

  public static final String SERIALIZED_NAME_CERT_PEM = "cert_pem";
  @SerializedName(SERIALIZED_NAME_CERT_PEM)
  @javax.annotation.Nullable
  private String certPem;

  public static final String SERIALIZED_NAME_KEY_PASSPHRASE = "key_passphrase";
  @SerializedName(SERIALIZED_NAME_KEY_PASSPHRASE)
  @javax.annotation.Nullable
  private String keyPassphrase;

  public static final String SERIALIZED_NAME_RAISON = "raison";
  @SerializedName(SERIALIZED_NAME_RAISON)
  @javax.annotation.Nullable
  private String raison;

  public static final String SERIALIZED_NAME_LOCALISATION = "localisation";
  @SerializedName(SERIALIZED_NAME_LOCALISATION)
  @javax.annotation.Nullable
  private String localisation;

  public static final String SERIALIZED_NAME_CONTACT = "contact";
  @SerializedName(SERIALIZED_NAME_CONTACT)
  @javax.annotation.Nullable
  private String contact;

  public static final String SERIALIZED_NAME_FIELD_NAME = "field_name";
  @SerializedName(SERIALIZED_NAME_FIELD_NAME)
  @javax.annotation.Nullable
  private String fieldName = "FactPulseSignature";

  public static final String SERIALIZED_NAME_USE_PADES_LT = "use_pades_lt";
  @SerializedName(SERIALIZED_NAME_USE_PADES_LT)
  @javax.annotation.Nullable
  private Boolean usePadesLt = false;

  public static final String SERIALIZED_NAME_USE_TIMESTAMP = "use_timestamp";
  @SerializedName(SERIALIZED_NAME_USE_TIMESTAMP)
  @javax.annotation.Nullable
  private Boolean useTimestamp = true;

  public ParametresSignature() {
  }

  public ParametresSignature keyPem(@javax.annotation.Nullable String keyPem) {
    this.keyPem = keyPem;
    return this;
  }

  /**
   * Get keyPem
   * @return keyPem
   */
  @javax.annotation.Nullable
  public String getKeyPem() {
    return keyPem;
  }

  public void setKeyPem(@javax.annotation.Nullable String keyPem) {
    this.keyPem = keyPem;
  }


  public ParametresSignature certPem(@javax.annotation.Nullable String certPem) {
    this.certPem = certPem;
    return this;
  }

  /**
   * Get certPem
   * @return certPem
   */
  @javax.annotation.Nullable
  public String getCertPem() {
    return certPem;
  }

  public void setCertPem(@javax.annotation.Nullable String certPem) {
    this.certPem = certPem;
  }


  public ParametresSignature keyPassphrase(@javax.annotation.Nullable String keyPassphrase) {
    this.keyPassphrase = keyPassphrase;
    return this;
  }

  /**
   * Get keyPassphrase
   * @return keyPassphrase
   */
  @javax.annotation.Nullable
  public String getKeyPassphrase() {
    return keyPassphrase;
  }

  public void setKeyPassphrase(@javax.annotation.Nullable String keyPassphrase) {
    this.keyPassphrase = keyPassphrase;
  }


  public ParametresSignature raison(@javax.annotation.Nullable String raison) {
    this.raison = raison;
    return this;
  }

  /**
   * Get raison
   * @return raison
   */
  @javax.annotation.Nullable
  public String getRaison() {
    return raison;
  }

  public void setRaison(@javax.annotation.Nullable String raison) {
    this.raison = raison;
  }


  public ParametresSignature localisation(@javax.annotation.Nullable String localisation) {
    this.localisation = localisation;
    return this;
  }

  /**
   * Get localisation
   * @return localisation
   */
  @javax.annotation.Nullable
  public String getLocalisation() {
    return localisation;
  }

  public void setLocalisation(@javax.annotation.Nullable String localisation) {
    this.localisation = localisation;
  }


  public ParametresSignature contact(@javax.annotation.Nullable String contact) {
    this.contact = contact;
    return this;
  }

  /**
   * Get contact
   * @return contact
   */
  @javax.annotation.Nullable
  public String getContact() {
    return contact;
  }

  public void setContact(@javax.annotation.Nullable String contact) {
    this.contact = contact;
  }


  public ParametresSignature fieldName(@javax.annotation.Nullable String fieldName) {
    this.fieldName = fieldName;
    return this;
  }

  /**
   * Nom du champ de signature PDF
   * @return fieldName
   */
  @javax.annotation.Nullable
  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(@javax.annotation.Nullable String fieldName) {
    this.fieldName = fieldName;
  }


  public ParametresSignature usePadesLt(@javax.annotation.Nullable Boolean usePadesLt) {
    this.usePadesLt = usePadesLt;
    return this;
  }

  /**
   * Activer PAdES-B-LT (archivage long terme). NÉCESSITE certificat avec accès OCSP/CRL
   * @return usePadesLt
   */
  @javax.annotation.Nullable
  public Boolean getUsePadesLt() {
    return usePadesLt;
  }

  public void setUsePadesLt(@javax.annotation.Nullable Boolean usePadesLt) {
    this.usePadesLt = usePadesLt;
  }


  public ParametresSignature useTimestamp(@javax.annotation.Nullable Boolean useTimestamp) {
    this.useTimestamp = useTimestamp;
    return this;
  }

  /**
   * Activer l&#39;horodatage RFC 3161 avec FreeTSA (PAdES-B-T)
   * @return useTimestamp
   */
  @javax.annotation.Nullable
  public Boolean getUseTimestamp() {
    return useTimestamp;
  }

  public void setUseTimestamp(@javax.annotation.Nullable Boolean useTimestamp) {
    this.useTimestamp = useTimestamp;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParametresSignature parametresSignature = (ParametresSignature) o;
    return Objects.equals(this.keyPem, parametresSignature.keyPem) &&
        Objects.equals(this.certPem, parametresSignature.certPem) &&
        Objects.equals(this.keyPassphrase, parametresSignature.keyPassphrase) &&
        Objects.equals(this.raison, parametresSignature.raison) &&
        Objects.equals(this.localisation, parametresSignature.localisation) &&
        Objects.equals(this.contact, parametresSignature.contact) &&
        Objects.equals(this.fieldName, parametresSignature.fieldName) &&
        Objects.equals(this.usePadesLt, parametresSignature.usePadesLt) &&
        Objects.equals(this.useTimestamp, parametresSignature.useTimestamp);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(keyPem, certPem, keyPassphrase, raison, localisation, contact, fieldName, usePadesLt, useTimestamp);
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
    sb.append("class ParametresSignature {\n");
    sb.append("    keyPem: ").append(toIndentedString(keyPem)).append("\n");
    sb.append("    certPem: ").append(toIndentedString(certPem)).append("\n");
    sb.append("    keyPassphrase: ").append(toIndentedString(keyPassphrase)).append("\n");
    sb.append("    raison: ").append(toIndentedString(raison)).append("\n");
    sb.append("    localisation: ").append(toIndentedString(localisation)).append("\n");
    sb.append("    contact: ").append(toIndentedString(contact)).append("\n");
    sb.append("    fieldName: ").append(toIndentedString(fieldName)).append("\n");
    sb.append("    usePadesLt: ").append(toIndentedString(usePadesLt)).append("\n");
    sb.append("    useTimestamp: ").append(toIndentedString(useTimestamp)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("key_pem", "cert_pem", "key_passphrase", "raison", "localisation", "contact", "field_name", "use_pades_lt", "use_timestamp"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(0);
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to ParametresSignature
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ParametresSignature.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field(s) %s in ParametresSignature is not found in the empty JSON string", ParametresSignature.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!ParametresSignature.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The field `%s` in the JSON string is not defined in the `ParametresSignature` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("key_pem") != null && !jsonObj.get("key_pem").isJsonNull()) && !jsonObj.get("key_pem").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `key_pem` to be a primitive type in the JSON string but got `%s`", jsonObj.get("key_pem").toString()));
      }
      if ((jsonObj.get("cert_pem") != null && !jsonObj.get("cert_pem").isJsonNull()) && !jsonObj.get("cert_pem").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `cert_pem` to be a primitive type in the JSON string but got `%s`", jsonObj.get("cert_pem").toString()));
      }
      if ((jsonObj.get("key_passphrase") != null && !jsonObj.get("key_passphrase").isJsonNull()) && !jsonObj.get("key_passphrase").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `key_passphrase` to be a primitive type in the JSON string but got `%s`", jsonObj.get("key_passphrase").toString()));
      }
      if ((jsonObj.get("raison") != null && !jsonObj.get("raison").isJsonNull()) && !jsonObj.get("raison").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `raison` to be a primitive type in the JSON string but got `%s`", jsonObj.get("raison").toString()));
      }
      if ((jsonObj.get("localisation") != null && !jsonObj.get("localisation").isJsonNull()) && !jsonObj.get("localisation").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `localisation` to be a primitive type in the JSON string but got `%s`", jsonObj.get("localisation").toString()));
      }
      if ((jsonObj.get("contact") != null && !jsonObj.get("contact").isJsonNull()) && !jsonObj.get("contact").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `contact` to be a primitive type in the JSON string but got `%s`", jsonObj.get("contact").toString()));
      }
      if ((jsonObj.get("field_name") != null && !jsonObj.get("field_name").isJsonNull()) && !jsonObj.get("field_name").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `field_name` to be a primitive type in the JSON string but got `%s`", jsonObj.get("field_name").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ParametresSignature.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ParametresSignature' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ParametresSignature> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ParametresSignature.class));

       return (TypeAdapter<T>) new TypeAdapter<ParametresSignature>() {
           @Override
           public void write(JsonWriter out, ParametresSignature value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public ParametresSignature read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of ParametresSignature given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of ParametresSignature
   * @throws IOException if the JSON string is invalid with respect to ParametresSignature
   */
  public static ParametresSignature fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ParametresSignature.class);
  }

  /**
   * Convert an instance of ParametresSignature to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

