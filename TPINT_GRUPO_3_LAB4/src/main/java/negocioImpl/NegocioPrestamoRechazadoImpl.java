package negocioImpl;

import dao.PrestamoRechazadoDao;
import daoImpl.PrestamoRechazadoDaoImpl;
import entidad.PrestamoRechazado;
import negocio.NegocioPrestamoRechazado;

public class NegocioPrestamoRechazadoImpl implements NegocioPrestamoRechazado{

	@Override
	public boolean nuevoPrestamoRechazado(PrestamoRechazado prestamoRechazado) {
		PrestamoRechazadoDao dao = new PrestamoRechazadoDaoImpl();
		return dao.insert(prestamoRechazado);
	}

	@Override
	public PrestamoRechazado selectMotivoRechazoPrestamo(int IDPrestamo) {
		PrestamoRechazadoDao dao = new PrestamoRechazadoDaoImpl();
		return dao.selectMotivoRechazo(IDPrestamo);
	}
	
}
