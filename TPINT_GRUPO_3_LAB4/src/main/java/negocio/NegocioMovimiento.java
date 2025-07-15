package negocio;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;
import entidad.Movimiento;

public interface NegocioMovimiento {

	public boolean nuevoMovimiento(Movimiento movimiento);

	public List<Movimiento> listarMovimientosPorCliente(int id);

	public List<Movimiento> listarMovimientosPorCuenta(int id);

	public List<Movimiento> listarMovimientos();

	public int ultimoIDGenerado();

	public List<Movimiento> listarUltimosMovimientos(int idCliente, int limite);

	public List<Movimiento> listarMovimientosConFiltros(int idCuenta, String tipo, double minMonto, double maxMonto,
			Date desde, Date hasta, String textoBusqueda, int offset, int limit);

	/* Prueba para reporte */
	public double obtenerTotalxTipo(int tipoMovimiento, LocalDate desde, LocalDate hasta);
}
