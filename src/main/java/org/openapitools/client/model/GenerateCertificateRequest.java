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
 * Requête pour générer un certificat X.509 auto-signé de test.  ⚠️ ATTENTION : Ce certificat est destiné uniquement aux TESTS. NE PAS utiliser en production ! Niveau eIDAS : SES (Simple Electronic Signature)
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-11-29T08:46:57.171193333Z[Etc/UTC]", comments = "Generator version: 7.18.0-SNAPSHOT")
public class GenerateCertificateRequest {
  public static final String SERIALIZED_NAME_CN = "cn";
  @SerializedName(SERIALIZED_NAME_CN)
  @javax.annotation.Nullable
  private String cn = "Test Signature FactPulse";

  public static final String SERIALIZED_NAME_ORGANISATION = "organisation";
  @SerializedName(SERIALIZED_NAME_ORGANISATION)
  @javax.annotation.Nullable
  private String organisation = "FactPulse Test";

  public static final String SERIALIZED_NAME_PAYS = "pays";
  @SerializedName(SERIALIZED_NAME_PAYS)
  @javax.annotation.Nullable
  private String pays = "FR";

  public static final String SERIALIZED_NAME_VILLE = "ville";
  @SerializedName(SERIALIZED_NAME_VILLE)
  @javax.annotation.Nullable
  private String ville = "Paris";

  public static final String SERIALIZED_NAME_PROVINCE = "province";
  @SerializedName(SERIALIZED_NAME_PROVINCE)
  @javax.annotation.Nullable
  private String province = "Ile-de-France";

  public static final String SERIALIZED_NAME_EMAIL = "email";
  @SerializedName(SERIALIZED_NAME_EMAIL)
  @javax.annotation.Nullable
  private String email;

  public static final String SERIALIZED_NAME_DUREE_JOURS = "duree_jours";
  @SerializedName(SERIALIZED_NAME_DUREE_JOURS)
  @javax.annotation.Nullable
  private Integer dureeJours = 365;

  public static final String SERIALIZED_NAME_TAILLE_CLE = "taille_cle";
  @SerializedName(SERIALIZED_NAME_TAILLE_CLE)
  @javax.annotation.Nullable
  private Integer tailleCle = 2048;

  public static final String SERIALIZED_NAME_PASSPHRASE_CLE = "passphrase_cle";
  @SerializedName(SERIALIZED_NAME_PASSPHRASE_CLE)
  @javax.annotation.Nullable
  private String passphraseCle;

  public static final String SERIALIZED_NAME_GENERER_P12 = "generer_p12";
  @SerializedName(SERIALIZED_NAME_GENERER_P12)
  @javax.annotation.Nullable
  private Boolean genererP12 = false;

  public static final String SERIALIZED_NAME_PASSPHRASE_P12 = "passphrase_p12";
  @SerializedName(SERIALIZED_NAME_PASSPHRASE_P12)
  @javax.annotation.Nullable
  private String passphraseP12 = "changeme";

  public GenerateCertificateRequest() {
  }

  public GenerateCertificateRequest cn(@javax.annotation.Nullable String cn) {
    this.cn = cn;
    return this;
  }

  /**
   * Common Name (CN) - Nom du certificat
   * @return cn
   */
  @javax.annotation.Nullable
  public String getCn() {
    return cn;
  }

  public void setCn(@javax.annotation.Nullable String cn) {
    this.cn = cn;
  }


  public GenerateCertificateRequest organisation(@javax.annotation.Nullable String organisation) {
    this.organisation = organisation;
    return this;
  }

  /**
   * Organisation (O)
   * @return organisation
   */
  @javax.annotation.Nullable
  public String getOrganisation() {
    return organisation;
  }

  public void setOrganisation(@javax.annotation.Nullable String organisation) {
    this.organisation = organisation;
  }


  public GenerateCertificateRequest pays(@javax.annotation.Nullable String pays) {
    this.pays = pays;
    return this;
  }

  /**
   * Code pays ISO 2 lettres (C)
   * @return pays
   */
  @javax.annotation.Nullable
  public String getPays() {
    return pays;
  }

  public void setPays(@javax.annotation.Nullable String pays) {
    this.pays = pays;
  }


