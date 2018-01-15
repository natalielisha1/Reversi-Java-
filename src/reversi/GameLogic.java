/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Ofek Segal and Natalie Elisha 
 */
public interface GameLogic {

    /**
     * The function returns an array of
     * available directions 
     * @return an array of directions
     */
    public Direction[] availableDirections();
    
    /**
     * The function calculating the moves that are
     * available for the player that holds the given
     * cell type and returns them as a hash set of points
     * @param cell - a type of cell
     * @return a hash set of points
     */
    public HashSet<Point> calcMoves(Cell cell);

    /**
     * The function puts the given cell type
     * in the given point on the board
     * @param point - a point
     * @param cell - a cell type
     * @return true if succeeded, otherwise false
     */
    public boolean put(Point point, Cell cell);
    
    /**
     * The function returns a hash map that
     * contains the quantities of the cells
     * for the O player, the X player and overall
     * @return a hash map
     */
    public HashMap<String, Integer> getQuantities();

    /**
     * The function checks which player won
     * and returns a game status accordingly
     * @return a game status
     */
    public GameStatus checkWinning();
    
    /**
     * The function returns a toString version
     * of the game board
     * @return a string that represents the game board
     */
    public String boardToString();
    
    /**
     * The function checks quickly if the game
     * has ended, if there's a winner
     * @return true if there's a winner, otherwise false
     */
    public boolean quickWinCheck();
}
