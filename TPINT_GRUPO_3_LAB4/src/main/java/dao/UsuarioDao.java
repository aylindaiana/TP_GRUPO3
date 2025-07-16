package dao;

import java.time.LocalDate;
import java.util.List;
import entidad.Usuario;

public interface UsuarioDao {
    public boolean insertar(Usuario usuario);            
    public boolean modificar(Usuario usuario);                    
    public Usuario obtenerPorId(int id);                 
    public List<Usuario> listar();          
    public int insertarYObtenerId(Usuario usuario);
    public boolean bajaLogica(int id);
    public boolean activar(int id);
    public boolean existeDni(int dni);
    public List<Usuario> listarPaginado(int offset, int limit);
    public int contarUsuarios();   
    public List<Usuario> listarClientes();
    public List<Usuario> buscarUsuarios(String criterio);
    public int contarUsuariosFiltrados(String criterio);
    public boolean existeCuil(long cuil);

    
    /* Prueba para reporte */
    public int contarNuevosClientes(LocalDate desde, LocalDate hasta);
}