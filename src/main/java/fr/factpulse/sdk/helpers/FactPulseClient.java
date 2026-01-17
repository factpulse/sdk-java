package fr.factpulse.sdk.helpers;

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

/** AFNOR PDP credentials for Zero-Trust mode. The FactPulse API uses these credentials to authenticate with the AFNOR PDP. */
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
// Amount helpers
// =============================================================================

class AmountHelpers {
    public static String amount(Object value) {
        if (value == null) return "0.00";
        if (value instanceof BigDecimal) return ((BigDecimal) value).setScale(2, RoundingMode.HALF_UP).toPlainString();
        if (value instanceof Number) return String.format("%.2f", ((Number) value).doubleValue());
        if (value instanceof String) return (String) value;
        return "0.00";
    }
    public static Map<String, Object> invoiceTotals(Object exclTax, Object vat, Object inclTax, Object amountDue) {
        return invoiceTotals(exclTax, vat, inclTax, amountDue, null, null, null);
    }
    public static Map<String, Object> invoiceTotals(Object exclTax, Object vat, Object inclTax, Object amountDue, Object globalAllowanceAmount, String globalAllowanceReason, Object prepayment) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("totalNetAmount", amount(exclTax)); result.put("vatAmount", amount(vat));
        result.put("totalGrossAmount", amount(inclTax)); result.put("amountDue", amount(amountDue));
        if (globalAllowanceAmount != null) result.put("globalAllowanceAmount", amount(globalAllowanceAmount));
        if (globalAllowanceReason != null) result.put("globalAllowanceReason", globalAllowanceReason);
        if (prepayment != null) result.put("prepayment", amount(prepayment));
        return result;
    }
    /** Creates an invoice line (aligned with InvoiceLine in models.py). */
    public static Map<String, Object> invoiceLine(int lineNumber, String itemName, Object quantity, Object unitNetPrice, Object lineNetAmount) {
        return invoiceLine(lineNumber, itemName, quantity, unitNetPrice, lineNetAmount, "20.00", "S", "LUMP_SUM", null);
    }
    public static Map<String, Object> invoiceLine(int lineNumber, String itemName, Object quantity, Object unitNetPrice, Object lineNetAmount, String vatRate, String vatCategory, String unit, Map<String, Object> options) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("lineNumber", lineNumber); result.put("itemName", itemName);
        result.put("quantity", amount(quantity)); result.put("unitNetPrice", amount(unitNetPrice));
        result.put("lineNetAmount", amount(lineNetAmount)); result.put("manualVatRate", amount(vatRate));
        result.put("vatCategory", vatCategory); result.put("unit", unit);
        if (options != null) {
            if (options.containsKey("reference")) result.put("reference", options.get("reference"));
            if (options.containsKey("lineAllowanceAmount")) result.put("lineAllowanceAmount", amount(options.get("lineAllowanceAmount")));
            if (options.containsKey("allowanceReasonCode")) result.put("allowanceReasonCode", options.get("allowanceReasonCode"));
            if (options.containsKey("allowanceReason")) result.put("allowanceReason", options.get("allowanceReason"));
            if (options.containsKey("periodStartDate")) result.put("periodStartDate", options.get("periodStartDate"));
            if (options.containsKey("periodEndDate")) result.put("periodEndDate", options.get("periodEndDate"));
        }
        return result;
    }
    /** Creates a VAT line (aligned with VATLine in models.py). */
    public static Map<String, Object> vatLine(Object manualRate, Object taxableAmount, Object vatAmount) { return vatLine(manualRate, taxableAmount, vatAmount, "S"); }
    public static Map<String, Object> vatLine(Object manualRate, Object taxableAmount, Object vatAmount, String category) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("manualRate", amount(manualRate)); result.put("taxableAmount", amount(taxableAmount));
        result.put("vatAmount", amount(vatAmount)); result.put("category", category);
        return result;
    }
    /** Creates a postal address for the FactPulse API. */
    public static Map<String, Object> postalAddress(String lineOne, String postalCode, String city) { return postalAddress(lineOne, postalCode, city, "FR", null, null); }
    public static Map<String, Object> postalAddress(String lineOne, String postalCode, String city, String countryCode, String lineTwo, String lineThree) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("lineOne", lineOne); result.put("postalCode", postalCode); result.put("city", city); result.put("countryCode", countryCode != null ? countryCode : "FR");
        if (lineTwo != null) result.put("lineTwo", lineTwo);
        if (lineThree != null) result.put("lineThree", lineThree);
        return result;
    }
    /** Creates an electronic address. schemeId: "0009"=SIREN, "0225"=SIRET */
    public static Map<String, Object> electronicAddress(String identifier) { return electronicAddress(identifier, "0009"); }
    public static Map<String, Object> electronicAddress(String identifier, String schemeId) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("identifier", identifier); result.put("schemeId", schemeId);
        return result;
    }
    /** Computes the French intra-community VAT number from a SIREN. */
    private static String computeVatIntra(String siren) {
        if (siren == null || siren.length() != 9 || !siren.matches("\\d+")) return null;
        long cle = (12 + 3 * (Long.parseLong(siren) % 97)) % 97;
        return String.format("FR%02d%s", cle, siren);
    }
    /** Creates a supplier (issuer) with auto-computed SIREN, intra-EU VAT number and addresses. */
    public static Map<String, Object> supplier(String name, String siret, String addressLine1, String postalCode, String city) { return supplier(name, siret, addressLine1, postalCode, city, null); }
    public static Map<String, Object> supplier(String name, String siret, String addressLine1, String postalCode, String city, Map<String, Object> options) {
        if (options == null) options = new LinkedHashMap<>();
        String siren = options.containsKey("siren") ? (String)options.get("siren") : (siret.length() == 14 ? siret.substring(0, 9) : null);
        String vatNumber = options.containsKey("vatNumber") ? (String)options.get("vatNumber") : (siren != null ? computeVatIntra(siren) : null);
        String countryCode = options.containsKey("countryCode") ? (String)options.get("countryCode") : "FR";
        String addressLine2 = options.containsKey("addressLine2") ? (String)options.get("addressLine2") : null;
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("name", name); result.put("supplierId", options.getOrDefault("supplierId", 0)); result.put("siret", siret);
        result.put("electronicAddress", electronicAddress(siret, "0225"));
        result.put("postalAddress", postalAddress(addressLine1, postalCode, city, countryCode, addressLine2, null));
        if (siren != null) result.put("siren", siren);
        if (vatNumber != null) result.put("vatNumber", vatNumber);
        if (options.containsKey("iban")) result.put("iban", options.get("iban"));
        if (options.containsKey("supplierServiceId")) result.put("supplierServiceId", options.get("supplierServiceId"));
        if (options.containsKey("supplierBankDetailsCode")) result.put("supplierBankDetailsCode", options.get("supplierBankDetailsCode"));
        return result;
    }
    /** Creates a recipient (customer) with auto-computed SIREN and addresses. */
    public static Map<String, Object> recipient(String name, String siret, String addressLine1, String postalCode, String city) { return recipient(name, siret, addressLine1, postalCode, city, null); }
    public static Map<String, Object> recipient(String name, String siret, String addressLine1, String postalCode, String city, Map<String, Object> options) {
        if (options == null) options = new LinkedHashMap<>();
        String siren = options.containsKey("siren") ? (String)options.get("siren") : (siret.length() == 14 ? siret.substring(0, 9) : null);
        String countryCode = options.containsKey("countryCode") ? (String)options.get("countryCode") : "FR";
        String addressLine2 = options.containsKey("addressLine2") ? (String)options.get("addressLine2") : null;
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("name", name); result.put("siret", siret);
        result.put("electronicAddress", electronicAddress(siret, "0225"));
        result.put("postalAddress", postalAddress(addressLine1, postalCode, city, countryCode, addressLine2, null));
        if (siren != null) result.put("siren", siren);
        if (options.containsKey("executingServiceCode")) result.put("executingServiceCode", options.get("executingServiceCode"));
        return result;
    }

    /**
     * Creates a beneficiary (factor) for factoring.
     *
     * The beneficiary (BG-10 / PayeeTradeParty) is used when payment
     * must be made to a third party different from the supplier, typically
     * a factor (factoring company).
     *
     * For factored invoices, you also need to:
     * - Use a factored document type (393, 396, 501, 502, 472, 473)
     * - Add an ACC note with the subrogation mention
     * - The beneficiary's IBAN will be used for payment
     *
     * @param name Factor's business name (BT-59)
     * @return Dict ready to be used in a factored invoice
     */
    public static Map<String, Object> beneficiary(String name) { return beneficiary(name, null); }

    /**
     * Creates a beneficiary (factor) for factoring.
     *
     * @param name Factor's business name (BT-59)
     * @param options Map with: siret (BT-60), siren (BT-61), iban, bic
     * @return Dict ready to be used in a factored invoice
     */
    public static Map<String, Object> beneficiary(String name, Map<String, Object> options) {
        if (options == null) options = new LinkedHashMap<>();
        String siret = options.containsKey("siret") ? (String)options.get("siret") : null;
        // Auto-compute SIREN from SIRET
        String siren = options.containsKey("siren") ? (String)options.get("siren")
            : (siret != null && siret.length() == 14 ? siret.substring(0, 9) : null);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("name", name);
        if (siret != null) result.put("siret", siret);
        if (siren != null) result.put("siren", siren);
        if (options.containsKey("iban")) result.put("iban", options.get("iban"));
        if (options.containsKey("bic")) result.put("bic", options.get("bic"));
        return result;
    }
}

