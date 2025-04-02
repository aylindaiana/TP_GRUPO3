package ejercicio2;

public class ProductoRefrigerado extends Producto{
	private int codOrganismoSupervisionAlimentaria;

	public ProductoRefrigerado(String fechaCaducidad, int numeroLote, int codOrganismoSupervisionAlimentaria) {
		super(fechaCaducidad, numeroLote);
		this.codOrganismoSupervisionAlimentaria = codOrganismoSupervisionAlimentaria;
	}

	public ProductoRefrigerado() {
		super();
		this.codOrganismoSupervisionAlimentaria = 123456789; 
	}
	
	public int getCodOrganismoSupervisionAlimentaria() {
		return codOrganismoSupervisionAlimentaria;
	}

	public void setCodOrganismoSupervisionAlimentaria(int codOrganismoSupervisionAlimentaria) {
		this.codOrganismoSupervisionAlimentaria = codOrganismoSupervisionAlimentaria;
	}

	@Override
	public String toString() {
		return "ProductoRefrigerado [codOrganismoSupervisionAlimentaria=" + codOrganismoSupervisionAlimentaria + ", " + super.toString();
	}
}
