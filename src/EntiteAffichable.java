import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
public abstract class EntiteAffichable {
	protected Position position;
	protected CarreHitbox carre;
	protected Image images;
	
	public EntiteAffichable(String path, CarreHitbox carre, Position position) throws SlickException {
		images = new Image(path);
		this.position = position;
		this.carre = carre;
	}
	
	public void afficher() {
		images.draw(position.getX(), position.getY(), carre.getWidth(), carre.getHeigth());
	}
	
}
