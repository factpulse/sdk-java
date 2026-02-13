# ValidationApi

All URIs are relative to *https://factpulse.fr*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**validateXmlApiV1ProcessingValidateXmlPost**](ValidationApi.md#validateXmlApiV1ProcessingValidateXmlPost) | **POST** /api/v1/processing/validate-xml | Validate a CII or UBL XML against Schematron rules |


<a id="validateXmlApiV1ProcessingValidateXmlPost"></a>
# **validateXmlApiV1ProcessingValidateXmlPost**
> ValidationSuccessResponse validateXmlApiV1ProcessingValidateXmlPost(xmlFile, profile, skipBrFr)

Validate a CII or UBL XML against Schematron rules

Validates a CII (Factur-X) or UBL XML file against Schematron business rules.  The XML format is **auto-detected** from namespace: - CII XML → validated with EN16931-CII + profile-specific + BR-FR-CII Schematrons - UBL XML → validated with EN16931-UBL + BR-FR-UBL Schematrons  ## Applied Standard  **Schematron ISO/IEC 19757-3**: Business rules validation language for XML - Semantic validation (beyond XSD syntax) - European EN 16931 business rules - French-specific rules (BR-FR-CII or BR-FR-UBL) - Arithmetic calculations and data consistency  ## Profiles and validated rules (CII only)  ### MINIMUM (45 rules) - Unique invoice identifier - Dates (issue, due date) - Party identifiers (SIRET/SIREN) - Total gross amount  ### BASIC (102 rules) - All MINIMUM rules - Detailed invoice lines - Basic VAT calculations - Payment methods - References (order, contract)  ### EN16931 (178 rules) - All BASIC rules - **European rules (BR-xx)**: 81 business rules - **French rules (FR-xx)**: 12 France-specific rules - **Advanced calculations (CR-xx)**: 32 calculation rules - **Standardized codes (CL-xx)**: 52 code lists  ### EXTENDED (210+ rules) - All EN16931 rules - Logistics information - Advanced accounting data - Multiple external references  ## Checks performed  ### 1. Syntax validation - Correct XML parsing (UTF-8, well-formed) - UN/CEFACT namespaces present - Hierarchical structure respected  ### 2. Business rules (BR-xx) Examples: - &#x60;BR-1&#x60;: Invoice total must equal sum of line totals + document-level amounts - &#x60;BR-CO-10&#x60;: Sum of VAT base amounts must equal invoice net total - &#x60;BR-16&#x60;: Invoice currency code must be in ISO 4217 list  ### 3. French rules (FR-xx) Examples: - &#x60;FR-1&#x60;: Supplier SIRET must have 14 digits - &#x60;FR-2&#x60;: Customer SIRET must have 14 digits (if present) - &#x60;FR-5&#x60;: Intra-EU VAT number must be in format FRxx999999999  ### 4. Calculation rules (CR-xx) - Net + VAT &#x3D; Gross amounts - Sum of lines &#x3D; Document total - Discounts and surcharges correctly applied - Compliant rounding (2 decimals for amounts)  ### 5. Standardized codes (CL-xx) - ISO 3166-1 alpha-2 country codes - ISO 4217 currency codes - UN/ECE Rec 20 measurement units - VAT codes (types, categories, exemptions) - SchemeID for identifiers (0002&#x3D;SIREN, 0009&#x3D;SIRET, etc.)  ## Validation process  1. **XSLT loading**: Schematron file converted to XSLT (Saxon-HE) 2. **Transformation**: Rules applied to XML 3. **Results analysis**: Extraction of errors (&#x60;failed-assert&#x60;) and warnings (&#x60;successful-report&#x60;) 4. **Report**: Structured list of non-conformities  ## Responses  **200 OK**: Compliant XML &#x60;&#x60;&#x60;json {   \&quot;message\&quot;: \&quot;XML is compliant with EN16931 profile\&quot; } &#x60;&#x60;&#x60;  **400 Bad Request**: Non-compliant XML &#x60;&#x60;&#x60;json {   \&quot;detail\&quot;: [     \&quot;[BR-1] Invoice total (120.00) does not match calculated sum (100.00 + 20.00)\&quot;,     \&quot;[FR-1] Supplier SIRET must contain exactly 14 digits\&quot;   ] } &#x60;&#x60;&#x60;  ## Use cases  - **Pre-validation**: Verify XML before PDF/A integration - **Debugging**: Precisely identify generation errors - **Testing**: Validate test or example XMLs - **Compliance**: Ensure European and French rules are met - **Development**: Quick testing without PDF generation  ## Processing time  - MINIMUM profile: ~0.5 second - EN16931 profile: ~1-2 seconds - EXTENDED profile: ~2-3 seconds

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ValidationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure API key authorization: APIKeyHeader
    ApiKeyAuth APIKeyHeader = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyHeader");
    APIKeyHeader.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyHeader.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ValidationApi apiInstance = new ValidationApi(defaultClient);
    File xmlFile = new File("/path/to/file"); // File | CII (Factur-X) or UBL XML file to validate (.xml format). Format is auto-detected.
    APIProfile profile = APIProfile.fromValue("MINIMUM"); // APIProfile | Validation profile (MINIMUM, BASIC, EN16931, EXTENDED). Used for CII only; ignored for UBL (always EN16931).
    Boolean skipBrFr = true; // Boolean | 
    try {
      ValidationSuccessResponse result = apiInstance.validateXmlApiV1ProcessingValidateXmlPost(xmlFile, profile, skipBrFr);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ValidationApi#validateXmlApiV1ProcessingValidateXmlPost");
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
| **xmlFile** | **File**| CII (Factur-X) or UBL XML file to validate (.xml format). Format is auto-detected. | |
| **profile** | [**APIProfile**](APIProfile.md)| Validation profile (MINIMUM, BASIC, EN16931, EXTENDED). Used for CII only; ignored for UBL (always EN16931). | [optional] [default to EXTENDED] [enum: MINIMUM, BASICWL, BASIC, EN16931, EXTENDED] |
| **skipBrFr** | **Boolean**|  | [optional] |

### Return type

[**ValidationSuccessResponse**](ValidationSuccessResponse.md)

### Authorization

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | XML does not comply with validation rules |  -  |
| **422** | Validation Error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

