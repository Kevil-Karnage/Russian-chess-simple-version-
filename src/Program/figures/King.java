package Program.figures;

import Program.Node;

// король
public class King implements Figure {

    private Node node;

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return "King"/* + node.toString()*/;
    }
}