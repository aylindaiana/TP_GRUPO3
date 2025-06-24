package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public int iniciarSesion(String email, String pass) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		// Structured Procedure, ver en el SQL
		String query = "CALL iniciar_sesion(?, ?)";
		// preparar parametros
		PreparedStatement st;
		// almacenar resultado
		ResultSet rs;
        
		try {
			st = cn.prepareStatement(query);
	        st.setString(1, email);
	        st.setString(2, pass);
	        
	        rs = st.executeQuery();
	        
	        if(rs.next()) {
	        	if(rs.getInt("IDCliente") > 0) {
	        		return rs.getInt("IDCliente");
	        	}
	        }
	        
		} catch (SQLException e) {
			e.printStackTrace();
            try {
                cn.rollback();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
		}
		return -1;
	}

    @Override
    public boolean cerrarSesion() {
        // Implementar lógica de cierre de sesión si es necesario
        return false;
    }
}
