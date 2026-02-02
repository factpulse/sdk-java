# Flux6InvoiceLifecycleCdarApi

All URIs are relative to *https://factpulse.fr*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**generateCdarApiV1CdarGeneratePost**](Flux6InvoiceLifecycleCdarApi.md#generateCdarApiV1CdarGeneratePost) | **POST** /api/v1/cdar/generate | Generate a CDAR message |
| [**getActionCodesApiV1CdarActionCodesGet**](Flux6InvoiceLifecycleCdarApi.md#getActionCodesApiV1CdarActionCodesGet) | **GET** /api/v1/cdar/action-codes | List of CDAR action codes |
| [**getReasonCodesApiV1CdarReasonCodesGet**](Flux6InvoiceLifecycleCdarApi.md#getReasonCodesApiV1CdarReasonCodesGet) | **GET** /api/v1/cdar/reason-codes | List of CDAR reason codes |
| [**getStatusCodesApiV1CdarStatusCodesGet**](Flux6InvoiceLifecycleCdarApi.md#getStatusCodesApiV1CdarStatusCodesGet) | **GET** /api/v1/cdar/status-codes | List of CDAR status codes |
| [**submitCdarApiV1CdarSubmitPost**](Flux6InvoiceLifecycleCdarApi.md#submitCdarApiV1CdarSubmitPost) | **POST** /api/v1/cdar/submit | Generate and submit a CDAR message |
| [**submitCdarXmlApiV1CdarSubmitXmlPost**](Flux6InvoiceLifecycleCdarApi.md#submitCdarXmlApiV1CdarSubmitXmlPost) | **POST** /api/v1/cdar/submit-xml | Submit a pre-generated CDAR XML |
| [**submitEncaisseeApiV1CdarEncaisseePost**](Flux6InvoiceLifecycleCdarApi.md#submitEncaisseeApiV1CdarEncaisseePost) | **POST** /api/v1/cdar/encaissee | [Simplified] Submit PAID status (212) - Issued invoice |
| [**submitRefuseeApiV1CdarRefuseePost**](Flux6InvoiceLifecycleCdarApi.md#submitRefuseeApiV1CdarRefuseePost) | **POST** /api/v1/cdar/refusee | [Simplified] Submit REFUSED status (210) - Received invoice |
| [**validateCdarApiV1CdarValidatePost**](Flux6InvoiceLifecycleCdarApi.md#validateCdarApiV1CdarValidatePost) | **POST** /api/v1/cdar/validate | Validate CDAR structured data |
| [**validateXmlCdarApiV1CdarValidateXmlPost**](Flux6InvoiceLifecycleCdarApi.md#validateXmlCdarApiV1CdarValidateXmlPost) | **POST** /api/v1/cdar/validate-xml | Validate CDAR XML against XSD and Schematron BR-FR-CDV |


<a id="generateCdarApiV1CdarGeneratePost"></a>
# **generateCdarApiV1CdarGeneratePost**
> GenerateCDARResponse generateCdarApiV1CdarGeneratePost(createCDARRequest)

Generate a CDAR message

Generate a CDAR XML message (Cross Domain Acknowledgement and Response) to communicate the status of an invoice.  **Message types:** - **23** (Processing): Standard lifecycle message - **305** (Transmission): Inter-platform transmission message  **Business rules:** - BR-FR-CDV-14: Status 212 (PAID) requires a paid amount - BR-FR-CDV-15: Statuses 206/207/208/210/213/501 require a reason code

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.Flux6InvoiceLifecycleCdarApi;

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

    Flux6InvoiceLifecycleCdarApi apiInstance = new Flux6InvoiceLifecycleCdarApi(defaultClient);
    CreateCDARRequest createCDARRequest = new CreateCDARRequest(); // CreateCDARRequest | 
    try {
      GenerateCDARResponse result = apiInstance.generateCdarApiV1CdarGeneratePost(createCDARRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling Flux6InvoiceLifecycleCdarApi#generateCdarApiV1CdarGeneratePost");
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
| **createCDARRequest** | [**CreateCDARRequest**](CreateCDARRequest.md)|  | |

### Return type

[**GenerateCDARResponse**](GenerateCDARResponse.md)

### Authorization

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request |  -  |
| **422** | Validation error |  -  |
| **500** | Server error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="getActionCodesApiV1CdarActionCodesGet"></a>
# **getActionCodesApiV1CdarActionCodesGet**
> ActionCodesResponse getActionCodesApiV1CdarActionCodesGet()

List of CDAR action codes

Returns the complete list of action codes (BR-FR-CDV-CL-10).  These codes indicate the requested action on the invoice.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.Flux6InvoiceLifecycleCdarApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");

    Flux6InvoiceLifecycleCdarApi apiInstance = new Flux6InvoiceLifecycleCdarApi(defaultClient);
    try {
      ActionCodesResponse result = apiInstance.getActionCodesApiV1CdarActionCodesGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling Flux6InvoiceLifecycleCdarApi#getActionCodesApiV1CdarActionCodesGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ActionCodesResponse**](ActionCodesResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request |  -  |
| **422** | Validation error |  -  |
| **500** | Server error |  -  |

<a id="getReasonCodesApiV1CdarReasonCodesGet"></a>
# **getReasonCodesApiV1CdarReasonCodesGet**
> ReasonCodesResponse getReasonCodesApiV1CdarReasonCodesGet()

List of CDAR reason codes

Returns the complete list of status reason codes (BR-FR-CDV-CL-09).  These codes explain the reason for a particular status.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.Flux6InvoiceLifecycleCdarApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");

    Flux6InvoiceLifecycleCdarApi apiInstance = new Flux6InvoiceLifecycleCdarApi(defaultClient);
    try {
      ReasonCodesResponse result = apiInstance.getReasonCodesApiV1CdarReasonCodesGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling Flux6InvoiceLifecycleCdarApi#getReasonCodesApiV1CdarReasonCodesGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ReasonCodesResponse**](ReasonCodesResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request |  -  |
| **422** | Validation error |  -  |
| **500** | Server error |  -  |

<a id="getStatusCodesApiV1CdarStatusCodesGet"></a>
# **getStatusCodesApiV1CdarStatusCodesGet**
> StatusCodesResponse getStatusCodesApiV1CdarStatusCodesGet()

List of CDAR status codes

Returns the complete list of invoice status codes (BR-FR-CDV-CL-06).  These codes indicate the lifecycle state of an invoice.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.Flux6InvoiceLifecycleCdarApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");

    Flux6InvoiceLifecycleCdarApi apiInstance = new Flux6InvoiceLifecycleCdarApi(defaultClient);
    try {
      StatusCodesResponse result = apiInstance.getStatusCodesApiV1CdarStatusCodesGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling Flux6InvoiceLifecycleCdarApi#getStatusCodesApiV1CdarStatusCodesGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**StatusCodesResponse**](StatusCodesResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request |  -  |
| **422** | Validation error |  -  |
| **500** | Server error |  -  |

<a id="submitCdarApiV1CdarSubmitPost"></a>
# **submitCdarApiV1CdarSubmitPost**
> SubmitCDARResponse submitCdarApiV1CdarSubmitPost(submitCDARRequest)

Generate and submit a CDAR message

Generate a CDAR message and submit it to the PA/PDP platform.  **Authentication strategies:** 1. **JWT with client_uid** (recommended): PDP credentials retrieved from backend 2. **Zero-storage**: Provide pdpFlowServiceUrl, pdpClientId, pdpClientSecret in the request  **Flow types (flowType):** - &#x60;CustomerInvoiceLC&#x60;: Client-side lifecycle (buyer) - &#x60;SupplierInvoiceLC&#x60;: Supplier-side lifecycle (seller)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.Flux6InvoiceLifecycleCdarApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    Flux6InvoiceLifecycleCdarApi apiInstance = new Flux6InvoiceLifecycleCdarApi(defaultClient);
    SubmitCDARRequest submitCDARRequest = new SubmitCDARRequest(); // SubmitCDARRequest | 
    try {
      SubmitCDARResponse result = apiInstance.submitCdarApiV1CdarSubmitPost(submitCDARRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling Flux6InvoiceLifecycleCdarApi#submitCdarApiV1CdarSubmitPost");
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
| **submitCDARRequest** | [**SubmitCDARRequest**](SubmitCDARRequest.md)|  | |

### Return type

[**SubmitCDARResponse**](SubmitCDARResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request |  -  |
| **422** | Validation error |  -  |
| **500** | Server error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="submitCdarXmlApiV1CdarSubmitXmlPost"></a>
# **submitCdarXmlApiV1CdarSubmitXmlPost**
> SubmitCDARResponse submitCdarXmlApiV1CdarSubmitXmlPost(submitCDARXMLRequest)

Submit a pre-generated CDAR XML

Submit a pre-generated CDAR XML message to the PA/PDP platform.  Useful for submitting XML generated by other systems.  **Validation:** The XML is validated against XSD and Schematron BR-FR-CDV rules BEFORE submission. Invalid XML will be rejected with detailed error messages.  **Authentication strategies:** 1. **JWT with client_uid** (recommended): PDP credentials retrieved from backend 2. **Zero-storage**: Provide pdpFlowServiceUrl, pdpClientId, pdpClientSecret in the request

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.Flux6InvoiceLifecycleCdarApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    Flux6InvoiceLifecycleCdarApi apiInstance = new Flux6InvoiceLifecycleCdarApi(defaultClient);
    SubmitCDARXMLRequest submitCDARXMLRequest = new SubmitCDARXMLRequest(); // SubmitCDARXMLRequest | 
    try {
      SubmitCDARResponse result = apiInstance.submitCdarXmlApiV1CdarSubmitXmlPost(submitCDARXMLRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling Flux6InvoiceLifecycleCdarApi#submitCdarXmlApiV1CdarSubmitXmlPost");
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
| **submitCDARXMLRequest** | [**SubmitCDARXMLRequest**](SubmitCDARXMLRequest.md)|  | |

### Return type

[**SubmitCDARResponse**](SubmitCDARResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request |  -  |
| **422** | Validation error |  -  |
| **500** | Server error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="submitEncaisseeApiV1CdarEncaisseePost"></a>
# **submitEncaisseeApiV1CdarEncaisseePost**
> SimplifiedCDARResponse submitEncaisseeApiV1CdarEncaisseePost(encaisseeRequest)

[Simplified] Submit PAID status (212) - Issued invoice

**Simplified endpoint for OD** - Submit a PAID status (212) for an **ISSUED** invoice.  This status is **mandatory for PPF** (BR-FR-CDV-14 requires the paid amount).  **Use case:** The **seller** confirms payment receipt for an invoice they issued.  **Who issues this status?** - **Issuer (IssuerTradeParty):** The seller (SE &#x3D; Seller) who received payment - **Recipient (RecipientTradeParty):** The buyer (BY &#x3D; Buyer) who paid  **Reference:** XP Z12-014 Annex B, example UC1_F202500003_07-CDV-212_Encaissee.xml  **Authentication:** JWT Bearer (recommended) or PDP credentials in request.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.Flux6InvoiceLifecycleCdarApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    Flux6InvoiceLifecycleCdarApi apiInstance = new Flux6InvoiceLifecycleCdarApi(defaultClient);
    EncaisseeRequest encaisseeRequest = new EncaisseeRequest(); // EncaisseeRequest | 
    try {
      SimplifiedCDARResponse result = apiInstance.submitEncaisseeApiV1CdarEncaisseePost(encaisseeRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling Flux6InvoiceLifecycleCdarApi#submitEncaisseeApiV1CdarEncaisseePost");
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
| **encaisseeRequest** | [**EncaisseeRequest**](EncaisseeRequest.md)|  | |

### Return type

[**SimplifiedCDARResponse**](SimplifiedCDARResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request |  -  |
| **422** | Validation error |  -  |
| **500** | Server error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="submitRefuseeApiV1CdarRefuseePost"></a>
# **submitRefuseeApiV1CdarRefuseePost**
> SimplifiedCDARResponse submitRefuseeApiV1CdarRefuseePost(refuseeRequest)

[Simplified] Submit REFUSED status (210) - Received invoice

**Simplified endpoint for OD** - Submit a REFUSED status (210) for a **RECEIVED** invoice.  This status is **mandatory for PPF** (BR-FR-CDV-15 requires a reason code).  **Use case:** The **buyer** refuses an invoice they received.  **Who issues this status?** - **Issuer (IssuerTradeParty):** The buyer (BY &#x3D; Buyer) refusing the invoice - **Recipient (RecipientTradeParty):** The seller (SE &#x3D; Seller) who issued the invoice  **Reference:** XP Z12-014 Annex B, example UC3_F202500005_04-CDV-210_Refusee.xml  **Allowed reason codes (BR-FR-CDV-CL-09):** - &#x60;TX_TVA_ERR&#x60;: Incorrect VAT rate - &#x60;MONTANTTOTAL_ERR&#x60;: Incorrect total amount - &#x60;CALCUL_ERR&#x60;: Calculation error - &#x60;NON_CONFORME&#x60;: Non-compliant - &#x60;DOUBLON&#x60;: Duplicate - &#x60;DEST_ERR&#x60;: Wrong recipient - &#x60;TRANSAC_INC&#x60;: Incomplete transaction - &#x60;EMMET_INC&#x60;: Unknown issuer - &#x60;CONTRAT_TERM&#x60;: Contract terminated - &#x60;DOUBLE_FACT&#x60;: Double billing - &#x60;CMD_ERR&#x60;: Order error - &#x60;ADR_ERR&#x60;: Address error - &#x60;REF_CT_ABSENT&#x60;: Missing contract reference  **Authentication:** JWT Bearer (recommended) or PDP credentials in request.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.Flux6InvoiceLifecycleCdarApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    Flux6InvoiceLifecycleCdarApi apiInstance = new Flux6InvoiceLifecycleCdarApi(defaultClient);
    RefuseeRequest refuseeRequest = new RefuseeRequest(); // RefuseeRequest | 
    try {
      SimplifiedCDARResponse result = apiInstance.submitRefuseeApiV1CdarRefuseePost(refuseeRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling Flux6InvoiceLifecycleCdarApi#submitRefuseeApiV1CdarRefuseePost");
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
| **refuseeRequest** | [**RefuseeRequest**](RefuseeRequest.md)|  | |

### Return type

[**SimplifiedCDARResponse**](SimplifiedCDARResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request |  -  |
| **422** | Validation error |  -  |
| **500** | Server error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="validateCdarApiV1CdarValidatePost"></a>
# **validateCdarApiV1CdarValidatePost**
> ValidateCDARResponse validateCdarApiV1CdarValidatePost(validateCDARRequest)

Validate CDAR structured data

Validate CDAR structured data without generating XML.  **Note:** This endpoint validates structured data fields only. Use &#x60;/validate-xml&#x60; to validate a pre-generated CDAR XML file against XSD and Schematron.  Checks: - Field formats (SIREN, dates, etc.) - Enum codes (status, reason, action) - Business rules BR-FR-CDV-*

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.Flux6InvoiceLifecycleCdarApi;

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

    Flux6InvoiceLifecycleCdarApi apiInstance = new Flux6InvoiceLifecycleCdarApi(defaultClient);
    ValidateCDARRequest validateCDARRequest = new ValidateCDARRequest(); // ValidateCDARRequest | 
    try {
      ValidateCDARResponse result = apiInstance.validateCdarApiV1CdarValidatePost(validateCDARRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling Flux6InvoiceLifecycleCdarApi#validateCdarApiV1CdarValidatePost");
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
| **validateCDARRequest** | [**ValidateCDARRequest**](ValidateCDARRequest.md)|  | |

### Return type

[**ValidateCDARResponse**](ValidateCDARResponse.md)

### Authorization

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request |  -  |
| **422** | Validation error |  -  |
| **500** | Server error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="validateXmlCdarApiV1CdarValidateXmlPost"></a>
# **validateXmlCdarApiV1CdarValidateXmlPost**
> Map&lt;String, Object&gt; validateXmlCdarApiV1CdarValidateXmlPost(xmlFile)

Validate CDAR XML against XSD and Schematron BR-FR-CDV

Validates a CDAR XML file against:  1. **XSD schema**: UN/CEFACT D22B CrossDomainAcknowledgementAndResponse 2. **Schematron BR-FR-CDV**: French business rules for invoice lifecycle  Returns validation status and detailed error messages if invalid.  **Note:** Use &#x60;/validate&#x60; to validate structured data fields (JSON).

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.Flux6InvoiceLifecycleCdarApi;

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

    Flux6InvoiceLifecycleCdarApi apiInstance = new Flux6InvoiceLifecycleCdarApi(defaultClient);
    File xmlFile = new File("/path/to/file"); // File | CDAR XML file to validate
    try {
      Map<String, Object> result = apiInstance.validateXmlCdarApiV1CdarValidateXmlPost(xmlFile);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling Flux6InvoiceLifecycleCdarApi#validateXmlCdarApiV1CdarValidateXmlPost");
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
| **xmlFile** | **File**| CDAR XML file to validate | |

### Return type

**Map&lt;String, Object&gt;**

### Authorization

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request |  -  |
| **422** | Validation error |  -  |
| **500** | Server error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

