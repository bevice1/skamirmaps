
package sk.amir.maps

import androidx.compose.ui.graphics.ImageBitmap
import cocoapods.MapLibre.MLNSource
import cocoapods.MapLibre.MLNStyle
import cocoapods.MapLibre.MLNStyleLayer
import cocoapods.MapLibre.MLNTransition
import cocoapods.MapLibre.MLNTransitionMake
import kotlinx.cinterop.CValue
import kotlinx.cinterop.refTo
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGBitmapContextCreate
import platform.CoreGraphics.CGBitmapContextCreateImage
import platform.CoreGraphics.CGColorSpaceCreateDeviceRGB
import platform.CoreGraphics.CGImageAlphaInfo
import platform.Foundation.NSMapGet
import platform.UIKit.UIImage
import sk.amir.maps.common.MapLayerTransition
import sk.amir.maps.common.MapLayerTransitionImpl
import sk.amir.maps.layers.MapBackgroundStyleLayerImpl
import sk.amir.maps.layers.MapCircleStyleLayerImpl
import sk.amir.maps.layers.MapFillStyleLayerImpl
import sk.amir.maps.layers.MapLineStyleLayerImpl
import sk.amir.maps.layers.MapStyleLayer
import sk.amir.maps.layers.MapSymbolStyleLayerImpl
import sk.amir.maps.layers.toOther
import sk.amir.maps.sources.MapShapeSourceImpl
import sk.amir.maps.sources.MapSource
import sk.amir.maps.sources.toOther

internal class MapStyleImpl(
    private val nStyle: MLNStyle
) : InternalMapStyle {
    override val layers: List<MapStyleLayer>
        get() = nStyle.layers
            .map { (it as MLNStyleLayer).toOther() }

    override val sources: List<MapSource>
        get() = nStyle.sources
            .map { (it as MLNSource).toOther() }

    override fun addLayer(layer: MapStyleLayer) {
        nStyle.addLayer(layer.toOther())
    }

    override fun addLayerAbove(layer: MapStyleLayer, otherLayerId: String) {
        val otherLayer = layerWithIdentifier(otherLayerId)
        if (otherLayer == null) {
            println("Unable to add layer ${layer.identifier} above $otherLayerId")
            return addLayer(layer)
        }
        nStyle.insertLayer(layer.toOther(), aboveLayer = otherLayer.toOther())
    }

    override fun addLayerBelow(layer: MapStyleLayer, otherLayerId: String) {
        val otherLayer = layerWithIdentifier(otherLayerId)
        if (otherLayer == null) {
            println("Unable to add layer ${layer.identifier} below $otherLayerId")
            return addLayer(layer)
        }
        nStyle.insertLayer(layer.toOther(), belowLayer = otherLayer.toOther())
    }

    override fun removeLayer(layer: MapStyleLayer) {
        if (nStyle.layerWithIdentifier(layer.identifier) == null) {
            println("layer ${layer.identifier} cannot be removed because it isnt' attached to style")
            return
        }
        nStyle.removeLayer(layer.nLayer)
    }

    override fun layerWithIdentifier(id: String): MapStyleLayer? {
        return nStyle.layerWithIdentifier(id)?.toOther()
    }

    override fun addSource(source: MapSource) {
        nStyle.addSource(source.toOther())
    }

    override fun removeSource(source: MapSource) {
        if (nStyle.sourceWithIdentifier(source.identifier) == null) {
            println("source ${source.identifier} cannot be removed because it isnt' attached to style")
            return
        }
        nStyle.removeSource(source.nSource)
    }

    override fun sourceWithIdentifier(id: String): MapSource? {
        return nStyle.sourceWithIdentifier(id)?.toOther()
    }

    override fun addImage(name: String, imageBitmap: ImageBitmap) {
        imageBitmap.toUIImage()?.let {
            nStyle.setImage(it, forName = name)
        }
    }

    override fun removeImage(name: String) {
        nStyle.removeImageForName(name)
    }

    override fun hasImage(name: String): Boolean {
        return nStyle.imageForName(name) != null
    }

    private fun ImageBitmap.toUIImage(): UIImage? {
        val width = this.width
        val height = this.height
        val buffer = IntArray(width * height)

        this.readPixels(buffer)

        val colorSpace = CGColorSpaceCreateDeviceRGB()
        val context = CGBitmapContextCreate(
            data = buffer.refTo(0),
            width = width.toULong(),
            height = height.toULong(),
            bitsPerComponent = 8u,
            bytesPerRow = (4 * width).toULong(),
            space = colorSpace,
            bitmapInfo = CGImageAlphaInfo.kCGImageAlphaPremultipliedLast.value
        )

        val cgImage = CGBitmapContextCreateImage(context)
        return cgImage?.let { UIImage.imageWithCGImage(it) }
    }
}

private val MapStyleLayer.nLayer: MLNStyleLayer get() = when (this) {
    // make casts explicit
    is MapCircleStyleLayerImpl -> nLayer
    is MapFillStyleLayerImpl -> nLayer
    is MapSymbolStyleLayerImpl -> nLayer
    is MapBackgroundStyleLayerImpl -> nLayer
    is MapLineStyleLayerImpl -> nLayer
    else -> TODO()
}

private val MapSource.nSource: MLNSource get() = when (this) {
    is MapShapeSourceImpl -> nSource
    else -> TODO()
}

internal fun CValue<MLNTransition>.toOther(): MapLayerTransition {
    return useContents {
        MapLayerTransitionImpl(
            delay = delay,
            duration = duration
        )
    }
}

internal fun MapLayerTransition.toOther(): CValue<MLNTransition> {
    return MLNTransitionMake(
        duration, delay
    )
}
