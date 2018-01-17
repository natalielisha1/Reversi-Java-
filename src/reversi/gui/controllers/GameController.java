package reversi.gui.controllers;

import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;
import java.util.HashSet;

import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import reversi.game.Board;
import reversi.gui.GUIAdapter;
import reversi.game.Point;

/**
 *
 * @author Ofek Segal and Natalie Elisha
 */
public class GameController implements Initializable{
    @FXML
    private BorderPane root;
    
    @FXML
    private GridPane board;
    
    @FXML
    private Text currPlayerText;
    
    @FXML
    private Text whitePlayerScoreText;
    
    @FXML
    private Text blackPlayerScoreText;
    
    private BoardController boardController;
    
    private GUIAdapter adapter;

    /**
     * Initializing the game
     * @param location unused
     * @param resources unused
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Creating the board animation
        boardController = new BoardController();
        boardController.setPrefHeight(500);
        boardController.setPrefWidth(500);
        board.getChildren().add(0, boardController);
        
        //Getting the link to the GUI adapter
        adapter = GUIAdapter.getInstance(true);
        
        //Setting this game controller as the adapter's game controller
        adapter.setController(this);
        
        
        boardController.setOnMouseClicked((MouseEvent event) -> {
            Point thePoint = boardController.calcMouseClick(event);
            adapter.setPoint(thePoint);
        });
        
        
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 240 - 20;
            boardController.setPrefWidth(boardNewWidth);
        });
        
        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewHeight = newValue.doubleValue() - 20;
            boardController.setPrefHeight(boardNewHeight);
        });
        
    }
    
    /**
     * Redrawing the board
     * @param b the board to draw
     */
    public void redraw(Board b) {
        boardController.draw(b);
    }
    
    /**
     * Updating the current player's text
     * @param player the current player
     */
    public void changeCurrentPlayer(String player) {
        currPlayerText.setText(player);
    }
    
    /**
     * Updating the white player's score
     * @param score the new score
     */
    public void changeWhitePlayerScore(String score) {
        whitePlayerScoreText.setText(score);
    }
    
    /**
     * Updating the black player's score
     * @param score the new score
     */
    public void changeBlackPlayerScore(String score) {
        blackPlayerScoreText.setText(score);
    }
    
    /**
     * Marking the options on the board
     * @param options the move options
     */
    public void markOptions(HashSet<Point> options) {
        boardController.markOptions(options);
    }

    /**
     * Returning to the main menu
     * in the end of the game
     */
    public void returnToMenu() {
        try {
            //Loading the main menu
            FXMLLoader menuLoader = new FXMLLoader(getClass().getClassLoader().getResource("reversi/gui/fxml/MenuFXML.fxml"));
            Parent menuParent = menuLoader.load();
            Scene menuScene = new Scene(menuParent);
            Stage theStage = (Stage) root.getScene().getWindow();
            theStage.setScene(menuScene);
        }   catch (IOException ex) {
            //Weird Error - if happens -> debug
            System.out.println("ReturnToMenu error:");
            ex.printStackTrace();
        }
    }
}
