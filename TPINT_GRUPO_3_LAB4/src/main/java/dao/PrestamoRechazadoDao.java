package dao;

import entidad.PrestamoRechazado;

public interface PrestamoRechazadoDao {
	public boolean insert(PrestamoRechazado prestamoRechazado);
	public PrestamoRechazado selectMotivoRechazo(int IDPrestamo);
}
