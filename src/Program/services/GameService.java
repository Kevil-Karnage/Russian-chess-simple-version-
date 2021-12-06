package Program.services;

import Program.Board;
import Program.Game;
import Program.Move;
import Program.Player;
import Program.figures.Figure;

import java.util.*;

public class GameService {

    public GameService() {

    }

    public void startGame(Game g) { // создание игры
        BoardService bs = new BoardService();
        FigureService fs = new FigureService();

        g = new Game(); // создаём игру
        addPlayersToGame(g); // добавляем игроков
        generatingBoard(g, bs, fs); // генерируем доску
        //bs.addFiguresToPlayers(g); // добавляем игрокам фигуры
        // определяем кто ходит первый (и соответственно, очередь игроков)
        play(g, fs); // делаем ходы до конца игры
    }

    /**
     * Генерация и сохранение доски(Board)
     * @param g игра (Game)
     * @param bs BoardService
     */
    public void generatingBoard(Game g, BoardService bs, FigureService fs) {
        // генерируем доску
        Board board = new Board(); // создаём доску
        g.setBoard(board);
        // добавляем все узлы, соединения и фигуры
        bs.addAllNodesToBoard(g, fs);

        printPlayerFigures(g);
        // сохраняем созданную доску
        g.setBoard(board);
    }

    /**
     * добавление игроков в игру
     * @param g - игра(game)
     */
    public void addPlayersToGame(Game g) {
        // создаём список из 4 игроков
        Player[] players = new Player[4];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(Integer.toString(i));
        }
        // сохраняем игроков в игре
        g.setPlayers(players);
    }

    private void play(Game g, FigureService fs) {
        // сама игра: ходы игроков (ботов) пока игра не закончится

        // создаём экземпляр сервиса для ходов игроков (ботов)
        MoveService ms = new MoveService();

        // создаём очередь из игроков (очерёденость хода)
        Player[] players = g.getPlayers();
        Queue<Player> queue = new LinkedList<>(Arrays.asList(players));

        List<Move> moves = new ArrayList<>();
        g.setMoves(moves);
        // пока игра НЕ закончена
        while(!gameIsEnded(g)) {
            // берём игрока
            Player current = queue.remove();
            // игрок делает ход
            ms.move(fs, g, players, current);
            // добавляем игрока в конец очереди
            queue.add(current);
        }

        g.setMoves(moves);
    }

    /**
     * проверка закончена игра или нет
     * @param g игра (Game)
     * @return boolean
     */
    private boolean gameIsEnded(Game g) {
        if (teamIsDestroyed(g)) {
            return true;
        }
        // собственно, проверяем доску на наличие мата и всех
        // остальных ситуаций, при которых игра заканчивается
        return false;
    }

    /**
     * уничтожена ли команда или нет
     * @param g - игра(Game)
     * @return boolean
     */
    private boolean teamIsDestroyed(Game g) {
        Player[] players = g.getPlayers();
        // если 1 команда не имеет ходов
        if (!playerCanMakeMove(players[0]) && !playerCanMakeMove(players[2])) {
            return true;
        }
        // или 2 команда не имеет ходов
        if (!playerCanMakeMove(players[1]) && !playerCanMakeMove(players[3])) {
            return true;
        }
        // то команда уничтожена, возвращаем true
        // во всех остальных случаях возвращаем false
        return false;
    }


    private boolean playerCanMakeMove(Player p) {
        // проверка: может ли игрок сделать ход
        return false;
    }

/** МЕТОДЫ ДЛЯ ВЫВОДА */

    public void printPlayerFigures(Game g) {
        for (Player p :
                g.getPlayers()) {
            System.out.println(p.toString());
        }
    }
}