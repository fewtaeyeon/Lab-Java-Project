
import acm.graphics.*;
import acm.program.GraphicsProgram;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends GraphicsProgram {

    /* Number of rows */
    private static final int NROWS = 8;

    /* Number of columns */
    private static final int NCOLUMNS = 8;

    private static final int SQUARE_SIZE = 80;

    private static final int WIDTH = SQUARE_SIZE * NCOLUMNS + 200;

    private static final int HEIGHT = SQUARE_SIZE * NROWS + 22;

    private static Board board = new Board();
    
    private static boolean isGameOver = false;

    public void run() {
        setSize(WIDTH, HEIGHT);
        addMouseListeners();
         display();
    }

    private void display() {
        if (!isGameOver) {
            drawbackground();
            displayBoard();
            displayLabel();
        }
    }

    private void drawbackground() {
        GRect blackground = new GRect(0, 0, WIDTH, HEIGHT);
        blackground.setFilled(true);
        blackground.setColor(Color.WHITE);
        add(blackground);
    }

    private void displayLabel() {
        
        GLabel blackLive = new GLabel("Black remaining life : " + PieceMouseAction.blackLife, WIDTH - 190, HEIGHT / 2 -50);
        blackLive.setFont("SansSerif-BOLD-14");
        blackLive.setColor(Color.BLACK);
        add(blackLive);
        
        GLabel whiteLive = new GLabel("White remaining life : " + PieceMouseAction.whiteLife, WIDTH - 190, HEIGHT / 2);
        whiteLive.setFont("SansSerif-BOLD-14");
        whiteLive.setColor(Color.BLACK);
        add(whiteLive);
        
        if (PieceMouseAction.blackLife == 0) {
            GLabel whiteWin = new GLabel("White Win!", WIDTH - 190, HEIGHT - 30);
            whiteWin.setFont("SansSerif-BOLD-34");
            whiteWin.setColor(Color.BLACK);
            add(whiteWin);
            isGameOver = true;
        }
        
        if (PieceMouseAction.whiteLife == 0) {
            GLabel blackWin = new GLabel("Black Win!", WIDTH - 190, 30);
            blackWin.setFont("SansSerif-BOLD-34");
            blackWin.setColor(Color.BLACK);
            add(blackWin);
            isGameOver = true;
        }
    }

    private void displayBoard() {
        for (int i = 0; i < NROWS; i++) {
            for (int j = 0; j < NCOLUMNS; j++) {
                int X = i * SQUARE_SIZE;
                int Y = j * SQUARE_SIZE;
                if (board.getBoard()[i][j].isWalkable()) {
                    if (board.getBoard()[i][j].getPiece() == null) {
                        createSquare(Y, X, Color.WHITE);
                    } else if (board.getBoard()[i][j].getPiece().isBlack()) {
                        createSquare(Y, X, Color.WHITE);
                        createCircle(Y, X, Color.BLACK);
                    } else if (board.getBoard()[i][j].getPiece().isWhite()) {
                        createSquare(Y, X, Color.WHITE);
                        createCircle(Y, X, Color.WHITE);
                    }
                } else {
                    createSquare(Y, X, Color.BLACK);
                }
            }
            System.out.println();
        }
    }

    private void createSquare(int x, int y, Color color) {
        GRect square = new GRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
        square.setFilled(true);
        square.setFillColor(color);
        square.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                PieceMouseAction.clicked(board.getBoard()[y/SQUARE_SIZE][x/SQUARE_SIZE], board);
                System.out.println(y/SQUARE_SIZE + " " + x/SQUARE_SIZE);
                display();
            }
        });
        add(square);
    }

    private void createCircle(int x, int y, Color color) {
        GOval circle = new GOval(x + SQUARE_SIZE / 4, y + SQUARE_SIZE / 4,
                SQUARE_SIZE / 2, SQUARE_SIZE / 2);
        circle.setFilled(true);
        circle.setFillColor(color);
        circle.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                PieceMouseAction.clicked(board.getBoard()[y/SQUARE_SIZE][x/SQUARE_SIZE], board);
                System.out.println(y/SQUARE_SIZE + " " + x/SQUARE_SIZE);
                display();
            }
        });
        add(circle);
    }

    private void commandlineDisplay() {
        for (int i = 0; i < NROWS; i++) {
            for (int j = 0; j < NCOLUMNS; j++) {
                if (board.getBoard()[i][j].isWalkable()) {
                    if (board.getBoard()[i][j].getPiece() == null) {
                        System.out.printf(" ");
                    } else if (board.getBoard()[i][j].getPiece().isBlack()) {
                        System.out.printf("B");
                    } else if (board.getBoard()[i][j].getPiece().isWhite()) {
                        System.out.printf("W");
                    }
                } else {
                    System.out.printf("#");
                }
                //System.out.printf("[%d,%d] ", board.getBoard()[i][j].getPositionX(), board.getBoard()[i][j].getPositionY());
            }
            System.out.println();
        }
    }
}
