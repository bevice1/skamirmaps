
package sk.amir.maps

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import org.maplibre.android.maps.MapLibreMap

@SuppressLint("MissingPermission")
@Composable
internal fun LoadUiSettings(
    uiSettings: MapUiSettings,
    currentMap: MapLibreMap?,
) {
    LaunchedEffect(uiSettings.isCompassEnabled, currentMap) {
        currentMap?.uiSettings?.isCompassEnabled = uiSettings.isCompassEnabled
    }
    LaunchedEffect(uiSettings.isRotateGesturesEnabled, currentMap) {
        currentMap?.uiSettings?.isRotateGesturesEnabled = uiSettings.isRotateGesturesEnabled
    }
    LaunchedEffect(uiSettings.isTiltGesturesEnabled, currentMap) {
        currentMap?.uiSettings?.isTiltGesturesEnabled = uiSettings.isTiltGesturesEnabled
    }
    LaunchedEffect(uiSettings.isZoomGesturesEnabled, currentMap) {
        currentMap?.uiSettings?.isZoomGesturesEnabled = uiSettings.isZoomGesturesEnabled
    }
    LaunchedEffect(uiSettings.isScrollGesturesEnabled, currentMap) {
        currentMap?.uiSettings?.isScrollGesturesEnabled = uiSettings.isScrollGesturesEnabled
    }
    LaunchedEffect(uiSettings.isLogoEnabled, currentMap) {
        currentMap?.uiSettings?.isLogoEnabled = uiSettings.isLogoEnabled
    }
    LaunchedEffect(uiSettings.isAttributionEnabled, currentMap) {
        currentMap?.uiSettings?.isAttributionEnabled = uiSettings.isAttributionEnabled
    }
}
