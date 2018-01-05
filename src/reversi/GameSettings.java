/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author OfekSegal
 */
public class GameSettings implements Serializable {
    private Cell _startingPlayer;
    
    private DiskColor _xPlayerColor;
    private DiskColor _oPlayerColor;
    
    private int _boardSize;
    
    public GameSettings() {
        _startingPlayer = Cell.X;
        _xPlayerColor = DiskColor.BLACK;
        _oPlayerColor = DiskColor.WHITE;
        _boardSize = 8;
    }
    
    public Cell getStartingPlayer() {
        return _startingPlayer;
    }
    
    public void setStartingPlayer(Cell newStarting) {
        _startingPlayer = newStarting;
    }
    
    public DiskColor getXColor() {
        return _xPlayerColor;
    }
    
    public void setXColor(DiskColor newColor) {
        _xPlayerColor = newColor;
    }
    
    public DiskColor getOColor() {
        return _oPlayerColor;
    }
    
    public void setOColor(DiskColor newColor) {
        _oPlayerColor = newColor;
    }
    
    public int getBoardSize() {
        return _boardSize;
    }
    
    public void setBoardSize(int newSize) {
        _boardSize = newSize;
    }

    public static GameSettings loadFromFile() {
        try (FileInputStream fileIn = new FileInputStream("settings.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn)) {
            GameSettings loaded = (GameSettings) in.readObject();
            in.close();
            fileIn.close();
            return loaded;
        } catch (Exception ex) {
            return new GameSettings();
        }
    }

    public void saveToFile() {
        try (FileOutputStream fileOut = new FileOutputStream("settings.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (Exception ex) {
            //Unknown Error, printing to console
            System.out.println("saveToFile failed with " + ex);
        }
    }
}
