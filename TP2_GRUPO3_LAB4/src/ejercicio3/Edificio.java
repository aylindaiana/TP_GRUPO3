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
        if (superficie < 0) {
            throw new IllegalArgumentException("La superficie no puede ser negativa");
        }
        this.superficie = superficie;
    }

	@Override
	public String toString() {
	    return String.format("Edificio%nMetros de Superficie: %.1f%nTipo de Instalación n° \n", superficie);
	}

	
	
}
