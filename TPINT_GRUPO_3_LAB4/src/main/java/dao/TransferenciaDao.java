package dao;

public interface TransferenciaDao {
    boolean transferir(int cuentaOrigen, int cuentaDestino, double monto, String comentario);
}