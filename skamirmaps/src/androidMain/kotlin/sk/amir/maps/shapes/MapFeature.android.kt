
package sk.amir.maps.shapes

import org.maplibre.geojson.Feature
import sk.amir.maps.common.LatLngBounds
import sk.amir.maps.sources.toJsonObjectOrEmpty

internal actual fun MapFeature(
    id: String?,
    geometry: MapGeometry?,
    latLngBounds: LatLngBounds?,
    attributes: Map<String, Any>?
) : MapFeature {
    return MapFeatureImpl(
        Feature.fromGeometry(
            (geometry as MapGeometryWithShape<*>).nShape,
            attributes.toJsonObjectOrEmpty()
        )
    )
}

internal data class MapFeatureImpl(
    val nFeature: Feature
) : MapFeature

internal fun org.maplibre.geojson.BoundingBox.toOther(): LatLngBounds {
    return LatLngBounds(
        southWest = southwest().toLatLng(),
        northEast = northeast().toLatLng()
    )
}

internal fun LatLngBounds.toOther(): org.maplibre.geojson.BoundingBox {
    return org.maplibre.geojson.BoundingBox.fromPoints(
        southWest.toPoint(),
        northEast.toPoint()
    )
}