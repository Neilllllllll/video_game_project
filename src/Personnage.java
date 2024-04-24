import org.newdawn.slick.SlickException;

public class Personnage extends EntiteBougeable{
	
	// Etats 
	private boolean droite = false;
	private boolean gauche = false;
	private boolean saute = false;
	
	// Coordonnée du saut
	float cord_h_saut;
	
	// Hauteur du saut;
	float hauteur_saut;
	private int nb_saut;
	
	// Force du personnage
	private Force force_saut;
	private Force force_poids;
	private Force force_droite;
	private Force force_gauche;
	
	// Zone où le personnage considère les plateformes
	private CercleHitbox cercle;
	
	public Personnage(Vitesse vitesse, Vitesse vitesselim, Acceleration acceleration, String path, CarreHitbox carre, Position position, float hauteur_saut) throws SlickException {
		super(vitesse, vitesselim, acceleration, path, carre, position);
		masse = 50;
		Position centre_pers = new Position(carre.position.getX() + (carre.getWidth()/2), carre.position.getY() + (carre.getHeigth()/2));
		cercle = new CercleHitbox(centre_pers, 22);
		force_saut = new Force(1000000, false, false);
		force_poids = new Force(100000, true, false);
		this.force_droite = new Force(10000, true, true);;
		this.force_gauche = new Force(10000, false, true);
		this.ajouter_force_y(force_poids);
		this.hauteur_saut = hauteur_saut;
	}
	
	public void saut() {
		if(nb_saut < 0) {
			System.out.println("Limite de saut atteint");
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

	public CercleHitbox getCercle() {
		return cercle;
	}

	public void setCercle(CercleHitbox cercle) {
		this.cercle = cercle;
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
}
