

# VerificationSuccessResponse

Successful verification response with unified data.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**isCompliant** | **Boolean** | True if no critical discrepancy |  |
|**complianceScore** | **BigDecimal** | Compliance score (0-100%) |  |
|**verifiedFieldsCount** | **Integer** | Number of verified fields |  [optional] |
|**compliantFieldsCount** | **Integer** | Number of compliant fields |  [optional] |
|**isFacturx** | **Boolean** | True if PDF contains Factur-X XML |  [optional] |
|**facturxProfile** | **String** |  |  [optional] |
|**fields** | [**List&lt;VerifiedFieldSchema&gt;**](VerifiedFieldSchema.md) | List of verified fields with values, statuses and PDF coordinates |  [optional] |
|**mandatoryNotes** | [**List&lt;MandatoryNoteSchema&gt;**](MandatoryNoteSchema.md) | Mandatory notes (PMT, PMD, AAB) with PDF location |  [optional] |
|**pageDimensions** | [**List&lt;PageDimensionsSchema&gt;**](PageDimensionsSchema.md) | Dimensions of each PDF page (width, height) |  [optional] |
|**warnings** | **List&lt;String&gt;** | Non-blocking warnings |  [optional] |



