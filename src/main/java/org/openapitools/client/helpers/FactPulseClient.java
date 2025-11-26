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
    private final long pollingInterval, pollingTimeout; private final int maxRetries;
    private final OkHttpClient httpClient;
    private String accessToken, refreshToken; private long tokenExpiresAt;

    public FactPulseClient(String email, String password) { this(email, password, null, null, 2000, 120000, 1); }
    public FactPulseClient(String email, String password, String apiUrl, String clientUid, long pollingInterval, long pollingTimeout, int maxRetries) {
        this.email = email; this.password = password;
        this.apiUrl = (apiUrl != null ? apiUrl : "https://factpulse.fr").replaceAll("/$", "");
        this.clientUid = clientUid; this.pollingInterval = pollingInterval > 0 ? pollingInterval : 2000;
        this.pollingTimeout = pollingTimeout > 0 ? pollingTimeout : 120000; this.maxRetries = maxRetries > 0 ? maxRetries : 1;
        this.httpClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();
    }

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
            Request request = new Request.Builder().url(apiUrl + "/api/facturation/v1/traitement/taches/" + taskId + "/statut")
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

    public static String formatMontant(Object m) {
        if (m == null) return "0.00";
        if (m instanceof BigDecimal) return ((BigDecimal) m).setScale(2, RoundingMode.HALF_UP).toPlainString();
        if (m instanceof Number) return String.format("%.2f", ((Number) m).doubleValue());
        if (m instanceof String) return (String) m;
        return "0.00";
    }
}
