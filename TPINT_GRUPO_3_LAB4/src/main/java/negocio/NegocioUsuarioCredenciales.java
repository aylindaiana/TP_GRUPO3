package negocio;

public interface NegocioUsuarioCredenciales {
	public boolean iniciarSesion(String email, String pass);
	public boolean cerrarSesion();
}
