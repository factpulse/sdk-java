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
import org.openapitools.client.model.CategorieTVA;
import org.openapitools.client.model.CodeRaisonReduction;
import org.openapitools.client.model.LigneDePosteMontantRemiseHt;
import org.openapitools.client.model.LigneDePosteTauxTvaManuel;
import org.openapitools.client.model.MontantTotalLigneHt;
import org.openapitools.client.model.MontantUnitaireHt;
import org.openapitools.client.model.Quantite;
import org.openapitools.client.model.Unite;
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
 * Représente une ligne de détail dans une facture.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-11-26T13:43:38.907758395Z[Etc/UTC]", comments = "Generator version: 7.18.0-SNAPSHOT")
public class LigneDePoste {
  public static final String SERIALIZED_NAME_NUMERO = "numero";
  @SerializedName(SERIALIZED_NAME_NUMERO)
  @javax.annotation.Nonnull
  private Integer numero;

  public static final String SERIALIZED_NAME_REFERENCE = "reference";
  @SerializedName(SERIALIZED_NAME_REFERENCE)
  @javax.annotation.Nullable
  private String reference;

  public static final String SERIALIZED_NAME_DENOMINATION = "denomination";
  @SerializedName(SERIALIZED_NAME_DENOMINATION)
  @javax.annotation.Nonnull
  private String denomination;

  public static final String SERIALIZED_NAME_QUANTITE = "quantite";
  @SerializedName(SERIALIZED_NAME_QUANTITE)
  @javax.annotation.Nonnull
  private Quantite quantite;

  public static final String SERIALIZED_NAME_UNITE = "unite";
  @SerializedName(SERIALIZED_NAME_UNITE)
  @javax.annotation.Nonnull
  private Unite unite;

  public static final String SERIALIZED_NAME_MONTANT_UNITAIRE_HT = "montantUnitaireHt";
  @SerializedName(SERIALIZED_NAME_MONTANT_UNITAIRE_HT)
  @javax.annotation.Nonnull
  private MontantUnitaireHt montantUnitaireHt;

  public static final String SERIALIZED_NAME_MONTANT_REMISE_HT = "montantRemiseHt";
  @SerializedName(SERIALIZED_NAME_MONTANT_REMISE_HT)
  @javax.annotation.Nullable
  private LigneDePosteMontantRemiseHt montantRemiseHt;

  public static final String SERIALIZED_NAME_MONTANT_TOTAL_LIGNE_HT = "montantTotalLigneHt";
  @SerializedName(SERIALIZED_NAME_MONTANT_TOTAL_LIGNE_HT)
  @javax.annotation.Nullable
  private MontantTotalLigneHt montantTotalLigneHt;

  public static final String SERIALIZED_NAME_TAUX_TVA = "tauxTva";
  @SerializedName(SERIALIZED_NAME_TAUX_TVA)
  @javax.annotation.Nullable
  private String tauxTva;

  public static final String SERIALIZED_NAME_TAUX_TVA_MANUEL = "tauxTvaManuel";
  @SerializedName(SERIALIZED_NAME_TAUX_TVA_MANUEL)
  @javax.annotation.Nullable
  private LigneDePosteTauxTvaManuel tauxTvaManuel;

  public static final String SERIALIZED_NAME_CATEGORIE_TVA = "categorieTva";
  @SerializedName(SERIALIZED_NAME_CATEGORIE_TVA)
  @javax.annotation.Nullable
  private CategorieTVA categorieTva;

  public static final String SERIALIZED_NAME_DATE_DEBUT_PERIODE = "dateDebutPeriode";
  @SerializedName(SERIALIZED_NAME_DATE_DEBUT_PERIODE)
  @javax.annotation.Nullable
  private String dateDebutPeriode;

  public static final String SERIALIZED_NAME_DATE_FIN_PERIODE = "dateFinPeriode";
  @SerializedName(SERIALIZED_NAME_DATE_FIN_PERIODE)
  @javax.annotation.Nullable
  private String dateFinPeriode;

