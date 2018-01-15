/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

/**
 * @author Ofek Segal and Natalie Elisha 
 */
public enum PointType {
    Board,
    Printable;
    
    /**
     * The function returns the reverse PointType
     * to the current PointType
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
