# ClientManagementApi

All URIs are relative to *https://factpulse.fr*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**activateClientApiV1ClientsUidActiverPost**](ClientManagementApi.md#activateClientApiV1ClientsUidActiverPost) | **POST** /api/v1/clients/{uid}/activer | Activate a client |
| [**createClientApiV1ClientsPost**](ClientManagementApi.md#createClientApiV1ClientsPost) | **POST** /api/v1/clients | Create a client |
| [**deactivateClientApiV1ClientsUidDesactiverPost**](ClientManagementApi.md#deactivateClientApiV1ClientsUidDesactiverPost) | **POST** /api/v1/clients/{uid}/desactiver | Deactivate a client |
| [**deleteWebhookSecretApiV1ClientsUidWebhookSecretDelete**](ClientManagementApi.md#deleteWebhookSecretApiV1ClientsUidWebhookSecretDelete) | **DELETE** /api/v1/clients/{uid}/webhook-secret | Delete webhook secret |
| [**generateWebhookSecretApiV1ClientsUidWebhookSecretGeneratePost**](ClientManagementApi.md#generateWebhookSecretApiV1ClientsUidWebhookSecretGeneratePost) | **POST** /api/v1/clients/{uid}/webhook-secret/generate | Generate webhook secret |
| [**getClientApiV1ClientsUidGet**](ClientManagementApi.md#getClientApiV1ClientsUidGet) | **GET** /api/v1/clients/{uid} | Get client details |
| [**getPdpConfigApiV1ClientsUidPdpConfigGet**](ClientManagementApi.md#getPdpConfigApiV1ClientsUidPdpConfigGet) | **GET** /api/v1/clients/{uid}/pdp-config | Get client PDP configuration |
| [**getWebhookSecretStatusApiV1ClientsUidWebhookSecretStatusGet**](ClientManagementApi.md#getWebhookSecretStatusApiV1ClientsUidWebhookSecretStatusGet) | **GET** /api/v1/clients/{uid}/webhook-secret/status | Get webhook secret status |
| [**listClientsApiV1ClientsGet**](ClientManagementApi.md#listClientsApiV1ClientsGet) | **GET** /api/v1/clients | List clients |
| [**rotateEncryptionKeyApiV1ClientsUidRotateEncryptionKeyPost**](ClientManagementApi.md#rotateEncryptionKeyApiV1ClientsUidRotateEncryptionKeyPost) | **POST** /api/v1/clients/{uid}/rotate-encryption-key | Rotate client encryption key |
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
    
    // Configure API key authorization: APIKeyHeader
    ApiKeyAuth APIKeyHeader = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyHeader");
    APIKeyHeader.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyHeader.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ClientManagementApi apiInstance = new ClientManagementApi(defaultClient);
    UUID uid = UUID.randomUUID(); // UUID | Client unique identifier (UUID)
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
| **uid** | **UUID**| Client unique identifier (UUID) | |

### Return type

[**ClientActivateResponse**](ClientActivateResponse.md)

### Authorization

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

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
    
    // Configure API key authorization: APIKeyHeader
    ApiKeyAuth APIKeyHeader = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyHeader");
    APIKeyHeader.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyHeader.setApiKeyPrefix("Token");

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

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

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
    
    // Configure API key authorization: APIKeyHeader
    ApiKeyAuth APIKeyHeader = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyHeader");
    APIKeyHeader.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyHeader.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ClientManagementApi apiInstance = new ClientManagementApi(defaultClient);
    UUID uid = UUID.randomUUID(); // UUID | Client unique identifier (UUID)
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
| **uid** | **UUID**| Client unique identifier (UUID) | |

### Return type

[**ClientActivateResponse**](ClientActivateResponse.md)

### Authorization

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

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

<a id="deleteWebhookSecretApiV1ClientsUidWebhookSecretDelete"></a>
# **deleteWebhookSecretApiV1ClientsUidWebhookSecretDelete**
> WebhookSecretDeleteResponse deleteWebhookSecretApiV1ClientsUidWebhookSecretDelete(uid)

Delete webhook secret

Delete the webhook secret for a client.  **Scope**: Client level (JWT with client_uid that must match {uid})  **After deletion**: Webhooks for this client will use the global server key for HMAC signature instead of a client-specific key.

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
    
    // Configure API key authorization: APIKeyHeader
    ApiKeyAuth APIKeyHeader = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyHeader");
    APIKeyHeader.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyHeader.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ClientManagementApi apiInstance = new ClientManagementApi(defaultClient);
    UUID uid = UUID.randomUUID(); // UUID | Client unique identifier (UUID)
    try {
      WebhookSecretDeleteResponse result = apiInstance.deleteWebhookSecretApiV1ClientsUidWebhookSecretDelete(uid);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ClientManagementApi#deleteWebhookSecretApiV1ClientsUidWebhookSecretDelete");
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
| **uid** | **UUID**| Client unique identifier (UUID) | |

### Return type

[**WebhookSecretDeleteResponse**](WebhookSecretDeleteResponse.md)

### Authorization

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

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

<a id="generateWebhookSecretApiV1ClientsUidWebhookSecretGeneratePost"></a>
# **generateWebhookSecretApiV1ClientsUidWebhookSecretGeneratePost**
> WebhookSecretGenerateResponse generateWebhookSecretApiV1ClientsUidWebhookSecretGeneratePost(uid)

Generate webhook secret

Generate or regenerate the webhook secret for a client.  **Scope**: Client level (JWT with client_uid that must match {uid})  **Important**: Save the returned secret immediately - it will never be shown again. The secret is used to sign webhooks sent by the server (HMAC-SHA256).  **If a secret already exists**: It will be replaced by the new one.

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
    
    // Configure API key authorization: APIKeyHeader
    ApiKeyAuth APIKeyHeader = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyHeader");
    APIKeyHeader.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyHeader.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ClientManagementApi apiInstance = new ClientManagementApi(defaultClient);
    UUID uid = UUID.randomUUID(); // UUID | Client unique identifier (UUID)
    try {
      WebhookSecretGenerateResponse result = apiInstance.generateWebhookSecretApiV1ClientsUidWebhookSecretGeneratePost(uid);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ClientManagementApi#generateWebhookSecretApiV1ClientsUidWebhookSecretGeneratePost");
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
| **uid** | **UUID**| Client unique identifier (UUID) | |

### Return type

[**WebhookSecretGenerateResponse**](WebhookSecretGenerateResponse.md)

### Authorization

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

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
    
    // Configure API key authorization: APIKeyHeader
    ApiKeyAuth APIKeyHeader = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyHeader");
    APIKeyHeader.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyHeader.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ClientManagementApi apiInstance = new ClientManagementApi(defaultClient);
    UUID uid = UUID.randomUUID(); // UUID | Client unique identifier (UUID)
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
| **uid** | **UUID**| Client unique identifier (UUID) | |

### Return type

[**ClientDetail**](ClientDetail.md)

### Authorization

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

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
    
    // Configure API key authorization: APIKeyHeader
    ApiKeyAuth APIKeyHeader = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyHeader");
    APIKeyHeader.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyHeader.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ClientManagementApi apiInstance = new ClientManagementApi(defaultClient);
    UUID uid = UUID.randomUUID(); // UUID | Client unique identifier (UUID)
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
| **uid** | **UUID**| Client unique identifier (UUID) | |

### Return type

[**PDPConfigResponse**](PDPConfigResponse.md)

### Authorization

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

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

<a id="getWebhookSecretStatusApiV1ClientsUidWebhookSecretStatusGet"></a>
# **getWebhookSecretStatusApiV1ClientsUidWebhookSecretStatusGet**
> WebhookSecretStatusResponse getWebhookSecretStatusApiV1ClientsUidWebhookSecretStatusGet(uid)

Get webhook secret status

Check if a webhook secret is configured for a client.  **Scope**: Client level (JWT with client_uid that must match {uid})  **Response**: - &#x60;hasSecret&#x60;: Whether a webhook secret is configured - &#x60;createdAt&#x60;: When the secret was created (if exists)  **Note**: The secret value is never returned, only its status.

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
    
    // Configure API key authorization: APIKeyHeader
    ApiKeyAuth APIKeyHeader = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyHeader");
    APIKeyHeader.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyHeader.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ClientManagementApi apiInstance = new ClientManagementApi(defaultClient);
    UUID uid = UUID.randomUUID(); // UUID | Client unique identifier (UUID)
    try {
      WebhookSecretStatusResponse result = apiInstance.getWebhookSecretStatusApiV1ClientsUidWebhookSecretStatusGet(uid);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ClientManagementApi#getWebhookSecretStatusApiV1ClientsUidWebhookSecretStatusGet");
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
| **uid** | **UUID**| Client unique identifier (UUID) | |

### Return type

[**WebhookSecretStatusResponse**](WebhookSecretStatusResponse.md)

### Authorization

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

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
    
    // Configure API key authorization: APIKeyHeader
    ApiKeyAuth APIKeyHeader = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyHeader");
    APIKeyHeader.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyHeader.setApiKeyPrefix("Token");

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

[APIKeyHeader](../README.md#APIKeyHeader), [HTTPBearer](../README.md#HTTPBearer)

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

<a id="rotateEncryptionKeyApiV1ClientsUidRotateEncryptionKeyPost"></a>
# **rotateEncryptionKeyApiV1ClientsUidRotateEncryptionKeyPost**
> KeyRotationResponse rotateEncryptionKeyApiV1ClientsUidRotateEncryptionKeyPost(uid, keyRotationRequest)

Rotate client encryption key

Rotate the client encryption key for all secrets in double encryption mode.  **Scope**: Client level (JWT with client_uid that must match {uid})  **What this does**: 1. Decrypts all secrets (PDP, Chorus Pro) using the old key 2. Re-encrypts them using the new key 3. Saves to database  **Important notes**: - Both keys must be base64-encoded AES-256 keys (32 bytes each) - The old key becomes invalid immediately after rotation - Only secrets encrypted with &#x60;encryptionMode: \&quot;double\&quot;&#x60; are affected - If the client has no double-encrypted secrets, returns 404  **Security**: - The old key must be valid (decryption is verified) - If decryption fails, rotation is aborted (atomic operation) - Neither key is logged or stored by the server

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
    
    // Configure API key authorization: APIKeyHeader
    ApiKeyAuth APIKeyHeader = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyHeader");
    APIKeyHeader.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyHeader.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ClientManagementApi apiInstance = new ClientManagementApi(defaultClient);
    UUID uid = UUID.randomUUID(); // UUID | Client unique identifier (UUID)
    KeyRotationRequest keyRotationRequest = new KeyRotationRequest(); // KeyRotationRequest | 
    try {
      KeyRotationResponse result = apiInstance.rotateEncryptionKeyApiV1ClientsUidRotateEncryptionKeyPost(uid, keyRotationRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ClientManagementApi#rotateEncryptionKeyApiV1ClientsUidRotateEncryptionKeyPost");
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
| **uid** | **UUID**| Client unique identifier (UUID) | |
| **keyRotationRequest** | [**KeyRotationRequest**](KeyRotationRequest.md)|  | |

### Return type

[**KeyRotationResponse**](KeyRotationResponse.md)

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
    
    // Configure API key authorization: APIKeyHeader
    ApiKeyAuth APIKeyHeader = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyHeader");
    APIKeyHeader.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyHeader.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ClientManagementApi apiInstance = new ClientManagementApi(defaultClient);
    UUID uid = UUID.randomUUID(); // UUID | Client unique identifier (UUID)
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
| **uid** | **UUID**| Client unique identifier (UUID) | |
| **clientUpdateRequest** | [**ClientUpdateRequest**](ClientUpdateRequest.md)|  | |

### Return type

[**ClientDetail**](ClientDetail.md)

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
| **401** | Missing or invalid JWT token |  -  |
| **403** | Access denied |  -  |
| **404** | Client not found |  -  |
| **500** | Server error |  -  |
| **422** | Validation Error |  -  |

<a id="updatePdpConfigApiV1ClientsUidPdpConfigPut"></a>
# **updatePdpConfigApiV1ClientsUidPdpConfigPut**
> PDPConfigResponse updatePdpConfigApiV1ClientsUidPdpConfigPut(uid, pdPConfigUpdateRequest, xEncryptionKey)

Configure client PDP

Configure or update the PDP (PA/PDP) configuration for a client.  **Scope**: Client level (JWT with client_uid that must match {uid})  **Required fields**: - &#x60;flowServiceUrl&#x60;: PDP Flow Service URL - &#x60;tokenUrl&#x60;: PDP OAuth token URL - &#x60;oauthClientId&#x60;: OAuth Client ID - &#x60;clientSecret&#x60;: OAuth Client Secret (sent but NEVER returned)  **Optional fields**: - &#x60;isActive&#x60;: Enable/disable the config (default: true) - &#x60;modeSandbox&#x60;: Sandbox mode (default: false) - &#x60;encryptionMode&#x60;: Encryption mode (default: \&quot;fernet\&quot;)   - \&quot;fernet\&quot;: Server-side encryption only   - \&quot;double\&quot;: Client AES-256-GCM + Server Fernet (requires X-Encryption-Key header)  **Double Encryption Mode**: When &#x60;encryptionMode&#x60; is set to \&quot;double\&quot;, you MUST also provide the &#x60;X-Encryption-Key&#x60; header containing a base64-encoded AES-256 key (32 bytes). This key is used to encrypt the &#x60;clientSecret&#x60; on the client side before the server encrypts it again with Fernet. The server cannot decrypt the secret without the client key.  **Security**: The &#x60;clientSecret&#x60; is stored encrypted on Django side and is never returned in API responses.

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
    
    // Configure API key authorization: APIKeyHeader
    ApiKeyAuth APIKeyHeader = (ApiKeyAuth) defaultClient.getAuthentication("APIKeyHeader");
    APIKeyHeader.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //APIKeyHeader.setApiKeyPrefix("Token");

    // Configure HTTP bearer authorization: HTTPBearer
    HttpBearerAuth HTTPBearer = (HttpBearerAuth) defaultClient.getAuthentication("HTTPBearer");
    HTTPBearer.setBearerToken("BEARER TOKEN");

    ClientManagementApi apiInstance = new ClientManagementApi(defaultClient);
    UUID uid = UUID.randomUUID(); // UUID | Client unique identifier (UUID)
    PDPConfigUpdateRequest pdPConfigUpdateRequest = new PDPConfigUpdateRequest(); // PDPConfigUpdateRequest | 
    String xEncryptionKey = "xEncryptionKey_example"; // String | Client encryption key for double encryption mode. Must be a base64-encoded AES-256 key (32 bytes). Required only when accessing resources encrypted with encryption_mode='double'.
    try {
      PDPConfigResponse result = apiInstance.updatePdpConfigApiV1ClientsUidPdpConfigPut(uid, pdPConfigUpdateRequest, xEncryptionKey);
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
| **uid** | **UUID**| Client unique identifier (UUID) | |
| **pdPConfigUpdateRequest** | [**PDPConfigUpdateRequest**](PDPConfigUpdateRequest.md)|  | |
| **xEncryptionKey** | **String**| Client encryption key for double encryption mode. Must be a base64-encoded AES-256 key (32 bytes). Required only when accessing resources encrypted with encryption_mode&#x3D;&#39;double&#39;. | [optional] |

### Return type

[**PDPConfigResponse**](PDPConfigResponse.md)

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
| **401** | Missing or invalid JWT token |  -  |
| **403** | Access denied |  -  |
| **404** | Client not found |  -  |
| **500** | Server error |  -  |
| **422** | Validation Error |  -  |

