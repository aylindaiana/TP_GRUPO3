package ejercicio1;

public class DniVerificador {
	
	public static void verificarDniInvalido(String dni) throws DniInvalido {
        if (dni == null || dni.isEmpty()) {
            throw new DniInvalido("El DNI no puede estar vacío");
        }
        
        for (int i = 0; i < dni.length(); i++) {
			if((int)dni.charAt(i) < 48 || (int)dni.charAt(i) > 57){
				throw new DniInvalido("Error el dni solo puede contener valores numericos.");
			}
		}
        
	}
}
