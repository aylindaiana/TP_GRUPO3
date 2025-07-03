package negocioImpl;

import java.time.LocalDate;
import java.util.List;

import entidad.Cuota;
import dao.CuotaDao;
import daoImpl.CuotaDaoImpl;
import negocio.NegocioCuota;

public class NegocioCuotaImpl implements NegocioCuota{
	private CuotaDao dao;

	public NegocioCuotaImpl() {
		super();
		dao = new CuotaDaoImpl();
	}

	@Override
	public boolean insertar(Cuota cuota) {
		
		return dao.insertar(cuota);
	}

	@Override
	public List<Cuota> listarCuotasPorPrestamo(int id) {
		
		return dao.listarCuotasPorPrestamo(id);
	}

	@Override
	public boolean pagarCuota(int id) {
	
		return dao.pagarCuota(id);
	}
	
    @Override
    public int contarCuotasPagadas(LocalDate desde, LocalDate hasta) {
        return dao.contarCuotasPagadas(desde, hasta);
    }

    @Override
    public double totalRecaudadoEnCuotas(LocalDate desde, LocalDate hasta) {
        return dao.totalRecaudadoEnCuotas(desde, hasta);
    }
	
}

