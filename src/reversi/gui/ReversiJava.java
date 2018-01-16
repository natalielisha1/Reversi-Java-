package reversi.gui;

/*
 * In this project we have used several images,
 * and here is the list of credits (in name: site format):
 * Ted Mosby:               http://how-i-met-your-mother.wikia.com
 * Barney Stinson:          http://how-i-met-your-mother.wikia.com
 * Lily Aldrin:             http://how-i-met-your-mother.wikia.com
 * Marshall Erikson:        http://how-i-met-your-mother.wikia.com
 * Robin Scherbatsky:       http://how-i-met-your-mother.wikia.com
 * Yellow Umbrella:         http://www.clipartpanda.com/clipart_images/yellow-umbrella-black-handle-34251159
 * Blue French Horn:        TheBurnsWalker from blenderartists.org
 * Purple Flower:           http://clipart-library.com
 * CPU:                     https://multimediacomputers.wordpress.com
 * Java Logo:               http://fiuxy.com
 * reversi.png:             https://play.google.com/store/apps/details?id=uk.co.aifactory.rr
 * revesiii.png:            https://www.hrkgame.com/en/games/product/reversi/
 * greenBackground.jpg:     https://hdwallsource.com/green-background-21869.html
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
    
    /**
     * Starting the application
     * @param stage the stage to use
     * @throws Exception unused
     */
    @Override
    public void start(Stage stage) throws Exception {
        //The app's title
        stage.setTitle("Reversi");
        
        //Loading necessary fonts
        Font.loadFont(getClass().getClassLoader().getResource("reversi/gui/fonts/ARDARLING.ttf").toExternalForm(), 0);
        Font.loadFont(getClass().getClassLoader().getResource("reversi/gui/fonts/ARDELANEY.ttf").toExternalForm(), 0);
        Font.loadFont(getClass().getClassLoader().getResource("reversi/gui/fonts/ARESSENCE.ttf").toExternalForm(), 0);
        Font.loadFont(getClass().getClassLoader().getResource("reversi/gui/fonts/ARJULIAN.ttf").toExternalForm(), 0);
        
        //Loading the main menu
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("reversi/gui/fxml/MenuFXML.fxml"));
        
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
