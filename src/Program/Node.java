package Program;


public class Node {
    private final char symbol;    // x-координата (от 'a' до 'p')
    private final int number;     // y-координата (от 0(1) до 15(16))

    // соседние узлы
    private Node left;
    private Node right;
    private Node up;
    private Node down;


    public Node(char symbol, int number) {
        this.symbol = symbol;
        this.number = number;

        left = null;
        right = null;
        up = null;
        down = null;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getNumber() {
        return number;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getUp() {
        return up;
    }

    public void setUp(Node up) {
        this.up = up;
    }

    public Node getDown() {
        return down;
    }

    public void setDown(Node down) {
        this.down = down;
    }

    public String toString() {
        return "" + symbol + number;
    }
}