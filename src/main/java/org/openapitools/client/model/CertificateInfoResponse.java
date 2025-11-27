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
 * Informations sur un certificat généré.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-11-27T15:22:03.945042717Z[Etc/UTC]", comments = "Generator version: 7.18.0-SNAPSHOT")
public class CertificateInfoResponse {
  public static final String SERIALIZED_NAME_CN = "cn";
  @SerializedName(SERIALIZED_NAME_CN)
  @javax.annotation.Nonnull
  private String cn;

  public static final String SERIALIZED_NAME_ORGANISATION = "organisation";
  @SerializedName(SERIALIZED_NAME_ORGANISATION)
  @javax.annotation.Nonnull
  private String organisation;

  public static final String SERIALIZED_NAME_PAYS = "pays";
  @SerializedName(SERIALIZED_NAME_PAYS)
  @javax.annotation.Nonnull
  private String pays;

  public static final String SERIALIZED_NAME_VILLE = "ville";
  @SerializedName(SERIALIZED_NAME_VILLE)
  @javax.annotation.Nonnull
  private String ville;

  public static final String SERIALIZED_NAME_PROVINCE = "province";
  @SerializedName(SERIALIZED_NAME_PROVINCE)
  @javax.annotation.Nonnull
  private String province;

  public static final String SERIALIZED_NAME_EMAIL = "email";
  @SerializedName(SERIALIZED_NAME_EMAIL)
  @javax.annotation.Nullable
  private String email;

  public static final String SERIALIZED_NAME_SUJET = "sujet";
  @SerializedName(SERIALIZED_NAME_SUJET)
  @javax.annotation.Nonnull
  private String sujet;

  public static final String SERIALIZED_NAME_EMETTEUR = "emetteur";
  @SerializedName(SERIALIZED_NAME_EMETTEUR)
  @javax.annotation.Nonnull
  private String emetteur;

  public static final String SERIALIZED_NAME_NUMERO_SERIE = "numero_serie";
  @SerializedName(SERIALIZED_NAME_NUMERO_SERIE)
  @javax.annotation.Nonnull
  private Integer numeroSerie;

  public static final String SERIALIZED_NAME_VALIDE_DU = "valide_du";
  @SerializedName(SERIALIZED_NAME_VALIDE_DU)
  @javax.annotation.Nonnull
  private String valideDu;

  public static final String SERIALIZED_NAME_VALIDE_AU = "valide_au";
  @SerializedName(SERIALIZED_NAME_VALIDE_AU)
  @javax.annotation.Nonnull
  private String valideAu;

  public static final String SERIALIZED_NAME_ALGORITHME = "algorithme";
  @SerializedName(SERIALIZED_NAME_ALGORITHME)
  @javax.annotation.Nonnull
  private String algorithme;

  public CertificateInfoResponse() {
  }

  public CertificateInfoResponse cn(@javax.annotation.Nonnull String cn) {
    this.cn = cn;
    return this;
  }

  /**
   * Common Name
   * @return cn
   */
  @javax.annotation.Nonnull
  public String getCn() {
    return cn;
  }

  public void setCn(@javax.annotation.Nonnull String cn) {
    this.cn = cn;
  }


  public CertificateInfoResponse organisation(@javax.annotation.Nonnull String organisation) {
    this.organisation = organisation;
    return this;
  }

  /**
   * Organisation
   * @return organisation
   */
  @javax.annotation.Nonnull
  public String getOrganisation() {
    return organisation;
  }

  public void setOrganisation(@javax.annotation.Nonnull String organisation) {
    this.organisation = organisation;
  }


  public CertificateInfoResponse pays(@javax.annotation.Nonnull String pays) {
    this.pays = pays;
    return this;
  }

  /**
   * Code pays
   * @return pays
   */
  @javax.annotation.Nonnull
  public String getPays() {
    return pays;
  }

  public void setPays(@javax.annotation.Nonnull String pays) {
    this.pays = pays;
  }


  public CertificateInfoResponse ville(@javax.annotation.Nonnull String ville) {
    this.ville = ville;
    return this;
  }

  /**
   * Ville
   * @return ville
   */
  @javax.annotation.Nonnull
  public String getVille() {
    return ville;
  }

