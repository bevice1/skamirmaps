
package sk.amir.maps.layers

import org.maplibre.android.style.layers.FillLayer
import org.maplibre.android.style.layers.PropertyFactory.fillAntialias
import org.maplibre.android.style.layers.PropertyFactory.fillColor
import org.maplibre.android.style.layers.PropertyFactory.fillOpacity
import org.maplibre.android.style.layers.PropertyFactory.fillOutlineColor
import org.maplibre.android.style.layers.PropertyFactory.fillPattern
import org.maplibre.android.style.layers.PropertyFactory.fillSortKey
import org.maplibre.android.style.layers.PropertyFactory.fillTranslate
import org.maplibre.android.style.layers.PropertyFactory.fillTranslateAnchor
import sk.amir.maps.common.MapLayerTransition
import sk.amir.maps.common.Ex
import sk.amir.maps.common.toOther
import sk.amir.maps.sources.MapSource

internal actual fun MapFillStyleLayer(
    identifier: String,
    source: MapSource
) : MapFillStyleLayer {
    return MapFillStyleLayerImpl(
        FillLayer(
            identifier,
            source.identifier
        )
    )
}

internal class MapFillStyleLayerImpl(
    nLayer: FillLayer
) : MapStyleLayerImpl<FillLayer>(nLayer), MapFillStyleLayer  {
    override var fillAntialiased: Ex
        get() = nLayer.fillAntialias.toOther()
        set(value) { nLayer.setProperties(fillAntialias(value.toOther()))}
    override var fillColor: Ex
        get() = nLayer.fillColor.toOther()
        set(value) { nLayer.setProperties(fillColor(value.toOther()))}
    override var fillColorTransition: MapLayerTransition
        get() = nLayer.fillColorTransition.toOther()
        set(value) { nLayer.fillColorTransition = value.toOther() }
    override var fillOpacity: Ex
        get() = nLayer.fillOpacity.toOther()
        set(value) { nLayer.setProperties(fillOpacity(value.toOther()))}
    override var fillOpacityTransition: MapLayerTransition
        get() = nLayer.fillOpacityTransition.toOther()
        set(value) { nLayer.fillOpacityTransition = value.toOther() }
    override var fillOutlineColor: Ex
        get() = nLayer.fillOutlineColor.toOther()
        set(value) { nLayer.setProperties(fillOutlineColor(value.toOther()))}
    override var fillOutlineColorTransition: MapLayerTransition
        get() = nLayer.fillOutlineColorTransition.toOther()
        set(value) { nLayer.fillOutlineColorTransition = value.toOther() }
    override var fillPattern: Ex
        get() = nLayer.fillPattern.toOther()
        set(value) { nLayer.setProperties(fillPattern(value.toOther()))}
    override var fillPatternTransition: MapLayerTransition
        get() = nLayer.fillTranslateTransition.toOther()
        set(value) { nLayer.fillTranslateTransition = value.toOther() }
    override var fillSortKey: Ex
        get() = nLayer.fillSortKey.toOther()
        set(value) { nLayer.setProperties(fillSortKey(value.toOther()))}
    override var fillTranslation: Ex
        get() = nLayer.fillTranslate.toOther()
        set(value) { nLayer.setProperties(fillTranslate(value.toOther()))}
    override var fillTranslationAnchor: Ex
        get() = nLayer.fillTranslateAnchor.toOther()
        set(value) { nLayer.setProperties(fillTranslateAnchor(value.toOther()))}
    override var fillTranslationTransition: MapLayerTransition
        get() = nLayer.fillTranslateTransition.toOther()
        set(value) { nLayer.fillTranslateTransition = value.toOther() }

    override fun setFilter(filter: Ex) {
        nLayer.setFilter(filter.toOther())
    }
}

