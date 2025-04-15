package ejercicio1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		Set<Persona> personas = new TreeSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader("Personas.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
            	//System.out.println("Línea leída: " + linea);
                String[] partes = linea.split("-");
                
                if (partes.length == 3) {
                    String nombre = partes[0].trim();
                    String apellido = partes[1].trim();
                    String dni = partes[2].trim();
                    
                    try {
                        DniVerificador.verificarDniInvalido(dni);
                        personas.add(new Persona(nombre, apellido, dni));
                    } catch (DniInvalido e) {
                        System.out.println("DNI inválido para " + nombre + " " + apellido + ": " + e.getMessage());
                    }
                } else {
                    System.out.println("Línea con formato incorrecto: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
	}
}
