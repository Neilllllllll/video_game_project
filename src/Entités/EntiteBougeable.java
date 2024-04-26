package Entités;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Hitbox.CarreHitbox;
import Mécanique.Acceleration;
import Mécanique.Force;
import Mécanique.Position;
import Mécanique.Vitesse;

//Classe abstraite représentant une entité pouvant se déplacer
public abstract class EntiteBougeable extends EntiteAffichable{
	
	protected Force force_y[];
	protected Force force_x[];
	
	protected float masse;
	
	private float deplacementx = 0;
	private float deplacementy = 0;
	
	protected Vitesse vitesse;
	protected Vitesse vitesselim;
	protected Acceleration acceleration;
	
	// Tableau des images par seconde du perso
	protected Image[] images_animation_droite;
	protected static int d = 0;
	protected Image[] images_animation_gauche;
	protected static int g = 0;
	protected Image[] images_animation_jump_d;
	protected static int j_d = 0;
	protected Image[] images_animation_jump_g;
	protected static int j_g = 0;
	
	// Etats 
	protected boolean droite = false;
	protected boolean gauche = false;
	protected boolean saute = false;
	protected boolean est_sol;
	protected static boolean direction_droite;
	
	// Force qui peut s'exercer sur l'entite
	protected Force force_saut;
	protected Force force_poids;
	protected Force force_droite;
	protected Force force_gauche;
	
	// Constructeur initialisant l'entité avec sa vitesse, accélération, et d'autres propriétés
	public EntiteBougeable(Vitesse vitesse, Vitesse vitesselim, Acceleration acceleration, String path, CarreHitbox carre, Position position) throws SlickException {
		super(path, carre, position);
		this.position = position;
		this.vitesse = vitesse;
		this.vitesselim = vitesselim;
		this.acceleration = acceleration;
		force_y = new Force[10];
		force_x = new Force[10];
		est_sol = true;
	}
	
	// Method pour afficher
	public void afficher_entite_bougeable() {
		if (saute == true) {
			if(direction_droite == true) {
				images_animation_jump_d[j_d].draw(position.getX(), position.getY(), carre.getWidth(), carre.getHeigth());
				j_d++;
				if(j_d == images_animation_jump_d.length-1) {
					j_d = 0;
				}
			}
			else {
				images_animation_jump_g[j_g].draw(position.getX(), position.getY(), carre.getWidth(), carre.getHeigth());
				j_g++;
				if(j_g == images_animation_jump_g.length-1) {
					j_g = 0;
				}
			}
		}
		else {
			if(est_sol == false) {
				if(direction_droite == true) {
					images_animation_jump_d[images_animation_jump_d.length-1].draw(position.getX(), position.getY(), carre.getWidth(), carre.getHeigth());
				}
				else {
					images_animation_jump_g[images_animation_jump_d.length-1].draw(position.getX(), position.getY(), carre.getWidth(), carre.getHeigth());
				}
			}
			
			else if(droite == true) {
				direction_droite = true;
				images_animation_droite[d].draw(position.getX(), position.getY(), carre.getWidth(), carre.getHeigth());
				d++;
				if(d == images_animation_droite.length-1) {
					d = 0;
				}
			}
			else if (gauche == true) {
				direction_droite = false;
				images_animation_gauche[g].draw(position.getX(), position.getY(), carre.getWidth(), carre.getHeigth());
				g++;
				if(g == images_animation_gauche.length-1) {
					g = 0;
				}
			}
			else {
				super.afficher_entite_affichable(direction_droite);
			}
		}
	}	
	
	// Méthode pour ajouter une force horizontale à l'entité
	public void ajouter_force_x(Force force) {
		if(!force.isHorizontale()) {
			System.out.println("On ne peut pas ajouter la force car elle est verticale");
			return;
		}
		for(int i = 0; i < force_x.length; i++) {
			if(force_x[i] == null) {
				force_x[i] = force;
				return;
			}
		}
		System.out.println("Force non ajouté");
	}
	
	// Méthode pour ajouter une force verticale à l'entité
	public void ajouter_force_y(Force force) {
		if(force.isHorizontale()) {
			System.out.println("On ne peut pas ajouter la force car elle est horizontale");
			return;
		}
		for(int i = 0; i < force_y.length; i++) {
			if(force_y[i] == null) {
				force_y[i] = force;
				return;
			}
		}
		System.out.println("Force non ajouté");
	}
	
	// Méthode pour supprimer une force verticale
	public void cancel_force_y(Force force) {
		if(force.isHorizontale() == true) {
			System.out.println("On ne peut pas retirer la force car elle est horizontale");
			return;
		}
		for(int i = 0; i < force_y.length; i++) {
			if(force_y[i] != null) {
				if(force_y[i] == force) {
					for(int j = i; j < force_y.length-1; j++) {
						force_y[j] = force_y[j+1];
						if(force_y[j+1] != null) {
							return;
						}
					}
				}
				System.out.println("Pas de force trouvée");
			}
		}
	}
	
	// Méthode pour supprimer une force horizontale
	public void cancel_force_x(Force force) {
		if(force.isHorizontale() == false) {
			System.out.println("On ne peut pas retirer la force car elle est verticale");
			return;
		}
		for(int i = 0; i < force_x.length; i++) {
			if(force_x[i] != null) {
				if(force_x[i] == force) {
					for(int j = i; j < force_x.length-1; j++) {
						force_x[j] = force_x[j+1];
						if(force_x[j+1] == null) {
							return;
						}
					}
					System.out.println("ici");
				}
				System.out.println("Pas de force trouvée");
			}
		}
	}
	
