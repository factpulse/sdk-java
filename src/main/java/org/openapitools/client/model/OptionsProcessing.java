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
import org.openapitools.client.model.ProfilAPI;

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
 * Options de traitement pour la génération et la soumission.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-11-26T07:35:36.724862333Z[Etc/UTC]", comments = "Generator version: 7.18.0-SNAPSHOT")
public class OptionsProcessing {
  public static final String SERIALIZED_NAME_PROFIL_FACTURX = "profil_facturx";
  @SerializedName(SERIALIZED_NAME_PROFIL_FACTURX)
  @javax.annotation.Nullable
  private ProfilAPI profilFacturx = ProfilAPI.EN16931;

  public static final String SERIALIZED_NAME_AUTO_ENRICHIR = "auto_enrichir";
  @SerializedName(SERIALIZED_NAME_AUTO_ENRICHIR)
  @javax.annotation.Nullable
  private Boolean autoEnrichir = true;

  public static final String SERIALIZED_NAME_VALIDER = "valider";
  @SerializedName(SERIALIZED_NAME_VALIDER)
  @javax.annotation.Nullable
  private Boolean valider = true;

  public static final String SERIALIZED_NAME_VERIFIER_PARAMETRES_DESTINATION = "verifier_parametres_destination";
  @SerializedName(SERIALIZED_NAME_VERIFIER_PARAMETRES_DESTINATION)
  @javax.annotation.Nullable
  private Boolean verifierParametresDestination = true;

  public OptionsProcessing() {
  }

  public OptionsProcessing profilFacturx(@javax.annotation.Nullable ProfilAPI profilFacturx) {
    this.profilFacturx = profilFacturx;
    return this;
  }

  /**
   * Profil Factur-X à utiliser
   * @return profilFacturx
   */
  @javax.annotation.Nullable
  public ProfilAPI getProfilFacturx() {
    return profilFacturx;
  }

  public void setProfilFacturx(@javax.annotation.Nullable ProfilAPI profilFacturx) {
    this.profilFacturx = profilFacturx;
  }


  public OptionsProcessing autoEnrichir(@javax.annotation.Nullable Boolean autoEnrichir) {
    this.autoEnrichir = autoEnrichir;
    return this;
  }

  /**
   * Auto-enrichir les données (APIs Entreprises, Chorus Pro, etc.)
   * @return autoEnrichir
   */
  @javax.annotation.Nullable
  public Boolean getAutoEnrichir() {
    return autoEnrichir;
  }

  public void setAutoEnrichir(@javax.annotation.Nullable Boolean autoEnrichir) {
    this.autoEnrichir = autoEnrichir;
  }


  public OptionsProcessing valider(@javax.annotation.Nullable Boolean valider) {
    this.valider = valider;
    return this;
  }

  /**
   * Valider le XML Factur-X avec Schematron
   * @return valider
   */
  @javax.annotation.Nullable
  public Boolean getValider() {
    return valider;
  }

  public void setValider(@javax.annotation.Nullable Boolean valider) {
    this.valider = valider;
  }


  public OptionsProcessing verifierParametresDestination(@javax.annotation.Nullable Boolean verifierParametresDestination) {
    this.verifierParametresDestination = verifierParametresDestination;
    return this;
  }

  /**
   * Vérifier les paramètres requis par la destination (ex: code_service pour Chorus)
   * @return verifierParametresDestination
   */
  @javax.annotation.Nullable
  public Boolean getVerifierParametresDestination() {
    return verifierParametresDestination;
  }

  public void setVerifierParametresDestination(@javax.annotation.Nullable Boolean verifierParametresDestination) {
    this.verifierParametresDestination = verifierParametresDestination;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OptionsProcessing optionsProcessing = (OptionsProcessing) o;
    return Objects.equals(this.profilFacturx, optionsProcessing.profilFacturx) &&
        Objects.equals(this.autoEnrichir, optionsProcessing.autoEnrichir) &&
        Objects.equals(this.valider, optionsProcessing.valider) &&
        Objects.equals(this.verifierParametresDestination, optionsProcessing.verifierParametresDestination);
  }

  @Override
  public int hashCode() {
    return Objects.hash(profilFacturx, autoEnrichir, valider, verifierParametresDestination);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OptionsProcessing {\n");
    sb.append("    profilFacturx: ").append(toIndentedString(profilFacturx)).append("\n");
    sb.append("    autoEnrichir: ").append(toIndentedString(autoEnrichir)).append("\n");
    sb.append("    valider: ").append(toIndentedString(valider)).append("\n");
    sb.append("    verifierParametresDestination: ").append(toIndentedString(verifierParametresDestination)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("profil_facturx", "auto_enrichir", "valider", "verifier_parametres_destination"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(0);
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to OptionsProcessing
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!OptionsProcessing.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field(s) %s in OptionsProcessing is not found in the empty JSON string", OptionsProcessing.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!OptionsProcessing.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The field `%s` in the JSON string is not defined in the `OptionsProcessing` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      // validate the optional field `profil_facturx`
      if (jsonObj.get("profil_facturx") != null && !jsonObj.get("profil_facturx").isJsonNull()) {
        ProfilAPI.validateJsonElement(jsonObj.get("profil_facturx"));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!OptionsProcessing.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'OptionsProcessing' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<OptionsProcessing> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(OptionsProcessing.class));

       return (TypeAdapter<T>) new TypeAdapter<OptionsProcessing>() {
           @Override
           public void write(JsonWriter out, OptionsProcessing value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public OptionsProcessing read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of OptionsProcessing given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of OptionsProcessing
   * @throws IOException if the JSON string is invalid with respect to OptionsProcessing
   */
  public static OptionsProcessing fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, OptionsProcessing.class);
  }

  /**
   * Convert an instance of OptionsProcessing to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

