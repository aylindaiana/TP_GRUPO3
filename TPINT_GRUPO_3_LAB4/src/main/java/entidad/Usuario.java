package entidad;

import java.sql.Date;

public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private int dni;
    private int cuil;
    private String sexo;
    private String nacionalidad;
    private Date fechaDeNacimiento;
    private String direccion;
    private int idLocalidad;
    private int idProvincia;
    private String nombreProvincia;
    private String nombreLocalidad;
    private String correoElectronico;
    private String telefono;
    private int idUsuario;
    private boolean estado;

    public Usuario() {
        super();
    }

    public Usuario(int id, String nombre, String apellido, int dni, int cuil, String sexo, String nacionalidad,
                   Date fechaDeNacimiento, String direccion, int idLocalidad, int idProvincia,
                   String correoElectronico, String telefono, int idUsuario, boolean estado) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.cuil = cuil;
        this.sexo = sexo;
        this.nacionalidad = nacionalidad;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.direccion = direccion;
        this.idLocalidad = idLocalidad;
        this.idProvincia = idProvincia;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.idUsuario = idUsuario;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public int getDni() { return dni; }
    public void setDni(int dni) { this.dni = dni; }

    public int getCuil() { return cuil; }
    public void setCuil(int cuil) { this.cuil = cuil; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }

    public Date getFechaDeNacimiento() { return fechaDeNacimiento; }
    public void setFechaDeNacimiento(Date fechaDeNacimiento) { this.fechaDeNacimiento = fechaDeNacimiento; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public int getIdLocalidad() { return idLocalidad; }
    public void setIdLocalidad(int idLocalidad) { this.idLocalidad = idLocalidad; }

    public int getIdProvincia() { return idProvincia; }
    public void setIdProvincia(int idProvincia) { this.idProvincia = idProvincia; }
    
    public String getNombreProvincia() { return nombreProvincia; }
    public void setNombreProvincia(String nombreProvincia) { this.nombreProvincia = nombreProvincia; }

    public String getNombreLocalidad() { return nombreLocalidad; }
    public void setNombreLocalidad(String nombreLocalidad) { this.nombreLocalidad = nombreLocalidad; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }
}
