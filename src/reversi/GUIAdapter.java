/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.HashSet;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

import reversi.fxml.GameController;

/**
 * @author Ofek Segal and Natalie Elisha 
 */
public class GUIAdapter {
    //The Singleton instance
    private static GUIAdapter _instance = null;
    
    private GameController _controller;
    
    private Point _lastPoint;
    
    private boolean _request = false;
    private boolean _fulfiled = false;
    
    private final Object _lock = new Object();
    
    private boolean _isGUI = false;
    
    /**
     * The function does nothing currently
     */
    private GUIAdapter() {
        //Nothing right now
    }
    
    /**
     * The function returns an instance of GUI adapter
     * @return a GUI adapter object
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
     * @param gui - a boolean value
     * @return a GUI adapter object
     */
    public static GUIAdapter getInstance(boolean gui) {
        if (_instance == null) {
            _instance = new GUIAdapter();
        }
        _instance._isGUI = gui;
        return _instance;
    }
    
    /**
     * The function sets the isGui boolean member
     * @param gui - a boolean value
     */
    public void setGUI(boolean gui) {
        _isGUI = gui;
    }
    
    /**
     * The function sets the game controller
     * @param controler - a new game controller
     */
    public void setController(GameController controller) {
        _controller = controller;
    }
    
    
    /**
     * The function requests to make a move and
     * returns a point according to the ability to make
     * a move in the game
     * @return a point
     */
    public Point requestPoint() {
        _request = true;
        synchronized(_lock) {
            while (!_fulfiled) {
                try {
                    _lock.wait();
                } catch (InterruptedException ex) {
                    _request = false;
                    _fulfiled = false;
                    Thread.currentThread().interrupt();
                    return Game.END_GAME_POINT;
                }
            }
        }
        
        _request = false;
        _fulfiled = false;
        return _lastPoint;
    }
    
    /**
     * The function sets a new point if there's a request
     * @param newPoint - a point
     */
    public void setPoint(Point newPoint) {
        if (!_request) {
            //Right now, we don't do anything if nobody asks
            return;
        }
        _lastPoint = newPoint;
        _fulfiled = true;
        synchronized(_lock) {
            _lock.notifyAll();
        }
    }
    
    /**
     * The function draws the game board
     * @param b - a board
     */
    public void draw(Board b) {
        if (_isGUI && _controller != null) {
            _controller.redraw(b);
        }
    }
    
    /**
     * The function changes the current player given
     * another player
     * @param p - a player
     */
    public void changeCurrPlayer(Player p) {
        if (_isGUI && _controller != null) {
            _controller.changeCurrentPlayer(p.toString());
        }
    }
    
    /**
     * The function sets the white player's score in
     * the game
     * @param score - a new score, integer
     */
    public void changeWhitePlayerScore(int score) {
        if (_isGUI && _controller != null) {
            _controller.changeWhitePlayerScore(Integer.toString(score));
        }
    }
    
    /**
     * The function sets the black player's score
     * given a new score
     * @param score - a new score, integer
     */
    public void changeBlackPlayerScore(int score) {
        if (_isGUI && _controller != null) {
            _controller.changeBlackPlayerScore(Integer.toString(score));
        }
    }
    
    /**
     * The function sets the information of the game's end alert
     * @param title - a string as the title of the alert
     * @param content - the content of the alert
     */
    public void infoAlert(String title, String content) {
        if (_isGUI) {
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
     * @param options - a hash set of point
     */
    public void markOptions(HashSet<Point> options) {
        if (_isGUI && _controller != null) {
            _controller.markOptions(options);
        }
    }
    
    /**
     * The function stops the animation
     */
    public void stop() {
        synchronized (_lock) {
            _lock.notifyAll();
        }
    }
    
    /**
     * The function terminates the GUI functionality
     */
    public void endGUI() {
        if (_isGUI && _controller != null) {
            Platform.runLater(() -> _controller.returnToMenu());
        }
    }
}
