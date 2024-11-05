package sk.amir.maps.compose.layer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import sk.amir.maps.common.Ex
import sk.amir.maps.compose.CircleLayerNode
import sk.amir.maps.compose.MapApplier
import sk.amir.maps.compose.getNextLayerId
import sk.amir.maps.compose.readSourceFromMapApplier
import sk.amir.maps.compose.setNonDefault
import sk.amir.maps.compose.sourcebuilder.LayerBuilder
import sk.amir.maps.compose.sourcebuilder.LocalShapeSourceId
import sk.amir.maps.internalStyle
import sk.amir.maps.layers.MapCircleStyleLayer
import kotlin.jvm.JvmOverloads

@Composable
@JvmOverloads
internal fun LayerBuilder.CircleLayer(
    radius: Ex = Ex.EmptyDefault,
    color: Ex = Ex.EmptyDefault,
    opacity: Ex = Ex.EmptyDefault,
    strokeColor: Ex = Ex.EmptyDefault,
    strokeWidth: Ex = Ex.EmptyDefault,
    strokeOpacity: Ex = Ex.EmptyDefault,
    blur: Ex = Ex.EmptyDefault,
    translation: Ex = Ex.EmptyDefault,
    translationAnchor: Ex = Ex.EmptyDefault,
    sortKey: Ex = Ex.EmptyDefault,
    pitchAlignment: Ex = Ex.EmptyDefault,
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
        ComposeNode<CircleLayerNode, MapApplier>(
            factory = {
                val layer = MapCircleStyleLayer(
                    identifier = layerId,
                    source = source,
                )
                CircleLayerNode(layer = layer)
            },
            update = {
                setNonDefault(radius) { layer.circleRadius = it }
                setNonDefault(color) { layer.circleColor = it }
                setNonDefault(opacity) { layer.circleOpacity = it }
                setNonDefault(strokeColor) { layer.circleStrokeColor = it }
                setNonDefault(strokeWidth) { layer.circleStrokeWidth = it }
                setNonDefault(strokeOpacity) { layer.circleStrokeOpacity = it }
                setNonDefault(blur) { layer.circleBlur = it }
                setNonDefault(translation) { layer.circleTranslation = it }
                setNonDefault(translationAnchor) { layer.circleTranslationAnchor = it }
                setNonDefault(sortKey) { layer.circleSortKey = it }
                setNonDefault(pitchAlignment) { layer.circlePitchAlignment = it }
                setNonDefault(filter) { layer.setFilter(it) }
                set(minZoomLevel) { layer.minZoomLevel = it }
                set(maxZoomLevel) { layer.maxZoomLevel = it }
            }
        )
    }
}
