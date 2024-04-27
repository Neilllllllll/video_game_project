package Entités;
import org.newdawn.slick.SlickException;
import Hitbox.CarreHitbox;
import Mécanique.Position;

public class Xp extends EntiteAffichable{
	// Etat 
	private boolean statique;
	
	private float dx;
	private float dy;
	private float h;
	private float teta;
	
	private String path_explosion;
	
	public Xp(
			String images_animation_g_path, 
			CarreHitbox carre, 
			Position position) 
			throws SlickException {
		
		super(images_animation_g_path,
				carre, 
				position
		);
		statique = true;
		path_explosion = "explosion_niveau_5";
	}
	
	public void update_xp(Joueur joueur) {
		if(statique == true) {
			stationnaire();
		}
		else {
			System.out.println("oui");
			statique = false;
			dx = joueur.position.getX() - position.getX();
			dy = joueur.position.getY() - position.getY();
			h = (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
			teta = (float) Math.acos(Math.sqrt(Math.pow(dx, 2)) / h);
		}
	}
	
	public void go_joueur(Joueur joueur) throws SlickException {
		float offset = 5;
		
		float centre_xp_x = position.getX() + carre.getWidth()/2;
		float centre_xp_y = position.getY() + carre.getHeigth()/2;
		
		float centre_joueur_x = joueur.position.getX() + joueur.getZone().getWidth()/2;
		float centre_joueur_y =	joueur.position.getY() + joueur.getZone().getHeigth()/2;
		
		if(!(centre_xp_x < centre_joueur_x + offset && centre_xp_x > centre_joueur_x - offset && centre_xp_y > centre_joueur_y - offset && centre_xp_y < centre_joueur_y + offset)) {
			teta += 0.01;
			h -= 0.6;
			position.setX((float)(h*Math.cos(teta)) + centre_joueur_x - carre.getWidth()/2);
			position.setY((float)(h*Math.sin(teta)) + centre_joueur_y - carre.getWidth()/2);
		}
		else {
			atteint_joueur(joueur);
		}
		
	}
	
	public void stationnaire() {
		super.afficher_entite_affichable(false);
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

	public float getDx() {
		return dx;
	}

	public void setDx(float dx) {
		this.dx = dx;
	}

	public float getDy() {
		return dy;
	}

	public void setDy(float dy) {
		this.dy = dy;
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
