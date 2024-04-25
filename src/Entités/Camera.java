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
	    	if(personnage.vitesse.getVx() > 0) {
	    		 position.setX(position.getX() - Math.abs(personnage.getDeplacementX()));
	    	}else if (personnage.vitesse.getVx() < 0){
	    		position.setX(position.getX() + Math.abs(personnage.getDeplacementX()));
	    	}
	    	
	    	if(personnage.vitesse.getVy() > 0) {
	    		 position.setY(position.getY() - Math.abs(personnage.getDeplacementY()));
	    	}else if (personnage.vitesse.getVy() < 0){
	    		position.setY(position.getY() + Math.abs(personnage.getDeplacementY()));
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
