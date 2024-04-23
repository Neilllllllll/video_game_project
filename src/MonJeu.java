import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MonJeu extends StateBasedGame {
	
	Input input;
	
	/*private Personnage personnage;
	Vitesse vitesse_pers = new Vitesse();
	Acceleration acceleration_pers = new Acceleration(100, 100);
	String path_pers = "images_droite/personnage_d_0.png/";
	Position position_pers = new Position(100, 100);
	CarreHitbox carre_pers = new CarreHitbox(position_pers, 100, 100);
	
	private Obstacle obstacle;
	Vitesse vitesse = new Vitesse();
	Acceleration acceleration = new Acceleration();
	String path = "Square/carré_rouge.png/";
	Position position_obst = new Position(0, 800);
	CarreHitbox carre = new CarreHitbox(position_obst, 1920, 30);
	boolean mur = false;*/
	
	
	public MonJeu(String title) {
		super(title);
	}
	
	public void init(GameContainer arg0, StateBasedGame game) throws SlickException {
		//personnage = new Personnage(vitesse_pers, acceleration_pers, path_pers, carre_pers, position_pers);
		//obstacle = new Obstacle(vitesse, acceleration, path, carre, position_obst, mur);
	}
	
	
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		
		game.render(gc, g);
		//personnage.afficher();
		//obstacle.afficher();
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		
		game.update(gc, delta);
		
		
		input = gc.getInput();
		
		// Déplacement du personnage en fonction des commandes entrées par l'utilisateur
		/*if(input.isKeyDown(Input.KEY_RIGHT)){
			personnage.droite(delta);
		}
		else if(input.isKeyDown(Input.KEY_LEFT)){
			personnage.gauche(delta);
		}
		else {
			personnage.stopX();
		}
		if (personnage.isSol(obstacle)) {
			if(input.isKeyPressed(Input.KEY_SPACE)){
				personnage.setSaut(true);
			}
		}
		if(personnage.isSaut()) {
			personnage.sauter(delta);
		}
		else {
			if(personnage.isSol(obstacle) == false) {
				personnage.bas(delta);
			}
		}
		obstacle.correction(personnage);*/
	}
	
	public void initStatesList(GameContainer arg0) throws SlickException {
		
	}

}
