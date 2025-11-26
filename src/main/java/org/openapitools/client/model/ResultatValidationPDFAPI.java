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
import org.openapitools.client.model.InformationSignatureAPI;
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
 * Résultat complet de la validation d&#39;un PDF Factur-X.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-11-26T07:35:36.724862333Z[Etc/UTC]", comments = "Generator version: 7.18.0-SNAPSHOT")
public class ResultatValidationPDFAPI {
  public static final String SERIALIZED_NAME_EST_CONFORME = "est_conforme";
  @SerializedName(SERIALIZED_NAME_EST_CONFORME)
  @javax.annotation.Nonnull
  private Boolean estConforme;

  public static final String SERIALIZED_NAME_XML_PRESENT = "xml_present";
  @SerializedName(SERIALIZED_NAME_XML_PRESENT)
  @javax.annotation.Nonnull
  private Boolean xmlPresent;

  public static final String SERIALIZED_NAME_XML_CONFORME = "xml_conforme";
  @SerializedName(SERIALIZED_NAME_XML_CONFORME)
  @javax.annotation.Nonnull
  private Boolean xmlConforme;

  public static final String SERIALIZED_NAME_PROFIL_DETECTE = "profil_detecte";
  @SerializedName(SERIALIZED_NAME_PROFIL_DETECTE)
  @javax.annotation.Nullable
  private String profilDetecte;

  public static final String SERIALIZED_NAME_ERREURS_XML = "erreurs_xml";
  @SerializedName(SERIALIZED_NAME_ERREURS_XML)
  @javax.annotation.Nullable
  private List<String> erreursXml = new ArrayList<>();

  public static final String SERIALIZED_NAME_PDFA_CONFORME = "pdfa_conforme";
  @SerializedName(SERIALIZED_NAME_PDFA_CONFORME)
  @javax.annotation.Nonnull
  private Boolean pdfaConforme;

  public static final String SERIALIZED_NAME_VERSION_PDFA = "version_pdfa";
  @SerializedName(SERIALIZED_NAME_VERSION_PDFA)
  @javax.annotation.Nullable
  private String versionPdfa;

  public static final String SERIALIZED_NAME_METHODE_VALIDATION_PDFA = "methode_validation_pdfa";
  @SerializedName(SERIALIZED_NAME_METHODE_VALIDATION_PDFA)
  @javax.annotation.Nullable
  private String methodeValidationPdfa = "metadata";

  public static final String SERIALIZED_NAME_REGLES_VALIDEES = "regles_validees";
  @SerializedName(SERIALIZED_NAME_REGLES_VALIDEES)
  @javax.annotation.Nullable
  private Integer reglesValidees;

  public static final String SERIALIZED_NAME_REGLES_ECHOUEES = "regles_echouees";
  @SerializedName(SERIALIZED_NAME_REGLES_ECHOUEES)
  @javax.annotation.Nullable
  private Integer reglesEchouees;

  public static final String SERIALIZED_NAME_ERREURS_PDFA = "erreurs_pdfa";
  @SerializedName(SERIALIZED_NAME_ERREURS_PDFA)
  @javax.annotation.Nullable
  private List<String> erreursPdfa = new ArrayList<>();

  public static final String SERIALIZED_NAME_AVERTISSEMENTS_PDFA = "avertissements_pdfa";
  @SerializedName(SERIALIZED_NAME_AVERTISSEMENTS_PDFA)
  @javax.annotation.Nullable
  private List<String> avertissementsPdfa = new ArrayList<>();

  public static final String SERIALIZED_NAME_XMP_PRESENT = "xmp_present";
  @SerializedName(SERIALIZED_NAME_XMP_PRESENT)
  @javax.annotation.Nonnull
  private Boolean xmpPresent;

  public static final String SERIALIZED_NAME_XMP_CONFORME_FACTURX = "xmp_conforme_facturx";
  @SerializedName(SERIALIZED_NAME_XMP_CONFORME_FACTURX)
  @javax.annotation.Nonnull
  private Boolean xmpConformeFacturx;

