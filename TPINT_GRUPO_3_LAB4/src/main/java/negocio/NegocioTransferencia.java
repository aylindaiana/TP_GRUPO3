package negocio;

public interface NegocioTransferencia {
    boolean transferir(int cuentaOrigen, int cuentaDestino, double monto, String comentario);
}
