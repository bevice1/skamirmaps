
package sk.amir.maps.layers

import org.maplibre.android.style.layers.CircleLayer
import org.maplibre.android.style.layers.PropertyFactory.circleBlur
import org.maplibre.android.style.layers.PropertyFactory.circleColor
import org.maplibre.android.style.layers.PropertyFactory.circleOpacity
import org.maplibre.android.style.layers.PropertyFactory.circlePitchAlignment
import org.maplibre.android.style.layers.PropertyFactory.circleRadius
import org.maplibre.android.style.layers.PropertyFactory.circleSortKey
import org.maplibre.android.style.layers.PropertyFactory.circleStrokeColor
import org.maplibre.android.style.layers.PropertyFactory.circleStrokeOpacity
import org.maplibre.android.style.layers.PropertyFactory.circleStrokeWidth
import org.maplibre.android.style.layers.PropertyFactory.circleTranslate
import org.maplibre.android.style.layers.PropertyFactory.circleTranslateAnchor
import sk.amir.maps.common.Ex
import sk.amir.maps.common.MapLayerTransition
import sk.amir.maps.common.toOther
import sk.amir.maps.sources.MapSource

internal class MapCircleStyleLayerImpl(
    nLayer: CircleLayer
) : MapStyleLayerImpl<CircleLayer>(nLayer), MapCircleStyleLayer {
    override var circleBlur: Ex
        get() = nLayer.circleBlur.toOther()
        set(value) { nLayer.setProperties(circleBlur(value.toOther())) }

    override var circleBlurTransition: MapLayerTransition
        get() = nLayer.circleBlurTransition.toOther()
        set(value) { nLayer.circleBlurTransition = value.toOther() }

    override var circleColor: Ex
        get() = nLayer.circleColor.toOther()
        set(value) { nLayer.setProperties(circleColor(value.toOther())) }

    override var circleColorTransition: MapLayerTransition
        get() = nLayer.circleColorTransition.toOther()
        set(value) { nLayer.circleColorTransition = value.toOther() }

    override var circleOpacity: Ex
        get() = nLayer.circleOpacity.toOther()
        set(value) { nLayer.setProperties(circleOpacity(value.toOther())) }

    override var circleOpacityTransition: MapLayerTransition
        get() = nLayer.circleOpacityTransition.toOther()
        set(value) { nLayer.circleOpacityTransition = value.toOther() }

    override var circlePitchAlignment: Ex
        get() = nLayer.circlePitchAlignment.toOther()
        set(value) { nLayer.setProperties(circlePitchAlignment(value.toOther())) }

    override var circleRadius: Ex
        get() = nLayer.circleRadius.toOther()
        set(value) { nLayer.setProperties(circleRadius(value.toOther())) }

    override var circleRadiusTransition: MapLayerTransition
        get() = nLayer.circleRadiusTransition.toOther()
        set(value) { nLayer.circleRadiusTransition = value.toOther() }

    override var circleSortKey: Ex
        get() = nLayer.circleSortKey.toOther()
        set(value) { nLayer.setProperties(circleSortKey(value.toOther())) }

    override var circleStrokeColor: Ex
        get() = nLayer.circleStrokeColor.toOther()
        set(value) { nLayer.setProperties(circleStrokeColor(value.toOther())) }

    override var circleStrokeColorTransition: MapLayerTransition
        get() = nLayer.circleStrokeColorTransition.toOther()
        set(value) { nLayer.circleStrokeColorTransition = value.toOther() }

    override var circleStrokeOpacity: Ex
        get() = nLayer.circleStrokeOpacity.toOther()
        set(value) { nLayer.setProperties(circleStrokeOpacity(value.toOther())) }

    override var circleStrokeOpacityTransition: MapLayerTransition
        get() = nLayer.circleStrokeOpacityTransition.toOther()
        set(value) { nLayer.circleStrokeOpacityTransition = value.toOther() }

    override var circleStrokeWidth: Ex
        get() = nLayer.circleStrokeWidth.toOther()
        set(value) { nLayer.setProperties(circleStrokeWidth(value.toOther())) }

    override var circleStrokeWidthTransition: MapLayerTransition
        get() = nLayer.circleStrokeWidthTransition.toOther()
        set(value) { nLayer.circleStrokeWidthTransition = value.toOther() }

    override var circleTranslation: Ex
        get() = nLayer.circleTranslate.toOther()
        set(value) { nLayer.setProperties(circleTranslate(value.toOther())) }

    override var circleTranslationAnchor: Ex
        get() = nLayer.circleTranslateAnchor.toOther()
        set(value) { nLayer.setProperties(circleTranslateAnchor(value.toOther())) }

    override var circleTranslationTransition: MapLayerTransition
        get() = nLayer.circleTranslateTransition.toOther()
        set(value) { nLayer.circleTranslateTransition = value.toOther() }

    override fun setFilter(filter: Ex) {
        nLayer.setFilter(filter.toOther())
    }
}

internal actual fun MapCircleStyleLayer(
    identifier: String,
    source: MapSource,
): MapCircleStyleLayer {
    return MapCircleStyleLayerImpl(
        CircleLayer(
            identifier,
            source.identifier
        )
    )
}

