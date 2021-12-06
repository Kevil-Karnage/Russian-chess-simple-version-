package Program;

import Program.services.GameService;

public class Main {

    public static void main(String[] args) {
	    Game g = new Game();
        GameService gs = new GameService();
        gs.startGame(g);
    }

    // https://ru.wikipedia.org/wiki/%D0%A0%D1%83%D1%81%D1%81%D0%BA%D0%B8%D0%B5_%D1%87%D0%B5%D1%82%D0%B2%D0%B5%D1%80%D0%BD%D1%8B%D0%B5_%D1%88%D0%B0%D1%85%D0%BC%D0%B0%D1%82%D1%8B_%D1%81_%D0%BA%D1%80%D0%B5%D0%BF%D0%BE%D1%81%D1%82%D1%8F%D0%BC%D0%B8
}
