package ejercicio1;

public class Principal {

	public static void main(String[] args) {
		Profesor a = new Profesor("Profesor Jackson", 30, "Profesor de Matematicas", 5);
		System.out.println(a.toString());
		
		Profesor profesor1 = new Profesor("Pepe",25,"Profesor de Lengua",10);
		Profesor profesor2 = new Profesor("Pepe",25,"Profesor de Lengua",10);
		
		System.out.println(profesor1.toString());
		System.out.println(profesor2.toString());
		
		if (profesor1.equals(profesor2)) {
            System.out.println("Es el mismo profesor.");
        }
	}
}
