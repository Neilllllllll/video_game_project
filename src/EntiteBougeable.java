import org.newdawn.slick.SlickException;

public abstract class EntiteBougeable extends EntiteAffichable{
	protected Vitesse vitesse;
	protected Acceleration acceleration;
	
	public EntiteBougeable(Vitesse vitesse, Acceleration acceleration, String path, CarreHitbox carre, Position position) throws SlickException {
		super(path, carre, position);
		this.position = position;
		this.vitesse = vitesse;
		this.acceleration = acceleration;
	}
	
	public void droite(float delta) {
        position.setX(position.getX() + (acceleration.getAx() * (delta/1000) * (delta/1000) + vitesse.getVx() * delta/1000));
        vitesse.setVx(vitesse.getVx() + acceleration.getAx()*delta/1000);
    }

	public void gauche(float delta) {
        position.setX(position.getX() - (acceleration.getAx() * (delta/1000) * (delta/1000) + vitesse.getVx() * (delta/1000)));
        vitesse.setVx(vitesse.getVx() + acceleration.getAx()*delta/1000);
    }
	
	public void stopX() {
        vitesse.setVx(0);
    }
	
	public void stopY() {
        vitesse.setVy(0);
    }
	
	 public void bas(float delta) {
	        position.setY(position.getY() + (acceleration.getAy() * (delta/1000) * (delta/1000) + vitesse.getVy() * (delta/1000)));
	        vitesse.setVy(vitesse.getVy() + acceleration.getAy() * delta/1000);
	    }
	
}
