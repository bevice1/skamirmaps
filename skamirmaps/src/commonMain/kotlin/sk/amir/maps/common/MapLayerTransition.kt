
package sk.amir.maps.common

internal interface MapLayerTransition {
    // seconds
    val delay: Double
    // seconds
    val duration: Double
}

internal class MapLayerTransitionImpl(
    override val delay: Double,
    override val duration: Double
) : MapLayerTransition

internal fun MapLayerTransition(
    delay: Double,
    duration: Double
): MapLayerTransition {
    return MapLayerTransitionImpl(
        delay = delay,
        duration = duration
    )
}