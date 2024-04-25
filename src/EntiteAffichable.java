import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
public abstract class EntiteAffichable {
	protected Position position;
	protected CarreHitbox carre;
	protected Image images;
	protected Image[] images_animation;
	protected static int i = 0;
	
	public EntiteAffichable(String path, CarreHitbox carre, Position position) throws SlickException {
		images = new Image(path);
		this.position = position;
		this.carre = carre;
		images_animation = new Image[1];
	}
	
	public void afficher_entite_affichable() {
		if(images_animation[0] == null) {
			images.draw(position.getX(), position.getY(), carre.getWidth(), carre.getHeigth());
		}
		else {
			images_animation[i].draw(position.getX(), position.getY(), carre.getWidth(), carre.getHeigth());
			i++;
			if(i == images_animation.length-1) {
				i = 0;
			}
		}
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public CarreHitbox getCarre() {
		return carre;
	}

	public void setCarre(CarreHitbox carre) {
		this.carre = carre;
	}

	public Image getImages() {
		return images;
	}

	public void setImages(Image images) {
		this.images = images;
	}

	
	
}
