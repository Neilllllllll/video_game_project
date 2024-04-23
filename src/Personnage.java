import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Personnage extends EntiteBougeable{
	// Condition si l'utilisateur saute
	private boolean saut = false;
	
	// timer doit être différent de 0 car on l'utilise pour une division par rapport au saut
	private float timer = 1000;
	
	// Condition si le personnage est sur le sol ou non
	private boolean sol = false;
	
	// Zone où le personnage considère les plateformes
	private CercleHitbox cercle;
	
	public Personnage(Vitesse vitesse, Acceleration acceleration, String path, CarreHitbox carre, Position position) throws SlickException {
		super(vitesse, acceleration, path, carre, position);
		Position centre_pers = new Position(carre.position.getX() + (carre.getWidth()/2), carre.position.getY() + (carre.getHeigth()/2));
		cercle = new CercleHitbox(centre_pers, 22);
	}

	public boolean isSaut() {
		return saut;
	}

	public void setSaut(boolean saut) {
		this.saut = saut;
	}

	public float getTimer() {
		return timer;
	}

	public void setTimer(float timer) {
		this.timer = timer;
	}

	public boolean isSol(Obstacle obstacle) {
		float distance = obstacle.position.getY() - (position.getY() + carre.getHeigth());
		return distance <= 0 && obstacle.getPosition_personnage() == 0;
	}

	public void setSol(boolean sol) {
		this.sol = sol;
	}
	
	public void sauter(float delta) {
		position.setY(position.getY() - ((vitesse.getVy() * delta/1000)/ (timer / 1000)));
		timer += delta;
		if(timer/1000 >= 1.3) {
			saut = false;
			timer = 1000;
		}
	}
}
