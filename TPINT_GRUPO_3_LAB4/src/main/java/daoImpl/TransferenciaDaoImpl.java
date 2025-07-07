package daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Timestamp; // ✅ Asegurarse de usar Timestamp
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TransferenciaDao;
import entidad.Transferencia;

public class TransferenciaDaoImpl implements TransferenciaDao {

    // === SP que ejecuta la transferencia ===
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

    // === SP que lista transferencias usando SP_LISTAR_TRANSFERENCIAS_POR_CUENTAS ===
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
}

