package negocio;

import java.sql.Date;
import java.util.List;

import entidad.Prestamo;

public interface NegocioPrestamo {

	public boolean solicitarPrestamo(Prestamo prestamo);                   
    public List<Prestamo> obtenerPorIdCliente(int id);                 
    public List<Prestamo> listarPrestamos(); 
    
	public boolean actualizacionEstadoPrestamo(int id, int estado);  
	public boolean actualizacionDeTablasEnBase_EstadoPrestamo(int IDPrestamo, int IDCuentaDestino, 
			int EstadoPrestamo, int CantidadCuotas, double ImporteSolicitado, 
			int IDCuentaOrigen, Date FechaSolicitudPrestamo, String ComentarioMovimiento, String MotivoRechazoPrestamo);     

	public Prestamo obtenerPorIDPrestamo(int id);
}
