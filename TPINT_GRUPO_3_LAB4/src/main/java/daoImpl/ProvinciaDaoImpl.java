package daoImpl;

import entidad.Provincia;
import dao.ProvinciaDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProvinciaDaoImpl implements ProvinciaDao {

    private static final String SELECT_ALL = "SELECT * FROM Provincia";

    @Override
    public List<Provincia> listarProvincias() {
        List<Provincia> lista = new ArrayList<>();
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try {
            PreparedStatement stmt = conexion.prepareStatement(SELECT_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Provincia p = new Provincia();
                p.setIdProvincia(rs.getInt("ID_Provincia"));
                p.setNombreProvincia(rs.getString("Nombre_Provincia"));
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
