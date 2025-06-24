package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.usuarioTipoDao;

public class usuarioTipoDaoImpl implements usuarioTipoDao{

	@Override
	public int buscarTipoId(int id) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		// Structured Procedure, ver en el SQL
		String query = "CALL tipo_cliente_id(?)";
		// preparar parametros
		PreparedStatement st;
		// almacenar resultado
		ResultSet rs;
        
		try {
			st = cn.prepareStatement(query);
	        st.setInt(1, id);
	        
	        rs = st.executeQuery();
	        
	        if(rs.next()) {
	        	if(rs.getInt("id") > 0) {
	        		return rs.getInt("id");
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

}
