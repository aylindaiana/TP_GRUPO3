package negocioImpl;

import java.time.LocalDate;
import java.util.List;

import dao.UsuarioDao;
import dao.UsuarioCredencialesDao;
import daoImpl.UsuarioDaoImpl;
import daoImpl.UsuarioCredencialesImpl;
import entidad.Usuario;
import entidad.UsuarioCredenciales;
import negocio.NegocioUsuario;

public class NegocioUsuarioImpl implements NegocioUsuario {

    private UsuarioDao usuarioDao = new UsuarioDaoImpl();
    
    private UsuarioCredencialesDao credencialesDao = new UsuarioCredencialesImpl();

    @Override
    public boolean agregarUsuario(Usuario u) {
        return usuarioDao.insertar(u);
    }

    @Override
    public boolean modificarUsuario(Usuario u) {
        return usuarioDao.modificar(u);
    }

    @Override
    public Usuario obtenerPorId(int id) {
        return usuarioDao.obtenerPorId(id);
    }

    @Override
    public List<Usuario> obtenerTodos() {
        return usuarioDao.listar();
    }

    @Override
    public boolean agregarUsuarioConCredenciales(Usuario usuario, String nombreUsuario, String password) {
    	UsuarioCredencialesDao credDao = new UsuarioCredencialesImpl();
        int idGenerado = usuarioDao.insertarYObtenerId(usuario);
        if (idGenerado == -1) return false;

        UsuarioCredenciales credenciales = new UsuarioCredenciales();
        credenciales.setIDCliente(idGenerado);
        credenciales.setIDTipo(2); // Cliente
        credenciales.setUsuario(nombreUsuario);
        credenciales.setPassword(password);
        credenciales.setEstado(1); // Activo

        return credDao.agregarCredenciales(credenciales);
    }
    
    @Override
    public boolean eliminarUsuario(int id) {
        return usuarioDao.bajaLogica(id);
    }
    
    @Override
    public boolean activarUsuario(int id) {
        return usuarioDao.activar(id);
    }
    
    @Override
    public boolean existeDni(int dni) {
        return usuarioDao.existeDni(dni);
    }
    
    @Override
    public boolean existeNombreUsuario(String nombreUsuario) {
        UsuarioCredencialesDao dao = new UsuarioCredencialesImpl();
        return dao.existeNombreUsuario(nombreUsuario);
    }
    
    @Override
    public List<Usuario> obtenerUsuariosPaginados(int pagina, int cantidadPorPagina) {
        int offset = (pagina - 1) * cantidadPorPagina;
        return usuarioDao.listarPaginado(offset, cantidadPorPagina);
    }

    @Override
    public int contarUsuarios() {
        return usuarioDao.contarUsuarios();
    }

	@Override
	public List<Usuario> listarClientes() {
		return usuarioDao.listarClientes();
	}
	
	@Override
	public List<Usuario> buscarUsuarios(String criterio) {
	    return usuarioDao.buscarUsuarios(criterio);
	}

	@Override
	public int contarUsuariosFiltrados(String criterio) {
	    return usuarioDao.contarUsuariosFiltrados(criterio);
	}

	@Override
	public boolean existeCuil(long cuil) {
	    return usuarioDao.existeCuil(cuil);
	}

	public UsuarioCredenciales obtenerCredencialesPorClienteId(int idCliente) {
	    return credencialesDao.obtenerPorClienteId(idCliente);
	}
	
	@Override
	public boolean reactivarCredencialesPorCliente(int idCliente) {
	    UsuarioCredencialesDao dao = new UsuarioCredencialesImpl();
	    return dao.reactivarCredencialesPorCliente(idCliente);
	}
	
	@Override
	public boolean reactivarCredenciales(int idCliente) {
	    UsuarioCredencialesDao credDao = new UsuarioCredencialesImpl();
	    return credDao.reactivarCredencialesPorCliente(idCliente);
	}


}
