

# MandatoryNoteSchema

Mandatory note detected with location and XML/PDF comparison.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**subjectCode** | **String** | Subject code (PMT, PMD, AAB) |  |
|**label** | **String** | Label (e.g., Recovery indemnity) |  |
|**pdfValue** | **String** |  |  [optional] |
|**xmlValue** | **String** |  |  [optional] |
|**status** | **FieldStatus** | Compliance status (COMPLIANT if XML found in PDF) |  [optional] |
|**message** | **String** |  |  [optional] |
|**bbox** | [**BoundingBoxSchema**](BoundingBoxSchema.md) |  |  [optional] |



