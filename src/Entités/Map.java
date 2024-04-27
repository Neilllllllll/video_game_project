package Entit�s;
import org.newdawn.slick.SlickException;

import Hitbox.CarreHitbox;
import M�canique.Position;

import java.util.ArrayList;


public class Map extends EntiteAffichable{

	private Joueur joueur;
	private Obstacle[] obstacles = new Obstacle[40];
	private ArrayList<Obstacle> list_obstacles = new ArrayList<Obstacle>();
	
	private Xp xp;

	public Map(Joueur joueur, String path, CarreHitbox carre, Obstacle[] obstacles, Xp xp) throws SlickException {
		super(path, carre, new Position(0,0));
		this.joueur = joueur;
		this.obstacles = obstacles;	
		this.xp = xp;
	}
	
	public void afficher_map() {
		super.afficher_entite_affichable(false);
		joueur.afficher_entite_bougeable();
		for(int i = 0; i < obstacles.length; i++) {
			obstacles[i].afficher_entite_affichable(false);
		}
		xp.afficher_entite_affichable(false);
	}
	
	public void update_map(int delta) throws SlickException {
				// D�place le personnage en fonction des forces qui lui sont appliqu�es
				joueur.update_mouvement(delta);
				joueur.getZone().update_position((-1* joueur.getZone().getWidth() / 2) + joueur.carre.getWidth()/2,( -1* joueur.getZone().getWidth() / 2) + joueur.carre.getHeigth()/2, joueur);
				if(joueur.considere_xp(xp)) {
					xp.calcul_intiale(joueur);
					xp.setStatique(false);
				}
				xp.update_xp(joueur);
				if(!xp.isStatique()) {
					xp.go_joueur(joueur);
				}
				for(int i = 0; i < obstacles.length; i++) {
					if(joueur.considere_obs(obstacles[i])) {
						list_obstacles.add(obstacles[i]);
					}
				}
				for (Obstacle obstacle : list_obstacles) {
				    obstacle.setPos_pers(joueur);
				    obstacle.correction(joueur);
				}
				list_obstacles.clear();
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public Obstacle[] getObstacles() {
		return obstacles;
	}

	public void setObstacles(Obstacle[] obstacles) {
		this.obstacles = obstacles;
	}
		
}