  public GenerateCertificateRequest ville(@javax.annotation.Nullable String ville) {
    this.ville = ville;
    return this;
  }

  /**
   * Ville (L)
   * @return ville
   */
  @javax.annotation.Nullable
  public String getVille() {
    return ville;
  }

  public void setVille(@javax.annotation.Nullable String ville) {
    this.ville = ville;
  }


  public GenerateCertificateRequest province(@javax.annotation.Nullable String province) {
    this.province = province;
    return this;
  }

  /**
   * Province/État (ST)
   * @return province
   */
  @javax.annotation.Nullable
  public String getProvince() {
    return province;
  }

  public void setProvince(@javax.annotation.Nullable String province) {
    this.province = province;
  }


  public GenerateCertificateRequest email(@javax.annotation.Nullable String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   */
  @javax.annotation.Nullable
  public String getEmail() {
    return email;
  }

  public void setEmail(@javax.annotation.Nullable String email) {
    this.email = email;
  }


  public GenerateCertificateRequest dureeJours(@javax.annotation.Nullable Integer dureeJours) {
    this.dureeJours = dureeJours;
    return this;
  }

  /**
   * Durée de validité en jours
   * minimum: 1
   * maximum: 3650
   * @return dureeJours
   */
  @javax.annotation.Nullable
  public Integer getDureeJours() {
    return dureeJours;
  }

  public void setDureeJours(@javax.annotation.Nullable Integer dureeJours) {
    this.dureeJours = dureeJours;
  }


  public GenerateCertificateRequest tailleCle(@javax.annotation.Nullable Integer tailleCle) {
    this.tailleCle = tailleCle;
    return this;
  }

  /**
   * Taille de la clé RSA en bits
   * @return tailleCle
   */
  @javax.annotation.Nullable
  public Integer getTailleCle() {
    return tailleCle;
  }

  public void setTailleCle(@javax.annotation.Nullable Integer tailleCle) {
    this.tailleCle = tailleCle;
  }


  public GenerateCertificateRequest passphraseCle(@javax.annotation.Nullable String passphraseCle) {
    this.passphraseCle = passphraseCle;
    return this;
  }

  /**
   * Get passphraseCle
   * @return passphraseCle
   */
  @javax.annotation.Nullable
  public String getPassphraseCle() {
    return passphraseCle;
  }

  public void setPassphraseCle(@javax.annotation.Nullable String passphraseCle) {
    this.passphraseCle = passphraseCle;
  }


  public GenerateCertificateRequest genererP12(@javax.annotation.Nullable Boolean genererP12) {
    this.genererP12 = genererP12;
    return this;
  }

  /**
   * Générer aussi un fichier PKCS#12 (.p12)
   * @return genererP12
   */
  @javax.annotation.Nullable
  public Boolean getGenererP12() {
    return genererP12;
  }

  public void setGenererP12(@javax.annotation.Nullable Boolean genererP12) {
    this.genererP12 = genererP12;
  }


  public GenerateCertificateRequest passphraseP12(@javax.annotation.Nullable String passphraseP12) {
    this.passphraseP12 = passphraseP12;
    return this;
  }

  /**
   * Passphrase pour le fichier PKCS#12
   * @return passphraseP12
   */
  @javax.annotation.Nullable
  public String getPassphraseP12() {
    return passphraseP12;
  }

  public void setPassphraseP12(@javax.annotation.Nullable String passphraseP12) {
    this.passphraseP12 = passphraseP12;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GenerateCertificateRequest generateCertificateRequest = (GenerateCertificateRequest) o;
    return Objects.equals(this.cn, generateCertificateRequest.cn) &&
        Objects.equals(this.organisation, generateCertificateRequest.organisation) &&
        Objects.equals(this.pays, generateCertificateRequest.pays) &&
        Objects.equals(this.ville, generateCertificateRequest.ville) &&
        Objects.equals(this.province, generateCertificateRequest.province) &&
        Objects.equals(this.email, generateCertificateRequest.email) &&
        Objects.equals(this.dureeJours, generateCertificateRequest.dureeJours) &&
        Objects.equals(this.tailleCle, generateCertificateRequest.tailleCle) &&
        Objects.equals(this.passphraseCle, generateCertificateRequest.passphraseCle) &&
        Objects.equals(this.genererP12, generateCertificateRequest.genererP12) &&
        Objects.equals(this.passphraseP12, generateCertificateRequest.passphraseP12);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(cn, organisation, pays, ville, province, email, dureeJours, tailleCle, passphraseCle, genererP12, passphraseP12);
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
    sb.append("class GenerateCertificateRequest {\n");
    sb.append("    cn: ").append(toIndentedString(cn)).append("\n");
    sb.append("    organisation: ").append(toIndentedString(organisation)).append("\n");
    sb.append("    pays: ").append(toIndentedString(pays)).append("\n");
    sb.append("    ville: ").append(toIndentedString(ville)).append("\n");
    sb.append("    province: ").append(toIndentedString(province)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    dureeJours: ").append(toIndentedString(dureeJours)).append("\n");
    sb.append("    tailleCle: ").append(toIndentedString(tailleCle)).append("\n");
    sb.append("    passphraseCle: ").append(toIndentedString(passphraseCle)).append("\n");
    sb.append("    genererP12: ").append(toIndentedString(genererP12)).append("\n");
    sb.append("    passphraseP12: ").append(toIndentedString(passphraseP12)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("cn", "organisation", "pays", "ville", "province", "email", "duree_jours", "taille_cle", "passphrase_cle", "generer_p12", "passphrase_p12"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(0);
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to GenerateCertificateRequest
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!GenerateCertificateRequest.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field(s) %s in GenerateCertificateRequest is not found in the empty JSON string", GenerateCertificateRequest.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!GenerateCertificateRequest.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The field `%s` in the JSON string is not defined in the `GenerateCertificateRequest` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("cn") != null && !jsonObj.get("cn").isJsonNull()) && !jsonObj.get("cn").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `cn` to be a primitive type in the JSON string but got `%s`", jsonObj.get("cn").toString()));
      }
      if ((jsonObj.get("organisation") != null && !jsonObj.get("organisation").isJsonNull()) && !jsonObj.get("organisation").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `organisation` to be a primitive type in the JSON string but got `%s`", jsonObj.get("organisation").toString()));
      }
      if ((jsonObj.get("pays") != null && !jsonObj.get("pays").isJsonNull()) && !jsonObj.get("pays").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `pays` to be a primitive type in the JSON string but got `%s`", jsonObj.get("pays").toString()));
      }
      if ((jsonObj.get("ville") != null && !jsonObj.get("ville").isJsonNull()) && !jsonObj.get("ville").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `ville` to be a primitive type in the JSON string but got `%s`", jsonObj.get("ville").toString()));
      }
      if ((jsonObj.get("province") != null && !jsonObj.get("province").isJsonNull()) && !jsonObj.get("province").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `province` to be a primitive type in the JSON string but got `%s`", jsonObj.get("province").toString()));
      }
      if ((jsonObj.get("email") != null && !jsonObj.get("email").isJsonNull()) && !jsonObj.get("email").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `email` to be a primitive type in the JSON string but got `%s`", jsonObj.get("email").toString()));
      }
      if ((jsonObj.get("passphrase_cle") != null && !jsonObj.get("passphrase_cle").isJsonNull()) && !jsonObj.get("passphrase_cle").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `passphrase_cle` to be a primitive type in the JSON string but got `%s`", jsonObj.get("passphrase_cle").toString()));
      }
      if ((jsonObj.get("passphrase_p12") != null && !jsonObj.get("passphrase_p12").isJsonNull()) && !jsonObj.get("passphrase_p12").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `passphrase_p12` to be a primitive type in the JSON string but got `%s`", jsonObj.get("passphrase_p12").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!GenerateCertificateRequest.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'GenerateCertificateRequest' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<GenerateCertificateRequest> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(GenerateCertificateRequest.class));

       return (TypeAdapter<T>) new TypeAdapter<GenerateCertificateRequest>() {
           @Override
           public void write(JsonWriter out, GenerateCertificateRequest value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public GenerateCertificateRequest read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of GenerateCertificateRequest given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of GenerateCertificateRequest
   * @throws IOException if the JSON string is invalid with respect to GenerateCertificateRequest
   */
  public static GenerateCertificateRequest fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, GenerateCertificateRequest.class);
  }

  /**
   * Convert an instance of GenerateCertificateRequest to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

