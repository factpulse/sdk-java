# FacturXPdfXmlVerificationApi

All URIs are relative to *https://factpulse.fr*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getVerificationStatusApiV1VerificationVerifyAsyncTaskIdStatusGet**](FacturXPdfXmlVerificationApi.md#getVerificationStatusApiV1VerificationVerifyAsyncTaskIdStatusGet) | **GET** /api/v1/verification/verify-async/{task_id}/status | Get status of an asynchronous verification |
| [**getVerificationTypedStatusApiV1VerificationVerifyAsyncTaskIdTypedStatusGet**](FacturXPdfXmlVerificationApi.md#getVerificationTypedStatusApiV1VerificationVerifyAsyncTaskIdTypedStatusGet) | **GET** /api/v1/verification/verify-async/{task_id}/typed-status | Get typed status of an asynchronous verification |
| [**verifyPdfAsyncApiV1VerificationVerifyAsyncPost**](FacturXPdfXmlVerificationApi.md#verifyPdfAsyncApiV1VerificationVerifyAsyncPost) | **POST** /api/v1/verification/verify-async | Verify PDF/XML Factur-X compliance (asynchronous) |
| [**verifyPdfSyncApiV1VerificationVerifyPost**](FacturXPdfXmlVerificationApi.md#verifyPdfSyncApiV1VerificationVerifyPost) | **POST** /api/v1/verification/verify | Verify PDF/XML Factur-X compliance (synchronous) |


<a id="getVerificationStatusApiV1VerificationVerifyAsyncTaskIdStatusGet"></a>
# **getVerificationStatusApiV1VerificationVerifyAsyncTaskIdStatusGet**
> AsyncTaskStatus getVerificationStatusApiV1VerificationVerifyAsyncTaskIdStatusGet(taskId)

Get status of an asynchronous verification

Retrieves the status and result of an asynchronous verification task.  **Possible statuses:** - &#x60;PENDING&#x60;: Task waiting in queue - &#x60;STARTED&#x60;: Task currently running - &#x60;SUCCESS&#x60;: Task completed successfully (see &#x60;result&#x60;) - &#x60;FAILURE&#x60;: System error (unhandled exception)  **Note:** The &#x60;result.status&#x60; field can be \&quot;SUCCESS\&quot; or \&quot;ERROR\&quot; independently of Celery status (which will always be SUCCESS if the task ran).

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FacturXPdfXmlVerificationApi;

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

    FacturXPdfXmlVerificationApi apiInstance = new FacturXPdfXmlVerificationApi(defaultClient);
    String taskId = "taskId_example"; // String | Celery task ID returned by /verify-async endpoint
    try {
      AsyncTaskStatus result = apiInstance.getVerificationStatusApiV1VerificationVerifyAsyncTaskIdStatusGet(taskId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FacturXPdfXmlVerificationApi#getVerificationStatusApiV1VerificationVerifyAsyncTaskIdStatusGet");
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
| **taskId** | **String**| Celery task ID returned by /verify-async endpoint | |

### Return type

[**AsyncTaskStatus**](AsyncTaskStatus.md)

### Authorization

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **422** | Validation Error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="getVerificationTypedStatusApiV1VerificationVerifyAsyncTaskIdTypedStatusGet"></a>
# **getVerificationTypedStatusApiV1VerificationVerifyAsyncTaskIdTypedStatusGet**
> VerificationTypedTaskStatus getVerificationTypedStatusApiV1VerificationVerifyAsyncTaskIdTypedStatusGet(taskId)

Get typed status of an asynchronous verification

Typed status endpoint for async verification tasks.  Returns a strongly-typed &#x60;result&#x60; (discriminated on &#x60;result.status&#x60;): - **SUCCESS**: &#x60;VerificationSuccessTaskResult&#x60; with &#x60;verification_result&#x60; - **ERROR**: &#x60;TaskErrorResult&#x60; in AFNOR format

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FacturXPdfXmlVerificationApi;

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

    FacturXPdfXmlVerificationApi apiInstance = new FacturXPdfXmlVerificationApi(defaultClient);
    String taskId = "taskId_example"; // String | Celery task ID returned by /verify-async endpoint
    try {
      VerificationTypedTaskStatus result = apiInstance.getVerificationTypedStatusApiV1VerificationVerifyAsyncTaskIdTypedStatusGet(taskId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FacturXPdfXmlVerificationApi#getVerificationTypedStatusApiV1VerificationVerifyAsyncTaskIdTypedStatusGet");
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
| **taskId** | **String**| Celery task ID returned by /verify-async endpoint | |

### Return type

[**VerificationTypedTaskStatus**](VerificationTypedTaskStatus.md)

### Authorization

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **422** | Validation Error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="verifyPdfAsyncApiV1VerificationVerifyAsyncPost"></a>
# **verifyPdfAsyncApiV1VerificationVerifyAsyncPost**
> TaskResponse verifyPdfAsyncApiV1VerificationVerifyAsyncPost(pdfFile, forceOcr, callbackUrl, webhookMode)

Verify PDF/XML Factur-X compliance (asynchronous)

Verifies PDF/XML Factur-X compliance asynchronously.  **IMPORTANT**: Only Factur-X PDFs (with embedded XML) are accepted. PDFs without Factur-X XML will return a &#x60;NOT_FACTURX&#x60; error in the result.  This version uses a Celery task and can call the OCR service if the PDF is an image or if &#x60;force_ocr&#x3D;true&#x60;.  **Returns immediately** a task ID. Use &#x60;/verify-async/{task_id}/status&#x60; to retrieve the result.  **Verification principle (Factur-X 1.08):** - Principle #2: XML can only contain info present in the PDF - Principle #4: All XML info must be present and compliant in the PDF  **Verified fields:** - Identification: BT-1 (invoice #), BT-2 (date), BT-3 (type), BT-5 (currency), BT-23 (framework) - Seller: BT-27 (name), BT-29 (SIRET), BT-30 (SIREN), BT-31 (VAT) - Buyer: BT-44 (name), BT-46 (SIRET), BT-47 (SIREN), BT-48 (VAT) - Amounts: BT-109 (excl. tax), BT-110 (VAT), BT-112 (incl. tax), BT-115 (amount due) - VAT breakdown: BT-116, BT-117, BT-118, BT-119 - Invoice lines: BT-153, BT-129, BT-146, BT-131 - Mandatory notes: PMT, PMD, AAB - Rule BR-FR-09: SIRET/SIREN consistency  **Advantages over synchronous version:** - OCR support for image PDFs (via DocTR service) - Longer timeout for large documents - Doesn&#39;t block the server  ## Webhook notification (recommended)  Instead of polling, you can receive a webhook notification when verification completes:  &#x60;&#x60;&#x60; callback_url&#x3D;https://your-server.com/webhook &#x60;&#x60;&#x60;  The webhook will POST a JSON payload with: - &#x60;event_type&#x60;: &#x60;verification.completed&#x60; or &#x60;verification.failed&#x60; - &#x60;data.is_compliant&#x60;: Whether the PDF/XML are consistent - &#x60;data.compliance_score&#x60;: Compliance score (0-1) - &#x60;X-Webhook-Signature&#x60; header for HMAC verification

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FacturXPdfXmlVerificationApi;

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

    FacturXPdfXmlVerificationApi apiInstance = new FacturXPdfXmlVerificationApi(defaultClient);
    File pdfFile = new File("/path/to/file"); // File | Factur-X PDF file to verify
    Boolean forceOcr = false; // Boolean | Force OCR usage even if PDF contains native text
    String callbackUrl = "callbackUrl_example"; // String | 
    String webhookMode = "inline"; // String | Webhook content delivery: 'inline' (base64 in payload) or 'download_url' (temporary URL, 1h TTL)
    try {
      TaskResponse result = apiInstance.verifyPdfAsyncApiV1VerificationVerifyAsyncPost(pdfFile, forceOcr, callbackUrl, webhookMode);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FacturXPdfXmlVerificationApi#verifyPdfAsyncApiV1VerificationVerifyAsyncPost");
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
| **pdfFile** | **File**| Factur-X PDF file to verify | |
| **forceOcr** | **Boolean**| Force OCR usage even if PDF contains native text | [optional] [default to false] |
| **callbackUrl** | **String**|  | [optional] |
| **webhookMode** | **String**| Webhook content delivery: &#39;inline&#39; (base64 in payload) or &#39;download_url&#39; (temporary URL, 1h TTL) | [optional] [default to inline] |

### Return type

[**TaskResponse**](TaskResponse.md)

### Authorization

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **202** | Successful Response |  -  |
| **422** | Validation Error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="verifyPdfSyncApiV1VerificationVerifyPost"></a>
# **verifyPdfSyncApiV1VerificationVerifyPost**
> VerificationSuccessResponse verifyPdfSyncApiV1VerificationVerifyPost(pdfFile)

Verify PDF/XML Factur-X compliance (synchronous)

Verifies compliance between the PDF and its embedded Factur-X XML.  **IMPORTANT**: Only Factur-X PDFs (with embedded XML) are accepted. PDFs without Factur-X XML will be rejected with a 400 error.  This synchronous version uses only native PDF extraction (pdfplumber). For image PDFs requiring OCR, use the &#x60;/verify-async&#x60; endpoint.  **Verification principle (Factur-X 1.08):** - Principle #2: XML can only contain info present in the PDF - Principle #4: All XML info must be present and compliant in the PDF  **Verified fields:** - Identification: BT-1 (invoice #), BT-2 (date), BT-3 (type), BT-5 (currency), BT-23 (framework) - Seller: BT-27 (name), BT-29 (SIRET), BT-30 (SIREN), BT-31 (VAT) - Buyer: BT-44 (name), BT-46 (SIRET), BT-47 (SIREN), BT-48 (VAT) - Amounts: BT-109 (excl. tax), BT-110 (VAT), BT-112 (incl. tax), BT-115 (amount due) - VAT breakdown: BT-116, BT-117, BT-118, BT-119 - Invoice lines: BT-153, BT-129, BT-146, BT-131 - Mandatory notes: PMT, PMD, AAB - Rule BR-FR-09: SIRET/SIREN consistency

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FacturXPdfXmlVerificationApi;

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

    FacturXPdfXmlVerificationApi apiInstance = new FacturXPdfXmlVerificationApi(defaultClient);
    File pdfFile = new File("/path/to/file"); // File | Factur-X PDF file to verify
    try {
      VerificationSuccessResponse result = apiInstance.verifyPdfSyncApiV1VerificationVerifyPost(pdfFile);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FacturXPdfXmlVerificationApi#verifyPdfSyncApiV1VerificationVerifyPost");
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
| **pdfFile** | **File**| Factur-X PDF file to verify | |

### Return type

[**VerificationSuccessResponse**](VerificationSuccessResponse.md)

### Authorization

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Verification successful |  -  |
| **400** | Verification error (non Factur-X PDF, invalid, etc.) |  -  |
| **413** | PDF too large or too many pages |  -  |
| **422** | Validation Error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

