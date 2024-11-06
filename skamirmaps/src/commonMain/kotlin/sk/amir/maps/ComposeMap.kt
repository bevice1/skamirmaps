
package sk.amir.maps

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Composition
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.imageResource
import sk.amir.maps.compose.MapApplier

internal suspend inline fun disposingComposition(factory: () -> Composition) {
    val composition = factory()
    try {
        awaitCancellation()
    } finally {
        composition.dispose()
    }
}

internal suspend fun newComposition(
    parent: CompositionContext,
    state: MapState,
    content: @Composable () -> Unit,
): Composition {
    state.onMapLoadFlow.first()
    state.onStyleFinishedLoadingFlow.first()
    // little delay for ios to settle. Otherwise it crashes while adding a source.
    // it crashes if you load a style. Because the default one is being unloaded.
    return Composition(
        MapApplier(state),
        parent
    ).apply {
        setContent(content)
    }
}

@Immutable
interface ImageMapping {
    val name: String
    val resource: DrawableResource
}

@Composable
private fun ImageMapping.Composable(mapStyle: InternalMapStyle) {
    val image = imageResource(resource)
    DisposableEffect(image) {
        mapStyle.addImage(name = name, image)
        onDispose {
            mapStyle.removeImage(name)
        }
    }
}

@Composable
private fun DrawableToMapStyleImageProvider(
    mapStyle: InternalMapStyle,
    list: List<ImageMapping>,
) {
    key(list) {
        for (item in list) {
            item.Composable(mapStyle)
        }
    }
}

@Composable
fun ComposeMap(
    modifier: Modifier,
    uiSettings: MapUiSettings,
    mapState: MapState,
    styleUrl: String,
    imageContainer: List<ImageMapping> = emptyList(),
    content: @Composable () -> Unit
) {
    val parentComposition = rememberCompositionContext()
    val currentContent by rememberUpdatedState(content)
    val style = mapState.style
    LaunchedEffect(style) {
        if (style != null) {
            disposingComposition {
                newComposition(parentComposition, mapState) {
                    currentContent()
                }
            }
        }
    }

    if (style != null) {
        DrawableToMapStyleImageProvider(style as InternalMapStyle, imageContainer)
    }

    LaunchedEffect(mapState, styleUrl, mapState.commandHandle) {
        val handle = mapState.commandHandle ?: return@LaunchedEffect
        mapState.onMapLoadFlow.collectLatest {
            println("loading style: $styleUrl")
            handle.asInternal.loadStyleUri(styleUrl)
        }
    }

  
        Box(Modifier.matchParentSize()) {
            NativeComposeMap(Modifier.fillMaxSize(), uiSettings, mapState)
        }
    
}


@Composable
internal expect fun NativeComposeMap(
    modifier: Modifier,
    uiSettings: MapUiSettings,
    mapState: MapState,
)
