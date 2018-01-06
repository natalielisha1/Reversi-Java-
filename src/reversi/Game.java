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
public class Game {
    public static final char HUMAN_PLAYER_IDENTIFIER = 'h';
    public static final char COMP_PLAYER_IDENTIFIER = 'c';
    public static final char BLANK_PLAYER_IDENTIFIER = 'b';
    public static final char REMOTE_PLAYER_IDENTIFIER = 'r';
    public static final char GUI_PLAYER_IDENTIFIER = 'g';
    
    public static final Point END_GAME_POINT = new Point(-2, -2, PointType.Board);
    public static final Point NO_MOVE_POINT = new Point(-1, -1, PointType.Board);
    
    private GameSettings _settings;
    
    private GameLogic _logic;
    
    private GameStatus _status;
    
    private Player _xPlayer;
    private Player _oPlayer;
    
    public Game() {
        _settings = GameSettings.getInstance();
        
        _logic = new ReversiLogic(_settings.getBoardSize());
        
        _status = GameStatus.NotPlaying;
        
        _xPlayer = new GUIPlayer(Cell.X, true);
        _oPlayer = new GUIPlayer(Cell.O, false);
    }
    
    public Game(char xPlayerIdentifier, char oPlayerIdentifier) {
        _settings = GameSettings.getInstance();
        
        _logic = new ReversiLogic(_settings.getBoardSize());
        
        _status = GameStatus.NotPlaying;
        
        switch (xPlayerIdentifier) {
            case GUI_PLAYER_IDENTIFIER:     _xPlayer = new GUIPlayer(Cell.X, true);
            case BLANK_PLAYER_IDENTIFIER:   _xPlayer = new BlankPlayer(Cell.X);
                                            break;
            /*
            case HUMAN_PLAYER_IDENTIFIER:   _xPlayer = new HumanPlayer(Cell.X);
                                            break;
            case COMP_PLAYER_IDENTIFIER:    _xPlayer = new CompPlayer(Cell.X);
                                            break;
            case REMOTE_PLAYER_IDENTIFIER:  _xPlayer = new RemotePlayer(Cell.X);
                                            break;
            */
            default:                        _xPlayer = new GUIPlayer(Cell.X, true);
                                            break;
	}
        
        switch (oPlayerIdentifier) {
            case GUI_PLAYER_IDENTIFIER:     if (xPlayerIdentifier == GUI_PLAYER_IDENTIFIER) {
                                                _oPlayer = new GUIPlayer(Cell.O, false);
                                            } else {
                                                _oPlayer = new GUIPlayer(Cell.O, true);
                                            }
                                            break;
            case BLANK_PLAYER_IDENTIFIER:   _oPlayer = new BlankPlayer(Cell.O);
                                            break;
            /*
            case HUMAN_PLAYER_IDENTIFIER:   _oPlayer = new HumanPlayer(Cell.O);
                                            break;
            case COMP_PLAYER_IDENTIFIER:    _oPlayer = new CompPlayer(Cell.O);
                                            break;
            
            case REMOTE_PLAYER_IDENTIFIER:  _oPlayer = new RemotePlayer(Cell.O);
                                            break;
            */
            default:                        _oPlayer = new GUIPlayer(Cell.O);
                                            break;
	}
        
        /*
        //If the server placed the players differently, swap them
	if (xPlayerIdentifier == REMOTE_PLAYER_IDENTIFIER) {
		if (_xPlayer.getType() == Cell.O) {
			Player temp = _xPlayer;
			_xPlayer = _oPlayer;
			_xPlayer.setType(Cell.X);
			_oPlayer = temp;
		}
	}
	if (oPlayerIdentifier == REMOTE_PLAYER_IDENTIFIER) {
		if (_oPlayer.getType() == Cell.X) {
			Player temp = _oPlayer;
			_oPlayer = _xPlayer;
			_oPlayer.setType(Cell.O);
			_xPlayer = temp;
		}
	}
        */
    }
    
    public Game(Game g) {
        _settings = g._settings;
        
        _logic = g._logic;
        
        _status = g._status;
        
        _xPlayer = new BlankPlayer(Cell.X);
        _oPlayer = new BlankPlayer(Cell.O);
    }
    
    public void run() {
        if (_status == GameStatus.NotPlaying) {
            _status = GameStatus.InProgress;
        }
        
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
            
            //Printing the board, and letting X play
            thePlayers[currPlayer].sendMessage("Current board:");
            thePlayers[currPlayer].sendMessage("");
            String boardToString = _logic.boardToString();
            _xPlayer.sendMessage(boardToString);
            
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
            //Letting him choose
            move = thePlayers[currPlayer].makeMove(tempOptions);
            if (move.equals(END_GAME_POINT)) {
                thePlayers[otherPlayer].sendMessage("Game Ended!");
                _status = _logic.checkWinning();
                break;
            }
            if (move.equals(NO_MOVE_POINT)) {
                if (toCheckOther) {
                    _status = _logic.checkWinning();
                    break;
                } else {
                    toCheckOther = true;
                }
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
    }
}
