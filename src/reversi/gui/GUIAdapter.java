package reversi.gui;

import reversi.game.players.Player;
import reversi.game.Game;
import reversi.game.Point;
import reversi.game.Board;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.HashSet;
import java.util.concurrent.Semaphore;

import reversi.gui.controllers.GameController;

/**
 * @author Ofek Segal and Natalie Elisha 
 */
public class GUIAdapter {
    //The Singleton instance
    private static GUIAdapter _instance = null;
    
    //The game controller to send the requests to
    private GameController _controller;
    
    //The point to pass around
    private Point _lastPoint;
    
    //Variables to monitor the point requests
    private boolean _request = false;
    private boolean _fulfiled = false;
    
    //A lock to synchronize the threads
    private final Object _lock = new Object();
    
    //The variable that checks if the display is GUI style
    private boolean _isGUI = false;
    
    /**
     * The function does nothing currently
     */
    private GUIAdapter() {
        //Nothing right now
    }
    
    /**
     * The function returns an instance of GUI adapter
     * @return the GUI adapter object
     */
    public static GUIAdapter getInstance() {
        if (_instance == null) {
            _instance = new GUIAdapter();
        }
        return _instance;
    }
    
    /**
     * The function returns an instance of GUI adapter
     * and sets the isGUI boolean member according the
     * given boolean value
     * @param gui a boolean value
     * @return the GUI adapter object
     */
    public static GUIAdapter getInstance(boolean gui) {
        if (_instance == null) {
            _instance = new GUIAdapter();
        }
        _instance._isGUI = gui;
        return _instance;
    }
    
    /**
     * The function sets the isGUI boolean member
     * @param gui a boolean value
     */
    public void setGUI(boolean gui) {
        _isGUI = gui;
    }
    
    /**
     * The function sets the game controller
     * @param controller the game controller
     */
    public void setController(GameController controller) {
        _controller = controller;
    }
    
    
    /**
     * Getting the next move from the GUI
     * @return a point
     */
    public Point requestPoint() {
        //Raising the request flag
        _request = true;
        
        //Using a synchronized block to sync the threads
        synchronized(_lock) {
            /*
             * The _lock can be notified 
             * because of various things
             * so, stopping to wait only if 
             * the notify was about the point
             * request
             */
            while (!_fulfiled) {
                try {
                    _lock.wait();
                } catch (InterruptedException ex) {
                    //InterruptedException means that the game ended
                    _request = false;
                    _fulfiled = false;
                    Thread.currentThread().interrupt();
                    return Game.END_GAME_POINT;
                }
            }
        }
        //Reseting the flags and returning the point
        _request = false;
        _fulfiled = false;
        return _lastPoint;
    }
    
    /**
     * Setting the next move if there's a request
     * @param newPoint a point
     */
    public void setPoint(Point newPoint) {
        if (!_request) {
            //Right now, we don't do anything if nobody asks
            return;
        }
        //Setting the point
        _lastPoint = newPoint;
        
        //Raising the fulfiled flag
        _fulfiled = true;
        
        //Notifying everyone, and only requestPoint will use the notification
        synchronized(_lock) {
            _lock.notifyAll();
        }
    }
    
    /**
     * The function draws the game board
     * @param b a board
     */
    public void draw(Board b) {
        if (_isGUI && _controller != null) {
            _controller.redraw(b);
        }
    }
    
    /**
     * Updating the current player text
     * @param p the current player
     */
    public void changeCurrPlayer(Player p) {
        if (_isGUI && _controller != null) {
            _controller.changeCurrentPlayer(p.toString());
        }
    }
    
    /**
     * Updating the score of the white player
     * @param score the new score
     */
    public void changeWhitePlayerScore(int score) {
        if (_isGUI && _controller != null) {
            _controller.changeWhitePlayerScore(Integer.toString(score));
        }
    }
    
    /**
     * Updating the score of the black player
     * @param score the new score
     */
    public void changeBlackPlayerScore(int score) {
        if (_isGUI && _controller != null) {
            _controller.changeBlackPlayerScore(Integer.toString(score));
        }
    }
    
    /**
     * Showing a message to the GUI user
     * @param title the title of the alert
     * @param content the content of the alert
     */
    public void infoAlert(String title, String content) {
        if (_isGUI) {
            //Using a semaphore to wait for the user to press "OK"
            Semaphore waiter = new Semaphore(0);
            Platform.runLater(() -> {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle(title);
                alert.setContentText(content);
                alert.showAndWait();
                waiter.release();
            });
            try {
                waiter.acquire();
            } catch (InterruptedException ex) {
                //The game has stopped, so cancel
            }
        }
    }
    
    /**
     * The function marks the options of the current
     * player in the game in the GUI animation
     * @param options set of points
     */
    public void markOptions(HashSet<Point> options) {
        if (_isGUI && _controller != null) {
            _controller.markOptions(options);
        }
    }
    
    /**
     * The function stops the game
     */
    public void stop() {
        synchronized (_lock) {
            _lock.notifyAll();
        }
    }
    
    /**
     * The function terminates the GUI animation
     */
    public void endGUI() {
        if (_isGUI && _controller != null) {
            Platform.runLater(() -> _controller.returnToMenu());
        }
    }
}
