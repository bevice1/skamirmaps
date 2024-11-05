import sk.amir.maps.common.Ex
import kotlin.test.DefaultAsserter.assertEquals
import kotlin.test.Test
import kotlin.test.assertEquals

class ExTests {
    @Test
    fun abs() {
        assertEquals("""["abs", -2]""", Ex.abs(-2).generateJson())
        assertEquals("""["abs", 21.12345678]""", Ex.abs(21.12345678).generateJson())
    }

    @Test
    fun literal() {
        assertEquals("""["literal", true]""", Ex.literal(true).generateJson())
        assertEquals("""["literal", 25]""", Ex.literal(25).generateJson())
        assertEquals("""["literal", "hello world"]""", Ex.literal("hello world").generateJson())
        assertEquals(
            """["literal", [2, "d"]]""",
            Ex.literal(arrayOf(Ex.Literal.Number(2), Ex.Literal.String("d"))).generateJson())
    }

    @Test
    fun get() {
        assertEquals("""["get", "some variable"]""", Ex.get("some variable").generateJson())
        assertEquals("""["get", "some variable"]""", Ex.get("some variable").generateJson())
    }
}
