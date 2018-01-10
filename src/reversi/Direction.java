/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

/**
 *
 * @author ofek_
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
     *
     * @return
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
     *
     * @param point
     * @return
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
