import sk.amir.maps.common.LatLng
import sk.amir.maps.shapes.MapPointGeometry
import sk.amir.maps.shapes.MapPolygonGeometry
import sk.amir.maps.shapes.MapPolylineGeometry
import kotlin.test.Test
import kotlin.test.assertEquals

class Equals {
    @Test
    fun points() {
        pointA.let {
            assertEquals(MapPointGeometry(it), MapPointGeometry(it))
        }
    }

    @Test
    fun polyline() {
        listOf(pointA, pointB).let {
            assertEquals(MapPolylineGeometry(it), MapPolylineGeometry(it))
        }
    }

    @Test
    fun polygon() {
        listOf(pointA, pointB, pointC).let {
            assertEquals(MapPolygonGeometry(listOf(it)), MapPolygonGeometry(listOf(it)))
        }
    }
}

val pointA = LatLng(25.0, 35.0)
val pointB = LatLng(26.0, 34.0)
val pointC = LatLng(27.0, 33.0)
