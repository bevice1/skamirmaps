
package sk.amir.maps.compose

import kotlinx.atomicfu.AtomicLong
import kotlinx.atomicfu.atomic

private val index: AtomicLong = atomic(0L)

internal fun getNextSourceId() = index.incrementAndGet().let { "s_$it" }
internal fun getNextLayerId() = index.incrementAndGet().let { "l_$it" }
