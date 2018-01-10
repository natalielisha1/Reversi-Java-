package reversi.fxml;

import reversi.*;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;

import java.util.HashSet;

public class BoardController extends GridPane {
    private final GameSettings gameSettings;
    private final GUIAdapter adapter;
    
    private final ImageView[][] theCells;
    private final int theSize;
    
    private boolean firstTime = true;
    
    private Board lastBoard;

    public BoardController() {
        gameSettings = GameSettings.getInstance();
        adapter = GUIAdapter.getInstance(true);
        
        theSize = gameSettings.getBoardSize();
        theCells = new ImageView[theSize][theSize];
        
        //Temporary board
        lastBoard = new Board(theSize);
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BoardFXML.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (Exception ex) {
            //Weird exception - if happens -> debug
            System.out.println("BoardController error:");
            ex.printStackTrace();
        }
    }
    
    public void draw(Board theBoard) {
        lastBoard = theBoard;
        
        this.draw();
   }
    
    public void draw() {
        int height = (int) this.getPrefHeight();
        int width = (int) this.getPrefWidth();
        
        int cellHeight = height / theSize;
        int cellWidth = width / theSize;
        
        for (int i = 0; i < theSize; i++) {
            for (int j = 0; j < theSize; j++) {
                Cell currCell = lastBoard.getCell(new Point(i, j));
                if (firstTime) {
                    final int row = i;
                    final int col = j;
                    theCells[i][j] = new ImageView();
                    
                    //Should find a way to do it without attaching a listener on every imageview
                    theCells[i][j].setOnMouseClicked((MouseEvent event) -> {
                        Point newPoint = new Point(row, col, PointType.Board);
                        adapter.setPoint(newPoint);
                    });
                    this.add(theCells[i][j], j, i);
                }
                switch (currCell) {
                    case O:     theCells[i][j].setImage(gameSettings.getOColor().getCell());
                                break;
                    case X:     theCells[i][j].setImage(gameSettings.getXColor().getCell());
                                break;
                    default:    //System.out.println(getClass().getClassLoader().getResource("reversi/res/BlankCell.png"));
                                theCells[i][j].setImage(new Image(getClass().getClassLoader().getResourceAsStream("reversi/res/BlankCell.png")));
                                break;
                }
                theCells[i][j].setPreserveRatio(false);
                
                theCells[i][j].setFitHeight(cellHeight);
                theCells[i][j].setFitWidth(cellWidth);
            }
        }
        firstTime = false;
    }
    
    public void markOptions(HashSet<Point> options) {
        for (Point option : options) {
            option.alignToBoard();
            theCells[option.getX()][option.getY()].setImage(new Image(getClass().getClassLoader().getResourceAsStream("reversi/res/ReadyCell.png")));
        }
    }

    public Point calcMouseClick(MouseEvent event) {
        double mouseX = event.getScreenX();
        double mouseY = event.getScreenY();
        
        Bounds boundsInScreen = this.localToScreen(this.getBoundsInLocal());
        
        int height = (int) boundsInScreen.getHeight();
        int width = (int) boundsInScreen.getWidth();
        
        int cellHeight = height / theSize;
        int cellWidth = width / theSize;
        
        double row = mouseX - boundsInScreen.getMinX();
        row /= cellWidth;
        row = Math.abs(row);
        
        int intRow = (int) Math.ceil(row);
        
        double col = mouseY - boundsInScreen.getMinY();
        col /= cellHeight;
        col = Math.abs(col);
        
        int intCol = (int) Math.ceil(col) - 2;
        
        return new Point(intRow, intCol, PointType.Board);
    }
}
