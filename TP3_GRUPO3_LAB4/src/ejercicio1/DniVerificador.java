package ejercicio1;

public class DniVerificador {
	
	public static void verificarDniInvalido(String dni) throws DniInvalido {
        if (dni == null || dni.isEmpty()) {
            throw new DniInvalido("El DNI no puede estar vacío");
        }
	}
}
