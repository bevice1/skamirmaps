package sk.amir.maps.compose.layer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import sk.amir.maps.common.Ex
import sk.amir.maps.compose.FillLayerNode
import sk.amir.maps.compose.MapApplier
import sk.amir.maps.compose.getNextLayerId
import sk.amir.maps.compose.readSourceFromMapApplier
import sk.amir.maps.compose.setNonDefault
import sk.amir.maps.compose.sourcebuilder.LayerBuilder
import sk.amir.maps.compose.sourcebuilder.LocalShapeSourceId
import sk.amir.maps.internalStyle
import sk.amir.maps.layers.MapFillStyleLayer
import sk.amir.maps.sources.MapShapeSource
import kotlin.jvm.JvmOverloads

@Composable
@JvmOverloads
internal fun LayerBuilder.FillLayer(
    color: Ex = Ex.EmptyDefault,
    opacity: Ex = Ex.EmptyDefault,
    antialiased: Ex = Ex.EmptyDefault,
    outlineColor: Ex = Ex.EmptyDefault,
    sortKey: Ex = Ex.EmptyDefault,
    pattern: Ex = Ex.EmptyDefault,
    translation: Ex = Ex.EmptyDefault,
    translationAnchor: Ex = Ex.EmptyDefault,
    filter: Ex = Ex.EmptyDefault,
    minZoomLevel: Float = 0f,
    maxZoomLevel: Float = 24f,
) {
    val mapApplier = currentComposer.applier as MapApplier
    val sourceId = LocalShapeSourceId.current
    val style = mapApplier.map.internalStyle ?: return

    val source = readSourceFromMapApplier(
        style = style,
        sourceId = sourceId,
        mapApplier = mapApplier
    ) ?: return

    // when style changes we recompose
    key(style) {
        val layerId = remember { getNextLayerId() }
        ComposeNode<FillLayerNode, MapApplier>(
            factory = {
                val layer = MapFillStyleLayer(
                    identifier = layerId,
                    source = source,
                )
                FillLayerNode(layer = layer)
            },
            update = {
                setNonDefault(color) { layer.fillColor = it }
                setNonDefault(opacity) { layer.fillOpacity = it }
                setNonDefault(antialiased) { layer.fillAntialiased = it }
                setNonDefault(outlineColor) { layer.fillOutlineColor = it }
                setNonDefault(sortKey) { layer.fillSortKey = it }
                setNonDefault(pattern) { layer.fillPattern = it }
                setNonDefault(translation) { layer.fillTranslation = it }
                setNonDefault(translationAnchor) { layer.fillTranslationAnchor = it }
                setNonDefault(filter) { layer.setFilter(it) }
                set(minZoomLevel) { layer.minZoomLevel = it }
                set(maxZoomLevel) { layer.maxZoomLevel = it }
            }
        )
    }
}
