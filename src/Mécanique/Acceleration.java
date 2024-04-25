package Mécanique;

public class Acceleration {

	private float ax,ay;
	
	public Acceleration() {
		ax = 0;
		ay = 0;
	}
	
	public Acceleration(float ax, float ay) {
		this.ax = ax;
		this.ay = ay;
	}

	public float getAx() {
		return ax;
	}

	public void setAx(float ax) {
		this.ax = ax;
	}

	public float getAy() {
		return ay;
	}

	public void setAy(float ay) {
		this.ay = ay;
	}
	
}
