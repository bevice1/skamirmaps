
package sk.amir.maps.shapes

import org.maplibre.geojson.Point
import sk.amir.maps.common.LatLng

internal actual fun MapPointGeometry(
    coordinate: LatLng,
): MapPointGeometry {
    return MapPointGeometryImpl(
        nShape = Point.fromLngLat(coordinate.longitude, coordinate.latitude)!!
    )
}

internal fun Point.toLatLng(): LatLng {
    return LatLng(
        latitude = latitude(),
        longitude = longitude()
    )
}

internal fun LatLng.toPoint(): Point {
    return Point.fromLngLat(
        longitude, latitude
    )
}

private data class MapPointGeometryImpl(
    override val nShape: Point
) : MapGeometryWithShape<Point>, MapPointGeometry {
    override val coordinate: LatLng
        get() = nShape.toLatLng()
}
