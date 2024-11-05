import sk.amir.maps.common.LatLng
import sk.amir.maps.common.normalized
import kotlin.test.Test
import kotlin.test.assertEquals

class LatLngNormalisationTests {
    @Test
    fun normaliseTest() {
        with(LatLng(0.0, 5.0)) { assertEquals(this, normalized()) }
        with(LatLng(80.0, 50.0)) { assertEquals(this, normalized()) }
        with(LatLng(-80.0, -50.0)) { assertEquals(this, normalized()) }
        with(LatLng(95.0, 5.0)) { assertEquals(LatLng(-85.0, 5.0), normalized()) }
        with(LatLng(95.0 + 180.0, 5.0)) { assertEquals(LatLng(-85.0, 5.0), normalized()) }
        with(LatLng(-95.0, 5.0)) { assertEquals(LatLng(85.0, 5.0), normalized()) }
        with(LatLng(-95.0 - 180.0, 5.0)) { assertEquals(LatLng(85.0, 5.0), normalized()) }
        with(LatLng(0.0, -185.0)) { assertEquals(LatLng(0.0, 175.0), normalized()) }
        with(LatLng(0.0, 185.0)) { assertEquals(LatLng(0.0, -175.0), normalized()) }
        with(LatLng(0.0, 185.0 + 360)) { assertEquals(LatLng(0.0, -175.0), normalized()) }
        with(LatLng(0.0, 185.0 + 720)) { assertEquals(LatLng(0.0, -175.0), normalized()) }
        with(LatLng(0.0, 185.0 - 360)) { assertEquals(LatLng(0.0, -175.0), normalized()) }
        with(LatLng(0.0, 185.0 - 720)) { assertEquals(LatLng(0.0, -175.0), normalized()) }
    }
}