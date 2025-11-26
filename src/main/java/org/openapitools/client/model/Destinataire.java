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
import org.openapitools.client.model.AdresseElectronique;
import org.openapitools.client.model.AdressePostale;
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
 * Informations sur le destinataire de la facture (le client).
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-11-26T13:43:38.907758395Z[Etc/UTC]", comments = "Generator version: 7.18.0-SNAPSHOT")
public class Destinataire {
  public static final String SERIALIZED_NAME_ADRESSE_ELECTRONIQUE = "adresseElectronique";
  @SerializedName(SERIALIZED_NAME_ADRESSE_ELECTRONIQUE)
  @javax.annotation.Nonnull
  private AdresseElectronique adresseElectronique;

  public static final String SERIALIZED_NAME_CODE_SERVICE_EXECUTANT = "codeServiceExecutant";
  @SerializedName(SERIALIZED_NAME_CODE_SERVICE_EXECUTANT)
  @javax.annotation.Nullable
  private String codeServiceExecutant;

  public static final String SERIALIZED_NAME_NOM = "nom";
  @SerializedName(SERIALIZED_NAME_NOM)
  @javax.annotation.Nullable
  private String nom;

  public static final String SERIALIZED_NAME_SIREN = "siren";
  @SerializedName(SERIALIZED_NAME_SIREN)
  @javax.annotation.Nullable
  private String siren;

  public static final String SERIALIZED_NAME_SIRET = "siret";
  @SerializedName(SERIALIZED_NAME_SIRET)
  @javax.annotation.Nullable
  private String siret;

  public static final String SERIALIZED_NAME_ADRESSE_POSTALE = "adressePostale";
  @SerializedName(SERIALIZED_NAME_ADRESSE_POSTALE)
  @javax.annotation.Nullable
  private AdressePostale adressePostale;

  public Destinataire() {
  }

  public Destinataire adresseElectronique(@javax.annotation.Nonnull AdresseElectronique adresseElectronique) {
    this.adresseElectronique = adresseElectronique;
    return this;
  }

  /**
   * Get adresseElectronique
   * @return adresseElectronique
   */
  @javax.annotation.Nonnull
  public AdresseElectronique getAdresseElectronique() {
    return adresseElectronique;
  }

  public void setAdresseElectronique(@javax.annotation.Nonnull AdresseElectronique adresseElectronique) {
    this.adresseElectronique = adresseElectronique;
  }


  public Destinataire codeServiceExecutant(@javax.annotation.Nullable String codeServiceExecutant) {
    this.codeServiceExecutant = codeServiceExecutant;
    return this;
  }

  /**
   * Get codeServiceExecutant
   * @return codeServiceExecutant
   */
  @javax.annotation.Nullable
  public String getCodeServiceExecutant() {
    return codeServiceExecutant;
  }

  public void setCodeServiceExecutant(@javax.annotation.Nullable String codeServiceExecutant) {
    this.codeServiceExecutant = codeServiceExecutant;
  }


  public Destinataire nom(@javax.annotation.Nullable String nom) {
    this.nom = nom;
    return this;
  }

  /**
   * Get nom
   * @return nom
   */
  @javax.annotation.Nullable
  public String getNom() {
    return nom;
  }

  public void setNom(@javax.annotation.Nullable String nom) {
    this.nom = nom;
  }


  public Destinataire siren(@javax.annotation.Nullable String siren) {
    this.siren = siren;
    return this;
  }

  /**
   * Get siren
   * @return siren
   */
  @javax.annotation.Nullable
  public String getSiren() {
    return siren;
  }

  public void setSiren(@javax.annotation.Nullable String siren) {
    this.siren = siren;
  }


  public Destinataire siret(@javax.annotation.Nullable String siret) {
    this.siret = siret;
    return this;
  }

  /**
   * Get siret
   * @return siret
   */
  @javax.annotation.Nullable
  public String getSiret() {
    return siret;
  }

