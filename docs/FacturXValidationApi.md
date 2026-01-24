# FacturXValidationApi

All URIs are relative to *https://factpulse.fr*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**validateFacturxPdfApiV1ProcessingValidateFacturxPdfPost**](FacturXValidationApi.md#validateFacturxPdfApiV1ProcessingValidateFacturxPdfPost) | **POST** /api/v1/processing/validate-facturx-pdf | Validate a complete Factur-X PDF |
| [**validateFacturxPdfAsyncApiV1ProcessingValidateFacturxAsyncPost**](FacturXValidationApi.md#validateFacturxPdfAsyncApiV1ProcessingValidateFacturxAsyncPost) | **POST** /api/v1/processing/validate-facturx-async | Validate a Factur-X PDF (asynchronous with polling) |
| [**validateXmlApiV1ProcessingValidateXmlPost**](FacturXValidationApi.md#validateXmlApiV1ProcessingValidateXmlPost) | **POST** /api/v1/processing/validate-xml | Validate an existing Factur-X XML |


<a id="validateFacturxPdfApiV1ProcessingValidateFacturxPdfPost"></a>
# **validateFacturxPdfApiV1ProcessingValidateFacturxPdfPost**
> PDFValidationResultAPI validateFacturxPdfApiV1ProcessingValidateFacturxPdfPost(pdfFile, profile, useVerapdf, skipBrFr)

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
import org.openapitools.client.api.FacturXValidationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    FacturXValidationApi apiInstance = new FacturXValidationApi(defaultClient);
    File pdfFile = new File("/path/to/file"); // File | Factur-X PDF file to validate (.pdf format).
    APIProfile profile = APIProfile.fromValue("MINIMUM"); // APIProfile | 
    Boolean useVerapdf = false; // Boolean | Enable strict PDF/A validation with VeraPDF (recommended for production). If False, uses basic metadata validation.
    Boolean skipBrFr = true; // Boolean | 
    try {
      PDFValidationResultAPI result = apiInstance.validateFacturxPdfApiV1ProcessingValidateFacturxPdfPost(pdfFile, profile, useVerapdf, skipBrFr);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FacturXValidationApi#validateFacturxPdfApiV1ProcessingValidateFacturxPdfPost");
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
| **profile** | [**APIProfile**](APIProfile.md)|  | [optional] [enum: MINIMUM, BASICWL, BASIC, EN16931, EXTENDED] |
| **useVerapdf** | **Boolean**| Enable strict PDF/A validation with VeraPDF (recommended for production). If False, uses basic metadata validation. | [optional] [default to false] |
| **skipBrFr** | **Boolean**|  | [optional] |

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
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="validateFacturxPdfAsyncApiV1ProcessingValidateFacturxAsyncPost"></a>
# **validateFacturxPdfAsyncApiV1ProcessingValidateFacturxAsyncPost**
> TaskResponse validateFacturxPdfAsyncApiV1ProcessingValidateFacturxAsyncPost(pdfFile, profile, useVerapdf, callbackUrl, webhookMode)

Validate a Factur-X PDF (asynchronous with polling)

Validates a Factur-X PDF asynchronously with polling system.  ## How it works  1. **Submission**: PDF is queued for asynchronous validation 2. **Immediate return**: You receive a &#x60;task_id&#x60; (HTTP 202) 3. **Tracking**: Use the &#x60;/tasks/{task_id}/status&#x60; endpoint to track progress  ## Advantages of asynchronous mode  - **No timeout**: Ideal for large PDFs or VeraPDF validation (which can take several seconds) - **Scalability**: Validations are processed by dedicated Celery workers - **Status tracking**: Allows you to monitor validation progress - **Non-blocking**: Your client doesn&#39;t wait during validation  ## Webhook notification (recommended)  Instead of polling, you can receive a webhook notification when validation completes:  &#x60;&#x60;&#x60; callback_url&#x3D;https://your-server.com/webhook webhook_mode&#x3D;download_url  # Optional: get download URL instead of base64 &#x60;&#x60;&#x60;  The webhook will POST a JSON payload with: - &#x60;event_type&#x60;: &#x60;validation.completed&#x60; or &#x60;validation.failed&#x60; - &#x60;data.is_compliant&#x60;: Whether the PDF is Factur-X compliant - &#x60;data.detected_profile&#x60;: The detected Factur-X profile - &#x60;X-Webhook-Signature&#x60; header for HMAC verification  ## When to use this mode?  - **VeraPDF validation enabled** (&#x60;use_verapdf&#x3D;True&#x60;): Strict validation can take 2-10 seconds - **Large PDF files**: PDFs &gt; 1 MB - **Batch processing**: Validating multiple invoices in parallel - **Asynchronous integration**: Your system supports polling  ## Checks performed  ### 1. Factur-X XML extraction and validation - Verifies presence of Factur-X compliant embedded XML file - Automatically detects profile used (MINIMUM, BASIC, EN16931, EXTENDED) - Validates XML against detected profile&#39;s Schematron rules  ### 2. PDF/A compliance - **Without VeraPDF**: Basic metadata validation (fast, ~100ms) - **With VeraPDF**: Strict ISO 19005 validation (146+ rules, 2-10s)   - Detects PDF/A version (PDF/A-1, PDF/A-3, etc.)   - Detailed non-compliance reports  ### 3. XMP metadata - Verifies presence of XMP metadata in PDF - Validates Factur-X metadata compliance (profile, version) - Extracts all available XMP metadata  ### 4. Electronic signatures - Detects presence of electronic signatures or seals - Extracts information about each signature (signer, date, reason) - Counts number of signatures present  ## Parameters  - **pdf_file**: The Factur-X PDF file to validate - **profile**: Expected Factur-X profile (optional). If not specified, profile   will be auto-detected from embedded XML file. - **use_verapdf**: Enable strict PDF/A validation with VeraPDF.   ⚠️ **Warning**: VeraPDF can take 2-10 seconds depending on PDF size.   Recommended only in asynchronous mode to avoid timeouts.  ## Retrieving results  After submission, use &#x60;GET /tasks/{task_id}/status&#x60; endpoint to retrieve the result.  **Polling example**: &#x60;&#x60;&#x60;python import requests import time  # 1. Submit task response &#x3D; requests.post(\&quot;/validate-facturx-async\&quot;, files&#x3D;{\&quot;pdf_file\&quot;: pdf_file}) task_id &#x3D; response.json()[\&quot;taskId\&quot;]  # 2. Poll every 2 seconds while True:     status_response &#x3D; requests.get(f\&quot;/tasks/{task_id}/status\&quot;)     status &#x3D; status_response.json()      if status[\&quot;status\&quot;] &#x3D;&#x3D; \&quot;SUCCESS\&quot;:         result &#x3D; status[\&quot;result\&quot;][\&quot;validation_result\&quot;]         print(f\&quot;Compliant: {result[&#39;is_compliant&#39;]}\&quot;)         break     elif status[\&quot;status\&quot;] &#x3D;&#x3D; \&quot;FAILURE\&quot;:         print(f\&quot;Error: {status[&#39;result&#39;][&#39;errorMessage&#39;]}\&quot;)         break      time.sleep(2)  # Wait 2 seconds before next check &#x60;&#x60;&#x60;  ## Use cases  - Validate invoices before sending with VeraPDF (strict validation) - Process invoice batches in parallel - Integrate validation into an asynchronous pipeline - Validate large PDFs without timeout risk

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FacturXValidationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    FacturXValidationApi apiInstance = new FacturXValidationApi(defaultClient);
    File pdfFile = new File("/path/to/file"); // File | Factur-X PDF file to validate (.pdf format).
    APIProfile profile = APIProfile.fromValue("MINIMUM"); // APIProfile | 
    Boolean useVerapdf = false; // Boolean | Enable strict PDF/A validation with VeraPDF (recommended for production). May take several seconds.
    String callbackUrl = "callbackUrl_example"; // String | 
    String webhookMode = "inline"; // String | Webhook content delivery: 'inline' (base64 in payload) or 'download_url' (temporary URL, 1h TTL)
    try {
      TaskResponse result = apiInstance.validateFacturxPdfAsyncApiV1ProcessingValidateFacturxAsyncPost(pdfFile, profile, useVerapdf, callbackUrl, webhookMode);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FacturXValidationApi#validateFacturxPdfAsyncApiV1ProcessingValidateFacturxAsyncPost");
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
| **profile** | [**APIProfile**](APIProfile.md)|  | [optional] [enum: MINIMUM, BASICWL, BASIC, EN16931, EXTENDED] |
| **useVerapdf** | **Boolean**| Enable strict PDF/A validation with VeraPDF (recommended for production). May take several seconds. | [optional] [default to false] |
| **callbackUrl** | **String**|  | [optional] |
| **webhookMode** | **String**| Webhook content delivery: &#39;inline&#39; (base64 in payload) or &#39;download_url&#39; (temporary URL, 1h TTL) | [optional] [default to inline] |

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
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="validateXmlApiV1ProcessingValidateXmlPost"></a>
# **validateXmlApiV1ProcessingValidateXmlPost**
> ValidationSuccessResponse validateXmlApiV1ProcessingValidateXmlPost(xmlFile, profile, skipBrFr)

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
import org.openapitools.client.api.FacturXValidationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    FacturXValidationApi apiInstance = new FacturXValidationApi(defaultClient);
    File xmlFile = new File("/path/to/file"); // File | Factur-X XML file to validate (.xml format).
    APIProfile profile = APIProfile.fromValue("MINIMUM"); // APIProfile | Validation profile (MINIMUM, BASIC, EN16931, EXTENDED).
    Boolean skipBrFr = true; // Boolean | 
    try {
      ValidationSuccessResponse result = apiInstance.validateXmlApiV1ProcessingValidateXmlPost(xmlFile, profile, skipBrFr);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FacturXValidationApi#validateXmlApiV1ProcessingValidateXmlPost");
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
| **profile** | [**APIProfile**](APIProfile.md)| Validation profile (MINIMUM, BASIC, EN16931, EXTENDED). | [optional] [default to EXTENDED] [enum: MINIMUM, BASICWL, BASIC, EN16931, EXTENDED] |
| **skipBrFr** | **Boolean**|  | [optional] |

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
| **401** | Authentication required - Invalid or missing JWT token |  -  |

