package reversi.game;

/**
 * @author Ofek Segal and Natalie Elisha 
 */
public enum PointType {
    Board,
    Printable;
    
    /**
     * The function returns the other PointType
     * @return reversed PointType
     */
    public PointType reverse() {
        switch (this) {
            case Board:     return Printable;
            case Printable: return Board;
            default:        return Board;
        }
    }
}
