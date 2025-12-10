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
import java.util.Arrays;

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
 * Coordonnées d&#39;une zone rectangulaire dans le PDF.  Les coordonnées sont en points PDF (1 point &#x3D; 1/72 pouce). L&#39;origine (0,0) est en bas à gauche de la page.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-12-10T06:32:02.575358678Z[Etc/UTC]", comments = "Generator version: 7.18.0-SNAPSHOT")
public class BoundingBoxSchema {
  public static final String SERIALIZED_NAME_X0 = "x0";
  @SerializedName(SERIALIZED_NAME_X0)
  @javax.annotation.Nonnull
  private BigDecimal x0;

  public static final String SERIALIZED_NAME_Y0 = "y0";
  @SerializedName(SERIALIZED_NAME_Y0)
  @javax.annotation.Nonnull
  private BigDecimal y0;

  public static final String SERIALIZED_NAME_X1 = "x1";
  @SerializedName(SERIALIZED_NAME_X1)
  @javax.annotation.Nonnull
  private BigDecimal x1;

  public static final String SERIALIZED_NAME_Y1 = "y1";
  @SerializedName(SERIALIZED_NAME_Y1)
  @javax.annotation.Nonnull
  private BigDecimal y1;

  public static final String SERIALIZED_NAME_PAGE = "page";
  @SerializedName(SERIALIZED_NAME_PAGE)
  @javax.annotation.Nullable
  private Integer page = 0;

  public static final String SERIALIZED_NAME_WIDTH = "width";
  @SerializedName(SERIALIZED_NAME_WIDTH)
  @javax.annotation.Nonnull
  private BigDecimal width;

  public static final String SERIALIZED_NAME_HEIGHT = "height";
  @SerializedName(SERIALIZED_NAME_HEIGHT)
  @javax.annotation.Nonnull
  private BigDecimal height;

  public BoundingBoxSchema() {
  }

  public BoundingBoxSchema x0(@javax.annotation.Nonnull BigDecimal x0) {
    this.x0 = x0;
    return this;
  }

  /**
   * Coordonnée X gauche
   * @return x0
   */
  @javax.annotation.Nonnull
  public BigDecimal getX0() {
    return x0;
  }

  public void setX0(@javax.annotation.Nonnull BigDecimal x0) {
    this.x0 = x0;
  }


  public BoundingBoxSchema y0(@javax.annotation.Nonnull BigDecimal y0) {
    this.y0 = y0;
    return this;
  }

  /**
   * Coordonnée Y bas
   * @return y0
   */
  @javax.annotation.Nonnull
  public BigDecimal getY0() {
    return y0;
  }

  public void setY0(@javax.annotation.Nonnull BigDecimal y0) {
    this.y0 = y0;
  }


  public BoundingBoxSchema x1(@javax.annotation.Nonnull BigDecimal x1) {
    this.x1 = x1;
    return this;
  }

  /**
   * Coordonnée X droite
   * @return x1
   */
  @javax.annotation.Nonnull
  public BigDecimal getX1() {
    return x1;
  }

  public void setX1(@javax.annotation.Nonnull BigDecimal x1) {
    this.x1 = x1;
  }


  public BoundingBoxSchema y1(@javax.annotation.Nonnull BigDecimal y1) {
    this.y1 = y1;
    return this;
  }

  /**
   * Coordonnée Y haut
   * @return y1
   */
  @javax.annotation.Nonnull
  public BigDecimal getY1() {
    return y1;
  }

  public void setY1(@javax.annotation.Nonnull BigDecimal y1) {
    this.y1 = y1;
  }


  public BoundingBoxSchema page(@javax.annotation.Nullable Integer page) {
    this.page = page;
    return this;
  }

  /**
   * Numéro de page (0-indexed)
   * minimum: 0
   * @return page
   */
  @javax.annotation.Nullable
  public Integer getPage() {
    return page;
  }

  public void setPage(@javax.annotation.Nullable Integer page) {
    this.page = page;
  }


  public BoundingBoxSchema width(@javax.annotation.Nonnull BigDecimal width) {
    this.width = width;
    return this;
  }

  /**
   * Largeur de la zone
   * @return width
   */
  @javax.annotation.Nonnull
  public BigDecimal getWidth() {
    return width;
  }

  public void setWidth(@javax.annotation.Nonnull BigDecimal width) {
    this.width = width;
  }


  public BoundingBoxSchema height(@javax.annotation.Nonnull BigDecimal height) {
    this.height = height;
    return this;
  }

  /**
   * Hauteur de la zone
   * @return height
   */
  @javax.annotation.Nonnull
  public BigDecimal getHeight() {
    return height;
  }

  public void setHeight(@javax.annotation.Nonnull BigDecimal height) {
    this.height = height;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BoundingBoxSchema boundingBoxSchema = (BoundingBoxSchema) o;
    return Objects.equals(this.x0, boundingBoxSchema.x0) &&
        Objects.equals(this.y0, boundingBoxSchema.y0) &&
        Objects.equals(this.x1, boundingBoxSchema.x1) &&
        Objects.equals(this.y1, boundingBoxSchema.y1) &&
        Objects.equals(this.page, boundingBoxSchema.page) &&
        Objects.equals(this.width, boundingBoxSchema.width) &&
        Objects.equals(this.height, boundingBoxSchema.height);
  }

  @Override
  public int hashCode() {
    return Objects.hash(x0, y0, x1, y1, page, width, height);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BoundingBoxSchema {\n");
    sb.append("    x0: ").append(toIndentedString(x0)).append("\n");
    sb.append("    y0: ").append(toIndentedString(y0)).append("\n");
    sb.append("    x1: ").append(toIndentedString(x1)).append("\n");
    sb.append("    y1: ").append(toIndentedString(y1)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    width: ").append(toIndentedString(width)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("x0", "y0", "x1", "y1", "page", "width", "height"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(Arrays.asList("x0", "y0", "x1", "y1", "width", "height"));
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to BoundingBoxSchema
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!BoundingBoxSchema.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field(s) %s in BoundingBoxSchema is not found in the empty JSON string", BoundingBoxSchema.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!BoundingBoxSchema.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The field `%s` in the JSON string is not defined in the `BoundingBoxSchema` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : BoundingBoxSchema.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!BoundingBoxSchema.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'BoundingBoxSchema' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<BoundingBoxSchema> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(BoundingBoxSchema.class));

       return (TypeAdapter<T>) new TypeAdapter<BoundingBoxSchema>() {
           @Override
           public void write(JsonWriter out, BoundingBoxSchema value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public BoundingBoxSchema read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of BoundingBoxSchema given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of BoundingBoxSchema
   * @throws IOException if the JSON string is invalid with respect to BoundingBoxSchema
   */
  public static BoundingBoxSchema fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, BoundingBoxSchema.class);
  }

  /**
   * Convert an instance of BoundingBoxSchema to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

