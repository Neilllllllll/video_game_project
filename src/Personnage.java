import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Personnage extends EntiteBougeable{
	
	// Coordonnée du saut
	private float cord_h_saut;
	
	// Hauteur du saut;
	private float hauteur_saut;
	private int nb_saut;
	
	// Zone où le personnage considère les plateformes
	private CarreHitbox zone;
	
	// Généraliser le constructeur
	public Personnage(Vitesse vitesse, Vitesse vitesselim, Acceleration acceleration, String path, CarreHitbox carre, Position position, float hauteur_saut, CarreHitbox zone) throws SlickException {
		super(vitesse, vitesselim, acceleration, path, carre, position);
		// Masse  
		masse = 50;
		
		// Hitbox pour détecter les plateformes 
		this.zone = zone;
		
		// Initialisation des forces du personnage - faut le mettre en paramètre car cela va varier en fonction du personnage
		force_saut = new Force(1000000, false, false);
		force_poids = new Force(100000, true, false);
		this.force_droite = new Force(10000, true, true);;
		this.force_gauche = new Force(10000, false, true);
		
		// Force mise par défaut
		this.ajouter_force_y(force_poids);
		
		// Hauteur du saut que peut faire le personnage
		this.hauteur_saut = hauteur_saut;
		
		// Images lorsqu'il est statique vers la droite
		images_animation_d = new Image[19];
		String path_s_d;
		for(int i = 0; i < images_animation_d.length; i++) {
			if(i < 10) {
				path_s_d = "wizar_d/Chara - BlueIdle0000" + i + ".png/";
			}
			else {
				path_s_d = "wizar_d/Chara - BlueIdle000" + i + ".png/";
			}
			images_animation_d[i] = new Image(path_s_d);
		}
		
		// Images lorsqu'il est statique vers la droite
		images_animation_g = new Image[19];
		String path_s_g;
		for(int i = 0; i < images_animation_g.length; i++) {
			if(i < 10) {
				path_s_g = "wizar_g/Chara - BlueIdle0000" + i + ".png/";
			}
			else {
				path_s_g = "wizar_g/Chara - BlueIdle000" + i + ".png/";
			}
			images_animation_g[i] = new Image(path_s_g);
		}
		
		// Images lorsqu'il va à droite
		images_animation_droite = new Image[19];
		String path_d;
		for(int i = 0; i < images_animation_droite.length; i++) {
			if(i < 10) {
				path_d = "wizar_droite/Chara_BlueWalk0000" + i + ".png/";
			}
			else {
				path_d = "wizar_droite/Chara_BlueWalk000" + i + ".png/";
			}
			images_animation_droite[i] = new Image(path_d);
		}
		
		// Images lorsqu'il va à droite
		images_animation_gauche = new Image[19];
		String path_g;
		for(int i = 0; i < images_animation_gauche.length; i++) {
			if(i < 10) {
				path_g = "wizar_gauche/Chara_BlueWalk0000" + i + ".png/";
			}
			else {
				path_g = "wizar_gauche/Chara_BlueWalk000" + i + ".png/";
			}
			images_animation_gauche[i] = new Image(path_g);
		}
		
		// Images lorsqu'il jump
		images_animation_jump_d = new Image[7];
		String path_j_d;
		for(int i = 0; i < images_animation_jump_d.length; i++) {
			path_j_d = "wizar_jump_d/CharaWizardJump_0000" + i + ".png/";
			images_animation_jump_d[i] = new Image(path_j_d);
		}
		
		// Images lorsqu'il jump
		images_animation_jump_g = new Image[7];
		String path_j_g;
		for(int i = 0; i < images_animation_jump_g.length; i++) {
			path_j_g = "wizar_jump_g/CharaWizardJump_0000" + i + ".png/";
			images_animation_jump_g[i] = new Image(path_j_g);
		}
	}
	
	public void saut() {
		if(nb_saut < 0) {
			return;
		}
		ajouter_force_y(force_saut);
		saute = true;
		nb_saut--;
		cord_h_saut = position.getY() + carre.getHeigth() - hauteur_saut;
	}
	
	public void check_hauteur() {
		if(position.getY() + carre.getHeigth() <= cord_h_saut) {
			saute = false;
			cancel_force_y(force_saut);
			System.out.println("hauteur atteinte ");
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
	
	public void check_input(Input input) {
		if(input.isKeyDown(Input.KEY_RIGHT)){
			droite();
		}
		else if (isDroite() && input.isKeyDown(Input.KEY_RIGHT) == false){
			ralentis_droite();
		}
		
		if(input.isKeyDown(Input.KEY_LEFT)){
			gauche();
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
}