  public void setSiret(@javax.annotation.Nullable String siret) {
    this.siret = siret;
  }


  public Destinataire adressePostale(@javax.annotation.Nullable AdressePostale adressePostale) {
    this.adressePostale = adressePostale;
    return this;
  }

  /**
   * Get adressePostale
   * @return adressePostale
   */
  @javax.annotation.Nullable
  public AdressePostale getAdressePostale() {
    return adressePostale;
  }

  public void setAdressePostale(@javax.annotation.Nullable AdressePostale adressePostale) {
    this.adressePostale = adressePostale;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Destinataire destinataire = (Destinataire) o;
    return Objects.equals(this.adresseElectronique, destinataire.adresseElectronique) &&
        Objects.equals(this.codeServiceExecutant, destinataire.codeServiceExecutant) &&
        Objects.equals(this.nom, destinataire.nom) &&
        Objects.equals(this.siren, destinataire.siren) &&
        Objects.equals(this.siret, destinataire.siret) &&
        Objects.equals(this.adressePostale, destinataire.adressePostale);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(adresseElectronique, codeServiceExecutant, nom, siren, siret, adressePostale);
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
    sb.append("class Destinataire {\n");
    sb.append("    adresseElectronique: ").append(toIndentedString(adresseElectronique)).append("\n");
    sb.append("    codeServiceExecutant: ").append(toIndentedString(codeServiceExecutant)).append("\n");
    sb.append("    nom: ").append(toIndentedString(nom)).append("\n");
    sb.append("    siren: ").append(toIndentedString(siren)).append("\n");
    sb.append("    siret: ").append(toIndentedString(siret)).append("\n");
    sb.append("    adressePostale: ").append(toIndentedString(adressePostale)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("adresseElectronique", "codeServiceExecutant", "nom", "siren", "siret", "adressePostale"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(Arrays.asList("adresseElectronique"));
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to Destinataire
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!Destinataire.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field(s) %s in Destinataire is not found in the empty JSON string", Destinataire.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!Destinataire.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The field `%s` in the JSON string is not defined in the `Destinataire` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : Destinataire.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      // validate the required field `adresseElectronique`
      AdresseElectronique.validateJsonElement(jsonObj.get("adresseElectronique"));
      if ((jsonObj.get("codeServiceExecutant") != null && !jsonObj.get("codeServiceExecutant").isJsonNull()) && !jsonObj.get("codeServiceExecutant").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `codeServiceExecutant` to be a primitive type in the JSON string but got `%s`", jsonObj.get("codeServiceExecutant").toString()));
      }
      if ((jsonObj.get("nom") != null && !jsonObj.get("nom").isJsonNull()) && !jsonObj.get("nom").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `nom` to be a primitive type in the JSON string but got `%s`", jsonObj.get("nom").toString()));
      }
      if ((jsonObj.get("siren") != null && !jsonObj.get("siren").isJsonNull()) && !jsonObj.get("siren").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `siren` to be a primitive type in the JSON string but got `%s`", jsonObj.get("siren").toString()));
      }
      if ((jsonObj.get("siret") != null && !jsonObj.get("siret").isJsonNull()) && !jsonObj.get("siret").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `siret` to be a primitive type in the JSON string but got `%s`", jsonObj.get("siret").toString()));
      }
      // validate the optional field `adressePostale`
      if (jsonObj.get("adressePostale") != null && !jsonObj.get("adressePostale").isJsonNull()) {
        AdressePostale.validateJsonElement(jsonObj.get("adressePostale"));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!Destinataire.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'Destinataire' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<Destinataire> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(Destinataire.class));

       return (TypeAdapter<T>) new TypeAdapter<Destinataire>() {
           @Override
           public void write(JsonWriter out, Destinataire value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public Destinataire read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of Destinataire given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of Destinataire
   * @throws IOException if the JSON string is invalid with respect to Destinataire
   */
  public static Destinataire fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, Destinataire.class);
  }

  /**
   * Convert an instance of Destinataire to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

