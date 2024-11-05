
package sk.amir.maps.layers

import sk.amir.maps.common.Ex

internal interface MapBackgroundStyleLayer : MapStyleLayer {
    var backgroundOpacity: Ex
    var backgroundColor: Ex
    var backgroundPattern: Ex
}

internal expect fun MapBackgroundStyleLayer(
    identifier: String
) : MapBackgroundStyleLayer

internal fun MapBackgroundStyleLayer(
    identifier: String,
    configure: MapBackgroundStyleLayer.() -> Unit
) = MapBackgroundStyleLayer(identifier).apply(configure)