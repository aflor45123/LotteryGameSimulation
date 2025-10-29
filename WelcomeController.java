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



public class WelcomeController{
	
	
	private MenuBar menuBar;
	private Menu menu;
	private MenuItem menuItemRules;
	private MenuItem menuItemOdds;
	private MenuItem menuItemMatch;
    private MenuItem menuItemExit;
    //private Button btnPlay;
    private BorderPane layout;
    private HBox centeredMenu;
    private Text welcomeText;
    
    private final SceneManager sceneManager;
    private final KenoGame game;
    
    
    public WelcomeController(SceneManager sceneManager, KenoGame game) {
        this.sceneManager = sceneManager;
        this.game = game;

        
        // Set menu options
        menuBar = new MenuBar();
        menu = new Menu("Menu");
        
        menuItemRules = new MenuItem("Game Rules");
        menuItemOdds  = new MenuItem("Winning Odds");
        menuItemMatch = new MenuItem("Play a Match");
        menuItemExit  = new MenuItem("Exit");
        menu.getItems().addAll(menuItemRules, menuItemOdds, menuItemMatch, menuItemExit);
        menuBar.getMenus().add(menu);

        centeredMenu = new HBox(menuBar);
        centeredMenu.setAlignment(Pos.CENTER);
        
        
        // Set welcome text and background shape
     	welcomeText = new Text("Welcome to the Lottery Zone!");
     	welcomeText.setFont(new Font(40));
     	welcomeText.setFill(Color.ORANGE);
     		
     	Rectangle rect = new Rectangle(550, 80);
     	rect.setFill(Color.BLACK);
     	rect.setArcHeight(50);
     	rect.setArcWidth(50);
     		
     	StackPane stackPane1 = new StackPane();
     	stackPane1.getChildren().addAll(rect, welcomeText);
     	
     	
     	// wire menu actions
        menuItemRules.setOnAction(e -> onShowRules());
        menuItemOdds.setOnAction(e -> onShowOdds());
        menuItemMatch.setOnAction(e -> onShowMatch());
        menuItemExit.setOnAction(e -> onExit());
     	
        
     	// Set layout components
     	layout = new BorderPane();
		layout.setTop(centeredMenu);
		layout.setCenter(stackPane1);
		layout.setPrefSize(900, 600);
		layout.setBackground(new Background (new BackgroundFill(
				Color.PURPLE,
				CornerRadii.EMPTY,
				Insets.EMPTY
				)));
    }

    public Parent getRoot() {
        return layout;
    }

    // --- public methods per UML ---
    public void onShowRules() {
        // stub: show a dialog, scene, or open a web page
    	Text RulesSign = new Text("Game Rules");
    	RulesSign.setFont(new Font(25));
    	
    	
    	Rectangle rectRules = new Rectangle(300, 400);
    	rectRules.setFill(Color.ORANGE);
    	rectRules.setArcHeight(50);
    	rectRules.setArcWidth(50);
    	
    	StackPane stackPane2 = new StackPane();
    	stackPane2.getChildren().addAll(rectRules, RulesSign);
    	StackPane.setAlignment(RulesSign, Pos.CENTER);
    	RulesSign.setTranslateY(-180);
    	
    	
    	layout.setCenter(stackPane2);
    }

    public void onShowOdds() {
    	Text RulesSign = new Text("Winning Odds");
    	RulesSign.setFont(new Font(25));
    	
    	
    	Rectangle rectRules = new Rectangle(300, 400);
    	rectRules.setFill(Color.ORANGE);
    	rectRules.setArcHeight(50);
    	rectRules.setArcWidth(50);
    	
    	StackPane stackPane2 = new StackPane();
    	stackPane2.getChildren().addAll(rectRules, RulesSign);
    	StackPane.setAlignment(RulesSign, Pos.CENTER);
    	RulesSign.setTranslateY(-180);
    	
    	
    	layout.setCenter(stackPane2);
    }
    
    public void onShowMatch() {
    	// Will transition to the next scene
    }

    public void onExit() {
        // close the app window
        layout.getScene().getWindow().hide();
    }

    public void onStartGame() {
        // swap to the game scene registered by ViewFactory
        sceneManager.show("game");
    }
	
}
