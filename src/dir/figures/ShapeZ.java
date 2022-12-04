package dir.figures;

import dir.Board;
import dir.Enums.FigureType;

public class ShapeZ extends Figure{
    public ShapeZ(FigureType figureType, int x, Board board) {
        super(board, figureType);
        this.points[0] = new Point(x, 2);
        this.points[1] = new Point(x, 1);
        this.points[2] = new Point(x + 1, 1);
        this.points[3] = new Point(x + 1, 0);
    }

    public void draw(){
        for (int i = 0; i < this.FIGURE_SIZE; i++) {
            this.board.addFigureToBoard(points[i].x, points[i].y);
        }
    }

    @Override
    protected void rotateClockwise(Point[] points) {
        switch (rotationState){
            case ANGLE_0:
                points[0].x++;
                points[0].y--;
                points[2].x--;
                points[2].y--;
                points[3].x -= 2;
                break;
            case ANGLE_90:
                points[0].x--;
                points[0].y--;
                points[2].x--;
                points[2].y++;
                points[3].y += 2;
                break;
            case ANGLE_180:
                points[0].x--;
                points[0].y++;
                points[2].x++;
                points[2].y++;
                points[3].x += 2;
                break;
            case ANGLE_270:
                points[0].x++;
                points[0].y++;
                points[2].x++;
                points[2].y--;
                points[3].y -= 2;
                break;
        }
    }
}
