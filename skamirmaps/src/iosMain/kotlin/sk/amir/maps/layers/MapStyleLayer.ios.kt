
package sk.amir.maps.layers

import cocoapods.MapLibre.MLNBackgroundStyleLayer
import cocoapods.MapLibre.MLNCircleStyleLayer
import cocoapods.MapLibre.MLNFillStyleLayer
import cocoapods.MapLibre.MLNLineStyleLayer
import cocoapods.MapLibre.MLNStyleLayer
import cocoapods.MapLibre.MLNSymbolStyleLayer

internal open class MapStyleLayerImpl<L: MLNStyleLayer>(
    val nLayer: L
) : MapStyleLayer {
    override val identifier: String
        get() = nLayer.identifier

    override var visible: Boolean by nLayer::visible

    override var maxZoomLevel: Float
        get() = nLayer.maximumZoomLevel
        set(value) { nLayer.maximumZoomLevel = value }

    override var minZoomLevel: Float
        get() = nLayer.minimumZoomLevel
        set(value) { nLayer.minimumZoomLevel = value }
}

internal fun MLNStyleLayer.toOther(): MapStyleLayer {
    return when (this) {
        is MLNCircleStyleLayer -> MapCircleStyleLayerImpl(this)
        is MLNSymbolStyleLayer -> MapSymbolStyleLayerImpl(this)
        is MLNLineStyleLayer -> MapLineStyleLayerImpl(this)
        is MLNFillStyleLayer -> MapFillStyleLayerImpl(this)
        is MLNBackgroundStyleLayer -> MapBackgroundStyleLayerImpl(this)
        else -> {
            println("Unsupported layer: $description")
            MapStyleLayerImpl(this)
        }
    }
}

internal fun MapStyleLayer.toOther(): MLNStyleLayer {
    return when (this) {
        is MapCircleStyleLayerImpl -> nLayer
        is MapSymbolStyleLayerImpl -> nLayer
        is MapFillStyleLayerImpl -> nLayer
        is MapLineStyleLayerImpl -> nLayer
        is MapBackgroundStyleLayerImpl -> nLayer
        else -> TODO("$identifier")
    }
}
