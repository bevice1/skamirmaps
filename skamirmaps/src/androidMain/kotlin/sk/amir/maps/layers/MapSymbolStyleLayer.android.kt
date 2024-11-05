
package sk.amir.maps.layers

import org.maplibre.android.style.layers.PropertyFactory.*
import org.maplibre.android.style.layers.SymbolLayer
import sk.amir.maps.common.MapLayerTransition
import sk.amir.maps.common.Ex
import sk.amir.maps.common.toOther
import sk.amir.maps.sources.MapSource


internal class MapSymbolStyleLayerImpl(
    nLayer: SymbolLayer
) : MapStyleLayerImpl<SymbolLayer>(nLayer), MapSymbolStyleLayer {
    override var iconAllowsOverlap: Ex
        get() = nLayer.iconAllowOverlap.toOther()
        set(value) { nLayer.setProperties(iconAllowOverlap(value.toOther())) }
    override var iconAnchor: Ex
        get() = nLayer.iconAnchor.toOther()
        set(value) { nLayer.setProperties(iconAnchor(value.toOther())) }
    override var iconColor: Ex
        get() = nLayer.iconColor.toOther()
        set(value) { nLayer.setProperties(iconColor(value.toOther())) }
    override var iconColorTransition: MapLayerTransition
        get() = nLayer.iconColorTransition.toOther()
        set(value) { nLayer.iconColorTransition = value.toOther() }
    override var iconHaloBlur: Ex
        get() = nLayer.iconHaloBlur.toOther()
        set(value) { nLayer.setProperties(iconHaloBlur(value.toOther())) }
    override var iconHaloBlurTransition: MapLayerTransition
        get() = nLayer.iconHaloBlurTransition.toOther()
        set(value) { nLayer.iconHaloBlurTransition = value.toOther() }
    override var iconHaloColor: Ex
        get() = nLayer.iconHaloColor.toOther()
        set(value) { nLayer.setProperties(iconHaloColor(value.toOther())) }
    override var iconHaloColorTransition: MapLayerTransition
        get() = nLayer.iconHaloColorTransition.toOther()
        set(value) { nLayer.iconHaloColorTransition = value.toOther() }
    override var iconHaloWidth: Ex
        get() = nLayer.iconHaloWidth.toOther()
        set(value) { nLayer.setProperties(iconHaloWidth(value.toOther())) }
    override var iconHaloWidthTransition: MapLayerTransition
        get() = nLayer.iconHaloWidthTransition.toOther()
        set(value) { nLayer.iconHaloWidthTransition = value.toOther() }
    /** renamed */
    override var iconIgnoresPlacement: Ex
        get() = nLayer.iconIgnorePlacement.toOther()
        set(value) { nLayer.setProperties(iconIgnorePlacement(value.toOther())) }
    override var iconImageName: Ex
        get() = nLayer.iconImage.toOther()
        set(value) { nLayer.setProperties(iconImage(value.toOther())) }
    override var iconOffset: Ex
        get() = nLayer.iconOffset.toOther()
        set(value) { nLayer.setProperties(iconOffset(value.toOther())) }
    override var iconOpacity: Ex
        get() = nLayer.iconOpacity.toOther()
        set(value) { nLayer.setProperties(iconOpacity(value.toOther())) }
    override var iconOpacityTransition: MapLayerTransition
        get() = nLayer.iconOpacityTransition.toOther()
        set(value) { nLayer.iconOpacityTransition = value.toOther() }
    override var iconOptional: Ex
        get() = nLayer.iconOptional.toOther()
        set(value) { nLayer.setProperties(iconOptional(value.toOther())) }
    override var iconPadding: Ex
        get() = nLayer.iconPadding.toOther()
        set(value) { nLayer.setProperties(iconPadding(value.toOther())) }
    override var iconPitchAlignment: Ex
        get() = nLayer.iconPitchAlignment.toOther()
        set(value) { nLayer.setProperties(iconPitchAlignment(value.toOther())) }
    /** renamed */
    override var iconRotation: Ex
        get() = nLayer.iconRotate.toOther()
        set(value) { nLayer.setProperties(iconRotate(value.toOther())) }
    /** renamed */
    override var iconRotationAlignment: Ex
        get() = nLayer.iconRotationAlignment.toOther()
        set(value) { nLayer.setProperties(iconRotationAlignment(value.toOther())) }
    /** renamed */
    override var iconScale: Ex
        get() = nLayer.iconSize.toOther()
        set(value) { nLayer.setProperties(iconSize(value.toOther())) }
    override var iconTextFit: Ex
        get() = nLayer.iconTextFit.toOther()
        set(value) { nLayer.setProperties(iconTextFit(value.toOther())) }
    override var iconTextFitPadding: Ex
        get() = nLayer.iconTextFitPadding.toOther()
        set(value) { nLayer.setProperties(iconTextFitPadding(value.toOther())) }
    /** renamed */
    override var iconTranslation: Ex
        get() = nLayer.iconTranslate.toOther()
        set(value) { nLayer.setProperties(iconTranslate(value.toOther())) }
    /** renamed */
    override var iconTranslationAnchor: Ex
        get() = nLayer.iconTranslateAnchor.toOther()
        set(value) { nLayer.setProperties(iconTranslateAnchor(value.toOther())) }
    /** renamed */
    override var iconTranslationTransition: MapLayerTransition
        get() = nLayer.iconTranslateTransition.toOther()
        set(value) { nLayer.iconTranslateTransition = value.toOther() }
    /** renamed */
    override var keepsIconUpright: Ex
        get() = nLayer.iconKeepUpright.toOther()
        set(value) { nLayer.setProperties(iconKeepUpright(value.toOther())) }
    /** renamed */
    override var keepsTextUpright: Ex
        get() = nLayer.textKeepUpright.toOther()
        set(value) { nLayer.setProperties(textKeepUpright(value.toOther())) }
    /** renamed */
    override var maximumTextAngle: Ex
        get() = nLayer.textMaxAngle.toOther()
        set(value) { nLayer.setProperties(textMaxAngle(value.toOther())) }
    /** renamed */
    override var maximumTextWidth: Ex
        get() = nLayer.textMaxWidth.toOther()
        set(value) { nLayer.setProperties(textMaxWidth(value.toOther())) }
    /** renamed */
    override var symbolAvoidsEdges: Ex
        get() = nLayer.symbolAvoidEdges.toOther()
        set(value) { nLayer.setProperties(symbolAvoidEdges(value.toOther())) }
    override var symbolPlacement: Ex
        get() = nLayer.symbolPlacement.toOther()
        set(value) { nLayer.setProperties(symbolPlacement(value.toOther())) }
    override var symbolSortKey: Ex
        get() = nLayer.symbolSortKey.toOther()
        set(value) { nLayer.setProperties(symbolSortKey(value.toOther())) }
    override var symbolSpacing: Ex
        get() = nLayer.symbolSpacing.toOther()
        set(value) { nLayer.setProperties(symbolSpacing(value.toOther())) }
    override var symbolZOrder: Ex
        get() = nLayer.symbolZOrder.toOther()
        set(value) { nLayer.setProperties(symbolZOrder(value.toOther())) }
    /** renamed */
    override var text: Ex
        get() = nLayer.textField.toOther()
        set(value) { nLayer.setProperties(textField(value.toOther())) }
    /** renamed */
    override var textAllowsOverlap: Ex
        get() = nLayer.textAllowOverlap.toOther()
        set(value) { nLayer.setProperties(textAllowOverlap(value.toOther())) }
    override var textAnchor: Ex
        get() = nLayer.textAnchor.toOther()
        set(value) { nLayer.setProperties(textAnchor(value.toOther())) }
    override var textColor: Ex
        get() = nLayer.textColor.toOther()
        set(value) { nLayer.setProperties(textColor(value.toOther())) }
    override var textColorTransition: MapLayerTransition
        get() = nLayer.textColorTransition.toOther()
        set(value) { nLayer.textColorTransition = value.toOther() }
    /** renamed */
    override var textFontNames: Ex
        get() = nLayer.textFont.toOther()
        set(value) { nLayer.setProperties(textFont(value.toOther())) }
    /** renamed */
    override var textFontSize: Ex
        get() = nLayer.textSize.toOther()
        set(value) { nLayer.setProperties(textSize(value.toOther())) }
    override var textHaloBlur: Ex
        get() = nLayer.textHaloBlur.toOther()
        set(value) { nLayer.setProperties(textHaloBlur(value.toOther())) }
    override var textHaloBlurTransition: MapLayerTransition
        get() = nLayer.textHaloBlurTransition.toOther()
        set(value) { nLayer.textHaloBlurTransition = value.toOther() }
    override var textHaloColor: Ex
        get() = nLayer.textHaloColor.toOther()
        set(value) { nLayer.setProperties(textHaloColor(value.toOther())) }
    override var textHaloColorTransition: MapLayerTransition
        get() = nLayer.textHaloColorTransition.toOther()
        set(value) { nLayer.textHaloColorTransition = value.toOther() }
    override var textHaloWidth: Ex
        get() = nLayer.textHaloWidth.toOther()
        set(value) { nLayer.setProperties(textHaloWidth(value.toOther())) }
    override var textHaloWidthTransition: MapLayerTransition
        get() = nLayer.textHaloWidthTransition.toOther()
        set(value) { nLayer.textHaloWidthTransition = value.toOther() }
    /** renamed */
    override var textIgnoresPlacement: Ex
        get() = nLayer.textIgnorePlacement.toOther()
        set(value) { nLayer.setProperties(textIgnorePlacement(value.toOther())) }
    /** renamed */
    override var textJustification: Ex
        get() = nLayer.textJustify.toOther()
        set(value) { nLayer.setProperties(textJustify(value.toOther())) }
    override var textLetterSpacing: Ex
        get() = nLayer.textLetterSpacing.toOther()
        set(value) { nLayer.setProperties(textLetterSpacing(value.toOther())) }
    override var textLineHeight: Ex
        get() = nLayer.textLineHeight.toOther()
        set(value) { nLayer.setProperties(textLineHeight(value.toOther())) }
    override var textOffset: Ex
        get() = nLayer.textOffset.toOther()
        set(value) { nLayer.setProperties(textOffset(value.toOther())) }
    override var textOpacity: Ex
        get() = nLayer.textOpacity.toOther()
        set(value) { nLayer.setProperties(textOpacity(value.toOther())) }
    override var textOpacityTransition: MapLayerTransition
        get() = nLayer.textOpacityTransition.toOther()
        set(value) { nLayer.textOpacityTransition = value.toOther() }
    override var textOptional: Ex
        get() = nLayer.textOptional.toOther()
        set(value) { nLayer.setProperties(textOptional(value.toOther())) }
    override var textPadding: Ex
        get() = nLayer.textPadding.toOther()
        set(value) { nLayer.setProperties(textPadding(value.toOther())) }
    override var textPitchAlignment: Ex
        get() = nLayer.textPitchAlignment.toOther()
        set(value) { nLayer.setProperties(textPitchAlignment(value.toOther())) }
    override var textRadialOffset: Ex
        get() = nLayer.textRadialOffset.toOther()
        set(value) { nLayer.setProperties(textRadialOffset(value.toOther())) }
    /** renamed */
    override var textRotation: Ex
        get() = nLayer.textRotate.toOther()
        set(value) { nLayer.setProperties(textRotate(value.toOther())) }
    override var textRotationAlignment: Ex
        get() = nLayer.textRotationAlignment.toOther()
        set(value) { nLayer.setProperties(textRotationAlignment(value.toOther())) }
    override var textTransform: Ex
        get() = nLayer.textTransform.toOther()
        set(value) { nLayer.setProperties(textTransform(value.toOther())) }
    /** renamed */
    override var textTranslation: Ex
        get() = nLayer.textTranslate.toOther()
        set(value) { nLayer.setProperties(textTranslate(value.toOther())) }
    /** renamed */
    override var textTranslationAnchor: Ex
        get() = nLayer.textTranslateAnchor.toOther()
        set(value) { nLayer.setProperties(textTranslateAnchor(value.toOther())) }
    /** renamed */
    override var textTranslationTransition: MapLayerTransition
        get() = nLayer.textTranslateTransition.toOther()
        set(value) { nLayer.textTranslateTransition = value.toOther() }
    override var textVariableAnchor: Ex
        get() = nLayer.textVariableAnchor.toOther()
        set(value) { nLayer.setProperties(textVariableAnchor(value.toOther())) }
    /** renamed */
    override var textWritingModes: Ex
        get() = nLayer.textWritingMode.toOther()
        set(value) { nLayer.setProperties(textWritingMode(value.toOther())) }

    override fun setFilter(filter: Ex) {
        nLayer.setFilter(filter.toOther())
    }
}

internal actual fun MapSymbolStyleLayer(
    identifier: String,
    source: MapSource
): MapSymbolStyleLayer {
    return MapSymbolStyleLayerImpl(
        SymbolLayer(identifier, source.identifier)
    )
}