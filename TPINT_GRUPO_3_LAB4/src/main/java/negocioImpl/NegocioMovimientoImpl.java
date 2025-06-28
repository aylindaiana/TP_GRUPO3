package negocioImpl;

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
	

}
