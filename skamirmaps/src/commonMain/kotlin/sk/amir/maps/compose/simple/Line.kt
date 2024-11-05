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
import sk.amir.maps.compose.layer.LineLayer
import sk.amir.maps.compose.sourcebuilder.ShapeSourceBuilderComposable

@Composable
fun Line(
    points: List<LatLng>,
    width: Double = 1.0,
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
            addLine(
                id = "0",
                points,
                mapOf(
                    LineColor to color?.toRGBHexString(),
                    LineOpacity to opacity,
                    LineWidth to width,
                )
            )
        }
    ) {
        LineLayer(
            color = Ex.toColor(Ex.get(LineColor)),
            opacity = Ex.get(LineOpacity),
            width = Ex.get(LineWidth),
        )
    }
}

private const val LineColor = "color"
private const val LineOpacity = "opacity"
private const val LineWidth = "width"
