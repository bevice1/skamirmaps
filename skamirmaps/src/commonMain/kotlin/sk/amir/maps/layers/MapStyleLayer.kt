
package sk.amir.maps.layers

internal interface MapStyleLayer {
    val identifier: String
    var visible: Boolean
    var minZoomLevel: Float
    var maxZoomLevel: Float
}
