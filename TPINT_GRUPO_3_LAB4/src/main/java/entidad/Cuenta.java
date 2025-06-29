package entidad;

import java.sql.Date;

public class Cuenta {
    private int id;
    private int idCliente;
    private String nombreCliente;
    private int idTipoDeCuenta;
    private Date fechaDeCreacion;
    private String cbu;
    private String descripcionTipoDeCuenta;
    private double saldo;
    private boolean estado;
    
    public Cuenta() {
    }
    
    public Cuenta(int idCliente, int idTipoDeCuenta, Date fechaDeCreacion, 
                  String cbu, double saldo, boolean estado) {
        this.idCliente = idCliente;
        this.idTipoDeCuenta = idTipoDeCuenta;
        this.fechaDeCreacion = fechaDeCreacion;
        this.cbu = cbu;
        this.saldo = saldo;
        this.estado = estado;
    }
    
    public Cuenta(int id, int idCliente, int idTipoDeCuenta, Date fechaDeCreacion, 
                  String cbu, double saldo, boolean estado) {
        this.id = id;
        this.idCliente = idCliente;
        this.idTipoDeCuenta = idTipoDeCuenta;
        this.fechaDeCreacion = fechaDeCreacion;
        this.cbu = cbu;
        this.saldo = saldo;
        this.estado = estado;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getIdCliente() {
        return idCliente;
    }
    
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    public int getIdTipoDeCuenta() {
        return idTipoDeCuenta;
    }
    
    public void setIdTipoDeCuenta(int idTipoDeCuenta) {
        this.idTipoDeCuenta = idTipoDeCuenta;
    }
    
    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }
    
    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }
    
    public String getCbu() {
        return cbu;
    }
    
    public String getDescripcionTipoDeCuenta() {
        return descripcionTipoDeCuenta;
    }
    
    public void setCbu(String cbu) {
        this.cbu = cbu;
    }
    
    public void setDescripcionTipoDeCuenta(String descripcionTipoDeCuenta) {
        this.descripcionTipoDeCuenta = descripcionTipoDeCuenta;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    public boolean isEstado() {
        return estado;
    }
    
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}