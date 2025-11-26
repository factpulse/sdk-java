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

public class FactPulseClient {
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final Gson gson = new Gson();
    private final String apiUrl, email, password, clientUid;
    private final long pollingInterval, pollingTimeout;
    private final int maxRetries;
    private final OkHttpClient httpClient;
    private String accessToken, refreshToken;
    private long tokenExpiresAt;

    public FactPulseClient(String email, String password) { this(email, password, null, null, 2000, 120000, 1); }

    public FactPulseClient(String email, String password, String apiUrl, String clientUid, long pollingInterval, long pollingTimeout, int maxRetries) {
        this.email = email; this.password = password;
        this.apiUrl = (apiUrl != null ? apiUrl : "https://factpulse.fr").replaceAll("/$", "");
        this.clientUid = clientUid;
        this.pollingInterval = pollingInterval > 0 ? pollingInterval : 2000;
        this.pollingTimeout = pollingTimeout > 0 ? pollingTimeout : 120000;
        this.maxRetries = maxRetries > 0 ? maxRetries : 1;
        this.httpClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();
    }

    private Map<String, String> obtainToken() throws FactPulseAuthException {
        Map<String, String> payload = new HashMap<>();
        payload.put("username", email); payload.put("password", password);
        if (clientUid != null) payload.put("client_uid", clientUid);
        Request request = new Request.Builder().url(apiUrl + "/api/token/").post(RequestBody.create(gson.toJson(payload), JSON)).build();
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new FactPulseAuthException("Impossible d'obtenir le token JWT");
            Type type = new TypeToken<Map<String, String>>(){}.getType();
            return gson.fromJson(response.body().string(), type);
        } catch (IOException e) { throw new FactPulseAuthException("Erreur réseau: " + e.getMessage()); }
    }

    public void ensureAuthenticated(boolean forceRefresh) throws FactPulseAuthException {
        long now = System.currentTimeMillis();
        if (forceRefresh || accessToken == null || now >= tokenExpiresAt) {
            Map<String, String> tokens = obtainToken();
            accessToken = tokens.get("access"); refreshToken = tokens.get("refresh");
            tokenExpiresAt = now + (28 * 60 * 1000);
        }
    }
    public void ensureAuthenticated() throws FactPulseAuthException { ensureAuthenticated(false); }
    public void resetAuth() { accessToken = refreshToken = null; tokenExpiresAt = 0; }

    @SuppressWarnings("unchecked")
    public Map<String, Object> pollTask(String taskId, Long timeout, Long interval) throws FactPulseException {
        long timeoutMs = timeout != null ? timeout : pollingTimeout;
        long intervalMs = interval != null ? interval : pollingInterval;
        long startTime = System.currentTimeMillis();
        double currentInterval = intervalMs;

        while (true) {
            if (System.currentTimeMillis() - startTime > timeoutMs) throw new FactPulsePollingTimeoutException(taskId, timeoutMs);
            ensureAuthenticated();
            Request request = new Request.Builder().url(apiUrl + "/api/facturation/v1/traitement/taches/" + taskId + "/statut")
                .addHeader("Authorization", "Bearer " + accessToken).get().build();
            try (Response response = httpClient.newCall(request).execute()) {
                if (response.code() == 401) { resetAuth(); continue; }
                Type type = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> data = gson.fromJson(response.body().string(), type);
                String statut = (String) data.get("statut");
                if ("SUCCESS".equals(statut)) return data.get("resultat") instanceof Map ? (Map<String, Object>) data.get("resultat") : new HashMap<>();
                if ("FAILURE".equals(statut)) {
                    Map<String, Object> resultat = (Map<String, Object>) data.get("resultat");
                    String errorMsg = resultat != null && resultat.containsKey("message_erreur") ? (String) resultat.get("message_erreur") : "Erreur inconnue";
                    List<ValidationErrorDetail> errors = new ArrayList<>();
                    if (resultat != null && resultat.containsKey("erreurs")) {
                        for (Map<String, Object> err : (List<Map<String, Object>>) resultat.get("erreurs")) errors.add(ValidationErrorDetail.fromMap(err));
                    }
                    throw new FactPulseValidationException("La tâche " + taskId + " a échoué: " + errorMsg, errors);
                }
                Thread.sleep((long) currentInterval);
                currentInterval = Math.min(currentInterval * 1.5, 10000);
            } catch (IOException | InterruptedException e) { throw new FactPulseValidationException("Erreur: " + e.getMessage()); }
        }
    }
    public Map<String, Object> pollTask(String taskId) throws FactPulseException { return pollTask(taskId, null, null); }

    @SuppressWarnings("unchecked")
    public byte[] genererFacturx(Object factureData, File pdfFile, String profil, String formatSortie, boolean sync, Long timeout) throws FactPulseException {
        String jsonData = factureData instanceof String ? (String) factureData : gson.toJson(factureData);
        if (profil == null || profil.isEmpty()) profil = "EN16931";
        if (formatSortie == null || formatSortie.isEmpty()) formatSortie = "pdf";
        String taskId = null;

        for (int attempt = 0; attempt <= maxRetries; attempt++) {
            ensureAuthenticated();
            RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("donnees_facture", jsonData).addFormDataPart("profil", profil).addFormDataPart("format_sortie", formatSortie)
                .addFormDataPart("source_pdf", pdfFile.getName(), RequestBody.create(pdfFile, MediaType.parse("application/pdf"))).build();
            Request request = new Request.Builder().url(apiUrl + "/api/facturation/v1/traitement/generer-facture")
                .addHeader("Authorization", "Bearer " + accessToken).post(requestBody).build();
            try (Response response = httpClient.newCall(request).execute()) {
                if (response.code() == 401 && attempt < maxRetries) { resetAuth(); continue; }
                Type type = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> data = gson.fromJson(response.body().string(), type);
                taskId = (String) data.get("id_tache"); break;
            } catch (IOException e) { if (attempt >= maxRetries) throw new FactPulseValidationException("Erreur: " + e.getMessage()); resetAuth(); }
        }
        if (taskId == null) throw new FactPulseValidationException("Pas d'ID de tâche");
        if (!sync) return taskId.getBytes();
        Map<String, Object> result = pollTask(taskId, timeout, null);
        if (result.containsKey("contenu_b64")) return Base64.getDecoder().decode((String) result.get("contenu_b64"));
        throw new FactPulseValidationException("Pas de contenu");
    }
    public byte[] genererFacturx(Object factureData, File pdfFile) throws FactPulseException { return genererFacturx(factureData, pdfFile, "EN16931", "pdf", true, null); }

    public static String formatMontant(Object montant) {
        if (montant == null) return "0.00";
        if (montant instanceof BigDecimal) return ((BigDecimal) montant).setScale(2, RoundingMode.HALF_UP).toPlainString();
        if (montant instanceof Number) return String.format("%.2f", ((Number) montant).doubleValue());
        if (montant instanceof String) return (String) montant;
        return "0.00";
    }
}
