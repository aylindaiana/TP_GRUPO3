package negocioImpl;

import java.time.LocalDate;
import java.util.List;

import dao.UsuarioDao;
import dao.usuarioTipoDao;
import dao.UsuarioCredencialesDao;
import daoImpl.UsuarioDaoImpl;
import daoImpl.usuarioTipoDaoImpl;
import daoImpl.UsuarioCredencialesImpl;
import entidad.Usuario;
import entidad.UsuarioCredenciales;
import negocio.NegocioUsuario;
import negocio.NegocioUsuarioTipo;

public class NegocioUsuarioTipoImpl implements NegocioUsuarioTipo {

	@Override
	public int buscarTipoId(int id) {
		usuarioTipoDao dao = new usuarioTipoDaoImpl();
		return dao.buscarTipoId(id);
	}
}
