package negocio;

import java.time.LocalDate;

import entidad.PrestamoRechazado;

public interface NegocioPrestamoRechazado {

	public boolean nuevoPrestamoRechazado(PrestamoRechazado prestamoRechazado);
	public PrestamoRechazado selectMotivoRechazoPrestamo(int IDPrestamo);
	
	/* prueba para los Reportes. */
	public int contarRechazos(LocalDate desde, LocalDate hasta);
}
 