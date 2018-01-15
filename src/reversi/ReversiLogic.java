/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author Ofek Segal and Natalie Elisha 
 */
public class ReversiLogic implements GameLogic {
    
    private final Board _gameBoard;
    private final HashSet<Point> _xLocations;
    private final HashSet<Point> _oLocations;
    private final GUIAdapter _adapter;
    
    /**
     * Creates a new ReversiLogic
     * object with board with the
     * specified size
     * @param boardSize - the size for the board
     */
    public ReversiLogic(int boardSize) {
        _gameBoard = new Board(boardSize);
        _xLocations = new HashSet<>();
        _oLocations = new HashSet<>();
        _adapter = GUIAdapter.getInstance();
        initBoard();
    }

    /**
     * Getting a list of available
     * directions to check for next
     * move
     * @return the list of directions
     */
    @Override
    public Direction[] availableDirections() {
        return Direction.values();
    }

    /**
     * Calculating the available
     * moves for player with cell type =
     * cell
     * @param cell - the player's cell type
     * @return the set of possible moves
     */
    @Override
    public HashSet<Point> calcMoves(Cell cell) {
        HashSet<Point> options = new HashSet<>();
        Iterator<Point> advLocations;
        switch (cell) {
            case X:     advLocations = _oLocations.iterator();
                        break;
            case O:     advLocations = _xLocations.iterator();
                        break;
            default:    return options;
        }
        
        Direction[] dirs = availableDirections();
        
        while (advLocations.hasNext()) {
            Point pointToCheck = advLocations.next();
            pointToCheck.alignToBoard();
            for (Direction dir : dirs) {
                Point currPoint = dir.getPointFromDir(pointToCheck);
                if (checkMoveEmpty(cell, currPoint, dir)) {
                    options.add(currPoint);
                }
            }
        }
        return options;
    }

    /**
     * The function puts the given cell type
     * in the given point on the board
     * @param point - a point
     * @param cell - a cell type
     * @return true if succeeded, otherwise false
     */
    @Override
    public boolean put(Point point, Cell cell) {
        if (!_gameBoard.pointExists(point)) {
            return false;
        } else {
            Direction[] dirs = availableDirections();
            for (Direction dir : dirs) {
                HashSet<Point> tempLocations = new HashSet<>();
                tempLocations.add(point);
                if (tryToPut(cell, dir.getPointFromDir(point), dir, tempLocations)) {
                    for (Point currPoint : tempLocations) {
                        currPoint.alignToBoard();
                        _gameBoard.setCell(currPoint, cell);
                        _xLocations.remove(currPoint);
                        _oLocations.remove(currPoint);
                        switch (cell) {
                            case X:     _xLocations.add(currPoint);
                                        break;
                            case O:     _oLocations.add(currPoint);
                                        break;
                            default:    break;
                        }
                    }
                }
            }
            _adapter.draw(_gameBoard);
            _adapter.changeBlackPlayerScore(_gameBoard.getXCount());
            _adapter.changeWhitePlayerScore(_gameBoard.getOCount());
            return true;
        }
    }
    
    /**
     * The function returns a hash map that
     * contains the quantities of the cells
     * for the O player, the X player and overall
     * @return a hash map
     */
    @Override
    public HashMap<String, Integer> getQuantities() {
        HashMap<String, Integer> toReturn = new HashMap<>();
        int xNumber, oNumber;
        
        xNumber = _xLocations.size();
        toReturn.put("xNumber", xNumber);
        
        oNumber = _oLocations.size();
        toReturn.put("oNumber", oNumber);
        
        toReturn.put("overallCells", xNumber + oNumber);
        
        return toReturn;
    }

    /**
     * The function checks which player won
     * and returns a game status accordingly
     * @return a game status
     */
    @Override
    public GameStatus checkWinning() {
        int xNumber, oNumber;
        
        xNumber = _xLocations.size();
        oNumber = _oLocations.size();
        
        if (xNumber > oNumber) {
            return GameStatus.XWins;
        } else if (xNumber < oNumber) {
            return GameStatus.OWins;
        } else {
            return GameStatus.Tie;
        }
    }
    
    /**
     * The function returns a toString version
     * of the game board
     * @return a string that represents the game board
     */
    @Override
    public String boardToString() {
        return _gameBoard.toString();
    }
    
    /**
     * The function checks quickly if the game
     * has ended, if there's a winner
     * @return true if there's a winner, otherwise false
     */
    @Override
    public boolean quickWinCheck() {
        int xNumber = _xLocations.size();
        int oNumber = _oLocations.size();
        
        return (xNumber + oNumber) == Math.pow(_gameBoard.getBoardSize(),2);
    }
    
    /**
     * The function maintains the game board's properties
     */
    private void initBoard() {
        int middle = _gameBoard.getBoardSize()/2 -1;
        Point[] firstXLocations = new Point[] {new Point(middle, middle + 1),
                                               new Point(middle + 1, middle)};
        Point[] firstOLocations = new Point[] {new Point(middle, middle),
                                               new Point(middle + 1, middle + 1)};
        for (Point xLocation : firstXLocations) {
            _gameBoard.setCell(xLocation, Cell.X);
            _xLocations.add(xLocation);
        }
        for (Point oLocation : firstOLocations) {
            _gameBoard.setCell(oLocation, Cell.O);
            _oLocations.add(oLocation);
        }
        _adapter.draw(_gameBoard);
        _adapter.changeBlackPlayerScore(_gameBoard.getXCount());
        _adapter.changeWhitePlayerScore(_gameBoard.getOCount());
    }
    
    /**
     * The function checks if the move from the given point
     * to the given direction is available (empty)
     * @param playerType - the cell type of the player
     * @param point - a point in board
     * @param dir - a direction
     * @return true if available, otherwise false
     */
    private boolean checkMoveEmpty(Cell playerType, Point point, Direction dir) {
        if (!_gameBoard.pointExists(point)) {
            return false;
        }
        Cell currCell = _gameBoard.getCell(point);
        if (currCell == Cell.Empty) {
            dir = dir.reverseDir();
            return checkMove(playerType, dir.getPointFromDir(point), dir);
        } else {
            return false;
        }
    }
    
    /**
     * The function checks if a move in the given direction is
     * possible to make
     * @param playerType - the cell type of the player
     * @param point - a point in board
     * @param dir - a direction
     * @return true if possible, otherwise false
     */
    private boolean checkMove(Cell playerType, Point point, Direction dir) {
        if (!_gameBoard.pointExists(point)) {
            return false;
        }
        Cell currCell = _gameBoard.getCell(point);
        if (currCell == playerType) {
            return true;
        } else if (currCell == Cell.Empty) {
            return false;
        } else {
            return checkMove(playerType, dir.getPointFromDir(point), dir);
        }
    }
    
    /**
     * The function tries to add the point to the locations
     * of the player who owns the given locations
     * @param cell - a cell type of the player
     * @param point - a point in board
     * @param dir - a direction
     * @param tempLocations - hash set of points the player has
     * @return true if succeeded, otherwise false
     */
    private boolean tryToPut(Cell cell, Point point, Direction dir, HashSet<Point> tempLocations) {
        if (!_gameBoard.pointExists(point)) {
            return false;
        } else {
            Cell currCell = _gameBoard.getCell(point);
            if (currCell == cell) {
                return true;
            } else if (currCell == Cell.Empty) {
                return false;
            } else {
                tempLocations.add(point);
                return tryToPut(cell, dir.getPointFromDir(point), dir, tempLocations);
            }
        }
    }
}
