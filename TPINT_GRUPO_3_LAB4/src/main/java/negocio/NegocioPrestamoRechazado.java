package negocio;

import entidad.PrestamoRechazado;

public interface NegocioPrestamoRechazado {

	public boolean nuevoPrestamoRechazado(PrestamoRechazado prestamoRechazado);
	public PrestamoRechazado selectMotivoRechazoPrestamo(int IDPrestamo);
}
 