package appPeliculas;

import java.util.Objects;

public class Peliculas implements Comparable<Peliculas> {
	private static int contador = 1; // contador para ID
	private int id;
	private String nombre; 
    private Categorias categoria; // Objeto de tipo Categorias (no es String)
    
    public Peliculas(String nombre, Categorias categoria) {
    	this.id = contador++;
        this.nombre = nombre;
        this.categoria = categoria;
    }
    
    // Get para ID, Set no es necesario.
    public int getId() {
        return id;
    }
    
    // Getter y Setter para nombre
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    // Getter y Setter para categoria
    public Categorias getCategoria() {
        return categoria;
    }
    
    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

	@Override
	public String toString() {
		return '"' + nombre + '"' + ", Genero:" + categoria;
	}

	@Override
	public int compareTo(Peliculas obj) {
		// comparo por nombre
		if((this.getNombre().toLowerCase().compareTo(obj.getNombre().toLowerCase())) != 0) {
			int n = this.getNombre().toLowerCase().compareTo(obj.getNombre().toLowerCase());
			return n;
		}
		// comparo por categoria
		return this.getCategoria().toString().compareTo(obj.getCategoria().toString());
	}
    
	// devolver contador
	public static int getProximoId() {
		return contador;
	}
    
}