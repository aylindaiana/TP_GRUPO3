package ejercicio1;
import java.util.ArrayList;
import java.util.ListIterator;

public class MainEjercicio1_a {

    public static void main(String[] args) {   
    	
        // Creo un ArrayList de tipo Profesor
        ArrayList<Profesor> profesores = new ArrayList<>();

        // Creo y agrego los 5 objetos Profesor al ArrayList
        profesores.add(new Profesor("Profesora Casali", 50, "Matemáticas", 25));
        profesores.add(new Profesor("Profesora Gutierrez", 45, "Historia", 15));
        profesores.add(new Profesor("Profesor Kloster", 53, "Física", 12));
        profesores.add(new Profesor("Profesor Candiba", 49, "Química", 16));
        profesores.add(new Profesor("Profesora Solis", 28, "Biología", 3));

        // Muestro la info utilizando un ListIterator (recorrido hacia adelante)
        System.out.println("Recorrido hacia adelante:");
        ListIterator<Profesor> listIterator = profesores.listIterator();
        
        while (listIterator.hasNext()) {
            Profesor profesor = listIterator.next();
            System.out.println(profesor.toString());
        }
        
        // Muestro la info utilizando un ListIterator (recorrido hacia atrás)
        System.out.println("\nRecorrido hacia atrás:");
        
        while (listIterator.hasPrevious()) {
            Profesor profesor = listIterator.previous();
            System.out.println(profesor.toString());          
        }
    }
}
