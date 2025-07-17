package negocio;

import java.time.LocalDate;
import java.util.List;
import entidad.Usuario;
import entidad.UsuarioCredenciales;

public interface NegocioUsuario {
    public boolean agregarUsuario(Usuario u);
    public boolean modificarUsuario(Usuario u);
    public Usuario obtenerPorId(int id);
    public List<Usuario> obtenerTodos(); 
    public boolean agregarUsuarioConCredenciales(Usuario usuario, String nombreUsuario, String password);
    public boolean eliminarUsuario(int id);
    public boolean activarUsuario(int id);
    public boolean existeDni(int dni);
    public boolean existeNombreUsuario(String nombreUsuario);
    public List<Usuario> obtenerUsuariosPaginados(int pagina, int cantidadPorPagina);
    public int contarUsuarios();
	public List<Usuario> listarClientes();
	public List<Usuario> buscarUsuarios(String criterio);
	public int contarUsuariosFiltrados(String criterio);
	public boolean existeCuil(long cuil);
	
	UsuarioCredenciales obtenerCredencialesPorClienteId(int idCliente);
	boolean reactivarCredencialesPorCliente(int idCliente);
	boolean reactivarCredenciales(int idCliente);


}