
package sk.amir.maps.common

import cocoapods.MapLibre.expressionWithMLNJSONObject
import cocoapods.MapLibre.mgl_jsonExpressionObject
import cocoapods.MapLibre.predicateWithMLNJSONObject
import platform.Foundation.NSArray
import platform.Foundation.NSData
import platform.Foundation.NSExpression
import platform.Foundation.NSJSONSerialization
import platform.Foundation.NSNumber
import platform.Foundation.NSPredicate
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.create
import platform.Foundation.dataUsingEncoding
import platform.darwin.NSObject


private fun Ex.toMLNJSONObject(): NSArray {
    val data = (generateJson() as NSString).dataUsingEncoding(NSUTF8StringEncoding)
//    println("data: ${generateJson()}")
    checkNotNull(data)
    return NSJSONSerialization.JSONObjectWithData(data, 0U, null) as NSArray
}

internal fun Ex.toOther(): NSExpression {
    if (this is Ex.Literal) {
        return NSExpression.expressionForConstantValue(this.toNS())
    }
    return NSExpression.expressionWithMLNJSONObject(toMLNJSONObject())
}

internal fun Ex.toNSPredicate(): NSPredicate {
    return NSPredicate.predicateWithMLNJSONObject(toMLNJSONObject())
}

internal fun <T: Ex.Literal>T.toNS(): NSObject = run {
    when (this) {
        is Ex.Literal.Number -> number as NSNumber
        is Ex.Literal.String -> string as NSString
        is Ex.Literal.Boolean -> bool as NSNumber
        is Ex.Literal.Array -> {
            literals.map { it.toNS() }.toTypedArray() as NSArray
        }
        else -> { TODO() }
    }
}

internal fun NSExpression.toOther(): Ex {
    return when (val obj = mgl_jsonExpressionObject) {
        is Number -> Ex.Literal.Number(obj)
        is String -> Ex.Literal.String(obj)
        is Boolean -> Ex.Literal.Boolean(obj)
        else -> {
            check(obj is NSArray) {
                "we were expecting an NSArray, got ${obj::class.qualifiedName}"
            }
            val data: NSData? = NSJSONSerialization.dataWithJSONObject(obj, 0U, null)
            checkNotNull(data)
            val json = NSString.create(data, NSUTF8StringEncoding) as String
            Ex.Converter.convert(json)
        }
    }
}
