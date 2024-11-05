
package sk.amir.maps

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import org.maplibre.android.maps.Style
import org.maplibre.android.style.layers.BackgroundLayer
import org.maplibre.android.style.layers.CircleLayer
import org.maplibre.android.style.layers.FillLayer
import org.maplibre.android.style.layers.Layer
import org.maplibre.android.style.layers.LineLayer
import org.maplibre.android.style.layers.SymbolLayer
import sk.amir.maps.layers.MapBackgroundStyleLayerImpl
import sk.amir.maps.layers.MapCircleStyleLayerImpl
import sk.amir.maps.layers.MapFillStyleLayerImpl
import sk.amir.maps.layers.MapLineStyleLayerImpl
import sk.amir.maps.layers.MapStyleLayer
import sk.amir.maps.layers.MapStyleLayerImpl
import sk.amir.maps.layers.MapSymbolStyleLayerImpl
import sk.amir.maps.sources.MapSource
import sk.amir.maps.sources.toOther

internal class MapStyleImpl(
    private val nStyle: Style
) : InternalMapStyle {
    override val sources: List<MapSource>
        get() = nStyle.sources
            .map { it.toOther() }

    override val layers: List<MapStyleLayer>
        get() = nStyle.layers
            .map { it.toOther() }

    override fun addLayer(layer: MapStyleLayer) {
        nStyle.addLayer(layer.toOther())
    }

    override fun addLayerAbove(layer: MapStyleLayer, otherLayerId: String) {
        nStyle.addLayerAbove(layer.toOther(), otherLayerId)
    }

    override fun addLayerBelow(layer: MapStyleLayer, otherLayerId: String) {
        nStyle.addLayerBelow(layer.toOther(), otherLayerId)
    }

    override fun removeLayer(layer: MapStyleLayer) {
        if (layerWithIdentifier(layer.identifier) == null) {
            println("Unable to remove layer ${layer.identifier}")
            return
        }
        nStyle.removeLayer((layer as MapStyleLayerImpl<*>).nLayer)
    }

    override fun layerWithIdentifier(id: String): MapStyleLayer? {
        return nStyle.getLayer(id)?.toOther()
    }

    override fun addSource(source: MapSource) {
        nStyle.addSource(source.toOther())
    }

    override fun removeSource(source: MapSource) {
        if (sourceWithIdentifier(source.identifier) == null) {
            println("Unable to remove source ${source.identifier}")
            return
        }
        nStyle.removeSource(source.identifier)
    }

    override fun sourceWithIdentifier(id: String): MapSource? {
        return nStyle.getSource(id)?.toOther()
    }

    override fun addImage(name: String, imageBitmap: ImageBitmap) {
        nStyle.addImage(name, imageBitmap.asAndroidBitmap())
    }

    override fun removeImage(name: String) {
        if (nStyle.isFullyLoaded) {
            nStyle.removeImage(name)
        }
    }

    override fun hasImage(name: String): Boolean {
        return nStyle.getImage(name) != null
    }

    @Deprecated("Please use only if necessary")
    internal fun _getStyle(): Style {
        return nStyle
    }
}

private fun Layer.toOther(): MapStyleLayer {
    return when (this) {
        is CircleLayer -> MapCircleStyleLayerImpl(this)
        is SymbolLayer -> MapSymbolStyleLayerImpl(this)
        is LineLayer -> MapLineStyleLayerImpl(this)
        is FillLayer -> MapFillStyleLayerImpl(this)
        is BackgroundLayer -> MapBackgroundStyleLayerImpl(this)
        else -> {
            println("Unsupported layer: $this")
            MapStyleLayerImpl(this)
        }
    }
}

private fun MapStyleLayer.toOther(): Layer {
    return when (this) {
        is MapCircleStyleLayerImpl -> nLayer
        is MapSymbolStyleLayerImpl -> nLayer
        is MapFillStyleLayerImpl -> nLayer
        is MapLineStyleLayerImpl -> nLayer
        is MapBackgroundStyleLayerImpl -> nLayer
        else -> TODO("$identifier $javaClass")
    }
}
