import acm.graphics.GImage;

public class Board {

    /* Number of rows */
    private static final int NROWS = 8;

    /* Number of columns */
    private static final int NCOLUMNS = 8;

    GImage selection = new GImage("selection.png");

    private Square[][] board = new Square[NROWS][NCOLUMNS];

    public Board() {
        for (int i = 0; i < NROWS; i++) {
            for (int j = 0; j < NCOLUMNS; j++) {
                if ((i + j) % 2 == 0) {
                    if (i < 2) {
                        board[i][j] = new Square(new Piece(true), true, i, j);
                    } else if (i > 5) {
                        board[i][j] = new Square(new Piece(false), true, i, j);
                    } else {
                        board[i][j] = new Square(null, true, i, j);
                    }
                } else if ((i + j) % 2 == 1) {
                    board[i][j] = new Square(null, false, i, j);
                }
                //System.out.println(board[i][j].getPositionX() + " " + board[i][j].getPositionY());
            }
        }
    }

    public Square[][] getBoard() {
        return board;
    }

    public void setBoard(Square[][] board) {
        this.board = board;
    }
}