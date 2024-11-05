
package sk.amir.maps

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Density
import cocoapods.MapLibre.MLNCoordinateBoundsMake
import cocoapods.MapLibre.MLNMapView
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGPointMake
import platform.Foundation.NSURL
import platform.UIKit.UIEdgeInsetsMake
import sk.amir.maps.common.CameraUpdate
import sk.amir.maps.common.LatLng
import sk.amir.maps.common.LatLngBounds
import sk.amir.maps.common.toOther

internal class MapCommandHandleImpl(
    val mapState: MapState,
    val mapView: MLNMapView,
    val delegate: MapViewDelegate,
) : MapCommandHandle, InternalMapCommandHandle {
    override fun projectLatLngToVisibleArea(latLng: LatLng, density: Density): Offset {
        return mapView.mapProjection().convertCoordinate(latLng.toOther())
            .useContents { toOther(density) }
    }

    override fun projectOffsetToLatLng(offset: Offset, density: Density): LatLng {
        // offset in points
        val offsetInPoints = with(density) { Offset(offset.x.toDp().value, offset.y.toDp().value) }
        return mapView.mapProjection()
            .convertPoint(CGPointMake(offsetInPoints.x.toDouble(), offsetInPoints.y.toDouble()))
            .useContents { toOther() }
    }

    override fun getVisibleArea(): LatLngBounds {
        return mapView.visibleCoordinateBounds
            .useContents { toOther() }
    }

    override fun loadStyleUri(styleUri: String) {
        (mapState as MapStateImpl).clearStyleLoaded()
        mapView.setStyleURL(NSURL.URLWithString(styleUri))
    }

    override fun updateCamera(
        cameraUpdate: CameraUpdate
    ) = cameraUpdate.run {
        when (this) {
            is CameraUpdate.TargetZoom -> {
                mapView.setCenterCoordinate(
                    centerCoordinate = target.toOther(),
                    zoomLevel = zoom,
                    animated = animated
                )
            }

            is CameraUpdate.Target -> {
                mapView.setCenterCoordinate(coordinate = target.toOther(), animated = animated)
            }

            is CameraUpdate.BoundsPadding -> {
                val camera = mapView.cameraThatFitsCoordinateBounds(
                    bounds = MLNCoordinateBoundsMake(
                        bounds.northEast.toOther(),
                        bounds.southWest.toOther()
                    ),
                    edgePadding = UIEdgeInsetsMake(
                        top = padding.top.value.toDouble(),
                        left = padding.start.value.toDouble(), // bug rtl
                        bottom = padding.bottom.value.toDouble(),
                        right = padding.end.value.toDouble() // bug rtl
                    )
                )
                mapView.setCamera(camera, animated = animated)
            }
        }
        delegate.enableCameraDidChange()
    }
}
