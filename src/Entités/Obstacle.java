package Entités;

import org.newdawn.slick.SlickException;

import Hitbox.CarreHitbox;
import Mécanique.Position;


public class Obstacle extends EntiteAffichable{
	
	// Position du personnage par rapport à la plateforme
	private int position_personnage;
	
	public Obstacle(String path, CarreHitbox carre, Position position) throws SlickException {
		super(path, carre, position);
	}
	
	public int getPosition_personnage() {
		return position_personnage;
	}
	
	public void setPos_pers(Personnage personnage) {
		// Définit une position par rapport à la plateforme : 0 pour top, 1 pour right, 2 pour bottom, 3 left, 4 aucun des autres
		
		// Si le personnage est entre les coordonnées en x de la plateforme
		if(personnage.position.getX() + personnage.carre.getWidth() > position.getX() && personnage.position.getX() < position.getX() + carre.getWidth() || position.getX() > personnage.position.getX() && position.getX() + carre.getWidth() < personnage.position.getX() + personnage.carre.getWidth()) {
			if(personnage.position.getY() + personnage.carre.getHeigth() < position.getY()) {
				position_personnage = 0;
			}else if(personnage.position.getY() >= position.getY() + carre.getHeigth()) {
				position_personnage = 2;
			}	
		}else if(personnage.position.getY() + personnage.carre.getHeigth() > position.getY() && personnage.position.getY() < position.getY() + carre.getHeigth() || position.getY() > personnage.position.getY() && carre.getHeigth() + position.getY() < personnage.position.getY() + personnage.carre.getHeigth()) {
			if(personnage.position.getX() > position.getX() + carre.getWidth()) {
				position_personnage = 1;
			}
			else if(personnage.position.getX() + personnage.carre.getWidth() < position.getX()) {
				position_personnage = 3;
			}
		}else {
			position_personnage = 4;
		}
	}
	
	// Empèche au personnage de pénétrer la plateforme en fonction des sa postions par rapport à celle ci
	public void correction(Personnage personnage) {
		if(position_personnage == 0 && personnage.position.getY() + personnage.carre.getHeigth() > position.getY()) {
			personnage.position.setY(position.getY() - personnage.carre.getHeigth());
			personnage.setNb_saut(2);
			personnage.vitesse.setVy(0);
			personnage.setEst_sol(true);
		}
		else if (position_personnage == 1 && personnage.position.getX() < position.getX() + carre.getWidth()){
			personnage.position.setX(position.getX() + carre.getWidth());
		}
		else if(position_personnage == 2 && personnage.position.getY() < position.getY() + carre.getHeigth()) {
			personnage.position.setY(position.getY() + carre.getHeigth());
			personnage.cancel_force_y(personnage.getForce_saut());
			personnage.vitesse.setVy(0);
		}
		else if(position_personnage == 3 && personnage.position.getX() + personnage.carre.getWidth() > position.getX()) {
			personnage.position.setX(position.getX() - personnage.carre.getWidth());
		}
	}
	
	public void afficher_pos() {
		System.out.println(position_personnage);
	}
}

