package ejercicio3;

public class Polideportivo extends Edificio implements IInstalacionDeportiva {
	private String nombre;
	private int tipoDeInstalacion;

	public Polideportivo() {
		super();
		nombre = "Polideportivo sin nombre";
	}

	public Polideportivo(String nombre, int tipoDeInstalacion) {
		super();
		this.nombre = nombre;
		this.tipoDeInstalacion = tipoDeInstalacion;
	}


	public Polideportivo(Double superficie, String nombre, int tipoDeInstalacion) {
		super(superficie);
		this.tipoDeInstalacion = tipoDeInstalacion;
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getTipoDeInstalacion() {
		return tipoDeInstalacion;
	}
	
	public void setTipoDeInstalacion(int tipoDeInstalacion) {
		this.tipoDeInstalacion = tipoDeInstalacion;
	}

	@Override
	public String toString() {
		return "Edificio de tipo Polideportivo\nNombre: " + nombre + "\nMetros de Superficie: " + getSuperficie() + "\nTipo de Instalacion n°" + getTipoDeInstalacion() + "\n";
	}
	
	
}