  public static final String SERIALIZED_NAME_PROFIL_XMP = "profil_xmp";
  @SerializedName(SERIALIZED_NAME_PROFIL_XMP)
  @javax.annotation.Nullable
  private String profilXmp;

  public static final String SERIALIZED_NAME_VERSION_XMP = "version_xmp";
  @SerializedName(SERIALIZED_NAME_VERSION_XMP)
  @javax.annotation.Nullable
  private String versionXmp;

  public static final String SERIALIZED_NAME_ERREURS_XMP = "erreurs_xmp";
  @SerializedName(SERIALIZED_NAME_ERREURS_XMP)
  @javax.annotation.Nullable
  private List<String> erreursXmp = new ArrayList<>();

  public static final String SERIALIZED_NAME_METADONNEES_XMP = "metadonnees_xmp";
  @SerializedName(SERIALIZED_NAME_METADONNEES_XMP)
  @javax.annotation.Nullable
  private Map<String, Object> metadonneesXmp = new HashMap<>();

  public static final String SERIALIZED_NAME_EST_SIGNE = "est_signe";
  @SerializedName(SERIALIZED_NAME_EST_SIGNE)
  @javax.annotation.Nonnull
  private Boolean estSigne;

  public static final String SERIALIZED_NAME_NOMBRE_SIGNATURES = "nombre_signatures";
  @SerializedName(SERIALIZED_NAME_NOMBRE_SIGNATURES)
  @javax.annotation.Nullable
  private Integer nombreSignatures = 0;

  public static final String SERIALIZED_NAME_SIGNATURES = "signatures";
  @SerializedName(SERIALIZED_NAME_SIGNATURES)
  @javax.annotation.Nullable
  private List<InformationSignatureAPI> signatures = new ArrayList<>();

  public static final String SERIALIZED_NAME_ERREURS_SIGNATURES = "erreurs_signatures";
  @SerializedName(SERIALIZED_NAME_ERREURS_SIGNATURES)
  @javax.annotation.Nullable
  private List<String> erreursSignatures = new ArrayList<>();

  public static final String SERIALIZED_NAME_MESSAGE_RESUME = "message_resume";
  @SerializedName(SERIALIZED_NAME_MESSAGE_RESUME)
  @javax.annotation.Nonnull
  private String messageResume;

  public ResultatValidationPDFAPI() {
  }

  public ResultatValidationPDFAPI estConforme(@javax.annotation.Nonnull Boolean estConforme) {
    this.estConforme = estConforme;
    return this;
  }

  /**
   * True si le PDF est conforme à tous les critères (XML, PDF/A, XMP)
   * @return estConforme
   */
  @javax.annotation.Nonnull
  public Boolean getEstConforme() {
    return estConforme;
  }

  public void setEstConforme(@javax.annotation.Nonnull Boolean estConforme) {
    this.estConforme = estConforme;
  }


  public ResultatValidationPDFAPI xmlPresent(@javax.annotation.Nonnull Boolean xmlPresent) {
    this.xmlPresent = xmlPresent;
    return this;
  }

  /**
   * True si un XML Factur-X est embarqué dans le PDF
   * @return xmlPresent
   */
  @javax.annotation.Nonnull
  public Boolean getXmlPresent() {
    return xmlPresent;
  }

  public void setXmlPresent(@javax.annotation.Nonnull Boolean xmlPresent) {
    this.xmlPresent = xmlPresent;
  }


  public ResultatValidationPDFAPI xmlConforme(@javax.annotation.Nonnull Boolean xmlConforme) {
    this.xmlConforme = xmlConforme;
    return this;
  }

  /**
   * True si le XML Factur-X est conforme aux règles Schematron
   * @return xmlConforme
   */
  @javax.annotation.Nonnull
  public Boolean getXmlConforme() {
    return xmlConforme;
  }

