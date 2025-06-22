package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.UsuarioCredencialesDao;

public class UsuarioCredencialesImpl implements UsuarioCredencialesDao {
	@Override
	public boolean iniciarSesion(String email, String pass) {
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
	        	if(rs.getInt("resultado") > 0) {
	        		return true;
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
		return false;
	}

	@Override
	public boolean cerrarSesion() {
		// ver como lo cierro
	}

}
