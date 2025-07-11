package negocioImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import entidad.Prestamo;
import negocio.NegocioPrestamo;
import dao.PrestamoDao;
import daoImpl.PrestamoDaoImpl;

public class NegocioPrestamoImpl implements NegocioPrestamo{
	private PrestamoDao dao = new PrestamoDaoImpl();
	@Override
	public boolean solicitarPrestamo(Prestamo prestamo) {
		
		return dao.insertar(prestamo);
	}

	@Override
	public List<Prestamo> obtenerPorIdCliente(int id) {
		return dao.obtenerPorIdCliente(id);
	}

	@Override
	public List<Prestamo> listarPrestamos() {
		
		return dao.listar();
	}

	@Override
	public boolean actualizacionEstadoPrestamo(int id, int estado) {
		return dao.actualizacionEstadoPrestamo(id, estado);
	}

	@Override
	public boolean actualizacionDeTablasEnBase_EstadoPrestamo(int IDPrestamo, int IDCuentaDestino, int EstadoPrestamo,
			int CantidadCuotas, double ImporteSolicitado, int IDCuentaOrigen, Date FechaSolicitudPrestamo,
			String ComentarioMovimiento, String MotivoRechazoPrestamo) {
		

		return dao.actualizacionDeTablasEnBase_EstadoPrestamo(IDPrestamo, IDCuentaDestino, EstadoPrestamo, 
				CantidadCuotas, ImporteSolicitado, IDCuentaOrigen, FechaSolicitudPrestamo, 
				ComentarioMovimiento, MotivoRechazoPrestamo);
	}

	@Override
	public Prestamo obtenerPorIDPrestamo(int id) {
		
		return dao.obtenerPorIDPrestamo(id);
	}
	
	/* prueba para los Reportes. */
    @Override
    public int contarPrestamosAprobados(LocalDate desde, LocalDate hasta) {
        return dao.contarPrestamosAprobados(desde, hasta);
    }
    
    @Override
    public int cantidadPrestamosPendientes() {
        return dao.contarPrestamosPendientes();
    }

	@Override
	public List<Prestamo> obtenerPorIdCuenta(int id) { 
		return dao.obtenerPorIdCuenta(id);
	}
}