// =============================================================================
// Main client
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
    // Shorter aliases
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
            Request request = new Request.Builder().url(apiUrl + "/api/v1/processing/tasks/" + taskId + "/status")
                .addHeader("Authorization", "Bearer " + accessToken).get().build();
            try (Response response = httpClient.newCall(request).execute()) {
                if (response.code() == 401) { resetAuth(); continue; }
                Type type = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> data = gson.fromJson(response.body().string(), type);
                String status = (String) data.get("status");
                if ("SUCCESS".equals(status)) return data.get("result") instanceof Map ? (Map<String, Object>) data.get("result") : new HashMap<>();
                if ("FAILURE".equals(status)) {
                    // Format AFNOR: errorMessage, details
                    Map<String, Object> res = (Map<String, Object>) data.get("result");
                    List<ValidationErrorDetail> errors = new ArrayList<>();
                    if (res != null && res.containsKey("details"))
                        for (Map<String, Object> e : (List<Map<String, Object>>) res.get("details")) errors.add(ValidationErrorDetail.fromMap(e));
                    throw new FactPulseValidationException("Task " + taskId + " failed: " + (res != null ? res.get("errorMessage") : "?"), errors);
                }
                Thread.sleep((long) currentInterval); currentInterval = Math.min(currentInterval * 1.5, 10000);
            } catch (IOException | InterruptedException e) { throw new FactPulseValidationException("Error: " + e.getMessage()); }
        }
    }

    public byte[] generateFacturx(Object invoiceData, String pdfPath, String profile, String outputFormat, boolean sync, Long timeout) throws FactPulseException {
        String jsonData;
        if (invoiceData instanceof String) jsonData = (String) invoiceData;
        else if (invoiceData instanceof Map) jsonData = gson.toJson(invoiceData);
        else jsonData = gson.toJson(invoiceData);

        if (profile == null) profile = "EN16931";
        if (outputFormat == null) outputFormat = "pdf";

        java.io.File pdfFile = new java.io.File(pdfPath);
        for (int attempt = 0; attempt <= maxRetries; attempt++) {
            ensureAuthenticated();
            try {
                RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("invoice_data", null, RequestBody.create(jsonData, MediaType.parse("application/json")))
                    .addFormDataPart("profile", profile)
                    .addFormDataPart("output_format", outputFormat)
                    .addFormDataPart("source_pdf", pdfFile.getName(), RequestBody.create(pdfFile, MediaType.parse("application/pdf")))
                    .build();
                Request request = new Request.Builder().url(apiUrl + "/api/v1/processing/generate-invoice")
                    .addHeader("Authorization", "Bearer " + accessToken).post(body).build();
                try (Response response = httpClient.newCall(request).execute()) {
                    if (response.code() == 401 && attempt < maxRetries) { resetAuth(); continue; }
                    String responseBody = response.body() != null ? response.body().string() : "";
                    if (!response.isSuccessful()) {
                        // Extract error details from response body
                        String errorMsg = "API Error (" + response.code() + ")";
                        List<ValidationErrorDetail> errors = new ArrayList<>();
                        try {
                            Type mapType = new TypeToken<Map<String, Object>>(){}.getType();
                            Map<String, Object> errorData = gson.fromJson(responseBody, mapType);
                            if (errorData != null) {
                                // Format FastAPI/Pydantic: {"detail": [{"loc": [...], "msg": "...", "type": "..."}]}
                                Object detail = errorData.get("detail");
                                if (detail instanceof List) {
                                    errorMsg = "Validation error";
                                    for (Object item : (List<?>) detail) {
                                        if (item instanceof Map) {
                                            Map<?, ?> errItem = (Map<?, ?>) item;
                                            String loc = "";
                                            if (errItem.get("loc") instanceof List) {
                                                List<?> locList = (List<?>) errItem.get("loc");
                                                loc = locList.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining(" -> "));
                                            }
                                            String reason = errItem.get("msg") != null ? String.valueOf(errItem.get("msg")) : "";
                                            String code = errItem.get("type") != null ? String.valueOf(errItem.get("type")) : null;
                                            errors.add(new ValidationErrorDetail("ERROR", loc, reason, "validation", code));
                                        }
                                    }
                                } else if (detail instanceof String) {
                                    errorMsg = (String) detail;
                                } else if (errorData.get("errorMessage") != null) {
                                    errorMsg = String.valueOf(errorData.get("errorMessage"));
                                }
                            }
                        } catch (Exception parseErr) { /* ignore parsing errors */ }
                        System.err.println("API Error " + response.code() + ": " + responseBody);
                        throw new FactPulseValidationException(errorMsg, errors);
                    }
                    Type type = new TypeToken<Map<String, Object>>(){}.getType();
                    Map<String, Object> result = gson.fromJson(responseBody, type);
                    String taskId = (String) result.get("taskId");
                    if (taskId == null) {
                        System.err.println("DEBUG: Response body: " + responseBody);
                        System.err.println("DEBUG: Parsed result: " + result);
                        throw new FactPulseValidationException("No task ID");
                    }
                    if (!sync) return taskId.getBytes();
                    Map<String, Object> pollResult = pollTask(taskId, timeout, null);

                    // Check for business error (task succeeded but business result is ERROR)
                    if ("ERROR".equals(pollResult.get("status"))) {
                        String errorMsg = pollResult.containsKey("errorMessage") ? (String) pollResult.get("errorMessage") : "Business error";
                        List<ValidationErrorDetail> errors = new ArrayList<>();
                        if (pollResult.containsKey("details") && pollResult.get("details") instanceof List) {
                            for (Object d : (List<?>) pollResult.get("details")) {
                                if (d instanceof Map) {
                                    Map<?, ?> det = (Map<?, ?>) d;
                                    errors.add(new ValidationErrorDetail(
                                        det.containsKey("level") ? det.get("level").toString() : "ERROR",
                                        det.containsKey("item") ? det.get("item").toString() : "",
                                        det.containsKey("reason") ? det.get("reason").toString() : "",
                                        det.containsKey("source") ? det.get("source").toString() : null,
                                        det.containsKey("code") ? det.get("code").toString() : null
                                    ));
                                }
                            }
                        }
                        throw new FactPulseValidationException(errorMsg, errors);
                    }

                    if (pollResult.containsKey("content_b64")) return Base64.getDecoder().decode((String) pollResult.get("content_b64"));
                    throw new FactPulseValidationException("No content in result");
                }
            } catch (IOException e) {
                if (attempt < maxRetries) continue;
                throw new FactPulseValidationException("Network error: " + e.getMessage());
            }
        }
        throw new FactPulseValidationException("Failed after retries");
    }
    public byte[] generateFacturx(Object invoiceData, String pdfPath) throws FactPulseException { return generateFacturx(invoiceData, pdfPath, "EN16931", "pdf", true, null); }

    public static String formatAmount(Object m) {
        if (m == null) return "0.00";
        if (m instanceof BigDecimal) return ((BigDecimal) m).setScale(2, RoundingMode.HALF_UP).toPlainString();
        if (m instanceof Number) return String.format("%.2f", ((Number) m).doubleValue());
        if (m instanceof String) return (String) m;
        return "0.00";
    }

    // =========================================================================
    // AFNOR PDP - Authentication and internal helpers
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

    public Map<String, Object> submitInvoiceAfnor(String pdfPath, String flowName, Map<String, Object> options) throws FactPulseException {
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
    public Map<String, Object> submitInvoiceAfnor(String pdfPath, String flowName) throws FactPulseException { return submitInvoiceAfnor(pdfPath, flowName, null); }

    public Map<String, Object> searchFlowsAfnor(Map<String, Object> criteria) throws FactPulseException {
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
    public Map<String, Object> searchFlowsAfnor() throws FactPulseException { return searchFlowsAfnor(null); }

    public byte[] downloadFlowAfnor(String flowId) throws FactPulseException {
        Map<String, Object> result = makeAfnorRequest("GET", "/flow/v1/flows/" + flowId, null, null);
        return result.containsKey("_raw") ? (byte[]) result.get("_raw") : new byte[0];
    }

    public Map<String, Object> healthcheckAfnor() throws FactPulseException {
        return makeAfnorRequest("GET", "/flow/v1/healthcheck", null, null);
    }

    // ==================== AFNOR Directory ====================

    /** Gets a facility by SIRET in the AFNOR directory. */
    public Map<String, Object> getSiretAfnor(String siret) throws FactPulseException {
        return makeAfnorRequest("GET", "/directory/v1/siret/code-insee:" + siret, null, null);
    }

    /** Gets a legal unit by SIREN in the AFNOR directory. */
    public Map<String, Object> getSirenAfnor(String siren) throws FactPulseException {
        return makeAfnorRequest("GET", "/directory/v1/siren/code-insee:" + siren, null, null);
    }

    /** Searches for legal units (SIREN) in the AFNOR directory. */
    public Map<String, Object> searchSirenAfnor(Map<String, Object> filters, int limit) throws FactPulseException {
        if (limit <= 0) limit = 25;
        if (filters == null) filters = new LinkedHashMap<>();
        Map<String, Object> searchBody = new LinkedHashMap<>();
        searchBody.put("filters", filters);
        searchBody.put("limit", limit);
        return makeAfnorRequest("POST", "/directory/v1/siren/search", searchBody, null);
    }

    /** Searches for routing codes in the AFNOR directory. */
    public Map<String, Object> searchRoutingCodesAfnor(Map<String, Object> filters, int limit) throws FactPulseException {
        if (limit <= 0) limit = 25;
        if (filters == null) filters = new LinkedHashMap<>();
        Map<String, Object> searchBody = new LinkedHashMap<>();
        searchBody.put("filters", filters);
        searchBody.put("limit", limit);
        return makeAfnorRequest("POST", "/directory/v1/routing-code/search", searchBody, null);
    }

    /** Gets a routing code by SIRET and routing identifier. */
    public Map<String, Object> getRoutingCodeAfnor(String siret, String routingIdentifier) throws FactPulseException {
        return makeAfnorRequest("GET", "/directory/v1/routing-code/siret:" + siret + "/code:" + routingIdentifier, null, null);
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

    public Map<String, Object> searchStructureChorus(String structureIdentifier, String companyName, String identifierType, boolean restrictPrivate) throws FactPulseException {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("restreindre_structures_privees", restrictPrivate);
        if (structureIdentifier != null) body.put("identifiant_structure", structureIdentifier);
        if (companyName != null) body.put("raison_sociale_structure", companyName);
        if (identifierType != null) body.put("type_identifiant_structure", identifierType);
        return makeChorusRequest("POST", "/structures/rechercher", body);
    }
    public Map<String, Object> searchStructureChorus(String structureIdentifier) throws FactPulseException { return searchStructureChorus(structureIdentifier, null, "SIRET", true); }

    public Map<String, Object> lookupStructureChorus(int structureIdCpp) throws FactPulseException {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("id_structure_cpp", structureIdCpp);
        return makeChorusRequest("POST", "/structures/consulter", body);
    }

    public Map<String, Object> getChorusIdFromSiret(String siret, String identifierType) throws FactPulseException {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("siret", siret);
        body.put("type_identifiant", identifierType != null ? identifierType : "SIRET");
        return makeChorusRequest("POST", "/structures/obtenir-id-depuis-siret", body);
    }
    public Map<String, Object> getChorusIdFromSiret(String siret) throws FactPulseException { return getChorusIdFromSiret(siret, "SIRET"); }

    public Map<String, Object> listStructureServicesChorus(int structureIdCpp) throws FactPulseException {
        return makeChorusRequest("GET", "/structures/" + structureIdCpp + "/services", null);
    }

    public Map<String, Object> submitInvoiceChorus(Map<String, Object> invoiceData) throws FactPulseException {
        return makeChorusRequest("POST", "/factures/soumettre", invoiceData);
    }

    public Map<String, Object> lookupInvoiceChorus(int invoiceIdCpp) throws FactPulseException {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("identifiant_facture_cpp", invoiceIdCpp);
        return makeChorusRequest("POST", "/factures/consulter", body);
    }

    // =========================================================================
    // Validation
    // =========================================================================

    /**
     * Validates a Factur-X PDF.
     * @param pdfPath Path to the PDF file
     * @param profile Factur-X profile (MINIMUM, BASIC, EN16931, EXTENDED). If null, auto-detected.
     * @param useVerapdf Enable strict PDF/A validation with VeraPDF (default: false)
     */
    public Map<String, Object> validateFacturxPdf(String pdfPath, String profile, boolean useVerapdf) throws FactPulseException {
        ensureAuthenticated();
        try {
            File pdfFile = new File(pdfPath);
            MultipartBody.Builder bodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("pdf_file", pdfFile.getName(), RequestBody.create(pdfFile, MediaType.parse("application/pdf")))
                .addFormDataPart("use_verapdf", String.valueOf(useVerapdf));
            if (profile != null) {
                bodyBuilder.addFormDataPart("profile", profile);
            }
            Request request = new Request.Builder().url(apiUrl + "/api/v1/processing/validate-facturx-pdf")
                .addHeader("Authorization", "Bearer " + accessToken).post(bodyBuilder.build()).build();
            try (Response response = httpClient.newCall(request).execute()) {
                Type type = new TypeToken<Map<String, Object>>(){}.getType();
                return gson.fromJson(response.body().string(), type);
            }
        } catch (IOException e) { throw new FactPulseValidationException("Validation error: " + e.getMessage()); }
    }
    public Map<String, Object> validateFacturxPdf(String pdfPath, String profile) throws FactPulseException { return validateFacturxPdf(pdfPath, profile, false); }
    public Map<String, Object> validateFacturxPdf(String pdfPath) throws FactPulseException { return validateFacturxPdf(pdfPath, null, false); }

    public Map<String, Object> validateFacturxXml(String xmlContent, String profile) throws FactPulseException {
        ensureAuthenticated();
        try {
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("xml_file", "invoice.xml", RequestBody.create(xmlContent, MediaType.parse("application/xml")))
                .addFormDataPart("profile", profile != null ? profile : "EN16931")
                .build();
            Request request = new Request.Builder().url(apiUrl + "/api/v1/processing/validate-xml")
                .addHeader("Authorization", "Bearer " + accessToken).post(body).build();
            try (Response response = httpClient.newCall(request).execute()) {
                Type type = new TypeToken<Map<String, Object>>(){}.getType();
                return gson.fromJson(response.body().string(), type);
            }
        } catch (IOException e) { throw new FactPulseValidationException("Validation error: " + e.getMessage()); }
    }
    public Map<String, Object> validateFacturxXml(String xmlContent) throws FactPulseException { return validateFacturxXml(xmlContent, "EN16931"); }

    public Map<String, Object> validatePdfSignature(String pdfPath) throws FactPulseException {
        ensureAuthenticated();
        try {
            File pdfFile = new File(pdfPath);
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("pdf_file", pdfFile.getName(), RequestBody.create(pdfFile, MediaType.parse("application/pdf")))
                .build();
            Request request = new Request.Builder().url(apiUrl + "/api/v1/processing/validate-pdf-signature")
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

    public byte[] signPdf(String pdfPath, Map<String, Object> options) throws FactPulseException {
        if (options == null) options = new HashMap<>();
        ensureAuthenticated();
        try {
            File pdfFile = new File(pdfPath);
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("pdf_file", pdfFile.getName(), RequestBody.create(pdfFile, MediaType.parse("application/pdf")));
            if (options.containsKey("reason")) builder.addFormDataPart("reason", (String) options.get("reason"));
            if (options.containsKey("location")) builder.addFormDataPart("location", (String) options.get("location"));
            if (options.containsKey("contact")) builder.addFormDataPart("contact", (String) options.get("contact"));
            builder.addFormDataPart("use_pades_lt", Boolean.TRUE.equals(options.get("usePadesLt")) ? "true" : "false");
            builder.addFormDataPart("use_timestamp", options.containsKey("useTimestamp") ? (Boolean.TRUE.equals(options.get("useTimestamp")) ? "true" : "false") : "true");

            Request request = new Request.Builder().url(apiUrl + "/api/v1/processing/sign-pdf")
                .addHeader("Authorization", "Bearer " + accessToken).post(builder.build()).build();
            try (Response response = httpClient.newCall(request).execute()) {
                Type type = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> result = gson.fromJson(response.body().string(), type);
                String base64Pdf = (String) result.get("signed_pdf_base64");
                if (base64Pdf == null) throw new FactPulseValidationException("Invalid signature response");
                return Base64.getDecoder().decode(base64Pdf);
            }
        } catch (IOException e) { throw new FactPulseValidationException("Signature error: " + e.getMessage()); }
    }
    public byte[] signPdf(String pdfPath) throws FactPulseException { return signPdf(pdfPath, null); }

    public Map<String, Object> generateTestCertificate(Map<String, Object> options) throws FactPulseException {
        if (options == null) options = new HashMap<>();
        ensureAuthenticated();
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("cn", options.getOrDefault("cn", "Test Organisation"));
        body.put("organisation", options.getOrDefault("organisation", "Test Organisation"));
        body.put("email", options.getOrDefault("email", "test@example.com"));
        body.put("validity_days", options.getOrDefault("validityDays", 365));
        body.put("key_size", options.getOrDefault("keySize", 2048));

        Request request = new Request.Builder().url(apiUrl + "/api/v1/processing/generate-test-certificate")
            .addHeader("Authorization", "Bearer " + accessToken)
            .post(RequestBody.create(gson.toJson(body), JSON)).build();
        try (Response response = httpClient.newCall(request).execute()) {
            Type type = new TypeToken<Map<String, Object>>(){}.getType();
            return gson.fromJson(response.body().string(), type);
        } catch (IOException e) { throw new FactPulseValidationException("Error: " + e.getMessage()); }
    }
    public Map<String, Object> generateTestCertificate() throws FactPulseException { return generateTestCertificate(null); }

    // =========================================================================
    // Complete workflow
    // =========================================================================

    @SuppressWarnings("unchecked")
    public Map<String, Object> generateCompleteFacturx(Map<String, Object> invoice, String pdfSourcePath, Map<String, Object> options) throws FactPulseException {
        if (options == null) options = new HashMap<>();
        String profile = (String) options.getOrDefault("profile", "EN16931");
        boolean validate = (boolean) options.getOrDefault("validate", true);
        boolean sign = (boolean) options.getOrDefault("sign", false);
        boolean submitAfnor = (boolean) options.getOrDefault("submitAfnor", false);
        Long timeout = options.containsKey("timeout") ? ((Number) options.get("timeout")).longValue() : 120000L;

        Map<String, Object> result = new LinkedHashMap<>();

        // 1. Generation
        byte[] pdfBytes = generateFacturx(invoice, pdfSourcePath, profile, "pdf", true, timeout);
        result.put("pdfBytes", pdfBytes);

        // Create a temporary file
        File tempFile = null;
        try {
            tempFile = File.createTempFile("facturx_", ".pdf");
            Files.write(tempFile.toPath(), pdfBytes);

            // 2. Validation
            if (validate) {
                Map<String, Object> validation = validateFacturxPdf(tempFile.getAbsolutePath(), profile);
                result.put("validation", validation);
                if (!Boolean.TRUE.equals(validation.get("isCompliant"))) {
                    if (options.containsKey("outputPath")) {
                        Files.write(new File((String) options.get("outputPath")).toPath(), pdfBytes);
                        result.put("pdfPath", options.get("outputPath"));
                    }
                    return result;
                }
            }

            // 3. Signature
            if (sign) {
                pdfBytes = signPdf(tempFile.getAbsolutePath(), options);
                result.put("pdfBytes", pdfBytes);
                result.put("signature", Map.of("signed", true));
                Files.write(tempFile.toPath(), pdfBytes);
            }

            // 4. AFNOR submission
            if (submitAfnor) {
                String invoiceNumber = (String) invoice.getOrDefault("invoiceNumber", invoice.getOrDefault("invoice_number", "INVOICE"));
                String flowName = (String) options.getOrDefault("afnorFlowName", "Invoice " + invoiceNumber);
                String trackingId = (String) options.getOrDefault("afnorTrackingId", invoiceNumber);
                Map<String, Object> afnorOpts = new HashMap<>();
                afnorOpts.put("trackingId", trackingId);
                Map<String, Object> afnorResult = submitInvoiceAfnor(tempFile.getAbsolutePath(), flowName, afnorOpts);
                result.put("afnor", afnorResult);
            }

            // Final save
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

    // Utilities
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
