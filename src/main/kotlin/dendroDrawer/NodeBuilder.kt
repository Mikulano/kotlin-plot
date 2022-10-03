package dendroDrawer

/**
 * The Builder of [Node] properties.
 */
class NodeBuilder {
    val children: MutableList<Node> = mutableListOf()

    private fun addChild(node: Node) {
        children.add(node)
    }

    fun Node(verticalOffset: Double, data: Map<Any, Any?>, block: (NodeBuilder.() -> Unit)? = null) {
        addChild(Node(verticalOffset, data, constructChildren(block)))
    }

    companion object {

        internal fun constructChildren(block: (NodeBuilder.() -> Unit)?): MutableList<Node> {
            val builder = NodeBuilder()
            block?.let { init -> builder.init() }

            return builder.children
        }
    }
}