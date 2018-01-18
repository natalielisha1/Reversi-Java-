package reversi.gui.controllers;

import java.io.IOException;
import reversi.gui.GameSettings;
import reversi.gui.GUIAdapter;
import reversi.game.Cell;
import reversi.game.PointType;
import reversi.game.Point;
import reversi.game.Board;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;

import java.util.HashSet;
/**
 * Names: Natalie Elisha and Ofek Segal.
 * IDs: 209475458, 315638288
 */
public class BoardController extends GridPane {
    //The game settings
    private final GameSettings gameSettings;
    
    //The link to the GUI adapter
    private final GUIAdapter adapter;
    
    //The cells matrix
    private final ImageView[][] theCells;
    
    //The board's size
    private final int theSize;
    
    //The last drawn board
    private Board lastBoard;

    /**
     * BoardController constructor
     */
    public BoardController() {
        //Getting the game settings
        gameSettings = GameSettings.getInstance();
        
        //Getting the GUI adapter
        adapter = GUIAdapter.getInstance(true);
        
        //Getting the board's size
        theSize = gameSettings.getBoardSize();
        
        //Setting the cells matrix
        theCells = new ImageView[theSize][theSize];
        
        //Loading the fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("reversi/gui/fxml/BoardFXML.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            //Weird exception - if happens -> debug
            System.out.println("BoardController error:");
            ex.printStackTrace();
        }
        
        //Defining the board and the cells
        this.setHgap(0);
        this.setVgap(0);
        
        for (int i = 0; i < theSize; i++) {
            for (int j = 0; j < theSize; j++) {
                final int row = i;
                final int col = j;
                theCells[i][j] = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("reversi/gui/res/BlankCell.png")));
                
                theCells[i][j].setPreserveRatio(false);
                
                theCells[i][j].fitHeightProperty().bind(this.prefHeightProperty().divide(theSize));
                theCells[i][j].fitWidthProperty().bind(this.prefWidthProperty().divide(theSize));
                
//                //Setting a "OnMouseClicked" event to each cell
//                theCells[i][j].setOnMouseClicked((MouseEvent event) -> {
//                    Point newPoint = new Point(row, col, PointType.Board);
//                    adapter.setPoint(newPoint);
//                });
                this.add(theCells[i][j], j, i);
            }
        }
    }
    
    /**
     * Drawing the given board
     * @param theBoard the board to draw on screen
     */
    public void draw(Board theBoard) {
        lastBoard = theBoard;
        
        this.draw();
   }
    
    /**
     * Drawing the last given board
     */
    public void draw() {
        if (lastBoard == null) {
            return;
        }
        
        for (int i = 0; i < theSize; i++) {
            for (int j = 0; j < theSize; j++) {
                Cell currCell = lastBoard.getCell(new Point(i, j));
                switch (currCell) {
                    case O:     theCells[i][j].setImage(gameSettings.getOColor().getCell());
                                break;
                    case X:     theCells[i][j].setImage(gameSettings.getXColor().getCell());
                                break;
                    default:    theCells[i][j].setImage(new Image(getClass().getClassLoader().getResourceAsStream("reversi/gui/res/BlankCell.png")));
                                break;
                }
            }
        }
    }
    
    /**
     * Marking the move options on the board
     * @param options the move options
     */
    public void markOptions(HashSet<Point> options) {
        for (Point option : options) {
            /*
             * Fixing a compatibility issue, from the Console
             * display - the Console displayed the locations
             * in the range [1,size], but the program
             * uses the [0,size-1] range
             */
            option.alignToBoard();
            
            theCells[option.getX()][option.getY()].setImage(new Image(getClass().getClassLoader().getResourceAsStream("reversi/gui/res/ReadyCell.png")));
        }
    }

    /**
     * Calc'ing the point that was clicked
     * @param event the mouse event
     * @return the point that was clicked
     */
    public Point calcMouseClick(MouseEvent event) {
        double mouseX = event.getSceneX();
        double mouseY = event.getSceneY();
        
        int height = (int) this.getPrefHeight();
        int width = (int) this.getPrefWidth();
        
        int cellHeight = height / theSize;
        int cellWidth = width / theSize;
        
        Bounds boundsInScence = this.localToScene(this.getBoundsInLocal());
        
        double row = mouseY - boundsInScence.getMinY();
        row /= cellHeight;
        row = Math.abs(row);

        int intRow = (int) Math.ceil(row) - 1;

        double col = mouseX - boundsInScence.getMinX();
        col /= cellWidth;
        col = Math.abs(col);

        int intCol = (int) Math.ceil(col) - 1;
        
        return new Point(intRow, intCol, PointType.Board);
    }
}
