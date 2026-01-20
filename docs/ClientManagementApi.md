# ClientManagementApi

All URIs are relative to *https://factpulse.fr*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**activateClientApiV1ClientsUidActiverPost**](ClientManagementApi.md#activateClientApiV1ClientsUidActiverPost) | **POST** /api/v1/clients/{uid}/activer | Activate a client |
| [**createClientApiV1ClientsPost**](ClientManagementApi.md#createClientApiV1ClientsPost) | **POST** /api/v1/clients | Create a client |
| [**deactivateClientApiV1ClientsUidDesactiverPost**](ClientManagementApi.md#deactivateClientApiV1ClientsUidDesactiverPost) | **POST** /api/v1/clients/{uid}/desactiver | Deactivate a client |
| [**getClientApiV1ClientsUidGet**](ClientManagementApi.md#getClientApiV1ClientsUidGet) | **GET** /api/v1/clients/{uid} | Get client details |
| [**getPdpConfigApiV1ClientsUidPdpConfigGet**](ClientManagementApi.md#getPdpConfigApiV1ClientsUidPdpConfigGet) | **GET** /api/v1/clients/{uid}/pdp-config | Get client PDP configuration |
| [**listClientsApiV1ClientsGet**](ClientManagementApi.md#listClientsApiV1ClientsGet) | **GET** /api/v1/clients | List clients |
| [**updateClientApiV1ClientsUidPatch**](ClientManagementApi.md#updateClientApiV1ClientsUidPatch) | **PATCH** /api/v1/clients/{uid} | Update a client |
| [**updatePdpConfigApiV1ClientsUidPdpConfigPut**](ClientManagementApi.md#updatePdpConfigApiV1ClientsUidPdpConfigPut) | **PUT** /api/v1/clients/{uid}/pdp-config | Configure client PDP |


<a id="activateClientApiV1ClientsUidActiverPost"></a>
# **activateClientApiV1ClientsUidActiverPost**
> ClientActivateResponse activateClientApiV1ClientsUidActiverPost(uid)

Activate a client

Activate a deactivated client.  **Scope**: Client level (JWT with client_uid that must match {uid})

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ClientManagementApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ClientManagementApi apiInstance = new ClientManagementApi(defaultClient);
    UUID uid = UUID.randomUUID(); // UUID | 
    try {
      ClientActivateResponse result = apiInstance.activateClientApiV1ClientsUidActiverPost(uid);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ClientManagementApi#activateClientApiV1ClientsUidActiverPost");
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
| **uid** | **UUID**|  | |

### Return type

[**ClientActivateResponse**](ClientActivateResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request |  -  |
| **401** | Missing or invalid JWT token |  -  |
| **403** | Access denied |  -  |
| **404** | Client not found |  -  |
| **500** | Server error |  -  |
| **422** | Validation Error |  -  |

<a id="createClientApiV1ClientsPost"></a>
# **createClientApiV1ClientsPost**
> ClientDetail createClientApiV1ClientsPost(clientCreateRequest)

Create a client

Create a new client for the account.  **Scope**: Account level (JWT without client_uid)  **Fields**: - &#x60;name&#x60;: Client name (required) - &#x60;description&#x60;: Optional description - &#x60;siret&#x60;: Optional SIRET (14 digits)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ClientManagementApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ClientManagementApi apiInstance = new ClientManagementApi(defaultClient);
    ClientCreateRequest clientCreateRequest = new ClientCreateRequest(); // ClientCreateRequest | 
    try {
      ClientDetail result = apiInstance.createClientApiV1ClientsPost(clientCreateRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ClientManagementApi#createClientApiV1ClientsPost");
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
| **clientCreateRequest** | [**ClientCreateRequest**](ClientCreateRequest.md)|  | |

### Return type

[**ClientDetail**](ClientDetail.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Successful Response |  -  |
| **400** | Invalid request |  -  |
| **401** | Missing or invalid JWT token |  -  |
| **403** | Access denied |  -  |
| **404** | Client not found |  -  |
| **500** | Server error |  -  |
| **422** | Validation Error |  -  |

<a id="deactivateClientApiV1ClientsUidDesactiverPost"></a>
# **deactivateClientApiV1ClientsUidDesactiverPost**
> ClientActivateResponse deactivateClientApiV1ClientsUidDesactiverPost(uid)

Deactivate a client

Deactivate an active client.  **Scope**: Client level (JWT with client_uid that must match {uid})  **Note**: A deactivated client cannot be used for API calls (AFNOR, Chorus Pro, etc.).

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ClientManagementApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ClientManagementApi apiInstance = new ClientManagementApi(defaultClient);
    UUID uid = UUID.randomUUID(); // UUID | 
    try {
      ClientActivateResponse result = apiInstance.deactivateClientApiV1ClientsUidDesactiverPost(uid);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ClientManagementApi#deactivateClientApiV1ClientsUidDesactiverPost");
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
| **uid** | **UUID**|  | |

### Return type

[**ClientActivateResponse**](ClientActivateResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request |  -  |
| **401** | Missing or invalid JWT token |  -  |
| **403** | Access denied |  -  |
| **404** | Client not found |  -  |
| **500** | Server error |  -  |
| **422** | Validation Error |  -  |

<a id="getClientApiV1ClientsUidGet"></a>
# **getClientApiV1ClientsUidGet**
> ClientDetail getClientApiV1ClientsUidGet(uid)

Get client details

Get details of a client.  **Scope**: Client level (JWT with client_uid that must match {uid})  **Security**: If the JWT contains a client_uid, it must match the {uid} in the URL, otherwise a 403 error is returned.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ClientManagementApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ClientManagementApi apiInstance = new ClientManagementApi(defaultClient);
    UUID uid = UUID.randomUUID(); // UUID | 
    try {
      ClientDetail result = apiInstance.getClientApiV1ClientsUidGet(uid);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ClientManagementApi#getClientApiV1ClientsUidGet");
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
| **uid** | **UUID**|  | |

### Return type

[**ClientDetail**](ClientDetail.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request |  -  |
| **401** | Missing or invalid JWT token |  -  |
| **403** | Access denied |  -  |
| **404** | Client not found |  -  |
| **500** | Server error |  -  |
| **422** | Validation Error |  -  |

<a id="getPdpConfigApiV1ClientsUidPdpConfigGet"></a>
# **getPdpConfigApiV1ClientsUidPdpConfigGet**
> PDPConfigResponse getPdpConfigApiV1ClientsUidPdpConfigGet(uid)

Get client PDP configuration

Get the PDP (PA/PDP) configuration for a client.  **Scope**: Client level (JWT with client_uid that must match {uid})  **Security**: The client secret is never returned. Only a status (&#x60;secretStatus&#x60;) indicates whether a secret is configured.  **Response**: - If configured: all config details (URLs, client_id, secret status) - If not configured: &#x60;isConfigured: false&#x60; with a message

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ClientManagementApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ClientManagementApi apiInstance = new ClientManagementApi(defaultClient);
    UUID uid = UUID.randomUUID(); // UUID | 
    try {
      PDPConfigResponse result = apiInstance.getPdpConfigApiV1ClientsUidPdpConfigGet(uid);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ClientManagementApi#getPdpConfigApiV1ClientsUidPdpConfigGet");
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
| **uid** | **UUID**|  | |

### Return type

[**PDPConfigResponse**](PDPConfigResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request |  -  |
| **401** | Missing or invalid JWT token |  -  |
| **403** | Access denied |  -  |
| **404** | Client not found |  -  |
| **500** | Server error |  -  |
| **422** | Validation Error |  -  |

<a id="listClientsApiV1ClientsGet"></a>
# **listClientsApiV1ClientsGet**
> ClientListResponse listClientsApiV1ClientsGet(page, pageSize)

List clients

Paginated list of clients for the account.  **Scope**: Account level (JWT without client_uid)  **Pagination**: - &#x60;page&#x60;: Page number (default: 1) - &#x60;pageSize&#x60;: Page size (default: 20, max: 100)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ClientManagementApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ClientManagementApi apiInstance = new ClientManagementApi(defaultClient);
    Integer page = 1; // Integer | Page number
    Integer pageSize = 20; // Integer | Page size
    try {
      ClientListResponse result = apiInstance.listClientsApiV1ClientsGet(page, pageSize);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ClientManagementApi#listClientsApiV1ClientsGet");
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
| **page** | **Integer**| Page number | [optional] [default to 1] |
| **pageSize** | **Integer**| Page size | [optional] [default to 20] |

### Return type

[**ClientListResponse**](ClientListResponse.md)

### Authorization

[HTTPBearer](../README.md#HTTPBearer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful Response |  -  |
| **400** | Invalid request |  -  |
| **401** | Missing or invalid JWT token |  -  |
| **403** | Access denied |  -  |
| **404** | Client not found |  -  |
| **500** | Server error |  -  |
| **422** | Validation Error |  -  |

<a id="updateClientApiV1ClientsUidPatch"></a>
# **updateClientApiV1ClientsUidPatch**
> ClientDetail updateClientApiV1ClientsUidPatch(uid, clientUpdateRequest)

Update a client

Update client information (partial update).  **Scope**: Client level (JWT with client_uid that must match {uid})  **Updatable fields**: - &#x60;name&#x60;: Client name - &#x60;description&#x60;: Description - &#x60;siret&#x60;: SIRET (14 digits)  Only provided fields are updated.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ClientManagementApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ClientManagementApi apiInstance = new ClientManagementApi(defaultClient);
    UUID uid = UUID.randomUUID(); // UUID | 
    ClientUpdateRequest clientUpdateRequest = new ClientUpdateRequest(); // ClientUpdateRequest | 
    try {
      ClientDetail result = apiInstance.updateClientApiV1ClientsUidPatch(uid, clientUpdateRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ClientManagementApi#updateClientApiV1ClientsUidPatch");
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
| **uid** | **UUID**|  | |
| **clientUpdateRequest** | [**ClientUpdateRequest**](ClientUpdateRequest.md)|  | |

### Return type

[**ClientDetail**](ClientDetail.md)

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
| **401** | Missing or invalid JWT token |  -  |
| **403** | Access denied |  -  |
| **404** | Client not found |  -  |
| **500** | Server error |  -  |
| **422** | Validation Error |  -  |

<a id="updatePdpConfigApiV1ClientsUidPdpConfigPut"></a>
# **updatePdpConfigApiV1ClientsUidPdpConfigPut**
> PDPConfigResponse updatePdpConfigApiV1ClientsUidPdpConfigPut(uid, pdPConfigUpdateRequest)

Configure client PDP

Configure or update the PDP (PA/PDP) configuration for a client.  **Scope**: Client level (JWT with client_uid that must match {uid})  **Required fields**: - &#x60;flowServiceUrl&#x60;: PDP Flow Service URL - &#x60;tokenUrl&#x60;: PDP OAuth token URL - &#x60;oauthClientId&#x60;: OAuth Client ID - &#x60;clientSecret&#x60;: OAuth Client Secret (sent but NEVER returned)  **Optional fields**: - &#x60;isActive&#x60;: Enable/disable the config (default: true) - &#x60;modeSandbox&#x60;: Sandbox mode (default: false)  **Security**: The &#x60;clientSecret&#x60; is stored encrypted on Django side and is never returned in API responses.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ClientManagementApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://factpulse.fr");
    
    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ClientManagementApi apiInstance = new ClientManagementApi(defaultClient);
    UUID uid = UUID.randomUUID(); // UUID | 
    PDPConfigUpdateRequest pdPConfigUpdateRequest = new PDPConfigUpdateRequest(); // PDPConfigUpdateRequest | 
    try {
      PDPConfigResponse result = apiInstance.updatePdpConfigApiV1ClientsUidPdpConfigPut(uid, pdPConfigUpdateRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ClientManagementApi#updatePdpConfigApiV1ClientsUidPdpConfigPut");
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
| **uid** | **UUID**|  | |
| **pdPConfigUpdateRequest** | [**PDPConfigUpdateRequest**](PDPConfigUpdateRequest.md)|  | |

### Return type

[**PDPConfigResponse**](PDPConfigResponse.md)

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
| **401** | Missing or invalid JWT token |  -  |
| **403** | Access denied |  -  |
| **404** | Client not found |  -  |
| **500** | Server error |  -  |
| **422** | Validation Error |  -  |

