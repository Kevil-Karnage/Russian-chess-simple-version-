package Program.services;

import Program.Node;
import Program.figures.Figure;

import java.util.List;

public class SearchNodesService {

    public SearchNodesService() {
    }

    /**
     * поиск фигуры из списка в узле
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
     * поиск всех узлов по диагонали вправо вверх
     * @param variables
     * @param contFigures
     * @param alliesFigures
     * @param node
     */
    public void searchRightUpNodes(
            List<Node> variables, List<Figure> contFigures, List<Figure> alliesFigures, Node node) {

        // ищем фигуру споерника в текущем узле
        Figure contFigure = searchFigureOfNodeInList(contFigures, node);
        // ищем фигуру союзника в текущем узле
        Figure alliesFigure = searchFigureOfNodeInList(alliesFigures, node);
        // если есть фигура соперника, то добавляем узел и останавливаем рекурсию
        if (contFigure != null) {
            variables.add(node);
        } else if (alliesFigure == null) {
            // иначе, если нет и фигуры союзника, то добавляем узел
            variables.add(node);
            // получаем следующий узел
            Node n = getNodeRightUp(node);
            // если следующий узел есть, то вызываем рекурсию для него
            if (n != null) {
                searchUpNodes(variables, contFigures, alliesFigures, node.getUp());
            }
        }
    }

    /**
     * поиск всех узлов по диагонали вправо вниз
     * @param variables
     * @param contFigures
     * @param alliesFigures
     * @param node
     */
    public void searchRightDownNodes(
            List<Node> variables, List<Figure> contFigures, List<Figure> alliesFigures, Node node) {

        // ищем фигуру соперника в текущем узле
        Figure contFigure = searchFigureOfNodeInList(contFigures, node);
        // ищем фигуру союзника в текущем узле
        Figure alliesFigure = searchFigureOfNodeInList(alliesFigures, node);
        // если есть фигура соперника, то добавляем узел и останавливаем рекурсию
        if (contFigure != null) {
            variables.add(node);
            // иначе, если нет и фигуры союзника, то добавляем узел и продолжаем рекурсию
        } else if (alliesFigure == null) {
            // иначе, если нет и фигуры союзника, то добавляем узел
            variables.add(node);
            // получаем следующий узел
            Node n = getNodeRightDown(node);
            // если следующий узел есть, то вызываем рекурсию для него
            if (n != null) {
                searchUpNodes(variables, contFigures, alliesFigures, node.getUp());
            }
        }
    }

    /**
     * поиск всех узлов по диагонали влево вниз
     * @param variables
     * @param contFigures
     * @param alliesFigures
     * @param node
     */
    public void searchLeftDownNodes(
            List<Node> variables, List<Figure> contFigures, List<Figure> alliesFigures, Node node) {

        // ищем фигуру соперника в текущем узле
        Figure contFigure = searchFigureOfNodeInList(contFigures, node);
        // ищем фигуру союзника в текущем узле
        Figure alliesFigure = searchFigureOfNodeInList(alliesFigures, node);
        // если есть фигура соперника, то добавляем узел и останавливаем рекурсию
        if (contFigure != null) {
            variables.add(node);
        } else if (alliesFigure == null) {
            // иначе, если нет и фигуры союзника, то добавляем узел
            variables.add(node);
            // получаем следующий узел
            Node n = getNodeLeftDown(node);
            // если следующий узел есть, то вызываем рекурсию для него
            if (n != null) {
                searchUpNodes(variables, contFigures, alliesFigures, node.getLeft().getDown());
            }
        }
    }

    /**
     * поиск всех узлов по диагонали влево вверх
     * @param variables
     * @param contFigures
     * @param alliesFigures
     * @param node
     */
    public void searchLeftUpNodes(
            List<Node> variables, List<Figure> contFigures, List<Figure> alliesFigures, Node node) {

        // ищем фигуру соперника в текущем узле
        Figure contFigure = searchFigureOfNodeInList(contFigures, node);
        // ищем фигуру союзника в текущем узле
        Figure alliesFigure = searchFigureOfNodeInList(alliesFigures, node);
        // если есть фигура соперника, то добавляем узел и останавливаем рекурсию
        if (contFigure != null) {
            variables.add(node);
        } else if (alliesFigure == null) {
            // иначе, если нет и фигуры союзника, то добавляем узел
            variables.add(node);
            // получаем следующий узел
            Node n = getNodeLeftUp(node);
            // если следующий узел есть, то вызываем рекурсию для него
            if (n != null) {
                searchUpNodes(variables, contFigures, alliesFigures, node.getLeft().getDown());
            }
        }
    }




