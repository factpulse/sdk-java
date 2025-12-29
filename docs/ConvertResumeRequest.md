

# ConvertResumeRequest

Requete de reprise de conversion avec corrections.  Le champ `overrides` accepte n'importe quel sous-ensemble de FacturXInvoice. Seuls les champs fournis seront mis a jour (merge profond).  Exemple:     {         \"overrides\": {             \"supplier\": {                 \"name\": \"Ma Société\",                 \"siret\": \"12345678901234\"             },             \"totals\": {                 \"total_net_amount\": 1000.00             }         }     }

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**overrides** | **Map&lt;String, Object&gt;** | Sous-ensemble de FacturXInvoice a mettre a jour (merge profond) |  [optional] |



