package dao;

import java.sql.Timestamp;
import java.util.List;

import entidad.Transferencia;

public interface TransferenciaDao {


    boolean transferir(int cuentaOrigen, int cuentaDestino, double monto, String comentario);

 
    List<Transferencia> listarTransferencias(
        int cuenta1,
        int cuenta2,
        int cuenta3,
        Timestamp fechaDesde, 
        Timestamp fechaHasta, 
        double montoMin,
        double montoMax,
        int offset,
        int limite
    );

}
