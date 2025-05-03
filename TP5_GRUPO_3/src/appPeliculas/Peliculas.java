package appPeliculas;

public class Peliculas {
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
}