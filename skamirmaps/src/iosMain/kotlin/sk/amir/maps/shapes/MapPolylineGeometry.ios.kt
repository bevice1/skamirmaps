
package sk.amir.maps.shapes

import sk.amir.maps.common.LatLng

internal actual fun MapPolylineGeometry(
    coordinates: List<LatLng>
): MapPolylineGeometry {
    return MapPolylineGeometryImpl(coordinates)
}

internal data class MapPolylineGeometryImpl(
    override val coordinates: List<LatLng>
): MapPolylineGeometry
