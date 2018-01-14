/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author ofek_
 */
public interface GameLogic {

    /**
     *
     * @return
     */
    public Direction[] availableDirections();
    
    /**
     *
     * @param cell
     * @return
     */
    public HashSet<Point> calcMoves(Cell cell);
    
    /**
     *
     * @param point
     * @param cell
     * @return
     */
    public boolean put(Point point, Cell cell);
    
    /**
     *
     * @return
     */
    public HashMap<String, Integer> getQuantities();
    
    /**
     *
     * @return
     */
    public GameStatus checkWinning();
    
    public String boardToString();
    
    public boolean quickWinCheck();
}
