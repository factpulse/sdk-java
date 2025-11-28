package org.openapitools.client.helpers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.*;
import java.util.concurrent.TimeUnit;

// =============================================================================
// Credentials classes
// =============================================================================

class ChorusProCredentials {
    public final String pisteClientId, pisteClientSecret, chorusProLogin, chorusProPassword;
    public final boolean sandbox;
    public ChorusProCredentials(String pisteClientId, String pisteClientSecret, String chorusProLogin, String chorusProPassword, boolean sandbox) {
        this.pisteClientId = pisteClientId; this.pisteClientSecret = pisteClientSecret;
        this.chorusProLogin = chorusProLogin; this.chorusProPassword = chorusProPassword; this.sandbox = sandbox;
    }
    public ChorusProCredentials(String pisteClientId, String pisteClientSecret, String chorusProLogin, String chorusProPassword) {
        this(pisteClientId, pisteClientSecret, chorusProLogin, chorusProPassword, true);
    }
    public Map<String, Object> toMap() {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("piste_client_id", pisteClientId); m.put("piste_client_secret", pisteClientSecret);
        m.put("chorus_pro_login", chorusProLogin); m.put("chorus_pro_password", chorusProPassword); m.put("sandbox", sandbox);
        return m;
    }
}

/** Credentials AFNOR PDP pour le mode Zero-Trust. L'API FactPulse utilise ces credentials pour s'authentifier auprès de la PDP AFNOR. */
class AFNORCredentials {
    public final String flowServiceUrl, tokenUrl, clientId, clientSecret;
    public final String directoryServiceUrl;
    public AFNORCredentials(String flowServiceUrl, String tokenUrl, String clientId, String clientSecret, String directoryServiceUrl) {
        this.flowServiceUrl = flowServiceUrl; this.tokenUrl = tokenUrl;
        this.clientId = clientId; this.clientSecret = clientSecret; this.directoryServiceUrl = directoryServiceUrl;
    }
    public AFNORCredentials(String flowServiceUrl, String tokenUrl, String clientId, String clientSecret) {
        this(flowServiceUrl, tokenUrl, clientId, clientSecret, null);
    }
    public Map<String, Object> toMap() {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("flow_service_url", flowServiceUrl); m.put("token_url", tokenUrl);
        m.put("client_id", clientId); m.put("client_secret", clientSecret);
        if (directoryServiceUrl != null) m.put("directory_service_url", directoryServiceUrl);
        return m;
    }
}

// =============================================================================
// Helpers pour les montants
// =============================================================================

