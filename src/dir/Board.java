package dir;

import dir.Logic.InputManager;
import dir.Rendering.FiguresRenderer;
import dir.Rendering.RemovalRenderer;
import dir.Rendering.RenderBoard;
import dir.Rendering.TextRenderer;
import dir.figures.Figure;
import dir.Enums.FigureType;
import dir.Enums.MoveType;

import java.util.Random;

public class Board {
    private static final int BOARD_SIZE = 15;

    private boolean gameOver = false;
    private boolean board[][];

    private Figure currentFigure;

    private boolean canMove = false;

    private int score = 0;

    FigureType figureType;

    public Board() {
        this.board = new boolean[BOARD_SIZE][BOARD_SIZE];
    }

    private void print() {
        System.out.println("----------------------");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print("*");
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[j][i]) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("*");
        }
        for (int i = 0; i < BOARD_SIZE + 2; i++) {
            System.out.print("*");
        }
        System.out.println();
        System.out.println("Score: " + score);
    }

    public void start() {
        RenderBoard renderBoard = new RenderBoard(this.board);
        FiguresRenderer figuresRenderer = new FiguresRenderer(this);
        InputManager inputManager = new InputManager();
        TextRenderer textRenderer = new TextRenderer();
        RemovalRenderer removalRenderer = new RemovalRenderer(board);

        Figure currentFigure = figuresRenderer.renderFigure();
        renderBoard.render();

        while (!gameOver != false){
            while (true){
                textRenderer.enterMove();
                String input = inputManager.readUserInput();
                inputManager.processUserInput(input, currentFigure);

                if (!currentFigure.canMove()){
                    break;
                }
            }
            currentFigure = figuresRenderer.renderFigure();

        }
//        Scanner scanner = new Scanner(System.in);
//        addFigure();
//        print();
//        while (currentFigure.canMove() && !gameOver) {
//            while (true) {
//                System.out.println();
//                System.out.print("Enter move: ");
//                String move = scanner.nextLine();
//                switch (move) {
//                    case "a":
//                        moveFigure(MoveType.LEFT);
//                        break;
//                    case "d":
//                        moveFigure(MoveType.RIGHT);
//                        break;
//                    case "s":
//                        moveFigure(MoveType.DOWN);
//                        break;
//                    case "x":
//                        moveFigure(MoveType.ROTATE);
//                        break;
//                }
//                print();
//                if (!currentFigure.canMove()) {
//                    break;
//                }
//            }
//            gameOver = gameOver();
//            addFigure();
//            checkForRemoval();
//            print();
//        }
//        System.out.println("---------");
//        System.out.println("Game over");
//        System.out.println("---------");
    }
    private boolean gameOver(){
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][1] == true){
                return true;
            }
        }
        return false;
    }

    private void removeRow(int row) {
        for (int i = row; i > 0; i--) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[j][i] = board[j][i - 1];
            }
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i][0] = false;
        }
        score += 1000;
    }

    public void checkForRemoval() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            boolean isRowFull = true;
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[j][i] == false) {
                    isRowFull = false;
                    break;
                }
            }
            if (isRowFull) {
                removeRow(i);
            }
        }
    }


    private void moveFigure(MoveType moveType) {
        if (moveType == MoveType.ROTATE && currentFigure.canRotate(this.figureType)){
            currentFigure.clear();
            currentFigure.makeMove(moveType);
//            currentFigure.moveDown();
            currentFigure.draw();
        }
        else if (moveType == MoveType.DOWN && currentFigure.canMove(moveType)) {
            currentFigure.clear();
            currentFigure.moveDown();
            currentFigure.draw();
        } else if (currentFigure.canMove(moveType) && moveType != MoveType.ROTATE) {
            currentFigure.clear();
            currentFigure.makeMove(moveType);
            if (currentFigure.getLastMove() < 2) {
                currentFigure.moveDown();
            }
            currentFigure.draw();
        }
    }

    public void addFigureToBoard(int x, int y) {
        //TODO:
        this.board[x][y] = true;
    }

    private void addFigure() {
        Random random = new Random();
        int placeToSpawn = 7; //TODO: Maybe do it const
        int randomType = random.nextInt(5);
        this.figureType = FigureType.getType(randomType);
        currentFigure = Figure.createFigure(FigureType.getType(randomType), placeToSpawn, this);
        currentFigure.draw();
    }

    public void clearFigureFromBoard(int x, int y) {
        this.board[x][y] = false;
    }

    public int getWidth() {
        return this.board.length;
    }

    public boolean isClear(int i, int j, Figure f) {
        if (i < 0 || i > BOARD_SIZE - 1 || j < 0 || j > BOARD_SIZE - 1) {
            return false;
        }
        return !board[i][j] || f.contains(i, j);
    }
    public boolean isClear(int i, int j) {
        if (i < 0 || i > BOARD_SIZE - 1 || j < 0 || j > BOARD_SIZE - 1) {
            return false;
        }
        return true;
    }

    public boolean getBoardElement(int x, int y) {
        return board[x][y];
    }

}
