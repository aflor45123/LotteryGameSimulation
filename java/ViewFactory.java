

public class ViewFactory {
	
	public WelcomeController buildWelcome(SceneManager sm, KenoGame game) {
		WelcomeController c = new WelcomeController(sm, game);
		sm.register("Welcome", c.getRoot());
		return c;
	}
	
	
}
