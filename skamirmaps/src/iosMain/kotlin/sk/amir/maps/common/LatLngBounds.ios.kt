
package sk.amir.maps.common

import cocoapods.MapLibre.MLNPolylineFeature
import kotlinx.cinterop.useContents
import sk.amir.maps.shapes.setCoordinates
import sk.amir.maps.toOther

actual fun Collection<LatLng>.toLatLngBounds(): LatLngBounds {
    return MLNPolylineFeature().apply {
        setCoordinates(this@toLatLngBounds.toTypedArray())
    }.overlayBounds.useContents { toOther() }

}

actual fun LatLngBounds(
    southWest: LatLng,
    northEast: LatLng
): LatLngBounds = LatLngBoundsImpl(
    southWest = southWest,
    northEast = northEast
)

data class LatLngBoundsImpl(
    override val southWest: LatLng,
    override val northEast: LatLng
) : LatLngBounds
