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

    public Transferencia() {
    }

    // Getters y Setters
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
}
