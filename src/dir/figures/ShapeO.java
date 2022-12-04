package dir.figures;

import dir.Board;
import dir.Enums.FigureType;

public class ShapeO extends Figure{
    public ShapeO(FigureType figureType, int x, Board board) {
        super(board, figureType);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                this.points[i * 2 + j] = new Point(x + i, j);
            }
        }
    }

    @Override
    protected void rotateClockwise(Point[] points) {

    }
}
