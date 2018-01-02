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
    private int xLoc;
    private int yLoc;
    
    public Point() {
        xLoc = -1;
        yLoc = -1;
    }
    
    public Point(int x, int y) {
        xLoc = x;
        yLoc = y;
    }
    
    public int getX() {
        return xLoc;
    }
    
    public int getY() {
        return yLoc;
    }
    
    public void setX(int x) {
        xLoc = x;
    }
    
    public void setY(int y) {
        yLoc = y;
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
        return this.xLoc == other.xLoc &&
               this.yLoc == other.yLoc;
    }
}
