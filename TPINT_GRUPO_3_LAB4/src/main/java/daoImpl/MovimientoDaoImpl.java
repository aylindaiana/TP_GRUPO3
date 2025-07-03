package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.MovimientoDao;
import entidad.Movimiento;

public class MovimientoDaoImpl implements MovimientoDao{

	private final String INSERT = "CALL SP_INSERTAR_MOVIMIENTO(?, ?, ?, ?, ?, ?, ?)";
	private final String LISTAR_MOVIMIENTOS_POR_CLIENTE = "CALL SP_LISTAR_MOVIMIENTOS_POR_CLIENTE(?)";
	private final String LISTAR_MOVIMIENTOS = "CALL SP_LISTAR_MOVIMIENTOS()";
	private final String ULTIMO_ID_GENERADO = "CALL SP_ULTIMO_ID_MOVIMIENTO_GENERADO()";
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
	        st.setInt(1, movimiento.getID());
	        st.setInt(2, movimiento.getIDCuentaOrigen()); 
	        st.setInt(3, movimiento.getIDCuentaDestino());
	        st.setDouble(4, movimiento.getMonto());
	        st.setDate(5, movimiento.getFecha());
	        st.setString(6, movimiento.getComentario());
	        st.setInt(7, movimiento.getIDTipodeMovimiento());
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
                movimiento.setIDTipodeMovimiento(rs.getInt("IDTipodeMovimiento"));
                
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
                movimiento.setIDTipodeMovimiento(rs.getInt("IDTipodeMovimiento"));
                
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
	            movimiento.setIDTipodeMovimiento(rs.getInt("IDTipodeMovimiento"));
	            lista.add(movimiento);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return lista;
	}


	
}