  public static final String SERIALIZED_NAME_CODE_RAISON_REDUCTION = "codeRaisonReduction";
  @SerializedName(SERIALIZED_NAME_CODE_RAISON_REDUCTION)
  @javax.annotation.Nullable
  private CodeRaisonReduction codeRaisonReduction;

  public static final String SERIALIZED_NAME_RAISON_REDUCTION = "raisonReduction";
  @SerializedName(SERIALIZED_NAME_RAISON_REDUCTION)
  @javax.annotation.Nullable
  private String raisonReduction;

  public LigneDePoste() {
  }

  public LigneDePoste numero(@javax.annotation.Nonnull Integer numero) {
    this.numero = numero;
    return this;
  }

  /**
   * Get numero
   * @return numero
   */
  @javax.annotation.Nonnull
  public Integer getNumero() {
    return numero;
  }

  public void setNumero(@javax.annotation.Nonnull Integer numero) {
    this.numero = numero;
  }


  public LigneDePoste reference(@javax.annotation.Nullable String reference) {
    this.reference = reference;
    return this;
  }

  /**
   * Get reference
   * @return reference
   */
  @javax.annotation.Nullable
  public String getReference() {
    return reference;
  }

  public void setReference(@javax.annotation.Nullable String reference) {
    this.reference = reference;
  }


  public LigneDePoste denomination(@javax.annotation.Nonnull String denomination) {
    this.denomination = denomination;
    return this;
  }

  /**
   * Get denomination
   * @return denomination
   */
  @javax.annotation.Nonnull
  public String getDenomination() {
    return denomination;
  }

  public void setDenomination(@javax.annotation.Nonnull String denomination) {
    this.denomination = denomination;
  }


  public LigneDePoste quantite(@javax.annotation.Nonnull Quantite quantite) {
    this.quantite = quantite;
    return this;
  }

  /**
   * Get quantite
   * @return quantite
   */
  @javax.annotation.Nonnull
  public Quantite getQuantite() {
    return quantite;
  }

  public void setQuantite(@javax.annotation.Nonnull Quantite quantite) {
    this.quantite = quantite;
  }


  public LigneDePoste unite(@javax.annotation.Nonnull Unite unite) {
    this.unite = unite;
    return this;
  }

  /**
   * Get unite
   * @return unite
   */
  @javax.annotation.Nonnull
  public Unite getUnite() {
    return unite;
  }

  public void setUnite(@javax.annotation.Nonnull Unite unite) {
    this.unite = unite;
  }


  public LigneDePoste montantUnitaireHt(@javax.annotation.Nonnull MontantUnitaireHt montantUnitaireHt) {
    this.montantUnitaireHt = montantUnitaireHt;
    return this;
  }

  /**
   * Get montantUnitaireHt
   * @return montantUnitaireHt
   */
  @javax.annotation.Nonnull
  public MontantUnitaireHt getMontantUnitaireHt() {
    return montantUnitaireHt;
  }

  public void setMontantUnitaireHt(@javax.annotation.Nonnull MontantUnitaireHt montantUnitaireHt) {
    this.montantUnitaireHt = montantUnitaireHt;
  }


  public LigneDePoste montantRemiseHt(@javax.annotation.Nullable LigneDePosteMontantRemiseHt montantRemiseHt) {
    this.montantRemiseHt = montantRemiseHt;
    return this;
  }

  /**
   * Get montantRemiseHt
   * @return montantRemiseHt
   */
  @javax.annotation.Nullable
  public LigneDePosteMontantRemiseHt getMontantRemiseHt() {
    return montantRemiseHt;
  }

  public void setMontantRemiseHt(@javax.annotation.Nullable LigneDePosteMontantRemiseHt montantRemiseHt) {
    this.montantRemiseHt = montantRemiseHt;
  }


  public LigneDePoste montantTotalLigneHt(@javax.annotation.Nullable MontantTotalLigneHt montantTotalLigneHt) {
    this.montantTotalLigneHt = montantTotalLigneHt;
    return this;
  }

