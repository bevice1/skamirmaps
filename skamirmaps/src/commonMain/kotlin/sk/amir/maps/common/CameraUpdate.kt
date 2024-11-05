
package sk.amir.maps.common

import androidx.compose.runtime.Immutable

// todo keep binary compatibility in mind for jvm + parcelable
@Immutable
internal sealed class CameraUpdate(
    val animated: Boolean
) {
    @Immutable
    class Target(
        val target: LatLng,
        animated: Boolean
    ) : CameraUpdate(animated)

    @Immutable
    class TargetZoom(
        val target: LatLng,
        val zoom: Double,
        animated: Boolean
    ) : CameraUpdate(animated)

    @Immutable
    class BoundsPadding(
        val bounds: LatLngBounds,
        val padding: CameraPadding,
        animated: Boolean
    ) : CameraUpdate(animated)
}
