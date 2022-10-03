package dendroDrawer

import org.jetbrains.letsPlot.export.ggsave
import kotlin.test.Test

class GGDendroPlotTests {

    @Test
    fun testBinaryTree() {
        val tree = Node(verticalOffset = 0.0, data = mapOf("isotype" to "igg")) {
            Node(verticalOffset = 1.0, data = mapOf("isotype" to "igg")) {
                Node(verticalOffset = 1.5, data = mapOf("isotype" to "igm", "treatment" to "A"))
                Node(verticalOffset = 3.5, data = mapOf("isotype" to "iga", "treatment" to "A"))
            }
            Node(verticalOffset = 2.0, data = mapOf("isotype" to "iga")) {
                Node(verticalOffset = 4.0, data = mapOf("isotype" to "igm", "treatment" to "B"))
                Node(verticalOffset = 4.0, data = mapOf("isotype" to "igm", "treatment" to "C"))
            }
        }

        val dendroPlot = GGDendroPlot(tree = tree) {
            color = "isotype"
        }

        ggsave(dendroPlot.plot, "binary-tree.png")
    }

    @Test
    fun testUnaryTree() {
        val tree = Node(verticalOffset = 0.0, data = mapOf("isotype" to "igg")) {
            Node(verticalOffset = 1.0, data = mapOf("isotype" to "igg")) {
                Node(verticalOffset = 1.5, data = mapOf("isotype" to "igm", "treatment" to "A")) {
                    Node(verticalOffset = 1.5, data = mapOf("isotype" to "igg", "treatment" to "A"))
                }
            }
        }

        val dendroPlot = GGDendroPlot(tree = tree) {
            color = "isotype"
        }

        ggsave(dendroPlot.plot, "unary-tree.png")
    }

    @Test
    fun testTripleTree() {
        val tree = Node(verticalOffset = 0.0, data = mapOf("isotype" to "igg")) {
            Node(verticalOffset = 1.0, data = mapOf("isotype" to "igg"))
            Node(verticalOffset = 2.0, data = mapOf("isotype" to "igm"))
            Node(verticalOffset = 3.0, data = mapOf("isotype" to "igg")) {
                Node(verticalOffset = 4.0, data = mapOf("isotype" to "igg"))
                Node(verticalOffset = 5.0, data = mapOf("isotype" to "iga"))
                Node(verticalOffset = 6.0, data = mapOf("isotype" to "iga"))
            }
        }

        val dendroPlot = GGDendroPlot(tree = tree) {
            color = "isotype"
        }

        ggsave(dendroPlot.plot, "triple-tree.png")
    }

    @Test
    fun testMultipleTree() {
        val tree = Node(verticalOffset = 0.0, data = mapOf("isotype" to "igg")) {
            Node(verticalOffset = 1.0, data = mapOf("isotype" to "igg"))
            Node(verticalOffset = 2.0, data = mapOf("isotype" to "igm"))
            Node(verticalOffset = 2.0, data = mapOf("isotype" to "igm"))
            Node(verticalOffset = 2.0, data = mapOf("isotype" to "igm"))
            Node(verticalOffset = 2.0, data = mapOf("isotype" to "igm"))
            Node(verticalOffset = 2.0, data = mapOf("isotype" to "igm"))
            Node(verticalOffset = 3.0, data = mapOf("isotype" to "igg")) {
                Node(verticalOffset = 4.0, data = mapOf("isotype" to "igg"))
                Node(verticalOffset = 5.0, data = mapOf("isotype" to "iga"))
                Node(verticalOffset = 6.0, data = mapOf("isotype" to "iga"))
                Node(verticalOffset = 2.0, data = mapOf("isotype" to "igm"))
                Node(verticalOffset = 2.0, data = mapOf("isotype" to "igm"))
                Node(verticalOffset = 7.0, data = mapOf("isotype" to "igm")) {
                    Node(verticalOffset = 3.0, data = mapOf("isotype" to "igm"))
                    Node(verticalOffset = 3.0, data = mapOf("isotype" to "igm"))
                    Node(verticalOffset = 3.0, data = mapOf("isotype" to "igm"))
                    Node(verticalOffset = 3.0, data = mapOf("isotype" to "igm"))
                    Node(verticalOffset = 3.0, data = mapOf("isotype" to "igm"))
                    Node(verticalOffset = 3.0, data = mapOf("isotype" to "igm"))
                    Node(verticalOffset = 5.0, data = mapOf("isotype" to "igm")) {
                        Node(verticalOffset = 4.0, data = mapOf("isotype" to "igm"))
                        Node(verticalOffset = 5.0, data = mapOf("isotype" to "igm"))
                        Node(verticalOffset = 4.0, data = mapOf("isotype" to "igm"))
                        Node(verticalOffset = 5.0, data = mapOf("isotype" to "igm"))
                    }
                }
                Node(verticalOffset = 2.0, data = mapOf("isotype" to "igm"))
                Node(verticalOffset = 2.0, data = mapOf("isotype" to "igm"))
                Node(verticalOffset = 2.0, data = mapOf("isotype" to "igm"))
                Node(verticalOffset = 2.0, data = mapOf("isotype" to "igm"))
            }
        }

        val dendroPlot = GGDendroPlot(tree = tree) {
            color = "isotype"
        }

        ggsave(dendroPlot.plot, "multiple-tree.png")
    }

