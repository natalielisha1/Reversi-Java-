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
public class Point {
    private int _xLoc;
    private int _yLoc;
    private PointType _type;
    
    /**
     *
     */
    public Point() {
        _xLoc = -1;
        _yLoc = -1;
        _type = PointType.Board;
    }
    
    /**
     *
     * @param x
     * @param y
     */
    public Point(int x, int y) {
        _xLoc = x;
        _yLoc = y;
        _type = PointType.Board;
    }
    
    public Point(int x, int y, PointType type) {
        _xLoc = x;
        _yLoc = y;
        _type = type;
    }
    
    public Point(Point p) {
        _xLoc = p._xLoc;
        _yLoc = p._yLoc;
        _type = p._type;
    }
    
    /**
     *
     * @return
     */
    public int getX() {
        return _xLoc;
    }
    
    /**
     *
     * @return
     */
    public int getY() {
        return _yLoc;
    }
    
    /**
     *
     * @param x
     */
    public void setX(int x) {
        _xLoc = x;
    }
    
    /**
     *
     * @param y
     */
    public void setY(int y) {
        _yLoc = y;
    }
    
    public PointType getType() {
        return _type;
    }
    
    public void setType(PointType type) {
        _type = type;
    }
    
    public void alignToBoard() {
        if (_type == PointType.Printable) {
            _xLoc--;
            _yLoc--;
            _type = PointType.Board;
        }
    }
    
    public void alignToPrint() {
        if (_type == PointType.Board) {
            _xLoc++;
            _yLoc++;
            _type = PointType.Printable;
        }
    }
    @Override
    public String toString() {
        return "(" + _xLoc + "," + _yLoc + ")";
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Point)) {
            return false;
        }
        Point other = (Point) o;
        if (this._type == PointType.Board) {
            if (other._type == PointType.Printable) {
                other = new Point(other);
                other.alignToBoard();
            }
        } else {
            if (other._type == PointType.Board) {
                other = new Point(other);
                other.alignToPrint();
            }
        }
        return this._xLoc == other._xLoc &&
               this._yLoc == other._yLoc;
    }

    @Override
    public int hashCode() {
        String toBecomeHash = "";
        int tempX, tempY;
        
        if (_xLoc < 0) {
            toBecomeHash += "1";
            tempX = _xLoc * -1;
        } else {
            toBecomeHash += "0";
            tempX = _xLoc;
        }
        toBecomeHash += tempX + "0";
        
        if (_yLoc < 0) {
            toBecomeHash += "1";
            tempY = _yLoc * -1;
        } else {
            toBecomeHash += "0";
            tempY = _yLoc;
        }
        toBecomeHash += tempY;
        
        if (_type == PointType.Board) {
            toBecomeHash += "0";
        } else {
            toBecomeHash += "1";
        }
        
        return Integer.parseInt(toBecomeHash);
    }
}
