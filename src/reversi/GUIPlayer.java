/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

import java.util.Arrays;
import java.util.HashSet;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author ofek_
 */
public class GUIPlayer implements Player{
    private Cell _type;
    private final boolean _toPrint;
    
    private final GUIAdapter _adapter;
    
    public GUIPlayer(Cell type) {
        _type = type;
        _toPrint = true;
        _adapter = GUIAdapter.getInstance(true);
    }
    
    public GUIPlayer(Cell type, boolean toPrint) {
        _type = type;
        _toPrint = toPrint;
        _adapter = GUIAdapter.getInstance(true);
    }

    @Override
    public Point makeMove(HashSet<Point> options) {
        if (options.isEmpty()) {
            _adapter.infoAlert("No Move Available!", "You don't have any move available, next player will now play");
            return Game.NO_MOVE_POINT;
        }
        System.out.println("Options: " + Arrays.toString(options.toArray()));
        Point currPoint = _adapter.requestPoint();
        System.out.println(currPoint);
        while (!options.contains(currPoint)) {
            _adapter.infoAlert("Ilegal Move!", "That move is not available to you, please choose a different option");
            currPoint = _adapter.requestPoint();
            System.out.println(currPoint);
        }
        return currPoint;
    }

    @Override
    public void sendMessage(String message) {
        if (_toPrint) {
            //Print the message (if it's something to show)
            if (message.contains("won") || message.contains("tie")) {
                message = message.replace("X", "Black");
                message = message.replace("O", "White");
                
                _adapter.infoAlert("Game Over!", message);
            }
        }
    }

    @Override
    public Cell getType() {
        return _type;
    }

    @Override
    public void setType(Cell newType) {
        _type = newType;
    }
    
    @Override
    public String toString() {
        switch (_type) {
            case X:     return "Black";
            case O:     return "White";
            default:    return "ERROR";
        }
    }
}
