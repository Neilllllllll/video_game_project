package Niveaux;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Entit�s.Camera;
import Entit�s.Joueur;
import Entit�s.Map;
import Entit�s.Obstacle;
import Entit�s.Xp;
import Hitbox.CarreHitbox;
import M�canique.Acceleration;
import M�canique.Position;
import M�canique.Vitesse;

public class Niveau1 extends BasicGameState {
	
	Input input;
	
	private Camera camera;
	
	private Xp xp;
	private CarreHitbox carre_xp;
	private Position position_xp;
	
	private Joueur joueur;
	private Vitesse vitesse_pers = new Vitesse();
	private Vitesse vitesse_lim_pers = new Vitesse(300, 700);
	private Acceleration acceleration_pers = new Acceleration(0, 0);
	private String path_pers_d = "wizar_d";
	private String path_pers_g = "wizar_g";
	private String images_animation_droite = "wizar_droite";
	private String images_animation_gauche = "wizar_gauche";
	private String images_animation_jump_d = "wizar_jump_d";
	private String images_animation_jump_g = "wizar_jump_g";
	private Position position_pers = new Position(1000/2, 1000/2);
	private CarreHitbox carre_pers = new CarreHitbox(position_pers, 100, 170);
	private CarreHitbox zone = new CarreHitbox(new Position(), 500, 500);
	
	private Obstacle obstacle;
	private String path = "fond";
	private Position position_obst = new Position(-500, 800);
	private CarreHitbox carre = new CarreHitbox(position_obst, 10000, 400);
	
	private Obstacle obstacle1;
	private String path1 = "fond";
	private Position position_obst1 = new Position(0, 600);
	private CarreHitbox carre1 = new CarreHitbox(position_obst1, 500, 100);
	
	private Obstacle obstacle2;
	private String path2 = "fond";
	private Position position_obst2 = new Position(1920, 600);
	private CarreHitbox carre2 = new CarreHitbox(position_obst2, 500, 100);
	
	private Obstacle[] obstacles = new Obstacle[3];
	
	private Map map;
	private CarreHitbox carre_map = new CarreHitbox(new Position(0, 0), 10000, 10000);
	
	boolean cond = true;
	
	public void init(GameContainer arg0, StateBasedGame game) throws SlickException {
		joueur = new Joueur(
				vitesse_pers, 
				vitesse_lim_pers,
				acceleration_pers, 
				path_pers_d, 
				path_pers_g,
				images_animation_droite,
				images_animation_gauche,
				images_animation_jump_d,
				images_animation_jump_g,
				carre_pers, 
				position_pers, 
				25, 
				zone);
		obstacle = new Obstacle(path, carre, position_obst);
		obstacle1 = new Obstacle(path1, carre1, position_obst1);
		obstacle2 = new Obstacle(path2, carre2, position_obst2);
		
		obstacles[0] = obstacle;
		obstacles[1] = obstacle1;
		obstacles[2] = obstacle2;
		
		position_xp = new Position(200, 500);
		carre_xp = new CarreHitbox(position_xp, 100, 100);
		xp = new Xp("image_xp", carre_xp, position_xp);
		
		map = new Map(joueur, "fond_niv_5", carre_map, obstacles, xp);
		
		// camera = new Camera(new Position(0, 0), new CarreHitbox(new Position(0, 0), arg0.getScreenWidth(), arg0.getScreenWidth()));
		camera = new Camera(new Position(0, 0), new CarreHitbox(new Position(0, 0), 1000, 1000));
	}
	
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {

	    // Appliquez la translation pour simuler le d�placement de la cam�ra
	    g.translate(camera.getPosition().getX(), camera.getPosition().getY());

	    // Dessinez ici la carte et les entit�s
	    map.afficher_map();
	    
	    // Affichage des Hitbox
		map.getJoueur().getCarre().afficher(g);
		for(int i = 0; i < obstacles.length; i++) {
			map.getObstacles()[i].getCarre().afficher(g);
		}
		joueur.getZone().afficher(g);
		g.translate(-camera.getPosition().getX(), -camera.getPosition().getY());
		
		g.drawRect(0, 0, 100, 100);
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		// update la position de la cam�ra en fonction de la position du personnage
		camera.update_pos_cam(joueur);
		
		// update la position du personnage + G�re les collision avec les plateformes
		map.update_map(delta);
		
		// R�cup�re les entr�es user pour 
		input = gc.getInput();
		joueur.check_input(input);

	}
	
	public int getID() {
		return 1;
	}

}
