package ejercicio1;
import java.util.Objects;

public class Persona implements Comparable<Persona> {
    private String nombre;
    private String apellido;
    private String dni;

    public Persona(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDni() { return dni; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;
        Persona persona = (Persona) o;
        return nombre.equals(persona.nombre) &&
               apellido.equals(persona.apellido) &&
               dni.equals(persona.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, dni);
    }

    @Override
    public int compareTo(Persona otra) {
    	int compApellido = this.apellido.compareToIgnoreCase(otra.apellido);
        if (compApellido != 0) return compApellido;

        int compNombre = this.nombre.compareToIgnoreCase(otra.nombre);
        if (compNombre != 0) return compNombre;

        return this.dni.compareTo(otra.dni);
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " - DNI: " + dni;
    }
}