package entidad;

import java.sql.Date;

public class Prestamo {

	private int ID;
	private String IDCliente;
	private int IDCuenta;
	private Date FechaDeAlta;
	private double Importe;
	private int PlazoPago;
	private double ImporteMensual;
	private int CantidadCuotas;
	private int Autorizacion;
	
	
	
	public Prestamo() {
		super();
	}

	public Prestamo(int iD, String iDCliente, int iDCuenta, Date fechaDeAlta, double importe, int plazoPago, 
			int importeMensual, int cantidadCuotas, int autorizacion) {
		super();
		ID = iD;
		IDCliente = iDCliente;
		IDCuenta = iDCuenta;
		FechaDeAlta = fechaDeAlta;
		Importe = importe;
		PlazoPago = plazoPago;
		ImporteMensual = importeMensual;
		CantidadCuotas = cantidadCuotas;
		Autorizacion = autorizacion;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getIDCliente() {
		return IDCliente;
	}
	public void setIDCliente(String iDCliente) {
		IDCliente = iDCliente;
	}
	public int getIDCuenta() {
		return IDCuenta;
	}
	public void setIDCuenta(int iDCuenta) {
		IDCuenta = iDCuenta;
	}
	public Date getFechaDeAlta() {
		return FechaDeAlta;
	}
	public void setFechaDeAlta(Date fechaDeAlta) {
		FechaDeAlta = fechaDeAlta;
	}
	public double getImporte() {
		return Importe;
	}
	public void setImporte(double importe) {
		Importe = importe;
	}
	public int getPlazoPago() {
		return PlazoPago;
	}
	public void setPlazoPago(int plazoPago) {
		PlazoPago = plazoPago;
	}
	public double getImporteMensual() {
		return ImporteMensual;
	}
	public void setImporteMensual(double d) {
		ImporteMensual = d;
	}
	public int getCantidadCuotas() {
		return CantidadCuotas;
	}
	public void setCantidadCuotas(int cantidadCuotas) {
		CantidadCuotas = cantidadCuotas;
	}
	public int getAutorizacion() {
		return Autorizacion;
	}
	public void setAutorizacion(int autorizacion) {
		Autorizacion = autorizacion;
	}
	
	@Override
	public String toString() {
		return "Prestamo [ID=" + ID + ", IDCliente=" + IDCliente + ", IDCuenta=" + IDCuenta + ", FechaDeAlta="
				+ FechaDeAlta + ", ImportePlazoPago=" + Importe + ", PlazoPago=" + PlazoPago
				+ ", ImporteMensual=" + ImporteMensual + ", CantidadCuotas=" + CantidadCuotas + ", Autorizacion="
				+ Autorizacion + "]";
	}
}
