package dao;

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
}