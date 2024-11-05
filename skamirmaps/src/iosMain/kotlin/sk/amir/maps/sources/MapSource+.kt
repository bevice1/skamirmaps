
package sk.amir.maps.sources

import cocoapods.MapLibre.MLNShapeSource
import cocoapods.MapLibre.MLNSource


internal fun MLNSource.toOther(): MapSource {
    return when (this) {
        is MLNShapeSource -> MapShapeSourceImpl(this)
        else -> TODO()
    }
}

internal fun MapSource.toOther(): MLNSource {
    return when (this) {
        is MapShapeSourceImpl -> nSource
        else -> TODO("$identifier")
    }
}