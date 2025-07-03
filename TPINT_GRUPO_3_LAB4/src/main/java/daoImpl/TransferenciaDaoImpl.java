package daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import dao.TransferenciaDao;

public class TransferenciaDaoImpl implements TransferenciaDao {

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
            cs.setDate(4, new Date(System.currentTimeMillis()));
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
}
