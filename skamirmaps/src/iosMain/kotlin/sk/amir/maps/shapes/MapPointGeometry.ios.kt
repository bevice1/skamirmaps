
package sk.amir.maps.shapes

import sk.amir.maps.common.LatLng

internal actual fun MapPointGeometry(
    coordinate: LatLng,
): MapPointGeometry {
    return MapPointGeometryImpl(
        coordinate
    )
}

internal data class MapPointGeometryImpl(
    override val coordinate: LatLng
): MapPointGeometry {
}
