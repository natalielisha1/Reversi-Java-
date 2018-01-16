package reversi.gui.controllers;

import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.application.Platform;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import reversi.gui.GUIAdapter;
import reversi.game.Game;

/**
 *
 * @author Ofek Segal and Natalie Elisha
 */
public class MenuFXMLController implements Initializable {
    @FXML
    private Button startGameBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private Button exitBtn;
    
    private Thread gameThread = null;
    
    /**
     * Initializing the menu scene
     * @param url unused
     * @param rb unused
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Setting the exitBtn action
        exitBtn.setOnAction((event) -> {
            Platform.exit();
            System.exit(0);
        });
    }

    /**
     * Switching to the settings scene
     * @param event unused
     */
    @FXML
    void changeSettings(ActionEvent event) {
        try {
            //Loading the settings scene
            FXMLLoader settingsLoader = new FXMLLoader(getClass().getClassLoader().getResource("reversi/gui/fxml/SettingsFXML.fxml"));
            Parent settingsParent = settingsLoader.load();
            Scene settingsScene = new Scene(settingsParent);
            Stage theStage = (Stage) settingsBtn.getScene().getWindow();
            theStage.setScene(settingsScene);
        }   catch (IOException ex) {
            //Weird Error - if happens -> debug
            System.out.println("ChangeSettings error:");
            ex.printStackTrace();
        }
    }

    /**
     * Switching to the game scene
     * @param event unused
     */
    @FXML
    void startGame(ActionEvent event) {
        Stage theStage = null;
        try {
            //Loading the game scene
            FXMLLoader gameLoader = new FXMLLoader(getClass().getClassLoader().getResource("reversi/gui/fxml/GameFXML.fxml"));
            Parent gameParent = gameLoader.load();
            Scene gameScene = new Scene(gameParent);
            theStage = (Stage) startGameBtn.getScene().getWindow();
            theStage.setScene(gameScene);
        }   catch (Exception ex) {
            //Weird Error - if happens -> debug
            System.out.println("startGame error:");
            ex.printStackTrace();
        }
        //Running the game in a background thread, so the GUI could work
        gameThread = new Thread(){
            @Override
            public void run() {
                Game theGame = new Game(Game.GUI_PLAYER_IDENTIFIER, Game.GUI_PLAYER_IDENTIFIER);
                theGame.run();
            }
        };
        gameThread.start();
        if (theStage != null) {
            //Setting a "onCloseRequest" event, to handle X button correctly
            theStage.setOnCloseRequest((WindowEvent event1) -> {
                //If there is a game running
                if (gameThread != null) {
                    try {
                        //Sending an Interrupt signal
                        gameThread.interrupt();
                        //Getting a link to the GUI adapter
                        GUIAdapter adapter = GUIAdapter.getInstance(true);
                        //Stopping the game
                        adapter.stop();
                        //Waiting for the game thread to finish shutting down
                        gameThread.join();
                    } catch (InterruptedException ex) {
                        //Weird error - if happens -> debug it
                        System.out.println("startGame (kill) error:");
                        ex.printStackTrace();
                    }
                }
                //Finishing the shut down
                Platform.exit();
                System.exit(0);
            });
        }
    }
    
}
