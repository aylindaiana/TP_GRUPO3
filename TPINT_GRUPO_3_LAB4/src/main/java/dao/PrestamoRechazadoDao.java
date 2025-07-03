package dao;

import java.time.LocalDate;

import entidad.PrestamoRechazado;

public interface PrestamoRechazadoDao {
	public boolean insert(PrestamoRechazado prestamoRechazado);
	public PrestamoRechazado selectMotivoRechazo(int IDPrestamo);
	
	 /* Prueba para reporte */
	public int contarRechazos(LocalDate desde, LocalDate hasta);
}
