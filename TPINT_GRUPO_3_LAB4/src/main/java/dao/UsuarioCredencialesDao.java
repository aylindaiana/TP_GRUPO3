package dao;
import entidad.UsuarioCredenciales;
import java.util.ArrayList;

public interface UsuarioCredencialesDao {
	public int iniciarSesion(String email, String pass);
	public boolean cerrarSesion();
	public boolean agregarCredenciales(UsuarioCredenciales credenciales);
	public boolean existeNombreUsuario(String nombreUsuario);
	int obtenerIDClientePorCredencial(int idCredencial);
}
