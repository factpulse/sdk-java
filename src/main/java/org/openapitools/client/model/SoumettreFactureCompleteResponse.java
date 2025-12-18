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
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Arrays;
import org.openapitools.client.model.FactureEnrichieInfo;
import org.openapitools.client.model.PDFFacturXInfo;
import org.openapitools.client.model.ResultatAFNOR;
import org.openapitools.client.model.ResultatChorusPro;
import org.openapitools.client.model.SignatureInfo;
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

import org.openapitools.client.JSON;

/**
 * Réponse complète après soumission automatisée.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-12-18T20:50:47.776075445Z[Etc/UTC]", comments = "Generator version: 7.18.0-SNAPSHOT")
public class SoumettreFactureCompleteResponse {
  public static final String SERIALIZED_NAME_SUCCES = "succes";
  @SerializedName(SERIALIZED_NAME_SUCCES)
  @javax.annotation.Nonnull
  private Boolean succes;

  /**
   * Type de destination
   */
  @JsonAdapter(DestinationTypeEnum.Adapter.class)
  public enum DestinationTypeEnum {
    CHORUS_PRO("chorus_pro"),
    
    AFNOR("afnor");

    private String value;

    DestinationTypeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static DestinationTypeEnum fromValue(String value) {
      for (DestinationTypeEnum b : DestinationTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<DestinationTypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final DestinationTypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public DestinationTypeEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return DestinationTypeEnum.fromValue(value);
      }
    }

    public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      String value = jsonElement.getAsString();
      DestinationTypeEnum.fromValue(value);
    }
  }

  public static final String SERIALIZED_NAME_DESTINATION_TYPE = "destination_type";
  @SerializedName(SERIALIZED_NAME_DESTINATION_TYPE)
  @javax.annotation.Nonnull
  private DestinationTypeEnum destinationType;

  public static final String SERIALIZED_NAME_RESULTAT_CHORUS = "resultat_chorus";
  @SerializedName(SERIALIZED_NAME_RESULTAT_CHORUS)
  @javax.annotation.Nullable
  private ResultatChorusPro resultatChorus;

  public static final String SERIALIZED_NAME_RESULTAT_AFNOR = "resultat_afnor";
  @SerializedName(SERIALIZED_NAME_RESULTAT_AFNOR)
  @javax.annotation.Nullable
  private ResultatAFNOR resultatAfnor;

  public static final String SERIALIZED_NAME_FACTURE_ENRICHIE = "facture_enrichie";
  @SerializedName(SERIALIZED_NAME_FACTURE_ENRICHIE)
  @javax.annotation.Nonnull
  private FactureEnrichieInfo factureEnrichie;

  public static final String SERIALIZED_NAME_PDF_FACTURX = "pdf_facturx";
  @SerializedName(SERIALIZED_NAME_PDF_FACTURX)
  @javax.annotation.Nonnull
  private PDFFacturXInfo pdfFacturx;

  public static final String SERIALIZED_NAME_SIGNATURE = "signature";
  @SerializedName(SERIALIZED_NAME_SIGNATURE)
  @javax.annotation.Nullable
  private SignatureInfo signature;

  public static final String SERIALIZED_NAME_PDF_BASE64 = "pdf_base64";
  @SerializedName(SERIALIZED_NAME_PDF_BASE64)
  @javax.annotation.Nonnull
  private String pdfBase64;

  public static final String SERIALIZED_NAME_MESSAGE = "message";
  @SerializedName(SERIALIZED_NAME_MESSAGE)
  @javax.annotation.Nonnull
  private String message;

  public SoumettreFactureCompleteResponse() {
  }

  public SoumettreFactureCompleteResponse succes(@javax.annotation.Nonnull Boolean succes) {
    this.succes = succes;
    return this;
  }

  /**
   * La facture a été soumise avec succès
   * @return succes
   */
  @javax.annotation.Nonnull
  public Boolean getSucces() {
    return succes;
  }

  public void setSucces(@javax.annotation.Nonnull Boolean succes) {
    this.succes = succes;
  }


  public SoumettreFactureCompleteResponse destinationType(@javax.annotation.Nonnull DestinationTypeEnum destinationType) {
    this.destinationType = destinationType;
    return this;
  }

  /**
   * Type de destination
   * @return destinationType
   */
  @javax.annotation.Nonnull
  public DestinationTypeEnum getDestinationType() {
    return destinationType;
  }

  public void setDestinationType(@javax.annotation.Nonnull DestinationTypeEnum destinationType) {
    this.destinationType = destinationType;
  }


  public SoumettreFactureCompleteResponse resultatChorus(@javax.annotation.Nullable ResultatChorusPro resultatChorus) {
    this.resultatChorus = resultatChorus;
    return this;
  }

  /**
   * Get resultatChorus
   * @return resultatChorus
   */
  @javax.annotation.Nullable
  public ResultatChorusPro getResultatChorus() {
    return resultatChorus;
  }

  public void setResultatChorus(@javax.annotation.Nullable ResultatChorusPro resultatChorus) {
    this.resultatChorus = resultatChorus;
  }


  public SoumettreFactureCompleteResponse resultatAfnor(@javax.annotation.Nullable ResultatAFNOR resultatAfnor) {
    this.resultatAfnor = resultatAfnor;
    return this;
  }

  /**
   * Get resultatAfnor
   * @return resultatAfnor
   */
  @javax.annotation.Nullable
  public ResultatAFNOR getResultatAfnor() {
    return resultatAfnor;
  }

  public void setResultatAfnor(@javax.annotation.Nullable ResultatAFNOR resultatAfnor) {
    this.resultatAfnor = resultatAfnor;
  }


  public SoumettreFactureCompleteResponse factureEnrichie(@javax.annotation.Nonnull FactureEnrichieInfo factureEnrichie) {
    this.factureEnrichie = factureEnrichie;
    return this;
  }

  /**
   * Données de la facture enrichie
   * @return factureEnrichie
   */
  @javax.annotation.Nonnull
  public FactureEnrichieInfo getFactureEnrichie() {
    return factureEnrichie;
  }

  public void setFactureEnrichie(@javax.annotation.Nonnull FactureEnrichieInfo factureEnrichie) {
    this.factureEnrichie = factureEnrichie;
  }


  public SoumettreFactureCompleteResponse pdfFacturx(@javax.annotation.Nonnull PDFFacturXInfo pdfFacturx) {
    this.pdfFacturx = pdfFacturx;
    return this;
  }

  /**
   * Informations sur le PDF généré
   * @return pdfFacturx
   */
  @javax.annotation.Nonnull
  public PDFFacturXInfo getPdfFacturx() {
    return pdfFacturx;
  }

  public void setPdfFacturx(@javax.annotation.Nonnull PDFFacturXInfo pdfFacturx) {
    this.pdfFacturx = pdfFacturx;
  }


  public SoumettreFactureCompleteResponse signature(@javax.annotation.Nullable SignatureInfo signature) {
    this.signature = signature;
    return this;
  }

  /**
   * Get signature
   * @return signature
   */
  @javax.annotation.Nullable
  public SignatureInfo getSignature() {
    return signature;
  }

  public void setSignature(@javax.annotation.Nullable SignatureInfo signature) {
    this.signature = signature;
  }


  public SoumettreFactureCompleteResponse pdfBase64(@javax.annotation.Nonnull String pdfBase64) {
    this.pdfBase64 = pdfBase64;
    return this;
  }

  /**
   * PDF Factur-X généré (et signé si demandé) encodé en base64
   * @return pdfBase64
   */
  @javax.annotation.Nonnull
  public String getPdfBase64() {
    return pdfBase64;
  }

  public void setPdfBase64(@javax.annotation.Nonnull String pdfBase64) {
    this.pdfBase64 = pdfBase64;
  }


  public SoumettreFactureCompleteResponse message(@javax.annotation.Nonnull String message) {
    this.message = message;
    return this;
  }

  /**
   * Message de retour
   * @return message
   */
  @javax.annotation.Nonnull
  public String getMessage() {
    return message;
  }

  public void setMessage(@javax.annotation.Nonnull String message) {
    this.message = message;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SoumettreFactureCompleteResponse soumettreFactureCompleteResponse = (SoumettreFactureCompleteResponse) o;
    return Objects.equals(this.succes, soumettreFactureCompleteResponse.succes) &&
        Objects.equals(this.destinationType, soumettreFactureCompleteResponse.destinationType) &&
        Objects.equals(this.resultatChorus, soumettreFactureCompleteResponse.resultatChorus) &&
        Objects.equals(this.resultatAfnor, soumettreFactureCompleteResponse.resultatAfnor) &&
        Objects.equals(this.factureEnrichie, soumettreFactureCompleteResponse.factureEnrichie) &&
        Objects.equals(this.pdfFacturx, soumettreFactureCompleteResponse.pdfFacturx) &&
        Objects.equals(this.signature, soumettreFactureCompleteResponse.signature) &&
        Objects.equals(this.pdfBase64, soumettreFactureCompleteResponse.pdfBase64) &&
        Objects.equals(this.message, soumettreFactureCompleteResponse.message);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(succes, destinationType, resultatChorus, resultatAfnor, factureEnrichie, pdfFacturx, signature, pdfBase64, message);
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
    sb.append("class SoumettreFactureCompleteResponse {\n");
    sb.append("    succes: ").append(toIndentedString(succes)).append("\n");
    sb.append("    destinationType: ").append(toIndentedString(destinationType)).append("\n");
    sb.append("    resultatChorus: ").append(toIndentedString(resultatChorus)).append("\n");
    sb.append("    resultatAfnor: ").append(toIndentedString(resultatAfnor)).append("\n");
    sb.append("    factureEnrichie: ").append(toIndentedString(factureEnrichie)).append("\n");
    sb.append("    pdfFacturx: ").append(toIndentedString(pdfFacturx)).append("\n");
    sb.append("    signature: ").append(toIndentedString(signature)).append("\n");
    sb.append("    pdfBase64: ").append(toIndentedString(pdfBase64)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("succes", "destination_type", "resultat_chorus", "resultat_afnor", "facture_enrichie", "pdf_facturx", "signature", "pdf_base64", "message"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(Arrays.asList("succes", "destination_type", "facture_enrichie", "pdf_facturx", "pdf_base64", "message"));
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to SoumettreFactureCompleteResponse
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!SoumettreFactureCompleteResponse.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "The required field(s) %s in SoumettreFactureCompleteResponse is not found in the empty JSON string", SoumettreFactureCompleteResponse.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!SoumettreFactureCompleteResponse.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "The field `%s` in the JSON string is not defined in the `SoumettreFactureCompleteResponse` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : SoumettreFactureCompleteResponse.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (!jsonObj.get("destination_type").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `destination_type` to be a primitive type in the JSON string but got `%s`", jsonObj.get("destination_type").toString()));
      }
      // validate the required field `destination_type`
      DestinationTypeEnum.validateJsonElement(jsonObj.get("destination_type"));
      // validate the optional field `resultat_chorus`
      if (jsonObj.get("resultat_chorus") != null && !jsonObj.get("resultat_chorus").isJsonNull()) {
        ResultatChorusPro.validateJsonElement(jsonObj.get("resultat_chorus"));
      }
      // validate the optional field `resultat_afnor`
      if (jsonObj.get("resultat_afnor") != null && !jsonObj.get("resultat_afnor").isJsonNull()) {
        ResultatAFNOR.validateJsonElement(jsonObj.get("resultat_afnor"));
      }
      // validate the required field `facture_enrichie`
      FactureEnrichieInfo.validateJsonElement(jsonObj.get("facture_enrichie"));
      // validate the required field `pdf_facturx`
      PDFFacturXInfo.validateJsonElement(jsonObj.get("pdf_facturx"));
      // validate the optional field `signature`
      if (jsonObj.get("signature") != null && !jsonObj.get("signature").isJsonNull()) {
        SignatureInfo.validateJsonElement(jsonObj.get("signature"));
      }
      if (!jsonObj.get("pdf_base64").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `pdf_base64` to be a primitive type in the JSON string but got `%s`", jsonObj.get("pdf_base64").toString()));
      }
      if (!jsonObj.get("message").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(java.util.Locale.ROOT, "Expected the field `message` to be a primitive type in the JSON string but got `%s`", jsonObj.get("message").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!SoumettreFactureCompleteResponse.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'SoumettreFactureCompleteResponse' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<SoumettreFactureCompleteResponse> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(SoumettreFactureCompleteResponse.class));

       return (TypeAdapter<T>) new TypeAdapter<SoumettreFactureCompleteResponse>() {
           @Override
           public void write(JsonWriter out, SoumettreFactureCompleteResponse value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public SoumettreFactureCompleteResponse read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of SoumettreFactureCompleteResponse given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of SoumettreFactureCompleteResponse
   * @throws IOException if the JSON string is invalid with respect to SoumettreFactureCompleteResponse
   */
  public static SoumettreFactureCompleteResponse fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, SoumettreFactureCompleteResponse.class);
  }

  /**
   * Convert an instance of SoumettreFactureCompleteResponse to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

