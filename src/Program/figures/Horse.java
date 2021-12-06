package Program.figures;

import Program.Node;

// конь (рыцарь)
public class Horse implements Figure {
    private Node node;

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return "Horse"/* + node.toString()*/;
    }
}