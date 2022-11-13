package dir.figures;

import dir.Board;
import dir.types.FigureType;

public class ShapeJ extends Figure {

    public ShapeJ(FigureType figureType, int x, Board board) {
        super(board, figureType);
        for (int i = 0; i < FIGURE_SIZE - 1; i++) {
            this.points[i] = new Point(x + 1, i);
        }
        this.points[FIGURE_SIZE - 1] = new Point(x, 2);
    }

    @Override
    protected void rotateClockwise(Point[] points) {
        switch (rotationState) {
            case ANGLE_0:
                points[0].x++;
                points[0].y++;
                points[2].x--;
                points[2].y--;
                // pts[3].x -= 2;
                points[3].y -= 2;
                break;
            case ANGLE_90:
                points[0].x--;
                points[0].y++;
                points[2].x++;
                points[2].y--;
                points[3].x += 2;
//                 points[3].y--;
                break;
            case ANGLE_180:
                points[0].x--;
                points[0].y--;
                points[2].x++;
                points[2].y++;
                // pts[3].x += 2;
                points[3].y += 2;
                break;
            case ANGLE_270:
                points[0].x++;
                points[0].y--;
                points[2].x--;
                points[2].y++;
                points[3].x -= 2;
                // pts[3].y -= 2;
                break;
        }
    }
}
