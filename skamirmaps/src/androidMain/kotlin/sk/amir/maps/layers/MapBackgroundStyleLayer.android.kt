
package sk.amir.maps.layers

import org.maplibre.android.style.layers.BackgroundLayer
import org.maplibre.android.style.layers.PropertyFactory.backgroundColor
import org.maplibre.android.style.layers.PropertyFactory.backgroundOpacity
import org.maplibre.android.style.layers.PropertyFactory.backgroundPattern
import sk.amir.maps.common.Ex
import sk.amir.maps.common.toOther

internal actual fun MapBackgroundStyleLayer(
    identifier: String
) : MapBackgroundStyleLayer {
    return MapBackgroundStyleLayerImpl(
        BackgroundLayer(identifier)
    )
}

internal class MapBackgroundStyleLayerImpl(
    nLayer: BackgroundLayer
) : MapStyleLayerImpl<BackgroundLayer>(nLayer), MapBackgroundStyleLayer {
    override var backgroundColor: Ex
        get() = nLayer.backgroundColor.toOther()
        set(value) { nLayer.setProperties(backgroundColor(value.toOther())) }
    override var backgroundOpacity: Ex
        get() = nLayer.backgroundOpacity.toOther()
        set(value) { nLayer.setProperties(backgroundOpacity(value.toOther())) }
    override var backgroundPattern: Ex
        get() = nLayer.backgroundPattern.toOther()
        set(value) { nLayer.setProperties(backgroundPattern(value.toOther())) }
}

