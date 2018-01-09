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
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 *
 * @author ofek_and_natalie
 */
public class MenuFXMLController implements Initializable {
	
	@FXML
	private VBox menuRoot;
    
    @FXML
    private Label label;

    @FXML
    private Button startGameBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private Button exitBtn;
    
//    @FXML
//    private void handleButtonAction(ActionEvent event) {
//        System.out.println("You clicked me!");
//        label.setText("Hello World!");
//    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void exitProgram(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void changeSettings(ActionEvent event) {
//        Parent root = FXMLLoader.load(getClass().getResource("fxml/SettingsFXML.fxml"));
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
    }

    @FXML
    void startGame(ActionEvent event) {

    }
    
}
