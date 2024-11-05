package sk.amir.maps.compose.layer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import sk.amir.maps.common.Ex
import sk.amir.maps.compose.LineLayerNode
import sk.amir.maps.compose.MapApplier
import sk.amir.maps.compose.getNextLayerId
import sk.amir.maps.compose.readSourceFromMapApplier
import sk.amir.maps.compose.setNonDefault
import sk.amir.maps.compose.sourcebuilder.LayerBuilder
import sk.amir.maps.compose.sourcebuilder.LocalShapeSourceId
import sk.amir.maps.internalStyle
import sk.amir.maps.layers.MapLineStyleLayer
import sk.amir.maps.sources.MapShapeSource
import kotlin.jvm.JvmOverloads

@Composable
@JvmOverloads
internal fun LayerBuilder.LineLayer(
    width: Ex = Ex.EmptyDefault,
    color: Ex = Ex.EmptyDefault,
    opacity: Ex = Ex.EmptyDefault,
    cap: Ex = Ex.EmptyDefault,
    join: Ex = Ex.EmptyDefault,
    sortKey: Ex = Ex.EmptyDefault,
    pattern: Ex = Ex.EmptyDefault,
    dashPattern: Ex = Ex.EmptyDefault,
    translation: Ex = Ex.EmptyDefault,
    offset: Ex = Ex.EmptyDefault,
    roundLimit: Ex = Ex.EmptyDefault,
    gapWidth: Ex = Ex.EmptyDefault,
    gradient: Ex = Ex.EmptyDefault,
    miterLimit: Ex = Ex.EmptyDefault,
    blur: Ex = Ex.EmptyDefault,
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
        ComposeNode<LineLayerNode, MapApplier>(
            factory = {
                val layer = MapLineStyleLayer(
                    identifier = layerId,
                    source = source,
                )
                LineLayerNode(layer = layer)
            },
            update = {
                setNonDefault(width) { layer.lineWidth = it }
                setNonDefault(color) { layer.lineColor = it }
                setNonDefault(opacity) { layer.lineOpacity = it }
                setNonDefault(cap) { layer.lineCap = it }
                setNonDefault(join) { layer.lineJoin = it }
                setNonDefault(sortKey) { layer.lineSortKey = it }
                setNonDefault(pattern) { layer.linePattern = it }
                setNonDefault(dashPattern) { layer.lineDashPattern = it }
                setNonDefault(translation) { layer.lineTranslation = it }
                setNonDefault(offset) { layer.lineOffset = it }
                setNonDefault(roundLimit) { layer.lineRoundLimit = it }
                setNonDefault(gapWidth) { layer.lineGapWidth = it }
                setNonDefault(gradient) { layer.lineGradient = it }
                setNonDefault(miterLimit) { layer.lineMiterLimit = it }
                setNonDefault(blur) { layer.lineBlur = it }
                setNonDefault(filter) { layer.setFilter(it) }
                set(minZoomLevel) { layer.minZoomLevel = it }
                set(maxZoomLevel) { layer.maxZoomLevel = it }
            }
        )
    }
}
