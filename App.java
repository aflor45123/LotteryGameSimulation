import javafx.application.Application;
import javafx.stage.Stage;

/** Base app class (parent in the diagram). */
public abstract class App extends Application {

    /** sceneManager: SceneManager */
    protected SceneManager sceneManager;

    /** start(primaryStage: javafx.stage.Stage): void */
    @Override
    public abstract void start(Stage primaryStage);
}