
package sk.amir.maps.samples

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sk.amir.maps.ComposeMap
import sk.amir.maps.MapUiSettings
import sk.amir.maps.common.LatLng
import sk.amir.maps.rememberMapState
import sk.amir.maps.updateCamera

@Composable
fun UiSettingsDemo() {
    val state = rememberMapState(
        onMapLoad = {
            commandHandle?.updateCamera(
                target = PointNitra,
                zoom = 14.0,
                animated = false
            )
        },
    )

    val settings = remember { MapUiSettings() }

    Column(
        Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        ComposeMap(
            modifier = Modifier.height(300.dp),
            styleUrl = StyleUrls.default,
            mapState = state,
            uiSettings = settings
        ) {}

        CheckboxWithTitle("isAttributionEnabled", settings.isAttributionEnabled) {
            settings.isAttributionEnabled = it
        }
        CheckboxWithTitle("isLogoEnabled", settings.isLogoEnabled) {
            settings.isLogoEnabled = it
        }
        CheckboxWithTitle("isCompassEnabled", settings.isCompassEnabled) {
            settings.isCompassEnabled = it
        }
        CheckboxWithTitle("isRotateGesturesEnabled", settings.isRotateGesturesEnabled) {
            settings.isRotateGesturesEnabled = it
        }
        CheckboxWithTitle("isTiltGesturesEnabled", settings.isTiltGesturesEnabled) {
            settings.isTiltGesturesEnabled = it
        }
        CheckboxWithTitle("isZoomGesturesEnabled", settings.isZoomGesturesEnabled) {
            settings.isZoomGesturesEnabled = it
        }
        CheckboxWithTitle("isScrollGesturesEnabled", settings.isScrollGesturesEnabled) {
            settings.isScrollGesturesEnabled = it
        }
    }
}

@Composable
private fun CheckboxWithTitle(name: String, checked: Boolean, onCheckedChanged: (newValue: Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked, onCheckedChange = onCheckedChanged)
        Text(name, Modifier.weight(1f))
    }
}