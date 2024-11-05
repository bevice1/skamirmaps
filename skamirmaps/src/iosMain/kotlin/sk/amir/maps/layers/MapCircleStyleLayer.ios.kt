
package sk.amir.maps.layers

import cocoapods.MapLibre.MLNCircleStyleLayer
import sk.amir.maps.MapSourceImpl
import sk.amir.maps.common.MapLayerTransition
import sk.amir.maps.common.Ex
import sk.amir.maps.common.toOther
import sk.amir.maps.common.toNSPredicate
import sk.amir.maps.sources.MapSource
import sk.amir.maps.toOther

internal actual fun MapCircleStyleLayer(
    identifier: String,
    source: MapSource,
): MapCircleStyleLayer {
    source as MapSourceImpl<*>
    return MapCircleStyleLayerImpl(
        MLNCircleStyleLayer(
            identifier = identifier,
            source = source.nSource
        )
    )
}

internal class MapCircleStyleLayerImpl(
    nLayer: MLNCircleStyleLayer
) :  MapStyleLayerImpl<MLNCircleStyleLayer>(nLayer), MapCircleStyleLayer {

    override var circleBlur: Ex
        get() = nLayer.circleBlur.toOther()
        set(value) { nLayer.circleBlur = value.toOther() }

    override var circleBlurTransition: MapLayerTransition
        get() = nLayer.circleBlurTransition.toOther()
        set(value) { nLayer.circleBlurTransition = value.toOther() }

    override var circleColor: Ex
        get() = nLayer.circleColor.toOther()
        set(value) { nLayer.circleColor = value.toOther() }

    override var circleColorTransition: MapLayerTransition
        get() = nLayer.circleColorTransition.toOther()
        set(value) { nLayer.circleColorTransition = value.toOther() }

    override var circleOpacity: Ex
        get() = nLayer.circleOpacity.toOther()
        set(value) { nLayer.circleOpacity = value.toOther() }

    override var circleOpacityTransition: MapLayerTransition
        get() = nLayer.circleOpacityTransition.toOther()
        set(value) { nLayer.circleOpacityTransition = value.toOther() }

    override var circlePitchAlignment: Ex
        get() = nLayer.circlePitchAlignment.toOther()
        set(value) { nLayer.circlePitchAlignment = value.toOther() }

    override var circleRadius: Ex
        get() = nLayer.circleRadius.toOther()
        set(value) { nLayer.circleRadius = value.toOther() }

    override var circleRadiusTransition: MapLayerTransition
        get() = nLayer.circleRadiusTransition.toOther()
        set(value) { nLayer.circleRadiusTransition = value.toOther() }

    override var circleSortKey: Ex
        get() = nLayer.circleSortKey.toOther()
        set(value) { nLayer.circleSortKey = value.toOther() }

    override var circleStrokeColor: Ex
        get() = nLayer.circleStrokeColor.toOther()
        set(value) { nLayer.circleStrokeColor = value.toOther() }

    override var circleStrokeColorTransition: MapLayerTransition
        get() = nLayer.circleStrokeColorTransition.toOther()
        set(value) { nLayer.circleStrokeColorTransition = value.toOther() }

    override var circleStrokeOpacity: Ex
        get() = nLayer.circleStrokeOpacity.toOther()
        set(value) { nLayer.circleStrokeOpacity = value.toOther() }

    override var circleStrokeOpacityTransition: MapLayerTransition
        get() = nLayer.circleStrokeOpacityTransition.toOther()
        set(value) { nLayer.circleStrokeOpacityTransition = value.toOther() }

    override var circleStrokeWidth: Ex
        get() = nLayer.circleStrokeWidth.toOther()
        set(value) { nLayer.circleStrokeWidth = value.toOther() }

    override var circleStrokeWidthTransition: MapLayerTransition
        get() = nLayer.circleStrokeWidthTransition.toOther()
        set(value) { nLayer.circleStrokeWidthTransition = value.toOther() }

    override var circleTranslation: Ex
        get() = nLayer.circleTranslation.toOther()
        set(value) { nLayer.circleTranslation = value.toOther() }

    override var circleTranslationAnchor: Ex
        get() = nLayer.circleTranslationAnchor.toOther()
        set(value) { nLayer.circleTranslationAnchor = value.toOther() }

    override var circleTranslationTransition: MapLayerTransition
        get() = nLayer.circleTranslationTransition.toOther()
        set(value) { nLayer.circleTranslationTransition = value.toOther() }

    override fun setFilter(filter: Ex) {
        nLayer.predicate = filter.toNSPredicate()
    }
}
