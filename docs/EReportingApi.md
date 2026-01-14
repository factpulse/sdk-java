# EReportingApi

All URIs are relative to *https://factpulse.fr*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**generateAggregatedEreportingApiV1EreportingGenerateAggregatedPost**](EReportingApi.md#generateAggregatedEreportingApiV1EreportingGenerateAggregatedPost) | **POST** /api/v1/ereporting/generate-aggregated | Generate aggregated e-reporting XML (PPF-compliant) |
| [**generateEreportingApiV1EreportingGeneratePost**](EReportingApi.md#generateEreportingApiV1EreportingGeneratePost) | **POST** /api/v1/ereporting/generate | Generate e-reporting XML |
| [**generateEreportingDownloadApiV1EreportingGenerateDownloadPost**](EReportingApi.md#generateEreportingDownloadApiV1EreportingGenerateDownloadPost) | **POST** /api/v1/ereporting/generate/download | Generate and download e-reporting XML |
| [**listCategoryCodesApiV1EreportingCategoryCodesGet**](EReportingApi.md#listCategoryCodesApiV1EreportingCategoryCodesGet) | **GET** /api/v1/ereporting/category-codes | List PPF-compliant category codes |
| [**listFlowTypesApiV1EreportingFlowTypesGet**](EReportingApi.md#listFlowTypesApiV1EreportingFlowTypesGet) | **GET** /api/v1/ereporting/flow-types | List available flow types |
| [**submitAggregatedEreportingApiV1EreportingSubmitAggregatedPost**](EReportingApi.md#submitAggregatedEreportingApiV1EreportingSubmitAggregatedPost) | **POST** /api/v1/ereporting/submit-aggregated | Submit aggregated e-reporting to PA/PDP |
| [**submitEreportingApiV1EreportingSubmitPost**](EReportingApi.md#submitEreportingApiV1EreportingSubmitPost) | **POST** /api/v1/ereporting/submit | Submit e-reporting to PA/PDP |
| [**submitXmlEreportingApiV1EreportingSubmitXmlPost**](EReportingApi.md#submitXmlEreportingApiV1EreportingSubmitXmlPost) | **POST** /api/v1/ereporting/submit-xml | Submit pre-generated e-reporting XML |
| [**validateAggregatedEreportingApiV1EreportingValidateAggregatedPost**](EReportingApi.md#validateAggregatedEreportingApiV1EreportingValidateAggregatedPost) | **POST** /api/v1/ereporting/validate-aggregated | Validate aggregated e-reporting data |
| [**validateEreportingApiV1EreportingValidatePost**](EReportingApi.md#validateEreportingApiV1EreportingValidatePost) | **POST** /api/v1/ereporting/validate | Validate e-reporting data |
| [**validateXmlEreportingApiV1EreportingValidateXmlPost**](EReportingApi.md#validateXmlEreportingApiV1EreportingValidateXmlPost) | **POST** /api/v1/ereporting/validate-xml | Validate e-reporting XML against PPF XSD schemas and business rules |


<a id="generateAggregatedEreportingApiV1EreportingGenerateAggregatedPost"></a>
# **generateAggregatedEreportingApiV1EreportingGenerateAggregatedPost**
> GenerateAggregatedReportResponse generateAggregatedEreportingApiV1EreportingGenerateAggregatedPost(createAggregatedReportRequest)

Generate aggregated e-reporting XML (PPF-compliant)

Generate a PPF-compliant aggregated e-reporting XML containing multiple flux types in a single file.  This endpoint creates a Report XML that can contain: - **TransactionsReport**: Invoice (10.1) AND/OR Transactions (10.3) - **PaymentsReport**: Invoice payments (10.2) AND/OR Transaction payments (10.4)  The AFNOR FlowType is automatically determined based on content: - Single type → Specific FlowType (e.g., AggregatedCustomerTransactionReport) - Multiple types → MultiFlowReport  **CategoryCode (TT-81)** must use PPF-compliant values: - TLB1: Goods deliveries - TPS1: Service provisions - TNT1: Non-taxed transactions - TMA1: Mixed transactions

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EReportingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    EReportingApi apiInstance = new EReportingApi(defaultClient);
    CreateAggregatedReportRequest createAggregatedReportRequest = new CreateAggregatedReportRequest(); // CreateAggregatedReportRequest | 
    try {
      GenerateAggregatedReportResponse result = apiInstance.generateAggregatedEreportingApiV1EreportingGenerateAggregatedPost(createAggregatedReportRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EReportingApi#generateAggregatedEreportingApiV1EreportingGenerateAggregatedPost");
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
| **createAggregatedReportRequest** | [**CreateAggregatedReportRequest**](CreateAggregatedReportRequest.md)|  | |

### Return type

[**GenerateAggregatedReportResponse**](GenerateAggregatedReportResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request data |  -  |
| **422** | Validation error |  -  |
| **500** | Internal server error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="generateEreportingApiV1EreportingGeneratePost"></a>
# **generateEreportingApiV1EreportingGeneratePost**
> GenerateEReportingResponse generateEreportingApiV1EreportingGeneratePost(createEReportingRequest)

Generate e-reporting XML

Generate e-reporting XML (FRR format) from structured data.  Supports all four flow types: - **10.1**: Unitary B2B international transactions (use &#x60;invoices&#x60; field) - **10.2**: Payments for B2B international invoices (use &#x60;invoicePayments&#x60; field) - **10.3**: Aggregated B2C transactions (use &#x60;transactions&#x60; field) - **10.4**: Aggregated B2C payments (use &#x60;aggregatedPayments&#x60; field)  The generated XML is compliant with DGFIP specifications and ready for submission to a PA (Plateforme Agréée).

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EReportingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    EReportingApi apiInstance = new EReportingApi(defaultClient);
    CreateEReportingRequest createEReportingRequest = new CreateEReportingRequest(); // CreateEReportingRequest | 
    try {
      GenerateEReportingResponse result = apiInstance.generateEreportingApiV1EreportingGeneratePost(createEReportingRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EReportingApi#generateEreportingApiV1EreportingGeneratePost");
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
| **createEReportingRequest** | [**CreateEReportingRequest**](CreateEReportingRequest.md)|  | |

### Return type

[**GenerateEReportingResponse**](GenerateEReportingResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request data |  -  |
| **422** | Validation error |  -  |
| **500** | Internal server error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="generateEreportingDownloadApiV1EreportingGenerateDownloadPost"></a>
# **generateEreportingDownloadApiV1EreportingGenerateDownloadPost**
> generateEreportingDownloadApiV1EreportingGenerateDownloadPost(createEReportingRequest, filename)

Generate and download e-reporting XML

Generate e-reporting XML and return as downloadable file.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EReportingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    EReportingApi apiInstance = new EReportingApi(defaultClient);
    CreateEReportingRequest createEReportingRequest = new CreateEReportingRequest(); // CreateEReportingRequest | 
    String filename = "filename_example"; // String | Output filename (default: ereporting_{reportId}.xml)
    try {
      apiInstance.generateEreportingDownloadApiV1EreportingGenerateDownloadPost(createEReportingRequest, filename);
    } catch (ApiException e) {
      System.err.println("Exception when calling EReportingApi#generateEreportingDownloadApiV1EreportingGenerateDownloadPost");
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
| **createEReportingRequest** | [**CreateEReportingRequest**](CreateEReportingRequest.md)|  | |
| **filename** | **String**| Output filename (default: ereporting_{reportId}.xml) | [optional] |

### Return type

null (empty response body)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request data |  -  |
| **422** | Validation error |  -  |
| **500** | Internal server error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="listCategoryCodesApiV1EreportingCategoryCodesGet"></a>
# **listCategoryCodesApiV1EreportingCategoryCodesGet**
> Map&lt;String, Object&gt; listCategoryCodesApiV1EreportingCategoryCodesGet()

List PPF-compliant category codes

Returns the list of valid CategoryCode values (TT-81) for e-reporting transactions.  Source: Annexe 6 - Format sémantique FE e-reporting v1.9

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EReportingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");

    EReportingApi apiInstance = new EReportingApi(defaultClient);
    try {
      Map<String, Object> result = apiInstance.listCategoryCodesApiV1EreportingCategoryCodesGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EReportingApi#listCategoryCodesApiV1EreportingCategoryCodesGet");
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

**Map&lt;String, Object&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request data |  -  |
| **422** | Validation error |  -  |
| **500** | Internal server error |  -  |

<a id="listFlowTypesApiV1EreportingFlowTypesGet"></a>
# **listFlowTypesApiV1EreportingFlowTypesGet**
> Map&lt;String, Object&gt; listFlowTypesApiV1EreportingFlowTypesGet()

List available flow types

Returns the list of supported e-reporting flow types with descriptions.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EReportingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");

    EReportingApi apiInstance = new EReportingApi(defaultClient);
    try {
      Map<String, Object> result = apiInstance.listFlowTypesApiV1EreportingFlowTypesGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EReportingApi#listFlowTypesApiV1EreportingFlowTypesGet");
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

**Map&lt;String, Object&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request data |  -  |
| **422** | Validation error |  -  |
| **500** | Internal server error |  -  |

<a id="submitAggregatedEreportingApiV1EreportingSubmitAggregatedPost"></a>
# **submitAggregatedEreportingApiV1EreportingSubmitAggregatedPost**
> SubmitEReportingResponse submitAggregatedEreportingApiV1EreportingSubmitAggregatedPost(submitAggregatedReportRequest)

Submit aggregated e-reporting to PA/PDP

Generate and submit a PPF-compliant aggregated e-reporting to a PA/PDP.  Combines generation and submission in a single call. Automatically determines the AFNOR FlowType based on content.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EReportingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    EReportingApi apiInstance = new EReportingApi(defaultClient);
    SubmitAggregatedReportRequest submitAggregatedReportRequest = new SubmitAggregatedReportRequest(); // SubmitAggregatedReportRequest | 
    try {
      SubmitEReportingResponse result = apiInstance.submitAggregatedEreportingApiV1EreportingSubmitAggregatedPost(submitAggregatedReportRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EReportingApi#submitAggregatedEreportingApiV1EreportingSubmitAggregatedPost");
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
| **submitAggregatedReportRequest** | [**SubmitAggregatedReportRequest**](SubmitAggregatedReportRequest.md)|  | |

### Return type

[**SubmitEReportingResponse**](SubmitEReportingResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request data |  -  |
| **422** | Validation error |  -  |
| **500** | Internal server error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="submitEreportingApiV1EreportingSubmitPost"></a>
# **submitEreportingApiV1EreportingSubmitPost**
> SubmitEReportingResponse submitEreportingApiV1EreportingSubmitPost(submitEReportingRequest)

Submit e-reporting to PA/PDP

Generate and submit e-reporting to a PA (Plateforme Agréée).  Authentication strategies (same as invoices): 1. **JWT with client_uid** (recommended): PDP credentials fetched from backend 2. **Zero-storage**: Provide pdpFlowServiceUrl, pdpClientId, pdpClientSecret in request  The e-reporting is submitted using the AFNOR Flow Service API with syntax&#x3D;FRR (FRench Reporting).

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EReportingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    EReportingApi apiInstance = new EReportingApi(defaultClient);
    SubmitEReportingRequest submitEReportingRequest = new SubmitEReportingRequest(); // SubmitEReportingRequest | 
    try {
      SubmitEReportingResponse result = apiInstance.submitEreportingApiV1EreportingSubmitPost(submitEReportingRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EReportingApi#submitEreportingApiV1EreportingSubmitPost");
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
| **submitEReportingRequest** | [**SubmitEReportingRequest**](SubmitEReportingRequest.md)|  | |

### Return type

[**SubmitEReportingResponse**](SubmitEReportingResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request data |  -  |
| **422** | Validation error |  -  |
| **500** | Internal server error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="submitXmlEreportingApiV1EreportingSubmitXmlPost"></a>
# **submitXmlEreportingApiV1EreportingSubmitXmlPost**
> SubmitEReportingResponse submitXmlEreportingApiV1EreportingSubmitXmlPost(xmlFile, trackingId, skipValidation, pdpFlowServiceUrl, pdpTokenUrl, pdpClientId, pdpClientSecret)

Submit pre-generated e-reporting XML

Submit a pre-generated e-reporting XML file directly to a PA/PDP.  This endpoint is designed for clients who generate their own PPF-compliant XML and only need FactPulse for the PDP submission.  **Process:** 1. Validates the XML against PPF XSD schemas 2. Determines the appropriate AFNOR FlowType 3. Submits to the configured PDP/PA 4. Returns the flowId for tracking  **Authentication:** Same strategies as /submit endpoint (JWT or zero-storage credentials).

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EReportingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    EReportingApi apiInstance = new EReportingApi(defaultClient);
    File xmlFile = new File("/path/to/file"); // File | E-reporting XML file
    String trackingId = "trackingId_example"; // String | 
    Boolean skipValidation = false; // Boolean | Skip XSD validation
    String pdpFlowServiceUrl = "pdpFlowServiceUrl_example"; // String | 
    String pdpTokenUrl = "pdpTokenUrl_example"; // String | 
    String pdpClientId = "pdpClientId_example"; // String | 
    String pdpClientSecret = "pdpClientSecret_example"; // String | 
    try {
      SubmitEReportingResponse result = apiInstance.submitXmlEreportingApiV1EreportingSubmitXmlPost(xmlFile, trackingId, skipValidation, pdpFlowServiceUrl, pdpTokenUrl, pdpClientId, pdpClientSecret);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EReportingApi#submitXmlEreportingApiV1EreportingSubmitXmlPost");
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
| **xmlFile** | **File**| E-reporting XML file | |
| **trackingId** | **String**|  | [optional] |
| **skipValidation** | **Boolean**| Skip XSD validation | [optional] [default to false] |
| **pdpFlowServiceUrl** | **String**|  | [optional] |
| **pdpTokenUrl** | **String**|  | [optional] |
| **pdpClientId** | **String**|  | [optional] |
| **pdpClientSecret** | **String**|  | [optional] |

### Return type

[**SubmitEReportingResponse**](SubmitEReportingResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request data |  -  |
| **422** | Validation error |  -  |
| **500** | Internal server error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="validateAggregatedEreportingApiV1EreportingValidateAggregatedPost"></a>
# **validateAggregatedEreportingApiV1EreportingValidateAggregatedPost**
> Map&lt;String, Object&gt; validateAggregatedEreportingApiV1EreportingValidateAggregatedPost(createAggregatedReportRequest)

Validate aggregated e-reporting data

Validates aggregated e-reporting data without generating XML.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EReportingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    EReportingApi apiInstance = new EReportingApi(defaultClient);
    CreateAggregatedReportRequest createAggregatedReportRequest = new CreateAggregatedReportRequest(); // CreateAggregatedReportRequest | 
    try {
      Map<String, Object> result = apiInstance.validateAggregatedEreportingApiV1EreportingValidateAggregatedPost(createAggregatedReportRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EReportingApi#validateAggregatedEreportingApiV1EreportingValidateAggregatedPost");
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
| **createAggregatedReportRequest** | [**CreateAggregatedReportRequest**](CreateAggregatedReportRequest.md)|  | |

### Return type

**Map&lt;String, Object&gt;**

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request data |  -  |
| **422** | Validation error |  -  |
| **500** | Internal server error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="validateEreportingApiV1EreportingValidatePost"></a>
# **validateEreportingApiV1EreportingValidatePost**
> ValidateEReportingResponse validateEreportingApiV1EreportingValidatePost(validateEReportingRequest)

Validate e-reporting data

Validate e-reporting data without generating or submitting.  Performs: - Schema validation - Business rule validation (correct flux type vs data) - Data consistency checks (tax totals, dates, etc.)  Returns validation errors and warnings.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EReportingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    EReportingApi apiInstance = new EReportingApi(defaultClient);
    ValidateEReportingRequest validateEReportingRequest = new ValidateEReportingRequest(); // ValidateEReportingRequest | 
    try {
      ValidateEReportingResponse result = apiInstance.validateEreportingApiV1EreportingValidatePost(validateEReportingRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EReportingApi#validateEreportingApiV1EreportingValidatePost");
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
| **validateEReportingRequest** | [**ValidateEReportingRequest**](ValidateEReportingRequest.md)|  | |

### Return type

[**ValidateEReportingResponse**](ValidateEReportingResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request data |  -  |
| **422** | Validation error |  -  |
| **500** | Internal server error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="validateXmlEreportingApiV1EreportingValidateXmlPost"></a>
# **validateXmlEreportingApiV1EreportingValidateXmlPost**
> Map&lt;String, Object&gt; validateXmlEreportingApiV1EreportingValidateXmlPost(xmlFile, validateBusinessRules)

Validate e-reporting XML against PPF XSD schemas and business rules

Validates an e-reporting XML file against:  1. **XSD schemas**: Official PPF e-reporting XSD (structure, types, cardinality) 2. **Business rules**: ISO codes and enum validation    - Currency codes (ISO 4217: EUR, USD, GBP, etc.)    - Country codes (ISO 3166-1 alpha-2: FR, DE, US, etc.)    - Scheme IDs (0009&#x3D;SIRET, 0002&#x3D;SIREN, etc.)    - Role codes (UNCL 3035: SE&#x3D;Seller, BY&#x3D;Buyer, WK&#x3D;Working party, etc.)  Returns validation status and detailed error messages if invalid.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EReportingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    EReportingApi apiInstance = new EReportingApi(defaultClient);
    File xmlFile = new File("/path/to/file"); // File | E-reporting XML file to validate
    Boolean validateBusinessRules = true; // Boolean | Also validate business rules (ISO codes, enums)
    try {
      Map<String, Object> result = apiInstance.validateXmlEreportingApiV1EreportingValidateXmlPost(xmlFile, validateBusinessRules);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EReportingApi#validateXmlEreportingApiV1EreportingValidateXmlPost");
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
| **xmlFile** | **File**| E-reporting XML file to validate | |
| **validateBusinessRules** | **Boolean**| Also validate business rules (ISO codes, enums) | [optional] [default to true] |

### Return type

**Map&lt;String, Object&gt;**

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request data |  -  |
| **422** | Validation error |  -  |
| **500** | Internal server error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

