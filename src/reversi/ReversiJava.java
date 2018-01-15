package reversi;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author Ofek Segal and Natalie Elisha 
 */
public class ReversiJava extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //The app's title
        stage.setTitle("Reversi");
        
        //Loading necessary fonts
        Font.loadFont(getClass().getResource("fonts/ARDARLING.ttf").toExternalForm(), 0);
        Font.loadFont(getClass().getResource("fonts/ARDELANEY.ttf").toExternalForm(), 0);
        Font.loadFont(getClass().getResource("fonts/ARESSENCE.ttf").toExternalForm(), 0);
        Font.loadFont(getClass().getResource("fonts/ARJULIAN.ttf").toExternalForm(), 0);
        
        Parent root = FXMLLoader.load(getClass().getResource("fxml/MenuFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
