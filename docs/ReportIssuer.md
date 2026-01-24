

# ReportIssuer

Report issuer/declarant information (TT-12 to TT-16).  The issuer is the French company declaring the transactions. The role_code determines whether the company is: - SE (Seller): B2Bi case - French company sells to foreign buyer - BY (Buyer): Bi2B case - French company buys from foreign seller  Source: Annexe 6 v1.9, bloc Issuer

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**siren** | **String** | SIREN or SIRET of the declarant (French company) |  |
|**name** | **String** |  |  [optional] |
|**vatId** | **String** |  |  [optional] |
|**roleCode** | **IssuerRoleCode** | Role of the declarant (TT-15). SE&#x3D;Seller (B2Bi: French seller to foreign buyer). BY&#x3D;Buyer (Bi2B: French buyer from foreign seller). |  [optional] |



