
package sk.amir.maps.layers

import cocoapods.MapLibre.MLNBackgroundStyleLayer
import sk.amir.maps.common.Ex
import sk.amir.maps.common.toOther

internal actual fun MapBackgroundStyleLayer(
    identifier: String
) : MapBackgroundStyleLayer {
    return MapBackgroundStyleLayerImpl(
        MLNBackgroundStyleLayer(identifier = identifier)
    )
}

internal class MapBackgroundStyleLayerImpl(
    nLayer: MLNBackgroundStyleLayer
) : MapStyleLayerImpl<MLNBackgroundStyleLayer>(nLayer), MapBackgroundStyleLayer {
    override var backgroundColor: Ex
        get() = nLayer.backgroundColor.toOther()
        set(value) { nLayer.backgroundColor = value.toOther() }

    override var backgroundOpacity: Ex
        get() = nLayer.backgroundOpacity.toOther()
        set(value) { nLayer.backgroundOpacity = value.toOther() }

    override var backgroundPattern: Ex
        get() = nLayer.backgroundPattern.toOther()
        set(value) { nLayer.backgroundPattern = value.toOther() }
}
