package dir.Logic;

import dir.Enums.MoveType;
import dir.figures.Figure;

public class MoveManager {

    private boolean gameOver;
    public void moveFigure(MoveType moveType, Figure currentFigure){
        if (moveType == MoveType.ROTATE && currentFigure.canRotate(currentFigure.getFigureType())){
            currentFigure.clear();
            currentFigure.makeMove(moveType);
            currentFigure.draw();
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
