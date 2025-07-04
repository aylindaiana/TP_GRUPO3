package dao;

import java.sql.Date;
import java.util.List;

import entidad.Transferencia;

public interface TransferenciaDao {

    // Ejecuta la transferencia llamando al SP sp_transferir
    boolean transferir(int cuentaOrigen, int cuentaDestino, double monto, String comentario);

    // Lista transferencias de hasta 3 cuentas (propias) con filtros
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
