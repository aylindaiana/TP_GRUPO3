package negocioImpl;

import java.sql.Date;
import java.util.List;

import entidad.Prestamo;
import negocio.NegocioPrestamo;
import dao.PrestamoDao;
import daoImpl.PrestamoDaoImpl;

public class NegocioPrestamoImpl implements NegocioPrestamo{

	@Override
	public boolean solicitarPrestamo(Prestamo prestamo) {
		PrestamoDao dao = new PrestamoDaoImpl();
		
		return dao.insertar(prestamo);
	}

	@Override
	public List<Prestamo> obtenerPorIdCliente(int id) {
		PrestamoDao dao = new PrestamoDaoImpl();
		
		return dao.obtenerPorIdCliente(id);
	}

	@Override
	public List<Prestamo> listarPrestamos() {
		PrestamoDao dao = new PrestamoDaoImpl();
		
		return dao.listar();
	}

	@Override
	public boolean actualizacionEstadoPrestamo(int id, int estado) {
		PrestamoDao dao = new PrestamoDaoImpl();
		return dao.actualizacionEstadoPrestamo(id, estado);
	}

	@Override
	public boolean actualizacionDeTablasEnBase_EstadoPrestamo(int IDPrestamo, int IDCuentaDestino, int EstadoPrestamo,
			int CantidadCuotas, double ImporteSolicitado, int IDCuentaOrigen, Date FechaSolicitudPrestamo,
			String ComentarioMovimiento, String MotivoRechazoPrestamo) {
		PrestamoDao dao = new PrestamoDaoImpl();

		return dao.actualizacionDeTablasEnBase_EstadoPrestamo(IDPrestamo, IDCuentaDestino, EstadoPrestamo, 
				CantidadCuotas, ImporteSolicitado, IDCuentaOrigen, FechaSolicitudPrestamo, 
				ComentarioMovimiento, MotivoRechazoPrestamo);
	}
	
}
