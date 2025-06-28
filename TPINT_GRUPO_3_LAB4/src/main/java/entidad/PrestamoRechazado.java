package entidad;

public class PrestamoRechazado {
	
	int ID;
	int IDPrestamo;
	String MotivoRechazo;
	
	public PrestamoRechazado() {
		super();
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getIDPrestamo() {
		return IDPrestamo;
	}
	public void setIDPrestamo(int iDPrestamo) {
		IDPrestamo = iDPrestamo;
	}
	public String getMotivoRechazo() {
		return MotivoRechazo;
	}
	public void setMotivoRechazo(String motivoRechazo) {
		MotivoRechazo = motivoRechazo;
	}
	
}
