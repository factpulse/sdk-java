# DownloadsApi

All URIs are relative to *https://factpulse.fr*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**checkFileApiV1DownloadDownloadIdHead**](DownloadsApi.md#checkFileApiV1DownloadDownloadIdHead) | **HEAD** /api/v1/download/{download_id} | Check if a file exists |
| [**downloadFileApiV1DownloadDownloadIdGet**](DownloadsApi.md#downloadFileApiV1DownloadDownloadIdGet) | **GET** /api/v1/download/{download_id} | Download a temporary file |


<a id="checkFileApiV1DownloadDownloadIdHead"></a>
# **checkFileApiV1DownloadDownloadIdHead**
> Object checkFileApiV1DownloadDownloadIdHead(downloadId)

Check if a file exists

Check if a temporary file exists and get its metadata without downloading.  Useful for: - Verifying a download URL is still valid - Getting file size before downloading - Checking expiration time  **Security**: Requires authentication, only file owner can check.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DownloadsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    DownloadsApi apiInstance = new DownloadsApi(defaultClient);
    String downloadId = "downloadId_example"; // String | 
    try {
      Object result = apiInstance.checkFileApiV1DownloadDownloadIdHead(downloadId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DownloadsApi#checkFileApiV1DownloadDownloadIdHead");
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
| **downloadId** | **String**|  | |

### Return type

**Object**

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | File exists |  -  |
| **401** | Authentication required |  -  |
| **403** | Access denied |  -  |
| **404** | File not found or expired |  -  |
| **422** | Validation Error |  -  |

<a id="downloadFileApiV1DownloadDownloadIdGet"></a>
# **downloadFileApiV1DownloadDownloadIdGet**
> Object downloadFileApiV1DownloadDownloadIdGet(downloadId, deleteAfter)

Download a temporary file

Download a file stored temporarily after asynchronous processing.  **Usage**: - This URL is provided in webhook notifications when using &#x60;webhook_mode: \&quot;download_url\&quot;&#x60; - Files are automatically deleted after 1 hour - Each file can only be downloaded until it expires  **Security**: - Requires a valid JWT token - Only the user who initiated the task can download the file

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DownloadsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    DownloadsApi apiInstance = new DownloadsApi(defaultClient);
    String downloadId = "downloadId_example"; // String | 
    Boolean deleteAfter = false; // Boolean | If true, delete the file after download (one-time download)
    try {
      Object result = apiInstance.downloadFileApiV1DownloadDownloadIdGet(downloadId, deleteAfter);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DownloadsApi#downloadFileApiV1DownloadDownloadIdGet");
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
| **downloadId** | **String**|  | |
| **deleteAfter** | **Boolean**| If true, delete the file after download (one-time download) | [optional] [default to false] |

### Return type

**Object**

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, application/pdf, application/xml, application/octet-stream

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | File content |  -  |
| **401** | Authentication required |  -  |
| **403** | Access denied - file belongs to another user |  -  |
| **404** | File not found or expired |  -  |
| **422** | Validation Error |  -  |

