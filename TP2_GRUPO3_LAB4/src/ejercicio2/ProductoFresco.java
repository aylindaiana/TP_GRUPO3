package ejercicio2;

public class ProductoFresco extends Producto{
	private String fechaEnvasado;
	private String paisOrigen;
	
	public ProductoFresco() {
		super();
		this.fechaEnvasado = "01/01/2025";
		this.paisOrigen = "Unknown";
	}

	public ProductoFresco(String fechaCaducidad, int numeroLote, String fechaEnvasado, String paisOrigen) {
		super(fechaCaducidad, numeroLote);
		this.fechaEnvasado = fechaEnvasado;
		this.paisOrigen = paisOrigen;
	}
	
	public String getFechaEnvasado() {
		return fechaEnvasado;
	}

	public void setFechaEnvasado(String fechaEnvasado) {
		this.fechaEnvasado = fechaEnvasado;
	}

	public String getPaisOrigen() {
		return paisOrigen;
	}

	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	@Override
	public String toString() {
	    return "Producto Fresco:\n" +
	           "  Fecha de envasado: " + fechaEnvasado + "\n" +
	           "  País de origen: " + paisOrigen + "\n" +
	           "  Fecha de caducidad: " + getFechaCaducidad() + "\n" +
	           "  Número de lote: " + getNumeroLote() + "\n";
	}

}
