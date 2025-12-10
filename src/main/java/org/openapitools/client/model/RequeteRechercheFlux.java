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
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.client.model.DirectionFlux;
import org.openapitools.client.model.StatutAcquittement;
import org.openapitools.client.model.TypeFlux;
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
 * Requête pour rechercher des flux soumis
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-12-10T09:35:31.529791871Z[Etc/UTC]", comments = "Generator version: 7.18.0-SNAPSHOT")
public class RequeteRechercheFlux {
  public static final String SERIALIZED_NAME_DATE_MAJ_APRES = "date_maj_apres";
  @SerializedName(SERIALIZED_NAME_DATE_MAJ_APRES)
  @javax.annotation.Nullable
  private OffsetDateTime dateMajApres;

  public static final String SERIALIZED_NAME_DATE_MAJ_AVANT = "date_maj_avant";
  @SerializedName(SERIALIZED_NAME_DATE_MAJ_AVANT)
  @javax.annotation.Nullable
  private OffsetDateTime dateMajAvant;

  public static final String SERIALIZED_NAME_TYPE_FLUX = "type_flux";
  @SerializedName(SERIALIZED_NAME_TYPE_FLUX)
  @javax.annotation.Nullable
  private List<TypeFlux> typeFlux;

  public static final String SERIALIZED_NAME_DIRECTION_FLUX = "direction_flux";
  @SerializedName(SERIALIZED_NAME_DIRECTION_FLUX)
  @javax.annotation.Nullable
  private List<DirectionFlux> directionFlux;

  public static final String SERIALIZED_NAME_TRACKING_ID = "tracking_id";
  @SerializedName(SERIALIZED_NAME_TRACKING_ID)
  @javax.annotation.Nullable
  private String trackingId;

  public static final String SERIALIZED_NAME_FLOW_ID = "flow_id";
  @SerializedName(SERIALIZED_NAME_FLOW_ID)
  @javax.annotation.Nullable
  private String flowId;

  public static final String SERIALIZED_NAME_STATUT_ACQUITTEMENT = "statut_acquittement";
  @SerializedName(SERIALIZED_NAME_STATUT_ACQUITTEMENT)
  @javax.annotation.Nullable
  private StatutAcquittement statutAcquittement;

  public static final String SERIALIZED_NAME_OFFSET = "offset";
  @SerializedName(SERIALIZED_NAME_OFFSET)
  @javax.annotation.Nullable
  private Integer offset = 0;

  public static final String SERIALIZED_NAME_LIMIT = "limit";
  @SerializedName(SERIALIZED_NAME_LIMIT)
  @javax.annotation.Nullable
  private Integer limit = 25;

  public RequeteRechercheFlux() {
  }

  public RequeteRechercheFlux dateMajApres(@javax.annotation.Nullable OffsetDateTime dateMajApres) {
    this.dateMajApres = dateMajApres;
    return this;
  }

  /**
   * Get dateMajApres
   * @return dateMajApres
   */
  @javax.annotation.Nullable
  public OffsetDateTime getDateMajApres() {
    return dateMajApres;
  }

  public void setDateMajApres(@javax.annotation.Nullable OffsetDateTime dateMajApres) {
    this.dateMajApres = dateMajApres;
  }


  public RequeteRechercheFlux dateMajAvant(@javax.annotation.Nullable OffsetDateTime dateMajAvant) {
    this.dateMajAvant = dateMajAvant;
    return this;
  }

  /**
   * Get dateMajAvant
   * @return dateMajAvant
   */
  @javax.annotation.Nullable
  public OffsetDateTime getDateMajAvant() {
    return dateMajAvant;
  }

  public void setDateMajAvant(@javax.annotation.Nullable OffsetDateTime dateMajAvant) {
    this.dateMajAvant = dateMajAvant;
  }


  public RequeteRechercheFlux typeFlux(@javax.annotation.Nullable List<TypeFlux> typeFlux) {
    this.typeFlux = typeFlux;
    return this;
  }

  public RequeteRechercheFlux addTypeFluxItem(TypeFlux typeFluxItem) {
    if (this.typeFlux == null) {
      this.typeFlux = new ArrayList<>();
    }
    this.typeFlux.add(typeFluxItem);
    return this;
  }

  /**
   * Get typeFlux
   * @return typeFlux
   */
  @javax.annotation.Nullable
  public List<TypeFlux> getTypeFlux() {
    return typeFlux;
  }

  public void setTypeFlux(@javax.annotation.Nullable List<TypeFlux> typeFlux) {
    this.typeFlux = typeFlux;
  }


  public RequeteRechercheFlux directionFlux(@javax.annotation.Nullable List<DirectionFlux> directionFlux) {
    this.directionFlux = directionFlux;
    return this;
  }

  public RequeteRechercheFlux addDirectionFluxItem(DirectionFlux directionFluxItem) {
    if (this.directionFlux == null) {
      this.directionFlux = new ArrayList<>();
    }
    this.directionFlux.add(directionFluxItem);
    return this;
  }

  /**
   * Get directionFlux
   * @return directionFlux
   */
  @javax.annotation.Nullable
  public List<DirectionFlux> getDirectionFlux() {
    return directionFlux;
  }

  public void setDirectionFlux(@javax.annotation.Nullable List<DirectionFlux> directionFlux) {
    this.directionFlux = directionFlux;
  }


