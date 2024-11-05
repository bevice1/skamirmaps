
package sk.amir.maps.samples

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

internal enum class DemoType(val factory: @Composable () -> Unit) {
    Points({ PointsDemo() }),
    Polygon({ PolygonDemo() }),
    DarkMode({ DarkModeDemo() }),
    CameraDemo({ CameraDemo() }),
    UiSettingsDemo({ UiSettingsDemo() }),
    SymbolImagesDemo({ SymbolImagesDemo() }),
    ReloadDemo({ ReloadDemo() }),
    LayeringOrderDemo({ LayeringOrderDemo() }),
    ;

    @Composable
    fun content() {
        factory()
    }
}

@Composable
fun DemoScreen() {
    var demoType by rememberSaveable { mutableStateOf<DemoType?>(null) }
    var expanded by rememberSaveable { mutableStateOf(true) }
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomEnd,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End,
        ) {
            Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                demoType?.content()
            }
            HorizontalDivider()
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(demoType?.name ?: "")
                Spacer(Modifier.weight(1f))
                Button(
                    onClick = { expanded = true }
                ) {
                    Icon(Icons.Default.Menu, "menu")
                }
            }
        }

        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.padding(16.dp).align(Alignment.End),
            ) {
                DemoType.entries
                    .map {
                        OutlinedButton(onClick = {
                            demoType = it
                            expanded = false
                        }) {
                            Text(it.name)
                        }
                    }
            }
        }
    }
}
