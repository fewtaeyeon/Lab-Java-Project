
public class PieceMouseAction {

    private static boolean isSelected = false;

    private static Square selectedSquareInstant;

    private static Square selectedSquare;

    private static boolean isBlackTurn = false;

    public static int blackLife = 3;

    public static int whiteLife = 3;

    public static void clicked(Square square, Board board) {
        if (isSelected) {
            move(square, board);
        } else {
            select(square);
        }
    }

    public static void select(Square square) {
        isSelected = true;
        selectedSquareInstant = new Square(square.getPiece(),
                square.isWalkable(), square.getPositionX(),
                square.getPositionY());
        selectedSquare = square;
    }

    public static void move(Square square, Board board) {

        int squarePos = square.getPositionX() * 10 + square.getPositionY();
        int selectedSquareInstantPos = selectedSquareInstant.getPositionX() * 10
                + selectedSquareInstant.getPositionY();
        int distance = selectedSquareInstantPos - squarePos;
        int pos = distance / 2 + squarePos;
        int posX = pos / 10;
        int posY = pos % 10;

        if (moveable(square, board).equals("move")) {
            square.setPiece(selectedSquareInstant.getPiece());
            selectedSquare.setPiece(null);
            isBlackTurn = !isBlackTurn;
        } else if (moveable(square, board).equals("eat")) {
            square.setPiece(selectedSquareInstant.getPiece());
            selectedSquare.setPiece(null);
            board.getBoard()[posX][posY].setPiece(null);
            if(isBlackTurn) {
                whiteLife--;
            }else{
                blackLife--;
            }
            isBlackTurn = !isBlackTurn;
        }
        isSelected = false;
    }

    private static String moveable(Square square, Board board) {
        System.out.printf("\n\n%d %d %d %d\n\n",
                selectedSquareInstant.getPositionX(),
                selectedSquareInstant.getPositionY(), square.getPositionX(),
                square.getPositionY());
        int squarePos = square.getPositionX() * 10 + square.getPositionY();
        int selectedSquareInstantPos = selectedSquareInstant.getPositionX() * 10
                + selectedSquareInstant.getPositionY();
        System.out.printf("\n\n%d\n\n", selectedSquareInstantPos - squarePos);
        int distance = selectedSquareInstantPos - squarePos;
        if ((distance == -11 || distance == -9)
                && (selectedSquareInstant.getPiece().isBlack()
                        && isBlackTurn)) {
            if (square.getPiece() == null) {
                return "move";
            }
        }
        if ((distance == 11 || distance == 9)
                && (selectedSquareInstant.getPiece().isWhite()
                        && !isBlackTurn)) {
            if (square.getPiece() == null) {
                return "move";
            }
        }

        // eat
        int pos = distance / 2 + squarePos;
        int posX = pos / 10;
        int posY = pos % 10;
        System.out.println(posX + " " + posY);

        if ((distance == -22 || distance == -18)
                && (selectedSquareInstant.getPiece().isBlack() && isBlackTurn
                        && board.getBoard()[posX][posY].getPiece().isWhite())) {
            if (square.getPiece() == null) {
                return "eat";
            }
        }
        if ((distance == 22 || distance == 18)
                && (selectedSquareInstant.getPiece().isWhite() && !isBlackTurn
                        && board.getBoard()[posX][posY].getPiece().isBlack())) {
            if (square.getPiece() == null) {
                return "eat";
            }
        }

        return "";
    }
}
