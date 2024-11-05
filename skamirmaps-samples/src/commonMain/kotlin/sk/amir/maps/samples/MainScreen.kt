
package sk.amir.maps.samples

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// to automate testing of all the features

@Composable
fun MainScreen() {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(primary = Color(0xff2c3d87))
    ) {
        DemoScreen()
    }
}
