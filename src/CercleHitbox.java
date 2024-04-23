public class CercleHitbox {
	
	protected Position position;
	protected float r;
	
	public CercleHitbox(Position position, float r) {
		this.position = position;
		this.r = r;
	}
	
	public float getR() {
		return r;
	}
}
