
package sk.amir.maps.layers

import cocoapods.MapLibre.MLNLineStyleLayer
import sk.amir.maps.MapSourceImpl
import sk.amir.maps.common.MapLayerTransition
import sk.amir.maps.common.Ex
import sk.amir.maps.common.toNSPredicate
import sk.amir.maps.common.toOther
import sk.amir.maps.sources.MapSource
import sk.amir.maps.toOther

internal actual fun MapLineStyleLayer(
    identifier: String,
    source: MapSource,
): MapLineStyleLayer {
    return MapLineStyleLayerImpl(
        MLNLineStyleLayer(
            identifier,
            source = (source as MapSourceImpl<*>).nSource
        )
    )
}

internal class MapLineStyleLayerImpl(
    nLayer: MLNLineStyleLayer
) : MapStyleLayerImpl<MLNLineStyleLayer>(nLayer), MapLineStyleLayer {
    override var lineBlur: Ex
        get() = nLayer.lineBlur.toOther()
        set(value) { nLayer.lineBlur = value.toOther() }
    override var lineBlurTransition: MapLayerTransition
        get() = nLayer.lineBlurTransition.toOther()
        set(value) { nLayer.lineBlurTransition = value.toOther() }
    override var lineCap: Ex
        get() = nLayer.lineCap.toOther()
        set(value) { nLayer.lineCap = value.toOther() }
    override var lineColor: Ex
        get() = nLayer.lineColor.toOther()
        set(value) { nLayer.lineColor = value.toOther() }
    override var lineColorTransition: MapLayerTransition
        get() = nLayer.lineColorTransition.toOther()
        set(value) { nLayer.lineColorTransition = value.toOther() }
    override var lineDashPattern: Ex
        get() = nLayer.lineDashPattern.toOther()
        set(value) { nLayer.lineDashPattern = value.toOther() }
    override var lineDashPatternTransition: MapLayerTransition
        get() = nLayer.lineDashPatternTransition.toOther()
        set(value) { nLayer.lineDashPatternTransition = value.toOther() }
    override var lineGapWidth: Ex
        get() = nLayer.lineGapWidth.toOther()
        set(value) { nLayer.lineGapWidth = value.toOther() }
    override var lineGapWidthTransition: MapLayerTransition
        get() = nLayer.lineGapWidthTransition.toOther()
        set(value) { nLayer.lineGapWidthTransition = value.toOther() }
    override var lineGradient: Ex
        get() = nLayer.lineGradient.toOther()
        set(value) { nLayer.lineGradient = value.toOther() }
    override var lineJoin: Ex
        get() = nLayer.lineJoin.toOther()
        set(value) { nLayer.lineJoin = value.toOther() }
    override var lineMiterLimit: Ex
        get() = nLayer.lineMiterLimit.toOther()
        set(value) { nLayer.lineMiterLimit = value.toOther() }
    override var lineOffset: Ex
        get() = nLayer.lineOffset.toOther()
        set(value) { nLayer.lineOffset = value.toOther() }
    override var lineOffsetTransition: MapLayerTransition
        get() = nLayer.lineOffsetTransition.toOther()
        set(value) { nLayer.lineOffsetTransition = value.toOther() }
    override var lineOpacity: Ex
        get() = nLayer.lineOpacity.toOther()
        set(value) { nLayer.lineOpacity = value.toOther() }
    override var lineOpacityTransition: MapLayerTransition
        get() = nLayer.lineOpacityTransition.toOther()
        set(value) { nLayer.lineOpacityTransition = value.toOther() }
    override var linePattern: Ex
        get() = nLayer.linePattern.toOther()
        set(value) { nLayer.linePattern = value.toOther() }
    override var linePatternTransition: MapLayerTransition
        get() = nLayer.linePatternTransition.toOther()
        set(value) { nLayer.linePatternTransition = value.toOther() }
    override var lineRoundLimit: Ex
        get() = nLayer.lineRoundLimit.toOther()
        set(value) { nLayer.lineRoundLimit = value.toOther() }
    override var lineSortKey: Ex
        get() = nLayer.lineSortKey.toOther()
        set(value) { nLayer.lineSortKey = value.toOther() }
    override var lineTranslation: Ex
        get() = nLayer.lineTranslation.toOther()
        set(value) { nLayer.lineTranslation = value.toOther() }
    override var lineTranslationTransition: MapLayerTransition
        get() = nLayer.lineTranslationTransition.toOther()
        set(value) { nLayer.lineTranslationTransition = value.toOther() }
    override var lineTranslationAnchor: Ex
        get() = nLayer.lineTranslationAnchor.toOther()
        set(value) { nLayer.lineTranslationAnchor = value.toOther() }
    override var lineWidth: Ex
        get() = nLayer.lineWidth.toOther()
        set(value) { nLayer.lineWidth = value.toOther() }
    override var lineWidthTransition: MapLayerTransition
        get() = nLayer.lineWidthTransition.toOther()
        set(value) { nLayer.lineWidthTransition = value.toOther() }

    override fun setFilter(filter: Ex) {
        nLayer.predicate = filter.toNSPredicate()
    }
}
