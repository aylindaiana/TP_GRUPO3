package ejercicio1;

public class Profesor extends Empleado {
	
	private String cargo;
	private int antiguedadDocente;
	
}

//Constructores
public Profesor() {
	super();
	this.cargo= "sin cargo";
	this.antiguedadDocente= 0;
}

public Profesor(string nombre, int edad, string cargo, int antiguedadDocente) {
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