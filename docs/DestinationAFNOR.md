

# DestinationAFNOR

Configuration spécifique pour la destination AFNOR PDP.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**type** | [**TypeEnum**](#TypeEnum) |  |  [optional] |
|**credentials** | [**CredentialsAFNOR**](CredentialsAFNOR.md) |  |  [optional] |
|**flowSyntax** | [**FlowSyntaxEnum**](#FlowSyntaxEnum) | Syntaxe du flux à envoyer |  [optional] |
|**trackingId** | **String** |  |  [optional] |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| AFNOR | &quot;afnor&quot; |



## Enum: FlowSyntaxEnum

| Name | Value |
|---- | -----|
| FACTUR_X | &quot;Factur-X&quot; |
| CII | &quot;CII&quot; |
| UBL | &quot;UBL&quot; |



