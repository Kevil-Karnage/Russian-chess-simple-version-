package Program;

public class Board { // фактически - реализация графа поля
    Node main; // левый нижний угол (через него можно попасть в любой другой узел)

    public Node getMain() {
        return main;
    }

    public void setMain(Node main) {
        this.main = main;
    }
}