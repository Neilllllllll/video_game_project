import org.newdawn.slick.Graphics;

public class CarreHitbox{
	
	protected Position position;
	protected float width;
	protected float heigth;
	
	public CarreHitbox(Position position, float width, float heigth) {
		this.position = position;
		this.width = width;
		this.heigth = heigth;
	}
	
	public void afficher(Graphics g) {
		g.drawRect(position.getX(), position.getY(), width, heigth);
	}
	
	public void update_position(float offsetx, float offsety, EntiteAffichable entite) {
		position.setX(entite.position.getX() + offsetx);
		position.setY(entite.position.getY() + offsety);
	}
	
	public static boolean sechevauche(CarreHitbox r1, CarreHitbox r2) {
		 // Vérifier si un rectangle est à côté de l'autre
		if (r1.position.getX() + r1.getWidth() < r2.position.getX() || r2.position.getX() + r2.getWidth() < r1.position.getX()) {
	        return false;
	    }
	    // Vérifier si un rectangle est au-dessus de l'autre
	    if (r1.position.getY() + r1.getHeigth() < r2.position.getY() || r2.position.getY() + r2.getHeigth() < r1.position.getY()) {
	        return false;
	    }
	    // Si aucune des conditions ci-dessus n'est vraie, les rectangles se chevauchent
	    return true;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeigth() {
		return heigth;
	}
	
	public Position getPosition() {
		return position;
	}
}
