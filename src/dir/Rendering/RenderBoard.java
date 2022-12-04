package dir.Rendering;

public class RenderBoard {

    private boolean board[][];
    private static final int BOARD_SIZE = 15;
    private int score = 0;

    public RenderBoard(boolean[][] board) {
        this.board = board;
    }

    public void render() {
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
}
