package dir;

import dir.figures.Figure;
import dir.types.FigureType;
import dir.types.MoveType;

import java.util.Random;
import java.util.Scanner;

public class Board {
    private static final int BOARD_SIZE = 7;

    private boolean gameOver = false;
    private boolean board[][];

    private Figure currentFigure;

    private boolean canMove = false;

    private int score = 0;

    public Board() {
        this.board = new boolean[BOARD_SIZE][BOARD_SIZE];
    }

    private void print() {
        System.out.println("----------------------");
//        clearBoard();
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
        Scanner scanner = new Scanner(System.in);
        addFigure();
        print();
        while (currentFigure.canMove() && !gameOver) {
            while (true) {
                System.out.println();
                System.out.print("Enter move: ");
                String move = scanner.nextLine();
                switch (move) {
                    case "a":
                        moveFigure(MoveType.LEFT);
                        break;
                    case "d":
                        moveFigure(MoveType.RIGHT);
                        break;
                    case "s":
                        moveFigure(MoveType.DOWN);
                        break;
                    case "x":
                        moveFigure(MoveType.ROTATE);
                        break;
                }
                print();
                if (!currentFigure.canMove()) {
                    break;
                }
            }
            gameOver = gameOver();
            addFigure();
            checkForRemoval();
            print();
        }
        System.out.println("Game over");
//        board[2][1] = true;
//        board[3][1] = true;
//        board[2][3] = true;
//        board[3][3] = true;
//        for (int i = 0; i < BOARD_SIZE; i++) {
//            board[i][2] = true;
//            board[i][4] = true;
//        }
//        print();
//        checkForRemoval();
//        print();
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
        for (int i = 0; i < BOARD_SIZE; i++) { // TODO: Uncomment
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
        if (moveType == MoveType.DOWN && currentFigure.canMove(moveType)) {
            canMove = true;
            currentFigure.clear();
            currentFigure.moveDown();
            currentFigure.draw();
        } else if (currentFigure.canMove(moveType)) {
            canMove = true;
            currentFigure.clear();
            currentFigure.makeMove(moveType);
            if (currentFigure.getLastMove() < 2) {
                currentFigure.moveDown();
            }

            currentFigure.draw();
        }
    }

    public void addFigureToBoard(int x, int y) {
        //TODO: Maybe add validation
        this.board[x][y] = true;
    }

    private void addFigure() {
        Random random = new Random();
        int placeToSpawn = 3; //TODO: Maybe do it const
        int randomType = random.nextInt(5); // TODO: make it const as number of figures
        currentFigure = Figure.createFigure(FigureType.getType(randomType), placeToSpawn, this);
//        currentFigure = Figure.createFigure(FigureType.getType(randomType), placeToSpawn, this);
        currentFigure.draw();
    }

    public void clearFigureFromBoard(int x, int y) {
        this.board[x][y] = false;
    }

    public int getWidth() {
        return this.board.length;
    }

    public boolean isClear(int i, int j, Figure f) { // TODO: Figure this out
        if (i < 0 || i > BOARD_SIZE - 1 || j < 0 || j > BOARD_SIZE - 1) {
            return false;
        }
        return !board[i][j] || f.contains(i, j);
    }

    public boolean getBoardElement(int x, int y) {
        return board[x][y];
    }
}
