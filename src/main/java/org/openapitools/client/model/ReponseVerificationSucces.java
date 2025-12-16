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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.client.model.ChampVerifieSchema;
import org.openapitools.client.model.DimensionPageSchema;
import org.openapitools.client.model.NoteObligatoireSchema;
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
 * Réponse de vérification réussie avec données unifiées.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-12-16T21:07:40.325889911Z[Etc/UTC]", comments = "Generator version: 7.18.0-SNAPSHOT")
public class ReponseVerificationSucces {
  public static final String SERIALIZED_NAME_EST_CONFORME = "est_conforme";
  @SerializedName(SERIALIZED_NAME_EST_CONFORME)
  @javax.annotation.Nonnull
  private Boolean estConforme;

  public static final String SERIALIZED_NAME_SCORE_CONFORMITE = "score_conformite";
  @SerializedName(SERIALIZED_NAME_SCORE_CONFORMITE)
  @javax.annotation.Nonnull
  private BigDecimal scoreConformite;

  public static final String SERIALIZED_NAME_CHAMPS_VERIFIES = "champs_verifies";
  @SerializedName(SERIALIZED_NAME_CHAMPS_VERIFIES)
  @javax.annotation.Nullable
  private Integer champsVerifies = 0;

  public static final String SERIALIZED_NAME_CHAMPS_CONFORMES = "champs_conformes";
  @SerializedName(SERIALIZED_NAME_CHAMPS_CONFORMES)
  @javax.annotation.Nullable
  private Integer champsConformes = 0;

  public static final String SERIALIZED_NAME_EST_FACTURX = "est_facturx";
  @SerializedName(SERIALIZED_NAME_EST_FACTURX)
  @javax.annotation.Nullable
  private Boolean estFacturx = false;

  public static final String SERIALIZED_NAME_PROFIL_FACTURX = "profil_facturx";
  @SerializedName(SERIALIZED_NAME_PROFIL_FACTURX)
  @javax.annotation.Nullable
  private String profilFacturx;

  public static final String SERIALIZED_NAME_CHAMPS = "champs";
  @SerializedName(SERIALIZED_NAME_CHAMPS)
  @javax.annotation.Nullable
  private List<ChampVerifieSchema> champs = new ArrayList<>();

  public static final String SERIALIZED_NAME_NOTES_OBLIGATOIRES = "notes_obligatoires";
  @SerializedName(SERIALIZED_NAME_NOTES_OBLIGATOIRES)
  @javax.annotation.Nullable
  private List<NoteObligatoireSchema> notesObligatoires = new ArrayList<>();

  public static final String SERIALIZED_NAME_DIMENSIONS_PAGES = "dimensions_pages";
  @SerializedName(SERIALIZED_NAME_DIMENSIONS_PAGES)
  @javax.annotation.Nullable
  private List<DimensionPageSchema> dimensionsPages = new ArrayList<>();

  public static final String SERIALIZED_NAME_AVERTISSEMENTS = "avertissements";
  @SerializedName(SERIALIZED_NAME_AVERTISSEMENTS)
  @javax.annotation.Nullable
  private List<String> avertissements = new ArrayList<>();

  public ReponseVerificationSucces() {
  }

  public ReponseVerificationSucces estConforme(@javax.annotation.Nonnull Boolean estConforme) {
    this.estConforme = estConforme;
    return this;
  }

  /**
   * True si aucun écart critique
   * @return estConforme
   */
  @javax.annotation.Nonnull
  public Boolean getEstConforme() {
    return estConforme;
  }

  public void setEstConforme(@javax.annotation.Nonnull Boolean estConforme) {
    this.estConforme = estConforme;
  }


  public ReponseVerificationSucces scoreConformite(@javax.annotation.Nonnull BigDecimal scoreConformite) {
    this.scoreConformite = scoreConformite;
    return this;
  }

