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
    private BorderPane layout;
    private HBox centeredMenu;
    private Text welcomeText;
    
    private final SceneManager sceneManager;
    private final KenoGame game;
    private final StackPane stackPane1;
    
    
    public WelcomeController(SceneManager sceneManager, KenoGame game) {
        this.sceneManager = sceneManager;
        this.game = game;

        
        // Set menuBar
        menuBar = new MenuBar();
        menu = new Menu("Menu");
        
        
        // Set menuItems
        menuItemRules = new MenuItem("Game Rules");
        menuItemOdds  = new MenuItem("Winning Odds");
        menuItemMatch = new MenuItem("Play a Match");
        menuItemExit  = new MenuItem("Exit");
        menu.getItems().addAll(menuItemRules, menuItemOdds, menuItemMatch, menuItemExit);
        menuBar.getMenus().add(menu);
        
        
        // Center menuBar
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
     	
     	
     	// Allow text and shape overlay
     	stackPane1 = new StackPane();
     	stackPane1.getChildren().addAll(rect, welcomeText);
     	
     	
     	// Wire menu actions
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
    	// Header
    	Text RulesSign = new Text("Game Rules");
    	RulesSign.setFont(new Font(25));
    	
    	
    	// Description
    	Text DescriptRules = new Text(
    			"As a player, you can only select the number\n "
    			+ "of spots you want at the beginning of the\n"
    			+ "match. This also applies to the number of\n"
    			+ "draws you want. You will not be able to\n"
    			+ "make any changes once the draw begins.\n\n\n\n\n\n");
    	DescriptRules.setFont(new Font(15));
    	
    	
    	// Set background shape
    	Rectangle rectRules = new Rectangle(300, 400);
    	rectRules.setFill(Color.ORANGE);
    	rectRules.setArcHeight(50);
    	rectRules.setArcWidth(50);
    	
    	
    	// Allow text and shape overlay
    	StackPane stackPane2 = new StackPane();
    	stackPane2.getChildren().addAll(rectRules, RulesSign, DescriptRules);
    	StackPane.setAlignment(RulesSign, Pos.CENTER);
    	RulesSign.setTranslateY(-180);
    	
    	
    	// Set layout component
    	layout.setCenter(stackPane2);
    }

    
    public void onShowOdds() {
    	
    	
    	// Header
    	Text RulesSign = new Text("Winning Odds");
    	RulesSign.setFont(new Font(25));
    	
    	
    	// Description
    	Text DescriptOdds = new Text(
    			"Winning odds increase if you choose\n"
    			+ "more spots on your bet card and if you\n"
    			+ "do more than one draw. There are 80 spots\n"
    			+ "on the bet card. If you are choosing to do\n"
    			+ "one draw, your odds of winning are shown\n"
    			+ "below:\n"
    			+ "\n"
    			+ "                    1 spot = 0.125%\r\n"
    			+ "                    4 spot = 0.5%\r\n"
    			+ "                    8 spot = 1%\r\n"
    			+ "                    10 spot = 1.25%");
    	DescriptOdds.setFont(new Font(15));
    	
    	
    	// Set background shape
    	Rectangle rectRules = new Rectangle(300, 400);
    	rectRules.setFill(Color.ORANGE);
    	rectRules.setArcHeight(50);
    	rectRules.setArcWidth(50);
    	
    	
    	// Allow text and shape overlay
    	StackPane stackPane2 = new StackPane();
    	stackPane2.getChildren().addAll(rectRules, RulesSign, DescriptOdds);
    	StackPane.setAlignment(RulesSign, Pos.CENTER);
    	RulesSign.setTranslateY(-180);
    	
    	
    	// Set layout component
    	layout.setCenter(stackPane2);
    }
    
    
    public void onShowMatch() {
        // Swap to the game scene registered by ViewFactory
    	sceneManager.show("game");
    	
    }

    
    public void onExit() {
        // Layout reverts back to welcome screen 
		layout.setCenter(stackPane1);
    }

	
}
