
# Sources
# namespace mapping table

|           | iOS  | Android  | -> Library | 
|:----------|:----------|:----------|:-|
| Vector Tile Source | MLNVectorTileSource | VectorSource | - |
| Raster Tile Source | MLNRasterTileSource | RasterSource | -|
| Raster DEM Tile Source | MLNRasterDemSource | RasterDemSource | - |
| Image Source | MLNImageSource | ImageSource | - |
| - | - | CustomGeometrySource | - |
| Shape Source |  MLNShapeSource | GeoJsonSource | MapShapeSource |
| - | - | UnknownSource | - |
| -> Base Source | MLNSource | Source | MapSource |


## Shape source / GeoJSON source
|  | iOS  | Android  | -> Library |
|:----------|:----------|:----------|:- |
| buffer    | buffer    | buffer    | buffer |
| Clips coordinates | clipsCoordinates | - | - |
| Cluster properties | clusterProperties | withClusterProperty | - |
| Cluster radius | clusterRadius | clusterRadius | clusterRadius |
| Cluster | clustered | cluster | cluster |
| Line Distance Metrics    | lineDistanceMetrics    | lineMetrics    | lineMetrics |
| Maximum Zoom Level    | maximumZoomLevel  | maxZoom    |  maxZoom |
| Maximum Zoom Level for clustering   | maximumZoomLevelForClustering  | clusterMaxZoom    | clusterMaxZoom |
| Minimum Zoom Level    | minimumZoomLevel  | minZoom    | minZoom |
| Simplification tolerance | tolerance    | simplificationTolerance | tolerance |
| Wraps coordinates | wrapsCoordinates | - | - |


## Layer support
| | iOS | Android | -> Library |
|:-|:-|:-|:-|
| Line | MLNLineStyleLayer | LineLayer | MapLineStyleLayer |
| Circle | MLNCircleStyleLayer | CircleLayer | MapCircleStyleLayer |
| Fill | MLNFillStyleLayer | FillLayer | MapFillStyleLayer |
| Background | MLNBackgroundStyleLayer | BackgroundLayer | MapBackgroundStyleLayer |
| HillShade | ? | ? | ? |
| FillExtrusion | ? | ? | ? |

## Shape support
| | iOS | Android | -> Library |
|:-|:-|:-|:-|
| Line | MLNLineStyleLayer | LineLayer | MapLineStyleLayer |
| Circle | MLNCircleStyleLayer | CircleLayer | MapCircleStyleLayer |
| Fill | MLNFillStyleLayer | FillLayer | MapFillStyleLayer |
| Background | MLNBackgroundStyleLayer | BackgroundLayer | MapBackgroundStyleLayer |
| HillShade | ? | ? | ? |
| FillExtrusion | ? | ? | ? |


(\w+): MapStyleExpression
(\s+)get\(\) = TODO\("Not yet implemented"\)
(\s+)set\(value\) \{\}

$1: MapStyleExpression
$2get() = nLayer.$1.toOther()
$3set(value) { nLayer.$1 = value.toOther() }

$1: MapStyleExpression
$2get() = nLayer.$1.toOther()
$3set(value) { nLayer.setProperties($1(value.toOther())) }

(\w+): MapLayerTransition
(\s+)get\(\) = TODO\("Not yet implemented"\)
(\s+)set\(value\) \{\}

$1: MapLayerTransition
$2get() = nLayer.$1.toOther()
$3set(value) { nLayer.$1 = value.toOther() }