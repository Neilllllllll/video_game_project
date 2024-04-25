import org.newdawn.slick.SlickException;
import java.util.ArrayList;


public class Map extends EntiteAffichable{

	private Personnage personnage;
	private Obstacle[] obstacles = new Obstacle[40];
	private ArrayList<Obstacle> list_obstacles = new ArrayList<Obstacle>();


	public Map(Personnage personnage, String path, CarreHitbox carre, Obstacle[] obstacles) throws SlickException {
		super(path, carre, new Position(0,0));
		this.personnage = personnage;
		this.obstacles = obstacles;
		
	}
	
	public void afficher_map() {
		super.afficher_entite_affichable();
		personnage.afficher_entite_bougeable();
		for(int i = 0; i < obstacles.length; i++) {
			obstacles[i].afficher_entite_bougeable();
		}
	}
	
	public void update_map(int delta) {
		// Déplace le personnage en fonction des forces qui lui sont appliquées
				personnage.update_mouvement(delta);
				personnage.getZone().update_position((-1* personnage.getZone().getWidth() / 2) + personnage.carre.getWidth()/2,( -1* personnage.getZone().getWidth() / 2) + personnage.carre.getHeigth()/2, personnage);
				
				for(int i = 0; i < obstacles.length; i++) {
					if(personnage.considere_obs(obstacles[i])) {
						list_obstacles.add(obstacles[i]);
					}
				}
				for (Obstacle obstacle : list_obstacles) {
				    obstacle.setPos_pers(personnage);
				    obstacle.correction(personnage);
				}
				list_obstacles.clear();
	}

	public Personnage getPersonnage() {
		return personnage;
	}

	public void setPersonnage(Personnage personnage) {
		this.personnage = personnage;
	}

	public Obstacle[] getObstacles() {
		return obstacles;
	}

	public void setObstacles(Obstacle[] obstacles) {
		this.obstacles = obstacles;
	}
	
	
}
