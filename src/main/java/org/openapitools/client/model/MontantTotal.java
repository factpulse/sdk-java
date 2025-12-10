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
import org.openapitools.client.model.MontantAPayer;
import org.openapitools.client.model.MontantHtTotal;
import org.openapitools.client.model.MontantRemiseGlobaleTtc;
import org.openapitools.client.model.MontantTotalAcompte;
import org.openapitools.client.model.MontantTtcTotal;
import org.openapitools.client.model.MontantTvaTotal;
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
 * Contient tous les montants totaux de la facture.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-12-10T06:32:02.575358678Z[Etc/UTC]", comments = "Generator version: 7.18.0-SNAPSHOT")
public class MontantTotal {
  public static final String SERIALIZED_NAME_MONTANT_HT_TOTAL = "montantHtTotal";
  @SerializedName(SERIALIZED_NAME_MONTANT_HT_TOTAL)
  @javax.annotation.Nonnull
  private MontantHtTotal montantHtTotal;

  public static final String SERIALIZED_NAME_MONTANT_TVA = "montantTva";
  @SerializedName(SERIALIZED_NAME_MONTANT_TVA)
  @javax.annotation.Nonnull
  private MontantTvaTotal montantTva;

  public static final String SERIALIZED_NAME_MONTANT_TTC_TOTAL = "montantTtcTotal";
  @SerializedName(SERIALIZED_NAME_MONTANT_TTC_TOTAL)
  @javax.annotation.Nonnull
  private MontantTtcTotal montantTtcTotal;

  public static final String SERIALIZED_NAME_MONTANT_A_PAYER = "montantAPayer";
  @SerializedName(SERIALIZED_NAME_MONTANT_A_PAYER)
  @javax.annotation.Nonnull
  private MontantAPayer montantAPayer;

  public static final String SERIALIZED_NAME_ACOMPTE = "acompte";
  @SerializedName(SERIALIZED_NAME_ACOMPTE)
  @javax.annotation.Nullable
  private MontantTotalAcompte acompte;

  public static final String SERIALIZED_NAME_MONTANT_REMISE_GLOBALE_TTC = "montantRemiseGlobaleTtc";
  @SerializedName(SERIALIZED_NAME_MONTANT_REMISE_GLOBALE_TTC)
  @javax.annotation.Nullable
  private MontantRemiseGlobaleTtc montantRemiseGlobaleTtc;

  public static final String SERIALIZED_NAME_MOTIF_REMISE_GLOBALE_TTC = "motifRemiseGlobaleTtc";
  @SerializedName(SERIALIZED_NAME_MOTIF_REMISE_GLOBALE_TTC)
  @javax.annotation.Nullable
  private String motifRemiseGlobaleTtc;

  public MontantTotal() {
  }

  public MontantTotal montantHtTotal(@javax.annotation.Nonnull MontantHtTotal montantHtTotal) {
    this.montantHtTotal = montantHtTotal;
    return this;
  }

  /**
   * Get montantHtTotal
   * @return montantHtTotal
   */
  @javax.annotation.Nonnull
  public MontantHtTotal getMontantHtTotal() {
    return montantHtTotal;
  }

  public void setMontantHtTotal(@javax.annotation.Nonnull MontantHtTotal montantHtTotal) {
    this.montantHtTotal = montantHtTotal;
  }


  public MontantTotal montantTva(@javax.annotation.Nonnull MontantTvaTotal montantTva) {
    this.montantTva = montantTva;
    return this;
  }

  /**
   * Get montantTva
   * @return montantTva
   */
  @javax.annotation.Nonnull
  public MontantTvaTotal getMontantTva() {
    return montantTva;
  }

  public void setMontantTva(@javax.annotation.Nonnull MontantTvaTotal montantTva) {
    this.montantTva = montantTva;
  }


  public MontantTotal montantTtcTotal(@javax.annotation.Nonnull MontantTtcTotal montantTtcTotal) {
    this.montantTtcTotal = montantTtcTotal;
    return this;
  }

  /**
   * Get montantTtcTotal
   * @return montantTtcTotal
   */
  @javax.annotation.Nonnull
  public MontantTtcTotal getMontantTtcTotal() {
    return montantTtcTotal;
  }

  public void setMontantTtcTotal(@javax.annotation.Nonnull MontantTtcTotal montantTtcTotal) {
    this.montantTtcTotal = montantTtcTotal;
  }


  public MontantTotal montantAPayer(@javax.annotation.Nonnull MontantAPayer montantAPayer) {
    this.montantAPayer = montantAPayer;
    return this;
  }

  /**
   * Get montantAPayer
   * @return montantAPayer
   */
  @javax.annotation.Nonnull
  public MontantAPayer getMontantAPayer() {
    return montantAPayer;
  }

  public void setMontantAPayer(@javax.annotation.Nonnull MontantAPayer montantAPayer) {
    this.montantAPayer = montantAPayer;
  }


  public MontantTotal acompte(@javax.annotation.Nullable MontantTotalAcompte acompte) {
    this.acompte = acompte;
    return this;
  }

  /**
   * Get acompte
   * @return acompte
   */
  @javax.annotation.Nullable
  public MontantTotalAcompte getAcompte() {
    return acompte;
  }

  public void setAcompte(@javax.annotation.Nullable MontantTotalAcompte acompte) {
    this.acompte = acompte;
  }


  public MontantTotal montantRemiseGlobaleTtc(@javax.annotation.Nullable MontantRemiseGlobaleTtc montantRemiseGlobaleTtc) {
    this.montantRemiseGlobaleTtc = montantRemiseGlobaleTtc;
    return this;
  }

