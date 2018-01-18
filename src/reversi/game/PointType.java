package reversi.game;
/**
 * Names: Natalie Elisha and Ofek Segal.
 * IDs: 209475458, 315638288
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
