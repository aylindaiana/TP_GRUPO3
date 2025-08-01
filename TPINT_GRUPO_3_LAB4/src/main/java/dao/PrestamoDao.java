package dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import entidad.Cuenta;
import entidad.Prestamo;

public interface PrestamoDao {
	public boolean insertar(Prestamo prestamo);                   
    public List<Prestamo> obtenerPorIdCliente(int id);                 
    public List<Prestamo> listar();
	public boolean actualizacionEstadoPrestamo(int id, int estado);  
	public boolean actualizacionDeTablasEnBase_EstadoPrestamo(int IDPrestamo, int IDCuentaDestino, int EstadoPrestamo, 
			int CantidadCuotas, double ImporteSolicitado, int IDCuentaOrigen, 
			Date FechaSolicitudPrestamo, String ComentarioMovimiento, String MotivoRechazoPrestamo);     
	public Prestamo obtenerPorIDPrestamo(int id);                   
    public List<Prestamo> obtenerPorIdCuenta(int id);
	 /* Prueba para reporte */
	public int contarPrestamosAprobados(LocalDate desde, LocalDate hasta);
	public int contarPrestamosPendientes();
}
