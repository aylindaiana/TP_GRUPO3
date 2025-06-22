package negocioImpl;

import daoImpl.UsuarioCredencialesImpl;
import negocio.NegocioUsuarioCredenciales;

public class NegocioUsuarioCredencialesImpl implements NegocioUsuarioCredenciales{

	@Override
	public boolean iniciarSesion(String email, String pass) {
		UsuarioCredencialesImpl dao = new UsuarioCredencialesImpl();
		return dao.iniciarSesion(email, pass);
	}

	@Override
	public boolean cerrarSesion() {
		return false;
	}

}
