package negocioImpl;

import dao.TransferenciaDao;
import daoImpl.TransferenciaDaoImpl;
import negocio.NegocioTransferencia;

public class NegocioTransferenciaImpl implements NegocioTransferencia {

    @Override
    public boolean transferir(int cuentaOrigen, int cuentaDestino, double monto, String comentario) {
        TransferenciaDao dao = new TransferenciaDaoImpl();
        return dao.transferir(cuentaOrigen, cuentaDestino, monto, comentario);
    }
}
