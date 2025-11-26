package org.openapitools.client.helpers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

class AFNORCredentials {
    public final String clientId, clientSecret, flowServiceUrl;
    public AFNORCredentials(String clientId, String clientSecret, String flowServiceUrl) {
        this.clientId = clientId; this.clientSecret = clientSecret; this.flowServiceUrl = flowServiceUrl;
    }
    public Map<String, Object> toMap() {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("client_id", clientId); m.put("client_secret", clientSecret); m.put("flow_service_url", flowServiceUrl);
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
    public static Map<String, Object> ligneDePoste(int numero, String denomination, Object quantite, Object montantUnitaireHt, Object montantLigneHt) {
        return ligneDePoste(numero, denomination, quantite, montantUnitaireHt, montantLigneHt, "20.00", "S", "C62", null);
    }
    public static Map<String, Object> ligneDePoste(int numero, String denomination, Object quantite, Object montantUnitaireHt, Object montantLigneHt, String tauxTva, String categorieTva, String unite, Map<String, Object> options) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("numero", numero); result.put("denomination", denomination);
        result.put("quantite", montant(quantite)); result.put("montantUnitaireHt", montant(montantUnitaireHt));
        result.put("montantTotalLigneHt", montant(montantLigneHt)); result.put("tauxTva", montant(tauxTva));
        result.put("categorieTva", categorieTva); result.put("unite", unite);
        if (options != null) {
            if (options.containsKey("reference")) result.put("reference", options.get("reference"));
            if (options.containsKey("montantTvaLigne")) result.put("montantTvaLigne", montant(options.get("montantTvaLigne")));
            if (options.containsKey("montantRemiseHt")) result.put("montantRemiseHt", montant(options.get("montantRemiseHt")));
            if (options.containsKey("codeRaisonReduction")) result.put("codeRaisonReduction", options.get("codeRaisonReduction"));
            if (options.containsKey("raisonReduction")) result.put("raisonReduction", options.get("raisonReduction"));
            if (options.containsKey("motifExoneration")) result.put("motifExoneration", options.get("motifExoneration"));
            if (options.containsKey("dateDebutPeriode")) result.put("dateDebutPeriode", options.get("dateDebutPeriode"));
            if (options.containsKey("dateFinPeriode")) result.put("dateFinPeriode", options.get("dateFinPeriode"));
            if (options.containsKey("description")) result.put("description", options.get("description"));
        }
        return result;
    }
    public static Map<String, Object> ligneDeTva(Object taux, Object baseHt, Object montantTva) { return ligneDeTva(taux, baseHt, montantTva, "S", null); }
    public static Map<String, Object> ligneDeTva(Object taux, Object baseHt, Object montantTva, String categorie, String motifExoneration) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("tauxTva", montant(taux)); result.put("montantBaseHt", montant(baseHt));
        result.put("montantTva", montant(montantTva)); result.put("categorieTva", categorie);
        if (motifExoneration != null) result.put("motifExoneration", motifExoneration);
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
        this.httpClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();
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
        else jsonData = gson.toJson(factureData); // Pour les objets avec to_dict ou similaire

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
}
