package Program.figures;

public enum PawnType {
    UP(0), RIGHT(1), DOWN(2), LEFT(3);

    int number;

    PawnType(int number) {
        this.number = number;
    }

    public static PawnType pawnTypeOf(int number) {
        for (PawnType pt: values()) {
            if (pt.number == number) {
                return pt;
            }
        }
        return null;
    }
}