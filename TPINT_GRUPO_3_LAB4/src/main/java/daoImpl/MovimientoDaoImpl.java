package daoImpl;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import dao.MovimientoDao;
import entidad.Movimiento;

public class MovimientoDaoImpl implements MovimientoDao{

	private final String INSERT = "CALL SP_INSERTAR_MOVIMIENTO(?, ?, ?, ?, ?, ?)";
	private final String LISTAR_MOVIMIENTOS_POR_CLIENTE = "CALL SP_LISTAR_MOVIMIENTOS_POR_CLIENTE(?)";
	private final String LISTAR_MOVIMIENTOS_POR_CUENTA = "CALL SP_LISTAR_MOVIMIENTOS_POR_CUENTA(?)";
	private final String LISTAR_MOVIMIENTOS = "CALL SP_LISTAR_MOVIMIENTOS()";
	private final String ULTIMO_ID_GENERADO = "CALL SP_ULTIMO_ID_MOVIMIENTO_GENERADO()";
	private final String TOTAL_POR_TIPO = "CALL SP_TOTAL_POR_TIPO(?, ?, ?)";
	private final String LISTAR_ULTIMOS_POR_CLIENTE = 
		    "SELECT * FROM movimientos WHERE IDCuentaOrigen IN "
		    + "(SELECT ID FROM cuenta WHERE IDCliente = ?) "
		    + "ORDER BY Fecha DESC LIMIT ?";
	
	@Override
	public boolean insertar(Movimiento movimiento) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		
		String query = INSERT;
		PreparedStatement st;
		Boolean exito = false;
        
