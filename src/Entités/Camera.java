package Entit�s;
import Hitbox.CarreHitbox;
import M�canique.Position;

public class Camera {
	    // D�claration des variables pour la position de la cam�ra, sa taille, et les limites de la carte.
	    private Position position;
	    private CarreHitbox carre;
	    

	    // Constructeur de la cam�ra.
	    public Camera(Position position, CarreHitbox carre) {
	    	this.position = position;
	    	this.carre = carre;
	    }
	    
	    public void update_pos_cam(Personnage personnage) {
	    	position.setX(-(personnage.position.getX() + (personnage.carre.getWidth()/2) - carre.getWidth()/2));
	    	position.setY(-(personnage.position.getY() + (personnage.carre.getHeigth()/2) - carre.getHeigth()/2));
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
