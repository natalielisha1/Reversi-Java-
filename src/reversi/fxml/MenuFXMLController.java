package reversi.fxml;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        exitBtn.setOnAction((event) -> {
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
        try {
            FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("GameFXML.fxml"));
            Parent gameParent = gameLoader.load();
            Scene gameScene = new Scene(gameParent);
            Stage theStage = (Stage) startGameBtn.getScene().getWindow();
            theStage.setScene(gameScene);
        }   catch (Exception ex) {
            //Weird Error - if happens -> debug
            System.out.println("ChangeSettings error:");
            ex.printStackTrace();
        }
        Thread gameThread = new Thread(){
            @Override
            public void run() {
                Game game = new Game(Game.GUI_PLAYER_IDENTIFIER, Game.GUI_PLAYER_IDENTIFIER);
                game.run();
            }
        };
        gameThread.start();
    }
    
}
