

# BoundingBoxSchema

Coordonnées d'une zone rectangulaire dans le PDF.  Les coordonnées sont en points PDF (1 point = 1/72 pouce). L'origine (0,0) est en bas à gauche de la page.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**x0** | **BigDecimal** | Coordonnée X gauche |  |
|**y0** | **BigDecimal** | Coordonnée Y bas |  |
|**x1** | **BigDecimal** | Coordonnée X droite |  |
|**y1** | **BigDecimal** | Coordonnée Y haut |  |
|**page** | **Integer** | Numéro de page (0-indexed) |  [optional] |
|**width** | **BigDecimal** | Largeur de la zone |  |
|**height** | **BigDecimal** | Hauteur de la zone |  |



