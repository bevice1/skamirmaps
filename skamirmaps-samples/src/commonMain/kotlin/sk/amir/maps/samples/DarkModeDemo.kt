
package sk.amir.maps.samples

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import sk.amir.maps.ComposeMap
import sk.amir.maps.MapUiSettings
import sk.amir.maps.common.LatLng
import sk.amir.maps.compose.simple.Circle
import sk.amir.maps.rememberMapState
import sk.amir.maps.updateCamera
import kotlin.random.Random

/**
 * SymbolLayer and CircleLayer
 * Demonstrates:
 *  - when the style is reloaded, the layer and sources are added again.
 */
@Composable
fun DarkModeDemo() {
    val mapState = rememberMapState(
        onMapLoad = {
            commandHandle?.updateCamera(
                target = PointNitra,
                zoom = 11.0,
                animated = false
            )
        }
    )
    var toggleSwitch by rememberSaveable { mutableStateOf(true) }

    val styleUrl = if (toggleSwitch) {
        StyleUrls.dark
    } else {
        StyleUrls.default
    }

    val points = remember {
        LatLng.getRandomPoints(Random(0), PointNitra, 0.03, 20)
    }

    Column(Modifier.fillMaxWidth()) {
        ComposeMap(
            modifier = Modifier.height(300.dp),
            uiSettings = MapUiSettings(),
            styleUrl = styleUrl,
            mapState = mapState
        ) {
            points.forEach {
                Circle(
                    center = it,
                    circleRadius = 5.0,
                    circleColor = Color.Blue,
                )
            }
        }

        Spacer(Modifier.weight(1f))
        Button(
            onClick = { toggleSwitch = !toggleSwitch }
        ) {
            Text("Next style")
        }
    }
}
