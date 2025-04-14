package ejercicio1;

import java.io.IOException;

public class DniInvalido extends IOException {
	
	private static final long serialVersionUID = 1L;
	
    public DniInvalido() {
        super("El DNI contiene caracteres no válidos. Solo debe contener números.");
    }

    public DniInvalido(String mensaje) {
        super(mensaje);
    }
}