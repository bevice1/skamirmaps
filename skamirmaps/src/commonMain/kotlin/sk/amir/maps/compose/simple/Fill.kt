package sk.amir.maps.compose.simple

import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import sk.amir.maps.common.Ex
import sk.amir.maps.common.LatLng
import sk.amir.maps.common.MapShapeSourceOptions
import sk.amir.maps.common.toRGBHexString
import sk.amir.maps.compose.MapApplier
import sk.amir.maps.compose.layer.FillLayer
import sk.amir.maps.compose.sourcebuilder.ShapeSourceBuilderComposable

@Composable
fun Fill(
    points: List<LatLng>,
    color: Color? = null,
    opacity: Double = 1.0,
) {
    val mapApplier = currentComposer.applier as MapApplier

    if (mapApplier.map.style == null) {
        return
    }

    val options = remember { MapShapeSourceOptions() }
    ShapeSourceBuilderComposable(
        options = options,
        sourceBuilder = {
            addPolygon(
                id = "0",
                listOf(points),
                mapOf(
                    PolygonFillColor to color?.toRGBHexString(),
                    PolygonFillOpacity to opacity
                )
            )
        }
    ) {
        FillLayer(
            color = Ex.toColor(Ex.get(PolygonFillColor)),
            opacity = Ex.get(PolygonFillOpacity),
        )
    }
}

private const val PolygonFillColor = "fillColor"
private const val PolygonFillOpacity = "fillOpacity"
