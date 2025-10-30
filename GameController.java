import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.text.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;



public class GameController {
	
	private Button b1, b2, b3, b4, b5, b6, b7, b8, b9;
	private MenuBar menuBar;
	private Menu menu;
	private MenuItem menuItemRules;
	private MenuItem menuItemOdds;
	private MenuItem menuItemMatch;
	private MenuItem menuItemExit;
	private MenuItem menuItemLook;
	private BorderPane layout;
	private HBox centeredMenu;
	private HBox spotQuestChoices;
	private HBox drawQuestChoices;
	private VBox components;
	private Text spotQuest;
	private Text drawQuest;
	private static final int ROWS = 8;
	private static final int COLS = 10;
	
	private final SceneManager sceneManager;
	private final KenoGame game;
	
	private StackPane stackPane2;
	
	
	
	
	public GameController(SceneManager sceneManager, KenoGame game) {
		this.sceneManager = sceneManager;
		this.game = game;
		
		// Set menu options
		menuBar = new MenuBar();
		menu = new Menu("Menu");
		
		menuItemRules = new MenuItem("Game Rules");
		menuItemOdds = new MenuItem("Winning Odds");
		menuItemMatch = new MenuItem("Play a Match");
		menuItemExit = new MenuItem("Exit");
		menuItemLook = new MenuItem("New Look");
		menu.getItems().addAll(menuItemRules, menuItemOdds, menuItemMatch, 
				menuItemExit, menuItemLook);
		menuBar.getMenus().add(menu);
		
		centeredMenu = new HBox(menuBar);
		centeredMenu.setAlignment(Pos.CENTER);
		
		// Set Questions and background shape
		spotQuest = new Text("How many spots do you want to play?");
		spotQuest.setFont(new Font(15));
		
		drawQuest = new Text("How many consecutive draws?");
		drawQuest.setFont(new Font(15));
		
		Rectangle rect = new Rectangle(550, 80);
		rect.setFill(Color.ORANGE);
		rect.setArcHeight(50);
		rect.setArcWidth(50);
		
		
		
		// Set buttons
		b1 = new Button("1");
		b1.setMinWidth(10);
		b2 = new Button("4");
		b2.setMinWidth(10);
		b3 = new Button("8");
		b3.setMinWidth(10);
		b4 = new Button("10");
		b4.setMinWidth(10);
		b5 = new Button("Auto Select");
		b5.setMinWidth(10);
		
		spotQuestChoices = new HBox(10);
		spotQuestChoices.getChildren().addAll(spotQuest, b1, b2, b3, b4, b5);
		spotQuestChoices.setAlignment(Pos.TOP_CENTER);
		
		b6 = new Button("1");
		b6.setMinWidth(10);
		b7 = new Button("2");
		b7.setMinWidth(10);
		b8 = new Button("3");
		b8.setMinWidth(10);
		b9 = new Button("4");
		b9.setMinWidth(10);
		
		drawQuestChoices = new HBox(10);
		drawQuestChoices.getChildren().addAll(drawQuest, b6, b7, b8, b9);
		drawQuestChoices.setAlignment(Pos.TOP_CENTER);
		
	
		// Set Button Grid
		GridPane grid = new GridPane();
		int counter = 1;
		
		for (int r = 0; r < ROWS; ++r) {
			for (int c = 0; c < COLS; ++c) {
				
				Button b0 = new Button("" + counter);
				b0.setPrefSize(50, 50);
				b0.setFont(new Font(20));
				grid.add(b0, c, r);
				counter++;
			}
		}
		
		HBox centeredGrid = new HBox(10);
		centeredGrid.getChildren().addAll(grid);
		centeredGrid.setAlignment(Pos.CENTER);
		
		
		// Vertically space out all components
		components = new VBox(25);
		components.getChildren().addAll(spotQuestChoices, drawQuestChoices, centeredGrid);
		
		
		
		// Center all components
		stackPane2 = new StackPane();
		stackPane2.getChildren().addAll(rect, components);
		stackPane2.setAlignment(Pos.TOP_CENTER);
		
		
		// wire menu actions
        menuItemRules.setOnAction(e -> onShowRules());
        menuItemOdds.setOnAction(e -> onShowOdds());
        menuItemMatch.setOnAction(e -> onShowMatch());
        menuItemExit.setOnAction(e -> onExit());
        menuItemLook.setOnAction(e -> onShowLook());
		
		// Set layout components
		layout = new BorderPane();
		layout.setTop(centeredMenu);
		layout.setCenter(stackPane2);
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
    	
    	// Backdrop
    	Rectangle rectRules = new Rectangle(300, 400);
    	rectRules.setFill(Color.ORANGE);
    	rectRules.setArcHeight(50);
    	rectRules.setArcWidth(50);
    	
    	// Align Header, Description, and Backdrop
    	StackPane stackPane2 = new StackPane();
    	stackPane2.getChildren().addAll(rectRules, RulesSign, DescriptRules);
    	StackPane.setAlignment(RulesSign, Pos.CENTER);
    	RulesSign.setTranslateY(-180);
    	
    	
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
    	
    	// Backdrop
    	Rectangle rectRules = new Rectangle(300, 400);
    	rectRules.setFill(Color.ORANGE);
    	rectRules.setArcHeight(50);
    	rectRules.setArcWidth(50);
    	
    	// Align Header, Description, and Backdrop
    	StackPane stackPane2 = new StackPane();
    	stackPane2.getChildren().addAll(rectRules, RulesSign, DescriptOdds);
    	StackPane.setAlignment(RulesSign, Pos.CENTER);
    	RulesSign.setTranslateY(-180);
    	
  
    	layout.setCenter(stackPane2);
    }
	
	public void onShowMatch() {
        // swap to the game scene registered by ViewFactory
		layout.setTop(centeredMenu);
		layout.setCenter(stackPane2);
    }
	
	public void onExit() {
		
		 // Set welcome text and background shape
     	Text welcomeText = new Text("Welcome to the Lottery Zone!");
     	welcomeText.setFont(new Font(40));
     	welcomeText.setFill(Color.ORANGE);
     		
     	Rectangle rect = new Rectangle(550, 80);
     	rect.setFill(Color.BLACK);
     	rect.setArcHeight(50);
     	rect.setArcWidth(50);
     	
		StackPane stackPane3 = new StackPane();
		stackPane3.getChildren().addAll(rect, welcomeText);
		
		// Set layout
		layout.setCenter(stackPane3);
    }
	
	public void onShowLook() {
		
		// Set layout components
		layout.setBackground(new Background (new BackgroundFill(
				Color.BLUE,
				CornerRadii.EMPTY,
				Insets.EMPTY
				)));
		
	}
	
	public void enableCard() {
		
	}
	
	public void disableCard() {
		
	}
	
	public void updateStatus(/*text String*/) {
		
	}
	
	public void populateGrid() {
		
	}
	
	public void selectNumber(/*n int*/) {
		
	}
	
	public void autoPick() {
		
	}
	
	public void startDrawings() {
		
	}
	
	public void nextDrawings() {
		
	}
	
	public void showResult() {
	}
	

	
}
