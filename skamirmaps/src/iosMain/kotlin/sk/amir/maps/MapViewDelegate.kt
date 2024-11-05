
package sk.amir.maps

import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.dp
import cocoapods.MapLibre.MLNCameraChangeReason
import cocoapods.MapLibre.MLNLocationManagerProtocol
import cocoapods.MapLibre.MLNMapView
import cocoapods.MapLibre.MLNMapViewDelegateProtocol
import cocoapods.MapLibre.MLNStyle
import kotlinx.cinterop.ObjCSignatureOverride
import kotlinx.cinterop.useContents
import platform.CoreLocation.CLAuthorizationStatus
import platform.Foundation.NSError
import platform.UIKit.UIImage
import platform.darwin.NSObject
import sk.amir.maps.common.CameraPadding
import sk.amir.maps.common.CameraPosition
import sk.amir.maps.common.toLatLng

internal class MapViewDelegate(
    val mapView: MLNMapView,
    val mapState: MapStateImpl,
    val locationAuthorizationStatus: MutableState<CLAuthorizationStatus>
) : NSObject(), MLNMapViewDelegateProtocol {
    private var suppressedCameraDidChange: Boolean = true
    // otherwise we would get the first default location
    fun enableCameraDidChange() {
        if (suppressedCameraDidChange) {
            suppressedCameraDidChange = false
            mapState.emitOnCameraDidChange(mapView.getCameraPosition())
        }
    }

    override fun mapView(
        mapView: MLNMapView,
        didChangeLocationManagerAuthorization: MLNLocationManagerProtocol
    ) {
        locationAuthorizationStatus.value = didChangeLocationManagerAuthorization.authorizationStatus
    }

    override fun mapViewDidBecomeIdle(mapView: MLNMapView) {
//        println("idle")
    }

    @ObjCSignatureOverride
    override fun mapView(mapView: MLNMapView, regionDidChangeAnimated: Boolean) {
//        println("did change normal")
    }

    @ObjCSignatureOverride
    override fun mapView(mapView: MLNMapView, regionWillChangeAnimated: Boolean) {
//        println("will change normal")
    }

    @ObjCSignatureOverride
    override fun mapView(mapView: MLNMapView, didChangeUserTrackingMode: MLNCameraChangeReason, animated: Boolean) {
//        println("will change")
    }

    @ObjCSignatureOverride
    override fun mapView(mapView: MLNMapView, regionWillChangeWithReason: MLNCameraChangeReason, animated: Boolean) {
//        println("will change")
    }
    //
    override fun mapViewRegionIsChanging(mapView: MLNMapView) {
//        println("is changing")
    }

    @ObjCSignatureOverride
    override fun mapView(mapView: MLNMapView, regionDidChangeWithReason: MLNCameraChangeReason, animated: Boolean) {
//        println("did change")
        if (!suppressedCameraDidChange) {
            val position = mapView.getCameraPosition()
            mapState.emitOnCameraDidChange(position)
        } else {
//            println("suppressed")
        }
    }

    override fun mapViewDidFailLoadingMap(mapView: MLNMapView, withError: NSError) {
//        println("failed loading map: ${withError.description}")
    }

    override fun mapViewDidFinishLoadingMap(mapView: MLNMapView) {
//        mapState.onEvent(mapState, Event.DidLoad)
//        println("mapViewDidFinishLoadingMap")
    }

    @ObjCSignatureOverride
    override fun mapView(mapView: MLNMapView, didFailToLoadImage: String): UIImage? {
        if (didFailToLoadImage.isNotBlank()) {
            println("image is missing: $didFailToLoadImage")
        }
        return null
    }

    @ObjCSignatureOverride
    override fun mapView(mapView: MLNMapView, shouldRemoveStyleImage: String): Boolean {
        return true
    }

    override fun mapView(mapView: MLNMapView, didFinishLoadingStyle: MLNStyle) {
        (mapState as MapStateImpl).style = MapStyleImpl(didFinishLoadingStyle)
//        println("emitOnStyleFinishedLoading() ${didFinishLoadingStyle.name}")
        mapState.emitOnStyleFinishedLoading()
    }
}

private fun MLNMapView.getCameraPosition(): CameraPosition {
    return CameraPosition(
        target = centerCoordinate.toLatLng(),
        zoom = zoomLevel,
        // is this the same?
        // from iOS maplibre docs:
        // Heading measured in degrees clockwise from true north.
        bearing = camera.heading,
        padding = cameraEdgeInsets.useContents {
            CameraPadding(
                top = top.dp,
                bottom = bottom.dp,
                // TODO Right to left layouts
                start = left.dp,
                end = right.dp
            )
        },
        // is this the same?
        // From iOS maplibre docs:
        // Pitch toward the horizon measured in degrees, with 0 degrees resulting in a two-dimensional map.
        tilt = camera.pitch,
    )
}
