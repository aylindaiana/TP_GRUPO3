package dao;
import entidad.UsuarioCredenciales;
import java.util.ArrayList;

public interface UsuarioCredencialesDao {
	public boolean iniciarSesion(String email, String pass);
	public boolean cerrarSesion();
	public boolean agregarCredenciales(UsuarioCredenciales credenciales);
}
