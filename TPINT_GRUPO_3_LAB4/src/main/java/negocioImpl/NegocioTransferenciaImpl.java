package negocioImpl;

import java.sql.Date;
import java.util.List;

import dao.TransferenciaDao;
import daoImpl.TransferenciaDaoImpl;
import entidad.Transferencia;
import negocio.NegocioTransferencia;

public class NegocioTransferenciaImpl implements NegocioTransferencia {

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
        Date fechaDesde,
        Date fechaHasta,
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
}
