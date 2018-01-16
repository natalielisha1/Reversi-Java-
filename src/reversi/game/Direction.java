package reversi.game;

/**
 * @author Ofek Segal and Natalie Elisha 
 */
public enum Direction {
    Up,
    Down,
    Left,
    Right,
    UpLeft,
    UpRight,
    DownLeft,
    DownRight;
    
    /**
     * The function returns the reversed direction
     * of the current direction
     * @return reversed direction
     */
    public Direction reverseDir() {
        switch (this) {
            case Up:        return Down;
            case Down:      return Up;
            case Right:     return Left;
            case Left:      return Right;
            case DownLeft:  return UpRight;
            case DownRight: return UpLeft;
            case UpLeft:    return DownRight;
            case UpRight:   return DownLeft;
            default:        return Up;
	}
    }
    
    /**
     * The function returns the next point in the line
     * of the current direction
     * @param point a point
     * @return a point
     */
    public Point getPointFromDir(Point point) {
        switch (this) {
            case Up:        return new Point(point.getX() - 1, point.getY());
            case Down:      return new Point(point.getX() + 1, point.getY());
            case Right:     return new Point(point.getX(), point.getY() + 1);
            case Left:      return new Point(point.getX(), point.getY() - 1);
            case DownLeft:  return new Point(point.getX() + 1, point.getY() - 1);
            case DownRight: return new Point(point.getX() + 1, point.getY() + 1);
            case UpLeft:    return new Point(point.getX() - 1, point.getY() - 1);
            case UpRight:   return new Point(point.getX() - 1, point.getY() + 1);
            default:        return new Point(-1,-1);
	}
    }
}
