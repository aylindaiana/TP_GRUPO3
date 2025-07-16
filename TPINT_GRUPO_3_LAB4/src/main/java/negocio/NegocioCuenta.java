package negocio;

import java.time.LocalDate;
import java.util.List;

import entidad.Cuenta;

public interface NegocioCuenta {
    public boolean insertar(Cuenta cuenta);
    public boolean modificar(Cuenta cuenta);
    public Cuenta obtenerPorId(int id);
    public List<Cuenta> listar();
    public List<Cuenta> listarCliente(int idCliente);
    public boolean inactivarCuenta(int id);
    public boolean activarCuenta(int id);
    public boolean existeCBU(String cbu);
    public boolean existeCBUExceptoId(String cbu, int idCuenta);
    public List<Cuenta> buscar(String nombreCliente, String cbu);
    public int contarCuentasPorTipo(int tipoCuenta, LocalDate desde, LocalDate hasta);
    public int contarCuentas(LocalDate desde, LocalDate hasta);



    public boolean cantidadCuentas(int id);
    public List<Cuenta> listarCuentas(int id);
	public void recargarCuenta(int IDCuenta, double montoSolicitado);
	int cuentasActivasPorCliente(int idCliente);
	public void bajaCuentasUsuario(int id);
	public void debitarCuenta(int IDCuenta, double montoDebito);
	public int contarCuentasPorUsuario(int idusuario);
	public int contarCuentasTotalesImpl();
	public double saldoTotalBancario();
	public List<Cuenta> listarCuentasActivasPorCliente(int id);
	public double obtenerSaldoCuenta(int id);
}