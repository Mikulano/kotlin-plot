package dendroDrawer

import dendroDrawer.NodeBuilder.Companion.constructChildren

/**
 * The tree node.
 *
 * @param verticalOffset The offset by which the node will be drawn vertically downwards.
 * @param data The metadata that stored in the node as the map of any data.
 * @param children The list of children nodes.
 */
class Node internal constructor(
    val verticalOffset: Double,
    val data: Map<Any, Any?>,
    val children: List<Node>
) {

    constructor(
        verticalOffset: Double,
        data: Map<Any, Any?>,
        block: (NodeBuilder.() -> Unit)? = null
    ) : this(verticalOffset, data, constructChildren(block))
}