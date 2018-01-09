package reversi.fxml;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import reversi.Cell;
import reversi.GameSettings;
import javafx.scene.image.ImageView;

public class GameController extends GridPane {
	 private Cell[][] board;
	 private GameSettings gameSett;
	 
	 public GameController(Cell[][] board, GameSettings settings) {
	 this.board = board;
	 this.gameSett = settings;
	 FXMLLoader fxmlLoader = new
	 FXMLLoader(getClass().getResource("Game2.fxml"));
	 fxmlLoader.setRoot(this);
	 fxmlLoader.setController(this);
	 
	 try {
		 fxmlLoader.load();
	} catch (IOException exception) {
		 throw new RuntimeException(exception);
		 }
	 }
	 
	 public void draw() {
		 this.getChildren().clear();
		 int size = gameSett.getBoardSize();
		 
		 ImageView imgv = new ImageView();
		 for (int i = 0; i < size; i++) {
			 for (int j = 0; j < size; j++) {
				 if (board[i][j] == Cell.O) {
					 imgv.setX(i);
					 imgv.setY(j);
					 //change the image as you wish
					 //this.getChildren().add(new ImageView(this.gameSett.getOColor()));
				 } else if (board[i][j] == Cell.X) {
					 imgv.setX(i);
					 imgv.setY(j);
					 //change the image as you wish
					 //this.getChildren().add(new ImageView(this.gameSett.getXColor()));
				 }
 			 }
		 }
	}


}
