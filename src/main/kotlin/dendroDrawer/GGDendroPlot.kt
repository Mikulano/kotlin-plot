package dendroDrawer

import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.geom.geomStep
import org.jetbrains.letsPlot.intern.Feature
import org.jetbrains.letsPlot.intern.Plot
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.themes.theme

/**
 * Class presents the plot maker of the tree structure.
 *
 * @param tree The main [Node] of a tree.
 * @param block The initializer for plot properties implemented as builder (see [GGDendroPlotBuilder])].
 */
class GGDendroPlot(
    tree: Node,
    block: (GGDendroPlotBuilder.() -> Unit)? = null
) {

    val plot: Plot
        get() {
            val data = mapOf<String, Any>(
                X_AXIS_NAME to xCoordinates,
                Y_AXIS_NAME to yCoordinates,
                (colorField ?: DEFAULT_COLOR_NAME) to colors
            )

            return letsPlot(data) + geomBranches + geomPoints + plotTheme
        }

    private val colorField: String?

    private val xCoordinates = mutableListOf<Double>()
    private val yCoordinates = mutableListOf<Double>()
    private val colors: Colors = mutableListOf()

    private val features = mutableListOf<Feature>()
    private val geomBranches
        get() = features.reduce { first, second -> first + second }

    private val geomPoints
        get() = geomPoint(
            size = POINT_SIZE
        ) {
            x = X_AXIS_NAME
            y = Y_AXIS_NAME
            color = colorField
        }

    private val plotTheme = theme(
        axis = "blank",
        line = "blank"
    )

    init {
        colorField = block?.let { init ->
            val dendroPlotBuilder = GGDendroPlotBuilder()
            dendroPlotBuilder.init()

            dendroPlotBuilder.color
        }
        handleNode(node = tree, position = PositionContext())
    }

    private fun handleNode(node: Node, position: PositionContext) {
        xCoordinates.add(position.xCoordinate)
        yCoordinates.add(position.yCoordinate)
        colors.addColorFromData(node.data)

        val childCount = node.children.count()
        node.children.forEachIndexed { index, child ->
            child.handleBranch(position = position, childNumber = index, childCount = childCount)
        }
    }

    private fun Node.handleBranch(position: PositionContext, childNumber: Int, childCount: Int) {
        val childPosition = position.getChildPosition(childNumber, childCount, verticalOffset)

        features.addBranch(
            nodePosition = position,
            nextPosition = childPosition,
            direction = position.getChildDirection(childPosition)
        )
        handleNode(
            node = this,
            position = childPosition
        )
    }

    private fun MutableList<Feature>.addBranch(
        nodePosition: PositionContext,
        nextPosition: PositionContext,
        direction: BranchDirection
    ) {
        add(
            geomStep(direction = direction.parameter, color = BRANCH_COLOR) {
                x = listOf(nodePosition.xCoordinate, nextPosition.xCoordinate)
                y = listOf(nodePosition.yCoordinate, nextPosition.yCoordinate)
            }
        )
    }

    private fun Colors.addColorFromData(data: Map<Any, Any?>) {
        if (colorField != null && data.containsKey(colorField)) {
            add(data[colorField])
        } else {
            add(DEFAULT_COLOR_FIELD)
        }
    }

    private companion object {

        const val X_AXIS_NAME = "x"
        const val Y_AXIS_NAME = "y"
        const val BRANCH_COLOR = "gray"
        const val POINT_SIZE = 4.0
        const val DEFAULT_COLOR_NAME = "color"
        const val DEFAULT_COLOR_FIELD = "none"
    }
}

private typealias Colors = MutableList<Any?>
