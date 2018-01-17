/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.game.players;

import reversi.game.Cell;
import reversi.game.Game;
import reversi.game.Point;
import java.util.Arrays;
import java.util.HashSet;
import reversi.gui.GUIAdapter;

/**
 * @author Ofek Segal and Natalie Elisha 
 */
public class GUIPlayer implements Player{
    
    //The type of the player
    private Cell _type;
    
    //If the game is GUI vs GUI, only one of them should print messages
    private final boolean _toPrint;
    
    //A link to the GUI, without knowing exactly how the GUI works
    private final GUIAdapter _adapter;
    
    /**
     * Constructor for GUIPlayer object using a type
     * of cell of the player
     * @param type a type of cell
     */
    public GUIPlayer(Cell type) {
        //Using the given type
        _type = type;
        
        //The default is to print messages
        _toPrint = true;
        
        //Getting the GUI link, and also activating the GUI functions
        _adapter = GUIAdapter.getInstance(true);
    }
    
    /**
     * Constructor for GUIPlayer object using a type
     * of cell of the player and a boolean
     * value that says if the player should send a message
     * @param type a type of cell
     * @param toPrint a boolean value
     */
    public GUIPlayer(Cell type, boolean toPrint) {
        //Using the given type
        _type = type;
        
        //Using the given boolean
        _toPrint = toPrint;
        
        //Getting the GUI link, and also activating the GUI functions
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
        //If no move - print message (regardless of the _toPrint) and return
        if (options.isEmpty()) {
            _adapter.infoAlert("No Move Available!", "You don't have any move available, next player will now play");
            return Game.NO_MOVE_POINT;
        }
        //Getting the point
        Point currPoint = _adapter.requestPoint();
        
        //As long as the point is not available, retry
        while (!options.contains(currPoint) && !currPoint.equals(Game.NO_MOVE_POINT) &&
               !currPoint.equals(Game.END_GAME_POINT)) {
            _adapter.infoAlert("Ilegal Move!", "That move is not available to you, please choose a different option");
            currPoint = _adapter.requestPoint();
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
