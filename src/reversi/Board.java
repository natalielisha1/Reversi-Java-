/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

/**
 *
 * @author ofek_
 */
public class Board {
    private static final int DEFAULT_SIZE = 8;
    
    private Cell[][] theBoard;
    private final int boardSize;
    private int xCount;
    private int oCount;
    
    public Board() {
        boardSize = DEFAULT_SIZE;
        theBoard = new Cell[boardSize][boardSize];
    }
    
    public Board(int size) {
        boardSize = size;
        theBoard = new Cell[boardSize][boardSize];
    }
    
    public Board(Board b) {
        boardSize = b.boardSize;
        theBoard = new Cell[boardSize][boardSize];
        
        for (int outerIndex = 0; outerIndex < boardSize; outerIndex++) {
            for (int innerIndex = 0; innerIndex < boardSize; innerIndex++) {
                theBoard[outerIndex][innerIndex] = b.theBoard[outerIndex][innerIndex];
            }
        }
    }
    
    public int getBoardSize() {
        return boardSize;
    }
    
    public int getXCount() {
        return xCount;
    }
    
    public int getOCount() {
        return oCount;
    }
    
    public boolean pointExists(Point p) {
        return 0 <= p.getX() && p.getX() < boardSize &&
               0 <= p.getY() && p.getY() < boardSize;
    }
    
    public boolean isCellEmpty(Point p) {
        if (!pointExists(p)) {
            return false;
        }
        return theBoard[p.getX()][p.getY()] == Cell.Empty;
    }
    
    public boolean put(Point p, Cell playerType) {
        if (!pointExists(p)) {
            return false;
        }
        theBoard[p.getX()][p.getY()] = playerType;
        return true;
    }
    
    public Cell getCell(Point p) {
        if (!pointExists(p)) {
            return Cell.Empty;
        }
        
        return theBoard[p.getX()][p.getY()];
    }
}
