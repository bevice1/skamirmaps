
package sk.amir.maps.shapes

import org.maplibre.geojson.Geometry

internal interface MapGeometryWithShape<S: Geometry> : MapGeometry {
    val nShape: S
}
