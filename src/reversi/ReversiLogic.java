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
 *
 * @author OfekSegal
 */
public class ReversiLogic implements GameLogic {
    
    private final Board _gameBoard;
    private final HashSet<Point> _xLocations;
    private final HashSet<Point> _oLocations;
    
    /**
     * Creates a new ReversiLogic
     * object with board with the
     * specified size
     * @param boardSize the size for the board
     */
    public ReversiLogic(int boardSize) {
        _gameBoard = new Board(boardSize);
        _xLocations = new HashSet<>();
        _oLocations = new HashSet<>();
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
        return new Direction[] {Direction.Up, Direction.Down, Direction.Left,
                                Direction.Right, Direction.UpLeft, Direction.UpRight,
                                Direction.DownLeft, Direction.DownRight};
    }

    /**
     * Calculating the available
     * moves for player with cell type =
     * cell
     * @param cell the player's cell type
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
            for (Direction dir : dirs) {
                Point currPoint = dir.getPointFromDir(advLocations.next());
                if (checkMoveEmpty(cell, currPoint, dir)) {
                    options.add(currPoint);
                }
            }
        }
        return options;
    }

    /**
     *
     * @param point
     * @param cell
     * @return
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
            return true;
        }
    }
    
    /**
     *
     * @return
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
     *
     * @return
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
    }
    
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