	// Méthode mettant à jour le mouvement de l'entité en fonction des forces appliquées
	public void update_mouvement(int delta) {
		
		float t = (float) delta/ (float) 1000;
		
		acceleration.setAy(pfd_y());
		if(acceleration.getAy() != 0) {
			deplacementy = (acceleration.getAy() * (float)Math.pow(t, 2))/2 + vitesse.getVy() * t;
			position.setY(position.getY() + deplacementy);
			if(vitesse.getVy() + acceleration.getAy()*t < vitesselim.getVy()) {
				vitesse.setVy(vitesse.getVy() + acceleration.getAy() * t);
			}
		}
		else {
			deplacementy = 0;
		}
		
		acceleration.setAx(pfd_x());
		if(acceleration.getAx() != 0) {
			deplacementx = (acceleration.getAx() * (float)Math.pow(t, 2))/2 + vitesse.getVx() * t;
			position.setX(position.getX() + deplacementx);
			if(Math.abs(vitesse.getVx() + acceleration.getAx()*t) < vitesselim.getVx()) {
				vitesse.setVx(vitesse.getVx() + acceleration.getAx() * t);
			}
		}
		else {
			deplacementx = 0;
		}
	}
	
	// Calcul de l'accélération verticale basée sur les forces appliquées
	public float pfd_y() {
		float sum = 0;
		for(int i = 0; i < force_y.length; i++) {
			if(force_y[i] != null) {
				sum += force_y[i].getNorme();
			}
			else {
				break;
			}
		}
		return sum/masse;
	}
	
	// Calcul de l'accélération horizontale basée sur les forces appliquées
	public float pfd_x() {
		float sum = 0;
		for(int i = 0; i < force_x.length; i++) {
			if(force_x[i] != null) {
				sum += force_x[i].getNorme();
			}
			else {
				break;
			}
		}
		return sum/masse;
	}

	public Force[] getForce_y() {
		return force_y;
	}

	public void setForce_y(Force[] force_y) {
		this.force_y = force_y;
	}

	public Force[] getForce_x() {
		return force_x;
	}

	public void setForce_x(Force[] force_x) {
		this.force_x = force_x;
	}

	public float getMasse() {
		return masse;
	}

	public void setMasse(float masse) {
		this.masse = masse;
	}

	public float getDeplacementX() {
		return deplacementx;
	}

	public void setDeplacementX(float deplacement) {
		this.deplacementx = deplacement;
	}
	
	public float getDeplacementY() {
		return deplacementy;
	}

	public void setDeplacementY(float deplacement) {
		this.deplacementy = deplacement;
	}

	public Vitesse getVitesse() {
		return vitesse;
	}

	public void setVitesse(Vitesse vitesse) {
		this.vitesse = vitesse;
	}

	public Vitesse getVitesselim() {
		return vitesselim;
	}

	public void setVitesselim(Vitesse vitesselim) {
		this.vitesselim = vitesselim;
	}

	public Acceleration getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Acceleration acceleration) {
		this.acceleration = acceleration;
	}

	public float getDeplacementx() {
		return deplacementx;
	}

	public void setDeplacementx(float deplacementx) {
		this.deplacementx = deplacementx;
	}

	public float getDeplacementy() {
		return deplacementy;
	}

	public void setDeplacementy(float deplacementy) {
		this.deplacementy = deplacementy;
	}

	public Image[] getImages_animation_droite() {
		return images_animation_droite;
	}

	public void setImages_animation_droite(Image[] images_animation_droite) {
		this.images_animation_droite = images_animation_droite;
	}

	public static int getD() {
		return d;
	}

	public static void setD(int d) {
		EntiteBougeable.d = d;
	}

	public Image[] getImages_animation_gauche() {
		return images_animation_gauche;
	}

	public void setImages_animation_gauche(Image[] images_animation_gauche) {
		this.images_animation_gauche = images_animation_gauche;
	}

	public static int getG() {
		return g;
	}

	public static void setG(int g) {
		EntiteBougeable.g = g;
	}

	public Image[] getImages_animation_jump_d() {
		return images_animation_jump_d;
	}

	public void setImages_animation_jump_d(Image[] images_animation_jump_d) {
		this.images_animation_jump_d = images_animation_jump_d;
	}

	public static int getJ_d() {
		return j_d;
	}

	public static void setJ_d(int j_d) {
		EntiteBougeable.j_d = j_d;
	}

	public Image[] getImages_animation_jump_g() {
		return images_animation_jump_g;
	}

	public void setImages_animation_jump_g(Image[] images_animation_jump_g) {
		this.images_animation_jump_g = images_animation_jump_g;
	}

	public static int getJ_g() {
		return j_g;
	}

	public static void setJ_g(int j_g) {
		EntiteBougeable.j_g = j_g;
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

	public boolean isSaute() {
		return saute;
	}

	public void setSaute(boolean saute) {
		this.saute = saute;
	}
	
	
	public boolean isEst_sol() {
		return est_sol;
	}

	public void setEst_sol(boolean est_sol) {
		this.est_sol = est_sol;
	}

	public static boolean isDirection_droite() {
		return direction_droite;
	}

	public static void setDirection_droite(boolean direction_droite) {
		EntiteBougeable.direction_droite = direction_droite;
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
}
