package Program;

import Program.figures.Figure;

public class Move {
    private Node begin;
    private Node end;
    private Player player;
    private Figure figure;

    public Move(Node begin, Node end, Player player, Figure figure) {
        this.begin = begin;
        this.end = end;
        this.player = player;
        this.figure = figure;
    }

    public Node getBegin() {
        return begin;
    }

    public void setBegin(Node begin) {
        this.begin = begin;
    }

    public Node getEnd() {
        return end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }
}
