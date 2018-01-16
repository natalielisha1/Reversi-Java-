package reversi.game;

/**
 * @author Ofek Segal and Natalie Elisha 
 */
public enum Cell {
    Empty,
    O,
    X;
    
    /**
     * The function returns the adversary's cell type
     * @return the opposite type
     */
    public Cell advCellType() {
        switch (this) {
            case O:     return X;
            case X:     return O;
            default:    return Empty;
        }
    }
}
