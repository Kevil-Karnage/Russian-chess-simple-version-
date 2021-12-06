package Program;

import Program.figures.Figure;

import java.util.List;

public class Player {
    private String name;
    private List<Figure> figures;

    public Player(String name) {
        this.name = name;
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public void setFigures(List<Figure> figures) {
        this.figures = figures;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Player ").append(name).append('\n');
        for (Figure f: figures) {
            sb.append(f.toString()).append(" ");
        }
        return sb.toString();
    }
}
