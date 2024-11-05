
package sk.amir.maps.common

import com.google.gson.Gson
import org.maplibre.android.style.expressions.Expression
import org.maplibre.android.style.layers.PropertyValue

internal fun <T> PropertyValue<T>.toOther(): Ex {
    return if (isExpression) {
        expression!!.toOther()
    } else {
        if (isValue) {
            val colorInt = colorInt
            if (colorInt != null) {
                val (r, g, b, a) = ColorUtils.colorToRgbaArray(colorInt)
                Ex.rgba(r, g, b, a)
            } else {
                Expression.ExpressionLiteral(value!!).toOther()
            }
        } else if (isNull) {
            Ex.literal("")
        } else {
            error("Unsupported expression $this")
        }
    }
}

internal fun Expression.toOther(): Ex {
    val string = toString()
    return Ex.Converter.convert(string)
}

internal fun Ex.toOther(): Expression {
    val generatedJson = generateJson()
    return Expression.Converter.convert(generatedJson)
}