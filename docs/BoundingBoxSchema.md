

# BoundingBoxSchema

Rectangular area coordinates in PDF.  Coordinates are in PDF points (1 point = 1/72 inch). Origin (0,0) is at the bottom-left of the page.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**x0** | **BigDecimal** | Left X coordinate |  |
|**y0** | **BigDecimal** | Bottom Y coordinate |  |
|**x1** | **BigDecimal** | Right X coordinate |  |
|**y1** | **BigDecimal** | Top Y coordinate |  |
|**page** | **Integer** | Page number (0-indexed) |  [optional] |
|**width** | **BigDecimal** | Area width |  |
|**height** | **BigDecimal** | Area height |  |



