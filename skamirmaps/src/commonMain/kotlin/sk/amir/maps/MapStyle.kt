
package sk.amir.maps

import androidx.compose.ui.graphics.ImageBitmap
import sk.amir.maps.layers.MapStyleLayer
import sk.amir.maps.sources.MapSource

interface MapStyle {

}

internal interface InternalMapStyle : MapStyle {
    val layers: List<MapStyleLayer>
    val sources: List<MapSource>

    fun addLayer(layer: MapStyleLayer)
    fun addLayerBelow(layer: MapStyleLayer, otherLayerId: String)
    fun addLayerAbove(layer: MapStyleLayer, otherLayerId: String)
    fun removeLayer(layer: MapStyleLayer)
    fun layerWithIdentifier(id: String): MapStyleLayer?

    fun addSource(source: MapSource)
    fun removeSource(source: MapSource)
    fun sourceWithIdentifier(id: String): MapSource?

    fun addImage(name: String, imageBitmap: ImageBitmap)
    fun removeImage(name: String)
    fun hasImage(name: String): Boolean
}
