package Entités;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Hitbox.CarreHitbox;
import Mécanique.Position;

public abstract class EntiteAffichable {
	protected Position position;
	protected CarreHitbox carre;
	protected Image[] images_animation_d;
	protected Image[] images_animation_g;
	protected int i = 0;
	protected int z = 0;
	
	public EntiteAffichable(String images_animation_g_path, CarreHitbox carre, Position position) throws SlickException {
		z = 0;
		this.position = position;
		this.carre = carre;
		this.images_animation_g = Animation.fill_Animation(images_animation_g_path);
		images_animation_d = new Image[1];	
		}
	
	public EntiteAffichable(CarreHitbox carre, String images_animation_d_path, Position position) throws SlickException {
		i = 0;
		this.position = position;
		this.carre = carre;
		this.images_animation_g = Animation.fill_Animation(images_animation_d_path);
		this.images_animation_g = new Image[1];	
	}
	
	public EntiteAffichable(String images_animation_d_path, String images_animation_g_path, CarreHitbox carre, Position position) throws SlickException {
		i = 0;
		z = 0;
		this.position = position;
		this.carre = carre;
		this.images_animation_g = Animation.fill_Animation(images_animation_g_path);
		this.images_animation_d = Animation.fill_Animation(images_animation_d_path);

	}
	
	public void afficher_entite_affichable(boolean direction_droite) {
		if(images_animation_d[0] != null && direction_droite == true) {
			if(i == images_animation_d.length) {
				i = 0;
			}
			images_animation_d[i].draw(position.getX(), position.getY(), carre.getWidth(), carre.getHeigth());
			i++;
		}
		else if (images_animation_g[0] != null && direction_droite == false) {
			if(z == images_animation_g.length) {
				z = 0;
			}
			images_animation_g[z].draw(position.getX(), position.getY(), carre.getWidth(), carre.getHeigth());
			z++;
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
}
