package negocioImpl;

import java.time.LocalDate;
import java.util.List;

import dao.MovimientoDao;
import daoImpl.MovimientoDaoImpl;
import entidad.Movimiento;
import negocio.NegocioMovimiento;

public class NegocioMovimientoImpl implements NegocioMovimiento{

	@Override
	public boolean nuevoMovimiento(Movimiento movimiento) {
		MovimientoDao dao = new MovimientoDaoImpl();
				
		return dao.insertar(movimiento);
	}

	@Override
	public List<Movimiento> listarMovimientosPorCuenta(int id) {
		MovimientoDao dao = new MovimientoDaoImpl();
		
		return dao.listarMovimientosPorCuenta(id);
	}
	
	@Override
	public List<Movimiento> listarMovimientosPorCliente(int id) {
		MovimientoDao dao = new MovimientoDaoImpl();
		
		return dao.listarMovimientosPorCliente(id);
	}

	@Override
	public List<Movimiento> listarMovimientos() {
		MovimientoDao dao = new MovimientoDaoImpl();
		
		return dao.listarMovimientos();
	}

	@Override
	public int ultimoIDGenerado() {
		MovimientoDao dao = new MovimientoDaoImpl();
		
		return dao.ultimoIDGenerado();
	}
	
	@Override
	public List<Movimiento> listarUltimosMovimientos(int idCliente, int limite) {
	    MovimientoDao dao = new MovimientoDaoImpl();
	    return dao.listarUltimosMovimientos(idCliente, limite);
	}
	
	/* Prueba para reporte */
    @Override
    public double obtenerTotalxTipo(int tipoMovimiento, LocalDate desde, LocalDate hasta) {
    	MovimientoDao dao = new MovimientoDaoImpl();
        return dao.obtenerTotalxTipo(tipoMovimiento, desde, hasta);
    }
    
    @Override
    public List<Movimiento> listarMovimientosConFiltros(int idCuenta, String tipo, double minMonto, double maxMonto, java.util.Date desde, java.util.Date hasta, String textoBusqueda, int offset, int limit) {
        MovimientoDao dao = new MovimientoDaoImpl();
        return dao.listarPorCuentaConFiltros(idCuenta, tipo, minMonto, maxMonto, desde, hasta, textoBusqueda, offset, limit);
    }

}
