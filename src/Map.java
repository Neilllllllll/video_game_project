import org.newdawn.slick.SlickException;

public class Map extends EntiteAffichable{

	private Personnage personnage;
	private Obstacle[] obstacles;

	public void setPersonnage(Personnage personnage) {
		this.personnage = personnage;
	}

	public Map(Personnage personnage, String path, CarreHitbox carre) throws SlickException {
		super(path, carre, new Position(0,0));
		this.personnage = personnage;
	}
}
