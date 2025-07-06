package daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import dao.PrestamoRechazadoDao;
import entidad.PrestamoRechazado;

public class PrestamoRechazadoDaoImpl implements PrestamoRechazadoDao{

	private static final String INSERT = "CALL SP_INSERTAR_PRESTAMO_RECHAZADO(?, ?, ?)";
	private static final String SELECT_MOTIVO_PRESTAMO_RECHAZADO = "CALL SP_SELECT_MOTIVO_PRESTAMO_RECHAZADO(?)";
	
	private final String CONTAR_PRESTAMOS_RECHAZADOS = "CALL SP_CONTAR_PRESTAMOS_RECHAZADOS(?, ?)";

	
	@Override
	public boolean insert(PrestamoRechazado prestamoRechazado) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		
		String query = INSERT;
		PreparedStatement st;
		Boolean exito = false;
        
		try {
			st = cn.prepareStatement(query); 
	        st.setInt(1, prestamoRechazado.getID());
	        st.setInt(2, prestamoRechazado.getIDPrestamo());
	        st.setString(3, prestamoRechazado.getMotivoRechazo());
	        if(st.executeUpdate() > 0) {
	            cn.commit();
	            exito = true;
	        }
		} catch (SQLException e) {
			e.printStackTrace();
            try {
                cn.rollback();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
		}
		
		return exito;
	}

	@Override
	public PrestamoRechazado selectMotivoRechazo(int IDPrestamo) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		String query = SELECT_MOTIVO_PRESTAMO_RECHAZADO;
		PrestamoRechazado prestamoRechazado = new PrestamoRechazado();
        
		try {
        	PreparedStatement st;
			st = cn.prepareStatement(query);
			st.setInt(1, IDPrestamo);
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                prestamoRechazado.setID(rs.getInt("ID"));
                prestamoRechazado.setIDPrestamo(rs.getInt("IDPrestamo"));
                prestamoRechazado.setMotivoRechazo(rs.getString("MotivoRechazo"));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamoRechazado;
	}
	
	/* Prueba para reporte */ 
	@Override
	public int contarRechazos(LocalDate desde, LocalDate hasta) {
		Connection cn = Conexion.getConexion().getSQLConexion();
	    int total = 0;

	    try {
	    	CallableStatement st = cn.prepareCall(CONTAR_PRESTAMOS_RECHAZADOS);

	        st.setDate(1, Date.valueOf(desde));
	        st.setDate(2, Date.valueOf(hasta));

	        ResultSet rs = st.executeQuery();
	        if (rs.next()) {
	            total = rs.getInt("total");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return total;
	}


}
