package ejercicio2;

public class ProductoCongelado extends Producto {
	private double temperaturaCongelacionRecomendada;

	public ProductoCongelado() {
		super();
		this.temperaturaCongelacionRecomendada = 0.00001;
	}

	public ProductoCongelado(String fechaCaducidad, int numeroLote, double temperaturaCongelacionRecomendada) {
		super(fechaCaducidad, numeroLote);
		this.temperaturaCongelacionRecomendada = temperaturaCongelacionRecomendada;
	}

	public double getTemperaturaCongelacionRecomendada() {
		return temperaturaCongelacionRecomendada;
	}

	public void setTemperaturaCongelacionRecomendada(double temperaturaCongelacionRecomendada) {
		this.temperaturaCongelacionRecomendada = temperaturaCongelacionRecomendada;
	}

	@Override
	public String toString() {
		return "ProductoCongelado [temperaturaCongelacionRecomendada=" + temperaturaCongelacionRecomendada +", "+ super.toString();
	}
}
