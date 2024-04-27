package Entités;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Hitbox.CarreHitbox;
import Mécanique.Acceleration;
import Mécanique.Position;
import Mécanique.Vitesse;

public class Joueur extends Personnage{

	public Joueur(
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
			position, 
			hauteur_saut, 
			zone);
	}
	
	public void check_input(Input input) {
		if(input.isKeyDown(Input.KEY_RIGHT)){
			droite();
			direction_droite = true;
		}
		else if (isDroite() && input.isKeyDown(Input.KEY_RIGHT) == false){
			ralentis_droite();
		}
		
		if(input.isKeyDown(Input.KEY_LEFT)){
			gauche();
			direction_droite = false;
		}
		else if(isGauche() && input.isKeyDown(Input.KEY_LEFT) == false){
			ralentis_gauche();
		}
		
		if(isDroite() == false && isGauche() == false) {
			vitesse.setVx(0);
		}
		if(input.isKeyPressed(Input.KEY_SPACE)){
			saut();
		}
		if(isSaute()) {
			check_hauteur();
		}
	}
	
	public boolean considere_xp(Xp xp) {
		if(CarreHitbox.sechevauche(zone, xp.carre)) {
			return true;
		}
		return false;
	}

}
