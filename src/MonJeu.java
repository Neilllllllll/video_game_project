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
	String path_pers = "images_droite/personnage_d_0.png/";
	Position position_pers = new Position((1000/2) - 10, 1000/2);
	CarreHitbox carre_pers = new CarreHitbox(position_pers, 100, 100);
	
	private Obstacle obstacle;
	Vitesse vitesse = new Vitesse();
	Vitesse vitesse_lim_obst = new Vitesse(300, 300);
	Acceleration acceleration = new Acceleration();
	String path = "Square/carré_rouge.png/";
	Position position_obst = new Position(-500, 800);
	CarreHitbox carre = new CarreHitbox(position_obst, 3000, 30);
	
	private Obstacle obstacle1;
	Vitesse vitesse1 = new Vitesse();
	Vitesse vitesse_lim_obst1 = new Vitesse(300, 300);
	Acceleration acceleration1 = new Acceleration();
	String path1 = "Square/carré_rouge.png/";
	Position position_obst1 = new Position(0, 600);
	CarreHitbox carre1 = new CarreHitbox(position_obst1, 200, 30);
	
	private Obstacle obstacle2;
	
	private Obstacle[] obstacles = new Obstacle[3];
	
	private Map map;
	CarreHitbox carre_map = new CarreHitbox(new Position(0, 0), 3000, 1080);
	
	public void init(GameContainer arg0, StateBasedGame game) throws SlickException {
		personnage = new Personnage(vitesse_pers, vitesse_lim_pers,acceleration_pers, path_pers, carre_pers, position_pers, 25);
		obstacle = new Obstacle(vitesse, vitesse_lim_obst, acceleration, path, carre, position_obst);
		obstacle1 = new Obstacle(vitesse1, vitesse_lim_obst1, acceleration1, path1, carre1, position_obst1);
		obstacle2 = new Obstacle(vitesse1, vitesse_lim_obst1, acceleration1, path1, carre1, new Position(1920, 600));
		
		obstacles[0] = obstacle;
		obstacles[1] = obstacle1;
		obstacles[2] = obstacle2;
		
		map = new Map(personnage, "fond/fond_1.jpg", carre_map, obstacles);
		
		camera = new Camera(new Position(0, 0), new CarreHitbox(new Position(0, 0), 1000, 1000), map);
	}
	
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		map.getPersonnage().carre.afficher(g);
		for(int i = 0; i < obstacles.length; i++) {
			map.getObstacles()[i].carre.afficher(g);
		}
		g.pushTransform(); // Sauvegardez l'état du contexte graphique

	    // Appliquez la translation pour simuler le déplacement de la caméra
	    g.translate(camera.getPosition().getX(), camera.getPosition().getY());

	    // Dessinez ici la carte et les entités
	    map.afficher_map();     // Imaginez que ceci dessine la carte entière

	    g.popTransform(); // R
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		camera.update_pos_cam(personnage);
		
		
		// Déplace le personnage en fonction des forces qui lui sont appliquées
		personnage.update_mouvement(delta);
		
		// Empêche le personnage de penetrer les plateformes
		obstacle.setPos_pers(personnage);
		obstacle.correction(personnage);
		obstacle1.setPos_pers(personnage);
		obstacle1.correction(personnage);
		obstacle2.setPos_pers(personnage);
		obstacle2.correction(personnage);
		
		input = gc.getInput();
		
		// Déplacement du personnage en fonction des commandes entrées par l'utilisateur
		if(input.isKeyDown(Input.KEY_RIGHT)){
			personnage.droite();
		}
		else if (personnage.isDroite() && input.isKeyDown(Input.KEY_RIGHT) == false){
			personnage.ralentis_droite();
		}
		
		if(input.isKeyDown(Input.KEY_LEFT)){
			personnage.gauche();
		}
		else if(personnage.isGauche() && input.isKeyDown(Input.KEY_LEFT) == false){
			personnage.ralentis_gauche();
		}
		
		if(personnage.isDroite() == false && personnage.isGauche() == false) {
			personnage.vitesse.setVx(0);
		}
		
		if(input.isKeyPressed(Input.KEY_SPACE)){
			personnage.saut();
		}
		if(personnage.isSaute()) {
			personnage.check_hauteur();
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

}
