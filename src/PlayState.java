import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PlayState extends BasicGameState {
	
	Input input;

	public void init (GameContainer arg0, StateBasedGame game) throws SlickException {
		
	}
	
	public void render (GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.fillRect(0, 0, 1920, 1080);
	}
	
	public void update (GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		input = gc.getInput();
		
		if(input.isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(0, null, null);
		}
	}

	public int getID() {
		return 1;
	}

}
