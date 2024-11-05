
package sk.amir.maps.layers

import org.maplibre.android.style.layers.Layer
import org.maplibre.android.style.layers.Property
import org.maplibre.android.style.layers.PropertyFactory
import sk.amir.maps.common.Ex
import sk.amir.maps.common.toOther

internal open class MapStyleLayerImpl<L : Layer>(
    val nLayer: L
) : MapStyleLayer {
    override val identifier: String
        get() = nLayer.id
    override var maxZoomLevel: Float by nLayer::maxZoom
    override var minZoomLevel: Float by nLayer::minZoom
    override var visible: Boolean
        get() = nLayer.visibility.toOther().let {
            when (it) {
                is Ex.Literal.String -> it.string == Property.VISIBLE
                else -> false
            }
        }
        set(value) {
            val property = if (value) Property.VISIBLE else Property.NONE
            nLayer.setProperties(PropertyFactory.visibility(property))
        }
}
