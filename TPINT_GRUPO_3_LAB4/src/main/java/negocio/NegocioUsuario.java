package negocio;

import java.time.LocalDate;
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
    public boolean existeNombreUsuario(String nombreUsuario);
    public List<Usuario> obtenerUsuariosPaginados(int pagina, int cantidadPorPagina);
    public int contarUsuarios();
    
    /* Prueba para reporte */
    public int contarNuevosClientes(LocalDate desde, LocalDate hasta);
}