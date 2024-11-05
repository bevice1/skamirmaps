
package sk.amir.maps.compose.simple

import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import sk.amir.maps.common.Ex
import sk.amir.maps.common.LatLng
import sk.amir.maps.common.MapShapeSourceOptions
import sk.amir.maps.common.toRGBHexString
import sk.amir.maps.compose.Anchor
import sk.amir.maps.compose.MapApplier
import sk.amir.maps.compose.layer.SymbolLayer
import sk.amir.maps.compose.sourcebuilder.ShapeSourceBuilderComposable

@Composable
fun Symbol(
    center: LatLng,
    text: String? = null,
    textColor: Color? = null,
    textOpacity: Double = 1.0,
    textAnchor: Anchor = Anchor.Bottom,
    iconName: String? = null,
    iconOpacity: Double = 1.0,
    iconScale: Double = 1.0,
    iconAnchor: Anchor = Anchor.Bottom
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
                    SymbolText to text,
                    SymbolTextColor to textColor?.toRGBHexString(),
                    SymbolIconName to iconName,
                    SymbolIconOpacity to iconOpacity,
                    SymbolTextOpacity to textOpacity,
                    SymbolTextAnchor to textAnchor.styleText,
                    SymbolIconScale to iconScale,
                    SymbolIconAnchor to iconAnchor.styleText
                )
            )
        }
    ) {
        SymbolLayer(
            icon = Ex.image(Ex.get(SymbolIconName)),
            text = Ex.get(SymbolText),
            textColor = Ex.toColor(Ex.get(SymbolTextColor)),
            iconOpacity = Ex.get(SymbolIconOpacity),
            textOpacity = Ex.get(SymbolTextOpacity),
            textAnchor = Ex.get(SymbolTextAnchor),
            iconScale = Ex.get(SymbolIconScale),
            iconAnchor = Ex.get(SymbolIconAnchor),
        )
    }
}

private const val SymbolText = "text"
private const val SymbolTextColor = "textColor"
private const val SymbolIconName = "icon"
private const val SymbolIconOpacity = "iconOpacity"
private const val SymbolTextOpacity = "textOpacity"
private const val SymbolTextAnchor = "textAnchor"
private const val SymbolIconScale = "iconScale"
private const val SymbolIconAnchor = "iconAnchor"
