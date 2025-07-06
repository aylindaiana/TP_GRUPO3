package daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.PrestamoDao;
import entidad.Prestamo;

public class PrestamoDaoImpl implements PrestamoDao{

	static String INSERT = "call sp_solicitar_prestamo(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	static String OBTENER_POR_ID_CLIENTE = "call sp_listar_prestamos_por_cliente(?)";
	static String LISTAR = "call sp_listar_prestamos";

	static String ACTUALIZAR_ESTADO_PRESTAMO = "CALL SP_ACTUALIZACION_DE_ESTADO_PRESTAMO(?, ?)";
	static String ACTUALIZAR_TABLAS_EN_BASE_A_ESTADO_PRESTAMO = "CALL SP_ACTUALIZACION_TABLAS_DEPENDIENDO_DE_ESTADO_PRESTAMO(?, ?, ?, ?, ?, ?, ?, ?, ?)";

	static String BUSCAR_PRESTAMO_POR_ID = " CALL SP_BUSCAR_PRESTAMO_POR_ID(?)";
	
	private final String CONTAR_PRESTAMOS_APROBADOS = "CALL SP_CONTAR_PRESTAMOS_APROBADOS(?, ?)";
	static String OBTENER_POR_ID_CUENTA = "CALL SP_LISTAR_PRESTAMOS_POR_CUENTA(?)";
	static String CANT_PRESTAMOS_PENDIENTES_APROB="SELECT COUNT(*) FROM prestamos WHERE Autorizacion is null";

	
	@Override
	public boolean insertar(Prestamo prestamo) {

		Connection cn = Conexion.getConexion().getSQLConexion();
		
		String query = INSERT;
		PreparedStatement st;
		Boolean exito = false;
        
		try {
			st = cn.prepareStatement(query);
	        st.setInt(1, prestamo.getID());
	        st.setString(2, prestamo.getIDCliente());
	        st.setInt(3, prestamo.getIDCuenta());
	        st.setDate(4, prestamo.getFechaDeAlta());
	        st.setDouble(5, prestamo.getImporte());
	        st.setInt(6, prestamo.getPlazoPago());
	        st.setDouble(7, prestamo.getImporteMensual());
	        st.setInt(8, prestamo.getCantidadCuotas());
	        st.setInt(9, prestamo.getAutorizacion());
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
	public List<Prestamo> obtenerPorIdCliente(int id) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		String query = OBTENER_POR_ID_CLIENTE;
		ArrayList<Prestamo> lista = new ArrayList<>();

        
        try {
        	PreparedStatement st;
			st = cn.prepareStatement(query);
	        st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setID(rs.getInt("ID"));
                prestamo.setIDCliente(rs.getString("IDCliente"));
                prestamo.setIDCuenta(rs.getInt("IDCuenta"));
                prestamo.setFechaDeAlta(rs.getDate("FechaDeAlta"));
                prestamo.setImporte(rs.getDouble("Importe"));
                prestamo.setPlazoPago(rs.getInt("PlazoPago"));
                prestamo.setImporteMensual(rs.getInt("ImporteMensual"));
                prestamo.setCantidadCuotas(rs.getInt("CantidadCuotas"));
                prestamo.setAutorizacion(rs.getInt("Autorizacion"));
                
                lista.add(prestamo);
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
	}

	@Override
	public List<Prestamo> listar() {
		Connection cn = Conexion.getConexion().getSQLConexion();
		String query = LISTAR;
		ArrayList<Prestamo> lista = new ArrayList<>();

        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setID(rs.getInt("ID"));
                prestamo.setIDCliente(rs.getString("IDCliente"));
                prestamo.setIDCuenta(rs.getInt("IDCuenta"));
                prestamo.setFechaDeAlta(rs.getDate("FechaDeAlta"));
                prestamo.setImporte(rs.getDouble("Importe"));
                prestamo.setPlazoPago(rs.getInt("PlazoPago"));
                prestamo.setImporteMensual(rs.getInt("ImporteMensual"));
                prestamo.setCantidadCuotas(rs.getInt("CantidadCuotas"));
                prestamo.setAutorizacion(rs.getInt("Autorizacion"));
                
                lista.add(prestamo);
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
	}

	@Override
	public boolean actualizacionEstadoPrestamo(int id, int estado) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		
		String query = ACTUALIZAR_ESTADO_PRESTAMO;
		PreparedStatement st;
		Boolean exito = false;
        
		try {
			st = cn.prepareStatement(query);
	        st.setInt(1, id);
	        st.setInt(2, estado);
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
	public boolean actualizacionDeTablasEnBase_EstadoPrestamo(int IDPrestamo, int IDCuentaDestino, int EstadoPrestamo,
			int CantidadCuotas, double ImporteSolicitado, int IDCuentaOrigen, Date FechaSolicitudPrestamo,
			String ComentarioMovimiento, String MotivoRechazoPrestamo) {


		Connection cn = Conexion.getConexion().getSQLConexion();
		
		String query = ACTUALIZAR_TABLAS_EN_BASE_A_ESTADO_PRESTAMO;
		PreparedStatement st;
		Boolean exito = false;
        
		try {
			st = cn.prepareStatement(query);
	        st.setInt(1, IDPrestamo);
	        st.setInt(2, IDCuentaDestino);
	        st.setInt(3, EstadoPrestamo);
	        st.setInt(4, CantidadCuotas);
	        st.setDouble(5, ImporteSolicitado);
	        st.setInt(6, IDCuentaOrigen);
	        st.setDate(7, FechaSolicitudPrestamo);
	        st.setString(8, ComentarioMovimiento);
	        st.setString(9, MotivoRechazoPrestamo);
	        
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
	public Prestamo obtenerPorIDPrestamo(int id) {

		Connection cn = Conexion.getConexion().getSQLConexion();
		String query = BUSCAR_PRESTAMO_POR_ID;
		Prestamo prestamo = new Prestamo();

        
        try {
        	PreparedStatement st;
			st = cn.prepareStatement(query);
	        st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                prestamo.setID(rs.getInt("ID"));
                prestamo.setIDCliente(rs.getString("IDCliente"));
                prestamo.setIDCuenta(rs.getInt("IDCuenta"));
                prestamo.setFechaDeAlta(rs.getDate("FechaDeAlta"));
                prestamo.setImporte(rs.getDouble("Importe"));
                prestamo.setPlazoPago(rs.getInt("PlazoPago"));
                prestamo.setImporteMensual(rs.getInt("ImporteMensual"));
                prestamo.setCantidadCuotas(rs.getInt("CantidadCuotas"));
                prestamo.setAutorizacion(rs.getInt("Autorizacion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prestamo;

	}
	 /* Prueba para reporte */
	
	@Override
	public int contarPrestamosAprobados(LocalDate desde, LocalDate hasta) {
		Connection cn = Conexion.getConexion().getSQLConexion();
	    int total = 0;

	    try {
	    	CallableStatement st = cn.prepareCall(CONTAR_PRESTAMOS_APROBADOS);

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
	public List<Prestamo> obtenerPorIdCuenta(int id) {

		Connection cn = Conexion.getConexion().getSQLConexion();
		String query = OBTENER_POR_ID_CUENTA;
		ArrayList<Prestamo> lista = new ArrayList<>();

        
        try {
        	PreparedStatement st;
			st = cn.prepareStatement(query);
	        st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setID(rs.getInt("ID"));
                prestamo.setIDCliente(rs.getString("IDCliente"));
                prestamo.setIDCuenta(rs.getInt("IDCuenta"));
                prestamo.setFechaDeAlta(rs.getDate("FechaDeAlta"));
                prestamo.setImporte(rs.getDouble("Importe"));
                prestamo.setPlazoPago(rs.getInt("PlazoPago"));
                prestamo.setImporteMensual(rs.getInt("ImporteMensual"));
                prestamo.setCantidadCuotas(rs.getInt("CantidadCuotas"));
                prestamo.setAutorizacion(rs.getInt("Autorizacion"));
                
                lista.add(prestamo);
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
	}
	
//	@Override
//	public int contarPrestamosPendientes() {
//	    int total = 0;
//
//	    try (Connection cn = Conexion.getConexion().getSQLConexion();
//	         PreparedStatement st = cn.prepareStatement(CANT_PRESTAMOS_PENDIENTES_APROB);
//	         ResultSet rs = st.executeQuery()) {
//
//	        if (rs.next()) {
//	            total = rs.getInt("total");
//	        }
//
//	        if (!cn.getAutoCommit()) {
//	            cn.commit();
//	        }
//
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//
//	    return total;
//	}



}
