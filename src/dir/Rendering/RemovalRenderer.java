package dir.Rendering;

import dir.Board;

public class RemovalRenderer {

    boolean board[][];

    Board boardClass;

    public RemovalRenderer(boolean[][] board) {
        this.board = board;
    }
    private void removeRow(int row) {
        for (int i = row; i > 0; i--) {
            for (int j = 0; j < boardClass.getWidth(); j++) {
                board[j][i] = board[j][i - 1];
            }
        }
        for (int i = 0; i < boardClass.getWidth(); i++) {
            board[i][0] = false;
        }
//        score += 1000;
    }

    public void checkForRemoval() {
        for (int i = 0; i < boardClass.getWidth(); i++) {
            boolean isRowFull = true;
            for (int j = 0; j < boardClass.getWidth(); j++) {
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

}
