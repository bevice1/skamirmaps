
package sk.amir.maps.common

import androidx.annotation.ColorInt
import androidx.compose.ui.graphics.Color

internal object ColorUtils {
    fun colorToRgbaArray(@ColorInt color: Int): FloatArray {
        return floatArrayOf(
            (color shr 16 and 255).toFloat(),
            (color shr 8 and 255).toFloat(),
            (color and 255).toFloat(),
            (color shr 24 and 255).toFloat() / 255.0f
        )
    }
}

@OptIn(ExperimentalStdlibApi::class)
internal fun Color.toRGBHexString(): String {
    return ByteArray(3) {
        when (it) {
            0 -> (red * 255).toUInt().toByte()
            1 -> (green * 255).toUInt().toByte()
            else -> (blue * 255).toUInt().toByte()
        }
    }.toHexString()
        .let { "#$it"}
}