  public void setXmlConforme(@javax.annotation.Nonnull Boolean xmlConforme) {
    this.xmlConforme = xmlConforme;
  }


  public ResultatValidationPDFAPI profilDetecte(@javax.annotation.Nullable String profilDetecte) {
    this.profilDetecte = profilDetecte;
    return this;
  }

  /**
   * Get profilDetecte
   * @return profilDetecte
   */
  @javax.annotation.Nullable
  public String getProfilDetecte() {
    return profilDetecte;
  }

  public void setProfilDetecte(@javax.annotation.Nullable String profilDetecte) {
    this.profilDetecte = profilDetecte;
  }


  public ResultatValidationPDFAPI erreursXml(@javax.annotation.Nullable List<String> erreursXml) {
    this.erreursXml = erreursXml;
    return this;
  }

  public ResultatValidationPDFAPI addErreursXmlItem(String erreursXmlItem) {
    if (this.erreursXml == null) {
      this.erreursXml = new ArrayList<>();
    }
    this.erreursXml.add(erreursXmlItem);
    return this;
  }

  /**
   * Liste des erreurs de validation XML
   * @return erreursXml
   */
  @javax.annotation.Nullable
  public List<String> getErreursXml() {
    return erreursXml;
  }

  public void setErreursXml(@javax.annotation.Nullable List<String> erreursXml) {
    this.erreursXml = erreursXml;
  }


  public ResultatValidationPDFAPI pdfaConforme(@javax.annotation.Nonnull Boolean pdfaConforme) {
    this.pdfaConforme = pdfaConforme;
    return this;
  }

  /**
   * True si le PDF est conforme PDF/A
   * @return pdfaConforme
   */
  @javax.annotation.Nonnull
  public Boolean getPdfaConforme() {
    return pdfaConforme;
  }

  public void setPdfaConforme(@javax.annotation.Nonnull Boolean pdfaConforme) {
    this.pdfaConforme = pdfaConforme;
  }


  public ResultatValidationPDFAPI versionPdfa(@javax.annotation.Nullable String versionPdfa) {
    this.versionPdfa = versionPdfa;
    return this;
  }

  /**
   * Get versionPdfa
   * @return versionPdfa
   */
  @javax.annotation.Nullable
  public String getVersionPdfa() {
    return versionPdfa;
  }

  public void setVersionPdfa(@javax.annotation.Nullable String versionPdfa) {
    this.versionPdfa = versionPdfa;
  }


  public ResultatValidationPDFAPI methodeValidationPdfa(@javax.annotation.Nullable String methodeValidationPdfa) {
    this.methodeValidationPdfa = methodeValidationPdfa;
    return this;
  }

  /**
   * Méthode utilisée pour la validation PDF/A (metadata ou verapdf)
   * @return methodeValidationPdfa
   */
  @javax.annotation.Nullable
  public String getMethodeValidationPdfa() {
    return methodeValidationPdfa;
  }

  public void setMethodeValidationPdfa(@javax.annotation.Nullable String methodeValidationPdfa) {
    this.methodeValidationPdfa = methodeValidationPdfa;
  }


  public ResultatValidationPDFAPI reglesValidees(@javax.annotation.Nullable Integer reglesValidees) {
    this.reglesValidees = reglesValidees;
    return this;
  }

  /**
   * Get reglesValidees
   * @return reglesValidees
   */
  @javax.annotation.Nullable
  public Integer getReglesValidees() {
    return reglesValidees;
  }

  public void setReglesValidees(@javax.annotation.Nullable Integer reglesValidees) {
    this.reglesValidees = reglesValidees;
  }


  public ResultatValidationPDFAPI reglesEchouees(@javax.annotation.Nullable Integer reglesEchouees) {
    this.reglesEchouees = reglesEchouees;
    return this;
  }

  /**
   * Get reglesEchouees
   * @return reglesEchouees
   */
  @javax.annotation.Nullable
  public Integer getReglesEchouees() {
    return reglesEchouees;
  }

