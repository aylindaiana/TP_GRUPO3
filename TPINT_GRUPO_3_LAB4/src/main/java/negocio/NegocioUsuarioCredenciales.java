package negocio;

public interface NegocioUsuarioCredenciales {
	public int iniciarSesion(String email, String pass);
	public boolean cerrarSesion();
}
