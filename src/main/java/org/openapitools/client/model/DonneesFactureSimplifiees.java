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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 * Données simplifiées de la facture (format minimal pour auto-enrichissement).
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-12-08T06:57:37.390616113Z[Etc/UTC]", comments = "Generator version: 7.18.0-SNAPSHOT")
public class DonneesFactureSimplifiees {
  public static final String SERIALIZED_NAME_NUMERO = "numero";
  @SerializedName(SERIALIZED_NAME_NUMERO)
  @javax.annotation.Nonnull
  private String numero;

  public static final String SERIALIZED_NAME_EMETTEUR = "emetteur";
  @SerializedName(SERIALIZED_NAME_EMETTEUR)
  @javax.annotation.Nonnull
  private Map<String, Object> emetteur = new HashMap<>();

  public static final String SERIALIZED_NAME_DESTINATAIRE = "destinataire";
  @SerializedName(SERIALIZED_NAME_DESTINATAIRE)
  @javax.annotation.Nonnull
  private Map<String, Object> destinataire = new HashMap<>();

  public static final String SERIALIZED_NAME_LIGNES = "lignes";
  @SerializedName(SERIALIZED_NAME_LIGNES)
  @javax.annotation.Nonnull
  private List<Map<String, Object>> lignes = new ArrayList<>();

  public static final String SERIALIZED_NAME_DATE = "date";
  @SerializedName(SERIALIZED_NAME_DATE)
  @javax.annotation.Nullable
  private String date;

  public static final String SERIALIZED_NAME_ECHEANCE_JOURS = "echeance_jours";
  @SerializedName(SERIALIZED_NAME_ECHEANCE_JOURS)
  @javax.annotation.Nullable
  private Integer echeanceJours = 30;

  public static final String SERIALIZED_NAME_COMMENTAIRE = "commentaire";
  @SerializedName(SERIALIZED_NAME_COMMENTAIRE)
  @javax.annotation.Nullable
  private String commentaire;

  public static final String SERIALIZED_NAME_NUMERO_BON_COMMANDE = "numero_bon_commande";
  @SerializedName(SERIALIZED_NAME_NUMERO_BON_COMMANDE)
  @javax.annotation.Nullable
  private String numeroBonCommande;

  public static final String SERIALIZED_NAME_NUMERO_MARCHE = "numero_marche";
  @SerializedName(SERIALIZED_NAME_NUMERO_MARCHE)
  @javax.annotation.Nullable
  private String numeroMarche;

  public DonneesFactureSimplifiees() {
  }

  public DonneesFactureSimplifiees numero(@javax.annotation.Nonnull String numero) {
    this.numero = numero;
    return this;
  }

  /**
   * Numéro de facture unique
   * @return numero
   */
  @javax.annotation.Nonnull
  public String getNumero() {
    return numero;
  }

  public void setNumero(@javax.annotation.Nonnull String numero) {
    this.numero = numero;
  }


  public DonneesFactureSimplifiees emetteur(@javax.annotation.Nonnull Map<String, Object> emetteur) {
    this.emetteur = emetteur;
    return this;
  }

  public DonneesFactureSimplifiees putEmetteurItem(String key, Object emetteurItem) {
    if (this.emetteur == null) {
      this.emetteur = new HashMap<>();
    }
    this.emetteur.put(key, emetteurItem);
    return this;
  }

  /**
   * Informations sur l&#39;émetteur (siret, iban, ...)
   * @return emetteur
   */
  @javax.annotation.Nonnull
  public Map<String, Object> getEmetteur() {
    return emetteur;
  }

  public void setEmetteur(@javax.annotation.Nonnull Map<String, Object> emetteur) {
    this.emetteur = emetteur;
  }


  public DonneesFactureSimplifiees destinataire(@javax.annotation.Nonnull Map<String, Object> destinataire) {
    this.destinataire = destinataire;
    return this;
  }

  public DonneesFactureSimplifiees putDestinataireItem(String key, Object destinataireItem) {
    if (this.destinataire == null) {
      this.destinataire = new HashMap<>();
    }
    this.destinataire.put(key, destinataireItem);
    return this;
  }

  /**
   * Informations sur le destinataire (siret, ...)
   * @return destinataire
   */
  @javax.annotation.Nonnull
  public Map<String, Object> getDestinataire() {
    return destinataire;
  }

  public void setDestinataire(@javax.annotation.Nonnull Map<String, Object> destinataire) {
    this.destinataire = destinataire;
  }


  public DonneesFactureSimplifiees lignes(@javax.annotation.Nonnull List<Map<String, Object>> lignes) {
    this.lignes = lignes;
    return this;
  }

  public DonneesFactureSimplifiees addLignesItem(Map<String, Object> lignesItem) {
    if (this.lignes == null) {
      this.lignes = new ArrayList<>();
    }
    this.lignes.add(lignesItem);
    return this;
  }

  /**
   * Lignes de la facture
   * @return lignes
   */
  @javax.annotation.Nonnull
  public List<Map<String, Object>> getLignes() {
    return lignes;
  }

  public void setLignes(@javax.annotation.Nonnull List<Map<String, Object>> lignes) {
    this.lignes = lignes;
  }


