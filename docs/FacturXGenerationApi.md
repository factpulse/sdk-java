# FacturXGenerationApi

All URIs are relative to *https://factpulse.fr*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**generateInvoiceApiV1ProcessingGenerateInvoicePost**](FacturXGenerationApi.md#generateInvoiceApiV1ProcessingGenerateInvoicePost) | **POST** /api/v1/processing/generate-invoice | Generate a Factur-X invoice |
| [**submitCompleteInvoiceApiV1ProcessingInvoicesSubmitCompletePost**](FacturXGenerationApi.md#submitCompleteInvoiceApiV1ProcessingInvoicesSubmitCompletePost) | **POST** /api/v1/processing/invoices/submit-complete | Submit a complete invoice (generation + signature + submission) |
| [**submitCompleteInvoiceAsyncApiV1ProcessingInvoicesSubmitCompleteAsyncPost**](FacturXGenerationApi.md#submitCompleteInvoiceAsyncApiV1ProcessingInvoicesSubmitCompleteAsyncPost) | **POST** /api/v1/processing/invoices/submit-complete-async | Submit a complete invoice (asynchronous with Celery) |


<a id="generateInvoiceApiV1ProcessingGenerateInvoicePost"></a>
# **generateInvoiceApiV1ProcessingGenerateInvoicePost**
> TaskResponse generateInvoiceApiV1ProcessingGenerateInvoicePost(invoiceData, profile, outputFormat, autoEnrich, sourcePdf, callbackUrl, webhookMode, skipBrFr)

Generate a Factur-X invoice

Generates an electronic invoice in Factur-X format compliant with European standards.  ## Applied Standards  - **Factur-X** (France): FNFE-MPE standard (Forum National de la Facture Électronique) - **ZUGFeRD** (Germany): German format compatible with Factur-X - **EN 16931**: European semantic standard for electronic invoicing - **ISO 19005-3** (PDF/A-3): Long-term electronic archiving - **Cross Industry Invoice (CII)**: UN/CEFACT XML syntax  ## 🆕 New: Simplified format with auto-enrichment (P0.1)  You can now create an invoice by providing only: - An invoice number - A supplier SIRET + **IBAN** (required) - A recipient SIRET - Invoice lines (description, quantity, net price)  **Simplified format example**: &#x60;&#x60;&#x60;json {   \&quot;number\&quot;: \&quot;FACT-2025-001\&quot;,   \&quot;supplier\&quot;: {     \&quot;siret\&quot;: \&quot;92019522900017\&quot;,     \&quot;iban\&quot;: \&quot;FR7630001007941234567890185\&quot;   },   \&quot;recipient\&quot;: {\&quot;siret\&quot;: \&quot;35600000000048\&quot;},   \&quot;lines\&quot;: [     {\&quot;description\&quot;: \&quot;Service\&quot;, \&quot;quantity\&quot;: 10, \&quot;unitPrice\&quot;: 100.00, \&quot;vatRate\&quot;: 20.0}   ] } &#x60;&#x60;&#x60;  **⚠️ Required fields (simplified format)**: - &#x60;number&#x60;: Unique invoice number - &#x60;supplier.siret&#x60;: Supplier&#39;s SIRET (14 digits) - &#x60;supplier.iban&#x60;: Bank account IBAN (no public API to retrieve it) - &#x60;recipient.siret&#x60;: Recipient&#39;s SIRET - &#x60;lines[]&#x60;: At least one invoice line  **What happens automatically with &#x60;auto_enrich&#x3D;True&#x60;**: - ✅ Name enrichment from Chorus Pro API - ✅ Address enrichment from Business Search API (free, public) - ✅ Automatic intra-EU VAT calculation (FR + key + SIREN) - ✅ Chorus Pro ID retrieval for electronic invoicing - ✅ Net/VAT/Gross totals calculation - ✅ Date generation (today + 30-day due date) - ✅ Multi-rate VAT handling  **Supported identifiers**: - SIRET (14 digits): Specific establishment ⭐ Recommended - SIREN (9 digits): Company (auto-selection of headquarters) - Special types: UE_HORS_FRANCE, RIDET, TAHITI, etc.  ## Checks performed during generation  ### 1. Data validation (Pydantic) - Data types (amounts as Decimal, ISO 8601 dates) - Formats (14-digit SIRET, 9-digit SIREN, IBAN) - Required fields per profile - Amount consistency (Net + VAT &#x3D; Gross)  ### 2. CII-compliant XML generation - Serialization according to Cross Industry Invoice XSD schema - Correct UN/CEFACT namespaces - Hierarchical structure respected - UTF-8 encoding without BOM  ### 3. Schematron validation - Business rules for selected profile (MINIMUM, BASIC, EN16931, EXTENDED) - Element cardinality (required, optional, repeatable) - Calculation rules (totals, VAT, discounts) - European EN 16931 compliance  ### 4. PDF/A-3 conversion (if output_format&#x3D;&#39;pdf&#39;) - Source PDF conversion to PDF/A-3 via Ghostscript - Factur-X XML embedding in PDF - Compliant XMP metadata - ICC sRGB color profile - Removal of forbidden elements (JavaScript, forms)  ## How it works  1. **Submission**: Invoice is queued in Celery for asynchronous processing 2. **Immediate return**: You receive a &#x60;task_id&#x60; (HTTP 202 Accepted) 3. **Tracking**: Use the &#x60;/tasks/{task_id}/status&#x60; endpoint to track progress  ## Webhook notification (recommended)  Instead of polling, you can receive a webhook notification when the task completes:  &#x60;&#x60;&#x60; callback_url&#x3D;https://your-server.com/webhook &#x60;&#x60;&#x60;  The webhook will POST a JSON payload with: - &#x60;event_type&#x60;: &#x60;generation.completed&#x60; or &#x60;generation.failed&#x60; - &#x60;data.task_id&#x60;: The Celery task ID - &#x60;data.content_b64&#x60; or &#x60;data.xml_content&#x60;: The generated content - &#x60;X-Webhook-Signature&#x60; header for HMAC verification  See &#x60;/docs/WEBHOOKS.md&#x60; for full documentation.  ## Output formats  - **xml**: Generates only Factur-X XML (recommended for testing) - **pdf**: Generates PDF/A-3 with embedded XML (requires &#x60;source_pdf&#x60;)  ## Factur-X profiles  - **MINIMUM**: Minimal data (simplified invoice) - **BASIC**: Basic information (SMEs) - **EN16931**: European standard (recommended, compliant with directive 2014/55/EU) - **EXTENDED**: All available data (large accounts)  ## What you get  After successful processing (status &#x60;completed&#x60;): - **XML only**: Base64-encoded Factur-X compliant XML file - **PDF/A-3**: PDF with embedded XML, ready for sending/archiving - **Metadata**: Profile, Factur-X version, file size - **Validation**: Schematron compliance confirmation  ## Validation  Data is automatically validated according to detected format. On error, a 422 status is returned with invalid field details.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FacturXGenerationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    FacturXGenerationApi apiInstance = new FacturXGenerationApi(defaultClient);
    String invoiceData = "invoiceData_example"; // String | Invoice data in JSON format.              Two formats accepted:             1. **Classic format**: Complete FacturXInvoice structure (all fields)             2. **Simplified format** (🆕 P0.1): Minimal structure with auto-enrichment              Format is detected automatically!             
    APIProfile profile = APIProfile.fromValue("MINIMUM"); // APIProfile | Factur-X profile: MINIMUM, BASIC, EN16931 or EXTENDED.
    OutputFormat outputFormat = OutputFormat.fromValue("xml"); // OutputFormat | Output format: 'xml' (XML only) or 'pdf' (Factur-X PDF with embedded XML).
    Boolean autoEnrich = true; // Boolean | 🆕 Enable auto-enrichment from SIRET/SIREN (simplified format only)
    File sourcePdf = new File("/path/to/file"); // File | 
    String callbackUrl = "callbackUrl_example"; // String | 
    String webhookMode = "inline"; // String | Webhook content delivery: 'inline' (base64 in payload) or 'download_url' (temporary URL, 1h TTL)
    Boolean skipBrFr = true; // Boolean | 
    try {
      TaskResponse result = apiInstance.generateInvoiceApiV1ProcessingGenerateInvoicePost(invoiceData, profile, outputFormat, autoEnrich, sourcePdf, callbackUrl, webhookMode, skipBrFr);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FacturXGenerationApi#generateInvoiceApiV1ProcessingGenerateInvoicePost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **invoiceData** | **String**| Invoice data in JSON format.              Two formats accepted:             1. **Classic format**: Complete FacturXInvoice structure (all fields)             2. **Simplified format** (🆕 P0.1): Minimal structure with auto-enrichment              Format is detected automatically!              | |
| **profile** | [**APIProfile**](APIProfile.md)| Factur-X profile: MINIMUM, BASIC, EN16931 or EXTENDED. | [optional] [default to EXTENDED] [enum: MINIMUM, BASICWL, BASIC, EN16931, EXTENDED] |
| **outputFormat** | [**OutputFormat**](OutputFormat.md)| Output format: &#39;xml&#39; (XML only) or &#39;pdf&#39; (Factur-X PDF with embedded XML). | [optional] [default to xml] [enum: xml, pdf] |
| **autoEnrich** | **Boolean**| 🆕 Enable auto-enrichment from SIRET/SIREN (simplified format only) | [optional] [default to true] |
| **sourcePdf** | **File**|  | [optional] |
| **callbackUrl** | **String**|  | [optional] |
| **webhookMode** | **String**| Webhook content delivery: &#39;inline&#39; (base64 in payload) or &#39;download_url&#39; (temporary URL, 1h TTL) | [optional] [default to inline] |
| **skipBrFr** | **Boolean**|  | [optional] |

### Return type

[**TaskResponse**](TaskResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **202** | Successful Response |  -  |
| **400** | Invalid invoice data or missing PDF file |  -  |
| **422** | Invoice data validation error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="submitCompleteInvoiceApiV1ProcessingInvoicesSubmitCompletePost"></a>
# **submitCompleteInvoiceApiV1ProcessingInvoicesSubmitCompletePost**
> SubmitCompleteInvoiceResponse submitCompleteInvoiceApiV1ProcessingInvoicesSubmitCompletePost(submitCompleteInvoiceRequest)

Submit a complete invoice (generation + signature + submission)

Unified endpoint to submit a complete invoice to different destinations.     **Facture prête pour Flux 2** - Génère une facture Factur-X complète avec signature optionnelle et soumission vers Chorus Pro ou PDP AFNOR.      **Automated workflow:**     1. **Auto-enrichment** (optional): retrieves data via public APIs and Chorus Pro/AFNOR     2. **Factur-X PDF generation**: creates a PDF/A-3 with embedded XML     3. **Electronic signature** (optional): signs the PDF with a certificate     4. **Submission**: sends to the chosen destination (Chorus Pro or AFNOR PDP)      **Supported destinations:**     - **Chorus Pro**: French B2G platform (invoices to public sector)     - **AFNOR PDP**: Partner Dematerialization Platforms      **Destination credentials - 2 modes available:**      **Mode 1 - Retrieval via JWT (recommended):**     - Credentials are retrieved automatically via the JWT &#x60;client_uid&#x60;     - Do not provide the &#x60;credentials&#x60; field in &#x60;destination&#x60;     - Zero-trust architecture: no secrets in the payload     - Example: &#x60;\&quot;destination\&quot;: {\&quot;type\&quot;: \&quot;chorus_pro\&quot;}&#x60;      **Mode 2 - Credentials in the payload:**     - Provide credentials directly in the payload     - Useful for tests or third-party integrations     - Example: &#x60;\&quot;destination\&quot;: {\&quot;type\&quot;: \&quot;chorus_pro\&quot;, \&quot;credentials\&quot;: {...}}&#x60;       **Electronic signature (optional) - 2 modes available:**      **Mode 1 - Stored certificate (recommended):**     - Certificate is retrieved automatically via the JWT &#x60;client_uid&#x60;     - No key to provide in the payload     - PAdES-B-LT signature with timestamp (eIDAS compliant)     - Example: &#x60;\&quot;signature\&quot;: {\&quot;reason\&quot;: \&quot;Factur-X compliance\&quot;}&#x60;      **Mode 2 - Keys in the payload (for tests):**     - Provide &#x60;key_pem&#x60; and &#x60;cert_pem&#x60; directly     - PEM format accepted: raw or base64     - Useful for tests or special cases without stored certificate     - Example: &#x60;\&quot;signature\&quot;: {\&quot;key_pem\&quot;: \&quot;-----BEGIN...\&quot;, \&quot;cert_pem\&quot;: \&quot;-----BEGIN...\&quot;}&#x60;      If &#x60;key_pem&#x60; and &#x60;cert_pem&#x60; are provided → Mode 2     Otherwise → Mode 1 (certificate retrieved via &#x60;client_uid&#x60;)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FacturXGenerationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    FacturXGenerationApi apiInstance = new FacturXGenerationApi(defaultClient);
    SubmitCompleteInvoiceRequest submitCompleteInvoiceRequest = new SubmitCompleteInvoiceRequest(); // SubmitCompleteInvoiceRequest | 
    try {
      SubmitCompleteInvoiceResponse result = apiInstance.submitCompleteInvoiceApiV1ProcessingInvoicesSubmitCompletePost(submitCompleteInvoiceRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FacturXGenerationApi#submitCompleteInvoiceApiV1ProcessingInvoicesSubmitCompletePost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **submitCompleteInvoiceRequest** | [**SubmitCompleteInvoiceRequest**](SubmitCompleteInvoiceRequest.md)|  | |

### Return type

[**SubmitCompleteInvoiceResponse**](SubmitCompleteInvoiceResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **422** | Validation Error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="submitCompleteInvoiceAsyncApiV1ProcessingInvoicesSubmitCompleteAsyncPost"></a>
# **submitCompleteInvoiceAsyncApiV1ProcessingInvoicesSubmitCompleteAsyncPost**
> TaskResponse submitCompleteInvoiceAsyncApiV1ProcessingInvoicesSubmitCompleteAsyncPost(submitCompleteInvoiceRequest, callbackUrl, webhookMode)

Submit a complete invoice (asynchronous with Celery)

Asynchronous version of the &#x60;/invoices/submit-complete&#x60; endpoint using Celery for background processing.     **Facture prête pour Flux 2** - Génère une facture Factur-X complète de manière asynchrone.      **Automated workflow (same as synchronous version):**     1. **Auto-enrichment** (optional): retrieves data via public APIs and Chorus Pro/AFNOR     2. **Factur-X PDF generation**: creates a PDF/A-3 with embedded XML     3. **Electronic signature** (optional): signs the PDF with a certificate     4. **Submission**: sends to the chosen destination (Chorus Pro or AFNOR PDP)      **Supported destinations:**     - **Chorus Pro**: French B2G platform (invoices to public sector)     - **AFNOR PDP**: Partner Dematerialization Platforms      **Differences with synchronous version:**     - ✅ **Non-blocking**: Returns immediately with a &#x60;task_id&#x60; (HTTP 202 Accepted)     - ✅ **Background processing**: Invoice is processed by a Celery worker     - ✅ **Progress tracking**: Use &#x60;/tasks/{task_id}/status&#x60; to track status     - ✅ **Ideal for high volumes**: Allows processing many invoices in parallel      **How to use:**     1. **Submission**: Call this endpoint with your invoice data     2. **Immediate return**: You receive a &#x60;task_id&#x60; (e.g., \&quot;abc123-def456\&quot;)     3. **Tracking**: Call &#x60;/tasks/{task_id}/status&#x60; to check progress     4. **Result**: When &#x60;status &#x3D; \&quot;SUCCESS\&quot;&#x60;, the &#x60;result&#x60; field contains the complete response      **Webhook notification (recommended):**      Instead of polling, add &#x60;?callback_url&#x3D;https://your-server.com/webhook&#x60; to receive automatic notification:     - &#x60;event_type&#x60;: &#x60;submission.completed&#x60;, &#x60;submission.failed&#x60;, or &#x60;submission.partial&#x60;     - &#x60;data.submission_result&#x60;: Complete submission result     - &#x60;X-Webhook-Signature&#x60; header for HMAC verification      **Credentials and signature**: Same modes as the synchronous version (JWT or payload).

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FacturXGenerationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    FacturXGenerationApi apiInstance = new FacturXGenerationApi(defaultClient);
    SubmitCompleteInvoiceRequest submitCompleteInvoiceRequest = new SubmitCompleteInvoiceRequest(); // SubmitCompleteInvoiceRequest | 
    String callbackUrl = "callbackUrl_example"; // String | Webhook URL for async notification when submission completes.
    String webhookMode = "inline"; // String | Webhook content delivery: 'inline' (base64 in payload) or 'download_url' (temporary URL, 1h TTL)
    try {
      TaskResponse result = apiInstance.submitCompleteInvoiceAsyncApiV1ProcessingInvoicesSubmitCompleteAsyncPost(submitCompleteInvoiceRequest, callbackUrl, webhookMode);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FacturXGenerationApi#submitCompleteInvoiceAsyncApiV1ProcessingInvoicesSubmitCompleteAsyncPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **submitCompleteInvoiceRequest** | [**SubmitCompleteInvoiceRequest**](SubmitCompleteInvoiceRequest.md)|  | |
| **callbackUrl** | **String**| Webhook URL for async notification when submission completes. | [optional] |
| **webhookMode** | **String**| Webhook content delivery: &#39;inline&#39; (base64 in payload) or &#39;download_url&#39; (temporary URL, 1h TTL) | [optional] [default to inline] |

### Return type

[**TaskResponse**](TaskResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **202** | Successful Response |  -  |
| **422** | Validation Error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

