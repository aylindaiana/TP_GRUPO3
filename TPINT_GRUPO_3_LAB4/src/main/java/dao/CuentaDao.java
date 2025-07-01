package dao;
import java.util.List;
import entidad.Cuenta;

public interface CuentaDao {
    public boolean insertar(Cuenta cuenta);            
    public boolean modificar(Cuenta cuenta);                    
    public Cuenta obtenerPorId(int id);
    public List<Cuenta> listar();
    public boolean bajaLogica(int id);
    public boolean activar(int id);
    public boolean existeCBU(String cbu);
    public boolean existeCBUExceptoId(String cbu, int idCuenta);
    public List<Cuenta> buscar(String nombreCliente, String cbu);
    
    
    public boolean cantidadCuentas(int id);
    //borrador para pruebas de prestamos------------------------------------------------------------------------------------------------------------------------------
	public List<Cuenta> listarCuentas(int id);
	public void recargarCuenta(int IDCuenta, double montoSolicitado);
	int cuentasActivasPorCliente(int idCliente);
	public void bajaCuentasUsuario(int id);
	public void debitarCuenta(int IDCuenta, double montoDebito);
}