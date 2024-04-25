package Menu;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

	public static void main(String[] args) throws SlickException {
		GameStateManager monjeu = new GameStateManager("Give up 2");
		AppGameContainer app = new AppGameContainer(monjeu);	
		app.setDisplayMode(1000, 1000, false);
		app.setTargetFrameRate(60);
		app.setShowFPS(false);
		app.start();
	}
}