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
    int cuentasActivasPorCliente(int idCliente);
    //borrador para pruebas de prestamos------------------------------------------------------------------------------------------------------------------------------
	public List<Cuenta> listarCuentas(int id);
	public void recargarCuenta(int IDCuenta, double montoSolicitado);
}