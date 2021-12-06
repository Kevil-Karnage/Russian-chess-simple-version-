package Program.services;

import Program.Board;
import Program.Game;
import Program.Node;
import Program.Player;
import Program.figures.FigureInfo;
import Program.figures.Figure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {

    public BoardService() {

    }

    /**
     * Создание и добавление всех узлов и их соединений на доску, а также фигур для игроков
     * @param
     */
    public void addAllNodesToBoard(Game g, FigureService fs) {
        // добавление всех узлов в поле(доску)

        // создаём список из узлов
        List<Node> allNodes = new ArrayList<>();
        // создаём все узлы
        for (int i = 0; i < 16; i++) {
            if (i < 2 || i >= 14) {
                // в 2 крайних верхних и нижних строках нет узлов в середине строки, от d до l
                allNodes = addNodes(allNodes, i, 'a', 'd');
                allNodes = addNodes(allNodes, i, 'm', 'p');
            } else if (i < 4 || i >= 12) {
                // в 3,4 и 13,14 рядах в наличии все узлы, поэтому добавляем все
                allNodes = addNodes(allNodes, i, 'a', 'p');
            } else {
                // во оставшихся 8ми рядах (5-12) нет первых и последних 2ух узлов,
                allNodes = addNodes(allNodes, i, 'c', 'n');
            }
        }
        // добавляем для каждого узла его соседей
        Map<FigureInfo, Figure> figuresInfo = fs.getFiguresInfoOfAllFigures(g);
        //printFigureInfo(figuresInfo);

        addAllEdgesToBoard(g, allNodes, figuresInfo);

        //printNodes(allNodes);
    }

    /**
     * добавление узлов заданного номера(y-координата) с определёнными символами(x-координата) в список,
     * включая конец
     * @param allNodes - список узлов
     * @param number - строка (y-координата)
     * @param from - начальный столбец (x-координата)
     * @param to - конечный столбец (x-координата)
     */
    private List<Node> addNodes(List<Node> allNodes, int number, char from, char to) {
        for (char i = from; i <= to; i++) {
            allNodes.add(new Node(i, number));
        }
        return allNodes;
    }

    /**
     * добавление и сохранение соединений узлов/ячеек доски в узлы
     * @param g игра
     * @param allNodes список узлов
     * @param figuresInfo информация о фигурах
     */
    public void addAllEdgesToBoard(Game g,
                                   List<Node> allNodes,
                                   Map<FigureInfo, Figure> figuresInfo) {
        // добавление всех рёбер в поле(доску)

        Map<Player, List<Figure>> playersFigures = new HashMap<>();

        for (Node n: allNodes) { // проходим по каждому узлу

            // ищем верхние и нижние узлы
            addEdgeToUp(allNodes, n);
            // ищем левый и правый узлы
            addEdgeToLeft(allNodes, n);
            // ставим фигуру, если координаты этого узла есть среди координат фигур
            searchNodeInCoordinates(figuresInfo, playersFigures, n);

        }

        // сохраняем доску
        Board b = g.getBoard();
        b.setMain(allNodes.get(0));
        System.out.println("Доска сгенерирована");

        // сохраняем фигуры игроков
        Player[] players = g.getPlayers();
        for (Player p : players) {
            p.setFigures(playersFigures.get(p));
        }
        System.out.println("Фигуры расставлены");
    }

    /**
     * проверка наличия текущего узла в информации о фигурах
     * @param figureInfo
     * @param figures
     * @param n
     */
    private void searchNodeInCoordinates(Map<FigureInfo, Figure> figureInfo,
                                         Map<Player, List<Figure>> figures,
                                         Node n) {
        // перебираем информацию о фигурах и сравниваем
        for (FigureInfo cAp : figureInfo.keySet()) {
            // если координаты совпадают
            if (cAp.getNumber() == n.getNumber() && cAp.getSymbol() == n.getSymbol()) {
                // то добавляем фигуре узел
                Figure f = figureInfo.get(cAp);
                f.setNode(n);

                // а фигуру добавляем игроку

                // лист с фигурами игрока, которому мы должны добавить эту фигуру
                List<Figure> playerFigure;

                // если уже добавляли фигуры (в мапе с фигурами игроков уже есть лист с фигурами)
                if (figures.containsKey(cAp.getPlayer())) {
                    // то будем добавлять в сохранённый лист:

                    playerFigure = figures.get(cAp.getPlayer());
                } else {
                    // иначе этот лист необходимо создать

                    playerFigure = new ArrayList<>();
                }
                // добавляем в него фигуру
                playerFigure.add(f);
                // кладём лист в мапу
                figures.put(cAp.getPlayer(), playerFigure);
            }
        }
    }

    /**
     * добавление ребра между текущим узлом и узлом сверху над ним (сохраняется для обоих узлов)
     * @param allNodes список всех созданных узлов
     * @param n текущий узел
     */
    private void addEdgeToUp(List<Node> allNodes, Node n) {
        // если не указан верхний узел и нет стены над текущим узлом, то ищем его верхний узел
        if (n.getUp() == null && !haveWallUpperNode(n)) { // если не указан верхний узел

            // ищем индекс верхнего узла в списке узлов
            int index = searchIndexOfNodeInList(allNodes, n.getSymbol(), n.getNumber() + 1);

            // если смогли найти (!= -1)
            if (index != -1) {
                // сохраняем данные в узлы
                n.setUp(allNodes.get(index));
                allNodes.get(index).setDown(n);
            }
            // для всех узлов автоматически находится нижний узел
            // это из-за того что
            // когда мы узнаём верхний узел для одного узла,
            // то для найденного узла первый является нижним и мы сразу сохраняем эти данные
        }
    }

    /**
     * добавление ребра между текущим узлом и узлом слева от него (сохраняется для обоих узлов)
     * @param allNodes список всех созданных узлов
     * @param n текущий узел
     */
    private void addEdgeToLeft(List<Node> allNodes, Node n) {
        //(полностью аналогичен методу addEdgeToUp, но по отношению к левому узлу)

        // если не указан левый узел и нет стены слева от текущего узла, то ищем его левый узел
        if (n.getLeft() == null && !haveWallLefterNode(n)) {
            // ищем индекс левого узла в списке узлов
            int index = searchIndexOfNodeInList(allNodes, (char) (n.getSymbol() - 1), n.getNumber());

            // если смогли найти (!= -1)
            if (index != -1) {
                // сохраняем данные в узлы
                n.setLeft(allNodes.get(index));
                allNodes.get(index).setRight(n);
            }
            // для всех узлов автоматически находится правый узел
            // это из-за того что
            // когда мы узнаём левый узел для одного узла,
            // то для найденного узла первый является правым и мы сразу сохраняем эти данные
        }
    }

    /**
     * проверка наличия стены над узлом
     * @param n узел
     * @return
     */
    private boolean haveWallUpperNode(Node n) {
        // стены есть в двух местах:
        // 1) над d12 и e12
        if (n.getSymbol() == 'c' || n.getSymbol() == 'd') {
            if (n.getNumber() == 11) {
                return true;
            }
        }
        // 2) над m4 и n4
        if (n.getSymbol() == 'm' || n.getSymbol() == 'n') {
            if (n.getNumber() == 3) {
                return true;
            }
        }
        // во всех остальных случаях стены нет
        return false;
    }

    /**
     * проверка наличия стены слева от узла
     * @param n узел
     * @return
     */
    private boolean haveWallLefterNode(Node n) {
        // стены слева от узлов есть в двух местах:
        // 1) у e4 и e5
        if (n.getSymbol() == 'e') {
            if (n.getNumber() == 2 || n.getNumber() == 3) {
                return true;
            }
        }
        // 2) у m13 и m14
        if (n.getSymbol() == 'm' ) {
            if (n.getNumber() == 12 || n.getNumber() == 13) {
                return true;
            }
        }
        // во всех остальных случаях стены нет
        return false;
    }

    /**
     * поиск местоположения узла в списке узлов по его координатам (-1 если отсутствует)
     * @param nodes
     * @param symbol
     * @param number
     * @return
     */
    private int searchIndexOfNodeInList(List<Node> nodes, char symbol, int number) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getNumber() == number && nodes.get(i).getSymbol() == symbol) {
                return i;
            }
        }
        return -1;
    }

/**
* МЕТОДЫ ВЫВОДА ИНФОРМАЦИИ В КОНСОЛЬ
*/

    private  void printFigureInfo(Map<FigureInfo, Figure> map) {
        for (FigureInfo c :
                map.keySet()) {
            System.out.print(c.toString() + " ");
            System.out.print(map.get(c).toString() + "\n");

        }
    }

    private void printNodes(List<Node> nodes) {
        for (Node n : nodes) {
            System.out.println(n.toString());
        }
    }
}
