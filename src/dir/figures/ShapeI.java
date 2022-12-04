package dir.figures;

import dir.Board;
import dir.Enums.FigureType;

public class ShapeI extends Figure {
    public ShapeI(FigureType figureType, int x, Board board) {
        super(board, figureType);
        for (int i = 0; i < FIGURE_SIZE; i++) {
            this.points[i] = new Point(x + i, 0);
        }
    }

    @Override
    protected void rotateClockwise(Point[] points) {
        switch (rotationState) {
            case ANGLE_0:
                points[0].x++;
                points[0].y--;
                points[2].x--;
                points[2].y++;
                points[3].x -= 2;
                points[3].y += 2;
                break;
            case ANGLE_90:
                points[0].x--;
                points[0].y++;
                points[2].x++;
                points[2].y-=1;
                points[3].x -= 2;
                points[3].y -= 2;
                break;
            case ANGLE_180:
                points[0].x++;
                points[0].y--;
                points[2].x--;
                points[2].y++;
                points[3].x += 2;
                points[3].y += 2;
                break;
            case ANGLE_270:
                points[0].x--;
                points[0].y++;
                points[2].x++;
                points[2].y--;
                points[3].x += 2;
                points[3].y -= 2;
                break;
        }
    }
}
