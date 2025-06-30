package daoImpl;

import entidad.Localidad;
import dao.LocalidadDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocalidadDaoImpl implements LocalidadDao {

    private static final String SELECT_ALL = "SELECT * FROM Localidad";

    @Override
    public List<Localidad> listarTodas() {
        List<Localidad> lista = new ArrayList<>();
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try {
            PreparedStatement stmt = conexion.prepareStatement(SELECT_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Localidad l = new Localidad();
                l.setIdLocalidad(rs.getInt("ID_Localidad"));
                l.setNombreLocalidad(rs.getString("Nombre_Localidad"));
                l.setIdProvincia(rs.getInt("ID_Provincia"));
                lista.add(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
