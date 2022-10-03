package dendroDrawer

import org.jetbrains.letsPlot.geom.geomStep

/**
 * Enum class presents the direction of a child branch of tree: left or right.
 *
 * @param parameter The direction parameter of [geomStep].
 */
internal enum class BranchDirection(val parameter: String) {
    LEFT(parameter = "vh"),
    RIGHT(parameter = "hv")
}