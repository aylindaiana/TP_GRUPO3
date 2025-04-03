package ejercicio3;

public class Edificio {
	private double superficie;
	
	public Edificio() {
		superficie = 2.0;
	}

	public Edificio(double superficie) {
		this.superficie = superficie;
	}

	public double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}

	@Override
	public String toString() {
		return "Edificio\nMetros de Superficie: " + superficie + "Tipo de Instalacion n°" + "\n";
	}

	
	
}
