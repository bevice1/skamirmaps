
package sk.amir.maps.shapes

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import sk.amir.maps.common.LatLng
import sk.amir.maps.common.fromJson
import kotlin.properties.Delegates

internal interface MapPointGeometry : MapGeometry {
    override val type get() = Companion.type
    override fun toJson(): JsonObject {
        return buildJsonObject {
            put("type", type)
            put("coordinates", coordinatesJsonArray)
        }
    }

    val coordinate: LatLng

    companion object
}

internal expect fun MapPointGeometry(
    coordinate: LatLng,
) : MapPointGeometry

internal val MapPointGeometry.Companion.type inline get() = "Point"

private val MapPointGeometry.coordinatesJsonArray get(): JsonArray {
    return buildJsonArray {
        add(coordinate.longitude)
        add(coordinate.latitude)
    }
}

internal fun MapPointGeometry.Companion.fromJson(jsonElement: JsonElement): MapPointGeometry {
    check(jsonElement is JsonObject)
    val type = jsonElement["type"]
    check(type is JsonPrimitive)
    check(type.isString)
    check(type.content == this.type)
    val coordinates = jsonElement["coordinates"]
    check(coordinates is JsonArray)
    check(coordinates.size >= 2)
    return MapPointGeometry(LatLng.fromJson(coordinates))
}