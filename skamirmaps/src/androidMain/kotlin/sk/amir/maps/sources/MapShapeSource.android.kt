
package sk.amir.maps.sources

import com.google.gson.JsonObject
import org.maplibre.android.style.sources.GeoJsonOptions
import org.maplibre.android.style.sources.GeoJsonSource
import org.maplibre.geojson.Feature
import org.maplibre.geojson.FeatureCollection
import org.maplibre.geojson.GeoJson
import sk.amir.maps.common.MapShapeSourceOptions
import sk.amir.maps.common.toOther
import sk.amir.maps.shapes.MapFeature
import sk.amir.maps.shapes.MapFeatureImpl
import java.net.URI

internal actual fun MapShapeSource(
    identifier: String,
    feature: MapFeature,
    options: MapShapeSourceOptions
): MapShapeSource {
    return MapShapeSourceImpl(
        GeoJsonSource(
            id = identifier,
            feature = feature.toOther(),
            options = options.toGeoJsonOptions()
        )
    )
}

internal actual fun MapShapeSource(
    identifier: String,
    features: List<MapFeature>,
    options: MapShapeSourceOptions
): MapShapeSource {
    return MapShapeSourceImpl(
        GeoJsonSource(
            id = identifier,
            features = FeatureCollection.fromFeatures(
                features.map { it.toOther() }
            ),
            options = options.toGeoJsonOptions()
        )
    )
}

internal actual fun MapShapeSource(
    identifier: String,
    url: String,
    options: MapShapeSourceOptions
): MapShapeSource {
    return MapShapeSourceImpl(
        GeoJsonSource(
            id = identifier,
            uri = URI.create(url),
            options = options.toGeoJsonOptions()
        )
    )
}

internal fun MapFeature.toOther(): Feature {
    return when (this) {
        is MapFeatureImpl -> this.nFeature
        else -> TODO()
    }
}

internal fun Map<String, Any?>?.toJsonObjectOrEmpty(): JsonObject {
    return this?.toJsonObject() ?: JsonObject()
}

internal fun Map<String, Any?>.toJsonObject(): JsonObject {
    return JsonObject().apply {
        this@toJsonObject.forEach {
            when (it.value) {
                is Number -> addProperty(it.key, it.value as Number)
                is String -> addProperty(it.key, it.value as String)
                is Boolean -> addProperty(it.key, it.value as Boolean)
                is Char -> addProperty(it.key, it.value as Char)
            }
        }
    }
}

private fun MapShapeSourceOptions.toGeoJsonOptions(): GeoJsonOptions {
    return GeoJsonOptions().apply {
        val entries = this@toGeoJsonOptions.entries
        for (entry in entries) {
            if (entry.key == MapShapeSourceOptions.CLUSTER_PROPERTIES) {
                put(
                    entry.key,
                    getClusterProperties()
                        .mapValues {
                            val (a, b) = it.value
                            arrayOf(
                                a.toOther().toArray(),
                                b.toOther().toArray()
                            )
                        }
                )
            } else {
                put(entry.key, entry.value)
            }
        }
    }
}

internal class MapShapeSourceImpl(
    val nSource: GeoJsonSource
) : MapShapeSource {
    override val identifier: String
        get() = nSource.id

    override fun setUrl(url: String) {
        nSource.setUri(url)
    }

    override fun setFeature(feature: MapFeature) {
        nSource.setGeoJson(feature = feature.toOther())
    }

    override fun setFeatureCollection(featureCollection: List<MapFeature>) {
        nSource.setGeoJson(featureCollection = FeatureCollection.fromFeatures(featureCollection.map { it.toOther() }))
    }
}
