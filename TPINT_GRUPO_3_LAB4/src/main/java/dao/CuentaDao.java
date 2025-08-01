package dao;
import java.time.LocalDate;
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
    int obtenerIdCuentaPorCBU(String cbu);
    public List<Cuenta> buscarAvanzado(String nombreCliente, String cbu, String fechaDesde, String fechaHasta, String tipoCuenta);
    public int contarCuentasPorTipo(int tipoCuenta, LocalDate desde, LocalDate hasta);
    public int contarCuentas(LocalDate desde, LocalDate hasta);


    
    
    public boolean cantidadCuentas(int id);
    //borrador para pruebas de prestamos------------------------------------------------------------------------------------------------------------------------------
	public List<Cuenta> listarCuentas(int id);
	public void recargarCuenta(int IDCuenta, double montoSolicitado);
	int cuentasActivasPorCliente(int idCliente);
	public void bajaCuentasUsuario(int id);
	public void debitarCuenta(int IDCuenta, double montoDebito);
	public int contarCuentasDeUsuario(int idusuario);
	public int contarCuentasTotalesActivas();
	public double saldoTotal();
	public List<Cuenta> listarCuentasActivasPorCliente(int id);
	double obtenerSaldoCuenta(int id);
}