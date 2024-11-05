
package sk.amir.maps.compose.sourcebuilder

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import sk.amir.maps.common.LatLng
import sk.amir.maps.common.MapShapeSourceOptions
import sk.amir.maps.compose.MapApplier
import sk.amir.maps.compose.ShapeSourceMapLayoutNode
import sk.amir.maps.compose.getNextSourceId
import sk.amir.maps.internalStyle
import sk.amir.maps.shapes.MapFeature
import sk.amir.maps.shapes.MapGeometry
import sk.amir.maps.shapes.MapPointGeometry
import sk.amir.maps.shapes.MapPolygonGeometry
import sk.amir.maps.shapes.MapPolylineGeometry
import sk.amir.maps.sources.MapShapeSource
import kotlin.jvm.JvmOverloads

internal sealed interface LayerBuilder

private data object LayerBuilderImpl : LayerBuilder

@Composable
internal fun ShapeSourceBuilderComposable(
    options: MapShapeSourceOptions,
    sourceBuilder: ShapeSourceBuilder.() -> Unit,
    layers: @Composable LayerBuilder.() -> Unit,
) {
    ShapeSourceComposable(
        options,
        ShapeSourceBuilder(sourceBuilder).getFeatures(),
        layers = layers
    )
}


@Composable
private fun ShapeSourceComposable(
    options: MapShapeSourceOptions,
    // this comparison doesn't work properly
    features: List<MapFeature>,
    layers: @Composable LayerBuilder.() -> Unit
) {
    val mapApplier = currentComposer.applier as MapApplier
    val style = mapApplier.map.internalStyle
    // when style changes we recompose, when options changes we recompose
    if (style != null) {
        key(options) {
            val sourceId = remember { getNextSourceId() }
            ComposeNode<ShapeSourceMapLayoutNode, MapApplier>(
                factory = {
                    val source = MapShapeSource(
                        identifier = sourceId,
                        features = features,
                        options = options
                    )
                    ShapeSourceMapLayoutNode(source)
                },
                update = {
                    set(features) {
                        val source =
                            style.sourceWithIdentifier(sourceId) as? MapShapeSource ?: TODO()
                        source.setFeatureCollection(features)
                    }
                }
            )
            CompositionLocalProvider(
                LocalShapeSourceId provides sourceId,
            ) {
                LayerBuilderImpl.layers()
            }
        }
    }
}

internal val LocalShapeSourceId = compositionLocalOf<String>{ TODO() }

internal class ShapeSourceBuilder(configure: ShapeSourceBuilder.() -> Unit) {
    private val features = mutableListOf<MapFeature>()
    init {
        configure()
    }

    private fun addFeature(
        id: String?,
        geometry: MapGeometry,
        attributes: Map<String, Any?>? = null,
    ) {
        MapFeature(
            id = id,
            geometry = geometry,
            attributes = attributes
                ?.mapNotNull {
                    if (it.value == null) {
                        null
                    } else {
                        it.key to it.value!!
                    }
                }
                ?.toMap()
        ).also { features.add(it) }
    }

    fun getFeatures() = features.toList()

    @JvmOverloads
    fun addPoint(
        id: String? = null,
        coordinate: LatLng,
        attributes: Map<String, Any?>? = null,
    ) = addFeature(
        id = id,
        geometry = MapPointGeometry(coordinate),
        attributes = attributes,
    )

    @JvmOverloads
    fun addLine(
        id: String? = null,
        coordinates: List<LatLng>,
        attributes: Map<String, Any?>? = null,
    ) = addFeature(
        id = id,
        geometry = MapPolylineGeometry(coordinates),
        attributes = attributes
    )

    @JvmOverloads
    fun addPolygon(
        id: String? = null,
        listOfPolygons: List<List<LatLng>>,
        attributes: Map<String, Any?>? = null,
    ) = addFeature(
        id = id,
        geometry = MapPolygonGeometry(listOfPolygons),
        attributes = attributes
    )
}
