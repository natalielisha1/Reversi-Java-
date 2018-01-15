/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Ofek Segal and Natalie Elisha 
 */
public class GUIPlayer implements Player{
    private Cell _type;
    private boolean _toPrint;
    
    private final GUIAdapter _adapter;
    
    /**
     * Constructor for GUIPlayer object using a type
     * of cell that bound to the player
     * @param type - a type of cell
     */
    public GUIPlayer(Cell type) {
        _type = type;
        _toPrint = true;
        _adapter = GUIAdapter.getInstance(true);
    }
    
    /**
     * Constructor for GUIPlayer object using a type
     * of cell that bound to the player and a boolean
     * value that says if the player should send a message
     * to print for the alert
     * @param type - a type of cell
     * @param toPrint - a boolean value
     */
    public GUIPlayer(Cell type, boolean toPrint) {
        _type = type;
        _toPrint = toPrint;
        _adapter = GUIAdapter.getInstance(true);
    }

    /**
     * The function plays the player's move
     * according to the given options
     * @param options - hash set of points
     * @return the move as a point
     */
    @Override
    public Point makeMove(HashSet<Point> options) {
        if (options.isEmpty()) {
            _adapter.infoAlert("No Move Available!", "You don't have any move available, next player will now play");
            return Game.NO_MOVE_POINT;
        }
        System.out.println("Options: " + Arrays.toString(options.toArray()));
        Point currPoint = _adapter.requestPoint();
        System.out.println(currPoint);
        while (!options.contains(currPoint) && !currPoint.equals(Game.NO_MOVE_POINT) &&
               !currPoint.equals(Game.END_GAME_POINT)) {
            _adapter.infoAlert("Ilegal Move!", "That move is not available to you, please choose a different option");
            currPoint = _adapter.requestPoint();
            System.out.println(currPoint);
        }
        return currPoint;
    }

    /**
     * The function sends the given message to the player
     * @param message - a string
     */
    @Override
    public void sendMessage(String message) {
        if (_toPrint) {
            //Print the message (if it's something to show)
            if (message.contains("won") || message.contains("tie")) {
                message = message.replace("X", "Black");
                message = message.replace("O", "White");
                
                _adapter.infoAlert("Game Over!", message);
            }
        }
    }

    /**
     * The function returns the player's cell type
     * @return type of cell
     */
    @Override
    public Cell getType() {
        return _type;
    }

    /**
     * The function sets the cell type of the player
     * @param newType - a new cell type
     */
    @Override
    public void setType(Cell newType) {
        _type = newType;
    }
    
    /**
     * The function returns the toString version
     * of the player's cell
     * @return a string
     */
    @Override
    public String toString() {
        switch (_type) {
            case X:     return "Black";
            case O:     return "White";
            default:    return "ERROR";
        }
    }
}
