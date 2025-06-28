package negocioImpl;

import java.util.List;

import entidad.Cuota;
import dao.CuotaDao;
import daoImpl.CuotaDaoImpl;
import negocio.NegocioCuota;

public class NegocioCuotaImpl implements NegocioCuota{

	@Override
	public boolean insertar(Cuota cuota) {
		CuotaDao dao = new CuotaDaoImpl();
		
		return dao.insertar(cuota);
	}

	@Override
	public List<Cuota> listarCuotasPorPrestamo(int id) {
		CuotaDao dao = new CuotaDaoImpl();
		
		return dao.listarCuotasPorPrestamo(id);
	}
	
}

