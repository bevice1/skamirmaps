
package sk.amir.maps.shapes

import sk.amir.maps.common.LatLngBounds

internal interface MapFeature {
    companion object
}

internal expect fun MapFeature(
    id: String? = null,
    geometry: MapGeometry? = null,
    latLngBounds: LatLngBounds? = null,
    attributes: Map<String, Any>? = null,
) : MapFeature