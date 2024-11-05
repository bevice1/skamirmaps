
package sk.amir.maps.layers

import cocoapods.MapLibre.MLNSymbolStyleLayer
import sk.amir.maps.common.MapLayerTransition
import sk.amir.maps.common.Ex
import sk.amir.maps.common.toOther
import sk.amir.maps.common.toNSPredicate
import sk.amir.maps.sources.MapSource
import sk.amir.maps.sources.toOther
import sk.amir.maps.toOther

internal class MapSymbolStyleLayerImpl(
    nLayer: MLNSymbolStyleLayer
) : MapStyleLayerImpl<MLNSymbolStyleLayer>(nLayer), MapSymbolStyleLayer {
    override var iconAllowsOverlap: Ex
	    get() = nLayer.iconAllowsOverlap.toOther()
	    set(value) { nLayer.iconAllowsOverlap.toOther() }
    override var iconAnchor: Ex
        get() = nLayer.iconAnchor.toOther()
        set(value) { nLayer.iconAnchor = value.toOther() }
    override var iconColor: Ex
        get() = nLayer.iconColor.toOther()
        set(value) { nLayer.iconColor = value.toOther() }
    override var iconColorTransition: MapLayerTransition
        get() = nLayer.iconColorTransition.toOther()
        set(value) { nLayer.iconColorTransition = value.toOther() }
    override var iconHaloBlur: Ex
        get() = nLayer.iconHaloBlur.toOther()
        set(value) { nLayer.iconHaloBlur = value.toOther() }
    override var iconHaloBlurTransition: MapLayerTransition
        get() = nLayer.iconHaloBlurTransition.toOther()
        set(value) { nLayer.iconHaloBlurTransition = value.toOther() }
    override var iconHaloColor: Ex
        get() = nLayer.iconHaloColor.toOther()
        set(value) { nLayer.iconHaloColor = value.toOther() }
    override var iconHaloColorTransition: MapLayerTransition
        get() = nLayer.iconHaloColorTransition.toOther()
        set(value) { nLayer.iconHaloColorTransition = value.toOther() }
    override var iconHaloWidth: Ex
        get() = nLayer.iconHaloWidth.toOther()
        set(value) { nLayer.iconHaloWidth = value.toOther() }
    override var iconHaloWidthTransition: MapLayerTransition
        get() = nLayer.iconHaloWidthTransition.toOther()
        set(value) { nLayer.iconHaloWidthTransition = value.toOther() }
    override var iconIgnoresPlacement: Ex
        get() = nLayer.iconIgnoresPlacement.toOther()
        set(value) { nLayer.iconIgnoresPlacement = value.toOther() }
    override var iconImageName: Ex
        get() = nLayer.iconImageName.toOther()
        set(value) { nLayer.iconImageName = value.toOther() }
    override var iconOffset: Ex
        get() = nLayer.iconOffset.toOther()
        set(value) { nLayer.iconOffset = value.toOther() }
    override var iconOpacity: Ex
        get() = nLayer.iconOpacity.toOther()
        set(value) { nLayer.iconOpacity = value.toOther() }
    override var iconOpacityTransition: MapLayerTransition
        get() = nLayer.iconOpacityTransition.toOther()
        set(value) { nLayer.iconOpacityTransition = value.toOther() }
    override var iconOptional: Ex
        get() = nLayer.iconOptional.toOther()
        set(value) { nLayer.iconOptional = value.toOther() }
    override var iconPadding: Ex
        get() = nLayer.iconPadding.toOther()
        set(value) { nLayer.iconPadding = value.toOther() }
    override var iconPitchAlignment: Ex
        get() = nLayer.iconPitchAlignment.toOther()
        set(value) { nLayer.iconPitchAlignment = value.toOther() }
    override var iconRotation: Ex
        get() = nLayer.iconRotation.toOther()
        set(value) { nLayer.iconRotation = value.toOther() }
    override var iconRotationAlignment: Ex
        get() = nLayer.iconRotationAlignment.toOther()
        set(value) { nLayer.iconRotationAlignment = value.toOther() }
    override var iconScale: Ex
        get() = nLayer.iconScale.toOther()
        set(value) { nLayer.iconScale = value.toOther() }
    override var iconTextFit: Ex
        get() = nLayer.iconTextFit.toOther()
        set(value) { nLayer.iconTextFit = value.toOther() }
    override var iconTextFitPadding: Ex
        get() = nLayer.iconTextFitPadding.toOther()
        set(value) { nLayer.iconTextFitPadding = value.toOther() }
    override var iconTranslation: Ex
        get() = nLayer.iconTranslation.toOther()
        set(value) { nLayer.iconTranslation = value.toOther() }
    override var iconTranslationAnchor: Ex
        get() = nLayer.iconTranslationAnchor.toOther()
        set(value) { nLayer.iconTranslationAnchor = value.toOther() }
    override var iconTranslationTransition: MapLayerTransition
        get() = nLayer.iconTranslationTransition.toOther()
        set(value) { nLayer.iconTranslationTransition = value.toOther() }
    override var keepsIconUpright: Ex
        get() = nLayer.keepsIconUpright.toOther()
        set(value) { nLayer.keepsIconUpright = value.toOther() }
    override var keepsTextUpright: Ex
        get() = nLayer.keepsTextUpright.toOther()
        set(value) { nLayer.keepsTextUpright = value.toOther() }
    override var maximumTextAngle: Ex
        get() = nLayer.maximumTextAngle.toOther()
        set(value) { nLayer.maximumTextAngle = value.toOther() }
    override var maximumTextWidth: Ex
        get() = nLayer.maximumTextWidth.toOther()
        set(value) { nLayer.maximumTextWidth = value.toOther() }
    override var symbolAvoidsEdges: Ex
        get() = nLayer.symbolAvoidsEdges.toOther()
        set(value) { nLayer.symbolAvoidsEdges = value.toOther() }
    override var symbolPlacement: Ex
        get() = nLayer.symbolPlacement.toOther()
        set(value) { nLayer.symbolPlacement = value.toOther() }
    override var symbolSortKey: Ex
        get() = nLayer.symbolSortKey.toOther()
        set(value) { nLayer.symbolSortKey = value.toOther() }
    override var symbolSpacing: Ex
        get() = nLayer.symbolSpacing.toOther()
        set(value) { nLayer.symbolSpacing = value.toOther() }
    override var symbolZOrder: Ex
        get() = nLayer.symbolZOrder.toOther()
        set(value) { nLayer.symbolZOrder = value.toOther() }
    override var text: Ex
        get() = nLayer.text.toOther()
        set(value) { nLayer.text = value.toOther() }
    override var textAllowsOverlap: Ex
        get() = nLayer.textAllowsOverlap.toOther()
        set(value) { nLayer.textAllowsOverlap = value.toOther() }
    override var textAnchor: Ex
        get() = nLayer.textAnchor.toOther()
        set(value) { nLayer.textAnchor = value.toOther() }
    override var textColor: Ex
        get() = nLayer.textColor.toOther()
        set(value) { nLayer.textColor = value.toOther() }
    override var textColorTransition: MapLayerTransition
        get() = nLayer.textColorTransition.toOther()
        set(value) { nLayer.textColorTransition = value.toOther() }
    override var textFontNames: Ex
        get() = nLayer.textFontNames.toOther()
        set(value) { nLayer.textFontNames = value.toOther() }
    override var textFontSize: Ex
        get() = nLayer.textFontSize.toOther()
        set(value) { nLayer.textFontSize = value.toOther() }
    override var textHaloBlur: Ex
        get() = nLayer.textHaloBlur.toOther()
        set(value) { nLayer.textHaloBlur = value.toOther() }
    override var textHaloBlurTransition: MapLayerTransition
        get() = nLayer.textHaloBlurTransition.toOther()
        set(value) { nLayer.textHaloBlurTransition = value.toOther() }
    override var textHaloColor: Ex
        get() = nLayer.textHaloColor.toOther()
        set(value) { nLayer.textHaloColor = value.toOther() }
    override var textHaloColorTransition: MapLayerTransition
        get() = nLayer.textHaloColorTransition.toOther()
        set(value) { nLayer.textHaloColorTransition = value.toOther() }
    override var textHaloWidth: Ex
        get() = nLayer.textHaloWidth.toOther()
        set(value) { nLayer.textHaloWidth = value.toOther() }
    override var textHaloWidthTransition: MapLayerTransition
        get() = nLayer.textHaloWidthTransition.toOther()
        set(value) { nLayer.textHaloWidthTransition = value.toOther() }
    override var textIgnoresPlacement: Ex
        get() = nLayer.textIgnoresPlacement.toOther()
        set(value) { nLayer.textIgnoresPlacement = value.toOther() }
    override var textJustification: Ex
        get() = nLayer.textJustification.toOther()
        set(value) { nLayer.textJustification = value.toOther() }
    override var textLetterSpacing: Ex
        get() = nLayer.textLetterSpacing.toOther()
        set(value) { nLayer.textLetterSpacing = value.toOther() }
    override var textLineHeight: Ex
        get() = nLayer.textLineHeight.toOther()
        set(value) { nLayer.textLineHeight = value.toOther() }
    override var textOffset: Ex
        get() = nLayer.textOffset.toOther()
        set(value) { nLayer.textOffset = value.toOther() }
    override var textOpacity: Ex
        get() = nLayer.textOpacity.toOther()
        set(value) { nLayer.textOpacity = value.toOther() }
    override var textOpacityTransition: MapLayerTransition
        get() = nLayer.textOpacityTransition.toOther()
        set(value) { nLayer.textOpacityTransition = value.toOther() }
    override var textOptional: Ex
        get() = nLayer.textOptional.toOther()
        set(value) { nLayer.textOptional = value.toOther() }
    override var textPadding: Ex
        get() = nLayer.textPadding.toOther()
        set(value) { nLayer.textPadding = value.toOther() }
    override var textPitchAlignment: Ex
        get() = nLayer.textPitchAlignment.toOther()
        set(value) { nLayer.textPitchAlignment = value.toOther() }
    override var textRadialOffset: Ex
        get() = nLayer.textRadialOffset.toOther()
        set(value) { nLayer.textRadialOffset = value.toOther() }
    override var textRotation: Ex
        get() = nLayer.textRotation.toOther()
        set(value) { nLayer.textRotation = value.toOther() }
    override var textRotationAlignment: Ex
        get() = nLayer.textRotationAlignment.toOther()
        set(value) { nLayer.textRotationAlignment = value.toOther() }
    override var textTransform: Ex
        get() = nLayer.textTransform.toOther()
        set(value) { nLayer.textTransform = value.toOther() }
    override var textTranslation: Ex
        get() = nLayer.textTranslation.toOther()
        set(value) { nLayer.textTranslation = value.toOther() }
    override var textTranslationAnchor: Ex
        get() = nLayer.textTranslationAnchor.toOther()
        set(value) { nLayer.textTranslationAnchor = value.toOther() }
    override var textTranslationTransition: MapLayerTransition
        get() = nLayer.textTranslationTransition.toOther()
        set(value) { nLayer.textTranslationTransition = value.toOther() }
    override var textVariableAnchor: Ex
        get() = nLayer.textVariableAnchor.toOther()
        set(value) { nLayer.textVariableAnchor = value.toOther() }
    override var textWritingModes: Ex
        get() = nLayer.textWritingModes.toOther()
        set(value) { nLayer.textWritingModes = value.toOther() }

    override fun setFilter(filter: Ex) {
        nLayer.predicate = filter.toNSPredicate()
    }
}

internal actual fun MapSymbolStyleLayer(
    identifier: String,
    source: MapSource
): MapSymbolStyleLayer {
    return MapSymbolStyleLayerImpl(
        MLNSymbolStyleLayer(
            identifier = identifier,
            source = source.toOther()
        )
    )
}