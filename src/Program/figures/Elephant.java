package Program.figures;


import Program.Node;

public class Elephant implements Figure{ // слон (офицер)
    private Node node;

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return "Elephant"/* + node.toString()*/;
    }
}