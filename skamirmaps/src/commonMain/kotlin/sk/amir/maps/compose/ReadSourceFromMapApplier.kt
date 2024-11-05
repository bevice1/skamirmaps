package sk.amir.maps.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import sk.amir.maps.InternalMapStyle
import sk.amir.maps.sources.MapSource

@Composable
internal fun readSourceFromMapApplier(
    style: InternalMapStyle,
    sourceId: String,
    mapApplier: MapApplier
): MapSource? {
    val isSourceAttached by remember(sourceId) {
        mapApplier.isSourceAttached(sourceId)
    }
    // make sure the source exists
    val sourceState = derivedStateOf {
        if (isSourceAttached) {
            style.sourceWithIdentifier(sourceId)
        } else {
            null
        }
    }
    return sourceState.value
}