  /**
   * Score de conformité (0-100%)
   * minimum: 0.0
   * maximum: 100.0
   * @return scoreConformite
   */
  @javax.annotation.Nonnull
  public BigDecimal getScoreConformite() {
    return scoreConformite;
  }

  public void setScoreConformite(@javax.annotation.Nonnull BigDecimal scoreConformite) {
    this.scoreConformite = scoreConformite;
  }


  public ReponseVerificationSucces champsVerifies(@javax.annotation.Nullable Integer champsVerifies) {
    this.champsVerifies = champsVerifies;
    return this;
  }

  /**
   * Nombre de champs vérifiés
   * minimum: 0
   * @return champsVerifies
   */
  @javax.annotation.Nullable
  public Integer getChampsVerifies() {
    return champsVerifies;
  }

  public void setChampsVerifies(@javax.annotation.Nullable Integer champsVerifies) {
    this.champsVerifies = champsVerifies;
  }


  public ReponseVerificationSucces champsConformes(@javax.annotation.Nullable Integer champsConformes) {
    this.champsConformes = champsConformes;
    return this;
  }

  /**
   * Nombre de champs conformes
   * minimum: 0
   * @return champsConformes
   */
  @javax.annotation.Nullable
  public Integer getChampsConformes() {
    return champsConformes;
  }

  public void setChampsConformes(@javax.annotation.Nullable Integer champsConformes) {
    this.champsConformes = champsConformes;
  }


  public ReponseVerificationSucces estFacturx(@javax.annotation.Nullable Boolean estFacturx) {
    this.estFacturx = estFacturx;
    return this;
  }

  /**
   * True si le PDF contient du XML Factur-X
   * @return estFacturx
   */
  @javax.annotation.Nullable
  public Boolean getEstFacturx() {
    return estFacturx;
  }

  public void setEstFacturx(@javax.annotation.Nullable Boolean estFacturx) {
    this.estFacturx = estFacturx;
  }


  public ReponseVerificationSucces profilFacturx(@javax.annotation.Nullable String profilFacturx) {
    this.profilFacturx = profilFacturx;
    return this;
  }

  /**
   * Get profilFacturx
   * @return profilFacturx
   */
  @javax.annotation.Nullable
  public String getProfilFacturx() {
    return profilFacturx;
  }

  public void setProfilFacturx(@javax.annotation.Nullable String profilFacturx) {
    this.profilFacturx = profilFacturx;
  }


  public ReponseVerificationSucces champs(@javax.annotation.Nullable List<ChampVerifieSchema> champs) {
    this.champs = champs;
    return this;
  }

  public ReponseVerificationSucces addChampsItem(ChampVerifieSchema champsItem) {
    if (this.champs == null) {
      this.champs = new ArrayList<>();
    }
    this.champs.add(champsItem);
    return this;
  }

  /**
   * Liste des champs vérifiés avec valeurs, statuts et coordonnées PDF
   * @return champs
   */
  @javax.annotation.Nullable
  public List<ChampVerifieSchema> getChamps() {
    return champs;
  }

  public void setChamps(@javax.annotation.Nullable List<ChampVerifieSchema> champs) {
    this.champs = champs;
  }


  public ReponseVerificationSucces notesObligatoires(@javax.annotation.Nullable List<NoteObligatoireSchema> notesObligatoires) {
    this.notesObligatoires = notesObligatoires;
    return this;
  }

  public ReponseVerificationSucces addNotesObligatoiresItem(NoteObligatoireSchema notesObligatoiresItem) {
    if (this.notesObligatoires == null) {
      this.notesObligatoires = new ArrayList<>();
    }
    this.notesObligatoires.add(notesObligatoiresItem);
    return this;
  }

  /**
   * Notes obligatoires (PMT, PMD, AAB) avec localisation PDF
   * @return notesObligatoires
   */
  @javax.annotation.Nullable
  public List<NoteObligatoireSchema> getNotesObligatoires() {
    return notesObligatoires;
  }

