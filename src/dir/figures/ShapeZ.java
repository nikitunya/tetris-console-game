package dir.figures;

import dir.Board;
import dir.types.FigureType;

public class ShapeZ extends Figure{
    public ShapeZ(FigureType figureType, int x, Board board) {
        super(board, figureType);
//        this.xPoints[0] = x;
//        this.xPoints[1] = x;
//        this.xPoints[2] = x + 1;
//        this.xPoints[3] = x + 1;
//
//        this.yPoints[0] = 2;
//        this.yPoints[1] = 1;
//        this.yPoints[2] = 1;
//        this.yPoints[3] = 0;
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
                // pts[3].y -= 2;
                break;
            case ANGLE_90:
                points[0].x--;
                points[0].y--;
                points[2].x--;
                points[2].y++;
                // pts[3].x -= 2;
                points[3].y += 2;
                break;
            case ANGLE_180:
                points[0].x--;
                points[0].y++;
                points[2].x++;
                points[2].y++;
                points[3].x += 2;
                // pts[3].y += 2;
                break;
            case ANGLE_270:
                points[0].x++;
                points[0].y++;
                points[2].x++;
                points[2].y--;
                // pts[3].x += 2;
                points[3].y -= 2;
                break;
        }
    }
}
