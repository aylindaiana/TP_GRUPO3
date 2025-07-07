package entidad;

import java.sql.Timestamp;

public class Transferencia {
    private int id;
    private int idCuentaOrigen;
    private int idCuentaDestino;
    private double monto;
    private Timestamp fecha;
    private String comentario;
    private String tipoMovimiento;
    private String nombreOrigen;
    private String cbuOrigen;
    private String nombreDestino;
    private String cbuDestino;

    public Transferencia() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getIdCuentaOrigen() {
        return idCuentaOrigen;
    }
    public void setIdCuentaOrigen(int idCuentaOrigen) {
        this.idCuentaOrigen = idCuentaOrigen;
    }

    public int getIdCuentaDestino() {
        return idCuentaDestino;
    }
    public void setIdCuentaDestino(int idCuentaDestino) {
        this.idCuentaDestino = idCuentaDestino;
    }

    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Timestamp getFecha() {
        return fecha;
    }
    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }
    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getNombreOrigen() {
        return nombreOrigen;
    }
    public void setNombreOrigen(String nombreOrigen) {
        this.nombreOrigen = nombreOrigen;
    }

    public String getCbuOrigen() {
        return cbuOrigen;
    }
    public void setCbuOrigen(String cbuOrigen) {
        this.cbuOrigen = cbuOrigen;
    }

    public String getNombreDestino() {
        return nombreDestino;
    }
    public void setNombreDestino(String nombreDestino) {
        this.nombreDestino = nombreDestino;
    }

    public String getCbuDestino() {
        return cbuDestino;
    }
    public void setCbuDestino(String cbuDestino) {
        this.cbuDestino = cbuDestino;
    }
}