package Program.figures;

import Program.Node;

public class Tower implements Figure { // ладья (башня)

    private Node node;

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return "Tower"/* + node.toString()*/;
    }
}