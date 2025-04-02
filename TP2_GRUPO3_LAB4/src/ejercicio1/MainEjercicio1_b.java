package ejercicio1;
import java.util.Iterator;
import java.util.TreeSet;

public class MainEjercicio1_b {

	public static void main(String[] args) {
		TreeSet<Profesor> profesores = new TreeSet<Profesor>();
		
		// creo los 5 objetos y lo agrego al treeset
        profesores.add(new Profesor("Profesora Wenner", 42, "Matemáticas", 25));
        profesores.add(new Profesor("Profesora Sar Fernandez", 35, "Historia", 15));
        profesores.add(new Profesor("Profesor Kloster", 53, "Física", 12));
        profesores.add(new Profesor("Profesor Lopez", 32, "Química", 16));
        profesores.add(new Profesor("Profesora Solis", 28, "Biología", 3));
        
        Iterator<Profesor> iterator = profesores.iterator();
        while (iterator.hasNext()) {
            Profesor profesor = iterator.next();
            System.out.println(profesor.toString());
        }

	}
}
