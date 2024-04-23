import org.newdawn.slick.SlickException;
public class Obstacle extends EntiteBougeable{
	
	// Savoir si c'est un mur ou une plateforme
	boolean mur;
	
	// Position du personnage par rapport à la plateforme
	int position_personnage;
	
	public int getPosition_personnage() {
		return position_personnage;
	}
	
	public Obstacle(Vitesse vitesse, Acceleration acceleration, String path, CarreHitbox carre, Position position, boolean mur) throws SlickException {
		super(vitesse, acceleration, path, carre, position);
		this.mur = mur;
	}
	
	public void setPos_pers(Personnage personnage) {
		// Définit une position par rapport à la plateforme : 0 pour top, 1 pour right, 2 pour bottom, 3 left, 4 aucun des autres
			if(personnage.position.getX() + personnage.carre.getWidth() > position.getX() && personnage.position.getX() < position.getX() + carre.getWidth()) {
				if(personnage.position.getY() + personnage.carre.getHeigth() < position.getY()) {
					position_personnage = 0;
				}
				else if(personnage.position.getY() > position.getY() + carre.getHeigth()) {
					position_personnage = 2;
				}	
			}
			else if(personnage.position.getY() + carre.getHeigth() > position.getY() && position.getY() < position.getY() + carre.getHeigth()) {
				if(personnage.position.getX() > position.getX() + carre.getWidth()) {
					position_personnage = 1;
				}
				else if(position.getX() + carre.getHeigth() < position.getX()) {
					position_personnage = 3;
				}
			}
			else {
				position_personnage = 4;
			}
	}
	
	// Empêche au personnage de pénétrer la plateforme en fonction des sa postions par rapport à celle ci
	public void correction(Personnage personnage) {
		if(position_personnage == 0 && personnage.position.getY() + personnage.carre.getHeigth() > position.getY()) {
			personnage.setSaut(false); // Important
		}
		if (position_personnage == 1 && personnage.position.getX() < position.getX() + carre.getWidth()){
			personnage.position.setX(position.getX() + carre.getWidth());
		}
		else if(position_personnage == 2 && personnage.position.getY() < position.getY() + carre.getHeigth()) {
			personnage.position.setY(position.getY() + carre.getHeigth());
		}
		else if(position_personnage == 3 && personnage.position.getX() + carre.getWidth() > position.getX()) {
			personnage.position.setX(position.getX() - carre.getWidth());
		}
	}
}

