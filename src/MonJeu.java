
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MonJeu extends BasicGameState {
	
	Input input;
	
	private Camera camera;
	
	private Personnage personnage;
	Vitesse vitesse_pers = new Vitesse();
	Vitesse vitesse_lim_pers = new Vitesse(300, 700);
	Acceleration acceleration_pers = new Acceleration(0, 0);
	String path_pers = "wizar/Chara - BlueIdle00000.png/";
	Position position_pers = new Position(1000/2, 1000/2);
	CarreHitbox carre_pers = new CarreHitbox(position_pers, 100, 170);
	CarreHitbox zone = new CarreHitbox(new Position(), 500, 500);
	
	private Obstacle obstacle;
	Vitesse vitesse = new Vitesse();
	Vitesse vitesse_lim_obst = new Vitesse(300, 300);
	Acceleration acceleration = new Acceleration();
	String path = "Square/carré_rouge.png/";
	Position position_obst = new Position(-500, 800);
	CarreHitbox carre = new CarreHitbox(position_obst, 10000, 30);
	
	private Obstacle obstacle1;
	Vitesse vitesse1 = new Vitesse();
	Vitesse vitesse_lim_obst1 = new Vitesse(300, 300);
	Acceleration acceleration1 = new Acceleration();
	String path1 = "Square/carré_violet.png/";
	Position position_obst1 = new Position(0, 600);
	CarreHitbox carre1 = new CarreHitbox(position_obst1, 500, 30);
	
	private Obstacle obstacle2;
	Position position_obst2 = new Position(1920, 600);
	CarreHitbox carre2 = new CarreHitbox(position_obst2, 500, 30);
	
	private Obstacle[] obstacles = new Obstacle[3];
	
	private Map map;
	CarreHitbox carre_map = new CarreHitbox(new Position(0, 0), 10000, 10000);
	
	public void init(GameContainer arg0, StateBasedGame game) throws SlickException {
		personnage = new Personnage(vitesse_pers, vitesse_lim_pers,acceleration_pers, path_pers, carre_pers, position_pers, 25, zone);
		obstacle = new Obstacle(vitesse, vitesse_lim_obst, acceleration, path, carre, position_obst);
		obstacle1 = new Obstacle(vitesse1, vitesse_lim_obst1, acceleration1, path1, carre1, position_obst1);
		obstacle2 = new Obstacle(vitesse1, vitesse_lim_obst1, acceleration1, path1, carre2, position_obst2);
		
		obstacles[0] = obstacle;
		obstacles[1] = obstacle1;
		obstacles[2] = obstacle2;
		
		map = new Map(personnage, "fond/fond_1.jpg", carre_map, obstacles);
		
		camera = new Camera(new Position(0, 0), new CarreHitbox(new Position(0, 0), 1000, 1000));
	}
	
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		
		// Sauvegardez l'état du contexte graphique
		g.pushTransform();

	    // Appliquez la translation pour simuler le déplacement de la caméra
	    g.translate(camera.getPosition().getX(), camera.getPosition().getY());

	    // Dessinez ici la carte et les entités
	    map.afficher_map();
	    
	    // Affichage des Hitbox
		map.getPersonnage().carre.afficher(g);
		for(int i = 0; i < obstacles.length; i++) {
			map.getObstacles()[i].carre.afficher(g);
		}
		personnage.getZone().afficher(g);
	    
	    g.popTransform();
	    // Afficher les éléments que l'on ne veut pas déplacer après la method g.popTransform(); (par exemple la barre de vie du personnage)
	    }

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		// update la position de la caméra en fonction de la position du personnage
		camera.update_pos_cam(personnage);
		
		// update la position du personnage + Gère les collision avec les plateformes
		map.update_map(delta);
		
		// Récupère les entrées user pour 
		input = gc.getInput();
		personnage.check_input(input);

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

}
