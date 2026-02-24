# ReferencesApi

All URIs are relative to *https://factpulse.fr*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getVatexCodesApiV1ReferencesVatexCodesGet**](ReferencesApi.md#getVatexCodesApiV1ReferencesVatexCodesGet) | **GET** /api/v1/references/vatex-codes | VATEX exemption reason codes |


<a id="getVatexCodesApiV1ReferencesVatexCodesGet"></a>
# **getVatexCodesApiV1ReferencesVatexCodesGet**
> VATEXCodesResponse getVatexCodesApiV1ReferencesVatexCodesGet(category)

VATEX exemption reason codes

Returns the list of VATEX (VAT exemption reason) codes from the Peppol BIS Billing 3.0 code list. Source: https://docs.peppol.eu/poacc/billing/3.0/codelist/vatex/

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ReferencesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");

    ReferencesApi apiInstance = new ReferencesApi(defaultClient);
    String category = "category_example"; // String | Filter by VAT category code (E, AE, K, G, O).
    try {
      VATEXCodesResponse result = apiInstance.getVatexCodesApiV1ReferencesVatexCodesGet(category);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ReferencesApi#getVatexCodesApiV1ReferencesVatexCodesGet");
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
| **category** | **String**| Filter by VAT category code (E, AE, K, G, O). | [optional] |

### Return type

[**VATEXCodesResponse**](VATEXCodesResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | List of VATEX codes with their descriptions and associated VAT categories. |  -  |
| **422** | Validation Error |  -  |