    @Test
    fun testWithoutColorField() {
        val tree = Node(verticalOffset = 0.0, data = mapOf("isotype" to "igg")) {
            Node(verticalOffset = 1.0, data = mapOf("isotype" to "igg")) {
                Node(verticalOffset = 1.5, data = mapOf("isotype" to "igm", "treatment" to "A"))
                Node(verticalOffset = 3.5, data = mapOf("isotype" to "iga", "treatment" to "A"))
            }
            Node(verticalOffset = 2.0, data = mapOf("isotype" to "iga")) {
                Node(verticalOffset = 4.0, data = mapOf("isotype" to "igm", "treatment" to "B"))
                Node(verticalOffset = 4.0, data = mapOf("isotype" to "igm", "treatment" to "C"))
            }
        }

        val dendroPlot = GGDendroPlot(tree = tree)

        ggsave(dendroPlot.plot, "without-color-field.png")
    }

    @Test
    fun testWithoutColorNodes() {
        val tree = Node(verticalOffset = 0.0, data = mapOf("isotype" to "igg")) {
            Node(verticalOffset = 1.0, data = mapOf("isotype" to "igg")) {
                Node(verticalOffset = 1.5, data = mapOf("isotype" to "igm", "treatment" to "A"))
                Node(verticalOffset = 3.5, data = mapOf("isotype" to "iga", "treatment" to "A"))
            }
            Node(verticalOffset = 2.0, data = mapOf("isotype" to "iga")) {
                Node(verticalOffset = 4.0, data = mapOf("isotype" to "igm", "treatment" to "B"))
                Node(verticalOffset = 4.0, data = mapOf("isotype" to "igm", "treatment" to "C"))
            }
        }

        val dendroPlot = GGDendroPlot(tree = tree){
            color = "treatment"
        }

        ggsave(dendroPlot.plot, "without-color-nodes.png")
    }

    @Test
    fun testWrongColorField() {
        val tree = Node(verticalOffset = 0.0, data = mapOf("isotype" to "igg")) {
            Node(verticalOffset = 1.0, data = mapOf("isotype" to "igg")) {
                Node(verticalOffset = 1.5, data = mapOf("isotype" to "igm", "treatment" to "A"))
                Node(verticalOffset = 3.5, data = mapOf("isotype" to "iga", "treatment" to "A"))
            }
            Node(verticalOffset = 2.0, data = mapOf("isotype" to "iga")) {
                Node(verticalOffset = 4.0, data = mapOf("isotype" to "igm", "treatment" to "B"))
                Node(verticalOffset = 4.0, data = mapOf("isotype" to "igm", "treatment" to "C"))
            }
        }

        val dendroPlot = GGDendroPlot(tree = tree){
            color = "isotpe"
        }

        ggsave(dendroPlot.plot, "wrong-color-field.png")
    }
}
