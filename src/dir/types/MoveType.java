package dir.types;

public enum MoveType {
    LEFT, RIGHT,DOWN, ROTATE;
    public static MoveType getType(int i) {return values()[i];}
}
