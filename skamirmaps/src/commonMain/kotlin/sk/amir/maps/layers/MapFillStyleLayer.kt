
package sk.amir.maps.layers

import sk.amir.maps.common.MapLayerTransition
import sk.amir.maps.common.Ex
import sk.amir.maps.sources.MapSource

internal interface MapFillStyleLayer : MapStyleLayer {
    var fillAntialiased: Ex
    var fillColor: Ex
    var fillColorTransition: MapLayerTransition
    var fillOpacity: Ex
    var fillOpacityTransition: MapLayerTransition
    var fillOutlineColor: Ex
    var fillOutlineColorTransition: MapLayerTransition
    var fillPattern: Ex
    var fillPatternTransition: MapLayerTransition
    var fillSortKey: Ex
    var fillTranslation: Ex
    var fillTranslationAnchor: Ex
    var fillTranslationTransition: MapLayerTransition
    fun setFilter(filter: Ex)
}

internal expect fun MapFillStyleLayer(
    identifier: String,
    source: MapSource
) : MapFillStyleLayer

internal fun MapFillStyleLayer(
    identifier: String,
    source: MapSource,
    configure: MapFillStyleLayer.() -> Unit
) : MapFillStyleLayer = MapFillStyleLayer(identifier = identifier, source = source).apply(configure)
