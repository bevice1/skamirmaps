
package sk.amir.maps.compose

import androidx.compose.runtime.AbstractApplier
import sk.amir.maps.InternalMapStyle
import sk.amir.maps.MapState
import sk.amir.maps.layers.MapStyleLayer

internal sealed class LayerInsertKind {
    class Below(val layerId: String) : LayerInsertKind()
    class Above(val layerId: String) : LayerInsertKind()
    // place on top
    data object Default : LayerInsertKind()
}

internal fun InternalMapStyle.addLayer(layer: MapStyleLayer, kind: LayerInsertKind) {
    when (kind) {
        is LayerInsertKind.Below -> addLayerBelow(layer = layer, otherLayerId = kind.layerId)
        is LayerInsertKind.Above -> addLayerAbove(layer = layer, otherLayerId = kind.layerId)
        is LayerInsertKind.Default -> addLayer(layer)
    }
}

internal class MapApplier(
    val map: MapState,
) : AbstractApplier<MapLayoutNode>(RootMapLayoutNode(map)) {
    private val layout inline get() = current as RootMapLayoutNode

    fun isSourceAttached(sourceId: String) = layout.isSourceAttached(sourceId)

    override fun insertBottomUp(index: Int, instance: MapLayoutNode) {
        // no-op
    }

    override fun insertTopDown(index: Int, instance: MapLayoutNode) {
        layout.insertTopDown(index, instance)
    }

    override fun move(from: Int, to: Int, count: Int) {
        layout.move(from, to, count)
    }

    override fun onClear() {
        layout.onClear()
    }

    override fun remove(index: Int, count: Int) {
        layout.remove(index, count)
    }
}