  public void setReglesEchouees(@javax.annotation.Nullable Integer reglesEchouees) {
    this.reglesEchouees = reglesEchouees;
  }


  public ResultatValidationPDFAPI erreursPdfa(@javax.annotation.Nullable List<String> erreursPdfa) {
    this.erreursPdfa = erreursPdfa;
    return this;
  }

  public ResultatValidationPDFAPI addErreursPdfaItem(String erreursPdfaItem) {
    if (this.erreursPdfa == null) {
      this.erreursPdfa = new ArrayList<>();
    }
    this.erreursPdfa.add(erreursPdfaItem);
    return this;
  }

  /**
   * Liste des erreurs de conformité PDF/A
   * @return erreursPdfa
   */
  @javax.annotation.Nullable
  public List<String> getErreursPdfa() {
    return erreursPdfa;
  }

  public void setErreursPdfa(@javax.annotation.Nullable List<String> erreursPdfa) {
    this.erreursPdfa = erreursPdfa;
  }


  public ResultatValidationPDFAPI avertissementsPdfa(@javax.annotation.Nullable List<String> avertissementsPdfa) {
    this.avertissementsPdfa = avertissementsPdfa;
    return this;
  }

  public ResultatValidationPDFAPI addAvertissementsPdfaItem(String avertissementsPdfaItem) {
    if (this.avertissementsPdfa == null) {
      this.avertissementsPdfa = new ArrayList<>();
    }
    this.avertissementsPdfa.add(avertissementsPdfaItem);
    return this;
  }

  /**
   * Liste des avertissements PDF/A
   * @return avertissementsPdfa
   */
  @javax.annotation.Nullable
  public List<String> getAvertissementsPdfa() {
    return avertissementsPdfa;
  }

  public void setAvertissementsPdfa(@javax.annotation.Nullable List<String> avertissementsPdfa) {
    this.avertissementsPdfa = avertissementsPdfa;
  }


  public ResultatValidationPDFAPI xmpPresent(@javax.annotation.Nonnull Boolean xmpPresent) {
    this.xmpPresent = xmpPresent;
    return this;
  }

  /**
   * True si des métadonnées XMP sont présentes
   * @return xmpPresent
   */
  @javax.annotation.Nonnull
  public Boolean getXmpPresent() {
    return xmpPresent;
  }

  public void setXmpPresent(@javax.annotation.Nonnull Boolean xmpPresent) {
    this.xmpPresent = xmpPresent;
  }


  public ResultatValidationPDFAPI xmpConformeFacturx(@javax.annotation.Nonnull Boolean xmpConformeFacturx) {
    this.xmpConformeFacturx = xmpConformeFacturx;
    return this;
  }

  /**
   * True si les métadonnées XMP contiennent des informations Factur-X
   * @return xmpConformeFacturx
   */
  @javax.annotation.Nonnull
  public Boolean getXmpConformeFacturx() {
    return xmpConformeFacturx;
  }

  public void setXmpConformeFacturx(@javax.annotation.Nonnull Boolean xmpConformeFacturx) {
    this.xmpConformeFacturx = xmpConformeFacturx;
  }


  public ResultatValidationPDFAPI profilXmp(@javax.annotation.Nullable String profilXmp) {
    this.profilXmp = profilXmp;
    return this;
  }

  /**
   * Get profilXmp
   * @return profilXmp
   */
  @javax.annotation.Nullable
  public String getProfilXmp() {
    return profilXmp;
  }

  public void setProfilXmp(@javax.annotation.Nullable String profilXmp) {
    this.profilXmp = profilXmp;
  }


  public ResultatValidationPDFAPI versionXmp(@javax.annotation.Nullable String versionXmp) {
    this.versionXmp = versionXmp;
    return this;
  }

  /**
   * Get versionXmp
   * @return versionXmp
   */
  @javax.annotation.Nullable
  public String getVersionXmp() {
    return versionXmp;
  }

