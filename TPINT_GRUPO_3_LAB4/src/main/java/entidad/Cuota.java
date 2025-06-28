package entidad;

import java.sql.Date;

public class Cuota {

    int ID;
    int IDPrestamo;
    int NumeroCuota;
    double Monto;
    Date FechaPago;
    String IDMovimiento;
    int Estado;

    public Cuota() {
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

    public int getNumeroCuota() {
        return NumeroCuota;
    }

    public void setNumeroCuota(int numeroCuota) {
        NumeroCuota = numeroCuota;
    }

    public double getMonto() {
        return Monto;
    }

    public void setMonto(double monto) {
        Monto = monto;
    }

    public Date getFechaPago() {
        return FechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        FechaPago = fechaPago;
    }

    public String getIDMovimiento() {
        return IDMovimiento;
    }

    public void setIDMovimiento(String iDMovimiento) {
        IDMovimiento = iDMovimiento;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }
}
