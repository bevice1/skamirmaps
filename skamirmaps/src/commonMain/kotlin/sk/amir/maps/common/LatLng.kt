
package sk.amir.maps.common

import androidx.annotation.FloatRange
import androidx.compose.runtime.Immutable

@Immutable
interface LatLng {
    @get:FloatRange(-90.0, 90.0)
    val latitude: Double
    @get:FloatRange(-180.0, 180.0)
    val longitude: Double

    operator fun component1(): Double
    operator fun component2(): Double

    companion object
}

expect fun LatLng(
    @FloatRange(-90.0, 90.0)
    latitude: Double,
    @FloatRange(-180.0, 180.0)
    longitude: Double
): LatLng