  public void setVersionXmp(@javax.annotation.Nullable String versionXmp) {
    this.versionXmp = versionXmp;
  }


  public ResultatValidationPDFAPI erreursXmp(@javax.annotation.Nullable List<String> erreursXmp) {
    this.erreursXmp = erreursXmp;
    return this;
  }

  public ResultatValidationPDFAPI addErreursXmpItem(String erreursXmpItem) {
    if (this.erreursXmp == null) {
      this.erreursXmp = new ArrayList<>();
    }
    this.erreursXmp.add(erreursXmpItem);
    return this;
  }

  /**
   * Liste des erreurs de métadonnées XMP
   * @return erreursXmp
   */
  @javax.annotation.Nullable
  public List<String> getErreursXmp() {
    return erreursXmp;
  }

  public void setErreursXmp(@javax.annotation.Nullable List<String> erreursXmp) {
    this.erreursXmp = erreursXmp;
  }


  public ResultatValidationPDFAPI metadonneesXmp(@javax.annotation.Nullable Map<String, Object> metadonneesXmp) {
    this.metadonneesXmp = metadonneesXmp;
    return this;
  }

  public ResultatValidationPDFAPI putMetadonneesXmpItem(String key, Object metadonneesXmpItem) {
    if (this.metadonneesXmp == null) {
      this.metadonneesXmp = new HashMap<>();
    }
    this.metadonneesXmp.put(key, metadonneesXmpItem);
    return this;
  }

  /**
   * Métadonnées XMP extraites du PDF
   * @return metadonneesXmp
   */
  @javax.annotation.Nullable
  public Map<String, Object> getMetadonneesXmp() {
    return metadonneesXmp;
  }

  public void setMetadonneesXmp(@javax.annotation.Nullable Map<String, Object> metadonneesXmp) {
    this.metadonneesXmp = metadonneesXmp;
  }


  public ResultatValidationPDFAPI estSigne(@javax.annotation.Nonnull Boolean estSigne) {
    this.estSigne = estSigne;
    return this;
  }

  /**
   * True si le PDF contient au moins une signature
   * @return estSigne
   */
  @javax.annotation.Nonnull
  public Boolean getEstSigne() {
    return estSigne;
  }

  public void setEstSigne(@javax.annotation.Nonnull Boolean estSigne) {
    this.estSigne = estSigne;
  }


  public ResultatValidationPDFAPI nombreSignatures(@javax.annotation.Nullable Integer nombreSignatures) {
    this.nombreSignatures = nombreSignatures;
    return this;
  }

  /**
   * Nombre de signatures électroniques trouvées
   * @return nombreSignatures
   */
  @javax.annotation.Nullable
  public Integer getNombreSignatures() {
    return nombreSignatures;
  }

  public void setNombreSignatures(@javax.annotation.Nullable Integer nombreSignatures) {
    this.nombreSignatures = nombreSignatures;
  }


  public ResultatValidationPDFAPI signatures(@javax.annotation.Nullable List<InformationSignatureAPI> signatures) {
    this.signatures = signatures;
    return this;
  }

  public ResultatValidationPDFAPI addSignaturesItem(InformationSignatureAPI signaturesItem) {
    if (this.signatures == null) {
      this.signatures = new ArrayList<>();
    }
    this.signatures.add(signaturesItem);
    return this;
  }

  /**
   * Liste des signatures trouvées avec leurs informations
   * @return signatures
   */
  @javax.annotation.Nullable
  public List<InformationSignatureAPI> getSignatures() {
    return signatures;
  }

  public void setSignatures(@javax.annotation.Nullable List<InformationSignatureAPI> signatures) {
    this.signatures = signatures;
  }


  public ResultatValidationPDFAPI erreursSignatures(@javax.annotation.Nullable List<String> erreursSignatures) {
    this.erreursSignatures = erreursSignatures;
    return this;
  }

