package Niveaux;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Entités.Camera;
import Entités.Joueur;
import Entités.Map;
import Entités.Obstacle;
import Entités.Xp;
import Hitbox.CarreHitbox;
import Mécanique.Acceleration;
import Mécanique.Position;
import Mécanique.Vitesse;

public class Niveau1 extends BasicGameState{

	
	Input input;
	
	private Camera camera;
	
	private Xp xp0;
	private CarreHitbox zone_xp0;
	private CarreHitbox carre_xp0;
	private Position position_xp0;
	
	private Xp xp1;
	private CarreHitbox zone_xp1;
	private CarreHitbox carre_xp1;
	private Position position_xp1;
	
	private Xp xp2;
	private CarreHitbox zone_xp2;
	private CarreHitbox carre_xp2;
	private Position position_xp2;
	
	private Xp xp3;
	private CarreHitbox zone_xp3;
	private CarreHitbox carre_xp3;
	private Position position_xp3;
	
	private Xp xp4;
	private CarreHitbox zone_xp4;
	private CarreHitbox carre_xp4;
	private Position position_xp4;
	
	private Xp[] xps;
	
	private Joueur joueur;
	private Vitesse vitesse_pers = new Vitesse();
	private Vitesse vitesse_lim_pers = new Vitesse(300, 700);
	private Acceleration acceleration_pers = new Acceleration(0, 0);
	private String path_pers_d = "wizar_d";
	private String path_pers_g = "wizar_g";
	private String images_animation_droite = "wizar_droite";
	private String images_animation_gauche = "carre_gauche";
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
	
	private Obstacle[] obstacles;
	
	private Map map;
	private CarreHitbox carre_map = new CarreHitbox(new Position(0, 0), 10000, 10000);
	
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
		
		obstacles = new Obstacle[3];
		
		obstacles[0] = obstacle;
		obstacles[1] = obstacle1;
		obstacles[2] = obstacle2;
		
		position_xp0 = new Position(0, 0);
		carre_xp0 = new CarreHitbox(position_xp0, 50, 50);
		zone_xp0 = new CarreHitbox(carre_xp0);
		xp0 = new Xp("image_xp", carre_xp0, position_xp0, zone_xp0);
		
		position_xp1 = new Position(200, 500);
		carre_xp1 = new CarreHitbox(position_xp1, 50, 50);
		zone_xp1 = new CarreHitbox(carre_xp1);
		xp1 = new Xp("image_xp", carre_xp1, position_xp1, zone_xp1);
		
		
		position_xp2 = new Position(500, 600);
		carre_xp2 = new CarreHitbox(position_xp2, 50, 50);
		zone_xp2 = new CarreHitbox(carre_xp2);
		xp2 = new Xp("image_xp", carre_xp2, position_xp2, zone_xp2);
		
		position_xp3 = new Position(100, 500);
		carre_xp3 = new CarreHitbox(position_xp3, 50, 50);
		zone_xp3 = new CarreHitbox(carre_xp3);
		xp3 = new Xp("image_xp", carre_xp3, position_xp3, zone_xp3);
		
		
		position_xp4 = new Position(1000, 500);
		carre_xp4 = new CarreHitbox(position_xp4, 50, 50);
		zone_xp4 = new CarreHitbox(carre_xp4);
		xp4 = new Xp("image_xp", carre_xp4, position_xp4, zone_xp4);
		
		xps = new Xp[5];
		
		xps[0] = xp0;
		xps[1] = xp1;
		xps[2] = xp2;
		xps[3] = xp3;
		xps[4] = xp4;
	
		map = new Map(joueur, "fond_niv_5", carre_map, obstacles, xps);
		
		//camera = new Camera(new Position(0, 0), new CarreHitbox(new Position(0, 0), arg0.getScreenWidth(), arg0.getScreenWidth()));
		camera = new Camera(new Position(0, 0), new CarreHitbox(new Position(0, 0), 1920, 1080));
	}
	
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {

	    // Appliquez la translation pour simuler le déplacement de la caméra
	    g.translate(camera.getPosition().getX(), camera.getPosition().getY());

	    // Dessinez ici la carte et les entités
	    map.afficher_map();
	    
	    // Affichage des Hitbox
		map.getJoueur().getCarre().afficher(g);
		for(int i = 0; i < obstacles.length; i++) {
			map.getObstacles()[i].getCarre().afficher(g);
		}
		joueur.getZone().afficher(g);
		zone_xp1.afficher(g);
		carre_xp1.afficher(g);
		g.translate(-camera.getPosition().getX(), -camera.getPosition().getY());
		g.drawRect(0, 0, 100, 100);
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		// update la position de la caméra en fonction de la position du personnage
		camera.update_pos_cam(joueur);
		
		// update la position du personnage + Gère les collision avec les plateformes
		map.update_map(delta);
		
		// Récupère les entrées user pour 
		input = gc.getInput();
		joueur.check_input(input);

	}
	
	public int getID() {
		return 1;
	}
}
