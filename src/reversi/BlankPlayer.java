/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

import java.util.HashSet;

/**
 *
 * @author OfekSegal
 */
public class BlankPlayer implements Player{
    private Cell _type;
    
    public BlankPlayer(Cell type) {
        _type = type;
    }
    
    @Override
    public Point makeMove(HashSet<Point> options) {
        return new Point(-1,-1);
    }

    @Override
    public void sendMessage(String message) {
        //Do nothing
    }

    @Override
    public Cell getType() {
        return _type;
    }

    @Override
    public void setType(Cell newType) {
        _type = newType;
    }
    
}
