
package sk.amir.maps.samples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import sk.amir.maps.ComposeMap
import sk.amir.maps.MapUiSettings
import sk.amir.maps.compose.simple.Fill
import sk.amir.maps.compose.simple.Line
import sk.amir.maps.rememberMapState
import sk.amir.maps.updateCamera

@Composable
internal fun PolygonDemo() {
    val scope = rememberCoroutineScope()
    val sliderState = remember { SliderState(0.5f) }
    Column(
        modifier = Modifier.padding(bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.TopCenter,
        ) {
            val mapState = rememberMapState(
                onMapLoad = {
                    scope.launch {
                        commandHandle?.updateCamera(
                            target = PointNitra,
                            zoom = 9.0,
                            animated = false,
                        )
                    }
                },
            )

            ComposeMap(
                modifier = Modifier.fillMaxSize(),
                uiSettings = MapUiSettings(),
                styleUrl = StyleUrls.default,
                mapState = mapState
            ) {
                Fill(
                    points = PolygonNitra,
                    opacity = 0.5,
                    color = createColorForSliderValue(sliderState.value),
                )
                Line(
                    points = PolygonNitra,
                    width = 5.0,
                    color = createColorForSliderValue(1 - sliderState.value),
                )
            }
        }
        Slider(sliderState, Modifier.padding(horizontal = 16.dp))
    }
}

/**
 * Transition between green and blue
 */
private fun createColorForSliderValue(value: Float): Color {
    return Color((value * 255).toInt() + ((1 - value) * 255).toInt() * 256)
}
