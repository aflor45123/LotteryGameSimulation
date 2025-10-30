

public class ViewFactory {
	
	public WelcomeController buildWelcome(SceneManager sm, KenoGame game) {
		WelcomeController c = new WelcomeController(sm, game);
		sm.register("Welcome", c.getRoot());
		return c;
	}
	
	public GameController buildGame(SceneManager sm, KenoGame game) {
		GameController c = new GameController(sm, game);
		sm.register("game", c.getRoot());
		return c;
	}
	
}
