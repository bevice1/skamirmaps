
package sk.amir.maps.common

import org.maplibre.android.style.layers.TransitionOptions

internal fun TransitionOptions.toOther(): MapLayerTransition {
    return MapLayerTransitionImpl(
        delay = delay.toDouble() * 1_000,
        duration = duration.toDouble() * 1_000
    )
}

internal fun MapLayerTransition.toOther(): TransitionOptions {
    return TransitionOptions(
        (delay / 1_000L).toLong(),
        (duration / 1_000L).toLong()
    )
}