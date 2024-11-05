import sk.amir.maps.common.LatLngBounds
import sk.amir.maps.common.LatLng
import sk.amir.maps.common.contains
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BoundingBoxTests {
    @Test
    fun contains() {
        with(LatLngBounds(LatLng(0.0, 0.0), LatLng(2.0, 50.0))) {
            assertTrue { contains(LatLng(0.0, 0.0)) }
            assertFalse { contains(LatLng(-0.1, 0.0)) }
            assertFalse { contains(LatLng(0.1, -0.1)) }
            assertFalse { contains(LatLng(-0.1, -0.1)) }
            assertTrue { contains(LatLng(1.0, 25.0)) }
            assertTrue { contains(LatLng(2.0, 50.0)) }
            assertFalse { contains(LatLng(2.1, 50.0)) }
            assertFalse { contains(LatLng(2.0, 51.0)) }
            assertFalse { contains(LatLng(2.0, -0.1)) }
        }

        with(LatLngBounds(LatLng(-20.0, 150.0), LatLng(20.0, -150.0))) {
            assertTrue { contains(LatLng(0.0, 180.0)) }
            assertTrue { contains(LatLng(0.0, -180.0)) }
        }

        with(LatLngBounds(LatLng(-20.0, -190.0), LatLng(20.0, -170.0))) {
            assertTrue { contains(LatLng(0.0, 180.0)) }
            assertTrue { contains(LatLng(0.0, -180.0)) }
        }

    }
}