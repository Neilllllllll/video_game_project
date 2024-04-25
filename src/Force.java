
public class Force {
	
	private float norme;
	private boolean positif, horizontale;
	
	public Force() {
		norme = 0;
		positif = true;
		horizontale = true;
	}
	
	public Force(float norme, boolean positif, boolean horizontale) {
		if(positif == true) {
			this.norme = norme;
		}
		else {
			this.norme = norme * -1;
		}
		this.positif = positif;
		this.horizontale = horizontale;
	}
	
	public float getNorme() {
		return norme;
	}

	public void setNorme(float norme) {
		this.norme = norme;
	}

	public boolean isPositif() {
		return positif;
	}

	public void setPositif(boolean positif) {
		this.positif = positif;
	}

	public boolean isHorizontale() {
		return horizontale;
	}

	public void setHorizontale(boolean horizontale) {
		this.horizontale = horizontale;
	}
	
	public String toString() {
		return "norme : " + norme + " horizontale : " + horizontale + " positif : " + positif;
	}
}
