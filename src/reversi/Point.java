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
    
    /**
     *
     */
    public Point() {
        xLoc = -1;
        yLoc = -1;
    }
    
    /**
     *
     * @param x
     * @param y
     */
    public Point(int x, int y) {
        xLoc = x;
        yLoc = y;
    }
    
    /**
     *
     * @return
     */
    public int getX() {
        return xLoc;
    }
    
    /**
     *
     * @return
     */
    public int getY() {
        return yLoc;
    }
    
    /**
     *
     * @param x
     */
    public void setX(int x) {
        xLoc = x;
    }
    
    /**
     *
     * @param y
     */
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

    @Override
    public int hashCode() {
        String toBecomeHash = "";
        int tempX, tempY;
        
        if (xLoc < 0) {
            toBecomeHash += "1";
            tempX = xLoc * -1;
        } else {
            toBecomeHash += "0";
            tempX = xLoc;
        }
        toBecomeHash += tempX + "0";
        
        if (yLoc < 0) {
            toBecomeHash += "1";
            tempY = yLoc * -1;
        } else {
            toBecomeHash += "0";
            tempY = yLoc;
        }
        toBecomeHash += tempY;
        
        return Integer.getInteger(toBecomeHash);
    }
}
