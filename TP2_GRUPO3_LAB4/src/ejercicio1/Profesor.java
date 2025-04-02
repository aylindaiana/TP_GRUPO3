package ejercicio1;

import java.util.Objects;

public class Profesor extends Empleado implements Comparable<Profesor> {
	
	private String cargo;
	private int antiguedadDocente;

	//Constructores
	public Profesor() {
		super();
		this.cargo= "sin cargo";
		this.antiguedadDocente= 0;
	}
	
	public Profesor(String nombre, int edad, String cargo, int antiguedadDocente) {
		super(nombre, edad);
		this.cargo= cargo;
		this.antiguedadDocente= antiguedadDocente;
	}
	
	//Getters y Setters
	public String getCargo() {
	    return cargo;
	}
	
	public void setCargo(String cargo) {
	    this.cargo = cargo;
	}
	
	public int getAntiguedadDocente() {
	    return antiguedadDocente;
	}
	
	public void setAntiguedadDocente(int antiguedadDocente) {
	    this.antiguedadDocente = antiguedadDocente;
	}
	
	//Método toString() sobreescrito
	@Override
	public String toString() {
	    return "Profesor [ID: " + getId() + 
	           ", Nombre: " + getNombre() + 
	           ", Edad: " + getEdad() + 
	           ", Cargo: " + cargo + 
	           ", Antigüedad docente: " + antiguedadDocente + " años]";
	}
	
	@Override
	public int compareTo(Profesor o) {
		return Integer.compare(this.getId(), o.getId());
	}

	//Equals de Profesor
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		return antiguedadDocente == other.antiguedadDocente && Objects.equals(cargo, other.cargo);
	}
	
}