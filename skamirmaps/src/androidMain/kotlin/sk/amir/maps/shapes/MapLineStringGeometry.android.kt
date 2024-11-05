
package sk.amir.maps.shapes

import org.maplibre.geojson.LineString
import sk.amir.maps.common.LatLng

internal actual fun MapPolylineGeometry(
    coordinates: List<LatLng>
): MapPolylineGeometry {
    return MapPolylineGeometryImpl(
        LineString.fromLngLats(
            coordinates
                .map { it.toPoint() }
        )
    )
}

private data class MapPolylineGeometryImpl(
    override val nShape: LineString
) : MapGeometryWithShape<LineString>, MapPolylineGeometry {
    override val coordinates: List<LatLng>
        get() = nShape.coordinates()
            .map { it.toLatLng() }
}
