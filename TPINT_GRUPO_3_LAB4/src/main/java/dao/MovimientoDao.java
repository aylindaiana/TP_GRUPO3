package dao;

import java.util.Date;
import java.time.LocalDate;
import java.util.List;
import entidad.Movimiento;

public interface MovimientoDao {

	public boolean insertar(Movimiento movimiento);

	public List<Movimiento> listarMovimientosPorCliente(int id);

	public List<Movimiento> listarMovimientosPorCuenta(int id);

	public List<Movimiento> listarMovimientos();

	public int ultimoIDGenerado();

	public List<Movimiento> listarUltimosMovimientos(int idCliente, int limite);

	public List<Movimiento> listarPorCuentaConFiltros(int idCuenta, String tipo, double minMonto, double maxMonto, Date desde,
			Date hasta, String textoBusqueda, int offset, int limit);

	/* Prueba para reporte */
	public double obtenerTotalxTipo(int tipoDeMovimiento, LocalDate desde, LocalDate hata);
}