  /**
   * Get montantTotalLigneHt
   * @return montantTotalLigneHt
   */
  @javax.annotation.Nullable
  public MontantTotalLigneHt getMontantTotalLigneHt() {
    return montantTotalLigneHt;
  }

  public void setMontantTotalLigneHt(@javax.annotation.Nullable MontantTotalLigneHt montantTotalLigneHt) {
    this.montantTotalLigneHt = montantTotalLigneHt;
  }


  public LigneDePoste tauxTva(@javax.annotation.Nullable String tauxTva) {
    this.tauxTva = tauxTva;
    return this;
  }

  /**
   * Get tauxTva
   * @return tauxTva
   */
  @javax.annotation.Nullable
  public String getTauxTva() {
    return tauxTva;
  }

  public void setTauxTva(@javax.annotation.Nullable String tauxTva) {
    this.tauxTva = tauxTva;
  }


  public LigneDePoste tauxTvaManuel(@javax.annotation.Nullable LigneDePosteTauxTvaManuel tauxTvaManuel) {
    this.tauxTvaManuel = tauxTvaManuel;
    return this;
  }

  /**
   * Get tauxTvaManuel
   * @return tauxTvaManuel
   */
  @javax.annotation.Nullable
  public LigneDePosteTauxTvaManuel getTauxTvaManuel() {
    return tauxTvaManuel;
  }

  public void setTauxTvaManuel(@javax.annotation.Nullable LigneDePosteTauxTvaManuel tauxTvaManuel) {
    this.tauxTvaManuel = tauxTvaManuel;
  }


  public LigneDePoste categorieTva(@javax.annotation.Nullable CategorieTVA categorieTva) {
    this.categorieTva = categorieTva;
    return this;
  }

  /**
   * Get categorieTva
   * @return categorieTva
   */
  @javax.annotation.Nullable
  public CategorieTVA getCategorieTva() {
    return categorieTva;
  }

  public void setCategorieTva(@javax.annotation.Nullable CategorieTVA categorieTva) {
    this.categorieTva = categorieTva;
  }


  public LigneDePoste dateDebutPeriode(@javax.annotation.Nullable String dateDebutPeriode) {
    this.dateDebutPeriode = dateDebutPeriode;
    return this;
  }

  /**
   * Get dateDebutPeriode
   * @return dateDebutPeriode
   */
  @javax.annotation.Nullable
  public String getDateDebutPeriode() {
    return dateDebutPeriode;
  }

  public void setDateDebutPeriode(@javax.annotation.Nullable String dateDebutPeriode) {
    this.dateDebutPeriode = dateDebutPeriode;
  }


  public LigneDePoste dateFinPeriode(@javax.annotation.Nullable String dateFinPeriode) {
    this.dateFinPeriode = dateFinPeriode;
    return this;
  }

  /**
   * Get dateFinPeriode
   * @return dateFinPeriode
   */
  @javax.annotation.Nullable
  public String getDateFinPeriode() {
    return dateFinPeriode;
  }

  public void setDateFinPeriode(@javax.annotation.Nullable String dateFinPeriode) {
    this.dateFinPeriode = dateFinPeriode;
  }


  public LigneDePoste codeRaisonReduction(@javax.annotation.Nullable CodeRaisonReduction codeRaisonReduction) {
    this.codeRaisonReduction = codeRaisonReduction;
    return this;
  }

  /**
   * Get codeRaisonReduction
   * @return codeRaisonReduction
   */
  @javax.annotation.Nullable
  public CodeRaisonReduction getCodeRaisonReduction() {
    return codeRaisonReduction;
  }

  public void setCodeRaisonReduction(@javax.annotation.Nullable CodeRaisonReduction codeRaisonReduction) {
    this.codeRaisonReduction = codeRaisonReduction;
  }


  public LigneDePoste raisonReduction(@javax.annotation.Nullable String raisonReduction) {
    this.raisonReduction = raisonReduction;
    return this;
  }

