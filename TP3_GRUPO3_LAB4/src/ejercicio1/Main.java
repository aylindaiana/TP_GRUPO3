package ejercicio1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new FileReader("Personas.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println("Línea leída: " + linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
	}

}
