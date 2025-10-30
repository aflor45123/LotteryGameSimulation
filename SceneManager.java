import java.util.HashMap;
import java.util.Map;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
	
	private Stage primaryStage;
	private Map<String, Scene> scenes = new HashMap<>();
	
	public SceneManager(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public void register(String name, Parent root) {
		scenes.put(name, new Scene(root));
	}
	
	public void show(String name) {
		Scene s = scenes.get(name);
		if(s == null) throw new IllegalArgumentException("Scene not found: " + name);
		primaryStage.setScene(s);
	}
	
	public Parent getRoot(String name) {
		Scene s = scenes.get(name);
		return s.getRoot();
	}
	
}
