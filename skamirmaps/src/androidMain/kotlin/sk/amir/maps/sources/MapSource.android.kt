
package sk.amir.maps.sources

import org.maplibre.android.style.sources.CustomGeometrySource
import org.maplibre.android.style.sources.GeoJsonSource
import org.maplibre.android.style.sources.ImageSource
import org.maplibre.android.style.sources.RasterDemSource
import org.maplibre.android.style.sources.RasterSource
import org.maplibre.android.style.sources.Source
import org.maplibre.android.style.sources.UnknownSource
import org.maplibre.android.style.sources.VectorSource


internal class MapSourceImpl(
    val nSource: Source
) : MapSource {
    override val identifier: String
        get() = nSource.id
}

internal fun Source.toOther(): MapSource {
    return when (this) {
        is GeoJsonSource -> MapShapeSourceImpl(this)

        is VectorSource -> MapSourceImpl(this)
        is CustomGeometrySource -> MapSourceImpl(this)
        is ImageSource -> MapSourceImpl(this)
        is RasterSource -> MapSourceImpl(this)
        is RasterDemSource -> MapSourceImpl(this)
        is UnknownSource -> MapSourceImpl(this)
        else -> {
            println("unknown source $id $javaClass")
            MapSourceImpl(this)
        }
    }
}

internal fun MapSource.toOther(): Source {
    return when (this) {
        is MapSourceImpl -> nSource
        is MapShapeSourceImpl -> nSource
        else -> TODO("$identifier $javaClass")
    }
}
