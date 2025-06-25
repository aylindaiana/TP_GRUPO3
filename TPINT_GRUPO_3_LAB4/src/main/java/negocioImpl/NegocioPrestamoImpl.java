package negocioImpl;

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
	
}
