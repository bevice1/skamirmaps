
package sk.amir.maps.common

import androidx.compose.runtime.Immutable
import kotlin.math.sign

@Immutable
interface LatLngBounds {
    val southWest: LatLng
    val northEast: LatLng
}

expect fun LatLngBounds(
    southWest: LatLng,
    northEast: LatLng
) : LatLngBounds

internal fun LatLngBounds.contains(latLng: LatLng): Boolean {
    val (lat, lng) = latLng.normalized()
    val (south, west) = southWest.normalized()
    val (north, east) = northEast.normalized()
    if (lat !in south..north) {
        return false
    }
    if (west < east) {
        if (lng !in west..east) {
            return false
        }
    } else {
        val lngCorrected = if (lng > 0) {
            lng
        } else {
            (lng + 360) % 360
        }
        val eastCorrected = (east + 360) % 360
        val liesWithin = lngCorrected in west..eastCorrected
        if (!liesWithin) {
            return false
        }
    }
    return true
}

internal fun LatLng.normalized(): LatLng {
    val normalisedLatitude = latitude.let {
        var new = it
        while (new !in -90.0..90.0) {
            new -= 180 * it.sign
        }
        new
    }

    val normalisedLongitude = longitude.let {
        var new = it
        while (new !in -180.0..180.0) {
            new -= 360 * it.sign
        }
        new
    }

    return LatLng(
        normalisedLatitude,
        normalisedLongitude
    )
}

expect fun Collection<LatLng>.toLatLngBounds(): LatLngBounds