		try {
			st = cn.prepareStatement(query);
	        st.setInt(1, movimiento.getIDCuentaOrigen()); 
	        st.setInt(2, movimiento.getIDCuentaDestino());
	        st.setDouble(3, movimiento.getMonto());
	        st.setDate(4, movimiento.getFecha());
	        st.setString(5, movimiento.getComentario());
	        st.setInt(6, movimiento.getIDTipoDeMovimiento());
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
	public List<Movimiento> listarMovimientosPorCuenta(int id) {

		Connection cn = Conexion.getConexion().getSQLConexion();
		String query = LISTAR_MOVIMIENTOS_POR_CUENTA;
		ArrayList<Movimiento> lista = new ArrayList<>();

        
        try {
        	PreparedStatement st;
			st = cn.prepareStatement(query);
	        st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                Movimiento movimiento = new Movimiento();
                movimiento.setID(rs.getInt("ID"));
                movimiento.setIDCuentaOrigen(rs.getInt("IDCuentaOrigen"));
                movimiento.setIDCuentaDestino(rs.getInt("IDCuentaDestino"));
                movimiento.setMonto((double)(rs.getInt("Monto")));
                movimiento.setFecha(rs.getDate("Fecha"));
                movimiento.setComentario(rs.getString("Comentario"));
                movimiento.setIDTipoDeMovimiento(rs.getInt("IDTipodeMovimiento"));
                movimiento.setDescripcionTipoDeMovimiento(rs.getString("DescripcionTipodeMovimiento"));
                
                lista.add(movimiento);
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
	}

	@Override
	public List<Movimiento> listarMovimientosPorCliente(int id) {

		Connection cn = Conexion.getConexion().getSQLConexion();
		String query = LISTAR_MOVIMIENTOS_POR_CLIENTE;
		ArrayList<Movimiento> lista = new ArrayList<>();

        
        try {
        	PreparedStatement st;
			st = cn.prepareStatement(query);
	        st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                Movimiento movimiento = new Movimiento();
                movimiento.setID(rs.getInt("ID"));
                movimiento.setIDCuentaOrigen(rs.getInt("IDCuentaOrigen"));
                movimiento.setIDCuentaDestino(rs.getInt("IDCuentaDestino"));
                movimiento.setMonto((double)(rs.getInt("Monto")));
                movimiento.setFecha(rs.getDate("Fecha"));
                movimiento.setComentario(rs.getString("Comentario"));
                movimiento.setIDTipoDeMovimiento(rs.getInt("IDTipodeMovimiento"));
                movimiento.setDescripcionTipoDeMovimiento(rs.getString("DescripcionTipodeMovimiento"));
                
                lista.add(movimiento);
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
	}

	@Override
	public List<Movimiento> listarMovimientos() {
		Connection cn = Conexion.getConexion().getSQLConexion();
		String query = LISTAR_MOVIMIENTOS;
		ArrayList<Movimiento> lista = new ArrayList<>();
		
        try {
        	PreparedStatement st;
			st = cn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                Movimiento movimiento = new Movimiento();
                movimiento.setID(rs.getInt("ID"));
                movimiento.setIDCuentaOrigen(rs.getInt("IDCuentaOrigen"));
                movimiento.setIDCuentaDestino(rs.getInt("IDCuentaDestino"));
                movimiento.setMonto((double)(rs.getInt("Monto")));
                movimiento.setFecha(rs.getDate("Fecha"));
                movimiento.setComentario(rs.getString("Comentario"));
                movimiento.setIDTipoDeMovimiento(rs.getInt("IDTipodeMovimiento"));
                movimiento.setDescripcionTipoDeMovimiento(rs.getString("DescripcionTipodeMovimiento"));
                
                lista.add(movimiento);
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public int ultimoIDGenerado() {
		Connection cn = Conexion.getConexion().getSQLConexion();
		String query = ULTIMO_ID_GENERADO;
		int ultimoID = -1;
        try {
        	PreparedStatement st;
			st = cn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                ultimoID = rs.getInt("ID");
                
                return ultimoID;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return ultimoID;
	}
	
	@Override
	public List<Movimiento> listarUltimosMovimientos(int idCliente, int limite) {
	    Connection cn = Conexion.getConexion().getSQLConexion();
	    ArrayList<Movimiento> lista = new ArrayList<>();
	    try {
	        PreparedStatement st = cn.prepareStatement(LISTAR_ULTIMOS_POR_CLIENTE);
	        st.setInt(1, idCliente);
	        st.setInt(2, limite);
	        ResultSet rs = st.executeQuery();
	        while (rs.next()) {
	            Movimiento movimiento = new Movimiento();
	            movimiento.setID(rs.getInt("ID"));
	            movimiento.setIDCuentaOrigen(rs.getInt("IDCuentaOrigen"));
	            movimiento.setIDCuentaDestino(rs.getInt("IDCuentaDestino"));
	            movimiento.setMonto(rs.getDouble("Monto"));
	            movimiento.setFecha(rs.getDate("Fecha"));
	            movimiento.setComentario(rs.getString("Comentario"));
                movimiento.setIDTipoDeMovimiento(rs.getInt("IDTipodeMovimiento"));
                movimiento.setDescripcionTipoDeMovimiento(rs.getString("DescripcionTipodeMovimiento"));
	            lista.add(movimiento);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return lista;
	}
	 /* Prueba para reporte */

	@Override
	public double obtenerTotalxTipo(int tipoMovimiento, LocalDate desde, LocalDate hasta) {
	    Connection cn = Conexion.getConexion().getSQLConexion();
	    double total = 0;

	    try {
	    	CallableStatement st = cn.prepareCall(TOTAL_POR_TIPO);
	        st.setInt(1, tipoMovimiento);
	        st.setDate(2, java.sql.Date.valueOf(desde));
	        st.setDate(3, java.sql.Date.valueOf(hasta));

	        ResultSet rs = st.executeQuery();

	        if (rs.next()) {
	            total = rs.getDouble("total");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return total;
	}
	
	@Override
	public List<Movimiento> listarPorCuentaConFiltros(int idCuenta, String tipo, double minMonto, double maxMonto, java.util.Date desde, java.util.Date hasta, String textoBusqueda, int offset, int limit) {
	    List<Movimiento> lista = new ArrayList<>();
	    Connection cn = Conexion.getConexion().getSQLConexion();
	    try {
	        CallableStatement cs = cn.prepareCall("CALL SP_LISTAR_MOVIMIENTOS_POR_CUENTA_Y_FILTROS(?, ?, ?, ?, ?, ?, ?, ?, ?)");
	        cs.setInt(1, idCuenta);
	        cs.setString(2, "%" + (textoBusqueda != null ? textoBusqueda : "") + "%");
	        cs.setString(3, tipo != null ? tipo : "");
	        cs.setDouble(4, minMonto);
	        cs.setDouble(5, maxMonto);
	        cs.setDate(6, new java.sql.Date(desde.getTime()));
	        cs.setDate(7, new java.sql.Date(hasta.getTime()));
	        cs.setInt(8, offset);
	        cs.setInt(9, limit);

	        ResultSet rs = cs.executeQuery();
	        while (rs.next()) {
	            Movimiento mov = new Movimiento();
	            mov.setFecha(rs.getDate("Fecha"));
	            mov.setIDCuentaDestino(rs.getInt("IDCuentaDestino"));
	            mov.setComentario(rs.getString("Detalle"));
	            mov.setMonto(rs.getDouble("Monto"));
	            mov.setDescripcionTipoDeMovimiento(rs.getString("TipoMovimiento"));
	            lista.add(mov);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return lista;
	}


	
}
