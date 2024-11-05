
package sk.amir.maps.shapes

import sk.amir.maps.common.LatLng

internal actual fun MapPolygonGeometry(
    listOfPolygons: List<List<LatLng>>
): MapPolygonGeometry {
    return MapPolygonGeometryImpl(
        listOfPolygons
    )
}

internal data class MapPolygonGeometryImpl(
    override val listOfPolygons: List<List<LatLng>>
) : MapPolygonGeometry