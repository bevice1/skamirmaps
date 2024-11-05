
package sk.amir.maps

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Density
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import sk.amir.maps.common.CameraPadding
import sk.amir.maps.common.CameraPosition
import sk.amir.maps.common.CameraUpdate
import sk.amir.maps.common.LatLngBounds
import sk.amir.maps.common.LatLng
import kotlin.jvm.JvmOverloads

@Stable
interface MapState {
    val onMapLoadFlow: Flow<Unit>
    val onStyleFinishedLoadingFlow: Flow<Unit>
    val onCameraDidChangeFlow: Flow<CameraPosition>
    val style: MapStyle?
    val commandHandle: MapCommandHandle?
}

internal val MapState.internalStyle inline get() = style as? InternalMapStyle

internal fun MapState(
    scope: CoroutineScope,
    onMapLoad: MapState.() -> Unit,
    onStyleFinishedLoading: MapState.() -> Unit,
    onCameraDidChange: MapState.(CameraPosition) -> Unit,
): MapState {
    fun <T> subscribe1Arg(flow: Flow<T>, callback: (args: T) -> Unit) {
        flow
            .onEach(callback)
            .launchIn(scope)
    }

    fun subscribeNoArg(flow: Flow<*>, callback: () -> Unit) {
        flow
            .onEach { callback() }
            .launchIn(scope)
    }

    return MapStateImpl()
        .apply {
            subscribeNoArg(onMapLoadFlow) { onMapLoad() }
            subscribeNoArg(onStyleFinishedLoadingFlow) { onStyleFinishedLoading() }
            subscribe1Arg(onCameraDidChangeFlow) { onCameraDidChange(it) }
        }
}

@Stable
internal class MapStateImpl : MapState {
    // when loaded, it's loaded
    fun emitOnMapLoad() = _onMapLoadFlow.tryEmit(Unit)
    private val _onMapLoadFlow = MutableSharedFlow<Unit>(replay = 1)
    override val onMapLoadFlow: SharedFlow<Unit> = _onMapLoadFlow.asSharedFlow()

    fun emitOnStyleFinishedLoading() = _onStyleFinishedLoadingFlow.tryEmit(Unit)

    private val _onStyleFinishedLoadingFlow = MutableSharedFlow<Unit>(replay = 1)
    fun clearStyleLoaded() {
        _onStyleFinishedLoadingFlow.resetReplayCache()
        style = null
    }
    override val onStyleFinishedLoadingFlow: SharedFlow<Unit> = _onStyleFinishedLoadingFlow.asSharedFlow()

    fun emitOnCameraDidChange(cameraPosition: CameraPosition) =
        _onCameraDidChangeFlow.tryEmit(cameraPosition)
    private val _onCameraDidChangeFlow = MutableSharedFlow<CameraPosition>(replay = 1)
    override val onCameraDidChangeFlow: Flow<CameraPosition> = _onCameraDidChangeFlow.asSharedFlow()

    override var style: MapStyle? by mutableStateOf(null)

    override var commandHandle: MapCommandHandle? by mutableStateOf(null)
}

@Composable
@JvmOverloads
fun rememberMapState(
    onMapLoad: MapState.() -> Unit = {},
    onStyleFinishedLoading: MapState.() -> Unit = {},
    onCameraDidChange: MapState.(CameraPosition) -> Unit = {},
): MapState {
    val scope = rememberCoroutineScope()
    return remember {
        MapState(
            scope = scope,
            onMapLoad = onMapLoad,
            onStyleFinishedLoading = onStyleFinishedLoading,
            onCameraDidChange = onCameraDidChange
        )
    }
}
