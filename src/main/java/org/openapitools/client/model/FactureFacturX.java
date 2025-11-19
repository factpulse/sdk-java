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
import java.util.List;
import org.openapitools.client.model.CadreDeFacturation;
import org.openapitools.client.model.Destinataire;
import org.openapitools.client.model.Fournisseur;
import org.openapitools.client.model.LigneDePoste;
import org.openapitools.client.model.LigneDeTVA;
import org.openapitools.client.model.ModeDepot;
import org.openapitools.client.model.MontantTotal;
import org.openapitools.client.model.Note;
import org.openapitools.client.model.PieceJointeComplementaire;
import org.openapitools.client.model.References;
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
 * Modèle de données pour une facture destinée à être convertie en Factur-X.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-11-19T15:21:40.682722075Z[Etc/UTC]", comments = "Generator version: 7.18.0-SNAPSHOT")
public class FactureFacturX {
  public static final String SERIALIZED_NAME_NUMERO_FACTURE = "numeroFacture";
  @SerializedName(SERIALIZED_NAME_NUMERO_FACTURE)
  @javax.annotation.Nonnull
  private String numeroFacture;

  public static final String SERIALIZED_NAME_DATE_ECHEANCE_PAIEMENT = "dateEcheancePaiement";
  @SerializedName(SERIALIZED_NAME_DATE_ECHEANCE_PAIEMENT)
  @javax.annotation.Nonnull
  private String dateEcheancePaiement;

  public static final String SERIALIZED_NAME_DATE_FACTURE = "dateFacture";
  @SerializedName(SERIALIZED_NAME_DATE_FACTURE)
  @javax.annotation.Nullable
  private String dateFacture;

  public static final String SERIALIZED_NAME_MODE_DEPOT = "modeDepot";
  @SerializedName(SERIALIZED_NAME_MODE_DEPOT)
  @javax.annotation.Nonnull
  private ModeDepot modeDepot;

  public static final String SERIALIZED_NAME_DESTINATAIRE = "destinataire";
  @SerializedName(SERIALIZED_NAME_DESTINATAIRE)
  @javax.annotation.Nonnull
  private Destinataire destinataire;

  public static final String SERIALIZED_NAME_FOURNISSEUR = "fournisseur";
  @SerializedName(SERIALIZED_NAME_FOURNISSEUR)
  @javax.annotation.Nonnull
  private Fournisseur fournisseur;

  public static final String SERIALIZED_NAME_CADRE_DE_FACTURATION = "cadreDeFacturation";
  @SerializedName(SERIALIZED_NAME_CADRE_DE_FACTURATION)
  @javax.annotation.Nonnull
  private CadreDeFacturation cadreDeFacturation;

  public static final String SERIALIZED_NAME_REFERENCES = "references";
  @SerializedName(SERIALIZED_NAME_REFERENCES)
  @javax.annotation.Nonnull
  private References references;

  public static final String SERIALIZED_NAME_MONTANT_TOTAL = "montantTotal";
  @SerializedName(SERIALIZED_NAME_MONTANT_TOTAL)
  @javax.annotation.Nonnull
  private MontantTotal montantTotal;

  public static final String SERIALIZED_NAME_LIGNES_DE_POSTE = "lignesDePoste";
  @SerializedName(SERIALIZED_NAME_LIGNES_DE_POSTE)
  @javax.annotation.Nullable
  private List<LigneDePoste> lignesDePoste = new ArrayList<>();

  public static final String SERIALIZED_NAME_LIGNES_DE_TVA = "lignesDeTva";
  @SerializedName(SERIALIZED_NAME_LIGNES_DE_TVA)
  @javax.annotation.Nullable
  private List<LigneDeTVA> lignesDeTva = new ArrayList<>();

  public static final String SERIALIZED_NAME_NOTES = "notes";
  @SerializedName(SERIALIZED_NAME_NOTES)
  @javax.annotation.Nullable
  private List<Note> notes = new ArrayList<>();

  public static final String SERIALIZED_NAME_COMMENTAIRE = "commentaire";
  @SerializedName(SERIALIZED_NAME_COMMENTAIRE)
  @javax.annotation.Nullable
  private String commentaire;

  public static final String SERIALIZED_NAME_ID_UTILISATEUR_COURANT = "idUtilisateurCourant";
  @SerializedName(SERIALIZED_NAME_ID_UTILISATEUR_COURANT)
  @javax.annotation.Nullable
  private Integer idUtilisateurCourant;

