package Entités;
import Hitbox.CarreHitbox;
import Mécanique.Position;

public class Camera {
	    // Déclaration des variables pour la position de la caméra, sa taille, et les limites de la carte.
	    private Position position;
	    private CarreHitbox carre;
	    

	    // Constructeur de la caméra.
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
