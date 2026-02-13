# ParseApi

All URIs are relative to *https://factpulse.fr*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**parseFacturxAsyncApiV1ProcessingParseFacturxAsyncPost**](ParseApi.md#parseFacturxAsyncApiV1ProcessingParseFacturxAsyncPost) | **POST** /api/v1/processing/parse-facturx/async | Parse Factur-X XML or PDF (async) |
| [**parseFacturxSyncApiV1ProcessingParseFacturxPost**](ParseApi.md#parseFacturxSyncApiV1ProcessingParseFacturxPost) | **POST** /api/v1/processing/parse-facturx | Parse CII, UBL or Factur-X PDF (sync) |


<a id="parseFacturxAsyncApiV1ProcessingParseFacturxAsyncPost"></a>
# **parseFacturxAsyncApiV1ProcessingParseFacturxAsyncPost**
> TaskResponse parseFacturxAsyncApiV1ProcessingParseFacturxAsyncPost(_file, callbackUrl)

Parse Factur-X XML or PDF (async)

Parse a Factur-X file asynchronously using a Celery task.  **Use case:** For large files or when you want non-blocking processing.  **Workflow:** 1. Submit file with this endpoint -&gt; receive &#x60;task_id&#x60; 2. Poll &#x60;/tasks/{task_id}/status&#x60; for result 3. Result contains &#x60;invoice&#x60; and &#x60;detected_profile&#x60;  **Optional:** Provide &#x60;callback_url&#x60; to receive a webhook when parsing completes.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ParseApi;

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

    ParseApi apiInstance = new ParseApi(defaultClient);
    File _file = new File("/path/to/file"); // File | Factur-X PDF or XML file to parse
    String callbackUrl = "callbackUrl_example"; // String | 
    try {
      TaskResponse result = apiInstance.parseFacturxAsyncApiV1ProcessingParseFacturxAsyncPost(_file, callbackUrl);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ParseApi#parseFacturxAsyncApiV1ProcessingParseFacturxAsyncPost");
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
| **_file** | **File**| Factur-X PDF or XML file to parse | |
| **callbackUrl** | **String**|  | [optional] |

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
| **202** | Task created |  -  |
| **422** | Validation Error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

<a id="parseFacturxSyncApiV1ProcessingParseFacturxPost"></a>
# **parseFacturxSyncApiV1ProcessingParseFacturxPost**
> ParseFacturXResponse parseFacturxSyncApiV1ProcessingParseFacturxPost(_file)

Parse CII, UBL or Factur-X PDF (sync)

Parse a CII XML, UBL XML, or Factur-X PDF and extract the invoice data as a FacturXInvoice model.  **Use cases:** - Extract invoice data from received invoices (any format) - Round-trip: parse an existing invoice, modify it, and regenerate in another format - Convert between CII and UBL via the FacturXInvoice pivot model  **Supported formats:** - Factur-X PDF (PDF/A-3 with embedded CII XML) - CII XML (UN/CEFACT Cross-Industry Invoice) - UBL 2.1 XML (OASIS Invoice or CreditNote)  **Returns:** - &#x60;invoice&#x60;: Complete FacturXInvoice data (can be used with /generate-invoice) - &#x60;detected_profile&#x60;: Factur-X profile (MINIMUM, BASIC, EN16931, EXTENDED)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ParseApi;

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

    ParseApi apiInstance = new ParseApi(defaultClient);
    File _file = new File("/path/to/file"); // File | Factur-X PDF or XML file to parse
    try {
      ParseFacturXResponse result = apiInstance.parseFacturxSyncApiV1ProcessingParseFacturxPost(_file);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ParseApi#parseFacturxSyncApiV1ProcessingParseFacturxPost");
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
| **_file** | **File**| Factur-X PDF or XML file to parse | |

### Return type

[**ParseFacturXResponse**](ParseFacturXResponse.md)

### Authorization

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Invoice successfully parsed |  -  |
| **400** | Invalid file format or parsing error |  -  |
| **422** | Validation Error |  -  |
| **401** | Authentication required - Invalid or missing JWT token |  -  |