  /**
   * Get montantRemiseGlobaleTtc
   * @return montantRemiseGlobaleTtc
   */
  @javax.annotation.Nullable
  public MontantRemiseGlobaleTtc getMontantRemiseGlobaleTtc() {
    return montantRemiseGlobaleTtc;
  }

  public void setMontantRemiseGlobaleTtc(@javax.annotation.Nullable MontantRemiseGlobaleTtc montantRemiseGlobaleTtc) {
    this.montantRemiseGlobaleTtc = montantRemiseGlobaleTtc;
  }


  public MontantTotal motifRemiseGlobaleTtc(@javax.annotation.Nullable String motifRemiseGlobaleTtc) {
    this.motifRemiseGlobaleTtc = motifRemiseGlobaleTtc;
    return this;
  }

  /**
   * Get motifRemiseGlobaleTtc
   * @return motifRemiseGlobaleTtc
   */
  @javax.annotation.Nullable
  public String getMotifRemiseGlobaleTtc() {
    return motifRemiseGlobaleTtc;
  }

  public void setMotifRemiseGlobaleTtc(@javax.annotation.Nullable String motifRemiseGlobaleTtc) {
    this.motifRemiseGlobaleTtc = motifRemiseGlobaleTtc;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MontantTotal montantTotal = (MontantTotal) o;
    return Objects.equals(this.montantHtTotal, montantTotal.montantHtTotal) &&
        Objects.equals(this.montantTva, montantTotal.montantTva) &&
        Objects.equals(this.montantTtcTotal, montantTotal.montantTtcTotal) &&
        Objects.equals(this.montantAPayer, montantTotal.montantAPayer) &&
        Objects.equals(this.acompte, montantTotal.acompte) &&
        Objects.equals(this.montantRemiseGlobaleTtc, montantTotal.montantRemiseGlobaleTtc) &&
        Objects.equals(this.motifRemiseGlobaleTtc, montantTotal.motifRemiseGlobaleTtc);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(montantHtTotal, montantTva, montantTtcTotal, montantAPayer, acompte, montantRemiseGlobaleTtc, motifRemiseGlobaleTtc);
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
    sb.append("class MontantTotal {\n");
    sb.append("    montantHtTotal: ").append(toIndentedString(montantHtTotal)).append("\n");
    sb.append("    montantTva: ").append(toIndentedString(montantTva)).append("\n");
    sb.append("    montantTtcTotal: ").append(toIndentedString(montantTtcTotal)).append("\n");
    sb.append("    montantAPayer: ").append(toIndentedString(montantAPayer)).append("\n");
    sb.append("    acompte: ").append(toIndentedString(acompte)).append("\n");
    sb.append("    montantRemiseGlobaleTtc: ").append(toIndentedString(montantRemiseGlobaleTtc)).append("\n");
    sb.append("    motifRemiseGlobaleTtc: ").append(toIndentedString(motifRemiseGlobaleTtc)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("montantHtTotal", "montantTva", "montantTtcTotal", "montantAPayer", "acompte", "montantRemiseGlobaleTtc", "motifRemiseGlobaleTtc"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(Arrays.asList("montantHtTotal", "montantTva", "montantTtcTotal", "montantAPayer"));
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to MontantTotal
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!MontantTotal.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field(s) %s in MontantTotal is not found in the empty JSON string", MontantTotal.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!MontantTotal.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The field `%s` in the JSON string is not defined in the `MontantTotal` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : MontantTotal.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      // validate the required field `montantHtTotal`
      MontantHtTotal.validateJsonElement(jsonObj.get("montantHtTotal"));
      // validate the required field `montantTva`
      MontantTvaTotal.validateJsonElement(jsonObj.get("montantTva"));
      // validate the required field `montantTtcTotal`
      MontantTtcTotal.validateJsonElement(jsonObj.get("montantTtcTotal"));
      // validate the required field `montantAPayer`
      MontantAPayer.validateJsonElement(jsonObj.get("montantAPayer"));
      // validate the optional field `acompte`
      if (jsonObj.get("acompte") != null && !jsonObj.get("acompte").isJsonNull()) {
        MontantTotalAcompte.validateJsonElement(jsonObj.get("acompte"));
      }
      // validate the optional field `montantRemiseGlobaleTtc`
      if (jsonObj.get("montantRemiseGlobaleTtc") != null && !jsonObj.get("montantRemiseGlobaleTtc").isJsonNull()) {
        MontantRemiseGlobaleTtc.validateJsonElement(jsonObj.get("montantRemiseGlobaleTtc"));
      }
      if ((jsonObj.get("motifRemiseGlobaleTtc") != null && !jsonObj.get("motifRemiseGlobaleTtc").isJsonNull()) && !jsonObj.get("motifRemiseGlobaleTtc").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `motifRemiseGlobaleTtc` to be a primitive type in the JSON string but got `%s`", jsonObj.get("motifRemiseGlobaleTtc").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!MontantTotal.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'MontantTotal' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<MontantTotal> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(MontantTotal.class));

       return (TypeAdapter<T>) new TypeAdapter<MontantTotal>() {
           @Override
           public void write(JsonWriter out, MontantTotal value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public MontantTotal read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of MontantTotal given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of MontantTotal
   * @throws IOException if the JSON string is invalid with respect to MontantTotal
   */
  public static MontantTotal fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, MontantTotal.class);
  }

  /**
   * Convert an instance of MontantTotal to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

