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
import org.openapitools.client.model.ModePaiement;
import org.openapitools.client.model.TypeFacture;
import org.openapitools.client.model.TypeTVA;
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
 * Contient les références diverses de la facture (devise, type, etc.).
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-11-27T20:29:46.067840253Z[Etc/UTC]", comments = "Generator version: 7.18.0-SNAPSHOT")
public class References {
  public static final String SERIALIZED_NAME_DEVISE_FACTURE = "deviseFacture";
  @SerializedName(SERIALIZED_NAME_DEVISE_FACTURE)
  @javax.annotation.Nullable
  private String deviseFacture = "EUR";

  public static final String SERIALIZED_NAME_MODE_PAIEMENT = "modePaiement";
  @SerializedName(SERIALIZED_NAME_MODE_PAIEMENT)
  @javax.annotation.Nonnull
  private ModePaiement modePaiement;

  public static final String SERIALIZED_NAME_TYPE_FACTURE = "typeFacture";
  @SerializedName(SERIALIZED_NAME_TYPE_FACTURE)
  @javax.annotation.Nonnull
  private TypeFacture typeFacture;

  public static final String SERIALIZED_NAME_TYPE_TVA = "typeTva";
  @SerializedName(SERIALIZED_NAME_TYPE_TVA)
  @javax.annotation.Nonnull
  private TypeTVA typeTva;

  public static final String SERIALIZED_NAME_NUMERO_MARCHE = "numeroMarche";
  @SerializedName(SERIALIZED_NAME_NUMERO_MARCHE)
  @javax.annotation.Nullable
  private String numeroMarche;

  public static final String SERIALIZED_NAME_MOTIF_EXONERATION_TVA = "motifExonerationTva";
  @SerializedName(SERIALIZED_NAME_MOTIF_EXONERATION_TVA)
  @javax.annotation.Nullable
  private String motifExonerationTva;

  public static final String SERIALIZED_NAME_NUMERO_BON_COMMANDE = "numeroBonCommande";
  @SerializedName(SERIALIZED_NAME_NUMERO_BON_COMMANDE)
  @javax.annotation.Nullable
  private String numeroBonCommande;

  public static final String SERIALIZED_NAME_NUMERO_FACTURE_ORIGINE = "numeroFactureOrigine";
  @SerializedName(SERIALIZED_NAME_NUMERO_FACTURE_ORIGINE)
  @javax.annotation.Nullable
  private String numeroFactureOrigine;

  public References() {
  }

  public References deviseFacture(@javax.annotation.Nullable String deviseFacture) {
    this.deviseFacture = deviseFacture;
    return this;
  }

  /**
   * Get deviseFacture
   * @return deviseFacture
   */
  @javax.annotation.Nullable
  public String getDeviseFacture() {
    return deviseFacture;
  }

  public void setDeviseFacture(@javax.annotation.Nullable String deviseFacture) {
    this.deviseFacture = deviseFacture;
  }


  public References modePaiement(@javax.annotation.Nonnull ModePaiement modePaiement) {
    this.modePaiement = modePaiement;
    return this;
  }

  /**
   * Get modePaiement
   * @return modePaiement
   */
  @javax.annotation.Nonnull
  public ModePaiement getModePaiement() {
    return modePaiement;
  }

  public void setModePaiement(@javax.annotation.Nonnull ModePaiement modePaiement) {
    this.modePaiement = modePaiement;
  }


  public References typeFacture(@javax.annotation.Nonnull TypeFacture typeFacture) {
    this.typeFacture = typeFacture;
    return this;
  }

  /**
   * Get typeFacture
   * @return typeFacture
   */
  @javax.annotation.Nonnull
  public TypeFacture getTypeFacture() {
    return typeFacture;
  }

  public void setTypeFacture(@javax.annotation.Nonnull TypeFacture typeFacture) {
    this.typeFacture = typeFacture;
  }


  public References typeTva(@javax.annotation.Nonnull TypeTVA typeTva) {
    this.typeTva = typeTva;
    return this;
  }

  /**
   * Get typeTva
   * @return typeTva
   */
  @javax.annotation.Nonnull
  public TypeTVA getTypeTva() {
    return typeTva;
  }

  public void setTypeTva(@javax.annotation.Nonnull TypeTVA typeTva) {
    this.typeTva = typeTva;
  }


  public References numeroMarche(@javax.annotation.Nullable String numeroMarche) {
    this.numeroMarche = numeroMarche;
    return this;
  }

  /**
   * Get numeroMarche
   * @return numeroMarche
   */
  @javax.annotation.Nullable
  public String getNumeroMarche() {
    return numeroMarche;
  }

  public void setNumeroMarche(@javax.annotation.Nullable String numeroMarche) {
    this.numeroMarche = numeroMarche;
  }


  public References motifExonerationTva(@javax.annotation.Nullable String motifExonerationTva) {
    this.motifExonerationTva = motifExonerationTva;
    return this;
  }

  /**
   * Get motifExonerationTva
   * @return motifExonerationTva
   */
  @javax.annotation.Nullable
  public String getMotifExonerationTva() {
    return motifExonerationTva;
  }

  public void setMotifExonerationTva(@javax.annotation.Nullable String motifExonerationTva) {
    this.motifExonerationTva = motifExonerationTva;
  }


