
package sk.amir.maps.layers

import sk.amir.maps.common.MapLayerTransition
import sk.amir.maps.common.Ex
import sk.amir.maps.sources.MapSource

internal interface MapLineStyleLayer : MapStyleLayer {
    var lineBlur: Ex
    var lineBlurTransition: MapLayerTransition
    var lineCap: Ex
    var lineColor: Ex
    var lineColorTransition: MapLayerTransition
    var lineDashPattern: Ex
    var lineDashPatternTransition: MapLayerTransition
    var lineGapWidth: Ex
    var lineGapWidthTransition: MapLayerTransition
    var lineGradient: Ex
    var lineJoin: Ex
    var lineMiterLimit: Ex
    var lineOffset: Ex
    var lineOffsetTransition: MapLayerTransition
    var lineOpacity: Ex
    var lineOpacityTransition: MapLayerTransition
    var linePattern: Ex
    var linePatternTransition: MapLayerTransition
    var lineRoundLimit: Ex
    var lineSortKey: Ex
    var lineTranslation: Ex
    var lineTranslationTransition: MapLayerTransition
    var lineTranslationAnchor: Ex
    var lineWidth: Ex
    var lineWidthTransition: MapLayerTransition
    fun setFilter(filter: Ex)
}

internal expect fun MapLineStyleLayer(
    identifier: String,
    source: MapSource,
): MapLineStyleLayer

internal fun MapLineStyleLayer(
    identifier: String,
    source: MapSource,
    configure: MapLineStyleLayer.() -> Unit
) = MapLineStyleLayer(identifier = identifier, source = source).apply(configure)