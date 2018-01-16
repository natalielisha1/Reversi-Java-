package reversi.game;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Ofek Segal and Natalie Elisha 
 */
public interface GameLogic {

    /**
     * Getting a list of available
     * directions to check for next
     * move
     * @return the list of directions
     */
    public Direction[] availableDirections();
    
    /**
     * Calculating the available
     * moves for player with cell type =
     * cell
     * @param cell the player's cell type
     * @return the set of possible moves
     */
    public HashSet<Point> calcMoves(Cell cell);

    /**
     * The function puts the given cell type
     * in the given point on the board
     * and changes all of the affected cells
     * @param point a point
     * @param cell a cell type
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
     * has ended (only checking if the board is full)
     * @return true if the game is over, otherwise false
     */
    public boolean quickWinCheck();
}
