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
 * Informations sur la facture enrichie.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-11-27T20:29:46.067840253Z[Etc/UTC]", comments = "Generator version: 7.18.0-SNAPSHOT")
public class FactureEnrichieInfo {
  public static final String SERIALIZED_NAME_NUMERO_FACTURE = "numero_facture";
  @SerializedName(SERIALIZED_NAME_NUMERO_FACTURE)
  @javax.annotation.Nonnull
  private String numeroFacture;

  public static final String SERIALIZED_NAME_ID_EMETTEUR = "id_emetteur";
  @SerializedName(SERIALIZED_NAME_ID_EMETTEUR)
  @javax.annotation.Nullable
  private Integer idEmetteur;

  public static final String SERIALIZED_NAME_ID_DESTINATAIRE = "id_destinataire";
  @SerializedName(SERIALIZED_NAME_ID_DESTINATAIRE)
  @javax.annotation.Nullable
  private Integer idDestinataire;

  public static final String SERIALIZED_NAME_NOM_EMETTEUR = "nom_emetteur";
  @SerializedName(SERIALIZED_NAME_NOM_EMETTEUR)
  @javax.annotation.Nonnull
  private String nomEmetteur;

  public static final String SERIALIZED_NAME_NOM_DESTINATAIRE = "nom_destinataire";
  @SerializedName(SERIALIZED_NAME_NOM_DESTINATAIRE)
  @javax.annotation.Nonnull
  private String nomDestinataire;

  public static final String SERIALIZED_NAME_MONTANT_HT_TOTAL = "montant_ht_total";
  @SerializedName(SERIALIZED_NAME_MONTANT_HT_TOTAL)
  @javax.annotation.Nonnull
  private String montantHtTotal;

  public static final String SERIALIZED_NAME_MONTANT_TVA = "montant_tva";
  @SerializedName(SERIALIZED_NAME_MONTANT_TVA)
  @javax.annotation.Nonnull
  private String montantTva;

  public static final String SERIALIZED_NAME_MONTANT_TTC_TOTAL = "montant_ttc_total";
  @SerializedName(SERIALIZED_NAME_MONTANT_TTC_TOTAL)
  @javax.annotation.Nonnull
  private String montantTtcTotal;

  public FactureEnrichieInfo() {
  }

  public FactureEnrichieInfo numeroFacture(@javax.annotation.Nonnull String numeroFacture) {
    this.numeroFacture = numeroFacture;
    return this;
  }

  /**
   * Get numeroFacture
   * @return numeroFacture
   */
  @javax.annotation.Nonnull
  public String getNumeroFacture() {
    return numeroFacture;
  }

  public void setNumeroFacture(@javax.annotation.Nonnull String numeroFacture) {
    this.numeroFacture = numeroFacture;
  }


  public FactureEnrichieInfo idEmetteur(@javax.annotation.Nullable Integer idEmetteur) {
    this.idEmetteur = idEmetteur;
    return this;
  }

  /**
   * Get idEmetteur
   * @return idEmetteur
   */
  @javax.annotation.Nullable
  public Integer getIdEmetteur() {
    return idEmetteur;
  }

  public void setIdEmetteur(@javax.annotation.Nullable Integer idEmetteur) {
    this.idEmetteur = idEmetteur;
  }


  public FactureEnrichieInfo idDestinataire(@javax.annotation.Nullable Integer idDestinataire) {
    this.idDestinataire = idDestinataire;
    return this;
  }

  /**
   * Get idDestinataire
   * @return idDestinataire
   */
  @javax.annotation.Nullable
  public Integer getIdDestinataire() {
    return idDestinataire;
  }

  public void setIdDestinataire(@javax.annotation.Nullable Integer idDestinataire) {
    this.idDestinataire = idDestinataire;
  }


  public FactureEnrichieInfo nomEmetteur(@javax.annotation.Nonnull String nomEmetteur) {
    this.nomEmetteur = nomEmetteur;
    return this;
  }

  /**
   * Get nomEmetteur
   * @return nomEmetteur
   */
  @javax.annotation.Nonnull
  public String getNomEmetteur() {
    return nomEmetteur;
  }

  public void setNomEmetteur(@javax.annotation.Nonnull String nomEmetteur) {
    this.nomEmetteur = nomEmetteur;
  }


  public FactureEnrichieInfo nomDestinataire(@javax.annotation.Nonnull String nomDestinataire) {
    this.nomDestinataire = nomDestinataire;
    return this;
  }

  /**
   * Get nomDestinataire
   * @return nomDestinataire
   */
  @javax.annotation.Nonnull
  public String getNomDestinataire() {
    return nomDestinataire;
  }

  public void setNomDestinataire(@javax.annotation.Nonnull String nomDestinataire) {
    this.nomDestinataire = nomDestinataire;
  }


  public FactureEnrichieInfo montantHtTotal(@javax.annotation.Nonnull String montantHtTotal) {
    this.montantHtTotal = montantHtTotal;
    return this;
  }

  /**
   * Get montantHtTotal
   * @return montantHtTotal
   */
  @javax.annotation.Nonnull
  public String getMontantHtTotal() {
    return montantHtTotal;
  }

  public void setMontantHtTotal(@javax.annotation.Nonnull String montantHtTotal) {
    this.montantHtTotal = montantHtTotal;
  }


