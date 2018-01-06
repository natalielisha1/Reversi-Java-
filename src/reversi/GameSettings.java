/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.PrintWriter;

/**
 *
 * @author OfekSegal
 */
public class GameSettings {
    //The Singleton instance
    private static GameSettings _instance = null;
    
    private final static String SETTINGS_FILE_NAME = "settings.ond";
    
    private Cell _startingPlayer;
    
    private DiskColor _xPlayerColor;
    private DiskColor _oPlayerColor;
    
    private int _boardSize;
    
    private GameSettings() {
        _startingPlayer = Cell.X;
        _xPlayerColor = DiskColor.BLACK;
        _oPlayerColor = DiskColor.WHITE;
        _boardSize = 8;
    }
    
    public static GameSettings getInstance() {
        if (_instance == null) {
            _instance = loadFromFile();
        }
        return _instance;
    }
    
    private static GameSettings loadFromFile() {
        try (FileReader file = new FileReader(SETTINGS_FILE_NAME);
             BufferedReader reader = new BufferedReader(file)) {
            GameSettings newInstance = new GameSettings();
            newInstance._startingPlayer = Cell.valueOf(reader.readLine());
            newInstance._xPlayerColor = DiskColor.valueOf(reader.readLine());
            newInstance._oPlayerColor = DiskColor.valueOf(reader.readLine());
            newInstance._boardSize = Integer.getInteger(reader.readLine());
            return newInstance;
        } catch (Exception ex) {
            return new GameSettings();
        }
    }

    public void saveToFile() {
        try (FileWriter file = new FileWriter(SETTINGS_FILE_NAME);
             PrintWriter printer = new PrintWriter(file)) {
            printer.println(_startingPlayer.name());
            printer.println(_xPlayerColor.name());
            printer.println(_oPlayerColor.name());
            printer.println(_boardSize);
            printer.close();
            file.close();
        } catch (Exception ex) {
            //Unknown Error, printing to console
            System.out.println("saveToFile failed with " + ex);
        }
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
}
