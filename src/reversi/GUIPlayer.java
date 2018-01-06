/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

import java.util.HashSet;

/**
 *
 * @author ofek_
 */
public class GUIPlayer implements Player{
    private Cell _type;
    private boolean _toPrint;
    
    public GUIPlayer(Cell type) {
        _type = type;
        _toPrint = true;
    }
    
    public GUIPlayer(Cell type, boolean toPrint) {
        _type = type;
        _toPrint = toPrint;
    }

    @Override
    public Point makeMove(HashSet<Point> options) {
        //TODO
        
        return Game.NO_MOVE_POINT;
    }

    @Override
    public void sendMessage(String message) {
        if (_toPrint) {
            //Print the message (if it's something to show)
            if (message.contains("won") || message.contains("tie")) {
                //TODO: Show the message
            }
        }
    }

    @Override
    public Cell getType() {
        return _type;
    }

    @Override
    public void setType(Cell newType) {
        _type = newType;
    }
    
    @Override
    public String toString() {
        switch (_type) {
            case X:     return "Black";
            case O:     return "White";
            default:    return "ERROR";
        }
    }
}
