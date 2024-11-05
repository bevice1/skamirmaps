
package sk.amir.maps.sources

import cocoapods.MapLibre.MLNPointFeature
import cocoapods.MapLibre.MLNShape
import cocoapods.MapLibre.MLNShapeCollectionFeature
import cocoapods.MapLibre.MLNShapeSource
import cocoapods.MapLibre.MLNShapeSourceOptionBuffer
import cocoapods.MapLibre.MLNShapeSourceOptionClusterProperties
import cocoapods.MapLibre.MLNShapeSourceOptionClusterRadius
import cocoapods.MapLibre.MLNShapeSourceOptionClustered
import cocoapods.MapLibre.MLNShapeSourceOptionLineDistanceMetrics
import cocoapods.MapLibre.MLNShapeSourceOptionMaximumZoomLevel
import cocoapods.MapLibre.MLNShapeSourceOptionMaximumZoomLevelForClustering
import cocoapods.MapLibre.MLNShapeSourceOptionMinimumZoomLevel
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import platform.Foundation.NSArray
import platform.Foundation.NSConstantValueExpressionType
import platform.Foundation.NSDictionary
import platform.Foundation.NSNumber
import platform.Foundation.NSURL
import platform.Foundation.arrayWithArray
import sk.amir.maps.MapSourceImpl
import sk.amir.maps.common.MapShapeSourceOptions
import sk.amir.maps.common.toOther
import sk.amir.maps.shapes.MapFeature
import sk.amir.maps.shapes.MapFeatureImpl
import sk.amir.maps.shapes.MapGeometry
import sk.amir.maps.shapes.MapPointGeometry

internal actual fun MapShapeSource(
    identifier: String,
    features: List<MapFeature>,
    options: MapShapeSourceOptions
): MapShapeSource {
    return MapShapeSourceImpl(
        MLNShapeSource(
            identifier = identifier,
            features = features
                .map { it.toNativeShape() },
            options = options.toOther()
        )
    )
}

internal fun MapGeometry.toShape(): MLNShape {
    return when (this) {
        is MapPointGeometry -> MLNPointFeature()
            .also {
                // attributes, identifier, title, subtitle are not populated for now
                it.setCoordinate(coordinate.toOther())
            }
        else -> TODO("Unsupported Geometry: ${this::class}")
    }
}

internal actual fun MapShapeSource(
    identifier: String,
    url: String,
    options: MapShapeSourceOptions
): MapShapeSource {
    return MapShapeSourceImpl(
        MLNShapeSource(
            identifier = identifier,
            URL = NSURL.URLWithString(url)!!,
            options = options.toOther()
        )
    )
}

internal actual fun MapShapeSource(
    identifier: String,
    feature: MapFeature,
    options: MapShapeSourceOptions
): MapShapeSource {
    return MapShapeSourceImpl(
        MLNShapeSource(
            identifier = identifier,
            features = listOf(feature.toNativeShape()),
            options = options.toOther()
        )
    )
}

internal fun MapFeature.toNativeShape(): MLNShape {
    return when (this) {
        is MapFeatureImpl<*> -> nFeature
        else -> TODO()
    }
}

private fun MapShapeSourceOptions.toOther(): Map<Any?, *> {
    return mapNotNull { entry ->
        when (entry.key) {
            MapShapeSourceOptions.MAX_ZOOM -> MLNShapeSourceOptionMaximumZoomLevel!! to NSNumber(entry.value as Int)
            MapShapeSourceOptions.MIN_ZOOM -> MLNShapeSourceOptionMinimumZoomLevel!! to NSNumber(entry.value as Int)
            MapShapeSourceOptions.LINE_METRICS -> MLNShapeSourceOptionLineDistanceMetrics!! to NSNumber(entry.value as Boolean)
            MapShapeSourceOptions.BUFFER -> MLNShapeSourceOptionBuffer!! to NSNumber(entry.value as Int)
            MapShapeSourceOptions.TOLERANCE -> MLNShapeSourceOptionBuffer!! to NSNumber(entry.value as Float)
            MapShapeSourceOptions.CLUSTER -> MLNShapeSourceOptionClustered!! to NSNumber(entry.value as Boolean)
            MapShapeSourceOptions.CLUSTER_MAX_ZOOM -> MLNShapeSourceOptionMaximumZoomLevelForClustering!! to NSNumber(entry.value as Int)
            MapShapeSourceOptions.CLUSTER_RADIUS -> MLNShapeSourceOptionClusterRadius!! to NSNumber(entry.value as Int)
            MapShapeSourceOptions.CLUSTER_PROPERTIES -> {
                val dictionary = getClusterProperties()
                    .mapValues { (key, value) ->
                        val (operatorExpr, mapExpr) = value

                        NSArray.arrayWithArray(listOf(operatorExpr.toOther(), mapExpr.toOther()))
                    } as NSDictionary
                MLNShapeSourceOptionClusterProperties!! to dictionary
            }
            else -> null
        }
    }
        .toMap()
}

internal class MapShapeSourceImpl(
    nSource: MLNShapeSource
) : MapSourceImpl<MLNShapeSource>(nSource), MapShapeSource {
    override fun setUrl(url: String) {
        nSource.URL = NSURL.URLWithString(url)
    }

    override fun setFeature(feature: MapFeature) {
        nSource.shape = feature.toNativeShape()
    }

    override fun setFeatureCollection(featureCollection: List<MapFeature>) {
        nSource.shape = MLNShapeCollectionFeature.shapeCollectionWithShapes(
            featureCollection.map { it.toNativeShape() }
        )
    }
}
