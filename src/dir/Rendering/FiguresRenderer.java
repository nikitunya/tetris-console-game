package dir.Rendering;

import dir.Board;
import dir.Enums.FigureType;
import dir.figures.Figure;

import java.util.Random;

public class FiguresRenderer {

    final int placeToSpawn = 7;
    Board board;
    FigureType figureType;

    public FiguresRenderer(Board board) {
        this.board = board;
    }

    public Figure renderFigure(){
        Random random = new Random();
        int figureTypeOrder = random.nextInt(5);
        Figure figure = Figure.createFigure(FigureType.getType(figureTypeOrder), placeToSpawn, this.board);
        figure.draw();
        return figure;
    }
}
