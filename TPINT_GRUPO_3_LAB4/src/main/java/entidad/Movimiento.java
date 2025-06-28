package entidad;

import java.sql.Date;

public class Movimiento {

	int ID;
	int IDCuentaOrigen;
	int IDCuentaDestino;
	double Monto;
	Date Fecha;
	String Comentario;
	int IDTipodeMovimiento;
	
	public Movimiento() {
		super();
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getIDCuentaOrigen() {
		return IDCuentaOrigen;
	}
	public void setIDCuentaOrigen(int iDCuentaOrigen) {
		IDCuentaOrigen = iDCuentaOrigen;
	}
	public int getIDCuentaDestino() {
		return IDCuentaDestino;
	}
	public void setIDCuentaDestino(int iDCuentaDestino) {
		IDCuentaDestino = iDCuentaDestino;
	}
	public double getMonto() {
		return Monto;
	}
	public void setMonto(double monto) {
		Monto = monto;
	}
	public Date getFecha() {
		return Fecha;
	}
	public void setFecha(Date fecha) {
		Fecha = fecha;
	}
	public String getComentario() {
		return Comentario;
	}
	public void setComentario(String comentario) {
		Comentario = comentario;
	}
	public int getIDTipodeMovimiento() {
		return IDTipodeMovimiento;
	}
	public void setIDTipodeMovimiento(int iDTipodeMovimiento) {
		IDTipodeMovimiento = iDTipodeMovimiento;
	}
	
	
}
