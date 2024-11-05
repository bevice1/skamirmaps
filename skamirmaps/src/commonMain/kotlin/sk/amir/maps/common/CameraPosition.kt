
package sk.amir.maps.common

import androidx.compose.runtime.Immutable

@Immutable
class CameraPosition(
    val target: LatLng,
    val zoom: Double,

    /**
     * Direction that the camera is pointing in, in degrees clockwise from north.
     */
    val bearing: Double,

    /**
     * in Dp
     */
    val padding: CameraPadding,

    /**
     * The angle, in degrees, of the camera angle from the nadir (directly facing the Earth)
     */
    val tilt: Double,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CameraPosition

        if (target != other.target) return false
        if (zoom != other.zoom) return false
        if (bearing != other.bearing) return false
        if (padding != other.padding) return false
        if (tilt != other.tilt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = target.hashCode()
        result = 31 * result + zoom.hashCode()
        result = 31 * result + bearing.hashCode()
        result = 31 * result + padding.hashCode()
        result = 31 * result + tilt.hashCode()
        return result
    }
}
