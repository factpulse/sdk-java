

# PDFValidationResultAPI

Complete result of a Factur-X PDF validation.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**isCompliant** | **Boolean** | True if PDF complies with all criteria (XML, PDF/A, XMP) |  |
|**xmlPresent** | **Boolean** | True if a Factur-X XML is embedded in the PDF |  |
|**xmlCompliant** | **Boolean** | True if Factur-X XML complies with Schematron rules |  |
|**detectedProfile** | **String** |  |  [optional] |
|**xmlErrors** | **List&lt;String&gt;** | List of XML validation errors |  [optional] |
|**pdfaCompliant** | **Boolean** | True if PDF is PDF/A compliant |  |
|**pdfaVersion** | **String** |  |  [optional] |
|**pdfaValidationMethod** | **String** | Method used for PDF/A validation (metadata or verapdf) |  [optional] |
|**validatedRules** | **Integer** |  |  [optional] |
|**failedRules** | **Integer** |  |  [optional] |
|**pdfaErrors** | **List&lt;String&gt;** | List of PDF/A compliance errors |  [optional] |
|**pdfaWarnings** | **List&lt;String&gt;** | List of PDF/A warnings |  [optional] |
|**xmpPresent** | **Boolean** | True if XMP metadata is present |  |
|**xmpFacturxCompliant** | **Boolean** | True if XMP metadata contains Factur-X information |  |
|**xmpProfile** | **String** |  |  [optional] |
|**xmpVersion** | **String** |  |  [optional] |
|**xmpErrors** | **List&lt;String&gt;** | List of XMP metadata errors |  [optional] |
|**xmpMetadata** | **Map&lt;String, Object&gt;** | XMP metadata extracted from PDF |  [optional] |
|**isSigned** | **Boolean** | True if PDF contains at least one signature |  |
|**signatureCount** | **Integer** | Number of electronic signatures found |  [optional] |
|**signatures** | [**List&lt;SignatureInfoAPI&gt;**](SignatureInfoAPI.md) | List of found signatures with their information |  [optional] |
|**signatureErrors** | **List&lt;String&gt;** | List of errors during signature analysis |  [optional] |
|**summaryMessage** | **String** | Message summarizing the validation result |  |



