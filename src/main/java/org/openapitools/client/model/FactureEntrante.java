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
import org.openapitools.client.model.FormatFacture;
import org.openapitools.client.model.FournisseurEntrant;
import org.openapitools.client.model.TypeDocument;
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
 * Facture reçue d&#39;un fournisseur via PDP/PA.  Ce modèle contient les métadonnées essentielles extraites des factures entrantes, quel que soit leur format source (CII, UBL, Factur-X).  Les montants sont en Decimal en Python mais seront sérialisés en string dans le JSON pour préserver la précision monétaire.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-12-10T08:47:10.107605590Z[Etc/UTC]", comments = "Generator version: 7.18.0-SNAPSHOT")
public class FactureEntrante {
  public static final String SERIALIZED_NAME_FLOW_ID = "flow_id";
  @SerializedName(SERIALIZED_NAME_FLOW_ID)
  @javax.annotation.Nullable
  private String flowId;

  public static final String SERIALIZED_NAME_FORMAT_SOURCE = "format_source";
  @SerializedName(SERIALIZED_NAME_FORMAT_SOURCE)
  @javax.annotation.Nonnull
  private FormatFacture formatSource;

  public static final String SERIALIZED_NAME_REF_FOURNISSEUR = "ref_fournisseur";
  @SerializedName(SERIALIZED_NAME_REF_FOURNISSEUR)
  @javax.annotation.Nonnull
  private String refFournisseur;

  public static final String SERIALIZED_NAME_TYPE_DOCUMENT = "type_document";
  @SerializedName(SERIALIZED_NAME_TYPE_DOCUMENT)
  @javax.annotation.Nullable
  private TypeDocument typeDocument = TypeDocument._380;

  public static final String SERIALIZED_NAME_FOURNISSEUR = "fournisseur";
  @SerializedName(SERIALIZED_NAME_FOURNISSEUR)
  @javax.annotation.Nonnull
  private FournisseurEntrant fournisseur;

  public static final String SERIALIZED_NAME_SITE_FACTURATION_NOM = "site_facturation_nom";
  @SerializedName(SERIALIZED_NAME_SITE_FACTURATION_NOM)
  @javax.annotation.Nonnull
  private String siteFacturationNom;

  public static final String SERIALIZED_NAME_SITE_FACTURATION_SIRET = "site_facturation_siret";
  @SerializedName(SERIALIZED_NAME_SITE_FACTURATION_SIRET)
  @javax.annotation.Nullable
  private String siteFacturationSiret;

  public static final String SERIALIZED_NAME_DATE_DE_PIECE = "date_de_piece";
  @SerializedName(SERIALIZED_NAME_DATE_DE_PIECE)
  @javax.annotation.Nonnull
  private String dateDePiece;

  public static final String SERIALIZED_NAME_DATE_REGLEMENT = "date_reglement";
  @SerializedName(SERIALIZED_NAME_DATE_REGLEMENT)
  @javax.annotation.Nullable
  private String dateReglement;

  public static final String SERIALIZED_NAME_DEVISE = "devise";
  @SerializedName(SERIALIZED_NAME_DEVISE)
  @javax.annotation.Nullable
  private String devise = "EUR";

  public static final String SERIALIZED_NAME_MONTANT_HT = "montant_ht";
  @SerializedName(SERIALIZED_NAME_MONTANT_HT)
  @javax.annotation.Nonnull
  private String montantHt;

  public static final String SERIALIZED_NAME_MONTANT_TVA = "montant_tva";
  @SerializedName(SERIALIZED_NAME_MONTANT_TVA)
  @javax.annotation.Nonnull
  private String montantTva;

  public static final String SERIALIZED_NAME_MONTANT_TTC = "montant_ttc";
  @SerializedName(SERIALIZED_NAME_MONTANT_TTC)
  @javax.annotation.Nonnull
  private String montantTtc;

  public static final String SERIALIZED_NAME_NUMERO_BON_COMMANDE = "numero_bon_commande";
  @SerializedName(SERIALIZED_NAME_NUMERO_BON_COMMANDE)
  @javax.annotation.Nullable
  private String numeroBonCommande;

  public static final String SERIALIZED_NAME_REFERENCE_CONTRAT = "reference_contrat";
  @SerializedName(SERIALIZED_NAME_REFERENCE_CONTRAT)
  @javax.annotation.Nullable
  private String referenceContrat;

  public static final String SERIALIZED_NAME_OBJET_FACTURE = "objet_facture";
  @SerializedName(SERIALIZED_NAME_OBJET_FACTURE)
  @javax.annotation.Nullable
  private String objetFacture;

