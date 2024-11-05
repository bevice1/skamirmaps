
package sk.amir.maps

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Density
import sk.amir.maps.common.CameraPadding
import sk.amir.maps.common.CameraUpdate
import sk.amir.maps.common.LatLng
import sk.amir.maps.common.LatLngBounds

interface MapCommandHandle {
    // pixels
    fun projectLatLngToVisibleArea(latLng: LatLng, density: Density): Offset
    // pixels
    fun projectOffsetToLatLng(offset: Offset, density: Density): LatLng
    fun getVisibleArea(): LatLngBounds
}

internal interface InternalMapCommandHandle {
    // when this function finishes it is not guaranteed that the style is loaded
    fun loadStyleUri(styleUri: String)
    fun updateCamera(cameraUpdate: CameraUpdate)
}

internal inline val MapCommandHandle.asInternal
    get() = this as InternalMapCommandHandle

// helpers
fun MapCommandHandle.updateCamera(target: LatLng, animated: Boolean) =
    asInternal.updateCamera(CameraUpdate.Target(target, animated))

fun MapCommandHandle.updateCamera(target: LatLng, zoom: Double, animated: Boolean) =
    asInternal.updateCamera(CameraUpdate.TargetZoom(target, zoom, animated))

fun MapCommandHandle.updateCamera(bounds: LatLngBounds, padding: CameraPadding, animated: Boolean) =
    asInternal.updateCamera(CameraUpdate.BoundsPadding(bounds, padding, animated))
