package ejercicio2;

public class Producto {
	private String fechaCaducidad;
	private int numeroLote;

	public Producto(String fechaCaducidad, int numeroLote) {
		this.fechaCaducidad = fechaCaducidad;
		this.numeroLote = numeroLote;
	}
	
	public Producto() {
		this.fechaCaducidad = "01/01/1999";
		this.numeroLote = 123456789;
	}

	public String getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(String fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public int getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(int numeroLote) {
		this.numeroLote = numeroLote;
	}

	@Override
	public String toString() {
		return "Producto fecha de Caducidad=" + fechaCaducidad + ", numero Lote=" + numeroLote;
	}
}