    /**
     * рекурсионный поиск всех возможных вариантов ходов сверху
     * @param variables
     * @param contFigures
     * @param alliesFigures
     * @param node
     */
    public void searchUpNodes(
            List<Node> variables, List<Figure> contFigures, List<Figure> alliesFigures, Node node) {
        if (node != null) {
            // ищем фигуру споерника в текущем узле
            Figure contFigure = searchFigureOfNodeInList(contFigures, node);
            // ищем фигуру союзника в текущем узле
            Figure alliesFigure = searchFigureOfNodeInList(alliesFigures, node);
            // если есть фигура соперника, то добавляем узел и останавливаем рекурсию
            if (contFigure != null) {
                variables.add(node);
                // иначе, если нет и фигуры союзника, то добавляем узел и продоожаем рекурсию
            } else if (alliesFigure == null) {
                variables.add(node);
                searchUpNodes(variables, contFigures, alliesFigures, node.getUp());
            }
        }
    }

    /**
     * рекурсионный поиск всех возможных вариантов ходов справа
     * @param variables
     * @param contFigures
     * @param alliesFigures
     * @param node
     */
    public void searchRightNodes(
            List<Node> variables, List<Figure> contFigures, List<Figure> alliesFigures, Node node) {
        if (node != null) {
            // ищем фигуру споерника в текущем узле
            Figure contFigure = searchFigureOfNodeInList(contFigures, node);
            // ищем фигуру союзника в текущем узле
            Figure alliesFigure = searchFigureOfNodeInList(alliesFigures, node);
            // если есть фигура соперника, то добавляем узел и останавливаем рекурсию
            if (contFigure != null) {
                variables.add(node);
            } else if (alliesFigure == null) {
                variables.add(node);
                searchRightNodes(variables, contFigures, alliesFigures, node.getRight());
            }
        }
    }

    /**
     * рекурсионный поиск всех возможных вариантов ходов снизу
     * @param variables
     * @param contFigures
     * @param alliesFigures
     * @param node
     */
    public void searchDownNodes(
            List<Node> variables, List<Figure> contFigures, List<Figure> alliesFigures, Node node) {
        if (node != null) {
            // ищем фигуру споерника в текущем узле
            Figure contFigure = searchFigureOfNodeInList(contFigures, node);
            // ищем фигуру союзника в текущем узле
            Figure alliesFigure = searchFigureOfNodeInList(alliesFigures, node);
            // если есть фигура соперника, то добавляем узел и останавливаем рекурсию
            if (contFigure != null) {
                variables.add(node);
            } else if (alliesFigure == null) {
                variables.add(node);
                searchDownNodes(variables, contFigures, alliesFigures, node.getDown());
            }
        }
    }

    /**
     * рекурсионный поиск всех возможных вариантов ходов слева
     * @param variables
     * @param contFigures
     * @param alliesFigures
     * @param node
     */
    public void searchLeftNodes(
            List<Node> variables, List<Figure> contFigures, List<Figure> alliesFigures, Node node) {
        if (node != null) {
            // ищем фигуру споерника в текущем узле
            Figure contFigure = searchFigureOfNodeInList(contFigures, node);
            // ищем фигуру союзника в текущем узле
            Figure alliesFigure = searchFigureOfNodeInList(alliesFigures, node);
            // если есть фигура соперника, то добавляем узел и останавливаем рекурсию
            if (contFigure != null) {
                variables.add(node);
            } else if (alliesFigure == null) {
                variables.add(node);
                searchLeftNodes(variables, contFigures, alliesFigures, node.getLeft());
            }
        }
    }




    /**
     * получение узла справа вверху
     * @param node
     * @return
     */
    public Node getNodeRightUp(Node node) {
        if (node.getUp() != null && node.getUp().getRight() != null) {
            return node.getUp().getRight();
        }
        if (node.getRight() != null && node.getRight().getUp() != null) {
            return node.getRight().getUp();
        }
        return null;
    }

    /**
     * получение узла справа внизу
     * @param node
     * @return
     */
    public Node getNodeRightDown(Node node) {
        if (node.getDown() != null && node.getDown().getRight() != null) {
            return node.getDown().getRight();
        }
        if (node.getRight() != null && node.getRight().getDown() != null) {
            return node.getRight().getDown();
        }
        return null;
    }

    /**
     * получение узла слева внизу
     * @param node
     * @return
     */
    public Node getNodeLeftDown(Node node) {
        if (node.getDown() != null && node.getDown().getLeft() != null) {
            return node.getDown().getLeft();
        }
        if (node.getLeft() != null && node.getLeft().getDown() != null) {
            return node.getLeft().getDown();
        }
        return null;
    }

    /**
     * получение узла слева вверху
     * @param node
     * @return
     */
    public Node getNodeLeftUp(Node node) {
        if (node.getUp() != null && node.getUp().getLeft() != null) {
            return node.getUp().getLeft();
        }
        if (node.getLeft() != null && node.getLeft().getUp() != null) {
            return node.getUp().getLeft();
        }
        return null;
    }

}
