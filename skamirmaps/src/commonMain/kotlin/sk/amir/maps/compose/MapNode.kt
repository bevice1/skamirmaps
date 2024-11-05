
package sk.amir.maps.compose

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateMapOf
import sk.amir.maps.InternalMapStyle
import sk.amir.maps.MapState
import sk.amir.maps.layers.MapCircleStyleLayer
import sk.amir.maps.layers.MapFillStyleLayer
import sk.amir.maps.layers.MapLineStyleLayer
import sk.amir.maps.layers.MapStyleLayer
import sk.amir.maps.layers.MapSymbolStyleLayer
import sk.amir.maps.sources.MapShapeSource
import sk.amir.maps.sources.MapSource

internal interface MapLayoutNode

// Layers
internal interface LayerMapLayoutNode : MapLayoutNode {
    val layer: MapStyleLayer
}

internal class CircleLayerNode(
    override val layer: MapCircleStyleLayer
) : LayerMapLayoutNode

internal class FillLayerNode(
    override val layer: MapFillStyleLayer
) : LayerMapLayoutNode

internal class LineLayerNode(
    override val layer: MapLineStyleLayer
) : LayerMapLayoutNode

internal class SymbolLayerNode(
    override val layer: MapSymbolStyleLayer
) : LayerMapLayoutNode

// Sources
internal interface SourceMapLayoutNode : MapLayoutNode {
    val source: MapSource
}

internal class ShapeSourceMapLayoutNode(
    override val source: MapShapeSource
) : SourceMapLayoutNode

// Root Layout Node
internal class RootMapLayoutNode(
    private val mapState: MapState
) : MapLayoutNode {
    private val mapStyle: InternalMapStyle? inline get() = mapState.style as? InternalMapStyle

    private val nodes = mutableListOf<MapLayoutNode>()
    private val sources = mutableStateMapOf<String, Unit>()
    internal fun isSourceAttached(sourceId: String) = derivedStateOf { sources.containsKey(sourceId) }
    fun insertTopDown(index: Int, node: MapLayoutNode) {
//        println("inserting node: $index $node")
        when (node) {
            is LayerMapLayoutNode -> {
                if (nodes.isNotEmpty()) {
                    // find first node that is lower than index
                    val pre = nodes.subList(0, index)
                    val post = if (index < nodes.lastIndex) nodes.subList(index, nodes.lastIndex) else emptyList()
                    val preLast = pre.lastOrNull { it is LayerMapLayoutNode } as? LayerMapLayoutNode
                    val postFirst =
                        post.firstOrNull { it is LayerMapLayoutNode } as? LayerMapLayoutNode
                    if (preLast != null) {
                        mapStyle?.addLayerAbove(node.layer, preLast.layer.identifier)
                    } else if (postFirst != null) {
                        mapStyle?.addLayerBelow(node.layer, postFirst.layer.identifier)
                    } else {
                        mapStyle?.addLayer(node.layer)
                    }
                } else {
                    mapStyle?.addLayer(node.layer)
                }
               nodes.add(index, node)
            }
            is SourceMapLayoutNode -> {
                sources[node.source.identifier] = Unit
                mapStyle?.addSource(node.source)
                nodes.add(index, node)
            }

            else -> TODO()
        }

    }

    fun onClear() {
        sources.clear()
//        println("clearing ${nodes.size}")
    }

    fun move(from: Int, to: Int, count: Int) {
        println("moving $from $to $count")
        TODO("Not yet implemented")
    }

    fun remove(index: Int, count: Int) {
//        println("removing $index $count of ${nodes.size}")
        val sub = nodes.subList(index, index + count)
        val layers = sub.filterIsInstance<LayerMapLayoutNode>()
        val sources = sub.filterIsInstance<SourceMapLayoutNode>()
        for (layer in layers) {
            mapStyle?.removeLayer(layer.layer)
        }
        for (source in sources) {
            this.sources.remove(source.source.identifier)
            mapStyle?.removeSource(source.source)
        }

        repeat(count) { nodes.removeAt(index) }
    }
}
