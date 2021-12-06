package Program.figures;


import Program.Node;

public class Queen implements Figure{ // ферзь(королева)

    private Node node;

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return "Queen"/* + node.toString()*/;
    }
}