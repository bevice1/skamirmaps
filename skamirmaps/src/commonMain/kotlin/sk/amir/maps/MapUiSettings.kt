
package sk.amir.maps

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.jvm.JvmOverloads

@Stable
class MapUiSettings @JvmOverloads constructor(applier: MapUiSettings.() -> Unit = {}) {
    var isAttributionEnabled: Boolean by mutableStateOf(false)
    var isLogoEnabled: Boolean by mutableStateOf(false)
    var isCompassEnabled: Boolean by mutableStateOf(false)
    var isRotateGesturesEnabled: Boolean by mutableStateOf(true)
    var isTiltGesturesEnabled: Boolean by mutableStateOf(true)
    var isZoomGesturesEnabled: Boolean by mutableStateOf(true)
    var isScrollGesturesEnabled: Boolean by mutableStateOf(true)

    init {
        applier()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MapUiSettings

        if (isAttributionEnabled != other.isAttributionEnabled) return false
        if (isLogoEnabled != other.isLogoEnabled) return false
        if (isCompassEnabled != other.isCompassEnabled) return false
        if (isRotateGesturesEnabled != other.isRotateGesturesEnabled) return false
        if (isTiltGesturesEnabled != other.isTiltGesturesEnabled) return false
        if (isZoomGesturesEnabled != other.isZoomGesturesEnabled) return false
        if (isScrollGesturesEnabled != other.isScrollGesturesEnabled) return false

        return true
    }

    override fun hashCode(): Int {
        var result = isAttributionEnabled.hashCode()
        result = 31 * result + isLogoEnabled.hashCode()
        result = 31 * result + isCompassEnabled.hashCode()
        result = 31 * result + isRotateGesturesEnabled.hashCode()
        result = 31 * result + isTiltGesturesEnabled.hashCode()
        result = 31 * result + isZoomGesturesEnabled.hashCode()
        result = 31 * result + isScrollGesturesEnabled.hashCode()
        return result
    }
}
