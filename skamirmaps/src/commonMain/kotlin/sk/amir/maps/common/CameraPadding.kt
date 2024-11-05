
package sk.amir.maps.common

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp

@Immutable
class CameraPadding(
    val top: Dp,
    val bottom: Dp,
    val start: Dp,
    val end: Dp
) {
    constructor(all: Dp) : this(
        top = all,
        bottom = all,
        start = all,
        end = all,
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CameraPadding

        if (top != other.top) return false
        if (bottom != other.bottom) return false
        if (start != other.start) return false
        if (end != other.end) return false

        return true
    }

    override fun hashCode(): Int {
        var result = top.hashCode()
        result = 31 * result + bottom.hashCode()
        result = 31 * result + start.hashCode()
        result = 31 * result + end.hashCode()
        return result
    }

}