
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

internal interface MapPolygonGeometry : MapGeometry {
    override val type get() = Companion.type

    override fun toJson(): JsonObject {
        return buildJsonObject {
            put("type", type)
            put("coordinates", coordinatesJsonArray)
        }
    }
    val listOfPolygons: List<List<LatLng>>
    companion object
}

internal expect fun MapPolygonGeometry(
    listOfPolygons: List<List<LatLng>>,
) : MapPolygonGeometry

internal fun MapPolygonGeometry.Companion.fromJson(jsonElement: JsonElement): MapPolygonGeometry {
    check(jsonElement is JsonObject)
    val type = jsonElement["type"]
    check(type is JsonPrimitive)
    check(type.isString)
    check(type.content == this.type)
    val listOfPolygons = jsonElement["coordinates"]
    check(listOfPolygons is JsonArray)
    return MapPolygonGeometry(
        listOfPolygons = listOfPolygons
            .map { polygon ->
                check(polygon is JsonArray)
                polygon
                    .map { LatLng.fromJson(it) }
            }
    )
}

internal val MapPolygonGeometry.Companion.type get() = "Polygon"

private val MapPolygonGeometry.coordinatesJsonArray get(): JsonArray {
    return buildJsonArray {
        for (polygon in listOfPolygons) {
            buildJsonArray {
                for (coord in polygon) {
                    buildJsonArray {
                        add(coord.longitude)
                        add(coord.latitude)
                    }.also { add(it)}
                }
            }.also { add(it) }
        }
    }
}