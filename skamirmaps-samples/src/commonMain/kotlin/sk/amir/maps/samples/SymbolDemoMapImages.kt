
package sk.amir.maps.samples

import org.jetbrains.compose.resources.DrawableResource
import sk.amir.maps.ImageMapping

internal enum class SymbolDemoMapImages(
    override val resource: DrawableResource
) : ImageMapping {
    Marker(Res.drawable.marker),
    ;
}

internal enum class SymbolDemoMapImagesQuestion(
    override val resource: DrawableResource
) : ImageMapping {
    Marker(Res.drawable.marker_green),
    ;
}
