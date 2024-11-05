
package sk.amir.maps

import sk.amir.maps.common.LatLng

internal fun LatLng.toOther(): org.maplibre.android.geometry.LatLng {
    return org.maplibre.android.geometry.LatLng(
        latitude = latitude,
        longitude = longitude
    )
}
