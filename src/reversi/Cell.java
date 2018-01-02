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
public enum Cell {
    Empty,
    O,
    X;
    
    public Cell advCellType() {
        switch (this) {
            case O:     return X;
            case X:     return O;
            default:    return Empty;
        }
    }
}
