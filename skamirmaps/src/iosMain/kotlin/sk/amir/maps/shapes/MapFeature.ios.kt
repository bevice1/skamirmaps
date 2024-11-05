
package sk.amir.maps.shapes

import cocoapods.MapLibre.MLNPointFeature
import cocoapods.MapLibre.MLNPolygonFeature
import cocoapods.MapLibre.MLNPolylineFeature
import cocoapods.MapLibre.MLNShape
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import platform.CoreLocation.CLLocationCoordinate2D
import sk.amir.maps.common.LatLngBounds
import sk.amir.maps.common.LatLng
import sk.amir.maps.common.toOther

internal actual fun MapFeature(
    id: String?,
    geometry: MapGeometry?,
    latLngBounds: LatLngBounds?,
    attributes: Map<String, Any>?
): MapFeature {
    return when (geometry) {
        is MapPointGeometry -> {
            MapFeatureImpl(
                MLNPointFeature().apply {
                    identifier = id
                    if (attributes != null) {
                        setAttributes(attributes.mapKeys { it.key })
                    }
                    setCoordinate(geometry.coordinate.toOther())
                }
            )
        }

        is MapPolylineGeometry -> {
            MapFeatureImpl(
                MLNPolylineFeature().apply {
                    identifier = id
                    if (attributes != null) {
                        setAttributes(attributes.mapKeys { it.key })
                    }
                    setCoordinates(geometry.coordinates.toTypedArray())
                }
            )
        }

        is MapPolygonGeometryImpl -> {
            MapFeatureImpl(
                MLNPolygonFeature().apply {
                    identifier = id
                    if (attributes != null) {
                        setAttributes(attributes.mapKeys { it.key })
                    }
                    check(geometry.listOfPolygons.size == 1) {
                        "Unsupported number of polygons: ${geometry.listOfPolygons.size}"
                    }
                    setCoordinates(geometry.listOfPolygons.first().toTypedArray())
                }
            )
        }
        else -> TODO("Unsupported Geometry $geometry")
    }
}

internal class MapFeatureImpl<T: MLNShape>(
    val nFeature: T
) : MapFeature

internal fun MLNPolylineFeature.setCoordinates(coordinates: Array<LatLng>) {
    setCoordinatesGeneric(coordinates, ::setCoordinates)
}

internal fun MLNPolygonFeature.setCoordinates(coordinates: Array<LatLng>) {
    setCoordinatesGeneric(coordinates, ::setCoordinates)
}

internal fun setCoordinatesGeneric(
    coordinates: Array<LatLng>,
    callback: (pointer: CPointer<CLLocationCoordinate2D>, size: ULong) -> Unit
) {
    memScoped {
        val res = allocArray<CLLocationCoordinate2D>(coordinates.size) {
            latitude = coordinates[it].latitude
            longitude = coordinates[it].longitude
        }
        callback(res, coordinates.size.toULong())
    }
}