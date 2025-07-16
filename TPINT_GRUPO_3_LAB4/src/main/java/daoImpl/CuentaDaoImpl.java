package daoImpl;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.CuentaDao;
import entidad.Cuenta;

public class CuentaDaoImpl implements CuentaDao {

    private static final String SELECT_ALL = "CALL SP_LISTAR_CUENTAS()";
    private static final String INSERT = "INSERT INTO cuenta (IDCliente, IDTipoDeCuenta, FechaDeCreacion, CBU, Saldo, Estado) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE cuenta SET IDCliente = ?, IDTipoDeCuenta = ?, FechaDeCreacion = ?, CBU = ?, Saldo = ?, Estado = ? WHERE ID = ?";
    private static final String SELECT_BY_ID = "CALL SP_OBTENER_CUENTA_POR_ID(?)";
    private static final String BAJA_LOGICA = "UPDATE cuenta SET Estado = 0 WHERE ID = ?";
    private static final String ACTIVAR = "UPDATE cuenta SET Estado = 1 WHERE ID = ?";
    private static final String CUENTAS_ACTIVAS_CLIENTE = "SELECT COUNT(*) FROM cuenta WHERE IDCliente = ? AND Estado = 1";
    private static final String LISTAR_CUENTAS_ACTIVAS_POR_CLIENTE = "CALL SP_LISTAR_CUENTAS_ACTIVAS_POR_CLIENTE(?)";
    private static final String EXISTE_CBU = "SELECT COUNT(*) FROM cuenta WHERE CBU = ?";
    private static final String EXISTE_CBU_EXCEPTO_ID = "SELECT COUNT(*) FROM cuenta WHERE CBU = ? AND ID <> ?";
    private static final String SELECT_ID_BY_CBU = "SELECT ID FROM cuenta WHERE CBU = ? AND Estado = 1";
    private static final String BUSCAR_CUENTAS_ASIGNADAS = "CALL sp_buscar_cuentas_asignadas(?)";
    private static final String BAJA_CLIENTE_BAJA_CUENTAS = "UPDATE cuenta SET Estado = 0 WHERE IDCliente = ?";
    private static final String RECARGAR_CUENTA = "CALL sp_recargar_cuenta(?, ?)";
    private static final String BUSCAR_CUENTAS = "CALL SP_BUSCAR_FILTRO(?, ?)";
    private static final String CONTAR_CUENTAS_ASIGNADA_A_CLIENTE = "CALL sp_buscar_cuentas_asignadas_a_cliente(?)";
    private static final String DEBITAR_CUENTA = "UPDATE cuenta SET Saldo = Saldo - ? WHERE ID = ?";
    private static final String CONTAR_CUENTA_TIPO = "CALL SP_CONTAR_CUENTAS_POR_TIPO(?, ?, ?)";
    private static final String CONTAR_CUENTAS = "CALL SP_CONTAR_CUENTAS_CREADAS(?, ?)";
    private static final String OBTENER_SALDO_CUENTA = "CALL SP_OBTENER_SALDO_CUENTA(?)";
    
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
            stmt.setDouble(5, 10000);
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
    

    private Cuenta mapearCuenta(ResultSet rs) throws SQLException {
        Cuenta c = new Cuenta();
        c.setId(rs.getInt("ID"));
        c.setIdCliente(rs.getInt("IDCliente"));
        c.setNombreCliente(rs.getString("NombreCliente"));
        c.setIdTipoDeCuenta(rs.getInt("IDTipoDeCuenta"));
        c.setFechaDeCreacion(rs.getDate("FechaDeCreacion"));
        c.setCbu(rs.getString("CBU"));
        c.setSaldo(rs.getDouble("Saldo"));
        c.setEstado(rs.getBoolean("Estado"));
        return c;
    }
    
    @Override
    public int cuentasActivasPorCliente(int idCliente) {
        int cantidad = 0;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(CUENTAS_ACTIVAS_CLIENTE)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cantidad = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cantidad;
    }
    
