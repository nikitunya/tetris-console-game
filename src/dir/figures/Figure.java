package dir.figures;

import dir.Board;
import dir.Enums.FigureType;
import dir.Enums.MoveType;
import dir.Enums.RotationState;


public abstract class Figure {
    protected static final int FIGURE_SIZE = 4;
    protected Board board;
    protected FigureType figureType;
    protected RotationState rotationState;
    private int lastMove = 0;
    protected Point[] points;

    public Figure(Board board, FigureType figureType) {
        this.board = board;
        this.figureType = figureType;
        this.rotationState = RotationState.ANGLE_0;
        this.points = new Point[FIGURE_SIZE];
    }

    public static Figure createFigure(FigureType typeOfFigure, int x, Board parentBoard) {
        switch (typeOfFigure) {
            case Z:
                return new ShapeZ(typeOfFigure, x, parentBoard);
            case I:
                return new ShapeI(typeOfFigure, x, parentBoard);
            case O:
                return new ShapeO(typeOfFigure, x, parentBoard);
            case L:
                return new ShapeL(typeOfFigure, x, parentBoard);
            case J:
                return new ShapeJ(typeOfFigure, x, parentBoard);
        }
        return null;
    }

    public void draw() {
        for (int i = 0; i < this.FIGURE_SIZE; i++) {
            this.board.addFigureToBoard(points[i].x, points[i].y);
        }
    }

    public boolean canMove() {
        Point[] tempPoints = new Point[FIGURE_SIZE];
        for (int i = 0; i < FIGURE_SIZE; i++) {
            tempPoints[i] = new Point(points[i].x, points[i].y);
        }
        checkMoveDown(tempPoints);
        for (int i = 0; i < tempPoints.length; i++) {
            if (!this.board.isClear(tempPoints[i].x, tempPoints[i].y, this)) {
                lastMove++;
                if (lastMove == 1)
                    return true;
                return false;
            }
        }

        if (canMove(MoveType.LEFT) || canMove(MoveType.RIGHT)) {
            return true;
        }
        return false;
    }

    public boolean canRotate(FigureType typeOfFigure) {
        Point[] tempPoints = new Point[FIGURE_SIZE];
        for (int i = 0; i < FIGURE_SIZE; i++) {
            tempPoints[i] = new Point(points[i].x, points[i].y);
        }
        rotateClockwise(tempPoints);
        for (int i = 0; i < FIGURE_SIZE; i++) {
            if (!this.board.isClear(tempPoints[i].x, tempPoints[i].y)) {
                return false;
            }
        }
        return true;
    }

    public boolean canMove(MoveType typeOfMove) {
        Point[] tempPoints = new Point[FIGURE_SIZE];
        for (int i = 0; i < FIGURE_SIZE; i++) {
            tempPoints[i] = new Point(points[i].x, points[i].y);
        }
        switch (typeOfMove) {
            case LEFT:
                moveLeft(tempPoints);
                break;
            case RIGHT:
                moveRight(tempPoints);
                break;
            case DOWN:
                checkMoveDown(tempPoints);
                break;
        }

        for (int i = 0; i < tempPoints.length; i++) {
            if (!this.board.isClear(tempPoints[i].x, tempPoints[i].y, this)) {
                return false;
            }
        }
        return true;
    }

    private void moveLeft(Point[] tempPoints) {
        for (int i = 0; i < FIGURE_SIZE; i++) {
            tempPoints[i].x--;
        }
    }

    public void makeMove(MoveType typeOfMove) {
        switch (typeOfMove) {
            case LEFT:
                moveLeft(points);
                break;
            case RIGHT:
                moveRight(points);
                break;
            case ROTATE:
                rotateClockwise(points);
                int rotationStateTemp = this.rotationState.ordinal();
                rotationStateTemp++;
                if (rotationStateTemp > 3) {
                    rotationStateTemp = 0;
                }
                this.rotationState = RotationState.getType(rotationStateTemp);
                break;
        }
    }


    private void moveRight(Point[] tempPoints) {
        for (int i = 0; i < FIGURE_SIZE; i++) {
            tempPoints[i].x++;
        }
    }

    public void checkMoveDown(Point[] tempPoints) {
        for (int i = 0; i < FIGURE_SIZE; i++) {
            tempPoints[i].y++;
        }
    }

    public void moveDown() {
        for (int i = 0; i < FIGURE_SIZE; i++) {
            this.points[i].y++;
        }
    }

    public void clear() {
        for (int i = 0; i < FIGURE_SIZE; i++) {
            this.board.clearFigureFromBoard(this.points[i].x, this.points[i].y);
        }
    }

    public boolean contains(int x, int y) {
        for (int i = 0; i < points.length; i++) {
            if (points[i].x == x && points[i].y == y) {
                return true;
            }
        }
        return false;
    }

    protected abstract void rotateClockwise(Point[] points);

    public int getLastMove() {
        return lastMove;
    }

    public FigureType getFigureType() {
        return figureType;
    }
}
