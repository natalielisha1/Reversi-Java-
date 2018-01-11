/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi.fxml;

import java.net.URL;

import java.util.ResourceBundle;
import java.util.HashSet;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;

import reversi.Board;
import reversi.GUIAdapter;
import reversi.Point;

/**
 *
 * @author ofek_
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boardController = new BoardController();
        boardController.setPrefHeight(500);
        boardController.setPrefWidth(500);
        board.getChildren().add(0, boardController);
        
        adapter = GUIAdapter.getInstance(true);
        adapter.setController(this);
        
        /*
        boardController.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Point thePoint = boardController.calcMouseClick(event);
                adapter.setPoint(thePoint);
            }
        });
        */
        
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 240 - 20;
            boardController.setPrefWidth(boardNewWidth);
        });
        
        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewHeight = newValue.doubleValue() - 20;
            boardController.setPrefHeight(boardNewHeight);
        });
        
    }
    
    public void redraw(Board b) {
        boardController.draw(b);
    }
    
    public void changeCurrentPlayer(String player) {
        currPlayerText.setText(player);
    }
    
    public void changeWhitePlayerScore(String score) {
        whitePlayerScoreText.setText(score);
    }
    
    public void changeBlackPlayerScore(String score) {
        blackPlayerScoreText.setText(score);
    }
    
    public void markOptions(HashSet<Point> options) {
        boardController.markOptions(options);
    }
}
