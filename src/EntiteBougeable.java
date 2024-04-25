import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

//Classe abstraite repr�sentant une entit� pouvant se d�placer
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
	protected Image[] images_animation_jump;
	protected static int j = 0;
	
	// Etats 
	protected boolean droite = false;
	protected boolean gauche = false;
	protected boolean saute = false;
	
	// Force qui peut s'exercer sur l'entite
	protected Force force_saut;
	protected Force force_poids;
	protected Force force_droite;
	protected Force force_gauche;
	
	// Constructeur initialisant l'entit� avec sa vitesse, acc�l�ration, et d'autres propri�t�s
	public EntiteBougeable(Vitesse vitesse, Vitesse vitesselim, Acceleration acceleration, String path, CarreHitbox carre, Position position) throws SlickException {
		super(path, carre, position);
		this.position = position;
		this.vitesse = vitesse;
		this.vitesselim = vitesselim;
		this.acceleration = acceleration;
		force_y = new Force[10];
		force_x = new Force[10];
	}
	
	// Method pour afficher
	public void afficher_entite_bougeable() {
		if(droite == true) {
			images_animation_droite[d].draw(position.getX(), position.getY(), carre.getWidth(), carre.getHeigth());
			d++;
			if(d == images_animation_droite.length-1) {
				d = 0;
			}
		}
		else if (gauche == true) {
			images_animation_gauche[g].draw(position.getX(), position.getY(), carre.getWidth(), carre.getHeigth());
			g++;
			if(g == images_animation_gauche.length-1) {
				g = 0;
			}
		}
		else if(saute == true) {
			images_animation_jump[j].draw(position.getX(), position.getY(), carre.getWidth(), carre.getHeigth());
			j++;
			if(j == images_animation_jump.length-1) {
				j = 0;
			}
		}
		else {
			super.afficher_entite_affichable();
		}
	}
	
	
	// M�thode pour ajouter une force horizontale � l'entit�
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
		System.out.println("Force non ajout�");
	}
	
	// M�thode pour ajouter une force verticale � l'entit�
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
		System.out.println("Force non ajout�");
	}
	
	// M�thode pour supprimer une force verticale
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
				System.out.println("Pas de force trouv�e");
			}
		}
	}
	
	// M�thode pour supprimer une force horizontale
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
				System.out.println("Pas de force trouv�e");
			}
		}
	}
	
	// M�thode mettant � jour le mouvement de l'entit� en fonction des forces appliqu�es
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
	
	// Calcul de l'acc�l�ration verticale bas�e sur les forces appliqu�es
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
	
	// Calcul de l'acc�l�ration horizontale bas�e sur les forces appliqu�es
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
	
	
	
	
	
}
