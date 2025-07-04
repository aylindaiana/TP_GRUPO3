package negocio;

import java.sql.Date;
import java.util.List;

import entidad.Transferencia;

public interface NegocioTransferencia {

    boolean transferir(int cuentaOrigen, int cuentaDestino, double monto, String comentario);

    List<Transferencia> listarTransferencias(
        int cuenta1,
        int cuenta2,
        int cuenta3,
        Date fechaDesde,
        Date fechaHasta,
        double montoMin,
        double montoMax,
        int offset,
        int limite
    );
}
