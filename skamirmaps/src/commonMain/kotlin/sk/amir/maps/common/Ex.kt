
package sk.amir.maps.common

import androidx.annotation.ColorInt
import androidx.annotation.Size
import androidx.compose.ui.text.intl.Locale
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.float
import kotlinx.serialization.json.floatOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive
import sk.amir.maps.shapes.MapGeometry
import sk.amir.maps.shapes.MapPolygonGeometry
import sk.amir.maps.shapes.fromJson

/**
 * Expressions
 *
 * Use this as a guide
 * https://docs.mapbox.com/style-spec/reference/expressions/
 * https://maplibre.org/maplibre-style-spec/expressions/
 * Only a subset of expressions are implemented.
 * Perhaps it would be better if we used the
 * generated json directly with the maplibre native (c++)
 */
internal sealed class Ex {
    internal object EmptyDefault : Ex() {
        override fun generateJson(): String {
            return ""
        }
    }

    override fun toString(): String {
        return generateJson()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Ex) { return false }
        return generateJson() == other.generateJson()
    }

    override fun hashCode(): Int {
        return generateJson().hashCode()
    }

    abstract fun generateJson(): String
    class Operator(
        private val operator: String,
        private val arguments: List<Ex>,
    ) : Ex() {
        constructor(operator: String, vararg arguments: Ex) : this(operator, arguments.toList())
        override fun generateJson(): String {
            return buildString {
                append("[\"")
                append(operator)
                append("\"")
                for (argument in arguments) {
                    append(", ")
                    append(argument.generateJson())
                }
                append("]")
            }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false
            if (!super.equals(other)) return false

            other as Operator

            if (operator != other.operator) return false
            if (arguments != other.arguments) return false

            return true
        }

        override fun hashCode(): Int {
            var result = super.hashCode()
            result = 31 * result + operator.hashCode()
            result = 31 * result + arguments.hashCode()
            return result
        }

    }

    sealed class Literal : Ex() {
        data class Number(val number: kotlin.Number) : Literal() {
            override fun generateJson() = number.toString()
        }

        data class Boolean(val bool: kotlin.Boolean) : Literal() {
            override fun generateJson() = bool.toString()
        }

        data class String(val string: kotlin.String) : Literal() {
            override fun generateJson() = "\"$string\""
        }

        internal data class Array(val literals: List<Literal>) : Literal() {
            override fun generateJson(): kotlin.String = buildString {
                append("[")
                for ((index, literal: Literal) in literals.withIndex()) {
                    append(literal.generateJson())
                    if (index != literals.lastIndex) {
                        append(", ")
                    }
                }
                append("]")
            }
        }
    }

    class Stop internal constructor(private val value: Ex, private val output: Ex) {
        companion object {
            fun toExpressionArray(vararg stops: Stop): Array<out Ex> {
                return stops
                    .map { listOf(it.value, it.output) }
                    .flatten()
                    .toTypedArray()
            }
        }
    }

    data class ExpressionMap(private val map: Map<String, Ex>) : Ex() {
        override fun generateJson() = buildString {
            append("{")
            map.onEachIndexed { index, (key, value) ->
                append("\"")
                append(key)
                append("\": ")
                append(value.generateJson())
                if (index != map.size - 1) {
                    append(", ")
                }
            }
            append("}")
        }
    }

    /*
    class NumberFormatOption internal constructor(type: String, value: Ex) :
        Option(type, value) {
        companion object {
            fun locale(string: Ex): NumberFormatOption {
                return NumberFormatOption("locale", string)
            }

            fun locale(string: String): NumberFormatOption {
                return NumberFormatOption("locale", Literal.String(string))
            }

            fun currency(string: Ex): NumberFormatOption {
                return NumberFormatOption("currency", string)
            }

            fun currency(string: String): NumberFormatOption {
                return NumberFormatOption(
                    "currency",
                    Literal.String(string)
                )
            }

            fun minFractionDigits(number: Ex): NumberFormatOption {
                return NumberFormatOption("min-fraction-digits", number)
            }

            fun minFractionDigits(number: Int): NumberFormatOption {
                return NumberFormatOption(
                    "min-fraction-digits",
                    Literal.Number(number as Number)
                )
            }

            fun maxFractionDigits(number: Ex): NumberFormatOption {
                return NumberFormatOption("max-fraction-digits", number)
            }

            fun maxFractionDigits(number: Int): NumberFormatOption {
                return NumberFormatOption(
                    "max-fraction-digits",
                    Literal.Number(number as Number)
                )
            }
        }
    }

    class FormatEntry internal constructor(
        internal val text: Ex,
        internal val options: List<FormatOption>?
    )

    class FormatOption internal constructor(type: String, value: Ex) :
        Option(type, value) {
        companion object {
            fun formatFontScale(expression: Ex): FormatOption {
                return FormatOption("font-scale", expression)
            }

            fun formatFontScale(scale: Double): FormatOption {
                return FormatOption(
                    "font-scale",
                    Literal.Number(scale)
                )
            }

            fun formatTextFont(expression: Ex): FormatOption {
                return FormatOption("text-font", expression)
            }

            fun formatTextFont(fontStack: Array<String>): FormatOption {
                val x = fontStack.map { Literal.String(it) }.toTypedArray()
                return FormatOption(
                    "text-font",
                    literal(x)
                )
            }

            fun formatTextColor(expression: Ex): FormatOption {
                return FormatOption("text-color", expression)
            }

            fun formatTextColor(@ColorInt color: Int): FormatOption {
                return FormatOption("text-color", color(color))
            }
        }
    }
     */

    internal object Converter {
        private fun convert(jsonArray: JsonArray): Ex {
            return if (jsonArray.size == 0) {
                throw IllegalArgumentException("Can't convert empty jsonArray expressions")
            } else {
                val operator = jsonArray[0].jsonPrimitive.toString().removeSurrounding("\"")
                val arguments: MutableList<Ex> = mutableListOf()
                if (operator == "within") {
                    // Test me!
                    within(
                        MapPolygonGeometry.fromJson(jsonArray[1])
                    )
                } else if (operator == "distance") {
                    distance(
                        MapGeometry.fromJson(jsonArray[1])
                    )
                } else {
                    for (i in 1 until jsonArray.size) {
                        val jsonElement = jsonArray[i]
                        if (operator == "literal" && jsonElement is JsonArray) {
                            val array = jsonElement.toTypedArray()
                                .map {
                                    check(it is JsonPrimitive) { "Nested literal arrays are not supported." }
                                    it.toLiteral()
                                }
                            arguments.add(Literal.Array(array))
                        } else if (jsonElement is JsonPrimitive) {
                            arguments.add(jsonElement.toLiteral())
                        } else {
                            arguments.add(convert(jsonElement))
                        }
                    }
                    Operator(operator, *arguments.toTypedArray())
                }
            }
        }

        private fun convert(jsonElement: JsonElement): Ex {
            return if (jsonElement is JsonArray) {
                convert(jsonElement)
            } else if (jsonElement is JsonPrimitive) {
                jsonElement.toLiteral()
            } else if (jsonElement is JsonNull) {
                Literal.String("")
            } else if (jsonElement !is JsonObject) {
                throw RuntimeException("Unsupported expression conversion for " + jsonElement::class)
            } else {
                val map: MutableMap<String, Ex> = mutableMapOf()
                val var2: Iterator<*> = jsonElement.keys.iterator()
                while (var2.hasNext()) {
                    val key = var2.next() as String
                    map[key] = convert(
                        jsonElement[key]!!
                    )
                }
                ExpressionMap(map)
            }
        }

        private fun JsonPrimitive.toLiteral(): Literal {
            return if (booleanOrNull != null) {
                Literal.Boolean(boolean)
            } else if (floatOrNull != null) {
                Literal.Number(float)
            } else if (jsonPrimitive.isString) {
                Literal.String(jsonPrimitive.toString().removeSurrounding("\""))
            } else {
                error("Unsupported literal expression conversion for " + jsonPrimitive::class)
            }
        }

        fun convert(rawExpression: String): Ex {
            val parsed = Json.parseToJsonElement(rawExpression)
            return if (rawExpression.startsWith("[") && rawExpression.endsWith("]")) {
                convert(parsed.jsonArray)
            } else {
                parsed.jsonPrimitive.toLiteral()
            }
        }
    }

    open class Option(var type: String, var value: Ex)
    internal companion object {
        fun literal(number: Number): Ex {
            return Operator("literal", Literal.Number(number))
        }

        fun literal(string: String): Ex {
            return Operator("literal", Literal.String(string))
        }

        fun literal(bool: Boolean): Ex {
            return Operator("literal", Literal.Boolean(bool))
        }

        fun <T: Literal>literal(array: Array<out T>): Ex {
            return Operator(
                "literal",
                Literal.Array(array.toList())
            )
        }

        fun color(@ColorInt color: Int): Ex {
            val rgba = ColorUtils.colorToRgbaArray(color)
            return rgba(
                rgba[0] as Number,
                rgba[1] as Number,
                rgba[2] as Number,
                rgba[3] as Number
            )
        }

        fun rgb(red: Ex, green: Ex, blue: Ex): Ex {
            return Operator("rgb", red, green, blue)
        }

        fun rgb(red: Number, green: Number, blue: Number): Ex {
            return rgb(
                Literal.Number(red),
                Literal.Number(green),
                Literal.Number(blue)
            )
        }

        fun rgba(
            red: Ex,
            green: Ex,
            blue: Ex,
            alpha: Ex
        ): Ex {
            return Operator("rgba", red, green, blue, alpha)
        }

        fun rgba(red: Number, green: Number, blue: Number, alpha: Number): Ex {
            return rgba(
                Literal.Number(red),
                Literal.Number(green),
                Literal.Number(blue),
                Literal.Number(alpha)
            )
        }

        fun toRgba(expression: Ex): Ex {
            return Operator("to-rgba", expression)
        }

        fun eq(compareOne: Ex, compareTwo: Ex): Ex {
            return Operator("==", compareOne, compareTwo)
        }

        fun eq(compareOne: Ex, compareTwo: Ex, collator: Ex): Ex {
            return Operator("==", compareOne, compareTwo, collator)
        }

        fun eq(compareOne: Ex, compareTwo: Boolean): Ex {
            return eq(compareOne, literal(compareTwo))
        }

        fun eq(compareOne: Ex, compareTwo: String): Ex {
            return eq(compareOne, literal(compareTwo))
        }

        fun eq(compareOne: Ex, compareTwo: String, collator: Ex): Ex {
            return eq(compareOne, literal(compareTwo), collator)
        }

        fun eq(compareOne: Ex, compareTwo: Number): Ex {
            return eq(compareOne, literal(compareTwo))
        }

        fun neq(compareOne: Ex, compareTwo: Ex): Ex {
            return Operator("!=", compareOne, compareTwo)
        }

        fun neq(compareOne: Ex, compareTwo: Ex, collator: Ex): Ex {
            return Operator("!=", compareOne, compareTwo, collator)
        }

        fun neq(compareOne: Ex, compareTwo: Boolean): Ex {
            return Operator("!=", compareOne, literal(compareTwo))
        }

        fun neq(compareOne: Ex, compareTwo: String): Ex {
            return Operator("!=", compareOne, literal(compareTwo))
        }

        fun neq(compareOne: Ex, compareTwo: String, collator: Ex): Ex {
            return Operator("!=", compareOne, literal(compareTwo), collator)
        }

        fun neq(compareOne: Ex, compareTwo: Number): Ex {
            return Operator("!=", compareOne, literal(compareTwo))
        }

        fun gt(compareOne: Ex, compareTwo: Ex): Ex {
            return Operator(">", compareOne, compareTwo)
        }

        fun gt(compareOne: Ex, compareTwo: Ex, collator: Ex): Ex {
            return Operator(">", compareOne, compareTwo, collator)
        }

        fun gt(compareOne: Ex, compareTwo: Number): Ex {
            return Operator(">", compareOne, literal(compareTwo))
        }

        fun gt(compareOne: Ex, compareTwo: String): Ex {
            return Operator(">", compareOne, literal(compareTwo))
        }

        fun gt(compareOne: Ex, compareTwo: String, collator: Ex): Ex {
            return Operator(">", compareOne, literal(compareTwo), collator)
        }

        fun lt(compareOne: Ex, compareTwo: Ex): Ex {
            return Operator("<", compareOne, compareTwo)
        }

        fun lt(compareOne: Ex, compareTwo: Ex, collator: Ex): Ex {
            return Operator("<", compareOne, compareTwo, collator)
        }

        fun lt(compareOne: Ex, compareTwo: Number): Ex {
            return Operator("<", compareOne, literal(compareTwo))
        }

        fun lt(compareOne: Ex, compareTwo: String): Ex = Operator(
            "<",
            compareOne, literal(compareTwo)
        )

        fun lt(compareOne: Ex, compareTwo: String, collator: Ex): Ex = Operator(
            "<",
            compareOne,
            literal(compareTwo),
            collator
        )

        fun gte(compareOne: Ex, compareTwo: Ex): Ex = Operator(
            ">=",
            compareOne, compareTwo
        )

        fun gte(compareOne: Ex, compareTwo: Ex, collator: Ex): Ex = Operator(
            ">=",
            compareOne, compareTwo, collator
        )

        fun gte(compareOne: Ex, compareTwo: Number): Ex = Operator(
            ">=",
            compareOne,
            literal(compareTwo)
        )

        fun gte(compareOne: Ex, compareTwo: String): Ex {
            return Operator(">=", compareOne, literal(compareTwo))
        }

        fun gte(compareOne: Ex, compareTwo: String, collator: Ex): Ex {
            return Operator(">=", compareOne, literal(compareTwo), collator)
        }

        fun lte(compareOne: Ex, compareTwo: Ex): Ex {
            return Operator("<=", compareOne, compareTwo)
        }

        fun lte(compareOne: Ex, compareTwo: Ex, collator: Ex): Ex {
            return Operator("<=", compareOne, compareTwo, collator)
        }

        fun lte(compareOne: Ex, compareTwo: Number): Ex {
            return Operator(
                "<=",
                compareOne, literal(compareTwo)
            )
        }

        fun lte(compareOne: Ex, compareTwo: String): Ex {
            return Operator(
                "<=",
                compareOne, literal(compareTwo)
            )
        }

        fun lte(compareOne: Ex, compareTwo: String, collator: Ex): Ex {
            return Operator(
                "<=",
                compareOne, literal(compareTwo), collator
            )
        }

        fun all(vararg input: Ex): Ex {
            return Operator("all", *input)
        }

        fun any(vararg input: Ex): Ex {
            return Operator("any", *input)
        }

        fun not(input: Ex): Ex {
            return Operator("!", input)
        }

        fun not(input: Boolean): Ex {
            return not(literal(input))
        }

        fun switchCase(@Size(min = 1L) vararg input: Ex): Ex {
            return Operator("case", *input)
        }

        fun match(@Size(min = 2L) vararg input: Ex): Ex {
            return Operator("match", *input)
        }

        fun match(
            input: Ex,
            defaultOutput: Ex,
            vararg stops: Stop
        ): Ex {
            return match(
                *join(
                    join(
                        arrayOf(input),
                        Stop.toExpressionArray(*stops)
                    ),
                    arrayOf(defaultOutput)
                )
            )
        }

        fun coalesce(vararg input: Ex): Ex {
            return Operator("coalesce", *input)
        }

        fun properties(): Ex {
            return Operator("properties")
        }

        fun geometryType(): Ex {
            return Operator("geometry-type")
        }

        fun id(): Ex {
            return Operator("id")
        }

        fun accumulated(): Ex {
            return Operator("accumulated")
        }

        fun heatmapDensity(): Ex {
            return Operator("heatmap-density")
        }

        fun lineProgress(): Ex {
            return Operator("line-progress")
        }

        fun at(number: Ex, expression: Ex): Ex {
            return Operator("at", number, expression)
        }

        fun at(number: Number, expression: Ex): Ex {
            return at(literal(number), expression)
        }

        fun `in`(needle: Ex, haystack: Ex): Ex {
            return Operator("in", needle, haystack)
        }

        fun indexOf(keyword: Ex, input: Ex): Ex {
            return Operator("index-of", keyword, input)
        }

        fun indexOf(keyword: Ex, input: Ex, fromIndex: Ex): Ex {
            return Operator("index-of", keyword, input, fromIndex)
        }

        fun slice(input: Ex, fromIndex: Ex): Ex {
            return Operator("slice", input, fromIndex)
        }

        fun slice(input: Ex, fromIndex: Ex, toIndex: Ex): Ex {
            return Operator("slice", input, fromIndex, toIndex)
        }

        fun `in`(needle: Number, haystack: Ex): Ex {
            return Operator(
                "in",
                *arrayOf<Ex>(literal(needle), haystack)
            )
        }

        fun `in`(needle: String, haystack: Ex): Ex {
            return Operator(
                "in",
                literal(needle), haystack
            )
        }

        fun distance(geoJson: MapGeometry): Ex {
            val map: MutableMap<String, Ex> = mutableMapOf()
            map["json"] = literal(geoJson.toJson().toString())
            return Operator("distance", ExpressionMap(map))
        }

        fun within(polygon: MapPolygonGeometry): Ex {
            val map: MutableMap<String, Ex> = mutableMapOf()
            map["type"] = literal(polygon.type)
            map["json"] = literal(polygon.toJson().toString())
            return Operator("within", ExpressionMap(map))
        }

        fun get(input: Ex): Ex {
            return Operator("get", input)
        }

        fun get(input: String): Ex {
            return get(Literal.String(input))
        }

        fun get(key: Ex, `object`: Ex): Ex {
            return Operator("get", key, `object`)
        }

        /**
         * coalesce to [object], if the property under [key] doesn't exist
         * @see <a href="https://docs.mapbox.com/style-spec/reference/expressions/#get">link</a>
         */
        fun get(key: String, `object`: Ex): Ex {
            return get(Literal.String(key), `object`)
        }

        fun has(key: Ex): Ex {
            return Operator("has", key)
        }

        fun has(key: String): Ex {
            return has(Literal.String(key))
        }

        fun has(key: Ex, `object`: Ex): Ex {
            return Operator("has", key, `object`)
        }

        fun has(key: String, `object`: Ex): Ex {
            return has(Literal.String(key), `object`)
        }

        fun length(expression: Ex): Ex {
            return Operator("length", expression)
        }

        fun length(input: String): Ex {
            return length(Literal.String(input))
        }

        fun ln2(): Ex {
            return Operator("ln2")
        }

        fun pi(): Ex {
            return Operator("pi")
        }

        fun e(): Ex {
            return Operator("e")
        }

        fun sum(@Size(min = 2L) vararg numbers: Ex): Ex {
            return Operator("+", *numbers)
        }

        fun sum(@Size(min = 2L) vararg numbers: Number): Ex {
            val numberExpression = arrayOfNulls<Ex>(numbers.size)
            for (i in numbers.indices) {
                numberExpression[i] = Literal.Number(
                    numbers[i]
                )
            }
            return sum(*numberExpression.requireNoNulls())
        }

        fun product(@Size(min = 2L) vararg numbers: Ex): Ex {
            return Operator("*", *numbers)
        }

        fun product(@Size(min = 2L) vararg numbers: Number): Ex {
            val numberExpression = arrayOfNulls<Ex>(numbers.size)
            for (i in numbers.indices) {
                numberExpression[i] = literal(
                    numbers[i]
                )
            }
            return product(*numberExpression.requireNoNulls())
        }

        fun subtract(number: Ex): Ex {
            return Operator("-", number)
        }

        fun subtract(number: Number): Ex {
            return subtract(Literal.Number(number))
        }

        fun subtract(first: Ex, second: Ex): Ex {
            return Operator("-", first, second)
        }

        fun subtract(first: Number, second: Number): Ex {
            return subtract(
                Literal.Number(first),
                Literal.Number(second)
            )
        }

        fun division(first: Ex, second: Ex): Ex {
            return Operator("/", first, second)
        }

        fun division(first: Number, second: Number): Ex {
            return division(
                Literal.Number(first),
                Literal.Number(second)
            )
        }

        fun mod(first: Ex, second: Ex): Ex {
            return Operator("%", first, second)
        }

        fun mod(first: Number, second: Number): Ex {
            return mod(
                Literal.Number(first),
                Literal.Number(second)
            )
        }

        fun pow(first: Ex, second: Ex): Ex {
            return Operator("^", first, second)
        }

        fun pow(first: Number, second: Number): Ex {
            return pow(
                Literal.Number(first),
                Literal.Number(second)
            )
        }

        fun sqrt(number: Ex): Ex {
            return Operator("sqrt", number)
        }

        fun sqrt(number: Number): Ex {
            return sqrt(Literal.Number(number))
        }

        fun log10(number: Ex): Ex {
            return Operator("log10", number)
        }

        fun log10(number: Number): Ex {
            return log10(Literal.Number(number))
        }

        fun ln(number: Ex): Ex {
            return Operator("ln", number)
        }

        fun ln(number: Number): Ex {
            return ln(Literal.Number(number))
        }

        fun log2(number: Ex): Ex {
            return Operator("log2", number)
        }

        fun log2(number: Number): Ex {
            return log2(Literal.Number(number))
        }

        fun sin(number: Ex): Ex {
            return Operator("sin", number)
        }

        fun sin(number: Number): Ex {
            return sin(Literal.Number(number))
        }

        fun cos(number: Ex): Ex {
            return Operator("cos", number)
        }

        fun cos(number: Number): Ex {
            return Operator("cos", Literal.Number(number))
        }

        fun tan(number: Ex): Ex {
            return Operator("tan", number)
        }

        fun tan(number: Number): Ex {
            return Operator("tan", Literal.Number(number))
        }

        fun asin(number: Ex): Ex {
            return Operator("asin", number)
        }

        fun asin(number: Number): Ex {
            return asin(Literal.Number(number))
        }

        fun acos(number: Ex): Ex {
            return Operator("acos", number)
        }

        fun acos(number: Number): Ex {
            return acos(Literal.Number(number))
        }

        fun atan(number: Ex): Ex {
            return Operator("atan", number)
        }

        fun atan(number: Number): Ex {
            return atan(Literal.Number(number))
        }

        fun min(@Size(min = 1L) vararg numbers: Ex): Ex {
            return Operator("min", *numbers)
        }

        fun min(@Size(min = 1L) vararg numbers: Number): Ex {
            return min(
                *numbers
                    .map { Literal.Number(it) }
                    .toTypedArray()
            )
        }

        fun max(@Size(min = 1L) vararg numbers: Ex): Ex {
            return Operator("max", *numbers)
        }

        fun max(@Size(min = 1L) vararg numbers: Number): Ex {
            return max(
                *numbers
                    .map { Literal.Number(it) }
                    .toTypedArray()
            )
        }

        fun round(expression: Ex): Ex {
            return Operator("round", expression)
        }

        fun round(number: Number): Ex {
            return round(Literal.Number(number))
        }

        fun abs(expression: Ex): Ex {
            return Operator("abs", expression)
        }

        fun abs(number: Number): Ex {
            return abs(Literal.Number(number))
        }

        fun ceil(expression: Ex): Ex {
            return Operator("ceil", expression)
        }

        fun ceil(number: Number): Ex {
            return ceil(Literal.Number(number))
        }

        fun floor(expression: Ex): Ex {
            return Operator("floor", expression)
        }

        fun floor(number: Number): Ex {
            return floor(Literal.Number(number))
        }

        fun resolvedLocale(collator: Ex): Ex {
            return Operator("resolved-locale", collator)
        }

        fun isSupportedScript(expression: Ex): Ex {
            return Operator("is-supported-script", expression)
        }

        fun isSupportedScript(string: String): Ex {
            return Operator(
                "is-supported-script",
                Literal.String(string)
            )
        }

        fun upcase(string: Ex): Ex {
            return Operator("upcase", string)
        }

        fun upcase(string: String): Ex {
            return upcase(Literal.String(string))
        }

        fun downcase(input: Ex): Ex {
            return Operator("downcase", input)
        }

        fun downcase(input: String): Ex {
            return downcase(Literal.String(input))
        }

        fun concat(vararg input: Ex): Ex {
            return Operator("concat", *input)
        }

        fun concat(vararg input: String): Ex {
            return concat(
                *input
                    .map { Literal.String(it) }
                    .toTypedArray()
            )
        }

        fun array(input: Ex): Ex {
            return Operator("array", input)
        }

        fun typeOf(input: Ex): Ex {
            return Operator("typeof", input)
        }

        fun string(vararg input: Ex): Ex {
            return Operator("string", *input)
        }

        fun number(vararg input: Ex): Ex {
            return Operator("number", *input)
        }

        /*
        fun numberFormat(
            number: Ex,
            vararg options: NumberFormatOption
        ): Ex {
            val map: MutableMap<String, Ex> = mutableMapOf()
            val var3: Array<out NumberFormatOption> = options
            val var4 = options.size
            for (var5 in 0 until var4) {
                val option = var3[var5]
                map[option.type] = option.value
            }
            return Operator("number-format", number, ExpressionMap(map))
        }

        fun numberFormat(
            number: Number,
            vararg options: NumberFormatOption
        ): Ex {
            return numberFormat(Literal.Number(number), *options)
        }
         */

        fun bool(vararg input: Ex): Ex {
            return Operator("boolean", *input)
        }

        fun collator(
            caseSensitive: Boolean,
            diacriticSensitive: Boolean,
            locale: Locale
        ): Ex {
            val map: MutableMap<String, Ex> = mutableMapOf()
            map["case-sensitive"] = Literal.Boolean(caseSensitive)
            map["diacritic-sensitive"] = Literal.Boolean(diacriticSensitive)
            val localeStringBuilder = StringBuilder()
            val language = locale.language
            if (language.isNotEmpty()) {
                localeStringBuilder.append(language)
            }
            val country = locale.region
            if (country.isNotEmpty()) {
                localeStringBuilder.append("-")
                localeStringBuilder.append(country)
            }
            map["locale"] = Literal.String(localeStringBuilder.toString())
            return Operator("collator", ExpressionMap(map))
        }

        fun collator(caseSensitive: Boolean, diacriticSensitive: Boolean): Ex {
            val map = mapOf(
                "case-sensitive" to Literal.Boolean(caseSensitive),
                "diacritic-sensitive" to  Literal.Boolean(diacriticSensitive)
            )
            return Operator("collator", ExpressionMap(map))
        }

        fun collator(
            caseSensitive: Ex,
            diacriticSensitive: Ex,
            locale: Ex
        ): Ex {
            val map: MutableMap<String, Ex> = mutableMapOf()
            map["case-sensitive"] = caseSensitive
            map["diacritic-sensitive"] = diacriticSensitive
            map["locale"] = locale
            return Operator("collator", ExpressionMap(map))
        }

        fun collator(caseSensitive: Ex, diacriticSensitive: Ex): Ex {
            val map: MutableMap<String, Ex> = mutableMapOf()
            map["case-sensitive"] = caseSensitive
            map["diacritic-sensitive"] = diacriticSensitive
            return Operator("collator", ExpressionMap(map))
        }

        /*
        fun format(vararg formatEntries: FormatEntry): Ex {
            val mappedExpressions = arrayOfNulls<Ex>(formatEntries.size * 2)
            var mappedIndex = 0
            val var3: kotlin.Array<out FormatEntry> = formatEntries
            val var4 = formatEntries.size
            for (var5 in 0 until var4) {
                val formatEntry = var3[var5]
                mappedExpressions[mappedIndex++] = formatEntry.text
                val map: MutableMap<String, Ex> = mutableMapOf()
                if (formatEntry.options != null) {
                    val var8 = formatEntry.options
                    val var9 = var8.size
                    for (var10 in 0 until var9) {
                        val option = var8[var10]
                        map[option.type] = option.value
                    }
                }
                mappedExpressions[mappedIndex++] = ExpressionMap(map)
            }
            return Operator("format", *mappedExpressions.requireNoNulls())
        }

        fun formatEntry(
            text: Ex,
            vararg formatOptions: FormatOption
        ): FormatEntry {
            return FormatEntry(text, formatOptions.toList())
        }

        fun formatEntry(text: Ex): FormatEntry {
            return FormatEntry(text, null)
        }

        fun formatEntry(
            text: String,
            vararg formatOptions: FormatOption
        ): FormatEntry {
            return FormatEntry(Literal.String(text), formatOptions.toList())
        }

        fun formatEntry(text: String): FormatEntry {
            return FormatEntry(
                Literal.String(text),
                null
            )
        }
        */

        fun image(input: Ex): Ex {
            return Operator("image", input)
        }

        fun `object`(input: Ex): Ex {
            return Operator("object", input)
        }

        fun toString(input: Ex): Ex {
            return Operator("to-string", input)
        }

        fun toNumber(input: Ex): Ex {
            return Operator("to-number", input)
        }

        fun toBool(input: Ex): Ex {
            return Operator("to-boolean", input)
        }

        fun toColor(vararg input: Ex): Ex {
            return Operator("to-color", *input)
        }

        fun let(@Size(min = 1L) vararg input: Ex): Ex {
            return Operator("let", *input)
        }

        fun `var`(expression: Ex): Ex {
            return Operator("var", expression)
        }

        fun `var`(variableName: String): Ex {
            return `var`(Literal.String(variableName))
        }

        fun zoom(): Ex {
            return Operator("zoom")
        }

        /* DOESN'T WORK PROPERLY
        fun stop(stop: Ex, value: Ex): Stop {
            return Stop(stop, value)
        }

        fun step(input: Number, defaultOutput: Ex, vararg stops: Ex): Ex {
            return step(
                Literal.Number(input),
                defaultOutput,
                *stops
            )
        }

        fun step(
            input: Ex,
            defaultOutput: Ex,
            vararg stops: Ex
        ): Ex {
            return Operator(
                "step", *join(
                    arrayOf(input, defaultOutput), stops
                )
            )
        }

        fun step(
            input: Number,
            defaultOutput: Ex,
            vararg stops: Stop
        ): Ex {
            return step(
                Literal.Number(input),
                defaultOutput,
                *Stop.toExpressionArray(*stops)
            )
        }

        fun step(
            input: Ex,
            defaultOutput: Ex,
            vararg stops: Stop
        ): Ex {
            return step(
                input,
                defaultOutput,
                *Stop.toExpressionArray(*stops)
            )
        }

        fun step(input: Number, defaultOutput: Number, vararg stops: Ex): Ex {
            return step(
                literal(input),
                defaultOutput,
                *stops
            )
        }

        fun step(input: Ex, defaultOutput: Number, vararg stops: Ex): Ex {
            return step(
                input,
                literal(defaultOutput),
                *stops
            )
        }

        fun step(input: Number, defaultOutput: Number, vararg stops: Stop): Ex {
            return step(
                literal(input),
                defaultOutput,
                *Stop.toExpressionArray(*stops)
            )
        }

        fun step(
            input: Ex,
            defaultOutput: Number,
            vararg stops: Stop
        ): Ex {
            return step(
                input,
                defaultOutput,
                *Stop.toExpressionArray(*stops)
            )
        }

        fun interpolator(operator: String, arguments: Array<Ex>, vararg stops: Ex) : Operator {
            return Operator("interpolate", Literal.String(operator), *arguments, *stops)
        }

        fun linear(): Operator {
            return Operator("linear")
        }

        fun exponential(base: Number): Operator {
            return exponential(literal(base))
        }

        fun exponential(expression: Ex): Operator {
            return interpolator("exponential", arrayOf(expression))
        }

        fun cubicBezier(
            x1: Ex,
            y1: Ex,
            x2: Ex,
            y2: Ex,
            vararg stops: Ex
        ): Operator {
            return interpolator("cubic-bezier", arrayOf(x1, y1, x2, y2), *stops)
        }

        fun cubicBezier(x1: Number, y1: Number, x2: Number, y2: Number): Operator {
            return cubicBezier(
                literal(x1),
                literal(y1),
                literal(x2),
                literal(y2)
            )
        }
        */

        private fun join(
            left: Array<out Ex>,
            right: Array<out Ex>
        ): Array<Ex> {
            return (left.toList() + right.toList()).toTypedArray()
        }
    }
}
