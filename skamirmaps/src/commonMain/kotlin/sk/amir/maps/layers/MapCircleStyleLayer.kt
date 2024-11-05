
package sk.amir.maps.layers

import sk.amir.maps.sources.MapSource
import sk.amir.maps.common.MapLayerTransition
import sk.amir.maps.common.Ex

internal interface MapCircleStyleLayer : MapStyleLayer {
    var circleBlur: Ex
    var circleBlurTransition: MapLayerTransition

    var circleColor: Ex
    var circleColorTransition: MapLayerTransition

    var circleOpacity: Ex
    var circleOpacityTransition: MapLayerTransition

    var circlePitchAlignment: Ex

    var circleRadius: Ex
    var circleRadiusTransition: MapLayerTransition

    var circleSortKey: Ex

    var circleStrokeColor: Ex
    var circleStrokeColorTransition: MapLayerTransition

    var circleStrokeOpacity: Ex
    var circleStrokeOpacityTransition: MapLayerTransition

    var circleStrokeWidth: Ex
    var circleStrokeWidthTransition: MapLayerTransition
    var circleTranslation: Ex
    var circleTranslationAnchor: Ex
    var circleTranslationTransition: MapLayerTransition

    fun setFilter(filter: Ex)
}

internal expect fun MapCircleStyleLayer(
    identifier: String,
    source: MapSource,
): MapCircleStyleLayer

internal fun MapCircleStyleLayer(
    identifier: String,
    source: MapSource,
    configure: MapCircleStyleLayer.() -> Unit
) = MapCircleStyleLayer(identifier = identifier, source = source).apply(configure)