  public ResultatValidationPDFAPI addErreursSignaturesItem(String erreursSignaturesItem) {
    if (this.erreursSignatures == null) {
      this.erreursSignatures = new ArrayList<>();
    }
    this.erreursSignatures.add(erreursSignaturesItem);
    return this;
  }

  /**
   * Liste des erreurs lors de l&#39;analyse des signatures
   * @return erreursSignatures
   */
  @javax.annotation.Nullable
  public List<String> getErreursSignatures() {
    return erreursSignatures;
  }

  public void setErreursSignatures(@javax.annotation.Nullable List<String> erreursSignatures) {
    this.erreursSignatures = erreursSignatures;
  }


  public ResultatValidationPDFAPI messageResume(@javax.annotation.Nonnull String messageResume) {
    this.messageResume = messageResume;
    return this;
  }

  /**
   * Message résumant le résultat de la validation
   * @return messageResume
   */
  @javax.annotation.Nonnull
  public String getMessageResume() {
    return messageResume;
  }

  public void setMessageResume(@javax.annotation.Nonnull String messageResume) {
    this.messageResume = messageResume;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResultatValidationPDFAPI resultatValidationPDFAPI = (ResultatValidationPDFAPI) o;
    return Objects.equals(this.estConforme, resultatValidationPDFAPI.estConforme) &&
        Objects.equals(this.xmlPresent, resultatValidationPDFAPI.xmlPresent) &&
        Objects.equals(this.xmlConforme, resultatValidationPDFAPI.xmlConforme) &&
        Objects.equals(this.profilDetecte, resultatValidationPDFAPI.profilDetecte) &&
        Objects.equals(this.erreursXml, resultatValidationPDFAPI.erreursXml) &&
        Objects.equals(this.pdfaConforme, resultatValidationPDFAPI.pdfaConforme) &&
        Objects.equals(this.versionPdfa, resultatValidationPDFAPI.versionPdfa) &&
        Objects.equals(this.methodeValidationPdfa, resultatValidationPDFAPI.methodeValidationPdfa) &&
        Objects.equals(this.reglesValidees, resultatValidationPDFAPI.reglesValidees) &&
        Objects.equals(this.reglesEchouees, resultatValidationPDFAPI.reglesEchouees) &&
        Objects.equals(this.erreursPdfa, resultatValidationPDFAPI.erreursPdfa) &&
        Objects.equals(this.avertissementsPdfa, resultatValidationPDFAPI.avertissementsPdfa) &&
        Objects.equals(this.xmpPresent, resultatValidationPDFAPI.xmpPresent) &&
        Objects.equals(this.xmpConformeFacturx, resultatValidationPDFAPI.xmpConformeFacturx) &&
        Objects.equals(this.profilXmp, resultatValidationPDFAPI.profilXmp) &&
        Objects.equals(this.versionXmp, resultatValidationPDFAPI.versionXmp) &&
        Objects.equals(this.erreursXmp, resultatValidationPDFAPI.erreursXmp) &&
        Objects.equals(this.metadonneesXmp, resultatValidationPDFAPI.metadonneesXmp) &&
        Objects.equals(this.estSigne, resultatValidationPDFAPI.estSigne) &&
        Objects.equals(this.nombreSignatures, resultatValidationPDFAPI.nombreSignatures) &&
        Objects.equals(this.signatures, resultatValidationPDFAPI.signatures) &&
        Objects.equals(this.erreursSignatures, resultatValidationPDFAPI.erreursSignatures) &&
        Objects.equals(this.messageResume, resultatValidationPDFAPI.messageResume);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(estConforme, xmlPresent, xmlConforme, profilDetecte, erreursXml, pdfaConforme, versionPdfa, methodeValidationPdfa, reglesValidees, reglesEchouees, erreursPdfa, avertissementsPdfa, xmpPresent, xmpConformeFacturx, profilXmp, versionXmp, erreursXmp, metadonneesXmp, estSigne, nombreSignatures, signatures, erreursSignatures, messageResume);
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
    sb.append("class ResultatValidationPDFAPI {\n");
    sb.append("    estConforme: ").append(toIndentedString(estConforme)).append("\n");
    sb.append("    xmlPresent: ").append(toIndentedString(xmlPresent)).append("\n");
    sb.append("    xmlConforme: ").append(toIndentedString(xmlConforme)).append("\n");
    sb.append("    profilDetecte: ").append(toIndentedString(profilDetecte)).append("\n");
    sb.append("    erreursXml: ").append(toIndentedString(erreursXml)).append("\n");
    sb.append("    pdfaConforme: ").append(toIndentedString(pdfaConforme)).append("\n");
    sb.append("    versionPdfa: ").append(toIndentedString(versionPdfa)).append("\n");
    sb.append("    methodeValidationPdfa: ").append(toIndentedString(methodeValidationPdfa)).append("\n");
    sb.append("    reglesValidees: ").append(toIndentedString(reglesValidees)).append("\n");
    sb.append("    reglesEchouees: ").append(toIndentedString(reglesEchouees)).append("\n");
    sb.append("    erreursPdfa: ").append(toIndentedString(erreursPdfa)).append("\n");
    sb.append("    avertissementsPdfa: ").append(toIndentedString(avertissementsPdfa)).append("\n");
    sb.append("    xmpPresent: ").append(toIndentedString(xmpPresent)).append("\n");
    sb.append("    xmpConformeFacturx: ").append(toIndentedString(xmpConformeFacturx)).append("\n");
    sb.append("    profilXmp: ").append(toIndentedString(profilXmp)).append("\n");
    sb.append("    versionXmp: ").append(toIndentedString(versionXmp)).append("\n");
    sb.append("    erreursXmp: ").append(toIndentedString(erreursXmp)).append("\n");
    sb.append("    metadonneesXmp: ").append(toIndentedString(metadonneesXmp)).append("\n");
    sb.append("    estSigne: ").append(toIndentedString(estSigne)).append("\n");
    sb.append("    nombreSignatures: ").append(toIndentedString(nombreSignatures)).append("\n");
    sb.append("    signatures: ").append(toIndentedString(signatures)).append("\n");
    sb.append("    erreursSignatures: ").append(toIndentedString(erreursSignatures)).append("\n");
    sb.append("    messageResume: ").append(toIndentedString(messageResume)).append("\n");
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
    openapiFields = new HashSet<String>(Arrays.asList("est_conforme", "xml_present", "xml_conforme", "profil_detecte", "erreurs_xml", "pdfa_conforme", "version_pdfa", "methode_validation_pdfa", "regles_validees", "regles_echouees", "erreurs_pdfa", "avertissements_pdfa", "xmp_present", "xmp_conforme_facturx", "profil_xmp", "version_xmp", "erreurs_xmp", "metadonnees_xmp", "est_signe", "nombre_signatures", "signatures", "erreurs_signatures", "message_resume"));

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>(Arrays.asList("est_conforme", "xml_present", "xml_conforme", "pdfa_conforme", "xmp_present", "xmp_conforme_facturx", "est_signe", "message_resume"));
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to ResultatValidationPDFAPI
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ResultatValidationPDFAPI.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field(s) %s in ResultatValidationPDFAPI is not found in the empty JSON string", ResultatValidationPDFAPI.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!ResultatValidationPDFAPI.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The field `%s` in the JSON string is not defined in the `ResultatValidationPDFAPI` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : ResultatValidationPDFAPI.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format(Locale.ROOT, "The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("profil_detecte") != null && !jsonObj.get("profil_detecte").isJsonNull()) && !jsonObj.get("profil_detecte").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `profil_detecte` to be a primitive type in the JSON string but got `%s`", jsonObj.get("profil_detecte").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("erreurs_xml") != null && !jsonObj.get("erreurs_xml").isJsonNull() && !jsonObj.get("erreurs_xml").isJsonArray()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `erreurs_xml` to be an array in the JSON string but got `%s`", jsonObj.get("erreurs_xml").toString()));
      }
      if ((jsonObj.get("version_pdfa") != null && !jsonObj.get("version_pdfa").isJsonNull()) && !jsonObj.get("version_pdfa").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `version_pdfa` to be a primitive type in the JSON string but got `%s`", jsonObj.get("version_pdfa").toString()));
      }
      if ((jsonObj.get("methode_validation_pdfa") != null && !jsonObj.get("methode_validation_pdfa").isJsonNull()) && !jsonObj.get("methode_validation_pdfa").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `methode_validation_pdfa` to be a primitive type in the JSON string but got `%s`", jsonObj.get("methode_validation_pdfa").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("erreurs_pdfa") != null && !jsonObj.get("erreurs_pdfa").isJsonNull() && !jsonObj.get("erreurs_pdfa").isJsonArray()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `erreurs_pdfa` to be an array in the JSON string but got `%s`", jsonObj.get("erreurs_pdfa").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("avertissements_pdfa") != null && !jsonObj.get("avertissements_pdfa").isJsonNull() && !jsonObj.get("avertissements_pdfa").isJsonArray()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `avertissements_pdfa` to be an array in the JSON string but got `%s`", jsonObj.get("avertissements_pdfa").toString()));
      }
      if ((jsonObj.get("profil_xmp") != null && !jsonObj.get("profil_xmp").isJsonNull()) && !jsonObj.get("profil_xmp").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `profil_xmp` to be a primitive type in the JSON string but got `%s`", jsonObj.get("profil_xmp").toString()));
      }
      if ((jsonObj.get("version_xmp") != null && !jsonObj.get("version_xmp").isJsonNull()) && !jsonObj.get("version_xmp").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `version_xmp` to be a primitive type in the JSON string but got `%s`", jsonObj.get("version_xmp").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("erreurs_xmp") != null && !jsonObj.get("erreurs_xmp").isJsonNull() && !jsonObj.get("erreurs_xmp").isJsonArray()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `erreurs_xmp` to be an array in the JSON string but got `%s`", jsonObj.get("erreurs_xmp").toString()));
      }
      if (jsonObj.get("signatures") != null && !jsonObj.get("signatures").isJsonNull()) {
        JsonArray jsonArraysignatures = jsonObj.getAsJsonArray("signatures");
        if (jsonArraysignatures != null) {
          // ensure the json data is an array
          if (!jsonObj.get("signatures").isJsonArray()) {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `signatures` to be an array in the JSON string but got `%s`", jsonObj.get("signatures").toString()));
          }

          // validate the optional field `signatures` (array)
          for (int i = 0; i < jsonArraysignatures.size(); i++) {
            InformationSignatureAPI.validateJsonElement(jsonArraysignatures.get(i));
          };
        }
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("erreurs_signatures") != null && !jsonObj.get("erreurs_signatures").isJsonNull() && !jsonObj.get("erreurs_signatures").isJsonArray()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `erreurs_signatures` to be an array in the JSON string but got `%s`", jsonObj.get("erreurs_signatures").toString()));
      }
      if (!jsonObj.get("message_resume").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format(Locale.ROOT, "Expected the field `message_resume` to be a primitive type in the JSON string but got `%s`", jsonObj.get("message_resume").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ResultatValidationPDFAPI.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ResultatValidationPDFAPI' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ResultatValidationPDFAPI> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ResultatValidationPDFAPI.class));

       return (TypeAdapter<T>) new TypeAdapter<ResultatValidationPDFAPI>() {
           @Override
           public void write(JsonWriter out, ResultatValidationPDFAPI value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public ResultatValidationPDFAPI read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of ResultatValidationPDFAPI given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of ResultatValidationPDFAPI
   * @throws IOException if the JSON string is invalid with respect to ResultatValidationPDFAPI
   */
  public static ResultatValidationPDFAPI fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ResultatValidationPDFAPI.class);
  }

  /**
   * Convert an instance of ResultatValidationPDFAPI to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