    @Override
    public boolean existeCBU(String cbu) {
        boolean existe = false;
        Connection conexion = Conexion.getConexion().getSQLConexion();

        try (PreparedStatement stmt = conexion.prepareStatement(EXISTE_CBU)) {
            stmt.setString(1, cbu);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return existe;
    }
    
    @Override
    public boolean existeCBUExceptoId(String cbu, int idCuenta) {
        boolean existe = false;
        Connection conexion = Conexion.getConexion().getSQLConexion();

        try (PreparedStatement stmt = conexion.prepareStatement(EXISTE_CBU_EXCEPTO_ID)) {
            stmt.setString(1, cbu);
            stmt.setInt(2, idCuenta);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return existe;
    }
    
    @Override
    public int obtenerIdCuentaPorCBU(String cbu) {
        int id = -1;
        Connection cn = Conexion.getConexion().getSQLConexion();

        try (PreparedStatement st = cn.prepareStatement(SELECT_ID_BY_CBU)) {
            st.setString(1, cbu);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                id = rs.getInt("ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }


	@Override
	public List<Cuenta> listarCuentas(int id){
		Connection cn = Conexion.getConexion().getSQLConexion();
		String query = "SELECT c.ID, c.IDCliente, c.IDTipoDeCuenta, " +
	               "ct.Descripcion AS DescripcionTipoDeCuenta, " +
	               "c.FechaDeCreacion, c.CBU, c.Saldo, c.Estado " +
	               "FROM cuenta c " +
	               "INNER JOIN cuenta_tipos ct ON c.IDTipoDeCuenta = ct.ID " +
	               "WHERE c.Estado = 1 AND c.IDCliente = " + id;
		ArrayList<Cuenta> lista = new ArrayList<>();

        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setId(rs.getInt("ID"));
                cuenta.setIdCliente(rs.getInt("IDCliente"));
                cuenta.setIdTipoDeCuenta(rs.getInt("IDTipoDeCuenta"));
                cuenta.setFechaDeCreacion(rs.getDate("FechaDeCreacion"));
                cuenta.setCbu(rs.getString("CBU"));
                cuenta.setDescripcionTipoDeCuenta(rs.getString("DescripcionTipoDeCuenta"));
                cuenta.setSaldo(rs.getDouble("Saldo"));
                cuenta.setEstado(rs.getBoolean("Estado"));
                
                lista.add(cuenta);
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
	}
	
	@Override
	public List<Cuenta> listarCuentasActivasPorCliente(int id){
		
		Connection cn = Conexion.getConexion().getSQLConexion();
		String query =  LISTAR_CUENTAS_ACTIVAS_POR_CLIENTE;
		List<Cuenta> lista = new ArrayList<>();

        
        try {
        	CallableStatement st = cn.prepareCall(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setId(rs.getInt("ID"));
                cuenta.setIdCliente(rs.getInt("IDCliente"));
                cuenta.setIdTipoDeCuenta(rs.getInt("IDTipoDeCuenta"));
                cuenta.setFechaDeCreacion(rs.getDate("FechaDeCreacion"));
                cuenta.setCbu(rs.getString("CBU"));
                cuenta.setSaldo(rs.getDouble("Saldo"));
                cuenta.setEstado(rs.getBoolean("Estado"));
                
                lista.add(cuenta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
	}
	
	

	@Override
	public double obtenerSaldoCuenta(int id){
		
		Connection cn = Conexion.getConexion().getSQLConexion();
		String query =  OBTENER_SALDO_CUENTA;
		double saldo = 0;
        
        try {
        	CallableStatement st = cn.prepareCall(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                saldo = rs.getDouble("Saldo");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return saldo;
	}
	

	@Override
	public void recargarCuenta(int IDCuenta, double montoSolicitado) {

		Connection cn = Conexion.getConexion().getSQLConexion();
		
		String query = RECARGAR_CUENTA;
		PreparedStatement st;
        
		try {
			st = cn.prepareStatement(query);
			st.setDouble(1, montoSolicitado);
	        st.setInt(2, IDCuenta);
	        if(st.executeUpdate() > 0) {
	            cn.commit();
	        }
		} catch (SQLException e) {
			e.printStackTrace();
            try {
                cn.rollback();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
		}
		
	}
	
	@Override
	public boolean bajaLogica(int id)
	{
		boolean resultado = false;
		PreparedStatement stmt = null;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		try {
			stmt = conexion.prepareStatement(BAJA_LOGICA);
			stmt.setInt(1, id);
			
		    if (PrestamosActivos(id)) {
		        return false;
		    }
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
	
	private boolean PrestamosActivos(int id) {
	    String query = "SELECT COUNT(*) FROM prestamos p " +
	                   "LEFT JOIN prestamo_rechazado pr ON p.ID = pr.IDPrestamo " +
	                   "WHERE p.IDCuenta = ? AND pr.IDPrestamo IS NULL";
	    
	    PreparedStatement stmt = null;
	    Connection conexion = Conexion.getConexion().getSQLConexion(); 
	    try{
	    	stmt = conexion.prepareStatement(query);
	        stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            return rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	
	@Override
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

	@Override
	public boolean cantidadCuentas(int id) {

		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement st;
        
        try  {

            st = cn.prepareStatement(BUSCAR_CUENTAS_ASIGNADAS);
            st.setInt(1, id);
        	
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int resultado = rs.getInt(1);
                if(resultado == 1) {
                	return true;
                }
                else {
                	return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public void bajaCuentasUsuario(int id) {

		PreparedStatement st = null;
		Connection cn = Conexion.getConexion().getSQLConexion();
		
		try {
			st = cn.prepareStatement(BAJA_CLIENTE_BAJA_CUENTAS);
			st.setInt(1, id);
			
			if (st.executeUpdate() > 0) {
				cn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			try { cn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
		}
		
	}
    @Override
    public List<Cuenta> buscar(String nombreCliente, String cbu) {
        List<Cuenta> lista = new ArrayList<>();
        Connection cn = Conexion.getConexion().getSQLConexion();;

        try {
            CallableStatement cs = cn.prepareCall(BUSCAR_CUENTAS);

            if (nombreCliente == null || nombreCliente.trim().isEmpty()) {
                cs.setNull(1, Types.VARCHAR);
            } else {
                cs.setString(1, (nombreCliente == null) ? "" : nombreCliente.trim());
            }

            if (cbu == null || cbu.trim().isEmpty()) {
                cs.setNull(2, Types.VARCHAR);
            } else {
                cs.setString(2, (cbu == null) ? "" : cbu.trim());
            }

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setId(rs.getInt("ID"));
                cuenta.setIdCliente(rs.getInt("IDCliente"));
                cuenta.setNombreCliente(rs.getString("nombre") + " " + rs.getString("apellido"));
                cuenta.setCbu(rs.getString("CBU"));
                cuenta.setIdTipoDeCuenta(rs.getInt("IDTipoDeCuenta"));
                cuenta.setFechaDeCreacion(rs.getDate("FechaDeCreacion"));
                cuenta.setSaldo(rs.getFloat("Saldo"));
                cuenta.setEstado(rs.getBoolean("Estado"));
                lista.add(cuenta);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

	@Override
	public void debitarCuenta(int IDCuenta, double montoDebito) {

		Connection cn = Conexion.getConexion().getSQLConexion();
		
		String query = DEBITAR_CUENTA;
		PreparedStatement st;
        
		try {
			st = cn.prepareStatement(query);
			st.setDouble(1, montoDebito);
	        st.setInt(2, IDCuenta);
	        if(st.executeUpdate() > 0) {
	            cn.commit();
	        }
		} catch (SQLException e) {
			e.printStackTrace();
            try {
                cn.rollback();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
		}
	}
	
	@Override
	public int contarCuentasDeUsuario(int idusuario)
	{
		Connection cn = Conexion.getConexion().getSQLConexion();
		PreparedStatement st;
        
        try  {

            st = cn.prepareStatement(CONTAR_CUENTAS_ASIGNADA_A_CLIENTE);
            st.setInt(1, idusuario);
        	
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int resultado = rs.getInt(1);
                return resultado;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
	}
	
	@Override
    public int contarCuentasTotalesActivas() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM cuenta WHERE Estado = 1";
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
	
	@Override
    public double saldoTotal() {
        double total = 0;
        String sql = "SELECT SUM(Saldo) AS DineroTotalActual FROM cuenta WHERE Estado = 1;";
        Connection conexion = Conexion.getConexion().getSQLConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                total = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
	@Override
	public int contarCuentasPorTipo(int tipoCuenta, LocalDate desde, LocalDate hasta) {
	    int total = 0;
	    Connection cn = Conexion.getConexion().getSQLConexion();

	    try {
	        CallableStatement st = cn.prepareCall(CONTAR_CUENTA_TIPO);
	        st.setInt(1, tipoCuenta);
	        st.setDate(2, java.sql.Date.valueOf(desde));
	        st.setDate(3, java.sql.Date.valueOf(hasta));
	        ResultSet rs = st.executeQuery();

	        if (rs.next()) {
	            total = rs.getInt("total");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return total;
	}
	
	@Override
	public int contarCuentas(LocalDate desde, LocalDate hasta) {
	    Connection cn = Conexion.getConexion().getSQLConexion();
	    int total = 0;
	    try {
	        CallableStatement st = cn.prepareCall(CONTAR_CUENTAS);
	        st.setDate(1, java.sql.Date.valueOf(desde));
	        st.setDate(2, java.sql.Date.valueOf(hasta));
	        ResultSet rs = st.executeQuery();
	        if (rs.next()) {
	            total = rs.getInt("total");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return total;
	}

}