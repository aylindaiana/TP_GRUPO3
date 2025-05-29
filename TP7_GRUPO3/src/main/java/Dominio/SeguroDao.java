package Dominio;

import java.util.ArrayList;
import Dominio.TipoSeguro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SeguroDao {

	private String host = "jdbc:mysql://localhost:3307/";
	private String user = "root";
	private String pass = "passwordLabIv";
	private String dbName = "segurosgroup";

	public int obtenerProximoIdSeguro() {

	    int proximoId = 1;
	    Connection cn = null;

	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        cn = DriverManager.getConnection(host + dbName, user, pass);
	        Statement st = cn.createStatement();

	        String query = "SELECT MAX(idSeguro) FROM seguros";
	        ResultSet rs = st.executeQuery(query);

	        if (rs.next()) {
	            int maxId = rs.getInt(1);
	            if (maxId > 0)
	                proximoId = maxId + 1;
	        }

	        cn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return proximoId;
	}
	
	public ArrayList<TipoSeguro> obtenerTiposSeguro() {
	    ArrayList<TipoSeguro> lista = new ArrayList<>();
	    
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	        Statement st = cn.createStatement();
	        ResultSet rs = st.executeQuery("SELECT * FROM tipoSeguros");

	        while (rs.next()) {
	            TipoSeguro tipo = new TipoSeguro();
	            tipo.setIdTipo(rs.getInt("idTipo"));
	            tipo.setDescripcion(rs.getString("descripcion"));
	            lista.add(tipo);
	        }

	        cn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return lista;
	}
	
	public int agregarSeguro(String descripcion, int tipo, double costoContratacion, double costoMaximo) {
	    int filas = 0;

	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	        Statement st = cn.createStatement();

	        String query = "INSERT INTO seguros (descripcion, idTipo, costoContratacion, costoAsegurado) VALUES ('"
	                + descripcion + "', " + tipo + ", " + costoContratacion + ", " + costoMaximo + ")";

	        filas = st.executeUpdate(query);

	        cn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return filas;
	}
	
	public ArrayList<Seguro> obtenerSeguros() {
		
		ArrayList<Seguro> lista = new ArrayList<>();
		try {
	        Class.forName("com.mysql.jdbc.Driver");
	        Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	        
	        String query = "SELECT S.idSeguro, S.idTipo, S.descripcion, TS.descripcion AS descripcionTipo, S.costoContratacion, S.costoAsegurado FROM seguros AS S JOIN tiposeguros AS TS ON S.idTipo = TS.idTipo";
	        Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);

	        while (rs.next()) {
	            Seguro segurodb = new Seguro();
                segurodb.setIdSeguro(rs.getInt("S.idSeguro"));
                segurodb.setIdTipo(rs.getInt("S.idTipo"));
                segurodb.setDescripcion(rs.getString("S.descripcion"));
                segurodb.setTipoDescripcion(rs.getString("descripcionTipo"));
                segurodb.setCostoAsegurado(rs.getFloat("s.costoAsegurado"));
                segurodb.setCostoContratacion(rs.getFloat("s.costoAsegurado"));
	            lista.add(segurodb);
	        }

	        cn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return lista;
	}

public ArrayList<Seguro> obtenerSeguros(int idTipoSeguro) {
		
		ArrayList<Seguro> lista = new ArrayList<>();
		try {
	        Class.forName("com.mysql.jdbc.Driver");
	        Connection cn = DriverManager.getConnection(host + dbName, user, pass);
	        String query = "SELECT S.idSeguro, S.idTipo, S.descripcion, TS.descripcion AS descripcionTipo, S.costoContratacion, S.costoAsegurado FROM seguros AS S JOIN tiposeguros AS TS ON S.idTipo = TS.idTipo WHERE TS.idTipo = ?";
			PreparedStatement st = cn.prepareStatement(query);
	        st.setInt(1, idTipoSeguro);
            ResultSet rs = st.executeQuery();

	        while (rs.next()) {

                Seguro segurodb = new Seguro();
                segurodb.setIdSeguro(rs.getInt("S.idSeguro"));
                segurodb.setIdTipo(rs.getInt("S.idTipo"));
                segurodb.setDescripcion(rs.getString("S.descripcion"));
                segurodb.setTipoDescripcion(rs.getString("descripcionTipo"));
                segurodb.setCostoAsegurado(rs.getFloat("s.costoAsegurado"));
                segurodb.setCostoContratacion(rs.getFloat("s.costoAsegurado"));
                lista.add(segurodb);
	        }

	        cn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return lista;
	}
}
