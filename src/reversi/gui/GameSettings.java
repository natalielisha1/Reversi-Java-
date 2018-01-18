package reversi.gui;

import reversi.game.Cell;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 * Names: Natalie Elisha and Ofek Segal.
 * IDs: 209475458, 315638288
 */
public class GameSettings {
    //The Singleton instance
    private static GameSettings _instance = null;
    
    //The settings file name
    private final static String SETTINGS_FILE_NAME = "settings.ond";
    
    //The settings variables
    private Cell _startingPlayer;
    
    private XDiskColor _xPlayerColor;
    private ODiskColor _oPlayerColor;
    
    private int _boardSize;
    
    /**
     * Constructor function for GameSettings
     */
    private GameSettings() {
        _startingPlayer = Cell.X;
        _xPlayerColor = XDiskColor.Black;
        _oPlayerColor = ODiskColor.White;
        _boardSize = 8;
    }
    
    /**
     * The function loads the game settings instance
     * and returns it (in case it hadn't been loaded)
     * @return a game settings instance
     */
    public static GameSettings getInstance() {
        if (_instance == null) {
            _instance = loadFromFile();
        }
        return _instance;
    }
    
    /**
     * The function loads the game settings from
     * the settings file
     * @return a game settings instance
     */
    private static GameSettings loadFromFile() {
        try (FileReader file = new FileReader(SETTINGS_FILE_NAME);
             BufferedReader reader = new BufferedReader(file)) {
            GameSettings newInstance = new GameSettings();
            newInstance._startingPlayer = Cell.valueOf(reader.readLine());
            newInstance._xPlayerColor = XDiskColor.valueOf(reader.readLine());
            newInstance._oPlayerColor = ODiskColor.valueOf(reader.readLine());
            newInstance._boardSize = Integer.parseInt(reader.readLine());
            return newInstance;
        } catch (Exception ex) {
            return new GameSettings();
        }
    }

    /**
     * The function saving the settings to the file
     */
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
    
    /**
     * The function returns the starting player
     * of the game
     * @return the type of cell of the starting player
     */
    public Cell getStartingPlayer() {
        return _startingPlayer;
    }
    
    /**
     * The function sets the starting player
     * of the game
     * @param newStarting the starting player
     */
    public void setStartingPlayer(Cell newStarting) {
        _startingPlayer = newStarting;
    }
    
    /**
     * The function returns the disk color of the
     * X player in the game
     * @return a color
     */
    public XDiskColor getXColor() {
        return _xPlayerColor;
    }
    
    /**
     * The function sets the color of the X player's
     * disk
     * @param newColor a new color for the disk
     */
    public void setXColor(XDiskColor newColor) {
        _xPlayerColor = newColor;
    }
    
    /**
     * The function returns the disk color of
     * the O player in the game
     * @return a color
     */
    public ODiskColor getOColor() {
        return _oPlayerColor;
    }
    
    /**
     * The function sets the color of the O player's
     * disk
     * @param newColor a new color for a disk
     */
    public void setOColor(ODiskColor newColor) {
        _oPlayerColor = newColor;
    }
    
    /**
     * The function returns the game board's size
     * @return size of board
     */
    public int getBoardSize() {
        return _boardSize;
    }
    
    /**
     * The function sets the size of the game
     * board
     * @param newSize - an integer 
     */
    public void setBoardSize(int newSize) {
        _boardSize = newSize;
    }

    /**
     * The function returns the available sizes
     * of the game board
     * @return an array list of strings
     */
    public ArrayList<String> availableBoardSizes() {
        ArrayList<String> toReturn = new ArrayList<>();
        for (int i = 4; i <= 20; i++) {
            toReturn.add(Integer.toString(i));
        }
        return toReturn;
    }
}
