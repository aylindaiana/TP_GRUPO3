package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Conexion instancia;
    private Connection connection;

    private Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/tpintegrador", "root", "passwordLabIv");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tpintegrador", "root", "root");
            this.connection.setAutoCommit(false);
        } catch (Exception e) {
        	System.err.println(">>> ❌ Error al conectar con MySQL:");
            e.printStackTrace();
        }
    }

    public static Conexion getConexion() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection getSQLConexion() {
        return this.connection;
    }

    public void cerrarConexion() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        instancia = null;
    }
}
