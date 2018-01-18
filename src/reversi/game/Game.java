package reversi.game;

import java.util.HashSet;
import reversi.game.players.BlankPlayer;
import reversi.gui.GUIAdapter;
import reversi.game.players.GUIPlayer;
import reversi.gui.GameSettings;
import reversi.game.players.Player;
/**
 * Names: Natalie Elisha and Ofek Segal.
 * IDs: 209475458, 315638288
 */
public class Game {
    //Player type identifiers
    public static final char HUMAN_PLAYER_IDENTIFIER = 'h';
    public static final char COMP_PLAYER_IDENTIFIER = 'c';
    public static final char BLANK_PLAYER_IDENTIFIER = 'b';
    public static final char REMOTE_PLAYER_IDENTIFIER = 'r';
    public static final char GUI_PLAYER_IDENTIFIER = 'g';
    
    //Special points
    public static final Point END_GAME_POINT = new Point(-2, -2, PointType.Board);
    public static final Point NO_MOVE_POINT = new Point(-1, -1, PointType.Board);
    
    //The game settings object
    private GameSettings _settings;
    
    //The logic to use
    private GameLogic _logic;
    
    //The current game status
    private GameStatus _status;
    
    //A link to the GUI, without knowing exactly how the GUI works
    private GUIAdapter _adapter;
    
    //The players
    private Player _xPlayer;
    private Player _oPlayer;
    
    /**
     * Constructor for Game object
     */
    public Game() {
        //Getting the settings
        _settings = GameSettings.getInstance();
        
        //Creating the logic
        _logic = new ReversiLogic(_settings.getBoardSize());
        
        //Getting the link to the GUI
        _adapter = GUIAdapter.getInstance();
        
        //Starting the game as "NotPlaying"
        _status = GameStatus.NotPlaying;
        
        //The default here is GUI players
        _xPlayer = new GUIPlayer(Cell.X, true);
        _oPlayer = new GUIPlayer(Cell.O, false);
    }

    /**
     * Constructor for Game object using x player and o player
     * identifiers
     * @param xPlayerIdentifier identifier char for x player
     * @param oPlayerIdentifier identifier char for o player
     */
    public Game(char xPlayerIdentifier, char oPlayerIdentifier) {
        //Getting the settings
        _settings = GameSettings.getInstance();
        
        //Creating the logic
        _logic = new ReversiLogic(_settings.getBoardSize());
        
        //Getting the link to the GUI
        _adapter = GUIAdapter.getInstance();
        
        //Starting the game as "NotPlaying"
        _status = GameStatus.NotPlaying;
        
        switch (xPlayerIdentifier) {
            case GUI_PLAYER_IDENTIFIER:     _xPlayer = new GUIPlayer(Cell.X, true);
                                            _adapter.setGUI(true);
                                            break;
            case BLANK_PLAYER_IDENTIFIER:   _xPlayer = new BlankPlayer(Cell.X);
                                            break;
            default:                        _xPlayer = new GUIPlayer(Cell.X, true);
                                            _adapter.setGUI(true);
                                            break;
	}
        
        switch (oPlayerIdentifier) {
            case GUI_PLAYER_IDENTIFIER:     if (xPlayerIdentifier == GUI_PLAYER_IDENTIFIER) {
                                                _oPlayer = new GUIPlayer(Cell.O, false);
                                            } else {
                                                _oPlayer = new GUIPlayer(Cell.O, true);
                                            }
                                            _adapter.setGUI(true);
                                            break;
            case BLANK_PLAYER_IDENTIFIER:   _oPlayer = new BlankPlayer(Cell.O);
                                            break;
            default:                        _oPlayer = new GUIPlayer(Cell.O);
                                            _adapter.setGUI(true);
                                            break;
	}
    }
    
