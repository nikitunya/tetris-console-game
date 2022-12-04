package dir.Logic;

import dir.Enums.MoveType;
import dir.figures.Figure;

import java.util.Scanner;

public class InputManager {
    MoveManager moveManager;

    public void processUserInput(String userInput, Figure currentFigure){
        switch (userInput){
            case "a":
                moveManager.moveFigure(MoveType.LEFT, currentFigure);
                break;
            case "d":
                moveManager.moveFigure(MoveType.RIGHT, currentFigure);
                break;
            case "s":
                moveManager.moveFigure(MoveType.DOWN, currentFigure);
                break;
            case "x":
                moveManager.moveFigure(MoveType.ROTATE, currentFigure);
                break;
            default:
                break;
        }
    }

    public String readUserInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter move: ");
        String input = scanner.nextLine();

        return input;
    }
}
