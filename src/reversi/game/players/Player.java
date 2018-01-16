/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.game.players;

import reversi.game.Cell;
import reversi.game.Point;
import java.util.HashSet;

/**
 * @author Ofek Segal and Natalie Elisha 
 */
public interface Player {
    /**
     * The function plays the player's move
     * according to the given options
     * @param options set of points
     * @return the move as a point
     */
    Point makeMove(HashSet<Point> options);
    
    /**
     * The function sends the given message to the player
     * @param message a string
     */
    void sendMessage(String message);
    
    /**
     * The function returns the player's cell type
     * @return type of cell
     */
    Cell getType();

    /**
     * The function sets the cell type of the player
     * @param newType a new cell type
     */
    void setType(Cell newType);
}
