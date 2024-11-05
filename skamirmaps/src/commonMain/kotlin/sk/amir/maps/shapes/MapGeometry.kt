
package sk.amir.maps.shapes

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

internal /* sealed */ interface MapGeometry {
    fun toJson(): JsonObject {
        return when (this) {
            is MapPointGeometry -> (this as MapPointGeometry).toJson()
            is MapPolylineGeometry -> (this as MapPolylineGeometry).toJson()
            is MapPolygonGeometry -> (this as MapPolygonGeometry).toJson()
            else -> TODO(this::class.qualifiedName.toString())
        }
    }

    /**
     * GeoJSON type
     */
    val type: String get() {
        return when (this) {
            is MapPointGeometry -> (this as MapPointGeometry).type
            is MapPolylineGeometry -> (this as MapPolylineGeometry).type
            is MapPolygonGeometry -> (this as MapPolygonGeometry).type
            else -> TODO(this::class.qualifiedName.toString())
        }
    }

    companion object {
        fun fromJson(jsonElement: JsonElement): MapGeometry {
            check(jsonElement is JsonObject)
            val type = jsonElement["type"]
            check(type is JsonPrimitive)
            check(type.isString)
            return when (type.content) {
                MapPolygonGeometry.type -> MapPolygonGeometry.fromJson(jsonElement)
                MapPolylineGeometry.type -> MapPolylineGeometry.fromJson(jsonElement)
                MapPointGeometry.type -> MapPointGeometry.fromJson(jsonElement)
                else -> TODO(this::class.qualifiedName.toString())
            }
        }
    }
}
