import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.text.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;



public class GameController {
	
	
	private Text questions;
	
	private final SceneManager sceneManager;
	private final KenoGame game;
	private BorderPane layout;
	
	
	
	public GameController(SceneManager sceneManager, KenoGame game) {
		this.sceneManager = sceneManager;
		this.game = game;
		
		questions = new Text(
				"How many spots do you want to play?\n"
				+ "How many consecutive draws?");
		
		layout = new BorderPane();
		layout.setCenter(questions);
		
		
		
		
	}
	
	public Parent getRoot() {
        return layout;
    }
	
}
