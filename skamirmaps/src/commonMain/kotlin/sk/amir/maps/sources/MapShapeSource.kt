
package sk.amir.maps.sources

import sk.amir.maps.common.MapShapeSourceOptions
import sk.amir.maps.shapes.MapFeature

internal interface MapShapeSource : MapSource {
    fun setUrl(url: String)
    fun setFeature(feature: MapFeature)
    fun setFeatureCollection(featureCollection: List<MapFeature>)
}

internal expect fun MapShapeSource(identifier: String, url: String, options: MapShapeSourceOptions): MapShapeSource
internal expect fun MapShapeSource(identifier: String, features: List<MapFeature>, options: MapShapeSourceOptions): MapShapeSource
internal expect fun MapShapeSource(identifier: String, feature: MapFeature, options: MapShapeSourceOptions): MapShapeSource
