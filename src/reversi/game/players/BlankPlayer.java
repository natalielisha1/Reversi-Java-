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
 * Names: Natalie Elisha and Ofek Segal.
 * IDs: 209475458, 315638288
 */
public class BlankPlayer implements Player{
    private Cell _type;
    
    /**.
     * The function creates an instance of BlankPlayer
     * @param type cell type
     */
    public BlankPlayer(Cell type) {
        _type = type;
    }
    
    /**.
     * The function returns a negligible point
     * @param options set of available moves
     * @return point
     */
    @Override
    public Point makeMove(HashSet<Point> options) {
        return new Point(-1,-1);
    }

    /**.
     * Currently, the function does nothing
     * @param message string
     */
    @Override
    public void sendMessage(String message) {
        //Do nothing
    }

    /**.
     * The function returns the type of the blank player
     * @return type of cell
     */    
    @Override
    public Cell getType() {
        return _type;
    }

    /**.
     * The function sets the type of the cell of
     * the blank player
     * @param newType new type of cell
     */
    @Override
    public void setType(Cell newType) {
        _type = newType;
    }
    
}
