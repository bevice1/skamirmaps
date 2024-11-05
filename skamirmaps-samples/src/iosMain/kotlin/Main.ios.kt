
package sk.amir.maplibre.kmp.testbench
import androidx.compose.ui.window.ComposeUIViewController
import sk.amir.maps.samples.MainScreen

class MainViewFactory() {
    fun create() = ComposeUIViewController {
        MainScreen()
    }
}
