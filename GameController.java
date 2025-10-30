import javafx.geometry.Pos;

import java.util.HashSet;
import java.util.Set;

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
	
	private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;
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
	
	private GridPane grid;
	private final Button[] numberBtns = new Button[80]; // optional: easy bulk enable/disable
    private int spotsChosen = 0;           // 0 means not set yet
    private int drawCountChosen = 0;     // 0 means not set yet
	
    private final Set<Integer> selectedNumbers = new HashSet<>();
    private boolean drawingStarted = false; // lock once drawings begin
    
    private int drawsCompleted = 0;
    private final java.util.Random rng = new java.util.Random();
    
	
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
		b10 = new Button("Start Draw!");
		b10.setMinWidth(30);
		
		
		drawQuestChoices = new HBox(10);
		drawQuestChoices.getChildren().addAll(drawQuest, b6, b7, b8, b9, b10);
		drawQuestChoices.setAlignment(Pos.TOP_CENTER);
		
	
		// Set Button Grid
		grid = new GridPane();
        int counter = 1;
        for (int r = 0; r < ROWS; ++r) {
            for (int c = 0; c < COLS; ++c) {
                final int n = counter;
                Button b0 = new Button(String.valueOf(n));
                b0.setPrefSize(50, 50);
                b0.setFont(new Font(20));
                b0.setOnAction(e -> selectNumber(n));  // your handler
                numberBtns[n - 1] = b0;
                grid.add(b0, c, r);
                counter++;
            }
        }
        initiateGame();
        // Disable the card at startup
        
		
		
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
	
	private void initiateGame() {
		disableCard();

        // hook spot-choice buttons to enable the card once chosen
        b1.setOnAction(e -> { spotsChosen = 1; b1.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;"); disableSpotChoices();});
        b2.setOnAction(e -> { spotsChosen = 4; b2.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;"); disableSpotChoices();});
        b3.setOnAction(e -> { spotsChosen = 8; b3.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;"); disableSpotChoices();});
        b4.setOnAction(e -> { spotsChosen = 10; b4.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;"); disableSpotChoices();});
        
        b6.setOnAction(e -> { drawCountChosen = 1;  b6.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;"); disableDrawChoices(); enableCard();});
        b7.setOnAction(e -> { drawCountChosen = 2;  b7.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;"); disableDrawChoices(); enableCard();});
        b8.setOnAction(e -> { drawCountChosen = 3;  b8.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;"); disableDrawChoices(); enableCard();});
        b9.setOnAction(e -> { drawCountChosen = 4;  b9.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;"); disableDrawChoices(); enableCard();});
		
        b5.setOnAction(e -> { autoPick();});
        
        b10.setOnAction(e -> { 
        	b10.setDisable(true);
        	b5.setDisable(true);
        	drawingStarted = true; 
        	disableCard(); 
        	if (!drawingStarted) {
                startDrawings();  // validates & runs the first drawing
            } else {
                nextDrawings();   // runs the next drawing until done
            }
        });
		
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
    	sceneManager.show("game");
    	
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
		BorderPane layout2 = new BorderPane();
		layout2.setPrefSize(900, 600);
		layout2.setBackground(new Background (new BackgroundFill(
				Color.GREEN,
				CornerRadii.EMPTY,
				Insets.EMPTY
				)));
		
	}
	
	public void enableCard() {
		grid.setDisable(false);
	}
	
	public void disableCard() {
		grid.setDisable(true);
		
	}
	private void resetSpotButtons() {
	    b1.setStyle("");
	    b2.setStyle("");
	    b3.setStyle("");
	    b4.setStyle("");
	}
	private void resetDrawButtons() {
		b6.setStyle("");
	    b7.setStyle("");
	    b8.setStyle("");
	    b9.setStyle("");
	}
	private void disableSpotChoices() {
		b1.setDisable(true);
		b2.setDisable(true);
		b3.setDisable(true);
		b4.setDisable(true);		
		
	}
	private void disableDrawChoices() {
		b6.setDisable(true);
		b7.setDisable(true);
		b8.setDisable(true);
		b9.setDisable(true);
	}
	
	public void updateStatus(/*text String*/) {
		
	}
	
	
	public void selectNumber(int n) {
	    if (drawingStarted) {
	        // Once drawings have started, do nothing.
	        return;
	    }

	    // safety check: must pick number of spots first
	    if (spotsChosen == 0) {
	        //updateStatus("Please choose how many spots you want to play first!");
	        return;
	    }

	    // locate the button from our stored array
	    Button btn = numberBtns[n - 1];

	    // toggle behavior
	    if (selectedNumbers.contains(n)) {
	        // unselect the number
	        selectedNumbers.remove(n);
	        btn.setStyle(""); // reset to default look
	    } else {
	        // add if we haven't hit the limit
	        if (selectedNumbers.size() >= spotsChosen) {
	            //updateStatus("You can only select " + spotsChosen + " numbers.");
	            return;
	        }
	        selectedNumbers.add(n);
	        btn.setStyle("-fx-background-color: gold; -fx-text-fill: black;");
	    }

	    //updateStatus("Selected: " + selectedNumbers.size() + "/" + spotsChosen);
	}
	
	public void autoPick() {
		if (spotsChosen == 0) {
	        // Player must choose how many spots first
	        System.out.println("Please choose how many spots to play before using Auto Select.");
	        return;
	    }

	    // Clear any existing selections
	    selectedNumbers.clear();
	    for (Button b : numberBtns) {
	        b.setStyle(""); // reset to default
	    }

	    // Randomly pick unique numbers up to the chosen spots
	    java.util.Random rand = new java.util.Random();
	    while (selectedNumbers.size() < spotsChosen) {
	        int randomNum = rand.nextInt(80) + 1; // 1–80
	        selectedNumbers.add(randomNum);
	    }

	    // Highlight selected buttons
	    for (int n : selectedNumbers) {
	        Button btn = numberBtns[n - 1];
	        btn.setStyle("-fx-background-color: gold; -fx-text-fill: black;");
	    }

	}
	private java.util.List<Integer> generateDraw20() {
	    java.util.List<Integer> pool = new java.util.ArrayList<>();
	    for (int i = 1; i <= 80; i++) pool.add(i);
	    java.util.Collections.shuffle(pool, rng);
	    return pool.subList(0, 20);
	}
	// Before each drawing, reset colors back to default, then re-apply user's picks (gold)
	private void resetColorsForNextDrawing() {
		
	    for (Button b : numberBtns) b.setStyle("");
	    for (int n : selectedNumbers) {
	        numberBtns[n - 1].setStyle("-fx-background-color: gold; -fx-text-fill: black;");
	    }
	}
	
	/**
	 * Reveal numbers one-by-one with a pause.
	 * Non-picked → red; picked → green.
	 * When finished, calls onDrawingFinished().
	 */
	private void revealSequence(java.util.List<Integer> sequence) {
	    // duration between numbers
	    javafx.util.Duration step = javafx.util.Duration.millis(600);

	    // Use a Timeline with cycle count = 20
	    final int[] idx = {0};
	    javafx.animation.Timeline tl = new javafx.animation.Timeline(
	        new javafx.animation.KeyFrame(step, e -> {
	            int n = sequence.get(idx[0]++);
	            Button btn = numberBtns[n - 1];

	            if (selectedNumbers.contains(n)) {
	                // hit → green
	                btn.setStyle("-fx-background-color: #00C853; -fx-text-fill: white;");
	            } else {
	                // miss → red
	                btn.setStyle("-fx-background-color: #D50000; -fx-text-fill: white;");
	            }

	            if (idx[0] == sequence.size()) {
	                // finished this drawing
	                onDrawingFinished(sequence);
	            }
	        })
	    );
	    tl.setCycleCount(sequence.size());
	    tl.play();
	}
	
	public void startDrawings() {
		
	    drawingStarted = true;
	    disableCard();        // lock the card
	    drawsCompleted = 0;   // reset session counter for this match
	    b10.setText("Next Draw"); // button now advances drawings

	    // run the first drawing
	    runOneDrawing();
	}
	
	public void nextDrawings() {
		if (!drawingStarted) return;
	    if (drawsCompleted >= drawCountChosen) {
	        System.out.println("All drawings complete.");
	        return;
	    }
	    runOneDrawing();
	}
	// Single drawing step: reset colors, generate 20 numbers, then animate reveal
	private void runOneDrawing() {
	    resetColorsForNextDrawing();
	    java.util.List<Integer> drawn = generateDraw20();
	    revealSequence(drawn);
	}
	
	// Called when a 20-number reveal completes
	private void onDrawingFinished(java.util.List<Integer> drawn) {
	    drawsCompleted++;

	    // Example: count hits; you can add payout logic here later
	    int hits = 0;
	    for (int n : drawn) if (selectedNumbers.contains(n)) hits++;

	    System.out.println("Drawing " + drawsCompleted + " finished. Hits: " + hits);
	    b10.setText("Next Draw!");
	    b10.setDisable(false);
	   
	    if (drawsCompleted >= drawCountChosen) {
	        // all done — allow new match setup
	        endOfMatchReset();
	    }
	}
	
	private void endOfMatchReset() {
	    drawingStarted = false;
	    b10.setText("Start Draw!");

	    // Re-enable choices so the player can set up a new game
	    // (numbers remain shown; user can start fresh or exit)
	    // If you want to clear everything immediately, also clear picks & styles.
	    enableCard(); // The spec allows new card after all drawings complete
	    // If you want to force re-choosing spots/draws, re-enable buttons here:
	    // (example)
	    b1.setDisable(false); b2.setDisable(false); b3.setDisable(false); b4.setDisable(false);
	    b6.setDisable(false); b7.setDisable(false); b8.setDisable(false); b9.setDisable(false);
	}
	public void showResult() {
	}
	

	
}