  public RequeteRechercheFlux trackingId(@javax.annotation.Nullable String trackingId) {
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


  public RequeteRechercheFlux flowId(@javax.annotation.Nullable String flowId) {
    this.flowId = flowId;
    return this;
  }

  /**
   * Get flowId
   * @return flowId
   */
  @javax.annotation.Nullable
  public String getFlowId() {
    return flowId;
  }

  public void setFlowId(@javax.annotation.Nullable String flowId) {
    this.flowId = flowId;
  }


  public RequeteRechercheFlux statutAcquittement(@javax.annotation.Nullable StatutAcquittement statutAcquittement) {
    this.statutAcquittement = statutAcquittement;
    return this;
  }

  /**
   * Get statutAcquittement
   * @return statutAcquittement
   */
  @javax.annotation.Nullable
  public StatutAcquittement getStatutAcquittement() {
    return statutAcquittement;
  }

  public void setStatutAcquittement(@javax.annotation.Nullable StatutAcquittement statutAcquittement) {
    this.statutAcquittement = statutAcquittement;
  }


  public RequeteRechercheFlux offset(@javax.annotation.Nullable Integer offset) {
    this.offset = offset;
    return this;
  }

  /**
   * Décalage pour la pagination
   * minimum: 0
   * @return offset
   */
  @javax.annotation.Nullable
  public Integer getOffset() {
    return offset;
  }

  public void setOffset(@javax.annotation.Nullable Integer offset) {
    this.offset = offset;
  }


  public RequeteRechercheFlux limit(@javax.annotation.Nullable Integer limit) {
    this.limit = limit;
    return this;
  }

  /**
   * Nombre maximum de résultats (max 100)
   * minimum: 1
   * maximum: 100
   * @return limit
   */
  @javax.annotation.Nullable
  public Integer getLimit() {
    return limit;
  }

  public void setLimit(@javax.annotation.Nullable Integer limit) {
    this.limit = limit;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RequeteRechercheFlux requeteRechercheFlux = (RequeteRechercheFlux) o;
    return Objects.equals(this.dateMajApres, requeteRechercheFlux.dateMajApres) &&
        Objects.equals(this.dateMajAvant, requeteRechercheFlux.dateMajAvant) &&
        Objects.equals(this.typeFlux, requeteRechercheFlux.typeFlux) &&
        Objects.equals(this.directionFlux, requeteRechercheFlux.directionFlux) &&
        Objects.equals(this.trackingId, requeteRechercheFlux.trackingId) &&
        Objects.equals(this.flowId, requeteRechercheFlux.flowId) &&
        Objects.equals(this.statutAcquittement, requeteRechercheFlux.statutAcquittement) &&
        Objects.equals(this.offset, requeteRechercheFlux.offset) &&
        Objects.equals(this.limit, requeteRechercheFlux.limit);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(dateMajApres, dateMajAvant, typeFlux, directionFlux, trackingId, flowId, statutAcquittement, offset, limit);
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
    sb.append("class RequeteRechercheFlux {\n");
    sb.append("    dateMajApres: ").append(toIndentedString(dateMajApres)).append("\n");
    sb.append("    dateMajAvant: ").append(toIndentedString(dateMajAvant)).append("\n");
    sb.append("    typeFlux: ").append(toIndentedString(typeFlux)).append("\n");
    sb.append("    directionFlux: ").append(toIndentedString(directionFlux)).append("\n");
    sb.append("    trackingId: ").append(toIndentedString(trackingId)).append("\n");
    sb.append("    flowId: ").append(toIndentedString(flowId)).append("\n");
    sb.append("    statutAcquittement: ").append(toIndentedString(statutAcquittement)).append("\n");
    sb.append("    offset: ").append(toIndentedString(offset)).append("\n");
    sb.append("    limit: ").append(toIndentedString(limit)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("date_maj_apres", "date_maj_avant", "type_flux", "direction_flux", "tracking_id", "flow_id", "statut_acquittement", "offset", "limit"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(0);
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to RequeteRechercheFlux
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!RequeteRechercheFlux.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field(s) %s in RequeteRechercheFlux is not found in the empty JSON string", RequeteRechercheFlux.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!RequeteRechercheFlux.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The field `%s` in the JSON string is not defined in the `RequeteRechercheFlux` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      // ensure the optional json data is an array if present
      if (jsonObj.get("type_flux") != null && !jsonObj.get("type_flux").isJsonNull() && !jsonObj.get("type_flux").isJsonArray()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `type_flux` to be an array in the JSON string but got `%s`", jsonObj.get("type_flux").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("direction_flux") != null && !jsonObj.get("direction_flux").isJsonNull() && !jsonObj.get("direction_flux").isJsonArray()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `direction_flux` to be an array in the JSON string but got `%s`", jsonObj.get("direction_flux").toString()));
      }
      if ((jsonObj.get("tracking_id") != null && !jsonObj.get("tracking_id").isJsonNull()) && !jsonObj.get("tracking_id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `tracking_id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("tracking_id").toString()));
      }
      if ((jsonObj.get("flow_id") != null && !jsonObj.get("flow_id").isJsonNull()) && !jsonObj.get("flow_id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `flow_id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("flow_id").toString()));
      }
      // validate the optional field `statut_acquittement`
      if (jsonObj.get("statut_acquittement") != null && !jsonObj.get("statut_acquittement").isJsonNull()) {
        StatutAcquittement.validateJsonElement(jsonObj.get("statut_acquittement"));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!RequeteRechercheFlux.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'RequeteRechercheFlux' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<RequeteRechercheFlux> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(RequeteRechercheFlux.class));

       return (TypeAdapter<T>) new TypeAdapter<RequeteRechercheFlux>() {
           @Override
           public void write(JsonWriter out, RequeteRechercheFlux value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public RequeteRechercheFlux read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of RequeteRechercheFlux given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of RequeteRechercheFlux
   * @throws IOException if the JSON string is invalid with respect to RequeteRechercheFlux
   */
  public static RequeteRechercheFlux fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, RequeteRechercheFlux.class);
  }

  /**
   * Convert an instance of RequeteRechercheFlux to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

