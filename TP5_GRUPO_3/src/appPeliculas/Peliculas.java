package appPeliculas;

public class Peliculas {
	private String nombre; 
    private Categorias categoria; // Objeto de tipo Categorias (no es String)
    
    public Peliculas(String nombre, Categorias categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
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
}