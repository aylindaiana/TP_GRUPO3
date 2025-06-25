package daoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.UsuarioDao;
import entidad.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {

    private static final String SELECT_ALL = "SELECT * FROM usuario";
    private static final String INSERT = "INSERT INTO usuario (Nombre, Apellido, Dni, Cuil, Sexo, Nacionalidad, FechaDeNacimiento, Direccion, Localidad, Provincia, CorreoElectronico, Telefono, IDUsuario, Estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE usuario SET Nombre = ?, Apellido = ?, Dni = ?, Cuil = ?, Sexo = ?, Nacionalidad = ?, FechaDeNacimiento = ?, Direccion = ?, Localidad = ?, Provincia = ?, CorreoElectronico = ?, Telefono = ?, IDUsuario = ? WHERE ID = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM usuario WHERE ID = ?";
    private static final String BAJA_LOGICA = "UPDATE usuario SET Estado = 0 WHERE ID = ?";
    private static final String ACTIVAR = "UPDATE usuario SET Estado = 1 WHERE ID = ?";

    @Override
    public boolean insertar(Usuario u) {
        boolean resultado = false;
        PreparedStatement stmt;
        Connection conexion = Conexion.getConexion().getSQLConexion();

        try {
            stmt = conexion.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, u.getNombre());
            stmt.setString(2, u.getApellido());
            stmt.setInt(3, u.getDni());
            stmt.setInt(4, u.getCuil());
            stmt.setString(5, u.getSexo());
            stmt.setString(6, u.getNacionalidad());
            stmt.setDate(7, u.getFechaDeNacimiento());
            stmt.setString(8, u.getDireccion());
            stmt.setString(9, u.getLocalidad());
            stmt.setString(10, u.getProvincia());
            stmt.setString(11, u.getCorreoElectronico());
            stmt.setString(12, u.getTelefono());
            stmt.setInt(13, u.getIdUsuario());
            stmt.setBoolean(14, true); // o u.isEstado() si ya lo tiene cargado


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
        PreparedStatement stmt;
        Connection conexion = Conexion.getConexion().getSQLConexion();

        try {
            stmt = conexion.prepareStatement(UPDATE);
            stmt.setString(1, u.getNombre());
            stmt.setString(2, u.getApellido());
            stmt.setInt(3, u.getDni());
            stmt.setInt(4, u.getCuil());
            stmt.setString(5, u.getSexo());
            stmt.setString(6, u.getNacionalidad());
            stmt.setDate(7, u.getFechaDeNacimiento());
            stmt.setString(8, u.getDireccion());
            stmt.setString(9, u.getLocalidad());
            stmt.setString(10, u.getProvincia());
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
        PreparedStatement stmt;
        ResultSet rs;
        Connection conexion = Conexion.getConexion().getSQLConexion();

        try {
            stmt = conexion.prepareStatement(SELECT_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = mapearUsuario(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        PreparedStatement stmt;
        ResultSet rs;
        Connection conexion = Conexion.getConexion().getSQLConexion();

        try {
            stmt = conexion.prepareStatement(SELECT_ALL);
            rs = stmt.executeQuery();

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
        PreparedStatement stmt;
        Connection conexion = Conexion.getConexion().getSQLConexion();

        try {
            stmt = conexion.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, u.getNombre());
            stmt.setString(2, u.getApellido());
            stmt.setInt(3, u.getDni());
            stmt.setInt(4, u.getCuil());
            stmt.setString(5, u.getSexo());
            stmt.setString(6, u.getNacionalidad());
            stmt.setDate(7, u.getFechaDeNacimiento());
            stmt.setString(8, u.getDireccion());
            stmt.setString(9, u.getLocalidad());
            stmt.setString(10, u.getProvincia());
            stmt.setString(11, u.getCorreoElectronico());
            stmt.setString(12, u.getTelefono());
            stmt.setInt(13, u.getIdUsuario());
            stmt.setBoolean(14, true); // o u.isEstado() si ya lo tiene cargado


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
        u.setLocalidad(rs.getString("Localidad"));
        u.setProvincia(rs.getString("Provincia"));
        u.setCorreoElectronico(rs.getString("CorreoElectronico"));
        u.setTelefono(rs.getString("Telefono"));
        u.setIdUsuario(rs.getInt("IDUsuario"));
        u.setEstado(rs.getBoolean("Estado"));

        return u;
    }
    
    @Override
    public boolean bajaLogica(int id) {
        boolean resultado = false;
        PreparedStatement stmt = null;
        Connection conexion = Conexion.getConexion().getSQLConexion();

        try {
            stmt = conexion.prepareStatement(BAJA_LOGICA);
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
    
    public boolean activar(int id)
    {
    	boolean resultado = false;
        PreparedStatement stmt = null;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try {
            stmt = conexion.prepareStatement(ACTIVAR);
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
}
