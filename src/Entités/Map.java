package Entités;
import org.newdawn.slick.SlickException;

import Hitbox.CarreHitbox;
import Mécanique.Position;

import java.util.ArrayList;


public class Map extends EntiteAffichable{

	private Joueur joueur;
	
	private Obstacle[] obstacles;
	private ArrayList<Obstacle> list_obstacles = new ArrayList<Obstacle>();
	
	private Xp[] xps;
	private ArrayList<Xp> list_xps = new ArrayList<Xp>();

	public Map(Joueur joueur, String path, CarreHitbox carre, Obstacle[] obstacles, Xp[] xps) throws SlickException {
		super(path, carre, new Position(0,0));
		this.joueur = joueur;
		this.obstacles = obstacles;	
		this.xps = xps;
	}
	
	public void afficher_map() {
		super.afficher_entite_affichable(false);
		joueur.afficher_entite_bougeable();
		for(int i = 0; i < obstacles.length; i++) {
			obstacles[i].afficher_entite_affichable(false);
		}
		for(int i = 0; i < xps.length; i++) {
			if(!xps[i].isAbsorbe())
				xps[i].afficher_entite_affichable(false);
		}
	}
	
	public void update_map(int delta) throws SlickException {
				// Déplace le personnage en fonction des forces qui lui sont appliquées
				joueur.update_mouvement(delta);
				joueur.getZone().update_position((-1* joueur.getZone().getWidth() / 2) + joueur.carre.getWidth()/2,( -1* joueur.getZone().getWidth() / 2) + joueur.carre.getHeigth()/2, joueur);
				
				for(int i = 0; i < xps.length; i++) {
					if(!xps[i].isAbsorbe()) {
						list_xps.add(xps[i]);
					}
				}
				
				for(Xp xp : list_xps) {
					if(joueur.considere_xp(xp)) {
						xp.calcul_intiale(joueur);
						xp.setStatique(false);
					}
					xp.update_xp(joueur, delta);
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
				list_xps.clear();
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
