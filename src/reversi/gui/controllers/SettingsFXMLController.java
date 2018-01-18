package reversi.gui.controllers;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import reversi.game.Cell;
import reversi.gui.GameSettings;
import reversi.gui.ODiskColor;
import reversi.gui.XDiskColor;
/**
 * Names: Natalie Elisha and Ofek Segal.
 * IDs: 209475458, 315638288
 */
public class SettingsFXMLController implements Initializable {
	
    @FXML
    private ChoiceBox<String> startingPlayerChoice;

    @FXML
    private ChoiceBox<String> whitePlayerChoice;

    @FXML
    private ChoiceBox<String> blackPlayerChoice;

    @FXML
    private ChoiceBox<String> boardSizeChoice;

    @FXML
    private ImageView whitePlayerDiskImage;

    @FXML
    private ImageView blackPlayerDiskImage;
    
    @FXML
    private Button saveBtn;

    private GameSettings gameSettings;

    @FXML
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //Getting the settings object
        gameSettings = GameSettings.getInstance();
        
        //Setting the options on the screen:
        
        //The starting player options
        startingPlayerChoice.setItems(FXCollections.observableArrayList("Black Player", "White Player"));
        switch (gameSettings.getStartingPlayer()) {
            case X:     startingPlayerChoice.getSelectionModel().select(0);
                        break;
            case O:     startingPlayerChoice.getSelectionModel().select(1);
                        break;
        }
        
        //The white player's disk color options
        whitePlayerChoice.setItems(FXCollections.observableArrayList(ODiskColor.getStrings()));
        whitePlayerChoice.getSelectionModel().select(gameSettings.getOColor().ordinal());
        whitePlayerChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ob, String value, String newValue) {
                whitePlayerDiskImage.setImage(ODiskColor.getEnum(newValue).getDisk());
            }
        });
        //The white player's disk color preview
        whitePlayerDiskImage.setImage(gameSettings.getOColor().getDisk());
        
        //The black player's disk color options
        blackPlayerChoice.setItems(FXCollections.observableArrayList(XDiskColor.getStrings()));
        blackPlayerChoice.getSelectionModel().select(gameSettings.getXColor().ordinal());
        blackPlayerChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ob, String value, String newValue) {
                blackPlayerDiskImage.setImage(XDiskColor.getEnum(newValue).getDisk());
            }
        });
        //The black player's disk color options
        blackPlayerDiskImage.setImage(gameSettings.getXColor().getDisk());
        
        
        //The board size options
        ArrayList<String> availableBoardSizes = gameSettings.availableBoardSizes();
        
        boardSizeChoice.setItems(FXCollections.observableArrayList(availableBoardSizes));
        boardSizeChoice.getSelectionModel().select(gameSettings.getBoardSize() - 4);
        
        //Making the saveBtn save the settings to the file, and return to the main menu
        saveBtn.setOnAction((event) -> {
            switch ((String)startingPlayerChoice.getValue()) {
                case "Black Player":    gameSettings.setStartingPlayer(Cell.X);
                                        break;
                case "White Player":    gameSettings.setStartingPlayer(Cell.O);
                                        break;
            }
            gameSettings.setOColor(ODiskColor.getEnum((String) whitePlayerChoice.getValue()));
            gameSettings.setXColor(XDiskColor.getEnum((String) blackPlayerChoice.getValue()));
            gameSettings.setBoardSize(Integer.parseInt((String) boardSizeChoice.getValue()));
            gameSettings.saveToFile();
            returnToMenu();
        });
    }

    private void returnToMenu() {
        try {
            //Loading the menu
            FXMLLoader menuLoader = new FXMLLoader(getClass().getClassLoader().getResource("reversi/gui/fxml/MenuFXML.fxml"));
            Parent menuParent = menuLoader.load();
            Scene menuScene = new Scene(menuParent);
            Stage theStage = (Stage) saveBtn.getScene().getWindow();
            theStage.setScene(menuScene);
        }   catch (IOException ex) {
            //Weird Error - if happens -> debug
            System.out.println("ChangeSettings error:");
            ex.printStackTrace();
        }
    }
}
