/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

/**
 * @author Ofek Segal and Natalie Elisha 
 */
public class Point {
    private int _xLoc;
    private int _yLoc;
    private PointType _type;
    
    /**
     * The function creates a default point
     */
    public Point() {
        _xLoc = -1;
        _yLoc = -1;
        _type = PointType.Board;
    }
    
    /**
     * The function creates a point according
     * to the given x and y rate
     * @param x - x rate of the point
     * @param y - y rate of the point
     */
    public Point(int x, int y) {
        _xLoc = x;
        _yLoc = y;
        _type = PointType.Board;
    }
    
    /**
    * The function creates a point according
    * to the given x and y rate and a given PointType
     * @param x - x rate of the point
     * @param y - y rate of the point
     * @param type - PointType instance
    */
    public Point(int x, int y, PointType type) {
        _xLoc = x;
        _yLoc = y;
        _type = type;
    }
    
    /**
    * The function creates an instance of a point
    * with the information of a given point
     * @param p - a point
    */
    public Point(Point p) {
        _xLoc = p._xLoc;
        _yLoc = p._yLoc;
        _type = p._type;
    }
    
    /**
     * The function returns the x rate of the point
     * @return x location
     */
    public int getX() {
        return _xLoc;
    }
    
    /**
     * The function returns the y rate of the point
     * @return y location
     */
    public int getY() {
        return _yLoc;
    }
    
    /**
     * The function sets the x rate of the point
     * @param x - new x location
     */
    public void setX(int x) {
        _xLoc = x;
    }
    
    /**
     * The function sets the y rate of the point
     * @param y - new x location
     */
    public void setY(int y) {
        _yLoc = y;
    }
    
    /**
     * The function returns the PointType of the current point
     * @return a PointType instance
     */
    public PointType getType() {
        return _type;
    }
    
    /**
     * The function sets the PointType of the point
     * @param type - the new PointType instance
     */
    public void setType(PointType type) {
        _type = type;
    }
    
    /**
     * The function aligns the current point for
     * being a part of a board that is playing a game
     */
    public void alignToBoard() {
        if (_type == PointType.Printable) {
            _xLoc--;
            _yLoc--;
            _type = PointType.Board;
        }
    }
    
    /**
     * The function aligns the current point for
     * being a part of a board that is being printed
     */
    public void alignToPrint() {
        if (_type == PointType.Board) {
            _xLoc++;
            _yLoc++;
            _type = PointType.Printable;
        }
    }
    @Override
    /**
     * The function creates a toString object that
     * represents the current point
     * @return toString object
     */
    public String toString() {
        return "(" + _xLoc + "," + _yLoc + ")";
    }
    
    /**
     * The function checks if the given object is
     * equal to the current point
     * @param o - an object
     * @return true if equal, otherwise false
     */
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
    /**
     * The function returns a hash code that
     * represents the point's validation
     * @return a hash code
     */
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
