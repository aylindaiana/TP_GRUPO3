package negocioImpl;

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
}
