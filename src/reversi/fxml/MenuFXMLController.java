package reversi.fxml;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import reversi.GUIAdapter;
import reversi.Game;

/**
 *
 * @author ofek_and_natalie
 */
public class MenuFXMLController implements Initializable {
    @FXML
    private Button startGameBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private Button exitBtn;
    
    private Thread gameThread = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        exitBtn.setOnAction((event) -> {
            Platform.exit();
            System.exit(0);
        });
    }

    @FXML
    void changeSettings(ActionEvent event) {
        try {
            FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("SettingsFXML.fxml"));
            Parent settingsParent = settingsLoader.load();
            Scene settingsScene = new Scene(settingsParent);
            Stage theStage = (Stage) settingsBtn.getScene().getWindow();
            theStage.setScene(settingsScene);
        }   catch (Exception ex) {
            //Weird Error - if happens -> debug
            System.out.println("ChangeSettings error:");
            ex.printStackTrace();
        }
    }

    @FXML
    void startGame(ActionEvent event) {
        Stage theStage = null;
        try {
            FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("GameFXML.fxml"));
            Parent gameParent = gameLoader.load();
            Scene gameScene = new Scene(gameParent);
            theStage = (Stage) startGameBtn.getScene().getWindow();
            theStage.setScene(gameScene);
        }   catch (Exception ex) {
            //Weird Error - if happens -> debug
            System.out.println("startGame error:");
            ex.printStackTrace();
        }
        gameThread = new Thread(){
            @Override
            public void run() {
                Game theGame = new Game(Game.GUI_PLAYER_IDENTIFIER, Game.GUI_PLAYER_IDENTIFIER);
                theGame.run();
            }
        };
        gameThread.start();
        if (theStage != null) {
            theStage.setOnCloseRequest((WindowEvent event1) -> {
                if (gameThread != null) {
                    try {
                        gameThread.interrupt();
                        GUIAdapter adapter = GUIAdapter.getInstance(true);
                        adapter.stop();
                        gameThread.join();
                    } catch (Exception ex) {
                        //Weird error - if happens -> debug it
                        System.out.println("startGame (kill) error:");
                        ex.printStackTrace();
                    }
                }
                Platform.exit();
                System.exit(0);
            });
        }
    }
    
}
