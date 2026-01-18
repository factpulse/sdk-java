/**
 * FactPulse SDK - Thin HTTP wrapper with auto-polling.
 *
 * Usage:
 *   FactPulseClient client = new FactPulseClient("email", "password", "client_uid");
 *
 *   // POST /api/v1/processing/invoices/submit-complete-async
 *   Map<String, Object> result = client.post("processing/invoices/submit-complete-async", Map.of(
 *       "invoiceData", invoiceData,
 *       "destination", Map.of("type", "afnor")
 *   ));
 *   byte[] pdfBytes = (byte[]) result.get("content"); // auto-decoded, auto-polled
 */
package fr.factpulse.sdk.helpers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class FactPulseClient {
    private static final String DEFAULT_API_URL = "https://factpulse.fr";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private final String apiUrl, email, password, clientUid;
    private final int timeout, pollingTimeout;
    private final OkHttpClient httpClient;
    private final Gson gson = new Gson();
    private final ReentrantLock tokenLock = new ReentrantLock();

    private String token;
    private long tokenExpiresAt;

    public FactPulseClient(String email, String password, String clientUid) {
        this(email, password, clientUid, DEFAULT_API_URL, 60, 120);
    }

    public FactPulseClient(String email, String password, String clientUid, String apiUrl, int timeoutSec, int pollingTimeoutSec) {
        this.email = email;
        this.password = password;
        this.clientUid = clientUid;
        this.apiUrl = (apiUrl != null ? apiUrl : DEFAULT_API_URL).replaceAll("/$", "");
        this.timeout = timeoutSec;
        this.pollingTimeout = pollingTimeoutSec;
        this.httpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(timeoutSec, TimeUnit.SECONDS)
            .build();
    }

    /** POST request to /api/v1/{path} */
    public Map<String, Object> post(String path, Map<String, Object> data) throws IOException, InterruptedException {
        return request("POST", path, data, true);
    }

    /** GET request to /api/v1/{path} */
    public Map<String, Object> get(String path) throws IOException, InterruptedException {
        return request("GET", path, null, true);
    }

    private Map<String, Object> request(String method, String path, Map<String, Object> data, boolean retryAuth) throws IOException, InterruptedException {
        ensureAuth();
        String url = apiUrl + "/api/v1/" + path;

        Request.Builder reqBuilder = new Request.Builder()
            .url(url)
            .header("Authorization", "Bearer " + token);

        if ("POST".equals(method)) {
            reqBuilder.post(RequestBody.create(gson.toJson(data != null ? data : Map.of()), JSON));
        } else {
            reqBuilder.get();
        }

        try (Response response = httpClient.newCall(reqBuilder.build()).execute()) {
            if (response.code() == 401 && retryAuth) {
                invalidateToken();
                return request(method, path, data, false);
            }

            Map<String, Object> result = parseResponse(response);

            // Auto-poll: support both taskId (camelCase) and task_id (snake_case)
            String taskId = result.containsKey("taskId") ? (String) result.get("taskId")
                          : result.containsKey("task_id") ? (String) result.get("task_id") : null;
            if (taskId != null) {
                result = poll(taskId);
            }

            // Auto-decode: support both content_b64 and contentB64
            String b64Content = result.containsKey("content_b64") ? (String) result.get("content_b64")
                              : result.containsKey("contentB64") ? (String) result.get("contentB64") : null;
            if (b64Content != null) {
                result.put("content", Base64.getDecoder().decode(b64Content));
                result.remove("content_b64");
                result.remove("contentB64");
            }

            return result;
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> parseResponse(Response response) throws IOException {
        String body = response.body() != null ? response.body().string() : "";
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> data = body.isEmpty() ? new HashMap<>() : gson.fromJson(body, type);

        if (response.isSuccessful()) return data != null ? data : new HashMap<>();

        String msg = "HTTP " + response.code();
        List<Object> details = new ArrayList<>();

        if (data != null) {
            Object detail = data.get("detail");
            if (detail instanceof List) {
                List<Object> errs = (List<Object>) detail;
                details.addAll(errs);
                StringBuilder sb = new StringBuilder("Validation error: ");
                for (Object e : errs) {
                    if (e instanceof Map) {
                        Map<String, Object> err = (Map<String, Object>) e;
                        List<?> loc = (List<?>) err.get("loc");
                        sb.append(loc != null && !loc.isEmpty() ? loc.get(loc.size() - 1) : "?")
                          .append(": ").append(err.get("msg")).append("; ");
                    }
                }
                msg = sb.toString();
            } else if (detail instanceof String) {
                msg = (String) detail;
            } else if (data.get("errorMessage") instanceof String) {
                msg = (String) data.get("errorMessage");
            }
        }

        throw new FactPulseException(msg, response.code(), details);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> poll(String taskId) throws IOException, InterruptedException {
        long start = System.currentTimeMillis();
        long interval = 1000;

        while (true) {
            long elapsed = System.currentTimeMillis() - start;
            if (elapsed >= pollingTimeout * 1000L) {
                throw new FactPulseException("Polling timeout after " + pollingTimeout + "s for task " + taskId);
            }

            ensureAuth();
            Request request = new Request.Builder()
                .url(apiUrl + "/api/v1/processing/tasks/" + taskId + "/status")
                .header("Authorization", "Bearer " + token)
                .get()
                .build();

            try (Response response = httpClient.newCall(request).execute()) {
                if (response.code() == 401) {
                    invalidateToken();
                    continue;
                }

                Map<String, Object> data = parseResponse(response);
                String status = (String) data.get("status");

                if ("SUCCESS".equals(status)) {
                    Map<String, Object> result = (Map<String, Object>) data.getOrDefault("result", new HashMap<>());
                    // Support both content_b64 and contentB64
                    String b64Content = result.containsKey("content_b64") ? (String) result.get("content_b64")
                                      : result.containsKey("contentB64") ? (String) result.get("contentB64") : null;
                    if (b64Content != null) {
                        result.put("content", Base64.getDecoder().decode(b64Content));
                        result.remove("content_b64");
                        result.remove("contentB64");
                    }
                    return result;
                }

                if ("FAILURE".equals(status)) {
                    Map<String, Object> res = (Map<String, Object>) data.getOrDefault("result", new HashMap<>());
                    throw new FactPulseException((String) res.getOrDefault("errorMessage", "Task failed"));
                }

                Thread.sleep(Math.min(interval, pollingTimeout * 1000L - elapsed));
                interval = Math.min((long) (interval * 1.5), 10000);
            }
        }
    }

    private void ensureAuth() throws IOException {
        tokenLock.lock();
        try {
            if (System.currentTimeMillis() >= tokenExpiresAt) {
                refreshToken();
            }
        } finally {
            tokenLock.unlock();
        }
    }

    private void refreshToken() throws IOException {
        Map<String, String> payload = Map.of("username", email, "password", password, "client_uid", clientUid);
        Request request = new Request.Builder()
            .url(apiUrl + "/api/token/")
            .post(RequestBody.create(gson.toJson(payload), JSON))
            .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new FactPulseException("Authentication failed: HTTP " + response.code(), response.code());
            }
            Type type = new TypeToken<Map<String, String>>(){}.getType();
            Map<String, String> data = gson.fromJson(response.body().string(), type);
            token = data.get("access");
            tokenExpiresAt = System.currentTimeMillis() + 28 * 60 * 1000;
        }
    }

    private void invalidateToken() {
        tokenLock.lock();
        try {
            tokenExpiresAt = 0;
        } finally {
            tokenLock.unlock();
        }
    }
}
