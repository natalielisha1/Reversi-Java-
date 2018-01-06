/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

/**
 *
 * @author OfekSegal
 */
public enum PointType {
    Board,
    Printable;
    
    public PointType reverse() {
        switch (this) {
            case Board:     return Printable;
            case Printable: return Board;
            default:        return Board;
        }
    }
}
