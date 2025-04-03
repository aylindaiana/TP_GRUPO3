package ejercicio3;
import java.util.ArrayList;
import java.util.ListIterator;

public class mainEjercicio3 {
	public static void main(String[] args) {   
		Polideportivo p1 = new Polideportivo();
		Polideportivo p2 = new Polideportivo("Polideportivo n° 2", 2);
		Polideportivo p3 = new Polideportivo(15.5, "Polideportivo N°3", 3);
		EdificioDeOficinas eo1 = new EdificioDeOficinas(5);
		EdificioDeOficinas eo2 = new EdificioDeOficinas(35.8, 7);
		
		ArrayList<Edificio> lista = new ArrayList<>();
		lista.add(p1);
		lista.add(p2);
		lista.add(p3);
		lista.add(eo1);
		lista.add(eo2);

        ListIterator<Edificio> listIterator = lista.listIterator();
        
        while (listIterator.hasNext()) {
            Edificio edf = listIterator.next();
            System.out.println(edf.toString());
        }
	}
}
