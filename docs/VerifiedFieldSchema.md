

# VerifiedFieldSchema

A verified field with all its information (extraction + compliance + location).

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**businessTerm** | **String** | EN16931 Business Term (e.g., BT-1) |  |
|**label** | **String** | Field label (e.g., Invoice Number) |  |
|**pdfValue** | **String** |  |  [optional] |
|**xmlValue** | **String** |  |  [optional] |
|**status** | **FieldStatus** | Compliance status |  |
|**message** | **String** |  |  [optional] |
|**confidence** | **BigDecimal** | Confidence score (0-1) |  [optional] |
|**source** | **String** | Extraction source |  [optional] |
|**bbox** | [**BoundingBoxSchema**](BoundingBoxSchema.md) |  |  [optional] |



