
package sk.amir.maps.common

import android.os.Parcelable
import androidx.annotation.FloatRange
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

actual fun LatLng(
    @FloatRange(-90.0, 90.0) latitude: Double,
    @FloatRange(-180.0, 180.0) longitude: Double
): LatLng {
    return LatLngImpl(
        latitude = latitude,
        longitude = longitude
    )
}

@Immutable
@Parcelize
internal data class LatLngImpl(
    override val latitude: Double,
    override val longitude: Double
) : LatLng, Parcelable, Serializable
