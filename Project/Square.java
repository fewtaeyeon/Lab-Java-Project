
public class Square {

    private Piece piece;
    private boolean isWalkable;
    private int positionX;
    private int positionY;
    
    public Square(Piece piece, boolean isWalkable, int positionX, int positionY) {
        this.piece = piece;
        this.isWalkable = isWalkable;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    public void setWalkable(boolean isWalkable) {
        this.isWalkable = isWalkable;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
