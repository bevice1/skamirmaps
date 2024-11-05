
package sk.amir.maps.common

import androidx.annotation.FloatRange
import androidx.compose.runtime.Immutable

actual fun LatLng(
    @FloatRange(-90.0, 90.0) latitude: Double,
    @FloatRange(-180.0, 180.0) longitude: Double
): LatLng {
    return LatLngImpl(
        latitude,
        longitude,
    )
}

@Immutable
private data class LatLngImpl(
    override val latitude: Double,
    override val longitude: Double
) : LatLng
