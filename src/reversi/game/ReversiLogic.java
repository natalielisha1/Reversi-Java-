package reversi.game;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import reversi.gui.GUIAdapter;

/**
 * @author Ofek Segal and Natalie Elisha 
 */
public class ReversiLogic implements GameLogic {
    
    //Variable to keep the board
    private final Board _gameBoard;
    
    //Sets to save the locations of the filled cells on the board
    private final HashSet<Point> _xLocations;
    private final HashSet<Point> _oLocations;
    
    //A link to the GUI, without knowing exactly how the GUI works
    private final GUIAdapter _adapter;
    
    /**
     * Creates a new ReversiLogic
     * object with board with the
     * specified size
     * @param boardSize the size for the board
     */
    public ReversiLogic(int boardSize) {
        //Creating the new board
        _gameBoard = new Board(boardSize);
        
        //Init'ing the sets
        _xLocations = new HashSet<>();
        _oLocations = new HashSet<>();
        
        //Getting the link to the GUI (works even if the display is not GUI)
        _adapter = GUIAdapter.getInstance();
        
        //Setting the initial locations
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
     * @param cell the player's cell type
     * @return the set of possible moves
     */
    @Override
    public HashSet<Point> calcMoves(Cell cell) {
        //Preparing the set to return
        HashSet<Point> options = new HashSet<>();
        
        /*
         * To not copy-paste code, I use the Iterator
         * so it doesn't matter what the cell type was,
         * the advLocations will iterate over the
         * adversary's cells' locations
         */
        Iterator<Point> advLocations;
        switch (cell) {
            case X:     advLocations = _oLocations.iterator();
                        break;
            case O:     advLocations = _xLocations.iterator();
                        break;
            default:    return options;
        }
        
        //Getting the directions to check
        Direction[] dirs = availableDirections();
        
        //Going through the adversary's cells' locations
        while (advLocations.hasNext()) {
            //Getting the curr point to check
            Point pointToCheck = advLocations.next();
            
            /*
             * Fixing a compatibility issue, from the Console
             * display - the Console displayed the locations
             * in the range [1,size], but the program
             * uses the [0,size-1] range
             */
            pointToCheck.alignToBoard();
            
            //For each direction to check:
            for (Direction dir : dirs) {
                //Getting the point that will be next in this direction
                Point currPoint = dir.getPointFromDir(pointToCheck);
                
                //If it's an option - it will be added to the set
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
     * and changes all of the affected cells
     * @param point a point
     * @param cell a cell type
     * @return true if succeeded, otherwise false
     */
    @Override
    public boolean put(Point point, Cell cell) {
        if (!_gameBoard.pointExists(point)) {
            return false;
        } else {
            //Getting the directions to check
            Direction[] dirs = availableDirections();
            for (Direction dir : dirs) {
                //Creating a set to store all the affected cells in this direction
                HashSet<Point> tempLocations = new HashSet<>();
                //Adding the current point
                tempLocations.add(point);
                //Checking if this direction will be affected, if so, the cells will be changed
                if (tryToPut(cell, dir.getPointFromDir(point), dir, tempLocations)) {
                    for (Point currPoint : tempLocations) {
                        /*
                         * Fixing a compatibility issue, from the Console
                         * display - the Console displayed the locations
                         * in the range [1,size], but the program
                         * uses the [0,size-1] range
                         */
                        currPoint.alignToBoard();
                        
                        //Changing the current point in the board
                        _gameBoard.setCell(currPoint, cell);
                        
                        //Removing the point from the location sets (if relavent)
                        _xLocations.remove(currPoint);
                        _oLocations.remove(currPoint);
                        
                        //Adding the point to the appropriate set
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
            
            //If the display is GUI style, it will be updated
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
     * has ended (only checking if the board is full)
     * @return true if the game is over, otherwise false
     */
    @Override
    public boolean quickWinCheck() {
        int xNumber = _xLocations.size();
        int oNumber = _oLocations.size();
        
        return (xNumber + oNumber) == Math.pow(_gameBoard.getBoardSize(),2);
    }
    
    /**
     * The function initializes the board
     */
    private void initBoard() {
        //Getting the middle of the board
        int middle = _gameBoard.getBoardSize()/2 -1;
        
        //Calc'ing the starting positions
        Point[] firstXLocations = new Point[] {new Point(middle, middle + 1),
                                               new Point(middle + 1, middle)};
        Point[] firstOLocations = new Point[] {new Point(middle, middle),
                                               new Point(middle + 1, middle + 1)};
        
        //Adding the starting positions to the board and the location sets
        for (Point xLocation : firstXLocations) {
            _gameBoard.setCell(xLocation, Cell.X);
            _xLocations.add(xLocation);
        }
        for (Point oLocation : firstOLocations) {
            _gameBoard.setCell(oLocation, Cell.O);
            _oLocations.add(oLocation);
        }
        
        //If the display is GUI style, it will be updated
        _adapter.draw(_gameBoard);
        _adapter.changeBlackPlayerScore(_gameBoard.getXCount());
        _adapter.changeWhitePlayerScore(_gameBoard.getOCount());
    }
    
    /**
     * The function checks if the move from the given point
     * to the given direction is empty
     * @param playerType the cell type of the player
     * @param point a point in board
     * @param dir a direction
     * @return true if available, otherwise false
     */
    private boolean checkMoveEmpty(Cell playerType, Point point, Direction dir) {
        if (!_gameBoard.pointExists(point)) {
            return false;
        }
        //Getting the cell in the specified point
        Cell currCell = _gameBoard.getCell(point);
        
        //If it's empty, reversing the direction and continuing the inspection
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
     * @param playerType the cell type of the player
     * @param point a point in board
     * @param dir a direction
     * @return true if possible, otherwise false
     */
    private boolean checkMove(Cell playerType, Point point, Direction dir) {
        if (!_gameBoard.pointExists(point)) {
            return false;
        }
        //Getting the cell in the specified point
        Cell currCell = _gameBoard.getCell(point);
        
        if (currCell == playerType) {
            //Same cell type - finished the line
            return true;
        } else if (currCell == Cell.Empty) {
            //Another empty cell - no line available
            return false;
        } else {
            //Adv cell type - continuing the inspection
            return checkMove(playerType, dir.getPointFromDir(point), dir);
        }
    }
    
    /**
     * The function tries to add the point to the locations
     * of the player who owns the given locations
     * @param cell the cell type of the player
     * @param point a point in the board
     * @param dir the direction
     * @param tempLocations set of points to turn
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
