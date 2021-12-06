package Program.figures;

import Program.Node;

public class Pawn implements Figure { // пешка (рядовой)
    private Node node;
    private PawnType type;

    /**
     * конструктор с добавлением type
     * @param type
     */
    public Pawn(int type) {
        this.type = PawnType.pawnTypeOf(type);
    }

    public Pawn() {
    }

    public PawnType getType() {
        return type;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return "Pawn"/* + node.toString()*/;
    }
}