package negocio;

import java.util.List;
import entidad.Usuario;

public interface NegocioUsuario {
    public boolean agregarUsuario(Usuario u);
    public boolean modificarUsuario(Usuario u);
    public Usuario obtenerPorId(int id);
    public List<Usuario> obtenerTodos(); 
    public boolean agregarUsuarioConCredenciales(Usuario usuario, String nombreUsuario, String password);
    public boolean eliminarUsuario(int id);
    public boolean activarUsuario(int id);
    public boolean existeDni(int dni);
}