  /**
   * Get raisonReduction
   * @return raisonReduction
   */
  @javax.annotation.Nullable
  public String getRaisonReduction() {
    return raisonReduction;
  }

  public void setRaisonReduction(@javax.annotation.Nullable String raisonReduction) {
    this.raisonReduction = raisonReduction;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LigneDePoste ligneDePoste = (LigneDePoste) o;
    return Objects.equals(this.numero, ligneDePoste.numero) &&
        Objects.equals(this.reference, ligneDePoste.reference) &&
        Objects.equals(this.denomination, ligneDePoste.denomination) &&
        Objects.equals(this.quantite, ligneDePoste.quantite) &&
        Objects.equals(this.unite, ligneDePoste.unite) &&
        Objects.equals(this.montantUnitaireHt, ligneDePoste.montantUnitaireHt) &&
        Objects.equals(this.montantRemiseHt, ligneDePoste.montantRemiseHt) &&
        Objects.equals(this.montantTotalLigneHt, ligneDePoste.montantTotalLigneHt) &&
        Objects.equals(this.tauxTva, ligneDePoste.tauxTva) &&
        Objects.equals(this.tauxTvaManuel, ligneDePoste.tauxTvaManuel) &&
        Objects.equals(this.categorieTva, ligneDePoste.categorieTva) &&
        Objects.equals(this.dateDebutPeriode, ligneDePoste.dateDebutPeriode) &&
        Objects.equals(this.dateFinPeriode, ligneDePoste.dateFinPeriode) &&
        Objects.equals(this.codeRaisonReduction, ligneDePoste.codeRaisonReduction) &&
        Objects.equals(this.raisonReduction, ligneDePoste.raisonReduction);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(numero, reference, denomination, quantite, unite, montantUnitaireHt, montantRemiseHt, montantTotalLigneHt, tauxTva, tauxTvaManuel, categorieTva, dateDebutPeriode, dateFinPeriode, codeRaisonReduction, raisonReduction);
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
    sb.append("class LigneDePoste {\n");
    sb.append("    numero: ").append(toIndentedString(numero)).append("\n");
    sb.append("    reference: ").append(toIndentedString(reference)).append("\n");
    sb.append("    denomination: ").append(toIndentedString(denomination)).append("\n");
    sb.append("    quantite: ").append(toIndentedString(quantite)).append("\n");
    sb.append("    unite: ").append(toIndentedString(unite)).append("\n");
    sb.append("    montantUnitaireHt: ").append(toIndentedString(montantUnitaireHt)).append("\n");
    sb.append("    montantRemiseHt: ").append(toIndentedString(montantRemiseHt)).append("\n");
    sb.append("    montantTotalLigneHt: ").append(toIndentedString(montantTotalLigneHt)).append("\n");
    sb.append("    tauxTva: ").append(toIndentedString(tauxTva)).append("\n");
    sb.append("    tauxTvaManuel: ").append(toIndentedString(tauxTvaManuel)).append("\n");
    sb.append("    categorieTva: ").append(toIndentedString(categorieTva)).append("\n");
    sb.append("    dateDebutPeriode: ").append(toIndentedString(dateDebutPeriode)).append("\n");
    sb.append("    dateFinPeriode: ").append(toIndentedString(dateFinPeriode)).append("\n");
    sb.append("    codeRaisonReduction: ").append(toIndentedString(codeRaisonReduction)).append("\n");
    sb.append("    raisonReduction: ").append(toIndentedString(raisonReduction)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("numero", "reference", "denomination", "quantite", "unite", "montantUnitaireHt", "montantRemiseHt", "montantTotalLigneHt", "tauxTva", "tauxTvaManuel", "categorieTva", "dateDebutPeriode", "dateFinPeriode", "codeRaisonReduction", "raisonReduction"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(Arrays.asList("numero", "denomination", "quantite", "unite", "montantUnitaireHt"));
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to LigneDePoste
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!LigneDePoste.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field(s) %s in LigneDePoste is not found in the empty JSON string", LigneDePoste.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!LigneDePoste.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The field `%s` in the JSON string is not defined in the `LigneDePoste` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : LigneDePoste.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("reference") != null && !jsonObj.get("reference").isJsonNull()) && !jsonObj.get("reference").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `reference` to be a primitive type in the JSON string but got `%s`", jsonObj.get("reference").toString()));
      }
      if (!jsonObj.get("denomination").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `denomination` to be a primitive type in the JSON string but got `%s`", jsonObj.get("denomination").toString()));
      }
      // validate the required field `quantite`
      Quantite.validateJsonElement(jsonObj.get("quantite"));
      // validate the required field `unite`
      Unite.validateJsonElement(jsonObj.get("unite"));
      // validate the required field `montantUnitaireHt`
      MontantUnitaireHt.validateJsonElement(jsonObj.get("montantUnitaireHt"));
      // validate the optional field `montantRemiseHt`
      if (jsonObj.get("montantRemiseHt") != null && !jsonObj.get("montantRemiseHt").isJsonNull()) {
        LigneDePosteMontantRemiseHt.validateJsonElement(jsonObj.get("montantRemiseHt"));
      }
      // validate the optional field `montantTotalLigneHt`
      if (jsonObj.get("montantTotalLigneHt") != null && !jsonObj.get("montantTotalLigneHt").isJsonNull()) {
        MontantTotalLigneHt.validateJsonElement(jsonObj.get("montantTotalLigneHt"));
      }
      if ((jsonObj.get("tauxTva") != null && !jsonObj.get("tauxTva").isJsonNull()) && !jsonObj.get("tauxTva").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `tauxTva` to be a primitive type in the JSON string but got `%s`", jsonObj.get("tauxTva").toString()));
      }
      // validate the optional field `tauxTvaManuel`
      if (jsonObj.get("tauxTvaManuel") != null && !jsonObj.get("tauxTvaManuel").isJsonNull()) {
        LigneDePosteTauxTvaManuel.validateJsonElement(jsonObj.get("tauxTvaManuel"));
      }
      // validate the optional field `categorieTva`
      if (jsonObj.get("categorieTva") != null && !jsonObj.get("categorieTva").isJsonNull()) {
        CategorieTVA.validateJsonElement(jsonObj.get("categorieTva"));
      }
      if ((jsonObj.get("dateDebutPeriode") != null && !jsonObj.get("dateDebutPeriode").isJsonNull()) && !jsonObj.get("dateDebutPeriode").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `dateDebutPeriode` to be a primitive type in the JSON string but got `%s`", jsonObj.get("dateDebutPeriode").toString()));
      }
      if ((jsonObj.get("dateFinPeriode") != null && !jsonObj.get("dateFinPeriode").isJsonNull()) && !jsonObj.get("dateFinPeriode").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `dateFinPeriode` to be a primitive type in the JSON string but got `%s`", jsonObj.get("dateFinPeriode").toString()));
      }
      // validate the optional field `codeRaisonReduction`
      if (jsonObj.get("codeRaisonReduction") != null && !jsonObj.get("codeRaisonReduction").isJsonNull()) {
        CodeRaisonReduction.validateJsonElement(jsonObj.get("codeRaisonReduction"));
      }
      if ((jsonObj.get("raisonReduction") != null && !jsonObj.get("raisonReduction").isJsonNull()) && !jsonObj.get("raisonReduction").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `raisonReduction` to be a primitive type in the JSON string but got `%s`", jsonObj.get("raisonReduction").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!LigneDePoste.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'LigneDePoste' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<LigneDePoste> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(LigneDePoste.class));

       return (TypeAdapter<T>) new TypeAdapter<LigneDePoste>() {
           @Override
           public void write(JsonWriter out, LigneDePoste value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public LigneDePoste read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of LigneDePoste given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of LigneDePoste
   * @throws IOException if the JSON string is invalid with respect to LigneDePoste
   */
  public static LigneDePoste fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, LigneDePoste.class);
  }

  /**
   * Convert an instance of LigneDePoste to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

