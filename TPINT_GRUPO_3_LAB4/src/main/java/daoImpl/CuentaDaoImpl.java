package daoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.CuentaDao;
import entidad.Cuenta;

public class CuentaDaoImpl implements CuentaDao {

    private static final String SELECT_ALL = "SELECT * FROM cuenta";
    private static final String INSERT = "INSERT INTO cuenta (IDCliente, IDTipoDeCuenta, FechaDeCreacion, CBU, Saldo, Estado) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE cuenta SET IDCliente = ?, IDTipoDeCuenta = ?, FechaDeCreacion = ?, CBU = ?, Saldo = ?, Estado = ? WHERE ID = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM cuenta WHERE ID = ?";

    @Override
    public boolean insertar(Cuenta c) {
        boolean resultado = false;
        PreparedStatement stmt;
        Connection conexion = Conexion.getConexion().getSQLConexion();

        try {
            stmt = conexion.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, c.getIdCliente());
            stmt.setInt(2, c.getIdTipoDeCuenta());
            stmt.setDate(3, c.getFechaDeCreacion());
            stmt.setString(4, c.getCbu());
            stmt.setDouble(5, c.getSaldo());
            stmt.setBoolean(6, c.isEstado());

            if (stmt.executeUpdate() > 0) {
                conexion.commit();
                resultado = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            try { conexion.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        }

        return resultado;
    }

    @Override
    public boolean modificar(Cuenta c) {
        boolean resultado = false;
        PreparedStatement stmt;
        Connection conexion = Conexion.getConexion().getSQLConexion();

        try {
            stmt = conexion.prepareStatement(UPDATE);
            stmt.setInt(1, c.getIdCliente());
            stmt.setInt(2, c.getIdTipoDeCuenta());
            stmt.setDate(3, c.getFechaDeCreacion());
            stmt.setString(4, c.getCbu());
            stmt.setDouble(5, c.getSaldo());
            stmt.setBoolean(6, c.isEstado());
            stmt.setInt(7, c.getId());

            if (stmt.executeUpdate() > 0) {
                conexion.commit();
                resultado = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            try { conexion.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        }

        return resultado;
    }

    @Override
    public Cuenta obtenerPorId(int id) {
        Cuenta cuenta = null;
        PreparedStatement stmt;
        ResultSet rs;
        Connection conexion = Conexion.getConexion().getSQLConexion();

        try {
            stmt = conexion.prepareStatement(SELECT_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                cuenta = mapearCuenta(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cuenta;
    }

    @Override
    public List<Cuenta> listar() {
        List<Cuenta> lista = new ArrayList<>();
        PreparedStatement stmt;
        ResultSet rs;
        Connection conexion = Conexion.getConexion().getSQLConexion();

        try {
            stmt = conexion.prepareStatement(SELECT_ALL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(mapearCuenta(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public int insertarYObtenerId(Cuenta c) {
        int idGenerado = -1;
        PreparedStatement stmt;
        Connection conexion = Conexion.getConexion().getSQLConexion();

        try {
            stmt = conexion.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, c.getIdCliente());
            stmt.setInt(2, c.getIdTipoDeCuenta());
            stmt.setDate(3, c.getFechaDeCreacion());
            stmt.setString(4, c.getCbu());
            stmt.setDouble(5, c.getSaldo());
            stmt.setBoolean(6, c.isEstado());

            if (stmt.executeUpdate() > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    idGenerado = rs.getInt(1);
                }
                conexion.commit();
            }

        } catch (Exception e) {
            e.printStackTrace();
            try { conexion.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        }

        return idGenerado;
    }

    private Cuenta mapearCuenta(ResultSet rs) throws SQLException {
        Cuenta c = new Cuenta();
        c.setId(rs.getInt("ID"));
        c.setIdCliente(rs.getInt("IDCliente"));
        c.setIdTipoDeCuenta(rs.getInt("IDTipoDeCuenta"));
        c.setFechaDeCreacion(rs.getDate("FechaDeCreacion"));
        c.setCbu(rs.getString("CBU"));
        c.setSaldo(rs.getDouble("Saldo"));
        c.setEstado(rs.getBoolean("Estado"));
        return c;
    }
}