  public FactureEnrichieInfo montantTva(@javax.annotation.Nonnull String montantTva) {
    this.montantTva = montantTva;
    return this;
  }

  /**
   * Get montantTva
   * @return montantTva
   */
  @javax.annotation.Nonnull
  public String getMontantTva() {
    return montantTva;
  }

  public void setMontantTva(@javax.annotation.Nonnull String montantTva) {
    this.montantTva = montantTva;
  }


  public FactureEnrichieInfo montantTtcTotal(@javax.annotation.Nonnull String montantTtcTotal) {
    this.montantTtcTotal = montantTtcTotal;
    return this;
  }

  /**
   * Get montantTtcTotal
   * @return montantTtcTotal
   */
  @javax.annotation.Nonnull
  public String getMontantTtcTotal() {
    return montantTtcTotal;
  }

  public void setMontantTtcTotal(@javax.annotation.Nonnull String montantTtcTotal) {
    this.montantTtcTotal = montantTtcTotal;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FactureEnrichieInfo factureEnrichieInfo = (FactureEnrichieInfo) o;
    return Objects.equals(this.numeroFacture, factureEnrichieInfo.numeroFacture) &&
        Objects.equals(this.idEmetteur, factureEnrichieInfo.idEmetteur) &&
        Objects.equals(this.idDestinataire, factureEnrichieInfo.idDestinataire) &&
        Objects.equals(this.nomEmetteur, factureEnrichieInfo.nomEmetteur) &&
        Objects.equals(this.nomDestinataire, factureEnrichieInfo.nomDestinataire) &&
        Objects.equals(this.montantHtTotal, factureEnrichieInfo.montantHtTotal) &&
        Objects.equals(this.montantTva, factureEnrichieInfo.montantTva) &&
        Objects.equals(this.montantTtcTotal, factureEnrichieInfo.montantTtcTotal);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(numeroFacture, idEmetteur, idDestinataire, nomEmetteur, nomDestinataire, montantHtTotal, montantTva, montantTtcTotal);
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
    sb.append("class FactureEnrichieInfo {\n");
    sb.append("    numeroFacture: ").append(toIndentedString(numeroFacture)).append("\n");
    sb.append("    idEmetteur: ").append(toIndentedString(idEmetteur)).append("\n");
    sb.append("    idDestinataire: ").append(toIndentedString(idDestinataire)).append("\n");
    sb.append("    nomEmetteur: ").append(toIndentedString(nomEmetteur)).append("\n");
    sb.append("    nomDestinataire: ").append(toIndentedString(nomDestinataire)).append("\n");
    sb.append("    montantHtTotal: ").append(toIndentedString(montantHtTotal)).append("\n");
    sb.append("    montantTva: ").append(toIndentedString(montantTva)).append("\n");
    sb.append("    montantTtcTotal: ").append(toIndentedString(montantTtcTotal)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("numero_facture", "id_emetteur", "id_destinataire", "nom_emetteur", "nom_destinataire", "montant_ht_total", "montant_tva", "montant_ttc_total"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(Arrays.asList("numero_facture", "nom_emetteur", "nom_destinataire", "montant_ht_total", "montant_tva", "montant_ttc_total"));
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to FactureEnrichieInfo
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!FactureEnrichieInfo.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field(s) %s in FactureEnrichieInfo is not found in the empty JSON string", FactureEnrichieInfo.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!FactureEnrichieInfo.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The field `%s` in the JSON string is not defined in the `FactureEnrichieInfo` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : FactureEnrichieInfo.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (!jsonObj.get("numero_facture").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `numero_facture` to be a primitive type in the JSON string but got `%s`", jsonObj.get("numero_facture").toString()));
      }
      if (!jsonObj.get("nom_emetteur").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `nom_emetteur` to be a primitive type in the JSON string but got `%s`", jsonObj.get("nom_emetteur").toString()));
      }
      if (!jsonObj.get("nom_destinataire").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `nom_destinataire` to be a primitive type in the JSON string but got `%s`", jsonObj.get("nom_destinataire").toString()));
      }
      if (!jsonObj.get("montant_ht_total").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `montant_ht_total` to be a primitive type in the JSON string but got `%s`", jsonObj.get("montant_ht_total").toString()));
      }
      if (!jsonObj.get("montant_tva").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `montant_tva` to be a primitive type in the JSON string but got `%s`", jsonObj.get("montant_tva").toString()));
      }
      if (!jsonObj.get("montant_ttc_total").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `montant_ttc_total` to be a primitive type in the JSON string but got `%s`", jsonObj.get("montant_ttc_total").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!FactureEnrichieInfo.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'FactureEnrichieInfo' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<FactureEnrichieInfo> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(FactureEnrichieInfo.class));

       return (TypeAdapter<T>) new TypeAdapter<FactureEnrichieInfo>() {
           @Override
           public void write(JsonWriter out, FactureEnrichieInfo value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public FactureEnrichieInfo read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of FactureEnrichieInfo given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of FactureEnrichieInfo
   * @throws IOException if the JSON string is invalid with respect to FactureEnrichieInfo
   */
  public static FactureEnrichieInfo fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, FactureEnrichieInfo.class);
  }

  /**
   * Convert an instance of FactureEnrichieInfo to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

