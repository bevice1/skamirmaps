
package sk.amir.maps.common

/**
 * Builder class for composing [MapShapeSourceOptions] objects.
 *
 * @see [The online documentation](https://maplibre.org/maplibre-style-spec/.sources-geojson)
 */
internal class MapShapeSourceOptions : MutableMap<String, Any> by HashMap() {
    /**
     * Minimum zoom level at which to create vector tiles (lower means more field of view detail at low zoom levels).
     *
     * @param minZoom the minimum zoom - Defaults to 0.
     * @return the current instance for chaining
     */
    fun withMinZoom(minZoom: Int): MapShapeSourceOptions = apply {
        this[MIN_ZOOM] = minZoom
    }

    /**
     * Maximum zoom level at which to create vector tiles (higher means greater detail at high zoom levels).
     *
     * @param maxZoom the maximum zoom - Defaults to 25.5
     * @return the current instance for chaining
     */
    fun withMaxZoom(maxZoom: Int): MapShapeSourceOptions = apply {
        this[MAX_ZOOM] = maxZoom
    }

    /**
     * Tile buffer size on each side (measured in 1/512ths of a tile; higher means fewer rendering artifacts near tile
     * edges but slower performance).
     *
     * @param buffer the buffer size - Defaults to 128.
     */
    fun withBuffer(buffer: Int): MapShapeSourceOptions = apply {
        this[BUFFER] = buffer
    }

    /**
     * Initialises whether to calculate addLine distance metrics.
     *
     * @param lineMetrics true to calculate addLine distance metrics.
     */
    fun withLineMetrics(lineMetrics: Boolean): MapShapeSourceOptions = apply {
        this[LINE_METRICS] = lineMetrics
    }

    /**
     * Douglas-Peucker simplification tolerance (higher means simpler geometries and faster performance).
     *
     * @param tolerance the tolerance - Defaults to 0.375
     */
    fun withTolerance(tolerance: Float): MapShapeSourceOptions = apply {
        this[TOLERANCE] = tolerance
    }

    /**
     * If the data is a collection of addPoint features, setting this to true clusters the points by radius into groups.
     *
     * @param cluster cluster? - Defaults to false
     */
    fun withCluster(cluster: Boolean): MapShapeSourceOptions = apply {
        this[CLUSTER] = cluster
    }

    /**
     * Max zoom to cluster points on.
     *
     * @param clusterMaxZoom clusterMaxZoom cluster maximum zoom - Defaults to one zoom less than maxzoom (so that last
     * zoom features are not clustered)
     */
    fun withClusterMaxZoom(clusterMaxZoom: Int): MapShapeSourceOptions = apply {
        this[CLUSTER_MAX_ZOOM] = clusterMaxZoom
    }

    /**
     * Radius of each cluster when clustering points, measured in 1/512ths of a tile.
     *
     * @param clusterRadius cluster radius - Defaults to 50
     */
    fun withClusterRadius(clusterRadius: Int): MapShapeSourceOptions = apply {
        this[CLUSTER_RADIUS] = clusterRadius
    }

    /**
     * An object defining custom properties on the generated clusters if clustering is enabled,
     * aggregating values from clustered points. Has the form {"property_name": [operator, [map_expression]]} or
     * {"property_name": [[operator, accumulated, expression], [map_expression]]}
     *
     * @param propertyName name of the property
     * @param operatorExpr operatorExpr is any expression function that accepts at least 2 operands (e.g. "+" or "max").
     * It accumulates the property value from clusters/points the cluster contains. It can either be
     * a literal with single operator, or be a valid expression
     * @param mapExpr map expression produces the value of a single addPoint, it shall be a valid expression
     * @return the current instance for chaining
     */
    fun withClusterProperty(propertyName: String, operatorExpr: Ex, mapExpr: Ex): MapShapeSourceOptions = apply {
        val properties: HashMap<String, Pair<Ex, Ex>> = getClusterProperties()
        properties[propertyName] = operatorExpr to mapExpr
        this[CLUSTER_PROPERTIES] = properties
    }

    internal fun getClusterProperties(): HashMap<String, Pair<Ex, Ex>> = (this[CLUSTER_PROPERTIES] as? HashMap<String, Pair<Ex, Ex>>?) ?: HashMap()

    companion object {
        internal const val CLUSTER_RADIUS = "clusterRadius"
        internal const val CLUSTER_MAX_ZOOM = "clusterMaxZoom"
        internal const val CLUSTER_PROPERTIES = "clusterProperties"
        internal const val CLUSTER = "cluster"
        internal const val TOLERANCE = "tolerance"
        internal const val BUFFER = "buffer"
        internal const val LINE_METRICS = "lineMetrics"
        internal const val MAX_ZOOM = "maxzoom"
        internal const val MIN_ZOOM = "minzoom"
    }
}

