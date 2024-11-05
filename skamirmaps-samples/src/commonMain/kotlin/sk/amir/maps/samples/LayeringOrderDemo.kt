

package sk.amir.maps.samples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import sk.amir.maps.ComposeMap
import sk.amir.maps.MapUiSettings
import sk.amir.maps.common.LatLng
import sk.amir.maps.compose.simple.Fill
import sk.amir.maps.rememberMapState
import sk.amir.maps.updateCamera

@Composable
fun LayeringOrderDemo() {
    val state = rememberMapState(
        onMapLoad = {
            commandHandle?.updateCamera(
                target = PointLondon,
                zoom = 6.0,
                animated = false,
            )
        }
    )

    var order by remember { mutableStateOf(true) }

    val displacement = 0.25
    val green = remember { movableContentOf { FillWithOffset(0.0, 0.0, Color.Green) } }
    val blue = remember { movableContentOf { FillWithOffset(-displacement/2, displacement, Color.Blue) } }
    val red = remember { movableContentOf { FillWithOffset(displacement/2, displacement, Color.Red) } }

    Column {
        Box(Modifier.weight(1f).fillMaxWidth()) {
            ComposeMap(
                modifier = Modifier.fillMaxSize(),
                styleUrl = StyleUrls.default,
                uiSettings = remember { MapUiSettings() },
                mapState = state,
                imageContainer = listOf(),
            ) {
                if (order) {
                    blue()
                    red()
                    green()
                } else {
                    green()
                    red()
                    blue()
                }
            }
        }

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(if (order) "blue, red, green" else "green, red, blue")
            Button(
                onClick = { order = !order },
            ) {
                Text("Reorder")
            }
        }
    }
}

@Composable
private fun FillWithOffset(
    offsetX: Double,
    offsetY: Double,
    color: Color
) {
    val baseCoord = PointLondon.let {
        LatLng(
            latitude = it.latitude + offsetY,
            longitude = it.longitude + offsetX
        )
    }
    Fill(
        points = listOf(
            baseCoord,
            LatLng(baseCoord.latitude + 0.5, baseCoord.longitude),
            LatLng(baseCoord.latitude + 0.5, baseCoord.longitude + 0.5),
            LatLng(baseCoord.latitude, baseCoord.longitude + 0.5),
        ),
        color = color,
        opacity = 0.95,
    )
}
