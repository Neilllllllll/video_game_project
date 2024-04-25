package Entités;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Hitbox.CarreHitbox;
import Mécanique.Position;

public abstract class EntiteAffichable {
	protected Position position;
	protected CarreHitbox carre;
	protected Image images;
	protected Image[] images_animation_d;
	protected Image[] images_animation_g;
	protected static int i = 0;
	protected static int z = 0;
	
	public EntiteAffichable(String path, CarreHitbox carre, Position position) throws SlickException {
		images = new Image(path);
		this.position = position;
		this.carre = carre;
		images_animation_d = new Image[1];
		images_animation_g = new Image[1];
	}
	
	public void afficher_entite_affichable(boolean direction_droite) {
		if(images_animation_d[0] != null && direction_droite == true) {
			images_animation_d[i].draw(position.getX(), position.getY(), carre.getWidth(), carre.getHeigth());
			i++;
			if(i == images_animation_d.length-1) {
				i = 0;
			}
		}
		else if(images_animation_g[0] != null && direction_droite == false) {
			images_animation_g[z].draw(position.getX(), position.getY(), carre.getWidth(), carre.getHeigth());
			z++;
			if(z == images_animation_g.length-1) {
				z = 0;
			}
		}
		else {
			images.draw(position.getX(), position.getY(), carre.getWidth(), carre.getHeigth());
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
