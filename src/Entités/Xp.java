package Entités;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import Hitbox.CarreHitbox;
import Mécanique.Position;
import java.util.Random;


public class Xp extends EntiteAffichable{
	// Etat 
	private boolean statique;
	private float h;
	private float teta;
	private CarreHitbox zone; 
	private String path_explosion;
	
	public Xp(
			String images_animation_g_path, 
			CarreHitbox carre, 
			Position position,
			CarreHitbox zone
			)
			throws SlickException {
		
		super(images_animation_g_path,
				carre, 
				position
		);
		this.zone = zone;
		statique = true;
		path_explosion = "explosion_niveau_5";
	}
	
	public void update_xp(Joueur joueur, int delta) throws SlickException {
		if(statique == true) {
			stationnaire(delta);
		}
		else {
			go_joueur(joueur);
		}
	}
	
	public void calcul_intiale(Joueur joueur) {
	    if (statique) {
	        float dx = (position.getX() + carre.getWidth() / 2) - (joueur.position.getX() + joueur.carre.getWidth() / 2);
	        float dy = (position.getY() + carre.getHeigth() / 2) - (joueur.position.getY() + joueur.carre.getHeigth() / 2);

	        h = (float) Math.sqrt(dx * dx + dy * dy);  // Calcul de la distance initiale

	        // Calcul de l'angle initial en utilisant atan2 pour gérer correctement tous les quadrants
	        teta = (float) Math.atan2(dy, dx);
	    }
	}

	
	public void go_joueur(Joueur joueur) throws SlickException {
	    float offset = 5;
	    
	    float centre_xp_x = position.getX() + carre.getWidth() / 2;
	    float centre_xp_y = position.getY() + carre.getHeigth() / 2;
	    
	    float centre_joueur_x = joueur.position.getX() + joueur.carre.getWidth() / 2;
	    float centre_joueur_y = joueur.position.getY() + joueur.carre.getHeigth() / 2;
	    
	    // Vérification si l'objet est assez proche du joueur pour considérer qu'il l'a "atteint"
	    if (Math.abs(centre_xp_x - centre_joueur_x) <= offset && Math.abs(centre_xp_y - centre_joueur_y) <= offset) {
	        atteint_joueur(joueur);
	    } else {
	        // Mise à jour de l'angle et de la distance pour le mouvement en spirale
	        teta -= 0.1; // Décrémente l'angle pour la rotation
	        h -= 2; // Réduit la distance pour créer l'effet spirale
	        
	        // Mise à jour de la position basée sur l'angle et la distance modifiés
	        position.setX((float) (h * Math.cos(teta)) + centre_joueur_x - carre.getWidth() / 2);
	        position.setY((float) (h * Math.sin(teta)) + centre_joueur_y - carre.getHeigth() / 2);
	    }
	}

	

	public void stationnaire(int delta) {
        if (statique) {
            Random rand = new Random();
            float vx = rand.nextFloat();
            float vy = rand.nextFloat();
            position.setX(position.getX() + (10 * delta/100));
            position.setY(position.getY() + (10 * delta/100));
            if (position.getX() < zone.getPosition().getX() || position.getX() + carre.getWidth() > zone.getPosition().getX() + zone.getWidth()) {
                vx *= -1;
            }
            if (position.getY() < zone.getPosition().getY() || position.getY() + carre.getHeigth() > zone.getPosition().getY() + zone.getHeigth()) {
                vy *= -1;
            }
        }
    }



	
	public void atteint_joueur(Joueur joueur) throws SlickException {
		images_animation_g = Animation.fill_Animation(path_explosion);
	}

	public boolean isStatique() {
		return statique;
	}

	public void setStatique(boolean statique) {
		this.statique = statique;
	}

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}

	public float getTeta() {
		return teta;
	}

	public void setTeta(float teta) {
		this.teta = teta;
	}

	public String getPath_explosion() {
		return path_explosion;
	}

	public void setPath_explosion(String path_explosion) {
		this.path_explosion = path_explosion;
	}
	
	
	
}
