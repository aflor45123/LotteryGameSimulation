import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class JavaFXTemplate extends Application {
	
	private ViewFactory viewFactory;
	private SceneManager sceneManager;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.sceneManager = new SceneManager(primaryStage);
		this.viewFactory = new ViewFactory();
		
		KenoGame game = new KenoGame();
		
		viewFactory.buildWelcome(sceneManager, game);
		viewFactory.buildGame(sceneManager, game);
		
		
		sceneManager.show("Welcome");
		primaryStage.setTitle("The Lottery Zone");
		primaryStage.show();
	}

}
