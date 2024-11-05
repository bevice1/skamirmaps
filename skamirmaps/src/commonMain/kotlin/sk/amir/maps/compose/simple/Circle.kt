
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
import sk.amir.maps.compose.layer.CircleLayer
import sk.amir.maps.compose.sourcebuilder.ShapeSourceBuilderComposable

@Composable
fun Circle(
    center: LatLng,
    circleRadius: Double? = null,
    circleColor: Color? = null,
    circleBlur: Double? = null,
    circleOpacity: Double? = null,
    circleStrokeWidth: Double? = null,
    circleStrokeColor: Color? = null,
    circleStrokeOpacity: Double? = null,
) {
    val mapApplier = currentComposer.applier as MapApplier

    if (mapApplier.map.style == null) {
        return
    }

    val options = remember { MapShapeSourceOptions() }
    ShapeSourceBuilderComposable(
        options = options,
        sourceBuilder = {
            addPoint(
                id = "0",
                center,
                mapOf(
                    CircleRadius to circleRadius,
                    CircleColor to circleColor?.toRGBHexString(),
                    CircleOpacity to circleOpacity,
                    CircleBlur to circleBlur,
                    CircleStrokeWidth to circleStrokeWidth,
                    CircleStrokeColor to circleStrokeColor?.toRGBHexString(),
                    CircleStrokeOpacity to circleStrokeOpacity
                )
            )
        }
    ) {
        CircleLayer(
            radius = Ex.get(CircleRadius),
            color = Ex.toColor(Ex.get(CircleColor)),
            opacity = Ex.get(CircleOpacity),
            blur = Ex.get(CircleBlur),
            strokeWidth = Ex.get(CircleStrokeWidth),
            strokeColor = Ex.toColor(Ex.get(CircleStrokeColor)),
            strokeOpacity = Ex.get(CircleStrokeOpacity),
        )
    }
}

private const val CircleRadius = "CIRCLE_RADIUS"
private const val CircleColor = "CIRCLE_COLOR"
private const val CircleBlur = "CIRCLE_BLUR"
private const val CircleOpacity = "CIRCLE_OPACITY"
private const val CircleStrokeWidth = "CIRCLE_STROKE_WIDTH"
private const val CircleStrokeColor = "CIRCLE_STROKE_COLOR"
private const val CircleStrokeOpacity = "CIRCLE_STROKE_OPACITY"