  public void setNotesObligatoires(@javax.annotation.Nullable List<NoteObligatoireSchema> notesObligatoires) {
    this.notesObligatoires = notesObligatoires;
  }


  public ReponseVerificationSucces dimensionsPages(@javax.annotation.Nullable List<DimensionPageSchema> dimensionsPages) {
    this.dimensionsPages = dimensionsPages;
    return this;
  }

  public ReponseVerificationSucces addDimensionsPagesItem(DimensionPageSchema dimensionsPagesItem) {
    if (this.dimensionsPages == null) {
      this.dimensionsPages = new ArrayList<>();
    }
    this.dimensionsPages.add(dimensionsPagesItem);
    return this;
  }

  /**
   * Dimensions de chaque page du PDF (largeur, hauteur)
   * @return dimensionsPages
   */
  @javax.annotation.Nullable
  public List<DimensionPageSchema> getDimensionsPages() {
    return dimensionsPages;
  }

  public void setDimensionsPages(@javax.annotation.Nullable List<DimensionPageSchema> dimensionsPages) {
    this.dimensionsPages = dimensionsPages;
  }


  public ReponseVerificationSucces avertissements(@javax.annotation.Nullable List<String> avertissements) {
    this.avertissements = avertissements;
    return this;
  }

  public ReponseVerificationSucces addAvertissementsItem(String avertissementsItem) {
    if (this.avertissements == null) {
      this.avertissements = new ArrayList<>();
    }
    this.avertissements.add(avertissementsItem);
    return this;
  }

  /**
   * Avertissements non bloquants
   * @return avertissements
   */
  @javax.annotation.Nullable
  public List<String> getAvertissements() {
    return avertissements;
  }

