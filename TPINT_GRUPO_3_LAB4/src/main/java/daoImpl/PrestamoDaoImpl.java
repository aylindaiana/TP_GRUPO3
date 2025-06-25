package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.PrestamoDao;
import entidad.Cuenta;
import entidad.Prestamo;

public class PrestamoDaoImpl implements PrestamoDao{

	static String insert = "call sp_solicitar_prestamo(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	static String obtenerPorIDCliente = "call sp_listar_prestamos_por_cliente(?)";
	static String listar = "call sp_listar_prestamos";
	
	@Override
	public boolean insertar(Prestamo prestamo) {

		Connection cn = Conexion.getConexion().getSQLConexion();
		
		String query = insert;
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
		String query = obtenerPorIDCliente;
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
		String query = listar;
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

	
}
