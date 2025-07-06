package daoImpl;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.CuotaDao;
import entidad.Cuota;

public class CuotaDaoImpl implements CuotaDao{
	
	private final String INSERT = "CALL SP_INSERTAR_CUOTA(?, ?, ?, ?, ?, ?, ?)";
	private final String LISTAR_CUOTAS_POR_PRESTAMO = "CALL SP_LISTAR_CUOTAS_POR_PRESTAMO(?)";
	private final String PAGAR_CUOTA = "UPDATE cuotas SET Estado = 1 WHERE ID = ?";
	
	/* Prueba para reporte */
	private final String CONTAR_CUOTAS_PAGADAS = "CALL SP_CONTAR_CUOTAS_PAGADAS(?, ?)";
	private final String TOTAL_RECAUDADO_CUOTAS = "CALL SP_TOTAL_RECAUDADO_CUOTAS(?, ?)";

	
	@Override
	public boolean insertar(Cuota cuota) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		
		String query = INSERT;
		PreparedStatement st;
		Boolean exito = false;
        
		try {
			st = cn.prepareStatement(query); 
	        st.setInt(1, cuota.getID());
	        st.setInt(2, cuota.getIDPrestamo());
	        st.setInt(3, cuota.getNumeroCuota());
	        st.setInt(4, (int)cuota.getMonto());
	        st.setDate(5, cuota.getFechaPago());
	        st.setString(6, cuota.getIDMovimiento());
	        st.setInt(7, cuota.getEstado());
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
	public List<Cuota> listarCuotasPorPrestamo(int id) {

		Connection cn = Conexion.getConexion().getSQLConexion();
		String query = LISTAR_CUOTAS_POR_PRESTAMO;
		ArrayList<Cuota> lista = new ArrayList<>();

        
        try {
        	PreparedStatement st;
			st = cn.prepareStatement(query);
	        st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                Cuota cuota = new Cuota();
                cuota.setID(rs.getInt("ID"));
                cuota.setIDPrestamo(rs.getInt("IDPrestamo"));
                cuota.setNumeroCuota(rs.getInt("NumeroCuota"));
                cuota.setMonto((double)(rs.getInt("Monto")));
                cuota.setFechaPago(rs.getDate("FechaPago"));
                cuota.setIDMovimiento(rs.getString("IDMovimiento"));
                cuota.setEstado(rs.getInt("Estado"));
                
                lista.add(cuota); 
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
	}

	@Override
	public boolean pagarCuota(int id) {

		Connection cn = Conexion.getConexion().getSQLConexion();
		
		String query = PAGAR_CUOTA;
		PreparedStatement st;
		Boolean exito = false;
        
		try {
			st = cn.prepareStatement(query); 
	        st.setInt(1, id);
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
	
	/* Prueba para reporte */
	
	@Override
	public int contarCuotasPagadas(LocalDate desde, LocalDate hasta) {
		Connection cn = Conexion.getConexion().getSQLConexion();
	    int total = 0;
	    try {
	    	CallableStatement st = cn.prepareCall(CONTAR_CUOTAS_PAGADAS);

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

	@Override
	public double totalRecaudadoEnCuotas(LocalDate desde, LocalDate hasta) {
		Connection cn = Conexion.getConexion().getSQLConexion();
	    double total = 0;
	    try {
	    	CallableStatement st = cn.prepareCall(TOTAL_RECAUDADO_CUOTAS);

	        st.setDate(1, Date.valueOf(desde));
	        st.setDate(2, Date.valueOf(hasta));

	        ResultSet rs = st.executeQuery();
	        if (rs.next()) {
	            total = rs.getDouble("total");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return total;
	}


}
