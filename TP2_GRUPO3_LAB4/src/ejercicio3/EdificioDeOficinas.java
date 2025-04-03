package ejercicio3;

public class EdificioDeOficinas extends Edificio {
	private int numeroOficinas;

	public EdificioDeOficinas() {
		super();
	}

	public EdificioDeOficinas(int numeroOficinas) {
		super();
		this.setNumeroOficinas(numeroOficinas);
	}
	

	public EdificioDeOficinas(double superficie, int numeroOficinas) {
		super(superficie);
		this.setNumeroOficinas(numeroOficinas);
	}

	public int getNumeroOficinas() {
		return numeroOficinas;
	}

	public void setNumeroOficinas(int numeroOficinas) {
        if (numeroOficinas < 0) {
            throw new IllegalArgumentException("El número de oficinas no puede ser negativo");
        }
        this.numeroOficinas = numeroOficinas;
    }

	@Override
    public String toString() {
        return String.format(
            "Edificio de Oficinas%n" +
            "Número de Oficinas: %d%n" +
            "Superficie: %.2f m² \n",
            numeroOficinas, getSuperficie()
        );
    }
	
	
}
