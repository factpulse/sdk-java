

# Destination

Destination configuration (Chorus Pro or AFNOR)

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**type** | [**TypeEnum**](#TypeEnum) |  |  [optional] |
|**credentials** | [**AFNORCredentials**](AFNORCredentials.md) |  |  [optional] |
|**flowSyntax** | [**FlowSyntaxEnum**](#FlowSyntaxEnum) | Flow syntax to send |  [optional] |
|**trackingId** | **String** |  |  [optional] |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| CHORUS_PRO | &quot;chorus_pro&quot; |
| AFNOR | &quot;afnor&quot; |



## Enum: FlowSyntaxEnum

| Name | Value |
|---- | -----|
| FACTUR_X | &quot;Factur-X&quot; |
| CII | &quot;CII&quot; |
| UBL | &quot;UBL&quot; |



