package dir.types;

public enum FigureType {
    Z, I, O, L, J; // TODO: Add more

    public static FigureType getType(int i) {return values()[i];}
}
