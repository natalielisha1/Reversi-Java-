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
public interface Player {
    Point makeMove(HashSet<Point> options);
    
    void sendMessage(String message);
    
    Cell getType();
    void setType(Cell newType);
}
