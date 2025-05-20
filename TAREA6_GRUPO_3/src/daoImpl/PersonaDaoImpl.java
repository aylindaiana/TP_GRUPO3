package daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.PersonaDao;
import entidad.Persona;

public class PersonaDaoImpl implements PersonaDao {

	@Override
	public boolean insertar(Persona persona) {
	    Connection cn = Conexion.getConexion().getSQLConexion();
	    String query = "INSERT INTO Personas (Dni, Nombre, Apellido) VALUES ('"
	                    + persona.getDni() + "', '"
	                    + persona.getNombre() + "', '"
	                    + persona.getApellido() + "')";
	    try {
	        Statement st = cn.createStatement();
	        if (st.executeUpdate(query) > 0) {
	            cn.commit();
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}


    @Override
    public boolean existeDni(String dni) {
        Connection cn = Conexion.getConexion().getSQLConexion();
        String query = "SELECT Dni FROM Personas WHERE Dni = '" + dni + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean eliminar(String dni) {
        Connection cn = Conexion.getConexion().getSQLConexion();
        String query = "DELETE FROM Personas WHERE Dni = ?";
        boolean eliminado = false;
        
        try {
            java.sql.PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, dni);

            if (ps.executeUpdate() > 0) {
                cn.commit();
                eliminado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                cn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return eliminado;
    }

	@Override
	public boolean modificar(Persona persona) {
		Connection cn = Conexion.getConexion().getSQLConexion();
	    String query = "UPDATE Personas SET Nombre = ?, Apellido = ? WHERE Dni = ?";
	    boolean modificado = false;
	    
	    try {
	        java.sql.PreparedStatement ps = cn.prepareStatement(query);
	        ps.setString(1, persona.getNombre());
	        ps.setString(2, persona.getApellido());
	        ps.setString(3, persona.getDni());
	
	        if (ps.executeUpdate() > 0) {
	            cn.commit();
	            modificado = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            cn.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	    return modificado;
	}
	
    @Override
    public List<Persona> obtenerTodas() {
        List<Persona> personas = new ArrayList<>();
        Connection cn = Conexion.getConexion().getSQLConexion();
        String query = "SELECT dni, nombre, apellido FROM personas";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                Persona persona = new Persona();
                persona.setDni(rs.getString("dni"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido(rs.getString("apellido"));
                personas.add(persona);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return personas;
    }

}
