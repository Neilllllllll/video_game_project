package Hitbox;
import org.newdawn.slick.Graphics;

import Mécanique.Position;

public class CercleHitbox {
	
	private Position position;
	private float r;
	
	public CercleHitbox(Position position, float r) {
		this.position = position;
		this.r = r;
	}
	
	public float getR() {
		return r;
	}
	
	public boolean iscollision(CarreHitbox carre) {
        // Trouver le point le plus proche sur le rectangle au centre du cercle
        float closestX = Math.max(carre.getPosition().getX(), Math.min(position.getX() + 500, carre.getPosition().getX() + carre.getWidth()));
        float closestY = Math.max(carre.getPosition().getY(), Math.min(position.getY() + 500, carre.getPosition().getY() + carre.getHeigth()));

        // Calculer la distance entre le centre du cercle et ce point
        float distanceX = Math.abs(position.getX() - closestX);
        float distanceY = Math.abs(position.getY() - closestY);
        float distanceSquared = (float) Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        // Comparer le carré de la distance au carré du rayon
        return distanceSquared <= r * r;
	}
	
	public void afficher(Graphics g) {
		g.drawOval(position.getX(), position.getY(), r, r);
	}
}
