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
		this.numeroOficinas = numeroOficinas;
	}

	@Override
	public String toString() {
		return "Edificio de Oficinas \nNumero de Oficinas:" + numeroOficinas + "\nMetros de Superficie" + getSuperficie() + "\n";
	}
	
	
}
