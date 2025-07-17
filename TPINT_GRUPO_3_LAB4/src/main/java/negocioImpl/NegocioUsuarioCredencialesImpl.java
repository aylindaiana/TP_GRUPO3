package negocioImpl;

import daoImpl.UsuarioCredencialesImpl;
import entidad.UsuarioCredenciales;
import negocio.NegocioUsuarioCredenciales;

public class NegocioUsuarioCredencialesImpl implements NegocioUsuarioCredenciales{
	public UsuarioCredencialesImpl dao = new UsuarioCredencialesImpl();
	@Override
	public int iniciarSesion(String user, String pass) {
		return dao.iniciarSesion(user, pass);
	}
	
	public int obtenerIDClientePorCredencial(int id) {
		return dao.obtenerIDClientePorCredencial(id);
	}
	
	public int buscarIdClienteInactivo(String user, String pass) {
		return dao.buscarIdClienteInactivo(user, pass);
	}
	
	public UsuarioCredenciales obtenerPorClienteId(int id) {
		return dao.obtenerPorClienteId(id);
	}

	@Override
	public boolean cerrarSesion() {
		return false;
	}

}
