
package sk.amir.maps.shapes

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import sk.amir.maps.common.LatLng
import sk.amir.maps.common.fromJson
import sk.amir.maps.common.toJson

internal interface MapPolylineGeometry : MapGeometry {
    override val type get() = Companion.type

    override fun toJson(): JsonObject {
        return buildJsonObject {
            put("type", type)
            val c = buildJsonArray {
                for (coord in coordinates) {
                    add(coord.toJson())
                }
            }
            put("coordinates", c)
        }
    }
    val coordinates: List<LatLng>
    companion object
}

internal val MapPolylineGeometry.Companion.type get() = "LineString"

internal expect fun MapPolylineGeometry(
    coordinates: List<LatLng>
) : MapPolylineGeometry


internal fun MapPolylineGeometry.Companion.fromJson(jsonElement: JsonElement): MapPolylineGeometry {
    check(jsonElement is JsonObject)
    val type = jsonElement["type"]
    check(type is JsonPrimitive)
    check(type.isString)
    check(type.content == this.type)
    val coordinates = jsonElement["coordinates"]
    check(coordinates is JsonArray)
    return MapPolylineGeometry(
        coordinates
            .map { LatLng.fromJson(it) }
    )
}