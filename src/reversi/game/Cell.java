package reversi.game;
/**
 * Names: Natalie Elisha and Ofek Segal.
 * IDs: 209475458, 315638288
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
