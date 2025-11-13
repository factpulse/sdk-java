

# ResultatValidationPDFAPI

Résultat complet de la validation d'un PDF Factur-X.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**estConforme** | **Boolean** | True si le PDF est conforme à tous les critères (XML, PDF/A, XMP) |  |
|**xmlPresent** | **Boolean** | True si un XML Factur-X est embarqué dans le PDF |  |
|**xmlConforme** | **Boolean** | True si le XML Factur-X est conforme aux règles Schematron |  |
|**profilDetecte** | **String** |  |  [optional] |
|**erreursXml** | **List&lt;String&gt;** | Liste des erreurs de validation XML |  [optional] |
|**pdfaConforme** | **Boolean** | True si le PDF est conforme PDF/A |  |
|**versionPdfa** | **String** |  |  [optional] |
|**methodeValidationPdfa** | **String** | Méthode utilisée pour la validation PDF/A (metadata ou verapdf) |  [optional] |
|**reglesValidees** | **Integer** |  |  [optional] |
|**reglesEchouees** | **Integer** |  |  [optional] |
|**erreursPdfa** | **List&lt;String&gt;** | Liste des erreurs de conformité PDF/A |  [optional] |
|**avertissementsPdfa** | **List&lt;String&gt;** | Liste des avertissements PDF/A |  [optional] |
|**xmpPresent** | **Boolean** | True si des métadonnées XMP sont présentes |  |
|**xmpConformeFacturx** | **Boolean** | True si les métadonnées XMP contiennent des informations Factur-X |  |
|**profilXmp** | **String** |  |  [optional] |
|**versionXmp** | **String** |  |  [optional] |
|**erreursXmp** | **List&lt;String&gt;** | Liste des erreurs de métadonnées XMP |  [optional] |
|**metadonneesXmp** | **Map&lt;String, Object&gt;** | Métadonnées XMP extraites du PDF |  [optional] |
|**estSigne** | **Boolean** | True si le PDF contient au moins une signature |  |
|**nombreSignatures** | **Integer** | Nombre de signatures électroniques trouvées |  [optional] |
|**signatures** | [**List&lt;InformationSignatureAPI&gt;**](InformationSignatureAPI.md) | Liste des signatures trouvées avec leurs informations |  [optional] |
|**erreursSignatures** | **List&lt;String&gt;** | Liste des erreurs lors de l&#39;analyse des signatures |  [optional] |
|**messageResume** | **String** | Message résumant le résultat de la validation |  |



