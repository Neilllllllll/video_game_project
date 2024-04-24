import org.newdawn.slick.SlickException;

public class Map extends EntiteAffichable{

	private Personnage personnage;
	private Obstacle[] obstacles;

	public Map(Personnage personnage, String path, CarreHitbox carre, Obstacle[] obstacles) throws SlickException {
		super(path, carre, new Position(0,0));
		this.personnage = personnage;
		this.obstacles = obstacles;
	}
	
	public void afficher_map() {
		super.afficher();
		personnage.afficher();
		for(int i = 0; i < obstacles.length; i++) {
			obstacles[i].afficher();
		}
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