    /**
     * Copy Constructor for Game object
     * @param g a game
     */
    public Game(Game g) {
        _settings = g._settings;
        
        _logic = g._logic;
        
        _adapter = g._adapter;
        
        _status = g._status;
        
        /*
         * The new game should be with "Blank" players, so it won't disturb the
         * current game
         */
        _xPlayer = new BlankPlayer(Cell.X);
        _oPlayer = new BlankPlayer(Cell.O);
    }
    
    /**
     * The function that runs the game
     */
    public void run() {
        //Changing the status
        _status = GameStatus.InProgress;
        
        //Temp variables to use later
        boolean toCheckOther = false;
        Point move = new Point(-1, -1, PointType.Board);
        String tempString;
        
        /*
         * To make the code more readable, the players are separeted
         * in the class defenition, but here, in order to reduce code
         * they will be joined in an array, and the current player
         * is defined by its index in the array
         */
        Player[] thePlayers = new Player[] {_xPlayer, _oPlayer};
        int currPlayer;
        switch (_settings.getStartingPlayer()) {
            case X:     currPlayer = 0;
                        break;
            case O:     currPlayer = 1;
                        break;
            default:    currPlayer = 0;
        }
        
        while (_status == GameStatus.InProgress) {
            /*
            * A temp variable to hold
            * the options to place Cells
            */
            HashSet<Point> tempOptions;
            
            //The other player's index
            int otherPlayer = (currPlayer + 1) % 2;
            
            //Printing the board, and letting the curr player play
            thePlayers[currPlayer].sendMessage("Current board:");
            thePlayers[currPlayer].sendMessage("");
            String boardToString = _logic.boardToString();
            thePlayers[currPlayer].sendMessage(boardToString);
            
            //Changing the curr player (GUI Only!)
            _adapter.changeCurrPlayer(thePlayers[currPlayer]);
            
            if (!move.equals(NO_MOVE_POINT)) {
                move.alignToPrint();
                tempString = thePlayers[otherPlayer].getType().toString() + " played ";
                tempString += move.toString();
                thePlayers[currPlayer].sendMessage(tempString);
                thePlayers[currPlayer].sendMessage("");
            }
            tempString = thePlayers[currPlayer].getType().toString();
            tempString += ": It's your move";
            thePlayers[currPlayer].sendMessage(tempString);
            
            //Calc'ing the moves available to the curr player
            tempOptions = _logic.calcMoves(thePlayers[currPlayer].getType());
            
            //Showing the options (GUI Only!)
            _adapter.markOptions(tempOptions);
            
            //If there are no options and the board is full - the game is over
            if (tempOptions.isEmpty() && _logic.quickWinCheck()) {
                _status = _logic.checkWinning();
                break;
            }
            
            /*
             * If there are no options and the other player didn't have options
             * then the game is over
             */
            if (tempOptions.isEmpty() && toCheckOther) {
                _status = _logic.checkWinning();
                break;
            }
            
            //Letting him choose
            move = thePlayers[currPlayer].makeMove(tempOptions);
            if (move.equals(END_GAME_POINT)) {
                thePlayers[otherPlayer].sendMessage("Game Ended!");
                _status = _logic.checkWinning();
                break;
            }
            if (move.equals(NO_MOVE_POINT)) {
                toCheckOther = true;
            } else {
                //If he chose something, then it will be put
                toCheckOther = false;
                move.alignToBoard();
                _logic.put(move, thePlayers[currPlayer].getType());
            }
            currPlayer = otherPlayer;
        }
        
        //Checking if the game is over
        switch (_status) {
            case XWins:     _xPlayer.sendMessage("X player won!");
                            _oPlayer.sendMessage("X player won!");
                            break;
            case OWins:     _xPlayer.sendMessage("O player won!");
                            _oPlayer.sendMessage("O player won!");
                            break;
            default:        _xPlayer.sendMessage("It's a tie!");
                            _oPlayer.sendMessage("It's a tie!");
                            break;
        }
        
        //Closing the GUI (GUI Only!)
        _adapter.endGUI();
    }
}
