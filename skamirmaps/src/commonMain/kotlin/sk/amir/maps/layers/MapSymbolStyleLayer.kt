
package sk.amir.maps.layers

import sk.amir.maps.common.MapLayerTransition
import sk.amir.maps.common.Ex
import sk.amir.maps.sources.MapSource

internal interface MapSymbolStyleLayer : MapStyleLayer {
    var iconAllowsOverlap: Ex

    var iconAnchor: Ex

    var iconColor: Ex

    var iconColorTransition: MapLayerTransition

    var iconHaloBlur: Ex

    var iconHaloBlurTransition: MapLayerTransition

    var iconHaloColor: Ex

    var iconHaloColorTransition: MapLayerTransition

    var iconHaloWidth: Ex

    var iconHaloWidthTransition: MapLayerTransition

    var iconIgnoresPlacement: Ex

    var iconImageName: Ex

    var iconOffset: Ex

    var iconOpacity: Ex

    var iconOpacityTransition: MapLayerTransition

    var iconOptional: Ex

    var iconPadding: Ex

    var iconPitchAlignment: Ex

    var iconRotation: Ex

    var iconRotationAlignment: Ex

    var iconScale: Ex

    var iconTextFit: Ex

    var iconTextFitPadding: Ex

    var iconTranslation: Ex

    var iconTranslationAnchor: Ex


    var iconTranslationTransition: MapLayerTransition

    var keepsIconUpright: Ex

    var keepsTextUpright: Ex
    var maximumTextAngle: Ex

    var maximumTextWidth: Ex

    var symbolAvoidsEdges: Ex

    var symbolPlacement: Ex

    var symbolSortKey: Ex

    var symbolSpacing: Ex
    var symbolZOrder: Ex
    var text: Ex
    var textAllowsOverlap: Ex
    var textAnchor: Ex
    var textColor: Ex
    var textColorTransition: MapLayerTransition
    var textFontNames: Ex

    var textFontSize: Ex

    var textHaloBlur: Ex

    var textHaloBlurTransition: MapLayerTransition

    var textHaloColor: Ex

    var textHaloColorTransition: MapLayerTransition

    var textHaloWidth: Ex

    var textHaloWidthTransition: MapLayerTransition

    var textIgnoresPlacement: Ex

    var textJustification: Ex

    var textLetterSpacing: Ex

    var textLineHeight: Ex

    var textOffset: Ex

    var textOpacity: Ex

    var textOpacityTransition: MapLayerTransition
    var textOptional: Ex

    var textPadding: Ex

    var textPitchAlignment: Ex
    var textRadialOffset: Ex

    var textRotation: Ex

    var textRotationAlignment: Ex

    var textTransform: Ex

    var textTranslation: Ex

    var textTranslationAnchor: Ex
    var textTranslationTransition: MapLayerTransition
    var textVariableAnchor: Ex

    var textWritingModes: Ex

    fun setFilter(filter: Ex)
}

internal expect fun MapSymbolStyleLayer(
    identifier: String,
    source: MapSource,
) : MapSymbolStyleLayer

internal fun MapSymbolStyleLayer(
    identifier: String,
    source: MapSource,
    configure: MapSymbolStyleLayer.() -> Unit
) : MapSymbolStyleLayer = MapSymbolStyleLayer(
    identifier = identifier,
    source = source
).apply(configure)
