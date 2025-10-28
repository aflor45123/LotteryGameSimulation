//import javafx.animation.FadeTransition;
//import javafx.animation.PauseTransition;
//import javafx.animation.RotateTransition;
//import javafx.animation.SequentialTransition;



import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


/*----------------------------------------------------------
Name 1: Andres Flores Jr.
Name 2: Irfan Ozmen
Date: October 20th, 2025

Description: A lottery game where the player can guess the 
winning slots on a grid. The player has different options
on the application such as changing the background, reviewing
the rules, and winning odds. These options are all accessible
the menu pop-down button.
----------------------------------------------------------*/


public class JavaFXTemplate extends Application {
	
	Button b1;
	Text t1;
	Rectangle r1;
	
	BorderPane borderPane1;
	VBox root;
	
	

	public static void main(String[] args) {
		launch(args);
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("The Lottery Zone");
		
		// Set menu button
		b1 = new Button("Menu");
		b1.setTextFill(Color.PURPLE);
		b1.setMinWidth(80);
		b1.setBorder(new Border(new BorderStroke(
				Color.ORANGE,
				BorderStrokeStyle.SOLID,
				CornerRadii.EMPTY,
				new BorderWidths(4)
				)));
		
		
		// Set Welcome text + shape
		t1 = new Text("Welcome to the Lottery Zone!");
		t1.setFont(new Font(40));
		t1.setFill(Color.ORANGE);
		
		Rectangle rect = new Rectangle(550, 80);
		rect.setFill(Color.BLACK);
		rect.setArcHeight(50);
		rect.setArcWidth(50);
		
		StackPane sp1 = new StackPane();
		sp1.getChildren().addAll(rect, t1);
		
		
		

		
		
		
		// Assign all screen components to borderPane1
		borderPane1 = new BorderPane();
		borderPane1.setTop(b1);
		borderPane1.setCenter(sp1);
		borderPane1.setBackground(new Background (new BackgroundFill(
				Color.PURPLE,
				CornerRadii.EMPTY,
				Insets.EMPTY
				)));
		
		// Set and show scene
		Scene Mainscene = new Scene(borderPane1, 900, 600);
		primaryStage.setScene(Mainscene);
		primaryStage.show();	
	}

}
