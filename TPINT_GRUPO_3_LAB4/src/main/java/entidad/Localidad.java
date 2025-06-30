package entidad;

public class Localidad {
    private int idLocalidad;
    private String nombreLocalidad;
    private int idProvincia;

    public Localidad() {
    }

    public Localidad(int idLocalidad, String nombreLocalidad, int idProvincia) {
        this.idLocalidad = idLocalidad;
        this.nombreLocalidad = nombreLocalidad;
        this.idProvincia = idProvincia;
    }

    public int getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getNombreLocalidad() {
        return nombreLocalidad;
    }

    public void setNombreLocalidad(String nombreLocalidad) {
        this.nombreLocalidad = nombreLocalidad;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }
}