  public void setAvertissements(@javax.annotation.Nullable List<String> avertissements) {
    this.avertissements = avertissements;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReponseVerificationSucces reponseVerificationSucces = (ReponseVerificationSucces) o;
    return Objects.equals(this.estConforme, reponseVerificationSucces.estConforme) &&
        Objects.equals(this.scoreConformite, reponseVerificationSucces.scoreConformite) &&
        Objects.equals(this.champsVerifies, reponseVerificationSucces.champsVerifies) &&
        Objects.equals(this.champsConformes, reponseVerificationSucces.champsConformes) &&
        Objects.equals(this.estFacturx, reponseVerificationSucces.estFacturx) &&
        Objects.equals(this.profilFacturx, reponseVerificationSucces.profilFacturx) &&
        Objects.equals(this.champs, reponseVerificationSucces.champs) &&
        Objects.equals(this.notesObligatoires, reponseVerificationSucces.notesObligatoires) &&
        Objects.equals(this.dimensionsPages, reponseVerificationSucces.dimensionsPages) &&
        Objects.equals(this.avertissements, reponseVerificationSucces.avertissements);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(estConforme, scoreConformite, champsVerifies, champsConformes, estFacturx, profilFacturx, champs, notesObligatoires, dimensionsPages, avertissements);
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
    sb.append("class ReponseVerificationSucces {\n");
    sb.append("    estConforme: ").append(toIndentedString(estConforme)).append("\n");
    sb.append("    scoreConformite: ").append(toIndentedString(scoreConformite)).append("\n");
    sb.append("    champsVerifies: ").append(toIndentedString(champsVerifies)).append("\n");
    sb.append("    champsConformes: ").append(toIndentedString(champsConformes)).append("\n");
    sb.append("    estFacturx: ").append(toIndentedString(estFacturx)).append("\n");
    sb.append("    profilFacturx: ").append(toIndentedString(profilFacturx)).append("\n");
    sb.append("    champs: ").append(toIndentedString(champs)).append("\n");
    sb.append("    notesObligatoires: ").append(toIndentedString(notesObligatoires)).append("\n");
    sb.append("    dimensionsPages: ").append(toIndentedString(dimensionsPages)).append("\n");
    sb.append("    avertissements: ").append(toIndentedString(avertissements)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("est_conforme", "score_conformite", "champs_verifies", "champs_conformes", "est_facturx", "profil_facturx", "champs", "notes_obligatoires", "dimensions_pages", "avertissements"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(Arrays.asList("est_conforme", "score_conformite"));
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to ReponseVerificationSucces
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ReponseVerificationSucces.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field(s) %s in ReponseVerificationSucces is not found in the empty JSON string", ReponseVerificationSucces.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!ReponseVerificationSucces.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The field `%s` in the JSON string is not defined in the `ReponseVerificationSucces` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : ReponseVerificationSucces.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("profil_facturx") != null && !jsonObj.get("profil_facturx").isJsonNull()) && !jsonObj.get("profil_facturx").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `profil_facturx` to be a primitive type in the JSON string but got `%s`", jsonObj.get("profil_facturx").toString()));
      }
      if (jsonObj.get("champs") != null && !jsonObj.get("champs").isJsonNull()) {
        JsonArray jsonArraychamps = jsonObj.getAsJsonArray("champs");
        if (jsonArraychamps != null) {
          // ensure the json data is an array
          if (!jsonObj.get("champs").isJsonArray()) {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `champs` to be an array in the JSON string but got `%s`", jsonObj.get("champs").toString()));
          }

          // validate the optional field `champs` (array)
          for (int i = 0; i < jsonArraychamps.size(); i++) {
            ChampVerifieSchema.validateJsonElement(jsonArraychamps.get(i));
          };
        }
      }
      if (jsonObj.get("notes_obligatoires") != null && !jsonObj.get("notes_obligatoires").isJsonNull()) {
        JsonArray jsonArraynotesObligatoires = jsonObj.getAsJsonArray("notes_obligatoires");
        if (jsonArraynotesObligatoires != null) {
          // ensure the json data is an array
          if (!jsonObj.get("notes_obligatoires").isJsonArray()) {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `notes_obligatoires` to be an array in the JSON string but got `%s`", jsonObj.get("notes_obligatoires").toString()));
          }

          // validate the optional field `notes_obligatoires` (array)
          for (int i = 0; i < jsonArraynotesObligatoires.size(); i++) {
            NoteObligatoireSchema.validateJsonElement(jsonArraynotesObligatoires.get(i));
          };
        }
      }
      if (jsonObj.get("dimensions_pages") != null && !jsonObj.get("dimensions_pages").isJsonNull()) {
        JsonArray jsonArraydimensionsPages = jsonObj.getAsJsonArray("dimensions_pages");
        if (jsonArraydimensionsPages != null) {
          // ensure the json data is an array
          if (!jsonObj.get("dimensions_pages").isJsonArray()) {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `dimensions_pages` to be an array in the JSON string but got `%s`", jsonObj.get("dimensions_pages").toString()));
          }

          // validate the optional field `dimensions_pages` (array)
          for (int i = 0; i < jsonArraydimensionsPages.size(); i++) {
            DimensionPageSchema.validateJsonElement(jsonArraydimensionsPages.get(i));
          };
        }
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("avertissements") != null && !jsonObj.get("avertissements").isJsonNull() && !jsonObj.get("avertissements").isJsonArray()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `avertissements` to be an array in the JSON string but got `%s`", jsonObj.get("avertissements").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ReponseVerificationSucces.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ReponseVerificationSucces' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ReponseVerificationSucces> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ReponseVerificationSucces.class));

       return (TypeAdapter<T>) new TypeAdapter<ReponseVerificationSucces>() {
           @Override
           public void write(JsonWriter out, ReponseVerificationSucces value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public ReponseVerificationSucces read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of ReponseVerificationSucces given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of ReponseVerificationSucces
   * @throws IOException if the JSON string is invalid with respect to ReponseVerificationSucces
   */
  public static ReponseVerificationSucces fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ReponseVerificationSucces.class);
  }

  /**
   * Convert an instance of ReponseVerificationSucces to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