class MontantHelpers {
    public static String montant(Object value) {
        if (value == null) return "0.00";
        if (value instanceof BigDecimal) return ((BigDecimal) value).setScale(2, RoundingMode.HALF_UP).toPlainString();
        if (value instanceof Number) return String.format("%.2f", ((Number) value).doubleValue());
        if (value instanceof String) return (String) value;
        return "0.00";
    }
    public static Map<String, Object> montantTotal(Object ht, Object tva, Object ttc, Object aPayer) {
        return montantTotal(ht, tva, ttc, aPayer, null, null, null);
    }
    public static Map<String, Object> montantTotal(Object ht, Object tva, Object ttc, Object aPayer, Object remiseTtc, String motifRemise, Object acompte) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("montantHtTotal", montant(ht)); result.put("montantTva", montant(tva));
        result.put("montantTtcTotal", montant(ttc)); result.put("montantAPayer", montant(aPayer));
        if (remiseTtc != null) result.put("montantRemiseGlobaleTtc", montant(remiseTtc));
        if (motifRemise != null) result.put("motifRemiseGlobaleTtc", motifRemise);
        if (acompte != null) result.put("acompte", montant(acompte));
        return result;
    }
    /** Crée une ligne de poste (aligné sur LigneDePoste de models.py). */
    public static Map<String, Object> ligneDePoste(int numero, String denomination, Object quantite, Object montantUnitaireHt, Object montantTotalLigneHt) {
        return ligneDePoste(numero, denomination, quantite, montantUnitaireHt, montantTotalLigneHt, "20.00", "S", "FORFAIT", null);
    }
    public static Map<String, Object> ligneDePoste(int numero, String denomination, Object quantite, Object montantUnitaireHt, Object montantTotalLigneHt, String tauxTva, String categorieTva, String unite, Map<String, Object> options) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("numero", numero); result.put("denomination", denomination);
        result.put("quantite", montant(quantite)); result.put("montantUnitaireHt", montant(montantUnitaireHt));
        result.put("montantTotalLigneHt", montant(montantTotalLigneHt)); result.put("tauxTvaManuel", montant(tauxTva));
        result.put("categorieTva", categorieTva); result.put("unite", unite);
        if (options != null) {
            if (options.containsKey("reference")) result.put("reference", options.get("reference"));
            if (options.containsKey("montantRemiseHt")) result.put("montantRemiseHt", montant(options.get("montantRemiseHt")));
            if (options.containsKey("codeRaisonReduction")) result.put("codeRaisonReduction", options.get("codeRaisonReduction"));
            if (options.containsKey("raisonReduction")) result.put("raisonReduction", options.get("raisonReduction"));
            if (options.containsKey("dateDebutPeriode")) result.put("dateDebutPeriode", options.get("dateDebutPeriode"));
            if (options.containsKey("dateFinPeriode")) result.put("dateFinPeriode", options.get("dateFinPeriode"));
        }
        return result;
    }
    /** Crée une ligne de TVA (aligné sur LigneDeTVA de models.py). */
    public static Map<String, Object> ligneDeTva(Object tauxManuel, Object montantBaseHt, Object montantTva) { return ligneDeTva(tauxManuel, montantBaseHt, montantTva, "S"); }
    public static Map<String, Object> ligneDeTva(Object tauxManuel, Object montantBaseHt, Object montantTva, String categorie) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("tauxManuel", montant(tauxManuel)); result.put("montantBaseHt", montant(montantBaseHt));
        result.put("montantTva", montant(montantTva)); result.put("categorie", categorie);
        return result;
    }
    /** Crée une adresse postale pour l'API FactPulse. */
    public static Map<String, Object> adressePostale(String ligne1, String codePostal, String ville) { return adressePostale(ligne1, codePostal, ville, "FR", null, null); }
    public static Map<String, Object> adressePostale(String ligne1, String codePostal, String ville, String pays, String ligne2, String ligne3) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("ligneUn", ligne1); result.put("codePostal", codePostal); result.put("nomVille", ville); result.put("paysCodeIso", pays != null ? pays : "FR");
        if (ligne2 != null) result.put("ligneDeux", ligne2);
        if (ligne3 != null) result.put("ligneTrois", ligne3);
        return result;
    }
    /** Crée une adresse électronique. schemeId: "0009"=SIREN, "0225"=SIRET */
    public static Map<String, Object> adresseElectronique(String identifiant) { return adresseElectronique(identifiant, "0009"); }
    public static Map<String, Object> adresseElectronique(String identifiant, String schemeId) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("identifiant", identifiant); result.put("schemeId", schemeId);
        return result;
    }
    /** Calcule le numéro TVA intracommunautaire français depuis un SIREN. */
    private static String calculerTvaIntra(String siren) {
        if (siren == null || siren.length() != 9 || !siren.matches("\\d+")) return null;
        long cle = (12 + 3 * (Long.parseLong(siren) % 97)) % 97;
        return String.format("FR%02d%s", cle, siren);
    }
    /** Crée un fournisseur (émetteur) avec auto-calcul SIREN, TVA intracommunautaire et adresses. */
    public static Map<String, Object> fournisseur(String nom, String siret, String adresseLigne1, String codePostal, String ville) { return fournisseur(nom, siret, adresseLigne1, codePostal, ville, null); }
    public static Map<String, Object> fournisseur(String nom, String siret, String adresseLigne1, String codePostal, String ville, Map<String, Object> options) {
        if (options == null) options = new LinkedHashMap<>();
        String siren = options.containsKey("siren") ? (String)options.get("siren") : (siret.length() == 14 ? siret.substring(0, 9) : null);
        String numeroTvaIntra = options.containsKey("numeroTvaIntra") ? (String)options.get("numeroTvaIntra") : (siren != null ? calculerTvaIntra(siren) : null);
        String pays = options.containsKey("pays") ? (String)options.get("pays") : "FR";
        String adresseLigne2 = options.containsKey("adresseLigne2") ? (String)options.get("adresseLigne2") : null;
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("nom", nom); result.put("idFournisseur", options.getOrDefault("idFournisseur", 0)); result.put("siret", siret);
        result.put("adresseElectronique", adresseElectronique(siret, "0225"));
        result.put("adressePostale", adressePostale(adresseLigne1, codePostal, ville, pays, adresseLigne2, null));
        if (siren != null) result.put("siren", siren);
        if (numeroTvaIntra != null) result.put("numeroTvaIntra", numeroTvaIntra);
        if (options.containsKey("iban")) result.put("iban", options.get("iban"));
        if (options.containsKey("codeService")) result.put("idServiceFournisseur", options.get("codeService"));
        if (options.containsKey("codeCoordonnesBancaires")) result.put("codeCoordonnesBancairesFournisseur", options.get("codeCoordonnesBancaires"));
        return result;
    }
    /** Crée un destinataire (client) avec auto-calcul SIREN et adresses. */
    public static Map<String, Object> destinataire(String nom, String siret, String adresseLigne1, String codePostal, String ville) { return destinataire(nom, siret, adresseLigne1, codePostal, ville, null); }
    public static Map<String, Object> destinataire(String nom, String siret, String adresseLigne1, String codePostal, String ville, Map<String, Object> options) {
        if (options == null) options = new LinkedHashMap<>();
        String siren = options.containsKey("siren") ? (String)options.get("siren") : (siret.length() == 14 ? siret.substring(0, 9) : null);
        String pays = options.containsKey("pays") ? (String)options.get("pays") : "FR";
        String adresseLigne2 = options.containsKey("adresseLigne2") ? (String)options.get("adresseLigne2") : null;
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("nom", nom); result.put("siret", siret);
        result.put("adresseElectronique", adresseElectronique(siret, "0225"));
        result.put("adressePostale", adressePostale(adresseLigne1, codePostal, ville, pays, adresseLigne2, null));
        if (siren != null) result.put("siren", siren);
        if (options.containsKey("codeServiceExecutant")) result.put("codeServiceExecutant", options.get("codeServiceExecutant"));
        return result;
    }
}

