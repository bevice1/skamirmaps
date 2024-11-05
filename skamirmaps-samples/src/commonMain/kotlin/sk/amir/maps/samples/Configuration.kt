
package sk.amir.maps.samples

import sk.amir.maps.common.LatLng
import kotlin.random.Random

internal val PointNitra: LatLng = LatLng(48.3178495,18.0899431)
internal val PointNitra2: LatLng = LatLng(48.30978495,18.0799431)
internal val PointLondon: LatLng = LatLng(51.506786631771675, -0.12728422025646574)
internal val PolygonNitra: List<LatLng> = listOf(
    17.92658996792636 to 48.425811829379455,
    17.808446506151398 to 48.27073402436582,
    18.12429404535692 to 48.18656851455367,
    18.22864452262374 to 48.26887046410724,
    18.228607409577222 to 48.40563686243894,
    18.135281260742033 to 48.356408610252004,
    17.99248574332438 to 48.351060557181654,
    18.09134010194515 to 48.46228668269859,
    17.92658996792636 to 48.425811829379455,
)
    .map { LatLng(it.second, it.first) }
internal fun LatLng.Companion.getRandomPoints(
    random: Random,
    base: LatLng,
    span: Double,
    count: Int,
): List<LatLng> {
    return mutableListOf<LatLng>()
        .apply {
            repeat(count) {
                add(
                    LatLng(
                        latitude = base.latitude + random.nextDouble(-span, span),
                        longitude = base.longitude + random.nextDouble(-span, span)
                    )
                )
            }
        }
        .toList()
}

internal object StyleUrls {
    const val default = "https://demotiles.maplibre.org/style.json"
    const val dark = "https://demotiles.maplibre.org/styles/osm-bright-gl-style/style.json"
}
