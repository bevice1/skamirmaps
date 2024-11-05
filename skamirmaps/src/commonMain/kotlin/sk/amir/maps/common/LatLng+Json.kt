
package sk.amir.maps.common

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.double
import kotlinx.serialization.json.doubleOrNull

internal fun LatLng.toJson(): JsonArray {
    return buildJsonArray {
        add(longitude)
        add(latitude)
    }
}

internal fun LatLng.Companion.fromJson(jsonElement: JsonElement): LatLng {
    check(jsonElement is JsonArray)
    check(jsonElement.size >= 2)
    val lat = jsonElement[1]
    val lng = jsonElement[0]
    check(lat is JsonPrimitive)
    checkNotNull(lat.doubleOrNull)
    check(lng is JsonPrimitive)
    checkNotNull(lng.doubleOrNull)
    return LatLng(lat.double, lng.double)
}
