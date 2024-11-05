
package sk.amir.maps.shapes

import org.maplibre.geojson.Polygon
import sk.amir.maps.common.LatLng

internal actual fun MapPolygonGeometry(
    listOfPolygons: List<List<LatLng>>,
): MapPolygonGeometry {
    return MapPolygonGeometryImpl(
        Polygon.fromLngLats(
            listOfPolygons
                .map { it.map { it.toPoint() } }
        )
    )
}

private data class MapPolygonGeometryImpl(
    override val nShape: Polygon
) : MapGeometryWithShape<Polygon>, MapPolygonGeometry {
    override val listOfPolygons: List<List<LatLng>>
        get() = nShape.coordinates()
            .map { it.map { it.toLatLng() } }
}
