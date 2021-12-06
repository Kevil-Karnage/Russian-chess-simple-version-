package Program.test;

import Program.*;
import Program.figures.Figure;
import Program.services.BoardService;
import Program.services.FigureService;
import Program.services.GameService;
import Program.services.MoveService;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Game g = new Game();
        GameService gs = new GameService();
        BoardService bs = new BoardService();
        FigureService fs = new FigureService();
        MoveService ms = new MoveService();

        gs.addPlayersToGame(g);
        gs.generatingBoard(g, bs, fs);
        Board b = g.getBoard();

        Player player = g.getPlayers()[0];
        Figure f = player.getFigures().get(0);
        Move move = new Move(f.getNode(), f.getNode().getUp(), player, f);
        List<Figure> contendersFigures = ms.getAllFiguresOfContenders(g.getPlayers(), player);
        System.out.print("Фигуры соперников: ");
        for (Figure figure :
                contendersFigures) {
            System.out.print(figure.toString() + " ");
        }

        g.setMoves(new ArrayList<>());
        ms.makingMove(g, move, contendersFigures);

        System.out.println("\n");
    }
}
