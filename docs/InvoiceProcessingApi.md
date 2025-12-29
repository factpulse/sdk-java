# InvoiceProcessingApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**generateInvoiceApiV1ProcessingGenerateInvoicePost**](InvoiceProcessingApi.md#generateInvoiceApiV1ProcessingGenerateInvoicePost) | **POST** /api/v1/processing/generate-invoice | Generate a Factur-X invoice |
| [**generateTestCertificateApiV1ProcessingGenerateTestCertificatePost**](InvoiceProcessingApi.md#generateTestCertificateApiV1ProcessingGenerateTestCertificatePost) | **POST** /api/v1/processing/generate-test-certificate | Generate a self-signed X.509 test certificate |
| [**getTaskStatusApiV1ProcessingTasksTaskIdStatusGet**](InvoiceProcessingApi.md#getTaskStatusApiV1ProcessingTasksTaskIdStatusGet) | **GET** /api/v1/processing/tasks/{task_id}/status | Get task generation status |
| [**signPdfApiV1ProcessingSignPdfPost**](InvoiceProcessingApi.md#signPdfApiV1ProcessingSignPdfPost) | **POST** /api/v1/processing/sign-pdf | Sign a PDF with client&#39;s certificate (PAdES-B-LT) |
| [**signPdfAsyncApiV1ProcessingSignPdfAsyncPost**](InvoiceProcessingApi.md#signPdfAsyncApiV1ProcessingSignPdfAsyncPost) | **POST** /api/v1/processing/sign-pdf-async | Sign a PDF asynchronously (Celery) |
| [**submitCompleteInvoiceApiV1ProcessingInvoicesSubmitCompletePost**](InvoiceProcessingApi.md#submitCompleteInvoiceApiV1ProcessingInvoicesSubmitCompletePost) | **POST** /api/v1/processing/invoices/submit-complete | Submit a complete invoice (generation + signature + submission) |
| [**submitCompleteInvoiceAsyncApiV1ProcessingInvoicesSubmitCompleteAsyncPost**](InvoiceProcessingApi.md#submitCompleteInvoiceAsyncApiV1ProcessingInvoicesSubmitCompleteAsyncPost) | **POST** /api/v1/processing/invoices/submit-complete-async | Submit a complete invoice (asynchronous with Celery) |
| [**validateFacturxPdfApiV1ProcessingValidateFacturxPdfPost**](InvoiceProcessingApi.md#validateFacturxPdfApiV1ProcessingValidateFacturxPdfPost) | **POST** /api/v1/processing/validate-facturx-pdf | Validate a complete Factur-X PDF |
| [**validateFacturxPdfAsyncApiV1ProcessingValidateFacturxAsyncPost**](InvoiceProcessingApi.md#validateFacturxPdfAsyncApiV1ProcessingValidateFacturxAsyncPost) | **POST** /api/v1/processing/validate-facturx-async | Validate a Factur-X PDF (asynchronous with polling) |
| [**validatePdfSignatureEndpointApiV1ProcessingValidatePdfSignaturePost**](InvoiceProcessingApi.md#validatePdfSignatureEndpointApiV1ProcessingValidatePdfSignaturePost) | **POST** /api/v1/processing/validate-pdf-signature | Validate electronic signatures of a PDF |
| [**validateXmlApiV1ProcessingValidateXmlPost**](InvoiceProcessingApi.md#validateXmlApiV1ProcessingValidateXmlPost) | **POST** /api/v1/processing/validate-xml | Validate an existing Factur-X XML |


<a id="generateInvoiceApiV1ProcessingGenerateInvoicePost"></a>
# **generateInvoiceApiV1ProcessingGenerateInvoicePost**
> TaskResponse generateInvoiceApiV1ProcessingGenerateInvoicePost(invoiceData, profile, outputFormat, autoEnrich, sourcePdf)

Generate a Factur-X invoice

Generates an electronic invoice in Factur-X format compliant with European standards.  ## Applied Standards  - **Factur-X** (France): FNFE-MPE standard (Forum National de la Facture Électronique) - **ZUGFeRD** (Germany): German format compatible with Factur-X - **EN 16931**: European semantic standard for electronic invoicing - **ISO 19005-3** (PDF/A-3): Long-term electronic archiving - **Cross Industry Invoice (CII)**: UN/CEFACT XML syntax  ## 🆕 New: Simplified format with auto-enrichment (P0.1)  You can now create an invoice by providing only: - An invoice number - A sender SIRET + **IBAN** (required) - A recipient SIRET - Invoice lines (description, quantity, net price)  **Simplified format example**: &#x60;&#x60;&#x60;json {   \&quot;number\&quot;: \&quot;FACT-2025-001\&quot;,   \&quot;sender\&quot;: {     \&quot;siret\&quot;: \&quot;92019522900017\&quot;,     \&quot;iban\&quot;: \&quot;FR7630001007941234567890185\&quot;   },   \&quot;recipient\&quot;: {\&quot;siret\&quot;: \&quot;35600000000048\&quot;},   \&quot;lines\&quot;: [     {\&quot;description\&quot;: \&quot;Service\&quot;, \&quot;quantity\&quot;: 10, \&quot;unitPrice\&quot;: 100.00, \&quot;vatRate\&quot;: 20.0}   ] } &#x60;&#x60;&#x60;  **⚠️ Required fields (simplified format)**: - &#x60;number&#x60;: Unique invoice number - &#x60;sender.siret&#x60;: Sender&#39;s SIRET (14 digits) - &#x60;sender.iban&#x60;: Bank account IBAN (no public API to retrieve it) - &#x60;recipient.siret&#x60;: Recipient&#39;s SIRET - &#x60;lines[]&#x60;: At least one invoice line  **What happens automatically with &#x60;auto_enrich&#x3D;True&#x60;**: - ✅ Name enrichment from Chorus Pro API - ✅ Address enrichment from Business Search API (free, public) - ✅ Automatic intra-EU VAT calculation (FR + key + SIREN) - ✅ Chorus Pro ID retrieval for electronic invoicing - ✅ Net/VAT/Gross totals calculation - ✅ Date generation (today + 30-day due date) - ✅ Multi-rate VAT handling  **Supported identifiers**: - SIRET (14 digits): Specific establishment ⭐ Recommended - SIREN (9 digits): Company (auto-selection of headquarters) - Special types: UE_HORS_FRANCE, RIDET, TAHITI, etc.  ## Checks performed during generation  ### 1. Data validation (Pydantic) - Data types (amounts as Decimal, ISO 8601 dates) - Formats (14-digit SIRET, 9-digit SIREN, IBAN) - Required fields per profile - Amount consistency (Net + VAT &#x3D; Gross)  ### 2. CII-compliant XML generation - Serialization according to Cross Industry Invoice XSD schema - Correct UN/CEFACT namespaces - Hierarchical structure respected - UTF-8 encoding without BOM  ### 3. Schematron validation - Business rules for selected profile (MINIMUM, BASIC, EN16931, EXTENDED) - Element cardinality (required, optional, repeatable) - Calculation rules (totals, VAT, discounts) - European EN 16931 compliance  ### 4. PDF/A-3 conversion (if output_format&#x3D;&#39;pdf&#39;) - Source PDF conversion to PDF/A-3 via Ghostscript - Factur-X XML embedding in PDF - Compliant XMP metadata - ICC sRGB color profile - Removal of forbidden elements (JavaScript, forms)  ## How it works  1. **Submission**: Invoice is queued in Celery for asynchronous processing 2. **Immediate return**: You receive a &#x60;task_id&#x60; (HTTP 202 Accepted) 3. **Tracking**: Use the &#x60;/tasks/{task_id}/status&#x60; endpoint to track progress  ## Output formats  - **xml**: Generates only Factur-X XML (recommended for testing) - **pdf**: Generates PDF/A-3 with embedded XML (requires &#x60;source_pdf&#x60;)  ## Factur-X profiles  - **MINIMUM**: Minimal data (simplified invoice) - **BASIC**: Basic information (SMEs) - **EN16931**: European standard (recommended, compliant with directive 2014/55/EU) - **EXTENDED**: All available data (large accounts)  ## What you get  After successful processing (status &#x60;completed&#x60;): - **XML only**: Base64-encoded Factur-X compliant XML file - **PDF/A-3**: PDF with embedded XML, ready for sending/archiving - **Metadata**: Profile, Factur-X version, file size - **Validation**: Schematron compliance confirmation  ## Validation  Data is automatically validated according to detected format. On error, a 422 status is returned with invalid field details.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InvoiceProcessingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    InvoiceProcessingApi apiInstance = new InvoiceProcessingApi(defaultClient);
    String invoiceData = "invoiceData_example"; // String | Invoice data in JSON format.              Two formats accepted:             1. **Classic format**: Complete FactureFacturX structure (all fields)             2. **Simplified format** (🆕 P0.1): Minimal structure with auto-enrichment              Format is detected automatically!             
    APIProfile profile = APIProfile.fromValue("MINIMUM"); // APIProfile | Factur-X profile: MINIMUM, BASIC, EN16931 or EXTENDED.
    OutputFormat outputFormat = OutputFormat.fromValue("xml"); // OutputFormat | Output format: 'xml' (XML only) or 'pdf' (Factur-X PDF with embedded XML).
    Boolean autoEnrich = true; // Boolean | 🆕 Enable auto-enrichment from SIRET/SIREN (simplified format only)
    File sourcePdf = new File("/path/to/file"); // File | 
    try {
      TaskResponse result = apiInstance.generateInvoiceApiV1ProcessingGenerateInvoicePost(invoiceData, profile, outputFormat, autoEnrich, sourcePdf);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InvoiceProcessingApi#generateInvoiceApiV1ProcessingGenerateInvoicePost");
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
| **invoiceData** | **String**| Invoice data in JSON format.              Two formats accepted:             1. **Classic format**: Complete FactureFacturX structure (all fields)             2. **Simplified format** (🆕 P0.1): Minimal structure with auto-enrichment              Format is detected automatically!              | |
| **profile** | [**APIProfile**](APIProfile.md)| Factur-X profile: MINIMUM, BASIC, EN16931 or EXTENDED. | [optional] [default to EXTENDED] [enum: MINIMUM, BASIC, EN16931, EXTENDED] |
| **outputFormat** | [**OutputFormat**](OutputFormat.md)| Output format: &#39;xml&#39; (XML only) or &#39;pdf&#39; (Factur-X PDF with embedded XML). | [optional] [default to xml] [enum: xml, pdf] |
| **autoEnrich** | **Boolean**| 🆕 Enable auto-enrichment from SIRET/SIREN (simplified format only) | [optional] [default to true] |
| **sourcePdf** | **File**|  | [optional] |

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

<a id="generateTestCertificateApiV1ProcessingGenerateTestCertificatePost"></a>
# **generateTestCertificateApiV1ProcessingGenerateTestCertificatePost**
> GenerateCertificateResponse generateTestCertificateApiV1ProcessingGenerateTestCertificatePost(generateCertificateRequest)

Generate a self-signed X.509 test certificate

Generates a self-signed X.509 certificate for PDF electronic signature testing.      **⚠️ WARNING: TEST certificate only!**      This certificate is:     - ✅ Suitable for testing and development     - ✅ Compatible with PDF signing (PAdES)     - ✅ Compliant with eIDAS **SES** level (Simple Electronic Signature)     - ❌ **NEVER usable in production**     - ❌ **Not recognized** by browsers and PDF readers     - ❌ **No legal value**      ## eIDAS levels      - **SES** (Simple): Self-signed certificate ← Generated by this endpoint     - **AdES** (Advanced): Commercial CA certificate (Let&#39;s Encrypt, etc.)     - **QES** (Qualified): Qualified certificate from QTSP (CertEurope, Universign, etc.)      ## Usage      Once generated, the certificate can be:      1. **Saved in Django** (recommended):        - Django Admin &gt; Signing Certificates        - Upload &#x60;certificate_pem&#x60; and &#x60;private_key_pem&#x60;      2. **Used directly**:        - Sign a PDF with &#x60;/sign-pdf&#x60;        - The certificate will be automatically used      ## Example call      &#x60;&#x60;&#x60;bash     curl -X POST \&quot;https://www.factpulse.fr/api/v1/processing/generate-test-certificate\&quot; \\       -H \&quot;Authorization: Bearer eyJ0eXAi...\&quot; \\       -H \&quot;Content-Type: application/json\&quot; \\       -d &#39;{         \&quot;cn\&quot;: \&quot;Test Client XYZ\&quot;,         \&quot;organization\&quot;: \&quot;Client XYZ Ltd\&quot;,         \&quot;email\&quot;: \&quot;contact@xyz.com\&quot;,         \&quot;validity_days\&quot;: 365       }&#39;     &#x60;&#x60;&#x60;      ## Use cases      - PDF signature testing in development     - Electronic signature POC     - Training and demos     - Automated integration tests      ## Technical compliance      Certificate generated with:     - RSA key 2048 or 4096 bits     - SHA-256 algorithm     - Key Usage extensions: &#x60;digitalSignature&#x60;, &#x60;contentCommitment&#x60; (non-repudiation)     - Extended Key Usage extensions: &#x60;codeSigning&#x60;, &#x60;emailProtection&#x60;     - Validity: 1 day to 10 years (configurable)     - Format: PEM (certificate and key)     - Optional: PKCS#12 (.p12)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InvoiceProcessingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    InvoiceProcessingApi apiInstance = new InvoiceProcessingApi(defaultClient);
    GenerateCertificateRequest generateCertificateRequest = new GenerateCertificateRequest(); // GenerateCertificateRequest | 
    try {
      GenerateCertificateResponse result = apiInstance.generateTestCertificateApiV1ProcessingGenerateTestCertificatePost(generateCertificateRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InvoiceProcessingApi#generateTestCertificateApiV1ProcessingGenerateTestCertificatePost");
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
| **generateCertificateRequest** | [**GenerateCertificateRequest**](GenerateCertificateRequest.md)|  | |

### Return type

[**GenerateCertificateResponse**](GenerateCertificateResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Certificate generated successfully |  -  |
| **400** | Invalid request (incorrect parameters) |  -  |
| **500** | Server error during generation |  -  |
| **422** | Validation Error |  -  |

<a id="getTaskStatusApiV1ProcessingTasksTaskIdStatusGet"></a>
# **getTaskStatusApiV1ProcessingTasksTaskIdStatusGet**
> AsyncTaskStatus getTaskStatusApiV1ProcessingTasksTaskIdStatusGet(taskId)

Get task generation status

Retrieves the progress status of an invoice generation task.  ## Possible states  The &#x60;status&#x60; field uses the &#x60;CeleryStatus&#x60; enum with values: - **PENDING, STARTED, SUCCESS, FAILURE, RETRY**  See the &#x60;CeleryStatus&#x60; schema documentation for details.  ## Business result  When &#x60;status&#x3D;\&quot;SUCCESS\&quot;&#x60;, the &#x60;result&#x60; field contains: - &#x60;status&#x60;: \&quot;SUCCESS\&quot; or \&quot;ERROR\&quot; (business result) - &#x60;content_b64&#x60;: Base64 encoded content (if success) - &#x60;errorCode&#x60;, &#x60;errorMessage&#x60;, &#x60;details&#x60;: AFNOR format (if business error)  ## Usage  Poll this endpoint every 2-3 seconds until &#x60;status&#x60; is &#x60;SUCCESS&#x60; or &#x60;FAILURE&#x60;.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InvoiceProcessingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    InvoiceProcessingApi apiInstance = new InvoiceProcessingApi(defaultClient);
    String taskId = "taskId_example"; // String | 
    try {
      AsyncTaskStatus result = apiInstance.getTaskStatusApiV1ProcessingTasksTaskIdStatusGet(taskId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InvoiceProcessingApi#getTaskStatusApiV1ProcessingTasksTaskIdStatusGet");
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
| **taskId** | **String**|  | |

### Return type

[**AsyncTaskStatus**](AsyncTaskStatus.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Current task state |  -  |
| **422** | Validation Error |  -  |

<a id="signPdfApiV1ProcessingSignPdfPost"></a>
# **signPdfApiV1ProcessingSignPdfPost**
> Object signPdfApiV1ProcessingSignPdfPost(pdfFile, reason, location, contact, fieldName, usePadesLt, useTimestamp)

Sign a PDF with client&#39;s certificate (PAdES-B-LT)

Signs an uploaded PDF with the electronic certificate configured for the client (via client_uid from JWT).      **Supported standards**: PAdES-B-B, PAdES-B-T (timestamping), PAdES-B-LT (long-term archiving).      **eIDAS levels**: SES (self-signed), AdES (commercial CA), QES (PSCO - out of scope).      **Security**: Double authentication X-Internal-Secret + JWT Bearer to retrieve the certificate.      **⚠️ Legal disclaimer**: Generated signatures are electronic seals as defined by     the eIDAS regulation. The level of legal validity depends on the certificate used (SES/AdES/QES).     FactPulse does not provide QES qualified certificates - you must obtain a certificate from     a PSCO (qualified Trust Service Provider) for maximum legal validity.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InvoiceProcessingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    InvoiceProcessingApi apiInstance = new InvoiceProcessingApi(defaultClient);
    File pdfFile = new File("/path/to/file"); // File | PDF file to sign (will be processed and returned signed in base64)
    String reason = "reason_example"; // String | 
    String location = "location_example"; // String | 
    String contact = "contact_example"; // String | 
    String fieldName = "FactPulseSignature"; // String | PDF signature field name
    Boolean usePadesLt = false; // Boolean | Enable PAdES-B-LT (long-term archiving with embedded validation data). REQUIRES a certificate with OCSP/CRL access.
    Boolean useTimestamp = true; // Boolean | Enable RFC 3161 timestamping with FreeTSA (PAdES-B-T)
    try {
      Object result = apiInstance.signPdfApiV1ProcessingSignPdfPost(pdfFile, reason, location, contact, fieldName, usePadesLt, useTimestamp);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InvoiceProcessingApi#signPdfApiV1ProcessingSignPdfPost");
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
| **pdfFile** | **File**| PDF file to sign (will be processed and returned signed in base64) | |
| **reason** | **String**|  | [optional] |
| **location** | **String**|  | [optional] |
| **contact** | **String**|  | [optional] |
| **fieldName** | **String**| PDF signature field name | [optional] [default to FactPulseSignature] |
| **usePadesLt** | **Boolean**| Enable PAdES-B-LT (long-term archiving with embedded validation data). REQUIRES a certificate with OCSP/CRL access. | [optional] [default to false] |
| **useTimestamp** | **Boolean**| Enable RFC 3161 timestamping with FreeTSA (PAdES-B-T) | [optional] [default to true] |

### Return type

**Object**

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | PDF signed successfully |  -  |
| **400** | Invalid or expired certificate |  -  |
| **404** | No certificate configured for this client |  -  |
| **401** | Not authenticated - Missing or invalid JWT token |  -  |
| **503** | Django service unavailable |  -  |
| **422** | Validation Error |  -  |

<a id="signPdfAsyncApiV1ProcessingSignPdfAsyncPost"></a>
# **signPdfAsyncApiV1ProcessingSignPdfAsyncPost**
> Object signPdfAsyncApiV1ProcessingSignPdfAsyncPost(pdfFile, reason, location, contact, fieldName, usePadesLt, useTimestamp)

Sign a PDF asynchronously (Celery)

Signs an uploaded PDF asynchronously via a Celery task.      **Difference with /sign-pdf**:     - &#x60;/sign-pdf&#x60;: Synchronous signature (blocking until completion)     - &#x60;/sign-pdf-async&#x60;: Asynchronous signature (returns immediately with task_id)      **Async advantages**:     - No timeout for large files     - No blocking of FastAPI worker     - Progress tracking via task_id     - Ideal for batch processing      **Supported standards**: PAdES-B-B, PAdES-B-T (timestamping), PAdES-B-LT (long-term archiving).      **⚠️ Legal disclaimer**: Same as /sign-pdf (see that endpoint&#39;s documentation).

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InvoiceProcessingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    InvoiceProcessingApi apiInstance = new InvoiceProcessingApi(defaultClient);
    File pdfFile = new File("/path/to/file"); // File | PDF file to sign (processed asynchronously)
    String reason = "reason_example"; // String | 
    String location = "location_example"; // String | 
    String contact = "contact_example"; // String | 
    String fieldName = "FactPulseSignature"; // String | PDF signature field name
    Boolean usePadesLt = false; // Boolean | Enable PAdES-B-LT (long-term archiving with embedded validation data). REQUIRES a certificate with OCSP/CRL access.
    Boolean useTimestamp = true; // Boolean | Enable RFC 3161 timestamping with FreeTSA (PAdES-B-T)
    try {
      Object result = apiInstance.signPdfAsyncApiV1ProcessingSignPdfAsyncPost(pdfFile, reason, location, contact, fieldName, usePadesLt, useTimestamp);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InvoiceProcessingApi#signPdfAsyncApiV1ProcessingSignPdfAsyncPost");
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
| **pdfFile** | **File**| PDF file to sign (processed asynchronously) | |
| **reason** | **String**|  | [optional] |
| **location** | **String**|  | [optional] |
| **contact** | **String**|  | [optional] |
| **fieldName** | **String**| PDF signature field name | [optional] [default to FactPulseSignature] |
| **usePadesLt** | **Boolean**| Enable PAdES-B-LT (long-term archiving with embedded validation data). REQUIRES a certificate with OCSP/CRL access. | [optional] [default to false] |
| **useTimestamp** | **Boolean**| Enable RFC 3161 timestamping with FreeTSA (PAdES-B-T) | [optional] [default to true] |

### Return type

**Object**

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **202** | Signature task created successfully |  -  |
| **400** | Invalid parameters |  -  |
| **401** | Not authenticated - Missing or invalid JWT token |  -  |
| **422** | Validation Error |  -  |

<a id="submitCompleteInvoiceApiV1ProcessingInvoicesSubmitCompletePost"></a>
# **submitCompleteInvoiceApiV1ProcessingInvoicesSubmitCompletePost**
> SubmitCompleteInvoiceResponse submitCompleteInvoiceApiV1ProcessingInvoicesSubmitCompletePost(submitCompleteInvoiceRequest)

Submit a complete invoice (generation + signature + submission)

Unified endpoint to submit a complete invoice to different destinations.      **Automated workflow:**     1. **Auto-enrichment** (optional): retrieves data via public APIs and Chorus Pro/AFNOR     2. **Factur-X PDF generation**: creates a PDF/A-3 with embedded XML     3. **Electronic signature** (optional): signs the PDF with a certificate     4. **Submission**: sends to the chosen destination (Chorus Pro or AFNOR PDP)      **Supported destinations:**     - **Chorus Pro**: French B2G platform (invoices to public sector)     - **AFNOR PDP**: Partner Dematerialization Platforms      **Destination credentials - 2 modes available:**      **Mode 1 - Retrieval via JWT (recommended):**     - Credentials are retrieved automatically via the JWT &#x60;client_uid&#x60;     - Do not provide the &#x60;credentials&#x60; field in &#x60;destination&#x60;     - Zero-trust architecture: no secrets in the payload     - Example: &#x60;\&quot;destination\&quot;: {\&quot;type\&quot;: \&quot;chorus_pro\&quot;}&#x60;      **Mode 2 - Credentials in the payload:**     - Provide credentials directly in the payload     - Useful for tests or third-party integrations     - Example: &#x60;\&quot;destination\&quot;: {\&quot;type\&quot;: \&quot;chorus_pro\&quot;, \&quot;credentials\&quot;: {...}}&#x60;       **Electronic signature (optional) - 2 modes available:**      **Mode 1 - Stored certificate (recommended):**     - Certificate is retrieved automatically via the JWT &#x60;client_uid&#x60;     - No key to provide in the payload     - PAdES-B-LT signature with timestamp (eIDAS compliant)     - Example: &#x60;\&quot;signature\&quot;: {\&quot;reason\&quot;: \&quot;Factur-X compliance\&quot;}&#x60;      **Mode 2 - Keys in the payload (for tests):**     - Provide &#x60;key_pem&#x60; and &#x60;cert_pem&#x60; directly     - PEM format accepted: raw or base64     - Useful for tests or special cases without stored certificate     - Example: &#x60;\&quot;signature\&quot;: {\&quot;key_pem\&quot;: \&quot;-----BEGIN...\&quot;, \&quot;cert_pem\&quot;: \&quot;-----BEGIN...\&quot;}&#x60;      If &#x60;key_pem&#x60; and &#x60;cert_pem&#x60; are provided → Mode 2     Otherwise → Mode 1 (certificate retrieved via &#x60;client_uid&#x60;)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InvoiceProcessingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    InvoiceProcessingApi apiInstance = new InvoiceProcessingApi(defaultClient);
    SubmitCompleteInvoiceRequest submitCompleteInvoiceRequest = new SubmitCompleteInvoiceRequest(); // SubmitCompleteInvoiceRequest | 
    try {
      SubmitCompleteInvoiceResponse result = apiInstance.submitCompleteInvoiceApiV1ProcessingInvoicesSubmitCompletePost(submitCompleteInvoiceRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InvoiceProcessingApi#submitCompleteInvoiceApiV1ProcessingInvoicesSubmitCompletePost");
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

<a id="submitCompleteInvoiceAsyncApiV1ProcessingInvoicesSubmitCompleteAsyncPost"></a>
# **submitCompleteInvoiceAsyncApiV1ProcessingInvoicesSubmitCompleteAsyncPost**
> TaskResponse submitCompleteInvoiceAsyncApiV1ProcessingInvoicesSubmitCompleteAsyncPost(submitCompleteInvoiceRequest)

Submit a complete invoice (asynchronous with Celery)

Asynchronous version of the &#x60;/invoices/submit-complete&#x60; endpoint using Celery for background processing.      **Automated workflow (same as synchronous version):**     1. **Auto-enrichment** (optional): retrieves data via public APIs and Chorus Pro/AFNOR     2. **Factur-X PDF generation**: creates a PDF/A-3 with embedded XML     3. **Electronic signature** (optional): signs the PDF with a certificate     4. **Submission**: sends to the chosen destination (Chorus Pro or AFNOR PDP)      **Supported destinations:**     - **Chorus Pro**: French B2G platform (invoices to public sector)     - **AFNOR PDP**: Partner Dematerialization Platforms      **Differences with synchronous version:**     - ✅ **Non-blocking**: Returns immediately with a &#x60;task_id&#x60; (HTTP 202 Accepted)     - ✅ **Background processing**: Invoice is processed by a Celery worker     - ✅ **Progress tracking**: Use &#x60;/tasks/{task_id}/status&#x60; to track status     - ✅ **Ideal for high volumes**: Allows processing many invoices in parallel      **How to use:**     1. **Submission**: Call this endpoint with your invoice data     2. **Immediate return**: You receive a &#x60;task_id&#x60; (e.g., \&quot;abc123-def456\&quot;)     3. **Tracking**: Call &#x60;/tasks/{task_id}/status&#x60; to check progress     4. **Result**: When &#x60;status &#x3D; \&quot;SUCCESS\&quot;&#x60;, the &#x60;result&#x60; field contains the complete response      **Credentials and signature**: Same modes as the synchronous version (JWT or payload).

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InvoiceProcessingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    InvoiceProcessingApi apiInstance = new InvoiceProcessingApi(defaultClient);
    SubmitCompleteInvoiceRequest submitCompleteInvoiceRequest = new SubmitCompleteInvoiceRequest(); // SubmitCompleteInvoiceRequest | 
    try {
      TaskResponse result = apiInstance.submitCompleteInvoiceAsyncApiV1ProcessingInvoicesSubmitCompleteAsyncPost(submitCompleteInvoiceRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InvoiceProcessingApi#submitCompleteInvoiceAsyncApiV1ProcessingInvoicesSubmitCompleteAsyncPost");
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

<a id="validateFacturxPdfApiV1ProcessingValidateFacturxPdfPost"></a>
# **validateFacturxPdfApiV1ProcessingValidateFacturxPdfPost**
> PDFValidationResultAPI validateFacturxPdfApiV1ProcessingValidateFacturxPdfPost(pdfFile, profile, useVerapdf)

Validate a complete Factur-X PDF

Validates a complete Factur-X PDF according to European and French standards.  ## Applied validation standards  - **EN 16931**: European semantic standard (directive 2014/55/EU) - **ISO 19005-3** (PDF/A-3): Long-term electronic archiving - **Factur-X / ZUGFeRD**: Franco-German specification - **Schematron**: XML business rules validation - **eIDAS**: European regulation on electronic identification (signatures)  ## Checks performed  ### 1. Factur-X XML extraction and validation **Checks performed:** - Presence of embedded XML file (&#x60;factur-x.xml&#x60; or &#x60;zugferd-invoice.xml&#x60;) - Automatic profile detection (MINIMUM, BASIC, EN16931, EXTENDED) - XML parsing with UTF-8 validation - GuidelineSpecifiedDocumentContextParameter/ID extraction  **Schematron validation:** - Business rules for detected profile (MINIMUM: 45 rules, EN16931: 178 rules) - Cardinality of required elements - Calculation consistency (net, VAT, gross amounts, discounts) - Identifier formats (SIRET, intra-EU VAT, IBAN) - Standardized codes (ISO country codes, UN/ECE units, VAT codes)  **What is verified:** - ✅ XML structure conforming to Cross Industry Invoice XSD - ✅ Correct UN/CEFACT namespace - ✅ European business rules (BR-xx) - ✅ French-specific rules (FR-xx)  ### 2. PDF/A-3 compliance **Basic validation (metadata):** - Presence of &#x60;/Type&#x60; field set to &#x60;Catalog&#x60; - Metadata &#x60;pdfaid:part&#x60; &#x3D; 3 (PDF/A-3) - Metadata &#x60;pdfaid:conformance&#x60; &#x3D; B or U - PDF version &gt;&#x3D; 1.4  **Strict VeraPDF validation (if use_verapdf&#x3D;True):** - 146+ ISO 19005-3 rules (PDF/A-3B) - Absence of forbidden content (JavaScript, multimedia, dynamic forms) - Correctly embedded fonts and subsets - Compliant color spaces (sRGB, DeviceGray) - Valid file structure (cross-reference table) - XMP metadata conforming to ISO 16684-1  **What is verified:** - ✅ Long-term archivable file (20+ years) - ✅ Guaranteed readability (embedded fonts) - ✅ Legal compliance (France, Germany, EU)  ### 3. XMP metadata (eXtensible Metadata Platform) **Checks performed:** - Presence of &#x60;&lt;?xpacket&gt;&#x60; block with XMP metadata - &#x60;fx:&#x60; namespace for Factur-X: &#x60;urn:factur-x:pdfa:CrossIndustryDocument:invoice:1p0#&#x60; - Required Factur-X fields:   - &#x60;fx:ConformanceLevel&#x60;: Profile (MINIMUM, BASIC, EN16931, EXTENDED)   - &#x60;fx:DocumentFileName&#x60;: Embedded XML name   - &#x60;fx:DocumentType&#x60;: \&quot;INVOICE\&quot;   - &#x60;fx:Version&#x60;: Factur-X version (1.0.07)  **What is verified:** - ✅ Metadata conforming to ISO 16684-1 - ✅ Correct declared Factur-X profile - ✅ Supported Factur-X version  ### 4. Electronic signatures **Detection and analysis:** - Presence of &#x60;/Sig&#x60; dictionaries in PDF - Signature type: PAdES (PDF Advanced Electronic Signature) - Information extraction:   - Signer name (&#x60;/Name&#x60;)   - Signing date (&#x60;/M&#x60;)   - Signature reason (&#x60;/Reason&#x60;)   - Signature location (&#x60;/Location&#x60;)   - Signature type (approval, certification)  **What is verified:** - ✅ Presence of signatures or seals - ✅ Number of signatures (single or multi-signature) - ℹ️ No cryptographic verification (requires certificates)  ## Parameters  - **pdf_file** (required): The Factur-X PDF file to validate - **profile** (optional): Expected profile. If absent, auto-detected from XML - **use_verapdf** (optional, default&#x3D;false): Enable strict PDF/A validation with VeraPDF   - &#x60;false&#x60;: Fast metadata validation (2-3 seconds)   - &#x60;true&#x60;: Complete ISO 19005-3 validation (15-30 seconds, **recommended for production**)  ## Detailed response  &#x60;&#x60;&#x60;json {   \&quot;isCompliant\&quot;: true,   \&quot;xml\&quot;: {     \&quot;present\&quot;: true,     \&quot;compliant\&quot;: true,     \&quot;profile\&quot;: \&quot;EN16931\&quot;,     \&quot;errors\&quot;: []   },   \&quot;pdfa\&quot;: {     \&quot;compliant\&quot;: true,     \&quot;version\&quot;: \&quot;PDF/A-3B\&quot;,     \&quot;method\&quot;: \&quot;verapdf\&quot;,     \&quot;errors\&quot;: []   },   \&quot;xmp\&quot;: {     \&quot;present\&quot;: true,     \&quot;compliant\&quot;: true,     \&quot;metadata\&quot;: {...}   },   \&quot;signatures\&quot;: {     \&quot;present\&quot;: true,     \&quot;count\&quot;: 1,     \&quot;details\&quot;: [...]   } } &#x60;&#x60;&#x60;  ## Use cases  - **Before sending**: Validate generated invoice before transmission to client - **On reception**: Verify compliance of invoice received from supplier - **Audit**: Check quality of invoice batches - **Legal compliance**: Ensure B2B/B2G obligations are met in France - **Debugging**: Identify issues in generation process - **Archiving**: Guarantee long-term validity (PDF/A-3)  ## Processing time  - Basic validation: 2-3 seconds - VeraPDF validation: 15-30 seconds (depends on PDF size)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InvoiceProcessingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    InvoiceProcessingApi apiInstance = new InvoiceProcessingApi(defaultClient);
    File pdfFile = new File("/path/to/file"); // File | Factur-X PDF file to validate (.pdf format).
    APIProfile profile = APIProfile.fromValue("MINIMUM"); // APIProfile | 
    Boolean useVerapdf = false; // Boolean | Enable strict PDF/A validation with VeraPDF (recommended for production). If False, uses basic metadata validation.
    try {
      PDFValidationResultAPI result = apiInstance.validateFacturxPdfApiV1ProcessingValidateFacturxPdfPost(pdfFile, profile, useVerapdf);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InvoiceProcessingApi#validateFacturxPdfApiV1ProcessingValidateFacturxPdfPost");
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
| **pdfFile** | **File**| Factur-X PDF file to validate (.pdf format). | |
| **profile** | [**APIProfile**](APIProfile.md)|  | [optional] [enum: MINIMUM, BASIC, EN16931, EXTENDED] |
| **useVerapdf** | **Boolean**| Enable strict PDF/A validation with VeraPDF (recommended for production). If False, uses basic metadata validation. | [optional] [default to false] |

### Return type

[**PDFValidationResultAPI**](PDFValidationResultAPI.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Validation completed successfully (check is_compliant field for result) |  -  |
| **400** | Invalid or unreadable PDF file |  -  |
| **422** | Validation Error |  -  |

<a id="validateFacturxPdfAsyncApiV1ProcessingValidateFacturxAsyncPost"></a>
# **validateFacturxPdfAsyncApiV1ProcessingValidateFacturxAsyncPost**
> TaskResponse validateFacturxPdfAsyncApiV1ProcessingValidateFacturxAsyncPost(pdfFile, profile, useVerapdf)

Validate a Factur-X PDF (asynchronous with polling)

Validates a Factur-X PDF asynchronously with polling system.  ## How it works  1. **Submission**: PDF is queued for asynchronous validation 2. **Immediate return**: You receive a &#x60;task_id&#x60; (HTTP 202) 3. **Tracking**: Use the &#x60;/tasks/{task_id}/status&#x60; endpoint to track progress  ## Advantages of asynchronous mode  - **No timeout**: Ideal for large PDFs or VeraPDF validation (which can take several seconds) - **Scalability**: Validations are processed by dedicated Celery workers - **Status tracking**: Allows you to monitor validation progress - **Non-blocking**: Your client doesn&#39;t wait during validation  ## When to use this mode?  - **VeraPDF validation enabled** (&#x60;use_verapdf&#x3D;True&#x60;): Strict validation can take 2-10 seconds - **Large PDF files**: PDFs &gt; 1 MB - **Batch processing**: Validating multiple invoices in parallel - **Asynchronous integration**: Your system supports polling  ## Checks performed  ### 1. Factur-X XML extraction and validation - Verifies presence of Factur-X compliant embedded XML file - Automatically detects profile used (MINIMUM, BASIC, EN16931, EXTENDED) - Validates XML against detected profile&#39;s Schematron rules  ### 2. PDF/A compliance - **Without VeraPDF**: Basic metadata validation (fast, ~100ms) - **With VeraPDF**: Strict ISO 19005 validation (146+ rules, 2-10s)   - Detects PDF/A version (PDF/A-1, PDF/A-3, etc.)   - Detailed non-compliance reports  ### 3. XMP metadata - Verifies presence of XMP metadata in PDF - Validates Factur-X metadata compliance (profile, version) - Extracts all available XMP metadata  ### 4. Electronic signatures - Detects presence of electronic signatures or seals - Extracts information about each signature (signer, date, reason) - Counts number of signatures present  ## Parameters  - **pdf_file**: The Factur-X PDF file to validate - **profile**: Expected Factur-X profile (optional). If not specified, profile   will be auto-detected from embedded XML file. - **use_verapdf**: Enable strict PDF/A validation with VeraPDF.   ⚠️ **Warning**: VeraPDF can take 2-10 seconds depending on PDF size.   Recommended only in asynchronous mode to avoid timeouts.  ## Retrieving results  After submission, use &#x60;GET /tasks/{task_id}/status&#x60; endpoint to retrieve the result.  **Polling example**: &#x60;&#x60;&#x60;python import requests import time  # 1. Submit task response &#x3D; requests.post(\&quot;/validate-facturx-async\&quot;, files&#x3D;{\&quot;pdf_file\&quot;: pdf_file}) task_id &#x3D; response.json()[\&quot;taskId\&quot;]  # 2. Poll every 2 seconds while True:     status_response &#x3D; requests.get(f\&quot;/tasks/{task_id}/status\&quot;)     status &#x3D; status_response.json()      if status[\&quot;status\&quot;] &#x3D;&#x3D; \&quot;SUCCESS\&quot;:         result &#x3D; status[\&quot;result\&quot;][\&quot;validation_result\&quot;]         print(f\&quot;Compliant: {result[&#39;is_compliant&#39;]}\&quot;)         break     elif status[\&quot;status\&quot;] &#x3D;&#x3D; \&quot;FAILURE\&quot;:         print(f\&quot;Error: {status[&#39;result&#39;][&#39;errorMessage&#39;]}\&quot;)         break      time.sleep(2)  # Wait 2 seconds before next check &#x60;&#x60;&#x60;  ## Use cases  - Validate invoices before sending with VeraPDF (strict validation) - Process invoice batches in parallel - Integrate validation into an asynchronous pipeline - Validate large PDFs without timeout risk

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InvoiceProcessingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    InvoiceProcessingApi apiInstance = new InvoiceProcessingApi(defaultClient);
    File pdfFile = new File("/path/to/file"); // File | Factur-X PDF file to validate (.pdf format).
    APIProfile profile = APIProfile.fromValue("MINIMUM"); // APIProfile | 
    Boolean useVerapdf = false; // Boolean | Enable strict PDF/A validation with VeraPDF (recommended for production). May take several seconds.
    try {
      TaskResponse result = apiInstance.validateFacturxPdfAsyncApiV1ProcessingValidateFacturxAsyncPost(pdfFile, profile, useVerapdf);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InvoiceProcessingApi#validateFacturxPdfAsyncApiV1ProcessingValidateFacturxAsyncPost");
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
| **pdfFile** | **File**| Factur-X PDF file to validate (.pdf format). | |
| **profile** | [**APIProfile**](APIProfile.md)|  | [optional] [enum: MINIMUM, BASIC, EN16931, EXTENDED] |
| **useVerapdf** | **Boolean**| Enable strict PDF/A validation with VeraPDF (recommended for production). May take several seconds. | [optional] [default to false] |

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
| **400** | Invalid or unreadable PDF file |  -  |
| **422** | Validation Error |  -  |

<a id="validatePdfSignatureEndpointApiV1ProcessingValidatePdfSignaturePost"></a>
# **validatePdfSignatureEndpointApiV1ProcessingValidatePdfSignaturePost**
> Object validatePdfSignatureEndpointApiV1ProcessingValidatePdfSignaturePost(pdfFile)

Validate electronic signatures of a PDF

Validates electronic signatures present in an uploaded PDF.      **Verifications performed**:     - Presence of signatures     - Document integrity (not modified since signing)     - Certificate validity     - Chain of trust (if available)     - Presence of timestamp (PAdES-B-T)     - Validation data (PAdES-B-LT)      **Supported standards**: PAdES-B-B, PAdES-B-T, PAdES-B-LT, ISO 32000-2.      **⚠️ Note**: This validation is technical (cryptographic integrity). Legal validity     depends on the eIDAS level of the certificate (SES/AdES/QES) and the context of use.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InvoiceProcessingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    InvoiceProcessingApi apiInstance = new InvoiceProcessingApi(defaultClient);
    File pdfFile = new File("/path/to/file"); // File | PDF file to validate (will be analyzed to detect and validate signatures)
    try {
      Object result = apiInstance.validatePdfSignatureEndpointApiV1ProcessingValidatePdfSignaturePost(pdfFile);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InvoiceProcessingApi#validatePdfSignatureEndpointApiV1ProcessingValidatePdfSignaturePost");
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
| **pdfFile** | **File**| PDF file to validate (will be analyzed to detect and validate signatures) | |

### Return type

**Object**

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Validation successful |  -  |
| **400** | Invalid or unreadable file |  -  |
| **422** | Validation Error |  -  |

<a id="validateXmlApiV1ProcessingValidateXmlPost"></a>
# **validateXmlApiV1ProcessingValidateXmlPost**
> ValidationSuccessResponse validateXmlApiV1ProcessingValidateXmlPost(xmlFile, profile)

Validate an existing Factur-X XML

Validates a Factur-X XML file against Schematron business rules according to EN 16931 standard.  ## Applied Standard  **Schematron ISO/IEC 19757-3**: Business rules validation language for XML - Semantic validation (beyond XSD syntax) - European EN 16931 business rules - French-specific Factur-X rules - Arithmetic calculations and data consistency  ## Profiles and validated rules  ### MINIMUM (45 rules) - Unique invoice identifier - Dates (issue, due date) - Party identifiers (SIRET/SIREN) - Total gross amount  ### BASIC (102 rules) - All MINIMUM rules - Detailed invoice lines - Basic VAT calculations - Payment methods - References (order, contract)  ### EN16931 (178 rules) - All BASIC rules - **European rules (BR-xx)**: 81 business rules - **French rules (FR-xx)**: 12 France-specific rules - **Advanced calculations (CR-xx)**: 32 calculation rules - **Standardized codes (CL-xx)**: 52 code lists  ### EXTENDED (210+ rules) - All EN16931 rules - Logistics information - Advanced accounting data - Multiple external references  ## Checks performed  ### 1. Syntax validation - Correct XML parsing (UTF-8, well-formed) - UN/CEFACT namespaces present - Hierarchical structure respected  ### 2. Business rules (BR-xx) Examples: - &#x60;BR-1&#x60;: Invoice total must equal sum of line totals + document-level amounts - &#x60;BR-CO-10&#x60;: Sum of VAT base amounts must equal invoice net total - &#x60;BR-16&#x60;: Invoice currency code must be in ISO 4217 list  ### 3. French rules (FR-xx) Examples: - &#x60;FR-1&#x60;: Supplier SIRET must have 14 digits - &#x60;FR-2&#x60;: Customer SIRET must have 14 digits (if present) - &#x60;FR-5&#x60;: Intra-EU VAT number must be in format FRxx999999999  ### 4. Calculation rules (CR-xx) - Net + VAT &#x3D; Gross amounts - Sum of lines &#x3D; Document total - Discounts and surcharges correctly applied - Compliant rounding (2 decimals for amounts)  ### 5. Standardized codes (CL-xx) - ISO 3166-1 alpha-2 country codes - ISO 4217 currency codes - UN/ECE Rec 20 measurement units - VAT codes (types, categories, exemptions) - SchemeID for identifiers (0002&#x3D;SIREN, 0009&#x3D;SIRET, etc.)  ## Validation process  1. **XSLT loading**: Schematron file converted to XSLT (Saxon-HE) 2. **Transformation**: Rules applied to XML 3. **Results analysis**: Extraction of errors (&#x60;failed-assert&#x60;) and warnings (&#x60;successful-report&#x60;) 4. **Report**: Structured list of non-conformities  ## Responses  **200 OK**: Compliant XML &#x60;&#x60;&#x60;json {   \&quot;message\&quot;: \&quot;XML is compliant with EN16931 profile\&quot; } &#x60;&#x60;&#x60;  **400 Bad Request**: Non-compliant XML &#x60;&#x60;&#x60;json {   \&quot;detail\&quot;: [     \&quot;[BR-1] Invoice total (120.00) does not match calculated sum (100.00 + 20.00)\&quot;,     \&quot;[FR-1] Supplier SIRET must contain exactly 14 digits\&quot;   ] } &#x60;&#x60;&#x60;  ## Use cases  - **Pre-validation**: Verify XML before PDF/A integration - **Debugging**: Precisely identify generation errors - **Testing**: Validate test or example XMLs - **Compliance**: Ensure European and French rules are met - **Development**: Quick testing without PDF generation  ## Processing time  - MINIMUM profile: ~0.5 second - EN16931 profile: ~1-2 seconds - EXTENDED profile: ~2-3 seconds

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InvoiceProcessingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    InvoiceProcessingApi apiInstance = new InvoiceProcessingApi(defaultClient);
    File xmlFile = new File("/path/to/file"); // File | Factur-X XML file to validate (.xml format).
    APIProfile profile = APIProfile.fromValue("MINIMUM"); // APIProfile | Validation profile (MINIMUM, BASIC, EN16931, EXTENDED).
    try {
      ValidationSuccessResponse result = apiInstance.validateXmlApiV1ProcessingValidateXmlPost(xmlFile, profile);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InvoiceProcessingApi#validateXmlApiV1ProcessingValidateXmlPost");
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
| **xmlFile** | **File**| Factur-X XML file to validate (.xml format). | |
| **profile** | [**APIProfile**](APIProfile.md)| Validation profile (MINIMUM, BASIC, EN16931, EXTENDED). | [optional] [default to EXTENDED] [enum: MINIMUM, BASIC, EN16931, EXTENDED] |

### Return type

[**ValidationSuccessResponse**](ValidationSuccessResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | XML does not comply with Factur-X profile rules |  -  |
| **422** | Validation Error |  -  |