  public static final String SERIALIZED_NAME_DOCUMENT_BASE64 = "document_base64";
  @SerializedName(SERIALIZED_NAME_DOCUMENT_BASE64)
  @javax.annotation.Nullable
  private String documentBase64;

  public static final String SERIALIZED_NAME_DOCUMENT_CONTENT_TYPE = "document_content_type";
  @SerializedName(SERIALIZED_NAME_DOCUMENT_CONTENT_TYPE)
  @javax.annotation.Nullable
  private String documentContentType;

  public static final String SERIALIZED_NAME_DOCUMENT_FILENAME = "document_filename";
  @SerializedName(SERIALIZED_NAME_DOCUMENT_FILENAME)
  @javax.annotation.Nullable
  private String documentFilename;

  public FactureEntrante() {
  }

  public FactureEntrante flowId(@javax.annotation.Nullable String flowId) {
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


  public FactureEntrante formatSource(@javax.annotation.Nonnull FormatFacture formatSource) {
    this.formatSource = formatSource;
    return this;
  }

  /**
   * Format source de la facture
   * @return formatSource
   */
  @javax.annotation.Nonnull
  public FormatFacture getFormatSource() {
    return formatSource;
  }

  public void setFormatSource(@javax.annotation.Nonnull FormatFacture formatSource) {
    this.formatSource = formatSource;
  }


  public FactureEntrante refFournisseur(@javax.annotation.Nonnull String refFournisseur) {
    this.refFournisseur = refFournisseur;
    return this;
  }

  /**
   * Numéro de facture émis par le fournisseur (BT-1)
   * @return refFournisseur
   */
  @javax.annotation.Nonnull
  public String getRefFournisseur() {
    return refFournisseur;
  }

  public void setRefFournisseur(@javax.annotation.Nonnull String refFournisseur) {
    this.refFournisseur = refFournisseur;
  }


  public FactureEntrante typeDocument(@javax.annotation.Nullable TypeDocument typeDocument) {
    this.typeDocument = typeDocument;
    return this;
  }

  /**
   * Type de document (BT-3)
   * @return typeDocument
   */
  @javax.annotation.Nullable
  public TypeDocument getTypeDocument() {
    return typeDocument;
  }

  public void setTypeDocument(@javax.annotation.Nullable TypeDocument typeDocument) {
    this.typeDocument = typeDocument;
  }


  public FactureEntrante fournisseur(@javax.annotation.Nonnull FournisseurEntrant fournisseur) {
    this.fournisseur = fournisseur;
    return this;
  }

  /**
   * Émetteur de la facture (SellerTradeParty)
   * @return fournisseur
   */
  @javax.annotation.Nonnull
  public FournisseurEntrant getFournisseur() {
    return fournisseur;
  }

  public void setFournisseur(@javax.annotation.Nonnull FournisseurEntrant fournisseur) {
    this.fournisseur = fournisseur;
  }


  public FactureEntrante siteFacturationNom(@javax.annotation.Nonnull String siteFacturationNom) {
    this.siteFacturationNom = siteFacturationNom;
    return this;
  }

  /**
   * Nom du destinataire / votre entreprise (BT-44)
   * @return siteFacturationNom
   */
  @javax.annotation.Nonnull
  public String getSiteFacturationNom() {
    return siteFacturationNom;
  }

  public void setSiteFacturationNom(@javax.annotation.Nonnull String siteFacturationNom) {
    this.siteFacturationNom = siteFacturationNom;
  }


  public FactureEntrante siteFacturationSiret(@javax.annotation.Nullable String siteFacturationSiret) {
    this.siteFacturationSiret = siteFacturationSiret;
    return this;
  }

  /**
   * Get siteFacturationSiret
   * @return siteFacturationSiret
   */
  @javax.annotation.Nullable
  public String getSiteFacturationSiret() {
    return siteFacturationSiret;
  }

  public void setSiteFacturationSiret(@javax.annotation.Nullable String siteFacturationSiret) {
    this.siteFacturationSiret = siteFacturationSiret;
  }


  public FactureEntrante dateDePiece(@javax.annotation.Nonnull String dateDePiece) {
    this.dateDePiece = dateDePiece;
    return this;
  }

  /**
   * Date de la facture (BT-2) - YYYY-MM-DD
   * @return dateDePiece
   */
  @javax.annotation.Nonnull
  public String getDateDePiece() {
    return dateDePiece;
  }

  public void setDateDePiece(@javax.annotation.Nonnull String dateDePiece) {
    this.dateDePiece = dateDePiece;
  }


  public FactureEntrante dateReglement(@javax.annotation.Nullable String dateReglement) {
    this.dateReglement = dateReglement;
    return this;
  }

  /**
   * Get dateReglement
   * @return dateReglement
   */
  @javax.annotation.Nullable
  public String getDateReglement() {
    return dateReglement;
  }

  public void setDateReglement(@javax.annotation.Nullable String dateReglement) {
    this.dateReglement = dateReglement;
  }


  public FactureEntrante devise(@javax.annotation.Nullable String devise) {
    this.devise = devise;
    return this;
  }

  /**
   * Code devise ISO (BT-5)
   * @return devise
   */
  @javax.annotation.Nullable
  public String getDevise() {
    return devise;
  }

  public void setDevise(@javax.annotation.Nullable String devise) {
    this.devise = devise;
  }


  public FactureEntrante montantHt(@javax.annotation.Nonnull String montantHt) {
    this.montantHt = montantHt;
    return this;
  }

  /**
   * Montant HT total (BT-109)
   * @return montantHt
   */
  @javax.annotation.Nonnull
  public String getMontantHt() {
    return montantHt;
  }

  public void setMontantHt(@javax.annotation.Nonnull String montantHt) {
    this.montantHt = montantHt;
  }


  public FactureEntrante montantTva(@javax.annotation.Nonnull String montantTva) {
    this.montantTva = montantTva;
    return this;
  }

  /**
   * Montant TVA total (BT-110)
   * @return montantTva
   */
  @javax.annotation.Nonnull
  public String getMontantTva() {
    return montantTva;
  }

  public void setMontantTva(@javax.annotation.Nonnull String montantTva) {
    this.montantTva = montantTva;
  }


  public FactureEntrante montantTtc(@javax.annotation.Nonnull String montantTtc) {
    this.montantTtc = montantTtc;
    return this;
  }

  /**
   * Montant TTC total (BT-112)
   * @return montantTtc
   */
  @javax.annotation.Nonnull
  public String getMontantTtc() {
    return montantTtc;
  }

  public void setMontantTtc(@javax.annotation.Nonnull String montantTtc) {
    this.montantTtc = montantTtc;
  }


  public FactureEntrante numeroBonCommande(@javax.annotation.Nullable String numeroBonCommande) {
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


  public FactureEntrante referenceContrat(@javax.annotation.Nullable String referenceContrat) {
    this.referenceContrat = referenceContrat;
    return this;
  }

  /**
   * Get referenceContrat
   * @return referenceContrat
   */
  @javax.annotation.Nullable
  public String getReferenceContrat() {
    return referenceContrat;
  }

  public void setReferenceContrat(@javax.annotation.Nullable String referenceContrat) {
    this.referenceContrat = referenceContrat;
  }


  public FactureEntrante objetFacture(@javax.annotation.Nullable String objetFacture) {
    this.objetFacture = objetFacture;
    return this;
  }

  /**
   * Get objetFacture
   * @return objetFacture
   */
  @javax.annotation.Nullable
  public String getObjetFacture() {
    return objetFacture;
  }

  public void setObjetFacture(@javax.annotation.Nullable String objetFacture) {
    this.objetFacture = objetFacture;
  }


  public FactureEntrante documentBase64(@javax.annotation.Nullable String documentBase64) {
    this.documentBase64 = documentBase64;
    return this;
  }

  /**
   * Get documentBase64
   * @return documentBase64
   */
  @javax.annotation.Nullable
  public String getDocumentBase64() {
    return documentBase64;
  }

  public void setDocumentBase64(@javax.annotation.Nullable String documentBase64) {
    this.documentBase64 = documentBase64;
  }


  public FactureEntrante documentContentType(@javax.annotation.Nullable String documentContentType) {
    this.documentContentType = documentContentType;
    return this;
  }

  /**
   * Get documentContentType
   * @return documentContentType
   */
  @javax.annotation.Nullable
  public String getDocumentContentType() {
    return documentContentType;
  }

  public void setDocumentContentType(@javax.annotation.Nullable String documentContentType) {
    this.documentContentType = documentContentType;
  }


  public FactureEntrante documentFilename(@javax.annotation.Nullable String documentFilename) {
    this.documentFilename = documentFilename;
    return this;
  }

  /**
   * Get documentFilename
   * @return documentFilename
   */
  @javax.annotation.Nullable
  public String getDocumentFilename() {
    return documentFilename;
  }

  public void setDocumentFilename(@javax.annotation.Nullable String documentFilename) {
    this.documentFilename = documentFilename;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FactureEntrante factureEntrante = (FactureEntrante) o;
    return Objects.equals(this.flowId, factureEntrante.flowId) &&
        Objects.equals(this.formatSource, factureEntrante.formatSource) &&
        Objects.equals(this.refFournisseur, factureEntrante.refFournisseur) &&
        Objects.equals(this.typeDocument, factureEntrante.typeDocument) &&
        Objects.equals(this.fournisseur, factureEntrante.fournisseur) &&
        Objects.equals(this.siteFacturationNom, factureEntrante.siteFacturationNom) &&
        Objects.equals(this.siteFacturationSiret, factureEntrante.siteFacturationSiret) &&
        Objects.equals(this.dateDePiece, factureEntrante.dateDePiece) &&
        Objects.equals(this.dateReglement, factureEntrante.dateReglement) &&
        Objects.equals(this.devise, factureEntrante.devise) &&
        Objects.equals(this.montantHt, factureEntrante.montantHt) &&
        Objects.equals(this.montantTva, factureEntrante.montantTva) &&
        Objects.equals(this.montantTtc, factureEntrante.montantTtc) &&
        Objects.equals(this.numeroBonCommande, factureEntrante.numeroBonCommande) &&
        Objects.equals(this.referenceContrat, factureEntrante.referenceContrat) &&
        Objects.equals(this.objetFacture, factureEntrante.objetFacture) &&
        Objects.equals(this.documentBase64, factureEntrante.documentBase64) &&
        Objects.equals(this.documentContentType, factureEntrante.documentContentType) &&
        Objects.equals(this.documentFilename, factureEntrante.documentFilename);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(flowId, formatSource, refFournisseur, typeDocument, fournisseur, siteFacturationNom, siteFacturationSiret, dateDePiece, dateReglement, devise, montantHt, montantTva, montantTtc, numeroBonCommande, referenceContrat, objetFacture, documentBase64, documentContentType, documentFilename);
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
    sb.append("class FactureEntrante {\n");
    sb.append("    flowId: ").append(toIndentedString(flowId)).append("\n");
    sb.append("    formatSource: ").append(toIndentedString(formatSource)).append("\n");
    sb.append("    refFournisseur: ").append(toIndentedString(refFournisseur)).append("\n");
    sb.append("    typeDocument: ").append(toIndentedString(typeDocument)).append("\n");
    sb.append("    fournisseur: ").append(toIndentedString(fournisseur)).append("\n");
    sb.append("    siteFacturationNom: ").append(toIndentedString(siteFacturationNom)).append("\n");
    sb.append("    siteFacturationSiret: ").append(toIndentedString(siteFacturationSiret)).append("\n");
    sb.append("    dateDePiece: ").append(toIndentedString(dateDePiece)).append("\n");
    sb.append("    dateReglement: ").append(toIndentedString(dateReglement)).append("\n");
    sb.append("    devise: ").append(toIndentedString(devise)).append("\n");
    sb.append("    montantHt: ").append(toIndentedString(montantHt)).append("\n");
    sb.append("    montantTva: ").append(toIndentedString(montantTva)).append("\n");
    sb.append("    montantTtc: ").append(toIndentedString(montantTtc)).append("\n");
    sb.append("    numeroBonCommande: ").append(toIndentedString(numeroBonCommande)).append("\n");
    sb.append("    referenceContrat: ").append(toIndentedString(referenceContrat)).append("\n");
    sb.append("    objetFacture: ").append(toIndentedString(objetFacture)).append("\n");
    sb.append("    documentBase64: ").append(toIndentedString(documentBase64)).append("\n");
    sb.append("    documentContentType: ").append(toIndentedString(documentContentType)).append("\n");
    sb.append("    documentFilename: ").append(toIndentedString(documentFilename)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("flow_id", "format_source", "ref_fournisseur", "type_document", "fournisseur", "site_facturation_nom", "site_facturation_siret", "date_de_piece", "date_reglement", "devise", "montant_ht", "montant_tva", "montant_ttc", "numero_bon_commande", "reference_contrat", "objet_facture", "document_base64", "document_content_type", "document_filename"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(Arrays.asList("format_source", "ref_fournisseur", "fournisseur", "site_facturation_nom", "date_de_piece", "montant_ht", "montant_tva", "montant_ttc"));
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to FactureEntrante
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!FactureEntrante.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field(s) %s in FactureEntrante is not found in the empty JSON string", FactureEntrante.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!FactureEntrante.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The field `%s` in the JSON string is not defined in the `FactureEntrante` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : FactureEntrante.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("flow_id") != null && !jsonObj.get("flow_id").isJsonNull()) && !jsonObj.get("flow_id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `flow_id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("flow_id").toString()));
      }
      // validate the required field `format_source`
      FormatFacture.validateJsonElement(jsonObj.get("format_source"));
      if (!jsonObj.get("ref_fournisseur").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `ref_fournisseur` to be a primitive type in the JSON string but got `%s`", jsonObj.get("ref_fournisseur").toString()));
      }
      // validate the optional field `type_document`
      if (jsonObj.get("type_document") != null && !jsonObj.get("type_document").isJsonNull()) {
        TypeDocument.validateJsonElement(jsonObj.get("type_document"));
      }
      // validate the required field `fournisseur`
      FournisseurEntrant.validateJsonElement(jsonObj.get("fournisseur"));
      if (!jsonObj.get("site_facturation_nom").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `site_facturation_nom` to be a primitive type in the JSON string but got `%s`", jsonObj.get("site_facturation_nom").toString()));
      }
      if ((jsonObj.get("site_facturation_siret") != null && !jsonObj.get("site_facturation_siret").isJsonNull()) && !jsonObj.get("site_facturation_siret").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `site_facturation_siret` to be a primitive type in the JSON string but got `%s`", jsonObj.get("site_facturation_siret").toString()));
      }
      if (!jsonObj.get("date_de_piece").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `date_de_piece` to be a primitive type in the JSON string but got `%s`", jsonObj.get("date_de_piece").toString()));
      }
      if ((jsonObj.get("date_reglement") != null && !jsonObj.get("date_reglement").isJsonNull()) && !jsonObj.get("date_reglement").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `date_reglement` to be a primitive type in the JSON string but got `%s`", jsonObj.get("date_reglement").toString()));
      }
      if ((jsonObj.get("devise") != null && !jsonObj.get("devise").isJsonNull()) && !jsonObj.get("devise").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `devise` to be a primitive type in the JSON string but got `%s`", jsonObj.get("devise").toString()));
      }
      if (!jsonObj.get("montant_ht").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `montant_ht` to be a primitive type in the JSON string but got `%s`", jsonObj.get("montant_ht").toString()));
      }
      if (!jsonObj.get("montant_tva").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `montant_tva` to be a primitive type in the JSON string but got `%s`", jsonObj.get("montant_tva").toString()));
      }
      if (!jsonObj.get("montant_ttc").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `montant_ttc` to be a primitive type in the JSON string but got `%s`", jsonObj.get("montant_ttc").toString()));
      }
      if ((jsonObj.get("numero_bon_commande") != null && !jsonObj.get("numero_bon_commande").isJsonNull()) && !jsonObj.get("numero_bon_commande").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `numero_bon_commande` to be a primitive type in the JSON string but got `%s`", jsonObj.get("numero_bon_commande").toString()));
      }
      if ((jsonObj.get("reference_contrat") != null && !jsonObj.get("reference_contrat").isJsonNull()) && !jsonObj.get("reference_contrat").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `reference_contrat` to be a primitive type in the JSON string but got `%s`", jsonObj.get("reference_contrat").toString()));
      }
      if ((jsonObj.get("objet_facture") != null && !jsonObj.get("objet_facture").isJsonNull()) && !jsonObj.get("objet_facture").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `objet_facture` to be a primitive type in the JSON string but got `%s`", jsonObj.get("objet_facture").toString()));
      }
      if ((jsonObj.get("document_base64") != null && !jsonObj.get("document_base64").isJsonNull()) && !jsonObj.get("document_base64").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `document_base64` to be a primitive type in the JSON string but got `%s`", jsonObj.get("document_base64").toString()));
      }
      if ((jsonObj.get("document_content_type") != null && !jsonObj.get("document_content_type").isJsonNull()) && !jsonObj.get("document_content_type").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `document_content_type` to be a primitive type in the JSON string but got `%s`", jsonObj.get("document_content_type").toString()));
      }
      if ((jsonObj.get("document_filename") != null && !jsonObj.get("document_filename").isJsonNull()) && !jsonObj.get("document_filename").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `document_filename` to be a primitive type in the JSON string but got `%s`", jsonObj.get("document_filename").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!FactureEntrante.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'FactureEntrante' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<FactureEntrante> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(FactureEntrante.class));

       return (TypeAdapter<T>) new TypeAdapter<FactureEntrante>() {
           @Override
           public void write(JsonWriter out, FactureEntrante value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public FactureEntrante read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of FactureEntrante given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of FactureEntrante
   * @throws IOException if the JSON string is invalid with respect to FactureEntrante
   */
  public static FactureEntrante fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, FactureEntrante.class);
  }

  /**
   * Convert an instance of FactureEntrante to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

