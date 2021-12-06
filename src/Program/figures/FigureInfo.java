package Program.figures;

import Program.Player;

public class FigureInfo {
    int number;
    char symbol;
    Player player;

    public FigureInfo(char symbol, int number, Player player) {
        this.number = number;
        this.symbol = symbol;
        this.player = player;
    }

    public FigureInfo(int number, char symbol) {
        this.number = number;
        this.symbol = symbol;
    }

    public String toString() {
        return "" + number + symbol + " player " + player.toString();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
