import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class WelcomeController{
	
	
	private MenuBar menuBar;
	private Menu menu;
	private MenuItem menuItemRules;
	private MenuItem menuItemOdds;
    private MenuItem menuItemExit;
    private Button btnPlay;
    private BorderPane layout;
    
    private final SceneManager sceneManager;
    private final KenoGame game;
    
    
    public WelcomeController(SceneManager sceneManager, KenoGame game) {
        this.sceneManager = sceneManager;
        this.game = game;

        // UI
        menuBar = new MenuBar();
        menu = new Menu("Menu");
        menuItemRules = new MenuItem("Rules");
        menuItemOdds  = new MenuItem("Odds");
        menuItemExit  = new MenuItem("Exit");
        menu.getItems().addAll(menuItemRules, menuItemOdds, menuItemExit);
        menuBar.getMenus().add(menu);

        btnPlay = new Button("Play");
        btnPlay.setOnAction(e -> onStartGame());

        // wire menu actions
        menuItemRules.setOnAction(e -> onShowRules());
        menuItemOdds.setOnAction(e -> onShowOdds());
        menuItemExit.setOnAction(e -> onExit());

        StackPane center = new StackPane(btnPlay);
        center.setAlignment(Pos.CENTER);

        layout = new BorderPane(center);
        layout.setTop(menuBar);
        layout.setPrefSize(640, 400);
    }

    public Parent getRoot() {
        return layout;
    }

    // --- public methods per UML ---
    public void onShowRules() {
        // stub: show a dialog, scene, or open a web page
        System.out.println("Show Rules");
    }

    public void onShowOdds() {
        System.out.println("Show Odds");
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
