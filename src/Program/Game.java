package Program;

import java.util.List;

public class Game {
    private Player[] players; // игроки попарно союзники: 1 и 3, 2 и 4ый
    private Board board;
    private List<Move> moves;

    public Game() {

    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
