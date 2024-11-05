
package sk.amir.maps.layers

import cocoapods.MapLibre.MLNFillStyleLayer
import sk.amir.maps.MapSourceImpl
import sk.amir.maps.common.MapLayerTransition
import sk.amir.maps.common.Ex
import sk.amir.maps.common.toNSPredicate
import sk.amir.maps.common.toOther
import sk.amir.maps.sources.MapSource
import sk.amir.maps.toOther

internal actual fun MapFillStyleLayer(
    identifier: String,
    source: MapSource
) : MapFillStyleLayer {
    return MapFillStyleLayerImpl(
        MLNFillStyleLayer(
            identifier,
            (source as MapSourceImpl<*>).nSource
        )
    )
}

internal class MapFillStyleLayerImpl(
    nLayer: MLNFillStyleLayer
) : MapStyleLayerImpl<MLNFillStyleLayer>(nLayer), MapFillStyleLayer  {
    override var fillAntialiased: Ex
        get() = nLayer.fillAntialiased.toOther()
        set(value) { nLayer.fillAntialiased = value.toOther() }

    override var fillColor: Ex
        get() = nLayer.fillColor.toOther()
        set(value) { nLayer.fillColor = value.toOther() }

    override var fillColorTransition: MapLayerTransition
        get() = nLayer.fillColorTransition.toOther()
        set(value) { nLayer.fillColorTransition = value.toOther() }

    override var fillOpacity: Ex
        get() = nLayer.fillOpacity.toOther()
        set(value) { nLayer.fillOpacity = value.toOther() }

    override var fillOpacityTransition: MapLayerTransition
        get() = nLayer.fillOpacityTransition.toOther()
        set(value) { nLayer.fillOpacityTransition = value.toOther() }

    override var fillOutlineColor: Ex
        get() = nLayer.fillOutlineColor.toOther()
        set(value) { nLayer.fillOutlineColor = value.toOther() }

    override var fillOutlineColorTransition: MapLayerTransition
        get() = nLayer.fillOutlineColorTransition.toOther()
        set(value) { nLayer.fillOutlineColorTransition = value.toOther() }

    override var fillPattern: Ex
        get() = nLayer.fillPattern.toOther()
        set(value) { nLayer.fillPattern = value.toOther() }

    override var fillPatternTransition: MapLayerTransition
        get() = nLayer.fillPatternTransition.toOther()
        set(value) { nLayer.fillPatternTransition = value.toOther() }

    override var fillSortKey: Ex
        get() = nLayer.fillSortKey.toOther()
        set(value) { nLayer.fillSortKey = value.toOther() }

    override var fillTranslation: Ex
        get() = nLayer.fillTranslation.toOther()
        set(value) { nLayer.fillTranslation = value.toOther() }

    override var fillTranslationAnchor: Ex
        get() = nLayer.fillTranslationAnchor.toOther()
        set(value) { nLayer.fillTranslationAnchor = value.toOther() }

    override var fillTranslationTransition: MapLayerTransition
        get() = nLayer.fillTranslationTransition.toOther()
        set(value) { nLayer.fillTranslationTransition = value.toOther() }

    override fun setFilter(filter: Ex) {
        nLayer.predicate = filter.toNSPredicate()
    }
}