// =============================================================================
// Client principal
// =============================================================================

public class FactPulseClient {
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final Gson gson = new Gson();
    private final String apiUrl, email, password, clientUid;
    private final ChorusProCredentials chorusCredentials;
    private final AFNORCredentials afnorCredentials;
    private final long pollingInterval, pollingTimeout; private final int maxRetries;
    private final OkHttpClient httpClient;
    private String accessToken, refreshToken; private long tokenExpiresAt;

    public FactPulseClient(String email, String password) { this(email, password, null, null, null, null, 2000, 120000, 1); }
    public FactPulseClient(String email, String password, String apiUrl, String clientUid, long pollingInterval, long pollingTimeout, int maxRetries) {
        this(email, password, apiUrl, clientUid, null, null, pollingInterval, pollingTimeout, maxRetries);
    }
    public FactPulseClient(String email, String password, String apiUrl, String clientUid, ChorusProCredentials chorusCredentials, AFNORCredentials afnorCredentials, long pollingInterval, long pollingTimeout, int maxRetries) {
        this.email = email; this.password = password;
        this.apiUrl = (apiUrl != null ? apiUrl : "https://factpulse.fr").replaceAll("/$", "");
        this.clientUid = clientUid;
        this.chorusCredentials = chorusCredentials;
        this.afnorCredentials = afnorCredentials;
        this.pollingInterval = pollingInterval > 0 ? pollingInterval : 2000;
        this.pollingTimeout = pollingTimeout > 0 ? pollingTimeout : 120000; this.maxRetries = maxRetries > 0 ? maxRetries : 1;
        this.httpClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).build();
    }

    public Map<String, Object> getChorusCredentialsForApi() { return chorusCredentials != null ? chorusCredentials.toMap() : null; }
    public Map<String, Object> getAfnorCredentialsForApi() { return afnorCredentials != null ? afnorCredentials.toMap() : null; }
    // Alias plus courts
    public Map<String, Object> getChorusProCredentials() { return getChorusCredentialsForApi(); }
    public Map<String, Object> getAfnorCredentials() { return getAfnorCredentialsForApi(); }

    public void ensureAuthenticated(boolean forceRefresh) throws FactPulseAuthException {
        long now = System.currentTimeMillis();
        if (forceRefresh || accessToken == null || now >= tokenExpiresAt) {
            Map<String, String> payload = new HashMap<>();
            payload.put("username", email); payload.put("password", password);
            if (clientUid != null) payload.put("client_uid", clientUid);
            Request request = new Request.Builder().url(apiUrl + "/api/token/").post(RequestBody.create(gson.toJson(payload), JSON)).build();
            try (Response response = httpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) throw new FactPulseAuthException("Auth failed");
                Type type = new TypeToken<Map<String, String>>(){}.getType();
                Map<String, String> tokens = gson.fromJson(response.body().string(), type);
                accessToken = tokens.get("access"); refreshToken = tokens.get("refresh"); tokenExpiresAt = now + (28 * 60 * 1000);
            } catch (IOException e) { throw new FactPulseAuthException("Network error: " + e.getMessage()); }
        }
    }
    public void ensureAuthenticated() throws FactPulseAuthException { ensureAuthenticated(false); }
    public void resetAuth() { accessToken = refreshToken = null; tokenExpiresAt = 0; }

    @SuppressWarnings("unchecked")
    public Map<String, Object> pollTask(String taskId, Long timeout, Long interval) throws FactPulseException {
        long timeoutMs = timeout != null ? timeout : pollingTimeout;
        long startTime = System.currentTimeMillis(); double currentInterval = interval != null ? interval : pollingInterval;
        while (true) {
            if (System.currentTimeMillis() - startTime > timeoutMs) throw new FactPulsePollingTimeoutException(taskId, timeoutMs);
            ensureAuthenticated();
            Request request = new Request.Builder().url(apiUrl + "/api/v1/traitement/taches/" + taskId + "/statut")
                .addHeader("Authorization", "Bearer " + accessToken).get().build();
            try (Response response = httpClient.newCall(request).execute()) {
                if (response.code() == 401) { resetAuth(); continue; }
                Type type = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> data = gson.fromJson(response.body().string(), type);
                String statut = (String) data.get("statut");
                if ("SUCCESS".equals(statut)) return data.get("resultat") instanceof Map ? (Map<String, Object>) data.get("resultat") : new HashMap<>();
                if ("FAILURE".equals(statut)) {
                    Map<String, Object> res = (Map<String, Object>) data.get("resultat");
                    List<ValidationErrorDetail> errors = new ArrayList<>();
                    if (res != null && res.containsKey("erreurs"))
                        for (Map<String, Object> e : (List<Map<String, Object>>) res.get("erreurs")) errors.add(ValidationErrorDetail.fromMap(e));
                    throw new FactPulseValidationException("Task " + taskId + " failed: " + (res != null ? res.get("message_erreur") : "?"), errors);
                }
                Thread.sleep((long) currentInterval); currentInterval = Math.min(currentInterval * 1.5, 10000);
            } catch (IOException | InterruptedException e) { throw new FactPulseValidationException("Error: " + e.getMessage()); }
        }
    }

    public byte[] genererFacturx(Object factureData, String pdfPath, String profil, String formatSortie, boolean sync, Long timeout) throws FactPulseException {
        String jsonData;
        if (factureData instanceof String) jsonData = (String) factureData;
        else if (factureData instanceof Map) jsonData = gson.toJson(factureData);
        else jsonData = gson.toJson(factureData);

        if (profil == null) profil = "EN16931";
        if (formatSortie == null) formatSortie = "pdf";

        java.io.File pdfFile = new java.io.File(pdfPath);
        for (int attempt = 0; attempt <= maxRetries; attempt++) {
            ensureAuthenticated();
            try {
                RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("donnees_facture", null, RequestBody.create(jsonData, MediaType.parse("application/json")))
                    .addFormDataPart("profil", profil)
                    .addFormDataPart("format_sortie", formatSortie)
                    .addFormDataPart("source_pdf", pdfFile.getName(), RequestBody.create(pdfFile, MediaType.parse("application/pdf")))
                    .build();
                Request request = new Request.Builder().url(apiUrl + "/api/v1/traitement/generer-facture")
                    .addHeader("Authorization", "Bearer " + accessToken).post(body).build();
                try (Response response = httpClient.newCall(request).execute()) {
                    if (response.code() == 401 && attempt < maxRetries) { resetAuth(); continue; }
                    if (!response.isSuccessful()) throw new FactPulseValidationException("API error: " + response.code());
                    Type type = new TypeToken<Map<String, Object>>(){}.getType();
                    Map<String, Object> result = gson.fromJson(response.body().string(), type);
                    String taskId = (String) result.get("id_tache");
                    if (taskId == null) throw new FactPulseValidationException("No task ID");
                    if (!sync) return taskId.getBytes();
                    Map<String, Object> pollResult = pollTask(taskId, timeout, null);
                    if (pollResult.containsKey("contenu_b64")) return Base64.getDecoder().decode((String) pollResult.get("contenu_b64"));
                    throw new FactPulseValidationException("No content in result");
                }
            } catch (IOException e) {
                if (attempt < maxRetries) continue;
                throw new FactPulseValidationException("Network error: " + e.getMessage());
            }
        }
        throw new FactPulseValidationException("Failed after retries");
    }
    public byte[] genererFacturx(Object factureData, String pdfPath) throws FactPulseException { return genererFacturx(factureData, pdfPath, "EN16931", "pdf", true, null); }

    public static String formatMontant(Object m) {
        if (m == null) return "0.00";
        if (m instanceof BigDecimal) return ((BigDecimal) m).setScale(2, RoundingMode.HALF_UP).toPlainString();
        if (m instanceof Number) return String.format("%.2f", ((Number) m).doubleValue());
        if (m instanceof String) return (String) m;
        return "0.00";
    }

    // =========================================================================
    // AFNOR PDP - Authentication et helpers internes
    // =========================================================================

    private AFNORCredentials getAfnorCredentialsInternal() throws FactPulseException {
        if (afnorCredentials != null) return afnorCredentials;

        ensureAuthenticated();
        Request request = new Request.Builder().url(apiUrl + "/api/v1/afnor/credentials")
            .addHeader("Authorization", "Bearer " + accessToken).get().build();
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new FactPulseAuthException("Failed to get AFNOR credentials");
            Type type = new TypeToken<Map<String, String>>(){}.getType();
            Map<String, String> creds = gson.fromJson(response.body().string(), type);
            return new AFNORCredentials(creds.get("flow_service_url"), creds.get("token_url"), creds.get("client_id"), creds.get("client_secret"), creds.get("directory_service_url"));
        } catch (IOException e) { throw new FactPulseAuthException("Network error: " + e.getMessage()); }
    }

    private String[] getAfnorTokenAndUrl() throws FactPulseException {
        AFNORCredentials credentials = getAfnorCredentialsInternal();
        FormBody formBody = new FormBody.Builder()
            .add("grant_type", "client_credentials")
            .add("client_id", credentials.clientId)
            .add("client_secret", credentials.clientSecret)
            .build();
        Request request = new Request.Builder().url(apiUrl + "/api/v1/afnor/oauth/token")
            .addHeader("X-PDP-Token-URL", credentials.tokenUrl).post(formBody).build();
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new FactPulseAuthException("AFNOR OAuth2 failed");
            Type type = new TypeToken<Map<String, Object>>(){}.getType();
            Map<String, Object> tokenData = gson.fromJson(response.body().string(), type);
            String token = (String) tokenData.get("access_token");
            if (token == null) throw new FactPulseAuthException("Invalid AFNOR OAuth2 response");
            return new String[]{token, credentials.flowServiceUrl};
        } catch (IOException e) { throw new FactPulseAuthException("Network error: " + e.getMessage()); }
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> makeAfnorRequest(String method, String endpoint, Map<String, Object> jsonData, RequestBody multipartBody) throws FactPulseException {
        String[] tokenAndUrl = getAfnorTokenAndUrl();
        String afnorToken = tokenAndUrl[0], pdpBaseUrl = tokenAndUrl[1];
        String url = apiUrl + "/api/v1/afnor" + endpoint;

        Request.Builder requestBuilder = new Request.Builder().url(url)
            .addHeader("Authorization", "Bearer " + afnorToken)
            .addHeader("X-PDP-Base-URL", pdpBaseUrl);

        if ("GET".equals(method)) requestBuilder.get();
        else if (multipartBody != null) requestBuilder.post(multipartBody);
        else if (jsonData != null) requestBuilder.post(RequestBody.create(gson.toJson(jsonData), JSON));
        else requestBuilder.post(RequestBody.create("", JSON));

        try (Response response = httpClient.newCall(requestBuilder.build()).execute()) {
            String body = response.body() != null ? response.body().string() : "";
            if (!response.isSuccessful()) throw new FactPulseValidationException("AFNOR error: " + response.code() + " - " + body);
            if (body.isEmpty()) return new HashMap<>();
            String contentType = response.header("Content-Type", "");
            if (contentType.contains("application/json")) {
                Type type = new TypeToken<Map<String, Object>>(){}.getType();
                return gson.fromJson(body, type);
            }
            Map<String, Object> result = new HashMap<>();
            result.put("_raw", body.getBytes());
            return result;
        } catch (IOException e) { throw new FactPulseValidationException("Network error: " + e.getMessage()); }
    }

    // ==================== AFNOR Flow Service ====================

    public Map<String, Object> soumettreFactureAfnor(String pdfPath, String flowName, Map<String, Object> options) throws FactPulseException {
        if (options == null) options = new HashMap<>();
        try {
            byte[] pdfContent = Files.readAllBytes(new File(pdfPath).toPath());
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String sha256 = bytesToHex(digest.digest(pdfContent));

            Map<String, Object> flowInfo = new LinkedHashMap<>();
            flowInfo.put("name", flowName);
            flowInfo.put("flowSyntax", options.getOrDefault("flowSyntax", "CII"));
            flowInfo.put("flowProfile", options.getOrDefault("flowProfile", "EN16931"));
            flowInfo.put("sha256", sha256);
            if (options.containsKey("trackingId")) flowInfo.put("trackingId", options.get("trackingId"));

            RequestBody multipartBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", new File(pdfPath).getName(), RequestBody.create(pdfContent, MediaType.parse("application/pdf")))
                .addFormDataPart("flowInfo", null, RequestBody.create(gson.toJson(flowInfo), MediaType.parse("application/json")))
                .build();

            return makeAfnorRequest("POST", "/flow/v1/flows", null, multipartBody);
        } catch (Exception e) { throw new FactPulseValidationException("Error: " + e.getMessage()); }
    }
    public Map<String, Object> soumettreFactureAfnor(String pdfPath, String flowName) throws FactPulseException { return soumettreFactureAfnor(pdfPath, flowName, null); }

    public Map<String, Object> rechercherFluxAfnor(Map<String, Object> criteria) throws FactPulseException {
        if (criteria == null) criteria = new HashMap<>();
        Map<String, Object> searchBody = new LinkedHashMap<>();
        searchBody.put("offset", criteria.getOrDefault("offset", 0));
        searchBody.put("limit", criteria.getOrDefault("limit", 25));
        Map<String, Object> where = new LinkedHashMap<>();
        if (criteria.containsKey("trackingId")) where.put("trackingId", criteria.get("trackingId"));
        if (criteria.containsKey("status")) where.put("status", criteria.get("status"));
        searchBody.put("where", where);
        return makeAfnorRequest("POST", "/flow/v1/flows/search", searchBody, null);
    }
    public Map<String, Object> rechercherFluxAfnor() throws FactPulseException { return rechercherFluxAfnor(null); }

    public byte[] telechargerFluxAfnor(String flowId) throws FactPulseException {
        Map<String, Object> result = makeAfnorRequest("GET", "/flow/v1/flows/" + flowId, null, null);
        return result.containsKey("_raw") ? (byte[]) result.get("_raw") : new byte[0];
    }

    public Map<String, Object> healthcheckAfnor() throws FactPulseException {
        return makeAfnorRequest("GET", "/flow/v1/healthcheck", null, null);
    }

    // ==================== AFNOR Directory ====================

    public Map<String, Object> rechercherSiretAfnor(String siret) throws FactPulseException {
        return makeAfnorRequest("GET", "/directory/siret/" + siret, null, null);
    }

    public Map<String, Object> rechercherSirenAfnor(String siren) throws FactPulseException {
        return makeAfnorRequest("GET", "/directory/siren/" + siren, null, null);
    }

    public Map<String, Object> listerCodesRoutageAfnor(String siren) throws FactPulseException {
        return makeAfnorRequest("GET", "/directory/siren/" + siren + "/routing-codes", null, null);
    }

    // =========================================================================
    // Chorus Pro
    // =========================================================================

    @SuppressWarnings("unchecked")
    private Map<String, Object> makeChorusRequest(String method, String endpoint, Map<String, Object> jsonData) throws FactPulseException {
        ensureAuthenticated();
        String url = apiUrl + "/api/v1/chorus-pro" + endpoint;

        Map<String, Object> body = jsonData != null ? new LinkedHashMap<>(jsonData) : new LinkedHashMap<>();
        if (chorusCredentials != null) body.put("credentials", chorusCredentials.toMap());

        Request.Builder requestBuilder = new Request.Builder().url(url).addHeader("Authorization", "Bearer " + accessToken);
        if ("GET".equals(method)) requestBuilder.get();
        else requestBuilder.post(RequestBody.create(gson.toJson(body), JSON));

        try (Response response = httpClient.newCall(requestBuilder.build()).execute()) {
            if (!response.isSuccessful()) throw new FactPulseValidationException("Chorus Pro error: " + response.code());
            Type type = new TypeToken<Map<String, Object>>(){}.getType();
            return gson.fromJson(response.body().string(), type);
        } catch (IOException e) { throw new FactPulseValidationException("Network error: " + e.getMessage()); }
    }

    public Map<String, Object> rechercherStructureChorus(String identifiantStructure, String raisonSociale, String typeIdentifiant, boolean restreindrePrivees) throws FactPulseException {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("restreindre_structures_privees", restreindrePrivees);
        if (identifiantStructure != null) body.put("identifiant_structure", identifiantStructure);
        if (raisonSociale != null) body.put("raison_sociale_structure", raisonSociale);
        if (typeIdentifiant != null) body.put("type_identifiant_structure", typeIdentifiant);
        return makeChorusRequest("POST", "/structures/rechercher", body);
    }
    public Map<String, Object> rechercherStructureChorus(String identifiantStructure) throws FactPulseException { return rechercherStructureChorus(identifiantStructure, null, "SIRET", true); }

    public Map<String, Object> consulterStructureChorus(int idStructureCpp) throws FactPulseException {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("id_structure_cpp", idStructureCpp);
        return makeChorusRequest("POST", "/structures/consulter", body);
    }

    public Map<String, Object> obtenirIdChorusDepuisSiret(String siret, String typeIdentifiant) throws FactPulseException {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("siret", siret);
        body.put("type_identifiant", typeIdentifiant != null ? typeIdentifiant : "SIRET");
        return makeChorusRequest("POST", "/structures/obtenir-id-depuis-siret", body);
    }
    public Map<String, Object> obtenirIdChorusDepuisSiret(String siret) throws FactPulseException { return obtenirIdChorusDepuisSiret(siret, "SIRET"); }

    public Map<String, Object> listerServicesStructureChorus(int idStructureCpp) throws FactPulseException {
        return makeChorusRequest("GET", "/structures/" + idStructureCpp + "/services", null);
    }

    public Map<String, Object> soumettreFactureChorus(Map<String, Object> factureData) throws FactPulseException {
        return makeChorusRequest("POST", "/factures/soumettre", factureData);
    }

    public Map<String, Object> consulterFactureChorus(int identifiantFactureCpp) throws FactPulseException {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("identifiant_facture_cpp", identifiantFactureCpp);
        return makeChorusRequest("POST", "/factures/consulter", body);
    }

    // =========================================================================
    // Validation
    // =========================================================================

    public Map<String, Object> validerPdfFacturx(String pdfPath, String profil) throws FactPulseException {
        ensureAuthenticated();
        try {
            File pdfFile = new File(pdfPath);
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("fichier_pdf", pdfFile.getName(), RequestBody.create(pdfFile, MediaType.parse("application/pdf")))
                .addFormDataPart("profil", profil != null ? profil : "EN16931")
                .build();
            Request request = new Request.Builder().url(apiUrl + "/api/v1/traitement/valider-pdf-facturx")
                .addHeader("Authorization", "Bearer " + accessToken).post(body).build();
            try (Response response = httpClient.newCall(request).execute()) {
                Type type = new TypeToken<Map<String, Object>>(){}.getType();
                return gson.fromJson(response.body().string(), type);
            }
        } catch (IOException e) { throw new FactPulseValidationException("Validation error: " + e.getMessage()); }
    }
    public Map<String, Object> validerPdfFacturx(String pdfPath) throws FactPulseException { return validerPdfFacturx(pdfPath, "EN16931"); }

    public Map<String, Object> validerXmlFacturx(String xmlContent, String profil) throws FactPulseException {
        ensureAuthenticated();
        try {
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("fichier_xml", "facture.xml", RequestBody.create(xmlContent, MediaType.parse("application/xml")))
                .addFormDataPart("profil", profil != null ? profil : "EN16931")
                .build();
            Request request = new Request.Builder().url(apiUrl + "/api/v1/traitement/valider-xml")
                .addHeader("Authorization", "Bearer " + accessToken).post(body).build();
            try (Response response = httpClient.newCall(request).execute()) {
                Type type = new TypeToken<Map<String, Object>>(){}.getType();
                return gson.fromJson(response.body().string(), type);
            }
        } catch (IOException e) { throw new FactPulseValidationException("Validation error: " + e.getMessage()); }
    }
    public Map<String, Object> validerXmlFacturx(String xmlContent) throws FactPulseException { return validerXmlFacturx(xmlContent, "EN16931"); }

    public Map<String, Object> validerSignaturePdf(String pdfPath) throws FactPulseException {
        ensureAuthenticated();
        try {
            File pdfFile = new File(pdfPath);
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("fichier_pdf", pdfFile.getName(), RequestBody.create(pdfFile, MediaType.parse("application/pdf")))
                .build();
            Request request = new Request.Builder().url(apiUrl + "/api/v1/traitement/valider-signature-pdf")
                .addHeader("Authorization", "Bearer " + accessToken).post(body).build();
            try (Response response = httpClient.newCall(request).execute()) {
                Type type = new TypeToken<Map<String, Object>>(){}.getType();
                return gson.fromJson(response.body().string(), type);
            }
        } catch (IOException e) { throw new FactPulseValidationException("Validation error: " + e.getMessage()); }
    }

    // =========================================================================
    // Signature
    // =========================================================================

    public byte[] signerPdf(String pdfPath, Map<String, Object> options) throws FactPulseException {
        if (options == null) options = new HashMap<>();
        ensureAuthenticated();
        try {
            File pdfFile = new File(pdfPath);
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("fichier_pdf", pdfFile.getName(), RequestBody.create(pdfFile, MediaType.parse("application/pdf")));
            if (options.containsKey("raison")) builder.addFormDataPart("raison", (String) options.get("raison"));
            if (options.containsKey("localisation")) builder.addFormDataPart("localisation", (String) options.get("localisation"));
            if (options.containsKey("contact")) builder.addFormDataPart("contact", (String) options.get("contact"));
            builder.addFormDataPart("use_pades_lt", Boolean.TRUE.equals(options.get("usePadesLt")) ? "true" : "false");
            builder.addFormDataPart("use_timestamp", options.containsKey("useTimestamp") ? (Boolean.TRUE.equals(options.get("useTimestamp")) ? "true" : "false") : "true");

            Request request = new Request.Builder().url(apiUrl + "/api/v1/traitement/signer-pdf")
                .addHeader("Authorization", "Bearer " + accessToken).post(builder.build()).build();
            try (Response response = httpClient.newCall(request).execute()) {
                Type type = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> result = gson.fromJson(response.body().string(), type);
                String base64Pdf = (String) result.get("pdf_signe_base64");
                if (base64Pdf == null) throw new FactPulseValidationException("Invalid signature response");
                return Base64.getDecoder().decode(base64Pdf);
            }
        } catch (IOException e) { throw new FactPulseValidationException("Signature error: " + e.getMessage()); }
    }
    public byte[] signerPdf(String pdfPath) throws FactPulseException { return signerPdf(pdfPath, null); }

    public Map<String, Object> genererCertificatTest(Map<String, Object> options) throws FactPulseException {
        if (options == null) options = new HashMap<>();
        ensureAuthenticated();
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("cn", options.getOrDefault("cn", "Test Organisation"));
        body.put("organisation", options.getOrDefault("organisation", "Test Organisation"));
        body.put("email", options.getOrDefault("email", "test@example.com"));
        body.put("duree_jours", options.getOrDefault("dureeJours", 365));
        body.put("taille_cle", options.getOrDefault("tailleCle", 2048));

        Request request = new Request.Builder().url(apiUrl + "/api/v1/traitement/generer-certificat-test")
            .addHeader("Authorization", "Bearer " + accessToken)
            .post(RequestBody.create(gson.toJson(body), JSON)).build();
        try (Response response = httpClient.newCall(request).execute()) {
            Type type = new TypeToken<Map<String, Object>>(){}.getType();
            return gson.fromJson(response.body().string(), type);
        } catch (IOException e) { throw new FactPulseValidationException("Error: " + e.getMessage()); }
    }
    public Map<String, Object> genererCertificatTest() throws FactPulseException { return genererCertificatTest(null); }

    // =========================================================================
    // Workflow complet
    // =========================================================================

    @SuppressWarnings("unchecked")
    public Map<String, Object> genererFacturxComplet(Map<String, Object> facture, String pdfSourcePath, Map<String, Object> options) throws FactPulseException {
        if (options == null) options = new HashMap<>();
        String profil = (String) options.getOrDefault("profil", "EN16931");
        boolean valider = (boolean) options.getOrDefault("valider", true);
        boolean signer = (boolean) options.getOrDefault("signer", false);
        boolean soumettreAfnor = (boolean) options.getOrDefault("soumettreAfnor", false);
        Long timeout = options.containsKey("timeout") ? ((Number) options.get("timeout")).longValue() : 120000L;

        Map<String, Object> result = new LinkedHashMap<>();

        // 1. Génération
        byte[] pdfBytes = genererFacturx(facture, pdfSourcePath, profil, "pdf", true, timeout);
        result.put("pdfBytes", pdfBytes);

        // Créer un fichier temporaire
        File tempFile = null;
        try {
            tempFile = File.createTempFile("facturx_", ".pdf");
            Files.write(tempFile.toPath(), pdfBytes);

            // 2. Validation
            if (valider) {
                Map<String, Object> validation = validerPdfFacturx(tempFile.getAbsolutePath(), profil);
                result.put("validation", validation);
                if (!Boolean.TRUE.equals(validation.get("est_conforme"))) {
                    if (options.containsKey("outputPath")) {
                        Files.write(new File((String) options.get("outputPath")).toPath(), pdfBytes);
                        result.put("pdfPath", options.get("outputPath"));
                    }
                    return result;
                }
            }

            // 3. Signature
            if (signer) {
                pdfBytes = signerPdf(tempFile.getAbsolutePath(), options);
                result.put("pdfBytes", pdfBytes);
                result.put("signature", Map.of("signe", true));
                Files.write(tempFile.toPath(), pdfBytes);
            }

            // 4. Soumission AFNOR
            if (soumettreAfnor) {
                String numeroFacture = (String) facture.getOrDefault("numeroFacture", facture.getOrDefault("numero_facture", "FACTURE"));
                String flowName = (String) options.getOrDefault("afnorFlowName", "Facture " + numeroFacture);
                String trackingId = (String) options.getOrDefault("afnorTrackingId", numeroFacture);
                Map<String, Object> afnorOpts = new HashMap<>();
                afnorOpts.put("trackingId", trackingId);
                Map<String, Object> afnorResult = soumettreFactureAfnor(tempFile.getAbsolutePath(), flowName, afnorOpts);
                result.put("afnor", afnorResult);
            }

            // Sauvegarde finale
            if (options.containsKey("outputPath")) {
                Files.write(new File((String) options.get("outputPath")).toPath(), pdfBytes);
                result.put("pdfPath", options.get("outputPath"));
            }
        } catch (IOException e) {
            throw new FactPulseValidationException("IO error: " + e.getMessage());
        } finally {
            if (tempFile != null) tempFile.delete();
        }

        return result;
    }

    // Utilitaires
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
