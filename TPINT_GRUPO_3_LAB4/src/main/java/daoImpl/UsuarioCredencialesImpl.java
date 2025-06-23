package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dao.UsuarioCredencialesDao;
import entidad.UsuarioCredenciales;

public class UsuarioCredencialesImpl implements UsuarioCredencialesDao {

    private static final String INSERT = "INSERT INTO usuario_credenciales (IDCliente, IDTipo, Usuario, Contraseña, Estado) VALUES (?, ?, ?, ?, ?)";

    @Override
    public boolean agregarCredenciales(UsuarioCredenciales c) {
        boolean estado = false;
        PreparedStatement stmt;
        Connection conexion = Conexion.getConexion().getSQLConexion();

        try {
            stmt = conexion.prepareStatement(INSERT);
            stmt.setInt(1, c.getIDCliente());
            stmt.setInt(2, c.getIDTipo());
            stmt.setString(3, c.getUsuario());
            stmt.setString(4, c.getPassword());
            stmt.setInt(5, c.getEstado());

            if (stmt.executeUpdate() > 0) {
                conexion.commit();
                estado = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            try {
                conexion.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return estado;
    }

    @Override
    public boolean iniciarSesion(String email, String pass) {
        // Por ahora no implementado
        return false;
    }

    @Override
    public boolean cerrarSesion() {
        // Por ahora no implementado
        return false;
    }
}
