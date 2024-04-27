package Menu;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import Niveaux.Niveau5;

public class GameStateManager extends StateBasedGame{
	
	public GameStateManager(String title) {
		super(title);
	}
	
	public void enterState(int i) {
		super.enterState(i);
	}
	
	public void render (GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		getCurrentState().render(gc, game, g);
	}
	
	public void update (GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		getCurrentState().update(gc, game, delta);
	}

	public void initStatesList(GameContainer arg0) throws SlickException {
		addState(new Interface());
		addState(new Niveau5());
	}
	
}