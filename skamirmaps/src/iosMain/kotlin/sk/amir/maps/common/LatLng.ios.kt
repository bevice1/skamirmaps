
package sk.amir.maps.common

import kotlinx.cinterop.CValue
import kotlinx.cinterop.useContents
import platform.CoreLocation.CLLocationCoordinate2D
import platform.CoreLocation.CLLocationCoordinate2DMake

internal fun LatLng.toOther(): CValue<CLLocationCoordinate2D> {
    return CLLocationCoordinate2DMake(
        latitude = latitude,
        longitude = longitude

    )
}

internal fun CValue<CLLocationCoordinate2D>.toLatLng() = useContents {
    LatLng(
        latitude = latitude,
        longitude = longitude
    )
}