
package sk.amir.maps.compose

import androidx.compose.runtime.Updater
import sk.amir.maps.common.Ex

// consider comparing to null?
internal inline fun <V: Ex, N: LayerMapLayoutNode> Updater<N>.setNonDefault(value: V, crossinline callback: N.(V) -> Unit) {
    set(value) { if (it !is Ex.EmptyDefault) callback(it) }
}
