package Program.services;

import Program.*;
import Program.figures.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoveService {

    public MoveService() {
    }

    /**
     * ход игрока
     * @param players
     * @param current
     * @return null, если нет возможных ходов (пат или мат)
     */
    public void move(FigureService fs, Game g, Player[] players, Player current) {
        // получаем фигуры игрока
        List<Figure> currentFigures = current.getFigures();
        // получаем фигуры соперников игрока
        List<Figure> contendersFigures = getAllFiguresOfContenders(players, current);
        // получаем фигуры союзников игрока
        List<Figure> alliesFigures = getAllFiguresOfAllies(players, current);

        // создаём итоговый ход игрока
        Move move = null;

        Figure figure;
        // если игроку поставлен шах
        if (haveCheck(players, current)) {
            // находим короля
            figure = searchKingInFigures(currentFigures);
            // будем делать ход королём
        } else {
            // берём случайную фигуру
            int number = (int) (currentFigures.size() * Math.random());
            figure = currentFigures.get(number);
            //будем делать ход этой фигурой
        }
        move = moveOfFigure(fs, contendersFigures, alliesFigures, figure, current);
        // совершаем ход фигурой и сохраняем его
        makingMove(g, move, contendersFigures);
    }

    public Move moveOfFigure(FigureService fs,
            List<Figure> contFigures, List<Figure> alliesFigures, Figure figure, Player current) {
        // находим все варианты хода этой фигурой
        List<Node> variablesForMove = searchAllVariablesForMove(fs, contFigures, alliesFigures, figure);
        // случайным образом выбираем один из них
        int numberOfEndNode = (int) (variablesForMove.size() * Math.random());
        Node endNode = variablesForMove.get(numberOfEndNode);
        // возвращаем его
        return new Move(figure.getNode(), endNode, current, figure);
    }

    public List<Node> searchAllVariablesForMove(FigureService fs,
            List<Figure> contFigures, List<Figure> alliesFigures, Figure figure) {
        List<Node> variables = null;

        SearchNodesService sns = new SearchNodesService();
        if (Tower.class.equals(figure.getClass())) {
            // ладья
            variables = fs.variablesOfTower(sns, contFigures, alliesFigures, figure.getNode());
        } else if (Queen.class.equals(figure.getClass())) {
            // ферзь
            variables = fs.variablesOfQueen(sns, contFigures, alliesFigures, figure.getNode());
        } else if (King.class.equals(figure.getClass())) {
            // король
            variables = fs.variablesOfKing(sns, contFigures, alliesFigures, figure.getNode());
        } else if (Horse.class.equals(figure.getClass())) {
            // конь
            variables = fs.variablesOfHorse(sns, contFigures, alliesFigures, figure.getNode());
        } else if (Elephant.class.equals(figure.getClass())) {
            // слон
            variables = fs.variablesOfElephant(sns, contFigures, alliesFigures, figure.getNode());
        } else if (Pawn.class.equals(figure.getClass())) {
            // пешка
            variables = fs.variablesOfPawn(sns, contFigures, alliesFigures, figure.getNode());
        }
        return variables;
    }

    /**
     * поиск короля в списке фигур
     * @param figures
     * @return
     */
    public Figure searchKingInFigures(List<Figure> figures) {
        // перебираем фигуры
        for (Figure f : figures) {
            // если текущая фигура - король
            if (f.getClass() == King.class) {
                // то возвращаем её
                return f;
            }
        }
        // если короля нет - возвращаем null
        return null;
    }

    public boolean haveCheck(Player[] players, Player current) {
        return false;
    }

    /**
     * получение всех фигур соперников игрока
     * @param players
     * @param current
     * @return
     */
    public List<Figure> getAllFiguresOfContenders(Player[] players, Player current) {
        boolean isEven = isEvenPlayer(players, current); // четность номера текущего игрока

        // создаём список фигур соперников
        List<Figure> contendersFigures = new ArrayList<>();
        for (int i = 0; i < players.length; i++) {
            // если четность текущего игрока не совпадает с чётностью i-го
            if (!isEven == (i % 2 == 0)) {
                // то они соперники,
                // добавляем все фигуры i-го игрока в список
                contendersFigures.addAll(players[i].getFigures());
            }
        }

        // возращаем полученный список
        return contendersFigures;
    }

    /**
     * получение всех фигур союзников игрока
     * @param players
     * @param current
     * @return
     */
    public List<Figure> getAllFiguresOfAllies(Player[] players, Player current) {
        boolean isEven = isEvenPlayer(players, current); // четность номера текущего игрока

        // создаём список фигур соперников
        List<Figure> alliesFigures = new ArrayList<>();
        for (int i = 0; i < players.length; i++) {
            // если четность текущего игрока совпадает с чётностью i-го
            if (isEven == (i % 2 == 0)) {
                // то они союзники,
                // добавляем все фигуры i-го игрока в список
                alliesFigures.addAll(players[i].getFigures());
            }
        }

        // возращаем полученный список
        return alliesFigures;
    }

    /**
     * определение четности номера игрока
     * @param players
     * @param current
     * @return
     */
    public boolean isEvenPlayer(Player[] players, Player current) {
        // находим игрока в списке
        for (int i = 0; i < players.length; i++) {
            if (players[i] == current) {
                // определяем четность его номера
                return (i % 2 == 0);
                // P.S: четность - четный индекс игрока в списке игроков или нечётный
            }
        }
        return false;
    }

    /**
     * совершение хода
     * @param g
     * @param move
     */
    public void makingMove(Game g, Move move, List<Figure> contendersFigures) {
        // получаем список произошедших ходов
        List<Move> moves = g.getMoves();
        // если текущий ход существует (!=null)
        if (move != null) {
            // получаем фигуру, находящуюся в конечном узле хода
            Figure figureInEnd = searchFigureOfNodeInList(contendersFigures, move.getEnd());
            // если она есть
            if (figureInEnd != null) {
                removeFigureFromBoard(g, figureInEnd);
                // удаляем фигуру у игрока
            }
            // переставляем фигуру в конечную точку хода
            Figure f = move.getFigure();
            f.setNode(move.getEnd());
            // сохраняем ход
            moves.add(move);
            g.setMoves(moves);
        }
    }

    /**
     * поиск вражеской фигуры в узле
     * @param figures
     * @param n
     * @return
     */
    public Figure searchFigureOfNodeInList(List<Figure> figures, Node n) {
        // перебираем фигуры
        for (Figure f : figures) {

            // если узел в котором находится фигура является искомым
            if (f.getNode() == n) {
                // возвращаем эту фигуру
                return f;
            }
        }

        // если не нашли, значит узел пустой
        return null;
    }

    /**
     * удаление фигуры с доски (съедание)
     * @param g
     * @param figure
     */
    public void removeFigureFromBoard(Game g, Figure figure) {
        // перебирем всех игроков
        for (Player p : g.getPlayers()) {
            // получаем фигуры игрока
            List<Figure> figures = p.getFigures();
            // перебираем их
            for (Figure f : figures) {
                // если это удаляемая фигура
                if (f.equals(figure)) {
                    // удаляем её из фигур игрока
                    figures.remove(figure);
                    p.setFigures(figures);
                    return;
                }
            }
        }
    }
}
