package Dominio;

public class Empleado {
	private final int id;
	private String nombre;
	private int edad;
	private static int cont = 1000;
	
	//Constructores

	public Empleado() {
        this.id = cont++;
        this.nombre = "Sin nombre";
        this.edad = 99;
    }
	
	public Empleado(String nombre, int edad) {
        this.id = cont++;
        this.nombre = nombre;
        this.edad = edad;
    }
	
	//Getters y Setters
	
	//Edad
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	//Id
	public int getId() {
		return id;
	}
	
	//Nombre
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	} 
	
	//Metodo ToString()
	
	 @Override
	    public String toString() {
	        return "Empleado " + nombre + ", edad: " + edad + ", legajo: " + cont;
	    }
}

