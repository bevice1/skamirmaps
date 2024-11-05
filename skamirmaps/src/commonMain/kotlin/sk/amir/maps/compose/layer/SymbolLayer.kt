
package sk.amir.maps.compose.layer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import sk.amir.maps.common.Ex
import sk.amir.maps.compose.MapApplier
import sk.amir.maps.compose.SymbolLayerNode
import sk.amir.maps.compose.getNextLayerId
import sk.amir.maps.compose.readSourceFromMapApplier
import sk.amir.maps.compose.setNonDefault
import sk.amir.maps.compose.sourcebuilder.LayerBuilder
import sk.amir.maps.compose.sourcebuilder.LocalShapeSourceId
import sk.amir.maps.internalStyle
import sk.amir.maps.layers.MapSymbolStyleLayer
import sk.amir.maps.sources.MapShapeSource
import kotlin.jvm.JvmOverloads

@Composable
@JvmOverloads
internal fun LayerBuilder.SymbolLayer(
    icon: Ex = Ex.EmptyDefault,
    iconColor: Ex = Ex.EmptyDefault,
    iconOpacity: Ex = Ex.EmptyDefault,
    iconScale: Ex = Ex.EmptyDefault,
    iconAnchor: Ex = Ex.EmptyDefault,
    iconOffset: Ex = Ex.EmptyDefault,
    iconAllowsOverlap: Ex = Ex.EmptyDefault,
    iconIgnoresPlacement: Ex = Ex.EmptyDefault,
    iconOptional: Ex = Ex.EmptyDefault,
    iconPadding: Ex = Ex.EmptyDefault,
    iconPitchAlignment: Ex = Ex.EmptyDefault,
    iconRotation: Ex = Ex.EmptyDefault,
    iconTextFit: Ex = Ex.EmptyDefault,
    iconTextFitPadding: Ex = Ex.EmptyDefault,
    iconRotationAlignment: Ex = Ex.EmptyDefault,
    iconTranslationAnchor: Ex = Ex.EmptyDefault,
    keepsIconUpright: Ex = Ex.EmptyDefault,
    text: Ex = Ex.EmptyDefault,
    textColor: Ex = Ex.EmptyDefault,
    textRadialOffset: Ex = Ex.EmptyDefault,
    textVariableAnchor: Ex = Ex.EmptyDefault,
    textAnchor: Ex = Ex.EmptyDefault,
    textOpacity: Ex = Ex.EmptyDefault,
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
        ComposeNode<SymbolLayerNode, MapApplier>(
            factory = {
                val layer = MapSymbolStyleLayer(
                    identifier = layerId,
                    source = source
                )
                SymbolLayerNode(layer = layer)
            },
            update = {
                setNonDefault(icon) { layer.iconImageName = it }
                setNonDefault(iconColor) { layer.iconColor = it }
                setNonDefault(iconOpacity) { layer.iconOpacity = it }
                setNonDefault(iconScale) { layer.iconScale = it }
                setNonDefault(iconAnchor) { layer.iconAnchor = it }
                setNonDefault(iconOffset) { layer.iconOffset = it }
                setNonDefault(iconAllowsOverlap) { layer.iconAllowsOverlap = it }
                setNonDefault(iconIgnoresPlacement) { layer.iconIgnoresPlacement = it }
                setNonDefault(iconOptional) { layer.iconOptional = it }
                setNonDefault(iconPadding) { layer.iconPadding = it }
                setNonDefault(iconPitchAlignment) { layer.iconPitchAlignment = it }
                setNonDefault(iconRotation) { layer.iconRotation = it }
                setNonDefault(iconTextFit) { layer.iconTextFit = it }
                setNonDefault(iconTextFitPadding) { layer.iconTextFitPadding = it }
                setNonDefault(iconRotationAlignment) { layer.iconRotationAlignment = it }
                setNonDefault(iconTranslationAnchor) { layer.iconTranslationAnchor = it }
                setNonDefault(keepsIconUpright) { layer.keepsIconUpright = it }
                setNonDefault(text) { layer.text = it }
                setNonDefault(textColor) { layer.textColor = it }
                setNonDefault(textRadialOffset) { layer.textRadialOffset = it }
                setNonDefault(textVariableAnchor) { layer.textVariableAnchor = it }
                setNonDefault(textAnchor) { layer.textAnchor = it }
                setNonDefault(textOpacity) { layer.textOpacity = it }
                setNonDefault(filter) { layer.setFilter(it) }
                set(minZoomLevel) { layer.minZoomLevel = it }
                set(maxZoomLevel) { layer.maxZoomLevel = it }
            }
        )
    }
}
