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
