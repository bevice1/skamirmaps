
package sk.amir.maps.samples

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun ReloadDemo() {
    val possibleDemos = remember {
        DemoType.entries
            .filter { it != DemoType.ReloadDemo }
            .toTypedArray()
    }
    var index by remember { mutableIntStateOf(0) }
    LaunchedEffect(index) {
        delay(5.seconds)
        index += 1
    }
    possibleDemos[index.mod(possibleDemos.size)].content()
}
