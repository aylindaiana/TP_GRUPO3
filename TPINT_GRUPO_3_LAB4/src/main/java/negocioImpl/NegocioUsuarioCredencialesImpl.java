package negocioImpl;

import daoImpl.UsuarioCredencialesImpl;
import negocio.NegocioUsuarioCredenciales;

public class NegocioUsuarioCredencialesImpl implements NegocioUsuarioCredenciales{

	@Override
	public int iniciarSesion(String email, String pass) {
		UsuarioCredencialesImpl dao = new UsuarioCredencialesImpl();
		return dao.iniciarSesion(email, pass);
	}

	@Override
	public boolean cerrarSesion() {
		return false;
	}

}
