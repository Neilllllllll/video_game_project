package Mécanique;

public class Vitesse {

	private float vx,vy;
	
	public Vitesse() {
		vx = 0;
		vy = 0;
	}

	public Vitesse(float vx, float vy) {
		this.vx = vx;
		this.vy = vy;
	}

	public float getVx() {
		return vx;
	}

	public void setVx(float vx) {
		this.vx = vx;
	}

	public float getVy() {
		return vy;
	}

	public void setVy(float vy) {
		this.vy = vy;
	}
	
	public boolean vxpluspetite(Vitesse v) {
		return vx < v.getVx();
	}
	
	public boolean vypluspetite(Vitesse v) {
		return vy < v.getVy();
	}
	
}