  public DonneesFactureSimplifiees date(@javax.annotation.Nullable String date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
   */
  @javax.annotation.Nullable
  public String getDate() {
    return date;
  }

  public void setDate(@javax.annotation.Nullable String date) {
    this.date = date;
  }


  public DonneesFactureSimplifiees echeanceJours(@javax.annotation.Nullable Integer echeanceJours) {
    this.echeanceJours = echeanceJours;
    return this;
  }

  /**
   * Échéance en jours (défaut: 30)
   * minimum: 0
   * maximum: 365
   * @return echeanceJours
   */
  @javax.annotation.Nullable
  public Integer getEcheanceJours() {
    return echeanceJours;
  }

  public void setEcheanceJours(@javax.annotation.Nullable Integer echeanceJours) {
    this.echeanceJours = echeanceJours;
  }


  public DonneesFactureSimplifiees commentaire(@javax.annotation.Nullable String commentaire) {
    this.commentaire = commentaire;
    return this;
  }

  /**
   * Get commentaire
   * @return commentaire
   */
  @javax.annotation.Nullable
  public String getCommentaire() {
    return commentaire;
  }

  public void setCommentaire(@javax.annotation.Nullable String commentaire) {
    this.commentaire = commentaire;
  }


  public DonneesFactureSimplifiees numeroBonCommande(@javax.annotation.Nullable String numeroBonCommande) {
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


  public DonneesFactureSimplifiees numeroMarche(@javax.annotation.Nullable String numeroMarche) {
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



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DonneesFactureSimplifiees donneesFactureSimplifiees = (DonneesFactureSimplifiees) o;
    return Objects.equals(this.numero, donneesFactureSimplifiees.numero) &&
        Objects.equals(this.emetteur, donneesFactureSimplifiees.emetteur) &&
        Objects.equals(this.destinataire, donneesFactureSimplifiees.destinataire) &&
        Objects.equals(this.lignes, donneesFactureSimplifiees.lignes) &&
        Objects.equals(this.date, donneesFactureSimplifiees.date) &&
        Objects.equals(this.echeanceJours, donneesFactureSimplifiees.echeanceJours) &&
        Objects.equals(this.commentaire, donneesFactureSimplifiees.commentaire) &&
        Objects.equals(this.numeroBonCommande, donneesFactureSimplifiees.numeroBonCommande) &&
        Objects.equals(this.numeroMarche, donneesFactureSimplifiees.numeroMarche);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(numero, emetteur, destinataire, lignes, date, echeanceJours, commentaire, numeroBonCommande, numeroMarche);
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
    sb.append("class DonneesFactureSimplifiees {\n");
    sb.append("    numero: ").append(toIndentedString(numero)).append("\n");
    sb.append("    emetteur: ").append(toIndentedString(emetteur)).append("\n");
    sb.append("    destinataire: ").append(toIndentedString(destinataire)).append("\n");
    sb.append("    lignes: ").append(toIndentedString(lignes)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    echeanceJours: ").append(toIndentedString(echeanceJours)).append("\n");
    sb.append("    commentaire: ").append(toIndentedString(commentaire)).append("\n");
    sb.append("    numeroBonCommande: ").append(toIndentedString(numeroBonCommande)).append("\n");
    sb.append("    numeroMarche: ").append(toIndentedString(numeroMarche)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("numero", "emetteur", "destinataire", "lignes", "date", "echeance_jours", "commentaire", "numero_bon_commande", "numero_marche"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(Arrays.asList("numero", "emetteur", "destinataire", "lignes"));
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to DonneesFactureSimplifiees
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!DonneesFactureSimplifiees.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field(s) %s in DonneesFactureSimplifiees is not found in the empty JSON string", DonneesFactureSimplifiees.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!DonneesFactureSimplifiees.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The field `%s` in the JSON string is not defined in the `DonneesFactureSimplifiees` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : DonneesFactureSimplifiees.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (!jsonObj.get("numero").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `numero` to be a primitive type in the JSON string but got `%s`", jsonObj.get("numero").toString()));
      }
      // ensure the required json array is present
      if (jsonObj.get("lignes") == null) {
        throw new IllegalArgumentException("Expected the field `linkedContent` to be an array in the JSON string but got `null`");
      } else if (!jsonObj.get("lignes").isJsonArray()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `lignes` to be an array in the JSON string but got `%s`", jsonObj.get("lignes").toString()));
      }
      if ((jsonObj.get("date") != null && !jsonObj.get("date").isJsonNull()) && !jsonObj.get("date").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `date` to be a primitive type in the JSON string but got `%s`", jsonObj.get("date").toString()));
      }
      if ((jsonObj.get("commentaire") != null && !jsonObj.get("commentaire").isJsonNull()) && !jsonObj.get("commentaire").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `commentaire` to be a primitive type in the JSON string but got `%s`", jsonObj.get("commentaire").toString()));
      }
      if ((jsonObj.get("numero_bon_commande") != null && !jsonObj.get("numero_bon_commande").isJsonNull()) && !jsonObj.get("numero_bon_commande").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `numero_bon_commande` to be a primitive type in the JSON string but got `%s`", jsonObj.get("numero_bon_commande").toString()));
      }
      if ((jsonObj.get("numero_marche") != null && !jsonObj.get("numero_marche").isJsonNull()) && !jsonObj.get("numero_marche").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `numero_marche` to be a primitive type in the JSON string but got `%s`", jsonObj.get("numero_marche").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!DonneesFactureSimplifiees.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'DonneesFactureSimplifiees' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<DonneesFactureSimplifiees> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(DonneesFactureSimplifiees.class));

       return (TypeAdapter<T>) new TypeAdapter<DonneesFactureSimplifiees>() {
           @Override
           public void write(JsonWriter out, DonneesFactureSimplifiees value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public DonneesFactureSimplifiees read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of DonneesFactureSimplifiees given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of DonneesFactureSimplifiees
   * @throws IOException if the JSON string is invalid with respect to DonneesFactureSimplifiees
   */
  public static DonneesFactureSimplifiees fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, DonneesFactureSimplifiees.class);
  }

  /**
   * Convert an instance of DonneesFactureSimplifiees to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

