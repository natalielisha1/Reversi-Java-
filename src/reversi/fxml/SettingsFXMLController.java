package reversi.fxml;
import java.net.URL;
import java.util.ResourceBundle;
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
import reversi.Cell;
import reversi.GameSettings;
import reversi.ODiskColor;
import reversi.XDiskColor;

/**
 *
 * @author ofek_and_natalie
 */
public class SettingsFXMLController implements Initializable {
	
    @FXML
    private ChoiceBox startingPlayerChoice;

    @FXML
    private ChoiceBox whitePlayerChoice;

    @FXML
    private ChoiceBox blackPlayerChoice;

    @FXML
    private ChoiceBox boardSizeChoice;

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
        gameSettings = GameSettings.getInstance();
        
        startingPlayerChoice.setItems(FXCollections.observableArrayList("Black Player", "White Player"));
        switch (gameSettings.getStartingPlayer()) {
            case X:     startingPlayerChoice.getSelectionModel().select(0);
                        break;
            case O:     startingPlayerChoice.getSelectionModel().select(1);
                        break;
        }
        
        whitePlayerChoice.setItems(FXCollections.observableArrayList(ODiskColor.getStrings()));
        whitePlayerChoice.getSelectionModel().select(gameSettings.getOColor().ordinal());
        whitePlayerChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ob, String value, String newValue) {
                whitePlayerDiskImage.setImage(ODiskColor.getEnum(newValue).getDisk());
            }
        });
        whitePlayerDiskImage.setImage(gameSettings.getOColor().getDisk());
        
        blackPlayerChoice.setItems(FXCollections.observableArrayList(XDiskColor.getStrings()));
        blackPlayerChoice.getSelectionModel().select(gameSettings.getXColor().ordinal());
        blackPlayerChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ob, String value, String newValue) {
                blackPlayerDiskImage.setImage(XDiskColor.getEnum(newValue).getDisk());
            }
        });
        blackPlayerDiskImage.setImage(gameSettings.getXColor().getDisk());
        
        boardSizeChoice.setItems(FXCollections.observableArrayList(4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20));
        boardSizeChoice.getSelectionModel().select(gameSettings.getBoardSize() - 4);
        
        saveBtn.setOnAction((event) -> {
            switch ((String)startingPlayerChoice.getValue()) {
                case "Black Player":    gameSettings.setStartingPlayer(Cell.X);
                                        break;
                case "White Player":    gameSettings.setStartingPlayer(Cell.O);
                                        break;
            }
            gameSettings.setOColor(ODiskColor.getEnum((String) whitePlayerChoice.getValue()));
            gameSettings.setXColor(XDiskColor.getEnum((String) blackPlayerChoice.getValue()));
            gameSettings.setBoardSize((Integer)boardSizeChoice.getValue());
            gameSettings.saveToFile();
            returnToMenu();
        });
    }

    private void returnToMenu() {
        try {
            FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("MenuFXML.fxml"));
            Parent menuParent = menuLoader.load();
            Scene menuScene = new Scene(menuParent);
            Stage theStage = (Stage) saveBtn.getScene().getWindow();
            theStage.setScene(menuScene);
        }   catch (Exception ex) {
            //Weird Error - if happens -> debug
            System.out.println("ChangeSettings error:");
            ex.printStackTrace();
        }
    }
}
