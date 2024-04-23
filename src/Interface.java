import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Interface extends BasicGameState {
	
	Input input;
	private Image fond;
	private int x = 760;
	private int y = 480;
	private int l = 465;
	private int L = 120;
	
	public void init (GameContainer arg0, StateBasedGame game) throws SlickException {
		fond = new Image("fond/fond_interface.png");
	}
	
	public void render (GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		fond.draw();
	}
	
	public void update (GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		input = gc.getInput();
		
		if( (input.getMouseX() >= x && input.getMouseX() <= (x+l)) && (input.getMouseY() >= y && input.getMouseY() <= (y+L)) ) {
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {	
				game.enterState(1, new FadeOutTransition(), new FadeInTransition());
			}
		}
	}
	
	public int getID() {
		return 0;
	}

}