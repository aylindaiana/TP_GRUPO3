package negocio;

import entidad.UsuarioCredenciales;

public interface NegocioUsuarioCredenciales {
	public int iniciarSesion(String user, String pass);
	public int obtenerIDClientePorCredencial(int id);
	public int buscarIdClienteInactivo(String user, String pass);
	public UsuarioCredenciales obtenerPorClienteId(int id);
	public boolean cerrarSesion();
}