  public void setVille(@javax.annotation.Nonnull String ville) {
    this.ville = ville;
  }


  public CertificateInfoResponse province(@javax.annotation.Nonnull String province) {
    this.province = province;
    return this;
  }

  /**
   * Province
   * @return province
   */
  @javax.annotation.Nonnull
  public String getProvince() {
    return province;
  }

  public void setProvince(@javax.annotation.Nonnull String province) {
    this.province = province;
  }


  public CertificateInfoResponse email(@javax.annotation.Nullable String email) {
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


  public CertificateInfoResponse sujet(@javax.annotation.Nonnull String sujet) {
    this.sujet = sujet;
    return this;
  }

  /**
   * Sujet complet (RFC4514)
   * @return sujet
   */
  @javax.annotation.Nonnull
  public String getSujet() {
    return sujet;
  }

  public void setSujet(@javax.annotation.Nonnull String sujet) {
    this.sujet = sujet;
  }


  public CertificateInfoResponse emetteur(@javax.annotation.Nonnull String emetteur) {
    this.emetteur = emetteur;
    return this;
  }

  /**
   * Émetteur (auto-signé &#x3D; même que sujet)
   * @return emetteur
   */
  @javax.annotation.Nonnull
  public String getEmetteur() {
    return emetteur;
  }

  public void setEmetteur(@javax.annotation.Nonnull String emetteur) {
    this.emetteur = emetteur;
  }


  public CertificateInfoResponse numeroSerie(@javax.annotation.Nonnull Integer numeroSerie) {
    this.numeroSerie = numeroSerie;
    return this;
  }

  /**
   * Numéro de série du certificat
   * @return numeroSerie
   */
  @javax.annotation.Nonnull
  public Integer getNumeroSerie() {
    return numeroSerie;
  }

  public void setNumeroSerie(@javax.annotation.Nonnull Integer numeroSerie) {
    this.numeroSerie = numeroSerie;
  }


  public CertificateInfoResponse valideDu(@javax.annotation.Nonnull String valideDu) {
    this.valideDu = valideDu;
    return this;
  }

  /**
   * Date de début de validité (ISO 8601)
   * @return valideDu
   */
  @javax.annotation.Nonnull
  public String getValideDu() {
    return valideDu;
  }

  public void setValideDu(@javax.annotation.Nonnull String valideDu) {
    this.valideDu = valideDu;
  }


  public CertificateInfoResponse valideAu(@javax.annotation.Nonnull String valideAu) {
    this.valideAu = valideAu;
    return this;
  }

  /**
   * Date de fin de validité (ISO 8601)
   * @return valideAu
   */
  @javax.annotation.Nonnull
  public String getValideAu() {
    return valideAu;
  }

  public void setValideAu(@javax.annotation.Nonnull String valideAu) {
    this.valideAu = valideAu;
  }


  public CertificateInfoResponse algorithme(@javax.annotation.Nonnull String algorithme) {
    this.algorithme = algorithme;
    return this;
  }

  /**
   * Algorithme de signature
   * @return algorithme
   */
  @javax.annotation.Nonnull
  public String getAlgorithme() {
    return algorithme;
  }

  public void setAlgorithme(@javax.annotation.Nonnull String algorithme) {
    this.algorithme = algorithme;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CertificateInfoResponse certificateInfoResponse = (CertificateInfoResponse) o;
    return Objects.equals(this.cn, certificateInfoResponse.cn) &&
        Objects.equals(this.organisation, certificateInfoResponse.organisation) &&
        Objects.equals(this.pays, certificateInfoResponse.pays) &&
        Objects.equals(this.ville, certificateInfoResponse.ville) &&
        Objects.equals(this.province, certificateInfoResponse.province) &&
        Objects.equals(this.email, certificateInfoResponse.email) &&
        Objects.equals(this.sujet, certificateInfoResponse.sujet) &&
        Objects.equals(this.emetteur, certificateInfoResponse.emetteur) &&
        Objects.equals(this.numeroSerie, certificateInfoResponse.numeroSerie) &&
        Objects.equals(this.valideDu, certificateInfoResponse.valideDu) &&
        Objects.equals(this.valideAu, certificateInfoResponse.valideAu) &&
        Objects.equals(this.algorithme, certificateInfoResponse.algorithme);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(cn, organisation, pays, ville, province, email, sujet, emetteur, numeroSerie, valideDu, valideAu, algorithme);
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
    sb.append("class CertificateInfoResponse {\n");
    sb.append("    cn: ").append(toIndentedString(cn)).append("\n");
    sb.append("    organisation: ").append(toIndentedString(organisation)).append("\n");
    sb.append("    pays: ").append(toIndentedString(pays)).append("\n");
    sb.append("    ville: ").append(toIndentedString(ville)).append("\n");
    sb.append("    province: ").append(toIndentedString(province)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    sujet: ").append(toIndentedString(sujet)).append("\n");
    sb.append("    emetteur: ").append(toIndentedString(emetteur)).append("\n");
    sb.append("    numeroSerie: ").append(toIndentedString(numeroSerie)).append("\n");
    sb.append("    valideDu: ").append(toIndentedString(valideDu)).append("\n");
    sb.append("    valideAu: ").append(toIndentedString(valideAu)).append("\n");
    sb.append("    algorithme: ").append(toIndentedString(algorithme)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("cn", "organisation", "pays", "ville", "province", "email", "sujet", "emetteur", "numero_serie", "valide_du", "valide_au", "algorithme"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(Arrays.asList("cn", "organisation", "pays", "ville", "province", "sujet", "emetteur", "numero_serie", "valide_du", "valide_au", "algorithme"));
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to CertificateInfoResponse
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!CertificateInfoResponse.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field(s) %s in CertificateInfoResponse is not found in the empty JSON string", CertificateInfoResponse.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!CertificateInfoResponse.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The field `%s` in the JSON string is not defined in the `CertificateInfoResponse` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : CertificateInfoResponse.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (!jsonObj.get("cn").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `cn` to be a primitive type in the JSON string but got `%s`", jsonObj.get("cn").toString()));
      }
      if (!jsonObj.get("organisation").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `organisation` to be a primitive type in the JSON string but got `%s`", jsonObj.get("organisation").toString()));
      }
      if (!jsonObj.get("pays").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `pays` to be a primitive type in the JSON string but got `%s`", jsonObj.get("pays").toString()));
      }
      if (!jsonObj.get("ville").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `ville` to be a primitive type in the JSON string but got `%s`", jsonObj.get("ville").toString()));
      }
      if (!jsonObj.get("province").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `province` to be a primitive type in the JSON string but got `%s`", jsonObj.get("province").toString()));
      }
      if ((jsonObj.get("email") != null && !jsonObj.get("email").isJsonNull()) && !jsonObj.get("email").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `email` to be a primitive type in the JSON string but got `%s`", jsonObj.get("email").toString()));
      }
      if (!jsonObj.get("sujet").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `sujet` to be a primitive type in the JSON string but got `%s`", jsonObj.get("sujet").toString()));
      }
      if (!jsonObj.get("emetteur").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `emetteur` to be a primitive type in the JSON string but got `%s`", jsonObj.get("emetteur").toString()));
      }
      if (!jsonObj.get("valide_du").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `valide_du` to be a primitive type in the JSON string but got `%s`", jsonObj.get("valide_du").toString()));
      }
      if (!jsonObj.get("valide_au").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `valide_au` to be a primitive type in the JSON string but got `%s`", jsonObj.get("valide_au").toString()));
      }
      if (!jsonObj.get("algorithme").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `algorithme` to be a primitive type in the JSON string but got `%s`", jsonObj.get("algorithme").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!CertificateInfoResponse.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'CertificateInfoResponse' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<CertificateInfoResponse> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(CertificateInfoResponse.class));

       return (TypeAdapter<T>) new TypeAdapter<CertificateInfoResponse>() {
           @Override
           public void write(JsonWriter out, CertificateInfoResponse value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public CertificateInfoResponse read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of CertificateInfoResponse given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of CertificateInfoResponse
   * @throws IOException if the JSON string is invalid with respect to CertificateInfoResponse
   */
  public static CertificateInfoResponse fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, CertificateInfoResponse.class);
  }

  /**
   * Convert an instance of CertificateInfoResponse to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

