package Entités;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Hitbox.CarreHitbox;
import Mécanique.Acceleration;
import Mécanique.Force;
import Mécanique.Position;
import Mécanique.Vitesse;

public class Personnage extends EntiteBougeable{
	
	// Coordonnée du saut
	protected float cord_h_saut;
	
	// Hauteur du saut;
	protected float hauteur_saut;
	protected int nb_saut;
	
	// Zone où le personnage considère les plateformes
	protected CarreHitbox zone;
	
	// Généraliser le constructeur
	public Personnage(
			Vitesse vitesse, 
			Vitesse vitesselim, 
			Acceleration acceleration, 
			String images_animation_g_path, 
			String images_animation_d_path,
			String images_animation_droite,
			String images_animation_gauche,
			String images_animation_jump_d,
			String images_animation_jump_g,
			CarreHitbox carre, 
			Position position, 
			float hauteur_saut, 
			CarreHitbox zone
			) throws SlickException {
		super(
				vitesse, 
				vitesselim, 
				acceleration, 
				images_animation_g_path, 
				images_animation_d_path,
				images_animation_droite,
				images_animation_gauche,
				images_animation_jump_d,
				images_animation_jump_g,
				carre, 
				position);
		// Masse  
		masse = 50;
		
		// Hitbox pour détecter les plateformes 
		this.zone = zone;
		
		// Initialisation des forces du personnage - faut le mettre en paramètre car cela va varier en fonction du personnage
		force_saut = new Force(1000000, false, false);
		force_poids = new Force(100000, true, false);
		this.force_droite = new Force(10000, true, true);
		this.force_gauche = new Force(10000, false, true);
		
		// Force mise par défaut
		this.ajouter_force_y(force_poids);
		
		// Hauteur du saut que peut faire le personnage
		this.hauteur_saut = hauteur_saut;
	}
	
	public void saut() {
		if(nb_saut < 0) {
			return;
		}
		ajouter_force_y(force_saut);
		saute = true;
		est_sol = false;
		System.out.println(false);
		nb_saut--;
		cord_h_saut = position.getY() + carre.getHeigth() - hauteur_saut;
	}
	
	public void check_hauteur() {
		if(position.getY() + carre.getHeigth() <= cord_h_saut) {
			cancel_force_y(force_saut);
			saute = false;
		}
	}	
	
	public void afficher_force() {
		for(int i = 0; i < force_y.length; i++) {
			if(force_y[i] != null) {
				System.out.println(force_y[i]);
			}
		}
	}
	
	public void droite() {
		if(droite == false) {
			droite = true;
			ajouter_force_x(force_droite);
		}
	}
	
	public void ralentis_droite() {
		if(force_droite.getNorme() > 0) {
			force_droite.setNorme(force_droite.getNorme() * -1);
		}
		if(vitesse.getVx() + acceleration.getAx() <= 0) {
			cancel_force_x(force_droite);
			force_droite.setNorme(force_droite.getNorme() * -1);
			droite = false;
		}
	}
	
	public void gauche() {
		if(gauche == false) {
			gauche = true;
			ajouter_force_x(force_gauche);
		}
	}
	
	public void ralentis_gauche() {
		if(force_gauche.getNorme() < 0) {
			force_gauche.setNorme(force_gauche.getNorme() * -1);
		}
		if(vitesse.getVx() + acceleration.getAx() >= 0) {
			cancel_force_x(force_gauche);
			force_gauche.setNorme(force_gauche.getNorme() * -1);
			gauche = false;
		}
	}
	
	public boolean considere_obs(Obstacle obstacle) {
		if(CarreHitbox.sechevauche(zone, obstacle.carre)) {
			return true;
		}
		return false;
	}

	public boolean isDroite() {
		return droite;
	}

	public void setDroite(boolean droite) {
		this.droite = droite;
	}

	public boolean isGauche() {
		return gauche;
	}

	public void setGauche(boolean gauche) {
		this.gauche = gauche;
	}

	public Force getForce_saut() {
		return force_saut;
	}

	public void setForce_saut(Force force_saut) {
		this.force_saut = force_saut;
	}

	public Force getForce_poids() {
		return force_poids;
	}

	public void setForce_poids(Force force_poids) {
		this.force_poids = force_poids;
	}

	public Force getForce_droite() {
		return force_droite;
	}

	public void setForce_droite(Force force_droite) {
		this.force_droite = force_droite;
	}

	public Force getForce_gauche() {
		return force_gauche;
	}

	public void setForce_gauche(Force force_gauche) {
		this.force_gauche = force_gauche;
	}

	public boolean isSaute() {
		return saute;
	}

	public void setSaute(boolean saute) {
		this.saute = saute;
	}
	
	public int getNb_saut() {
		return nb_saut;
	}

	public void setNb_saut(int nb_saut) {
		this.nb_saut = nb_saut;
	}

	public CarreHitbox getZone() {
		return zone;
	}

	public void setZone(CarreHitbox zone) {
		this.zone = zone;
	}

	public float getCord_h_saut() {
		return cord_h_saut;
	}

	public void setCord_h_saut(float cord_h_saut) {
		this.cord_h_saut = cord_h_saut;
	}

	public float getHauteur_saut() {
		return hauteur_saut;
	}

	public void setHauteur_saut(float hauteur_saut) {
		this.hauteur_saut = hauteur_saut;
	}
	
}
