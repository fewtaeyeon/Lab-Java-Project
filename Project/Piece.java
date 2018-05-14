

public class Piece {
    
    private boolean isBlack = true;
    private boolean isWhite = false;
    
    private boolean isHos = false;

    public Piece(boolean isBlack) {
        this.isBlack = isBlack;
        this.isWhite = !isBlack;
    }
    
    public boolean isBlack() {
        return isBlack;
    }
    public void setBlack(boolean isBlack) {
        this.isBlack = isBlack;
    }
    
    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean isWhite) {
        this.isWhite = isWhite;
    }
    
    public boolean isHos() {
        return isHos;
    }
    public void setHos(boolean isHos) {
        this.isHos = isHos;
    }
}