  public References numeroBonCommande(@javax.annotation.Nullable String numeroBonCommande) {
    this.numeroBonCommande = numeroBonCommande;
    return this;
  }

  /**
   * Get numeroBonCommande
   * @return numeroBonCommande
   */
  @javax.annotation.Nullable
  public String getNumeroBonCommande() {
    return numeroBonCommande;
  }

  public void setNumeroBonCommande(@javax.annotation.Nullable String numeroBonCommande) {
    this.numeroBonCommande = numeroBonCommande;
  }


  public References numeroFactureOrigine(@javax.annotation.Nullable String numeroFactureOrigine) {
    this.numeroFactureOrigine = numeroFactureOrigine;
    return this;
  }

  /**
   * Get numeroFactureOrigine
   * @return numeroFactureOrigine
   */
  @javax.annotation.Nullable
  public String getNumeroFactureOrigine() {
    return numeroFactureOrigine;
  }

  public void setNumeroFactureOrigine(@javax.annotation.Nullable String numeroFactureOrigine) {
    this.numeroFactureOrigine = numeroFactureOrigine;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    References references = (References) o;
    return Objects.equals(this.deviseFacture, references.deviseFacture) &&
        Objects.equals(this.modePaiement, references.modePaiement) &&
        Objects.equals(this.typeFacture, references.typeFacture) &&
        Objects.equals(this.typeTva, references.typeTva) &&
        Objects.equals(this.numeroMarche, references.numeroMarche) &&
        Objects.equals(this.motifExonerationTva, references.motifExonerationTva) &&
        Objects.equals(this.numeroBonCommande, references.numeroBonCommande) &&
        Objects.equals(this.numeroFactureOrigine, references.numeroFactureOrigine);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(deviseFacture, modePaiement, typeFacture, typeTva, numeroMarche, motifExonerationTva, numeroBonCommande, numeroFactureOrigine);
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
    sb.append("class References {\n");
    sb.append("    deviseFacture: ").append(toIndentedString(deviseFacture)).append("\n");
    sb.append("    modePaiement: ").append(toIndentedString(modePaiement)).append("\n");
    sb.append("    typeFacture: ").append(toIndentedString(typeFacture)).append("\n");
    sb.append("    typeTva: ").append(toIndentedString(typeTva)).append("\n");
    sb.append("    numeroMarche: ").append(toIndentedString(numeroMarche)).append("\n");
    sb.append("    motifExonerationTva: ").append(toIndentedString(motifExonerationTva)).append("\n");
    sb.append("    numeroBonCommande: ").append(toIndentedString(numeroBonCommande)).append("\n");
    sb.append("    numeroFactureOrigine: ").append(toIndentedString(numeroFactureOrigine)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("deviseFacture", "modePaiement", "typeFacture", "typeTva", "numeroMarche", "motifExonerationTva", "numeroBonCommande", "numeroFactureOrigine"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(Arrays.asList("modePaiement", "typeFacture", "typeTva"));
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to References
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!References.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field(s) %s in References is not found in the empty JSON string", References.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!References.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The field `%s` in the JSON string is not defined in the `References` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : References.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("deviseFacture") != null && !jsonObj.get("deviseFacture").isJsonNull()) && !jsonObj.get("deviseFacture").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `deviseFacture` to be a primitive type in the JSON string but got `%s`", jsonObj.get("deviseFacture").toString()));
      }
      // validate the required field `modePaiement`
      ModePaiement.validateJsonElement(jsonObj.get("modePaiement"));
      // validate the required field `typeFacture`
      TypeFacture.validateJsonElement(jsonObj.get("typeFacture"));
      // validate the required field `typeTva`
      TypeTVA.validateJsonElement(jsonObj.get("typeTva"));
      if ((jsonObj.get("numeroMarche") != null && !jsonObj.get("numeroMarche").isJsonNull()) && !jsonObj.get("numeroMarche").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `numeroMarche` to be a primitive type in the JSON string but got `%s`", jsonObj.get("numeroMarche").toString()));
      }
      if ((jsonObj.get("motifExonerationTva") != null && !jsonObj.get("motifExonerationTva").isJsonNull()) && !jsonObj.get("motifExonerationTva").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `motifExonerationTva` to be a primitive type in the JSON string but got `%s`", jsonObj.get("motifExonerationTva").toString()));
      }
      if ((jsonObj.get("numeroBonCommande") != null && !jsonObj.get("numeroBonCommande").isJsonNull()) && !jsonObj.get("numeroBonCommande").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `numeroBonCommande` to be a primitive type in the JSON string but got `%s`", jsonObj.get("numeroBonCommande").toString()));
      }
      if ((jsonObj.get("numeroFactureOrigine") != null && !jsonObj.get("numeroFactureOrigine").isJsonNull()) && !jsonObj.get("numeroFactureOrigine").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `numeroFactureOrigine` to be a primitive type in the JSON string but got `%s`", jsonObj.get("numeroFactureOrigine").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!References.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'References' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<References> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(References.class));

       return (TypeAdapter<T>) new TypeAdapter<References>() {
           @Override
           public void write(JsonWriter out, References value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public References read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of References given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of References
   * @throws IOException if the JSON string is invalid with respect to References
   */
  public static References fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, References.class);
  }

  /**
   * Convert an instance of References to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

