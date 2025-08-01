package daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TransferenciaDao;
import entidad.Transferencia;

public class TransferenciaDaoImpl implements TransferenciaDao {

	private final String CONTAR_TRANSFERENCIAS = "CALL SP_CONTAR_TRANSFERENCIAS(?, ?)";
	
    @Override
    public boolean transferir(int cuentaOrigen, int cuentaDestino, double monto, String comentario) {
        Connection cn = Conexion.getConexion().getSQLConexion();
        boolean exito = false;

        String sql = "{ CALL sp_transferir(?, ?, ?, ?, ?) }";

        try {
            CallableStatement cs = cn.prepareCall(sql);
            cs.setInt(1, cuentaOrigen);
            cs.setInt(2, cuentaDestino);
            cs.setDouble(3, monto);
            cs.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            cs.setString(5, comentario);
            cs.executeUpdate();
            cn.commit();
            exito = true;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                cn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return exito;
    }

    @Override
    public List<Transferencia> listarTransferencias(
            int cuenta1,
            int cuenta2,
            int cuenta3,
            Timestamp fechaDesde,
            Timestamp fechaHasta,
            double montoMin,
            double montoMax,
            int offset,
            int limite) {

        List<Transferencia> lista = new ArrayList<>();
        Connection cn = Conexion.getConexion().getSQLConexion();

        String sql = "{ CALL SP_LISTAR_TRANSFERENCIAS_POR_CUENTAS(?, ?, ?, ?, ?, ?, ?, ?, ?) }";

        try {
            CallableStatement cs = cn.prepareCall(sql);
            cs.setInt(1, cuenta1);
            cs.setInt(2, cuenta2);
            cs.setInt(3, cuenta3);

            cs.setTimestamp(4, fechaDesde);
            cs.setTimestamp(5, fechaHasta);

            cs.setDouble(6, montoMin);
            cs.setDouble(7, montoMax);
            cs.setInt(8, offset);
            cs.setInt(9, limite);

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Transferencia t = new Transferencia();
                t.setId(rs.getInt("ID"));
                t.setIdCuentaOrigen(rs.getInt("IDCuentaOrigen"));
                t.setIdCuentaDestino(rs.getInt("IDCuentaDestino"));
                t.setNombreOrigen(rs.getString("NombreOrigen"));
                t.setCbuOrigen(rs.getString("CBUOrigen"));
                t.setNombreDestino(rs.getString("NombreDestino"));
                t.setCbuDestino(rs.getString("CBUDestino"));
                t.setMonto(rs.getDouble("Monto"));
                t.setFecha(rs.getTimestamp("Fecha"));
                t.setComentario(rs.getString("Comentario"));
                t.setTipoMovimiento(rs.getString("TipoMovimiento"));

                lista.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    

    @Override
    public String clientePropietarioOrigen(int id) {
    	Connection cn = Conexion.getConexion().getSQLConexion();

        String sql = "{ CALL sp_listar_cliente_cuenta(?) }";

        try {
            CallableStatement cs = cn.prepareCall(sql);
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                String nombre = (rs.getString("Nombre"));
                return nombre;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                cn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String clientePropietarioDestino(int id) {
    	Connection cn = Conexion.getConexion().getSQLConexion();

        String sql = "{ CALL sp_listar_cliente_cuenta(?) }";

        try {
            CallableStatement cs = cn.prepareCall(sql);
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                String nombre = (rs.getString("Nombre"));
                return nombre;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                cn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    @Override
    public double saldoTotalEnTransferencia() {
        double total = 0;
        String sql = "SELECT COALESCE(SUM(Monto), 0) AS DineroTotal FROM transferencia WHERE Estado = 1;";
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                total = rs.getDouble(1);
                if (rs.wasNull()) {
                    total = 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
    
    @Override
    public int contarTransferencias(LocalDate desde, LocalDate hasta) {
        Connection cn = Conexion.getConexion().getSQLConexion();
        int total = 0;
        try {
            CallableStatement st = cn.prepareCall(CONTAR_TRANSFERENCIAS);
            st.setDate(1, java.sql.Date.valueOf(desde));
            st.setDate(2, java.sql.Date.valueOf(hasta));
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

