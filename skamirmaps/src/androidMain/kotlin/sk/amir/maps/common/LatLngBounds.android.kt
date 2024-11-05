
package sk.amir.maps.common

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import sk.amir.maps.toOther
import java.io.Serializable

actual fun Collection<LatLng>.toLatLngBounds(): LatLngBounds {
    return org.maplibre.android.geometry.LatLngBounds.fromLatLngs(
        map { it.toOther() }
    ).toOther()
}

actual fun LatLngBounds(
    southWest: LatLng,
    northEast: LatLng
): LatLngBounds {
    return LatLngBoundsImpl(
        southWest = southWest as LatLngImpl,
        northEast = northEast as LatLngImpl
    )
}

@Parcelize
@Immutable
internal data class LatLngBoundsImpl(
    override val southWest: LatLngImpl,
    override val northEast: LatLngImpl,
) : LatLngBounds, Parcelable, Serializable
