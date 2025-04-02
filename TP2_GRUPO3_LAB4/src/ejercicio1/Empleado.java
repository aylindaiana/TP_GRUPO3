package ejercicio1;

import java.util.Objects;

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
	
	//Método estático retorna el próximo ID
    public static int devuelveProximoID() {
	    return cont;
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
	        return "Empleado: " + nombre + ", Edad: " + edad + ", Id: " + id;
	    }
	
	// Equals de Empleado
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return edad == other.edad && Objects.equals(nombre, other.nombre);
	}
	 
}
	