  public static final String SERIALIZED_NAME_PIECES_JOINTES_COMPLEMENTAIRES = "piecesJointesComplementaires";
  @SerializedName(SERIALIZED_NAME_PIECES_JOINTES_COMPLEMENTAIRES)
  @javax.annotation.Nullable
  private List<PieceJointeComplementaire> piecesJointesComplementaires;

  public FactureFacturX() {
  }

  public FactureFacturX numeroFacture(@javax.annotation.Nonnull String numeroFacture) {
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


  public FactureFacturX dateEcheancePaiement(@javax.annotation.Nonnull String dateEcheancePaiement) {
    this.dateEcheancePaiement = dateEcheancePaiement;
    return this;
  }

  /**
   * Get dateEcheancePaiement
   * @return dateEcheancePaiement
   */
  @javax.annotation.Nonnull
  public String getDateEcheancePaiement() {
    return dateEcheancePaiement;
  }

  public void setDateEcheancePaiement(@javax.annotation.Nonnull String dateEcheancePaiement) {
    this.dateEcheancePaiement = dateEcheancePaiement;
  }


  public FactureFacturX dateFacture(@javax.annotation.Nullable String dateFacture) {
    this.dateFacture = dateFacture;
    return this;
  }

  /**
   * Get dateFacture
   * @return dateFacture
   */
  @javax.annotation.Nullable
  public String getDateFacture() {
    return dateFacture;
  }

  public void setDateFacture(@javax.annotation.Nullable String dateFacture) {
    this.dateFacture = dateFacture;
  }


  public FactureFacturX modeDepot(@javax.annotation.Nonnull ModeDepot modeDepot) {
    this.modeDepot = modeDepot;
    return this;
  }

  /**
   * Get modeDepot
   * @return modeDepot
   */
  @javax.annotation.Nonnull
  public ModeDepot getModeDepot() {
    return modeDepot;
  }

  public void setModeDepot(@javax.annotation.Nonnull ModeDepot modeDepot) {
    this.modeDepot = modeDepot;
  }


  public FactureFacturX destinataire(@javax.annotation.Nonnull Destinataire destinataire) {
    this.destinataire = destinataire;
    return this;
  }

  /**
   * Get destinataire
   * @return destinataire
   */
  @javax.annotation.Nonnull
  public Destinataire getDestinataire() {
    return destinataire;
  }

  public void setDestinataire(@javax.annotation.Nonnull Destinataire destinataire) {
    this.destinataire = destinataire;
  }


  public FactureFacturX fournisseur(@javax.annotation.Nonnull Fournisseur fournisseur) {
    this.fournisseur = fournisseur;
    return this;
  }

  /**
   * Get fournisseur
   * @return fournisseur
   */
  @javax.annotation.Nonnull
  public Fournisseur getFournisseur() {
    return fournisseur;
  }

  public void setFournisseur(@javax.annotation.Nonnull Fournisseur fournisseur) {
    this.fournisseur = fournisseur;
  }


  public FactureFacturX cadreDeFacturation(@javax.annotation.Nonnull CadreDeFacturation cadreDeFacturation) {
    this.cadreDeFacturation = cadreDeFacturation;
    return this;
  }

  /**
   * Get cadreDeFacturation
   * @return cadreDeFacturation
   */
  @javax.annotation.Nonnull
  public CadreDeFacturation getCadreDeFacturation() {
    return cadreDeFacturation;
  }

  public void setCadreDeFacturation(@javax.annotation.Nonnull CadreDeFacturation cadreDeFacturation) {
    this.cadreDeFacturation = cadreDeFacturation;
  }


  public FactureFacturX references(@javax.annotation.Nonnull References references) {
    this.references = references;
    return this;
  }

  /**
   * Get references
   * @return references
   */
  @javax.annotation.Nonnull
  public References getReferences() {
    return references;
  }

  public void setReferences(@javax.annotation.Nonnull References references) {
    this.references = references;
  }


  public FactureFacturX montantTotal(@javax.annotation.Nonnull MontantTotal montantTotal) {
    this.montantTotal = montantTotal;
    return this;
  }

  /**
   * Get montantTotal
   * @return montantTotal
   */
  @javax.annotation.Nonnull
  public MontantTotal getMontantTotal() {
    return montantTotal;
  }

  public void setMontantTotal(@javax.annotation.Nonnull MontantTotal montantTotal) {
    this.montantTotal = montantTotal;
  }


  public FactureFacturX lignesDePoste(@javax.annotation.Nullable List<LigneDePoste> lignesDePoste) {
    this.lignesDePoste = lignesDePoste;
    return this;
  }

  public FactureFacturX addLignesDePosteItem(LigneDePoste lignesDePosteItem) {
    if (this.lignesDePoste == null) {
      this.lignesDePoste = new ArrayList<>();
    }
    this.lignesDePoste.add(lignesDePosteItem);
    return this;
  }

  /**
   * Get lignesDePoste
   * @return lignesDePoste
   */
  @javax.annotation.Nullable
  public List<LigneDePoste> getLignesDePoste() {
    return lignesDePoste;
  }

  public void setLignesDePoste(@javax.annotation.Nullable List<LigneDePoste> lignesDePoste) {
    this.lignesDePoste = lignesDePoste;
  }


  public FactureFacturX lignesDeTva(@javax.annotation.Nullable List<LigneDeTVA> lignesDeTva) {
    this.lignesDeTva = lignesDeTva;
    return this;
  }

  public FactureFacturX addLignesDeTvaItem(LigneDeTVA lignesDeTvaItem) {
    if (this.lignesDeTva == null) {
      this.lignesDeTva = new ArrayList<>();
    }
    this.lignesDeTva.add(lignesDeTvaItem);
    return this;
  }

  /**
   * Get lignesDeTva
   * @return lignesDeTva
   */
  @javax.annotation.Nullable
  public List<LigneDeTVA> getLignesDeTva() {
    return lignesDeTva;
  }

  public void setLignesDeTva(@javax.annotation.Nullable List<LigneDeTVA> lignesDeTva) {
    this.lignesDeTva = lignesDeTva;
  }


  public FactureFacturX notes(@javax.annotation.Nullable List<Note> notes) {
    this.notes = notes;
    return this;
  }

  public FactureFacturX addNotesItem(Note notesItem) {
    if (this.notes == null) {
      this.notes = new ArrayList<>();
    }
    this.notes.add(notesItem);
    return this;
  }

  /**
   * Get notes
   * @return notes
   */
  @javax.annotation.Nullable
  public List<Note> getNotes() {
    return notes;
  }

  public void setNotes(@javax.annotation.Nullable List<Note> notes) {
    this.notes = notes;
  }


  public FactureFacturX commentaire(@javax.annotation.Nullable String commentaire) {
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


  public FactureFacturX idUtilisateurCourant(@javax.annotation.Nullable Integer idUtilisateurCourant) {
    this.idUtilisateurCourant = idUtilisateurCourant;
    return this;
  }

  /**
   * Get idUtilisateurCourant
   * @return idUtilisateurCourant
   */
  @javax.annotation.Nullable
  public Integer getIdUtilisateurCourant() {
    return idUtilisateurCourant;
  }

  public void setIdUtilisateurCourant(@javax.annotation.Nullable Integer idUtilisateurCourant) {
    this.idUtilisateurCourant = idUtilisateurCourant;
  }


  public FactureFacturX piecesJointesComplementaires(@javax.annotation.Nullable List<PieceJointeComplementaire> piecesJointesComplementaires) {
    this.piecesJointesComplementaires = piecesJointesComplementaires;
    return this;
  }

  public FactureFacturX addPiecesJointesComplementairesItem(PieceJointeComplementaire piecesJointesComplementairesItem) {
    if (this.piecesJointesComplementaires == null) {
      this.piecesJointesComplementaires = new ArrayList<>();
    }
    this.piecesJointesComplementaires.add(piecesJointesComplementairesItem);
    return this;
  }

  /**
   * Get piecesJointesComplementaires
   * @return piecesJointesComplementaires
   */
  @javax.annotation.Nullable
  public List<PieceJointeComplementaire> getPiecesJointesComplementaires() {
    return piecesJointesComplementaires;
  }

  public void setPiecesJointesComplementaires(@javax.annotation.Nullable List<PieceJointeComplementaire> piecesJointesComplementaires) {
    this.piecesJointesComplementaires = piecesJointesComplementaires;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FactureFacturX factureFacturX = (FactureFacturX) o;
    return Objects.equals(this.numeroFacture, factureFacturX.numeroFacture) &&
        Objects.equals(this.dateEcheancePaiement, factureFacturX.dateEcheancePaiement) &&
        Objects.equals(this.dateFacture, factureFacturX.dateFacture) &&
        Objects.equals(this.modeDepot, factureFacturX.modeDepot) &&
        Objects.equals(this.destinataire, factureFacturX.destinataire) &&
        Objects.equals(this.fournisseur, factureFacturX.fournisseur) &&
        Objects.equals(this.cadreDeFacturation, factureFacturX.cadreDeFacturation) &&
        Objects.equals(this.references, factureFacturX.references) &&
        Objects.equals(this.montantTotal, factureFacturX.montantTotal) &&
        Objects.equals(this.lignesDePoste, factureFacturX.lignesDePoste) &&
        Objects.equals(this.lignesDeTva, factureFacturX.lignesDeTva) &&
        Objects.equals(this.notes, factureFacturX.notes) &&
        Objects.equals(this.commentaire, factureFacturX.commentaire) &&
        Objects.equals(this.idUtilisateurCourant, factureFacturX.idUtilisateurCourant) &&
        Objects.equals(this.piecesJointesComplementaires, factureFacturX.piecesJointesComplementaires);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(numeroFacture, dateEcheancePaiement, dateFacture, modeDepot, destinataire, fournisseur, cadreDeFacturation, references, montantTotal, lignesDePoste, lignesDeTva, notes, commentaire, idUtilisateurCourant, piecesJointesComplementaires);
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
    sb.append("class FactureFacturX {\n");
    sb.append("    numeroFacture: ").append(toIndentedString(numeroFacture)).append("\n");
    sb.append("    dateEcheancePaiement: ").append(toIndentedString(dateEcheancePaiement)).append("\n");
    sb.append("    dateFacture: ").append(toIndentedString(dateFacture)).append("\n");
    sb.append("    modeDepot: ").append(toIndentedString(modeDepot)).append("\n");
    sb.append("    destinataire: ").append(toIndentedString(destinataire)).append("\n");
    sb.append("    fournisseur: ").append(toIndentedString(fournisseur)).append("\n");
    sb.append("    cadreDeFacturation: ").append(toIndentedString(cadreDeFacturation)).append("\n");
    sb.append("    references: ").append(toIndentedString(references)).append("\n");
    sb.append("    montantTotal: ").append(toIndentedString(montantTotal)).append("\n");
    sb.append("    lignesDePoste: ").append(toIndentedString(lignesDePoste)).append("\n");
    sb.append("    lignesDeTva: ").append(toIndentedString(lignesDeTva)).append("\n");
    sb.append("    notes: ").append(toIndentedString(notes)).append("\n");
    sb.append("    commentaire: ").append(toIndentedString(commentaire)).append("\n");
    sb.append("    idUtilisateurCourant: ").append(toIndentedString(idUtilisateurCourant)).append("\n");
    sb.append("    piecesJointesComplementaires: ").append(toIndentedString(piecesJointesComplementaires)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("numeroFacture", "dateEcheancePaiement", "dateFacture", "modeDepot", "destinataire", "fournisseur", "cadreDeFacturation", "references", "montantTotal", "lignesDePoste", "lignesDeTva", "notes", "commentaire", "idUtilisateurCourant", "piecesJointesComplementaires"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(Arrays.asList("numeroFacture", "dateEcheancePaiement", "modeDepot", "destinataire", "fournisseur", "cadreDeFacturation", "references", "montantTotal"));
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to FactureFacturX
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!FactureFacturX.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field(s) %s in FactureFacturX is not found in the empty JSON string", FactureFacturX.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!FactureFacturX.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The field `%s` in the JSON string is not defined in the `FactureFacturX` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : FactureFacturX.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (!jsonObj.get("numeroFacture").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `numeroFacture` to be a primitive type in the JSON string but got `%s`", jsonObj.get("numeroFacture").toString()));
      }
      if (!jsonObj.get("dateEcheancePaiement").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `dateEcheancePaiement` to be a primitive type in the JSON string but got `%s`", jsonObj.get("dateEcheancePaiement").toString()));
      }
      if ((jsonObj.get("dateFacture") != null && !jsonObj.get("dateFacture").isJsonNull()) && !jsonObj.get("dateFacture").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `dateFacture` to be a primitive type in the JSON string but got `%s`", jsonObj.get("dateFacture").toString()));
      }
      // validate the required field `modeDepot`
      ModeDepot.validateJsonElement(jsonObj.get("modeDepot"));
      // validate the required field `destinataire`
      Destinataire.validateJsonElement(jsonObj.get("destinataire"));
      // validate the required field `fournisseur`
      Fournisseur.validateJsonElement(jsonObj.get("fournisseur"));
      // validate the required field `cadreDeFacturation`
      CadreDeFacturation.validateJsonElement(jsonObj.get("cadreDeFacturation"));
      // validate the required field `references`
      References.validateJsonElement(jsonObj.get("references"));
      // validate the required field `montantTotal`
      MontantTotal.validateJsonElement(jsonObj.get("montantTotal"));
      if (jsonObj.get("lignesDePoste") != null && !jsonObj.get("lignesDePoste").isJsonNull()) {
        JsonArray jsonArraylignesDePoste = jsonObj.getAsJsonArray("lignesDePoste");
        if (jsonArraylignesDePoste != null) {
          // ensure the json data is an array
          if (!jsonObj.get("lignesDePoste").isJsonArray()) {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `lignesDePoste` to be an array in the JSON string but got `%s`", jsonObj.get("lignesDePoste").toString()));
          }

          // validate the optional field `lignesDePoste` (array)
          for (int i = 0; i < jsonArraylignesDePoste.size(); i++) {
            LigneDePoste.validateJsonElement(jsonArraylignesDePoste.get(i));
          };
        }
      }
      if (jsonObj.get("lignesDeTva") != null && !jsonObj.get("lignesDeTva").isJsonNull()) {
        JsonArray jsonArraylignesDeTva = jsonObj.getAsJsonArray("lignesDeTva");
        if (jsonArraylignesDeTva != null) {
          // ensure the json data is an array
          if (!jsonObj.get("lignesDeTva").isJsonArray()) {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `lignesDeTva` to be an array in the JSON string but got `%s`", jsonObj.get("lignesDeTva").toString()));
          }

          // validate the optional field `lignesDeTva` (array)
          for (int i = 0; i < jsonArraylignesDeTva.size(); i++) {
            LigneDeTVA.validateJsonElement(jsonArraylignesDeTva.get(i));
          };
        }
      }
      if (jsonObj.get("notes") != null && !jsonObj.get("notes").isJsonNull()) {
        JsonArray jsonArraynotes = jsonObj.getAsJsonArray("notes");
        if (jsonArraynotes != null) {
          // ensure the json data is an array
          if (!jsonObj.get("notes").isJsonArray()) {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `notes` to be an array in the JSON string but got `%s`", jsonObj.get("notes").toString()));
          }

          // validate the optional field `notes` (array)
          for (int i = 0; i < jsonArraynotes.size(); i++) {
            Note.validateJsonElement(jsonArraynotes.get(i));
          };
        }
      }
      if ((jsonObj.get("commentaire") != null && !jsonObj.get("commentaire").isJsonNull()) && !jsonObj.get("commentaire").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `commentaire` to be a primitive type in the JSON string but got `%s`", jsonObj.get("commentaire").toString()));
      }
      if (jsonObj.get("piecesJointesComplementaires") != null && !jsonObj.get("piecesJointesComplementaires").isJsonNull()) {
        JsonArray jsonArraypiecesJointesComplementaires = jsonObj.getAsJsonArray("piecesJointesComplementaires");
        if (jsonArraypiecesJointesComplementaires != null) {
          // ensure the json data is an array
          if (!jsonObj.get("piecesJointesComplementaires").isJsonArray()) {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `piecesJointesComplementaires` to be an array in the JSON string but got `%s`", jsonObj.get("piecesJointesComplementaires").toString()));
          }

          // validate the optional field `piecesJointesComplementaires` (array)
          for (int i = 0; i < jsonArraypiecesJointesComplementaires.size(); i++) {
            PieceJointeComplementaire.validateJsonElement(jsonArraypiecesJointesComplementaires.get(i));
          };
        }
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!FactureFacturX.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'FactureFacturX' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<FactureFacturX> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(FactureFacturX.class));

       return (TypeAdapter<T>) new TypeAdapter<FactureFacturX>() {
           @Override
           public void write(JsonWriter out, FactureFacturX value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public FactureFacturX read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of FactureFacturX given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of FactureFacturX
   * @throws IOException if the JSON string is invalid with respect to FactureFacturX
   */
  public static FactureFacturX fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, FactureFacturX.class);
  }

  /**
   * Convert an instance of FactureFacturX to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

