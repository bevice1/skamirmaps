
package sk.amir.maps

import cocoapods.MapLibre.MLNSource
import sk.amir.maps.sources.MapSource

internal open class MapSourceImpl<Source : MLNSource>(
    val nSource: Source
) : MapSource {
    override val identifier: String
        get() = nSource.identifier
}