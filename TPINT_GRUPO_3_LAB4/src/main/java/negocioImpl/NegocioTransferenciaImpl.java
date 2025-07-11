package negocioImpl;

import java.sql.Timestamp;
import java.util.List;

import dao.CuentaDao;
import dao.TransferenciaDao;
import daoImpl.TransferenciaDaoImpl;
import entidad.Transferencia;
import negocio.NegocioTransferencia;

public class NegocioTransferenciaImpl implements NegocioTransferencia {

	private TransferenciaDao transferenciaDao;
	
	
    @Override
    public boolean transferir(int cuentaOrigen, int cuentaDestino, double monto, String comentario) {
        TransferenciaDao dao = new TransferenciaDaoImpl();
        return dao.transferir(cuentaOrigen, cuentaDestino, monto, comentario);
    }

    @Override
    public List<Transferencia> listarTransferencias(
        int cuenta1,
        int cuenta2,
        int cuenta3,
        Timestamp fechaDesde,
        Timestamp fechaHasta,
        double montoMin,
        double montoMax,
        int offset,
        int limite
    ) {
        TransferenciaDao dao = new TransferenciaDaoImpl();
        return dao.listarTransferencias(
            cuenta1, cuenta2, cuenta3,
            fechaDesde, fechaHasta,
            montoMin, montoMax,
            offset, limite
        );
    }
    
    @Override
	public double saldoTotalDeTransfe() {
    	TransferenciaDao transferenciaDao = new TransferenciaDaoImpl();
		return transferenciaDao.saldoTotalEnTransferencia();
	}
}
