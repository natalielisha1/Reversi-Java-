/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import reversi.fxml.GameController;

/**
 *
 * @author ofek_
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
    
    private GUIAdapter() {
        //Nothing right now
    }
    
    public static GUIAdapter getInstance() {
        if (_instance == null) {
            _instance = new GUIAdapter();
        }
        return _instance;
    }
    
    public static GUIAdapter getInstance(boolean gui) {
        if (_instance == null) {
            _instance = new GUIAdapter();
        }
        _instance._isGUI = gui;
        return _instance;
    }
    
    public void setGUI(boolean gui) {
        _isGUI = gui;
    }
    
    public void setController(GameController controller) {
        _controller = controller;
    }
    
    
    public Point requestPoint() {
        _request = true;
        synchronized(_lock) {
            while (!_fulfiled) {
                try {
                    _lock.wait();
                } catch (InterruptedException ex) {
                    //Weird Error - if happens, debug it
                    System.out.println("request point error - " + ex.getLocalizedMessage());
                }
            }
        }
        _request = false;
        _fulfiled = false;
        return _lastPoint;
    }
    
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
    
    public void draw(Board b) {
        if (_isGUI && _controller != null) {
            _controller.redraw(b);
        }
    }
    
    public void changeCurrPlayer(Player p) {
        if (_isGUI && _controller != null) {
            _controller.changeCurrentPlayer(p.toString());
        }
    }
    
    public void changeWhitePlayerScore(int score) {
        if (_isGUI && _controller != null) {
            _controller.changeWhitePlayerScore(Integer.toString(score));
        }
    }
    
    public void changeBlackPlayerScore(int score) {
        if (_isGUI && _controller != null) {
            _controller.changeBlackPlayerScore(Integer.toString(score));
        }
    }
    
    public void infoAlert(String title, String content) {
        if (_isGUI) {
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle(title);
                    alert.setContentText(content);
                    alert.showAndWait();
                }
            });
        }
    }
}
