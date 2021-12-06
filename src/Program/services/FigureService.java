package Program.services;

import Program.Game;
import Program.Node;
import Program.Player;
import Program.figures.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FigureService {

    public FigureService() {}

    public Map<FigureInfo, Figure> getFiguresInfoOfAllFigures(Game g) {
        Player[] players = g.getPlayers();
        Map<FigureInfo, Figure> figuresInfo = new HashMap<>();

        for (int i = 0; i < players.length; i++) {
            addQueenCoordinates(i, players[i], figuresInfo); // ферзи
            addTowersCoordinates(i, players[i], figuresInfo); // ладьи
            addElephantsCoordinates(i, players[i], figuresInfo); // слонов
            addHorsesCoordinates(i, players[i], figuresInfo); // коней
            addPawnsCoordinates(i, players[i], figuresInfo); // пешки
            addKingsCoordinates(i, players[i], figuresInfo); // короля
        }
        return figuresInfo;
    }

    /**
     * добавление координат ферзей в список координат фигур
     * @param number номер игрока
     * @param p игрок
     * @param figuresInfo список координат фигур
     */
    private void addQueenCoordinates(int number, Player p, Map<FigureInfo, Figure> figuresInfo) {
        FigureInfo current = null;

        switch (number) {
            case 0 -> current = new FigureInfo('i', 13, p); // верхний
            case 1 -> current = new FigureInfo('n', 7, p);  // правый
            case 2 -> current = new FigureInfo('h', 2, p);  // нижний
            case 3 -> current = new FigureInfo('c', 8, p);  // левый
        }
        figuresInfo.put(current, new Queen());
    }

    /**
     * добавление координат ладей в список координат фигур
     * @param number номер игрока
     * @param p игрок
     * @param figuresInfo список координат фигур
     */
    private void addTowersCoordinates(int number, Player p, Map<FigureInfo, Figure> figuresInfo) {
        List<FigureInfo> currents = new ArrayList<>();


        switch (number) {
            case 0 -> { // верхний
                currents.add(new FigureInfo( 'a', 12, p));
                currents.add(new FigureInfo( 'e', 13, p));
                currents.add(new FigureInfo( 'l', 13, p));
            }
            case 1 -> { // правый
                currents.add(new FigureInfo( 'm', 15, p));
                currents.add(new FigureInfo( 'n', 4, p));
                currents.add(new FigureInfo( 'n', 11, p));
            }
            case 2 -> { // нижний
                currents.add(new FigureInfo( 'p', 3, p));
                currents.add(new FigureInfo( 'e', 2, p));
                currents.add(new FigureInfo( 'l', 2, p));
            }
            case 3 -> { // левый
                currents.add(new FigureInfo( 'd', 0, p));
                currents.add(new FigureInfo( 'c', 4, p));
                currents.add(new FigureInfo( 'c', 11, p));
            }
        }
        for (FigureInfo current :
                currents) {
            figuresInfo.put(current, new Tower());
        }
    }

    /**
     * добавление координат слонов в список координат фигур
     * @param number номер игрока
     * @param p игрок
     * @param figuresInfo список координат фигур
     */
    private void addElephantsCoordinates(int number, Player p, Map<FigureInfo, Figure> figuresInfo) {
        List<FigureInfo> currents = new ArrayList<>();

        switch (number) {
            case 0 -> { // верхний
                currents.add(new FigureInfo( 'b', 15, p));
                currents.add(new FigureInfo( 'g', 13, p));
                currents.add(new FigureInfo( 'j', 13, p));
            }
            case 1 -> { // правый
                currents.add(new FigureInfo( 'p', 14, p));
                currents.add(new FigureInfo( 'n', 6, p));
                currents.add(new FigureInfo( 'n', 9, p));
            }
            case 2 -> { // нижний
                currents.add(new FigureInfo( 'o', 0, p));
                currents.add(new FigureInfo( 'g', 2, p));
                currents.add(new FigureInfo( 'j', 2, p));
            }
            case 3 -> { // левый
                currents.add(new FigureInfo( 'd', 1, p));
                currents.add(new FigureInfo( 'c', 6, p));
                currents.add(new FigureInfo( 'c', 9, p));
            }
        }
        for (FigureInfo current : currents) {
            figuresInfo.put(current, new Elephant());
        }
    }

    /**
     * добавление координат коней в список координат фигур
     * @param number номер игрока
     * @param p игрок
     * @param figuresInfo список координат фигур
     */
    private void addHorsesCoordinates(int number, Player p, Map<FigureInfo, Figure> figuresInfo) {
        List<FigureInfo> currents = new ArrayList<>();

        switch (number) {
            case 0 -> { // верхний
                currents.add(new FigureInfo( 'c', 13, p));
                currents.add(new FigureInfo( 'f', 13, p));
                currents.add(new FigureInfo( 'k', 13, p));
            }
            case 1 -> { // правый
                currents.add(new FigureInfo( 'n', 13, p));
                currents.add(new FigureInfo( 'n', 5, p));
                currents.add(new FigureInfo( 'n', 10, p));
            }
            case 2 -> { // нижний
                currents.add(new FigureInfo( 'n', 2, p));
                currents.add(new FigureInfo( 'f', 2, p));
                currents.add(new FigureInfo( 'k', 2, p));
            }
            case 3 -> { // левый
                currents.add(new FigureInfo( 'c', 2, p));
                currents.add(new FigureInfo( 'c', 5, p));
                currents.add(new FigureInfo( 'c', 10, p));
            }
        }
        for (FigureInfo current : currents) {
            figuresInfo.put(current, new Horse());
        }
    }

    /**
     * добавление координат пешек в список координат фигур
     * @param number номер игрока
     * @param p игрок
     * @param coordinates список координат фигур
     */
    private void addPawnsCoordinates(int number, Player p, Map<FigureInfo, Figure> coordinates) {
        List<FigureInfo> currents = new ArrayList<>();


        switch (number) {
            case 0 -> { // верхний
                for (char symb = 'e'; symb <= 'l'; symb++) {
                    currents.add(new FigureInfo( symb, 12, p));
                }
            }
            case 1 -> { // правый
                for (int numb = 4; numb <= 11; numb++) {
                    currents.add(new FigureInfo( 'm', numb, p));
                }
            }
            case 2 -> { // нижний
                for (char symb = 'e'; symb <= 'l'; symb++) {
                    currents.add(new FigureInfo( symb, 3, p));
                }
            }
            case 3 -> { // левый
                for (int numb = 4; numb <= 11; numb++) {
                    currents.add(new FigureInfo( 'd', numb, p));
                }
            }
        }
        for (FigureInfo current : currents) {
            coordinates.put(current, new Pawn(number));
        }
    }

    /**
     * добавление координат королей в список координат фигур
     * @param number номер игрока
     * @param p игрок
     * @param figuresInfo список координат фигур
     */
    private void addKingsCoordinates(int number, Player p, Map<FigureInfo, Figure> figuresInfo) {
        List<FigureInfo> currents = new ArrayList<>();

        switch (number) {
            case 0 -> currents.add(new FigureInfo('h', 13, p)); // верхний
            case 1 -> currents.add(new FigureInfo('n', 8, p));  // правый
            case 2 -> currents.add(new FigureInfo('i', 2, p));  // нижний
            case 3 -> currents.add(new FigureInfo('c', 7, p));  // левый
        }
        for (FigureInfo current : currents) {
            figuresInfo.put(current, new King());
        }
    }


    /**
     * получение всех вариантов хода ладьи
      * @param contFigures
     * @param alliesFigures
     * @param node
     * @return
     */
    public List<Node> variablesOfTower (
            SearchNodesService sns, List<Figure> contFigures, List<Figure> alliesFigures, Node node) {
        List<Node> variables = new ArrayList<>();

        sns.searchUpNodes(variables, contFigures, alliesFigures, node);
        sns.searchRightNodes(variables, contFigures, alliesFigures, node);
        sns.searchDownNodes(variables, contFigures, alliesFigures, node);
        sns.searchLeftNodes(variables, contFigures, alliesFigures, node);

        return variables;
    }

    /**
     * получение всех вариантов хода слона
     * @param contFigures
     * @param alliesFigures
     * @param node
     * @return
     */
    public List<Node> variablesOfElephant(
            SearchNodesService sns, List<Figure> contFigures, List<Figure> alliesFigures, Node node) {
        List<Node> variables = new ArrayList<>();

        sns.searchRightUpNodes(variables, contFigures, alliesFigures, node);
        sns.searchRightDownNodes(variables, contFigures, alliesFigures, node);
        sns.searchLeftDownNodes(variables, contFigures, alliesFigures, node);
        sns.searchLeftUpNodes(variables, contFigures, alliesFigures, node);

        return variables;
    }

    /**
     * получение всех вариантов хода ферзя
     * @param contFigures
     * @param alliesFigures
     * @param node
     * @return
     */
    public List<Node> variablesOfQueen(
            SearchNodesService sns, List<Figure> contFigures, List<Figure> alliesFigures, Node node) {
        List<Node> variables = new ArrayList<>();
        // т.к. ферзь по сути = ладья + слон

        // добавляем все ходы ладьи из той же точки
        variables.addAll(variablesOfTower(sns, contFigures, alliesFigures, node));
        // добавляем все ходы слона из той же точки
        variables.addAll(variablesOfElephant(sns, contFigures, alliesFigures, node));

        return variables;
    }

    public List<Node> variablesOfHorse(
            SearchNodesService sns, List<Figure> contFigures, List<Figure> alliesFigures, Node node) {
        List<Node> variables = new ArrayList<>();
        return variables;
    }

    public List<Node> variablesOfKing(
            SearchNodesService sns, List<Figure> contFigures, List<Figure> alliesFigures, Node node) {
        List<Node> variables = new ArrayList<>();
        return variables;
    }

    public List<Node> variablesOfPawn(
            SearchNodesService sns, List<Figure> contFigures, List<Figure> alliesFigures, Node node) {
        List<Node> variables = new ArrayList<>();
        return variables;
    }
}