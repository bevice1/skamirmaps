
package sk.amir.maps.layers

import org.maplibre.android.style.layers.LineLayer
import org.maplibre.android.style.layers.PropertyFactory.lineBlur
import org.maplibre.android.style.layers.PropertyFactory.lineCap
import org.maplibre.android.style.layers.PropertyFactory.lineColor
import org.maplibre.android.style.layers.PropertyFactory.lineDasharray
import org.maplibre.android.style.layers.PropertyFactory.lineGapWidth
import org.maplibre.android.style.layers.PropertyFactory.lineGradient
import org.maplibre.android.style.layers.PropertyFactory.lineJoin
import org.maplibre.android.style.layers.PropertyFactory.lineMiterLimit
import org.maplibre.android.style.layers.PropertyFactory.lineOffset
import org.maplibre.android.style.layers.PropertyFactory.lineOpacity
import org.maplibre.android.style.layers.PropertyFactory.linePattern
import org.maplibre.android.style.layers.PropertyFactory.lineRoundLimit
import org.maplibre.android.style.layers.PropertyFactory.lineSortKey
import org.maplibre.android.style.layers.PropertyFactory.lineTranslate
import org.maplibre.android.style.layers.PropertyFactory.lineTranslateAnchor
import org.maplibre.android.style.layers.PropertyFactory.lineWidth
import sk.amir.maps.common.MapLayerTransition
import sk.amir.maps.common.Ex
import sk.amir.maps.common.toOther
import sk.amir.maps.sources.MapSource

internal actual fun MapLineStyleLayer(
    identifier: String,
    source: MapSource
): MapLineStyleLayer {
    return MapLineStyleLayerImpl(
        LineLayer(
            identifier,
            source.identifier
        )
    )
}

internal class MapLineStyleLayerImpl(
    nLayer: LineLayer
) : MapStyleLayerImpl<LineLayer>(nLayer), MapLineStyleLayer {
    override var lineBlur: Ex
        get() = nLayer.lineBlur.toOther()
        set(value) { nLayer.setProperties(lineBlur(value.toOther())) }
    override var lineBlurTransition: MapLayerTransition
        get() = nLayer.lineBlurTransition.toOther()
        set(value) { nLayer.lineBlurTransition = value.toOther() }
    override var lineCap: Ex
        get() = nLayer.lineCap.toOther()
        set(value) { nLayer.setProperties(lineCap(value.toOther())) }
    override var lineColor: Ex
        get() = nLayer.lineColor.toOther()
        set(value) { nLayer.setProperties(lineColor(value.toOther())) }
    override var lineColorTransition: MapLayerTransition
        get() = nLayer.lineColorTransition.toOther()
        set(value) { nLayer.lineColorTransition = value.toOther() }
    override var lineDashPattern: Ex
        get() = nLayer.lineDasharray.toOther()
        set(value) { nLayer.setProperties(lineDasharray(value.toOther())) }
    override var lineDashPatternTransition: MapLayerTransition
        get() = nLayer.lineDasharrayTransition.toOther()
        set(value) { nLayer.lineDasharrayTransition = value.toOther() }
    override var lineGapWidth: Ex
        get() = nLayer.lineGapWidth.toOther()
        set(value) { nLayer.setProperties(lineGapWidth(value.toOther())) }
    override var lineGapWidthTransition: MapLayerTransition
        get() = nLayer.lineGapWidthTransition.toOther()
        set(value) { nLayer.lineGapWidthTransition = value.toOther() }
    override var lineGradient: Ex
        get() = nLayer.lineGradient.toOther()
        set(value) { nLayer.setProperties(lineGradient(value.toOther())) }
    override var lineJoin: Ex
        get() = nLayer.lineJoin.toOther()
        set(value) { nLayer.setProperties(lineJoin(value.toOther())) }
    override var lineMiterLimit: Ex
        get() = nLayer.lineMiterLimit.toOther()
        set(value) { nLayer.setProperties(lineMiterLimit(value.toOther())) }
    override var lineOffset: Ex
        get() = nLayer.lineOffset.toOther()
        set(value) { nLayer.setProperties(lineOffset(value.toOther())) }
    override var lineOffsetTransition: MapLayerTransition
        get() = nLayer.lineOffsetTransition.toOther()
        set(value) { nLayer.lineOffsetTransition = value.toOther() }
    override var lineOpacity: Ex
        get() = nLayer.lineOpacity.toOther()
        set(value) { nLayer.setProperties(lineOpacity(value.toOther())) }
    override var lineOpacityTransition: MapLayerTransition
        get() = nLayer.lineOpacityTransition.toOther()
        set(value) { nLayer.lineOpacityTransition = value.toOther() }
    override var linePattern: Ex
        get() = nLayer.linePattern.toOther()
        set(value) { nLayer.setProperties(linePattern(value.toOther())) }
    override var linePatternTransition: MapLayerTransition
        get() = nLayer.linePatternTransition.toOther()
        set(value) { nLayer.linePatternTransition = value.toOther() }
    override var lineRoundLimit: Ex
        get() = nLayer.lineRoundLimit.toOther()
        set(value) { nLayer.setProperties(lineRoundLimit(value.toOther())) }
    override var lineSortKey: Ex
        get() = nLayer.lineSortKey.toOther()
        set(value) { nLayer.setProperties(lineSortKey(value.toOther())) }
    override var lineTranslation: Ex
        get() = nLayer.lineTranslate.toOther()
        set(value) { nLayer.setProperties(lineTranslate(value.toOther())) }
    override var lineTranslationTransition: MapLayerTransition
        get() = nLayer.lineTranslateTransition.toOther()
        set(value) { nLayer.lineTranslateTransition = value.toOther() }
    override var lineTranslationAnchor: Ex
        get() = nLayer.lineTranslateAnchor.toOther()
        set(value) { nLayer.setProperties(lineTranslateAnchor(value.toOther())) }
    override var lineWidth: Ex
        get() = nLayer.lineWidth.toOther()
        set(value) { nLayer.setProperties(lineWidth(value.toOther())) }
    override var lineWidthTransition: MapLayerTransition
        get() = nLayer.lineWidthTransition.toOther()
        set(value) { nLayer.lineWidthTransition = value.toOther() }

    override fun setFilter(filter: Ex) {
        nLayer.setFilter(filter.toOther())
    }
}