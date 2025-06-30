package daoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.UsuarioDao;
import entidad.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {

    private static final String SELECT_ALL = "SELECT * FROM usuario";
    private static final String INSERT = "INSERT INTO usuario (Nombre, Apellido, Dni, Cuil, Sexo, Nacionalidad, FechaDeNacimiento, Direccion, ID_Localidad, ID_Provincia, CorreoElectronico, Telefono, IDUsuario, Estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE usuario SET Nombre = ?, Apellido = ?, Dni = ?, Cuil = ?, Sexo = ?, Nacionalidad = ?, FechaDeNacimiento = ?, Direccion = ?, ID_Localidad = ?, ID_Provincia = ?, CorreoElectronico = ?, Telefono = ?, IDUsuario = ? WHERE ID = ?";
    private static final String SELECT_BY_ID =
        "SELECT u.*, p.Nombre_Provincia, l.Nombre_Localidad " +
        "FROM usuario u " +
        "JOIN Provincia p ON u.ID_Provincia = p.ID_Provincia " +
        "JOIN Localidad l ON u.ID_Localidad = l.ID_Localidad " +
        "WHERE u.ID = ?";
    private static final String BAJA_LOGICA = "UPDATE usuario SET Estado = 0 WHERE ID = ?";
    private static final String ACTIVAR = "UPDATE usuario SET Estado = 1 WHERE ID = ?";

    @Override
    public boolean insertar(Usuario u) {
        boolean resultado = false;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, u.getNombre());
            stmt.setString(2, u.getApellido());
            stmt.setInt(3, u.getDni());
            stmt.setInt(4, u.getCuil());
            stmt.setString(5, u.getSexo());
            stmt.setString(6, u.getNacionalidad());
            stmt.setDate(7, u.getFechaDeNacimiento());
            stmt.setString(8, u.getDireccion());
            stmt.setInt(9, u.getIdLocalidad());
            stmt.setInt(10, u.getIdProvincia());
            stmt.setString(11, u.getCorreoElectronico());
            stmt.setString(12, u.getTelefono());
            stmt.setInt(13, u.getIdUsuario());
            stmt.setBoolean(14, true); // Estado activo

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
    public boolean modificar(Usuario u) {
        boolean resultado = false;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(UPDATE)) {
            stmt.setString(1, u.getNombre());
            stmt.setString(2, u.getApellido());
            stmt.setInt(3, u.getDni());
            stmt.setInt(4, u.getCuil());
            stmt.setString(5, u.getSexo());
            stmt.setString(6, u.getNacionalidad());
            stmt.setDate(7, u.getFechaDeNacimiento());
            stmt.setString(8, u.getDireccion());
            stmt.setInt(9, u.getIdLocalidad());
            stmt.setInt(10, u.getIdProvincia());
            stmt.setString(11, u.getCorreoElectronico());
            stmt.setString(12, u.getTelefono());
            stmt.setInt(13, u.getIdUsuario());
            stmt.setInt(14, u.getId());

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
    public Usuario obtenerPorId(int id) {
        Usuario usuario = null;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(SELECT_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = mapearUsuario(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearUsuario(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public int insertarYObtenerId(Usuario u) {
        int idGenerado = -1;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, u.getNombre());
            stmt.setString(2, u.getApellido());
            stmt.setInt(3, u.getDni());
            stmt.setInt(4, u.getCuil());
            stmt.setString(5, u.getSexo());
            stmt.setString(6, u.getNacionalidad());
            stmt.setDate(7, u.getFechaDeNacimiento());
            stmt.setString(8, u.getDireccion());
            stmt.setInt(9, u.getIdLocalidad());
            stmt.setInt(10, u.getIdProvincia());
            stmt.setString(11, u.getCorreoElectronico());
            stmt.setString(12, u.getTelefono());
            stmt.setInt(13, u.getIdUsuario());
            stmt.setBoolean(14, true); // Estado activo

            if (stmt.executeUpdate() > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        idGenerado = rs.getInt(1);
                    }
                }
                conexion.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            try { conexion.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        }
        return idGenerado;
    }

    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId(rs.getInt("ID"));
        u.setNombre(rs.getString("Nombre"));
        u.setApellido(rs.getString("Apellido"));
        u.setDni(rs.getInt("Dni"));
        u.setCuil(rs.getInt("Cuil"));
        u.setSexo(rs.getString("Sexo"));
        u.setNacionalidad(rs.getString("Nacionalidad"));
        u.setFechaDeNacimiento(rs.getDate("FechaDeNacimiento"));
        u.setDireccion(rs.getString("Direccion"));
        u.setIdLocalidad(rs.getInt("ID_Localidad"));
        u.setIdProvincia(rs.getInt("ID_Provincia"));
        u.setCorreoElectronico(rs.getString("CorreoElectronico"));
        u.setTelefono(rs.getString("Telefono"));
        u.setIdUsuario(rs.getInt("IDUsuario"));
        u.setEstado(rs.getBoolean("Estado"));
        u.setNombreProvincia(rs.getString("Nombre_Provincia"));
        u.setNombreLocalidad(rs.getString("Nombre_Localidad"));
        return u;
    }

    @Override
    public boolean bajaLogica(int id) {
        boolean resultado = false;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(BAJA_LOGICA)) {
            stmt.setInt(1, id);
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
    public boolean activar(int id) {
        boolean resultado = false;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(ACTIVAR)) {
            stmt.setInt(1, id);
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
    public boolean existeDni(int dni) {
        boolean existe = false;
        String sql = "SELECT COUNT(*) FROM usuario WHERE Dni = ?";
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, dni);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    existe = rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return existe;
    }

    @Override
    public List<Usuario> listarPaginado(int offset, int limit) {
        List<Usuario> lista = new ArrayList<>();
        String sql =
            "SELECT u.*, p.Nombre_Provincia, l.Nombre_Localidad " +
            "FROM usuario u " +
            "JOIN Provincia p ON u.ID_Provincia = p.ID_Provincia " +
            "JOIN Localidad l ON u.ID_Localidad = l.ID_Localidad " +
            "LIMIT ? OFFSET ?";
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, limit);
            stmt.setInt(2, offset);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearUsuario(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public int contarUsuarios() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM usuario";
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
}

