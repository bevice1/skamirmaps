
import androidx.compose.ui.graphics.Color
import sk.amir.maps.common.Ex
import sk.amir.maps.common.toOther
import sk.amir.maps.common.toRGBHexString
import kotlin.test.Test

class ExTestsIos {
    @Test
    fun test() {
        val x = Ex.toColor(Ex.Literal.String(Color.White.toRGBHexString()))
        println(x.generateJson())
        x.toOther()
    }
}