package dendroDrawer

/**
 * Data class presents the Node position in the plot.
 *
 * @param xCoordinate The x coordinate in the plot.
 * @param yCoordinate The y coordinate in the plot.
 * @param scale The scale which used for calculating child coordinate offsets.
 */
internal data class PositionContext(
    val xCoordinate: Double = 0.0,
    val yCoordinate: Double = 0.0,
    private val scale: Double = 1.0
) {

    fun getChildPosition(childNumber: Int, childCount: Int, yOffset: Double): PositionContext =
        PositionContext(
            xCoordinate = if (childCount > 1) {
                xCoordinate - scale + scale * childNumber * 2 / (childCount - 1)
            } else {
                xCoordinate
            },
            yCoordinate = yCoordinate - yOffset,
            scale = scale * SCALE_FACTOR
        )

    fun getChildDirection(childPosition: PositionContext): BranchDirection =
        if (childPosition.xCoordinate - xCoordinate < 0) {
            BranchDirection.LEFT
        } else {
            BranchDirection.RIGHT
        }


    private companion object {

        const val SCALE_FACTOR = 0.5
    }
}
